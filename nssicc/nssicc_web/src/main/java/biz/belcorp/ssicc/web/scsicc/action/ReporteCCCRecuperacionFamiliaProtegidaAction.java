package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCCCRecuperacionFamiliaProtegidaForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCCCRecuperacionFamiliaProtegidaAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = -1091798983329193025L;

	private String formatoReporte;
	private String tipoReporte;

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

	/**
	 * @return
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCRecuperacionFamiliaProtegidaForm reporteForm = new ReporteCCCRecuperacionFamiliaProtegidaForm();
		return reporteForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		this.formatoReporte = ((ReporteCCCRecuperacionFamiliaProtegidaForm) this.formReporte)
				.getFormatoExportacion();
		return "reporteCCCRecuperacionFamiliaProtegida" + this.tipoReporte
				+ "XLS";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCCCRecuperacionFamiliaProtegidaForm.prepareParameterMap' method");
		}
		// servicios
		ReporteCCCRecuperacionFamiliaProtegidaForm reporteCCCRecuperacionFamiliaProtegidaForm = (ReporteCCCRecuperacionFamiliaProtegidaForm) this.formReporte;
		
		reporteCCCRecuperacionFamiliaProtegidaForm.setFechaDesde(DateUtil.convertDateToString(reporteCCCRecuperacionFamiliaProtegidaForm.getFechaDesdeD()));
		reporteCCCRecuperacionFamiliaProtegidaForm.setFechaHasta(DateUtil.convertDateToString(reporteCCCRecuperacionFamiliaProtegidaForm.getFechaHastaD()));
		// asignacion
		this.tipoReporte = reporteCCCRecuperacionFamiliaProtegidaForm.getTipoReporte();
		params.put("codigoPais",mPantallaPrincipalBean.getCurrentCountry().getCodigo() );
		params.put("tipoReporte", tipoReporte);
		params.put("codigoCampanaInicial",reporteCCCRecuperacionFamiliaProtegidaForm.getCodigoCampanaInicial());
		params.put("codigoCampanaFinal",reporteCCCRecuperacionFamiliaProtegidaForm.getCodigoCampanaFinal());
		params.put("fechaDesde", reporteCCCRecuperacionFamiliaProtegidaForm.getFechaDesde() );
		params.put("fechaHasta", reporteCCCRecuperacionFamiliaProtegidaForm.getFechaHasta());	
		
		if (log.isDebugEnabled()) {
			log.debug(params);
		}
	
		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReporteCOMComisionPagoEjecutivasAction.setViewAtributes' method");
		}
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		// servicios

		// beans principales
		ReporteCCCRecuperacionFamiliaProtegidaForm reporteCCCRecuperacionFamiliaProtegidaForm = (ReporteCCCRecuperacionFamiliaProtegidaForm) this.formReporte;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		reporteCCCRecuperacionFamiliaProtegidaForm.setCodigoPais(pais.getCodigo());
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		log.debug("Todo Ok: Redireccionando");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteCCCRecuperacionFamiliaProtegidaForm reporteRETForm = (ReporteCCCRecuperacionFamiliaProtegidaForm) this.formReporte;
		String vFechaDesde = DateUtil.getDate(reporteRETForm.getFechaDesdeD());
		String vFechaHasta = DateUtil.getDate(reporteRETForm.getFechaHastaD());
		if (!vFechaDesde.isEmpty() || !vFechaHasta.isEmpty()) {

			if (vFechaDesde.isEmpty() || vFechaHasta.isEmpty()) {
				return "Llene ambas fechas";
			} else {
				if (reporteRETForm.getFechaDesdeD().compareTo(
						reporteRETForm.getFechaHastaD()) > 0)
					return "La fecha de inicio no puede ser mayor a la Fecha final";
				if (reporteRETForm.getCodigoCampanaInicial().compareTo(
						reporteRETForm.getCodigoCampanaFinal()) > 0)
					return "La campaña inicial no puede ser mayor a la campaña final";
			}
		} else {
			return null;
		}
		return null;
	}
}