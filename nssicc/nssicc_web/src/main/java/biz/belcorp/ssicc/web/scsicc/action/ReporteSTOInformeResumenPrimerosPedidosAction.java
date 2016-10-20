package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteSTOInformeResumenPrimerosPedidosForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteSTOInformeResumenPrimerosPedidosAction extends
		BaseReporteAbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5200301129774692978L;

	private String formatoReporte;
	private Date fechaini;
	private Date fechafin;

	public Date getFechaini() {
		return fechaini;
	}

	public void setFechaini(Date fechaini) {
		this.fechaini = fechaini;
	}

	public Date getFechafin() {
		return fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSTOInformeResumenPrimerosPedidosForm reporteForm = new ReporteSTOInformeResumenPrimerosPedidosForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(this.formatoReporte)) {
			return "reporteSTOInformeResumenPrimerosPedidosXLS";
		} else {
			return "reporteMaestroHorizontal";
		}
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteSTOInformeResumenPrimerosPedidosForm f = (ReporteSTOInformeResumenPrimerosPedidosForm) this.formReporte;
		this.formatoReporte = f.getFormatoExportacion();

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		params.put("codigoPais", f.getCodigoPais());
		params.put("periodo", f.getCodigoPeriodo());
		params.put("fechaDesde", DateUtil.convertDateToString("dd/MM/yyyy", f.getFechaDesdeD()));
		params.put("fechaHasta", DateUtil.convertDateToString("dd/MM/yyyy", f.getFechaHastaD()));
		params.put("oidPeriodo", reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodo", params));
		params.put("titulo", this.getResourceMessage("reporteSTOProductividadForm.titulo"));

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		Map criteria = new HashMap();
		criteria.put("codigoPais", this.mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());
		criteria.put("codigoUsuario", this.mPantallaPrincipalBean
				.getCurrentUser().getLogin());

		ReporteSTOInformeResumenPrimerosPedidosForm f = (ReporteSTOInformeResumenPrimerosPedidosForm) this.formReporte;

		/* SAQUE DEL FORM.java */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));

		// CAMBIAR POR INICIO y FIN DE PERIODO SELECCIONADO
		// sdf = new SimpleDateFormat("dd/MM/yyyy");
		/*
		 * f.setFechaDesdeD(DateUtil.convertStringToDate(f.getFechaDesde())) ;
		 * f.setFechaHastaD(DateUtil.convertStringToDate(f.getFechaHasta()));
		 */

		f.setCodigoPeriodo(mPantallaPrincipalBean.getCodigoPeriodoActual());

		if (StringUtils.isEmpty(f.getCodigoPeriodo())) {
			f.setCodigoPeriodo(periodo);
		}
		/* FIN SAQUE DEL FORM.java */

		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		String fechaDesde = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(
				this.getmPantallaPrincipalBean().getCurrentCountry()
						.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, f.getCodigoPeriodo());

		String fechaHasta = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(
				this.getmPantallaPrincipalBean().getCurrentCountry()
						.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, f.getCodigoPeriodo());

		f.setFechaDesdeD(DateUtil.convertStringToDate(fechaDesde));
		f.setFechaHastaD(DateUtil.convertStringToDate(fechaHasta));

		this.fechaini = DateUtil.convertStringToDate(fechaDesde);
		this.fechafin = DateUtil.convertStringToDate(fechaHasta);
	}

	public void loadFechasPeriodo(String valor) {
		if (log.isDebugEnabled()) {
			log.debug("loadFechasPeriodo");
		}
		ReporteSTOInformeResumenPrimerosPedidosForm reporteForm = (ReporteSTOInformeResumenPrimerosPedidosForm) this.formReporte;
		try {
			if (StringUtils.isNotBlank(valor) && valor.length() == 6) {
				AjaxService ajaxService = (AjaxService) getBean("ajaxService");
				String fechaDesde = ajaxService
						.getFechaInicioPeriodoByPaisMarcaCanal(this
								.getmPantallaPrincipalBean()
								.getCurrentCountry().getCodigo(),
								Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, valor);
				String fechaHasta = ajaxService
						.getFechaFinalPeriodoByPaisMarcaCanal(this
								.getmPantallaPrincipalBean()
								.getCurrentCountry().getCodigo(),
								Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, valor);
				
				reporteForm.setFechaDesdeD(DateUtil.convertStringToDate(fechaDesde));
				reporteForm.setFechaHastaD(DateUtil.convertStringToDate(fechaHasta));

				this.fechaini = DateUtil.convertStringToDate(fechaDesde);
				this.fechafin = DateUtil.convertStringToDate(fechaHasta);
			}else
			{
				reporteForm.setFechaDesde(null);
				reporteForm.setFechaDesdeD(null);
				reporteForm.setFechaHasta(null);
				reporteForm.setFechaHastaD(null);
			}
		} catch (Exception e) {

		} 
	}
	
	public String setValidarReporte() {
		ReporteSTOInformeResumenPrimerosPedidosForm form = (ReporteSTOInformeResumenPrimerosPedidosForm) this.formReporte;
		String fechaIni =DateUtil.convertDateToString(fechaini);
		String fechaFin =DateUtil.convertDateToString(fechafin);
		
		if(form.getFechaDesdeD() != null && form.getFechaHastaD() != null)
		{
			if (fechaini.compareTo(form.getFechaDesdeD()) ==1 || fechafin.compareTo(form.getFechaDesdeD())<0) {
				String mensaje = this.getResourceMessage("errors.compare.campaigns.periodo.fechaInicio")+fechaIni+" - "+fechaFin;
				return mensaje;
			} else if (fechafin.compareTo(form.getFechaHastaD())<0 || fechaini.compareTo(form.getFechaHastaD()) ==1) {
				String mensaje = this.getResourceMessage("errors.compare.campaigns.periodo.fechaFin")+fechaIni+" - "+fechaFin;
				return mensaje;
			}			
		}		
		return null;
	}
	
	@Override
	protected String devuelveBeanReporteService(){
		return "reportes.ReporteSTOInformeResumenPrimerosPedidosService";
	}
}
