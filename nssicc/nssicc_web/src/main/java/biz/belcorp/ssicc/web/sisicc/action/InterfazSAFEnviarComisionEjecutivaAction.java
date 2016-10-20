package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSAFEnviarComisionEjecutivaForm;

@ManagedBean
@SessionScoped
public class InterfazSAFEnviarComisionEjecutivaAction extends BaseInterfazAbstractAction{

	private static final long serialVersionUID = 9208954718525742123L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazSAFEnviarComisionEjecutivaForm interfazForm= new  InterfazSAFEnviarComisionEjecutivaForm();
		return interfazForm;
	}
	
	@Override
	 protected void setViewAtributes() throws Exception {
	     if (log.isDebugEnabled()) {
	           log.debug("Entering 'setViewAttributes' method");
	     }       
			
			/* obteniendo codigo de periodo actual */
		InterfazSAFEnviarComisionEjecutivaForm f = (InterfazSAFEnviarComisionEjecutivaForm) this.formInterfaz;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
			
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
	    criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
	    criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());	
			
		}
	
	@Override
    protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		params = super.prepareParamsBeforeExecute(params, form);						
		return params;
    }
}
