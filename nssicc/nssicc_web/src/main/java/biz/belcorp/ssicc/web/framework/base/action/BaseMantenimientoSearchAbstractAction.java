package biz.belcorp.ssicc.web.framework.base.action;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.sisicc.model.HistoricoAuditoria;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;

/**
 * Clase ManageBeans Abstracta, contiene metodos comunes para el flujo de ejecucion
 * de Búsqueda de Mantenimientos
 * @author cbazalar
 *
 */
public abstract class BaseMantenimientoSearchAbstractAction extends MBaseSistemaAbstractJSF  {

	private static final long serialVersionUID = -7095145353945539636L;
	
	//Bean donde se colocaran los criterios de Busqueda
	protected BaseSearchForm formBusqueda;  
	
	//List donde se guardara el resultado de la busqueda y será mostrada en el Datatable
	protected List listaBusqueda; 
	protected boolean mostrarListaBusqueda = true;
		
	//Atributo DataTableModel usado en el Datatable la cual contiene la lista guardada en listaBusqueda
	protected DataTableModel datatableBusqueda; 
		
	//Bean que contendra el registro que haya sido seleccionado en el DataTable
	protected Object beanRegistroSeleccionado ; 
	protected Object[] beanListaSeleccionado;
	
	//Verifica que el registro haya sido seleccionado
	//Para ello en el xhtml deben incluirlo con inputHidden
	protected boolean seleccionoRegistro= false;
	
	/* Atributos para mostrar / ocultar los botones estandares de la Pantalla de Busqueda */
	protected boolean mostrarBotonNuevo = this.verificarMostrarBotonNuevo();
	protected boolean mostrarBotonModificar = this.verificarMostrarBotonModificar();
	protected boolean mostrarBotonConsultar = this.verificarMostrarBotonConsultar();
	protected boolean mostrarBotonEliminar = this.verificarMostrarBotonEliminar();
	protected boolean mostrarBotonSave = true;
	protected boolean mostrarBotonSalir = true;
	protected boolean mostrarBotonBuscar = true;
	protected boolean mostrarCriteriosBusqueda = true;
	
	protected boolean invocarFindLuegoGrabar = true;
	protected boolean editableMantenimiento = true;
	protected boolean salirGrabarPantallaPadre = true;
	
	protected boolean esSeleccionMultiple = false;
	
	protected boolean mostrarPaginacionDatatableSpinner = false;
	protected int nroPaginacionDatatable = 10; 
	protected boolean mostrarErrorNoExistenRegistroBusqueda = true;
	
	//Bean donde se colocaran los datos a mostrar en la pantalla de mantenimiento
	protected BaseEditForm formMantenimiento;
	protected String nombreVentanaPopupMantenimiento = "dialogMantenimientoForm";
	protected final String nombreVentanaPopupMantenimientoDefault = "dialogMantenimientoForm";
	
	
	/**
	 *  Metodo que verifica si se ha seleccionado algun registro del Datatable (Selección Multiple)
	 * @return
	 */
	protected boolean verificarRegistroSeleccionadoMultiple() {
        boolean verificar= true;
		
		/* Selección Simple */
		if (this.esSeleccionMultiple) {
			try {
				if (this.beanListaSeleccionado == null) 
					verificar = false;
				else {
					if (this.beanListaSeleccionado.length <= 0) 
						verificar = false;
				} 
				
			}
			catch (Exception e) {
				verificar = false;
			}
			if (!verificar) 
				this.addWarn("Warning: ",this.getResourceMessage("errors.select.item"));
			else {
				this.mPantallaPrincipalBean.setCriteriosBusqueda(this.formBusqueda);			
				this.mPantallaPrincipalBean.setManageBeanPadre(this);
			}	
		
		}
		return verificar;
	}
	
