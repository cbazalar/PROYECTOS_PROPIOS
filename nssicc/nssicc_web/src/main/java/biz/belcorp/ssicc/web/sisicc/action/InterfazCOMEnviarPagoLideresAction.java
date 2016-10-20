package biz.belcorp.ssicc.web.sisicc.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazCOMEnviarPagoLideresForm;

@SessionScoped
@ManagedBean
public class InterfazCOMEnviarPagoLideresAction extends
		BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2719997060438170243L;
	private List siccComisionList;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazCOMEnviarPagoLideresForm form = new InterfazCOMEnviarPagoLideresForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		InterfazCOMEnviarPagoLideresForm f = (InterfazCOMEnviarPagoLideresForm) this.formInterfaz;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccComisionList = service.getComision();

		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoPeriodo(codigoPeriodo);

	}

	/**
	 * @return the siccComisionList
	 */
	public List getSiccComisionList() {
		return siccComisionList;
	}

	/**
	 * @param siccComisionList
	 *            the siccComisionList to set
	 */
	public void setSiccComisionList(List siccComisionList) {
		this.siccComisionList = siccComisionList;
	}

}
