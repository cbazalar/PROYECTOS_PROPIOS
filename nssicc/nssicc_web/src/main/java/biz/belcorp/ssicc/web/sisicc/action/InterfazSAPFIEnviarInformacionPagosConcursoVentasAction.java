package biz.belcorp.ssicc.web.sisicc.action;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang3.time.DateFormatUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSAPFIEnviarInformacionPagosConcursoVentasForm;

@ManagedBean
@SessionScoped
public class InterfazSAPFIEnviarInformacionPagosConcursoVentasAction extends BaseInterfazAbstractAction{
	
	private static final long serialVersionUID = -6292427445140339690L;
	
	private boolean opcionPeriodoFecha;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazSAPFIEnviarInformacionPagosConcursoVentasForm interfazForm= new  InterfazSAPFIEnviarInformacionPagosConcursoVentasForm();
		return interfazForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'InterfazSAPFIEnviarInformacionPagosConcursoVentasAction.setViewAttributes' method");
		}
		
		
		String strMessage = null;
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		InterfazSAPFIEnviarInformacionPagosConcursoVentasForm f = (InterfazSAPFIEnviarInformacionPagosConcursoVentasForm)this.formInterfaz;
		
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		
		//-- Capturar periodo actual --------------------------------
		List lista = service.getPeriodoFechaProcesoActual(criteria);
		String periodoActual = ((HashMap)lista.get(0)).get("cod_peri").toString();
		String fechaFacturacion = ((HashMap)lista.get(0)).get("fec_proc").toString();
		f.setCodigoPeriodo(periodoActual);
		f.setFechaFacturacion(fechaFacturacion);
		Date fecha=DateUtil.convertStringToDate(fechaFacturacion);
		f.setFechaFacturacionDate(fecha);
		
		f.setHabilitadorHidden(""); 
		setOpcionPeriodoFecha(true);
		f.setTipoEnvio("");
		
	}
	
	@Override
	public String setValidarInterfaz() {
		InterfazSAPFIEnviarInformacionPagosConcursoVentasForm f = (InterfazSAPFIEnviarInformacionPagosConcursoVentasForm)this.formInterfaz;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		AjaxService ajax = (AjaxService) getBean("ajaxService");
    	
    	String fechaInicio = ajax.getFechaInicioPeriodoByPaisMarcaCanal(pais.getCodigo(), 
    						Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, f.getCodigoPeriodo());
	    String fechaFinal = ajax.getFechaFinalPeriodoByPaisMarcaCanal(pais.getCodigo(), 
	    					Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, f.getCodigoPeriodo());
		
		String fechaFacturacion=DateFormatUtils.format(f.getFechaFacturacionDate(),"dd/MM/yyyy");

		if (fechaFacturacion != "" ){
	    	int resultado  = DateUtil.compareDates(fechaFacturacion ,fechaFinal,"dd/MM/yyyy");    
	    	int resultado1 = DateUtil.compareDates(fechaInicio ,fechaFacturacion,"dd/MM/yyyy");  
	    	if ( resultado == 1 || resultado1==1) 
	        {	
	    		String mensaje = getResourceMessage("errors.compare.campaigns.periodo.fechaFacturacion");
				return mensaje.concat(fechaInicio).concat(" - ").concat(fechaFinal);
	        }	    	  
        }

	    return null;
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
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		InterfazSAPFIEnviarInformacionPagosConcursoVentasForm f = (InterfazSAPFIEnviarInformacionPagosConcursoVentasForm)this.formInterfaz;
		params =  super.prepareParamsBeforeExecute(params, form);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		params.put("codigoIdiomaIso", pais.getCodigoIdiomaIso());
		
		String fechaFacturacion = DateUtil.convertDateToString(f.getFechaFacturacionDate());
		params.put("fechaFacturacion", fechaFacturacion);
		return params;
	}

	public boolean isOpcionPeriodoFecha() {
		return opcionPeriodoFecha;
	}

	public void setOpcionPeriodoFecha(boolean opcionPeriodoFecha) {
		this.opcionPeriodoFecha = opcionPeriodoFecha;
	}

}
