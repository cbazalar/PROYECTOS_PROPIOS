package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBCarteraSupervisorForm;


/**
 * The Class ReporteCOBCarteraSupervisorAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 19/11/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class ReporteCOBCarteraSupervisorAction extends BaseReporteAbstractAction {	

	private static final long serialVersionUID = -2222041999188186390L;
	private String tipoReporte;
	private List siccSociedadList;
	private LabelValue[] siccEtapaDeudaList;
	private List siccRegionList;
	private List siccZonaList;	
	private boolean bzona;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBCarteraSupervisorForm form = new ReporteCOBCarteraSupervisorForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOBCarteraSupervisorAction.setViewAtributes' method");
		}		
		this.mostrarReporteOCSV = false;
		this.mostrarReporteXLSX = false;
		this.mostrarReporteCSV = false;
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		this.mostrarReporteOJXLSX = false;
		this.mostrarReporteOOXLSX = false;	
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService ajax = (AjaxService) getBean("ajaxService");
				
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		ReporteCOBCarteraSupervisorForm f = (ReporteCOBCarteraSupervisorForm) this.formReporte;					
		f.setCodigoPais(pais.getCodigo());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));		
		f.setCodigoPeriodo(periodo);
		
		this.siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());
		Base base = (Base) this.siccSociedadList.get(0);
		f.setCodigoSociedad(base.getCodigo());
		
		this.siccEtapaDeudaList = ajax.getEtapasDeudaByPaisSociedad(f.getCodigoPais(), base.getCodigo());
		this.siccRegionList = new ArrayList();
		this.siccZonaList = new ArrayList();
		this.bzona = true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
	   return  "reporteCOBCarteraSupervisor" + this.tipoReporte + "XLS";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {			
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReporteCOBCarteraSupervisorForm reporteCOBForm = (ReporteCOBCarteraSupervisorForm) this.formReporte;
						
		this.tipoReporte = reporteCOBForm.getTipoReporte();					
		        				                      
		String codigoPais =  reporteCOBForm.getCodigoPais();
		String codigoSociedad =  reporteCOBForm.getCodigoSociedad();
																                             
		String condicionRegion = obtieneCondicion(reporteCOBForm.getRegionList(), "CARTE.COD_REGI_CLIE", "'");        
        String condicionZonas = obtieneCondicion(reporteCOBForm.getZonaList(), "CARTE.COD_ZONA_CLIE", "'");
        			
		String condicion = condicionZonas + condicionRegion;
		
		log.debug("Condicion : ");
		log.debug(condicion);
		
		params.put("codigoPais", codigoPais);
		params.put("codigoSociedad", codigoSociedad);
		params.put("condicion", condicion);
		
		log.debug("Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
		return params;
	}
	
	/**
	 * loadEtapas.
	 *
	 * @param val the val
	 */
	public void loadEtapas(ValueChangeEvent val) {
		log.debug(">>loadEtapas ");
		log.debug("val: "+val.getNewValue().toString());
		ReporteCOBCarteraSupervisorForm form = (ReporteCOBCarteraSupervisorForm) this.formReporte;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		
		form.setCodigoSociedad(val.getNewValue().toString());
		
		this.siccEtapaDeudaList = ajax.getEtapasDeudaByPaisSociedad(form.getCodigoPais(), form.getCodigoSociedad());
	}
	
	/**
	 * loadRegionesEtapaList.
	 *
	 * @param val the val
	 */  
	public void loadRegionesEtapaList(ValueChangeEvent val) {	
		log.debug(">>loadRegionesEtapaList ");
		log.debug("val: "+val.getNewValue().toString());
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ReporteCOBCarteraSupervisorForm f = (ReporteCOBCarteraSupervisorForm) this.formReporte;	
		LabelValue regiones[] = ajax.getRegionesByPaisSociedadEtapaDeudaPeriodo(f.getCodigoPais(), f.getCodigoSociedad(), 
																				val.getNewValue().toString(), f.getCodigoPeriodo());
		if(regiones != null && regiones.length > 0) {
			this.siccRegionList = Arrays.asList(regiones);
		} else {
			this.siccRegionList = new ArrayList();
		}
	} 
	
	/**
	 * loadRegionesPeriodoList.
	 *
	 * @param val the val
	 */  
	public void loadRegionesPeriodoList(String val) {	
		log.debug(">>loadRegionesPeriodoList ");
		log.debug("val: "+val);
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ReporteCOBCarteraSupervisorForm f = (ReporteCOBCarteraSupervisorForm) this.formReporte;	
		LabelValue regiones[] = ajax.getRegionesByPaisSociedadEtapaDeudaPeriodo(f.getCodigoPais(), f.getCodigoSociedad(), 
																				f.getCodigoEtapaDeuda(), val);
		if(regiones != null && regiones.length > 0) {
			this.siccRegionList = Arrays.asList(regiones);
		} else {
			this.siccRegionList = new ArrayList();
		}
	} 
	
	/**
	 * loadListasTipoVista.
	 *
	 * @param val the val
	 */
	public void loadZonasList(ValueChangeEvent val) {              
		log.debug(">>loadZonasList ");
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		LabelValue zonas[] = ajax.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), "T", "VD", (String[]) val.getNewValue(), "T");
		if(zonas != null && zonas.length > 0) {
			this.siccZonaList = Arrays.asList(zonas);
			this.bzona = false;
		} else {
			this.siccZonaList = new ArrayList();
			this.bzona = true;
		}
	}

	
	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
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
	public List getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the bzona
	 */
	public boolean isBzona() {
		return bzona;
	}

	/**
	 * @param bzona the bzona to set
	 */
	public void setBzona(boolean bzona) {
		this.bzona = bzona;
	}

	/**
	 * @return the siccEtapaDeudaList
	 */
	public LabelValue[] getSiccEtapaDeudaList() {
		return siccEtapaDeudaList;
	}

	/**
	 * @param siccEtapaDeudaList the siccEtapaDeudaList to set
	 */
	public void setSiccEtapaDeudaList(LabelValue[] siccEtapaDeudaList) {
		this.siccEtapaDeudaList = siccEtapaDeudaList;
	}
}