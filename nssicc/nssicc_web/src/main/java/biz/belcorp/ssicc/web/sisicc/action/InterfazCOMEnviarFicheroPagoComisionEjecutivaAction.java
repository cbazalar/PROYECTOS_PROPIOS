package biz.belcorp.ssicc.web.sisicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoCalificacionTramoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazCOMEnviarFicheroPagoComisionEjecutivaForm;

/**

  */
@ManagedBean
@SessionScoped
public class InterfazCOMEnviarFicheroPagoComisionEjecutivaAction extends
		BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8458524143661824562L;
	private List comCodigoComisionList;
	private List comTipoComisionistaList;
	private List siccComisionList;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazCOMEnviarFicheroPagoComisionEjecutivaForm form = new InterfazCOMEnviarFicheroPagoComisionEjecutivaForm();
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
		    InterfazCOMEnviarFicheroPagoComisionEjecutivaForm form=(InterfazCOMEnviarFicheroPagoComisionEjecutivaForm) this.formInterfaz;
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();      
	        InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

			log.debug("pais.getCodigo()" + pais.getCodigo());
			form.setTipoComisionista(Constants.CODIGO_TIPO_COMISIONISTA_DEFAULT);
			this.comCodigoComisionList=svc.getListCodComision(pais.getCodigo());
		
	        ProcesoCOMCalculoCalificacionTramoService tramoService = (ProcesoCOMCalculoCalificacionTramoService) 
			getBean("spusicc.procesoCOMCalculoCalificacionTramoService");		
	        this.comTipoComisionistaList= tramoService.getTiposComisionistas(pais.getCodigo());	
	        this.siccComisionList= svc.getComision();

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
	 * @return the comCodigoComisionList
	 */
	public List getComCodigoComisionList() {
		return comCodigoComisionList;
	}

	/**
	 * @param comCodigoComisionList the comCodigoComisionList to set
	 */
	public void setComCodigoComisionList(List comCodigoComisionList) {
		this.comCodigoComisionList = comCodigoComisionList;
	}

	/**
	 * @return the comTipoComisionistaList
	 */
	public List getComTipoComisionistaList() {
		return comTipoComisionistaList;
	}

	/**
	 * @param comTipoComisionistaList the comTipoComisionistaList to set
	 */
	public void setComTipoComisionistaList(List comTipoComisionistaList) {
		this.comTipoComisionistaList = comTipoComisionistaList;
	}

	/**
	 * @return the siccComisionList
	 */
	public List getSiccComisionList() {
		return siccComisionList;
	}

	/**
	 * @param siccComisionList the siccComisionList to set
	 */
	public void setSiccComisionList(List siccComisionList) {
		this.siccComisionList = siccComisionList;
	}
	
	



}

