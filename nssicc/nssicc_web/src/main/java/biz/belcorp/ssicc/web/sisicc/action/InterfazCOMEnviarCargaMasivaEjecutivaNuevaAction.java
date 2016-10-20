package biz.belcorp.ssicc.web.sisicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoCalificacionTramoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazCOMEnviarCargaMasivaEjecutivaNuevaForm;

/**

  */
@ManagedBean
@SessionScoped
public class InterfazCOMEnviarCargaMasivaEjecutivaNuevaAction extends
		BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1800105527516765913L;
	private List siccMarcaList;
	private List siccCanalList;
	private List comTipoComisionistaList;
	private List comTramoList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazCOMEnviarCargaMasivaEjecutivaNuevaForm form = new InterfazCOMEnviarCargaMasivaEjecutivaNuevaForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		// Carga de los combos de Marca, Canal, Accesos y Subaccesos
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccMarcaList = svc.getMarcas();
		this.siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());

		ProcesoCOMCalculoCalificacionTramoService tramoService = (ProcesoCOMCalculoCalificacionTramoService) getBean("spusicc.procesoCOMCalculoCalificacionTramoService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.comTipoComisionistaList = tramoService.getTiposComisionistas(pais
				.getCodigo());
		this.comTramoList = tramoService.getTramos(pais.getCodigo());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #prepareParamsBeforeExecute(java.util.Map,
	 * biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		// TODO Auto-generated method stub
		params = super.prepareParamsBeforeExecute(params, form);
		return params;

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

	/**
	 * @return the comTipoComisionistaList
	 */
	public List getComTipoComisionistaList() {
		return comTipoComisionistaList;
	}

	/**
	 * @param comTipoComisionistaList
	 *            the comTipoComisionistaList to set
	 */
	public void setComTipoComisionistaList(List comTipoComisionistaList) {
		this.comTipoComisionistaList = comTipoComisionistaList;
	}

	/**
	 * @return the comTramoList
	 */
	public List getComTramoList() {
		return comTramoList;
	}

	/**
	 * @param comTramoList
	 *            the comTramoList to set
	 */
	public void setComTramoList(List comTramoList) {
		this.comTramoList = comTramoList;
	}

}
