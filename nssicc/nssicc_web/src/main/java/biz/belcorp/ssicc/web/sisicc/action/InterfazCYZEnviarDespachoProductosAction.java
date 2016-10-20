package biz.belcorp.ssicc.web.sisicc.action;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazCYZEnviarDespachoProductosForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazCYZEnviarDespachoProductosAction.java.html">
 * <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 * 
  */
@ManagedBean
@SessionScoped
public class InterfazCYZEnviarDespachoProductosAction extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3685990750715054615L;

	
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception 
	{
		InterfazCYZEnviarDespachoProductosForm interfazForm = new InterfazCYZEnviarDespachoProductosForm();
		return interfazForm;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		InterfazCYZEnviarDespachoProductosForm actionForm = (InterfazCYZEnviarDespachoProductosForm) this.formInterfaz;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		
		MantenimientoOCRPedidoControlFacturacionService serviceOCR = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceOCR.getControlFacturacionById(criteria);

		// Actualizamos los valores en caso estos esten vacios
		if (StringUtils.isBlank(actionForm.getCodigoPeriodo()) && StringUtils.isBlank(actionForm.getFechaFacturacion())) 
		{
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			
			actionForm.setFechaFacturacion(controlFacturacion.getFechaProceso());			
			actionForm.setFechaFacturacionDate(sdf1.parse(controlFacturacion.getFechaProceso()));
			actionForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		}		
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		InterfazCYZEnviarDespachoProductosForm f = (InterfazCYZEnviarDespachoProductosForm) this.formInterfaz;
			
		if(f.getFechaFacturacionDate()!=null){
			f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionDate()));
		}
		params = super.prepareParamsBeforeExecute(params, form);
		return params;
	}
}
