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
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.sisicc.framework.SSiCC_Desfasado_InterfazExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.ws.InterfazSATWebService;
import biz.belcorp.ssicc.service.sisicc.ws.beans.InterfazResultado;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazSATWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli </a>
 */
public class InterfazSATWebServiceImpl extends ServletEndpointSupport implements
        InterfazSATWebService {
	
	//-- Variables
    Log log = LogFactory.getLog(InterfazSATWebServiceImpl.class);
    SSiCC_Desfasado_InterfazExecutionService interfazExecutionService ;
    UsuarioService usuarioService;    
    InterfazService interfazService;
    private static String usuario= "USUSATWS";
    
    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
     */
    protected void onInit() throws ServiceException {
		interfazExecutionService = (SSiCC_Desfasado_InterfazExecutionService) getWebApplicationContext().getBean("sisicc_desfasado.interfazExecutionService");
        usuarioService = (UsuarioService)getWebApplicationContext().getBean("usuarioService");
        interfazService = (InterfazService)getWebApplicationContext().getBean("sisicc.interfazService");
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
            nombreArchivo = interfaz.getNombreArchivo(interfaz.getNombreArchivoEntradaSalida() + numeroLote);;
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

	/**
	 * @param codigoPais
	 * @param usuario
	 * @param codigoInterfaz
	 * @return
	 */
	private Map prepareParamsBeforeExecuteInterfaz(String codigoPais,
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
	       
			params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
			params.put("codigoSociedad", Constants.CODIGO_SOCIEDAD_DEFAULT);
			params.put("ES_SSICC_DESFASADO", Constants.SI);
			return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.ws.InterfazSATWebService#executeRecepcionCiaTrasnportesCentroAcopio(java.lang.String)
	 */
	public InterfazResultado executeRecepcionCiaTrasnportesCentroAcopio(
			String codigoPais) throws RemoteException {
	   	 // Bean devuelto por el metodo
        SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
        InterfazResult interfazResult = null;
        InterfazResultado interfazResultado = null;    
        
        try {
            if (log.isDebugEnabled()) {
            	log.debug("Parametros enviados al web service:");
                log.debug("codigoPais : " + codigoPais);
                log.debug("usuario : " + usuario);
            }
            // Validacion de par�metros enviados
            if (StringUtils.isBlank(codigoPais)) {
                throw new ServiceException("Codigo de pais es nulo");
            }

            Map params = prepareParamsBeforeExecuteInterfaz(codigoPais, usuario, "SAT-13");
            params.put("descripcion", getDescripcionInterfaz(params));
            
			executionResult = interfazExecutionService.executeInterfaz(params);
			List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult)list.get(0);
			
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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.ws.InterfazSATWebService#executeRecepcionCoberturaPorCodigoTerritorial(java.lang.String)
	 */
	public InterfazResultado executeRecepcionCoberturaPorCodigoTerritorial(
			String codigoPais) throws RemoteException {
	   	 // Bean devuelto por el metodo
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
        InterfazResult interfazResult = null;
        InterfazResultado interfazResultado = null;    
        
        try {
            if (log.isDebugEnabled()) {
            	log.debug("Parametros enviados al web service:");
                log.debug("codigoPais : " + codigoPais);
                log.debug("usuario : " + usuario);
            }
            // Validacion de par�metros enviados
            if (StringUtils.isBlank(codigoPais)) {
                throw new ServiceException("Codigo de pais es nulo");
            }

            Map params = prepareParamsBeforeExecuteInterfaz(codigoPais, usuario, "SAT-14");
            params.put("descripcion", getDescripcionInterfaz(params));
            
			executionResult = interfazExecutionService.executeInterfaz(params);
			List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult)list.get(0);
			
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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.ws.InterfazSATWebService#executeRecepcionDivisionArmadoCDP(java.lang.String)
	 */
	public InterfazResultado executeRecepcionDivisionArmadoCDP(String codigoPais)
			throws RemoteException {
	   	 // Bean devuelto por el metodo
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
        InterfazResult interfazResult = null;
        InterfazResultado interfazResultado = null;    
        
        try {
            if (log.isDebugEnabled()) {
            	log.debug("Parametros enviados al web service:");
                log.debug("codigoPais : " + codigoPais);
                log.debug("usuario : " + usuario);
            }
            // Validacion de par�metros enviados
            if (StringUtils.isBlank(codigoPais)) {
                throw new ServiceException("Codigo de pais es nulo");
            }

            Map params = prepareParamsBeforeExecuteInterfaz(codigoPais, usuario, "SAT-15");
            params.put("descripcion", getDescripcionInterfaz(params));
            params.put("ES_SSICC_DESFASADO", Constants.SI);
			executionResult = interfazExecutionService.executeInterfaz(params);
			List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult)list.get(0);
			
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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.ws.InterfazSATWebService#executeRecepcionChequeo(java.lang.String)
	 */
	public InterfazResultado executeRecepcionChequeo(String codigoPais)
			throws RemoteException {
	   	 // Bean devuelto por el metodo
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
        InterfazResult interfazResult = null;
        InterfazResultado interfazResultado = null;    
        
        try {
            if (log.isDebugEnabled()) {
            	log.debug("Parametros enviados al web service:");
                log.debug("codigoPais : " + codigoPais);
                log.debug("usuario : " + usuario);
            }
            // Validacion de par�metros enviados
            if (StringUtils.isBlank(codigoPais)) {
                throw new ServiceException("Codigo de pais es nulo");
            }

            Map params = prepareParamsBeforeExecuteInterfaz(codigoPais, usuario, "APE-11");
            params.put("descripcion", getDescripcionInterfaz(params));
            
			executionResult = interfazExecutionService.executeInterfaz(params);
			List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult)list.get(0);
			
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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.ws.InterfazSATWebService#executeRecepcionOrdenImpresionAPESAT(java.lang.String)
	 */
	public InterfazResultado executeRecepcionOrdenImpresionAPESAT(
			String codigoPais) throws RemoteException {
	   	 // Bean devuelto por el metodo
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
        InterfazResult interfazResult = null;
        InterfazResultado interfazResultado = null;    
        
        try {
            if (log.isDebugEnabled()) {
            	log.debug("Parametros enviados al web service:");
                log.debug("codigoPais : " + codigoPais);
                log.debug("usuario : " + usuario);
            }
            // Validacion de par�metros enviados
            if (StringUtils.isBlank(codigoPais)) {
                throw new ServiceException("Codigo de pais es nulo");
            }

            Map params = prepareParamsBeforeExecuteInterfaz(codigoPais, usuario, "SAT-16");
            params.put("descripcion", getDescripcionInterfaz(params));
            
			executionResult = interfazExecutionService.executeInterfaz(params);
			List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult)list.get(0);
			
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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.ws.InterfazSATWebService#executeRecepcionParametrizacionCalculoFechaEntregaExacta(java.lang.String)
	 */
	public InterfazResultado executeRecepcionParametrizacionCalculoFechaEntregaExacta(
			String codigoPais) throws RemoteException {
	   	 // Bean devuelto por el metodo
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
        InterfazResult interfazResult = null;
        InterfazResultado interfazResultado = null;    
        
        try {
            if (log.isDebugEnabled()) {
            	log.debug("Parametros enviados al web service:");
                log.debug("codigoPais : " + codigoPais);
                log.debug("usuario : " + usuario);
            }
            // Validacion de par�metros enviados
            if (StringUtils.isBlank(codigoPais)) {
                throw new ServiceException("Codigo de pais es nulo");
            }

            Map params = prepareParamsBeforeExecuteInterfaz(codigoPais, usuario, "SAT-17");
            params.put("descripcion", getDescripcionInterfaz(params));
            
			executionResult = interfazExecutionService.executeInterfaz(params);
			List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult)list.get(0);
			
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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.ws.InterfazSATWebService#executeRecepcionExcepcionesFechaEntregaExacta(java.lang.String)
	 */
	public InterfazResultado executeRecepcionExcepcionesFechaEntregaExacta(
			String codigoPais) throws RemoteException {
	   	 // Bean devuelto por el metodo
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
        InterfazResult interfazResult = null;
        InterfazResultado interfazResultado = null;    
        
        try {
            if (log.isDebugEnabled()) {
            	log.debug("Parametros enviados al web service:");
                log.debug("codigoPais : " + codigoPais);
                log.debug("usuario : " + usuario);
            }
            // Validacion de par�metros enviados
            if (StringUtils.isBlank(codigoPais)) {
                throw new ServiceException("Codigo de pais es nulo");
            }

            Map params = prepareParamsBeforeExecuteInterfaz(codigoPais, usuario, "SAT-18");
            params.put("descripcion", getDescripcionInterfaz(params));
            
			executionResult = interfazExecutionService.executeInterfaz(params);
			List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult)list.get(0);
			
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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.ws.InterfazSATWebService#executeRecepcionSeguimientoPedido(java.lang.String)
	 */
	public InterfazResultado executeRecepcionSeguimientoPedido(String codigoPais)
			throws RemoteException {
	   	 // Bean devuelto por el metodo
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
        InterfazResult interfazResult = null;
        InterfazResultado interfazResultado = null;    
        
        try {
            if (log.isDebugEnabled()) {
            	log.debug("Parametros enviados al web service:");
                log.debug("codigoPais : " + codigoPais);
                log.debug("usuario : " + usuario);
            }
            // Validacion de par�metros enviados
            if (StringUtils.isBlank(codigoPais)) {
                throw new ServiceException("Codigo de pais es nulo");
            }

            Map params = prepareParamsBeforeExecuteInterfaz(codigoPais, usuario, "SAT-19");
            params.put("descripcion", getDescripcionInterfaz(params));
            
			executionResult = interfazExecutionService.executeInterfaz(params);
			List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult)list.get(0);
			
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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.ws.InterfazSATWebService#executeRecepcionImpresionBoletaEntrega(java.lang.String)
	 */
	public InterfazResultado executeRecepcionImpresionBoletaEntrega(
			String codigoPais) throws RemoteException {
	   	 // Bean devuelto por el metodo
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
        InterfazResult interfazResult = null;
        InterfazResultado interfazResultado = null;    
        
        try {
            if (log.isDebugEnabled()) {
            	log.debug("Parametros enviados al web service:");
                log.debug("codigoPais : " + codigoPais);
                log.debug("usuario : " + usuario);
            }
            // Validacion de par�metros enviados
            if (StringUtils.isBlank(codigoPais)) {
                throw new ServiceException("Codigo de pais es nulo");
            }

            Map params = prepareParamsBeforeExecuteInterfaz(codigoPais, usuario, "SAT-20");
            params.put("descripcion", getDescripcionInterfaz(params));
            
			executionResult = interfazExecutionService.executeInterfaz(params);
			List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult)list.get(0);
			
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

}
