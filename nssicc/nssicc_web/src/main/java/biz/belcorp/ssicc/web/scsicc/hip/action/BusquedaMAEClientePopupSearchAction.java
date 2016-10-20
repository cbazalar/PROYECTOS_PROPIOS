package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.BusquedaMAEClienteSearchForm;

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
public class BusquedaMAEClientePopupSearchAction extends BasePopupAbstractAction {

	private static final long serialVersionUID = 1L;
	private List zonaList = new ArrayList();

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		BusquedaMAEClienteSearchForm BusquedaMAEClienteSearchForm = new BusquedaMAEClienteSearchForm();	
		return BusquedaMAEClienteSearchForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'BusquedaHIPClientePOPUPSearchAction - setViewAtributes' method");
		}
		
		BusquedaMAEClienteSearchForm searchForm = (BusquedaMAEClienteSearchForm) this.formBusqueda;
		
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService)this.getBeanService("spusicc.mantenimientoMAEClienteService"); 
				
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService)this.getBeanService("scsicc.consultaHIPDatosClienteService");
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.mPantallaPrincipalBean.getCountryCode());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
        criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("codigoISO", this.getmPantallaPrincipalBean().getCurrentIdioma().getCodigoISO());		
		this.zonaList = clienteService.getZonasByPaisMarcaCanal(criteria);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'BusquedaHIPClientePOPUPSearchAction - setFindAttributes' method");
		}
		
		BusquedaMAEClienteSearchForm searchForm = (BusquedaMAEClienteSearchForm) this.formBusqueda;			
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService)this.getBeanService("scsicc.consultaHIPDatosClienteService");
		
		if (StringUtils.isBlank(searchForm.getCodigoZona()) &&
			StringUtils.isBlank(searchForm.getCodigoCliente()) &&
			StringUtils.isBlank(searchForm.getNumeroDocIdentidad()) &&
			StringUtils.isBlank(searchForm.getNombre1()) &&
			StringUtils.isBlank(searchForm.getNombre2()) &&
			StringUtils.isBlank(searchForm.getApellido1()) &&
			StringUtils.isBlank(searchForm.getApellido2()) &&
			StringUtils.isBlank(searchForm.getCriterioBusqueda1()) &&
			StringUtils.isBlank(searchForm.getCriterioBusqueda2())) {
//			String mensajeGrabarKey = this.getResourceMessage("BusquedaMAEClienteSearchForm.error.criterios"); 
//			throw new Exception(mensajeGrabarKey);
		}
		
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		List resultado = clienteService.getClientesByCriteria(getCriteriaSearch());
		
		if(resultado.size() == 0){
			String mensajeGrabarKey = this.getResourceMessage("busquedaClienteList.notfound"); 
			this.addInfo("Info:", mensajeGrabarKey);
			return null;
		}
		
		log.debug("Pintando el tamaño de la lista " + resultado.size());
		return resultado;
	}
	
	
	/**
	 * Limpia los campos del formulario
	 * 
	 * @param searchForm
	 */
	private void limpiar(BusquedaMAEClienteSearchForm searchForm) {
		searchForm.setApellido1("");
		searchForm.setApellido2("");
		searchForm.setNombre1("");
		searchForm.setNombre2("");
		searchForm.setNumeroDocIdentidad("");
		searchForm.setCodigoCliente("");
	}
	
	/**
	 * @param valueChangeEvent
	 */
	public void selectZonasMultipleByPaisMarcaCanalRegion(ValueChangeEvent event){
		
		log.debug("selectZonasMultipleByPaisMarcaCanalRegion method");
		BusquedaMAEClienteSearchForm searchForm = (BusquedaMAEClienteSearchForm) this.formBusqueda;
		log.debug("" + searchForm.getCodigoZona());
		
	}

	private Map getCriteriaSearch() {
		Map criteria = new HashMap();
		BusquedaMAEClienteSearchForm searchForm = (BusquedaMAEClienteSearchForm) this.formBusqueda;
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo());
		
		criteria.put("oidPais", clienteService.getOidPais(criteria));		
		criteria.put("codigoCliente", searchForm.getCodigoCliente());
		criteria.put("nombre1", searchForm.getNombre1());
		criteria.put("nombre2", searchForm.getNombre2());
		criteria.put("apellido1", searchForm.getApellido1());
		criteria.put("apellido2", searchForm.getApellido2());
		criteria.put("criterioBusqueda1", searchForm.getCriterioBusqueda1());
		criteria.put("criterioBusqueda2", searchForm.getCriterioBusqueda2());
		criteria.put("documentoIdentidad", searchForm.getNumeroDocIdentidad());
		criteria.put("codigoZona", searchForm.getCodigoZona());
		return criteria;
	}
	
	public List getZonaList() {
		return zonaList;
	}

	public void setZonaList(List zonaList) {
		this.zonaList = zonaList;
	}

}
