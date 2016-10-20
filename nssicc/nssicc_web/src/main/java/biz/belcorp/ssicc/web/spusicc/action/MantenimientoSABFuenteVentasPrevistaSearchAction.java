/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.FuenteVentasService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoSABFuenteVentasPrevistaSearchForm;

/**
 * @author Sigcomt
 *
 */
@ManagedBean
@SessionScoped
public class MantenimientoSABFuenteVentasPrevistaSearchAction extends BaseMantenimientoSearchAbstractAction{
	
	
	private List siccSociedadList = new ArrayList();
	private List siccMarcaList = new ArrayList();
	private List siccAlmacenList = new ArrayList();
	private List siccCanalList = new ArrayList();
	private List siccRangoPeriodoList = new ArrayList();
	private LabelValue [] siccRegionList ={};
	private LabelValue [] siccZonaList ={};
	private boolean viewEdit;
	private boolean viewModificar;	
	private ArrayList listaFuentesVenta;
	


	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSABFuenteVentasPrevistaSearchForm form = new MantenimientoSABFuenteVentasPrevistaSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setFindAttributes' method");
		}
		
		MantenimientoSABFuenteVentasPrevistaSearchForm searchForm = (MantenimientoSABFuenteVentasPrevistaSearchForm) this.formBusqueda;

		// Obtenemos las propiedades del bean como un 'Map'
		Map criteria = BeanUtils.describe(searchForm);
		// La busqueda solo la realizaremos en las interfaces activas
		criteria.put("estado", Constants.ESTADO_ACTIVO);

		FuenteVentasService service = (FuenteVentasService) getBean("sisicc.fuenteVentasService");
		
		// Obtenemmos el periodo Inicio y Fin
		String periodoInicio = service.getPeriodoInicio(searchForm.getCodigoRangoPeriodo());
		String periodoFin = service.getPeriodoFin(searchForm.getCodigoRangoPeriodo());
		String periodoMenor = searchForm.getCodigoAnio() + periodoInicio;
		String periodoMayor = searchForm.getCodigoAnio() + periodoFin;

		criteria.put("periodoMenor", periodoMenor);
		criteria.put("periodoMayor", periodoMayor);
		criteria.put("periodoInicio", periodoInicio);
		criteria.put("periodoFin", periodoFin);

		String codigoRegionElegido = searchForm.getCodigoRegion();
		String codigoZonaElegida = searchForm.getCodigoZona();

//		session.setAttribute("codigoRegionElegido", codigoRegionElegido);
//		session.setAttribute("codigoZonaElegida", codigoZonaElegida);

		// Realizamos la búsqueda con los criterios ingresados
		List listaFuenteVentas = service.getFuenteVentasByCriteria(criteria);

		// Usuario usuario = getUsuario(request.getSession());

		if (listaFuenteVentas == null) { // service.insertNuevaConfiguracion(criteria,usuario);
			listaFuenteVentas = service.getFuenteVentasByCriteria(criteria);
		}

		if (listaFuenteVentas != null) {
			if (listaFuenteVentas.size() == 0) { // service.insertNuevaConfiguracion(criteria,usuario);
				listaFuenteVentas = service.getFuenteVentasByCriteria(criteria);
			}
		}
		
		ArrayList listaFuenteVentasAux = new ArrayList(listaFuenteVentas);
		log.debug("tamano del size: "+ listaFuenteVentasAux.size()); //efernandezo
		if (listaFuenteVentasAux.size() == 6)
			{searchForm.listaACampos(listaFuenteVentasAux);
			this.viewEdit = true;
			this.viewModificar = true;}
		else {
			this.viewEdit = false;
			this.viewModificar = false;
		}
		
		this.listaFuentesVenta = listaFuenteVentasAux;
		
		return listaFuentesVenta;

	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
		if (log.isDebugEnabled()) {
            log.debug("Entering 'fuenteVentasPrevista' method");
        }
		// Removemos el form bean
		MantenimientoSABFuenteVentasPrevistaSearchForm searchForm = (MantenimientoSABFuenteVentasPrevistaSearchForm) this.formBusqueda;
		
		// Obtenemos los beans básicos de la sesión
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map params = BeanUtils.describe(searchForm);
		params.clear();
		params.put("codigoISO", usuario.getIdioma().getCodigoISO());
		params.put("codigoPais", pais.getCodigo());
		
		// Cargamos los combos a utilizar
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccSociedadList = svc.getSociedadesByCodigoPais(pais.getCodigo());
		this.siccMarcaList = svc.getMarcas();
		this.siccAlmacenList = svc.getAlmacenesByCodigoISOPais(params);
		this.siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccRangoPeriodoList = svc.getRangosPeriodo();
		Base codigoRangoPeriodo = (Base) this.siccRangoPeriodoList.get(0);
		searchForm.setCodigoRangoPeriodo(codigoRangoPeriodo.getCodigo());
		
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		this.siccRegionList = ajax.getRegionesByPaisMarcaCanal( pais.getCodigo(),Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT);
		searchForm.setCodigoRegion(this.siccRegionList[0].getValue());
		
		this.siccZonaList = ajax.getZonasByPaisCanalRegion( pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT, searchForm.getCodigoRegion());
		searchForm.setCodigoZona(this.siccZonaList[0].getValue());
		this.viewEdit= false; 
		this.viewModificar = false;
		
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarListaBusqueda = false;

		// searchForm.inicializar();
		// La busqueda solo la realizaremos en los sistemas activos
