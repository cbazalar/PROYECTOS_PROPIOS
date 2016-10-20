package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoCUPGenerarCCCWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.scsicc.framework.ReporteExecutionService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.spncd.MantenimientoCUPProgDsctoService;
import biz.belcorp.ssicc.service.spusicc.mae.ProcesoMAECalificacionEstatusService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoRECEnviaMsgReclamoWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public class ACOProcesoCUPGenerarCCCWebServiceImpl extends BaseProcesoHiloAbstractWebService implements ACOProcesoCUPGenerarCCCWebService{

	InterfazSiCCService interfazSiCCService;
	MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
	ProcesoMAECalificacionEstatusService procesoMAECalificacionEstatusService;
	ReporteExecutionService reporteExecutionService;
	MantenimientoCUPProgDsctoService mantenimientoCUPProgDsctoService;
	
	protected void onInit() throws ServiceException {
		super.onInit();
		this.interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
		this.mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		this.procesoMAECalificacionEstatusService = (ProcesoMAECalificacionEstatusService)getWebApplicationContext().getBean("spusicc.procesoMAECalificacionEstatusService");
		this.reporteExecutionService = (ReporteExecutionService)getWebApplicationContext().getBean("scsicc.reporteExecutionService");
		this.mantenimientoCUPProgDsctoService = (MantenimientoCUPProgDsctoService)getWebApplicationContext().getBean("spncd.mantenimientoCUPProgDsctoService");
	}

	
	public ACOWebServiceResponse ejecutarProcesoCUPGenerarCCC(
			String codigoUsuario,
			String codigoPais,			
			String codigoPeriodo,
			String fechaFacturacion,
			String codigoPrograma) throws RemoteException {
		
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";		
		final String CODIGO_BATCH = "02";
		final String CODIGO_SISTEMA = "CCC";
		final String CODIGO_PROGRAMA = "027";
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz ejecutarProcesoCUPGenerarCCC");
		}
		try{
			Pais pais = this.paisService.getPais(codigoPais);
			if(StringUtils.isBlank(codigoPais) || StringUtils.isEmpty(codigoPais)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null,
				getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			
			
			Map criteria = new HashMap();
			
			// Asignamos al codigo del periodo el valor por defecto
	        criteria.put("codigoPais", pais.getCodigo());
	        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		    criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
			
			PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);
			if (StringUtils.isBlank(codigoPeriodo) || StringUtils.isEmpty(codigoPeriodo)) {
	        	codigoPeriodo = controlFacturacion.getCodigoPeriodo();
			}
			if(StringUtils.isBlank(fechaFacturacion) || StringUtils.isEmpty(fechaFacturacion)){
	        	fechaFacturacion = controlFacturacion.getFechaProceso();
	        }
			criteria.put("fechaFacturacion", fechaFacturacion);
			criteria.put("codigoPeriodo", codigoPeriodo);
			
			/**
		     * Valida usuario
		     */
		    if(StringUtils.isBlank(codigoUsuario)|| StringUtils.isEmpty(codigoUsuario)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);
		    }
		    
//		    Usuario usuario = this.usuarioService.getUsuarioByUsername(codigoUsuario);
		    Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);
		    
		    criteria.put("codigoISO",usuario.getIdioma().getCodigoISO());
		    criteria.put("codigoUsuario", codigoUsuario);
		    criteria.put("usuario", usuario);
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        
    		
	        List programaCuponList = this.mantenimientoCUPProgDsctoService.getProgramasDescuentosbyPais(criteria);
	        
	        /**
			 * Si acceso es vacio o en blanco, traer el de por defecto
			 */
			if(StringUtils.isBlank(codigoPrograma) || StringUtils.isEmpty(codigoPrograma)){
				codigoPrograma = CODIGO_PROGRAMA;				
			}else{
				EqualPredicate nameEqlPredicate = new EqualPredicate(codigoPrograma);
				BeanPredicate beanPredicate = new BeanPredicate("codigo", nameEqlPredicate);					
				if(programaCuponList.size()!=0){
					if(!CollectionUtils.exists(programaCuponList,beanPredicate)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExistePrograma",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de codigo programa vacio.");
				}
			}
	        
	        //criteria.put("codigoPrograma",);
	        
    		this.executeProceso(criteria);
 		    objetoRespuesta.setEjecucionExitosa(true);
	     
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


	protected Map executeProcess(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("executeProcess");
		}
		params.put("periodo", (String)params.get("codigoPeriodo"));
		params.put("fechaFact", (String)params.get("fechaFacturacion"));
        params.put("mostrarPaginaConsultaBatch", "S");        
        this.mantenimientoOCRPedidoControlFacturacionService.executeProcesoOCRActualizaGP2(params);
        
        //Calificacion Estatus por Facturaci�n Diaria        
        //this.procesoMAECalificacionEstatusService.executeCalificacionEstatusFacturacionDiaria(params);
		
		/*ReporteParams reportParams = new ReporteParams();
        reportParams.setJasperFileName("reporteSACIndicadoresFNA.jasper");
        Map queryParams = new HashMap();
        queryParams.put("codigoPeriodo",(String)params.get("codigoPeriodo"));
        queryParams.put("fechaHasta",(String)params.get("fechaFacturacion"));
        reportParams.setQueryParams(queryParams);       
        ReporteResult reporteResult = reporteExecutionService.executeReporte(reportParams);
        
		String outfile = new String("");// archivo de salida
		outfile += "reporteGenerico.xls";
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, reporteResult.getJasperPrint());
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outfile );
		exporter.exportReport();
        
		params.put("fileAttachment", new File(outfile));		
		procesoMAECalificacionEstatusService.enviarCorreoAdjunto(params);*/
		return params;
	}	
	
	
}
