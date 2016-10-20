package biz.belcorp.ssicc.service.sisicc.framework.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOBloqueoControlDAO;
import biz.belcorp.ssicc.service.sisicc.framework.BaseInterfazService;
import biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.BaseNuevoMultiHiloInterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazNuevoMultiHiloParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazStatus;


/* NUEVA REESTRUCTURACION NSSICC */
/**
 * Clase Service abstracta para los Paquetes de Interfaces SiCC. Implementa el template
 * method 'executePaquete' que contiene el algoritmo generico para las
 * Interfaces SiCC, tanto de entrada como de salida. Provee diversos metodos
 * para extender este comportamiento generico en las subclases.
 * 
 * Mejoras Realizadas NSSICC:
 *  - Colocacion del this
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 */
@Service("sisicc.baseInterfazPaqueteAbstractService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public abstract class BaseInterfazPaqueteAbstractServiceImpl extends BaseInterfazAbstractService implements
		BaseInterfazService {
	
	@Resource(name="sisicc.interfazExecutionService")
	protected InterfazExecutionService interfazExecutionService;
	
	@Resource(name="spusicc.mantenimientoSTOBloqueoControlDAO")
	protected MantenimientoSTOBloqueoControlDAO mantenimientoSTOBloqueoControlDAO;
	
	protected InterfazExecutionResult interfazExecutionResult;
		
	private boolean ejecucionExitosa;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.BaseInterfazAbstractService#executeInterfaz(biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams)
	 */
	public InterfazResult executeInterfaz(InterfazParams interfazParams) throws Exception {
		log.debug("Entering 'executeInterfaz' method");
		this.ejecucionExitosa = true;
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.BaseInterfazAbstractService#executePaquete(biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams)
	 */
	public InterfazExecutionResult executePaquete(InterfazParams interfazParams) throws Exception {
		log.debug("Entering 'executePaquete' method");
		this.ejecucionExitosa = true;
		interfazParams = this.initInterfazParams(interfazParams);
		
		Interfaz interfazPaquete = interfazParams.getInterfaz();
		String codigoInterfazPaquete  = interfazPaquete.getCodigo();
		String descripcionLotePaquete = interfazPaquete.getDescripcion();
		
		Usuario usuario = interfazParams.getUsuario();		
		Map<String, Object> params = interfazParams.getQueryParams();
		boolean errorInterfaz = false;
		Exception excepcionInterfaz = new Exception("");
		
		int registrosProcesados = 0;
		try {
			log.info("Iniciando procesamiento de Paquete de Interfaz");
			
			/* INSERTANDO NRO DE LOTE */
			this.insertarNumeroLotePaquete(interfazParams);
			params = interfazParams.getQueryParams();
			
			/* Inicializacion del RESULT */
			this.interfazResult = this.initInterfazResult(interfazParams);
			this.interfazExecutionResult = this.initPaqueteResult(interfazParams);
			

			/* PROCESO PRINCIPAL */
			Historico historicoEjecucion = interfazParams.getHistorico();
			
			/* EJECUCION PROCESOS PREVIOS INTERFAZ */
			try {
				params.put("descripcionEtapaProceso", Constants.PREVIO_INTERFAZ);
				this.procesoBatchService.updateProcesoBatchEtapaProceso(params, usuario);
				
				historicoEjecucion.setFechaIniProcesoAnteriorInterfaz(new Timestamp(System.currentTimeMillis()));
				this.historicoService.updateHistoricoIniProcesoAnteriorInterfaz(historicoEjecucion, usuario);
				
				if (this.beforeTransaccionIndependiente) {
					this.procesoBatchService.beforeNewTransaction(interfazParams, this);
				}
				else {
					this.beforeExecuteInterfaz(params);
					this.beforeProcessInterfaz(interfazParams);
				}
				interfazResult.setCompletadoProcesosBeforeInterfaz(true); // FRAMEWORK NSSICC
				this.interfazExecutionResult.setInterfazResultPaquete(this.interfazResult);
				
				historicoEjecucion.setCodigoInterfaz(codigoInterfazPaquete);
				interfazParams.getInterfaz().setCodigo(codigoInterfazPaquete);
				interfazParams.getInterfaz().setDescripcion(descripcionLotePaquete);
				
				historicoEjecucion.setFechaFinProcesoAnteriorInterfaz(new Timestamp(System.currentTimeMillis()));
				historicoEjecucion.setFlagErrorProcesoAnteriorInterfaz(Constants.NO);
				this.historicoService.updateHistoricoFinProcesoAnteriorInterfaz(historicoEjecucion, usuario);
			}
			catch (Exception e) {
				errorInterfaz = true;
				historicoEjecucion.setCodigoInterfaz(codigoInterfazPaquete);
				interfazParams.getInterfaz().setCodigo(codigoInterfazPaquete);
				interfazParams.getInterfaz().setDescripcion(descripcionLotePaquete);
				
				historicoEjecucion.setFechaFinProcesoAnteriorInterfaz(new Timestamp(System.currentTimeMillis()));
				historicoEjecucion.setFlagErrorProcesoAnteriorInterfaz(Constants.SI);
				this.historicoService.updateHistoricoFinProcesoAnteriorInterfaz(historicoEjecucion, usuario);
				excepcionInterfaz = e;
			}
			
			
			/* EJECUCION INTERFAZ */
			if (this.continueExecuteInterfaz(params)) {
				if (!errorInterfaz) {
					try {
						params.put("descripcionEtapaProceso", Constants.EJECUCION_INTERFAZ);
						this.procesoBatchService.updateProcesoBatchEtapaProceso(params, usuario);
						
						historicoEjecucion.setFechaIniInterfaz(new Timestamp(System.currentTimeMillis()));
						historicoEjecucion.setFlagErrorInterfaz(Constants.NO);
						this.historicoService.updateHistoricoIniInterfaz(historicoEjecucion, usuario);
						
						this.processInterfaz(interfazParams);
						
						historicoEjecucion.setCodigoInterfaz(codigoInterfazPaquete);
						interfazParams.getInterfaz().setCodigo(codigoInterfazPaquete);
						interfazParams.getInterfaz().setDescripcion(descripcionLotePaquete);
						interfazResult.setCompletadoInterfaz(true);  // FRAMEWORK NSSICC
						this.interfazExecutionResult.setInterfazResultPaquete(this.interfazResult);
						
						historicoEjecucion.setFechaFinInterfaz(new Timestamp(System.currentTimeMillis()));
						this.historicoService.updateHistoricoFinInterfaz(historicoEjecucion, usuario);
					}
					catch (Exception e) {
						errorInterfaz = true;
						
						historicoEjecucion.setCodigoInterfaz(codigoInterfazPaquete);
						interfazParams.getInterfaz().setCodigo(codigoInterfazPaquete);
						interfazParams.getInterfaz().setDescripcion(descripcionLotePaquete);
						historicoEjecucion.setFechaFinInterfaz(new Timestamp(System.currentTimeMillis()));
						
						String validarListaInterfaceSalida = (String) params.get("validarListaInterfaceSalida");
						if (StringUtils.isNotBlank(validarListaInterfaceSalida) && Constants.NO.equals(validarListaInterfaceSalida)) {
							errorInterfaz = false;
						}
						if (errorInterfaz) {
							historicoEjecucion.setFlagErrorInterfaz(Constants.SI);
							historicoEjecucion.setEstadoProceso(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_DESCONOCIDO);
						}
						this.historicoService.updateHistoricoFinInterfaz(historicoEjecucion, usuario);
						excepcionInterfaz = e;
					}
				}
				/* EJECUCION PROCESOS POSTERIORES INTERFAZ */
				if (!errorInterfaz) {
					try {
						params.put("descripcionEtapaProceso", Constants.POSTERIOR_INTERFAZ);
						this.procesoBatchService.updateProcesoBatchEtapaProceso(params, usuario);
						
						historicoEjecucion.setFechaIniProcesoPosteriorInterfaz(new Timestamp(System.currentTimeMillis()));
						this.historicoService.updateHistoricoIniProcesoPosteriorInterfaz(historicoEjecucion, usuario);
					
						if (this.afterTransaccionIndependiente) {
							this.procesoBatchService.afterNewTransaction(interfazParams, this);
						}
						else {
							this.afterProcessInterfaz(interfazParams);
							this.afterExecuteInterfaz(params);
						}
						
						historicoEjecucion.setCodigoInterfaz(codigoInterfazPaquete);
						interfazParams.getInterfaz().setCodigo(codigoInterfazPaquete);
						interfazParams.getInterfaz().setDescripcion(descripcionLotePaquete);
						interfazResult.setCompletadoProcesosAfterInterfaz(true); // FRAMEWORK NSSICC
						this.interfazExecutionResult.setInterfazResultPaquete(this.interfazResult);
						
						historicoEjecucion.setFechaFinProcesoPosteriorInterfaz(new Timestamp(System.currentTimeMillis()));
						historicoEjecucion.setFlagErrorProcesoPosteriorInterfaz(Constants.NO);
						this.historicoService.updateHistoricoFinProcesoPosteriorInterfaz(historicoEjecucion, usuario);
					}
					catch (Exception e) {
						errorInterfaz = true;
						
						historicoEjecucion.setCodigoInterfaz(codigoInterfazPaquete);
						interfazParams.getInterfaz().setCodigo(codigoInterfazPaquete);
						interfazParams.getInterfaz().setDescripcion(descripcionLotePaquete);
						historicoEjecucion.setFechaFinProcesoPosteriorInterfaz(new Timestamp(System.currentTimeMillis()));
						historicoEjecucion.setFlagErrorProcesoPosteriorInterfaz(Constants.SI);
						this.historicoService.updateHistoricoFinProcesoPosteriorInterfaz(historicoEjecucion, usuario);
						excepcionInterfaz = e;
					}
				}
			}
			
	
		} finally {
			
					
			/* ACTUALIZANDO DATOS FINALES */
			interfazParams.getInterfaz().setCodigo(codigoInterfazPaquete);
			interfazParams.getInterfaz().setDescripcion(descripcionLotePaquete);
			Historico historicoEjecucion = interfazParams.getHistorico();
			
			historicoEjecucion.setCodigoInterfaz(codigoInterfazPaquete);
			interfazParams.setHistorico(historicoEjecucion);
			
			log.info("Interfaz procesada, registrosProcesados="	+ registrosProcesados);
			
			if (!errorInterfaz && this.ejecucionExitosa) {
				this.updateInterfazResultOnSuccess(interfazResult, registrosProcesados);
				this.updateHistoricoOnSuccess(interfazParams.getHistorico(), registrosProcesados);
			}
			else {
				this.updateResultOnException(interfazResult, excepcionInterfaz);
				this.updateHistoricoOnExceptionGral(interfazParams.getHistorico(),excepcionInterfaz);
			}
			this.onFinally(interfazParams, interfazResult);
			this.saveInterfaz(interfazParams);
			try {
				this.saveLog(interfazParams);
			}
			catch(Exception exlog) {
				exlog.printStackTrace();
			}
			this.saveHistorico(interfazParams);
			
			if (!errorInterfaz && this.ejecucionExitosa) {
				String indicadorActualizarProcesoBatchEnAction = (String)params.get("indicadorActualizarProcesoBatchEnAction");
				if (StringUtils.isBlank(indicadorActualizarProcesoBatchEnAction) )
					this.updateInterfazRegistroProcesoBatch(interfazParams);
				else 
					if (indicadorActualizarProcesoBatchEnAction.equals(Constants.NO)) {
						this.updateInterfazRegistroProcesoBatch(interfazParams);
					}
			}
			else {
				this.updateInterfazRegistroProcesoBatch(interfazParams, excepcionInterfaz);
			}
		}
		
		log.info("Se termino la ejecucion del Paquete de Interfaz");
		return this.interfazExecutionResult;
	}
	
		
	/**
	 * Genera e inserta Nro de Lote en la Interfaz y actualiza LOTE en PROCESO BATCH
	 * @param interfazParams
	 * @throws Exception
	 */
	private void insertarNumeroLotePaquete(InterfazParams interfazParams) throws Exception {
		Map params = interfazParams.getQueryParams();
		Usuario usuario = interfazParams.getUsuario();
		String tipoInterfazExecution = (String) params.get("tipoInterfazExecution");
		this.insertarNumeroLote(interfazParams);
		if (tipoInterfazExecution.equals(Constants.TIPO_GENERACION_PAQUETE))
			this.procesoBatchService.updateProcesoBatchActuLote(params, usuario);
	}
		
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.BaseInterfazAbstractService#initInterfazParams(biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams)
	 */
	protected InterfazParams initInterfazParams(InterfazParams interfazParams) throws Exception {
		interfazParams = super.initInterfazParams(interfazParams);
		this.initInterfazParamsPaquete(interfazParams);
		return interfazParams;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.BaseInterfazAbstractService#validateValoresIniciales(biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams)
	 */
	private void initInterfazParamsPaquete(InterfazParams interfazParams) throws Exception  {
		
		Map params = interfazParams.getQueryParams();
		Usuario usuario = (Usuario) params.get("usuario");
		Interfaz interfaz = interfazParams.getInterfaz();
				
		/* Otros valores */
		params.put("recomendacionError", "    ");
		params.put("indicadorEjecucion",Constants.INDICADOR_EJECUCION_PROCESO_BATCH_SI);
		params.put("codigoEstadoProceso",Constants.CODIGO_PROCESO_BATCH_EN_EJECUCION);
		params.put("descripcionEtapaProceso",Constants.NUMERO_ETAPA_PROCESO_BATCH_DEFAULT);
		params.put("mostrarPaginaConsultaBatch", Constants.SI);		
		
		interfazParams.setQueryParams(params);
		return;
		
	}

	
	/**
	 * Crea e inicializa el InterfazExecutionResult.
	 * @param interfazParams
	 * @return
	 */
	protected final InterfazExecutionResult initPaqueteResult(InterfazParams interfazParams) {
		this.interfazExecutionResult = new InterfazExecutionResult(interfazParams.getNumeroLote(), interfazParams.getInterfaz());
		this.interfazExecutionResult.setInterfazResultPaquete(this.interfazResult);
		this.interfazExecutionResult = this.setInitPaqueteResult(interfazParams, this.interfazExecutionResult);
		return interfazExecutionResult;
	}
	
	
	/**
	 * Metodo la cual se puede sobreescribir para setear valores Iniciales al InterfazExecutionResult
	 * @param interfazParams
	 * @param interfazExecutionResult
	 * @return
	 */
	protected InterfazExecutionResult setInitPaqueteResult(InterfazParams interfazParams, InterfazExecutionResult interfazExecutionResult) {
		return interfazExecutionResult;
	}
	
	
	/**
	 * Metodo que invoca a la interfaces Empaquetadas del Paquete
	 * @param interfazParams
	 * @param interfazExecutionResult
	 */
	protected int processInterfaz(InterfazParams interfazParams) throws Exception {
		Interfaz interfazEjecucion = interfazParams.getInterfaz();
		
		/* EJECUCION DEL PAQUETE */
		/* Invocando Paquete en forma secuencial */		
		if (Constants.NO.equals(interfazEjecucion.getEsPaqueteMultiHilo())) {
			this.processPaqueteSecuencial(interfazParams); 
		}
		else {
			/* Invocando Paquete en forma MultiHilo / MutliNivel */
			this.processPaqueteMultiHilo(interfazParams);	
		}
		
		/* PARTE FINAL EJECUCION DEL PAQUETE */
		Map params = interfazParams.getQueryParams();
		Usuario usuario = (Usuario) params.get("usuario");
		Historico historicoEjecucion = interfazParams.getHistorico();
		historicoEjecucion.setFechaFinProceso(new Timestamp(System.currentTimeMillis()));
		
		this.interfazExecutionResult.setInterfazResultPaquete(this.interfazResult);
		this.ejecucionExitosa = this.interfazExecutionResult.ejecucionCompletada();  
		
		if (!this.ejecucionExitosa) {
			historicoEjecucion.setFlagError(Constants.SI);
			historicoEjecucion.setFlagErrorInterfaz(Constants.SI);
			historicoEjecucion.setDescripcionError(Constants.INTERFAZSICC_ERROR_EJECUCION_PAQUETE);
			historicoEjecucion.setEstadoProceso(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_DESCONOCIDO);
			interfazParams.setHistorico(historicoEjecucion);
		}
		//ejecutarSto= ejecutarSto || ejecucionExitosa;
		//historicoService.updateHistorico(historicoEjecucion, usuario);

		return 0;
	}
	
	
	/**
	 * Ejecucion del Paquete de Interfaces en forma secuencial
	 * @param interfazParams
	 * @throws Exception
	 */
	private void processPaqueteSecuencial(InterfazParams interfazParams) throws Exception {
		Map params = interfazParams.getQueryParams();
		Interfaz interfazPaquete = new Interfaz();
		interfazPaquete = interfazParams.getInterfaz();
		ArrayList listaInterfacesEmpaquetadas = (ArrayList)params.get("listaInterfacesEmpaquetadas");
	
		Iterator iterator = listaInterfacesEmpaquetadas.iterator();
		while (iterator.hasNext()) {
			Interfaz interfazEmpaquetada = (Interfaz) iterator.next();
			log.info("Iniciando ejecucion de Interfaz Unitaria empaquetada: "
							+ interfazEmpaquetada.getCodigo()
							+ " "
							+ interfazEmpaquetada.getDescripcion());
			
			BaseInterfazService interfazEmpaquetadaImpl = this.interfazExecutionService.getInterfazImplementation(interfazEmpaquetada.getCodigo());
			InterfazParams interfazParamsClone = this.obtenerInterfazParams(interfazParams, interfazEmpaquetada);
			
			
			/* Ejecucion de Interfaz */
			InterfazResult interfazResult = interfazEmpaquetadaImpl.executeInterfaz(interfazParamsClone);
			List<InterfazResult> interfazResults = this.interfazExecutionResult.getInterfazResultsInterfaz();
			interfazResults.add(interfazResult);
			this.interfazExecutionResult.setInterfazResultsInterfaz(interfazResults);
		}
		
		// Volviendo valores del paquete
		params.put("codigoInterfaz", interfazPaquete.getCodigo());
		interfazParams.setInterfaz(interfazPaquete);
		interfazParams.setQueryParams(params);
	}
	
	
	/**
	 * @param interfazParamsPaquete
	 * @param interfazEmpaquetada
	 * @return
	 * @throws CloneNotSupportedException
	 */
	private InterfazParams obtenerInterfazParams(InterfazParams interfazParamsPaquete, Interfaz interfazEmpaquetada) throws CloneNotSupportedException {
		Map params = interfazParamsPaquete.getQueryParams();
		params.put("codigoInterfaz", interfazEmpaquetada.getCodigo());
		interfazParamsPaquete.setInterfaz(interfazEmpaquetada);
		return interfazParamsPaquete;
	}
	


	/**
	 * Obteniendo interfazParams CLON para las interfaces empaquetadas del Paquete
	 * @param interfazParams
	 * @param interfazEmpaquetada
	 * @return
	 * @throws CloneNotSupportedException
	 */
	private InterfazParams obtenerInterfazParamsClone(InterfazParams interfazParamsPaquete, Interfaz interfazEmpaquetada) throws CloneNotSupportedException {
		Map params = interfazParamsPaquete.getQueryParams();
		InterfazParams interfazParamsClone = new InterfazParams();
		
		interfazParamsClone = (InterfazParams)interfazParamsPaquete.clone();
		interfazParamsClone.getQueryParams().putAll(params);
		
		Map paramsInterfaz = interfazParamsClone.getQueryParams();
		paramsInterfaz.put("codigoInterfaz", interfazEmpaquetada.getCodigo());
		interfazParamsClone.setQueryParams(paramsInterfaz);
		
		Interfaz interfazEmpaquetadaClone = new Interfaz();
		interfazEmpaquetadaClone = (Interfaz) interfazEmpaquetada.clone();
		interfazParamsClone.setInterfaz(interfazEmpaquetadaClone);
		return interfazParamsClone;
	}
	
	
	/**
	 * Invocacion al fomato MultiHilo para la ejecucion del Paquete de Interfaces
	 * @param interfazParams
	 * @throws Exception
	 */
	private void processPaqueteMultiHilo(InterfazParams interfazParams) throws Exception {
		Map params = interfazParams.getQueryParams();
		
		Interfaz interfazEjecucion = interfazParams.getInterfaz();
		
		/* Variables del Pool de Conexiones */
		ExecutorService poolMultiHilo = null;
		Integer nroHilosPoolConexion = new Integer(5);
		Long timeWait = new Long(1000); //POR DEFAULT 1 Segundo
		
		/* obteniendo maximo hilos */
		Map criteriaParamPais = new HashMap();
		criteriaParamPais.put("codigoPais", interfazEjecucion.getCodigoPais());
		criteriaParamPais.put("codigoSistema", "GEN");
		criteriaParamPais.put("nombreParametro", Constants.IND_MAX_HILO_INTERFAZ);
		String nroHilos = mantenimientoSTOBloqueoControlDAO.getParametroGenericoSistema(criteriaParamPais);
		if (StringUtils.isNotBlank(nroHilos))
			nroHilosPoolConexion = new Integer(nroHilos);
		
		/* obteniendo tiempo de espera x nivel */
		criteriaParamPais = new HashMap();
		criteriaParamPais.put("codigoPais", interfazEjecucion.getCodigoPais());
		criteriaParamPais.put("codigoSistema", "GEN");
		criteriaParamPais.put("nombreParametro", Constants.TIME_WAIT_HILO_NIVEL);
		String strTimeWait = mantenimientoSTOBloqueoControlDAO.getParametroGenericoSistema(criteriaParamPais);
		if (StringUtils.isNotBlank(strTimeWait))
			timeWait = new Long(strTimeWait);
		/* fin de obtener el timewait */
		
		
		/* EJECUCION DEL MULTINIVEL Y MULTIHILO */
		ArrayList listaInterfacesEmpaquetadas = (ArrayList)params.get("listaInterfacesEmpaquetadas");
		Integer nroNivelesEjecutar = (Integer)params.get("nroNivelesEjecutar");
		int posIni=0;
		for(int n=0; n<nroNivelesEjecutar; n++) {	
		
			List listInterfacesEmpaquetadasNivel = getListEmpaquetadaNivel(listaInterfacesEmpaquetadas, posIni);
			posIni+=listInterfacesEmpaquetadasNivel.size();
			
			log.debug("Inicio Nivel " + n);
			log.debug("Lista Nivel " + listInterfacesEmpaquetadasNivel.size() + "  inicio de Nivel en : "+posIni);
			
			poolMultiHilo = Executors.newFixedThreadPool(nroHilosPoolConexion);		
			
			this.executePaqueteMultiHilo(interfazParams, poolMultiHilo, timeWait, new Long(n), listInterfacesEmpaquetadasNivel);
		}
		
	}
	
	/**
	 * Devuelve Lista de Interfaces a Ejecutar desde Posicion Ini en la Lista
	 * @param listInterfacesEmpaquetadas
	 * @param posIni
	 * @return
	 */
	protected List getListEmpaquetadaNivel(List listInterfacesEmpaquetadas, int posIni) {
		List result = new ArrayList();		
		Long nivelInicio= ((Interfaz)listInterfacesEmpaquetadas.get(posIni)).getNivelHilo();
		for(int n=posIni; n<listInterfacesEmpaquetadas.size();n++) {
			Interfaz interfazEmpaquetada = (Interfaz) listInterfacesEmpaquetadas.get(n);
			Long nivelHilo =interfazEmpaquetada.getNivelHilo();					
			
			if(nivelHilo.longValue() != nivelInicio.longValue()) break;
			
			result.add(interfazEmpaquetada);
						
		}	
		return result;
	}


	/**
	 * Ejecucion de las interfaces Empaquetadas en MultiHilo
	 * @param interfazParams
	 * @param poolMultiHilo
	 * @param timeWait
	 * @param indiceNivel
	 * @param listaInterfacesEmpaquetadas
	 * @throws Exception
	 */
	private void executePaqueteMultiHilo(InterfazParams interfazParams, ExecutorService poolMultiHilo, Long timeWait,
			Long indiceNivel, List listaInterfacesEmpaquetadas)
			throws Exception {
		
		List<InterfazNuevoMultiHiloParams> listaInterfazNuevoMultiHiloParams = new ArrayList<InterfazNuevoMultiHiloParams>(); 
		
		List<Future<InterfazExecutionResult>> resultHilo = new ArrayList<Future<InterfazExecutionResult>>(); 
		Long hiloActual = new Long(-1);
		int nroHilosEjecutar = -1;
		
		/* AGRUPANDO LOS HILOS */
		Iterator iterator = listaInterfacesEmpaquetadas.iterator();
		
		while (iterator.hasNext()) {
			Interfaz interfazEmpaquetada = (Interfaz) iterator.next();
			Long hiloInterfaz = interfazEmpaquetada.getOrdenHilo();
			if (hiloActual.longValue() != hiloInterfaz.longValue()) {
				hiloActual = hiloInterfaz;
				nroHilosEjecutar++;
				List<Interfaz> listaMultiHilo = new ArrayList<Interfaz>();
				listaMultiHilo.add(interfazEmpaquetada);
				InterfazNuevoMultiHiloParams interfazNuevoMultiHiloParams = new InterfazNuevoMultiHiloParams(indiceNivel, hiloInterfaz, listaMultiHilo );
				listaInterfazNuevoMultiHiloParams.add(interfazNuevoMultiHiloParams);
			}
			else {
				InterfazNuevoMultiHiloParams interfazNuevoMultiHiloParams = listaInterfazNuevoMultiHiloParams.get(nroHilosEjecutar);
				List<Interfaz> listaMultiHilo = interfazNuevoMultiHiloParams.getListaInterfacesEmpaquetadas();
				listaMultiHilo.add(interfazEmpaquetada);
				interfazNuevoMultiHiloParams.setListaInterfacesEmpaquetadas(listaMultiHilo);
				listaInterfazNuevoMultiHiloParams.set(nroHilosEjecutar, interfazNuevoMultiHiloParams);
			}
		}
		
		/* EJECUTANDO POR HILOS */
		int tam = listaInterfazNuevoMultiHiloParams.size();
		for(int i=0; i<tam; i++) {
			InterfazNuevoMultiHiloParams interfazNuevoMultiHiloParams = listaInterfazNuevoMultiHiloParams.get(i);
			
			/*INVOCADOR AL EJECUTOR DE INTERFAZ POR HILO */
			Future<InterfazExecutionResult> submit = poolMultiHilo.submit(new BaseNuevoMultiHiloInterfazResult(this, interfazParams, interfazNuevoMultiHiloParams));
			resultHilo.add(submit);
		}

		// REGRESO AL HILO PADRE DE LA INTERFAZ
		// This will make the executor accept no new threads
		// and finish all existing threads in the queue
		poolMultiHilo.shutdown();
		
		/* VERIFICANDO LA FINALIZACION DE EJECUCION DE LOS HILOS */
		while (!poolMultiHilo.isTerminated()) {
			//Thread.sleep(timeWait);
			poolMultiHilo.awaitTermination(timeWait, TimeUnit.MILLISECONDS);	
		}
		
		/* COLOCANDO EL RESULTADO DE TODOS LOS HILOS EN EL RESULT PRINCIPAL result */
		for(Future<InterfazExecutionResult> res :resultHilo) {
			InterfazExecutionResult resultadoHilo = res.get();
			Iterator iteratorResult = resultadoHilo.getInterfazResultsInterfaz().iterator();
			while (iteratorResult.hasNext()) {
				  InterfazResult interfazResult = (InterfazResult) iteratorResult.next();
				  this.interfazExecutionResult.getInterfazResultsInterfaz().add(interfazResult);
				  
			}
			
			Iterator iteratorParams = resultadoHilo.getListaParamsInterfaz().iterator();
			while (iteratorParams.hasNext()) {
				  Map queryParams = (Map) iteratorParams.next();
				  this.interfazExecutionResult.getListaParamsInterfaz().add(queryParams);
				  
			}
		}		
		
		/* Actualizando QueryParams del Paquete con todo los map de las interfaces unitarias */
		Map paramPaquete = interfazParams.getQueryParams();
		for(int i=0; i < this.interfazExecutionResult.getListaParamsInterfaz().size(); i++) {
			Map paramInterfaz = (Map) this.interfazExecutionResult.getListaParamsInterfaz().get(i);
			paramPaquete.putAll(paramInterfaz);
		}
		interfazParams.setQueryParams(paramPaquete);
	}	
	
	
	/**
	 * Proceso ASINCRONO que ejecuta un conjunto de Interfaces en base al Hilo
	 * @param interfazMultiHiloParams
	 * @param interfazParams
	 * @return
	 */
	public InterfazExecutionResult executeMultiHiloResult(InterfazNuevoMultiHiloParams interfazMultiHiloParams, InterfazParams interfazParams) throws Exception {
		InterfazExecutionResult result = new InterfazExecutionResult();
		if (log.isDebugEnabled()) {
			log.debug("Entering 'executeMultiHiloResult' method");
		}
		List<Interfaz> listaInterfacesEmpaquetadas  = interfazMultiHiloParams.getListaInterfacesEmpaquetadas();
		
		/* Invocando a la Ejecucion de Interfaces */
		for (int i=0;i < listaInterfacesEmpaquetadas.size(); i++) {
			
			Interfaz interfazEmpaquetada = (Interfaz) listaInterfacesEmpaquetadas.get(i);
			BaseInterfazService interfazEmpaquetadaImpl = this.interfazExecutionService.getInterfazImplementation(interfazEmpaquetada.getCodigo());
			if (interfazEmpaquetadaImpl == null) {
				throw new Exception("Flata implementar Clase Service para la Interfaz: " + interfazEmpaquetada.getCodigo());
			}
			
			
			log.info("NIVEL: "+ interfazMultiHiloParams.getNroNivel());
			log.info("HILO: "+ interfazMultiHiloParams.getNroHilo());
			log.info("Interfaz Unitaria empaquetada: "
							+ interfazEmpaquetada.getCodigo()
							+ " "
							+ interfazEmpaquetada.getDescripcion());
			log.info("Implementacion: "+ interfazEmpaquetadaImpl.toString());
			
			InterfazParams interfazParamsClone = this.obtenerInterfazParamsClone(interfazParams, interfazEmpaquetada);
			
			/* Ejecucion de Interfaz */
			interfazParamsClone.setEjecucionPaqueteInterfaz(true);
			InterfazResult interfazResult = interfazEmpaquetadaImpl.executeInterfaz(interfazParamsClone);
			result.getInterfazResultsInterfaz().add(interfazResult);
			result.getListaParamsInterfaz().add(interfazParamsClone.getQueryParams());
			
		}
		if (log.isDebugEnabled()) {
			log.debug("Fin 'executeMultiHiloResult' method");
		}
		return result;
	}
	

	/**
	 * Metodo que es invocado para finalizar la ejecucion del proceso BATCH
	 * @param params
	 * @param request
	 * @param interfazExecutionResult
	 * @param usuario
	 */
	protected void updateInterfazRegistroProcesoBatch(InterfazParams interfazParams) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateInterfazRegistroProcesoBatch' method");
		}
		Map params = interfazParams.getQueryParams();
		String codigoProcesoBatch = (String) params.get("codigoProcesoBatch");
		if (StringUtils.isBlank(codigoProcesoBatch)) return;
		
		Usuario usuario = (Usuario) params.get("usuario");
		String numeroLote = this.interfazExecutionResult.getNumeroLote();
		params.put("numeroLote", numeroLote);
		params.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);
		params.put("usuario", usuario);
		params.put("descripcionEtapaProceso", Constants.FINALIZADO);
		
		if (this.ejecucionExitosa) {
			params.put("descripcionLog", Constants.ARCHIVO_ENVIADO_GENERICO);
			params.put("codigoEstadoProceso", Constants.CODIGO_PROCESO_BATCH_OK);
		}
		else {
			params.put("descripcionLog", Constants.PROCESO_BATCH_ENVIADO_GENERICO_ERRORES);
			params.put("codigoEstadoProceso", Constants.CODIGO_PROCESO_BATCH_ERROR);
		}	
		this.procesoBatchService.updateProcesoBatchActu03(params, usuario);
	}
	
	/**
	 * Metodo que es invocado para finalizar la ejecucion del proceso BATCH, cuando se haya generado una excepcion
	 * @param interfazParams
	 * @param exception
	 */
	protected void updateInterfazRegistroProcesoBatch(InterfazParams interfazParams, Exception exception) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateInterfazRegistroProcesoBatch' method");
		}
		Map params = interfazParams.getQueryParams();
		String codigoProcesoBatch = (String) params.get("codigoProcesoBatch");
		if (StringUtils.isBlank(codigoProcesoBatch)) return;
		
		Usuario usuario = (Usuario) params.get("usuario");
		params.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);
		params.put("codigoEstadoProceso",Constants.CODIGO_PROCESO_BATCH_ERROR);
		params.put("descripcionEtapaProceso", Constants.FINALIZADO);
		log.error(exception);
		String descripcionLog = exception.getMessage();
		
		if (descripcionLog!= null && descripcionLog.length() >= 1000) {
			descripcionLog = descripcionLog.substring(1,999);
		}
		
		params.put("descripcionLog", descripcionLog);
		String tipoInterfazExecution = (String) params.get("tipoInterfazExecution");
		this.procesoBatchService.updateProcesoBatchActu03(params, usuario);
	}
	
		
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.BaseInterfazAbstractService#devuelveStatusInterfaz(biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams)
	 */
	protected List<InterfazStatus> devuelveStatusInterfaz(InterfazParams interfazParams) {
		List<InterfazStatus> listaStatus = new ArrayList<InterfazStatus>();
		String ejecucionProcesosBeforeInterfaz = this.getKeyMessage("sisicc.ejecucionProcesosBeforeInterfaz");
		String ejecucionInterfaz = this.getKeyMessage("sisicc.ejecucionInterfaz");
		String ejecucionProcesosAfterInterfaz = this.getKeyMessage("sisicc.ejecucionProcesosAfterInterfaz");
		
		InterfazStatus status = new InterfazStatus(1, interfazResult.isCompletadoProcesosBeforeInterfaz(), ejecucionProcesosBeforeInterfaz);
		listaStatus.add(status);
		status = new InterfazStatus(2,interfazResult.isCompletadoInterfaz(), ejecucionInterfaz);
		listaStatus.add(status);
		status = new InterfazStatus(4, interfazResult.isCompletadoProcesosAfterInterfaz(), ejecucionProcesosAfterInterfaz);
		listaStatus.add(status);
		return listaStatus;
	}	
	
	
	/**
	 * verifica  los archivos de la interfaz contra la tabla de control y los deja en un estado pendiente(2)
	 * al terminar la ejecucion del apquete actualiza ha enviado
	 * @param params 
	 * @param listaNombresArchivos
	 * @param listaArchivosSize
	 * @return
	 */
	private String validarInterfazByControl(Map params, String[] listaNombresArchivos, 	long[] listaArchivosSize) {
		String codigo="";
		Map criteria = new HashMap();
		Long oidControl=null;
		//List<Long> listOids= new ArrayList();
		//ojo la validacion es x lote si viniera cabecera y detalle y basta que uno falle en num lineas
		// no carga nada de ese lote
		for(int i=0;i<listaNombresArchivos.length;i++){
			oidControl=null;
			String nombreArchivoOriginal = listaNombresArchivos[i];
			String numLoteArchivo ="";
			String preNomArchivo="";
			criteria.put("codigoPais", params.get("codigoPais"));
			criteria.put("codigoSistema", params.get("codigoSistema"));			
			//si el nombre del archvio es vacio ya no es valido 
			if(StringUtils.isEmpty(nombreArchivoOriginal)) {
				//se valida si existe en control el paquete de interfaz
				return "-1";				
			}
			
			int index= nombreArchivoOriginal.indexOf(".");
			int index2 = nombreArchivoOriginal.indexOf("_");
			
			if(index != -1 && index2!=-1){//hay archivo
			  String [] split =  nombreArchivoOriginal.split("_");	
			  numLoteArchivo = split[1].substring(0, index - split[0].length()-1);
			  preNomArchivo = split[0]+"_";//ex OCR5C_									
			  criteria.put("preNomArchivo", preNomArchivo);
			  criteria.put("numLoteArchivo", numLoteArchivo);
			  //lo colocamos al params para hacer el update a cargo si queda con pendiente
			  params.put("numLoteArchivo", numLoteArchivo);
			  params.put("preNomArchivo", preNomArchivo);
			  //INICIALMENTE SE HARA UNA VALDIACION DE QUE EXISTA EN EL ARCHIVOD E CONTROL 
			  //INDEPENDIENTEMENTE DEL NUMERO D ELINEAS
			   oidControl = interfazService.getOidArchivoControl(criteria);//si existe, oidcontrol es not null
			}

			if(oidControl != null) {
				//validamos numero de linea
				log.debug("lineas "+ listaArchivosSize[i]);
				criteria.put("numLineas", listaArchivosSize[i]);
				//criteria.put("idProcesoBatch", params.get("idProcesoBatch"));//siemopre es unico en una carga
				oidControl = interfazService.getOidArchivoControl(criteria);
				criteria.remove("numLineas");
				log.debug("oidcontrol encontrado  "+ oidControl);
				if(oidControl == null) return "-1";//existe pero no concuerda numero de linea
			}
			else {
				return "-2";
			}
		}							
		return codigo;
	}
	
}