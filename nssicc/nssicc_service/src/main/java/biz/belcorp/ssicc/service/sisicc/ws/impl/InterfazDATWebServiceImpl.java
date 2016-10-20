/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.sisicc.ws.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.sisicc.framework.SSiCC_Desfasado_InterfazExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.impl.InterfazExecutionServiceImpl;
import biz.belcorp.ssicc.service.sisicc.ws.InterfazDATWebService;
import biz.belcorp.ssicc.service.sisicc.ws.beans.InterfazResultado;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazDATWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli </a>
 */
public class InterfazDATWebServiceImpl extends ServletEndpointSupport implements
        InterfazDATWebService {

    Log log = LogFactory.getLog(InterfazDATWebServiceImpl.class);

    SSiCC_Desfasado_InterfazExecutionService interfazExecutionService ;
    
    MantenimientoOCRPedidoControlFacturacionService controlFacturacionService;
    
    UsuarioService usuarioService;    
    
    InterfazExecutionServiceImpl interfazserviceImpl;
    
    InterfazService interfazService;
    
    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
     */
    protected void onInit() throws ServiceException {
		interfazExecutionService = (SSiCC_Desfasado_InterfazExecutionService) getWebApplicationContext()
				.getBean("sisicc_desfasado.interfazExecutionService");

        controlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
        
        usuarioService = (UsuarioService)getWebApplicationContext().getBean("usuarioService");
        
        interfazService = (InterfazService)getWebApplicationContext().getBean("sisicc.interfazService");
    }
    
 

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.web.ws.InterfazDATWebService#executeInterfazDATEnviarAdministracionFlujos(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public InterfazResultado executeInterfazDATEnviarAdministracionFlujos(String codigoPais,
    		String codigoInterfaz,
    		String codigoPeriodo,
    		String fechaFacturacion,
    		String codigoMarca,
    		String codigoCanal,
    		String codigoAcceso,    		
    		String usuario)throws RemoteException{
    	
    	 // Bean devuelto por el metodo
        SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
        InterfazResult interfazResult = null;
        InterfazResultado interfazResultado = null;    
        
        try {
            if (log.isDebugEnabled()) {
            	log.debug("Parametros enviados al web service:");
                log.debug("codigoPais : " + codigoPais);
                log.debug("codigoInterfaz : " + codigoInterfaz);
                log.debug("codigoPeriodo : " + codigoPeriodo);
                log.debug("fechaFacturacion : " + fechaFacturacion);
                log.debug("codigoMarca : " + codigoMarca);
                log.debug("codigoCanal : " + codigoCanal);
                log.debug("codigoAcceso : " + codigoAcceso);                
                log.debug("usuario : " + usuario);
            }
            // Validacion de par�metros enviados
            if (StringUtils.isBlank(codigoPais)) {
                throw new ServiceException("Codigo de pais es nulo");
            }

            if (StringUtils.isBlank(codigoInterfaz)) {
                throw new ServiceException("Codigo de Interfaz es nulo");
            }

            if (StringUtils.isBlank(codigoPeriodo)) {
                throw new ServiceException("Codigo de periodo es nulo");
            }

            if (StringUtils.isBlank(fechaFacturacion)) {
                throw new ServiceException("Fecha Facturacion es nulo");
            }
            
            if (StringUtils.isBlank(codigoMarca)) {
                throw new ServiceException("Codigo Marca es nulo");
            }

            if (StringUtils.isBlank(codigoCanal)) {
                throw new ServiceException("Codigo Canal es nulo");
            }

            if (StringUtils.isBlank(codigoAcceso)) {
                throw new ServiceException("Codigo Acceso es nulo");
            }
            
            
            Map params = prepareParamsBeforeExecuteInterfazDAT(codigoPais,usuario,codigoInterfaz,
            												   codigoPeriodo,fechaFacturacion,
            												   codigoMarca,codigoCanal,codigoAcceso);
            params.put("descripcion", getDescripcionInterfaz(params));
//            // Invocamos al servicio 
//            Map mapInterfaz =interfazserviceImpl.getInterfazImplementations();
//           
//            interfazSAMEnviarMovimientosAlmacenService = (BaseInterfazService)mapInterfaz.get(codigoInterfaz);
			executionResult = interfazExecutionService.executeInterfaz(params);
			List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult)list.get(0);
			//logger.debug("erere "+ interfazResult.getMensaje());
			if (interfazResult != null){
				interfazResultado = new InterfazResultado();
				interfazResultado.setNumeroRegistros(interfazResult.getRegistrosProcesados());
				interfazResultado.setCompletado(interfazResult.isCompletado());
				interfazResultado.setMensaje(interfazResult.getMensaje());
				interfazResultado.setNombreArchivoEntradaSalida(getNombreArchivoEntradaSalida(interfazResult));
				interfazResultado.setNumeroLote(interfazResult.getNumeroLote());
			}
		
        } catch (Exception e) {
           // log.debug(e.getMessage(), e);            
            interfazResultado = new InterfazResultado();
			interfazResultado.setNumeroRegistros(0);
			interfazResultado.setCompletado(false);
			interfazResultado.setMensaje(e.getMessage());            
           // throw new RemoteException(e.getMessage());
        }

        if (log.isDebugEnabled()) {
            if (executionResult != null) {
                log.debug("Bean de retorno executionResult: "
                        + executionResult);
            }else{
            	log.debug("Bean de retorno executionResult: NULL ");	
            }
        }
      
        return interfazResultado;

    }
    /**
     * Retorna la descripcion de la intefaz
     * @param params
     * @return
     */
    private String getDescripcionInterfaz(Map params) {
    	
  	  	String codigoPais = (String) params.get("codigoPais");
		String codigoSistema = (String) params.get("codigoSistema");
		String codigoInterfaz = (String) params.get("codigoInterfaz");
		InterfazPK interfazEjecucionPK = new InterfazPK(codigoPais,
				codigoSistema, codigoInterfaz);
		Interfaz interfazEjecucion = interfazService
				.getInterfaz(interfazEjecucionPK);
		return (interfazEjecucion!=null?interfazEjecucion.getDescripcion():"");
	}


	/**
     * @param codigoPais
     * @param usuarioBiztalk
     * @param codigoInterfaz
	 * @param codigoAcceso 
	 * @param codigoCanal 
	 * @param codigoMarca 
     * @param fechaProceso 
     * @return
     * @throws Exception
     */
    private Map prepareParamsBeforeExecuteInterfazDAT(String codigoPais, String login, String codigoInterfaz,
    															String codigoPeriodo, String fechaFacturacion,
    															String codigoMarca, String codigoCanal, 
    															String codigoAcceso) throws Exception {
		// Paso todos los parametros al map
		Map params = new HashMap();		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  

        PedidoControlFacturacion controlFacturacion = controlFacturacionService.getControlFacturacionById(criteria);
        
        // Si no se envio algun parametro, se obtiene el periodo activo
        if(StringUtils.isEmpty(codigoPeriodo) ||
				StringUtils.isEmpty(fechaFacturacion)){
            if (log.isDebugEnabled()) {
	        	log.debug("No se envio alguno de los parametros de periodo o fecha.");
	        	log.debug("Obteniendo los datos del periodo activo.");
            }
        	
        	// Validamos que exista un periodo activo
        	if(controlFacturacion == null) {
        		throw new ServiceException("No existe un periodo activo.");
        	}
        	codigoPeriodo = controlFacturacion.getCodigoPeriodo();
        	fechaFacturacion = controlFacturacion.getFechaProceso();
		}
		
        Usuario usuario = new Usuario();
        usuario.setLogin(login);//usuarioService.getUsuarioByUsername(login); 
        if (log.isDebugEnabled()) {
            log.debug("usuario : " + usuario);
        }
        String codigoSistema = codigoInterfaz.substring(0,codigoInterfaz.indexOf('-'));
        params.put("codigoPais"	,  codigoPais);
        params.put("codigoSistema",codigoSistema);
        params.put("codigoInterfaz", codigoInterfaz);   
		params.put("usuario", usuario);
        params.put("codigoPeriodo",codigoPeriodo);
        params.put("codigoMarca",  codigoMarca);
        params.put("codigoCanal",  codigoCanal);
        params.put("codigoAcceso", codigoAcceso);       
		return params;
	}

    /**
     * Devuelve el mpmbre del archivo
     * @param interfazResult
     * @return
     */
    private String getNombreArchivoEntradaSalida(InterfazResult interfazResult) {
        String nombreArchivo;

        Interfaz interfaz = interfazResult.getInterfaz();
        String numeroLote = interfazResult.getNumeroLote();

        // Tipo de nombre fijo
        if (interfaz.getTipoNombreArchivo().equalsIgnoreCase(
                Constants.ARCHIVO_FIJO)) {
            nombreArchivo = interfaz.getNombreArchivo(interfaz.getNombreArchivoEntradaSalida() + numeroLote);
          
        }
        // Tipo de nombre variable
        else {
            String nombreArchivoSalida = interfaz
                    .getNombreArchivoEntradaSalida();
            if (StringUtils.isEmpty(nombreArchivoSalida)
                    || StringUtils.isEmpty(nombreArchivoSalida)) {
                nombreArchivoSalida = interfaz.getCodigo() + "_";
            }
            nombreArchivo = interfaz.getNombreArchivo(nombreArchivoSalida + numeroLote);
        }
        return nombreArchivo;
    }



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.ws.InterfazDATWebService#executeRecepcionOCSWebDD(java.lang.String, java.lang.String, java.lang.String)
	 */
	public InterfazResultado executeRecepcionOCSWebDD(String codigoPais,
			String codigoInterfaz, String usuario) throws RemoteException {
	   	 // Bean devuelto por el metodo
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
        InterfazResult interfazResult = null;
        InterfazResultado interfazResultado = null;    
        
        try {
            if (log.isDebugEnabled()) {
            	log.debug("Parametros enviados al web service:");
                log.debug("codigoPais : " + codigoPais);
                log.debug("codigoInterfaz : " + codigoInterfaz);                       
                log.debug("usuario : " + usuario);
            }
            // Validacion de par�metros enviados
            if (StringUtils.isBlank(codigoPais)) {
                throw new ServiceException("Codigo de pais es nulo");
            }

            if (StringUtils.isBlank(codigoInterfaz)) {
                throw new ServiceException("Codigo de Interfaz es nulo");
            }
                        
            Map params = prepareParamsBeforeExecuteInterfazOCS(codigoPais,usuario,codigoInterfaz);
            params.put("descripcion", getDescripcionInterfaz(params));
//            // Invocamos al servicio 
//            Map mapInterfaz =interfazserviceImpl.getInterfazImplementations();
//           
//            interfazSAMEnviarMovimientosAlmacenService = (BaseInterfazService)mapInterfaz.get(codigoInterfaz);
			executionResult = interfazExecutionService.executeInterfaz(params);
			List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult)list.get(0);
			//logger.debug("erere "+ interfazResult.getMensaje());
			if (interfazResult != null){
				interfazResultado = new InterfazResultado();
				interfazResultado.setNumeroRegistros(interfazResult.getRegistrosProcesados());
				interfazResultado.setCompletado(interfazResult.isCompletado());
				interfazResultado.setMensaje(interfazResult.getMensaje());
				interfazResultado.setNombreArchivoEntradaSalida(getNombreArchivoEntradaSalida(interfazResult));
				interfazResultado.setNumeroLote(interfazResult.getNumeroLote());
			}
		
        } catch (Exception e) {
           // log.debug(e.getMessage(), e);            
            interfazResultado = new InterfazResultado();
			interfazResultado.setNumeroRegistros(0);
			interfazResultado.setCompletado(false);
			interfazResultado.setMensaje(e.getMessage());            
           // throw new RemoteException(e.getMessage());
        }

        if (log.isDebugEnabled()) {
            if (executionResult != null) {
                log.debug("Bean de retorno executionResult: "
                        + executionResult);
            }
        }
      
        return interfazResultado;

	}



	/**
	 * @param codigoPais
	 * @param usuario
	 * @param codigoInterfaz
	 * @return
	 */
	private Map prepareParamsBeforeExecuteInterfazOCS(String codigoPais,
			String login, String codigoInterfaz) {
			Map params = new HashMap();				
		    Usuario usuario = new Usuario();
	        usuario.setLogin(login);//usuarioService.getUsuarioByUsername(login); 
	        if (log.isDebugEnabled()) {
	            log.debug("usuario : " + usuario);
	        }
	        String codigoSistema = codigoInterfaz.substring(0,codigoInterfaz.indexOf('-'));
	        params.put("codigoPais"	,  codigoPais);
	        params.put("codigoSistema",codigoSistema);
	        params.put("codigoInterfaz", codigoInterfaz);   
			params.put("usuario", usuario);
	       
			return params;
	}

}
