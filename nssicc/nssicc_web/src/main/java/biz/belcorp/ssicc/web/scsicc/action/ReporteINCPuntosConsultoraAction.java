package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteINCPuntosConsultoraForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteINCPuntosConsultoraAction extends BaseReporteAbstractAction{
	
	private static final long serialVersionUID = 5415567056031185504L;
	
	
	private String formatoReporte;	
	private String tipoReporte;
	
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};
	private List siccConcursoList;
	private List incConcursoVigentesCerradosList;

	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCPuntosConsultoraForm reporteForm = new ReporteINCPuntosConsultoraForm();
		return reporteForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarReporteXLS = true;
		this.mostrarReporteOCSV=true;
		
		ReporteINCPuntosConsultoraForm f = (ReporteINCPuntosConsultoraForm) this.formReporte;		
		MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) 
									getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		String codpais = pais.getCodigo();
		f.setCodigoPais(codpais);
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", codpais);
		
		
		this.siccRegionList=reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);
		
		this.incConcursoVigentesCerradosList= service.getListConcursosVigentesPuntos();
		
		
		
		f.setPuntajeMinimo("0");
		
		log.debug("Todo Ok: Redireccionando");
		
	
	}
	
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte)){
			if("1".equals(tipoReporte))
				return "reporteINCPuntosConsultoraXLS";
			else
				return "reporteINCPuntosCampaniaXLS";
		}
		else
			return "reporteMaestroHorizontal";

	}
	
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if (("PDF".equals(formatoReporte)))
			if("1".equals(tipoReporte))
				return "reporteINCPuntosConsultora";
			else
				return "reporteINCPuntosCampania";
		return "";
	}
	
	
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteINCPuntosConsultoraService";
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReporteINCPuntosConsultoraForm f = (ReporteINCPuntosConsultoraForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		formatoReporte = f.getFormatoExportacion();
 		tipoReporte = f.getCodigoCampania();
		
		Map criteria = params;		

		String codigoRegion = (String) params.get("codigoRegion");
		String codigoZona = (String) params.get("codigoZona");
		String condicionZonas = "";
		String condicionRegion = "";
		String condicionConcurso = "";

		if ("Todos".equals(codigoRegion) || StringUtils.isEmpty(codigoRegion)) {
			codigoRegion = null;
			params.put("codigoRegion", codigoRegion);
		} else {
			condicionRegion = obtieneCondicion(f.getCodigoRegion(), "COD_REGI", "'");
		}

		if ("Todos".equals(codigoZona) || StringUtils.isEmpty(codigoZona)) {
			codigoZona = null;
			params.put("codigoZona", codigoZona);
		} else {
			condicionZonas = obtieneCondicion(f.getCodigoZona(),"COD_ZONA", "'");
		}
		
		String condicion = condicionZonas + condicionRegion;
		params.put("condicion", condicion);
		params.put("tipoReporte", f.getCodigoCampania());
		params.put("formatoReporte", f.getFormatoExportacion());

		if("1".equals(tipoReporte))
			params.put("titulo",getReportResourceMessage("reporteINCPuntosConsultoraForm.titulo"));
		else
			params.put("titulo",getReportResourceMessage("reporteINCPuntosConsultoraForm.titulo.campania"));
			
		return params;

	}
	
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		log.debug("val: " + val.getNewValue().toString());
		
		
		ReporteINCPuntosConsultoraForm f = (ReporteINCPuntosConsultoraForm) this.formReporte;
		String[] regiones = (String[]) val.getNewValue();
		if (!val.equals(null) && regiones.length > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(
					f.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,Constants.CODIGO_CANAL_DEFAULT, regiones,
					Constants.FORMATO_TOTAL));			
			f.setCodigoZona(null);
		} else {
			this.siccZonaList = null;
			f.setCodigoZona(null);
		}
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
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

	public List getSiccConcursoList() {
		return siccConcursoList;
	}

	public void setSiccConcursoList(List siccConcursoList) {
		this.siccConcursoList = siccConcursoList;
	}

	public List getIncConcursoVigentesCerradosList() {
		return incConcursoVigentesCerradosList;
	}

	public void setIncConcursoVigentesCerradosList(
			List incConcursoVigentesCerradosList) {
		this.incConcursoVigentesCerradosList = incConcursoVigentesCerradosList;
	}	
    
}
