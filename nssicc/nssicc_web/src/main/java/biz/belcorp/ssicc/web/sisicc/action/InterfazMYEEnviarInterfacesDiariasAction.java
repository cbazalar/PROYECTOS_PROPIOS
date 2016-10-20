/*
 * Created on 26/09/2006 11:32:06 AM
 * biz.belcorp.ssicc.sisicc.web.action.InterfazMYEEnviarMovimientosCuentaCorrienteAction
 */
package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazMYEEnviarInterfacesDiariasForm;


@ManagedBean
@SessionScoped
public class InterfazMYEEnviarInterfacesDiariasAction extends BaseInterfazAbstractAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3203189676172552747L;
	
	private List siccMarcaList;
	private List siccCanalList;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		return new InterfazMYEEnviarInterfacesDiariasForm();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
	        log.debug("Entering 'setViewAttributes' method");
	    }
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		siccMarcaList = svc.getMarcas();		
		siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
	
		InterfazMYEEnviarInterfacesDiariasForm form = (InterfazMYEEnviarInterfacesDiariasForm) this.formInterfaz;
		
		String codigoPeriodoActual = this.getmPantallaPrincipalBean().getCodigoPeriodoActual();
		
		if (StringUtils.isBlank(codigoPeriodoActual)){
			codigoPeriodoActual = DateFormatUtils.format(new Date(System.currentTimeMillis()), "yyyyMM");
		}
		
		form.setCodigoPeriodo(codigoPeriodoActual);
		form.setFechaFacturacion(new Date(System.currentTimeMillis()));
		form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
	    form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		InterfazMYEEnviarInterfacesDiariasForm f = (InterfazMYEEnviarInterfacesDiariasForm) this.formInterfaz;
		
		params = super.prepareParamsBeforeExecute(params, form);
		Date fecha = f.getFechaFacturacion();
		
		params.put("fechaFacturacion", DateFormatUtils.format(fecha, "dd/MM/yyyy"));
		
	   return params;
	}
	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList the siccMarcaList to set
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
	 * @param siccCanalList the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	
	
	
	

	
    

}
