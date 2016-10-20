package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOBReporteDetalladoCobranza36DiasForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;


/**

 */
@ManagedBean
@SessionScoped
public class ReporteCOBReporteDetalladoCobranza36DiasAction extends BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4902609111751997753L;
	List siccSociedadList = new ArrayList();
	List siccRegionList = new ArrayList();
	List siccZonaList = new ArrayList();
	private String codigoIdiomaISO;
	private String formatoReporte;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBReporteDetalladoCobranza36DiasForm form = new ReporteCOBReporteDetalladoCobranza36DiasForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		
		ReporteCOBReporteDetalladoCobranza36DiasForm form = (ReporteCOBReporteDetalladoCobranza36DiasForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion())){
			return "reporteCOBReporteDetalladoCobranza36DiasXLS";
		}else{
		   return "reporteMaestroHorizontal";
		}
		
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if ("PDF".equals(formReporte.getFormatoExportacion())){
			 return "reporteCOBReporteDetalladoCobranza36DiasPDF";
		}else{
			return null;
		}

	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReporteCOBReporteDetalladoCobranza36DiasForm reporteCOBForm = (ReporteCOBReporteDetalladoCobranza36DiasForm) this.formReporte;
		formatoReporte = reporteCOBForm.getFormatoExportacion();
		
		String condicionRegion = "";
		String condicionZona = "";
		
		condicionRegion = this.obtieneCondicion(reporteCOBForm.getCodigoRegion(), "cbz.COD_REGI", "'");
		condicionZona = this.obtieneCondicion(reporteCOBForm.getCodigoZona(), "cbz.COD_ZONA", "'");
		 
		params.put("titulo", getResourceMessage("reporteCOBReporteDetalladoCobranza36DiasForm.titulo"));			
		params.put("codigoPais",reporteCOBForm.getCodigoPais());
		params.put("codigoPeriodoInicio",reporteCOBForm.getCodigoPeriodoInicio());
		params.put("codigoPeriodoFin",reporteCOBForm.getCodigoPeriodoFin());
		params.put("codigoSociedad",reporteCOBForm.getCodigoSociedad());
		params.put("condicionRegion", condicionRegion!=null?condicionRegion:"");
		params.put("condicionZona", condicionZona!=null?condicionZona:"");
					
		reporteCOBForm.setFormatoExportacion(formatoReporte);
		
		return params;
	}
	
	public String setValidarReporte() {
		ReporteCOBReporteDetalladoCobranza36DiasForm form = (ReporteCOBReporteDetalladoCobranza36DiasForm)this.formReporte;
		int codperini = Integer.parseInt(form.getCodigoPeriodoInicio());
		int codperfin = Integer.parseInt(form.getCodigoPeriodoFin());
		if(codperfin<codperini){
			String mensaje =  this.getResourceMessage("reporteCOBReporteDetalladoCobranza36DiasForm.msg.validacionCampanha");
			return mensaje;
		}

	    					
	    return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOBReporteDetalladoCobranza36DiasAction.setViewAtributes' method");
		}
	
		this.mostrarReporteXLS = true;
	
		if(log.isDebugEnabled()){
			log.debug("Entro a ReporteCOBReporteDetalladoCobranza36DiasAction - setViewAttributes");	
		}
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();	
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());
		siccRegionList = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		siccZonaList = new ArrayList();
		codigoIdiomaISO = usuario.getIdioma().getCodigoISO();
		
		log.info("Salio a ReporteCOBReporteDetalladoCobranza36DiasAction - setViewAttributes");
	}
	
	
	/**
	 * Show zonasx region.
	 *
	 * @param val the val
	 */
	public void showZonasxRegion(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if(StringUtils.isNotEmpty(val.getNewValue().toString()) 
				|| StringUtils.isNotBlank(val.getNewValue().toString())){
			String[] regionListado = (String[])val.getNewValue();
			log.debug(regionListado.length);
			
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			if(regionListado.length>0){
				siccZonaList = Arrays.asList(ajax.getZonasMultipleByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), "T", "VD", regionListado,"T"));			
			}else{
				siccZonaList = new ArrayList();
			}
		}
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanConstructorService()
	 */
	protected String devuelveBeanReporteService(){
		return "reportes.reporteCOBReporteDetalladoCobranza36DiasService";
	}

	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the codigoIdiomaISO
	 */
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	/**
	 * @param codigoIdiomaISO the codigoIdiomaISO to set
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}


	
	
	
	
}
