package biz.belcorp.ssicc.web.spncd.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spncd.MantenimientoCUPProgDsctoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spncd.form.MantenimientoCUPRegistrarExcepcionesSearchForm;

@SessionScoped
@ManagedBean
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoCUPRegistrarExcepcionesSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -1118361217941725769L;
	private List cupExcepcionesList;
	private List cupProgramasList = new ArrayList();
	private List siccRegionList;
	private LabelValue[] siccZonaList;
	private DataTableModel listaEstructuraArchivoModel;
	private Object[] columnasSeleccionadas;
	

	/**
	 * @param f
	 * @return
	 */
	private Map getCriteria(MantenimientoCUPRegistrarExcepcionesSearchForm f) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoPrograma", f.getCodigoPrograma());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		if(f.getCodigoRegion()!=null && f.getCodigoRegion().length>0 && StringUtils.isNotEmpty(f.getCodigoRegion()[0])){
			criteria.put("codigoRegion", f.getCodigoRegion());
		} 
		if(f.getCodigoZona()!=null && f.getCodigoZona().length>0 && StringUtils.isNotEmpty(f.getCodigoZona()[0])){
			criteria.put("codigoZona", f.getCodigoZona());
		}
		return criteria;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoCUPRegistrarExcepcionesList";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCUPRegistrarExcepcionesSearchForm form = new MantenimientoCUPRegistrarExcepcionesSearchForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setFindAttributes' method");
		}
		MantenimientoCUPRegistrarExcepcionesSearchForm f = (MantenimientoCUPRegistrarExcepcionesSearchForm)this.formBusqueda;
		Map criteria = getCriteria(f);
		MantenimientoCUPProgDsctoService serviceCupones = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		List listaExcepciones = serviceCupones.getExcepciones(criteria);
		this.cupExcepcionesList = listaExcepciones;	
		return listaExcepciones;
	}

//	@Override
//	protected boolean setDeleteAttributes() throws Exception {
//		if (log.isDebugEnabled()) {
//			log.debug("Entering 'setDeleteAttributes' method");
//		}
//		MantenimientoCUPRegistrarExcepcionesSearchForm f = (MantenimientoCUPRegistrarExcepcionesSearchForm)this.formBusqueda;
//		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
////		service.deleteExcepciones(f.getSelectedItems());
//		//setFindAttributes(request,form);
//	}

	public void saveBean(){
		try {
			MantenimientoCUPRegistrarExcepcionesSearchForm f = (MantenimientoCUPRegistrarExcepcionesSearchForm)this.formBusqueda;
			Map criteria = getCriteria(f);
			MantenimientoCUPProgDsctoService serviceCupones = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
			serviceCupones.insertExcepciones(criteria);
			String mensaje = this.getResourceMessage("confirm.save.information");
			this.addInfo("Info :", mensaje);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'guardar' method");
		}
		MantenimientoCUPRegistrarExcepcionesSearchForm f = (MantenimientoCUPRegistrarExcepcionesSearchForm)this.formBusqueda;
		Map criteria = getCriteria(f);
		MantenimientoCUPProgDsctoService serviceCupones = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		serviceCupones.insertExcepciones(criteria);
		return true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}
	
	/**
	 * Obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		try {
			MantenimientoCUPRegistrarExcepcionesSearchForm form = (MantenimientoCUPRegistrarExcepcionesSearchForm) this.formBusqueda;
			String[] regiones = (String[]) val.getNewValue();
			if (!val.equals(null) && regiones.length > 0) {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.siccZonaList = aSvc.getZonasMultipleByPaisMarcaCanalRegion(
						form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, regiones,
						Constants.FORMATO_TOTAL);
				form.setCodigoZona(null);
			} else {
				this.siccZonaList = null;
				form.setCodigoZona(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		Pais pais =  this.mPantallaPrincipalBean.getCurrentCountry();
		
		this.mostrarBotonConsultar = false;
		this.mostrarBotonSave = true;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoCUPProgDsctoService serviceCupones = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		
		MantenimientoCUPRegistrarExcepcionesSearchForm f = (MantenimientoCUPRegistrarExcepcionesSearchForm)this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		//Seteo periodo por defecto
		f.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));
		
		//Cargo combo de cupones
		this.cupProgramasList = serviceCupones.getProgramasDescuentosbyPais(criteriaOperacion);
		Base valor = (Base) this.cupProgramasList.get(0);
		f.setCodigoPrograma(valor.getCodigo());
		//Cargo la region, zona		
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		MantenimientoCUPRegistrarExcepcionesSearchForm f = (MantenimientoCUPRegistrarExcepcionesSearchForm)this.formBusqueda;
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		String[] arrayString = new String [1]; 		
		arrayString[0] = f.getCodigoPais() + "|" + f.getCodigoPeriodo() + "|" +f.getCodigoPrograma() + "|" + f.getCodigoRegion() + "|" + f.getCodigoZona();
		
		service.deleteExcepciones(arrayString);		
//		String mensaje = this.getResourceMessage("confirm.delete.messag");
//		this.addInfo("Info :", mensaje);
		return true;
	}

	/**
	 * @return the cupExcepcionesList
	 */
	public List getCupExcepcionesList() {
		return cupExcepcionesList;
	}

	/**
	 * @param cupExcepcionesList the cupExcepcionesList to set
	 */
	public void setCupExcepcionesList(List cupExcepcionesList) {
		this.cupExcepcionesList = cupExcepcionesList;
	}

	/**
	 * @return the cupProgramasList
	 */
	public List getCupProgramasList() {
		return cupProgramasList;
	}

	/**
	 * @param cupProgramasList the cupProgramasList to set
	 */
	public void setCupProgramasList(List cupProgramasList) {
		this.cupProgramasList = cupProgramasList;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}
	/**
	 * @return the listaEstructuraArchivoModel
	 */
	public DataTableModel getListaEstructuraArchivoModel() {
		return listaEstructuraArchivoModel;
	}

	/**
	 * @param listaEstructuraArchivoModel the listaEstructuraArchivoModel to set
	 */
	public void setListaEstructuraArchivoModel(
			DataTableModel listaEstructuraArchivoModel) {
		this.listaEstructuraArchivoModel = listaEstructuraArchivoModel;
	}

	/**
	 * @return the columnasSeleccionadas
	 */
	public Object[] getColumnasSeleccionadas() {
		return columnasSeleccionadas;
	}

	/**
	 * @param columnasSeleccionadas the columnasSeleccionadas to set
	 */
	public void setColumnasSeleccionadas(Object[] columnasSeleccionadas) {
		this.columnasSeleccionadas = columnasSeleccionadas;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}
}