package biz.belcorp.ssicc.web.sisicc.action;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazACCEnviarNovedadesForm;


@ManagedBean
@SessionScoped
public class InterfazACCEnviarNovedadesAction extends BaseInterfazAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6308282885334364474L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazACCEnviarNovedadesForm form= new InterfazACCEnviarNovedadesForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		
		HashMap criteria = new HashMap();
		InterfazACCEnviarNovedadesForm form=(InterfazACCEnviarNovedadesForm) this.formInterfaz;
		criteria.put("codigoPais",usuario.getPais().getCodigo());
		form.setCodigoPais(pais.getCodigo());
		form.setCodigoPeriodo(interfazSiCCService.getPeriodoDefaultByPaisCanal(usuario.getPais().getCodigo(), Constants.CODIGO_CANAL_DEFAULT));
		
		MantenimientoOCRPedidoControlFacturacionService serviceCF = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService"); 
        Map criteriaPeriodo = new HashMap();
        criteriaPeriodo.put("codigoPais", pais.getCodigo());
        criteriaPeriodo.put("estadoCampanha", Constants.DAT_PARAM_STA_CAMP_CERO);
        criteriaPeriodo.put("indicadorActiva", Constants.DAT_PARAM_IND_CAMP_ACT_UNO);
        
        PedidoControlFacturacion controlFacturacion = serviceCF.getControlFacturacionById(criteriaPeriodo);
        form.setFechaFact(DateUtil.convertStringToDate(controlFacturacion.getFechaProceso()));        

	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		
		InterfazACCEnviarNovedadesForm form1=(InterfazACCEnviarNovedadesForm) this.formInterfaz;
		form1.setFechaFacturacion(DateUtil.convertDateToString(form1.getFechaFact()));
		params = super.prepareParamsBeforeExecute(params, form);
		params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		//params.put("fechaFacturacion", DateUtil.convertStringToDate(form1.getFechaFacturacion()));
		return params;
		
	}
	
	public String setValidarInterfaz() {

		String  mensaje="";
		AjaxService ajaxService=(AjaxService) getBean("ajaxService");
		InterfazACCEnviarNovedadesForm form=(InterfazACCEnviarNovedadesForm) this.formInterfaz;
		String fechaIni=ajaxService.getFechaInicioPeriodoByPaisMarcaCanal( form.getCodigoPais(), "T", "VD", form.getCodigoPeriodo());
		String fechaFin=ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(form.getCodigoPais(), "T", "VD", form.getCodigoPeriodo());
		
		try {
			if(form.getFechaFact().after(DateUtil.convertStringToDate(fechaFin)) || form.getFechaFact().before(DateUtil.convertStringToDate(fechaIni)))
			{
				mensaje = this.getResourceMessage("interfazACCEnviarNovedadesForm.fechaFacturacion.error")+fechaIni+" - "+fechaFin;
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mensaje;
	}
		


}
