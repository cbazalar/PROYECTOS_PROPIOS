/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.sisicc.ws.impl;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseInterfazService;
import biz.belcorp.ssicc.service.sisicc.framework.SSiCC_Desfasado_InterfazExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;
import biz.belcorp.ssicc.service.sisicc.impl.InterfazLARRecepcionarActualizaResultadoChequeoServiceImpl;
import biz.belcorp.ssicc.service.sisicc.impl.InterfazLARRecepcionarActualizaSecuenciacionzonasServiceImpl;
import biz.belcorp.ssicc.service.sisicc.ws.InterfazWebService;
import biz.belcorp.ssicc.service.sisicc.ws.beans.InterfazResultado;
import biz.belcorp.ssicc.service.sisicc.ws.beans.InterfazResultadoChequeoLar;
import biz.belcorp.ssicc.service.sisicc.ws.beans.RutasLARWebService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">efernandezo </a>
 */
public class InterfazWebServiceImpl extends ServletEndpointSupport implements
        InterfazWebService {

    Log log = LogFactory.getLog(InterfazWebServiceImpl.class);

    SSiCC_Desfasado_InterfazExecutionService interfazExecutionService ;
    
    MantenimientoOCRPedidoControlFacturacionService controlFacturacionService;
    
    UsuarioService usuarioService;    
    
    BaseInterfazService interfazLARRecepcionarService; 
     
    
    /*/InterfazLARRecepcionarActualizaResultadoChequeoServiceImpl interfazLar = null;
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
        
        interfazLARRecepcionarService =(BaseInterfazService) getWebApplicationContext().getBean("sisicc.interfazLARRecepcionarActualizaSecuenciacionzonasService");
       
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService#executeEnvioMatrizPuntajes(java.lang.String)
     */
    public InterfazResultado executeEnvioMatrizPuntajes(String codigoPais, String usuario) throws RemoteException {
        if (log.isDebugEnabled()) {
            log.debug("Dentro del metodo 'executeEnvioMatrizPuntajes'");
        }

        // Bean devuelto por el metodo
        SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
        InterfazResult interfazResult = null;
        InterfazResultado interfazResultado = null;
        try {
            if (log.isDebugEnabled()) {
                log.debug("codigoPais : " + codigoPais);
            }
            // Validacion de par�metros enviados
            if (StringUtils.isBlank(codigoPais)) {
                throw new ServiceException("Codigo de pais es nulo");
            }
            Map params = prepareParamsBeforeExecute(codigoPais, usuario);
            
            // Invocamos al servicio 
			executionResult = interfazExecutionService.executeInterfaz(params);
			
			List list = executionResult.getInterfazResults();
			
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				interfazResult = (InterfazResult) iter.next();
			}
			if (interfazResult != null){
				interfazResultado = new InterfazResultado();
				Interfaz interfaz = interfazResult.getInterfaz();
				
				interfazResultado.setNumeroRegistros(interfazResult.getRegistrosProcesados());
				interfazResultado.setCompletado(interfazResult.isCompletado());
				interfazResultado.setMensaje(interfazResult.getMensaje());
				interfazResultado.setNombreArchivoEntradaSalida(getNombreArchivoEntradaSalida(interfazResult));
				interfazResultado.setNumeroLote(interfazResult.getNumeroLote());
			}
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
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
    
	protected Map prepareParamsBeforeExecute(String codigoPais, String usuarioBiztalk) throws Exception {
		// Paso todos los parametros al map
		Map params = new HashMap();
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  

        PedidoControlFacturacion controlFacturacion = controlFacturacionService.getControlFacturacionById(criteria);
        
        String periodoFin = controlFacturacion.getCodigoPeriodo();
        if (log.isDebugEnabled()) {
            log.debug("periodoFin : " + periodoFin);
        }
        
		Map criteriaNCamapanha = new HashMap();
		criteriaNCamapanha.put("codigoPeriodo", periodoFin);
		criteriaNCamapanha.put("numCampanhas",new BigDecimal(-2)); // La 2da campannha anterior 
        
        String periodoInicio = controlFacturacionService.getPedidosNSiguienteCampanha(criteriaNCamapanha);
        if (log.isDebugEnabled()) {
            log.debug("periodoInicio : " + periodoInicio);
        }
        
        Usuario usuario = this.obtenerUsuarioByDefault(usuarioBiztalk);
        		//usuarioService.getUsuarioByUsername(usuarioBiztalk); // usuario deberia pasarse como parametro desde Biztalk
        if (log.isDebugEnabled()) {
            log.debug("usuario : " + usuario);
        }
        
        params.put("codigoPais"	,  codigoPais);
        params.put("codigoSistema", Constants.INT_RETAIL);
        params.put("codigoInterfaz", Constants.INT_RETAIL_ENVIA_MATRIZ_PUNTAJES_CALYPSO);   
        params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT)  ;
        params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT) ;
        params.put("codigoAcceso", Constants.CODIGO_ACCESO_DEFAULT);
        params.put("codigoSubacceso", Constants.REC_SUBACCESO_DEFAULT);   
		params.put("usuario", usuario);
        params.put("periodoDesde", periodoInicio);
        params.put("periodoHasta", periodoFin);
		
        params.put("ES_SSICC_DESFASADO", Constants.SI);
		return params;
	}
    
    public String getNombreArchivoEntradaSalida(InterfazResult interfazResult) {
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
    
    public InterfazResultadoChequeoLar resultadoChequeoList(String codigoPais, String codigoInterfaz, String codigoMarca,
    		String codigoCanal,String codigoAcceso,String codigoSubacceso,
    		String usuarioLogin,String codigoIsoIdioma) {

    	InterfazResultadoChequeoLar interfazResultadoChequeoLar = new InterfazResultadoChequeoLar();
		try {
    	Map params = new HashMap();
    	String codigoSistema = codigoInterfaz.substring(0,codigoInterfaz.indexOf('-'));
    	params.put("codigoPais", codigoPais);
    	params.put("codigoSistema", codigoSistema);
    	params.put("codigoInterfaz",codigoInterfaz);
    	params.put("codigoMarca",codigoMarca);
    	params.put("codigoCanal",codigoCanal);
    	params.put("codigoAcceso",codigoAcceso);
    	params.put("codigoSubacceso",codigoSubacceso);
    	params.put("usuarioLogin",usuarioLogin);
    	params.put("codigoIsoIdioma",codigoIsoIdioma);
    	params.put("descripcion","ACTUALIZA RESULTADO DE CHEQUEO");
    	params.put("usuarioProceso","ADMIN");
    	params.put("auditInfo.createdBy","ADMIN");
    	params.put("ES_SSICC_DESFASADO", Constants.SI);
    	Usuario usuario = new Usuario();
    	usuario.setLogin(usuarioLogin);
    	params.put("usuario",usuario);

    	HistoricoService historicoService;
    	historicoService = (HistoricoService) getWebApplicationContext().getBean("sisicc.historicoService");
   	
		List historicos = historicoService.getUltimoHistoricoByCodInterfaz(params);
		if (historicos != null) {
	            if (historicos.size() == 1)
	                params.put("fechaInicioUltimoProceso", ((Historico) historicos
	                        .get(0)).getFechaInicioProceso());
	        } else {
	            params.put("fechaInicioUltimoProceso", null);
	        }
 	
		//interfazLar.getInterfazExecutionResultx().setInterfazResults(null);
        SSiCC_Desfasado_InterfazExecutionResult interfazExecutionResult = interfazExecutionService.executeInterfaz(params);
        List  listaResult =  interfazExecutionResult.getInterfazResults() ;
        
        InterfazResult interfazResult;
		if (listaResult.size()>0 ){
				interfazResult=(InterfazResult) listaResult.get(0);
				if(!interfazResult.isCompletado()){
					interfazResultadoChequeoLar.setCodigo(Constants.EDU_WEBSERVICE_RESULTADO_ERROR);
					interfazResultadoChequeoLar.setMensaje(interfazResult.getMensaje());
					interfazResultadoChequeoLar.setList(null);
					return interfazResultadoChequeoLar;
				}
		}        

        Map mapInterfaz =interfazExecutionService.getInterfazImplementations();

        InterfazLARRecepcionarActualizaResultadoChequeoServiceImpl interfazLar = new InterfazLARRecepcionarActualizaResultadoChequeoServiceImpl();

        interfazLar=(InterfazLARRecepcionarActualizaResultadoChequeoServiceImpl) mapInterfaz.get(codigoInterfaz);
        
		int varInt = interfazLar.getInterfazExecutionResultx().getInterfazResults().size();
		
		ArrayList listaLar=new ArrayList();
		listaLar= null;

		listaLar = (ArrayList) interfazLar.getInterfazExecutionResultx().getInterfazResults();
		
		if (listaLar.size()>0 ){
				Map paramsf = new HashMap();
				log.debug("listaLar.get(posicion-1) " + listaLar.get(listaLar.size()-1));
				paramsf = (HashMap) listaLar.get(listaLar.size()-1);
				String [] list = new String[9];
				int j=0;
					interfazResultadoChequeoLar.setNomArchivo((String) paramsf.get("nombreArchivo"));
					list[j++]=(String)paramsf.get("varFC");
					list[j++]=(String)paramsf.get("varTC");
					list[j++]=(String)paramsf.get("varPD");
					list[j++]=(String)paramsf.get("varSB");
					list[j++]=(String)paramsf.get("varNQ");
					list[j++]=(String)paramsf.get("varSR");
					list[j++]=(String)paramsf.get("varHS");
					list[j++]=(String)paramsf.get("varHZ");
					list[j++]=(String)paramsf.get("varOK");					

				interfazResultadoChequeoLar.setList(list);
				interfazResultadoChequeoLar.setMensaje(String.valueOf(list.length));
			}

	} catch (Exception e) {
		interfazResultadoChequeoLar.setCodigo(Constants.EDU_WEBSERVICE_RESULTADO_ERROR);
		interfazResultadoChequeoLar.setMensaje(e.getMessage());
		interfazResultadoChequeoLar.setList(null);
		return interfazResultadoChequeoLar;
	}		
		interfazResultadoChequeoLar.setCodigo(Constants.EDU_WEBSERVICE_RESULTADO_OK);	
		return interfazResultadoChequeoLar;
		
   }
    
    public InterfazResultadoChequeoLar resultadoSecuenciacionZonasList(String codigoPais, String codigoInterfaz, String usuarioLogin,String codigoIsoIdioma) {

    	InterfazResultadoChequeoLar interfazResultadoChequeoLar = new InterfazResultadoChequeoLar();
		try {
    	Map params = new HashMap();
    	String codigoSistema = codigoInterfaz.substring(0,codigoInterfaz.indexOf('-'));
    	params.put("codigoPais", codigoPais);
    	params.put("codigoSistema", codigoSistema);
    	params.put("codigoInterfaz",codigoInterfaz);
    	params.put("usuarioLogin",usuarioLogin);
    	params.put("codigoIsoIdioma",codigoIsoIdioma);
    	params.put("descripcion","ACTUALIZA SECUENCIACION DE ZONAS");
    	params.put("usuarioProceso",usuarioLogin);
    	params.put("auditInfo.createdBy",usuarioLogin);
    	params.put("ES_SSICC_DESFASADO", Constants.SI);
    	Usuario usuario = new Usuario();
    	usuario.setLogin(usuarioLogin);
    	params.put("usuario",usuario);

    	HistoricoService historicoService;
    	historicoService = (HistoricoService) getWebApplicationContext().getBean("sisicc.historicoService");
   	
    	List historicos = historicoService.getUltimoHistoricoByCodInterfaz(params);
		if (historicos != null) {
	            if (historicos.size() == 1)
	                params.put("fechaInicioUltimoProceso", ((Historico) historicos
	                        .get(0)).getFechaInicioProceso());
	        } else {
	            params.put("fechaInicioUltimoProceso", null);
	        }
 	
        SSiCC_Desfasado_InterfazExecutionResult interfazExecutionResult = interfazExecutionService.executeInterfaz(params);
        List  listaResultInterfaz =  interfazExecutionResult.getInterfazResults() ;
        
        InterfazResult interfazResult;
		if (listaResultInterfaz.size()>0 ){
				interfazResult=(InterfazResult) listaResultInterfaz.get(0);
				if(!interfazResult.isCompletado()){
					interfazResultadoChequeoLar.setCodigo(Constants.EDU_WEBSERVICE_RESULTADO_ERROR);
					interfazResultadoChequeoLar.setMensaje(interfazResult.getMensaje());
					interfazResultadoChequeoLar.setList(null);
					return interfazResultadoChequeoLar;
				}
		}

        Map mapInterfaz =interfazExecutionService.getInterfazImplementations();
        InterfazLARRecepcionarActualizaSecuenciacionzonasServiceImpl interfazLar = new InterfazLARRecepcionarActualizaSecuenciacionzonasServiceImpl();

        interfazLar=(InterfazLARRecepcionarActualizaSecuenciacionzonasServiceImpl) mapInterfaz.get(codigoInterfaz);
        
		int varInt = interfazLar.getInterfazExecutionResultx().getInterfazResults().size();

		List listaRutas = interfazLar.getInterfazExecutionResultx().getInterfazResults();
		String nombreArchivo = interfazLar.getInterfazExecutionResultx().getNumeroLote();
		RutasLARWebService[] listaResult = new RutasLARWebService[0];

		if ((listaRutas!=null) && (listaRutas.size()>0)){
			listaResult= new RutasLARWebService[listaRutas.size()];

			RutasLARWebService rutasLARWebService = new RutasLARWebService();
			RutasLARWebService rutasLARWebServicex = new RutasLARWebService();
			for (int i=0;i<listaRutas.size();i++){
				rutasLARWebServicex = new RutasLARWebService();
				rutasLARWebService = new RutasLARWebService();
				log.debug("listaRutas.get(i)" + listaRutas.get(i));
				Map ListaMap  = new HashMap();
				ListaMap=(HashMap)listaRutas.get(i);
				rutasLARWebServicex.setZona(ListaMap.get("zona").toString());
				rutasLARWebServicex.setDescripcion(ListaMap.get("descripcion").toString());
				rutasLARWebServicex.setSecuencia(ListaMap.get("secuencia").toString());
				BeanUtils.copyProperties(rutasLARWebService, rutasLARWebServicex);
				listaResult[i]= rutasLARWebService;
			}
		}
		interfazResultadoChequeoLar.setListaRutas(listaResult);
		interfazResultadoChequeoLar.setNomArchivo(nombreArchivo);
		interfazResultadoChequeoLar.setMensaje(String.valueOf(listaResult.length));
		
		log.debug("listaLar.size()" + listaResult.length);

	} catch (Exception e) {
		interfazResultadoChequeoLar.setCodigo(Constants.EDU_WEBSERVICE_RESULTADO_ERROR);
		interfazResultadoChequeoLar.setMensaje(e.getMessage());
		interfazResultadoChequeoLar.setList(null);
		return interfazResultadoChequeoLar;
	}		
		interfazResultadoChequeoLar.setCodigo(Constants.EDU_WEBSERVICE_RESULTADO_OK);	
		return interfazResultadoChequeoLar;
		
   }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService#executeEnvioMatrizRetail
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public InterfazResultado executeEnvioMatrizRetail(String codigoPais,
			String periodoInicial, String periodoFinal, String usuario) throws RemoteException {
		if (log.isDebugEnabled()) {
			log.debug("Dentro del metodo 'executeEnvioMatrizRetail'");
		}

		// Bean devuelto por el metodo
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		InterfazResultado interfazResultado = null;
		
		try {
			if (log.isDebugEnabled()) {
				log.debug("codigoPais : " + codigoPais);
				log.debug("periodoInicial : " + periodoInicial);
				log.debug("periodoFinal : " + periodoFinal);
				log.debug("usuario : " + usuario);
			}
			
			// Validacion de par�metros enviados
			if (StringUtils.isBlank(codigoPais)) {
				throw new ServiceException("Codigo de pais es nulo");
			}
			
			// Preparamos los parametros
			Map params = new HashMap();
	        Usuario usr =  this.obtenerUsuarioByDefault(usuario);
	        		//usuarioService.getUsuarioByUsername(usuario);
	        if (log.isDebugEnabled()) {
	            log.debug("user : " + usr);
	        }
	        params.put("codigoPais"	,  codigoPais);
	        params.put("codigoSistema", Constants.INT_RETAIL);
	        params.put("codigoInterfaz", Constants.INT_RETAIL_ENVIA_MATRIZ);   
	        params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT)  ;
	        params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT) ;
	        params.put("codigoAcceso", Constants.CODIGO_ACCESO_DEFAULT);
	        params.put("codigoSubacceso", Constants.REC_SUBACCESO_DEFAULT);   
			params.put("usuario", usr);
	        params.put("periodoDesde", periodoInicial);
	        params.put("periodoHasta", periodoFinal);
			
			// Invocamos al servicio
			executionResult = interfazExecutionService.executeInterfaz(params);

			// Obtenemos el resultado de la ejecucion
			List list = executionResult.getInterfazResults();

			// Iteramos sobre la lista de resultados, deber�a haber uno solo
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				interfazResult = (InterfazResult) iter.next();
			}
			
			if (interfazResult != null) {
				interfazResultado = new InterfazResultado();
				interfazResultado.setNumeroRegistros(interfazResult
						.getRegistrosProcesados());
				interfazResultado.setCompletado(interfazResult.isCompletado());
				interfazResultado.setMensaje(interfazResult.getMensaje());
				interfazResultado
						.setNombreArchivoEntradaSalida(getNombreArchivoEntradaSalida(interfazResult));
				interfazResultado.setNumeroLote(interfazResult.getNumeroLote());
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage());
		}

		if (log.isDebugEnabled()) {
			if (executionResult != null) {
				log.debug("Bean de retorno executionResult: " + executionResult);
			}
		}

		return interfazResultado;
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
