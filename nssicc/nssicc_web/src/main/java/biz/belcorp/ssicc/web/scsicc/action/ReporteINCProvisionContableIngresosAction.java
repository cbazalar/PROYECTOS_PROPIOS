package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteINCProvisionContableIngresosForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteINCProvisionContableIngresosAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5455167127897787213L;
	private String formatoReporte;
	private String tipoReporte;

	private List incConcursoTipoProgramaPuntosList;
	
	private String codigoPais;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCProvisionContableIngresosForm reporteForm = new ReporteINCProvisionContableIngresosForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		this.mostrarReporteOCSV = true;

		ReporteINCProvisionContableIngresosForm f = (ReporteINCProvisionContableIngresosForm) this.formReporte;
		MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");

		codigoPais = this.mPantallaPrincipalBean.getCurrentCountry().getCodigo();
		f.setCodigoPais(codigoPais);

		Map<String,String> criteriaOperacion = new HashMap<String,String>();
		criteriaOperacion.put("codigoPais", codigoPais);


		this.incConcursoTipoProgramaPuntosList = service
				.getListConcursosTipoProgramaPuntos();
		

		log.debug("Todo Ok: Redireccionando");

	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte)) 
				return "reporteINCProvisionContableIngresosXLS";
		else
			return "reporteMaestroHorizontal";

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

    @Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteINCProvisionContableIngresosService";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteINCProvisionContableIngresosForm f = (ReporteINCProvisionContableIngresosForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		formatoReporte = f.getFormatoExportacion();
		
		String oidConcurso = reporteService.getOidConcursoByNumConc(f.getNumeroConcurso());

		Map criteria = params;
		params.put("formatoReporte", formatoReporte);
		params.put("numeroConcurso", oidConcurso);
		params.put("fechaInicio", DateUtil.convertDateToString(DateUtil.getDatePattern(), f.getFechaInicioD()));
		params.put("fechaFin", DateUtil.convertDateToString(DateUtil.getDatePattern(), f.getFechaFinD()));

		return params;

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

	public List getIncConcursoTipoProgramaPuntosList() {
		return incConcursoTipoProgramaPuntosList;
	}

	public void setIncConcursoTipoProgramaPuntosList(
			List incConcursoTipoProgramaPuntosList) {
		this.incConcursoTipoProgramaPuntosList = incConcursoTipoProgramaPuntosList;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
}

