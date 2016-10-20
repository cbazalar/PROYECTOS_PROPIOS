package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazRETRecepcionarVentasRetailCabDetForm;

@ManagedBean
@SessionScoped
public class InterfazRETRecepcionarVentasRetailCabDetAction extends
		BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3622050720244724536L;
	private boolean visible;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazRETRecepcionarVentasRetailCabDetForm form = new InterfazRETRecepcionarVentasRetailCabDetForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}

		this.visible = false;

	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {

		InterfazRETRecepcionarVentasRetailCabDetForm form1 = (InterfazRETRecepcionarVentasRetailCabDetForm) this.formInterfaz;
		if (form1.getIndicadorReproceso().equals("true"))
			form1.setIndicadorReproceso("1");
		else
			form1.setIndicadorReproceso("0");
		form1.setFechaInicio(DateUtil.convertDateToString(form1
				.getFechaInicioD()));
		form1.setFechaFinal(DateUtil.convertDateToString(form1.getFechaFinalD()));
		params = super.prepareParamsBeforeExecute(params, form);
		return params;

	}

	/**
	 * Metodo ocultar campos
	 * 
	 * @param val
	 */
	public void loadOcultar(ValueChangeEvent val) {

		String opcion = (String) val.getNewValue().toString();
		if (opcion == "true") {
			setVisible(true);

		} else {
			setVisible(false);
		}
	}

	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visible
	 *            the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
