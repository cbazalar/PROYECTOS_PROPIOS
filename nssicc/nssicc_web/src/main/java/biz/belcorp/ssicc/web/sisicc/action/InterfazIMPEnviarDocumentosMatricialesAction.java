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
import biz.belcorp.ssicc.web.sisicc.form.InterfazIMPEnviarDocumentosMatricialesForm;

@ManagedBean
@SessionScoped
public class InterfazIMPEnviarDocumentosMatricialesAction extends BaseInterfazAbstractAction{

	private static final long serialVersionUID = 7920848488531443801L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazIMPEnviarDocumentosMatricialesForm interfazForm = new InterfazIMPEnviarDocumentosMatricialesForm();
		return interfazForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}	
		
        Pais pais =this.mPantallaPrincipalBean.getCurrentCountry();
        Map criteria = new HashMap();
        criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
        
        InterfazIMPEnviarDocumentosMatricialesForm f = (InterfazIMPEnviarDocumentosMatricialesForm)this.formInterfaz ; 
        MantenimientoOCRPedidoControlFacturacionService serviceOCR = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
        PedidoControlFacturacion controlFacturacion = serviceOCR.getControlFacturacionById(criteria);
        f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
        String fecha=controlFacturacion.getFechaProceso();
        f.setFechaFacturacionDate(DateUtil.convertStringToDate(fecha));       
       
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		InterfazIMPEnviarDocumentosMatricialesForm f = (InterfazIMPEnviarDocumentosMatricialesForm)this.formInterfaz ; 
		f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionDate()));
		params = super.prepareParamsBeforeExecute(params, form);
		return params;
	}
}
