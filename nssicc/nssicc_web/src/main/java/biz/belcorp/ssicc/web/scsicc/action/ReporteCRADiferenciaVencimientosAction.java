package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCRADiferenciaVencimientosForm;

@ManagedBean
@SessionScoped
public class ReporteCRADiferenciaVencimientosAction extends
		BaseReporteAbstractAction {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3042392520343965394L;
	private String formatoReporte;


	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCRADiferenciaVencimientosForm reporteForm = new ReporteCRADiferenciaVencimientosForm();
		return reporteForm;
	}



	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteCRADiferenciaVencimientosAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteCRADiferenciaVencimientosForm f = (ReporteCRADiferenciaVencimientosForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		
		String periodoActual = reporteService.getStringGenerico("getPeriodoByFechaActual", criteriaOperacion);
		f.setCodigoPeriodo(periodoActual);
		
		log.debug("Todo Ok: Redireccionando");


	}
	


	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteCRADiferenciaVencimientosXLS";
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if ("PDF".equals(formatoReporte))
			return "reporteCRADiferenciaVencimientos";
		else
			return " ";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		MantenimientoMAEClienteService service = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		ReporteCRADiferenciaVencimientosForm reporteCRAForm = (ReporteCRADiferenciaVencimientosForm) this.formReporte;
		formatoReporte = reporteCRAForm.getFormatoExportacion();

		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoMarca", reporteCRAForm.getCodigoMarca());
		criteria.put("codigoCanal", reporteCRAForm.getCodigoCanal());
		criteria.put("codigoPeriodo", reporteCRAForm.getCodigoPeriodo());

		Base basePeriodoSiguiente = service.getSiguientePeriodo(criteria);
		
		criteria.put("codigoPeriodo", basePeriodoSiguiente.getDescripcion());
		Base basePeriodoActual = service.getPeriodoAnterior(criteria);
		
		Base basePeriodoSiguiente2 = service.getSiguientePeriodo(criteria);
		
		criteria.put("codigoPeriodo", basePeriodoSiguiente2.getDescripcion());
		Base basePeriodoSiguiente3 = service.getSiguientePeriodo(criteria);
		
		params.put("oidPeriodoActual", basePeriodoActual.getCodigo());
		params.put("oidPeriodoSiguiente", basePeriodoSiguiente.getCodigo());
		params.put("oidPeriodoSiguiente2", basePeriodoSiguiente2.getCodigo());
		params.put("oidPeriodoSiguiente3", basePeriodoSiguiente3.getCodigo());
		
		params.put("codigoPeriodoActual", basePeriodoActual.getDescripcion());
		params.put("codigoPeriodoSiguiente", basePeriodoSiguiente.getDescripcion());
		params.put("codigoPeriodoSiguiente2", basePeriodoSiguiente2.getDescripcion());
		params.put("codigoPeriodoSiguiente3", basePeriodoSiguiente3.getDescripcion());
		
		params.put("NroReporte", " ");
		params.put("titulo",getResourceMessage("reporteCRADiferenciaVencimientosForm.titulo"));
	
		log.info("Salio a ReporteCRADiferenciaVencimientosAction - prepareParameterMap");
		return params;
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

}
