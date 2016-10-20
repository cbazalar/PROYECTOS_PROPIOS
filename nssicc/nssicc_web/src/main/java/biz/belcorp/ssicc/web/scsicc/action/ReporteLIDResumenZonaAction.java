package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteLIDResumenZonaForm;

/**
 * The Class ReporteLIDResumenZonaAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 03/06/2014
 */
@ManagedBean
@SessionScoped
public class ReporteLIDResumenZonaAction extends BaseReporteAbstractAction{
	
	private List siccMarcaList;
	private List siccCanalList;
	private List siccRegionList;
	private List siccZonaList;
	private String formatoReporte;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLIDResumenZonaForm reporteForm = new ReporteLIDResumenZonaForm();
		return reporteForm;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(this.formatoReporte)) {
			return "reporteCOMComisionGRegionObjeXLS";
		} 
		else {
			return "reporteMaestroHorizontal";
		} 
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {		
		return "";
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteLIDResumenZonaAction.setViewAtributes' method");
		}		
		
		ReporteLIDResumenZonaForm f = (ReporteLIDResumenZonaForm)this.formReporte;
		
		this.mostrarReporteXLS = true;
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		setSiccMarcaList(service.getMarcas());
		setSiccCanalList(service.getCanalesByCodigoISO(this.mPantallaPrincipalBean.getCurrentUser().getIdioma().getCodigoISO()));	
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
				
		f.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(),Constants.CODIGO_CANAL_DEFAULT));
		
		this.setSiccRegionList(reporteService.getListaGenerico("getRegionesByPais",(Map)new HashMap().put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo())));
		this.setSiccZonaList(new ArrayList());		
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteLIDResumenZonaAction.prepareParameterMap' method");
		}
		
		ReporteLIDResumenZonaForm f = (ReporteLIDResumenZonaForm)this.formReporte;
		formatoReporte = f.getFormatoExportacion();
		
		String condicionRegion = this.obtieneCondicion(f.getRegionList(), "r.COD_REGI", "'");
		String condicionZona = this.obtieneCondicion(f.getZonaList(), "z.COD_ZONA", "'");
		
		this.formatoReporte = f.getFormatoExportacion();
		
		f.setTitulo(this.getResourceMessage("reporteLIDResumenZonaForm.title"));
		f.setBeforeExecuteReporte(true);
		params.put("titulo", f.getTitulo());
		
		params.put("codigoPais",f.getCodigoPais());
		params.put("codigoMarca",f.getCodigoMarca());
		params.put("codigoPeriodo",f.getCodigoPeriodo());
		params.put("condicionRegion", condicionRegion);
		params.put("condicionZona", condicionZona);
		params.put("titulo", f.getTitulo()+" - Campaa "+f.getCodigoPeriodo());
		
		return params;
	}

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
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
}