package biz.belcorp.ssicc.web.framework.base.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.HistoricoAuditoria;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatchActu;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.framework.thread.BaseHiloProceso;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;

/**
 * Clase ManageBeans Abstracta, contiene metodos comunes para el flujo de ejecucion
 * de Interfaces
 * @author cbazalar
 *
 */
public abstract class BaseProcesoAbstractAction extends MBaseSistemaAbstractJSF  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1799378475129376299L;

	//Bean donde se colocaran los criterios de Busqueda
	protected BaseProcesoForm formProceso;  
	
	//List donde se guardara el resultado de la busqueda y será mostrada en el Datatable  
	protected boolean mostrarListaBusqueda = false;
	protected boolean mostrarBotonBuscar = false;
	protected boolean mostrarBotonExecute = true;
	protected boolean mostrarBotonActualizarDatos = true;
	protected boolean mostrarPanelAdicionalProceso = false;
	
	//Atributo DataTableModel usado en el Datatable la cual contiene la lista guardada en listaBusqueda
	protected List listaBusqueda;
	protected DataTableModel datatableBusqueda; 
	
	//Bean que contendra el registro que haya sido seleccionado en el DataTable
	protected Object beanRegistroSeleccionado ; 
	
	//Map con los parametros que se le envia a la Interfaz
	protected Map<String, Object> paramfiltros;
	
	// Service de Ejecucion de Interfaces
	private InterfazExecutionService interfazExecutionService;
	private ProcesoBatchService procesoBatchService;

	protected boolean esProcesoBatch = false;
	protected boolean enEjecucion = false;

	
	protected String mensajeEnEjecucion;
	
	protected List listaProcesoBatchActual;
	protected boolean validacionPrevia = false;
	
	protected boolean mostrarPaginacionDatatableSpinner = false;
	protected int nroPaginacionDatatable = 10; 
	
	protected String mensajeConfirmacionEjecucion;
	
	/**
	 * Metodo que verifica si se ha seleccionado algun registro del Datatable
	 * @return
	 */
	protected final boolean verificarRegistroSeleccionado() {
		boolean verificar= true;
		
		try {
			if (this.beanRegistroSeleccionado == null)
				verificar = false;
		}	
		catch (Exception e) {		
			verificar = false;
		}
		if (!verificar) 
			this.addWarn("Warning: ",this.getResourceMessage("errors.select.item"));
		return verificar;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setBeforeInitAtributes()
	 */
	@Override
	protected final void setBeforeViewAtributes() throws Exception {
		super.setBeforeViewAtributes();
		this.formProceso = this.devuelveFormProceso();
		this.formProceso.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		
		this.listaBusqueda = new ArrayList();
		this.interfazExecutionService = (InterfazExecutionService) getBean("sisicc.interfazExecutionService");
		this.procesoBatchService = (ProcesoBatchService) getBean("scsicc.procesoBatchService");
		this.mostrarListaBusqueda = false;
		this.mostrarBotonBuscar = false;
		this.activarHotkeyEstandar = false;
		this.mensajeEnEjecucion = "";
		this.mensajeConfirmacionEjecucion = this.getResourceMessage("confirmDialogProceso.mensaje");
		
		if (this.mPantallaPrincipalBean != null) {
			this.mPantallaPrincipalBean.setCurrentMenu(this.parametrosPantalla.get("codigoMenu"));
		}
 	       
		/* Obteniendo valoes base a partir del MENU */
		if (this.validarParametriaMenu) {
			this.setVerificarValidacionParametriaMenu();
			if (this.verificarValidacionParametriaMenu()) {
				
				if (this.esProcesoBatch) {
					/* Seteando Criteria */
					Map<String, Object> criteria = new HashMap<String, Object>();
					criteria.put("codigoPais", this.formProceso.getCodigoPais());  
			      	criteria.put("codigoSistema", this.formProceso.getCodigoSistema());
			      	
					criteria.put("codigoProcesoBatch", this.formProceso.getCodigoProcesoBatch());
					
					/* Verificando que no se este ejecutando Proceso BATCH */
					this.verificarProcesoBatchEnEjecucion(criteria);
					
					/* Vrificando que no se este ejecutando Proceso BATCH Dependientes */
					if (!this.enEjecucion)
						this.verificarProcesoBatchEnEjecucionDependientes(criteria);
					
					/* Obtener Consulta de Procesos Batch */
					this.obtenerConsultaProcesoBatch(criteria);
				}
			}	
		}
		
		if (!this.esProcesoBatch) {
			this.mostrarBotonActualizarDatos = false;
		}
	}

	
	/**
	 * Metodo Poll que actualiza los datos de la Interfaz en la Pantalla
	 */
	public void actualizarDatos(ActionEvent actionEvent) {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'actualizarDatosInterfaz' method");
		}
		
		/* Seteando Criteria */
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("codigoPais", this.formProceso.getCodigoPais());  
      	criteria.put("codigoSistema", this.formProceso.getCodigoSistema());
		
		if (this.esProcesoBatch) {
			criteria.put("codigoProcesoBatch", this.formProceso.getCodigoProcesoBatch());
			criteria =  this.setActualizarDatos(criteria);
			
			/* Verificando que no se este ejecutando Proceso BATCH */
			this.verificarProcesoBatchEnEjecucion(criteria);
			
			/* Verificando que no se este ejecutando Proceso BATCH Dependientes */
			if (!this.enEjecucion)
				this.verificarProcesoBatchEnEjecucionDependientes(criteria);
			
			/* Obtener Consulta de Procesos Batch */
			this.obtenerConsultaProcesoBatch(criteria);
		}
			
		if (log.isWarnEnabled()) {
			log.warn("Fin 'actualizarDatosInterfaz' method");
		}
		
	}
	
	
	/**
	 * @param criteria
	 * @return
	 */
	protected Map<String, Object> setActualizarDatos(Map<String, Object> criteria) {
		return criteria;
	}
	
	/**
	 *
	 */
	public void actualizarDatosEnEjecucion() {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'actualizarDatosEnEjecucion' method");
		}
		
		/* Seteando Criteria */
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("codigoPais", this.formProceso.getCodigoPais());  
      	criteria.put("codigoSistema", this.formProceso.getCodigoSistema());
		
		if (this.esProcesoBatch) {
			criteria.put("codigoProcesoBatch", this.formProceso.getCodigoProcesoBatch());
			criteria =  this.setActualizarDatos(criteria);
			
			/* Verificando que no se este ejecutando Proceso BATCH */
			this.verificarProcesoBatchEnEjecucion(criteria);
			
			/* Obtener Consulta de Procesos Batch */
			this.obtenerConsultaProcesoBatch(criteria);
		}

		if (log.isWarnEnabled()) {
			log.warn("Fin 'actualizarDatosEnEjecucion' method");
		}
		
	}

	/**
	 * Obtiene consulta de Procesos Batch
	 * @param criteria
	 */
	public void obtenerConsultaProcesoBatch(Map<String, Object> criteria) {
		String codigoProcesoBatch = (String) criteria.get("codigoProcesoBatch");
		if (StringUtils.isBlank(codigoProcesoBatch)) return;
		ProcesoBatchService service = (ProcesoBatchService) this.getBeanService("scsicc.procesoBatchService");    		
		this.listaProcesoBatchActual = service.getProcesoBatchActuByCriteria(criteria);
		
	}
	
	
	
	/**
	 * Metodo para adicionar valores antes de la Verificacion de Validacion
	 * de Parametría del Menú
	 */
	protected void setVerificarValidacionParametriaMenu() {
		
	}
	
	/**
	 * Validando que la parametria ingresada en el MENU sea la correcta
	 * @param criteria
	 */
	private boolean verificarValidacionParametriaMenu() {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'verificarValidacionParametriaMenu' method");
		}
		
		String codigoPais = this.mPantallaPrincipalBean.getCurrentCountry().getCodigo();
		this.formProceso.setCodigoPais(codigoPais);
		if (StringUtils.isBlank(codigoPais)) {
			String error = this.getResourceMessage("interfaz.error.codigoPais");
			this.addError("Error: ", error);
			return false;
		}
		
		/* Verificando si es Proceso BATCH */
		String codigoProcesoBatch = (String)this.parametrosPantalla.get("codigoProcesoBatch");	
		this.formProceso.setCodigoProcesoBatch(codigoProcesoBatch);
		if (StringUtils.isNotBlank(codigoProcesoBatch)) 
			this.esProcesoBatch = true;
		
		if (this.esProcesoBatch) {
			String codigoSistema = (String)this.parametrosPantalla.get("codigoSistema");
			this.formProceso.setCodigoSistema(codigoSistema);
			if (StringUtils.isBlank(codigoSistema)) {
				String error = this.getResourceMessage("interfaz.error.codigoSistema");
				this.addError("Error: ", error);
				return false;
			}
		}
		
		if (log.isWarnEnabled()) {
			log.warn("Fin 'verificarValidacionParametriaMenu' method");
		}
		return true;
    }

	/**
	 * Verificando que no se este ejecutando Proceso BATCH para el caso de PAQUETE DE INTERFACES
	 * @param criteria
	 */
	public void verificarProcesoBatchEnEjecucion(Map<String, Object> criteria) {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'verificarProcesoBatchEnEjecucion' method");
		}
		this.enEjecucion = false;
		String codigoProcesoBatch = (String) criteria.get("codigoProcesoBatch");
		if (StringUtils.isBlank(codigoProcesoBatch)) return;
		
		String ejecucion = this.procesoBatchService.verificarProcesoBatchEnEjecucion(criteria);
		if (StringUtils.isNotBlank(ejecucion)) {
			this.enEjecucion = true;
			this.mensajeEnEjecucion = ejecucion;
		}
		
		if (log.isWarnEnabled()) {
			log.warn("Fin 'verificarProcesoBatchEnEjecucion' method");
		}
	}
	
	/**
	 * Verificando que no se este ejecutando Proceso BATCH Dependientes para el caso de PAQUETE DE INTERFACES
	 * @param criteria
	 */
	public void verificarProcesoBatchEnEjecucionDependientes(Map<String, Object> criteria) {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'verificarProcesoBatchEnEjecucionDependientes' method");
		}
		this.enEjecucion = false;
		String codigoProcesoBatch = (String) criteria.get("codigoProcesoBatch");
		if (StringUtils.isBlank(codigoProcesoBatch)) return;
		
		String ejecucion = this.procesoBatchService.verificarProcesoBatchEnEjecucionDependientes(criteria);
		if (StringUtils.isNotBlank(ejecucion)) {
			this.enEjecucion = true;
			this.mensajeEnEjecucion = ejecucion;	
			//this.addWarn("Warning: ", ejecucion);	
			
		}
		if (log.isWarnEnabled()) {
			log.warn("Fin 'verificarProcesoBatchEnEjecucionDependientes' method");
		}
	}

	/**
	 *  Metodo que limpia la Grilla de Busqueda
	 * de Busqueda
	 * Para las busquedas tipo AJAX
	 */
	public void limpiarFind(ActionEvent actionEvent) {
		try {	
			this.listaBusqueda = new ArrayList();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			this.setLimpiarFind();
		}
		catch(Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 *  Metodo principal que efectua la busqueda en base a los filtros seleccionados en la pantalla
	 * de Busqueda
	 * Para las busquedas tipo AJAX
	 */
	public void find(ActionEvent actionEvent) {
		this.find();
	}
	
	public void find(String mensaje) {
		this.find();
		this.addInfo("Info: ", mensaje);
	}
	
	
	/**
	 * Metodo principal que efectua la busqueda en base a los filtros seleccionados en la pantalla
	 * de Busqueda
	 * Para las busquedas tipo ACTION
	 */
	public String find() {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'find' method");
		}
		if(!this.validarFind()){
			this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
			return null;
		}
		
		try {			
			this.datatableBusqueda = null;
			this.paramfiltros = new HashMap<String, Object>();
			this.paramfiltros = BeanUtils.describe(this.formProceso);
			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			if ((this.listaBusqueda == null) ||(this.listaBusqueda.size() == 0)){
				this.addWarn("Warning: ", this.getResourceMessage("errors.datos.fuentes.busqueda"));
			}
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		if (log.isWarnEnabled()) {
			log.warn("Finish 'find' method");
		}
		return null;
	}
	
	/**
	 * Realiza acciones personalizadas de validación previa, de existir, antes de realizar Busqueda
	 * @return
	 */
	private boolean validarFind(){
		boolean validacion = true;	
		String lsMensajeError = this.setValidarFind();
		if (StringUtils.isNotBlank(lsMensajeError)) {
			validacion = false;	
			this.setMensajeAlertaDefault(lsMensajeError);
		}
		return validacion;
	}
	
	/**
	 * Devuelve Mensaje de error personalizado de validacion extra antes de realizar busqueda
	 * @return
	 */
	public String setValidarFind(){
		return "";
	}
	
	/**
	 * Aqui se debe asociar la clase FORM al Manage Beans, la cual internamente se asociara al
	 * atributo formProceso
	 * @return
	 * @throws Exception
	 */
	protected abstract BaseProcesoForm devuelveFormProceso() throws Exception;
	
	/**
	 * Hook method para la ejecucion de la Busqueda. Esta implementacion devuelve una lista con los valores
	 * respectivos de acuerdo a los filtros seleccionados. Dicho metodo es obligatorio sobreescribirlo 
	 * La busqueda por defecto es por AJAX
	 * @throws Exception
	 */
	protected List setFindAttributes() throws Exception {
		return null;
	}
	
	
	
	/**
	 * Realiza validaciones previas antes de la Ejecucion de la Interfaz
	 * @param actionEvent
	 */
	public void validarExecuteProceso(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validarExecuteProceso' method");
		}
		
		if(!this.validarProceso()){
			this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
			return ;
		}
		this.getRequestContext().execute("PF('confirmDialogGenerarProceso').show()");
		return;
	}	
	
	
	/**
	 * Ejecucion de Proceso
	 */
	public void executeProceso() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'executeProceso' method");
		}
	
		try {	
			
			/* Insertando la parametria necesaria para la ejecucion del proceso */
			this.paramfiltros = this.prepareParamsBeforeExecute(this.paramfiltros, this.formProceso);
			
			/* Ejecutando las validaciones previas antes de la Ejecución del Proceso */
			this.paramfiltros = this.procesoBatchService.executeProcesoValidacionesPrevias(this.paramfiltros);
			
			/* Insertando Registro en Tabla de PROCESO BATCH */
			if (StringUtils.isNotBlank(this.codigoProcesoBatch)) 
				this.paramfiltros = this.interfazExecutionService.insertProceBatch(this.paramfiltros);
			log.info("queryParams=" + this.paramfiltros);
		}
		catch (Exception e) {
			//this.addError("Error: ", this.obtieneMensajeErrorException(e));
			String lsMensajeError = this.obtieneMensajeErrorException(e);
			this.setMensajeAlertaDefault(lsMensajeError);
			this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
			return;
		}
		
		/* Invocando Hilo */
		if (this.esProcesoBatch) {
			BaseHiloProceso hilo = new BaseHiloProceso(this, this.paramfiltros);
			hilo.start();
			this.enEjecucion = true;
		}
		else {
			
			this.enEjecucion = true;  
			try {                                                                
				this.executeHilo(this.paramfiltros);                               
			}                                                                    
			catch (Exception e) {                                                
				this.addError("Error: ", this.obtieneMensajeErrorException(e));    
			}                                                                    
			this.enEjecucion = false;                                            
		}
		
		/* SI ES PROCESO BATCH */
		if (this.esProcesoBatch) {
			
			/* Obtener Consulta de Procesos Batch */
			try {			
				Map<String, Object> criteria = new HashMap<String, Object>();
				criteria.put("codigoPais", this.formProceso.getCodigoPais());  
		      	criteria.put("codigoSistema", this.formProceso.getCodigoSistema());
				criteria.put("codigoProcesoBatch", this.formProceso.getCodigoProcesoBatch());
				
				long timeWait = 1000;//POR DEFAULT 1Sg
				Thread.sleep(timeWait);
				
				if (StringUtils.isNotBlank(this.codigoProcesoBatch)) 
					this.obtenerConsultaProcesoBatch(criteria);
				
			}
			catch (Exception e) {
				this.addError("Error: ", this.obtieneMensajeErrorException(e));
				return;
			}
		}
	
		if (log.isWarnEnabled()) {
			log.warn("Finish 'executeInterfaz' method");
		}
		try {
			this.getExecuteForward();
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			return;
		}
	}
	
	/**
	 * @param actionEvent
	 */
	public void executeProceso(ActionEvent actionEvent) {
		this.executeProceso();
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	protected String getExecuteForward() throws Exception {
		return null;
	}
		
	
	/**
	 * Ejecuta el Proceso en un THREAD (HILO) APARTE
	 * @param params
	 * @throws Exception
	 */
	public void executeHilo(Map<String, Object> params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'executeHilo' method");
		}
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		HistoricoAuditoria historicoAuditoria = new HistoricoAuditoria();
		historicoAuditoria.setCodigoAccion(this.ACCION_EJECUTAR);
		this.grabarAuditoriaUsuarioIni(historicoAuditoria, this.codigoMenu);
		
		/* invocando al proceso */
		try {
			params.put("descripcionEtapaProceso",Constants.NUMERO_ETAPA_PROCESO_BATCH_DEFAULT);
			params = this.executeProcess(params);
			String errorProceso = this.setErrorLogicaNegocio(params);
			if (StringUtils.isNotBlank(errorProceso)) {
				throw new Exception(errorProceso);
			}
			this.afterExecuteProcess(this.formProceso);
			this.afterExecuteProcessSuccess();
			this.updateInterfazRegistroProcesoBatchOK(params, usuario); 
			if (!this.esProcesoBatch) {
				String error = "";
				try {
					error = (String)params.get("error");
				}
				catch (Exception e) {
					error = "";
				}
				if (StringUtils.isBlank(error))
					this.addInfo("Información", this.getResourceMessage("proceso.concluido"));
				else 
					this.addError("Error", error);
			}
		}
		catch (Exception e) {
			this.updateInterfazRegistroProcesoBatchError(params, usuario, e); 
			if (!this.esProcesoBatch) {
				this.addError("Error: ", this.obtieneMensajeErrorException(e));
			}
		}
				
		log.debug("Fin 'executeHilo' method");
		if (!this.esProcesoBatch) this.enEjecucion = false;
		return;		
	}
	
	
	
	/**
	 * Metodo que es invocado para finalizar la ejecucion del proceso
	 * @param params
	 * @param request
	 * @param interfazExecutionResult
	 * @param usuario
	 */
	private void updateInterfazRegistroProcesoBatchOK(Map params, Usuario usuario) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateInterfazRegistroProcesoBatch' method");
		}
		if (!this.esProcesoBatch) return;
		params.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);
		params.put("descripcionLog", Constants.ARCHIVO_ENVIADO_GENERICO);
		params.put("codigoEstadoProceso", Constants.CODIGO_PROCESO_BATCH_OK);
		params.put("descripcionEtapaProceso", Constants.FINALIZADO);
		this.procesoBatchService.updateProcesoBatchActu03(params, usuario);
	}
	
	/**
	 * Metodo que es invocado para finalizar la ejecucion del proceso, cuando se haya generado una excepcion
	 * @param params
	 * @param request
	 * @param usuario
	 * @param exception
	 */
	private void updateInterfazRegistroProcesoBatchError(Map params, Usuario usuario, Exception exception) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateInterfazRegistroProcesoBatch' method");
		}
		if (!this.esProcesoBatch) return;
		params.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);
		String codigoError = this.getCodigoErrorBatch(params);
		if (StringUtils.isNotBlank(codigoError))
			params.put("codigoEstadoProceso",codigoError);
		else 
			params.put("codigoEstadoProceso",Constants.CODIGO_PROCESO_BATCH_ERROR); 
		
		String descripcionLog = exception.getMessage();
		
		if (descripcionLog.length() >= 1000) {
			descripcionLog = descripcionLog.substring(1,999);
		}
		params.put("descripcionLog", descripcionLog);
		params.put("descripcionEtapaProceso", Constants.FINALIZADO);
		this.procesoBatchService.updateProcesoBatchActu03(params, usuario);
	}
	
	/**
	 * Metodo que es invocado para finalizar la ejecucion del proceso, cuando se haya generado una excepcion
	 * @param params
	 * @param request
	 * @param usuario
	 * @param exception
	 */
	private void updateInterfazRegistroProcesoBatchError(Map params, Usuario usuario, String mensaje) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateInterfazRegistroProcesoBatch' method");
		}
		if (!this.esProcesoBatch) return;
		params.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);
		String codigoError = this.getCodigoErrorBatch(params);
		if (StringUtils.isNotBlank(codigoError))
			params.put("codigoEstadoProceso",codigoError);
		else 
			params.put("codigoEstadoProceso",Constants.CODIGO_PROCESO_BATCH_ERROR); 
		
		String descripcionLog = mensaje;
		
		if (descripcionLog.length() >= 1000) {
			descripcionLog = descripcionLog.substring(1,999);
		}
		params.put("descripcionLog", descripcionLog);
		params.put("descripcionEtapaProceso", Constants.FINALIZADO);
		this.procesoBatchService.updateProcesoBatchActu03(params, usuario);
	}
	
	
	/**
	 * Método que revisa si existe algun error personalizado generado por la lógica del Negocio
	 * No es una exception que genera el proceso, sino alguna variable que contenga mensaje de error
	 * devuelto por el proceso respectivo.
	 * Dicho metodo puede ser sobreescrito  
	 * @param params
	 * @return
	 */
	protected String setErrorLogicaNegocio(Map params) {
		return null;
	}
	
	
	protected abstract Map<String, Object> executeProcess(Map<String, Object> params) throws Exception;
	
	/**
	 * Método que permite modifcar el estado del formulario luego de ejecutarse 
	 * el proceso sea satisfactioria o no.
	 * Dicho metodo puede ser sobreescrito  
	 * @param form
	 * @param request
	 * @param error
	 */
	public void afterExecuteProcess(BaseProcesoForm form) throws Exception {
		
	}
	
	/**
	 * Método que permite modifcar el estado del formulario en caso la ejecucion 
	 * del proceso sea satisfactioria.
	 * Dicho metodo puede ser sobreescrito  
	 */
	protected void afterExecuteProcessSuccess() {
		return;
	}
	
	
	/**
	 * Realiza acciones personalizadas de validación previa, de existir, antes de realizar la verificacion de ejecucion de la interfaz
	 * @return
	 */
	private boolean validarProceso(){
		this.validacionPrevia = true;	
		String lsMensajeError = this.setValidarProceso();
		if (StringUtils.isNotBlank(lsMensajeError)) {
			this.validacionPrevia = false;	
			this.setMensajeAlertaDefault(lsMensajeError);
		}
		return this.validacionPrevia;
	}
	
	/**
	 * Realiza acciones personalizadas de validación previa, de existir, antes de realizar la verificacion de ejecucion de la interfaz
	 * Devuelve Mensaje de error personalizado de validacion extra antes de la verificación de la ejecución del reporte 
	 * @return
	 */
	public String setValidarProceso(){
		return "";
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseAbstractJSF#setObtenerPaginaAyudaPantalla()
	 */
	protected String setObtenerPaginaAyudaPantalla() {
		return "/pages/ayuda/consultaAyudaInterfaces.xhtml";
	}

	/**
	 * Metodo que coloca Codigo de ERROR en caso el proceso no concluya satisfactoriamente
	 * Se pude sobreescribir para que devuelva otro codigo de Error.
	 * @param params
	 * @return
	 */
	protected String getCodigoErrorBatch(Map params) {
		return Constants.CODIGO_PROCESO_BATCH_ERROR; 
	}
	
	
	
	/**
	 * Proceso de Anular Proceso Batch
	 * @param actionEvent
	 */
	public void anularProcesoBath(ActionEvent actionEvent) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		
		if(log.isDebugEnabled())			
			log.debug("Ingreso anularProcesoBath");
		Map criteria = new HashMap();
    	Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		ProcesoBatchService procesoBatchService = (ProcesoBatchService) getBean("scsicc.procesoBatchService");
		if (this.listaProcesoBatchActual != null && this.listaProcesoBatchActual.size() > 0) {
			ProcesoBatchActu procesoBatchActu = (ProcesoBatchActu) this.listaProcesoBatchActual.get(0); 
			criteria.put("codigoSistema", procesoBatchActu.getCodigoSistema());
	    	criteria.put("codigoProcesoBatch", this.codigoProcesoBatch);
	    	criteria.put("numeroLote", procesoBatchActu.getNumeroLote());
			criteria.put("codigoPais", pais.getCodigo());
			
			if (this.validationSuccessfulProcesoBatch(criteria)){
			    		    	    	
		    	criteria.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);		
		    	criteria.put("descripcionLog", this.getResourceMessage("consultaBASProcesoBatchActuaSearchForm.logError"));
				criteria.put("codigoEstadoProceso", Constants.CODIGO_PROCESO_BATCH_ERROR);
							    	
				procesoBatchService.updateProcesoBatchActu(criteria, usuario);
			}
			this.actualizarDatos(actionEvent) ;
		}
	}	
	
	
	/**
	 * Método pque valida que el proceso este en ejecución
	 * @param request
	 * @param criteria
	 * @return
	 */
	private boolean validationSuccessfulProcesoBatch(Map criteria) {
		boolean isOk = true;

		ProcesoBatchService service = (ProcesoBatchService) getBean("scsicc.procesoBatchService");		
		ProcesoBatchActu procesoBatch = (ProcesoBatchActu) service.getProcesoBatchActuByCriteria(criteria).get(0);
				
		if (procesoBatch.getIndicadorEjecucion().equals(Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO))
		{
			this.addError("Error: ", this.getResourceMessage("errors.ejecucion.procesoBatchActu"));
			isOk = false;
		}

		return isOk;
	}
	
	
	/* GET - SET ATRIBUTOS */	

	/**
	 * @return the formProceso
	 */
	public BaseProcesoForm getFormProceso() {
		return formProceso;
	}


	/**
	 * @param formProceso the formProceso to set
	 */
	public void setFormProceso(BaseProcesoForm formProceso) {
		this.formProceso = formProceso;
	}


	/**
	 * @return the listaBusqueda
	 */
	public List getListaBusqueda() {
		return listaBusqueda;
	}


	/**
	 * @param listaBusqueda the listaBusqueda to set
	 */
	public void setListaBusqueda(List listaBusqueda) {
		this.listaBusqueda = listaBusqueda;
	}


	/**
	 * @return the mostrarListaBusqueda
	 */
	public boolean isMostrarListaBusqueda() {
		return mostrarListaBusqueda;
	}


	/**
	 * @param mostrarListaBusqueda the mostrarListaBusqueda to set
	 */
	public void setMostrarListaBusqueda(boolean mostrarListaBusqueda) {
		this.mostrarListaBusqueda = mostrarListaBusqueda;
	}


	/**
	 * @return the mostrarBotonBuscar
	 */
	public boolean isMostrarBotonBuscar() {
		return mostrarBotonBuscar;
	}


	/**
	 * @param mostrarBotonBuscar the mostrarBotonBuscar to set
	 */
	public void setMostrarBotonBuscar(boolean mostrarBotonBuscar) {
		this.mostrarBotonBuscar = mostrarBotonBuscar;
	}


	/**
	 * @return the datatableBusqueda
	 */
	public DataTableModel getDatatableBusqueda() {
		return datatableBusqueda;
	}


	/**
	 * @param datatableBusqueda the datatableBusqueda to set
	 */
	public void setDatatableBusqueda(DataTableModel datatableBusqueda) {
		this.datatableBusqueda = datatableBusqueda;
	}


	/**
	 * @return the paramfiltros
	 */
	public Map<String, Object> getParamfiltros() {
		return paramfiltros;
	}


	/**
	 * @param paramfiltros the paramfiltros to set
	 */
	public void setParamfiltros(Map<String, Object> paramfiltros) {
		this.paramfiltros = paramfiltros;
	}


	/**
	 * @return the esProcesoBatch
	 */
	public boolean isEsProcesoBatch() {
		return esProcesoBatch;
	}


	/**
	 * @param esProcesoBatch the esProcesoBatch to set
	 */
	public void setEsProcesoBatch(boolean esProcesoBatch) {
		this.esProcesoBatch = esProcesoBatch;
	}


	/**
	 * @return the enEjecucion
	 */
	public boolean isEnEjecucion() {
		return enEjecucion;
	}


	/**
	 * @param enEjecucion the enEjecucion to set
	 */
	public void setEnEjecucion(boolean enEjecucion) {
		this.enEjecucion = enEjecucion;
	}


	/**
	 * @return the mensajeEnEjecucion
	 */
	public String getMensajeEnEjecucion() {
		return mensajeEnEjecucion;
	}


	/**
	 * @param mensajeEnEjecucion the mensajeEnEjecucion to set
	 */
	public void setMensajeEnEjecucion(String mensajeEnEjecucion) {
		this.mensajeEnEjecucion = mensajeEnEjecucion;
	}


	/**
	 * @return the listaProcesoBatchActual
	 */
	public List getListaProcesoBatchActual() {
		return listaProcesoBatchActual;
	}


	/**
	 * @param listaProcesoBatchActual the listaProcesoBatchActual to set
	 */
	public void setListaProcesoBatchActual(List listaProcesoBatchActual) {
		this.listaProcesoBatchActual = listaProcesoBatchActual;
	}


	/**
	 * @return the validacionPrevia
	 */
	public boolean isValidacionPrevia() {
		return validacionPrevia;
	}


	/**
	 * @param validacionPrevia the validacionPrevia to set
	 */
	public void setValidacionPrevia(boolean validacionPrevia) {
		this.validacionPrevia = validacionPrevia;
	}


	/**
	 * @return the mostrarBotonExecute
	 */
	public boolean isMostrarBotonExecute() {
		return mostrarBotonExecute;
	}


	/**
	 * @param mostrarBotonExecute the mostrarBotonExecute to set
	 */
	public void setMostrarBotonExecute(boolean mostrarBotonExecute) {
		this.mostrarBotonExecute = mostrarBotonExecute;
	}


	/**
	 * @return the mostrarPanelAdicionalProceso
	 */
	public boolean isMostrarPanelAdicionalProceso() {
		return mostrarPanelAdicionalProceso;
	}


	/**
	 * @param mostrarPanelAdicionalProceso the mostrarPanelAdicionalProceso to set
	 */
	public void setMostrarPanelAdicionalProceso(boolean mostrarPanelAdicionalProceso) {
		this.mostrarPanelAdicionalProceso = mostrarPanelAdicionalProceso;
	}


	/**
	 * @return the beanRegistroSeleccionado
	 */
	public Object getBeanRegistroSeleccionado() {
		return beanRegistroSeleccionado;
	}


	/**
	 * @param beanRegistroSeleccionado the beanRegistroSeleccionado to set
	 */
	public void setBeanRegistroSeleccionado(Object beanRegistroSeleccionado) {
		this.beanRegistroSeleccionado = beanRegistroSeleccionado;
	}


	/**
	 * @return the mostrarPaginacionDatatableSpinner
	 */
	public boolean isMostrarPaginacionDatatableSpinner() {
		return mostrarPaginacionDatatableSpinner;
	}


	/**
	 * @param mostrarPaginacionDatatableSpinner the mostrarPaginacionDatatableSpinner to set
	 */
	public void setMostrarPaginacionDatatableSpinner(
			boolean mostrarPaginacionDatatableSpinner) {
		this.mostrarPaginacionDatatableSpinner = mostrarPaginacionDatatableSpinner;
	}


	/**
	 * @return the nroPaginacionDatatable
	 */
	public int getNroPaginacionDatatable() {
		return nroPaginacionDatatable;
	}


	/**
	 * @param nroPaginacionDatatable the nroPaginacionDatatable to set
	 */
	public void setNroPaginacionDatatable(int nroPaginacionDatatable) {
		this.nroPaginacionDatatable = nroPaginacionDatatable;
	}


	/**
	 * @return the mostrarBotonActualizarDatos
	 */
	public boolean isMostrarBotonActualizarDatos() {
		return mostrarBotonActualizarDatos;
	}


	/**
	 * @param mostrarBotonActualizarDatos the mostrarBotonActualizarDatos to set
	 */
	public void setMostrarBotonActualizarDatos(boolean mostrarBotonActualizarDatos) {
		this.mostrarBotonActualizarDatos = mostrarBotonActualizarDatos;
	}


	/**
	 * @return the mensajeConfirmacionEjecucion
	 */
	public String getMensajeConfirmacionEjecucion() {
		return mensajeConfirmacionEjecucion;
	}


	/**
	 * @param mensajeConfirmacionEjecucion the mensajeConfirmacionEjecucion to set
	 */
	public void setMensajeConfirmacionEjecucion(String mensajeConfirmacionEjecucion) {
		this.mensajeConfirmacionEjecucion = mensajeConfirmacionEjecucion;
	}


}
