package biz.belcorp.ssicc.web.sisicc.action;

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
import biz.belcorp.ssicc.web.sisicc.form.InterfazGISEnviarDireccionConsultorasForm;

@ManagedBean
@SessionScoped
public class InterfazGISEnviarDireccionConsultorasAction extends BaseInterfazAbstractAction{
	
	private static final long serialVersionUID = -7456645091357530316L;
	
	private List siccMarcaList;
	private List siccCanalList;
	private List siccTipoClienteList;
	private LabelValue[] siccRegionList;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazGISEnviarDireccionConsultorasForm interfazForm= new  InterfazGISEnviarDireccionConsultorasForm();
		return interfazForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		
		InterfazGISEnviarDireccionConsultorasForm f = (InterfazGISEnviarDireccionConsultorasForm)this.formInterfaz;
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		String codPais=pais.getCodigo();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		this.siccMarcaList=svc.getMarcas();
		this.siccCanalList=svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccTipoClienteList=svc.getTiposClientesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccRegionList=aSvc.getRegionesByPaisMarcaCanal(codPais,Constants.CODIGO_MARCA_DEFAULT,Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoTipoCliente(Constants.CODIGO_TIPO_CLIENTE_DEFAULT);				
	}
	
	@Override
    protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		params = super.prepareParamsBeforeExecute(params, form);						
		return params;
    }
	
	public LabelValue[] RegionRequerida(String marca, String canal) {
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		LabelValue[] datos={};
		datos = ajax.getRegionesByPaisMarcaCanal(pais.getCodigo(), marca,canal);		
		return datos;
	}
	
	
	public void loadRegionMarca(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoMarca");
		}
		String marca = (String) val.getNewValue();
		InterfazGISEnviarDireccionConsultorasForm f = (InterfazGISEnviarDireccionConsultorasForm)this.formInterfaz;
		this.siccRegionList=RegionRequerida(marca, f.getCodigoCanal());		
	}
	
	public void loadRegionCanal(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoCanal");
		}
		String canal = (String) val.getNewValue();
		InterfazGISEnviarDireccionConsultorasForm f = (InterfazGISEnviarDireccionConsultorasForm)this.formInterfaz;
		this.siccRegionList=RegionRequerida(f.getCodigoMarca(), canal);		
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

	public List getSiccTipoClienteList() {
		return siccTipoClienteList;
	}

	public void setSiccTipoClienteList(List siccTipoClienteList) {
		this.siccTipoClienteList = siccTipoClienteList;
	}

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}
	

}
