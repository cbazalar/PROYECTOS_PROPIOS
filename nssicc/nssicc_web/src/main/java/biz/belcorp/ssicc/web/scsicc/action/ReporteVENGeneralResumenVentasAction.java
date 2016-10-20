package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteVENResumenVentasForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteVENGeneralResumenVentasAction extends BaseReporteAbstractAction  {
    
	private static final long serialVersionUID = 1L;
	private String formatoReporte;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteVENResumenVentasForm form = new ReporteVENResumenVentasForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))	return  "reporteVENRegistroVentasResumenEsquemasXLS";
		
		else return "reporteVENRegistroVentasResumenEsquemasPDF";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteVENResumenVentasForm form = (ReporteVENResumenVentasForm) this.formReporte;
		if (form.getFechaHastaD().compareTo(form.getFechaDesdeD()) < 0) {
			String mensaje = this.getResourceMessage("errors.compare.dates");
			return mensaje;
		}

		return null;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteVENRegistroVentasResumenEsquemasPDF";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteVENResumenVentasForm reporteVENForm = (ReporteVENResumenVentasForm)this.formReporte;
		String fecha1, fecha2;
		fecha1 = DateUtil.getDate(reporteVENForm
				.getFechaDesdeD());
		fecha2 = DateUtil.getDate(reporteVENForm
				.getFechaHastaD());
		reporteVENForm.setFechaDesde(fecha1);
		reporteVENForm.setFechaHasta(fecha2);
		formatoReporte = reporteVENForm.getFormatoExportacion();
		params.put("NroReporte", "");
		params.put("superiorIzquierda", this.getResourceMessage("reporte.maestro.cetco.ruc"));
		params.put("titulo", this.getResourceMessage("reporteVENGeneralResumenVentasForm.titulo"));
		params.put("condicionFechaHora","NO");
		params.put("condicionUsuario","NO");
		params.put("fechaDesde",reporteVENForm.getFechaDesde());
		params.put("fechaHasta",reporteVENForm.getFechaHasta());
		params.put("condicionFechaHora","NO");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		params.put("codigoPaisLbel", pais.getCodigo().substring(0,2)+ Constants.FIN_CODIGO_PAIS_LBEL);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReportePDF = true;
		this.mostrarReporteXLS = true;
		ReporteVENResumenVentasForm form = (ReporteVENResumenVentasForm) this.formReporte;
		form.setFechaDesdeD(new Date(System.currentTimeMillis()));
		form.setFechaHastaD(new Date(System.currentTimeMillis()));		
	}
}
