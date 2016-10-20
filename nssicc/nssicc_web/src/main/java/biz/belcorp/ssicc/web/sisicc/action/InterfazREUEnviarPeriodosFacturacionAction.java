package biz.belcorp.ssicc.web.sisicc.action;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazREUEnviarPeriodosFacturacionForm;

@ManagedBean
@SessionScoped
public class InterfazREUEnviarPeriodosFacturacionAction extends
		BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2310891834317671315L;

	private List siccMarcaList;
	private List siccCanalList;
	private LabelValue[] siccPeriodoList;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception 
	{
		InterfazREUEnviarPeriodosFacturacionForm formInterfaz = new InterfazREUEnviarPeriodosFacturacionForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		InterfazREUEnviarPeriodosFacturacionForm f = (InterfazREUEnviarPeriodosFacturacionForm)this.formInterfaz;
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		// Carga de los combos Marca y Canales
		this.siccMarcaList = interfazSiCCService.getMarcas();
		this.siccCanalList = interfazSiCCService.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
//		request.getSession().removeAttribute(Constants.SICC_PERIODO_LIST);
		
		/* METODO RESET */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

        f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
        f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
        f.setPeriodoDesde(periodoRequerido(Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT));
        f.setPeriodoHasta(periodoRequerido(Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT));
		/**/

        /* METODO RELOAD */
        AjaxService aSvc = (AjaxService) getBean("ajaxService");
        this.siccPeriodoList = aSvc.getPeriodosByPaisMarcaCanal(f.getCodigoPais(), f.getCodigoMarca(), f.getCodigoCanal());
        /**/
	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		params = super.prepareParamsBeforeExecute(params, form);
		return params;
	}
	
	public String periodoRequerido(String marca, String canal) {
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String dato = "";
		dato = ajax.getPeriodoDefaultByPaisMarcaCanal(pais.getCodigo(), marca,
				canal);
		return dato;
	}
	
	public void loadPeriodoMarca(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoMarca");
		}
		String marca = (String) val.getNewValue();
		InterfazREUEnviarPeriodosFacturacionForm f = (InterfazREUEnviarPeriodosFacturacionForm) this.formInterfaz;
		f.setPeriodoDesde(periodoRequerido(marca, f.getCodigoCanal()));
		f.setPeriodoHasta(periodoRequerido(marca, f.getCodigoCanal()));
	}

	public void loadPeriodoCanal(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoCanal");
		}
		String canal = (String) val.getNewValue();
		InterfazREUEnviarPeriodosFacturacionForm f = (InterfazREUEnviarPeriodosFacturacionForm) this.formInterfaz;
		f.setPeriodoDesde(periodoRequerido(f.getCodigoMarca(), canal));
		f.setPeriodoHasta(periodoRequerido(f.getCodigoMarca(), canal));
	}
	
	public String setValidarInterfaz() 
	{
		InterfazREUEnviarPeriodosFacturacionForm f = (InterfazREUEnviarPeriodosFacturacionForm) this.formInterfaz;
		String mensaje = null;
		
		if ((f.getPeriodoDesde() != null && f.getPeriodoDesde().length() > 1)
                && (f.getPeriodoHasta() != null && f.getPeriodoHasta().length() > 1)) {
            Long desde = new Long(f.getPeriodoDesde());
            Long hasta = new Long(f.getPeriodoHasta());

            if (desde.longValue() > hasta.longValue()) {
            	mensaje = this.getResourceMessage("interfazSiCC.error.periodo.Desde.Mayor");
            }
        }
		return mensaje;
	}
	
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}
}
