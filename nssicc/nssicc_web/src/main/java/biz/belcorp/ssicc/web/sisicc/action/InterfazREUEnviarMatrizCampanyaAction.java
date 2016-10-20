/*
 * Created on 26/09/2006 11:32:06 AM
 * biz.belcorp.ssicc.sisicc.web.action.InterfazMYEEnviarMovimientosCuentaCorrienteAction
 */
package biz.belcorp.ssicc.web.sisicc.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazREUEnviarMatrizCampanyaForm;


@ManagedBean
@SessionScoped
public class InterfazREUEnviarMatrizCampanyaAction extends BaseInterfazAbstractAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3203189676172552747L;
	
	private List siccMarcaList;
	private List siccCanalList;
	private List siccAccesoList;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		return new InterfazREUEnviarMatrizCampanyaForm();
	}
	
	
	 public void showPeriodosXAcceso(ValueChangeEvent val){
		 InterfazREUEnviarMatrizCampanyaForm form = (InterfazREUEnviarMatrizCampanyaForm) this.formInterfaz;
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			String acceso = (String) val.getNewValue();
			
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			form.setPeriodoDesde(ajax.getPeriodoDefaultByPaisMarcaCanalAcceso(pais.getCodigo(),form.getCodigoMarca(), 
		    		form.getCodigoCanal(), acceso));
			
			form.setPeriodoHasta(ajax.getPeriodoDefaultByPaisMarcaCanalAcceso(pais.getCodigo(),form.getCodigoMarca(), 
		    		form.getCodigoCanal(), acceso));
	 }
    
	 public void showAccesoXCanal(ValueChangeEvent val){
			log.debug(">>showAccesoXCanal ");
			log.debug("val: "+ val.getNewValue());
			InterfazREUEnviarMatrizCampanyaForm form = (InterfazREUEnviarMatrizCampanyaForm) this.formInterfaz;
			String canal = (String) val.getNewValue();		
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			if (log.isDebugEnabled()) {
				log.debug("loadAcceso");
			}
		
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
			if (StringUtils.isBlank(canal)) {
				this.siccAccesoList = svc
						.getAccesosTodosByCanalByCodigoISO(usuario.getIdioma()
								.getCodigoISO());
			} else {

				this.siccAccesoList = getAccesoList(canal);
			}

			form.setCodigoAcceso(null);
			
			form.setPeriodoDesde(ajax.getPeriodoDefaultByPaisMarcaCanalAcceso(pais.getCodigo(),form.getCodigoMarca(), 
					canal, form.getCodigoAcceso()));
			
			form.setPeriodoHasta(ajax.getPeriodoDefaultByPaisMarcaCanalAcceso(pais.getCodigo(),form.getCodigoMarca(), 
					canal, form.getCodigoAcceso()));
		}
	 
	 
	 @Override
	public String setValidarInterfaz() {
		 InterfazREUEnviarMatrizCampanyaForm form = (InterfazREUEnviarMatrizCampanyaForm) this.formInterfaz;
		 int periodoDesde = Integer.valueOf(form.getPeriodoDesde());
		 int periodoHasta = Integer.valueOf(form.getPeriodoHasta());
		 String msg = "";
		
		 if( periodoDesde > periodoHasta){
			 msg = "El Periodo Hasta debe ser mayor o igual al Periodo Desde";
				
		 }
				
	   return msg;
		 
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
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		 InterfazREUEnviarMatrizCampanyaForm form = (InterfazREUEnviarMatrizCampanyaForm) this.formInterfaz;
		 AjaxService ajax = (AjaxService) getBean("ajaxService");
		 InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

	        // Carga de los combos de Marca, Canal y Acceso
		 siccMarcaList = interfazSiCCService.getMarcas();
	        
		 siccCanalList = interfazSiCCService.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());

		 siccAccesoList = getAccesoList(Constants.CODIGO_CANAL_DEFAULT);

	
		form.setCodigoMarca("");
		form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		form.setCodigoAcceso(Constants.CODIGO_ACCESO_DEFAULT);
		form.setPeriodoDesde(ajax.getPeriodoDefaultByPaisMarcaCanalAcceso(pais.getCodigo(),form.getCodigoMarca(), 
				form.getCodigoCanal(), form.getCodigoAcceso()));
		
		form.setPeriodoHasta(ajax.getPeriodoDefaultByPaisMarcaCanalAcceso(pais.getCodigo(),form.getCodigoMarca(), 
				form.getCodigoCanal(), form.getCodigoAcceso()));
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

	
	

	
    

}
