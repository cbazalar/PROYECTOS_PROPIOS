package biz.belcorp.ssicc.web.scsicc.action;


import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteLIDActividadFinalZonaForm;

/** 
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *         
 */

@ManagedBean
@SessionScoped
public class ReporteLIDActividadFinalZonaAction extends	BaseReporteAbstractAction {
	
	private static final long serialVersionUID = -5136393973960796714L;
	
	private List siccMarcaList;
	private LabelValue[] siccRegionList;
	private LabelValue[] siccZonaList;
	private String formatoReporte;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLIDActividadFinalZonaForm reporteForm = new ReporteLIDActividadFinalZonaForm();
		return reporteForm;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if (("PDF".equals(formatoReporte))||("VPDF".equals(formatoReporte))) 
			return "reporteLIDActividadFinalZonaPDF";
		if ("XLS".equals(formatoReporte))
			return "reporteLIDActividadFinalZonaXLS";
		return "reporteMaestroVertical";		
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {		
		return "";
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteLIDActividadFinalZonaAction.setViewAtributes' method");
		}
		
		log.debug("Seting : Attributes.");
		
		this.mostrarBotonBuscar = true;
		this.mostrarReportePDF = true;
		this.mostrarReporteXLS = true;
		 
		ReporteLIDActividadFinalZonaForm f = (ReporteLIDActividadFinalZonaForm)this.formReporte;
		setSiccMarcaList(((ReporteService) getBean("scsicc.reporteService")).getMarcas());
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		
		//obtine el perido actual de cra_perio
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		f.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(),Constants.CODIGO_CANAL_DEFAULT));
		this.inicializar();
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ProcesoCOMComisionGerenteZonaAction.prepareParameterMap' method");
		}
		
		ReporteLIDActividadFinalZonaForm reporteForm = (ReporteLIDActividadFinalZonaForm) this.formReporte;		
		formatoReporte = reporteForm.getFormatoExportacion();
		
		reporteForm.setTitulo(this.getReportResourceMessage("reporteLIDActividadFinalZonaForm.title"));
		reporteForm.setBeforeExecuteReporte(true);
		params.put("titulo", reporteForm.getTitulo());
		
		String condicionRegion = this.obtieneCondicion(reporteForm.getRegionList(), "zr.COD_REGI", "'");
		String condicionZona = this.obtieneCondicion(reporteForm.getZonaList(), "zz.COD_ZONA", "'");
		
		params.put("codigoPais",reporteForm.getCodigoPais());
		params.put("codigoMarca",reporteForm.getCodigoMarca());
		params.put("codigoPeriodo",reporteForm.getCodigoPeriodo());
		params.put("condicionRegion", condicionRegion);
		params.put("condicionZona", condicionZona);
		params.put("titulo", reporteForm.getTitulo());
		
		return params;
	}
	
	/**
	 * 
	 */
	public void inicializar(){
		log.debug("ingresando al prerender");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");	
		this.siccRegionList = aSvc.getRegionesByPaisMarcaCanal(
				this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(), 
				Constants.CODIGO_MARCA_DEFAULT, 
				Constants.CODIGO_CANAL_DEFAULT);
//		String[] valor = {"-1"};
//		this.siccZonaList = aSvc.getZonasMultipleByPaisMarcaCanalRegion(
//				this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(),
//				Constants.CODIGO_MARCA_DEFAULT,
//				Constants.CODIGO_CANAL_DEFAULT,
//				valor,
//				Constants.CODIGO_MARCA_DEFAULT);
		
	}
	
	/**
	 * Obtiene combo de Regiones
	 * @param val
	 */
	public void loadRegiones(ValueChangeEvent val){
		String valor = val.getNewValue().toString();
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		
		this.siccRegionList = aSvc.getRegionesByPaisMarcaCanal(
				this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(), 
				valor, 
				Constants.CODIGO_CANAL_DEFAULT);
		String[] valor1 = {"-1"};
		this.siccZonaList = aSvc.getZonasMultipleByPaisMarcaCanalRegion(
				this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(),
				Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT,
				valor1,
				Constants.CODIGO_MARCA_DEFAULT);
	}
	
	
	/**
	 * Obtiene combo de Zonas
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val){
		
		AjaxService aSvc = (AjaxService) getBean("ajaxService");			
		
		this.siccZonaList = aSvc.getZonasMultipleByPaisMarcaCanalRegion(
				this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(),
				Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT,
				(String[])val.getNewValue(),
				Constants.FORMATEAR_TODOS);
	}

	/**
	 * @return
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}	
	
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}
}