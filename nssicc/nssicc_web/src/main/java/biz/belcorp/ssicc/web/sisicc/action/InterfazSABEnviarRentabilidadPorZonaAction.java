package biz.belcorp.ssicc.web.sisicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSABEnviarRentabilidadPorZonaForm;

@ManagedBean
@SessionScoped
public class InterfazSABEnviarRentabilidadPorZonaAction extends BaseInterfazAbstractAction{
		
	private static final long serialVersionUID = -3317633938733331100L;
	
	private List siccSociedadList;
	private List siccMarcaList;
	private List siccCanalList;
	
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazSABEnviarRentabilidadPorZonaForm interfazForm= new  InterfazSABEnviarRentabilidadPorZonaForm();
		return interfazForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		InterfazSABEnviarRentabilidadPorZonaForm f = (InterfazSABEnviarRentabilidadPorZonaForm)this.formInterfaz;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		String codPais=pais.getCodigo();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccSociedadList=svc.getSociedadesByCodigoPais(codPais);
		this.siccMarcaList=svc.getMarcas();
		this.siccCanalList=svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		String periodo=ajax.getPeriodoDefaultByPaisMarcaCanal(codPais,Constants.CODIGO_MARCA_DEFAULT,Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoPeriodo(periodo);  
		
	}
	
	@Override
    protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
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
		InterfazSABEnviarRentabilidadPorZonaForm f = (InterfazSABEnviarRentabilidadPorZonaForm)this.formInterfaz;
		f.setCodigoPeriodo(periodoRequerido(marca, f.getCodigoCanal()));
	}
	
	public void loadPeriodoCanal(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoCanal");
		}
		String canal = (String) val.getNewValue();
		InterfazSABEnviarRentabilidadPorZonaForm f = (InterfazSABEnviarRentabilidadPorZonaForm)this.formInterfaz;
		f.setCodigoPeriodo(periodoRequerido(f.getCodigoMarca(), canal));
	}

	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
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
