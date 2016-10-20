package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRCargaPedidoService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

@ManagedBean  
@SessionScoped
public class MantenimientoOCRCargaPedidosAction extends BasePopupAbstractAction{
	
	private static final long serialVersionUID = -6048435060620880744L;
	
	private Object registroPrincipal;
	
	

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoOCRCargaPedidoService service = (MantenimientoOCRCargaPedidoService)getBean("spusicc.pedidos.mantenimientoOCRCargaPedidoService");      	
		
		LabelDatosConsultoraValue registro= new LabelDatosConsultoraValue();
		registro=(LabelDatosConsultoraValue)this.registroPrincipal;
		
		Map filter = new HashMap();			
		filter.put("codigoPais", registro.getCodPais());
		filter.put("codigoPeriodo", registro.getPeriodoFacturacion());			
		filter.put("codigoCliente", registro.getCodigoConsultora());
		filter.put("numLote", registro.getNumLote());
		
		List list = service.getDetallePedidoByFilter(filter);
		return list;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		this.mostrarListaBusqueda = false;
		
	}

	public Object getRegistroPrincipal() {
		return registroPrincipal;
	}

	public void setRegistroPrincipal(Object registroPrincipal) {
		this.registroPrincipal = registroPrincipal;
	}
	
	

}
