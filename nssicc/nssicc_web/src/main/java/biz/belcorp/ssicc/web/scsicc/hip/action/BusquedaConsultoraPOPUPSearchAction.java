package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.form.BusquedaConsultoraSearchForm;

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
@SuppressWarnings({"rawtypes", "unchecked"})
public class BusquedaConsultoraPOPUPSearchAction extends BasePopupAbstractAction {

	private static final long serialVersionUID = 1L;
	
	private DataTableModel dataTableModel = new DataTableModel();
	private String longitudCampoClientes;
	

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		BusquedaConsultoraSearchForm f = new BusquedaConsultoraSearchForm();
		return f;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes");
		}		
		
        BusquedaConsultoraSearchForm searchForm = (BusquedaConsultoraSearchForm) this.formBusqueda;
		
		List resultado = new ArrayList();
		log.debug(searchForm.toString());
		

		Map criteria = BeanUtils.describe(searchForm);
		criteria.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		
		if (StringUtils.isBlank(searchForm.getCodigoConsultora()) &&
			StringUtils.isBlank(searchForm.getApellidoPaterno()) &&
			StringUtils.isBlank(searchForm.getApellidoMaterno()) &&
			StringUtils.isBlank(searchForm.getNombre())
		  ) {	
			String error = this.getResourceMessage("errors.criteria.required");
			throw new Exception(error);
		}

		if (StringUtils.isNotBlank(searchForm.getCodigoConsultora())) {
			criteria.put("codigo", searchForm.getCodigoConsultora().trim() + "%");
		}
		if (StringUtils.isNotBlank(searchForm.getApellidoPaterno())) {
			criteria.put("apellidoPaterno", searchForm.getApellidoPaterno().trim() + "%");
		}
		if (StringUtils.isNotBlank(searchForm.getApellidoMaterno())) {
			criteria.put("apellidoMaterno", searchForm.getApellidoMaterno().trim() + "%");
		}
		if (StringUtils.isNotBlank(searchForm.getNombre())) {
			criteria.put("nombre", searchForm.getNombre().trim() + "%");
		}

		// La busqueda solo la realizaremos en las interfaces activas
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		resultado = service.getConsultorasByCriteria(criteria);
		return resultado;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		BusquedaConsultoraSearchForm searchForm = (BusquedaConsultoraSearchForm) this.formBusqueda;
		ClienteUAGenerarService svc = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		this.longitudCampoClientes  = svc.getTamanhoNumeroCliente(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo()).toString();
		searchForm.setApellidoPaterno("");
		searchForm.setApellidoMaterno("");
		searchForm.setNombre("");
		searchForm.setCodigoConsultora("");
		
	}

	
	
	/**
	 * @return the dataTableModel
	 */
	public DataTableModel getDataTableModel() {
		return dataTableModel;
	}

	/**
	 * @param dataTableModel the dataTableModel to set
	 */
	public void setDataTableModel(DataTableModel dataTableModel) {
		this.dataTableModel = dataTableModel;
	}

	public String getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	public void setLongitudCampoClientes(String longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}
}