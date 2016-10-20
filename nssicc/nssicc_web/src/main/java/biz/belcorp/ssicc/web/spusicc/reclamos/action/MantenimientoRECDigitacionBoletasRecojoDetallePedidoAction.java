package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections.MapUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECDigitacionBoletasRecojoDetallePedidoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoRECDigitacionBoletasRecojoDetallePedidoAction extends BasePopupAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6916787649270624246L;
	
	private String oidSoliCabecera;
	private List hipDetallePedidosConsultoraList;
	private DataTableModel datatableModel;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoRECDigitacionBoletasRecojoDetallePedidoForm form = new MantenimientoRECDigitacionBoletasRecojoDetallePedidoForm();
		return form;
	}
		
	@Override
	protected List setFindAttributes() throws Exception 
	{
		MantenimientoRECDigitacionBoletasRecojoDetallePedidoForm f = (MantenimientoRECDigitacionBoletasRecojoDetallePedidoForm)this.formBusqueda;
		Map criteria = new HashMap();
		criteria.put("oidSoliCabecera" , this.oidSoliCabecera);
		criteria.put("codigoVentaLog" , f.getCodigoVenta());
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		List detallePedidoList = service.getDetallePedido(criteria);
		
//		this.hipDetallePedidosConsultoraList = detallePedidoList;
		
		return detallePedidoList;	
	}
	
	@Override
	protected void setViewAtributes() throws Exception 
	{
		
	}	
	
	public void inicializar(ActionEvent event)
	{
		try {
			MantenimientoRECDigitacionBoletasRecojoDetallePedidoForm f = (MantenimientoRECDigitacionBoletasRecojoDetallePedidoForm)this.formBusqueda;
			
			ExternalContext externalContext = FacesContext.getCurrentInstance()	.getExternalContext();
			String valor = externalContext.getRequestParameterMap().get("valor").toString();
			
			this.oidSoliCabecera = valor;
			Map criteria = new HashMap();
			
			criteria.put("oidSoliCabecera" , this.oidSoliCabecera);
			ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
//			List detallePedidoList = service.getDetallePedido(criteria);
			
			Map resultado = service.getPeriodoNumeroPedido(criteria);
			
			f.setNumeroPedido(MapUtils.getString(resultado, "numeroPedido"));
			f.setCodigoPeriodo(MapUtils.getString(resultado, "codigoPeriodo"));
			f.setCodigoVenta("");
			
			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			this.mostrarListaBusqueda = false;				
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}			
	}
	
	public String getOidSoliCabecera() {
		return oidSoliCabecera;
	}

	public void setOidSoliCabecera(String oidSoliCabecera) {
		this.oidSoliCabecera = oidSoliCabecera;
	}

	public List getHipDetallePedidosConsultoraList() {
		return hipDetallePedidosConsultoraList;
	}

	public void setHipDetallePedidosConsultoraList(
			List hipDetallePedidosConsultoraList) {
		this.hipDetallePedidosConsultoraList = hipDetallePedidosConsultoraList;
	}

	public DataTableModel getDatatableModel() {
		return datatableModel;
	}

	public void setDatatableModel(DataTableModel datatableModel) {
		this.datatableModel = datatableModel;
	}	
}
