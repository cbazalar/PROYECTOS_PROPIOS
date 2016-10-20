package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.app.ProcesoAPPSecuenciarZonaTerritorioService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaAPPSecuenciaTerritorioForm;




@ManagedBean
@SessionScoped
public class ConsultaAPPSecuenciaTerritorioAction extends BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8774551054058084279L;
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};
	private List appSecuenciaZonaList;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ConsultaAPPSecuenciaTerritorioForm form = new ConsultaAPPSecuenciaTerritorioForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes");			
		}
		
		ConsultaAPPSecuenciaTerritorioForm f = (ConsultaAPPSecuenciaTerritorioForm) this.formReporte;
		ProcesoAPPSecuenciarZonaTerritorioService service = 
			(ProcesoAPPSecuenciarZonaTerritorioService) getBean("spusicc.procesoAPPSecuenciarZonaTerritorioService");
		Map criteria = new HashMap();

		if(f.getRegionList() != null && f.getRegionList().length > 0 && !f.getRegionList()[0].equals(""))
			criteria.put("codigoRegion", f.getRegionList());

		if(f.getZonaList() != null && f.getZonaList().length > 0 && !f.getZonaList()[0].equals(""))
			criteria.put("codigoZona", f.getZonaList());

		List listaZonas = service.getSecuenciaTerritorioList(criteria);

//		session.removeAttribute(Constants.APP_SECUENCIA_ZONA_LIST );
		this.appSecuenciaZonaList=listaZonas;

		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		List l = new ArrayList();

		LabelValue[] lv = aSvc.getZonasMultipleByPaisMarcaCanalRegion(
				this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),
																	  Constants.CODIGO_MARCA_DEFAULT,
																	  Constants.CODIGO_CANAL_DEFAULT,
																	  f.getRegionList(),
																	  Constants.FORMATEAR_TODOS);			
		for (int i = 1; i < lv.length; i++) {
			Base base = new Base();
			base.setCodigo(lv[i].getValue());
			base.setDescripcion(lv[i].getLabel());
			l.add(base);
		};

//		request.getSession().removeAttribute(Constants.SICC_ZONA_LIST);
//		request.getSession().setAttribute(Constants.SICC_ZONA_LIST,l);

		return listaZonas;
		

	
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");			
		}
		this.mostrarListaBusqueda = true;
		this.mostrarBotonBuscar = true;
		this.mostrarReportePDF = false;
		
		
		ConsultaAPPSecuenciaTerritorioForm f = (ConsultaAPPSecuenciaTerritorioForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteriaOperacion = new HashMap();

		criteriaOperacion.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		this.siccRegionList= reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);
//		request.getSession().setAttribute(Constants.SICC_ZONA_LIST,new ArrayList());
//		session.removeAttribute(Constants.APP_SECUENCIA_ZONA_LIST );

		f.setRegionList(null);
		f.setZonaList(null);
		
		
	}
	
	/**
	 * Cambia zonas by region.
	 *
	 * @param val the val
	 */
	public void cambiaZonasByRegion(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("cambiaZonas...");
		}
		
		String[] valor = (String[]) val.getNewValue();
		ConsultaAPPSecuenciaTerritorioForm f = (ConsultaAPPSecuenciaTerritorioForm) this.formReporte;

		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.setSiccZonaList(ajax.getZonasMultipleByPaisMarcaCanalRegion(
				this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT,valor,Constants.FORMATEAR_TODOS));
		if(valor.length==0){
			setSiccZonaList(null);
			f.setZonaList(null);
		}
		
	
	}
	


	@Override
	protected String devuelveNombreReporte() throws Exception {
		return " ";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return " ";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		return params;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
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
	 * @return the appSecuenciaZonaList
	 */
	public List getAppSecuenciaZonaList() {
		return appSecuenciaZonaList;
	}

	/**
	 * @param appSecuenciaZonaList the appSecuenciaZonaList to set
	 */
	public void setAppSecuenciaZonaList(List appSecuenciaZonaList) {
		this.appSecuenciaZonaList = appSecuenciaZonaList;
	}	
	
	
	
	
	
	

}
