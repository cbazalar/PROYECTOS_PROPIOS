package biz.belcorp.ssicc.web.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteEmitidosStubAction extends BaseReporteAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6268785854908837265L;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		// TODO Auto-generated method stub
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
