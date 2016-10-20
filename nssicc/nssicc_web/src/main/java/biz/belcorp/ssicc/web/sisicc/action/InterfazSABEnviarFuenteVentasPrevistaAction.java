package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
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
import biz.belcorp.ssicc.web.sisicc.form.InterfazSABEnviarFuenteVentasPrevistaForm;


@ManagedBean
@SessionScoped
public class InterfazSABEnviarFuenteVentasPrevistaAction extends BaseInterfazAbstractAction{

	private static final long serialVersionUID = -6069508547038740553L;
	
	private List siccSociedadList;
	private List siccMarcaList;
	private List siccCanalList;
	private List siccAlmacenList;	

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazSABEnviarFuenteVentasPrevistaForm interfazForm= new  InterfazSABEnviarFuenteVentasPrevistaForm();
		return interfazForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		InterfazSABEnviarFuenteVentasPrevistaForm f = (InterfazSABEnviarFuenteVentasPrevistaForm)this.formInterfaz;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		String codPais=pais.getCodigo();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		Map params = new HashMap();
        params.clear();
        params.put("codigoISO",usuario.getIdioma().getCodigoISO());
        params.put("codigoPais",codPais);
		
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccSociedadList=svc.getSociedadesByCodigoPais(codPais);
		this.siccMarcaList=svc.getMarcas();
		this.siccCanalList=svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccAlmacenList=svc.getAlmacenesByCodigoISOPais(params);
		f.setCodigoSociedad(Constants.CODIGO_SOCIEDAD_DEFAULT);
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoAlmacen(Constants.CODIGO_ALMACEN_DEFAULT);		
		String periodo=ajax.getPeriodoDefaultByPaisMarcaCanal( codPais,Constants.CODIGO_MARCA_DEFAULT,Constants.CODIGO_CANAL_DEFAULT);
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
		InterfazSABEnviarFuenteVentasPrevistaForm f = (InterfazSABEnviarFuenteVentasPrevistaForm)this.formInterfaz;
		f.setCodigoPeriodo(periodoRequerido(marca, f.getCodigoCanal()));
	}
	
	public void loadPeriodoCanal(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoCanal");
		}
		String canal = (String) val.getNewValue();
		InterfazSABEnviarFuenteVentasPrevistaForm f = (InterfazSABEnviarFuenteVentasPrevistaForm)this.formInterfaz;
		f.setCodigoPeriodo(periodoRequerido(f.getCodigoMarca(), canal));
	}
	
	/*
	public String setValidarInterfaz() {
		InterfazSABEnviarFuenteVentasPrevistaForm f = (InterfazSABEnviarFuenteVentasPrevistaForm)this.formInterfaz;		
		String periodo = f.getCodigoPeriodo();
		String periodorequerido = periodoRequerido(f.getCodigoMarca(),
				f.getCodigoCanal());
		if (!periodo.equals(periodorequerido)) {
			String mensaje = this
					.getResourceMessage("interfazRETEnviarFacturasVentaDirectaForm.error.rango.fechaFacturacion");
			return mensaje;
		}
		return null;
	}
	*/

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

	public List getSiccAlmacenList() {
		return siccAlmacenList;
	}

	public void setSiccAlmacenList(List siccAlmacenList) {
		this.siccAlmacenList = siccAlmacenList;
	}	
	
	
}
