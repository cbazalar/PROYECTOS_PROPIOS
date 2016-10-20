package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoLARGenerarLARFacturacionElectronicaWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazLARService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoLARGenerarLARFacturacionElectronicaWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
public class ACOProcesoLARGenerarLARFacturacionElectronicaWebServiceImpl extends BaseInterfazHiloAbstractWebService implements ACOProcesoLARGenerarLARFacturacionElectronicaWebService {

	ReporteService reporteService;
	AjaxService ajaxService;
	InterfazLARService interfazLARService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazHiloAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		reporteService = (ReporteService) getWebApplicationContext().getBean("scsicc.reporteService");
		ajaxService = (AjaxService) getWebApplicationContext().getBean("ajaxService");
		interfazLARService = (InterfazLARService) getWebApplicationContext().getBean("sisicc.interfazLARService");
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoLARGenerarLARFacturacionElectronicaWebService#ejecutarProcesoLARGenerarLARFacturacionElectronica(java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.String[], java.lang.String[], java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoLARGenerarLARFacturacionElectronica(
			String codigoPais, String codigoPeriodo, String fecha,
			
			String desde, String hasta, String codigoUsuario)
			throws RemoteException {

		String[] tipoDocumentoList = null;
		String[] regionList= null;
		String[] zonaList= null;
		
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";
		String CODIGO_BATCH = Constants.LAR_CODIGO_PROCESO_BATCH_GENERAR_LAR8_FACTURACION_ELECTRONICA;
		String CODIGO_INTERFAZ = Constants.LAR_CODIGO_INTERFAZ_GENERAR_LAR8_FACTURACION_ELECTRONICA;
		String CODIGO_SISTEMA = Constants.CODIGO_SISTEMA_LAR;
		
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
			
			Usuario user = this.obtenerUsuarioByDefault(codigoUsuario);
			
			Map criteria = new HashMap();
			
			// Asignamos al codigo del periodo el valor por defecto
	        criteria.put("codigoPais", pais.getCodigo());
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
	        
	        if(StringUtils.isBlank(fecha) || StringUtils.isEmpty(fecha)){
	        	fecha = controlFacturacion.getFechaProceso();
	        }
	        
	        criteria.put("fecha", fecha);
	        
	        // tipoDocumentoList
	        if(tipoDocumentoList != null && tipoDocumentoList.length > 0)
	        {
				List tiposDocumento = getTiposDocumento();						
				for(int i=0; i<tipoDocumentoList.length; i++)
				{
					EqualPredicate nameEqlPredicate = new EqualPredicate(tipoDocumentoList[i]);
					BeanPredicate beanPredicate = new BeanPredicate("codigo", nameEqlPredicate);					
					if(tiposDocumento.size() != 0)
					{
						if(!CollectionUtils.exists(tiposDocumento, beanPredicate))
						{
							mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCodigoTipoDocumento.1",new String[]{tipoDocumentoList[i]},getLocaleIdioma(pais.getCodigoIdiomaIso()));
							throw new Exception(mensajeError);
						}
					}
					else
					{
						log.debug("Listado de codigo de tipoDocumento vacio.");
					}
				}				
	        }
	        //
	        
	        // validar regionList
			if(regionList != null && regionList.length > 0)
			{
				List regiones = reporteService.getListaGenerico("getRegionesByPais",criteria);						
				for(int i=0; i<regionList.length; i++)
				{
					EqualPredicate nameEqlPredicate = new EqualPredicate(regionList[i]);
					BeanPredicate beanPredicate = new BeanPredicate("codigo", nameEqlPredicate);					
					if(regiones.size() != 0)
					{
						if(!CollectionUtils.exists(regiones, beanPredicate))
						{
							mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCodigoRegion.1",new String[]{regionList[i]},getLocaleIdioma(pais.getCodigoIdiomaIso()));
							throw new Exception(mensajeError);
						}
					}
					else
					{
						log.debug("Listado de codigo de region vacio.");
					}
				}				
			}
			//
	        
	        // Validar zonaList
			if(zonaList != null && zonaList.length > 0)
			{
				LabelValue[] zonasRegiones = ajaxService.getZonasMultipleByPaisMarcaCanalRegion(codigoPais, Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, regionList, "T");				
				List zonasRegionesList = new ArrayList(); 
				zonasRegionesList.addAll(Arrays.asList(zonasRegiones));
				for(int i=0; i<zonaList.length; i++){
					EqualPredicate nameEqlPredicate = new EqualPredicate(zonaList[i]);
					BeanPredicate beanPredicate = new BeanPredicate("value", nameEqlPredicate);					
					if(zonasRegionesList.size() != 0){
						if(!CollectionUtils.exists(zonasRegionesList, beanPredicate)){
							mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCodigoZona.1",new String[]{zonaList[i]},getLocaleIdioma(pais.getCodigoIdiomaIso()));
							throw new Exception(mensajeError);
						}
					}else{
						log.debug("Listado de codigo de zona vacio.");
					}
				}				
			}
	        //
			
			criteria.put("tipoDocumento", (tipoDocumentoList == null || tipoDocumentoList.length == 0) ? new ArrayList() : Arrays.asList(tipoDocumentoList));
			criteria.put("zonaList", (zonaList == null || zonaList.length == 0) ? new ArrayList() : Arrays.asList(zonaList));
			criteria.put("regionList", (regionList == null || regionList.length == 0) ? new ArrayList() : Arrays.asList(regionList));
			
			criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			criteria.put("desde", desde);
			criteria.put("hasta", hasta);
			
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
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazAbstractWebService#beforeExecuteInterfaz(java.util.Map)
	 */
	@Override
	protected void beforeExecuteInterfaz(Map params) {
    	super.beforeExecuteInterfaz(params);    	
            	
    	if(log.isDebugEnabled())
    		log.debug("Metodo beforeExecuteInterfaz");
    	
    	List tipoDocList = (List)params.get("tipoDocumento");
    	List zonaList = (List)params.get("zonaList");
    	List regionList = (List)params.get("regionList");
    	 
    	interfazLARService.deleteInterfazLAR8Parametros();
    	
    	if(tipoDocList!=null && tipoDocList.size()>0){
    		for(int i=0; i<tipoDocList.size(); i++){
    			String tipoDocumento = (String)tipoDocList.get(i);
    			Map map = new HashMap();
    			map.put("tipoParam", "TIPODOC");
    			map.put("tipoDocumento", tipoDocumento);
    			interfazLARService.insertInterfazLAR8Parametros(map);
    		}    		
    	}
    	
    	if(zonaList!=null && zonaList.size()>0){
    		for(int i=0; i<zonaList.size(); i++){
    			String zona = (String)zonaList.get(i);
    			Map map = new HashMap();
    			map.put("tipoParam", "ZONA");
    			map.put("codigoZona", zona);
    			interfazLARService.insertInterfazLAR8Parametros(map);
    		}    		
    	}
    	
    	if(regionList!=null && regionList.size()>0){
    		for(int i=0; i<regionList.size(); i++){
    			String region = (String)regionList.get(i);
    			Map map = new HashMap();
    			map.put("tipoParam", "REGION");
    			map.put("codigoRegion", region);
    			interfazLARService.insertInterfazLAR8Parametros(map);
    		}    		
    	}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazAbstractWebService#afterExecuteInterfaz(java.util.Map, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazExecutionResult)
	 */
	@Override
	protected void afterExecuteInterfaz(Map params,
			SSiCC_Desfasado_InterfazExecutionResult interfazExecutionResult) throws Exception {
		super.afterExecuteInterfaz(params, interfazExecutionResult);
		
    	if(log.isDebugEnabled())
    		log.debug("Metodo afterExecuteInterfaz");
		
		interfazLARService.deleteInterfazLAR8Parametros();
	}

	/**
	 * 
	 * @return
	 */
	private List getTiposDocumento()
	{
		List tipos = new ArrayList();
		
		Base t1 = new Base();
		t1.setCodigo("001");
		
		Base t2 = new Base();
		t2.setCodigo("011");

		Base t3 = new Base();
		t3.setCodigo("012");
		
		tipos.add(t1);
		tipos.add(t2);
		tipos.add(t3);
		
		return tipos;
	}
}
