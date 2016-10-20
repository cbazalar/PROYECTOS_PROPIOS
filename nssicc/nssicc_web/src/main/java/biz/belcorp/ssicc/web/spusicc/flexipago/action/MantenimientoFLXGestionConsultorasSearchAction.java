/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.flexipago.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.swing.event.ChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.flexipago.MantenimientoFLXModeloOtorgamientoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.flexipago.form.MantenimientoFLXGestionConsultorasSearchForm;

/**
 * @author Sigcomt
 *
 */
@ManagedBean
@SessionScoped
public class MantenimientoFLXGestionConsultorasSearchAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3936459200899078606L;
	
	private List flxMotivoRechazoList = new ArrayList();
	private List flxEstatusRechazoList = new ArrayList();
	private LabelValue [] siccZonaList = {};
	private LabelValue [] siccRegionList = {};

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoFLXGestionConsultorasSearchForm form = new MantenimientoFLXGestionConsultorasSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		String mensaje="";
		if(log.isDebugEnabled()){
			log.debug("setViewAttributes - MantenimientoFLXGestionConsultorasSearchAction");
		}
		MantenimientoFLXGestionConsultorasSearchForm f = (MantenimientoFLXGestionConsultorasSearchForm)this.formBusqueda;
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService) getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		HashMap criteria =new HashMap();
		this.flxMotivoRechazoList = service.getMotivosRechazoByCriteria(criteria);
		this.flxEstatusRechazoList = service.getEstatusRechazoByCriteria(criteria);
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		AjaxService ajax = (AjaxService)getBean("ajaxService");
		this.siccRegionList = ajax.getRegionesByPaisMarcaCanal( pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoRegion(this.siccRegionList[0].getValue());
		this.siccZonaList = ajax.getZonasByPaisMarcaCanalRegion( pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, f.getCodigoRegion());
		f.setCodigoZona(this.siccZonaList[0].getValue());
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;

	}
	
	public void loadZonas(ValueChangeEvent event){
		if(log.isDebugEnabled()){
			log.debug("loadZonas");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String region = (String)event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		this.siccZonaList = ajax.getZonasByPaisMarcaCanalRegion( pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, region);
		
	}

	/**
	 * @return the flxMotivoRechazoList
	 */
	public List getFlxMotivoRechazoList() {
		return flxMotivoRechazoList;
	}

	/**
	 * @param flxMotivoRechazoList the flxMotivoRechazoList to set
	 */
	public void setFlxMotivoRechazoList(List flxMotivoRechazoList) {
		this.flxMotivoRechazoList = flxMotivoRechazoList;
	}

	/**
	 * @return the flxEstatusRechazoList
	 */
	public List getFlxEstatusRechazoList() {
		return flxEstatusRechazoList;
	}

	/**
	 * @param flxEstatusRechazoList the flxEstatusRechazoList to set
	 */
	public void setFlxEstatusRechazoList(List flxEstatusRechazoList) {
		this.flxEstatusRechazoList = flxEstatusRechazoList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

}
