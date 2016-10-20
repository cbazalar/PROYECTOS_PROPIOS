package biz.belcorp.ssicc.service.sisicc.ws.impl;

import java.rmi.RemoteException;
import java.util.Calendar;
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
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.ws.InterfazCOBWebService;
import biz.belcorp.ssicc.service.sisicc.ws.beans.InterfazResultado;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazHIPWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Buchelli </a>
 */
public class InterfazCOBWebServiceImpl extends ServletEndpointSupport implements
	InterfazCOBWebService {
	
	private static final String COD_INTERFAZ_COB = "COB-5";
	private static final String USU_COB_WS = "USUCOBWS";
	//-- Variables
    Log log = LogFactory.getLog(InterfazCOBWebServiceImpl.class);
    SSiCC_Desfasado_InterfazExecutionService interfazExecutionService ;
    UsuarioService usuarioService;    
    InterfazService interfazService;
    
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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.ws.InterfazHIPWebService#executeRecepcionHIPWeb(java.lang.String, java.lang.String, java.lang.String)
	 */
	public InterfazResultado executeEnvioRecuperacionCobranzaByCobrador(String codigoPais,
			String codigoSociedad) throws RemoteException {
	   	 // Bean devuelto por el metodo
        SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
        InterfazResult interfazResult = null;
        InterfazResultado interfazResultado = null;    
        
        try {
            if (log.isDebugEnabled()) {
            	log.debug("Parametros enviados al web service:");
                log.debug("codigoPais : " + codigoPais);
                log.debug("codigoSociedad : " + codigoSociedad);
                log.debug("usuario : " + USU_COB_WS);
            }
            // Validacion de par�metros enviados
            if (StringUtils.isBlank(codigoPais)) {
                throw new ServiceException("Codigo de pais es nulo");
            }
            if (StringUtils.isBlank(codigoSociedad)) {
                throw new ServiceException("Codigo de sociedad es nulo");
            }

            Map params = prepareParamsBeforeExecuteInterfaz(codigoPais,codigoSociedad, USU_COB_WS, COD_INTERFAZ_COB);
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
        log.debug("ini getNombreArchivoEntradaSalida");
        Interfaz interfaz = interfazResult.getInterfaz();
        //String numeroLote = interfazResult.getNumeroLote();
        log.debug("interfaz "+interfaz.getCodigo()+ " pais "+interfaz.getCodigoPais());
	    String codigoSistema = interfaz.getCodigoSistema();
	    String paisMarca = interfaz.getCodigoPais();
	    
	    String anhio = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
	    String mes = (Calendar.getInstance().get(Calendar.MONTH) + 1) < 10 ? "0"+String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1) : String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
	    String dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) < 10 ? "0"+String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) : String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
	    String hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < 10 ? "0"+String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) : String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
	    String minutos = Calendar.getInstance().get(Calendar.MINUTE) < 10 ? "0"+String.valueOf(Calendar.getInstance().get(Calendar.MINUTE)) : String.valueOf(Calendar.getInstance().get(Calendar.MINUTE));
	    
	    nombreArchivo = codigoSistema+"_"+paisMarca+"_"+anhio+mes+dia+"_"+hora+minutos;
	    
	    log.debug("fin getNombreArchivoEntradaSalida "+nombreArchivo);        
        return nombreArchivo;
    }

	/**
	 * @param codigoPais
	 * @param usuario
	 * @param codigoInterfaz
	 * @return
	 */
	private Map prepareParamsBeforeExecuteInterfaz(String codigoPais,
			String codigoSociedad,
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
			params.put("codigoSociedad", codigoSociedad);
			params.put("ES_SSICC_DESFASADO", Constants.SI);
			return params;
	}

}