//		session.removeAttribute("codigoRegionElegido");
//		session.removeAttribute("codigoZonaElegida");

//		return mapping.findForward("list");

		
	}
	
	public void modificar(ActionEvent actionEvent){
		this.viewModificar = false;
		this.viewEdit=true;
		this.mostrarBotonBuscar=false;

		
	}
	
	public void regresar(ActionEvent actionEvent){
		this.viewEdit=false;
		this.mostrarBotonBuscar=true;
		
	}
	
	public void loadRegionesByMarca(ValueChangeEvent event){
		if (log.isDebugEnabled()) {
			log.debug("loadRegionesByMarca");
		}
		String marca = (String)event.getNewValue();
		MantenimientoSABFuenteVentasPrevistaSearchForm searchForm = (MantenimientoSABFuenteVentasPrevistaSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		this.siccRegionList = ajax.getRegionesByPaisMarcaCanal( pais.getCodigo(),marca, searchForm.getCodigoCanal());
		this.siccZonaList = null;
		
	}
	
	public void loadRegionesByCanal(ValueChangeEvent event){
		if (log.isDebugEnabled()) {
			log.debug("loadRegionesByCanal");
		}
		String canal = (String)event.getNewValue();
		MantenimientoSABFuenteVentasPrevistaSearchForm searchForm = (MantenimientoSABFuenteVentasPrevistaSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		this.siccRegionList = ajax.getRegionesByPaisMarcaCanal( pais.getCodigo(),searchForm.getCodigoMarca(), canal);
		this.siccZonaList = null;
		
	}
	
	public void loadZonas(ValueChangeEvent event){
		
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		String region = (String) event.getNewValue();
		MantenimientoSABFuenteVentasPrevistaSearchForm searchForm = (MantenimientoSABFuenteVentasPrevistaSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		this.siccZonaList = ajax.getZonasByPaisCanalRegion( pais.getCodigo(), searchForm.getCodigoCanal(), region);
	}

	/**
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccAlmacenList
	 */
	public List getSiccAlmacenList() {
		return siccAlmacenList;
	}

	/**
	 * @param siccAlmacenList the siccAlmacenList to set
	 */
	public void setSiccAlmacenList(List siccAlmacenList) {
		this.siccAlmacenList = siccAlmacenList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the siccRangoPeriodoList
	 */
	public List getSiccRangoPeriodoList() {
		return siccRangoPeriodoList;
	}

	/**
	 * @param siccRangoPeriodoList the siccRangoPeriodoList to set
	 */
	public void setSiccRangoPeriodoList(List siccRangoPeriodoList) {
		this.siccRangoPeriodoList = siccRangoPeriodoList;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
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

	/**
	 * @return the viewEdit
	 */
	public boolean isViewEdit() {
		return viewEdit;
	}

	/**
	 * @param viewEdit the viewEdit to set
	 */
	public void setViewEdit(boolean viewEdit) {
		this.viewEdit = viewEdit;
	}

	/**
	 * @return the viewModificar
	 */
	public boolean isViewModificar() {
		return viewModificar;
	}

	/**
	 * @param viewModificar the viewModificar to set
	 */
	public void setViewModificar(boolean viewModificar) {
		this.viewModificar = viewModificar;
	}

	/**
	 * @return the listaFuentesVenta
	 */
	public ArrayList getListaFuentesVenta() {
		return listaFuentesVenta;
	}

	/**
	 * @param listaFuentesVenta the listaFuentesVenta to set
	 */
	public void setListaFuentesVenta(ArrayList listaFuentesVenta) {
		this.listaFuentesVenta = listaFuentesVenta;
	}
	
	
}
