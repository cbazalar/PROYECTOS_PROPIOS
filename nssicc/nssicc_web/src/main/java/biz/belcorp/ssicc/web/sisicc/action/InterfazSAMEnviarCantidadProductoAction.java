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
import biz.belcorp.ssicc.web.sisicc.form.InterfazSAMEnviarCantidadProductoForm;

@ManagedBean
@SessionScoped
public class InterfazSAMEnviarCantidadProductoAction extends BaseInterfazAbstractAction{

	
	private static final long serialVersionUID = -1200290431156497117L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazSAMEnviarCantidadProductoForm interfazForm= new  InterfazSAMEnviarCantidadProductoForm();
		return interfazForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'setViewAttributes' method");
        }
        
      
        InterfazSAMEnviarCantidadProductoForm f = (InterfazSAMEnviarCantidadProductoForm)this.formInterfaz;
        Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		String codPais=pais.getCodigo();
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codPais);
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
        
        MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
        PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		// Carga Fecha y Periodo       
        f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
        String fecha=controlFacturacion.getFechaProceso();
        f.setFechaFacturacionDate(DateUtil.convertStringToDate(fecha));   
      
    }
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,BaseForm form) throws Exception {		
		InterfazSAMEnviarCantidadProductoForm f = (InterfazSAMEnviarCantidadProductoForm)this.formInterfaz;
		String fecha=DateUtil.convertDateToString(f.getFechaFacturacionDate());	
		f.setFechaFacturacion(fecha);
		params = super.prepareParamsBeforeExecute(params, form);
		params.put("codigoPeriodo",f.getCodigoPeriodo());
		params.put("fechaFacturacion",f.getFechaFacturacion());
		return params;
	}
}