	/**
	 * Metodo que verifica si se ha seleccionado algun registro del Datatable
	 * @return
	 */
	protected boolean verificarRegistroSeleccionado() {
		boolean verificar= true;
		
		/* Selección Simple */
		if (!this.esSeleccionMultiple) {
	        try {
				if (this.beanRegistroSeleccionado == null)
					verificar = false;
			}	
			catch (Exception e) {		
				verificar = false;
			}
			if (!verificar) 
				this.addWarn("Warning: ",this.getResourceMessage("errors.select.item"));
			else {
				this.mPantallaPrincipalBean.setCriteriosBusqueda(this.formBusqueda);			
				this.mPantallaPrincipalBean.setManageBeanPadre(this);
			}		
			this.seleccionoRegistro = verificar;
		}
		
		else { /* Selección Multiple */
			try {
				if (this.beanListaSeleccionado == null) 
					verificar = false;
				else {
					if (this.beanListaSeleccionado.length <= 0) 
						verificar = false;
				} 
				
			}
			catch (Exception e) {
				verificar = false;
			}
			if (!verificar) 
				this.addWarn("Warning: ",this.getResourceMessage("errors.select.item"));
			else {
				if (this.beanListaSeleccionado.length > 1 ) {
					verificar = false;
				}
				if (!verificar) 
					this.addWarn("Warning: ",this.getResourceMessage("errors.select.item.masDeUno"));
				else {
					this.mPantallaPrincipalBean.setCriteriosBusqueda(this.formBusqueda);			
					this.mPantallaPrincipalBean.setManageBeanPadre(this);
				}
			}	
			
			if (verificar) {
				this.beanRegistroSeleccionado = this.beanListaSeleccionado[0];
			}
			
		}
		return verificar;
	}
	
	
	/**
	 * Metodo que verifica que haya sido seleccionado algun registro del Datatable de Busqueda
	 * @param actionEvent
	 */
	public void verificarRegistro(ActionEvent actionEvent) {
		this.seleccionoRegistro= true;
		
		/* Selección Simple */
		if (!this.esSeleccionMultiple) {
			try {
				if (this.beanRegistroSeleccionado == null)
					seleccionoRegistro = false;
			}	
			catch (Exception e) {		
				seleccionoRegistro = false;
			}
			if (!seleccionoRegistro) 
				this.addWarn("Warning: ", this.getResourceMessage("errors.select.item"));	
			else {
				this.mPantallaPrincipalBean.setCriteriosBusqueda(this.formBusqueda);
			}		
		}
		else {
			try {
				if (this.beanListaSeleccionado == null)
					seleccionoRegistro = false;
			}	
			catch (Exception e) {		
				seleccionoRegistro = false;
			}
			if (!seleccionoRegistro) 
				this.addWarn("Warning: ", this.getResourceMessage("errors.select.item"));	
			else {
				if (this.beanListaSeleccionado.length > 1 ) {
					seleccionoRegistro = false;
				}
				if (!seleccionoRegistro) 
					this.addWarn("Warning: ",this.getResourceMessage("errors.select.item.masDeUno"));
				else {
					this.mPantallaPrincipalBean.setCriteriosBusqueda(this.formBusqueda);	
				}
			}	
			
			if (seleccionoRegistro) {
				this.beanRegistroSeleccionado = this.beanListaSeleccionado[0];
			}
			
		}
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setBeforeInitAtributes()
	 */
	@Override
	protected final void setBeforeViewAtributes() throws Exception {
		super.setBeforeViewAtributes();
		log.debug("Entering 'setBeforeViewAtributes' ");
		this.accion = this.ACCION_BUSCAR;
		this.mPantallaPrincipalBean.setCurrentMenu(this.parametrosPantalla.get("codigoMenu"));
		this.formBusqueda = this.devuelveFormBusqueda();
		if (this.formBusqueda != null) {
			this.formBusqueda.setAnyoPeriodo(this.mPantallaPrincipalBean.getAnyoActualperiodo());
			this.formBusqueda.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		}
		
		this.listaBusqueda = new ArrayList();
		//this.salirGrabarPantallaPadre = this.mPantallaPrincipalBean.isSalirGrabarPantallaPadre();
	}
	
	
	/**
	 *  Metodo que limpia la Grilla de Busqueda
	 * de Busqueda
	 * Para las busquedas tipo AJAX
	 */
	public void limpiarFind(ActionEvent actionEvent) {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'limpiarFind' method");
		}
		try {	
			this.listaBusqueda = new ArrayList();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			this.setLimpiarFind();
		}
		catch(Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
		if (log.isWarnEnabled()) {
			log.warn("Finish 'limpiarFind' method");
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
	
	public void find(String mensaje) 
	{
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
		this.accion = this.ACCION_BUSCAR;
		
		this.datatableBusqueda = null;
		this.listaBusqueda = new ArrayList();
		
		if(!this.validarFind()){
			this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
			return null;
		}
		
		try {			
			
			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			if (this.mostrarErrorNoExistenRegistroBusqueda) {
				if ((this.listaBusqueda == null) ||(this.listaBusqueda.size() == 0)){
					this.addWarn("Warning: ", this.getResourceMessage("errors.datos.fuentes.busqueda"));
				}
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
	 * Metodo principal que ejecuta la eliminacion de un Registro seleccionado en la Búsqueda del 
	 * mantenimiento
	 * @return
	 */
	public void delete(ActionEvent actionEvent) {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'delete' method");
		}
		
		this.accion = this.ACCION_ELIMINAR;
		
		if (!this.verificarRegistroSeleccionado()) return;
		
		HistoricoAuditoria historicoAuditoria = new HistoricoAuditoria();
		historicoAuditoria.setCodigoAccion(this.ACCION_ELIMINAR);
		
		try{
			
			this.grabarAuditoriaUsuarioIni(historicoAuditoria, this.codigoMenu);
			String mensajeGrabarKey = new String();
			
			if (this.setDeleteAttributes()) {
				mensajeGrabarKey = this.devuelveMensajeKeyEliminarOK(); 
				this.addInfo("Info: ", this.getResourceMessage(mensajeGrabarKey));
			}
			this.mostrarErrorNoExistenRegistroBusqueda = false;
			this.setDeleteFindBeforeAttributes();
			this.find(actionEvent);	
			this.setDeleteFindAfterAttributes();
			this.mostrarErrorNoExistenRegistroBusqueda = true;
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			return;
		}
		
		if (log.isWarnEnabled()) {
			log.warn("Finish 'delete' method");
		}
		
	}
	
	/**
	 * Metodo que va a la Pagina de Mantenimiento para el ingreso de un nuevo Registro
	 * Metodo tipo AJAX
	 * @return
	 */
	public void add(ActionEvent actionEvent) {
		if (log.isWarnEnabled()) { 
			log.warn("Entering 'add' method");
		}
				
		this.accion = this.ACCION_NUEVO;
		this.accionFuncional = this.ACCION_NUEVO;
		HistoricoAuditoria historicoAuditoria = new HistoricoAuditoria();
		historicoAuditoria.setCodigoAccion(this.accion);
		
		/* Redireccionando a la pagina respectiva */
		try{			
			this.grabarAuditoriaUsuarioIni(historicoAuditoria, this.codigoMenu);
			
			this.editableMantenimiento = true;
			this.formMantenimiento = this.setObtenerRegistroAttributes();
			this.formMantenimiento.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
			
			this.setAddAttributes(); 
			if (this.mostrarMantenimientoEnPopup) {
				String nombreVentana = this.nombreVentanaPopupMantenimiento;
				String ventana = "PF('" + nombreVentana + "').show()";
				this.getRequestContext().execute(ventana);
			}
			else
				this.redireccionarPagina(this.getPaginaMantenimiento());
		}catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));		
			return;
		}
	}

	/**
	 * Metodo que va a la Pagina de Mantenimiento para la edicion del Registro seleccionado
	 * @return
	 */
	public void edit() {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'edit' method");
		}
		
		this.accion = this.ACCION_MODIFICAR;
		this.accionFuncional = this.ACCION_MODIFICAR;
		
		if (!this.verificarRegistroSeleccionado())
			return;
		
		HistoricoAuditoria historicoAuditoria = new HistoricoAuditoria();
		historicoAuditoria.setCodigoAccion(this.accion);
		
		/* Redireccionando a la pagina respectiva */
		try{
			this.grabarAuditoriaUsuarioIni(historicoAuditoria, this.codigoMenu);
			
			this.editableMantenimiento = true;
			this.formMantenimiento = this.setObtenerRegistroAttributes();
			this.formMantenimiento.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
			this.setEditAttributes(); 
			
			
			if (this.mostrarMantenimientoEnPopup) {
				String nombreVentana = this.nombreVentanaPopupMantenimiento;
				String ventana = "PF('" + nombreVentana + "').show()";
				this.getRequestContext().execute(ventana);
			}
			else
				this.redireccionarPagina(this.getPaginaMantenimiento());
		}	catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			return;
		}
		return;
	}
	
	
	/**
	 * Metodo que va a la Pagina de Mantenimiento para la edicion del Registro seleccionado
	 * @return
	 */
	public void edit(ActionEvent actionEvent) {
		this.edit();
		return;
	}

	
	/**
	 * Metodo que va a la Pagina de Mantenimiento para la consulta del Registro seleccionado
	 * @return
	 */
	public void consultar(ActionEvent actionEvent) {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'consultar' method");
		}
		
