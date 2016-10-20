package biz.belcorp.ssicc.web.spusicc.app.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.app.ProcesoAPPSecuenciarZonaTerritorioService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.app.form.ProcesoAPPZonasTerritoriosSinSecuenciaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"unchecked","rawtypes"})
public class ProcesoAPPZonasTerritoriosSinSecuenciaAction extends BaseProcesoAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2559305383512976618L;
	private List procesoAPPZonaSinSecuenciarList = new ArrayList();
	private DataTableModel procesoAPPZonaSinSecuenciarModel = new DataTableModel();	
	private List procesoAPPTerritorioSinSecuenciarList = new ArrayList();
	private DataTableModel procesoAPPTerritorioSinSecuenciarModel = new DataTableModel();

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoAPPZonasTerritoriosSinSecuenciaForm procesoForm = new ProcesoAPPZonasTerritoriosSinSecuenciaForm();
		return procesoForm;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarBotonExecute = false;
		this.mostrarListaBusqueda = false;
		this.mostrarPanelAdicionalProceso= false; 
		ProcesoAPPSecuenciarZonaTerritorioService service = (ProcesoAPPSecuenciarZonaTerritorioService) getBean("spusicc.procesoAPPSecuenciarZonaTerritorioService");
		service.deleteRutasTerri();		
	}
	
	@Override
	protected List setFindAttributes() throws Exception 
	{		
		ProcesoAPPSecuenciarZonaTerritorioService service = (ProcesoAPPSecuenciarZonaTerritorioService) getBean("spusicc.procesoAPPSecuenciarZonaTerritorioService");
		this.mostrarPanelAdicionalProceso= false;
		List listaZonas = service.getZonasSinSecuenciarList();
		this.procesoAPPZonaSinSecuenciarList = listaZonas;
		this.procesoAPPZonaSinSecuenciarModel=  new DataTableModel(this.procesoAPPZonaSinSecuenciarList);
		
		List listaTerritorios = service.getTerritoriosSinSecuenciarList();
		this.procesoAPPTerritorioSinSecuenciarList = listaTerritorios;		
		this.procesoAPPTerritorioSinSecuenciarModel = new DataTableModel(this.procesoAPPTerritorioSinSecuenciarList);		
		
		List listFake = new ArrayList();
		
		if((listaZonas!=null && listaZonas.size()>0)||(listaTerritorios!=null && listaTerritorios.size()>0)){
			this.mostrarPanelAdicionalProceso= true;
			if(listaZonas!=null && listaZonas.size()>0)
				listFake = listaZonas;
			else
				listFake = listaTerritorios;
			
		}
		
		return listFake;
	}
	
	public void procesar(ActionEvent event)
	{
		if (log.isDebugEnabled()){
			log.debug("Entering 'procesar' method");
		}
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		Map criteria = new HashMap();
		criteria.put("codUsuario", usuario.getLogin());
				
		ProcesoAPPSecuenciarZonaTerritorioService service = (ProcesoAPPSecuenciarZonaTerritorioService) getBean("spusicc.procesoAPPSecuenciarZonaTerritorioService");
		service.executeProcesoSecuenciarZonaTerritorio(criteria);
		
		this.addInfo("", this.getResourceMessage("procesoAPPZonasTerritoriosSinSecuenciaForm.process"));
		
		List listaZonas = service.getZonasSinSecuenciarList();
		this.procesoAPPZonaSinSecuenciarList = listaZonas;
		
		List listaTerritorios = service.getTerritoriosSinSecuenciarList();
		this.procesoAPPTerritorioSinSecuenciarList = listaTerritorios;
		
		this.procesoAPPZonaSinSecuenciarModel = new DataTableModel(this.procesoAPPZonaSinSecuenciarList);
		this.procesoAPPTerritorioSinSecuenciarModel = new DataTableModel(this.procesoAPPTerritorioSinSecuenciarList);	
	}

	public List getProcesoAPPZonaSinSecuenciarList() {
		return procesoAPPZonaSinSecuenciarList;
	}

	public void setProcesoAPPZonaSinSecuenciarList(
			List procesoAPPZonaSinSecuenciarList) {
		this.procesoAPPZonaSinSecuenciarList = procesoAPPZonaSinSecuenciarList;
	}

	public List getProcesoAPPTerritorioSinSecuenciarList() {
		return procesoAPPTerritorioSinSecuenciarList;
	}

	public void setProcesoAPPTerritorioSinSecuenciarList(
			List procesoAPPTerritorioSinSecuenciarList) {
		this.procesoAPPTerritorioSinSecuenciarList = procesoAPPTerritorioSinSecuenciarList;
	}

	public DataTableModel getProcesoAPPTerritorioSinSecuenciarModel() {
		return procesoAPPTerritorioSinSecuenciarModel;
	}

	public void setProcesoAPPTerritorioSinSecuenciarModel(
			DataTableModel procesoAPPTerritorioSinSecuenciarModel) {
		this.procesoAPPTerritorioSinSecuenciarModel = procesoAPPTerritorioSinSecuenciarModel;
	}

	/**
	 * @return the procesoAPPZonaSinSecuenciarModel
	 */
	public DataTableModel getProcesoAPPZonaSinSecuenciarModel() {
		return procesoAPPZonaSinSecuenciarModel;
	}

	/**
	 * @param procesoAPPZonaSinSecuenciarModel the procesoAPPZonaSinSecuenciarModel to set
	 */
	public void setProcesoAPPZonaSinSecuenciarModel(
			DataTableModel procesoAPPZonaSinSecuenciarModel) {
		this.procesoAPPZonaSinSecuenciarModel = procesoAPPZonaSinSecuenciarModel;
	}	
}