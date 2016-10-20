package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBRecuperacionCarteraVentasForm;


/**
 * The Class ReporteCOBRecuperacionCarteraVentasAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 24/11/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class ReporteCOBRecuperacionCarteraVentasAction extends BaseReporteAbstractAction {
		
	private static final long serialVersionUID = -7806782418697079621L;
	private String tipoVista;
	private List siccSociedadList;
	private List siccRegionList;
	private List siccZonaList;
	private List siccSeccionList;
	private boolean bzona;
	private boolean bseccion;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBRecuperacionCarteraVentasForm form = new ReporteCOBRecuperacionCarteraVentasForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOBRecuperacionCarteraVentasAction.setViewAtributes' method");
		}		
		this.mostrarReporteOCSV = false;
		this.mostrarReporteXLSX = false;
		this.mostrarReporteCSV = false;
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		this.mostrarReporteOJXLSX = false;
		this.mostrarReporteOOXLSX = false;
				
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		ReporteCOBRecuperacionCarteraVentasForm f = (ReporteCOBRecuperacionCarteraVentasForm) this.formReporte;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));		
		f.setCodigoPeriodoInicial(periodo);
		f.setCodigoPeriodoFinal(periodo);
		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());			
		this.siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);
		this.siccZonaList = new ArrayList();
		this.siccSeccionList = new ArrayList();
		this.bzona = true;
		this.bseccion = true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteCOBRecuperacionCarteraVentas" + tipoVista + "XLS";
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
		
		ReporteCOBRecuperacionCarteraVentasForm reporteCOBForm = (ReporteCOBRecuperacionCarteraVentasForm) this.formReporte;
						
		this.tipoVista = reporteCOBForm.getTipoVista();						
		
		String condicionZonas = obtieneCondicion(reporteCOBForm.getZonaList(), "COD_ZONA", "'");
		String condicionRegion = obtieneCondicion(reporteCOBForm.getRegionList(), "COD_REGI", "'");
		String condicionSeccion = obtieneCondicion(reporteCOBForm.getSeccionList(), "COD_SECC", "'");
			
		String condicion = condicionZonas + condicionRegion + condicionSeccion;
								  		        						
		params.put("condicion", condicion);
		
		log.debug(" JFA : Obteniendo el % de Recuperacion");
		
		ConsultaCOBGenericoService serviceCOB = (ConsultaCOBGenericoService) getBean("spusicc.consultaCOBGenericoService");
		
		Integer porcentajeRecuperacion = serviceCOB.getPorcentajeMetaEtapaVentas(params);
		
		log.debug(porcentajeRecuperacion);
		params.put("porcentajeRecuperacion", porcentajeRecuperacion);
		
		log.debug("Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
		return params;		
	}
	
	/**
	 * loadZonas.
	 *
	 * @param val the val
	 */
	public void loadZonas(ValueChangeEvent val) {     
		log.debug(">>loadZonas ");
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		
		String[] regiones= (String[]) val.getNewValue();
		
		if(regiones != null && regiones.length > 0)
		{
			LabelValue zonas[] = ajax.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), "T", "VD", regiones, "T");
			if(zonas != null && zonas.length > 0) {
				this.siccZonaList = Arrays.asList(zonas);
				this.bzona = false;
			} else {
				this.siccZonaList = new ArrayList();
				this.bzona = true;
			}		
		}
		else
		{
			this.siccZonaList = new ArrayList();
			this.bzona = true;
		}		
	}
	
	/**
	 * loadSecciones.
	 *
	 * @param val the val
	 */
	public void loadSecciones(ValueChangeEvent val) {     
		log.debug(">>loadSecciones ");
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		ReporteCOBRecuperacionCarteraVentasForm reporteCOBForm = (ReporteCOBRecuperacionCarteraVentasForm) this.formReporte;
		
		String[] zonas = (String[]) val.getNewValue();
				
		if(zonas != null && zonas.length > 0)
		{		
			LabelValue secciones[] = ajax.getSeccionMultipleByPaisMarcaCanalRegionZona(pais.getCodigo(), "T", "VD", 
					   reporteCOBForm.getRegionList(), 
					   zonas, "T");
			if(secciones != null && secciones.length > 0) {
				this.siccSeccionList = Arrays.asList(secciones);
				this.bseccion = false;
			} else {
				this.siccSeccionList = new ArrayList();
				this.bseccion = true;
			}		
			
		}
		else
		{
			this.siccSeccionList = new ArrayList();
			this.bseccion = true;
		}
	}
	

	/**
	 * @return the tipoVista
	 */
	public String getTipoVista() {
		return tipoVista;
	}

	/**
	 * @param tipoVista the tipoVista to set
	 */
	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
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
	 * @return the siccSeccionList
	 */
	public List getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList the siccSeccionList to set
	 */
	public void setSiccSeccionList(List siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
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
	 * @return the bseccion
	 */
	public boolean isBseccion() {
		return bseccion;
	}

	/**
	 * @param bseccion the bseccion to set
	 */
	public void setBseccion(boolean bseccion) {
		this.bseccion = bseccion;
	}
	
}