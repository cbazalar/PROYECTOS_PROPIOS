package biz.belcorp.ssicc.web.spusicc.action;

import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.form.InterfazPRECambioCodigoVentaModificaOfertaExecuteForm;

@SessionScoped
@ManagedBean
public class InterfazPRECambioCodigoVentaModificaOfertaExecute extends
		BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6311675329456219705L;
	private String codigoPaisExportar;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazPRECambioCodigoVentaModificaOfertaExecuteForm form = new InterfazPRECambioCodigoVentaModificaOfertaExecuteForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		InterfazPRECambioCodigoVentaModificaOfertaAction action = findManageBean("interfazPRECambioCodigoVentaModificaOfertaAction");
		MantenimientoPRECambioCodigoVentaSearchAction action2 = findManageBean("mantenimientoPRECambioCodigoVentaSearchAction");
		InterfazPRECambioCodigoVentaModificaOfertaExecuteForm f = (InterfazPRECambioCodigoVentaModificaOfertaExecuteForm) this.formInterfaz;
		f.setCodigoInterfaz(action.getCodigoInterfaz());
		params = super.prepareParamsBeforeExecute(params, form);

		params.put("codigoPaisExportar", getCodigoPaisExportar());
		params.put("oidOferta", action2.getOidOferta());
		params.put("oidDetaOferta", action2.getOidDetaOferta());
		params.put("numSecUsuario",action2.getNumSecUsuario());

		return params;

	}

	/**
	 * @return the codigoPaisExportar
	 */
	public String getCodigoPaisExportar() {
		return codigoPaisExportar;
	}

	/**
	 * @param codigoPaisExportar the codigoPaisExportar to set
	 */
	public void setCodigoPaisExportar(String codigoPaisExportar) {
		this.codigoPaisExportar = codigoPaisExportar;
	}
	
	

}
