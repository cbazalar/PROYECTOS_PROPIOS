package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

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
import biz.belcorp.ssicc.web.sisicc.form.InterfazSAMEnviarMovimientosAlmacenForm;

@ManagedBean
@SessionScoped
public class InterfazSAMEnviarMovimientosAlmacenAction extends BaseInterfazAbstractAction{
	
	private static final long serialVersionUID = -5160694374990905821L;	
	private boolean indMaterial;
	String resultadoInterfaz;
	
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazSAMEnviarMovimientosAlmacenForm interfazForm= new  InterfazSAMEnviarMovimientosAlmacenForm();
		return interfazForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}		

		InterfazSAMEnviarMovimientosAlmacenForm f = (InterfazSAMEnviarMovimientosAlmacenForm)  this.formInterfaz;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		String codPais=pais.getCodigo();
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais",  codPais);
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
	     
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);
		String periodo=controlFacturacion.getCodigoPeriodo();
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		String fecha=controlFacturacion.getFechaProceso();		
		f.setFechaProcesoDate(DateUtil.convertStringToDate(fecha));
		
		ProcesoPEDService servicePed = (ProcesoPEDService) getBean("spusicc.procesoPEDService");
		criteriaPeriodo.put("codigoPeriodo", controlFacturacion.getCodigoPeriodo());
		String indicadorPROL = servicePed.getIndicadorActividadPROL(criteriaPeriodo);		
		if(StringUtils.isNotBlank(indicadorPROL))
		f.setIndicadorPROL(indicadorPROL);		
		//f.setIndicadorMaterial(Constants.NUMERO_CERO);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#afterExecuteInterfaz(java.util.Map, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazExecutionResult)
	 */
	protected void afterExecuteInterfaz(Map params, InterfazExecutionResult interfazExecutionResult) throws Exception {
		super.afterExecuteInterfaz(params, interfazExecutionResult);
		 log.debug("Actualizando indicador PROL");
		 resultadoInterfaz=params.get("resultadoInterfaz").toString();
		
		 ProcesoPEDService servicePed = (ProcesoPEDService) getBean("spusicc.procesoPEDService");
		 Map criteria = new HashMap();

   		 String indicadorPROL = servicePed.getIndicadorActividadPROL(criteria);
		 
	    	if (StringUtils.isNotEmpty(indicadorPROL)){
	    		criteria.put("indicador", Constants.NUMERO_CINCO);
	    		servicePed.executeActualizaIndicadorPROL(criteria);
	    	}			
		
	}	
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		
		InterfazSAMEnviarMovimientosAlmacenForm f = (InterfazSAMEnviarMovimientosAlmacenForm)  this.formInterfaz;
		String fecha=DateUtil.convertDateToString(f.getFechaProcesoDate());
		f.setFechaProceso(fecha);
		params =super.prepareParamsBeforeExecute(params,form);	
		params.put("codigoPeriodo",f.getCodigoPeriodo());
		params.put("fechaFacturacion",f.getFechaProceso());
		params.put("indicadorMaterial",this.indMaterial==false?"0":"1");
		return params;
	}
	
	public String setValidarInterfaz() {
		InterfazSAMEnviarMovimientosAlmacenForm f = (InterfazSAMEnviarMovimientosAlmacenForm)  this.formInterfaz;
		String indicadorPROL=f.getIndicadorPROL();
		if(indicadorPROL == "0"){
			String mensaje =  this.getResourceMessage("interfazSAMEnviarMovimientosAlmacenForm.indicadorPROL.stockPROL");
			return mensaje;
		}
		if(indicadorPROL == "1"){
			String mensaje =  this.getResourceMessage("interfazSAMEnviarMovimientosAlmacenForm.indicadorPROL.PROLactivo");
			return mensaje;
		}
		if(indicadorPROL == "2"){
			String mensaje =  this.getResourceMessage("interfazSAMEnviarMovimientosAlmacenForm.indicadorPROL.stockPROL.noEnviado");
			return mensaje;
		}
		if(indicadorPROL == "3"){
			String mensaje =  this.getResourceMessage("interfazSAMEnviarMovimientosAlmacenForm.indicadorPROL.stockSAP");
			return mensaje;
		}	       					
	    return null;
	}

	/**
	 * @return the indMaterial
	 */
	public boolean isIndMaterial() {
		return indMaterial;
	}

	/**
	 * @param indMaterial the indMaterial to set
	 */
	public void setIndMaterial(boolean indMaterial) {
		this.indMaterial = indMaterial;
	}
	
	
}
