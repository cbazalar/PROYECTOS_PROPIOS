package biz.belcorp.ssicc.web.sisicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoCalificacionTramoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazCOMEnviarArchivoEjecutivaNuevaAspiranteForm;

/**

  */
@ManagedBean
@SessionScoped
public class InterfazCOMEnviarArchivoEjecutivaNuevaAspiranteAction extends
		BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6700366214550752128L;
	private List comTipoComisionistaList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazCOMEnviarArchivoEjecutivaNuevaAspiranteForm form = new InterfazCOMEnviarArchivoEjecutivaNuevaAspiranteForm();
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
		InterfazCOMEnviarArchivoEjecutivaNuevaAspiranteForm form=(InterfazCOMEnviarArchivoEjecutivaNuevaAspiranteForm) this.formInterfaz;
		form.setTipoComisionista(Constants.CODIGO_TIPO_COMISIONISTA_DEFAULT);
		ProcesoCOMCalculoCalificacionTramoService tramoService = (ProcesoCOMCalculoCalificacionTramoService) getBean("spusicc.procesoCOMCalculoCalificacionTramoService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.comTipoComisionistaList = tramoService.getTiposComisionistas(pais
				.getCodigo());

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

}
