/*
 * Created on 26/09/2006 11:32:06 AM
 * biz.belcorp.ssicc.sisicc.web.action.InterfazMYEEnviarMovimientosCuentaCorrienteAction
 */
package biz.belcorp.ssicc.web.sisicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSAPFIColombiaForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazDATEnviarAdministracionFlujosAction.java.html">
 * <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 * 
  */
@ManagedBean
@SessionScoped
public class InterfazSAPFIColombiaAction extends BaseInterfazAbstractAction {



	/**
	 * 
	 */
	private static final long serialVersionUID = 8810898854443478348L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazSAPFIColombiaForm interfazSAPFIColombiaForm = new InterfazSAPFIColombiaForm();
		return interfazSAPFIColombiaForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'setViewAttributes' method");
        }
		
		InterfazSAPFIColombiaForm formSAPFI = (InterfazSAPFIColombiaForm)formInterfaz;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        
        SimpleDateFormat sdf;		
		sdf = new SimpleDateFormat("yyyyMM");
		formSAPFI.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", formSAPFI.getCodigoPais());
		
		List lista = service.getPeriodoFechaProcesoActual(criteria);
		String codigoPeriodo = ((HashMap)lista.get(0)).get("cod_peri").toString();
		String fechaProceso = ((HashMap)lista.get(0)).get("fec_proc").toString();
		
		criteria.put("codigoProcesoBatch", codigoProcesoBatch);
				
		formSAPFI.setCodigoPeriodo(codigoPeriodo);
		formSAPFI.setFechaProcesoD(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, fechaProceso));
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		params =  super.prepareParamsBeforeExecute(params, form);
		InterfazSAPFIColombiaForm formSAPFI = (InterfazSAPFIColombiaForm)formInterfaz;
	
		formSAPFI.setFechaProceso(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, formSAPFI.getFechaProcesoD()));
		params.put("fechaProceso", formSAPFI.getFechaProceso());
		
		return params;
	}
}