package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDNivelesRiesgoChequearService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDTipoChequeoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDNivelesRiesgoChequearSearchForm;

@SessionScoped
@ManagedBean
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoPEDNivelesRiesgoChequearSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 1L;
	private List pedTipoChequeoList;
	private List pedNivelRiesgo;
	private List pedNivelRiesgoList;
	private Object columnaSeleccionada;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoPEDNivelesRiesgoChequearSearchForm form = new MantenimientoPEDNivelesRiesgoChequearSearchForm();
		return form;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoPEDNivelesRiesgoChequearForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes...");
		}
		
		MantenimientoPEDNivelesRiesgoChequearService mantenimientoPEDNivelesRiesgoChequearService = (MantenimientoPEDNivelesRiesgoChequearService) getBean("spusicc.pedidos.mantenimientoPEDNivelesRiesgoChequearService");

		this.pedNivelRiesgoList = mantenimientoPEDNivelesRiesgoChequearService.getListaPEDNivelesRiesgoChequear();
		return this.pedNivelRiesgoList;
	}

	/**
	 * Metodo que controla la accion de insertar un nuevo registro
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void insertarRegistro(ActionEvent evt) {
		log.info("Entro a MantenimientoPEDNivelesRiesgoChequearSearchAction - insertarRegistro");

		MantenimientoPEDNivelesRiesgoChequearSearchForm f = (MantenimientoPEDNivelesRiesgoChequearSearchForm) this.formBusqueda;
		MantenimientoPEDNivelesRiesgoChequearService mantenimientoPEDNivelesRiesgoChequearService = (MantenimientoPEDNivelesRiesgoChequearService) getBean("spusicc.pedidos.mantenimientoPEDNivelesRiesgoChequearService");

		Map map = new HashMap();
		map.put("codigoTipoChequeo", f.getCodigoTipoChequeo());
		map.put("codigoNivelRiesgo", f.getCodigoNivelRiesgo());

		String mensaje = "";
		
		try {
			mantenimientoPEDNivelesRiesgoChequearService.insertPEDNivelesRiesgoChequear(map);

			this.pedNivelRiesgoList = mantenimientoPEDNivelesRiesgoChequearService.getListaPEDNivelesRiesgoChequear();
			this.listaBusqueda = this.pedNivelRiesgoList;
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);

			mensaje = "";
			mensaje = this.getResourceMessage("mantenimientoPEDNivelesRiesgoChequearSearchForm.add");

			this.addInfo("Info : ", mensaje);
		} catch (Exception e) {
			mensaje = this.getResourceMessage("mantenimientoPEDNivelesRiesgoChequearSearchForm.add.error");
			this.addError("Error : ", mensaje);
		}

		log.info("Salio a MantenimientoPEDNivelesRiesgoChequearSearchAction - insertarRegistro");
	}
	
	/**
	 * Metodo para borrar filas del datatable
	 * @param evt
	 */
	public void borrarFilas(ActionEvent evt) {
		log.info("Entro a MantenimientoPEDNivelesRiesgoChequearSearchAction - borrarFilas");
		String mensaje = "";
		
		try {
			HashMap ut = (HashMap) this.beanRegistroSeleccionado;
			
			MantenimientoPEDNivelesRiesgoChequearSearchForm f = (MantenimientoPEDNivelesRiesgoChequearSearchForm) this.formBusqueda;
			MantenimientoPEDNivelesRiesgoChequearService mantenimientoPEDNivelesRiesgoChequearService = (MantenimientoPEDNivelesRiesgoChequearService) getBean("spusicc.pedidos.mantenimientoPEDNivelesRiesgoChequearService");
			
			// String id = ut.get("").toString();
			String cadena[] = new String[2];
			cadena[0] = ut.get("cod_tipo_cheq").toString();
			cadena[1] = ut.get("cod_nive_ries").toString();

			Map map = new HashMap();
			map.put("codigoTipoChequeo", cadena[0].trim());
			map.put("codigoNivelRiesgo", cadena[1].trim());

			mantenimientoPEDNivelesRiesgoChequearService.deletePEDNivelesRiesgoChequear(map);

			this.pedNivelRiesgoList = mantenimientoPEDNivelesRiesgoChequearService.getListaPEDNivelesRiesgoChequear();
			this.listaBusqueda = this.pedNivelRiesgoList;
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);

			mensaje = this.getResourceMessage("mantenimientoPEDNivelesRiesgoChequearSearchForm.deleted");
			this.addInfo("Info : ", mensaje);
			
			f.setCodigoTipoChequeo("");
			f.setCodigoNivelRiesgo("");
			
			log.info("Salio a MantenimientoPEDNivelesRiesgoChequearSearchAction - borrarFilas");
		} catch (Exception e) {
			mensaje = this.getResourceMessage("mantenimientoPEDNivelesRiesgoChequearSearchForm.add.error");
			this.addError("Error : ", mensaje);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {

		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}

	/**
	 * Inicializando valores
	 */
	public void inicializar() {
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonBuscar = false;
		this.mostrarListaBusqueda = true;
		this.listaBusqueda = new ArrayList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.info("Entro a MantenimientoPEDNivelesRiesgoChequearSearchAction - setViewAttributes");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.inicializar();
		
		MantenimientoPEDNivelesRiesgoChequearSearchForm f = (MantenimientoPEDNivelesRiesgoChequearSearchForm) this.formBusqueda;
		MantenimientoPEDTipoChequeoService mantenimientoPEDTipoChequeoService = (MantenimientoPEDTipoChequeoService) getBean("spusicc.pedidos.mantenimientoPEDTipoChequeoService");
		MantenimientoPEDNivelesRiesgoChequearService mantenimientoPEDNivelesRiesgoChequearService = (MantenimientoPEDNivelesRiesgoChequearService) getBean("spusicc.pedidos.mantenimientoPEDNivelesRiesgoChequearService");

		this.pedNivelRiesgoList = mantenimientoPEDNivelesRiesgoChequearService.getListaPEDNivelesRiesgoChequear();

		this.find();
		this.listaBusqueda = this.pedNivelRiesgoList;

		f.setCodigoTipoChequeo("");
		f.setCodigoNivelRiesgo("");
		f.setCodigoPais(pais.getCodigo());
		
		this.pedNivelRiesgoList = mantenimientoPEDNivelesRiesgoChequearService.getListaPEDNivelesRiesgoChequear();
		this.pedTipoChequeoList = mantenimientoPEDTipoChequeoService.getTipoChequeoAll();
		this.pedNivelRiesgo = mantenimientoPEDNivelesRiesgoChequearService.getNivelRiesgo();
		this.pedNivelRiesgoList = mantenimientoPEDNivelesRiesgoChequearService.getListaPEDNivelesRiesgoChequear();
		
		log.info("Salio a MantenimientoPEDNivelesRiesgoChequearSearchAction - setViewAttributes");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		if(log.isDebugEnabled()){
			log.debug("setValidarConfirmar...");
		}
		
		MantenimientoPEDNivelesRiesgoChequearSearchForm f = (MantenimientoPEDNivelesRiesgoChequearSearchForm) this.formBusqueda;
		
		if(StringUtils.equals(accion, "AGREGAR")){
			if(StringUtils.isBlank(f.getCodigoTipoChequeo())){
				return "Seleccionar Tipo Chequeo";
			}
			
			if(StringUtils.isBlank(f.getCodigoNivelRiesgo())){
				return "Seleccionar un Nivel Riesgo";
			}
		}
		
		if(StringUtils.equals(accion, "CERRAR")){
			HashMap ut = (HashMap) this.beanRegistroSeleccionado;
			
			if(ut == null){
				return "Seleccione un elemento de la lista";
			}
		}
		
		return super.setValidarConfirmar(accion);
	}
	
	/**
	 * @return the pedTipoChequeoList
	 */
	public List getPedTipoChequeoList() {
		return pedTipoChequeoList;
	}

	/**
	 * @param pedTipoChequeoList
	 *            the pedTipoChequeoList to set
	 */
	public void setPedTipoChequeoList(List pedTipoChequeoList) {
		this.pedTipoChequeoList = pedTipoChequeoList;
	}

	/**
	 * @return the pedNivelRiesgo
	 */
	public List getPedNivelRiesgo() {
		return pedNivelRiesgo;
	}

	/**
	 * @param pedNivelRiesgo
	 *            the pedNivelRiesgo to set
	 */
	public void setPedNivelRiesgo(List pedNivelRiesgo) {
		this.pedNivelRiesgo = pedNivelRiesgo;
	}

	/**
	 * @return the pedNivelRiesgoList
	 */
	public List getPedNivelRiesgoList() {
		return pedNivelRiesgoList;
	}

	/**
	 * @param pedNivelRiesgoList
	 *            the pedNivelRiesgoList to set
	 */
	public void setPedNivelRiesgoList(List pedNivelRiesgoList) {
		this.pedNivelRiesgoList = pedNivelRiesgoList;
	}

	/**
	 * @return the columnaSeleccionada
	 */
	public Object getColumnaSeleccionada() {
		return columnaSeleccionada;
	}

	/**
	 * @param columnaSeleccionada
	 *            the columnaSeleccionada to set
	 */
	public void setColumnaSeleccionada(Object columnaSeleccionada) {
		this.columnaSeleccionada = columnaSeleccionada;
	}
}