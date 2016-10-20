/**
 * 
 */
package biz.belcorp.ssicc.web.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.service.spncd.MantenimientoCUPProgDsctoService;
import biz.belcorp.ssicc.web.form.BusquedaProductoMatrizSearchForm;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

@ManagedBean  
@SessionScoped
public class BusquedaProductoMatrizSearchAction extends BasePopupAbstractAction {

	private static final long serialVersionUID = -3478915411467067714L;
	private List busquedaProductoMatrizList;
	private String codigoPeriodo;


	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		BusquedaProductoMatrizSearchForm form = new BusquedaProductoMatrizSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		

		BusquedaProductoMatrizSearchForm searchForm = (BusquedaProductoMatrizSearchForm) this.formBusqueda;
		//ActionMessages messages = new ActionMessages();
		searchForm.setCodigoPeriodo(codigoPeriodo);

		Map criteria = BeanUtils.describe(searchForm);
		
		log.debug("criteria:"+criteria.toString());

		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");		

		List resultado = service.getProductosMatrizByCriteria(criteria);
		
		if (resultado.size()>0)
			for (int i =0;i<resultado.size();i++)
			log.debug("i"+i+":"+resultado.get(i));		

		busquedaProductoMatrizList = resultado;
		if (resultado.size() == 0){
			String mensaje = this.getResourceMessage("busquedaProductoMatrizList.notfound");
			addError("Error:", mensaje);
		}

		//saveMessages(request.getSession(), messages);

		//return mapping.findForward("list");
		return resultado;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		BusquedaProductoMatrizSearchForm busquedaSearchProductos = (BusquedaProductoMatrizSearchForm) this.formBusqueda;

		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String parametria= externalContext.getRequestParameterMap().get("parametroParametria");
		//String codigoPeriodo = parametria;
			
		//busquedaSearchProductos.setCodigoPeriodo(parametria);
		busquedaSearchProductos.setCodigoVenta("");
		busquedaSearchProductos.setCodigoSap("");
		busquedaSearchProductos.setDescripcionCorta("");
	}

	/**
	 * @return the busquedaProductoMatrizList
	 */
	public List getBusquedaProductoMatrizList() {
		return busquedaProductoMatrizList;
	}

	/**
	 * @param busquedaProductoMatrizList the busquedaProductoMatrizList to set
	 */
	public void setBusquedaProductoMatrizList(List busquedaProductoMatrizList) {
		this.busquedaProductoMatrizList = busquedaProductoMatrizList;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
}