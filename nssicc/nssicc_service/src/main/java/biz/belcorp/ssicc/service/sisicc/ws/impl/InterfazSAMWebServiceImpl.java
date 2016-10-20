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
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.sisicc.framework.SSiCC_Desfasado_InterfazExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.ws.InterfazSAMWebService;
import biz.belcorp.ssicc.service.sisicc.ws.beans.InterfazResultado;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">efernandezo </a>
 */
public class InterfazSAMWebServiceImpl extends ServletEndpointSupport implements
        InterfazSAMWebService {

    Log log = LogFactory.getLog(InterfazSAMWebServiceImpl.class);

    SSiCC_Desfasado_InterfazExecutionService interfazExecutionService ;
    
    MantenimientoOCRPedidoControlFacturacionService controlFacturacionService;
    
    UsuarioService usuarioService;    
    
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
     * @see biz.belcorp.ssicc.sisicc.web.ws.InterfazSAMWebService#executeInterfazSAMEnviarMovimientosAlmacen(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public InterfazResultado executeInterfazSAMEnviarMovimientosAlmacen(String codigoPais,String codigoInterfaz
    		,String codigoPeriodo,String fechaFacturacion,String numeroLote,
    		String indicadorMaterialPromocional,String usuario)throws RemoteException{
    	
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
                log.debug("numeroLote : " + numeroLote);
                log.debug("indicadorMaterialPromocional : " + indicadorMaterialPromocional);
                log.debug("usuario : " + usuario);
            }
            // Validacion de par�metros enviados
            if (StringUtils.isBlank(codigoPais)) {
                throw new ServiceException("Codigo de pais es nulo");
            }
            
            if (StringUtils.isBlank(indicadorMaterialPromocional)) {
                throw new ServiceException("indicadorMaterialPromocional es nulo");
            }
            
            Map params = prepareParamsBeforeExecuteInterfazSAM(codigoPais,usuario,codigoInterfaz,
            												   codigoPeriodo,fechaFacturacion);
            params.put("numeroLote", numeroLote);
            params.put("indicadorMaterial", indicadorMaterialPromocional);
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
            log.debug(e.getMessage(), e);            
            throw new RemoteException(e.getMessage());
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
     * @param codigoPais
     * @param usuarioBiztalk
     * @param codigoInterfaz
     * @param fechaProceso 
     * @return
     * @throws Exception
     */
    private Map prepareParamsBeforeExecuteInterfazSAM(String codigoPais, String usuarioBiztalk, String codigoInterfaz,
    															String codigoPeriodo, String fechaFacturacion) throws Exception {
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
		
        Usuario usuario =  obtenerUsuarioByDefault(usuarioBiztalk);
        		//usuarioService.getUsuarioByUsername(usuarioBiztalk); // usuario deberia pasarse como parametro desde Biztalk
        if (log.isDebugEnabled()) {
            log.debug("usuario : " + usuario);
        }
        String codigoSistema = codigoInterfaz.substring(0,codigoInterfaz.indexOf('-'));
        params.put("codigoPais"	,  codigoPais);
        params.put("codigoSistema",codigoSistema);
        params.put("codigoInterfaz", codigoInterfaz);   
		params.put("usuario", usuario);
        params.put("codigoPeriodo", codigoPeriodo);
        params.put("fechaFacturacion", fechaFacturacion);      
        params.put("ES_SSICC_DESFASADO", Constants.SI);
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

	 /**
     * Metodo que permite obtener el usuario por defecto
     * @return Usuario, objeto de respuesta
     */
    protected Usuario obtenerUsuarioByDefault(String login){
    	Idioma idioma = new Idioma();
        idioma.setCodigo("01");
        idioma.setCodigoISO("ES");
        idioma.setCodigoSiCC(Constants.EDU_IDIOMA_DEFAULT_ES);
        idioma.setEstado("1");
        
        Usuario usuario = new Usuario();
        usuario.setCodigoIdioma("01");
        usuario.setCodigoPais("");
        usuario.setLogin(login);
        usuario.setCodigo("");
        usuario.setIdioma(idioma);
    	return usuario;
    }
	
}
