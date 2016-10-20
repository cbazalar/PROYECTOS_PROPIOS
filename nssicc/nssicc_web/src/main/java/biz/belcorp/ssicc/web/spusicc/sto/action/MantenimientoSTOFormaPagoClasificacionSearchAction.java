package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOFormaPagoClasificacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOFormaPagoClasificacionForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOFormaPagoClasificacionSearchForm;

/**
 * The Class MantenimientoSTOFormaPagoClasificacionSearchAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 13/02/2015
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@ManagedBean
@SessionScoped
public class MantenimientoSTOFormaPagoClasificacionSearchAction extends BaseMantenimientoSearchAbstractAction {
	
	private static final long serialVersionUID = 2870186612322988068L;
	private List siccRegionList;
	private List siccTipoClienteList;
	private List siccFormaPagoList;
	private List siccSubTipoClienteList;
	private List siccTipoClasificacionList;
	private List siccClasificacionList;
	private List siccZonaList;
	private List stoLevantamientoErroresClientesList;
	private List stoResumenClientesList;	
	private String codigoIdiomaISO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoSTOFormaPagoClasificacionForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOFormaPagoClasificacionSearchForm searchForm = new MantenimientoSTOFormaPagoClasificacionSearchForm();
		return searchForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		
		log.info("MantenimientoSTOFormaPagoClasificacionSearchAction - setViewAttributes");
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		
		MantenimientoSTOFormaPagoClasificacionSearchForm f = (MantenimientoSTOFormaPagoClasificacionSearchForm) this.formBusqueda;
		
		cleanForm(f);
		f.setCodigoPais(pais.getCodigo());
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		criteriaOperacion.put("oidPais", reporteService.getOidString("getOidPaisByCodigoPais", criteriaOperacion));

		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);		
		this.siccTipoClienteList = interfazSiCCService.getTiposClientesByCodigoISOOID(usuario.getIdioma().getCodigoISO());		
		this.siccFormaPagoList = reporteService.getListaGenerico("getListaFormaPago",criteriaOperacion);
		this.siccSubTipoClienteList = new ArrayList();
		this.siccTipoClasificacionList = new ArrayList();
		this.siccClasificacionList = new ArrayList();
		this.siccZonaList = new ArrayList();
		
		this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();
		
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		
		log.debug("MantenimientoSTOFormaPagoClasificacionSearchAction - setFindAttributes");
		
		MantenimientoSTOFormaPagoClasificacionSearchForm f = (MantenimientoSTOFormaPagoClasificacionSearchForm) this.formBusqueda;
		
		ReporteService reporteService = (ReporteService)getBean("scsicc.reporteService");
		MantenimientoSTOFormaPagoClasificacionService service =(MantenimientoSTOFormaPagoClasificacionService)getBean("sto.mantenimientoSTOFormaPagoClasificacionService");
		MantenimientoMAEClienteService clienteService =(MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
	
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		Map criteria = new HashMap();
		
		criteria.put("codigoPais", f.getCodigoPais());
		String oidPais = clienteService.getOidPais(criteria);
		criteria.put("oidPais", oidPais);
		criteria.put("codigoIso", usuario.getIdioma().getCodigoISO());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		
		if(StringUtils.isNotBlank(f.getCodigoCUV())){
			criteria.put("codigoCUV", f.getCodigoCUV());
			criteria.put("oidDetaOfer", reporteService.getOidString("getOidDetaOferByCodigoCUVPeriodo", criteria));
			}
		else 
			criteria.put("oidDetaOfer", null);
		
		if(StringUtils.isNotBlank(f.getCodigoPeriodo()))
			criteria.put("oidPeriodo", reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria));
		else 
			criteria.put("oidPeriodo", null);
		
		if(StringUtils.isNotBlank(f.getOidTipoCliente()))
			criteria.put("oidTipoCliente", Integer.valueOf(f.getOidTipoCliente()));
		else
			criteria.put("oidTipoCliente", null);
		
		if(StringUtils.isNotBlank(f.getOidSubTipoCliente())){
			criteria.put("oidSubTipoCliente", Integer.valueOf(f.getOidSubTipoCliente()));
			//request.setAttribute("oidSubTipoCliente", criteria.get("oidSubTipoCliente"));
		}else{
			criteria.put("oidSubTipoCliente", null);
			//request.setAttribute("oidSubTipoCliente", null);
		}
		
		if(StringUtils.isNotBlank(f.getOidTipoClasificacion())){
			criteria.put("oidSubTipoClasificacion", Integer.valueOf(f.getOidTipoClasificacion()));
			//request.setAttribute("oidSubTipoClasificacion", criteria.get("oidSubTipoClasificacion"));
		}else{
			criteria.put("oidSubTipoClasificacion", null);
			//request.setAttribute("oidSubTipoClasificacion",null);
		}
		
		if(StringUtils.isNotBlank(f.getOidClasificacion())){
			criteria.put("oidClasificacion", Integer.valueOf(f.getOidClasificacion()));
			//request.setAttribute("oidClasificacion", criteria.get("oidClasificacion"));
		}else{
			criteria.put("oidClasificacion", null);
			//request.setAttribute("oidClasificacion", null);
		}
		
		if(StringUtils.isNotBlank(f.getCodigoRegion())){
			criteria.put("codigoRegion", f.getCodigoRegion());
			criteria.put("oidRegion", reporteService.getOidRegionByPaisMarcaCanal(criteria));
		}else
			criteria.put("oidRegion", null);
		
		if(StringUtils.isNotBlank(f.getCodigoZona()) && StringUtils.isNotBlank(f.getCodigoRegion())){
			criteria.put("codigoZona", f.getCodigoZona());
			criteria.put("oidZona", reporteService.getOidZonaByPaisMarcaCanalRegion(criteria));
			//request.setAttribute("codigoZona", criteria.get("codigoZona"));
		}else{ 
			criteria.put("oidZona", null);
			//request.setAttribute("codigoZona", null);
		}

		if(StringUtils.isNotBlank(f.getFormaPago()))
			criteria.put("oidFormaPago", f.getFormaPago());
		else 
			criteria.put("oidFormaPago", null);
		

		List lista = new ArrayList();		
		lista = service.getFormaPagoClasificacionList(criteria);
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoSTOFormaPagoClasificacionSearchForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {

		log.debug("MantenimientoSTOFormaPagoClasificacionSearchAction - setDeleteAttributes");
		
		MantenimientoSTOFormaPagoClasificacionSearchForm f = (MantenimientoSTOFormaPagoClasificacionSearchForm) this.formBusqueda;	
		MantenimientoSTOFormaPagoClasificacionService service =  (MantenimientoSTOFormaPagoClasificacionService)getBean("sto.mantenimientoSTOFormaPagoClasificacionService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map parametros = new HashMap();
		
		Map mapa = (Map) this.beanRegistroSeleccionado;
		String[] selectedItem = new String[1];
		selectedItem[0] = mapa.get("oid").toString();
		parametros.put("idSeleccionados", selectedItem);
		parametros.put("usuario", usuario.getLogin());
		parametros.put("codPais", f.getCodigoPais());
		service.deleteFormaPagoClasificacion(parametros);
		
		this.getResourceMessage("estructuraArchivo.deleted");

		return true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		log.debug("MantenimientoSTOFormaPagoClasificacionAction - setAddAttributes");
		MantenimientoSTOFormaPagoClasificacionForm f = (MantenimientoSTOFormaPagoClasificacionForm) this.formMantenimiento;
		
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String msgExisteOidDetaOferta = ajax.getExisteOidDetaOferta(f.getCodigoCUV(), f.getCodigoPeriodo());
		if(StringUtils.isNotEmpty(msgExisteOidDetaOferta)) {
			this.addInfo("Info:", this.getResourceMessage("mantenimientoSTOFormaPagoClasificacionForm.message"));
			return false;
		}		
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		
		
		ReporteService reporteService = (ReporteService)getBean("scsicc.reporteService");
		MantenimientoSTOFormaPagoClasificacionService service = (MantenimientoSTOFormaPagoClasificacionService)
																	getBean("sto.mantenimientoSTOFormaPagoClasificacionService");

		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoCUV", f.getCodigoCUV());
		criteria.put("codigoIso", usuario.getIdioma().getCodigoISO());
		criteria.put("usuario", usuario.getLogin());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		
		if(StringUtils.isNotBlank(f.getCodigoPeriodo()))
			criteria.put("oidDetaOfer", reporteService.getOidString("getOidDetaOferByCodigoCUVPeriodo", criteria));
		else 
			criteria.put("oidDetaOfer", null);
		
		if(StringUtils.isNotBlank(f.getCodigoPeriodo()))
			criteria.put("oidPeriodo", reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria));
		else 
			criteria.put("oidPeriodo", null);
		
		if(StringUtils.isNotBlank(f.getCodigoPeriodo()))
			criteria.put("oidFormPago", f.getFormaPago());
		else 
			criteria.put("oidFormPago", null);

		if(StringUtils.isNotBlank(f.getOidTipoCliente()))
			criteria.put("oidTipoCliente", f.getOidTipoCliente());
		else
			criteria.put("oidTipoCliente", null);

		if(StringUtils.isNotBlank(f.getOidSubTipoCliente())){
			criteria.put("oidSubTipoCliente", f.getOidSubTipoCliente());
			//request.setAttribute("oidSubTipoCliente", criteria.get("oidSubTipoCliente"));
		}else{
			criteria.put("oidSubTipoCliente", null);
			//request.setAttribute("oidSubTipoCliente", null);
		}

		if(StringUtils.isNotBlank(f.getOidTipoClasificacion())){
			criteria.put("oidSubTipoClasificacion", f.getOidTipoClasificacion());
			//request.setAttribute("oidSubTipoClasificacion", criteria.get("oidSubTipoClasificacion"));
		}else{
			criteria.put("oidSubTipoClasificacion", null);
			//request.setAttribute("oidSubTipoClasificacion",null);
		}

		if(StringUtils.isNotBlank(f.getOidClasificacion())){
			criteria.put("oidClasificacion", f.getOidClasificacion());
			//request.setAttribute("oidClasificacion", criteria.get("oidClasificacion"));
		}else{
			criteria.put("oidClasificacion", null);
			//request.setAttribute("oidClasificacion", null);
		}

		if(StringUtils.isNotBlank(f.getCodigoRegion())){
			criteria.put("codigoRegion", f.getCodigoRegion());
			criteria.put("oidRegion", reporteService.getOidRegionByPaisMarcaCanal(criteria));
		}else
			criteria.put("oidRegion", null);

		if(StringUtils.isNotBlank(f.getCodigoZona())){
			criteria.put("codigoZona", f.getCodigoZona());
			criteria.put("oidZona", reporteService.getOidZonaByPaisMarcaCanalRegion(criteria));
			//request.setAttribute("codigoZona", criteria.get("codigoZona"));
		}else{ 
			criteria.put("oidZona", null);
			//request.setAttribute("codigoZona", null);
		}

		criteria.put("fechaCreacion", Calendar.getInstance().getTime());
		criteria.put("observaciones", f.getObservaciones());

		/*-------------------------*/
	
		service.insertFormaPagoClasificacion(criteria);

		//session.setAttribute("indicadorGraboPagoClasificacion", Constants.NUMERO_UNO);

		return true;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		MantenimientoSTOFormaPagoClasificacionForm f = new MantenimientoSTOFormaPagoClasificacionForm();		
		return f;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		return "proceso.ok";
	}
	
	
	/**
	 * Limpia los campos del formulario
	 * @param f
	 */
	private void cleanForm(MantenimientoSTOFormaPagoClasificacionSearchForm f) {
		
		f.setOidTipoCliente("");
		f.setOidSubTipoCliente("");  
		f.setOidTipoClasificacion("");
		f.setOidClasificacion("");
		f.setCodigoRegion("");
		f.setCodigoZona("");
		f.setCodigoPeriodo("");
		f.setCodigoCUV("");
		
	}
	
	/**
	 * loadSubTiposClientes 
	 * @param event 
	 */
	public void loadSubTiposClientes(ValueChangeEvent event) {
		ArrayList tipoCliente = new ArrayList();
		tipoCliente.add(event.getNewValue().toString());
    	if(StringUtils.isNotBlank(event.getNewValue().toString())) {
    		AjaxService ajax = (AjaxService) getBean("ajaxService");
        	this.siccSubTipoClienteList = Arrays.asList(ajax.getSubTiposClientesPorPaisTipoClientesOID(this.codigoIdiomaISO, tipoCliente));
		} else {
			this.siccSubTipoClienteList = new ArrayList();
		}
	}
	
	/**
	 * loadTiposClasificaciones 
	 * @param event 
	 */
	public void loadTiposClasificaciones(ValueChangeEvent event) {
		MantenimientoSTOFormaPagoClasificacionSearchForm f = (MantenimientoSTOFormaPagoClasificacionSearchForm) this.formBusqueda;
		ArrayList subTipoCliente = new ArrayList();
		subTipoCliente.add(event.getNewValue().toString());
    	if(StringUtils.isNotBlank(event.getNewValue().toString())) {
    		AjaxService ajax = (AjaxService) getBean("ajaxService");
        	this.siccTipoClasificacionList = Arrays.asList(ajax.getTiposClasificacionesByCriteriaMultipleOID(this.codigoIdiomaISO,
        																								     f.getOidTipoCliente(),
        																								     subTipoCliente));
    	} else {
			this.siccTipoClasificacionList = new ArrayList();
		}	
	}
	
	/**
	 * loadClasificaciones 
	 * @param event 
	 */
	public void loadClasificaciones(ValueChangeEvent event) {
		MantenimientoSTOFormaPagoClasificacionSearchForm f = (MantenimientoSTOFormaPagoClasificacionSearchForm) this.formBusqueda;
		ArrayList valuesClasificacion = new ArrayList();
		valuesClasificacion.add(event.getNewValue().toString());
		
		ArrayList codigoSubTipoCliente = new ArrayList();
		codigoSubTipoCliente.add(f.getOidSubTipoCliente());
    	if(StringUtils.isNotBlank(event.getNewValue().toString())) {
    		AjaxService ajax = (AjaxService) getBean("ajaxService");
    		this.siccClasificacionList = Arrays.asList(ajax.getClasificacionesByCriteriaMultipleOID(this.codigoIdiomaISO,
																				    				f.getOidTipoCliente(), 
																				    				codigoSubTipoCliente,
																				    				valuesClasificacion));
    	} else {
			this.siccClasificacionList = new ArrayList();
		}	
	}
	
	/**
	 * loadZonas 
	 * @param event 
	 */
	public void loadZonas(ValueChangeEvent event) {
		MantenimientoSTOFormaPagoClasificacionSearchForm f = (MantenimientoSTOFormaPagoClasificacionSearchForm) this.formBusqueda;
		String values[] = new String[1];
		values[0] = event.getNewValue().toString();
    	if(StringUtils.isNotBlank(event.getNewValue().toString())) {
    		AjaxService ajax = (AjaxService) getBean("ajaxService");
        	this.siccZonaList = Arrays.asList(ajax.getZonasMultipleByPaisMarcaCanalRegion(f.getCodigoPais(), "T", "VD", values, ""));
    	} else {
			this.siccZonaList = new ArrayList();
		}	
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
	 * @return the siccTipoClienteList
	 */
	public List getSiccTipoClienteList() {
		return siccTipoClienteList;
	}

	/**
	 * @param siccTipoClienteList the siccTipoClienteList to set
	 */
	public void setSiccTipoClienteList(List siccTipoClienteList) {
		this.siccTipoClienteList = siccTipoClienteList;
	}

	/**
	 * @return the siccFormaPagoList
	 */
	public List getSiccFormaPagoList() {
		return siccFormaPagoList;
	}

	/**
	 * @param siccFormaPagoList the siccFormaPagoList to set
	 */
	public void setSiccFormaPagoList(List siccFormaPagoList) {
		this.siccFormaPagoList = siccFormaPagoList;
	}

	/**
	 * @return the siccSubTipoClienteList
	 */
	public List getSiccSubTipoClienteList() {
		return siccSubTipoClienteList;
	}

	/**
	 * @param siccSubTipoClienteList the siccSubTipoClienteList to set
	 */
	public void setSiccSubTipoClienteList(List siccSubTipoClienteList) {
		this.siccSubTipoClienteList = siccSubTipoClienteList;
	}

	/**
	 * @return the siccTipoClasificacionList
	 */
	public List getSiccTipoClasificacionList() {
		return siccTipoClasificacionList;
	}

	/**
	 * @param siccTipoClasificacionList the siccTipoClasificacionList to set
	 */
	public void setSiccTipoClasificacionList(List siccTipoClasificacionList) {
		this.siccTipoClasificacionList = siccTipoClasificacionList;
	}

	/**
	 * @return the siccClasificacionList
	 */
	public List getSiccClasificacionList() {
		return siccClasificacionList;
	}

	/**
	 * @param siccClasificacionList the siccClasificacionList to set
	 */
	public void setSiccClasificacionList(List siccClasificacionList) {
		this.siccClasificacionList = siccClasificacionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public List getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the stoLevantamientoErroresClientesList
	 */
	public List getStoLevantamientoErroresClientesList() {
		return stoLevantamientoErroresClientesList;
	}

	/**
	 * @param stoLevantamientoErroresClientesList the stoLevantamientoErroresClientesList to set
	 */
	public void setStoLevantamientoErroresClientesList(
			List stoLevantamientoErroresClientesList) {
		this.stoLevantamientoErroresClientesList = stoLevantamientoErroresClientesList;
	}

	/**
	 * @return the stoResumenClientesList
	 */
	public List getStoResumenClientesList() {
		return stoResumenClientesList;
	}

	/**
	 * @param stoResumenClientesList the stoResumenClientesList to set
	 */
	public void setStoResumenClientesList(List stoResumenClientesList) {
		this.stoResumenClientesList = stoResumenClientesList;
	}

	/**
	 * @return the codigoIdiomaISO
	 */
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	/**
	 * @param codigoIdiomaISO the codigoIdiomaISO to set
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}

}