		this.accion = this.ACCION_CONSULTAR;
		
		if (!this.verificarRegistroSeleccionado())
			return;
		
		HistoricoAuditoria historicoAuditoria = new HistoricoAuditoria();
		historicoAuditoria.setCodigoAccion(this.ACCION_CONSULTAR);

		/* Redireccionando a la pagina respectiva */
		try {
			
			this.grabarAuditoriaUsuarioIni(historicoAuditoria, this.codigoMenu);
			
			this.editableMantenimiento = false;
			this.formMantenimiento = this.setObtenerRegistroAttributes();
			this.formMantenimiento.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
			this.setConsultarAttributes(); 
			this.mostrarBotonSave = false;
			
			if (this.mostrarMantenimientoEnPopup) {
				String nombreVentana = this.nombreVentanaPopupMantenimiento;
				String ventana = "PF('" + nombreVentana + "').show()";
				this.getRequestContext().execute(ventana);
			}
			else
				this.redireccionarPagina(this.getPaginaMantenimiento());
			
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			return;
		}
		return;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setEjecutarDBClick(java.lang.String)
	 */
	protected void setEjecutarDBClick(String accion) {
		if (accion.equals("EDIT")) {
			this.edit();
		}
	}
	
	/**
	 * Metodo Salvar
	 * @return
	 */
	public void save(ActionEvent actionEvent)  {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'save' method");
		}
		String mensajeGrabarKey = new String();
		HistoricoAuditoria historicoAuditoria = new HistoricoAuditoria();
		historicoAuditoria.setCodigoAccion((this.accionFuncional != null)?this.accionFuncional:this.ACCION_GRABAR);
		boolean error=true;
		
