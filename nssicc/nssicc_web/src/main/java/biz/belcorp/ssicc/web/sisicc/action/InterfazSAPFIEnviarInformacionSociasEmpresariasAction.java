package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSAPFIEnviarInformacionSociasEmpresariasForm;

@ManagedBean
@SessionScoped
public class InterfazSAPFIEnviarInformacionSociasEmpresariasAction extends BaseInterfazAbstractAction{

	
	private static final long serialVersionUID = -6292427445140339690L;
	
	private boolean opcionPeriodoFecha;
	private String tipoEnvio;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazSAPFIEnviarInformacionSociasEmpresariasForm interfazForm= new  InterfazSAPFIEnviarInformacionSociasEmpresariasForm();
		return interfazForm;
	}
	

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'InterfazSAPFIEnviarInformacionSociasEmpresariasAction.setViewAttributes' method");
		}		
		
		String strMessage = null;
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		InterfazSAPFIEnviarInformacionSociasEmpresariasForm f = (InterfazSAPFIEnviarInformacionSociasEmpresariasForm)this.formInterfaz;
		
			
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			
			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			
			//-- Capturar periodo actual --------------------------------
			List lista = service.getPeriodoFechaProcesoActual(criteria);
			String periodoActual = ((HashMap)lista.get(0)).get("cod_peri").toString();
			String fechaFacturacion = ((HashMap)lista.get(0)).get("fec_proc").toString();
			f.setCodigoPeriodo(periodoActual);
			Date fecha=DateUtil.convertStringToDate(fechaFacturacion);
			f.setFechaFacturacionDate(fecha);
			f.setFechaFacturacion(DateUtil.convertDateToString(fecha));
			
			f.setHabilitadorHidden(""); 
			setOpcionPeriodoFecha(true);
			this.tipoEnvio="0";
		
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		params =  super.prepareParamsBeforeExecute(params, form);
		params.put("tipoEnvio", this.tipoEnvio);
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

	/**
	 * @return the tipoEnvio
	 */
	public String getTipoEnvio() {
		return tipoEnvio;
	}

	/**
	 * @param tipoEnvio the tipoEnvio to set
	 */
	public void setTipoEnvio(String tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}
	
	
	

}
