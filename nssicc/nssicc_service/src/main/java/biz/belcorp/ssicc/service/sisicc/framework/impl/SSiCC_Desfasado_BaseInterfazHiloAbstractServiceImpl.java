package biz.belcorp.ssicc.service.sisicc.framework.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.LabelArchivos;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatchActu;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.sisicc.framework.SSiCC_Desfasado_BaseInterfazHiloAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.SSiCC_Desfasado_InterfazCompuestoExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;

/**
 * @author peextsbuchelli
 *
 */
@Service("sisicc_desfasado.baseInterfazHiloAbstractService")
public abstract class SSiCC_Desfasado_BaseInterfazHiloAbstractServiceImpl implements SSiCC_Desfasado_BaseInterfazHiloAbstractService {
	protected transient final Log log = LogFactory.getLog(getClass());
	
	@Resource(name="sisicc.interfazService")
	private InterfazService interfazService;
	
	@Resource(name="scsicc.procesoBatchService")
	private ProcesoBatchService procesoBatchService;	
	
	@Resource(name="sisicc_desfasado.interfazCompuestoExecutionService")
	private SSiCC_Desfasado_InterfazCompuestoExecutionService interfazCompuestoExecutionService;
	
   @Resource(name="messageSource")
 protected MessageSource messageSource;
	    

		

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazHiloAbstractService#verificaParamsBeforeExecute(java.util.Map)
	 */
	public String verificaParamsBeforeExecute(Map params) {
		String returnMensaje="";
		
		Usuario usuario = (Usuario)params.get("usuario"); 
		
		String codigoPais = (String) params.get("codigoPais");
		String codigoInterfaz = (String) params.get("codigoInterfaz");
		String codigoSistema = (String) params.get("codigoSistema");
		String codigoProcesoBatch = (String) params.get("codigoProcesoBatch");
		
		//String listaInterfaces[] = (String []) params.get("listaInterfaces");
		String listaInterfacesSeleccionadas[] = (String []) params.get("listaInterfacesSeleccionadas");				
				
		if (StringUtils.isNotBlank(codigoInterfaz) && StringUtils.isNotBlank(codigoSistema)) {
			InterfazPK interfazEjecucionPK = new InterfazPK(codigoPais,
					codigoSistema, codigoInterfaz);
	
			Interfaz interfazEjecucion = interfazService.getInterfaz(interfazEjecucionPK);
			if (interfazEjecucion!= null) {
				params.put("indicadorMultiLote", interfazEjecucion.getIndicadorMultiLote());
				if (Constants.SI.equals(interfazEjecucion.getIndicadorSeleccion())) {
					if (listaInterfacesSeleccionadas == null || listaInterfacesSeleccionadas.length == 0) {
						String keyMensaje = "interfaz.sinSeleccionInterfazPaquete";
						returnMensaje = getKeyMessage(keyMensaje,usuario);
						return returnMensaje;
					}
				}
			}	
		}
						
		String mensajeWarnig01 = getKeyMessage("procesoBatch.error.ingreseParametroMenu",usuario);				
		if (StringUtils.isBlank(codigoProcesoBatch)) 
			return mensajeWarnig01;
		
		/* Verificando que no se encuentre en Ejecucion */
		String mensajeWarnig02 = getKeyMessage("procesoBatch.error.procesoEnEjecucion",usuario);

		List listaProcesoBatch = procesoBatchService.getProcesoBatchActuByCriteria(params);
		if (listaProcesoBatch.size() > 0) {
			ProcesoBatchActu procesoBatchActu =  (ProcesoBatchActu) listaProcesoBatch.get(0);
			if (Constants.INDICADOR_EJECUCION_PROCESO_BATCH_SI.equals(procesoBatchActu.getIndicadorEjecucion())) {
				return mensajeWarnig02;
			}
		}
		
		List listaProcesoBatchDependientes = procesoBatchService.getProcesoBatchActuDependientesByCriteria(params);
		if (listaProcesoBatchDependientes.size() > 0) {
			
			String mensaje = getKeyMessage("procesoBatch.error.procesoDependienteEnEjecucion",usuario);
					
			for (int i = 0; i < listaProcesoBatchDependientes.size(); i++) {
				
				ProcesoBatchActu procesoBatchActu =  (ProcesoBatchActu) listaProcesoBatchDependientes.get(i);
				
				mensaje  +=  "\n" + procesoBatchActu.getCodigoSistema() + "-" + procesoBatchActu.getProcesoBatch().getCodigoProcesoBatch();
				mensaje  +=  " " + procesoBatchActu.getProcesoBatch().getDescripcionProcesoBatch();
				mensaje += "; ";
			}
			mensaje +=  "\n" + getKeyMessage("procesoBatch.error.espereProcesoDependienteEnEjecucion",usuario);
			return mensaje;			
		}
		
						
		/******************************************/
		//Se verifica el numero de lote de los archivos

		String verifica = verificaNumeroLote(params); 
		
		if(verifica.equals(Constants.NUMERO_UNO)){
			String keyMensaje = "interfaz.numero.lote";
			returnMensaje = getKeyMessage(keyMensaje, usuario);
			return returnMensaje;
		
		}
		if (verifica.equals("-1")){
				String keyMensaje = "interfaz.numero.archivos.incompletos";
				returnMensaje = getKeyMessage(keyMensaje, usuario);
				return returnMensaje;
		}
		if (verifica.equals("-2")){
			String keyMensaje = "interfaz.compuesta.control";
			returnMensaje = getKeyMessage(keyMensaje, usuario);
			return returnMensaje;
	    }
		if (verifica.equals("-3")){
			String keyMensaje = "interfaz.compuesta.numero.archivos.lotes.incompletos";
			returnMensaje = getKeyMessage(keyMensaje, usuario);
			return returnMensaje;
		}				
		/******************************************/
		
		
		return returnMensaje;
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazHiloAbstractService#executeProcessBeforeInterfaz(java.util.Map)
	 */
	public Map executeProcessBeforeInterfaz(Map params) throws Exception {
		   // Paso todos los parametros al map
			Usuario usuario = (Usuario) params.get("usuario");
			params.put("descripcionEtapaProceso",Constants.NUMERO_ETAPA_PROCESO_BATCH_DEFAULT);
			Long idProcesoBatch = procesoBatchService.getSecuenciaSiguienteProcesoBatchActu();
			params.put("idProcesoBatch", idProcesoBatch);
			procesoBatchService.deleteProcesoBatchActu(params, usuario);
			procesoBatchService.insertProcesoBatchActu(params, usuario);
			
			params.put("mostrarPaginaConsultaBatch", Constants.SI);
			return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazHiloAbstractService#executeHilo(java.util.Map)
	 */
	public void executeHilo(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'executeHilo' method");
		}
		Usuario usuario = (Usuario) params.get("usuario");
		try {
			params = executeProcessBeforeInterfaz(params);
			log.info("queryParams=" + params);
		}
		catch (Exception e) {
			params.put("errorInterfaz", Constants.SI);
			this.procesoBatchService.updateInterfazRegistroProcesoBatch(params,usuario, e, this);			
			if (log.isDebugEnabled()) {
				log.debug("Fin 'executeHilo' method");
			}
			return ;
		}
		
		try {
			if (continueExecuteInterfaz(params)) {
				beforeExecuteInterfaz(params);
	            this.procesoBatchService.updateEstadoEjecucionInterfazProcesoBatch(params,usuario, this);  
	            SSiCC_Desfasado_InterfazExecutionResult interfazExecutionResult = interfazCompuestoExecutionService.executeInterfaz((HashMap)params);
	            afterExecuteInterfaz(params, interfazExecutionResult);
	            this.procesoBatchService.updateInterfazRegistroProcesoBatch(params, interfazExecutionResult, usuario, this);
			}
			else {
				finalizeProcesoBatchSinEjecutarInterfaz(params, usuario);
			}
		}
		catch (Exception e) {
			params.put("errorInterfaz", Constants.SI);
			this.procesoBatchService.updateInterfazRegistroProcesoBatch(params, usuario, e, this);			
		}
		
		if (log.isDebugEnabled()) {
			log.debug("Fin 'executeHilo' method");
		}
		return ;
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazHiloAbstractService#executeInterfaz(java.util.Map)
	 */
	public void executeInterfaz(final Map params) throws Exception {
		log.debug("inicio executeInterfaz");
		ExecutorService executeService = Executors.newSingleThreadExecutor() ;
		
			executeService.execute(new Runnable() {
				public void run() { 
					 try {
						 String codigo = (String) params.get("codigoInterfaz");
						 log.debug("codigo executeInterfaz "+codigo);
						 //BaseInterfazHiloAbstractService service = getImplementation(codigo);
						 executeHilo(params); 
					} catch (Exception e) {
						e.printStackTrace();
					} 
				  }
				});
		
			executeService.shutdown();
			
	}
		
	/**
	 * @param params
	 */
	public abstract void beforeExecuteInterfaz(Map params);
	
	/**
	 * @param params
	 * @param SSiCC_Desfasado_InterfazExecutionResult
	 */
	public void afterExecuteInterfaz(Map params, SSiCC_Desfasado_InterfazExecutionResult interfazExecutionResult){
		params.put("executionResult", interfazExecutionResult);		
	}
	
	/**
	 * Metodo que es invocado para finalizar la ejecucion del proceso
	 * @param params
	 * @param request
	 * @param SSiCC_Desfasado_InterfazExecutionResult
	 * @param usuario
	 */
	public void updateInterfazRegistroProcesoBatch(Map params,			
			SSiCC_Desfasado_InterfazExecutionResult interfazExecutionResult,
			Usuario usuario) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateInterfazRegistroProcesoBatch' method");
		}
		String numeroLote = interfazExecutionResult.getNumeroLote();
		params.put("numeroLote", numeroLote);
		params.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);
		params.put("usuario", usuario);
		params.put("flagFinalizo", true);
		boolean ejecucionCompletada = interfazExecutionResult.ejecucionCompletada();
		if (ejecucionCompletada) {
			params.put("descripcionLog", Constants.ARCHIVO_ENVIADO_GENERICO);
			params.put("codigoEstadoProceso", Constants.CODIGO_PROCESO_BATCH_OK);
		}
		else {
			params.put("descripcionLog", Constants.PROCESO_BATCH_ENVIADO_GENERICO_ERRORES);
			params.put("codigoEstadoProceso", Constants.CODIGO_PROCESO_BATCH_ERROR);
		}	
		procesoBatchService.updateProcesoBatchActu02(params, usuario);
		procesoBatchService.executeProcesoBatchActuRecomendacionError(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazHiloAbstractService#updateEstadoEjecucionInterfazProcesoBatch(java.util.Map, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEstadoEjecucionInterfazProcesoBatch(Map params,
			Usuario usuario) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateInterfazEstadoRegistroProcesoBatch' method");
		}
		params.put("codigoEstadoProceso", Constants.CODIGO_PROCESO_BATCH_EN_GENERACION_INTERFAZ);
		procesoBatchService.updateEstadoProcesoBatchActu(params, usuario);		
	}


	/**
	 * @param params
	 * @param usuario
	 * @param exception
	 */
	public void updateInterfazRegistroProcesoBatch(Map params, Usuario usuario,
			Exception exception) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateInterfazRegistroProcesoBatch' method");
		}
		params.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);
		params.put("codigoEstadoProceso",Constants.CODIGO_PROCESO_BATCH_ERROR);
		
		log.error(exception);
		String descripcionLog = exception.getMessage();
		
		if (descripcionLog!= null && descripcionLog.length() >= 1000) {
			descripcionLog = descripcionLog.substring(1,999);
		}
		
		params.put("flagFinalizo", true);
		params.put("descripcionLog", descripcionLog);	
		procesoBatchService.updateProcesoBatchActu02(params, usuario);
		params.put("usuario", usuario);
		procesoBatchService.executeProcesoBatchActuRecomendacionError(params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazHiloAbstractService#finalizeProcesoBatchSinEjecutarInterfaz(java.util.Map, biz.belcorp.ssicc.model.Usuario)
	 */
	public void finalizeProcesoBatchSinEjecutarInterfaz(Map params,
			Usuario usuario) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'finalizeProcesoBatchSinInterfaz' method");
		}
		params.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);
		params.put("codigoEstadoProceso",Constants.CODIGO_PROCESO_BATCH_OK);
		procesoBatchService.updateProcesoBatchActu(params, usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazHiloAbstractService#setExecuteInterfazONResults(java.util.Map)
	 */
	public void setExecuteInterfazONResults(Map params) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazHiloAbstractService#getMessageEjecutandoseProcesoBatch()
	 */
	public String getMessageEjecutandoseProcesoBatch() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazHiloAbstractService#getListaArchivos(biz.belcorp.ssicc.sisicc.model.Interfaz)
	 */
	public List getListaArchivos(Map params,Interfaz interfaz) {
		Usuario usuario=(Usuario)params.get("usuario");
		String mensaje = getKeyMessage("mensaje.error.noExisteArchivo",usuario);
		List archivosList = new ArrayList();			
		InterfazParams interfazParams = new InterfazParams();
		interfazParams.setInterfaz(interfaz);
		try {
			archivosList = interfazParams.getListArchivosEntrada();		
			if(archivosList.size() == 0){
				LabelArchivos labelArchivos = new LabelArchivos();
				labelArchivos.setNombreArchivo(mensaje);
				archivosList.add(labelArchivos);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return archivosList;
	}

	/* 
	 * valida lo archvios que van aprocesar
	 * retorna -1 si archivos incompletos
	 *          0 exito
	 *          1 numero de archvio de lote o lotes no son correctos 
	 *          -2 archvio de control obligatorio
	 *          -3 N�mero de Archivos Incompletos o el n�mero de Lote de los archivos no son iguales.
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazHiloAbstractService#verificaNumeroLote(java.util.Map)
	 */
	public String verificaNumeroLote(Map params) {
		String valida = "0";
		String codigoPais = (String) params.get("codigoPais");
		String codigoSistema = (String) params.get("codigoSistema");
		String codigoInterfaz = (String) params.get("codigoInterfaz");
		
		InterfazPK interfazPK = new InterfazPK(codigoPais,
				codigoSistema, codigoInterfaz);
		Interfaz interfaz = interfazService.getInterfaz(interfazPK);
		
		/* INI NUEVO MULTILOTE */
		int numeroMultiLote = 1;
		//int numeroMultiLoteActual = numeroMultiLote;
		params.put("numeroMultiLote", numeroMultiLote);
		List listaMultiLoteCompuesto = new ArrayList();
		List listaMultiLoteCompuestoSize = new ArrayList();
		params.put("listaMultiLoteCompuesto", listaMultiLoteCompuesto);
		params.put("listaMultiLoteCompuestoSize", listaMultiLoteCompuestoSize); //contiene el numero de registros del archivo , su corrsondiente
		//se encuentra en la lista anterior
		/* FIN NUEVO MULTILOTE */
		
		params.put("listaNombresArchivos", null);
		
		if (StringUtils.isBlank(codigoSistema) || StringUtils.isBlank(codigoInterfaz))
			return valida;
		
		if(interfaz.getTipo().equals(Constants.INTERFAZ_TIPO_ENTRADA) && 
		   interfaz.getTipoGeneracion().equals(Constants.TIPO_GENERACION_PAQUETE)){
			
			valida = validaEntradaPaquete(params,interfazPK,
					numeroMultiLote, listaMultiLoteCompuesto,listaMultiLoteCompuestoSize);
		}
		
		if(interfaz.getTipo().equals(Constants.INTERFAZ_TIPO_ENTRADA) && 
				   interfaz.getTipoGeneracion().equals(Constants.TIPO_GENERACION_COMPUESTA)){
					
			valida = validaEntradaPaqueteCompuesta(params,interfazPK,
							numeroMultiLote, listaMultiLoteCompuesto,listaMultiLoteCompuestoSize);
		}
		
		return valida;
	}
	
	/**
	 * Si son de Tipo Compuesta y de Entrada se debe validar que los archivos tengan el mismo numero de Lote
	 * @param params
	 * @param valida
	 * @param interfazPK
	 * @param numeroMultiLote
	 * @param listaMultiLote
	 * @return
	 */
	private String validaEntradaPaqueteCompuesta(Map params,InterfazPK interfazPK, int numeroMultiLote, List listaMultiLoteCompuesto, List listaMultiLoteCompuestoSize) {
		String valida=Constants.NUMERO_CERO;
		int numeroMultiLoteActual=numeroMultiLote;
		boolean validaPaquetes=false;
		boolean validaUnitaria=false;
		boolean isVacioPaquete= true;
		// Si son de Tipo Paquete y de Entrada se debe validar que los archivos tengan el mismo numero de Lote
		//Obteniendo la lista de interfaces del paquete
		List listPaquete = interfazService.getComponentesInterfazPaquete(interfazPK);
		Iterator it = listPaquete.iterator();
		while(it.hasNext()){
			Interfaz interInicial = (Interfaz)it.next();
			if(Constants.TIPO_GENERACION_UNITARIA.equals(interInicial.getTipoGeneracion())){				
				List listaMultiLote = new ArrayList();
				List listaMultiLoteSize = new ArrayList();
				List archivos = getListaArchivos(params,interInicial);
				log.debug("archivos size "+archivos.size());
				String[] listaNombresArchivos = null;
				long[] listaArchivosSize = null;				
				for(int x=0; x<archivos.size();x++){
					listaNombresArchivos = new String[1];//es unitaria
					listaArchivosSize = new long[1];
					LabelArchivos labelArchivos = (LabelArchivos)archivos.get(x);	
					log.debug("labelArchivos "+labelArchivos.getNombreArchivo());
					if (labelArchivos.getPesoArchivo() == null ){
						valida = "-1";
						validaUnitaria=true;
						listaNombresArchivos[0] = labelArchivos.getNombreArchivo();
						listaArchivosSize[0] = labelArchivos.getNumeroRegistro();
						//si la interfaz es de control deberia cargar interfaz de control
						/*if(Constants.NUMERO_UNO.equals(interInicial.getIndicadorControl())){
							valida = "-2";
							return valida;
						}*/												
						//return valida;
					}else{
						valida="0";
						validaUnitaria=true;
						listaNombresArchivos[0] = labelArchivos.getNombreArchivo();
						listaArchivosSize[0] = labelArchivos.getNumeroRegistro();
					}
					listaMultiLote.add(listaNombresArchivos);
					listaMultiLoteSize.add(listaArchivosSize);
					validaUnitaria= false || validaUnitaria;
					log.debug("hay al menos un archivo UNITARIO para ejecutar "+ validaUnitaria);
				}
				if(Constants.NUMERO_UNO.equals(interInicial.getIndicadorControl())){
					//params.put("hayArchivoControl", Constants.NUMERO_UNO);
					params.put("codigoInterfazArchivoControl", interInicial.getCodigo());
				}				
				listaMultiLoteCompuesto.add(listaMultiLote);
				listaMultiLoteCompuestoSize.add(listaMultiLoteSize);
				log.debug("size unitario "+listaMultiLoteCompuesto.size());
			}
			if(Constants.TIPO_GENERACION_PAQUETE.equals(interInicial.getTipoGeneracion())){							
				valida = validaEntradaPaquete(params,interInicial.getInterfazPK(),
											  numeroMultiLote, listaMultiLoteCompuesto,listaMultiLoteCompuestoSize);				

				if(Constants.NUMERO_CERO.equals(valida)){
					validaPaquetes=true;
					isVacioPaquete = false;
				}else{
					if("-2".equals(valida))//es vacio
						validaPaquetes = validaPaquetes && true;
					else{				//si la validacion del paquete sale distinto de cero hay q retornanr el error
						validaPaquetes=false;
						isVacioPaquete = false;//ya no es vacio pero hay error
					}
				}
					
				validaPaquetes= false || validaPaquetes ;
				log.debug("hay al menos un PAQUETE para ejecutar "+ validaPaquetes);
			}
		}			
		
		//validamos si nohay archivos pa evaluar
		Iterator itCompuestoSize = listaMultiLoteCompuestoSize.iterator();
		long sum=0;
		while(itCompuestoSize.hasNext()){
			List list = (List)itCompuestoSize.next();
			for(int k=0;k<list.size();k++){
				long[] arrSize = (long[])list.get(k);
				for(int m=0; m< arrSize.length;m++){
					sum+=arrSize[m];
					if(sum >0 ) break;
				}
			}
		}
		log.debug("sum "+sum);
		boolean validaTodas=false;
		if(isVacioPaquete)
			validaTodas= validaUnitaria && (sum != 0);
		else{
			validaTodas= validaPaquetes && validaUnitaria && (sum != 0);
		}
		log.debug("Resultado final >>> existe archivo UNITARIO/PAQUETE para ejecutar "+ validaTodas);
		return !validaTodas?"-3":"0";
	}

	/**
	 * Si son de Tipo Paquete y de Entrada se debe validar que los archivos tengan el mismo numero de Lote
	 * @param params
	 * @param valida
	 * @param interfazPK
	 * @param numeroMultiLote
	 * @param listaMultiLote
	 * @return
	 */
	private String validaEntradaPaquete(Map params,	InterfazPK interfazPK, int numeroMultiLote,List listMultiLoteCompuesto,List listMultiLoteCompuestoSize) {
		String valida=Constants.NUMERO_CERO;
		int numeroMultiLoteActual=numeroMultiLote;//defaul es 1
		List listaMultiLote = new ArrayList();
		List listaMultiLoteSize = new ArrayList();
		// Si son de Tipo Paquete y de Entrada se debe validar que los archivos tengan el mismo numero de Lote
		//Obteniendo la lista de interfaces del paquete
		List listPaquete = interfazService.getComponentesInterfazPaquete(interfazPK);
		boolean isVacio=true;
		if (listPaquete != null) {
			String[] listaInterfaces = new String[listPaquete.size()];
			String[] listaInterfacesPrefijo = new String[listPaquete.size()];
			String[] listaNombreArchivo = new String[listPaquete.size()];
			String[] listaNombresArchivos = new String[listPaquete.size()];
			long[] listaArchivosSize = new long[listPaquete.size()];
			List archivos = null;
			
			/* INI NUEVO MULTILOTE */
			String indicadorMultiLote = (String)params.get("indicadorMultiLote");
			if (Constants.SI.equals(indicadorMultiLote)) {
				Interfaz interInicial = (Interfaz)listPaquete.get(0);
				archivos = getListaArchivos(params,interInicial);							
				numeroMultiLote = archivos.size();
				numeroMultiLoteActual = numeroMultiLote;
				params.put("numeroMultiLote", numeroMultiLote); 
			}	
			
			for (int x=0; x < numeroMultiLote; x++) {
				listaNombresArchivos = new String[listPaquete.size()];
				listaArchivosSize = new long[listPaquete.size()];
			/* FIN NUEVO MULTILOTE */
				
				for (int i = 0; i < listPaquete.size(); i++) {
					Interfaz inter = (Interfaz)listPaquete.get(i);
					
					listaInterfaces[i] = inter.getCodigo();					
					if (inter.getNombreArchivoEntradaSalida() != null){
						listaInterfacesPrefijo[i] = inter.getNombreArchivoEntradaSalida();
					}
					else{
						listaInterfacesPrefijo[i] = inter.getCodigo() + '_';
					}					
					archivos = getListaArchivos(params,inter);
															
					
					/* INI NUEVO MULTILOTE */
					//LabelArchivos labelArchivos = (LabelArchivos)archivos.get(0);
					numeroMultiLoteActual = archivos.size();
					if (Constants.SI.equals(indicadorMultiLote)) {
						if (numeroMultiLoteActual != numeroMultiLote) {
							valida = "-1";
							break;
						}
					}	
					LabelArchivos labelArchivos = (LabelArchivos)archivos.get(x);
					
										
					
					/* FIN NUEVO MULTILOTE */
					
					if (labelArchivos.getPesoArchivo() != null ){
						listaNombreArchivo[i] = labelArchivos.getNombreArchivo().substring(listaInterfacesPrefijo[i].length(), labelArchivos.getNombreArchivo().length()-4);
						listaNombresArchivos[i] = labelArchivos.getNombreArchivo();
						listaArchivosSize[i] = labelArchivos.getNumeroRegistro();
						isVacio=false;
					}
					else{						
						isVacio= isVacio && true;
						//cuando el numero de archvios es vacio x unitarias debe retornar true						
						valida = "-1";//para no validar los nombres de archivo
						//break;
					}
				}
				
				/* INI NUEVO MULTILOTE */
				if (valida.equals(Constants.NUMERO_CERO)){
					String loteAnterior = "";
					//Se compara los numeros de lotes de los archivos de las interfaces
					for (int i = 0; i < listaNombreArchivo.length; i++) {
						if (i!= 0 ){
							if (!listaNombreArchivo[i].equals(loteAnterior)){
								valida = "1";
								break;	
							}	
						}
						loteAnterior = listaNombreArchivo[i];
					}
				}
				if (x==0) {
					params.put("listaNombresArchivos", listaNombresArchivos);
				}
				listaMultiLote.add(listaNombresArchivos);
				listaMultiLoteSize.add(listaArchivosSize);
				/* FIN NUEVO MULTILOTE */
				
			}
		  if(isVacio) {
			  valida = "-2";	
		  }
		}
		
		listMultiLoteCompuesto.add(listaMultiLote);
		listMultiLoteCompuestoSize.add(listaMultiLoteSize);
		return valida;
	}

	
	

	/**
	 * @return Returns the messageSource.
	 */
	public MessageSource getMessageSource() {
		return messageSource;
	}

	/**
	 * @param messageSource The messageSource to set.
	 */
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * @return the interfazService
	 */
	public InterfazService getInterfazService() {
		return interfazService;
	}

	/**
	 * @param interfazService the interfazService to set
	 */
	public void setInterfazService(InterfazService interfazService) {
		this.interfazService = interfazService;
	}

	/**
	 * @return the procesoBatchService
	 */
	public ProcesoBatchService getProcesoBatchService() {
		return procesoBatchService;
	}

	/**
	 * @param procesoBatchService the procesoBatchService to set
	 */
	public void setProcesoBatchService(ProcesoBatchService procesoBatchService) {
		this.procesoBatchService = procesoBatchService;
	}
	
	/**
	 * Hook method para la ejecucion de la Interfaz. Esta implementacion
	 * devuelve siempre true y siempre se ejecuta la Interfaz. En caso que la
	 * Interfaz no se deba ejecutar debido a algun valor en los parametros se
	 * puede sobrescribir este metodo.
	 * 
	 * @param params
	 *            parametros de la Interfaz
	 * @return true si se va a ejecutar la Interfaz, false en caso de que no se
	 *         ejecute
	 */
	protected boolean continueExecuteInterfaz(Map params) {
		return true;
	}

	/**
	 * @return the interfazCompuestoExecutionService
	 */
	public SSiCC_Desfasado_InterfazCompuestoExecutionService getInterfazCompuestoExecutionService() {
		return interfazCompuestoExecutionService;
	}

	/**
	 * @param interfazCompuestoExecutionService the interfazCompuestoExecutionService to set
	 */
	public void setInterfazCompuestoExecutionService(
			SSiCC_Desfasado_InterfazCompuestoExecutionService interfazCompuestoExecutionService) {
		this.interfazCompuestoExecutionService = interfazCompuestoExecutionService;
	}



		/* (non-Javadoc)
		 * @see biz.belcorp.ssicc.service.framework.Service#getKeyMessage(java.lang.String)
		 */
		public String getKeyMessage(String keyMensaje) {
			String mensaje = new String();
			if (StringUtils.isBlank(keyMensaje)) return "";
			try {
				mensaje = this.messageSource.getMessage(keyMensaje, null,
						new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mensaje;
		}
		
		
		/**
		 * @param keyMensaje
		 * @param args
		 * @return
		 */
		public String getKeyMessage(String keyMensaje, Object args[]) {
			String mensaje = new String();
			if (StringUtils.isBlank(keyMensaje)) return "";
			try {
				mensaje = this.messageSource.getMessage(keyMensaje, args,
						new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mensaje;
		}

		
		/* (non-Javadoc)
		 * @see biz.belcorp.ssicc.service.framework.Service#getKeyMessage(java.lang.String, biz.belcorp.ssicc.dao.model.Usuario)
		 */
		public String getKeyMessage(String keyMensaje, Usuario usuario) {		
			String mensaje = new String();
			if (StringUtils.isBlank(keyMensaje)) return "";
			try {
				mensaje = this.messageSource.getMessage(keyMensaje, null,
						this.getLocale(usuario));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mensaje;
		}
		
		
		/* (non-Javadoc)
		 * @see biz.belcorp.ssicc.service.framework.Service#getLocale(biz.belcorp.ssicc.dao.model.Usuario)
		 */
		public Locale getLocale(Usuario usuario) {
			
			if (usuario != null) {
				Idioma idioma = usuario.getIdioma();
				Locale locale = new Locale(idioma.getCodigoISO());
				if (locale == null)
					locale = new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);
				return locale;
			}	
			return new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);
		}    
		
		
		
		/* (non-Javadoc)
		 * @see biz.belcorp.ssicc.service.framework.Service#obtieneMensajeErrorException(java.lang.Exception)
		 */
		public String obtieneMensajeErrorException(Exception e) {
			String retorno = null;
			if (StringUtils.isNotBlank(e.getMessage()))
				return e.getMessage();
			if (StringUtils.isNotBlank(e.getLocalizedMessage()))
				return e.getLocalizedMessage();
			retorno = e.toString();
			return retorno;
		}
	


}
