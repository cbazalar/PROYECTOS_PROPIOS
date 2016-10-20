/*
 * Created on 26/09/2006 11:32:06 AM
 * biz.belcorp.ssicc.sisicc.web.action.InterfazMYEEnviarMovimientosCuentaCorrienteAction
 */
package biz.belcorp.ssicc.web.sisicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazMYEEnviarMovimientosCuentaCorrienteForm;


@ManagedBean
@SessionScoped
public class InterfazMYEEnviarMovimientosCuentaCorrienteAction extends BaseInterfazAbstractAction {

   
	private static final long serialVersionUID = -3203189676172552747L;
	
	private List siccCanalList;
	private List siccAccesoList;
	private List siccAccesoTodosList;
	private List siccSubAccesoList;

	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		return new InterfazMYEEnviarMovimientosCuentaCorrienteForm();
	}
	
	
	
	
	   public void showAccesoXCanal(ValueChangeEvent val){
			log.debug(">>showAccesoXCanal ");
			log.debug("val: "+ val.getNewValue());
			InterfazMYEEnviarMovimientosCuentaCorrienteForm form = (InterfazMYEEnviarMovimientosCuentaCorrienteForm) this.formInterfaz;
			String canal = (String) val.getNewValue();		
		      
			
			if (log.isDebugEnabled()) {
				log.debug("loadAcceso");
			}
		
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
			if (StringUtils.isBlank(canal)) {
				this.siccAccesoList = svc
						.getAccesosTodosByCanalByCodigoISO(usuario.getIdioma()
								.getCodigoISO());
				this.siccSubAccesoList = svc
						.getSubaccesosByCodigoISO(usuario.getIdioma()
								.getCodigoISO());
			} else {

				this.siccAccesoList = getAccesoList(canal);
			}

			form.setCodigoAcceso(null);
		}
	   
	   public void showSubAccesoXAcesso(ValueChangeEvent val){
			log.debug(">>showSubAccesoXAcesso ");
			log.debug("val: "+ val.getNewValue());
			InterfazMYEEnviarMovimientosCuentaCorrienteForm form = (InterfazMYEEnviarMovimientosCuentaCorrienteForm) this.formInterfaz;
			String acceso = (String) val.getNewValue();		
		      
			
			if (log.isDebugEnabled()) {
				log.debug("loadSubAcceso");
			}
		
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
			if (StringUtils.isBlank(acceso)) {
				this.siccSubAccesoList = svc
						.getSubaccesosByCodigoISO(usuario.getIdioma()
								.getCodigoISO());

			} else {

				this.siccSubAccesoList =  getSubAccesoList(acceso);
			}

			form.setCodigoSubacceso(null);
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
	    
	    InterfazMYEEnviarMovimientosCuentaCorrienteForm form = (InterfazMYEEnviarMovimientosCuentaCorrienteForm) this.formInterfaz;
        // Carga de los combos de Accesos y Subaccesos
        InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        
        siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
        siccAccesoList = getAccesoList(Constants.CODIGO_CANAL_DEFAULT);
        siccSubAccesoList = getSubAccesoList(Constants.CODIGO_ACCESO_DEFAULT);
        
       
        form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
        form.setCodigoAcceso(Constants.CODIGO_ACCESO_DEFAULT);
        form.setCodigoSubacceso(Constants.CODIGO_SUBACCESO_DEFAULT);
	}

	
	

	
    @Override
    protected Map<String, Object> prepareParamsBeforeExecute(Map params,
    		BaseForm form) throws Exception {
    	params = super.prepareParamsBeforeExecute(params,form);
    	
    	params.put("codigoSubacceso", String.valueOf(params.get("codigoSubacceso")).trim());
    	params.put("codigoAcceso", String.valueOf(params.get("codigoAcceso")).trim());
    	params.put("codigoCanal", String.valueOf(params.get("codigoCanal")).trim());
    	
		HistoricoService historicoService = (HistoricoService) getBean("sisicc.historicoService");
		List historicos = historicoService.getUltimoHistoricoByCodInterfaz(params);
		if (historicos != null) {
	            if (historicos.size() == 1)
	                params.put("fechaInicioUltimoProceso", ((Historico) historicos.get(0)).getFechaInicioProceso());
	        } else {
	            params.put("fechaInicioUltimoProceso", null);
	        }
		return params;
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

	/**
	 * @return the siccAccesoList
	 */
	public List getSiccAccesoList() {
		return siccAccesoList;
	}

	/**
	 * @param siccAccesoList the siccAccesoList to set
	 */
	public void setSiccAccesoList(List siccAccesoList) {
		this.siccAccesoList = siccAccesoList;
	}

	/**
	 * @return the siccAccesoTodosList
	 */
	public List getSiccAccesoTodosList() {
		return siccAccesoTodosList;
	}

	/**
	 * @param siccAccesoTodosList the siccAccesoTodosList to set
	 */
	public void setSiccAccesoTodosList(List siccAccesoTodosList) {
		this.siccAccesoTodosList = siccAccesoTodosList;
	}

	/**
	 * @return the siccSubAccesoList
	 */
	public List getSiccSubAccesoList() {
		return siccSubAccesoList;
	}

	/**
	 * @param siccSubAccesoList the siccSubAccesoList to set
	 */
	public void setSiccSubAccesoList(List siccSubAccesoList) {
		this.siccSubAccesoList = siccSubAccesoList;
	}

    
}
