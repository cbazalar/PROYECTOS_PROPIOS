package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.ReclamoDigitadoCabecera;
import biz.belcorp.ssicc.dao.model.ReclamoDigitadoDetalle;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.DatosCuv;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.DatosMasivo;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECGestionIngresoAnulacionNmpsSearchService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoMasivoOperacionesService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.service.util.FileUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECIngresoMasivoOperacionesForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoRECIngresoMasivoOperacionesAction extends BaseProcesoAbstractAction{

	private static final long serialVersionUID = 1L;
	private List recTipoOperacionList = new ArrayList();
	private List mantenimientoRECIngresoMasivoOperacionesProcessList = new ArrayList();
	private DataTableModel dataTableModelResultado = new DataTableModel();
	private static final String CODIGO_SISTEMA = "STO";
	private static final String CODIGO_PROCESO_BATCH = "03";
	private boolean disableSflagIndicExpress = false;
	private boolean mostrarArchiv = false;
	private boolean mostrarBotonprocesar = false;
	private String numeroLote = "";
	private String attachment = "";
	private String rutaAbsolutaArchivoIngresoMasivo = "";
	private String nombreArchivoIngresoMasivo = "";
	private String dirTemporal = "";
	private String dirHistorico = "";
	private String nombreArchivo = "";	
	private String ftp = "";
	private String reclamosMasivosOperacionesExitosos = "";
	private String reclamosMasivosOperacionesErroneos = "";
	private Map criteriaMap = new HashMap();
	private List listaSTO = new ArrayList();
	private String mensajeFilas;
	private List recTipoMotivolist;
	private boolean mostrarMotivo;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		MantenimientoRECIngresoMasivoOperacionesForm form = new MantenimientoRECIngresoMasivoOperacionesForm();
		return form;
	}
	
	/**
	 * @param e
	 * @throws Exception
	 */
	public void procesar(ActionEvent e){
		try {		
		if(log.isDebugEnabled()){
			log.debug("procesar");
		}
		MantenimientoRECIngresoMasivoOperacionesForm f = (MantenimientoRECIngresoMasivoOperacionesForm) formProceso;

		ReclamoDigitadoCabecera reclamoDigitCabec = null;
		ReclamoDigitadoDetalle reclamoDigitDetal = null;
		List cabeceraList = new ArrayList();
		List detalleList = new ArrayList();
		List listProceso = this.mantenimientoRECIngresoMasivoOperacionesProcessList;
		this.dataTableModelResultado = new DataTableModel(listProceso);
		ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");

		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();

		String codigoOper = f.getCodigoOperacion();
		String codigoOperSICC = "";
		String tipoOperSICC = "";
		String codigoOperMot = "";
		String estadoProceso = "";
		String indCambiaObligatorio = "";

		Map map = null;

		List tipoMasivoOperacionesList = this.recTipoOperacionList;
		for (int i = 0; i < tipoMasivoOperacionesList.size(); i++) {
			map = (Map) tipoMasivoOperacionesList.get(i);

			if (((String) map.get("codigoOperacion")).equals(codigoOper)) {
				codigoOperSICC = (String) map.get("codigoOperacionSICC");
				tipoOperSICC = (String) map.get("tipoOperacionSICC");
				codigoOperMot = (String) map.get("codigoOperacionMot");
				indCambiaObligatorio = MapUtils.getString(map, "indCambiaObligatorio", "");

				break;
			}
		}

		String pedidoGeneral = "";
		String clienteGeneral = "";
		int contNumeroDocumento = 0;
		for (int i = 0; i < listProceso.size(); i++) {

			if (StringUtils.equals(f.getOrigen(), Constants.REC_ORIGEN_ARCHIVO_FTP)) {
				try {
					map = BeanUtils.describe(listProceso.get(i));
				} catch (IllegalAccessException e1) {
					this.addError("Error : ", this.obtieneMensajeErrorException(e1));
				} catch (InvocationTargetException e1) {
					this.addError("Error : ", this.obtieneMensajeErrorException(e1));
				} catch (NoSuchMethodException e1) {
					this.addError("Error : ", this.obtieneMensajeErrorException(e1));
				}
			} else {
				map = (Map) listProceso.get(i);
			}

			String clienteActual = (String) map.get("codigoCliente");
			String pedidoActual = (String) map.get("pedido");

			if (!(pedidoGeneral.equals(pedidoActual)
					&& clienteGeneral.equals(clienteActual))) {
				contNumeroDocumento++;
				clienteGeneral = clienteActual;
				pedidoGeneral = pedidoActual;
			}

			estadoProceso = (String) map.get("estadoProceso");
			if (!StringUtils.equals(estadoProceso, Constants.CODIGO_CONSULTORA_NO_EXISTE)) {

				String numeroDocumento = String.valueOf(contNumeroDocumento);

				reclamoDigitCabec = new ReclamoDigitadoCabecera();
				reclamoDigitCabec.setCodigoPais(pais.getCodigo());
				reclamoDigitCabec.setCodigoEmpresa(pais.getCodigo());
				reclamoDigitCabec.setNumeroDocumento(numeroDocumento);
				reclamoDigitCabec.setCodigoPeriodo(f.getCodigoPeriodoProceso());
				reclamoDigitCabec.setCodigoCliente((String) map.get("codigoCliente"));
				reclamoDigitCabec.setNumeroDocumentoCruce((String) map.get("pedido"));
				reclamoDigitCabec.setTipoSolicitud(Constants.TIPO_SOLICITUD_SOC);
				reclamoDigitCabec.setCodigoSubAcceso(Constants.CODIGO_SUBACCESO_000);
				reclamoDigitCabec.setAccesoFisico(Constants.ACCESO_FISICO_01);
				reclamoDigitCabec.setEstadoProceso(Constants.ESTADO_PROCESO_01);
				reclamoDigitCabec.setCodigoTipoDocumento(Constants.TIPO_DOCUMENTO_SPVC);
				reclamoDigitCabec.setIndicadorOrigen(Constants.REC_DIGI_CDRS_ORIG_M);

				if (f.issFlagIndExpress()) {
					reclamoDigitCabec.setIndicadorExpress(Constants.IND_EXPRESS_ACTIVO);
				} else {
					reclamoDigitCabec.setIndicadorExpress(Constants.IND_EXPRESS_INACTIVO);
				}
				cabeceraList.add(reclamoDigitCabec);

				reclamoDigitDetal = new ReclamoDigitadoDetalle();
				reclamoDigitDetal.setCodigoPais(f.getCodigoPais());
				reclamoDigitCabec.setCodigoEmpresa(pais.getCodigo());
				reclamoDigitDetal.setNumeroDocumento(numeroDocumento);
				reclamoDigitDetal.setCodigoCliente((String) map.get("codigoCliente"));
				reclamoDigitDetal.setTipoReferencia(codigoOper);
				reclamoDigitDetal.setEstadoProceso(Constants.ESTADO_PROCESO_01);
				reclamoDigitDetal.setMotivoSPV(f.getCodigoMotivo());
				

				if (f.issFlagIndExpress()) {
					reclamoDigitDetal.setCodigoOperador(codigoOperMot);
				} else {
					reclamoDigitDetal.setCodigoOperador(codigoOperSICC);
				}

				reclamoDigitDetal.setCodigoTipoOperacion(tipoOperSICC);
				reclamoDigitDetal.setCodigoTipoDocumento(Constants.TIPO_DOCUMENTO_SPVD);

				String cuvGrilla = MapUtils.getString(map, "cuv", "");
				String cantidadGrilla = null;
                if (StringUtils.equals(f.getOrigen(), Constants.REC_ORIGEN_ARCHIVO_FTP)) {
                    cantidadGrilla = MapUtils.getString(map, "unidades", "");
			    } else {
			        cantidadGrilla = MapUtils.getString(map, "cantidad", "");
			    }
				
				if (StringUtils.equalsIgnoreCase(indCambiaObligatorio, Constants.REC_INDICADOR_CAMBIA_OBLIGATORIO_SI)) {
					reclamoDigitDetal.setCodigoVentaDevuelve(cuvGrilla);
					reclamoDigitDetal.setCantidadProductosDevuelve(cantidadGrilla);
					reclamoDigitDetal.setCodigoVentaDesea(null);
					reclamoDigitDetal.setCantidadProductosDesea(null);
				} else {
					reclamoDigitDetal.setCodigoVentaDevuelve(null);
					reclamoDigitDetal.setCantidadProductosDevuelve(null);
					reclamoDigitDetal.setCodigoVentaDesea(cuvGrilla);
					reclamoDigitDetal.setCantidadProductosDesea(cantidadGrilla);
				}

				detalleList.add(reclamoDigitDetal);

			}
		}

		Map params = new HashMap();
		params.put("codigoPais", pais.getCodigo());
		params.put("codigoDocumento", Constants.STO_TIPO_DOCUMENTO_SPVC);

		TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK = new TipoDocumentoDigitadoPK(pais.getCodigo(), Constants.STO_TIPO_DOCUMENTO_SPVC);
		ProcesoSTOService stoService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
		String numLoteSTO = stoService.updateLoteSTO(tipoDocumentoDigitadoPK);

		params.put("usuario", usuario);
		params.put("numLoteSTO", numLoteSTO);

		if (!cabeceraList.isEmpty()) {
			this.numeroLote = numLoteSTO;
			MantenimientoRECIngresoMasivoOperacionesService service = (MantenimientoRECIngresoMasivoOperacionesService) getBean("spusicc.mantenimientoRECIngresoMasivoOperacionesService");
			service.insertIngresoMasivoOperaciones(cabeceraList, detalleList, params);
			this.addInfo("Info:", this.getResourceMessage("mantenimientoRECIngresoMasivoOperacionesForm.procesado", new Object[]{this.numeroLote}));
			initForm();
		} else {
			this.addInfo("Info", this.getResourceMessage("mantenimientoRECIngresoMasivoOperacionesForm.procesado.ceroRegistros"));
		}

		String rutaAbsolutaArchivo = this.rutaAbsolutaArchivoIngresoMasivo;// seteado
																													// en
																													// linea
																													// 276
		String nombreArchivoIngresoMasivo = this.nombreArchivoIngresoMasivo;// seteado
																													// en
																													// linea
																													// 277
		// sacar
		File srcFile = new File(rutaAbsolutaArchivo);
		if (srcFile.isFile())
			log.debug("Encontro el archivo");

		// Obtener la ruta de base de datos
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema(Constants.CODIGO_SISTEMA_REC);
		parametroPais.setCodigoParametro(Constants.CODIGO_PARAMETRO_INGRESO_MASIVO);
		parametroPais.setNombreParametro(Constants.RUTA_ARCHIVO_PROCESADO);
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		List lstParametroPais = genericoService.getParametrosPais(parametroPais);
		ParametroPais objParametroPais = (ParametroPais) lstParametroPais.get(0);
		File destFileTemporal = new File(objParametroPais.getValorParametro()
				+ numLoteSTO + "_" + nombreArchivoIngresoMasivo);
		try {
			FileUtils.copyFile(srcFile, destFileTemporal);
		} catch (IOException e1) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e1));
		}

		if (log.isDebugEnabled()) {
			log.debug("Se envio el archivo al destino final");
		}

		// inicio VEN-SiCC-2012-0184
		Map criteria = new HashMap();
		criteria.put("codPais", MapUtils.getString(params, "codigoPais"));
		criteria.put("codTipoDocu", Constants.STO_TIPO_DOCUMENTO_SPVC);
		criteria.put("numLote", numLoteSTO);
		criteria.put("usuario", usuario);

		List listaSTO = procesoSTOService.getDocumentoDigitadoPKByLote(criteria);

		this.dirTemporal = "";
		this.nombreArchivo = "";
		this.ftp = "";

		if (listaSTO != null && listaSTO.size() > 0) {
			// guardar al historico
			String dh = dirHistorico;
			if (StringUtils.isNotBlank(dh)) {
				File dirHistorico = new File(dh);
				try {
					FileUtils.copyFileToDirectory(destFileTemporal, dirHistorico);
				} catch (IOException e1) {
					this.addError("Error : ", this.obtieneMensajeErrorException(e1));
				}
				FileUtil.deleteFile(destFileTemporal);
				FileUtil.deleteFile(srcFile);
			}

			// //Si la listaSTO no devuelve registros es por que no se ha
			// registrado el nro de lote
			// //en insertIngresoMasivoOperaciones, por lo tanto no se lanzan
			// las validaciones
			// //esto se debe a que todos los registros del archivo son
			// erroneos.
			this.criteriaMap = criteria;
			this.listaSTO =  listaSTO;
			this.executeProceso();
		}
		} catch (Exception e2) {
			this.addError("Error : ",this.obtieneMensajeErrorException(e2));
		}

	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)throws Exception {
		if(log.isDebugEnabled()){
			log.debug("executeProcess");
		}
		ProcesoSTOExecutionService procesoSTOExecutionService = (ProcesoSTOExecutionService) getBean("spusicc.procesoSTOExecutionService");
		
		params.putAll(criteriaMap);
		List listaSTO = this.listaSTO;

		AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(MapUtils.getString(params, "codigoPais"), Constants.STO_TIPO_DOCUMENTO_SPVC, Constants.STO_ACCI_VALI_LINEA);
		procesoSTOExecutionService.execute(accionTipoDocumento, params, listaSTO);
		log.debug("Procesando el listado por lote");
		
		this.numeroLote = "";
		this.mostrarBotonprocesar = false;
		this.mantenimientoRECIngresoMasivoOperacionesProcessList= new ArrayList();
		this.dataTableModelResultado = new DataTableModel(this.mantenimientoRECIngresoMasivoOperacionesProcessList);
		this.mostrarPanelAdicionalProceso = false;
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarMotivo=false;
		this.mostrarBotonExecute = false;		
		this.mostrarPanelAdicionalProceso = false;
//		this.esProcesoBatch = true;
		this.mostrarBotonprocesar = false;
		this.mensajeFilas = "";
	
			Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
			MantenimientoRECIngresoMasivoOperacionesForm f = (MantenimientoRECIngresoMasivoOperacionesForm) formProceso;
			MantenimientoRECIngresoMasivoOperacionesService service = (MantenimientoRECIngresoMasivoOperacionesService) getBean("spusicc.mantenimientoRECIngresoMasivoOperacionesService");
			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("estadoCampanha", Constants.NUMERO_CERO);
			criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica ampanha activa que se procesa actualmente																		
			String campahniaActual = service.getObtenerCampahniaActiva(criteria);
			if (campahniaActual == null) 
				campahniaActual = "";			

			f.setCodigoPeriodoProceso(campahniaActual);
			List tipoMasivoOperacionesList = service.getTipoMasivoOperacionesList();
			this.setRecTipoOperacionList(tipoMasivoOperacionesList);

			f.setMostrarPanel(Constants.NO);
			f.setCodigoOperacion("");
			f.setsFlagIndExpress(false);
			f.setOrigen("1");

			this.dirTemporal="";
			this.nombreArchivo="";
			this.numeroLote="";
			this.ftp="";
			initForm();		
	}
	
	/**
	 * @param request
	 * @param f
	 * 
	 * Inicializa campos del formulario
	 * 
	 */
	private void initForm() {
		MantenimientoRECIngresoMasivoOperacionesForm f = (MantenimientoRECIngresoMasivoOperacionesForm) formProceso;
		f.setCodigoOperacion("");
		f.setsFlagIndExpress(false);
	}
	
	public void cargar(FileUploadEvent event) {
		try {		
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		MantenimientoRECIngresoMasivoOperacionesForm f = (MantenimientoRECIngresoMasivoOperacionesForm) this.formProceso;		
		if (event != null) {
			f.setArchivo(event.getFile());
			this.setAttachment(event.getFile().getFileName());
			if(StringUtils.isBlank(f.getCodigoOperacion())&& StringUtils.isEmpty(f.getCodigoOperacion())){
				this.addWarn("Advertencia: ", this.getResourceMessage("mantenimientoRECIngresoMasivoOperacionesForm.codigo.operacion.requerido"));
				return;
				
			}else{
				try {
					validarDatos();
					this.mostrarBotonprocesar = true;
					this.mostrarPanelAdicionalProceso = true;
				} catch (Exception e) {				
					this.addError("Error : ", this.obtieneMensajeErrorException(e));
				}
			}
		}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
		
	}
	
	/**
	 * @param e
	 */
	public void getIndExpress(ValueChangeEvent value){
		try {
			MantenimientoRECIngresoMasivoOperacionesForm f = (MantenimientoRECIngresoMasivoOperacionesForm) formProceso;
			MantenimientoRECIngresoMasivoOperacionesService service = (MantenimientoRECIngresoMasivoOperacionesService) getBean("spusicc.mantenimientoRECIngresoMasivoOperacionesService");
			AjaxService ajaxService = (AjaxService)getBean("ajaxService");
			String valor = (String) value.getNewValue();
			if(StringUtils.isNotBlank(valor)){
				String val = ajaxService.getIndExpress(valor);
				if(StringUtils.equals(val, "1")){
					f.setsFlagIndExpress(false);
					this.setDisableSflagIndicExpress(false);
				}
				if(StringUtils.equals(val, "0")){
					f.setsFlagIndExpress(false);
					this.setDisableSflagIndicExpress(true);
				}
				//Motivo
				this.mostrarMotivo=true;
				String operacion="";
				for(int i=0;i<this.recTipoOperacionList.size();i++){
					Map busqueda = (Map)this.recTipoOperacionList.get(i);
					String codigo=busqueda.get("codigoOperacion").toString();
					if(StringUtils.equals(codigo, valor)){
						operacion=busqueda.get("codigoOperacionSICC").toString();
						break;
					}
				}
				Map criteria = new HashMap();
				criteria.put("codigoMotivo",operacion);
				this.recTipoMotivolist=service.getMotivoReclamoList(criteria);								
			}else{
				f.setsFlagIndExpress(false);
				this.mostrarMotivo=false;
				this.recTipoMotivolist= new ArrayList();
				f.setCodigoMotivo("");	
			}
			
		} catch (Exception e) {
			this.addError("Error:", this.obtieneMensajeErrorException(e));
		}
		
		
	}
	
	/**
	 * @param e
	 */
	public void inSelectOneRadio(ValueChangeEvent e){
		if(log.isDebugEnabled()){
			log.debug("inSelectOneRadio");
		}
		MantenimientoRECIngresoMasivoOperacionesForm f = (MantenimientoRECIngresoMasivoOperacionesForm) formProceso;
		String valor = (String)e.getNewValue();
		if(StringUtils.isNotEmpty(valor) 
				&& StringUtils.isNotBlank(valor)){
			f.setOrigen(valor);
			if(StringUtils.equals(f.getOrigen(), Constants.REC_ORIGEN_ARCHIVO_RED)){				
				ocultar();
			}
			if(StringUtils.equals(f.getOrigen(), Constants.REC_ORIGEN_ARCHIVO_FTP)){
				obtenerArchivo();
			}
		}
	}
	
	/**
	 * 
	 */
	public void ocultar(){
		if(log.isDebugEnabled()){
			log.debug("ocultar");
		}
		this.setMostrarArchiv(false);
	}
	
	/**
	 * @throws Exception
	 */
	public void validarDatos() {		
		try{		
		if(log.isDebugEnabled()){
			log.debug("validar");
		}
		MantenimientoRECIngresoMasivoOperacionesForm f = (MantenimientoRECIngresoMasivoOperacionesForm) formProceso;
		// ////////////////////////
		MantenimientoRECIngresoAtencionesService mantenimientoRECIngresoAtencionesService = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		MantenimientoRECGestionIngresoAnulacionNmpsSearchService mantenimientoRECGestionIngresoAnulacionNmpsSearchService = (MantenimientoRECGestionIngresoAnulacionNmpsSearchService) getBean("spusicc.mantenimientoRECGestionIngresoAnulacionNmpsSearchService");

		List list = null;
		int numfila = 0;
		Map params = null;
		String codigoCliente = "";
		String pedido = "";
		String cuv = "";
		String cantidad = "";

		String codigoOper = f.getCodigoOperacion();
		String codigoOperSICC = "";
		String tipoOperSICC = "";
		String codigoOperMot = "";
		String operacion = "";

		// //////////////////////////////
		Integer cantExis = 0;
		Integer cantNoExis = 0;
		String estadoProceso = "";
		String descError = "";
		Map criteria = new HashMap();
		List datosList = null;
		String existe = "";
		String codigoSap = "";
		String codMotivo=f.getCodigoMotivo();

		String errorConsultora = this.getResourceMessage("mantenimientoRECIngresoMasivoOperacionesForm.error.consultora");
		String errorPedido = this.getResourceMessage("mantenimientoRECIngresoMasivoOperacionesForm.error.pedido");
		String errorCuv = this.getResourceMessage("mantenimientoRECIngresoMasivoOperacionesForm.error.cuv");
		String errorUnidad = this.getResourceMessage("mantenimientoRECIngresoMasivoOperacionesForm.error.unidad");

		List tipoMasivoOperacionesList = this.getRecTipoOperacionList();
		for (int i = 0; i < tipoMasivoOperacionesList.size(); i++) {
			HashMap map = (HashMap) tipoMasivoOperacionesList.get(i);

			if (((String) map.get("codigoOperacion")).equals(codigoOper)) {
				codigoOperSICC = (String) map.get("codigoOperacionSICC");
				tipoOperSICC = (String) map.get("tipoOperacionSICC");
				codigoOperMot = (String) map.get("codigoOperacionMot");
				break;
			}
		}

		if (f.issFlagIndExpress()) {
			operacion = codigoOperMot;			
		} else {
			operacion = codigoOperSICC;						
		}

		if (StringUtils.equals(f.getOrigen(), Constants.REC_ORIGEN_ARCHIVO_RED)) {

			File file = new File(f.getArchivo().getFileName());// Aqui le dan el
																// nombre y/o
																// con la ruta
																// del archivo
																// salida

			// sacar la ruta absoluta
			this.setRutaAbsolutaArchivoIngresoMasivo(file.getAbsolutePath());
			this.setNombreArchivoIngresoMasivo(f.getArchivo().getFileName());

			// Abrimos el archivo Excel para su procesamiento
			InputStream entrada = f.getArchivo().getInputstream();

			OutputStream salida = new FileOutputStream(file);
			byte[] buf = new byte[1024];// Actualizado me olvide del 1024
			int len;

			while ((len = entrada.read(buf)) > 0) {
				salida.write(buf, 0, len);
			}
			salida.close();
			entrada.close();

			ExcelUtil excelUtil = new ExcelUtil(file);
			// nos colocamos en la primera hora del documento Excel
			excelUtil.initSheet(0);

			while (excelUtil.hasNext()) {
				params = new HashMap();
				Map mapRow = excelUtil.next();

				if (list == null)
					list = new ArrayList();

				if (numfila > 0) {

					if (mapRow.get("0") != null && !mapRow.get("0").equals("")) {
						codigoCliente = (String) mapRow.get("0");
					}

					if (mapRow.get("1") != null && !mapRow.get("1").equals("")) {
						pedido = (String) mapRow.get("1");
					}
					
					criteria.put("codigoConsultora", codigoCliente);
					criteria.put("numeroPedido", pedido);					
					// //////////////////////////////////////////////////////
					// validacion consultora
					existe = mantenimientoRECIngresoAtencionesService.getCodigoConsultora(criteria);

					if (StringUtils.isNotEmpty(existe)) {
						params.put("estadoProceso", Constants.CODIGO_CONSULTORA_EXISTE);
						params.put("descError", "");

					} else {
						params.put("estadoProceso", Constants.CODIGO_CONSULTORA_NO_EXISTE);
						params.put("descError", errorConsultora);
						cantNoExis++;
					}

					// validacion pedido
					if (StringUtils.equals((String) params.get("estadoProceso"), Constants.CODIGO_CONSULTORA_EXISTE)) {

						datosList = mantenimientoRECGestionIngresoAnulacionNmpsSearchService.getConsultoraPedidoList(criteria);

						if (!datosList.isEmpty()) {
							params.put("estadoProceso", Constants.CODIGO_CONSULTORA_EXISTE);
							params.put("descError", "");

						} else {
							params.put("estadoProceso", Constants.CODIGO_CONSULTORA_NO_EXISTE);
							params.put("descError", errorPedido);
							cantNoExis++;
						}
					}

					// validacion cuv
					if (mapRow.get("2") != null && !mapRow.get("2").equals("")) {
						cuv = (String) mapRow.get("2");

						if (StringUtils.equals((String) params.get("estadoProceso"), Constants.CODIGO_CONSULTORA_EXISTE)) {
							if (!cuv.contains(".0")) {
								params.put("estadoProceso", Constants.CODIGO_CONSULTORA_EXISTE);
								params.put("descError", "");

							} else {
								params.put("estadoProceso", Constants.CODIGO_CONSULTORA_NO_EXISTE);
								params.put("descError", errorCuv);
								cantNoExis++;
							}
						}
					}

					// validacion cantidad
					if (mapRow.get("3") != null && !mapRow.get("3").equals("")) {
						cantidad = (String) mapRow.get("3");
						cantidad = String.valueOf(Double.valueOf(cantidad).longValue());

						if (StringUtils.equals((String) params.get("estadoProceso"), Constants.CODIGO_CONSULTORA_EXISTE)) {

							if (Integer.parseInt(cantidad) > 0) {
								params.put("estadoProceso", Constants.CODIGO_CONSULTORA_EXISTE);
								params.put("descError", "");
								cantExis++;
							} else {
								params.put("estadoProceso", Constants.CODIGO_CONSULTORA_NO_EXISTE);
								params.put("descError", errorUnidad);
								cantNoExis++;
							}
						}
					}

					params.put("codigoCliente", codigoCliente);
					params.put("pedido", pedido);
					params.put("cuv", cuv);
					params.put("cantidad", cantidad);
					params.put("operacion", operacion);
					params.put("tipo", tipoOperSICC);
					params.put("codigoMotivo", codMotivo);
					list.add(params);

					codigoCliente = "";
					pedido = "";
					cuv = "";
					cantidad = "";
				}

				numfila++;
			}

			excelUtil.cerrar();

			this.dirTemporal ="";
			this.nombreArchivo = "";
			this.numeroLote = "";
			this.ftp="";

		} else if (StringUtils.equals(f.getOrigen(), Constants.REC_ORIGEN_ARCHIVO_FTP)) {
			this.ftp = Constants.REC_ORIGEN_ARCHIVO_FTP;
			String nombreArchivo = this.nombreArchivo;
			String dirTemporal = this.dirTemporal;
			
			String archivo = FileUtil.formatDirectory(dirTemporal+ System.getProperty("file.separator") + this.nombreArchivo);

			File file = new File(archivo);

			nombreArchivoIngresoMasivo = nombreArchivo;// Setea
																							// el
																							// nombre
																							// del
																							// archivo

			if (file.exists()) {
				rutaAbsolutaArchivoIngresoMasivo = file.getAbsolutePath();// Setea
																												// la
																												// ruta
																												// absoluta

				long lNumeroLineas;
				String sCadena;
				FileReader fr;

				lNumeroLineas = 0;
				list = new ArrayList();
				List listMasivo = new ArrayList();
				try {

					fr = new FileReader(archivo);
					DatosMasivo dat = null;
					BufferedReader bf = new BufferedReader(fr);
					while ((sCadena = bf.readLine()) != null) {
						codigoCliente = StringUtils.trim(sCadena.substring(0, 15));
						pedido = StringUtils.trim(sCadena.substring(15, 25));
						codigoSap = StringUtils.trim(sCadena.substring(25, 35));
						cantidad = String.valueOf(Integer.parseInt(StringUtils.trim(sCadena.substring(35, 41))));
						dat = new DatosMasivo(codigoCliente, pedido, codigoSap, cantidad);
						listMasivo.add(dat);
						lNumeroLineas++;
					}
					bf.close();
					fr.close();

					Map numMap = new HashMap();
					list = obtenerListado(listMasivo, numMap, tipoOperSICC, operacion,codMotivo);

					// FileUtil.deleteFile(new File(archivo));
					cantExis = MapUtils.getInteger(numMap, "cantExis");
					cantNoExis = MapUtils.getInteger(numMap, "cantNoExis");

				} catch (Exception e) {
					this.addError("Error:",this.obtieneMensajeErrorException(e));

				}
			} else {
				List listaLimpiar=	new ArrayList();
				this.dataTableModelResultado = new DataTableModel(listaLimpiar);
				this.mensajeFilas ="";
				this.addError("Error:",this.getResourceMessage("mantenimientoRECIngresoMasivoOperacionesForm.archivo.no.existe"));
				return;
			}
	
		}

		
		this.mantenimientoRECIngresoMasivoOperacionesProcessList = list;
		this.dataTableModelResultado = new DataTableModel(this.mantenimientoRECIngresoMasivoOperacionesProcessList);
		this.mostrarPanelAdicionalProceso = true;
		this.reclamosMasivosOperacionesExitosos = Integer.toString(cantExis);
		this.reclamosMasivosOperacionesErroneos = Integer.toString(cantNoExis);
		this.mensajeFilas = "Total Registros: " + this.reclamosMasivosOperacionesExitosos + " Registro(s) Exitosos  - " + this.reclamosMasivosOperacionesErroneos + " Registro(s) con Error ";
		}
		catch(Exception e){
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			
		}

	}
	
	public void obtenerArchivo(){
		if(log.isDebugEnabled()){
			log.debug("obtenerArchivo");
		}
		MantenimientoRECIngresoMasivoOperacionesForm f = (MantenimientoRECIngresoMasivoOperacionesForm) formProceso;
		if (StringUtils.isBlank(f.getCodigoOperacion())) {
			
			String mensajeAlert = "Ingrese Tipo de Operacion";
			this.setMensajeAlertaDefault(mensajeAlert);
			String ventana = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventana);
			return;
		}
		
		
		this.setMostrarArchiv(true);		

		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		this.nombreArchivo = "";
		InterfazService interfazService = (InterfazService) getBean("sisicc.interfazService");
		InterfazPK pk = new InterfazPK();
		pk.setCodigoPais(pais.getCodigo());
		pk.setCodigoSistema("APE");
		pk.setCodigo("APE-21");
		Interfaz interfaz = interfazService.getInterfaz(pk);

		File direc = null;

		try {

			direc = new File(FileUtil.formatDirectory(interfaz.getDirectorioEntradaSalida()));
		} catch (Exception e) {
			direc = null;
		}

		if (direc != null && direc.exists()) {

			List listDytfal = new ArrayList();
			int tamanioLista = direc.list().length;
			if (tamanioLista > 0 ) {
				dirTemporal=interfaz.getDirectorioEntradaSalida();

				dirHistorico = interfaz.getDirectorioHistorico();
				for (int i = 0; i < tamanioLista; i++) {
					if (direc.list()[i].startsWith("DYTFAL_")) {

						listDytfal.add(direc.list()[i]);

					}
				}

				String resultadoFinal = null;
				String archivoResultado = null;
				String resultadoObtenido = null;
				String archivoObtenido = null;

				if (CollectionUtils.isNotEmpty(listDytfal)) {

					archivoResultado = (String) listDytfal.get(0);
					resultadoFinal = archivoResultado.substring(archivoResultado.indexOf("_") + 1, archivoResultado.indexOf("."));
					log.debug("resultado " + resultadoFinal);

					for (int i = 0; i < listDytfal.size(); i++) {
						archivoObtenido = (String) listDytfal.get(i);
						resultadoObtenido = archivoObtenido.substring(archivoObtenido.indexOf("_") + 1, archivoObtenido.indexOf("."));
						if (Long.parseLong(resultadoObtenido) > Long.parseLong(resultadoFinal)) {
							archivoResultado = (String) listDytfal.get(i);
							resultadoFinal = archivoResultado.substring(archivoResultado.indexOf("_") + 1, archivoResultado.indexOf("."));

						}
					}
				}
				if (StringUtils.isBlank(archivoResultado)) {
					this.addError("Error:", this.getResourceMessage("mantenimientoRECIngresoMasivoOperacionesForm.archivo.no.existe"));
					return;
				}
				
				// obteniendo el ultimo archivo
				this.nombreArchivo = archivoResultado;
				this.validarDatos();
				this.mostrarBotonprocesar = true;
				this.mostrarPanelAdicionalProceso = true;
				
			}else{
				
				this.addError("Error:", this.getResourceMessage("mantenimientoRECIngresoMasivoOperacionesForm.archivo.no.existe"));

			}

		} else {
			this.addError("Error:", this.getResourceMessage("mantenimientoRECIngresoMasivoOperacionesForm.directorio.no.existe", new Object[]{interfaz.getDirectorioEntradaSalida()}));
		}
	}
	
	private List obtenerListado(List listMasivo,	Map map, String tipoOperSICC, String operacion,String codMotivo) throws IllegalAccessException, InvocationTargetException {		

		Map criteria = new HashMap();

		List list = new ArrayList();
		MantenimientoRECIngresoMasivoOperacionesService service = (MantenimientoRECIngresoMasivoOperacionesService) getBean("spusicc.mantenimientoRECIngresoMasivoOperacionesService");
		MantenimientoRECIngresoAtencionesService mantenimientoRECIngresoAtencionesService = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		MantenimientoRECGestionIngresoAnulacionNmpsSearchService mantenimientoRECGestionIngresoAnulacionNmpsSearchService = (MantenimientoRECGestionIngresoAnulacionNmpsSearchService) getBean("spusicc.mantenimientoRECGestionIngresoAnulacionNmpsSearchService");
		String errorConsultora = this.getResourceMessage("mantenimientoRECIngresoMasivoOperacionesForm.error.consultora");
		String errorPedido = this.getResourceMessage("mantenimientoRECIngresoMasivoOperacionesForm.error.pedido");
		String errorCuv = this.getResourceMessage("mantenimientoRECIngresoMasivoOperacionesForm.error.cuv");
		String errorUnidad = this.getResourceMessage("mantenimientoRECIngresoMasivoOperacionesForm.error.unidad");
		int cantNoExis = 0;
		int cantExis = 0;
		String cuv;
		DatosMasivo dat = null;
		DatosCuv datosCuv = null;
		boolean flagError = false;
		StringBuilder errores = new StringBuilder();

		for (int i = 0; i < listMasivo.size(); i++) {
			flagError = false;
			errores = new StringBuilder();
			dat = (DatosMasivo) listMasivo.get(i);

			criteria.put("codigoConsultora", dat.getCodigoCliente());
			criteria.put("numeroPedido", dat.getPedido());

			// validacion consultora
			String existe = mantenimientoRECIngresoAtencionesService.getCodigoConsultora(criteria);

			if (StringUtils.isNotEmpty(existe)) {
				dat.setEstadoProceso(Constants.CODIGO_CONSULTORA_EXISTE);
			} else {

				dat.setEstadoProceso(Constants.CODIGO_CONSULTORA_NO_EXISTE);
				errores.append(errorConsultora).append("<br/>");
				flagError = true;
			}

			// validacion pedido
			if (StringUtils.equals(dat.getEstadoProceso(), Constants.CODIGO_CONSULTORA_EXISTE)) {

				List datosList = mantenimientoRECGestionIngresoAnulacionNmpsSearchService.getConsultoraPedidoList(criteria);

				if (!datosList.isEmpty()) {
					dat.setEstadoProceso(Constants.CODIGO_CONSULTORA_EXISTE);
				} else {
					dat.setEstadoProceso(Constants.CODIGO_CONSULTORA_NO_EXISTE);
					errores.append(errorPedido).append("<br/>");
					flagError = true;
				}

			}

			// validacion cantidad
			if (dat.getCantidad() != null && !dat.getCantidad().equals("")) {

				String cantidad = String.valueOf(Double.valueOf(dat.getCantidad()).longValue());

				if (StringUtils.equals(dat.getEstadoProceso(), Constants.CODIGO_CONSULTORA_EXISTE)) {

					if (Integer.parseInt(cantidad) > 0) {
						dat.setEstadoProceso(Constants.CODIGO_CONSULTORA_EXISTE);
					} else {
						dat.setEstadoProceso(Constants.CODIGO_CONSULTORA_NO_EXISTE);
						errores.append(errorUnidad).append("<br/>");
						flagError = true;
					}
				}
			}

			dat.setOperacion(operacion);
			dat.setTipo(tipoOperSICC);
			dat.setCodigoMotivo(codMotivo);
			criteria.put("codigoSap", dat.getCodigoSap());

			List listCuv = service.getCuv(criteria);

			if (CollectionUtils.isEmpty(listCuv)) {

				dat.setEstadoProceso(Constants.CODIGO_CONSULTORA_NO_EXISTE);
				errores.append(this.getResourceMessage("mantenimientoRECIngresoMasivoOperacionesForm.error.sap", new Object[]{dat.getCodigoSap()})).append("<br/>");
				flagError = true;

				list.add(dat);
			} else {

				if (listCuv.size() == 1) {

					cuv = ((DatosCuv) listCuv.get(0)).getCodigoVenta();
					dat.setCuv(cuv);
					dat.setUnidades(dat.getCantidad());
					// validacion cuv
					if (StringUtils.equals(dat.getEstadoProceso(), Constants.CODIGO_CONSULTORA_EXISTE)) {
						if (!cuv.contains(".0")) {
							dat.setEstadoProceso(Constants.CODIGO_CONSULTORA_EXISTE);
							cantExis++;
						} else {
							dat.setEstadoProceso(Constants.CODIGO_CONSULTORA_NO_EXISTE);
							errores.append(errorCuv).append("<br/>");
							flagError = true;
						}
					}

					list.add(dat);
				} else {

					// El tope en unidades es el que viene en el TXT
					int topeUnidades = Integer.parseInt(dat.getCantidad());
					int totalAsignados = 0;
					
					for (int j = 0; j < listCuv.size(); j++) 
					{
						DatosMasivo dest = new DatosMasivo();
						BeanUtils.copyProperties(dest, dat);
						datosCuv = (DatosCuv) listCuv.get(j);
						dest.setCuv(datosCuv.getCodigoVenta());
						
						if((totalAsignados + Integer.parseInt(datosCuv.getNumeroUnidades())) <= topeUnidades)
						{							
							dest.setUnidades(datosCuv.getNumeroUnidades());
							list.add(dest);							
						}
						else
						{													
							if((topeUnidades - totalAsignados) > 0)
							{
								dest.setUnidades(String.valueOf(topeUnidades - totalAsignados));
								list.add(dest);
							}								
							
							break;
						}
						
						totalAsignados += Integer.parseInt(datosCuv.getNumeroUnidades());						
					}
				}
			}

			dat.setDescError(errores.toString());
			if (flagError)
				cantNoExis++;
			else
				cantExis++;
		}

		map.put("cantNoExis", cantNoExis);
		map.put("cantExis", cantExis);

		return list;
	}

	/**
	 * @return the recTipoOperacionList
	 */
	public List getRecTipoOperacionList() {
		return recTipoOperacionList;
	}

	/**
	 * @param recTipoOperacionList the recTipoOperacionList to set
	 */
	public void setRecTipoOperacionList(List recTipoOperacionList) {
		this.recTipoOperacionList = recTipoOperacionList;
	}

	/**
	 * @return the mantenimientoRECIngresoMasivoOperacionesProcessList
	 */
	public List getMantenimientoRECIngresoMasivoOperacionesProcessList() {
		return mantenimientoRECIngresoMasivoOperacionesProcessList;
	}

	/**
	 * @param mantenimientoRECIngresoMasivoOperacionesProcessList the mantenimientoRECIngresoMasivoOperacionesProcessList to set
	 */
	public void setMantenimientoRECIngresoMasivoOperacionesProcessList(
			List mantenimientoRECIngresoMasivoOperacionesProcessList) {
		this.mantenimientoRECIngresoMasivoOperacionesProcessList = mantenimientoRECIngresoMasivoOperacionesProcessList;
	}

	/**
	 * @return the dataTableModelResultado
	 */
	public DataTableModel getDataTableModelResultado() {
		return dataTableModelResultado;
	}

	/**
	 * @param dataTableModelResultado the dataTableModelResultado to set
	 */
	public void setDataTableModelResultado(DataTableModel dataTableModelResultado) {
		this.dataTableModelResultado = dataTableModelResultado;
	}

	/**
	 * @return the disableSflagIndicExpress
	 */
	public boolean isDisableSflagIndicExpress() {
		return disableSflagIndicExpress;
	}

	/**
	 * @param disableSflagIndicExpress the disableSflagIndicExpress to set
	 */
	public void setDisableSflagIndicExpress(boolean disableSflagIndicExpress) {
		this.disableSflagIndicExpress = disableSflagIndicExpress;
	}

	/**
	 * @return the mostrarArchiv
	 */
	public boolean isMostrarArchiv() {
		return mostrarArchiv;
	}

	/**
	 * @param mostrarArchiv the mostrarArchiv to set
	 */
	public void setMostrarArchiv(boolean mostrarArchiv) {
		this.mostrarArchiv = mostrarArchiv;
	}

	/**
	 * @return the mostrarBotonprocesar
	 */
	public boolean isMostrarBotonprocesar() {
		return mostrarBotonprocesar;
	}

	/**
	 * @param mostrarBotonprocesar the mostrarBotonprocesar to set
	 */
	public void setMostrarBotonprocesar(boolean mostrarBotonprocesar) {
		this.mostrarBotonprocesar = mostrarBotonprocesar;
	}

	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return the rutaAbsolutaArchivoIngresoMasivo
	 */
	public String getRutaAbsolutaArchivoIngresoMasivo() {
		return rutaAbsolutaArchivoIngresoMasivo;
	}

	/**
	 * @param rutaAbsolutaArchivoIngresoMasivo the rutaAbsolutaArchivoIngresoMasivo to set
	 */
	public void setRutaAbsolutaArchivoIngresoMasivo(
			String rutaAbsolutaArchivoIngresoMasivo) {
		this.rutaAbsolutaArchivoIngresoMasivo = rutaAbsolutaArchivoIngresoMasivo;
	}

	/**
	 * @return the nombreArchivoIngresoMasivo
	 */
	public String getNombreArchivoIngresoMasivo() {
		return nombreArchivoIngresoMasivo;
	}

	/**
	 * @param nombreArchivoIngresoMasivo the nombreArchivoIngresoMasivo to set
	 */
	public void setNombreArchivoIngresoMasivo(String nombreArchivoIngresoMasivo) {
		this.nombreArchivoIngresoMasivo = nombreArchivoIngresoMasivo;
	}

	/**
	 * @return the dirTemporal
	 */
	public String getDirTemporal() {
		return dirTemporal;
	}

	/**
	 * @param dirTemporal the dirTemporal to set
	 */
	public void setDirTemporal(String dirTemporal) {
		this.dirTemporal = dirTemporal;
	}

	/**
	 * @return the dirHistorico
	 */
	public String getDirHistorico() {
		return dirHistorico;
	}

	/**
	 * @param dirHistorico the dirHistorico to set
	 */
	public void setDirHistorico(String dirHistorico) {
		this.dirHistorico = dirHistorico;
	}

	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * @param nombreArchivo the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * @return the ftp
	 */
	public String getFtp() {
		return ftp;
	}

	/**
	 * @param ftp the ftp to set
	 */
	public void setFtp(String ftp) {
		this.ftp = ftp;
	}

	/**
	 * @return the reclamosMasivosOperacionesExitosos
	 */
	public String getReclamosMasivosOperacionesExitosos() {
		return reclamosMasivosOperacionesExitosos;
	}

	/**
	 * @param reclamosMasivosOperacionesExitosos the reclamosMasivosOperacionesExitosos to set
	 */
	public void setReclamosMasivosOperacionesExitosos(
			String reclamosMasivosOperacionesExitosos) {
		this.reclamosMasivosOperacionesExitosos = reclamosMasivosOperacionesExitosos;
	}

	/**
	 * @return the reclamosMasivosOperacionesErroneos
	 */
	public String getReclamosMasivosOperacionesErroneos() {
		return reclamosMasivosOperacionesErroneos;
	}

	/**
	 * @param reclamosMasivosOperacionesErroneos the reclamosMasivosOperacionesErroneos to set
	 */
	public void setReclamosMasivosOperacionesErroneos(
			String reclamosMasivosOperacionesErroneos) {
		this.reclamosMasivosOperacionesErroneos = reclamosMasivosOperacionesErroneos;
	}

	/**
	 * @return the criteriaMap
	 */
	public Map getCriteriaMap() {
		return criteriaMap;
	}

	/**
	 * @param criteriaMap the criteriaMap to set
	 */
	public void setCriteriaMap(Map criteriaMap) {
		this.criteriaMap = criteriaMap;
	}

	/**
	 * @return the listaSTO
	 */
	public List getListaSTO() {
		return listaSTO;
	}

	/**
	 * @param listaSTO the listaSTO to set
	 */
	public void setListaSTO(List listaSTO) {
		this.listaSTO = listaSTO;
	}

	/**
	 * @return the mensajeFilas
	 */
	public String getMensajeFilas() {
		return mensajeFilas;
	}

	/**
	 * @param mensajeFilas the mensajeFilas to set
	 */
	public void setMensajeFilas(String mensajeFilas) {
		this.mensajeFilas = mensajeFilas;
	}

	/**
	 * @return the recTipoMotivolist
	 */
	public List getRecTipoMotivolist() {
		return recTipoMotivolist;
	}

	/**
	 * @param recTipoMotivolist the recTipoMotivolist to set
	 */
	public void setRecTipoMotivolist(List recTipoMotivolist) {
		this.recTipoMotivolist = recTipoMotivolist;
	}

	/**
	 * @return the mostrarMotivo
	 */
	public boolean isMostrarMotivo() {
		return mostrarMotivo;
	}

	/**
	 * @param mostrarMotivo the mostrarMotivo to set
	 */
	public void setMostrarMotivo(boolean mostrarMotivo) {
		this.mostrarMotivo = mostrarMotivo;
	}
	
	
	
//	public void mostrarDialogo(ActionEvent actionEvent){
//		if (log.isDebugEnabled()) {
//			log.debug("mostrarDialogo");
//		}
//		MantenimientoRECIngresoMasivoOperacionesForm f = (MantenimientoRECIngresoMasivoOperacionesForm) formProceso;
//		if(StringUtils.isBlank(f.getCodigoOperacion())
//			&& StringUtils.isEmpty(f.getCodigoOperacion())){
//			this.getRequestContext().execute("PF('confirmation').show()");
//		}else{
//			try {
//				validarDatos();
//				this.mostrarBotonExecute = true;
//			} catch (Exception e) {				
//				e.printStackTrace();
//				this.addError("Error", e.getMessage());
//			}
//		}
//	}

	
	
	
}
