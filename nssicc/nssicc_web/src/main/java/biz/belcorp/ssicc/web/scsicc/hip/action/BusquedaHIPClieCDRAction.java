package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.SelectEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.service.AjaxService2;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.BusquedaHIPClienteSearchForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.action.BusquedaRECDocumentoReferenciaSearchAction;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.BusquedaRECDocumentoReferenciaSearchForm;

/**
 * <p>
 * <a href="BusquedaHIPClienteSearchAction.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
@ManagedBean  
@SessionScoped
public class BusquedaHIPClieCDRAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 1L;
	
	private String codigoCliente;	
	private String tipoDocIdentidad;
    private String numeroDocIdentidad;	
    private String nombre1;	
    private String nombre2;	
	private String apellido1;	
	private String apellido2;
	
	private String oidIdioma;
	private String longitudCodigoCliente;
	
	private String codigoPais;
	private String codigoRegion;
	private String codigoZona;
	private String criterio1;
	private String criterio2;
	
	private List<ConsultaHIPDatosCliente> listaResultado = new ArrayList<ConsultaHIPDatosCliente>();
	private ConsultaHIPDatosCliente selected = new ConsultaHIPDatosCliente();

	@ManagedProperty(value="#{busquedaRECDocumentoReferenciaSearchAction}")
	private BusquedaRECDocumentoReferenciaSearchAction busquedaRECDocumentoReferenciaSearchAction;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		BusquedaHIPClienteSearchForm busquedaHIPClienteSearchForm = new BusquedaHIPClienteSearchForm();	
		return busquedaHIPClienteSearchForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'BusquedaHIPClientePOPUPSearchAction - setViewAtributes' method");
		}
		
		BusquedaHIPClienteSearchForm searchForm = (BusquedaHIPClienteSearchForm) this.formBusqueda;
		
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService)this.getBeanService("spusicc.mantenimientoMAEClienteService"); 
				
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService)this.getBeanService("scsicc.consultaHIPDatosClienteService");
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.mPantallaPrincipalBean.getCountryCode());
		criteria.put("oidIdioma", this.mPantallaPrincipalBean.getOidIdiomaIso());		
		searchForm.setOidIdioma(this.mPantallaPrincipalBean.getOidIdiomaIso());
		
		this.mPantallaPrincipalBean.setSiccTipoDocumentoList(service.getTiposDocumento(criteria));
		limpiar(searchForm);
		
		//Verificamos si cargamos con busqueda por numero de documento
		Map<String, String> params = this.parametrosPantalla;
		String numeroDocumento = params.get("numeroDocumento");
		if(numeroDocumento != null){
			searchForm.setNumeroDocIdentidad(numeroDocumento);
			List resultado = service.getClientesByCriteria(getCriteriaSearch(searchForm));
			this.listaBusqueda = resultado;
		}
		
		//longitud de codigo de cliente para el pais
		searchForm.setLongitudCodigoCliente(clienteService.getLongitudCodigoCliente(criteria));
		searchForm.setCodigoRegion("");
		searchForm.setCodigoZona("");
				
		AjaxService2 ajaxService2 = (AjaxService2)this.getBeanService("ajaxService2");
		
		LabelValue[] regionesList = ajaxService2.getRegionesByPaisMarcaCanal(mPantallaPrincipalBean.getCountryCode(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT);
		
		mPantallaPrincipalBean.setSiccRegionList(Arrays.asList(regionesList));
		mPantallaPrincipalBean.setSiccZonaList(null);

		if (log.isDebugEnabled()) {
			log.debug("End 'BusquedaHIPClientePOPUPSearchAction - setViewAtributes' method");
		}
		
	}
	
	public void seleccionado(SelectEvent s){
		if(log.isDebugEnabled()){
			log.debug("seleccionado");			
		}
		try {
			BeanUtils.copyProperties(this.getSelected(), s);
			if(StringUtils.isNotBlank(this.getSelected().getCodigoCliente())
				&& StringUtils.isNotEmpty(this.getSelected().getCodigoCliente())){
				((BusquedaRECDocumentoReferenciaSearchForm)busquedaRECDocumentoReferenciaSearchAction.getFormBusqueda()).setCodigoClienteBuscar(this.getSelected().getCodigoCliente());
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void actualizarCamposXrmc(){
		if(log.isDebugEnabled()){
			log.debug("actualizarCamposXrmc");
		}
		BusquedaHIPClienteSearchForm searchForm = (BusquedaHIPClienteSearchForm) this.formBusqueda;
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> params = context.getExternalContext().getRequestParameterMap();
		
		//POR RMC
		this.setCodigoCliente(params.get("codigoCliente"));
		this.setNumeroDocIdentidad(params.get("numeroDocIdentidad"));
		this.setNombre1(params.get("nombre1"));
		this.setNombre2(params.get("nombre2"));
		this.setApellido1(params.get("apellido1"));
		this.setApellido2(params.get("apellido2"));
		
		searchForm.setCodigoCliente(this.getCodigoCliente());	
		searchForm.setTipoDocIdentidad(this.getTipoDocIdentidad());
		searchForm.setNumeroDocIdentidad(this.getNumeroDocIdentidad());	
		searchForm.setNombre1(this.getNombre1());	
		searchForm.setNombre2(this.getNombre2());	
		searchForm.setApellido1(this.getApellido1());	
		searchForm.setApellido2(this.getApellido2());
	}
	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'BusquedaHIPClientePOPUPSearchAction - setFindAttributes' method");
		}
		FacesContext context = FacesContext.getCurrentInstance();
		BusquedaHIPClienteSearchForm searchForm = (BusquedaHIPClienteSearchForm) this.formBusqueda;
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService)this.getBeanService("scsicc.consultaHIPDatosClienteService");
		
		if (StringUtils.isBlank(searchForm.getCodigoRegion()) && 
			StringUtils.isBlank(searchForm.getCodigoZona()) &&
			StringUtils.isBlank(searchForm.getCodigoCliente()) &&
			StringUtils.isBlank(searchForm.getNumeroDocIdentidad()) &&
			StringUtils.isBlank(searchForm.getNombre1()) &&
			StringUtils.isBlank(searchForm.getNombre2()) &&
			StringUtils.isBlank(searchForm.getApellido1()) &&
			StringUtils.isBlank(searchForm.getApellido2()) ) {
			
			String mensajeGrabarKey = this.getResourceMessage("busquedaHIPClienteSearchForm.error.criterios");
			this.setListaResultado(new ArrayList<ConsultaHIPDatosCliente>());			
			throw new Exception(mensajeGrabarKey);
		}
			
		List resultado = service.getClientesByCriteria(getCriteriaSearch(searchForm));
		log.debug("Pintando el tamaño de la lista " + resultado.size());
		
		List<ConsultaHIPDatosCliente> resultadoCopiado = new ArrayList<ConsultaHIPDatosCliente>();
		for (Object object : resultado) {
			ConsultaHIPDatosCliente destino = new ConsultaHIPDatosCliente();
			BeanUtils.copyProperties(destino, object);
			resultadoCopiado.add(destino);
		}
		this.setListaResultado(resultadoCopiado);
		return resultado;
				
	}
	
	/**
	 * se recupera los datos ingresados en la pantalla de busqueda de clientes
	 * 
	 * @param request
	 * @param searchForm
	 * @return
	 */
	private Map getCriteriaSearch(BusquedaHIPClienteSearchForm searchForm) {		
		Map criteria = new HashMap();
		
		Pais pais = mPantallaPrincipalBean.getCurrentCountry(); 
				
		criteria.put("codigoPais", pais.getCodigo() );
		criteria.put("oidIdioma", searchForm.getOidIdioma() );
		
		criteria.put("codigoCliente", searchForm.getCodigoCliente());
		criteria.put("tipoDocIdentidad", searchForm.getTipoDocIdentidad());
		criteria.put("numeroDocIdentidad", searchForm.getNumeroDocIdentidad());
		criteria.put("nombre1", searchForm.getNombre1());
		criteria.put("nombre2", searchForm.getNombre2());
		criteria.put("apellido1", searchForm.getApellido1());
		criteria.put("apellido2", searchForm.getApellido2());
		criteria.put("codigoRegion", searchForm.getCodigoRegion());		
		criteria.put("codigoZona", searchForm.getCodigoZona());

		return criteria;
	}
	
	/**
	 * Limpia los campos del formulario
	 * 
	 * @param searchForm
	 */
	private void limpiar(BusquedaHIPClienteSearchForm searchForm) {
		searchForm.setApellido1("");
		searchForm.setApellido2("");
		searchForm.setNombre1("");
		searchForm.setNombre2("");
		searchForm.setTipoDocIdentidad("");
		searchForm.setNumeroDocIdentidad("");
		searchForm.setCodigoCliente("");
		searchForm.setCodigoRegion("");
		searchForm.setCodigoZona("");
		
		this.setApellido1("");
		this.setApellido2("");
		this.setNombre1("");
		this.setNombre2("");
		this.setTipoDocIdentidad("");
		this.setNumeroDocIdentidad("");
		this.setCodigoCliente("");
		this.setCodigoRegion("");
		this.setCodigoZona("");

	}
	
	public void clearFormAll(ActionEvent actionEvent){
		BusquedaHIPClienteSearchForm f= (BusquedaHIPClienteSearchForm) this.formBusqueda;
		this.limpiar(f);
	}
	
	
	public void selectZonasMultipleByPaisMarcaCanalRegion(){
		if(log.isDebugEnabled()){
			log.debug("selectZonasMultipleByPaisMarcaCanalRegion");
		}
		BusquedaHIPClienteSearchForm f = (BusquedaHIPClienteSearchForm) this.formBusqueda;
		AjaxService2 ajaxService2 = (AjaxService2)this.getBeanService("ajaxService2");
		if(codigoRegion!=null && codigoRegion.length()>0){
			f.setCodigoRegion(codigoRegion);
			String[] regionesList = new String[1];
			regionesList[0] = codigoRegion;
			LabelValue[] zonasList = ajaxService2.getZonasMultipleByPaisMarcaCanalRegion(mPantallaPrincipalBean.getCountryCode(), 
					 Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, regionesList, 
					 Constants.EDU_FLAG_AJAX_COMBO_CODIGO_NOTODOS);			 
			this.mPantallaPrincipalBean.setSiccZonaList(Arrays.asList(zonasList));			 
		}else{
			this.setCodigoRegion(null);
			this.setCodigoZona(null);
			f.setCodigoRegion(null);
			f.setCodigoZona(null);
			this.mPantallaPrincipalBean.setSiccZonaList(null);
		} 
	}
	
	public void loadSeleccionZona(ValueChangeEvent e){
		if(log.isDebugEnabled()){
			log.debug("loadSeleccionZona");
		}
		String valor = (String)e.getNewValue();
		BusquedaHIPClienteSearchForm f = (BusquedaHIPClienteSearchForm) this.formBusqueda;
		log.debug("Codigo de zona seleccionado: " + valor);
		f.setCodigoZona(valor);
	}
	
	public void rmcBusquedaClie(){
		if(log.isDebugEnabled()){
			log.debug("rmcBusquedaClie");
		}
		try {
			this.actualizarCamposXrmc();
			this.setFindAttributes();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
	
	public void accionSalir(ActionEvent e){
		if(log.isDebugEnabled()){
			log.debug("accionSalir");
		}		
		this.getRequestContext().update("busquedaRECDocumentoReferencia:codigoCliente:input");
	}
	

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getTipoDocIdentidad() {
		return tipoDocIdentidad;
	}

	public void setTipoDocIdentidad(String tipoDocIdentidad) {
		this.tipoDocIdentidad = tipoDocIdentidad;
	}

	public String getNumeroDocIdentidad() {
		return numeroDocIdentidad;
	}

	public void setNumeroDocIdentidad(String numeroDocIdentidad) {
		this.numeroDocIdentidad = numeroDocIdentidad;
	}

	public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getOidIdioma() {
		return oidIdioma;
	}

	public void setOidIdioma(String oidIdioma) {
		this.oidIdioma = oidIdioma;
	}

	public String getLongitudCodigoCliente() {
		return longitudCodigoCliente;
	}

	public void setLongitudCodigoCliente(String longitudCodigoCliente) {
		this.longitudCodigoCliente = longitudCodigoCliente;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	public String getCodigoZona() {
		return codigoZona;
	}

	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	public String getCriterio1() {
		return criterio1;
	}

	public void setCriterio1(String criterio1) {
		this.criterio1 = criterio1;
	}

	public String getCriterio2() {
		return criterio2;
	}

	public void setCriterio2(String criterio2) {
		this.criterio2 = criterio2;
	}

	public List getListaResultado() {
		return listaResultado;
	}
	
	public ConsultaHIPDatosCliente getSelected() {
		return selected;
	}

	public void setSelected(ConsultaHIPDatosCliente selected) {
		this.selected = selected;
	}

	public BusquedaRECDocumentoReferenciaSearchAction getBusquedaRECDocumentoReferenciaSearchAction() {
		return busquedaRECDocumentoReferenciaSearchAction;
	}

	public void setBusquedaRECDocumentoReferenciaSearchAction(
			BusquedaRECDocumentoReferenciaSearchAction busquedaRECDocumentoReferenciaSearchAction) {
		this.busquedaRECDocumentoReferenciaSearchAction = busquedaRECDocumentoReferenciaSearchAction;
	}

	public void setListaResultado(List<ConsultaHIPDatosCliente> listaResultado) {
		this.listaResultado = listaResultado;
	}

}
