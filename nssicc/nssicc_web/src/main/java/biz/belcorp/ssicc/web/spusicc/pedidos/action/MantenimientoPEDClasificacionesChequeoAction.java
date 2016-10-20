package biz.belcorp.ssicc.web.spusicc.pedidos.action;

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

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDClasificacionesChequeoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDTipoChequeoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDClasificacionesChequeoForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDClasificacionesChequeoSearchForm;

@SessionScoped
@ManagedBean
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoPEDClasificacionesChequeoAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 1L;

	List clasificacionChequeoList;
	List listTipoChequeo;
	List listTipoCliente;
	LabelValue[] listSubTiposClientes;
	LabelValue[] listTiposClasificaciones;
	LabelValue[] listClasificaciones;
	private Object[] objMultipleSelecion;
	private DataTableModel dmPEDClasificacionChqueo;
	private String parametroAccion;

	/**
	 * @return the clasificacionChequeoList
	 */
	public List getClasificacionChequeoList() {
		return clasificacionChequeoList;
	}

	/**
	 * @param clasificacionChequeoList
	 *            the clasificacionChequeoList to set
	 */
	public void setClasificacionChequeoList(List clasificacionChequeoList) {
		this.clasificacionChequeoList = clasificacionChequeoList;
	}

	/**
	 * @return the listTipoChequeo
	 */
	public List getListTipoChequeo() {
		return listTipoChequeo;
	}

	/**
	 * @param listTipoChequeo
	 *            the listTipoChequeo to set
	 */
	public void setListTipoChequeo(List listTipoChequeo) {
		this.listTipoChequeo = listTipoChequeo;
	}

	/**
	 * @return the listTipoCliente
	 */
	public List getListTipoCliente() {
		return listTipoCliente;
	}

	/**
	 * @param listTipoCliente
	 *            the listTipoCliente to set
	 */
	public void setListTipoCliente(List listTipoCliente) {
		this.listTipoCliente = listTipoCliente;
	}

	/**
	 * @return the listSubTiposClientes
	 */
	public LabelValue[] getListSubTiposClientes() {
		return listSubTiposClientes;
	}

	/**
	 * @param listSubTiposClientes
	 *            the listSubTiposClientes to set
	 */
	public void setListSubTiposClientes(LabelValue[] listSubTiposClientes) {
		this.listSubTiposClientes = listSubTiposClientes;
	}

	/**
	 * @return the listTiposClasificaciones
	 */
	public LabelValue[] getListTiposClasificaciones() {
		return listTiposClasificaciones;
	}

	/**
	 * @param listTiposClasificaciones
	 *            the listTiposClasificaciones to set
	 */
	public void setListTiposClasificaciones(
			LabelValue[] listTiposClasificaciones) {
		this.listTiposClasificaciones = listTiposClasificaciones;
	}

	/**
	 * @return the listClasificaciones
	 */
	public LabelValue[] getListClasificaciones() {
		return listClasificaciones;
	}

	/**
	 * @param listClasificaciones
	 *            the listClasificaciones to set
	 */
	public void setListClasificaciones(LabelValue[] listClasificaciones) {
		this.listClasificaciones = listClasificaciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {

		return "mantenimientoPEDClasificacionesChequeoList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {

		return "mantenimientoPEDClasificacionesChequeoForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {

		MantenimientoPEDClasificacionesChequeoSearchForm objForm = new MantenimientoPEDClasificacionesChequeoSearchForm();
		return objForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {

		MantenimientoPEDClasificacionesChequeoSearchForm f = (MantenimientoPEDClasificacionesChequeoSearchForm) this.formBusqueda;

		MantenimientoPEDClasificacionesChequeoService service = (MantenimientoPEDClasificacionesChequeoService) getBean("spusicc.pedidos.mantenimientoPEDClasificacionesChequeoService");

		Map map = new HashMap();

		map.put("codTipoChequeo", f.getCodigoTipoChequeo());
		map.put("oidTipoCliente", f.getOidTipoCliente());
		map.put("oidSubTipoCliente", f.getOidSubTipoCliente());
		map.put("oidTipoClasificacion", f.getOidTipoClasificacion());
		map.put("oidClasificacion", f.getOidClasificacion());

		List list = service.getClasificacionChequeo(map);
		this.clasificacionChequeoList = list;
		this.dmPEDClasificacionChqueo = new DataTableModel(
				this.clasificacionChequeoList);

		return list;
	}

	@Override
	public String setValidarConfirmar(String accion) {
		
		if (this.objMultipleSelecion == null || this.objMultipleSelecion.length == 0) {
			return "Debe seleccionar un registro";
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {

		return false;
	}

	public void eliminar(ActionEvent evt) {
		try {
			MantenimientoPEDClasificacionesChequeoForm f = new MantenimientoPEDClasificacionesChequeoForm();
			// BeanUtils.copyProperties(f, bean);
			//
			// f.setCodigoTipoChequeo(String.valueOf(bean.get("codTipoChequeo")));
			// f.setCodigoTipoChequeoOld(String.valueOf(bean.get("codTipoChequeo")));

			MantenimientoPEDClasificacionesChequeoService service = (MantenimientoPEDClasificacionesChequeoService) getBean("spusicc.pedidos.mantenimientoPEDClasificacionesChequeoService");

			Map map = new HashMap();
			map.put("codigoPais",this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
			
			String id = "";
			int tamanio = this.objMultipleSelecion.length;
			String[] items = new String[tamanio];
			for (int i = 0; i < tamanio; i++) {
				Map bean = (HashMap) this.objMultipleSelecion[i];
				id = bean.get("codTipoChequeo").toString() + "|"
						+ bean.get("oidTipoCliente").toString() + "|"
						+ bean.get("oidSubTipoCliente").toString() + "|"
						+ bean.get("oidTipoClasificacion").toString() + "|"
						+ bean.get("oidClasificacion").toString();
				// id= f.getCodigoTipoChequeo() + "|" + f.getOidTipoCliente()
				// + "|" + f.getOidSubTipoCliente() + "|"
				// + f.getOidTipoClasificacion() + "|" +
				// f.getOidClasificacion();
				items[i] = id;
				id = "";				
			}
			
			service.deleteClasificacionChequeo(map, items);
			
			this.addInfo("", this.getResourceMessage("mantenimientoEDUEmpresaForm.deleted"));
			
			this.clasificacionChequeoList = this.setFindAttributes();
			this.dmPEDClasificacionChqueo = new DataTableModel(this.clasificacionChequeoList);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {

		MantenimientoPEDClasificacionesChequeoForm f = (MantenimientoPEDClasificacionesChequeoForm) this.formMantenimiento;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoPEDClasificacionesChequeoService service = (MantenimientoPEDClasificacionesChequeoService) getBean("spusicc.pedidos.mantenimientoPEDClasificacionesChequeoService");

		Map params = new HashMap();

		try {

			if (this.accion.equals(this.ACCION_NUEVO)) {

				params.put("codigoPais", pais.getCodigo());
				params.put("codTipoChequeo", f.getCodigoTipoChequeo());
				params.put("oidTipoCliente", f.getOidTipoCliente());
				params.put("oidSubTipoCliente", f.getOidSubTipoCliente());
				params.put("oidTipoClasificacion", f.getOidTipoClasificacion());
				params.put("oidClasificacion", f.getOidClasificacion());

				service.insertClasificacionChequeo(params);
				this.clasificacionChequeoList = this.setFindAttributes();
				this.dmPEDClasificacionChqueo = new DataTableModel(
						this.clasificacionChequeoList);
			} else {

				params.put("codigoPais", pais.getCodigo());
				params.put("codTipoChequeo", f.getCodigoTipoChequeo());
				params.put("oidTipoCliente", f.getOidTipoCliente());
				params.put("oidSubTipoCliente", f.getOidSubTipoCliente());
				params.put("oidTipoClasificacion", f.getOidTipoClasificacion());
				params.put("oidClasificacion", f.getOidClasificacion());

				// los ids temporales antes de ser actualizados
				params.put("codigoPaisOld", f.getCodigoPaisOld());
				params.put("codTipoChequeoOld", f.getCodigoTipoChequeoOld());
				params.put("oidTipoClienteOld", f.getOidTipoClienteOld());
				params.put("oidSubTipoClienteOld", f.getOidSubTipoClienteOld());
				params.put("oidTipoClasificacionOld",
						f.getOidTipoClasificacionOld());
				params.put("oidClasificacionOld", f.getOidClasificacionOld());

				service.updateClasificacionChequeo(params);
				this.clasificacionChequeoList = this.setFindAttributes();
				this.dmPEDClasificacionChqueo = new DataTableModel(
						this.clasificacionChequeoList);
			}
		} catch (Exception e) {
			this.addError("Error : ",this.obtieneMensajeErrorException(e));

		}
		this.clasificacionChequeoList = service
				.getClasificacionChequeo(new HashMap());

		return true;
	}

	/**
	 * @param evt
	 */
	public void obtenerRegistros(ActionEvent evt) {
		try {

			if (this.objMultipleSelecion == null || this.objMultipleSelecion.length == 0) {
				this.addWarn("Error: ", "Solo debe seleccionar un registro");
				return;
			}

			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			this.parametroAccion = externalContext.getRequestParameterMap()
					.get("parametroAccion");
			MantenimientoPEDClasificacionesChequeoForm f = new MantenimientoPEDClasificacionesChequeoForm();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			f.setCodigoPais(pais.getCodigo());
			if (this.parametroAccion.equals(this.ACCION_CONSULTAR)) {
				f.setActivo(true);
				this.mostrarBotonSave = false;
			}
			
			if (this.parametroAccion.equals(this.ACCION_MODIFICAR))
				this.mostrarBotonSave = true;
				
			if (!this.parametroAccion.equals(this.ACCION_NUEVO)) {
				int tamanio = this.objMultipleSelecion.length;
				String[] items = new String[tamanio];
				Map bean = (HashMap) this.objMultipleSelecion[0];

				BeanUtils.copyProperties(f, bean);

				AjaxService aSvc = (AjaxService) getBean("ajaxService");

				Map map = new HashMap();
				String[] id = { f.getCodigoTipoChequeo() };
				f.setCodigoTipoChequeo(String.valueOf(bean
						.get("codTipoChequeo")));
				f.setCodigoTipoChequeoOld(String.valueOf(bean
						.get("codTipoChequeo")));
				f.setCodigoPaisOld(f.getCodigoPais());

				f.setOidTipoClienteOld(f.getOidTipoCliente());
				f.setOidSubTipoClienteOld(f.getOidSubTipoCliente());
				f.setOidTipoClasificacionOld(f.getOidTipoClasificacion());
				f.setOidClasificacionOld(f.getOidClasificacion());
				f.setCodigoPais(pais.getCodigo());

				if (StringUtils.isNotBlank(f.getOidSubTipoCliente())) {
					this.listSubTiposClientes = aSvc
							.getSubTipoClienteByOidTipoCliente(f
									.getOidTipoCliente());
				}

				if (StringUtils.isNotBlank(f.getOidTipoClasificacion())) {
					this.listTiposClasificaciones = aSvc
							.getTipoClasificacionByOidSubTipoCliente(f
									.getOidSubTipoCliente());
				}

				if (StringUtils.isNotBlank(f.getOidClasificacion())) {
					this.listClasificaciones = aSvc
							.getClasificacionByOidTipoClasificacion(f
									.getOidTipoClasificacion());
				}
				
				this.formMantenimiento=f;
				this.redireccionarPagina("mantenimientoPEDClasificacionesChequeoForm");
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		MantenimientoPEDClasificacionesChequeoForm f = new MantenimientoPEDClasificacionesChequeoForm();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoPEDTipoChequeoService mantenimientoPEDTipoChequeoService = (MantenimientoPEDTipoChequeoService) getBean("spusicc.pedidos.mantenimientoPEDTipoChequeoService");

		f.setCodigoPais(pais.getCodigo());
		f.setOidTipoCliente(null);
		f.setOidSubTipoCliente(null);
		f.setOidTipoClasificacion(null);
		f.setOidClasificacion(null);
		this.mostrarBotonSave = true;

		if (this.accion.equals(this.ACCION_CONSULTAR)) {
			this.mostrarBotonSave = false;
			f.setActivo(true);
		}
		this.listTipoChequeo = mantenimientoPEDTipoChequeoService
				.getTipoChequeoAll();

		return f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonModificar = false;
		this.mostrarListaBusqueda = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonConsultar = false;
		MantenimientoPEDClasificacionesChequeoSearchForm f = (MantenimientoPEDClasificacionesChequeoSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());

		MantenimientoPEDTipoChequeoService mantenimientoPEDTipoChequeoService = (MantenimientoPEDTipoChequeoService) getBean("spusicc.pedidos.mantenimientoPEDTipoChequeoService");
		MantenimientoPEDClasificacionesChequeoService mantenimientoPEDClasificacionesChequeoService = (MantenimientoPEDClasificacionesChequeoService) getBean("spusicc.pedidos.mantenimientoPEDClasificacionesChequeoService");

		this.listTipoChequeo = mantenimientoPEDTipoChequeoService
				.getTipoChequeoAll();
		this.listTipoCliente = mantenimientoPEDClasificacionesChequeoService
				.getTipoCliente();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {

		if (this.accion.equals(this.ACCION_NUEVO)) {
			return "mantenimientoPEDClasificacionesChequeoForm.cabecera.insert";
		} else {
			return "mantenimientoPEDClasificacionesChequeoForm.cabecera.update";
		}
	}

	/**
	 * @param val
	 */
	public void loadSubTipoCliente(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadSubTipoCliente");
		}
		try {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			String valor = (String) val.getNewValue();

			this.listSubTiposClientes = aSvc
					.getSubTipoClienteByOidTipoCliente(valor);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param val
	 */
	public void loadTipoClasificacion(ValueChangeEvent val) {
		try {
			if (log.isDebugEnabled())
				log.debug("loadTipoClasificacion");

			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			String valor = (String) val.getNewValue();

			this.listTiposClasificaciones = aSvc
					.getTipoClasificacionByOidSubTipoCliente(valor);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param val
	 */
	public void loadClasificacion(ValueChangeEvent val) {
		try {
			if (log.isDebugEnabled())
				log.debug("loadClasificacion");
			String TipoCliente, TipoClasificacion;

			MantenimientoPEDClasificacionesChequeoSearchForm objSearchForm = (MantenimientoPEDClasificacionesChequeoSearchForm) this.formBusqueda;
			MantenimientoPEDClasificacionesChequeoForm objForm = (MantenimientoPEDClasificacionesChequeoForm) this.formMantenimiento;

			if (this.accion.equals(this.ACCION_BUSCAR)) {
				TipoCliente = objSearchForm.getOidTipoCliente();
				TipoClasificacion = objSearchForm.getOidTipoClasificacion();
			} else {
				TipoCliente = objForm.getOidTipoCliente();
				TipoClasificacion = objForm.getOidTipoClasificacion();
			}

			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			String valor = (String) val.getNewValue();

			this.listClasificaciones = aSvc
					.getClasificacionByOidTipoClasificacion(valor);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @return the objMultipleSelecion
	 */
	public Object[] getObjMultipleSelecion() {
		return objMultipleSelecion;
	}

	/**
	 * @param objMultipleSelecion
	 *            the objMultipleSelecion to set
	 */
	public void setObjMultipleSelecion(Object[] objMultipleSelecion) {
		this.objMultipleSelecion = objMultipleSelecion;
	}

	/**
	 * @return the dmPEDClasificacionChqueo
	 */
	public DataTableModel getDmPEDClasificacionChqueo() {
		return dmPEDClasificacionChqueo;
	}

	/**
	 * @param dmPEDClasificacionChqueo
	 *            the dmPEDClasificacionChqueo to set
	 */
	public void setDmPEDClasificacionChqueo(
			DataTableModel dmPEDClasificacionChqueo) {
		this.dmPEDClasificacionChqueo = dmPEDClasificacionChqueo;
	}

}