		try{			
			this.grabarAuditoriaUsuarioIni(historicoAuditoria, this.codigoMenu);			
			
			if (this.setSaveAttributes()) {
				mensajeGrabarKey = this.devuelveMensajeKeySaveOK(); 
				String mensaje = this.getResourceMessage(mensajeGrabarKey);	
				error=false;
				
				
				if (this.salirGrabarPantallaPadre) {
					if (this.invocarFindLuegoGrabar) {
						this.setSaveFindBeforeAttributes();
						this.find();
						this.setSaveFindAfterAttributes();
					}
					this.accion = this.ACCION_BUSCAR;
					if (this.mostrarMantenimientoEnPopup) {
						String ventana = "PF('dialogMantenimientoForm').hide()";
						this.getRequestContext().execute(ventana);
						RequestContext.getCurrentInstance().update("listaBusquedaForm");
						RequestContext.getCurrentInstance().update("idBody_final_busqueda");						
						this.addInfo("Info: ", mensaje);
					}
					else {			
						String mensajeAlert = this.devuelveMensajeAlertaDefaultAction();
						if (StringUtils.isBlank(mensajeAlert)) {
							mensajeAlert = mensaje;	
						}
						this.setMensajeAlertaDefaultAction(mensajeAlert);
						RequestContext.getCurrentInstance().update("principalFormAlertAction:textoMensajeAlerta");
						String ventana = "PF('principalFormAlertAction_alertDialogAction').show()";
						this.getRequestContext().execute(ventana);
					}
				}
				else {
					this.addInfo("Info: ", mensaje);
				}
				
			}	
		}
		catch (Exception e) {
 			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			historicoAuditoria.setIndicadorEstado(Constants.MENSAJE_PROCESO_ERROR);
			return;
		}
		if (log.isWarnEnabled()) {
			log.warn("Finish 'save' method");
		}
		if(this.activarGrabarWindowClose && !error) {
			this.getRequestContext().addCallbackParam("activarCerrar", true);
		}
		else {
			this.getRequestContext().addCallbackParam("activarCerrar", false);
		}
		return;
	}
	
	
	/**
	 * Metodo que se inovca al regresar de Grabar y activar antes de un Find
	 * @throws Exception
	 */
	public void setSaveFindBeforeAttributes() throws Exception {
		
	}
	
	/**
	 * Metodo que se inovca al regresar de Salir y activar luego de un Find
	 * @throws Exception
	 */
	public void setSalirFindAfterAttributes() throws Exception {
		
	}
	
	
	/**
	 * Metodo que se inovca al regresar de Salir y activar antes de un Find
	 * @throws Exception
	 */
	public void setSalirFindBeforeAttributes() throws Exception {
		
	}
	
	/**
	 * Metodo que se inovca al regresar de Eliminar y activar luego de un Find
	 * @throws Exception
	 */
	public void setDeleteFindAfterAttributes() throws Exception {
		
	}
	
	
	/**
	 * Metodo que se inovca al regresar de Eliminar y activar antes de un Find
	 * @throws Exception
	 */
	public void setDeleteFindBeforeAttributes() throws Exception {
		
	}
	
	/**
	 * Metodo que se inovca al regresar de Grabar y activar luego de un Find
	 * @throws Exception
	 */
	public void setSaveFindAfterAttributes() throws Exception {
		
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setConfirmarAlertDialogAction()
     */
    public void setConfirmarAlertDialogAction() throws Exception {
    	if (this.salirGrabarPantallaPadre) {
    		this.redireccionarPagina(this.getSalirForward());
    	}
		
	}
    
    /**
     * Cambia mensaje personalizado para Dialog de Alerta Action
     * @return
     * @throws Exception
     */
    public String devuelveMensajeAlertaDefaultAction() throws Exception {
    	return null;
    }
	
	/**
	 * Realiza validaciones previas antes de la Grabacion
	 * @param actionEvent
	 */
	public void validarSave(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validarSave' method");
		}
		boolean error = false;
		if(!this.validarMantenimiento()){
			
			error = true;
			if(this.activarGrabarWindowClose && !error) {
				this.getRequestContext().addCallbackParam("activarCerrar", true);
			}
			else {
				this.getRequestContext().addCallbackParam("activarCerrar", false);
			}
			this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
			return ;
		}
		if (this.activarVentanaConfirmacionSave) {
			String mensajeConfirmacion = this.cambiarMensajeConfirmacionSave(); 
			if (StringUtils.isNotBlank(mensajeConfirmacion)) {
				this.mensajeConfirmacionSave = mensajeConfirmacion;
			}
			this.getRequestContext().execute("PF('confirmationDialogConfirmarSave').show()");
			return;
		}
		else {
			this.save(actionEvent);
		}
		return;
	}	
	
	
	/**
	 * Realiza acciones personalizadas de validación previa, de existir, antes de realizar el grabado del Mantenimiento
	 * @return
	 */
	private boolean validarMantenimiento(){
		boolean validacion = true;
		String lsMensajeError = this.setValidarMantenimiento();
		if (StringUtils.isNotBlank(lsMensajeError)) {
			validacion = false;	
			this.setMensajeAlertaDefault(lsMensajeError);
		}
		return validacion;
	}
	
	/**
	 * Cambia el mensaje de Confirmacion al momento de Grabar
	 * @return
	 */
	public String cambiarMensajeConfirmacionSave() {
		return null;
	}
	
	/**
	 * Realiza acciones personalizadas de validación previa, de existir, antes de realizar el grabado del Mantenimiento
	 * Devuelve Mensaje de error personalizado de validacion extra antes de realizar el grabado del Mantenimiento
	 * @return
	 */
	public String setValidarMantenimiento(){
		return "";
	}
	
	/**
	 * Metodo Salir
	 * @return
	 */
	public void salir(ActionEvent actionEvent)  {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'Salir' method");
		}
		/* Redireccionando a la pagina respectiva */
		try{			
			if (this.invocarFindLuegoGrabar) {
				this.setSalirFindBeforeAttributes();
				this.find();
				this.setSalirFindAfterAttributes();
			}
			
			if (this.mostrarMantenimientoEnPopup) {
				String ventana = "PF('dialogMantenimientoForm').hide()";
				this.getRequestContext().execute(ventana);
				
				this.listaBusqueda = this.setFindAttributes();
				this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
				RequestContext.getCurrentInstance().update("listaBusquedaForm");
			}
			else
				this.redireccionarPagina(this.getSalirForward());	
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			return;
		}
		this.accion = this.ACCION_BUSCAR;
		return;
	}
	
	
	
	
	/**
	 * Metodo que devuelve el key del mensaje a Mostrar al ejecutar Salvar en forma OK
	 * @return
	 */
	protected String devuelveMensajeKeySaveOK() {
		if (this.ACCION_NUEVO.equals(this.accion)) {
			return "datos.insert.ok";
		}	
		if (this.ACCION_MODIFICAR.equals(this.accion)) {
			return "datos.update.ok";
		}	
		return "datos.actualizado.ok";	
	}
	
	/**
	 * Method donde debera colocarse la pagina xhtml a la cual redireccionar 
	 * luego que haya grabado correctamente el registro
	 * Solo colocar el nombre de la pagina sin extension xhtml
	 * @return
	 */
	protected String getSaveForward() {
		return this.getSalirForward();
	}
	
	/**
	 * Hook method donde debera colocarse la pagina xhtml a la cual redireccionar 
	 * luego que se haya hecho click en el boton cancelar
	 * Solo colocar el nombre de la pagina sin extension xhtml
	 * @return
	 */
	protected abstract String getSalirForward();
	
	/**
	 * Metodo que permite obtener el listado geografico por el codigo de unidad geografica 
	 * de acuerdo al total de nivel geografico a mostrar en una lista desplegable.
	 *
	 * @param oidPais the oid pais
	 * @param codigoUnidadGeografica the codigo unidad geografica
	 * @return the list
	 */
	protected LabelValue[] obtenerListaGeoPorCodUniGeo(String oidPais, String codigoUnidadGeografica){
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		return ajax.getUnidadesGeograficas(oidPais, codigoUnidadGeografica);			
	}
	
	
	/**
	 * Hook method en la cual se debe indicar el nombre de la Pagina XHTML de Mantenimiento asociado
	 * En este caso se debera poner solo el nombre de la pagina sin extension (xhtml) 
	 * @return
	 * @throws Exception
	 */
	protected abstract String getPaginaMantenimiento() throws Exception;
	
	/**
	 * Aqui se debe asociar la clase FORM al Manage Beans, la cual internamente se asociara al
	 * atributo formBusqueda
	 * @return
	 * @throws Exception
	 */
	protected abstract BaseSearchForm devuelveFormBusqueda() throws Exception;
	
	/**
	 * Hook method para la ejecucion de la Busqueda. Esta implementacion devuelve una lista con los valores
	 * respectivos de acuerdo a los filtros seleccionados. Dicho metodo es obligatorio sobreescribirlo 
	 * La busqueda por defecto es por AJAX
	 * @throws Exception
	 */
	protected abstract List setFindAttributes() throws Exception;
	
	/**
	 * Hook method para la ejecucion de la Eliminacion de Registros. Dicha implementacion contiene la logica
	 * personalizada para cada mantenimiento para efectar dicha eliminacion
	 * @return
	 * @throws Exception
	 */
	protected abstract boolean setDeleteAttributes() throws Exception;
	
		
	
	/**
	 * Hook method encargado de realizar la grabacion respectiva
	 * @param request
	 * @param form
	 * @return
	 * @throws Exception
	 */
	protected abstract boolean setSaveAttributes() throws Exception; 
	
	
	/**
	 *  Hook method encargado de obtener el registro que será utilizado en el mantenimiento
	 * @return
	 * @throws Exception
	 */
	protected abstract BaseEditForm setObtenerRegistroAttributes() throws Exception;
	
	
	/**
	 * Metodo que cargara un nuevo registro antes de ir a la Pantalla de Mantenimiento para la edicion del
	 * Nuevo Registro 
	 * @throws Exception
	 */
	protected void setAddAttributes() throws Exception {
		
	}
	
	
	/**
	 * Metodo que cargara valores previos antes de ir a la Pantalla de Mantenimiento para la edicion del
	 * Registro seleccionado
	 * @throws Exception
	 */
	protected void setEditAttributes() throws Exception {
		
	}
	
	
	/**
	 * Metodo que cargara valores previos antes de ir a la Pantalla de Mantenimiento para la consulta del
	 * Registro seleccionado
	 * @throws Exception
	 */
	protected void setConsultarAttributes() throws Exception {
		
	}
	
		
	
	/**
	 * Metodo que verifica si se muestra Boton Nuevo
	 * @return
	 */
	protected boolean verificarMostrarBotonNuevo() {
		return true;
	}
	
	/**
	 * Metodo que verifica si se muestra Boton Consultar
	 * @return
	 */
	protected boolean verificarMostrarBotonConsultar() {
		return true;
	}
	
	/**
	 * Metodo que verifica si se muestra Boton Eliminar
	 * @return
	 */
	protected boolean verificarMostrarBotonEliminar() {
		return true;
	}
	
	/**
	 * Metodo que verifica si se muestra Boton Modificar
	 * @return
	 */
	protected boolean verificarMostrarBotonModificar() {
		return true;
	}
	
	
	
		
	/* GET - SET ATRIBUTOS */	
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
	 * @return the formBusqueda
	 */
	public BaseSearchForm getFormBusqueda() {
		return formBusqueda;
	}

	/**
	 * @param formBusqueda the formBusqueda to set
	 */
	public void setFormBusqueda(BaseSearchForm formBusqueda) {
		this.formBusqueda = formBusqueda;
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
	 * @return the seleccionoRegistro
	 */
	public boolean isSeleccionoRegistro() {
		return seleccionoRegistro;
	}


	/**
	 * @param seleccionoRegistro the seleccionoRegistro to set
	 */
	public void setSeleccionoRegistro(boolean seleccionoRegistro) {
		this.seleccionoRegistro = seleccionoRegistro;
	}


	/**
	 * @return the mostrarBotonNuevo
	 */
	public boolean isMostrarBotonNuevo() {
		return mostrarBotonNuevo;
	}


	/**
	 * @param mostrarBotonNuevo the mostrarBotonNuevo to set
	 */
	public void setMostrarBotonNuevo(boolean mostrarBotonNuevo) {
		this.mostrarBotonNuevo = mostrarBotonNuevo;
	}


	/**
	 * @return the mostrarBotonModificar
	 */
	public boolean isMostrarBotonModificar() {
		return mostrarBotonModificar;
	}


	/**
	 * @param mostrarBotonModificar the mostrarBotonModificar to set
	 */
	public void setMostrarBotonModificar(boolean mostrarBotonModificar) {
		this.mostrarBotonModificar = mostrarBotonModificar;
	}


	/**
	 * @return the mostrarBotonConsultar
	 */
	public boolean isMostrarBotonConsultar() {
		return mostrarBotonConsultar;
	}


	/**
	 * @param mostrarBotonConsultar the mostrarBotonConsultar to set
	 */
	public void setMostrarBotonConsultar(boolean mostrarBotonConsultar) {
		this.mostrarBotonConsultar = mostrarBotonConsultar;
	}


	/**
	 * @return the mostrarBotonEliminar
	 */
	public boolean isMostrarBotonEliminar() {
		return mostrarBotonEliminar;
	}


	/**
	 * @param mostrarBotonEliminar the mostrarBotonEliminar to set
	 */
	public void setMostrarBotonEliminar(boolean mostrarBotonEliminar) {
		this.mostrarBotonEliminar = mostrarBotonEliminar;
	}


	/**
	 * @return the mostrarBotonSave
	 */
	public boolean isMostrarBotonSave() {
		return mostrarBotonSave;
	}


	/**
	 * @param mostrarBotonSave the mostrarBotonSave to set
	 */
	public void setMostrarBotonSave(boolean mostrarBotonSave) {
		this.mostrarBotonSave = mostrarBotonSave;
	}


	/**
	 * @return the mostrarBotonSalir
	 */
	public boolean isMostrarBotonSalir() {
		return mostrarBotonSalir;
	}


	/**
	 * @param mostrarBotonSalir the mostrarBotonSalir to set
	 */
	public void setMostrarBotonSalir(boolean mostrarBotonSalir) {
		this.mostrarBotonSalir = mostrarBotonSalir;
	}


	/**
	 * @return the invocarFindLuegoGrabar
	 */
	public boolean isInvocarFindLuegoGrabar() {
		return invocarFindLuegoGrabar;
	}


	/**
	 * @param invocarFindLuegoGrabar the invocarFindLuegoGrabar to set
	 */
	public void setInvocarFindLuegoGrabar(boolean invocarFindLuegoGrabar) {
		this.invocarFindLuegoGrabar = invocarFindLuegoGrabar;
	}


	/**
	 * @return the formMantenimiento
	 */
	public BaseEditForm getFormMantenimiento() {
		return formMantenimiento;
	}


	/**
	 * @param formMantenimiento the formMantenimiento to set
	 */
	public void setFormMantenimiento(BaseEditForm formMantenimiento) {
		this.formMantenimiento = formMantenimiento;
	}


	/**
	 * @return the editableMantenimiento
	 */
	public boolean isEditableMantenimiento() {
		return editableMantenimiento;
	}


	/**
	 * @param editableMantenimiento the editableMantenimiento to set
	 */
	public void setEditableMantenimiento(boolean editableMantenimiento) {
		this.editableMantenimiento = editableMantenimiento;
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
	 * @return the mostrarCriteriosBusqueda
	 */
	public boolean isMostrarCriteriosBusqueda() {
		return mostrarCriteriosBusqueda;
	}


	/**
	 * @param mostrarCriteriosBusqueda the mostrarCriteriosBusqueda to set
	 */
	public void setMostrarCriteriosBusqueda(boolean mostrarCriteriosBusqueda) {
		this.mostrarCriteriosBusqueda = mostrarCriteriosBusqueda;
	}


	/**
	 * @return the salirGrabarPantallaPadre
	 */
	public boolean isSalirGrabarPantallaPadre() {
		return salirGrabarPantallaPadre;
	}


	/**
	 * @param salirGrabarPantallaPadre the salirGrabarPantallaPadre to set
	 */
	public void setSalirGrabarPantallaPadre(boolean salirGrabarPantallaPadre) {
		this.salirGrabarPantallaPadre = salirGrabarPantallaPadre;
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
	 * @return the esSeleccionMultiple
	 */
	public boolean isEsSeleccionMultiple() {
		return esSeleccionMultiple;
	}


	/**
	 * @param esSeleccionMultiple the esSeleccionMultiple to set
	 */
	public void setEsSeleccionMultiple(boolean esSeleccionMultiple) {
		this.esSeleccionMultiple = esSeleccionMultiple;
	}

	/**
	 * @return the beanListaSeleccionado
	 */
	public Object[] getBeanListaSeleccionado() {
		return beanListaSeleccionado;
	}

	/**
	 * @param beanListaSeleccionado the beanListaSeleccionado to set
	 */
	public void setBeanListaSeleccionado(Object[] beanListaSeleccionado) {
		this.beanListaSeleccionado = beanListaSeleccionado;
	}
	
	
	
			
}
