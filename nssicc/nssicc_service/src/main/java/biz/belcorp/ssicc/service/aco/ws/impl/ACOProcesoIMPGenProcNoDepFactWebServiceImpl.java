package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;

import org.apache.axis.MessageContext;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoIMPGenProcNoDepFactWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.gen.ProcesoGENProcesarCierreService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.service.util.FileUtil;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoRECEnviaMsgReclamoWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public class ACOProcesoIMPGenProcNoDepFactWebServiceImpl extends BaseInterfazHiloAbstractWebService implements ACOProcesoIMPGenProcNoDepFactWebService{

	/** The proceso gen procesar cierre service. */
	ProcesoGENProcesarCierreService procesoGENProcesarCierreService;
	
	/** The interfaz si cc service. */
	InterfazSiCCService interfazSiCCService;
	
	/** The mantenimiento ocr pedido control facturacion service. */
	MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
	
	/** The reporte service. */
	ReporteService reporteService;
	
	/** The mantenimiento sto bloqueo control service. */
	MantenimientoSTOBloqueoControlService mantenimientoSTOBloqueoControlService;
	
	/** The ajax service. */
	AjaxService ajaxService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazHiloAbstractWebService#onInit()
	 */
	protected void onInit() throws ServiceException {
		super.onInit();
		this.interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
		this.mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		this.procesoGENProcesarCierreService = (ProcesoGENProcesarCierreService)getWebApplicationContext().getBean("spusicc.procesoGENProcesarCierreService");
		this.mantenimientoSTOBloqueoControlService = (MantenimientoSTOBloqueoControlService)getWebApplicationContext().getBean("spusicc.mantenimientoSTOBloqueoControlService");
		this.reporteService = (ReporteService)getWebApplicationContext().getBean("scsicc.reporteService");
		this.ajaxService = (AjaxService)getWebApplicationContext().getBean("ajaxService");
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoIMPGenProcNoDepFactWebService#ejecutarProcesoIMPGenProcNoDepFact(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoIMPGenProcNoDepFact(
			String codigoUsuario,
			String codigoPais,
			String codigoMarca,
			String codigoCanal,
			String codigoPeriodo,
			String fechaFacturacion) throws RemoteException {
		
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";
		final String CODIGO_BATCH = Constants.IMP_CODIGO_PROCESO_BATCH_GEN_PROC_NO_DEP_FACT;
		final String CODIGO_INTERFAZ = Constants.IMP_CODIGO_INTERFAZ_BATCH_GEN_PROC_NO_DEP_FACT;
		final String CODIGO_SISTEMA = Constants.CODIGO_SISTEMA_IMP;
		final String INDICADOR_NOVEDAD = "0";
		final String NOMBRE_PARAMETRO = "documentosSTOReporteErrores";
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz Generacion de Procesos que no depende de la Facturacion");
		}
		try{			
			Pais pais = this.paisService.getPais(codigoPais);
			if(StringUtils.isBlank(codigoPais) || StringUtils.isEmpty(codigoPais)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null,
				getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			
			
			/**
		     * Valida usuario
		     */
		    if(StringUtils.isBlank(codigoUsuario)|| StringUtils.isEmpty(codigoUsuario)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);
		    }
			
		    Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);
		    
		    List siccMarcaList = this.interfazSiCCService.getMarcas();
			List siccCanalList = this.interfazSiCCService.getCanalesByCodigoISO(pais.getCodigoIdiomaIso());
		    
			/**
			 * 	Validar codigoMarca
			 */
			if(StringUtils.isBlank(codigoMarca) || StringUtils.isEmpty(codigoMarca)){
				codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
			}else{
				if(siccMarcaList.size()!=0){
					if(!existeCodigoEnLista(siccMarcaList, codigoMarca)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteMarca",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de marca vacio.");
				}
			}
			
			/**
			 * 	Validar codigoCanal
			 */
			if(StringUtils.isBlank(codigoCanal) || StringUtils.isEmpty(codigoCanal)){
				codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
			}else{
				if(siccMarcaList.size()!=0){
					if(!existeCodigoEnLista(siccCanalList, codigoCanal)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteMarca",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de canal vacio.");
				}
			}
			
			Map criteria = new HashMap();
			
			// Asignamos al codigo del periodo el valor por defecto
	        criteria.put("codigoPais", pais.getCodigo());
	        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		    criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		    
		    PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);
		    /**
			 * Valida codigoPeriodo
			 */
			if (StringUtils.isBlank(codigoPeriodo) || StringUtils.isEmpty(codigoPeriodo)) {
	        	codigoPeriodo = controlFacturacion.getCodigoPeriodo();
			}
			if(StringUtils.isBlank(fechaFacturacion) || StringUtils.isEmpty(fechaFacturacion)){
	        	fechaFacturacion = controlFacturacion.getFechaProceso();
	        }
			
		    criteria.put("codigoUsuario", codigoUsuario);
		    criteria.put("usuario", usuario);
			String hora =  new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
			criteria.put("fechaProcesoHora", hora);
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
	        criteria.put("codigoPeriodo", codigoPeriodo);
	        criteria.put("fechaFacturacion", fechaFacturacion);
    		
	        executionResult = this.executeInterfaz(criteria);
	        
	        List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult) list.get(0);
		
			if (interfazResult != null) {				
				if(StringUtils.equals(this.estadoProceso, Constants.OK)){
					estado = true;
				}
				
				objetoRespuesta.setEjecucionExitosa(estado);
				objetoRespuesta.setMensaje(this.mensajeError);
				log.debug(objetoRespuesta.getMensaje());
				
				List<ParametroACOWebService> respuestaWebService =  new ArrayList<ParametroACOWebService>();				
				
				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("numeroRegistros");
				parametroACOWebService.setValor(String.valueOf(interfazResult.getRegistrosProcesados()));
				respuestaWebService.add(parametroACOWebService);
				
				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("nombreArchivoEntradaSalida");
				parametroACOWebService.setValor(getNombreArchivoEntradaSalida(interfazResult));
				respuestaWebService.add(parametroACOWebService);
				
				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("numeroLote");
				parametroACOWebService.setValor(interfazResult.getNumeroLote());
				respuestaWebService.add(parametroACOWebService);
				
				objetoRespuesta.setRespuestaWebService(respuestaWebService.toArray(new ParametroACOWebService[respuestaWebService.size()]));
			}
	     
		}catch (Exception e) {			
			log.error(e.getMessage());
			objetoRespuesta.setMensaje(e.getMessage());
			objetoRespuesta.setRespuestaWebService(null);			
			objetoRespuesta.setEjecucionExitosa(estado);
		}finally{
			log.debug("Estado del servicio: " + estado);
			if(estado){				
				log.info("Se ejecuto el servicio con exito.");					
			}else{
				log.error("Excepcion en el servicio.");				
			}						
		}	
		return objetoRespuesta;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazHiloAbstractWebService#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.model.Pais)
	 */
	@Override
	protected Map prepareParamsBeforeExecute(Map params, Pais pais)
			throws Exception {
		Map queryParams = super.prepareParamsBeforeExecute(params, pais);
		String oidPeriodo=reporteService.getOidString("getDesPerioByOidPerio", queryParams);
		queryParams.put("oidPeriodo", oidPeriodo);
		
		queryParams.put("fechaProcesoArchivo", DateUtil.convertDateToString("yyyyMMdd", DateUtil.convertStringToDate((String)params.get("fechaFacturacion"))));
		queryParams.put("fechaProceso", DateUtil.convertDateToString("yyyyMMdd", DateUtil.convertStringToDate((String)params.get("fechaFacturacion"))));

		String hora =  new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
		queryParams.put("fechaProcesoHora", hora);
		
		String indicadorNovedad = "0";
		queryParams.put("indicadorNovedad", indicadorNovedad);
		
		 MessageContext msgCtx = MessageContext.getCurrentContext();
		 HttpServletRequest httpServletRequest = (HttpServletRequest)msgCtx.  
	        	    getProperty("transport.http.servletRequest");
		queryParams.put("rutaPath",FileUtil.formatDirectory(httpServletRequest.getRealPath("/")));
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", queryParams.get("codigoPais"));
		criteria.put("codigoSistema",Constants.SISTEMA_IMP);
		criteria.put("nombreParametro","documentosSTOReporteErrores");
		
		String valorParametro = this.mantenimientoSTOBloqueoControlService.getParametroGenericoSistema(criteria);
		queryParams.put("documentosSTOReporteErrores",valorParametro);		
		
		LabelValue[] result = ajaxService.getRegionesACerrar(pais.getCodigo());
		
		if (result != null) {
			String[] codigoRegionList = new String[result.length];
			for(int i=0;i<result.length;i++){
				codigoRegionList[i] = result[i].getValue();
			}
			queryParams.put("codigoRegionList",codigoRegionList);
		}
		return queryParams;
	}	

}
