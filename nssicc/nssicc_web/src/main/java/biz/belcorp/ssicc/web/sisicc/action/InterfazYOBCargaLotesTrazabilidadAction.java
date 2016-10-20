package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazYOBCargaLotesTrazabilidadForm;

@ManagedBean
@SessionScoped
public class InterfazYOBCargaLotesTrazabilidadAction extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7033424493421291202L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazYOBCargaLotesTrazabilidadForm formInterfaz = new InterfazYOBCargaLotesTrazabilidadForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,BaseForm form) throws Exception 
	{
		Map queryParams = super.prepareParamsBeforeExecute(params, form);
						
		String indicadorNovedad = "1";
		queryParams.put("indicadorNovedad", indicadorNovedad);
		
		String codigoPeriodo = "";
		String fechaFacturacion = "";
		queryParams.put("codigoPeriodo", codigoPeriodo);
		queryParams.put("fechaFacturacion", fechaFacturacion);

		return queryParams;		
	}

}
