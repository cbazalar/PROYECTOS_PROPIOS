package biz.belcorp.ssicc.web.seguridad.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sicc.model.OpcionesSICC;
import biz.belcorp.ssicc.dao.spusicc.sicc.model.RolSICC;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.sicc.MantenimientoSICCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.action.ReporteSEGPerfilUsuarioAction;
import biz.belcorp.ssicc.web.seguridad.form.MantenimientoRolSICCForm;
import biz.belcorp.ssicc.web.seguridad.form.MantenimientoRolSICCSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoRolSICCSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	@ManagedProperty(value = "#{reporteSEGPerfilUsuarioAction}")
	private ReporteSEGPerfilUsuarioAction reporteSEGPerfilUsuarioAction;

	private static final long serialVersionUID = 8683609379008074717L;

	private List listaRoles;
	private List listaUsuarios;
	private Boolean copiarRol = Boolean.FALSE;
	private Boolean disCodigoRol = Boolean.TRUE;
	private Boolean consultar = false;

	private List sicOpcionesList;
	private DataTableModel sicOpcionesDataModel;
	private List<OpcionesSICC> selectedSicOpciones;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoRolSICCForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoRolSICCSearchForm searchForm = new MantenimientoRolSICCSearchForm();
		return searchForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoRolSICCSearchForm f = (MantenimientoRolSICCSearchForm) this.formBusqueda;
		Map criteria = new HashMap();

		MantenimientoSICCService service = (MantenimientoSICCService) getBeanService("sicc.mantenimientoSICCService");
		if (StringUtils.isNotEmpty(f.getDescripcion()))
			criteria.put("descripcion", "%" + f.getDescripcion() + "%");

		List resultado = service.getListaRolSICCByCriteria(criteria);

		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {

		Map criteria = new HashMap();
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();

		// Obtenemos el id seleccionado
		RolSICC rol = (RolSICC) this.beanRegistroSeleccionado;

		if (log.isDebugEnabled()) {
			log.debug("Id seleccionado de la lista: " + rol.getOid());
		}
		criteria.put("oid", rol.getOid());

		MantenimientoSICCService service = (MantenimientoSICCService) this
				.getBeanService("sicc.mantenimientoSICCService");
		service.deleteRolSICCByCriteria(criteria, usuario);
		return true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonSave = false;
		this.salirGrabarPantallaPadre = true;

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoRolSICCList";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		MantenimientoRolSICCForm f = (MantenimientoRolSICCForm) this.formMantenimiento;
		if(StringUtils.isBlank(f.getDescripcion())){
			return "El campo 'Descripcion' es requerido";
			
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}
		MantenimientoRolSICCForm f = (MantenimientoRolSICCForm) this.formMantenimiento;
		MantenimientoSICCService service = (MantenimientoSICCService) getBeanService("sicc.mantenimientoSICCService");
		
		if(StringUtils.isBlank(f.getDescripcion())){
			throw new Exception("El campo 'Descripcion' es requerido");			
		}
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		// Paso todos los parametros al map
		Map params = BeanUtils.describe(f);
		List accesosRol = new ArrayList();
		List listaAcceso = new ArrayList();

		if (this.selectedSicOpciones != null
				&& this.selectedSicOpciones.size() > 0) {
			log.debug("Nro de Seleccionadas: " + selectedSicOpciones.size());
			for (int i = 0; i < selectedSicOpciones.size(); i++) {
				accesosRol.add(Constants.ESTADO_ACTIVO);
				listaAcceso.add(this.selectedSicOpciones.get(i).getOid());
			}
		} else {
			throw new Exception(
					this.getResourceMessage("mantenimientoRolSICCForm.listaVaciaOpciones"));
		}

		params.put("listaIndicadorOpcion", accesosRol);
		params.put("listaAccesoOpcionOid", listaAcceso);

		if (!f.isNewRecord()) {
			if (log.isDebugEnabled()) {
				log.debug("EN EL CASO QUE SEA MODIFICACION");
			}
			service.updateRolSICCByCriteria(params, usuario);
		} else {
			if (log.isDebugEnabled()) {
				log.debug("EN EL CASO QUE SEA CREACION");
			}
			service.insertRolSICCByCriteria(params, usuario);
		}

		return true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoRolSICCForm f = (MantenimientoRolSICCForm) this.formMantenimiento;
		boolean isNew = f.isNewRecord();
		if (isNew) {
			return "mantenimientoRolSICCForm.add";
		} else {
			return "mantenimientoRolSICCForm.updated";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entro a MantenimientoRolSICCAction - setViewAtributes");

		MantenimientoRolSICCForm f = new MantenimientoRolSICCForm();
		MantenimientoSICCService service = (MantenimientoSICCService) this
				.getBeanService("sicc.mantenimientoSICCService");
		RolSICC rolbusqueda = (RolSICC) this.beanRegistroSeleccionado;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		this.mostrarBotonSave = false;
		this.selectedSicOpciones = new ArrayList();
		this.sicOpcionesList = new ArrayList();
		this.sicOpcionesDataModel = new DataTableModel(this.sicOpcionesList);
		this.consultar = false;
		this.disCodigoRol = Boolean.TRUE;
		this.copiarRol = Boolean.FALSE;
		f.setEditable(Boolean.TRUE);
		f.setNewRecord(Boolean.TRUE);

		if (this.accion.equals(this.ACCION_MODIFICAR)) {
			String id = rolbusqueda.getOid().toString();
			if (id != null) {
				if (log.isDebugEnabled()) {
					log.debug("Id seleccionado de la lista: " + id);
				}
				criteria.put("oid", id);

				RolSICC rolSICC = service.getRolSICCByCriteria(criteria);

				BeanUtils.copyProperties(f, rolSICC);
				f.setCodigoPais(pais.getCodigo());

				List listaOpciones = service
						.getListaOpcionesRolSICCByCriteria(criteria);
				List aux = new ArrayList();
				aux = espaciado(listaOpciones);
				this.sicOpcionesList = aux;
				this.sicOpcionesDataModel = new DataTableModel(
						this.sicOpcionesList);

				mostrarOpcionesSeleccionadas(id, listaOpciones);
				f.setNewRecord(false);
				f.setCodigoRol(id);
			}
		} else if (this.accion.equals(this.ACCION_NUEVO)) {
			f.setCodigoPais(pais.getCodigo());
			f.setDescripcion("");

			List listaOpciones = service
					.getListaOpcionesSICCByCriteria(criteria);
			List aux = new ArrayList();
			aux = espaciado(listaOpciones);
			this.sicOpcionesList = aux;
			this.sicOpcionesDataModel = new DataTableModel(this.sicOpcionesList);

			listaRoles = service.getListaRolSICCByCriteria(criteria);
		} else if (this.accion.equals(this.ACCION_CONSULTAR)) {
			String id = rolbusqueda.getOid().toString();
			if (id != null) {
				criteria.put("oid", id);

				// -- Recuperar descripciones del perfil
				RolSICC rolSICC = service.getRolSICCByCriteria(criteria);
				BeanUtils.copyProperties(f, rolSICC);
				f.setCodigoPais(pais.getCodigo());
				f.setEditable(false);
				f.setNewRecord(false);
				// -- Recuperar lista de opciones del perfil consultado
				List listaOpciones = service
						.getListaOpcionesRolPermisoSICCByCriteria(criteria);
				List aux = new ArrayList();
				aux = espaciado(listaOpciones);
				listaUsuarios = service
						.getListaSICCUsuariosRolByCriteria(criteria);

				this.sicOpcionesList = aux;
				this.sicOpcionesDataModel = new DataTableModel(
						this.sicOpcionesList);
				this.selectedSicOpciones = listaOpciones;
				this.consultar = true;
				this.mostrarBotonSave = false;
			}
		}
		return f;
	}

	/**
	 * @param codigo
	 * @param opciones
	 */
	public void mostrarOpcionesSeleccionadas(String codigo, List opciones) {
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		List<OpcionesSICC> lista = new ArrayList();
		String abc = ajax.getListaRolSICCSeleccionados(codigo);
		String valor[] = abc.split(";");
		for (int i = 0; i < valor.length; i++) {
			for (int j = 0; j < opciones.size(); j++) {
				String cad = ((OpcionesSICC) opciones.get(j)).getOid()
						.toString();
				if (valor[i].equals(cad)) {
					lista.add((OpcionesSICC) opciones.get(j));

				}
			}
		}
		this.selectedSicOpciones = lista;
	}

	/**
	 * @param list
	 * @return
	 */
	public List espaciado(List list) {

		List aux = new ArrayList();

		for (int i = 0; i < list.size(); i++) {
			OpcionesSICC op = (OpcionesSICC) list.get(i);
			int rama = op.getRamaMenu();
			String espaciado = "";
			for (int j = 0; j < rama; j++) {
				espaciado = "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
						+ espaciado;
			}
			op.setDescripcion(espaciado + op.getDescripcion());
			espaciado = "";
			aux.add(op);
		}
		return aux;

	}

	/**
	 * @param event
	 */
	public void limpiarAccesos(ValueChangeEvent event) {

		log.debug("Enter method - limpiarAccesos");
		MantenimientoRolSICCForm f = (MantenimientoRolSICCForm) this.formMantenimiento;
		f.setCodigoRol("");
		Boolean bol = (Boolean) event.getNewValue();
		if (bol.equals(Boolean.TRUE)) {
			this.disCodigoRol = Boolean.FALSE;
		} else {
			this.disCodigoRol = Boolean.TRUE;
			this.selectedSicOpciones = new ArrayList();
		}

	}

	/**
	 * @param event
	 */
	public void copiarAccesosDeRol(ValueChangeEvent event) {

		log.debug("Enter method - copiarAccesosDeRol");
		MantenimientoSICCService service = (MantenimientoSICCService) this
				.getBeanService("sicc.mantenimientoSICCService");
		String val = (String) event.getNewValue();
		Map criteria = new HashMap();
		List listaOpciones = new ArrayList();
		this.selectedSicOpciones = new ArrayList();
		if (StringUtils.isNotBlank(val)) {
			criteria.put("oid", val);
			listaOpciones = service
					.getListaOpcionesRolPermisoSICCByCriteria(criteria);
			this.selectedSicOpciones = listaOpciones;
		}
	}

	/**
	 * @param event
	 */
	public void ejecutarReporte(ActionEvent event) {

		try {
			this.reporteSEGPerfilUsuarioAction.setFormato("XLS");
			this.reporteSEGPerfilUsuarioAction.setFormatoExportacion("XLS");
			this.reporteSEGPerfilUsuarioAction.getFormReporte()
					.setFormatoExportacion("XLS");
			this.reporteSEGPerfilUsuarioAction.executeReporte();
		} catch (Exception e) {

			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @return the reporteSEGPerfilUsuarioAction
	 */
	public ReporteSEGPerfilUsuarioAction getReporteSEGPerfilUsuarioAction() {
		return reporteSEGPerfilUsuarioAction;
	}

	/**
	 * @param reporteSEGPerfilUsuarioAction the reporteSEGPerfilUsuarioAction to set
	 */
	public void setReporteSEGPerfilUsuarioAction(
			ReporteSEGPerfilUsuarioAction reporteSEGPerfilUsuarioAction) {
		this.reporteSEGPerfilUsuarioAction = reporteSEGPerfilUsuarioAction;
	}

	/**
	 * @return the listaRoles
	 */
	public List getListaRoles() {
		return listaRoles;
	}

	/**
	 * @param listaRoles the listaRoles to set
	 */
	public void setListaRoles(List listaRoles) {
		this.listaRoles = listaRoles;
	}

	/**
	 * @return the listaUsuarios
	 */
	public List getListaUsuarios() {
		return listaUsuarios;
	}

	/**
	 * @param listaUsuarios the listaUsuarios to set
	 */
	public void setListaUsuarios(List listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	/**
	 * @return the copiarRol
	 */
	public Boolean getCopiarRol() {
		return copiarRol;
	}

	/**
	 * @param copiarRol the copiarRol to set
	 */
	public void setCopiarRol(Boolean copiarRol) {
		this.copiarRol = copiarRol;
	}

	/**
	 * @return the disCodigoRol
	 */
	public Boolean getDisCodigoRol() {
		return disCodigoRol;
	}

	/**
	 * @param disCodigoRol the disCodigoRol to set
	 */
	public void setDisCodigoRol(Boolean disCodigoRol) {
		this.disCodigoRol = disCodigoRol;
	}

	/**
	 * @return the consultar
	 */
	public Boolean getConsultar() {
		return consultar;
	}

	/**
	 * @param consultar the consultar to set
	 */
	public void setConsultar(Boolean consultar) {
		this.consultar = consultar;
	}

	/**
	 * @return the sicOpcionesList
	 */
	public List getSicOpcionesList() {
		return sicOpcionesList;
	}

	/**
	 * @param sicOpcionesList the sicOpcionesList to set
	 */
	public void setSicOpcionesList(List sicOpcionesList) {
		this.sicOpcionesList = sicOpcionesList;
	}

	/**
	 * @return the sicOpcionesDataModel
	 */
	public DataTableModel getSicOpcionesDataModel() {
		return sicOpcionesDataModel;
	}

	/**
	 * @param sicOpcionesDataModel the sicOpcionesDataModel to set
	 */
	public void setSicOpcionesDataModel(DataTableModel sicOpcionesDataModel) {
		this.sicOpcionesDataModel = sicOpcionesDataModel;
	}

	/**
	 * @return the selectedSicOpciones
	 */
	public List<OpcionesSICC> getSelectedSicOpciones() {
		return selectedSicOpciones;
	}

	/**
	 * @param selectedSicOpciones the selectedSicOpciones to set
	 */
	public void setSelectedSicOpciones(List<OpcionesSICC> selectedSicOpciones) {
		this.selectedSicOpciones = selectedSicOpciones;
	}
}