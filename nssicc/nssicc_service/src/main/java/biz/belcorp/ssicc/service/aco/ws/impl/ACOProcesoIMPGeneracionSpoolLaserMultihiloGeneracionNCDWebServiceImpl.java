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
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoIMPGeneracionSpoolLaserMultihiloGeneracionNCDWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.service.util.FileUtil;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoIMPGeneracionSpoolLaserMultihiloWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
public class ACOProcesoIMPGeneracionSpoolLaserMultihiloGeneracionNCDWebServiceImpl extends BaseInterfazHiloAbstractWebService implements ACOProcesoIMPGeneracionSpoolLaserMultihiloGeneracionNCDWebService {

	InterfazSiCCService interfazSiCCService;
	MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
	MantenimientoSTOBloqueoControlService mantenimientoSTOBloqueoControlService;
	ReporteService reporteService;
	AjaxService ajaxService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazHiloAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		this.interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
		this.mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		this.mantenimientoSTOBloqueoControlService = (MantenimientoSTOBloqueoControlService) getWebApplicationContext().getBean("spusicc.mantenimientoSTOBloqueoControlService");
		this.reporteService = (ReporteService) getWebApplicationContext().getBean("scsicc.reporteService");
		this.ajaxService = (AjaxService) getWebApplicationContext().getBean("ajaxService");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoIMPGeneracionSpoolLaserMultihiloGeneracionNCDWebService#ejecutarProcesoIMPGeneracionSpoolLaserMultihilo(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoIMPGeneracionSpoolLaserMultihilo(
			String codigoPais, String codigoMarca, String codigoCanal,
			String codigoPeriodo, String fechaFacturacion, String codigoUsuario)
			throws RemoteException {

		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";
		String CODIGO_BATCH = Constants.IMP_CODIGO_PROCESO_BATCH_GENERACION_NOTAS_CREDITO_DEBITO;
		String CODIGO_INTERFAZ = Constants.IMP_CODIGO_INTERFAZ_GENERACION_NOTAS_CREDITO_DEBITO;
		String CODIGO_SISTEMA = Constants.IMP_CODIGO_SISTEMA;
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz ProcesoIMPGeneracionSpoolLaserMultihilo");
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
			
			List siccMarcaList = this.interfazSiCCService.getMarcas();
			List siccCanalList = this.interfazSiCCService.getCanalesByCodigoISO(pais.getCodigoIdiomaIso());
			
			/**
			 * Si marca es vacio o en blanco, traer el de por defecto
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
			 * Si canal es vacio o en blanco, traer el de por defecto
			 */
			if(StringUtils.isBlank(codigoCanal) || StringUtils.isEmpty(codigoCanal)){
				codigoCanal = Constants.CODIGO_MARCA_DEFAULT;				
			}else{
				if(siccCanalList.size()!=0){
					if(!existeCodigoEnLista(siccCanalList, codigoCanal)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCanal",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de canal vacio.");
				}
			}
			
			Usuario user = this.obtenerUsuarioByDefault(codigoUsuario);
			
			Map criteria = new HashMap();
			
			// Asignamos al codigo del periodo el valor por defecto
	        criteria.put("codigoPais", pais.getCodigo());
	        criteria.put("codigoMarca", codigoMarca);
	        criteria.put("codigoCanal", codigoCanal);
	        criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
			criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
			criteria.put("codigoUsuario", codigoUsuario);
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
			
			PedidoControlFacturacion controlFacturacion = (PedidoControlFacturacion) this.mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);
			
	        if (StringUtils.isBlank(codigoPeriodo) || StringUtils.isEmpty(codigoPeriodo)) {
	        	codigoPeriodo = controlFacturacion.getCodigoPeriodo();
			}
	        
	        criteria.put("codigoPeriodo", codigoPeriodo);
	        
	        if(StringUtils.isBlank(fechaFacturacion) || StringUtils.isEmpty(fechaFacturacion)){
	        	fechaFacturacion = controlFacturacion.getFechaProceso();
	        }
	        
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
	
	protected Map prepareParamsBeforeExecute(Map params, Pais pais) throws Exception {
		
		Map queryParams = params;
		String oidPeriodo = reporteService.getOidString("getDesPerioByOidPerio", queryParams);
		queryParams.put("oidPeriodo", oidPeriodo);

		queryParams.put("fechaProcesoArchivo", DateUtil.convertDateToString("yyyyMMdd", DateUtil.convertStringToDate((String)params.get("fechaFacturacion"))));
		queryParams.put("fechaProceso", DateUtil.convertDateToString("yyyyMMdd", DateUtil.convertStringToDate((String)params.get("fechaFacturacion"))));

		String hora =  new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
		queryParams.put("fechaProcesoHora", hora);
		
		String indicadorNovedad = "0";
		queryParams.put("indicadorNovedad", indicadorNovedad);
		
		//params.put("rutaPath", getWebApplicationContext().getServletContext().getRealPath("/"));
		//params.put("rutaPath", this.usuarioService.getRutaPath());
		 MessageContext msgCtx = MessageContext.getCurrentContext();
	     HttpServletRequest httpServletRequest = (HttpServletRequest)msgCtx.  
	        	    getProperty("transport.http.servletRequest"); 
		params.put("rutaPath", FileUtil.formatDirectory(httpServletRequest.getRealPath("/")));		
		Map criteria = new HashMap();
		criteria.put("codigoPais", queryParams.get("codigoPais"));
		criteria.put("codigoSistema",Constants.SISTEMA_IMP);
		criteria.put("nombreParametro","documentosSTOReporteErrores");
		
		String valorParametro = mantenimientoSTOBloqueoControlService.getParametroGenericoSistema(criteria);
		queryParams.put("documentosSTOReporteErrores",valorParametro);
		String codigoPais = MapUtils.getString(queryParams, "codigoPais");
		LabelValue[] result = ajaxService.getRegionesACerrar( codigoPais);
		
		if (result != null) {
			String[] codigoRegionList = new String[result.length];
			for(int i=0;i<result.length;i++){
				codigoRegionList[i] = result[i].getValue();
			}
			queryParams.put("codigoRegionList",codigoRegionList);
		}
		
		return super.prepareParamsBeforeExecute(params, pais);
	}
}
