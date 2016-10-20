package biz.belcorp.ssicc.web.spusicc.cronograma.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang3.ArrayUtils;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DualListModel;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.cronograma.MantenimientoCRAGrupoZonaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRAGrupoZonaForm;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRAGrupoZonaSearchForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENPatronMensajeForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoCRAGrupoZonaSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	public MantenimientoCRAGrupoZonaSearchAction() {
	}

	private static final long serialVersionUID = 9046087508667111219L;

	private List siccEtapaDeudaList;
	private String codigoUsuario;

	private boolean asignarDisponibleTab = true;
	private List craGruposComboList = new ArrayList();
	private List craRegionNoAsignadasList;

	private List craZonaAsignadasEntreGrupo = new ArrayList();

	// ***** Asignar Disponible *****
	private List craZonaNoAsignadasTemp = new ArrayList();
	private LabelValue[] craZonaNoAsignadas = {};
	private List craZonaAsignadas;

	// ** Mover Entre Grupo
	private List craZonasGrupoInicial;
	private LabelValue[] craZonasGrupoFinal;
	private List craZonasGrupoFinalTemp = new ArrayList();
	private List craGruposList = new ArrayList();

	private List zonaSelecionadas = new ArrayList();

	// List Dual
	private DualListModel<Base> listaZonas;
	private DualListModel<Base> listaZonasEntreGrupos;

	private Object[] beanMantenimientoCRAGrupoZona;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoCRAGrupoZonaList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCRAGrupoZonaForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCRAGrupoZonaSearchForm searchForm = new MantenimientoCRAGrupoZonaSearchForm();
		return searchForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("setFindAttributes");
		MantenimientoCRAGrupoZonaSearchForm f = (MantenimientoCRAGrupoZonaSearchForm) formBusqueda;
		MantenimientoCRAGrupoZonaService service = (MantenimientoCRAGrupoZonaService) getBean("spusicc.mantenimientoCRAGrupoZonaService");

		Map criteria = new HashMap();
		criteria.put("oidGrupo", f.getOidGrupo());
		List list = new ArrayList();
		list = service.getGrupos(criteria);
		this.craGruposList = list;
		datatableBusqueda = new DataTableModel(this.craGruposList);
		return list;
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

	public void eliminarZona(ActionEvent event) {
		MantenimientoCRAGrupoZonaService service = (MantenimientoCRAGrupoZonaService) getBean("spusicc.mantenimientoCRAGrupoZonaService");
		MantenimientoCRAGrupoZonaSearchForm f = (MantenimientoCRAGrupoZonaSearchForm) formBusqueda;
		int tamanio = this.beanMantenimientoCRAGrupoZona.length;
		try {
			for (int i = 0; i < tamanio; i++) {
				Map grupos = (Map) this.beanMantenimientoCRAGrupoZona[i];
				String oid = grupos.get("codigo").toString();
				Map criteria = new HashMap();

				criteria.put("usuario", codigoUsuario);
				criteria.put("oidGrupo", oid);
				// Eliminar el grupo de zona y delete en la matriz
				service.deleteGrupoZona(criteria);
			}
			f.setOidGrupo(null);
			find();
			addInfo("Ok", this.getResourceMessage("datos.delete.ok"));
			beanMantenimientoCRAGrupoZona = null;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	@Override
	protected String devuelveMensajeKeyEliminarOK() {
		return "mantenimientoCRAGrupoZonaForm.deleted";
	}

	/**
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		log.debug("val: " + ArrayUtils.toString(val.getNewValue()));
		String[] regiones = (String[]) val.getNewValue();

		if (!val.equals(null) && regiones.length > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.craZonaNoAsignadas = aSvc.getZonasNoAsignadas(regiones);
			this.craZonaNoAsignadasTemp.clear();
			for (int i = 0; i < this.craZonaNoAsignadas.length; i++) {
				Base b = new Base();
				b.setCodigo(this.craZonaNoAsignadas[i].getValue());
				b.setDescripcion(this.craZonaNoAsignadas[i].getLabel());
				this.craZonaNoAsignadasTemp.add(b);
			}
			this.craZonaNoAsignadas = null;
		} else {
			this.craZonaNoAsignadas = null;
			this.craZonaNoAsignadasTemp.clear();
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

		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}

		MantenimientoCRAGrupoZonaForm f = (MantenimientoCRAGrupoZonaForm) this.formMantenimiento;

		MantenimientoCRAGrupoZonaService service = (MantenimientoCRAGrupoZonaService) getBean("spusicc.mantenimientoCRAGrupoZonaService");

		Map map = new HashMap();

		try {
			map.put("codigoPais", f.getCodigoPais());
			map.put("nombreGrupo", f.getNombreGrupo());
			map.put("marca", Constants.CODIGO_MARCA_DEFAULT);
			map.put("canal", Constants.CODIGO_CANAL_DEFAULT);
			map.put("acceso", Constants.CODIGO_ACCESO_DEFAULT);
			map.put("usuario", codigoUsuario);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			String anhio = sdf.format(new Date(System.currentTimeMillis()));

			map.put("anhio", anhio);

			// Inserta grupo de zona y en el calendario
			if (f.isNewRecord()) {
				service.insertNombreGrupoZona(map);
				Map criteria = new HashMap();
				criteria.put("oidGrupo", "");
				this.craGruposComboList = service.getGrupos(criteria);
			} else {

				if (this.asignarDisponibleTab) {// seteado en el evento del tab
					List result = listaZonas.getTarget();
					String[] arrayZonasAsignadas = new String[result.size()];
					for (int i = 0; i < result.size(); i++)
						arrayZonasAsignadas[i] = ((Base) result.get(i))
								.getCodigo();
					f.setZonasAsignadas(arrayZonasAsignadas);
					asignarDisponible();

				} else {

					// *** seteando grupo inicial de la lista ***

					List result = this.listaZonasEntreGrupos.getSource();
					String[] arrayZonasGrupoInicial = new String[result.size()];
					for (int i = 0; i < result.size(); i++)
						arrayZonasGrupoInicial[i] = ((Base) result.get(i))
								.getCodigo();

					f.setZonasGrupoInicial(arrayZonasGrupoInicial);

					// *** seteando grupo final de la lista ***
					List result2 = this.listaZonasEntreGrupos.getTarget();
					String[] arrayZonasGrupoFinal = new String[result2.size()];
					for (int i = 0; i < result2.size(); i++)
						arrayZonasGrupoFinal[i] = ((Base) result2.get(i))
								.getCodigo();
					f.setZonasGrupoFinal(arrayZonasGrupoFinal);
					moverEntreGrupo();
				}
			}
			return true;
		} catch (Exception e) {

			return false;
		}

	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void asignarDisponible() {

		log.debug("asignarDisponible");

		MantenimientoCRAGrupoZonaForm f = (MantenimientoCRAGrupoZonaForm) this.formMantenimiento;
		MantenimientoCRAGrupoZonaService service = (MantenimientoCRAGrupoZonaService) getBean("spusicc.mantenimientoCRAGrupoZonaService");
		Map map = new HashMap();

		String id = f.getOidGrupo();

		map.put("codigoPais", f.getCodigoPais());
		map.put("nombreGrupo", f.getNombreGrupo());
		map.put("marca", Constants.CODIGO_MARCA_DEFAULT);
		map.put("canal", Constants.CODIGO_CANAL_DEFAULT);
		map.put("acceso", Constants.CODIGO_ACCESO_DEFAULT);
		map.put("usuario", codigoUsuario);

		map.put("oidGrupo", id);
		service.updateNombreGrupoZona(map);

		map.put("zonas", f.getZonasAsignadas());
		service.updateZonasAsignadas(map);
		this.craZonaNoAsignadasTemp = new ArrayList();
		this.craZonaAsignadas = new ArrayList();
		this.listaZonas = new DualListModel<Base>(new ArrayList(),
				new ArrayList());
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void moverEntreGrupo() {

		log.debug("moverEntreGrupo");
		MantenimientoCRAGrupoZonaForm f = (MantenimientoCRAGrupoZonaForm) this.formMantenimiento;
		MantenimientoCRAGrupoZonaService service = (MantenimientoCRAGrupoZonaService) getBean("spusicc.mantenimientoCRAGrupoZonaService");
		Map map = new HashMap();

		String id = f.getOidGrupo();
		map.put("codigoPais", f.getCodigoPais());
		map.put("nombreGrupo", f.getNombreGrupo());
		map.put("marca", Constants.CODIGO_MARCA_DEFAULT);
		map.put("canal", Constants.CODIGO_CANAL_DEFAULT);
		map.put("acceso", Constants.CODIGO_ACCESO_DEFAULT);
		map.put("usuario", codigoUsuario);

		map.put("oidGrupo", id);
		service.updateNombreGrupoZona(map);

		map.put("zonas", f.getZonasGrupoInicial());
		service.updateZonasAsignadas(map);

		map.put("oidGrupo", f.getGrupoFinal());
		map.put("zonas", f.getZonasGrupoFinal());
		service.updateZonasAsignadas(map);
		this.craZonasGrupoInicial = new ArrayList();
		this.craZonasGrupoFinalTemp = new ArrayList();
		this.listaZonasEntreGrupos = new DualListModel<Base>(new ArrayList(),
				new ArrayList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {

		/* *********** METODO ADD ******** */

		MantenimientoCRAGrupoZonaForm f = new MantenimientoCRAGrupoZonaForm();

		f.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		f.setOidGrupo("");
		f.setNombreGrupo("");
		this.craZonasGrupoFinalTemp.clear();

		/************** Fin ADD ***********/
		return f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	public void modificarZona(ActionEvent event) throws Exception {

		MantenimientoCRAGrupoZonaForm f = new MantenimientoCRAGrupoZonaForm();

		this.craZonasGrupoFinalTemp.clear();
		try {

			int tamanio = beanMantenimientoCRAGrupoZona.length;

			if (tamanio > 1)
				throw new Exception(
						this.getResourceMessage("errors.select.unique.item"));

			Map grupos = (Map) this.beanMantenimientoCRAGrupoZona[0];

			f.setNewRecord(false);
			String oid = grupos.get("codigo").toString();
			String desc = grupos.get("descripcion").toString();
			log.debug("setEditAttributes");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoCRAGrupoZonaService service = (MantenimientoCRAGrupoZonaService) getBean("spusicc.mantenimientoCRAGrupoZonaService");
			String id = oid;
			if (id != null) {
				if (log.isDebugEnabled()) {
					log.debug("Id seleccionado de la lista: " + id);
				}
				f.setCodigoPais(pais.getCodigo());
				f.setOidGrupo(oid);
				f.setNombreGrupo(desc);
				f.setOidGrupoFinal("");
				f.setGrupoFinal("");

				Map criteria = new HashMap();
				criteria.put("oidGrupo", "");

				String grupoZonaList[] = { f.getOidGrupo() };
				criteria.put("grupoZonaList", grupoZonaList);

				// Mover Entre Grupos
				this.craZonasGrupoInicial = service
						.getZonaAsignadasGrupo(criteria);
				this.craRegionNoAsignadasList = service
						.getGrupoRegionNoAsignadas();
				this.craZonaAsignadas = service.getZonaAsignadasGrupo(criteria);

				// Entre Grupos
				this.craGruposList = service.getGrupos(criteria);
				this.craZonasGrupoInicial.clear();
				this.craZonasGrupoInicial.addAll(craZonaAsignadas);

				// Asignar Disponible

				if (this.craZonaNoAsignadas != null) {
					for (int i = 0; i < this.craZonaNoAsignadas.length; i++) {
						Base b = new Base();
						b.setCodigo(this.craZonaNoAsignadas[i].getValue());
						b.setDescripcion(this.craZonaNoAsignadas[i].getLabel());
						this.craZonaNoAsignadasTemp.add(b);
					}

				}
				if (this.craZonasGrupoFinal != null) {
					for (int i = 0; i < this.craZonasGrupoFinal.length; i++) {
						Base b = new Base();
						b.setCodigo(this.craZonasGrupoFinal[i].getValue());
						b.setDescripcion(this.craZonasGrupoFinal[i].getLabel());
						this.craZonasGrupoFinalTemp.add(b);
					}
				}
				this.listaZonas = new DualListModel<Base>(
						this.craZonaNoAsignadasTemp, this.craZonaAsignadas);
				this.listaZonasEntreGrupos = new DualListModel<Base>(
						this.craZonasGrupoInicial, this.craZonasGrupoFinalTemp);
			}

			formMantenimiento = f;
			this.redireccionarPagina("mantenimientoCRAGrupoZonaForm");
		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param val
	 */
	public void getZonasGrupo(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		try {
			String grupos = (String) val.getNewValue();
			String[] valor = new String[] { grupos };
			if (val != null && grupos.length() > 0) {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.craZonasGrupoFinal = aSvc.getZonasGrupo(valor);
				this.craZonasGrupoFinalTemp.clear();
				for (int i = 0; i < this.craZonasGrupoFinal.length; i++) {
					Base b = new Base();
					b.setCodigo(this.craZonasGrupoFinal[i].getValue());
					b.setDescripcion(this.craZonasGrupoFinal[i].getLabel());
					this.craZonasGrupoFinalTemp.add(b);

				}
				this.craZonasGrupoFinal = null;
			} else {
				this.craZonasGrupoFinal = null;
				this.craZonasGrupoFinalTemp.clear();
			}
		} catch (Exception e) {
		}
	}

	/**
	 * @param event
	 */
	public void cambioTab(TabChangeEvent event) {
		try {
			String valorTab = event.getTab().getId();
			if (valorTab.equalsIgnoreCase("asignar")) {
				this.asignarDisponibleTab = true;
			} else
				this.asignarDisponibleTab = false;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("setViewAttributes");
		this.mostrarBotonConsultar = false;
		MantenimientoCRAGrupoZonaSearchForm f = (MantenimientoCRAGrupoZonaSearchForm) this.formBusqueda;
		MantenimientoCRAGrupoZonaService service = (MantenimientoCRAGrupoZonaService) getBean("spusicc.mantenimientoCRAGrupoZonaService");
		f.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		Map criteria = new HashMap();

		criteria.put("oidGrupo", "");

		List list = new ArrayList();
		list = service.getGrupos(criteria);

		this.craGruposList = list;
		this.craGruposComboList = list;
		f.setOidGrupo("");
		codigoUsuario = this.mPantallaPrincipalBean.getCurrentUser().getLogin();

		this.mostrarBotonBuscar = false;
		this.listaBusqueda = this.setFindAttributes();
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
		mostrarListaBusqueda = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonEliminar = false;

	}

	/**
	 * @param
	 * @throws Exception
	 */

	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoCRAGrupoZonaForm cobradorForm = (MantenimientoCRAGrupoZonaForm) this.formMantenimiento;
		boolean isNew = cobradorForm.isNewRecord();
		if (isNew) {
			return "mantenimientoCRAGrupoZonaForm.add.success";
		} else {
			return "mantenimientoCRAGrupoZonaForm.add.success";
		}
	}

	public String setValidarConfirmar(String accion) {
		if (accion.equals("ELIMINAR"))
			if (beanMantenimientoCRAGrupoZona.length == 0
					|| beanMantenimientoCRAGrupoZona == null)
				return this.getResourceMessage("errors.select.item");

		return null;
	}

	/**
	 * @return
	 */
	public List getSiccEtapaDeudaList() {
		return siccEtapaDeudaList;
	}

	/**
	 * @param siccEtapaDeudaList
	 */
	public void setSiccEtapaDeudaList(List siccEtapaDeudaList) {
		this.siccEtapaDeudaList = siccEtapaDeudaList;
	}

	/**
	 * @return the asignarDisponibleTab
	 */
	public boolean isAsignarDisponibleTab() {
		return asignarDisponibleTab;
	}

	/**
	 * @param asignarDisponibleTab
	 *            the asignarDisponibleTab to set
	 */
	public void setAsignarDisponibleTab(boolean asignarDisponibleTab) {
		this.asignarDisponibleTab = asignarDisponibleTab;
	}

	/**
	 * @return the craGruposComboList
	 */
	public List getCraGruposComboList() {
		return craGruposComboList;
	}

	/**
	 * @param craGruposComboList
	 *            the craGruposComboList to set
	 */
	public void setCraGruposComboList(List craGruposComboList) {
		this.craGruposComboList = craGruposComboList;
	}

	/**
	 * @return the craRegionNoAsignadasList
	 */
	public List getCraRegionNoAsignadasList() {
		return craRegionNoAsignadasList;
	}

	/**
	 * @param craRegionNoAsignadasList
	 *            the craRegionNoAsignadasList to set
	 */
	public void setCraRegionNoAsignadasList(List craRegionNoAsignadasList) {
		this.craRegionNoAsignadasList = craRegionNoAsignadasList;
	}

	/**
	 * @return the craZonaAsignadasEntreGrupo
	 */
	public List getCraZonaAsignadasEntreGrupo() {
		return craZonaAsignadasEntreGrupo;
	}

	/**
	 * @param craZonaAsignadasEntreGrupo
	 *            the craZonaAsignadasEntreGrupo to set
	 */
	public void setCraZonaAsignadasEntreGrupo(List craZonaAsignadasEntreGrupo) {
		this.craZonaAsignadasEntreGrupo = craZonaAsignadasEntreGrupo;
	}

	/**
	 * @return the craZonaNoAsignadasTemp
	 */
	public List getCraZonaNoAsignadasTemp() {
		return craZonaNoAsignadasTemp;
	}

	/**
	 * @param craZonaNoAsignadasTemp
	 *            the craZonaNoAsignadasTemp to set
	 */
	public void setCraZonaNoAsignadasTemp(List craZonaNoAsignadasTemp) {
		this.craZonaNoAsignadasTemp = craZonaNoAsignadasTemp;
	}

	/**
	 * @return the craZonaNoAsignadas
	 */
	public LabelValue[] getCraZonaNoAsignadas() {
		return craZonaNoAsignadas;
	}

	/**
	 * @param craZonaNoAsignadas
	 *            the craZonaNoAsignadas to set
	 */
	public void setCraZonaNoAsignadas(LabelValue[] craZonaNoAsignadas) {
		this.craZonaNoAsignadas = craZonaNoAsignadas;
	}

	/**
	 * @return the craZonaAsignadas
	 */
	public List getCraZonaAsignadas() {
		return craZonaAsignadas;
	}

	/**
	 * @param craZonaAsignadas
	 *            the craZonaAsignadas to set
	 */
	public void setCraZonaAsignadas(List craZonaAsignadas) {
		this.craZonaAsignadas = craZonaAsignadas;
	}

	/**
	 * @return the craZonasGrupoInicial
	 */
	public List getCraZonasGrupoInicial() {
		return craZonasGrupoInicial;
	}

	/**
	 * @param craZonasGrupoInicial
	 *            the craZonasGrupoInicial to set
	 */
	public void setCraZonasGrupoInicial(List craZonasGrupoInicial) {
		this.craZonasGrupoInicial = craZonasGrupoInicial;
	}

	/**
	 * @return the craZonasGrupoFinal
	 */
	public LabelValue[] getCraZonasGrupoFinal() {
		return craZonasGrupoFinal;
	}

	/**
	 * @param craZonasGrupoFinal
	 *            the craZonasGrupoFinal to set
	 */
	public void setCraZonasGrupoFinal(LabelValue[] craZonasGrupoFinal) {
		this.craZonasGrupoFinal = craZonasGrupoFinal;
	}

	/**
	 * @return the craZonasGrupoFinalTemp
	 */
	public List getCraZonasGrupoFinalTemp() {
		return craZonasGrupoFinalTemp;
	}

	/**
	 * @param craZonasGrupoFinalTemp
	 *            the craZonasGrupoFinalTemp to set
	 */
	public void setCraZonasGrupoFinalTemp(List craZonasGrupoFinalTemp) {
		this.craZonasGrupoFinalTemp = craZonasGrupoFinalTemp;
	}

	/**
	 * @return the craGruposList
	 */
	public List getCraGruposList() {
		return craGruposList;
	}

	/**
	 * @param craGruposList
	 *            the craGruposList to set
	 */
	public void setCraGruposList(List craGruposList) {
		this.craGruposList = craGruposList;
	}

	/**
	 * @return the zonaSelecionadas
	 */
	public List getZonaSelecionadas() {
		return zonaSelecionadas;
	}

	/**
	 * @param zonaSelecionadas
	 *            the zonaSelecionadas to set
	 */
	public void setZonaSelecionadas(List zonaSelecionadas) {
		this.zonaSelecionadas = zonaSelecionadas;
	}

	/**
	 * @return the listaZonas
	 */
	public DualListModel<Base> getListaZonas() {
		return listaZonas;
	}

	/**
	 * @param listaZonas
	 *            the listaZonas to set
	 */
	public void setListaZonas(DualListModel<Base> listaZonas) {
		this.listaZonas = listaZonas;
	}

	/**
	 * @return the listaZonasEntreGrupos
	 */
	public DualListModel<Base> getListaZonasEntreGrupos() {
		return listaZonasEntreGrupos;
	}

	/**
	 * @param listaZonasEntreGrupos
	 *            the listaZonasEntreGrupos to set
	 */
	public void setListaZonasEntreGrupos(
			DualListModel<Base> listaZonasEntreGrupos) {
		this.listaZonasEntreGrupos = listaZonasEntreGrupos;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public Object[] getBeanMantenimientoCRAGrupoZona() {
		return beanMantenimientoCRAGrupoZona;
	}

	public void setBeanMantenimientoCRAGrupoZona(
			Object[] beanMantenimientoCRAGrupoZona) {
		this.beanMantenimientoCRAGrupoZona = beanMantenimientoCRAGrupoZona;
	}

}