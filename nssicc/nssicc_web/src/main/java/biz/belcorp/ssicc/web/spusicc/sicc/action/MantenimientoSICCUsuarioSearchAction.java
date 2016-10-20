package biz.belcorp.ssicc.web.spusicc.sicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.sicc.model.UsuarioSICC;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.sicc.MantenimientoSICCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sicc.form.MantenimientoSICCUsuarioForm;
import biz.belcorp.ssicc.web.spusicc.sicc.form.MantenimientoSICCUsuarioSearchForm;

/**
 * The Class MantenimientoSICCUsuarioSearchAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 06/02/2015
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@ManagedBean
@SessionScoped
public class MantenimientoSICCUsuarioSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 6052662102155361651L;
	private List mantenimientoSICCRolUsuarioList;
	private List mantenimientoSICCUsuarioList;
	private boolean flagUsuarioBlocked;
	private boolean flagCopiado;
	private List selectedSicOpciones;
	private List selectedUsuariosList;
	private String mensajeDialog;
	private String accionDialog;
	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoSICCUsuarioForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {		
		MantenimientoSICCUsuarioSearchForm mantenimientoSICCUsuarioSearchForm = new MantenimientoSICCUsuarioSearchForm();
		return mantenimientoSICCUsuarioSearchForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		MantenimientoSICCUsuarioSearchForm f = (MantenimientoSICCUsuarioSearchForm) this.formBusqueda;
		f.setLogin("");
		f.setApellidoPaterno("");
		f.setApellidoMaterno("");
		f.setPrimerNombre("");
		f.setSegundoNombre("");
		f.setEmail("");
		f.setTelefono("");
		
		this.salirGrabarPantallaPadre = true;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {		
		MantenimientoSICCUsuarioSearchForm f = (MantenimientoSICCUsuarioSearchForm) this.formBusqueda;
		Map criteria = new HashMap();
		MantenimientoSICCService service = (MantenimientoSICCService) getBean("sicc.mantenimientoSICCService");		
		criteria.put("login", f.getLogin());
		criteria.put("apellidoPaterno", f.getApellidoPaterno());
		criteria.put("apellidoMaterno", f.getApellidoMaterno());
		criteria.put("primerNombre", f.getPrimerNombre());
		criteria.put("segundoNombre", f.getSegundoNombre());
		criteria.put("email", f.getEmail());
		criteria.put("telefono", f.getTelefono());

		List resultado = service.getListaSICCUsuarioByCriteria(criteria);
		this.mantenimientoSICCUsuarioList = resultado;
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		try {			
			MantenimientoSICCService service = (MantenimientoSICCService) getBean("sicc.mantenimientoSICCService");	
			UsuarioSICC usuarioSICC = (UsuarioSICC) this.beanRegistroSeleccionado;
			
			Map criteria = new HashMap();			
			criteria.put("oid", usuarioSICC.getOid());
			
			service.deleteSICCUsuario(criteria);
			this.getResourceMessage("mantenimientoSICCUsuarioForm.deleted");			
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));			
		}
		return true;		
	}
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#devuelveMensajeKeyEliminarOK()
	 */
	@Override
	protected String devuelveMensajeKeyEliminarOK() {
		return "mantenimientoSICCUsuarioForm.deleted";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoSICCUsuarioList";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}
		MantenimientoSICCUsuarioForm f = (MantenimientoSICCUsuarioForm) this.formMantenimiento;
		MantenimientoSICCService service = (MantenimientoSICCService) getBean("sicc.mantenimientoSICCService");
		try {
			UsuarioSICC usuario = null;
			if(!f.isNewRecord()) {
				usuario = (UsuarioSICC) this.beanRegistroSeleccionado;
			} else {
				usuario = new UsuarioSICC();
			}
			BeanUtils.copyProperties(usuario, f);
			
			List lista = this.selectedSicOpciones;

			if (!f.isNewRecord()) {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA MODIFICACION");
				}				
				service.updateSICCUsuario(usuario, lista);
			} else {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA CREACION");
				}
				usuario.setClave(Constants.SIC_CLAVE_DEFAULT);
				service.insertSICCUsuario(usuario, lista);
			}
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		MantenimientoSICCUsuarioForm f = new MantenimientoSICCUsuarioForm();
		f.setEditable(false);
		this.flagCopiado = false;
		this.selectedSicOpciones = new ArrayList();
		if (!this.accion.equals(this.ACCION_NUEVO)) { 	
			MantenimientoSICCService service = (MantenimientoSICCService) getBean("sicc.mantenimientoSICCService");			
			
			UsuarioSICC cabecera = (UsuarioSICC) this.beanRegistroSeleccionado;			
			Map criteria = new HashMap();			
			criteria.put("oid", cabecera.getOid());
			
			List resultado = (List)service.getListaSICCUsuarioByCriteria(criteria);			
			if (resultado != null && resultado.size() > 0 ) {
				cabecera = (UsuarioSICC) resultado.get(0);
				BeanUtils.copyProperties(f, cabecera);
			}
			List rolesUsuarioList = service.getListaSICCRolUsuarioByCriteria(criteria);
			this.mantenimientoSICCRolUsuarioList = rolesUsuarioList;						
			
			for(int j = 0; j < this.mantenimientoSICCRolUsuarioList.size(); j++) {
    			Map rolUsuario = (Map) this.mantenimientoSICCRolUsuarioList.get(j);
    			if(Constants.ESTADO_ACTIVO.equals(rolUsuario.get("indPerfilAsignado"))) {
    				this.selectedSicOpciones.add(rolUsuario);
	            }    			
    		}			
			
			f.setNewRecord(false);
		} else {
			log.info("Entro a MantenimientoSICCUsuarioAction - setAddAttributes");
			
			//-- Variables
			MantenimientoSICCService service = (MantenimientoSICCService) getBean("sicc.mantenimientoSICCService");
			Map criteria = null;
			
			//-- Parametros
			String codigoPais = this.mPantallaPrincipalBean.getCountryCode();
			
			//-- Setear formulario
			UsuarioSICC cabecera = new UsuarioSICC();
			cabecera.setCodigoPais(codigoPais);
			BeanUtils.copyProperties(f, cabecera);
			
			//-- Logica de negocio --------------------------------------
			
			//-- getListaSICCRolUsuarioByCriteria
			criteria = new HashMap();
			criteria.put("oid", "");
			List rolesUsuarioList = service.getListaSICCRolUsuarioByCriteria(criteria);
			
			//-- getListaSICCUsuarioByCriteria
			criteria = new HashMap();
			criteria.put("login","");
			criteria.put("apellidoPaterno", "");
			criteria.put("apellidoMaterno", "");
			criteria.put("primerNombre", "");
			criteria.put("segundoNombre", "");
			criteria.put("email", "");
			criteria.put("telefono", "");
			List resultado = service.getListaSICCUsuarioByCriteria(criteria);
			
			//-- Peticiones
			this.mantenimientoSICCRolUsuarioList = rolesUsuarioList;
			this.mantenimientoSICCUsuarioList = resultado;		
			
			f.setNewRecord(true);
			log.info("Salio a MantenimientoSICCUsuarioAction - setAddAttributes");			
		}
		this.flagUsuarioBlocked = false;
		this.mostrarBotonSave = true;
		return f;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoSICCUsuarioForm mantenimientoForm = (MantenimientoSICCUsuarioForm) this.formMantenimiento;
		boolean isNew = mantenimientoForm.isNewRecord();
		if(isNew) {
			return "mantenimientoSICCUsuarioForm.add";
		} else {
			return "mantenimientoSICCUsuarioForm.updated";
		}	
	}
	
	/**
	 * Metodo que cargara valores previos antes de ir a la Pantalla de Mantenimiento para la consulta del
	 * Registro seleccionado
	 * @throws Exception
	 */
	@Override
	protected void setConsultarAttributes() throws Exception {
		MantenimientoSICCUsuarioForm f = (MantenimientoSICCUsuarioForm) this.formMantenimiento;
		MantenimientoSICCService service = (MantenimientoSICCService) getBean("sicc.mantenimientoSICCService");
		f.setEditable(true);
		UsuarioSICC cabecera = (UsuarioSICC) this.beanRegistroSeleccionado;
		
		Map criteria = new HashMap();
		
		criteria.put("oid", cabecera.getOid());
		
		List resultado = (List)service.getListaSICCUsuarioByCriteria(criteria);
		
		if (resultado != null && resultado.size() > 0 ) {
			cabecera = (UsuarioSICC) resultado.get(0);
			BeanUtils.copyProperties(f, cabecera);
		}
		List rolesUsuarioList = service.getListaSICCRolUsuarioPermisosByCriteria(criteria);

		this.mantenimientoSICCRolUsuarioList = rolesUsuarioList;
		
		this.selectedSicOpciones = new ArrayList();
		for(int j = 0; j < this.mantenimientoSICCRolUsuarioList.size(); j++) {
			Map rolUsuario = (Map) this.mantenimientoSICCRolUsuarioList.get(j);
			if(Constants.ESTADO_ACTIVO.equals(rolUsuario.get("indPerfilAsignado"))) {
				this.selectedSicOpciones.add(rolUsuario);
            }    			
		}
		this.flagUsuarioBlocked = false;
		this.mostrarBotonSave = false;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		
		
		String mensaje = null;
		
		if(StringUtils.equals(accion, Constants.NUMERO_UNO)){
			if(this.beanRegistroSeleccionado == null) {
				
				return this.getResourceMessage("errors.select.item");
			}else{
				this.mensajeDialog = this.getResourceMessage("mantenimientoSICCUsuarioForm.dialog.bloquear");
				this.accionDialog = Constants.NUMERO_UNO;
			}			
		}
		
		if(StringUtils.equals(accion, Constants.NUMERO_DOS)){
			if(this.beanRegistroSeleccionado == null) {
				
				return this.getResourceMessage("errors.select.item");
			}else{
				this.mensajeDialog = this.getResourceMessage("mantenimientoSICCUsuarioForm.dialog.desbloquear");
				this.accionDialog = Constants.NUMERO_DOS;
			}
		}
		
		if(StringUtils.equals(accion, Constants.NUMERO_TRES)){
			if(this.selectedUsuariosList == null || this.selectedUsuariosList.size() == 0) {
				
				return this.getResourceMessage("errors.select.item");
			}else{
				this.mensajeDialog = this.getResourceMessage("mantenimientoSICCUsuarioForm.dialog.bloquear");
				this.accionDialog = Constants.NUMERO_UNO;
			}			
		}
		
		if(StringUtils.equals(accion, Constants.NUMERO_CUATRO)){
			if(this.selectedUsuariosList == null || this.selectedUsuariosList.size() == 0) {
				
				return this.getResourceMessage("errors.select.item");
			}else{
				this.mensajeDialog = this.getResourceMessage("mantenimientoSICCUsuarioForm.dialog.desbloquear");
				this.accionDialog = Constants.NUMERO_DOS;
			}
		}
		
		
		return mensaje;
	}

	/**
	 * Método que controla la acción de bloqueo del usuario
	 * @param event
	 */
	public void blockedUnlocked(ActionEvent event) {	
		log.info("Entro a MantenimientoSICCUsuarioAction - blockedUnlocked");
		//-- Variables
		MantenimientoSICCService service = (MantenimientoSICCService) getBean("sicc.mantenimientoSICCService");

		//-- Parametros		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String parametroAccion = externalContext.getRequestParameterMap().get("parametroAccion");
		
		UsuarioSICC usuarioSICC = (UsuarioSICC) this.beanRegistroSeleccionado;	
		
		//String id = request.getParameter("id");  
		String id = usuarioSICC.getOid();
		int accion = Integer.valueOf(parametroAccion).intValue();
		log.info("Parametros:".concat(id).concat(";"+accion));
		
		//-- Crear pojo
		Map criteria = new HashMap();
		criteria.put("oid", id);
		criteria.put("accion", accion);
		
		//-- Logica negocio
		service.updateSICCUsuarioBlocked(criteria);
		
		//-- Peticiones
		find();
		this.addInfo("Info: ", this.getResourceMessage("mantenimientoSICCUsuarioList.updateSICCUsuarioBlocked"));
		
		log.info("Salio a MantenimientoSICCUsuarioAction - blockedUnlocked");
	}
	
	/**
	 * Lista de usuarios a bloquear / desbloquear masivamente 
	 * @param event
	 */
	public void blockedUnlockedMasssive(ActionEvent event) {
		log.info("Entro a MantenimientoSICCUsuarioAction - blockedUnlockedMasssive");
		
		//-- Variables
		MantenimientoSICCService service = (MantenimientoSICCService) getBean("sicc.mantenimientoSICCService");
		
		//-- Crear pojo
		Map criteria = new HashMap();
		criteria.put("login","");
		criteria.put("apellidoPaterno", "");
		criteria.put("apellidoMaterno", "");
		criteria.put("primerNombre", "");
		criteria.put("segundoNombre", "");
		criteria.put("email", "");
		criteria.put("telefono", "");
		
		//-- Logica negocio
		List resultado = service.getListaSICCUsuarioByCriteria(criteria);
		
		//-- Peticiones
		this.mantenimientoSICCUsuarioList = resultado;
		this.flagUsuarioBlocked = true;
		this.mostrarBotonSave = false;
		
		try {
			this.redireccionarPagina(this.getPaginaMantenimiento());
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		log.info("Salio a MantenimientoSICCUsuarioAction - blockedUnlockedMasssive");
	}
	
	/**
	 * Método que controla las acciones del m�dulo de Bloqueo Masivo
	 * @param event
	 */
	public void blockedUnlockedMasssiveAction(ActionEvent event) {
		log.info("Entro a MantenimientoSICCUsuarioAction - blockedUnlockedMasssiveAction");
		
		//-- Variables
		MantenimientoSICCService service = (MantenimientoSICCService) getBean("sicc.mantenimientoSICCService");
		Map criteria = new HashMap();
		
		//-- Parametros
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String parametroAccion = externalContext.getRequestParameterMap().get("parametroAccion");
		int accion = Integer.valueOf(parametroAccion).intValue();
		
		//-- Logica
		for(int j = 0; j < this.selectedUsuariosList.size(); j++) {
			UsuarioSICC usuarioSICC = (UsuarioSICC) this.selectedUsuariosList.get(j);	
			//-- Crear pojo
			criteria =  new HashMap();
			criteria.put("oid", usuarioSICC.getOid());
			criteria.put("accion", accion);			
			//-- Logica negocio
			try {
				service.updateSICCUsuarioBlocked(criteria);							
				
			} catch (Exception e) {
				String error = e.getMessage();
				if (StringUtils.isBlank(error)) {
					error = e.getLocalizedMessage();
				}
				addError("Error:", this.getResourceMessage("errors.detail", new Object[]{ error }));
			}
		}	
		
		this.selectedUsuariosList = new ArrayList();
		String mensaje = this.getResourceMessage("mantenimientoSICCUsuarioList.updateSICCUsuarioBlocked");			
		this.setMensajeAlertaDefaultAction(mensaje);
		RequestContext.getCurrentInstance().update("principalFormAlertAction:textoMensajeAlerta");
		String ventana = "PF('principalFormAlertAction_alertDialogAction').show()";
		this.getRequestContext().execute(ventana);
		
		log.info("Salio a MantenimientoSICCUsuarioAction - blockedUnlockedMasssiveAction");
	}
	
	/**
	 * resetMantenimientoSICCUsuario
	 * @param event
	 */
	public void resetMantenimientoSICCUsuario(ActionEvent event) {
		
		if (log.isDebugEnabled())log.debug("Entering 'resetMantenimientoSICCUsuario' method");

		MantenimientoSICCUsuarioForm f = (MantenimientoSICCUsuarioForm) this.formMantenimiento;
		MantenimientoSICCService service = (MantenimientoSICCService) getBean("sicc.mantenimientoSICCService");
		
		try {
			UsuarioSICC usuario = new UsuarioSICC();
			BeanUtils.copyProperties(usuario, f);
			
			if (log.isDebugEnabled()) log.debug("RESET CLAVE");
			
			usuario.setClave(Constants.SIC_CLAVE_DEFAULT);
			service.updateSICCUsuarioResetClave(usuario);
			
			this.selectedSicOpciones = new ArrayList();
			String mensaje = this.getResourceMessage("mantenimientoSICCUsuarioForm.reseted");			
			this.setMensajeAlertaDefaultAction(mensaje);
			RequestContext.getCurrentInstance().update("principalFormAlertAction:textoMensajeAlerta");
			String ventana = "PF('principalFormAlertAction_alertDialogAction').show()";
			this.getRequestContext().execute(ventana);
		
		} catch (Exception e) {
				this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	/**
	 * actualizaMantenimientoSICCUsuario
	 * @param event
	 */
	public void actualizaMantenimientoSICCUsuario(ActionEvent event) {

		if (log.isDebugEnabled())log.debug("Entering 'actualizaMantenimientoSICCUsuario' method");

		MantenimientoSICCUsuarioForm f = (MantenimientoSICCUsuarioForm) this.formMantenimiento;
		MantenimientoSICCService service = (MantenimientoSICCService) getBean("sicc.mantenimientoSICCService");
		 
		try {
			if (log.isDebugEnabled()) log.debug("ACTUALIZA DATOS MASIVOS");
			
			UsuarioSICC usuario = new UsuarioSICC();
			BeanUtils.copyProperties(usuario, f);
			
			service.updateSICCUsuarioDatosMasivos(usuario);
			
			this.beanRegistroSeleccionado = new ArrayList();
			String mensaje = this.getResourceMessage("mantenimientoSICCUsuarioForm.updatedMasivo");			
			this.setMensajeAlertaDefaultAction(mensaje);
			RequestContext.getCurrentInstance().update("principalFormAlertAction:textoMensajeAlerta");
			String ventana = "PF('principalFormAlertAction_alertDialogAction').show()";
			this.getRequestContext().execute(ventana);
			
		} catch (Exception e) {
				this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	
	}
	
	
	/**
	 * enabledSelect
	 * @param event
	 */
	public void enabledSelect(ValueChangeEvent event) {
		
		log.debug("Enter method - enabledSelect");
		
		this.flagCopiado = (Boolean) event.getNewValue();
		MantenimientoSICCUsuarioForm f = (MantenimientoSICCUsuarioForm) this.formMantenimiento;		
		if(Boolean.FALSE.equals(this.flagCopiado)){
			this.selectedSicOpciones = new ArrayList(); 
			f.setCodigoUsuario("");
		}			
	}
	
	/**
	 * Marca los perfiles seleccionados,
	 * dependiendo de usuario
	 * 
	 * @param event
	 */
	public void loadUsuariosSeleccionados(ValueChangeEvent event) {
		
		log.debug("Enter method - loadUsuariosSeleccionados");
		MantenimientoSICCService service = (MantenimientoSICCService) getBean("sicc.mantenimientoSICCService");
		String val = (String) event.getNewValue();
		this.selectedSicOpciones = new ArrayList(); 
		
		if(StringUtils.isNotBlank(val)){			
			Map criteria = new HashMap();		
			criteria.put("oid", val);		
			List rolesUsuarioList = service.getListaSICCRolUsuarioPermisosByCriteria(criteria);

			List aux = rolesUsuarioList;
			
			this.selectedSicOpciones = new ArrayList();
			for(int j = 0; j < aux.size(); j++) {
				Map rolUsuario = (Map) aux.get(j);
				if(Constants.ESTADO_ACTIVO.equals(rolUsuario.get("indPerfilAsignado"))) {
					this.selectedSicOpciones.add(rolUsuario);
	            }    			
			}			
		}
	}
	

	/**
	 * @return the mantenimientoSICCRolUsuarioList
	 */
	public List getMantenimientoSICCRolUsuarioList() {
		return mantenimientoSICCRolUsuarioList;
	}

	/**
	 * @param mantenimientoSICCRolUsuarioList the mantenimientoSICCRolUsuarioList to set
	 */
	public void setMantenimientoSICCRolUsuarioList(
			List mantenimientoSICCRolUsuarioList) {
		this.mantenimientoSICCRolUsuarioList = mantenimientoSICCRolUsuarioList;
	}

	/**
	 * @return the mantenimientoSICCUsuarioList
	 */
	public List getMantenimientoSICCUsuarioList() {
		return mantenimientoSICCUsuarioList;
	}

	/**
	 * @param mantenimientoSICCUsuarioList the mantenimientoSICCUsuarioList to set
	 */
	public void setMantenimientoSICCUsuarioList(List mantenimientoSICCUsuarioList) {
		this.mantenimientoSICCUsuarioList = mantenimientoSICCUsuarioList;
	}

	/**
	 * @return the flagUsuarioBlocked
	 */
	public boolean isFlagUsuarioBlocked() {
		return flagUsuarioBlocked;
	}

	/**
	 * @param flagUsuarioBlocked the flagUsuarioBlocked to set
	 */
	public void setFlagUsuarioBlocked(boolean flagUsuarioBlocked) {
		this.flagUsuarioBlocked = flagUsuarioBlocked;
	}

	/**
	 * @return the selectedSicOpciones
	 */
	public List getSelectedSicOpciones() {
		return selectedSicOpciones;
	}

	/**
	 * @param selectedSicOpciones the selectedSicOpciones to set
	 */
	public void setSelectedSicOpciones(List selectedSicOpciones) {
		this.selectedSicOpciones = selectedSicOpciones;
	}

	/**
	 * @return the flagCopiado
	 */
	public boolean isFlagCopiado() {
		return flagCopiado;
	}

	/**
	 * @param flagCopiado the flagCopiado to set
	 */
	public void setFlagCopiado(boolean flagCopiado) {
		this.flagCopiado = flagCopiado;
	}

	/**
	 * @return the selectedUsuariosList
	 */
	public List getSelectedUsuariosList() {
		return selectedUsuariosList;
	}

	/**
	 * @param selectedUsuariosList the selectedUsuariosList to set
	 */
	public void setSelectedUsuariosList(List selectedUsuariosList) {
		this.selectedUsuariosList = selectedUsuariosList;
	}

	/**
	 * @return the mensajeDialog
	 */
	public String getMensajeDialog() {
		return mensajeDialog;
	}

	/**
	 * @param mensajeDialog the mensajeDialog to set
	 */
	public void setMensajeDialog(String mensajeDialog) {
		this.mensajeDialog = mensajeDialog;
	}

	/**
	 * @return the accionDialog
	 */
	public String getAccionDialog() {
		return accionDialog;
	}

	/**
	 * @param accionDialog the accionDialog to set
	 */
	public void setAccionDialog(String accionDialog) {
		this.accionDialog = accionDialog;
	}
}