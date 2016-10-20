package biz.belcorp.ssicc.web.sisicc.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazCOBEnviarDatosInfocorpForm;

@ManagedBean
@SessionScoped
public class InterfazCOBEnviarDatosInfocorpAction extends
		BaseInterfazAbstractAction {

	private static final long serialVersionUID = -6455170967708439482L;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazCOBEnviarDatosInfocorpForm form = new InterfazCOBEnviarDatosInfocorpForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		InterfazCOBEnviarDatosInfocorpForm form = (InterfazCOBEnviarDatosInfocorpForm) this.formInterfaz;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		form.setCampanyaInicial("199501");
		form.setImporte("1");
		form.setDiasVencimiento("63");
		form.setCodigoPais(pais.getCodigo());	
	}
}