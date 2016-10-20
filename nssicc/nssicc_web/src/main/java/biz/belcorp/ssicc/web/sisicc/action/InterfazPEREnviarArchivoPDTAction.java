package biz.belcorp.ssicc.web.sisicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazPEREnviarArchivoPDTForm;


/**
 * The Class InterfazPEREnviarArchivoPDTAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 01/12/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class InterfazPEREnviarArchivoPDTAction extends BaseInterfazAbstractAction {
	
	private static final long serialVersionUID = 8233142814382347659L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazPEREnviarArchivoPDTForm interfazPEREnviar = new InterfazPEREnviarArchivoPDTForm();
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
        InterfazPEREnviarArchivoPDTForm interfazPEREnviar = (InterfazPEREnviarArchivoPDTForm) this.formInterfaz;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        interfazPEREnviar.setFechaDesde(sdf.format(new Date(System.currentTimeMillis())));
        interfazPEREnviar.setFechaHasta(sdf.format(new Date(System.currentTimeMillis())));
        interfazPEREnviar.setFechaDesdeD(new Date(System.currentTimeMillis()));
        interfazPEREnviar.setFechaHastaD(new Date(System.currentTimeMillis()));
        Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
        interfazPEREnviar.setCodigoPais(pais.getCodigo());
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarInterfaz() {
		if(log.isDebugEnabled()){
			log.debug("setValidarInterfaz");
		}
		InterfazPEREnviarArchivoPDTForm form = (InterfazPEREnviarArchivoPDTForm) this.formInterfaz;
		if(form.getFechaDesdeD() != null && form.getFechaHastaD() != null){
			if(form.getFechaDesdeD().compareTo(form.getFechaHastaD()) > 0){
				this.setMensajeAlertaDefault(this.getResourceMessage("errors.compare.dates"));
				return this.getMensajeAlertaDefault();
			}
		}
		return null;
	}
	 
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
        
        InterfazPEREnviarArchivoPDTForm f = (InterfazPEREnviarArchivoPDTForm) form;
        if(f.getFechaHastaD() != null && f.getFechaDesdeD() != null){
			f.setFechaDesde(DateUtil.convertDateToString(f.getFechaDesdeD()));
			f.setFechaHasta(DateUtil.convertDateToString(f.getFechaHastaD()));
        }
        params =  super.prepareParamsBeforeExecute(params, form);
        params.put("codigoPaisLbel", f.getCodigoPais().substring(0,2) + Constants.FIN_CODIGO_PAIS_LBEL);
        
        return params;
    }	
	
}
