package biz.belcorp.ssicc.web.sisicc.action;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSMSEnviarConsultorasPedidosForm;


@ManagedBean
@SessionScoped
public class InterfazSMSEnviarConsultorasPedidosAction extends BaseInterfazAbstractAction{

	private static final long serialVersionUID = 403282976068972262L;
	
	private List smsTipoInterfazList;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazSMSEnviarConsultorasPedidosForm interfazForm= new  InterfazSMSEnviarConsultorasPedidosForm();
		return interfazForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		InterfazSMSEnviarConsultorasPedidosForm f = (InterfazSMSEnviarConsultorasPedidosForm) this.formInterfaz;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codPais = pais.getCodigo();		
		this.smsTipoInterfazList=interfazSiCCService.getTiposInterfazSMS();	
		// Carga Periodo
        String periodo=interfazSiCCService.getPeriodoDefaultByPaisCanal(codPais,Constants.CODIGO_CANAL_DEFAULT);
        f.setCodigoPeriodo(periodo);
        //Fecha Facturacion       
        f.setFechaFacturacionDate(new Date(System.currentTimeMillis()));     	
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception{
		
		InterfazSMSEnviarConsultorasPedidosForm f = (InterfazSMSEnviarConsultorasPedidosForm) this.formInterfaz;
		Usuario usuario =mPantallaPrincipalBean.getCurrentUser();
		String fecha=DateUtil.convertDateToString(f.getFechaFacturacionDate());
		f.setFechaFacturacion(fecha);
		params = super.prepareParamsBeforeExecute(params, form);
		params.put("codigoInterfaz", f.getTipoInterfaz());
		params.put("usuario", usuario);
		
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		interfazSiCCService.updateInterfazProcesoBatch(params);		
		return params;        
	}
	
	public String setValidarInterfaz() {
		InterfazSMSEnviarConsultorasPedidosForm f = (InterfazSMSEnviarConsultorasPedidosForm) this.formInterfaz;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		Pais pais =this.mPantallaPrincipalBean.getCurrentCountry();
		String codPais = pais.getCodigo();	
		String periodo = f.getCodigoPeriodo();
		
		String fechaDesde = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(codPais, Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, periodo);
		String fechaHasta = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(codPais, Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, periodo);		
		try {			
			Date fechaDesdeD=DateUtil.convertStringToDate(fechaDesde);
			Date fechaHastaD=DateUtil.convertStringToDate(fechaHasta);
			if (f.getFechaFacturacionDate().before(fechaDesdeD)	|| f.getFechaFacturacionDate().after(fechaHastaD)) {
				String mensaje ="La fecha de facturación debe de encontrarse en los siguientes intervalos"
						+ " (" + fechaDesde + " - " + fechaHasta + ")";
				return mensaje;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List getSmsTipoInterfazList() {
		return smsTipoInterfazList;
	}

	public void setSmsTipoInterfazList(List smsTipoInterfazList) {
		this.smsTipoInterfazList = smsTipoInterfazList;
	}
	
}
