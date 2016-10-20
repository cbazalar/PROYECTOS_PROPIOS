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
import biz.belcorp.ssicc.web.sisicc.form.InterfazSAMEnviarMovimientosAlmacenSiccForm;

@ManagedBean
@SessionScoped
public class InterfazSAMEnviarMovimientosAlmacenSiccAction extends BaseInterfazAbstractAction{
	
	private static final long serialVersionUID = -5574678097455560634L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazSAMEnviarMovimientosAlmacenSiccForm interfazForm = new InterfazSAMEnviarMovimientosAlmacenSiccForm();
		return interfazForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}		
		InterfazSAMEnviarMovimientosAlmacenSiccForm f = (InterfazSAMEnviarMovimientosAlmacenSiccForm)  this.formInterfaz;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codPais=pais.getCodigo();
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais",  codPais);
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente	     
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		String fecha=controlFacturacion.getFechaProceso();
		f.setFechaProcesoDate(DateUtil.convertStringToDate(fecha));	
		
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,BaseForm form) throws Exception {		
		InterfazSAMEnviarMovimientosAlmacenSiccForm f = (InterfazSAMEnviarMovimientosAlmacenSiccForm)  this.formInterfaz;
		String fecha=DateUtil.convertDateToString(f.getFechaProcesoDate());
		f.setFechaProceso(fecha);
		params = super.prepareParamsBeforeExecute(params, form);
		params.put("codigoPeriodo",f.getCodigoPeriodo());
		params.put("fechaFacturacion",f.getFechaProceso());		
		return params;
	}
}
