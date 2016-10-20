package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.IdiomaService;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoOCRRecuperarImagenesSCService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.hip.action.ReporteHIPImagenesSCAction;
import biz.belcorp.ssicc.web.spusicc.mae.action.ReporteMAECargaClasificacionClientesAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ConsultaOCRSolicitudesCreditoForm;

/**
 * @author jpulido
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ConsultaOCRSolicitudesCreditoAction extends
		BaseProcesoAbstractAction {
	private static final long serialVersionUID = 1L;

	private List ocrDatosClienteEscaneadosSCList = new ArrayList();
	private String ocrCarpetaMasiva = "";
	private String attachment1 = "";
	private String attachment2 = "";
	private String valorFocus;
	private static String CODIGO_SISTEMA = "OCR";
	private static String CODIGO_PROCESO_BATCH = "11";
	private DataTableModel resultadoBusqueda = new DataTableModel();
	private Boolean verPDF;
	
	@ManagedProperty(value = "#{reporteHIPImagenesSCAction}")
	private ReporteHIPImagenesSCAction reporteHIPImagenesSC;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ConsultaOCRSolicitudesCreditoForm f = new ConsultaOCRSolicitudesCreditoForm();
		return f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #executeProcess(java.util.Map)
	 */
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("executeProcess");
		}
		ConsultaOCRSolicitudesCreditoForm f = (ConsultaOCRSolicitudesCreditoForm) this.formProceso;

		ProcesoOCRRecuperarImagenesSCService service = (ProcesoOCRRecuperarImagenesSCService) getBean("spusicc.pedido.procesoOCRRecuperarImagenesSCService");

		// Generamos los pdfs de las consultoras cargadas del archivo de texto
		service.executeGenerarConsultaMasivaSC(params);

		return params;
	}
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setViewAtributes");
		}
		this.mostrarPanelAdicionalProceso = false;
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		ConsultaOCRSolicitudesCreditoForm f = (ConsultaOCRSolicitudesCreditoForm) this.formProceso;
		this.verPDF=false;

		// Recuperamos el pais logueado
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		this.mostrarListaBusqueda = false;
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());

		f.setCodigoCliente("");
		f.setNumeroDocIdentidad("");
		f.setClienteFile(null);
		f.setClienteFile2(null);
		f.setMostrarReporteImagenesSC(false);
		f.setMostrarListaDatos(false);

		// longitud de codigo de cliente para el pais
		f.setLongitudCodigoCliente(clienteService.getLongitudCodigoCliente(criteria));
		this.valorFocus = "1";
		this.ocrCarpetaMasiva = "";
	}

	/**
	 * 
	 */
	public void setFind() {
		if (log.isDebugEnabled())
			log.debug("setFind");

		ConsultaOCRSolicitudesCreditoForm f = (ConsultaOCRSolicitudesCreditoForm) this.formProceso;
		this.verPDF=false;

		if (StringUtils.isNotBlank(f.getCodigoCliente())) {
			if (f.getCodigoCliente().length() <= Integer.parseInt(f.getLongitudCodigoCliente()))
				f.setCodigoCliente(StringUtils.leftPad(f.getCodigoCliente(), Integer.parseInt(f.getLongitudCodigoCliente()),
						"0"));
			else {
				int tamanio = f.getCodigoCliente().length() - Integer.parseInt(f.getLongitudCodigoCliente());
				String codFinal = f.getCodigoCliente().substring(tamanio);
				f.setCodigoCliente(codFinal);
			}
		}

		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		// Recuperamos el idioma
		IdiomaService idiomaService = (IdiomaService) getBean("idiomaService");
		String s = this.getmPantallaPrincipalBean().getLocale();
		Idioma idioma = idiomaService.getIdiomaByCodigoISO(s);
		Map parameterMap = new HashMap();
		parameterMap.put("codigoIdiomaIso", idioma.getCodigoSiCC());
		parameterMap.put("codigoIdioma", idioma.getCodigoISO());
		String oidIdiomaIso = reporteService.getOidString(
				"getOidIdiomaByCodigoIdiomaIso", parameterMap);
		f.setOidIdioma(oidIdiomaIso);

		/* obteniendo valores */
		Map criterios = new HashMap();
		criterios.put("codigoPais", f.getCodigoPais());
		criterios.put("codigoCliente", f.getCodigoCliente());
		criterios.put("oidIdioma", f.getOidIdioma());
		criterios.put("numeroDocIdentidad", f.getNumeroDocIdentidad());

		/* Obteniendo los datos generales del Cliente */
		Map mapDatosGenerales = service.getDatosGenerales(criterios);
		if (mapDatosGenerales != null) {
			String codigoCliente = (String) mapDatosGenerales
					.get("codigoCliente");

			f.setCodigoCliente(codigoCliente);
			// validamos si se muestra el icono de reporte de imagenes
			// escaneadas de S/C
			f.setMostrarReporteImagenesSC(service
					.validarImagenesEscaneoSC(codigoCliente));

			if (!f.isMostrarReporteImagenesSC()) {
				this.addError(
						"Error:",
						this.getResourceMessage("consultaOCRSolicitudesCreditoForm.msg.imagenesNoEscaneados"));
			}else{
				this.verPDF=true;
				f.setCodigoCliente(codigoCliente);
				
			}

		} else {
			f.setMostrarReporteImagenesSC(false);
			this.addError(
					"Error:",
					this.getResourceMessage("consultaOCRSolicitudesCreditoForm.msg.clienteNoEncontrado"));
		}

		f.setMostrarListaDatos(false);
		this.valorFocus = "1";
		
	}
	
	/**
	 * @param evt
	 */
	public void abrirImagenPDF(ActionEvent evt){
		try {
			ConsultaOCRSolicitudesCreditoForm f = (ConsultaOCRSolicitudesCreditoForm) this.formProceso;
			this.reporteHIPImagenesSC.setCodigoCliente(f.getCodigoCliente());
			this.reporteHIPImagenesSC.executeReporteImage();
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}

	/**
	 * @param event
	 */
	public void handleFileUpload1(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload1");
		}
		ConsultaOCRSolicitudesCreditoForm f = (ConsultaOCRSolicitudesCreditoForm) formProceso;
		if (event.getFile() != null) {
			f.setTipoArchivo("C");
			f.setClienteFile(event.getFile());
			loadFile(event.getFile());
		}
	}

	/**
	 * @param event
	 */
	public void handleFileUpload2(FileUploadEvent event) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("handleFileUpload2");
			}
			ConsultaOCRSolicitudesCreditoForm f = (ConsultaOCRSolicitudesCreditoForm) formProceso;
			if (event.getFile() != null) {
				f.setTipoArchivo("D");
				f.setClienteFile2(event.getFile());
				loadFile(event.getFile());
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));

		}

	}

	/**
	 * @param archivo
	 */
	public void loadFile(UploadedFile archivo) {
		if (log.isDebugEnabled()) {
			log.debug("loadFile");
		}
		this.mostrarPanelAdicionalProceso = false;

		ProcesoOCRRecuperarImagenesSCService service = (ProcesoOCRRecuperarImagenesSCService) getBean("spusicc.pedido.procesoOCRRecuperarImagenesSCService");
		ConsultaOCRSolicitudesCreditoForm f = (ConsultaOCRSolicitudesCreditoForm) formProceso;
		f.setCodigoCliente("");
		f.setNumeroDocIdentidad("");

		List listaDatos = new ArrayList();

		InputStream is;
		try {
			is = archivo.getInputstream();

			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String linea = "";

			PaisService paisService = (PaisService) getBean("paisService");

			int contFilas = 0;
			while (true) {
				linea = br.readLine();
				if (linea == null)
					break;

				if (StringUtils.isNotEmpty(linea)) {
					contFilas++;
					listaDatos.add(linea);
				}
			}

			Map params = new HashMap();
			params.put("tipoArchivo", f.getTipoArchivo());
			params.put("listaDatos", listaDatos);
			List listaConsulta = service.getConsultaMasivaSC(params);

			this.setOcrDatosClienteEscaneadosSCList(listaConsulta);
			this.resultadoBusqueda = new DataTableModel(
					this.getOcrDatosClienteEscaneadosSCList());
			this.ocrCarpetaMasiva = service.getIDCarpeta();

			f.setTotalConsultora(String.valueOf(listaConsulta.size()));
			f.setMostrarReporteImagenesSC(false);
			f.setMostrarListaDatos(true);
			if(listaConsulta.size() > 0 ){
				this.mostrarPanelAdicionalProceso = false;

			}

			if (f.getTipoArchivo().equals(
					Constants.OCR_TIPO_CODIGO_CLIENTE_ESCANEADOS_SC)) // Codigo
																		// de
																		// Clientes
				this.setAttachment1(archivo.getFileName());
			else
				this.setAttachment2(archivo.getFileName());

			this.mostrarPanelAdicionalProceso = true;
		} catch (IOException e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #prepareParamsBeforeExecute(java.util.Map)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParamsBeforeExecute");
		}
		
		params = super.prepareParamsBeforeExecute(params,form);
		
		MantenimientoSTOBloqueoControlService stoService = (MantenimientoSTOBloqueoControlService) getBean("spusicc.mantenimientoSTOBloqueoControlService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ConsultaOCRSolicitudesCreditoForm f = (ConsultaOCRSolicitudesCreditoForm) this.formProceso;
		List listaDatos = this.getOcrDatosClienteEscaneadosSCList();
		String servidorFtpSC ="";
		String puertoFtpSC = "";
		String directorioFtpSC ="";
		// -- Seteamos el Codigo de Pais y Codigo de Sistema
		Map criteria = new HashMap();
		criteria.put("codigoPais",pais.getCodigo());
		criteria.put("codigoSistema", CODIGO_SISTEMA);

		// -- obtenemos los valores para comunicarse con el servidor FTP
		criteria.put("nombreParametro", "servidorFtpSC");
		servidorFtpSC = stoService.getParametroGenericoSistema(criteria);
		criteria.put("nombreParametro", "puertoFtpSC");
		puertoFtpSC = stoService.getParametroGenericoSistema(criteria);
		criteria.put("nombreParametro", "directorioFtpSC");
		directorioFtpSC = stoService
				.getParametroGenericoSistema(criteria);
		criteria.put("nombreParametro", "usuarioFtpSC");
		String usuarioFtpSC = stoService.getParametroGenericoSistema(criteria);
		criteria.put("nombreParametro", "passwordFtpSC");
		String passwordFtpSC = stoService.getParametroGenericoSistema(criteria);
		criteria.put("nombreParametro", "directorioTempSC");
		String directorioTempSC = stoService
				.getParametroGenericoSistema(criteria);
		criteria.put("nombreParametro", "directorioFtpMasivSC");
		String directorioFtpMasivSC = stoService
				.getParametroGenericoSistema(criteria);
		criteria.put("nombreParametro", "scaleFitSC");
		String scaleFitSC = stoService.getParametroGenericoSistema(criteria);

		params.put("servidorFtpSC", servidorFtpSC);
		params.put("puertoFtpSC", puertoFtpSC);
		params.put("directorioFtpSC", directorioFtpSC);
		params.put("usuarioFtpSC", usuarioFtpSC);
		params.put("passwordFtpSC", passwordFtpSC);
		params.put("directorioTempSC", directorioTempSC);
		params.put("directorioFtpMasivSC", directorioFtpMasivSC);
		params.put("scaleFitSC", scaleFitSC);
		params.put("listaDatos", listaDatos);
		params.put("carpetaMasiva", this.ocrCarpetaMasiva);

		if (log.isDebugEnabled())
			log.debug("Parametros: " + params);

		String carpetaMasiva = (String) params.get("carpetaMasiva");
		// ActionMessages messages = new ActionMessages();
		// messages.add(ActionMessages.GLOBAL_MESSAGE, new
		// ActionMessage("consultaOCRSolicitudesCreditoForm.ok",
		// carpetaMasiva));
		// saveMessages(request.getSession(), messages);

		// request.getSession().removeAttribute(Constants.OCR_DATOS_CLIENTE_ESCANEADOS_SC_LIST);

		f.setMostrarListaDatos(false);
		return params;
	}

	/**
	 * Ceros izquierda.
	 * 
	 * @param query
	 *            the query
	 * @return the list
	 */
	public void cerosIzquierda() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cerosIzquierda' method");
		}

		ConsultaOCRSolicitudesCreditoForm f = (ConsultaOCRSolicitudesCreditoForm) this.formProceso;

		if (StringUtils.isNotBlank(f.getCodigoCliente())) {
			if (f.getCodigoCliente().length() <= Integer.parseInt(f.getLongitudCodigoCliente()))
				f.setCodigoCliente(StringUtils.leftPad(f.getCodigoCliente(),Integer.parseInt(f.getLongitudCodigoCliente()),
						"0"));
			else {
				int tamanio = f.getCodigoCliente().length() - Integer.parseInt(f.getLongitudCodigoCliente());
				String codFinal = f.getCodigoCliente().substring(tamanio);
				f.setCodigoCliente(codFinal);
			}
		}
	}

	/**
	 * @return
	 */
	public List getOcrDatosClienteEscaneadosSCList() {
		return ocrDatosClienteEscaneadosSCList;
	}

	/**
	 * @param ocrDatosClienteEscaneadosSCList
	 */
	public void setOcrDatosClienteEscaneadosSCList(
			List ocrDatosClienteEscaneadosSCList) {
		this.ocrDatosClienteEscaneadosSCList = ocrDatosClienteEscaneadosSCList;
	}

	/**
	 * @return
	 */
	public String getOcrCarpetaMasiva() {
		return ocrCarpetaMasiva;
	}

	/**
	 * @param ocrCarpetaMasiva
	 */
	public void setOcrCarpetaMasiva(String ocrCarpetaMasiva) {
		this.ocrCarpetaMasiva = ocrCarpetaMasiva;
	}

	/**
	 * @return
	 */
	public String getAttachment1() {
		return attachment1;
	}

	/**
	 * @param attachment1
	 */
	public void setAttachment1(String attachment1) {
		this.attachment1 = attachment1;
	}

	/**
	 * @return
	 */
	public String getAttachment2() {
		return attachment2;
	}

	/**
	 * @param attachment2
	 */
	public void setAttachment2(String attachment2) {
		this.attachment2 = attachment2;
	}

	/**
	 * @return
	 */
	public String getValorFocus() {
		return valorFocus;
	}

	/**
	 * @param valorFocus
	 */
	public void setValorFocus(String valorFocus) {
		this.valorFocus = valorFocus;
	}

	/**
	 * @return
	 */
	public DataTableModel getResultadoBusqueda() {
		return resultadoBusqueda;
	}

	/**
	 * @param resultadoBusqueda
	 */
	public void setResultadoBusqueda(DataTableModel resultadoBusqueda) {
		this.resultadoBusqueda = resultadoBusqueda;
	}

	/**
	 * @return the reporteHIPImagenesSC
	 */
	public ReporteHIPImagenesSCAction getReporteHIPImagenesSC() {
		return reporteHIPImagenesSC;
	}

	/**
	 * @param reporteHIPImagenesSC the reporteHIPImagenesSC to set
	 */
	public void setReporteHIPImagenesSC(
			ReporteHIPImagenesSCAction reporteHIPImagenesSC) {
		this.reporteHIPImagenesSC = reporteHIPImagenesSC;
	}

	/**
	 * @return the verPDF
	 */
	public Boolean getVerPDF() {
		return verPDF;
	}

	/**
	 * @param verPDF the verPDF to set
	 */
	public void setVerPDF(Boolean verPDF) {
		this.verPDF = verPDF;
	}
	
	
}