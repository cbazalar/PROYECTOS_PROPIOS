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
import biz.belcorp.ssicc.web.sisicc.form.InterfazLAREnvioDocumentosCabeceraComplementoForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazDATEnviarAdministracionFlujosAction.java.html">
 * <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 * 
  */
@ManagedBean
@SessionScoped
public class InterfazLAREnvioDocumentosCabeceraComplementoAction extends BaseInterfazAbstractAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3203182676172552747L;
	


	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazLAREnvioDocumentosCabeceraComplementoForm interfazLAREnvioDocumentosCabeceraComplementoForm = new InterfazLAREnvioDocumentosCabeceraComplementoForm();
		return interfazLAREnvioDocumentosCabeceraComplementoForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'setViewAttributes' method");
        }
        InterfazLAREnvioDocumentosCabeceraComplementoForm f = (InterfazLAREnvioDocumentosCabeceraComplementoForm)this.formInterfaz;
        PedidoControlFacturacion periodo =getCodigoPeriodo();
        f.setCodigoPeriodo(periodo.getCodigoPeriodo());
	}

	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */





	private PedidoControlFacturacion getCodigoPeriodo() {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
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
