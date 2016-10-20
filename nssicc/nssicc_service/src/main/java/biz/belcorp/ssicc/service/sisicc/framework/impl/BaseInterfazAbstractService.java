package biz.belcorp.ssicc.service.sisicc.framework.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.GenericoDAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.sisicc.model.ParametroInterfaz;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseInterfazService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.formatter.InterfazFormatServiceFacade;
import biz.belcorp.ssicc.service.sisicc.framework.formatter.InterfazFormatterService;
import biz.belcorp.ssicc.service.util.FTPClientWrapper;
import biz.belcorp.ssicc.service.util.FTPUtil;
import biz.belcorp.ssicc.service.util.FileUtil;

/* NUEVA REESTRUCTURACION NSSICC */
/**
 * Clase Service abstracta para las Interfaces SiCC. Implementa el template
 * method 'executeInterfaz' que contiene el algoritmo generico para las
 * Interfaces SiCC, tanto de entrada como de salida. Provee diversos metodos
 * para extender este comportamiento generico en las subclases.
 * 
 * Mejoras Realizadas NSSICC:
 *  - Colocacion del this
 *  - Creacion del metodo executePaquete
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 * 
 */
@Service("sisicc.baseInterfazAbstractService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public abstract class BaseInterfazAbstractService extends BaseService implements BaseInterfazService {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@Resource(name="sisicc.interfazService")
	protected InterfazService interfazService;

	@Resource(name="sisicc.interfazSiCCService")
	protected InterfazSiCCService interfazSiCCService;

	@Resource(name="sisicc.interfazSiCCDAO")
	protected InterfazSiCCDAO interfazSiCCDAO;

	@Resource(name="sisicc.historicoService")
	protected HistoricoService historicoService;

	@Resource(name="sisicc.interfazFormatServiceFacade")
	protected InterfazFormatServiceFacade interfazFormatServiceFacade;

	@Resource(name="sisicc.interfazFormatterService")
	protected InterfazFormatterService interfazFormatterService;
	
	@Resource(name="scsicc.procesoBatchService")
	protected ProcesoBatchService procesoBatchService;
	
	@Resource(name="genericoDAO")
    protected GenericoDAO genericoDAO;
	
	/* INI SA PER-SiCC-2012-0840 */
    @Resource(name="sisicc.baseInterfazMailService")
	protected BaseMailService mailService;
	/* FIN SA PER-SiCC-2012-0840 */

	protected InterfazResult interfazResult;
    protected boolean beforeTransaccionIndependiente = true;
    protected boolean afterTransaccionIndependiente = true;
    protected boolean afterTransaccionIndependienteDobleTransaccion = false;

	
	/**
	 * @return the interfazResult
	 */
	public InterfazResult getInterfazResult() {
		return interfazResult;
	}

	/**
	 * @param interfazResult the interfazResult to set
	 */
	public void setInterfazResult(InterfazResult interfazResult) {
		this.interfazResult = interfazResult;
	}
	
	

	/**
	 * Template method que define el algoritmo básico para las Interfaces de
	 * SiCC.
	 * 
	 * @param InterfazResult
	 *            con el resultado de la ejecución de la Interfaz
	 * @return resultado de la ejecución de la interfaz
	 */
	public InterfazResult executeInterfaz(InterfazParams interfazParams) throws Exception {
		log.debug("Entering 'executeInterfaz' method");
		Interfaz interfazIni = interfazParams.getInterfaz();
		interfazParams = this.initInterfazParams(interfazParams);
		
		Usuario usuario = interfazParams.getUsuario();
		Map<String, Object> params = interfazParams.getQueryParams();
		int registrosProcesados = 0;
		boolean errorInterfaz = false;
		Exception excepcionInterfaz = new Exception("");
		
		try {
			log.info("Iniciando procesamiento de Interfaz");
			
			
			/* INSERTANDO NRO DE LOTE */
			this.insertarNumeroLoteUnitaria(interfazParams);
			params = interfazParams.getQueryParams();
			
			/* Inicializacion del RESULT */
			this.interfazResult = this.initInterfazResult(interfazParams);
			

			/* PROCESO PRINCIPAL */
			Historico historicoEjecucion = interfazParams.getHistorico();
			
				
			/* EJECUCION PROCESOS PREVIOS INTERFAZ */
			try {
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
				
				historicoEjecucion.setFechaFinProcesoAnteriorInterfaz(new Timestamp(System.currentTimeMillis()));
				historicoEjecucion.setFlagErrorProcesoAnteriorInterfaz(Constants.NO);
				this.historicoService.updateHistoricoFinProcesoAnteriorInterfaz(historicoEjecucion, usuario);
			}
			catch (Exception e) {
				errorInterfaz = true;
				historicoEjecucion.setFechaFinProcesoAnteriorInterfaz(new Timestamp(System.currentTimeMillis()));
				historicoEjecucion.setFlagErrorProcesoAnteriorInterfaz(Constants.SI);
				this.historicoService.updateHistoricoFinProcesoAnteriorInterfaz(historicoEjecucion, usuario);
				excepcionInterfaz = e;
			}
			
			/* EJECUCION INTERFAZ */
			if (this.continueExecuteInterfaz(params)) {
				if (!errorInterfaz) {
					try {
						historicoEjecucion.setFechaIniInterfaz(new Timestamp(System.currentTimeMillis()));
						this.historicoService.updateHistoricoIniInterfaz(historicoEjecucion, usuario);
						
						registrosProcesados = this.procesoBatchService.processInterfazNEW(interfazParams, this);
						interfazResult.setCompletadoInterfaz(true);  // FRAMEWORK NSSICC
						
						historicoEjecucion.setFechaFinInterfaz(new Timestamp(System.currentTimeMillis()));
						historicoEjecucion.setFlagErrorInterfaz(Constants.NO);
						this.historicoService.updateHistoricoFinInterfaz(historicoEjecucion, usuario);
					}
					catch (Exception e) {
						errorInterfaz = true;
						historicoEjecucion.setFechaFinInterfaz(new Timestamp(System.currentTimeMillis()));
						historicoEjecucion.setFlagErrorInterfaz(Constants.SI);
						this.historicoService.updateHistoricoFinInterfaz(historicoEjecucion, usuario);
						excepcionInterfaz = e;
					}
				}

				/* EJECUCION PROCESOS POSTERIORES INTERFAZ */
				if (!errorInterfaz) {
					try {
						historicoEjecucion.setFechaIniProcesoPosteriorInterfaz(new Timestamp(System.currentTimeMillis()));
						this.historicoService.updateHistoricoIniProcesoPosteriorInterfaz(historicoEjecucion, usuario);
						
						if (this.afterTransaccionIndependiente) {
							if (this.afterTransaccionIndependienteDobleTransaccion) {
								this.procesoBatchService.afterNewProcessInterfaz(interfazParams, this);
								this.procesoBatchService.afterNewExecuteInterfaz(params, this);
							}
							else {
								this.procesoBatchService.afterNewTransaction(interfazParams, this);
							}
						}
						else {
							this.afterProcessInterfaz(interfazParams);
							this.afterExecuteInterfaz(params);
						}
						interfazParams.setQueryParams(params);
						interfazResult.setCompletadoProcesosAfterInterfaz(true); // FRAMEWORK NSSICC
						
						historicoEjecucion.setFechaFinProcesoPosteriorInterfaz(new Timestamp(System.currentTimeMillis()));
						historicoEjecucion.setFlagErrorProcesoPosteriorInterfaz(Constants.NO);
						this.historicoService.updateHistoricoFinProcesoPosteriorInterfaz(historicoEjecucion, usuario);
					}
					catch (Exception e) {
						errorInterfaz = true;
						historicoEjecucion.setFechaFinProcesoPosteriorInterfaz(new Timestamp(System.currentTimeMillis()));
						historicoEjecucion.setFlagErrorProcesoPosteriorInterfaz(Constants.SI);
						this.historicoService.updateHistoricoFinProcesoPosteriorInterfaz(historicoEjecucion, usuario);
						excepcionInterfaz = e;
					}
				}
				
			}
				
			
				
		} finally {
			
			/* ACTUALIZANDO DATOS FINALES */	
			log.info("Interfaz procesada, registrosProcesados="	+ registrosProcesados);
			if (!errorInterfaz) {
				this.updateInterfazResultOnSuccess(interfazResult, registrosProcesados);
				this.updateHistoricoOnSuccess(interfazParams.getHistorico(), registrosProcesados);
				this.updateInterfazRegistroProcesoBatch(interfazParams);
			}
			else {
				this.updateResultOnException(interfazResult, excepcionInterfaz);
				this.updateHistoricoOnExceptionGral(interfazParams.getHistorico(),excepcionInterfaz);
				this.updateInterfazRegistroProcesoBatch(interfazParams, excepcionInterfaz);
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
			this.sendMail(interfazParams);
			
		}
		log.info("Se termino la ejecucion de la Interfaz: " + interfazParams.getInterfaz().getCodigo());
		return this.interfazResult;
	}
	
	/* INI FRAMEWORK NSSICC */
	
	/**
	 * Ejecuta procesos previos a la Interfaz en una NUEVA TRANSACCION. 
	 * Dicho metodo en el SSICC se encuentra a nivel del ACTION se ha movido a la Capa Service en el NSSiCC
	 * @param params
	 * @return
	 * @throws Exception
	 */
	protected Map prepareParamsBeforeExecute(Map params) throws Exception {
		return params;
	}
	
		
	/**
	 * Ejecuta los procesos previos a la Interfaz en una nueva transaccion
	 * @param interfazParams
	 * @throws Exception
	 */
	public void beforeNewTransaction(InterfazParams interfazParams) throws Exception {
		Map params = interfazParams.getQueryParams();
		this.beforeExecuteInterfaz(params);
		this.beforeProcessInterfaz(interfazParams);
	}
	
	
	/**
	 * Ejecuta los procesos posteriores a la Interfaz en una nueva transaccion
	 * @param interfazParams
	 * @throws Exception
	 */
	public void afterNewTransaction(InterfazParams interfazParams) throws Exception {
		Map params = interfazParams.getQueryParams();
		this.afterProcessInterfaz(interfazParams);
		this.afterExecuteInterfaz(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.BaseInterfazService#executePaquete(biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams)
	 */
	public InterfazExecutionResult executePaquete(InterfazParams interfazParams) throws Exception {
		return null;
	}
	
	
	/**
	 * Inicializa Valores INICIALES de interfazParams
	 * @param interfazParams
	 * @return
	 */
	protected InterfazParams initInterfazParams(InterfazParams interfazParams) throws Exception {
		Map params = interfazParams.getQueryParams();
		Usuario usuario = (Usuario) params.get("usuario");
		
		String codigoPais = (String) params.get("codigoPais");
		String codigoSistema = (String) params.get("codigoSistema");
		String codigoInterfaz = (String) params.get("codigoInterfaz");
			
		/* Obteniendo INTERFACE */
		Interfaz interfazEjecucion = interfazParams.getInterfaz();
		Interfaz interfazEjecucionHilo = interfazParams.getInterfaz();
		if (!interfazParams.isEjecucionPaqueteInterfaz()) {
			InterfazPK interfazEjecucionPK = new InterfazPK(codigoPais,	codigoSistema, codigoInterfaz);
			interfazEjecucion = interfazService.getInterfaz(interfazEjecucionPK);
			interfazParams.setInterfaz(interfazEjecucion);
		}
						
		/* Obteniendo HISTORICO */
		Historico historicoEjecucion = initializeHistorico(interfazEjecucion, params);
		if (interfazEjecucionHilo != null) {
			historicoEjecucion.setNivelHilo(interfazEjecucionHilo.getNivelHilo());
			historicoEjecucion.setOrdenHilo(interfazEjecucionHilo.getOrdenHilo());
			historicoEjecucion.setOrderEjecucionHisto(interfazEjecucionHilo.getOrdenEjecucion());
		}
		
		String nombArchivoEjecutar = null;
		if (interfazEjecucion.getTipoGeneracion().equals(Constants.TIPO_GENERACION_UNITARIA)) { //REVISAR LUEGO YA QUUE PUEDE OBTENERLO DE UNA LISTA SI ES MULTILOTE O POR CONTROL
			nombArchivoEjecutar = interfazParams.getArchivoEntradaFileName();
		}
		historicoEjecucion.setNombreArchivo(nombArchivoEjecutar);
		interfazParams.setUsuario(usuario);
		interfazParams.setHistorico(historicoEjecucion);
				
		/* MultiLote */
		String indicadorMultiLote = Constants.NO;
		params.put("indicadorMultiLote", indicadorMultiLote); 
		
		
		/* Invocando Metodos de Inicializacion del Service tal como estaba en SSiCC */
		this.validateInterfazParams(interfazParams);
		this.prepareInterfazParams(interfazParams);
		
		
		/* Otros valores */
		params.put("recomendacionError", "    ");
		interfazParams.setQueryParams(params);
				
		return interfazParams;
	}
	
	/**
	 * Genera e inserta Nro de Lote en la Interfaz
	 * @param interfazParams
	 * @throws Exception
	 */
	protected final void insertarNumeroLote(InterfazParams interfazParams) throws Exception {
		Map params = interfazParams.getQueryParams();
		Historico historicoEjecucion = interfazParams.getHistorico();
		Usuario usuario = interfazParams.getUsuario();
		
		/* Obteniendo Nro de Lote SOLO SE EJECUTA EN LA INTERFAZ INICIAL */
		InterfazPK interfazEjecucionPK = (InterfazPK)params.get("interfazEjecucionPK");
		Interfaz interfazEjecucion = (Interfaz)params.get("interfazEjecucion");
		String codigoInterfaz = (String) params.get("codigoInterfaz");
		String numeroLote = (String) params.get("numeroLote");
		synchronized (this) {			
			if (StringUtils.isBlank(numeroLote)) {
				if (codigoInterfaz.equals(interfazEjecucion.getCodigo()))
					numeroLote = this.interfazService.getNumeroLote(interfazEjecucionPK);
			}			
			params.put("numeroLote", numeroLote);
		}
		
		/* Insertando en BAS_HISTO_LOTES */
		historicoEjecucion.setNumeroLote(numeroLote);
		log.debug("Insertando historico=" + historicoEjecucion);
		String es_interfaz_desfasado = (String)params.get("ES_SSICC_DESFASADO");
		if (StringUtils.isNotBlank(es_interfaz_desfasado) && Constants.SI.equals(es_interfaz_desfasado)) {
			
		}
		else {
			this.historicoService.insertHistorico(historicoEjecucion, usuario);
		}
		
		interfazParams.setHistorico(historicoEjecucion);
		log.info("Se obtuvo y se inserto en el historico el numeroLote="+ historicoEjecucion.getNumeroLote());
		
		params = this.prepareQueryParams(interfazParams);
		interfazParams.setQueryParams(params);
		
	}
	
	/**
	 * Genera e inserta Nro de Lote en la Interfaz y actualiza LOTE en PROCESO BATCH
	 * @param interfazParams
	 * @throws Exception
	 */
	private void insertarNumeroLoteUnitaria(InterfazParams interfazParams) throws Exception {
		Map params = interfazParams.getQueryParams();
		Usuario usuario = interfazParams.getUsuario();
		String tipoInterfazExecution = (String) params.get("tipoInterfazExecution");
		this.insertarNumeroLote(interfazParams);
		if (tipoInterfazExecution.equals(Constants.TIPO_GENERACION_UNITARIA))
			this.procesoBatchService.updateProcesoBatchActuLote(params, usuario);
	}
	
	/**
	 * Crea e inicializa el Historico.
	 * 
	 * @param params
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @return Historico inicializado
	 */
	protected final Historico initializeHistorico(Interfaz interfaz, Map params) {
		if (log.isDebugEnabled())
			log.debug("Entering 'initializeHistorico' method");
		Historico historico = new Historico();
		historico.setFechaInicioProceso(new Timestamp(System.currentTimeMillis()));
		historico.setCodigoPais(interfaz.getCodigoPais());
		historico.setCodigoSistema(interfaz.getCodigoSistema());
		historico.setCodigoInterfaz(interfaz.getCodigo());
		historico.setFlagError(Constants.NO);
		historico.setRegistrosProcesados(new Long(0));
		historico.setRegistrosErroneos(new Long(0));
		historico.setEstadoProceso(Constants.ESTADO_PROCESO_INTERFAZ_OK);
		historico.setDescripcionLote(interfaz.getDescripcion());
		historico.setObservaciones((String) params.get("observaciones"));
		historico.setOrdenHilo(interfaz.getOrdenHilo());
		historico.setOrderEjecucionHisto(interfaz.getOrdenEjecucion());
		historico.setNivelHilo(interfaz.getNivelHilo());
		historico.setCodigoInterfazEmpaquetada((String) params.get("codigoInterfazEmpaquetada"));
		
		/* Nro de id proceso batch */
		Long idProcesoBatch = null;
		try {
			idProcesoBatch = (Long)params.get("idProcesoBatch");
		}
		catch(Exception e) {
			idProcesoBatch = null;
		}
		historico.setIdProcesoBatch((Long)params.get("idProcesoBatch"));
		
		
		//se borra limpia el paramas en niveles, orden y ejecucion
		params.remove("ordenHilo");
		params.remove("ordenEjecucionHisto");
		params.remove("nivelHilo");
		
		if (log.isDebugEnabled())
			log.debug("historico=" + historico);
		return historico;
	}	
	

	
	/**
     * Hook method, invocado antes de la llamada a
     * 'interfazExecutionService.executeInterfaz' dentro del metodo
     * 'executeInterfaz'.
     * Dicho metodo en el SSICC se encuentra a nivel del ACTION se ha movido a la Capa Service en el NSSiCC
     * 
     * @param params
     *            parametros de la interfaz
     */
    protected void beforeExecuteInterfaz(Map params) throws Exception {
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
	 * Hook method, invocado despues de la llamada a
	 * 'interfazExecutionService.executeInterfaz' dentro del metodo
	 * 'executeInterfaz'.
	 * Dicho metodo en el SSICC se encuentra a nivel del ACTION se ha movido a la Capa Service en el NSSiCC
	 * 
	 * @param params
	 *            parametros de la interfaz
	 */
    protected void afterExecuteInterfaz(Map params) throws Exception {
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
		String isCompuesta = (String) params.get("isCompuesta");
		if (StringUtils.isBlank(codigoProcesoBatch)) return;
		if (StringUtils.isNotEmpty(isCompuesta)) return;
		
		Usuario usuario = (Usuario) params.get("usuario");
		String numeroLote = this.interfazResult.getNumeroLote();
		params.put("numeroLote", numeroLote);
		params.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);
		params.put("descripcionEtapaProceso", Constants.FINALIZADO);
		params.put("usuario", usuario);
		
		boolean ejecucionCompletada = this.interfazResult.isCompletado();
		if (ejecucionCompletada) {
			params.put("descripcionLog", Constants.ARCHIVO_ENVIADO_GENERICO);
			params.put("codigoEstadoProceso", Constants.CODIGO_PROCESO_BATCH_OK);
		}
		else {
			params.put("descripcionLog", Constants.PROCESO_BATCH_ENVIADO_GENERICO_ERRORES);
			params.put("codigoEstadoProceso", Constants.CODIGO_PROCESO_BATCH_ERROR);
		}	
		String tipoInterfazExecution = (String) params.get("tipoInterfazExecution");
		if (tipoInterfazExecution.equals(Constants.TIPO_GENERACION_UNITARIA)) {
			this.procesoBatchService.updateProcesoBatchActu03(params, usuario);
		}
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
		if (tipoInterfazExecution.equals(Constants.TIPO_GENERACION_UNITARIA)) {
			this.procesoBatchService.updateProcesoBatchActu03(params, usuario);
		}
	}
	/* FIN FRAMEWORK NSSICC */
   
	
	/**
	 * @param historico
	 * @param e
	 */
	protected final void updateHistoricoOnExceptionGral(Historico historico, Exception e) {
		historico.setFlagError(Constants.SI);
		
		historico.setEstadoProceso(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_LOGICA_NEGOCIO);

		if (e.getCause() != null) {
			historico.setDescripcionError(e.getCause()
					.toString());
		} else
			historico.setDescripcionError(e.getMessage());
		
	}

	/**
	 * @param interfazResult
	 * @param e
	 */
	protected final void updateResultOnException(InterfazResult interfazResult, Exception e) {
		interfazResult.setCompletado(false);
		interfazResult.setMensaje(e.getMessage());
		
	}

	/**
	 * Valida que los parametros sean validos antes de la ejecucion de la
	 * interfaz.
	 * 
	 * @param interfazParams
	 */
	protected final void validateInterfazParams(InterfazParams interfazParams) {
		log.info("Validando interfazParams... ");		
		if (interfazParams == null || interfazParams.getInterfaz() == null
				|| interfazParams.getHistorico() == null
				|| interfazParams.getUsuario() == null
				|| interfazParams.getQueryParams() == null)
			throw new IllegalArgumentException(
					"Alguno de los parametros necesarios para la ejecución de la Interfaz es nulo");
	}

	/**
	 * Agrega al queryParams los parametros configurados en el mantenimiento de
	 * Interfaces.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 */
	protected final void prepareInterfazParams(InterfazParams interfazParams) {
		log.info("Preparando interfazParams...");
		Map queryParams = interfazParams.getQueryParams();
		List parametros = interfazParams.getInterfaz().getParametros();
		if (parametros != null & parametros.size() > 0) {
			for (Iterator iter = parametros.iterator(); iter.hasNext();) {
				ParametroInterfaz parametro = (ParametroInterfaz) iter.next();
				queryParams.put(parametro.getNombre(), parametro.getValor());
			}
		}
		log
				.info("Se agregaron los parametros configurados para la Interfaz al queryParams");
	}

	/**
	 * Devuelve el Map de parametros del query. Se puede extender y sobrescribir
	 * este metodo en caso se requieran mas parametros.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @return Map con parametros del query
	 */
	protected Map prepareQueryParams(InterfazParams interfazParams)
			throws InterfazException {
		return interfazParams.getQueryParams();
	}

	/**
	 * Metodo ejecutado antes de 'processInterfaz'. Este método no tiene
	 * implementación, su intencion es el de ser sobrescrito en caso se requiera
	 * realizar una tarea adicional antes del procesamiento de la Interfaz.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 */
	protected void beforeProcessInterfaz(InterfazParams interfazParams)
			throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'beforeProcessInterfaz' method");
	}

	/**
	 * Metodo ejecutado despues de 'processInterfaz'. Este método no tiene
	 * implementación, su intencion es el de ser sobrescrito en caso se requiera
	 * realizar una tarea adicional despues del procesamiento de la Interfaz.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams)
			throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'afterProcessInterfaz' method");
	}
	
	
	/**
	 * @param interfazParams
	 * @throws InterfazException
	 */
	public final void afterNewProcessInterfaz(InterfazParams interfazParams) throws InterfazException {
		this.afterProcessInterfaz(interfazParams);
	}
	
	/**
	 * @param params
	 * @throws Exception
	 */
	public final void afterNewExecuteInterfaz(Map params) throws Exception {
		this.afterExecuteInterfaz(params);
	}

	/**
	 * Crea e inicializa el InterfazResult.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @return InterfazResult inicializado
	 */
	protected final InterfazResult initInterfazResult(InterfazParams interfazParams) {
		InterfazResult interfazResult = new InterfazResult(interfazParams.getNumeroLote(), interfazParams.getInterfaz());
		return interfazResult;
	}

	/**
	 * Procesa la interfaz SiCC, este metodo define el algoritmo para las
	 * interfaces de entrada o de salida.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @return numero de registros procesados
	 * @throws Exception TODO
	 */
	protected abstract int processInterfaz(InterfazParams interfazParams) throws Exception;

	
	public int processInterfazNEW(InterfazParams interfazParams) throws Exception {
		return this.processInterfaz(interfazParams);
	}
	
	/**
	 * Devuelve el nombre del archivo de log.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 */
	protected String getLogFileName(InterfazParams interfazParams) {
		if (log.isDebugEnabled())
			log.debug("Entering 'getLogFileName' method");
		String logFileName;
		String numeroLote;
		Interfaz interfaz = interfazParams.getInterfaz();
		numeroLote = interfazParams.getNumeroLote();

		// Tipo de nombre fijo
		if (interfaz.getTipoNombreArchivo().equalsIgnoreCase(
				Constants.ARCHIVO_FIJO)) {
			logFileName = interfaz.getNombreArchivoEntradaSalida() + numeroLote
					+ Constants.DELIMITADOR_EXTENSION_ARCHIVO
					+ interfaz.getExtensionLogErroresDescripcion();
		}
		// Tipo de nombre variable
		else {
			String nombreArchivoSalida = interfaz
					.getNombreArchivoEntradaSalida();
			if (StringUtils.isEmpty(nombreArchivoSalida)
					|| StringUtils.isEmpty(nombreArchivoSalida)) {
				nombreArchivoSalida = interfaz.getCodigo() + "_";
			}
			logFileName = nombreArchivoSalida + numeroLote
					+ Constants.DELIMITADOR_EXTENSION_ARCHIVO
					+ interfaz.getExtensionLogErroresDescripcion();
		}
		if (log.isDebugEnabled()) {
			log.debug("logFileName=" + logFileName);
		}
		return logFileName;
	}

	/**
	 * Llamado dentro del catch de 'executeInterfaz' luego de
	 * 'exceptionInterfaz', guarda en el archivo de errores los detalles de la
	 * excepcion.
	 * 
	 * @param interfazParams
	 *            parámetros de la interfaz
	 */
	protected final void saveLog(InterfazParams interfazParams) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'saveLog' method");
		try {
			Interfaz interfaz = interfazParams.getInterfaz();

			// Si la configuracion indica que no debe generar Log
			if (interfaz.getFlagLogErrores().trim().equalsIgnoreCase(
					Constants.NO)) {
				log.info("La interfaz esta configurada para no generar log");
				return;
			}

			StringBuffer logBuffer = getLogBuffer(interfazParams);

			if (StringUtils.isNotEmpty(logBuffer.toString())) {
				log.debug("Generando el archivo de log");
				log.debug(logBuffer.toString());
				String logFileName = getLogFileName(interfazParams);
				if (interfaz.getFlagEnvioArchivo().trim().equalsIgnoreCase(Constants.ENVIO_FTP)) { // Envio por FTP
					log.debug("Grabando el archivo de log por FTP");
					FTPUtil ftpUtil = new FTPUtil();
					ftpUtil.loguearFTP(interfaz);
					ftpUtil.generarArchivo(interfaz.getDirectorioLog(),logFileName, logBuffer);
					ftpUtil.cerrarFTP();
				} 
				/* INI SA PER-SiCC-2012-0840 */
				else if(interfaz.getFlagEnvioArchivo().trim().equalsIgnoreCase(Constants.ENVIO_SFTP)){ // Envio por Red para el caso SFTP
					log.debug("Grabando el archivo de log por Red para el caso SFTP");
					String logPath = FileUtil.formatDirectory(interfaz.getDirectorioLog())+ logFileName;
					File logFile = new File(logPath);
					log.debug("Copiando a: " + logFile.getAbsolutePath());
					FileUtils.writeStringToFile(logFile, logBuffer.toString(),null);
				} 
				/* FIN SA PER-SiCC-2012-0840 */
				else if(interfaz.getFlagEnvioArchivo().trim().equalsIgnoreCase(Constants.ENVIO_RED)){ // Envio por Red
					log.debug("Grabando el archivo de log por Red");
					String logPath = FileUtil.formatDirectory(interfaz.getDirectorioLog())+ logFileName;
					File logFile = new File(logPath);
					log.debug("Copiando a: " + logFile.getAbsolutePath());
					FileUtils.writeStringToFile(logFile, logBuffer.toString(),null);
				}else if(interfaz.getFlagEnvioArchivo().trim().equalsIgnoreCase(Constants.ENVIO_MIXTO)){ // Envio Mixto
					if(interfaz.getFlagDirectorioLog().trim().equalsIgnoreCase(Constants.ENVIO_FTP)){
						log.debug("Grabando el archivo de log por FTP");
						FTPUtil ftpUtil = new FTPUtil();
						ftpUtil.loguearFTP(interfaz);
						ftpUtil.generarArchivo(interfaz.getDirectorioLog(),logFileName, logBuffer);
						ftpUtil.cerrarFTP();
					}else if(interfaz.getFlagDirectorioLog().trim().equalsIgnoreCase(Constants.ENVIO_RED)){
						log.debug("Grabando el archivo de log por Red");
						String logPath = FileUtil.formatDirectory(interfaz.getDirectorioLog())+ logFileName;
						File logFile = new File(logPath);
						log.debug("Copiando a: " + logFile.getAbsolutePath());
						FileUtils.writeStringToFile(logFile, logBuffer.toString(),null);
					}
				}
				
				log.info("Se genero el archivo de log");
			} else {
				log.info("El log esta vacio, no se generara el archivo de log");
			}
		} catch (Exception e) {
			log.error("Ocurrio un error al guardar el log: " + e.getMessage());
			throw new Exception(e);
		}
	}

	/**
	 * Actualiza el InterfazResult, invocado luego de la ejecucion correcta de
	 * la Interfaz.
	 * 
	 * @param interfazResult
	 *            a actualizar
	 * @param registrosProcesados
	 *            número de registros procesados
	 */
	protected void updateInterfazResultOnSuccess(InterfazResult interfazResult,
			int registrosProcesados) {
		if (log.isDebugEnabled())
			log.debug("Entering 'updateInterfazResultOnSuccess' method");
		interfazResult.setCompletado(true);
		interfazResult.setMensaje(Constants.INTERFAZSICC_ARCHIVO_ENVIADOGENERICO);
		interfazResult.setRegistrosProcesados(registrosProcesados);
	}

	/**
	 * Actualiza el InterfazResult, invocado luego de producirse una excepcion.
	 * 
	 * @param interfazResult
	 *            a actualizar
	 * @param interfazException
	 */
    protected void updateInterfazResultOnException(InterfazResult interfazResult,
			InterfazException interfazException) {
		if (log.isDebugEnabled())
			log.debug("Entering 'updateInterfazResult' method");
		interfazResult.setCompletado(false);
		interfazResult.setMensaje(interfazException.getMessage());
		interfazResult.setRegistrosProcesados(0);
	}

	/**
	 * Actualiza el Historico, invocado luego de la ejecucion correcta de la
	 * Interfaz.
	 * 
	 * @param interfazResult
	 *            a actualizar
	 * @param registrosProcesados
	 *            número de registros procesados
	 */
	protected final void updateHistoricoOnSuccess(Historico historico,
			int registrosProcesados) {
		if (log.isDebugEnabled())
			log.debug("Entering 'updateHistoricoOnSuccess' method");
		historico.setFlagError(Constants.NO);
		historico.setRegistrosProcesados(new Long(registrosProcesados));
		historico.setRegistrosErroneos(Constants.REGISTROS_ERRONEOS_ZERO);
		historico.setEstadoProceso(Constants.ESTADO_PROCESO_INTERFAZ_OK);
	}

	/**
	 * Actualiza el Historico, invocado luego de producirse una excepcion.
	 * 
	 * @param interfazResult
	 *            a actualizar
	 * @param interfazException
	 */
	protected final void updateHistoricoOnException(Historico historico,
			InterfazException interfazException) {
		if (log.isDebugEnabled())
			log.debug("Entering 'updateHistoricoOnException' method");
		historico.setFlagError(Constants.SI);
		historico.setRegistrosProcesados(Constants.REGISTROS_ERRONEOS_ZERO);
		historico.setRegistrosErroneos(new Long(interfazException
				.getRegistrosProcesados()));

		if (StringUtils.isNotEmpty(interfazException.getCodigoError()))
			historico.setEstadoProceso(interfazException.getCodigoError());
		else
			historico
					.setEstadoProceso(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_LOGICA_NEGOCIO);

		if (interfazException.getCause() != null) {
			historico.setDescripcionError(interfazException.getCause()
					.toString());
		} else
			historico.setDescripcionError(interfazException.getMessage());
	}

	/**
	 * Sobrescribir en caso se quiera modificar el InterfazParams o el
	 * InterfazResult antes de guardar la Interfaz, el Log y el Historico y
	 * retornar el InterfazResult.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @param interfazResult
	 *            resultado de la interfaz
	 */
	protected void onFinally(InterfazParams interfazParams,
			InterfazResult interfazResult) {
	}

	/**
	 * Guarda la informacion del Historico en base de datos.
	 * Se guarda en una transaccion aparte
	 * 
	 * @param historico
	 *            Histórico a guardar
	 */
	protected final void saveHistorico(InterfazParams interfazParams) {
		if (log.isDebugEnabled())
			log.debug("Entering 'saveHistorico' method");
		try {
			Historico historico = interfazParams.getHistorico();
			historico.setFechaFinProceso(new Timestamp(System
					.currentTimeMillis()));
			historico.setHistoricoFileName(interfazParams.getHistoricoFileName());
			historico.setDescripcionLote(interfazParams.getInterfaz().getDescripcion());
			log.debug(historico);
			this.historicoService.updateHistorico(historico, interfazParams
					.getUsuario());
			log.info("Se actualizo el historico en la base de datos");
		} catch (Exception e) {
			log.error("Error al grabar el Historico.");
			log.error(e.getMessage());
		}
	}

	/**
	 * Guarda la informacion del correlativo en Interfaz en base de datos.
	 * 
	 * @param InterfazParams
	 *            Histórico a guardar
	 */
	protected final void saveInterfaz(InterfazParams interfazParams) {
		if (log.isDebugEnabled())
			log.debug("Entering 'saveInterfaz' method");
		Usuario usuario = interfazParams.getUsuario();
		Interfaz interfaz = interfazParams.getInterfaz();

		// FIXME PROBLEMA DE CONCURRENCIA AQUI
		Long correlativo = interfaz.getNumeroEjecucion();
		if (correlativo == null)
			interfaz.setNumeroEjecucion(new Long(1));
		else
			interfaz.setNumeroEjecucion(new Long(correlativo.longValue() + 1));
		try {
			this.interfazService.updateNumeroEjecucionInterfaz(interfaz,usuario);
			log.info("Se actualizo el numero de ejecucion de la interfaz en la base de datos");
		} catch (Exception e) {
			log.error("Error al guardar el numero de ejecucion de la interfaz.");
			log.error(e.getMessage());
		}

	}

	/**
	 * Genera el contenido del Log de Errores. Se puede sobrescribir para
	 * personalizar los mensajes.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @return StringBuffer con el mensaje a ser grabado en el log
	 */
	protected final StringBuffer getLogBuffer(InterfazParams interfazParams) {
		if (log.isDebugEnabled())
			log.debug("Entering 'getLogBuffer' method");

		String separator = System.getProperty("line.separator");
		StringBuffer buffer = new StringBuffer();

		// Incluyo en el log los detalles de la excepcion
		if (interfazParams.getInterfazException() != null) {
			InterfazException interfazException = interfazParams.getInterfazException();
			if (StringUtils.isNotBlank(interfazException.getMessage())) {
				buffer.append("Excepcion: " + interfazException.getMessage() + separator);
			}
			if (interfazException.getCause() != null) {
				buffer.append("Causa: " + separator);
				buffer.append(interfazException.getCause().toString() + separator);
			}
		}

		// Incluyo el log de errores
		if (StringUtils.isNotBlank(interfazParams.getLog())) {
			buffer.append("Log de errores:" + separator);
			buffer.append(interfazParams.getLog());
		}
		return buffer;
	}

	/**
	 * Copia el archivo temporal en el directorio historico por Red.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @param tempFile
	 *            archivo temporal a copiar
	 */
	protected final void copyTempToHistoricoRed(InterfazParams interfazParams,
			File tempFile) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'copyTempToHistoricoRed' method");
		try {
			String nombreArchivoHistorico = this.setObtenerNombreGrabarArchivoHistoricoRed(interfazParams);
			File histoFile = new File(nombreArchivoHistorico);
			String tipoNombreArchivo = interfazParams.getInterfaz().getTipoNombreArchivo();
			if  (tipoNombreArchivo.equals(Constants.ARCHIVO_FIJO)) {
			
				File histoFijoFile = new File(interfazParams.getHistoricoPathNombreFijo());
				FileUtils.copyFile(tempFile, histoFijoFile);
			}
			FileUtils.copyFile(tempFile, histoFile);
			log.info("Archivo copiado a la carpeta Historico por Red: "	+ histoFile.getAbsolutePath());
		} catch (Exception e) {
			log.error(e);
			InterfazException ie = new InterfazException(Constants.INTERFAZSICC_ERROR_ENVIAR_ARCHIVO_FTP_HISTO);
			ie.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_HIST);
			throw ie;
		}
	}
	
	/**
	 * Metodo que se puede sobreescribir para cambiar el nombre del Archivo con que se grabaria en la Ruta Historica
	 * @param interfazParams
	 * @return
	 */
	protected String setObtenerNombreGrabarArchivoHistoricoRed(InterfazParams interfazParams) {
		return interfazParams.getHistoricoPath();
	}

	/**
	 * Copia el archivo temporal en el directorio historico por FTP.
	 * 
	 * @param ftp
	 *            conexion al FTP
	 * @param tempFile
	 *            nombre de archivo a copiar
	 * @param interfaz
	 *            interfaz con los datos a buscar
	 */
	// TODO Refactoring a los parametros
	protected final void copyTempToHistoricoFTP(FTPUtil ftp,
			InterfazParams interfazParams, File tempFile,
			String historicoFileName) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'copyTempToHistoricoFTP' method");
		Interfaz interfaz = interfazParams.getInterfaz();
		try {
			ftp.copiarArchivoRedaFTP(tempFile, interfaz
					.getDirectorioHistorico(), historicoFileName);
			log.info("Archivo copiado a la carpeta Historico por FTP: "
					+ historicoFileName);
		} catch (Exception e) {
			InterfazException ie = new InterfazException(
					Constants.INTERFAZSICC_ERROR_ENVIAR_ARCHIVO_FTP_HISTO);
			ie.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_HIST);
			throw ie;
		}
	}

	/**
	 * @param interfazParams
	 * @return
	 */
	protected final FTPClientWrapper getFTPClientWrapper(
			InterfazParams interfazParams) {
		Interfaz interfaz = interfazParams.getInterfaz();
		return new FTPClientWrapper(interfaz.getServidorFtp(), interfaz
				.getPuertoFtpInt(), interfaz.getUsuarioFtp(), interfaz
				.getPasswordFtp());
	}


       /**
	 * Envia mail del resultado de la Ejecucion de la Interfaz
	 * 
	 * @param historico
	 *            Histrico a guardar
	 */
	private void sendMail(InterfazParams interfazParams) {
		Interfaz interfaz = interfazParams.getInterfaz();
		
		if(Constants.TIPO_GENERACION_UNITARIA.equals(interfaz.getTipoGeneracion())) {
			if (log.isDebugEnabled())
				log.debug("Entering 'sendMail' method");
			
			try {
				log.debug("Entering 'sendMail' Indicador EnviarCorreo = " + interfaz.getIndicadorEnviarCorreo());
				
				if(Constants.SI.equals(interfaz.getIndicadorEnviarCorreo())) {
					Map parameterMap = new HashMap();
					parameterMap.put("interfaz", interfaz);
					parameterMap.put("historico", interfazParams.getHistorico());
					
					Map queryParams = new HashMap();
					queryParams.put("parameterMap", parameterMap);
				
					MailParams mailParams = new MailParams();
					mailParams.setQueryParams(queryParams);
					mailParams.setUsuario(interfazParams.getUsuario());
					mailParams.setPais(interfaz.getPais());
				
					mailService.enviarMail(mailParams);
				
					log.info("Se envio un mail de la interfaz Ejecutada :" + interfaz.getCodigo());
				}	
			} catch (Exception e) {
				log.error("Error al grabar el Historico.");
				log.error(e.getMessage());
			}
		}	
	}

	/* INI FRAMEWORK NSSICC PRUEBA TRANSACCION */
	public void executePruebaTransaccion() {
		
	}
	/* INI FRAMEWORK NSSICC PRUEBA TRANSACCION */
    	
}