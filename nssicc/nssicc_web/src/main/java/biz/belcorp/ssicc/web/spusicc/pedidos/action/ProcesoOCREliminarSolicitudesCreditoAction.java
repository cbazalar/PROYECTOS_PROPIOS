package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.LazyDataModel;
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
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoOCREliminarSolicitudesCreditoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoOCREliminarSolicitudesCreditoAction extends BaseProcesoAbstractAction 
{
	private static final long serialVersionUID = 1L;
	private List ocrDatosClienteEscaneadosSCList = new ArrayList();
	private String attachment1 = "";
	private String attachment2 = "";
	private String valorFocus;
	private DataTableModel resultadoBusqueda = new DataTableModel();
	private Boolean indicadorConsultorasInd;
	private Boolean indicadorConsultorasFile1;
	private Boolean indicadorConsultorasFile2;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoOCREliminarSolicitudesCreditoForm f = new ProcesoOCREliminarSolicitudesCreditoForm();
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
		ProcesoOCRRecuperarImagenesSCService service = (ProcesoOCRRecuperarImagenesSCService) getBean("spusicc.pedido.procesoOCRRecuperarImagenesSCService");
		ProcesoOCREliminarSolicitudesCreditoForm f = (ProcesoOCREliminarSolicitudesCreditoForm) this.formProceso;

		service.executeDeleteMasivaSC(params);
		/*
		String mensajeEjecucion = MapUtils.getString(params, "mensajeEjecucion");
		if (mensajeEjecucion!=null && StringUtils.isNotBlank(mensajeEjecucion) && StringUtils.isNotEmpty(mensajeEjecucion))
		{
			this.addWarn("", this.getResourceMessage(mensajeEjecucion));
		}
		*/

		this.setOcrDatosClienteEscaneadosSCList(new ArrayList());
		f.setMostrarListaDatos(false);

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
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		ProcesoOCREliminarSolicitudesCreditoForm f = (ProcesoOCREliminarSolicitudesCreditoForm) this.formProceso;

		// Recuperamos el pais logueado
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());

		f.setCodigoCliente("");
		f.setNumeroDocIdentidad("");
		f.setClienteFile(null);
		f.setClienteFile2(null);
		f.setMostrarListaDatos(false);
		f.setIndicadorConsultorasInd("0");
		f.setIndicadorConsultorasFile1("0");
		f.setIndicadorConsultorasFile2("0");
		this.indicadorConsultorasFile1 = false;
		this.indicadorConsultorasInd = false;
		this.indicadorConsultorasFile2 = false;

		// longitud de codigo de cliente para el pais
		f.setLongitudCodigoCliente(clienteService
				.getLongitudCodigoCliente(criteria));
		this.valorFocus = "1";
		this.mostrarPanelAdicionalProceso = false;
		this.cantidadRowsDefaultDatatable = "50";
	}

	/**
	 * 
	 */
	public void setFind() {
		if (log.isDebugEnabled()) {
			log.debug("setFindAttributes");
		}
		try {
			this.mostrarPanelAdicionalProceso = false;

			ProcesoOCREliminarSolicitudesCreditoForm f = (ProcesoOCREliminarSolicitudesCreditoForm) this.formProceso;
			ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

			ProcesoOCRRecuperarImagenesSCService imagenesService = (ProcesoOCRRecuperarImagenesSCService) getBean("spusicc.pedido.procesoOCRRecuperarImagenesSCService");

			if(indicadorConsultorasInd)
				f.setIndicadorConsultorasInd("1");
			else
				f.setIndicadorConsultorasInd("0");
			
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
			List listaConsulta = new ArrayList();
			if (mapDatosGenerales != null) 
			{
				String codigoCliente = (String) mapDatosGenerales.get("codigoCliente");
				List listaDatos = new ArrayList();
				listaDatos.add(codigoCliente);

				Map params = new HashMap();
				params.put("tipoArchivo", Constants.OCR_TIPO_CODIGO_CLIENTE_ESCANEADOS_SC);
				params.put("listaDatos", listaDatos);
				params.put("indRetiradas", f.getIndicadorConsultorasInd());

				listaConsulta = imagenesService.getConsultaMasivaSC(params);

				this.ocrDatosClienteEscaneadosSCList = listaConsulta;
				f.setTotalConsultora(String.valueOf(listaConsulta.size()));

			} else {
				this.ocrDatosClienteEscaneadosSCList = listaConsulta;
				f.setTotalConsultora(String.valueOf(listaConsulta.size()));
			}
			this.resultadoBusqueda = new DataTableModel(
					this.getOcrDatosClienteEscaneadosSCList());
			f.setMostrarListaDatos(true);
			this.mostrarPanelAdicionalProceso = true;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param e
	 */
	public void buscar(ActionEvent e) {
		if (log.isDebugEnabled()) {
			log.debug("buscar");
		}
		try {
			this.ocrDatosClienteEscaneadosSCList = new ArrayList();
			this.setOcrDatosClienteEscaneadosSCList(this.ocrDatosClienteEscaneadosSCList);
			this.resultadoBusqueda = new DataTableModel(this.getOcrDatosClienteEscaneadosSCList());
			this.mostrarPanelAdicionalProceso = true;
			ProcesoOCREliminarSolicitudesCreditoForm f = (ProcesoOCREliminarSolicitudesCreditoForm) this.formProceso;
			ProcesoOCRRecuperarImagenesSCService imagenesService = (ProcesoOCRRecuperarImagenesSCService) getBean("spusicc.pedido.procesoOCRRecuperarImagenesSCService");
			if (this.indicadorConsultorasFile1) {
				f.setIndicadorConsultorasFile1("1");
			} else
				f.setIndicadorConsultorasFile1("0");
			if (this.indicadorConsultorasFile2) {
				f.setIndicadorConsultorasFile2("1");
			} else
				f.setIndicadorConsultorasFile2("0");
			if (this.indicadorConsultorasInd) {
				f.setIndicadorConsultorasInd("1");
			} else
				f.setIndicadorConsultorasInd("0");
			Map params = new HashMap();
			params.put("indRetiradas", "1");
			List listaConsulta = imagenesService
					.getConsultaTodasMasivaSC(params);
			log.debug("tamanio lista: " + listaConsulta.size());
			this.mostrarPanelAdicionalProceso = true;

			this.ocrDatosClienteEscaneadosSCList = listaConsulta;
			f.setTotalConsultora(String.valueOf(listaConsulta.size()));

			f.setMostrarListaDatos(true);
		} catch (Exception e2) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e2));
		}

	}

	/**
	 * @param event
	 */
	public void handleFileUpload1(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload1");
		}
		try {
			ProcesoOCREliminarSolicitudesCreditoForm f = (ProcesoOCREliminarSolicitudesCreditoForm) formProceso;
			if(this.indicadorConsultorasFile1)
				f.setIndicadorConsultorasFile1("1");
			else
				f.setIndicadorConsultorasFile1("0");
			
			if (event.getFile() != null) {
				f.setTipoArchivo("C");
				f.setClienteFile(event.getFile());
				loadfile(event.getFile());
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param event
	 */
	public void handleFileUpload2(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload2");
		}
		try {
			ProcesoOCREliminarSolicitudesCreditoForm f = (ProcesoOCREliminarSolicitudesCreditoForm) formProceso;
			if(this.indicadorConsultorasFile2)
				f.setIndicadorConsultorasFile2("1");
			else
				f.setIndicadorConsultorasFile2("0");
			
			if (event.getFile() != null) {
				f.setTipoArchivo("D");
				f.setClienteFile2(event.getFile());
				loadfile(event.getFile());
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param archivo
	 */
	public void loadfile(UploadedFile archivo) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'load Clientes from file' method");
		}

		ProcesoOCRRecuperarImagenesSCService service = (ProcesoOCRRecuperarImagenesSCService) getBean("spusicc.pedido.procesoOCRRecuperarImagenesSCService");
		ProcesoOCREliminarSolicitudesCreditoForm f = (ProcesoOCREliminarSolicitudesCreditoForm) this.formProceso;
		f.setCodigoCliente("");
		f.setNumeroDocIdentidad("");

		List listaDatos = new ArrayList();
		try {

			if (f.getTipoArchivo().equals(
					Constants.OCR_TIPO_CODIGO_CLIENTE_ESCANEADOS_SC))
				archivo = f.getClienteFile();
			else
				archivo = f.getClienteFile2();

			InputStream is = archivo.getInputstream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String linea = "";

			PaisService paisService = (PaisService) getBean("paisService");
			Pais pais = paisService.getPais(f.getCodigoPais());

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

			if (f.getTipoArchivo().equals(Constants.OCR_TIPO_CODIGO_CLIENTE_ESCANEADOS_SC))
				params.put("indRetiradas", f.getIndicadorConsultorasFile1());
			else
				params.put("indRetiradas", f.getIndicadorConsultorasFile2());

			List listaConsulta = service.getConsultaMasivaSC(params);

			this.setOcrDatosClienteEscaneadosSCList(listaConsulta);
			this.resultadoBusqueda = new DataTableModel(
					this.getOcrDatosClienteEscaneadosSCList());
			f.setTotalConsultora(String.valueOf(listaConsulta.size()));
			f.setMostrarListaDatos(true);
			this.mostrarPanelAdicionalProceso = true;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));

		}
		/*
		 * f.setIndicadorConsultorasInd("0");
		 * 
		 * if(f.getTipoArchivo().equals(Constants.
		 * OCR_TIPO_CODIGO_CLIENTE_ESCANEADOS_SC))
		 * f.setIndicadorConsultorasFile2("0"); else
		 * f.setIndicadorConsultorasFile2("0");
		 */

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #prepareParamsBeforeExecute(java.util.Map)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		params = super.prepareParamsBeforeExecute(params, form);
		MantenimientoSTOBloqueoControlService stoService = (MantenimientoSTOBloqueoControlService) getBean("spusicc.mantenimientoSTOBloqueoControlService");
		List listaDatos = this.getOcrDatosClienteEscaneadosSCList();
		ProcesoOCREliminarSolicitudesCreditoForm f = (ProcesoOCREliminarSolicitudesCreditoForm) this.formProceso;

		if (this.indicadorConsultorasFile1) {
			f.setIndicadorConsultorasFile1("1");
		} else
			f.setIndicadorConsultorasFile1(null);
		if (this.indicadorConsultorasFile2) {
			f.setIndicadorConsultorasFile2("1");
		} else
			f.setIndicadorConsultorasFile2(null);
		if (this.indicadorConsultorasInd) {
			f.setIndicadorConsultorasInd("1");
		} else
			f.setIndicadorConsultorasInd("0");

		// -- Seteamos el Codigo de Pais y Codigo de Sistema
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.getmPantallaPrincipalBean()
				.getCurrentCountry().getCodigo());
		criteria.put("codigoSistema", "OCR");

		params.put("indicadorConsultorasFile1",
				f.getIndicadorConsultorasFile1());
		params.put("indicadorConsultorasFile2",
				f.getIndicadorConsultorasFile2());
		params.put("indicadorConsultorasInd", f.getIndicadorConsultorasInd());

		// -- obtenemos los valores para comunicarse con el servidor FTP
		criteria.put("nombreParametro", "servidorFtpSC");
		String servidorFtpSC = stoService.getParametroGenericoSistema(criteria);
		criteria.put("nombreParametro", "puertoFtpSC");
		String puertoFtpSC = stoService.getParametroGenericoSistema(criteria);
		criteria.put("nombreParametro", "directorioFtpSC");
		String directorioFtpSC = stoService
				.getParametroGenericoSistema(criteria);
		criteria.put("nombreParametro", "usuarioFtpSC");
		String usuarioFtpSC = stoService.getParametroGenericoSistema(criteria);
		criteria.put("nombreParametro", "passwordFtpSC");
		String passwordFtpSC = stoService.getParametroGenericoSistema(criteria);
		criteria.put("nombreParametro", "directorioTempSC");
		String directorioTempSC = stoService
				.getParametroGenericoSistema(criteria);
		criteria.put("nombreParametro", "scaleFitSC");
		String scaleFitSC = stoService.getParametroGenericoSistema(criteria);

		params.put("servidorFtpSC", servidorFtpSC);
		params.put("puertoFtpSC", puertoFtpSC);
		params.put("directorioFtpSC", directorioFtpSC);
		params.put("usuarioFtpSC", usuarioFtpSC);
		params.put("passwordFtpSC", passwordFtpSC);
		params.put("directorioTempFtpSC", directorioTempSC);
		params.put("scaleFitSC", scaleFitSC);
		params.put("listaDatos", listaDatos);

		return params;
	}

	/**
	 * Ceros izquierda.
	 * 
	 */
	public void cerosIzquierda() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cerosIzquierda' method");
		}
		ProcesoOCREliminarSolicitudesCreditoForm f = (ProcesoOCREliminarSolicitudesCreditoForm) this.formProceso;

		if (StringUtils.isNotBlank(f.getCodigoCliente())) {
			if (f.getCodigoCliente().length() <= Integer.parseInt(f
					.getLongitudCodigoCliente()))
				f.setCodigoCliente(StringUtils.leftPad(f.getCodigoCliente(),
						Integer.parseInt(f.getLongitudCodigoCliente()), "0"));
			else {
				int tamanio = f.getCodigoCliente().length()
						- Integer.parseInt(f.getLongitudCodigoCliente());
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
	 * @return the indicadorConsultorasInd
	 */
	public Boolean getIndicadorConsultorasInd() {
		return indicadorConsultorasInd;
	}

	/**
	 * @param indicadorConsultorasInd
	 *            the indicadorConsultorasInd to set
	 */
	public void setIndicadorConsultorasInd(Boolean indicadorConsultorasInd) {
		this.indicadorConsultorasInd = indicadorConsultorasInd;
	}

	/**
	 * @return the indicadorConsultorasFile1
	 */
	public Boolean getIndicadorConsultorasFile1() {
		return indicadorConsultorasFile1;
	}

	/**
	 * @param indicadorConsultorasFile1
	 *            the indicadorConsultorasFile1 to set
	 */
	public void setIndicadorConsultorasFile1(Boolean indicadorConsultorasFile1) {
		this.indicadorConsultorasFile1 = indicadorConsultorasFile1;
	}

	/**
	 * @return the indicadorConsultorasFile2
	 */
	public Boolean getIndicadorConsultorasFile2() {
		return indicadorConsultorasFile2;
	}

	/**
	 * @param indicadorConsultorasFile2
	 *            the indicadorConsultorasFile2 to set
	 */
	public void setIndicadorConsultorasFile2(Boolean indicadorConsultorasFile2) {
		this.indicadorConsultorasFile2 = indicadorConsultorasFile2;
	}
}