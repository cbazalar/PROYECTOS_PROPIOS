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
import biz.belcorp.ssicc.web.sisicc.form.InterfazADAEnviarInformacionAdamForm;

/**
 * The Class InterfazADAEnviarInformacionAdamAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 03/12/2014
 */
@ManagedBean
@SessionScoped
public class InterfazADAEnviarInformacionAdamAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = -5276636567145859345L;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazADAEnviarInformacionAdamForm interfazADAEnviarForm = new InterfazADAEnviarInformacionAdamForm();
		return interfazADAEnviarForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		
		InterfazADAEnviarInformacionAdamForm f = (InterfazADAEnviarInformacionAdamForm) this.formInterfaz;		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		
		MantenimientoOCRPedidoControlFacturacionService serviceCF = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService"); 
        PedidoControlFacturacion controlFacturacion = null; 
        
        Map criteriaPeriodo = new HashMap();        
        criteriaPeriodo.put("codigoPais", pais.getCodigo());
        criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
        criteriaPeriodo.put("indicadorActiva", Constants.NUMERO_UNO);

        controlFacturacion = serviceCF.getControlFacturacionById(criteriaPeriodo);
        
        f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
        f.setCodigoPais(pais.getCodigo());
	}
	
}