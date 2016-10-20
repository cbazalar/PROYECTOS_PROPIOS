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
import biz.belcorp.ssicc.web.sisicc.form.InterfazREUEnviarCronogramaFacturacionForm;

@ManagedBean
@SessionScoped
public class InterfazREUEnviarCronogramaFacturacionAction extends
		BaseInterfazAbstractAction {

	private static final long serialVersionUID = -6707643179961348319L;

	private List siccMarcaList;
	private List siccCanalList;
	private List siccActividadList;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazREUEnviarCronogramaFacturacionForm interfazForm = new InterfazREUEnviarCronogramaFacturacionForm();
		return interfazForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}

		InterfazREUEnviarCronogramaFacturacionForm f = (InterfazREUEnviarCronogramaFacturacionForm) this.formInterfaz;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		String codPais = pais.getCodigo();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		// Carga de los combos Marca, Canal Actividad y Periodo
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccMarcaList = svc.getMarcas();
		this.siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());		
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoActividad(Constants.CODIGO_ACTIVIDAD_DEFAULT);
		String periodo = ajax.getPeriodoDefaultByPaisMarcaCanal(codPais,
				Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT);
		f.setPeriodoDesde(periodo);
		f.setPeriodoHasta(periodo);
		this.siccActividadList = getActividadByPaisByMarcaByCanal(codPais,
				Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT);

	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
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

	/* MODIFICAR PERIODO Y ACTIVIDAD POR MARCA */
	public void loadPeriodoActividadxMarca(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoMarca");
		}
		String marca = (String) val.getNewValue();
		InterfazREUEnviarCronogramaFacturacionForm f = (InterfazREUEnviarCronogramaFacturacionForm) this.formInterfaz;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setPeriodoDesde(periodoRequerido(marca, f.getCodigoCanal()));
		f.setPeriodoHasta(periodoRequerido(marca, f.getCodigoCanal()));
		this.siccActividadList = getActividadByPaisByMarcaByCanal(
				pais.getCodigo(), marca, f.getCodigoCanal());
	}

	/* MODIFICAR PERIODO Y ACTIVIDAD POR CANAL */
	public void loadPeriodoActividadxCanal(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoCanal");
		}
		String canal = (String) val.getNewValue();
		InterfazREUEnviarCronogramaFacturacionForm f = (InterfazREUEnviarCronogramaFacturacionForm) this.formInterfaz;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setPeriodoDesde(periodoRequerido(f.getCodigoMarca(), canal));
		f.setPeriodoHasta(periodoRequerido(f.getCodigoMarca(), canal));
		this.siccActividadList = getActividadByPaisByMarcaByCanal(
				pais.getCodigo(), f.getCodigoMarca(), canal);

	}

	public String setValidarInterfaz() {
		InterfazREUEnviarCronogramaFacturacionForm f = (InterfazREUEnviarCronogramaFacturacionForm) this.formInterfaz;
		int codperini = Integer.parseInt(f.getPeriodoDesde());
		int codperfin = Integer.parseInt(f.getPeriodoHasta());
		if (codperfin < codperini) {
			String mensaje = "El Periodo Hasta debe ser mayor o igual al Periodo Desde";
			return mensaje;
		}
		return null;
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

	public List getSiccActividadList() {
		return siccActividadList;
	}

	public void setSiccActividadList(List siccActividadList) {
		this.siccActividadList = siccActividadList;
	}

}
