package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCCCDetalladoConsultorasIncobrableForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes"})
public class ReporteCCCDetalladoConsultorasIncobrableAction extends BaseReporteAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1602036502143701881L;
	
	private List siccRegionList;
	private LabelValue[] siccZonaList;
	private LabelValue[] siccSeccionList;
	private String formatoReporte;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCDetalladoConsultorasIncobrableForm formReporte = new ReporteCCCDetalladoConsultorasIncobrableForm();
		return formReporte;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteCCCDetalladoConsultorasIncobrableCVS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception 
	{
		ReporteCCCDetalladoConsultorasIncobrableForm reporteForm = (ReporteCCCDetalladoConsultorasIncobrableForm) this.formReporte;
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		reporteForm.setFechaHasta(DateUtil.convertDateToString(reporteForm.getFechaHastaDate()));
		reporteForm.setCampanaHasta(DateUtil.convertDateToString(reporteForm.getCampanaHastaDate()));
		
		this.formatoReporte = reporteForm.getFormatoExportacion();
		
//		this.setVirtualizador(true);
		this.setGenerateTabsXLS(true);
	
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarReportePDF = false;
		this.mostrarReporteOCSV = true;
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteCCCDetalladoConsultorasIncobrableForm f = (ReporteCCCDetalladoConsultorasIncobrableForm) this.formReporte;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(new Date(System.currentTimeMillis()));
		
		f.setCampanaHasta(fecha);
		f.setFechaHasta(fecha);
		f.setCampanaHastaDate(DateUtil.convertStringToDate(f.getCampanaHasta()));
		f.setFechaHastaDate(DateUtil.convertStringToDate(f.getFechaHasta()));
		
		Map criteriaOperacion = new HashMap();		
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);
		LabelValue aux = new LabelValue();
		aux.setLabel("Todos");
		aux.setValue("");
		this.siccZonaList = new LabelValue[]{aux};
		this.siccSeccionList = new LabelValue[]{aux};
	}
	
	@Override
	protected String devuelveBeanReporteService() {
		// TODO Auto-generated method stub
		return "reportes.reporteCCCDetalladoConsultorasIncobrableService";
	}
	
	public void loadZonas(ValueChangeEvent event)
	{
		String[] valor = (String[])event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		if(valor != null && valor.length >0)
			this.siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), "T", "VD", valor,"T");
		else
		{
			LabelValue aux = new LabelValue();
			aux.setLabel("Todos");
			aux.setValue("");
			this.siccZonaList = new LabelValue[]{aux};
			this.siccSeccionList = new LabelValue[]{aux};
		}
	}
	
	public void loadSeccion(ValueChangeEvent event)
	{
		String[] valor = (String[])event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteCCCDetalladoConsultorasIncobrableForm f = (ReporteCCCDetalladoConsultorasIncobrableForm) this.formReporte;
		
		if(valor != null && valor.length >0)
			this.siccSeccionList = ajax.getSeccionMultipleByPaisMarcaCanalRegionZona(pais.getCodigo(), "T", "VD", f.getRegionList(), valor,"T");
		else
		{
			LabelValue aux = new LabelValue();
			aux.setLabel("Todos");
			aux.setValue("");
			this.siccSeccionList = new LabelValue[]{aux};
		}
	}
	
	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

}
