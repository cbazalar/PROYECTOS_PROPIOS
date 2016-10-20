package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazLETModuloCaribeForm;

@ManagedBean
@SessionScoped
public class InterfazLETModuloCaribeAction extends BaseInterfazAbstractAction{
			
	private static final long serialVersionUID = 2518186983161180100L;
	
	private boolean opcionPeriodoFecha;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		 InterfazLETModuloCaribeForm interfazForm= new  InterfazLETModuloCaribeForm();
		return interfazForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'setViewAttributes' method");
        }
        
        InterfazLETModuloCaribeForm f = (InterfazLETModuloCaribeForm) this.formInterfaz;
        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();
       
        Map criteriaPeriodo = new HashMap();
        criteriaPeriodo.put("codigoPais", pais.getCodigo());
        criteriaPeriodo.put("estadoCampanha", Constants.DAT_PARAM_STA_CAMP_CERO);
        criteriaPeriodo.put("indicadorActiva", Constants.DAT_PARAM_IND_CAMP_ACT_UNO);
        
        MantenimientoOCRPedidoControlFacturacionService serviceCF = 
        	(MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService"); 
        PedidoControlFacturacion controlFacturacion = null; 
        controlFacturacion = serviceCF.getControlFacturacionById(criteriaPeriodo);
        f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		String fecha=controlFacturacion.getFechaProceso();
		f.setFechaFacturacionDate(DateUtil.convertStringToDate(fecha));		
		f.setHabilitadorHidden(""); 
		setOpcionPeriodoFecha(true);
    }
    
	@Override
    protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		
		InterfazLETModuloCaribeForm f = (InterfazLETModuloCaribeForm) this.formInterfaz;
		f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionDate()));
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		params = super.prepareParamsBeforeExecute(params, form);
		
		params.put("codigoIdiomaIso", pais.getCodigoIdiomaIso());						
		return params;
    }
	
	public void loadPeriodoFecha(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoFecha");
		}
		String opcion = (String) val.getNewValue().toString();

		if (opcion == "true") {
			setOpcionPeriodoFecha(false);
		} else {
			setOpcionPeriodoFecha(true);
		}
	}

	public boolean isOpcionPeriodoFecha() {
		return opcionPeriodoFecha;
	}

	public void setOpcionPeriodoFecha(boolean opcionPeriodoFecha) {
		this.opcionPeriodoFecha = opcionPeriodoFecha;
	}
	

  
}
