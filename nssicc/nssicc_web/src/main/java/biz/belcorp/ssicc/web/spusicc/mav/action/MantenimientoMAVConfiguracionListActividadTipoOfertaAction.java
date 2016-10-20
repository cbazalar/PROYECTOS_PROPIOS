package biz.belcorp.ssicc.web.spusicc.mav.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.mav.model.ActividadTipoOferta;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.mav.MantenimientoMAVConfiguracionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.mav.form.MantenimientoMAVConfiguracionActividadTipoOfertaForm;
import biz.belcorp.ssicc.web.spusicc.mav.form.MantenimientoMAVConfiguracionListActividadTipoOfertaSearchForm;

@SuppressWarnings({ "unchecked", "rawtypes" })
@ManagedBean
@SessionScoped
public class MantenimientoMAVConfiguracionListActividadTipoOfertaAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 1L;
	private List mavTipoClienteList;
	private List mavConfiguracionesActividadTipoOfertaList;
	private List mavTiposOfertaList;
	private LabelValue[] mavActividadesList = {};
	private String retorno = "view";
	private List listaActividadesTemp;
	private List listActividades;
	private List listaTipoCliente;
	private List mavConfiguracionesTemporalesList;
	private Object beanMantenimientoMAVConfiguracionListActividadTipoOferta;
	private List listObject = new ArrayList();
	private DataTableModel datatableMAV;
	private Boolean consultar;
	private Boolean nuevo;
	private Boolean editar;

	public void loadActividades(ValueChangeEvent val) {	
		String objeto = (String) val.getNewValue();
		if (!StringUtils.isBlank(objeto)) {
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			this.mavActividadesList = ajaxService.getActividadesByTipoCliente(objeto);
			this.mavActividadesList=validaListaActividades(this.mavActividadesList);					
			
		} else {
			setMavActividadesList(null);
		}
	}
	
	public LabelValue[] validaListaActividades(LabelValue[] prueba){
		for(int i=0;i<prueba.length;i++){
			if(prueba[i].getLabel()==null)
				prueba[i].setLabel("");			
		}
		return prueba;
	}

	/**
	 * Elimina una temporal
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void desactiva(ActionEvent evt) throws Exception {
		try {			
			HashMap objSeleccionado = (HashMap) this.beanRegistroSeleccionado;

			MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService) this
					.getBean("spusicc.mantenimientoMAVConfiguracionService");

			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

			String id = objSeleccionado.get("OidActTipoOferta").toString();
			String mensaje = "";
			Map params = new HashMap();
			params.put("oidTipoOfer", id);
			params.put("usuario", usuario.getLogin());
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
			if (StringUtils.isNotBlank(id)) {
				ActividadTipoOferta objEnt = new ActividadTipoOferta();
				objEnt = service.getObtieneActividadesTipoOferta(params);
				objEnt.setCodigoPais(pais.getCodigo());
				Map vad = new HashMap();
				vad.put("oidOfer", objEnt.getTipoOferta());
				vad.put("oidActiv", objEnt.getActividad());

				if (!service.getValidaEditActividadOferta(params)) {
					
					service.DesactivarActividadTipoOferta(params, usuario);
					this.listaBusqueda = this.setFindAttributes();
					this.datatableBusqueda = new DataTableModel(listaBusqueda);
		
					mensaje = this.getResourceMessage("mantenimientoMAVConfiguracionActividadTipoOfertaForm.desactiva");
					this.addInfo("Info : ", mensaje);	
				} else {
					mensaje = this.getResourceMessage("mantenimientoMAVConfiguracionActividadTipoOfertaForm.validaerror");
					this.addError("Error : ", mensaje);
				}
			}			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		

	}

	@Override
	protected String getSalirForward() {
		return "mantenimientoMAVConfiguracionListActividadTipoOfertaList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoMAVConfiguracionListActividadTipoOfertaForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoMAVConfiguracionListActividadTipoOfertaSearchForm form = new MantenimientoMAVConfiguracionListActividadTipoOfertaSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {

		MantenimientoMAVConfiguracionListActividadTipoOfertaSearchForm f = (MantenimientoMAVConfiguracionListActividadTipoOfertaSearchForm) this.formBusqueda;
		MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService) this
				.getBean("spusicc.mantenimientoMAVConfiguracionService");

		String TipoActividad = f.getTipoActividad();
		String Actividad = f.getActividad();
		String oferta = f.getTipoOferta();

		Map criteria = new HashMap();
		criteria.put("oidTipoActividad", TipoActividad);
		criteria.put("oidActi", Actividad);
		criteria.put("oidOferta", oferta);
		List listaActividadesTipoOferta = service
				.getActividadesTipoOferta(criteria);
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");

		if (StringUtils.isNotBlank(f.getTipoActividad())) {
			LabelValue[] listActividades = ajaxService.getActividadesByTipoCliente(f.getTipoActividad());			
			this.mavActividadesList = validaListaActividades(listActividades);			
		}

		this.mavConfiguracionesActividadTipoOfertaList = listaActividadesTipoOferta;

		return listaActividadesTipoOferta;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {		
		retorno = "view";
		MantenimientoMAVConfiguracionActividadTipoOfertaForm f = (MantenimientoMAVConfiguracionActividadTipoOfertaForm) this.formMantenimiento;
		ActividadTipoOferta objeto= new ActividadTipoOferta();

		MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService) this
				.getBean("spusicc.mantenimientoMAVConfiguracionService");

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String mensaje = "";
		boolean isNew = f.isNewRecord();

		for (int i = 0; i < this.listObject.size(); i++) {
			objeto = (ActividadTipoOferta) listObject.get(i);			
			
			Map params = new HashMap();
			params.put("oidTipoOfer", objeto.getCodigoTOferta());
			params.put("oidActiv", objeto.getActividad());
			params.put("oidOfer", objeto.getTipoOferta());
			params.put("estado", "1");
			params.put("usuario", usuario.getLogin());

		if (isNew) {			
			if (!service.getExisteActividadesTipoOferta(params)) 
				service.insertActividadesTipoOferta(params, usuario);
			else {				
				mensaje = this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error");
				throw new Exception(mensaje);
			}
		} else {
				Map paramupd = new HashMap();
				paramupd.put("oidTipoOfer", f.getOidTipoOfer());
				paramupd.put("oidActiv", f.getActividad());
				paramupd.put("oidOfer", f.getTipoOferta());
				paramupd.put("usuario", usuario.getLogin());

				ActividadTipoOferta ATO = service.getObtieneActividadesTipoOferta(paramupd);

				if (ATO.getActividad().equals(f.getActividad())
						&& ATO.getTipoOferta().equals(f.getTipoOferta())) {
					return false;
				} else {
					if (!service.getExisteActividadesTipoOferta(paramupd)) {
						if (!service.getValidaEditActividadOferta(paramupd)) {
							Map tacti = new HashMap();
							tacti.put("TipoActividad", f.getTipoActividad());

							String Tipoactividad = service.getDescripcionTipoActividad(tacti);
							Map acti = new HashMap();
							acti.put("oidActiv", f.getActividad());

							String actividad = service.getDescripcionActividad(acti);
							f.setDescripcionActividad(actividad);
							f.setDescripcionTipoActividad(Tipoactividad);
							service.updateActividadesTipoOferta(paramupd,usuario);
							BeanUtils.copyProperties(objeto, f);
							this.listObject = new ArrayList();
							this.listObject.add(objeto);							
							this.addInfo("Info :", mensaje);
						} else {
							mensaje = this.getResourceMessage("mantenimientoMAVConfiguracionActividadTipoOfertaForm.validaerror");
							throw new Exception(mensaje);							
						}
					} else {
						mensaje = this.getResourceMessage("mantenimientoMAVConfiguracionActividadTipoOfertaForm.existe");
						throw new Exception(mensaje);
					}
				}
			}
		}
		return true;
	}
	
	

	String idActividad;
	String idOferta;

	/**
	 * Elimina una temporal	
	 */
	public void deleteTemporal(ActionEvent evt){
		try {
			ActividadTipoOferta actTipoOferta =  (ActividadTipoOferta) this.beanMantenimientoMAVConfiguracionListActividadTipoOferta;
			idActividad = actTipoOferta.getActividad();
			idOferta = actTipoOferta.getTipoOferta();
			
			/*String id = "";
			String[] listaIDs = StringUtils.split(id, "|");

			for (int i = 0; i < listaIDs.length; i++) {
				if (i == 0) {
					idActividad = StringUtils.split(listaIDs[i], "|")[0];
					idOferta = listaIDs[i + 1];
				}
			}*/

			if (actTipoOferta != null) {
				MantenimientoMAVConfiguracionListActividadTipoOfertaSearchForm form = (MantenimientoMAVConfiguracionListActividadTipoOfertaSearchForm) this.formBusqueda;
				ActividadTipoOferta objeto = new ActividadTipoOferta();
				List listaDelete = new ArrayList();
				listaDelete = listaActividadesTemp;

				for (int i = 0; i < this.listObject.size(); i++) {
					objeto = (ActividadTipoOferta) this.listObject.get(i);
					if (objeto.getTipoOferta().equals(idOferta) && objeto.getActividad().equals(idActividad)) {
						listObject.remove(objeto);
					}
				}
				
				this.mavConfiguracionesTemporalesList = listObject;			
			}
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	//metodo para insertar los datos en la grilla
	public void ejecutar(ActionEvent ect) {
		try {
			MantenimientoMAVConfiguracionActividadTipoOfertaForm f = (MantenimientoMAVConfiguracionActividadTipoOfertaForm) this.formMantenimiento;
			ActividadTipoOferta form = new ActividadTipoOferta();
			//MantenimientoMAVConfiguracionActividadTipoOfertaForm form = new MantenimientoMAVConfiguracionActividadTipoOfertaForm();
			String mensaje = "";			

			if (f.getActividad().equals(form.getActividad())
					&& f.getTipoOferta().equals(form.getTipoOferta())) {

				mensaje = this.getResourceMessage("mantenimientoMAVConfiguracionActividadTipoOfertaForm.validando");
				this.addError("Error : ", mensaje);
				return;
			}
			form.setActividad(f.getActividad());
			form.setTipoActividad(f.getTipoActividad());
			form.setDescripcionActividad(f.getDescripcionActividad());
			form.setCodigoTOferta(f.getCodigoTOferta());
			form.setTipoOferta(f.getTipoOferta());

			MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService) this
					.getBean("spusicc.mantenimientoMAVConfiguracionService");

			Map tacti = new HashMap();
			tacti.put("TipoActividad", f.getTipoActividad());

			String Tipoactividad = service.getDescripcionTipoActividad(tacti);

			Map acti = new HashMap();
			acti.put("oidActiv", f.getActividad());

			String actividad = service.getDescripcionActividad(acti);
			Map ofer = new HashMap();
			ofer.put("oidOfer", f.getTipoOferta());
			ofer.put("oidActiv", f.getActividad());

			String toferta = service.getDescripcionTipoOferta(ofer);
			form.setDescripcionTipoActividad(Tipoactividad);
			form.setDescripcionActividad(actividad);
			form.setDescripcionTipoOferta(toferta);

			String codigoOferta = service.getCodigoTipoOferta(ofer);
			form.setCodigoTOferta(codigoOferta);
			BeanUtils.copyProperties(f, form);

			this.listObject.add(form);
			this.datatableMAV = new DataTableModel(listObject);
			
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			if (StringUtils.isNotBlank(f.getTipoActividad())) {
				LabelValue[] listActividades = ajaxService.getActividadesByTipoCliente(f.getTipoActividad());
				this.mavActividadesList = this.validaListaActividades(listActividades);				
			}		
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));

		}

	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		MantenimientoMAVConfiguracionActividadTipoOfertaForm f = new MantenimientoMAVConfiguracionActividadTipoOfertaForm();
		this.listObject = new ArrayList();
		List listaVacia = new ArrayList();
		this.datatableMAV = new DataTableModel(listaVacia);
		f.setNewRecord(true);
		HashMap objSeleccionado = (HashMap) this.beanRegistroSeleccionado;
		this.nuevo = true;
		this.editar = false;
		this.consultar = false;
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			String id = objSeleccionado.get("OidActTipoOferta").toString();
			if (!StringUtils.isBlank(id)) {
				this.editar = true;
				this.nuevo = false;
				this.consultar = false;
				this.mostrarBotonSave = true;				
				f.setNewRecord(false);
				MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService) this.getBean("spusicc.mantenimientoMAVConfiguracionService");
				List listaObjeto = new ArrayList();
				ActividadTipoOferta objEnt = new ActividadTipoOferta();

				Map objAc = new HashMap();
				objAc.put("oidTipoOfer", id);
				objEnt = service.getObtieneActividadesTipoOferta(objAc);				
				f.setOidTipoOfer(id);
				BeanUtils.copyProperties(f, objEnt);

				AjaxService ajaxService = (AjaxService) getBean("ajaxService");
				Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
				String codigoIdiomaISO = usuario.getIdioma().getCodigoISO();

				if (StringUtils.isNotBlank(objEnt.getTipoActividad())) {
					LabelValue[] listActividades = ajaxService.getActividadesByTipoCliente(objEnt.getTipoActividad());
					this.mavActividadesList = validaListaActividades(listActividades);
				}

				Map tacti = new HashMap();
				tacti.put("TipoActividad", objEnt.getTipoActividad());

				String Tipoactividad = service.getDescripcionTipoActividad(tacti);

				Map acti = new HashMap();
				acti.put("oidActiv", objEnt.getActividad());

				String actividad = service.getDescripcionActividad(acti);

				Map ofer = new HashMap();
				ofer.put("oidOfer", objEnt.getTipoOferta());
				ofer.put("oidActiv", objEnt.getActividad());

				String toferta = service.getDescripcionTipoOferta(ofer);

				objEnt.setDescripcionTipoActividad(Tipoactividad);
				objEnt.setDescripcionActividad(actividad);
				objEnt.setDescripcionTipoOferta(toferta);

				String codigoOferta = service.getCodigoTipoOferta(ofer);
				f.setTipoActividad("0" + f.getTipoActividad());
				objEnt.setCodigoTOferta(codigoOferta);
				this.listaActividadesTemp = new ArrayList();
				this.listaActividadesTemp.add(objEnt);
				this.mavConfiguracionesTemporalesList = listaActividadesTemp;
				this.listObject = this.mavConfiguracionesTemporalesList;
				this.datatableMAV = new DataTableModel(mavConfiguracionesTemporalesList);				
			}
			
			if (this.accion.equals(this.ACCION_CONSULTAR)) {
				this.consultar = true;
				this.editar = false;
				this.nuevo = false;
				this.mostrarBotonSave = false;
			}
		}		
		return f;

	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'MantenimientoMAVConfiguracionListActividadTipoOfertaAction - setViewAttributes' method");
		}
		this.mostrarBotonEliminar = false;
		MantenimientoMAVConfiguracionListActividadTipoOfertaSearchForm f = (MantenimientoMAVConfiguracionListActividadTipoOfertaSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());

		MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService) this
				.getBean("spusicc.mantenimientoMAVConfiguracionService");
		this.consultar = false;
		this.nuevo = false;
		this.editar = false;
		// combo actividades
		this.mavTiposOfertaList = service.getTiposOfertaId("");
		List listaTipoCliente = new ArrayList();
		Base base = new Base();
		base.setCodigo("");
		base.setDescripcion("Seleccione");
		listaTipoCliente.add(base);
		f.setTipoActividad("");
		f.setActividad("");
		f.setTipoOferta("");

		base = new Base();
		base.setCodigo("02");
		String proceso = this
				.getResourceMessage("mantenimientoMAVConfiguracionSearchForm.tipoClienteConsultora");
		base.setDescripcion(proceso);
		listaTipoCliente.add(base);
		base = new Base();
		base.setCodigo("04");
		proceso = this.getResourceMessage("mantenimientoMAVConfiguracionSearchForm.tipoClienteGerente");
		base.setDescripcion(proceso);
		listaTipoCliente.add(base);
		this.mavTipoClienteList = listaTipoCliente;
		this.mavConfiguracionesActividadTipoOfertaList = new ArrayList();
	}

	/**
	 * @return the mavTipoClienteList
	 */
	public List getMavTipoClienteList() {
		return mavTipoClienteList;
	}

	/**
	 * @param mavTipoClienteList
	 *            the mavTipoClienteList to set
	 */
	public void setMavTipoClienteList(List mavTipoClienteList) {
		this.mavTipoClienteList = mavTipoClienteList;
	}

	/**
	 * @return the mavConfiguracionesActividadTipoOfertaList
	 */
	public List getMavConfiguracionesActividadTipoOfertaList() {
		return mavConfiguracionesActividadTipoOfertaList;
	}

	/**
	 * @param mavConfiguracionesActividadTipoOfertaList
	 *            the mavConfiguracionesActividadTipoOfertaList to set
	 */
	public void setMavConfiguracionesActividadTipoOfertaList(
			List mavConfiguracionesActividadTipoOfertaList) {
		this.mavConfiguracionesActividadTipoOfertaList = mavConfiguracionesActividadTipoOfertaList;
	}

	/**
	 * @return the mavTiposOfertaList
	 */
	public List getMavTiposOfertaList() {
		return mavTiposOfertaList;
	}

	/**
	 * @param mavTiposOfertaList
	 *            the mavTiposOfertaList to set
	 */
	public void setMavTiposOfertaList(List mavTiposOfertaList) {
		this.mavTiposOfertaList = mavTiposOfertaList;
	}

	/**
	 * @return the mavActividadesList
	 */
	public LabelValue[] getMavActividadesList() {
		return mavActividadesList;
	}

	/**
	 * @param mavActividadesList
	 *            the mavActividadesList to set
	 */
	public void setMavActividadesList(LabelValue[] mavActividadesList) {
		this.mavActividadesList = mavActividadesList;
	}

	/**
	 * @return the retorno
	 */
	public String getRetorno() {
		return retorno;
	}

	/**
	 * @param retorno
	 *            the retorno to set
	 */
	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}

	/**
	 * @return the listaActividadesTemp
	 */
	public List getListaActividadesTemp() {
		return listaActividadesTemp;
	}

	/**
	 * @param listaActividadesTemp
	 *            the listaActividadesTemp to set
	 */
	public void setListaActividadesTemp(List listaActividadesTemp) {
		this.listaActividadesTemp = listaActividadesTemp;
	}

	/**
	 * @return the listActividades
	 */
	public List getListActividades() {
		return listActividades;
	}

	/**
	 * @param listActividades
	 *            the listActividades to set
	 */
	public void setListActividades(List listActividades) {
		this.listActividades = listActividades;
	}

	/**
	 * @return the listaTipoCliente
	 */
	public List getListaTipoCliente() {
		return listaTipoCliente;
	}

	/**
	 * @param listaTipoCliente
	 *            the listaTipoCliente to set
	 */
	public void setListaTipoCliente(List listaTipoCliente) {
		this.listaTipoCliente = listaTipoCliente;
	}

	/**
	 * @return the mavConfiguracionesTemporalesList
	 */
	public List getMavConfiguracionesTemporalesList() {
		return mavConfiguracionesTemporalesList;
	}

	/**
	 * @param mavConfiguracionesTemporalesList
	 *            the mavConfiguracionesTemporalesList to set
	 */
	public void setMavConfiguracionesTemporalesList(
			List mavConfiguracionesTemporalesList) {
		this.mavConfiguracionesTemporalesList = mavConfiguracionesTemporalesList;
	}

	/**
	 * @return the beanMantenimientoMAVConfiguracionListActividadTipoOferta
	 */
	public Object getBeanMantenimientoMAVConfiguracionListActividadTipoOferta() {
		return beanMantenimientoMAVConfiguracionListActividadTipoOferta;
	}

	/**
	 * @param beanMantenimientoMAVConfiguracionListActividadTipoOferta
	 *            the beanMantenimientoMAVConfiguracionListActividadTipoOferta
	 *            to set
	 */
	public void setBeanMantenimientoMAVConfiguracionListActividadTipoOferta(
			Object beanMantenimientoMAVConfiguracionListActividadTipoOferta) {
		this.beanMantenimientoMAVConfiguracionListActividadTipoOferta = beanMantenimientoMAVConfiguracionListActividadTipoOferta;
	}

	/**
	 * @return the listObject
	 */
	public List getListObject() {
		return listObject;
	}

	/**
	 * @param listObject
	 *            the listObject to set
	 */
	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	/**
	 * @return the idActividad
	 */
	public String getIdActividad() {
		return idActividad;
	}

	/**
	 * @param idActividad
	 *            the idActividad to set
	 */
	public void setIdActividad(String idActividad) {
		this.idActividad = idActividad;
	}

	/**
	 * @return the idOferta
	 */
	public String getIdOferta() {
		return idOferta;
	}

	/**
	 * @param idOferta
	 *            the idOferta to set
	 */
	public void setIdOferta(String idOferta) {
		this.idOferta = idOferta;
	}

	/**
	 * @return the datatableMAV
	 */
	public DataTableModel getDatatableMAV() {
		return datatableMAV;
	}

	/**
	 * @param datatableMAV
	 *            the datatableMAV to set
	 */
	public void setDatatableMAV(DataTableModel datatableMAV) {
		this.datatableMAV = datatableMAV;
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
	 * @return the nuevo
	 */
	public Boolean getNuevo() {
		return nuevo;
	}

	/**
	 * @param nuevo the nuevo to set
	 */
	public void setNuevo(Boolean nuevo) {
		this.nuevo = nuevo;
	}

	/**
	 * @return the editar
	 */
	public Boolean getEditar() {
		return editar;
	}

	/**
	 * @param editar the editar to set
	 */
	public void setEditar(Boolean editar) {
		this.editar = editar;
	}
	
	
}