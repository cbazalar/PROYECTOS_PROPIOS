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
public class BusquedaHIPClientePOPUPSearchAction extends BasePopupAbstractAction {

	private static final long serialVersionUID = 8093699536678451434L;

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
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'BusquedaHIPClientePOPUPSearchAction - setFindAttributes' method");
		}
		
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
			throw new Exception(mensajeGrabarKey);
		}
			
		List resultado = service.getClientesByCriteria(getCriteriaSearch(searchForm));
		log.debug("Pintando el tamaño de la lista " + resultado.size());
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
		if(StringUtils.isNotBlank(searchForm.getCodigoRegion())){
			if(searchForm.getCodigoRegion().length()>0)
				criteria.put("codigoZona", searchForm.getCodigoZona());
		}		

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

}
