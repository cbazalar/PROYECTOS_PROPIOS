/*
 * Created on 26/09/2006 11:32:06 AM
 * biz.belcorp.ssicc.sisicc.web.action.InterfazMYEEnviarMovimientosCuentaCorrienteAction
 */
package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazLAREnvioEstimadosYobelForm;


@ManagedBean
@SessionScoped
public class InterfazLAREnvioEstimadosYobelAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = -3203189676172552747L;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		return new InterfazLAREnvioEstimadosYobelForm();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
	        log.debug("Entering 'setViewAttributes' method");
	    }
		
		 Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		 InterfazLAREnvioEstimadosYobelForm f = (InterfazLAREnvioEstimadosYobelForm) this.formInterfaz;
	     PedidoControlFacturacion periodo =getCodigoPeriodo(pais);
	     f.setCodigoPeriodo(periodo.getCodigoPeriodo());
	}
	
	

	private PedidoControlFacturacion getCodigoPeriodo(Pais pais) {

		Map criteriaPeriodo = new HashMap();
		MantenimientoOCRPedidoControlFacturacionService service = 
			 (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.NUMERO_UNO);
	    PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);
	    log.debug("periodo actual " + controlFacturacion.getCodigoPeriodo());
	    
		return controlFacturacion;
	}
	

	
    

}
