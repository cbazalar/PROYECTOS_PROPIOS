package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.util.Arrays;
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
import biz.belcorp.ssicc.service.AjaxService2;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.BusquedaHIPClienteSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class BusquedaHIPClienteSearchAction extends BasePopupAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7045990073820589333L;
	
	private List busquedaClientesList;

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		BusquedaHIPClienteSearchForm formPopup = new BusquedaHIPClienteSearchForm();
		return formPopup;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
				
		BusquedaHIPClienteSearchForm searchForm = (BusquedaHIPClienteSearchForm) this.formBusqueda;		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");		
		List resultado = service.getClientesByCriteria(getCriteriaSearch(searchForm));

		log.debug("Pintando el tamaño de la lista " + resultado.size());

		this.busquedaClientesList = resultado;	
		return resultado;
	}


	@Override
	protected void setViewAtributes() throws Exception 
	{
		cargarVista();
	}
	
	public void cargarVista() throws Exception 
	{
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// Removemos la lista de clientes
		this.busquedaClientesList = null;

		BusquedaHIPClienteSearchForm searchForm = (BusquedaHIPClienteSearchForm) this.formBusqueda;

		// comente yo String oidIdioma = request.getParameter("oidIdioma");

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("oidIdioma", searchForm.getOidIdioma());
		// searchForm.setOidIdioma(oidIdioma);
		//
		this.mPantallaPrincipalBean.setSiccTipoDocumentoList(service.getTiposDocumento(criteria));
		limpiar(searchForm);

		// Verificamos si cargamos con busqueda por numero de documento
		// String numeroDocumento = request.getParameter("numeroDocumento");
		Map<String, String> params = this.parametrosPantalla;
		String numeroDocumento = params.get("numeroDocumento");
		if (numeroDocumento != null) {
			searchForm.setNumeroDocIdentidad(numeroDocumento);
			List resultado = service
					.getClientesByCriteria(getCriteriaSearch(searchForm));
			this.busquedaClientesList = resultado;
		}

		// longitud de codigo de cliente para el pais
		searchForm.setLongitudCodigoCliente(clienteService
				.getLongitudCodigoCliente(criteria));
		searchForm.setCodigoRegion("");
		searchForm.setCodigoZona("");

		AjaxService2 ajaxService2 = (AjaxService2) this
				.getBeanService("ajaxService2");

		LabelValue[] regionesList = ajaxService2.getRegionesByPaisMarcaCanal(
				mPantallaPrincipalBean.getCountryCode(),
				Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT);

		mPantallaPrincipalBean.setSiccRegionList(Arrays.asList(regionesList));
		mPantallaPrincipalBean.setSiccZonaList(null);
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
	}

	/**
	 * se recupera los datos ingresados en la pantalla de busqueda de clientes
	 * 
	 * @param searchForm
	 * @return
	 */
	private Map getCriteriaSearch(BusquedaHIPClienteSearchForm searchForm) {
		Map criteria = new HashMap();
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
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
		if(searchForm.getCodigoRegion()!=null && searchForm.getCodigoRegion().length()>0)
			criteria.put("codigoZona", searchForm.getCodigoZona());

		criteria.put("codigoConexionExterna", pais.getCodigoConexionExterna());
		
		return criteria;
	}
	
	/**
	 * @param valueChangeEvent
	 */
	public void selectZonasMultipleByPaisMarcaCanalRegion(ValueChangeEvent event){
		
		log.debug("selectZonasMultipleByPaisMarcaCanalRegion method");

		// Obtenemos la lista de zonas segun region seleccionado		 
		 AjaxService2 ajaxService2 = (AjaxService2)this.getBeanService("ajaxService2");
		 	 
		 String codigoRegion = (String) event.getNewValue();
		 
		 if(codigoRegion!=null && codigoRegion.length()>0){			 
			 String[] regionesList = new String[1];
			 regionesList[0] = codigoRegion; 
			 LabelValue[] zonasList = ajaxService2.getZonasMultipleByPaisMarcaCanalRegion(mPantallaPrincipalBean.getCountryCode(), 
					 Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, regionesList, 
					 Constants.EDU_FLAG_AJAX_COMBO_CODIGO_NOTODOS);			 
			 this.mPantallaPrincipalBean.setSiccZonaList(Arrays.asList(zonasList));			 
		 }else{
			 this.mPantallaPrincipalBean.setSiccZonaList(null);
		 } 
		
	}
	
	@Override
	public String setValidarFind() 
	{
		String mensaje = "";
		BusquedaHIPClienteSearchForm f = (BusquedaHIPClienteSearchForm) this.formBusqueda;
		
		if(StringUtils.isBlank(f.getCodigoRegion()) && StringUtils.isBlank(f.getCodigoZona()) 
				&& StringUtils.isBlank(f.getCodigoCliente()) && StringUtils.isBlank(f.getTipoDocIdentidad()) &&
				StringUtils.isBlank(f.getNumeroDocIdentidad()) && StringUtils.isBlank(f.getNombre1()) 
				&& StringUtils.isBlank(f.getNombre2()) && StringUtils.isBlank(f.getApellido1()) 
				&& StringUtils.isBlank(f.getApellido2()))
		{
			mensaje = this.getResourceMessage("busquedaHIPClienteSearchForm.error.criterios");			
			return mensaje;
		}
		
		return mensaje;		
	}
	
	public List getBusquedaClientesList() {
		return busquedaClientesList;
	}

	public void setBusquedaClientesList(List busquedaClientesList) {
		this.busquedaClientesList = busquedaClientesList;
	}
	

}
