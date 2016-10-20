package biz.belcorp.ssicc.service.sisicc.ws.impl;

import java.rmi.RemoteException;
import java.util.Date;
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
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.sisicc.framework.SSiCC_Desfasado_InterfazExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.ws.InterfazPEDWebService;
import biz.belcorp.ssicc.service.sisicc.ws.beans.InterfazResultado;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazPEDWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:ghuertasa@sigcomt.com">Gonzalo Javier Huertas Agurto</a>
 */
public class InterfazPEDWebServiceImpl extends ServletEndpointSupport implements InterfazPEDWebService{
	//-- Variables
    Log log = LogFactory.getLog(InterfazPEDWebServiceImpl.class);
    SSiCC_Desfasado_InterfazExecutionService interfazExecutionService ;
    UsuarioService usuarioService;    
    InterfazService interfazService;
    GenericoService genericoService;
    
    private static String usuario= "USUSATWS";
    private Date fechaSistema = new Date();
    
    /* (non-Javadoc)
     * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
     */
    protected void onInit() throws ServiceException {
		interfazExecutionService = (SSiCC_Desfasado_InterfazExecutionService) getWebApplicationContext().getBean("sisicc_desfasado.interfazExecutionService");
        usuarioService = (UsuarioService)getWebApplicationContext().getBean("usuarioService");
        interfazService = (InterfazService)getWebApplicationContext().getBean("sisicc.interfazService");
        genericoService = (GenericoService)getWebApplicationContext().getBean("genericoService");
    }
    
	public InterfazResultado executeEnviarMatrizFacturacionFutura(String codigoCentro, String codigoCampanha) throws RemoteException {
	
        SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
        InterfazResult interfazResult = null;
        InterfazResultado interfazResultado = null;    
        
        try {
            
            //Obtenemos el pais por default
            String codigoPais = genericoService.getParametroPais("", Constants.SISTEMA_GEN, Constants.GEN_CODIGO_PARAMETRO_PAIS_DEFAULT);

            if (log.isDebugEnabled()) {
            	log.debug("Parametros enviados al web service:");
                log.debug("codigoCentro : " + codigoCentro);
                log.debug("codigoCampanha : " + codigoCampanha);
                log.debug("usuario : " + usuario);
            }
            
            // Validacion de par?metros enviados
            if (StringUtils.isBlank(codigoPais)) {
                throw new ServiceException("Codigo de Pais es nulo");
            }
            if (StringUtils.isBlank(codigoCentro)) {
                throw new ServiceException("Codigo de Centro es nulo");
            }
            if (StringUtils.isBlank(codigoCampanha)) {
                throw new ServiceException("Codigo de Campa?a es nulo");
            }
            
            Map params = prepareParamsBeforeExecuteInterfaz(codigoPais, codigoCentro, codigoCampanha, usuario, Constants.INTERFAZ_PED_ENVIAR_MATRIZ_FACTURACION_FUTURA);
            
            
			executionResult = interfazExecutionService.executeInterfaz(params);
			List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult)list.get(0);
			
			if (interfazResult != null){
				interfazResultado = new InterfazResultado();
				interfazResultado.setNumeroRegistros(interfazResult.getRegistrosProcesados());
				interfazResultado.setCompletado(interfazResult.isCompletado());
				interfazResultado.setMensaje(interfazResult.getMensaje());
				interfazResultado.setNombreArchivoEntradaSalida(getNombreArchivoEntradaSalida(interfazResult,codigoCentro));
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
	
	private Map prepareParamsBeforeExecuteInterfaz(String codigoPais, String codigoCentro, String codigoCampanha,
			String login, String codigoInterfaz) {
			Map params = new HashMap();				
		    Usuario usuario = new Usuario();
	        usuario.setLogin(login);//usuarioService.getUsuarioByUsername(login); 
	        if (log.isDebugEnabled()) {
	            log.debug("usuario : " + usuario);
	        }
	        String codigoSistema = codigoInterfaz.substring(0,codigoInterfaz.indexOf('-'));
	        
	        params.put("codigoPais", codigoPais);
	        params.put("codigoPeriodo", codigoCampanha);
	        params.put("codigoSistema",codigoSistema);
	        params.put("codigoInterfaz", codigoInterfaz);
	        params.put("codigoCentro",  codigoCentro);
			params.put("usuario", usuario);
			params.put("ES_SSICC_DESFASADO", Constants.SI);
			return params;
	}
	private String getNombreArchivoEntradaSalida(InterfazResult interfazResult, String codigoCentro) {
        
		String nombreArchivo;
		Interfaz interfaz = interfazResult.getInterfaz();
		String fechaActual = DateUtil.getDateTime("yyyyMMdd", fechaSistema);
		
        nombreArchivo = interfaz.getNombreArchivo("MATRIZ_"+codigoCentro+"_"+fechaActual);
        
        return nombreArchivo;
    }
}
