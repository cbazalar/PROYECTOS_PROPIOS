package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazLAREnviarVisibilidadCronogramaFacturacionForm;

/**
 * The Class InterfazLAREnviarVisibilidadCronogramaFacturacionAction.
 * 
 * @autor: Hernando Huaman Flores
 * @version: 1.0 02/12/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class InterfazLAREnviarVisibilidadCronogramaFacturacionAction extends
		BaseInterfazAbstractAction {

	private static final long serialVersionUID = 4864356697116483433L;
	private List siccMarcaList;
	private List siccCanalList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazLAREnviarVisibilidadCronogramaFacturacionForm actionForm = new InterfazLAREnviarVisibilidadCronogramaFacturacionForm();
		return actionForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();

		// Carga de los combos Marca y Canal
		this.siccMarcaList = interfazSiCCService.getMarcas();
		this.siccCanalList = interfazSiCCService.getCanalesByCodigoISO(usuario
				.getIdioma().getCodigoISO());

		// Asignamos al codigo del periodo el valor por defecto
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		List periodos = interfazSiCCService.getPeriodosDefaultByPMC(criteria);

		InterfazLAREnviarVisibilidadCronogramaFacturacionForm actionForm = (InterfazLAREnviarVisibilidadCronogramaFacturacionForm) this.formInterfaz;
		actionForm.setCodigoPais(pais.getCodigo());
		if (periodos != null && periodos.size() > 0) {
			Base base = (Base) periodos.get(0);
			actionForm.setCodigoPeriodo(base.getCodigo());
		}
	}

	/**
	 * loadPeriodosMarca.
	 * 
	 * @param val
	 *            the val
	 */
	public void loadPeriodosMarca(ValueChangeEvent val) {
		try {
			log.debug(">>loadPeriodosMarca ");
			log.debug("val: " + val.getNewValue().toString());
			InterfazLAREnviarVisibilidadCronogramaFacturacionForm form = (InterfazLAREnviarVisibilidadCronogramaFacturacionForm) this.formInterfaz;
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			LabelValue periodos[] = ajax.getPeriodosDefaultByPaisMarcaCanal(
					form.getCodigoPais(), val.getNewValue().toString(),
					form.getCodigoCanal());
			if (periodos != null && periodos.length > 0) {
				LabelValue label = periodos[0];
				form.setCodigoPeriodo(label.getValue());
			} else {
				form.setCodigoPeriodo(null);
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * loadPeriodosCanal.
	 * 
	 * @param val
	 *            the val
	 */
	public void loadPeriodosCanal(ValueChangeEvent val) {
		try {
			log.debug(">>loadPeriodosCanal ");
			log.debug("val: " + val.getNewValue().toString());
			InterfazLAREnviarVisibilidadCronogramaFacturacionForm form = (InterfazLAREnviarVisibilidadCronogramaFacturacionForm) this.formInterfaz;
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			LabelValue periodos[] = ajax.getPeriodosDefaultByPaisMarcaCanal(
					form.getCodigoPais(), form.getCodigoMarca(), val
							.getNewValue().toString());
			if (periodos != null && periodos.length > 0) {
				LabelValue label = periodos[0];
				form.setCodigoPeriodo(label.getValue());
			} else {
				form.setCodigoPeriodo(null);
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 *            the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList
	 *            the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

}
