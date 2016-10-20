package biz.belcorp.ssicc.web.sisicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSAMEnviarInicializacionStocksForm;

@ManagedBean
@SessionScoped
public class InterfazSAMEnviarInicializacionStocksAction extends BaseInterfazAbstractAction{
	
	private static final long serialVersionUID = 4550469562356891027L;
	
	private List siccAlmacenList;
	private List siccEstadoMercaList;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazSAMEnviarInicializacionStocksForm interfazForm= new  InterfazSAMEnviarInicializacionStocksForm();
		return interfazForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSAMEnviarInicializacionStocksForm f = (InterfazSAMEnviarInicializacionStocksForm) this.formInterfaz;
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccAlmacenList=svc.getAlmacenesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccEstadoMercaList=svc.getEstadosMercaderiaByCodigoISO(usuario.getIdioma().getCodigoISO());
		f.setCodigoAlmacen(Constants.CODIGO_ALMACEN_DEFAULT);
		f.setCodigoEstadoMerca(Constants.CODIGO_ESTADO_MERCA_DEFAULT);		
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		params = super.prepareParamsBeforeExecute(params, form);
		return params;
	}

	public List getSiccAlmacenList() {
		return siccAlmacenList;
	}

	public void setSiccAlmacenList(List siccAlmacenList) {
		this.siccAlmacenList = siccAlmacenList;
	}

	public List getSiccEstadoMercaList() {
		return siccEstadoMercaList;
	}

	public void setSiccEstadoMercaList(List siccEstadoMercaList) {
		this.siccEstadoMercaList = siccEstadoMercaList;
	}
	
}
