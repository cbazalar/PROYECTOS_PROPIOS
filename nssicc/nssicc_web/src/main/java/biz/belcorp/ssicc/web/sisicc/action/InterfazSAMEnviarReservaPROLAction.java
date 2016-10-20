package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSAMEnviarReservaPROLForm;

@ManagedBean
@SessionScoped
public class InterfazSAMEnviarReservaPROLAction extends BaseInterfazAbstractAction{
	
	private static final long serialVersionUID = -1706594798145257882L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazSAMEnviarReservaPROLForm interfazForm = new InterfazSAMEnviarReservaPROLForm();
		return interfazForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}

		
		InterfazSAMEnviarReservaPROLForm f = (InterfazSAMEnviarReservaPROLForm)  this.formInterfaz;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codPais=pais.getCodigo();
		
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais",  codPais);
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

		MantenimientoOCRPedidoControlFacturacionService service = 
			(MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		ProcesoPEDService servicePed = (ProcesoPEDService) getBean("spusicc.procesoPEDService");

		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		String fecha=controlFacturacion.getFechaProceso();
		f.setFechaProcesoDate(DateUtil.convertStringToDate(fecha));
		criteriaPeriodo.put("codigoPeriodo", controlFacturacion.getCodigoPeriodo());
		String indicadorPROL = servicePed.getIndicadorActividadPROL(criteriaPeriodo);
		f.setIndicadorPROL(indicadorPROL);
		
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,BaseForm form) throws Exception {		
		InterfazSAMEnviarReservaPROLForm f = (InterfazSAMEnviarReservaPROLForm)  this.formInterfaz;
		String fecha=DateUtil.convertDateToString(f.getFechaProcesoDate());	
		f.setFechaProceso(fecha);
		params = super.prepareParamsBeforeExecute(params, form);
		params.put("codigoPeriodo",f.getCodigoPeriodo());
		params.put("fechaFacturacion",f.getFechaProceso());

		return params;
	}
	
	public String setValidarInterfaz() {
		InterfazSAMEnviarReservaPROLForm f = (InterfazSAMEnviarReservaPROLForm)  this.formInterfaz;
		String indicadorPROL=f.getIndicadorPROL();
		if(indicadorPROL==""){
			String mensaje =  this.getResourceMessage("interfazSAMEnviarReservaPROLForm.indicadorPROL.nodisponible");
			return mensaje;
		}
		if(indicadorPROL=="0"){
			String mensaje =  this.getResourceMessage("interfazSAMEnviarReservaPROLForm.indicadorPROL.realiza.carga");
			return mensaje;
		}
		if(indicadorPROL=="1"){
			String mensaje =  this.getResourceMessage("interfazSAMEnviarReservaPROLForm.indicadorPROL.activo");
			return mensaje;
		}
		if(indicadorPROL=="3"){
			String mensaje =  this.getResourceMessage("interfazSAMEnviarReservaPROLForm.indicadorPROL.envioSAP");
			return mensaje;
		}
		if(indicadorPROL=="4"){
			String mensaje =  this.getResourceMessage("interfazSAMEnviarReservaPROLForm.indicadorPROL.envioSAPBatch");
			return mensaje;
		}	    
	    return null;
	    
	    
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#afterExecuteInterfaz(java.util.Map, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazExecutionResult)
	 */
	protected void afterExecuteInterfaz(Map params, InterfazExecutionResult interfazExecutionResult) throws Exception{
		super.afterExecuteInterfaz(params, interfazExecutionResult);
		//resultadoInterfaz=params.get("resultadoInterfaz").toString();

		ProcesoPEDService servicePed = (ProcesoPEDService) getBean("spusicc.procesoPEDService");
		Map criteria = new HashMap();

		String codigoPais = (String)params.get("codigoPais");
		String codigoPeriodo = (String)params.get("codigoPeriodo");

		criteria.put("codigoPais", codigoPais);
    	criteria.put("codigoPeriodo", codigoPeriodo);
    	String indicadorPROL = servicePed.getIndicadorActividadPROL(criteria);
    	if (indicadorPROL.equals(Constants.NUMERO_DOS))
		criteria.put("indicador", Constants.NUMERO_TRES);
    	if (indicadorPROL.equals(Constants.NUMERO_CINCO))
    		criteria.put("indicador", Constants.NUMERO_CERO);
		servicePed.executeActualizaIndicadorPROL(criteria);

	}

}
