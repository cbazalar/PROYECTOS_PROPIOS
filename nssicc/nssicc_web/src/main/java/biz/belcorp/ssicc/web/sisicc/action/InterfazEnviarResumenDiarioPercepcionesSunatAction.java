package biz.belcorp.ssicc.web.sisicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazEnviarResumenDiarioPercepcionesSunatForm;

@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class InterfazEnviarResumenDiarioPercepcionesSunatAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = -8188085087079443339L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazEnviarResumenDiarioPercepcionesSunatForm interfazPEREnviar = new InterfazEnviarResumenDiarioPercepcionesSunatForm();
		return interfazPEREnviar;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'setViewAttributes' method");
        }
        InterfazEnviarResumenDiarioPercepcionesSunatForm interfazPEREnviar = (InterfazEnviarResumenDiarioPercepcionesSunatForm) this.formInterfaz;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        interfazPEREnviar.setFechaDesde(sdf.format(new Date(System.currentTimeMillis())));       
        interfazPEREnviar.setFechaDesdeD(new Date(System.currentTimeMillis()));       
        Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
        interfazPEREnviar.setCodigoPais(pais.getCodigo());
    }
	
	
	 
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
        
        InterfazEnviarResumenDiarioPercepcionesSunatForm f = (InterfazEnviarResumenDiarioPercepcionesSunatForm) form;
        if(f.getFechaDesdeD() != null)
			f.setFechaDesde(DateUtil.convertDateToString(f.getFechaDesdeD()));			
        
        params =  super.prepareParamsBeforeExecute(params, form); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");      
        String fechagenera = sdf.format(f.getFechaDesdeD());    
        params.put("fechaGenera", fechagenera);
        return params;
    }	
	
}
