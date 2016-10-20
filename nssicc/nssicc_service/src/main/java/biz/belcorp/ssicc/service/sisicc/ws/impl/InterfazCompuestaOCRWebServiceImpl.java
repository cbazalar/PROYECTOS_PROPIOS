/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.sisicc.ws.impl;

import java.net.URL;
import java.net.URLDecoder;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.GenericoDAO;
import biz.belcorp.ssicc.dao.PaisDAO;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatchActu;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.sisicc.framework.InterfazBaseCompuestaService;
import biz.belcorp.ssicc.service.sisicc.framework.SSiCC_Desfasado_BaseInterfazHiloAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.ws.InterfazCompuestaOCRWebService;
import biz.belcorp.ssicc.service.sisicc.ws.beans.InterfazResultado;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ReporteOCREnvioCargaDocumentoService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOHistoricoService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazCompuestaOCRWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli </a>
 */
public class InterfazCompuestaOCRWebServiceImpl extends ServletEndpointSupport implements
		InterfazCompuestaOCRWebService {

    private static final long TIME_OUT = 1000;
	Log log = LogFactory.getLog(InterfazCompuestaOCRWebServiceImpl.class);
	public static String INTERFAZ_OCR_COMPUESTA ="OCR-C1";
	public static String INTERFAZ_OCR_CONTROL ="OCR-35";
	public static String USUARIO_OCR_COMPUESTA ="USU_OCR_COMPUESTA";
	
	InterfazBaseCompuestaService interfazBaseCompuestaService ;
	InterfazService interfazService;
	ProcesoPEDService procesoPEDService;
	ProcesoBatchService procesoBatchService;
	ReporteOCREnvioCargaDocumentoService reporteService;
	
	private GenericoDAO genericoDAO;
	private PaisDAO paisDAO;
	private ProcesoSTOHistoricoService serviceHistorico;
	
    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
     */
    protected void onInit() throws ServiceException {
    	 interfazBaseCompuestaService = (InterfazBaseCompuestaService)getWebApplicationContext().getBean("sisicc.interfazBaseCompuestaService");
    	 interfazService = (InterfazService)getWebApplicationContext().getBean("sisicc.interfazService");
    	 procesoPEDService = (ProcesoPEDService)getWebApplicationContext().getBean("spusicc.procesoPEDService");
    	 genericoDAO = (GenericoDAO)getWebApplicationContext().getBean("genericoDAO");
    	 procesoBatchService = (ProcesoBatchService)getWebApplicationContext().getBean("scsicc.procesoBatchService");
    	 reporteService = (ReporteOCREnvioCargaDocumentoService)getWebApplicationContext().getBean("spusicc.reporteOCREnvioCargaDocumentoService");
    	 paisDAO = (PaisDAO)getWebApplicationContext().getBean("paisDAO");
    	 serviceHistorico = (ProcesoSTOHistoricoService) getWebApplicationContext().getBean("spusicc.procesoSTOHistoricoService");
    }
    

    public void executeRecepcionCompuestaWeb(String codigoPaisOcr,
		String codigoCompania, String usuario) throws RemoteException {

		//SE VERIFICA SI ESTA ACTIVO LA EJECUCION DEL WEBSERVICE
    	String codigoPais = obtenerValorParametro("", Constants.SISTEMA_GEN, "codigoPaisDefault");
		String indActivoEjecucionWS = obtenerValorParametro(codigoPais, Constants.SISTEMA_OCR, "indActivoEjecucionWS");
		log.debug("indActivoEjecucionWS : " + indActivoEjecucionWS);
		
		if("1".equals(indActivoEjecucionWS)) {
	    	//Realizamos la ejecucion del procesamiente de la interfaz en forma asincrona
			InterfaceThread interfaceThread = new InterfaceThread(codigoPaisOcr, codigoCompania, usuario);
			interfaceThread.start();
		} else {
			log.debug("No esta activo la ejecucion del WEB SERVICE");
		}	
	}



	/**
	 * Realiza el envio de correo segun parametro de ind_mail del Bas_repor_param
	 * @param codigoPais 
	 * 
	 * @param interfazResultado
	 * @param usuario 
	 */
	private void sendMail(String codigoPais, InterfazResultado interfazResultado, String usuario, String nombreReporte) {
		
		Map params= new HashMap();
		params.put("codigoPais", codigoPais);
		params.put("completado", interfazResultado.isCompletado()?Constants.NUMERO_UNO:Constants.NUMERO_CERO);
		params.put("mensaje", interfazResultado.getMensaje());
		params.put("numeroLote", interfazResultado.getNumeroLote());
		params.put("codigoUsuario", usuario);
		params.put("codigoInterfaz", INTERFAZ_OCR_COMPUESTA);
		params.put("nombreReporte", nombreReporte);
		
		if(log.isDebugEnabled())
			log.debug(params);
		
		procesoPEDService.sendMailOcr(params);
		
	}



	private Map prepareParamsBeforeExecute(String codigoPais,String codigoInterfaz,String usuario, String codigoProcesoBatch) {
		Map params = new HashMap();
		Usuario usua = new Usuario();
		usua.setLogin(usuario);
		Idioma idioma= new Idioma();
		idioma.setCodigoISO("es");
		usua.setIdioma(idioma);
		String codigoSistema = codigoInterfaz.substring(0,codigoInterfaz.indexOf('-'));
        params.put("codigoPais"	,  codigoPais);
        params.put("codigoSistema",codigoSistema);
        params.put("codigoInterfaz", codigoInterfaz);
        params.put("codigoProcesoBatch", codigoProcesoBatch);
		params.put("usuario", usua);
		params.put("codigoUsuario", usuario);		
		params.put("indicadorEjecucion",Constants.INDICADOR_EJECUCION_PROCESO_BATCH_SI);
		params.put("codigoEstadoProceso",Constants.CODIGO_PROCESO_BATCH_EN_EJECUCION);
		params.put("recomendacionError", "    ");
		params.put("indicadorMultiLote", Constants.NO);
		params.put("codTipoDocu", Constants.STO_TIPO_DOCUMENTO_OCC);
		params.put("indOrigen", Constants.STO_ORIGEN_OCR);
		params.put("flagFinalizo", false);
		
		params.put("usuarioTemp", usua);
		
		Pais pais = paisDAO.getPais(codigoPais);
		params.put("descripcionPais", pais.getDescripcion());
		params.put("pais", pais);
		
		try	{
			URL r = this.getClass().getResource("/");
			String decoded = URLDecoder.decode(r.getFile(), "UTF-8");
            log.debug("decode "+decoded);
			decoded = StringUtils.remove(decoded, "WEB-INF/classes/");
			params.put("rutaPath", decoded);
			
		} catch(Exception ex) {
			params.put("rutaPath", "/");
		}
		params.put("ES_SSICC_DESFASADO", Constants.SI);
		params.put("isCompuesta",  Constants.SI);
		return params;
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

    private String obtenerValorParametro(String codigoPais, String codigoSistema, String nombreParametro) {
    	String valorParametro = null;
    	
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(codigoPais);
		parametroPais.setCodigoSistema(codigoSistema);
		parametroPais.setNombreParametro(nombreParametro);				
		parametroPais.setIndicadorActivo("1");
		
		List parametros = genericoDAO.getParametrosPais(parametroPais);
		if(parametros.size()>0) {
			ParametroPais pPais = (ParametroPais)parametros.get(0);
			valorParametro = pPais.getValorParametro();
		}	
		
		return valorParametro;
	}
    
    class InterfaceThread extends Thread {
    	
    	String codigoPaisOcr;
		String codigoCompania;
		String usuario;
		
		public InterfaceThread(String codigoPaisOcr, String codigoCompania, String usuario) {
			this.codigoPaisOcr = codigoPaisOcr;
			this.codigoCompania = codigoCompania;
			this.usuario = usuario;
		}
		
		public void run() {
			 // Bean devuelto por el metodo
	        SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
	        InterfazResult interfazResult = null;
	        InterfazResultado interfazResultado = null;  
	        String codigoPais="";
	        
	        try {
	            if (log.isDebugEnabled()) {
	            	log.debug("Parametros enviados al web service:");
	                log.debug("codigoPais : " + codigoPaisOcr);
	                log.debug("codigoCompania : " + codigoCompania);                       
	                log.debug("usuario : " + usuario);
	            }
	            
	            //PRIMER TIMER
	            codigoPais = obtenerValorParametro("", Constants.SISTEMA_GEN, "codigoPaisDefault");
	            String timerEjecucionWS = obtenerValorParametro(codigoPais, Constants.SISTEMA_OCR, "timerEjecucionWS");
	            log.debug("timerEjecucionWS : " + timerEjecucionWS);
	            
	            if(timerEjecucionWS!=null && !"0".equals(timerEjecucionWS)) {
	            	Thread.sleep(new Long(timerEjecucionWS)*1000*60);
	            }
	            
	            //VALIDACIONES DEL WEB SERVICE
	            // Validacion de par�metros enviados
	            if (StringUtils.isBlank(codigoPaisOcr)) {
	                throw new ServiceException("Codigo de pais es nulo");
	            }
	
	            if (StringUtils.isBlank(codigoCompania)) {
	                throw new ServiceException("Compania es nulo");
	            }
	            
	            if (StringUtils.isBlank(usuario)) {
	            	usuario = USUARIO_OCR_COMPUESTA;
	            }
	            
	            //obtener codigo pais            
	            Map criteria = new HashMap();
	            criteria.put("codigoPaisOcr", codigoPaisOcr);
	            criteria.put("codigoCompania", codigoCompania);
	            
	            codigoPais=interfazService.getPaisByCia(criteria);
	            criteria.put("codigoPais", codigoPais);
	            if (StringUtils.isBlank(codigoPais)) {
	            	 throw new ServiceException("No se pudo obtener codigo pais comercial");
	            }
	            
	            String codigoSistema = INTERFAZ_OCR_COMPUESTA.substring(0,INTERFAZ_OCR_COMPUESTA.indexOf('-'));
	            //obtener proceso batch
	            criteria.put("codigoSistema", codigoSistema);                      
	            criteria.put("codigoInterfaz", INTERFAZ_OCR_COMPUESTA);
	            String codigoProcesoBatch = interfazService.getCodigoProcesoBatch(criteria);
	            
	            if (StringUtils.isBlank(codigoProcesoBatch)) {
	           	 throw new ServiceException("No se pudo obtener codigo de proceso Batch para la interfaz compuesta");
	           }
	            
  	            //SEGUNDO TIMER
	            if(isEjecucionProceso(codigoPais, INTERFAZ_OCR_COMPUESTA, codigoProcesoBatch)) {
	            	throw new ServiceException("Proceso Batch se encuentra actualmente EN EJECUCI�N");
	            }

	    		Long ltimerRecepcionWS = new Long(1);
	    		Long ltimerRecepcion = new Long(0);
	    		Long ltimerLimiteRecepcionWS = new Long(30);
	    		
	    		String timerRecepcionWS = obtenerValorParametro(codigoPais, Constants.SISTEMA_OCR, "timerRecepcionWS");
	    		if(timerRecepcionWS!=null) ltimerRecepcionWS = new Long(timerRecepcionWS);
	    		log.debug("timerRecepcionWS : " + timerRecepcionWS);
	    		
	    		String timerLimiteRecepcionWS = obtenerValorParametro(codigoPais, Constants.SISTEMA_OCR, "timerLimiteRecepcionWS");
	    		if(timerLimiteRecepcionWS!=null) ltimerLimiteRecepcionWS = new Long(timerLimiteRecepcionWS);
	    		log.debug("timerLimiteRecepcionWS : " + timerLimiteRecepcionWS);
	    		
	    		boolean ejecucionProcesosDependientes = isEjecucionProcesoDependientes(codigoPais, INTERFAZ_OCR_COMPUESTA, codigoProcesoBatch);
	    		if (ejecucionProcesosDependientes) {
	    			log.debug("Empezando Verificacion Procesos Dependientes");
	    			
	    			while(ltimerRecepcion.longValue() < ltimerLimiteRecepcionWS.longValue()) {
	    				Thread.sleep(new Long(timerRecepcionWS)*1000*60);
	    				log.debug("Reitento Verificacion Procesos Dependientes");
	    				
	    				ejecucionProcesosDependientes = isEjecucionProcesoDependientes(codigoPais, INTERFAZ_OCR_COMPUESTA, codigoProcesoBatch);
	    				
	    				if(!ejecucionProcesosDependientes) {
	    					break;
	    				} else {
	    					ltimerRecepcion = ltimerRecepcion + ltimerRecepcionWS;
	    				}
	    			}
	    			
	    			if(ejecucionProcesosDependientes) {
	    				log.debug("Se llego al tiempo limite de recepci�n de los procesos dependientes");
		    			throw new ServiceException("Se llego al tiempo limite de recepci�n de los procesos dependientes");
		    		}
	    		}
	            	
	    		/*VALIDACION DE EJECUCIONES STO OCC*/
	    		String indValiSTOWSOCRCompuesta = obtenerValorParametro(codigoPais, Constants.SISTEMA_OCR, "indValiSTOWSOCRCompuesta");
	    		String tiempoEsperaSTOWSOCRCompuesta = obtenerValorParametro(codigoPais, Constants.SISTEMA_OCR, "tiempoEsperaSTOWSOCRCompuesta");
	    		String limiteEsperaSTOWSOCRCompuesta = obtenerValorParametro(codigoPais, Constants.SISTEMA_OCR, "limiteEsperaSTOWSOCRCompuesta");
	    		
	    		
	    		if  (indValiSTOWSOCRCompuesta != null && indValiSTOWSOCRCompuesta.equals(Constants.SI)){
	    			
	    			
	    			Long lnWaitSTOLimite = new Long(1);
	    			if(limiteEsperaSTOWSOCRCompuesta!=null) lnWaitSTOLimite = new Long(limiteEsperaSTOWSOCRCompuesta);
		    		
		    		Long lnwaitSTO = new Long(1);
		    		if(tiempoEsperaSTOWSOCRCompuesta!=null) lnwaitSTO = new Long(tiempoEsperaSTOWSOCRCompuesta);
		    		
		    		
		    		Long lnwaitSTOActual = new Long(0);
		    		/*parametros STO*/
		    		Map params = new HashMap();
		    		params.put("codTipoDocu", Constants.STO_TIPO_DOCUMENTO_OCC);
		    		
		    		
		    		log.warn("Se valida procesos STO en ejecucion: ");
		    		log.debug("lnWaitSTOLimite: " + lnWaitSTOLimite);
		    		log.debug("lnwaitSTO: " + lnwaitSTO);
		    		
		    		
	    			while(lnwaitSTOActual.longValue() < lnWaitSTOLimite.longValue()) {
	    				
	    				log.warn("Validacion procesos STO en ejecucion");
	    				log.debug("lnwaitSTOActual: " + lnwaitSTOActual);
	    				
	    				List historicoList = serviceHistorico.getProcesoEjecucionByDocumento(params);
	    				
	    				if(historicoList.size()>0)
	    				{
	    					log.warn("Se espera " + lnwaitSTO + " minutos por ejecucion de proceso, existen " + historicoList.size() + "procesos en ejecucion");
	    					Thread.sleep(new Long(lnwaitSTO)*1000*60);
	    					lnwaitSTOActual += lnwaitSTO;	    					
	    					
	    				} else {
	    					log.warn("No existen procesos STO en ejecucion, se inicia ejecucion ..");
	    					break;
	    				}
	    				
	    			}
	    			
	    			log.warn("Fin de espera, lnwaitSTOActual " + lnwaitSTOActual );
		    		
	    			
	    		}
	            	
	    		//EJECUCION DE LA INTERFAZ COMPUESTA
	            final Map params= prepareParamsBeforeExecute(codigoPais,INTERFAZ_OCR_COMPUESTA,usuario,codigoProcesoBatch);
	            params.put("descripcion", getDescripcionInterfaz(params));
	    		final SSiCC_Desfasado_BaseInterfazHiloAbstractService baseInterfazService = (SSiCC_Desfasado_BaseInterfazHiloAbstractService) interfazBaseCompuestaService.
	    																						getInterfazImplementation(INTERFAZ_OCR_COMPUESTA);			   	
	
	    		//verificamos interface si esta en ejecucion o si viene los parametros nullos y verifica lote
	    		String mensajeError = baseInterfazService.verificaParamsBeforeExecute(params);
	    		
	    		if(StringUtils.isNotEmpty(mensajeError)){    			
	    			throw new ServiceException(mensajeError);
	    		}

				log.debug("execute Interfaz Compuesta");
				baseInterfazService.executeInterfaz(params);
					//esperamos que los hijos terminen
				
				boolean flagFinalizo=false;
				while(!flagFinalizo){
					Thread.sleep(TIME_OUT);
					flagFinalizo= (Boolean)params.get("flagFinalizo");				
				}
				log.debug("finish execute Interfaz Compuesta");		    		            
	            
				String idProcesoBatch = params.get("idProcesoBatch").toString();
				
				executionResult = (SSiCC_Desfasado_InterfazExecutionResult)params.get("executionResult");
				log.debug("executionResult "+executionResult);
				List list = executionResult.getInterfazResults();
				interfazResult = (InterfazResult)list.get(0);
				//logger.debug("erere "+ interfazResult.getMensaje());
				if (interfazResult != null){
					interfazResultado = new InterfazResultado();
					interfazResultado.setNumeroRegistros(interfazResult.getRegistrosProcesados());
					log.debug(" >>interfazResult.isCompletado() >>> "+interfazResult.isCompletado());
					interfazResultado.setCompletado(interfazResult.isCompletado());
					interfazResultado.setMensaje(!interfazResult.isCompletado()?"Error en paquete compuesta : "+interfazResult.getMensaje():"El(los) archivo(s) fue(ron) generado(s) y enviado(s) satisfactoriamente :"+interfazResult.getMensaje());
					interfazResultado.setNombreArchivoEntradaSalida("");
					interfazResultado.setNumeroLote(interfazResult.getNumeroLote());				
				}
		
				//SE ENVIA CORREO POR INTERFAZ DE CONTROL DE CIERRE
				String indEnvioCorreoCierreWS = obtenerValorParametro(codigoPais, Constants.SISTEMA_OCR, "indEnvioCorreoCierreWS");
				log.debug("indEnvioCorreoCierreWS : " + indEnvioCorreoCierreWS);
	    		if("1".equals(indEnvioCorreoCierreWS)) {
	    			log.debug("Se envia correo de Interfaz de Control de Cierre");
	    			
	    			Map datosCierre = obtenerDatosArchivoControl(idProcesoBatch, true);
	    			if(datosCierre != null) {
		    			params.put("fechaProceso", (String)datosCierre.get("fechaProceso"));
		    			
		    			try {
							reporteService.grabarReporte(params);
						} catch (Exception e) {
							log.error("No se pudo enviar correo de Interfaz de Control de Cierre");
							e.printStackTrace(); 
						}
	    			}	
	    		}
	    		
				
	        } catch (Exception e) {
	           // log.debug(e.getMessage(), e);            
	            interfazResultado = new InterfazResultado();
				interfazResultado.setNumeroRegistros(0);
				interfazResultado.setCompletado(false);
				interfazResultado.setMensaje(e.getMessage());            
	           // throw new RemoteException(e.getMessage());
	        }
	        
	        if(StringUtils.isEmpty(codigoPais))
	        	codigoPais = obtenerValorParametro("", Constants.SISTEMA_GEN, "codigoPaisDefault");
	        
	        sendMail(codigoPais,interfazResultado,usuario, "procesoOCRInterfazCompuesta");
	        
	        //EN CASO DE ERROR, SE ENVIO OTRO CORREO
        	sendMail(codigoPais,interfazResultado,usuario, "procesoOCRInterfazCompuestaError");
	
	        if (log.isDebugEnabled()) {
	            if (executionResult != null) {
	                log.debug("Bean de retorno executionResult : "
	                        + executionResult);
	            }
	        }
		}
	}

    /**
	 * Se encarga de verificar si ya esta en ejecucion
	 * @param micPrams
	 * @param codigoProcesoBatch 
	 * @return
	 */
	private boolean isEjecucionProceso(String codigoPais, String codigoInterfaz, String codigoProcesoBatch) {
		Map params = new HashMap();
		String codigoSistema = codigoInterfaz.substring(0, codigoInterfaz.indexOf('-')).trim();
		
		params.put("codigoPais", codigoPais);
		params.put("codigoSistema", codigoSistema);
		params.put("codigoProcesoBatch", codigoProcesoBatch);
		params.put("indicadorEjecucion",Constants.INDICADOR_EJECUCION_PROCESO_BATCH_SI);
		
		/* Verificando que no se encuentre en Ejecucion */
		List listaProcesoBatch = procesoBatchService.getProcesoBatchActuByCriteria(params);
		if (listaProcesoBatch.size() > 0) {
			ProcesoBatchActu procesoBatchActu =  (ProcesoBatchActu) listaProcesoBatch.get(0);
			if (Constants.INDICADOR_EJECUCION_PROCESO_BATCH_SI.equals(procesoBatchActu.getIndicadorEjecucion())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Se encarga de verificar si los procesos dependientes estan en ejecucion
	 * 
	 * @param micPrams
	 * @param codigoProcesoBatch 
	 * @return
	 */
	private boolean isEjecucionProcesoDependientes(String codigoPais, String codigoInterfaz, String codigoProcesoBatch) {
		Map params = new HashMap();
		String codigoSistema = codigoInterfaz.substring(0, codigoInterfaz.indexOf('-')).trim();
		
		params.put("codigoPais", codigoPais);
		params.put("codigoSistema", codigoSistema);
		params.put("codigoProcesoBatch", codigoProcesoBatch);
		
		/* Verificando que no se encuentre en Ejecucion */
		List listaProcesoBatchDependientes = procesoBatchService.getProcesoBatchActuDependientesByCriteria(params);
		if (listaProcesoBatchDependientes.size() > 0) {
			return true;			
		}
		
		return false;
	}

	private Map obtenerDatosArchivoControl(String idProcesoBatch, boolean esTipoLoteCierre) {
		Map criteria = new HashMap();
		Map datos = null;
		
		criteria.put("codigoSistema", Constants.SISTEMA_OCR);
		criteria.put("codigoInterfaz", INTERFAZ_OCR_CONTROL);
		criteria.put("idProcesoBatch", idProcesoBatch);
		
		if(esTipoLoteCierre)
			criteria.put("tipoLoteCierre", "S");
		
		String descripcion = interfazService.getDescripcionArchivoControl(criteria);
		if(descripcion != null) {
			datos = new HashMap();
			StringTokenizer st = new StringTokenizer(descripcion, ",");
			
			while(st.hasMoreTokens()) {
				StringTokenizer st2 = new StringTokenizer(st.nextToken(), "=");
				String id = st2.nextToken();
				String valor = st2.nextToken();
				
				datos.put(id, valor);
			}
		}
		
		return datos;
	}
	
}
