/**
 * 
 */
package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazOCREnviarOrdenesTransporteForm;

/**
 * @author fochoa
 *
 */

@ManagedBean
@SessionScoped
public class InterfazOCREnviarOrdenesTransporteAction extends BaseInterfazAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7289617510054167343L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazOCREnviarOrdenesTransporteForm form = new InterfazOCREnviarOrdenesTransporteForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		
        InterfazOCREnviarOrdenesTransporteForm f = (InterfazOCREnviarOrdenesTransporteForm)this.formInterfaz ; 
        MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
        PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		
		// Carga Fecha y Periodo
        f.setFechaFacturacion(controlFacturacion.getFechaProceso());
        f.setFechaFacturacionDate(DateUtil.convertStringToDate(f.getFechaFacturacion()));
        f.setPeriodo(controlFacturacion.getCodigoPeriodo());
	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		params =  super.prepareParamsBeforeExecute(params, form);		
		InterfazOCREnviarOrdenesTransporteForm f = (InterfazOCREnviarOrdenesTransporteForm)this.formInterfaz ;
		f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionDate()));
		params.put("fechaFacturacion", f.getFechaFacturacion());
		
		return params;
		
	}
	
	

}
