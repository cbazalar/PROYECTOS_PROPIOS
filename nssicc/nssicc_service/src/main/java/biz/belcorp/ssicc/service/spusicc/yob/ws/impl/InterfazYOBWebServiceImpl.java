/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.spusicc.yob.ws.impl;

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
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.ws.beans.InterfazResultado;
import biz.belcorp.ssicc.service.spusicc.yob.ws.InterfazYOBWebService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazYOBWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">sbuchelli </a>
 */
public class InterfazYOBWebServiceImpl extends ServletEndpointSupport implements
				InterfazYOBWebService {

    Log log = LogFactory.getLog(InterfazYOBWebServiceImpl.class);    
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
	 * @see biz.belcorp.ssicc.spusicc.yob.web.ws.InterfazYOBWebService#executeYOBCargaLotesTrazabilidadWeb(java.lang.String, java.lang.String, java.lang.String)
	 */
	public InterfazResultado executeYOBCargaLotesTrazabilidadWeb(String codigoPais,
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
                        
            Map params = prepareParamsBeforeExecuteInterfazYOB(codigoPais,usuario,codigoInterfaz);
            params.put("descripcion", getDescripcionInterfaz(params));
        	String indicadorNovedad = "1";
        	params.put("indicadorNovedad", indicadorNovedad);
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
	private Map prepareParamsBeforeExecuteInterfazYOB(String codigoPais,
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
			params.put("ES_SSICC_DESFASADO", Constants.SI);
			return params;
	}
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.ws.InterfazSAMWebService#cargaLotexProd(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String[] cargaLotexProd(String codigoPais, String usuario,
			String nombreArchivo) {
		log.debug("inicio cargaLotexProd ");
		String [] result = new String[2];
		result[0]= Constants.NUMERO_UNO;
		result[1]="";				
		log.debug("fin cargaLotexProd "+result[0]);
		return result;
	}

}
