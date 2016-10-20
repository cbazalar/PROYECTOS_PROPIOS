package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCCargosDirectosForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes" })
public class ReporteCCCCargosDirectosAction extends BaseReporteAbstractAction
		implements Serializable {

	private List tipoReporteList = new ArrayList();
	private String tipoReporte;
	private String formatoReporte;
	private List ccTipoCargosDirectosList;

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
	public List getCcTipoCargosDirectosList() {
		return ccTipoCargosDirectosList;
	}

	/**
	 * @param ccTipoCargosDirectosList
	 */
	public void setCcTipoCargosDirectosList(List ccTipoCargosDirectosList) {
		this.ccTipoCargosDirectosList = ccTipoCargosDirectosList;
	}

	/**
	 * @return
	 */
	public List getTipoReporteList() {
		return tipoReporteList;
	}

	/**
	 * @param tipoReporteList
	 */
	public void setTipoReporteList(List tipoReporteList) {
		this.tipoReporteList = tipoReporteList;
	}

	private static final long serialVersionUID = 5452137025798023586L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCCargosDirectosForm reporteForm = new ReporteCCCCargosDirectosForm();
		return reporteForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {

		return "reporteCCCCargosDirectos" + this.tipoReporte + "XLS";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()ReporteRECMercaderiaSiniestradaAction
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		// reportes
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		// Servicios
		ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");
		// Formulario
		ReporteCCCCargosDirectosForm f = (ReporteCCCCargosDirectosForm) this.formReporte;
		// defecto
		f.reset();
		f.setPais(mPantallaPrincipalBean.getCurrentCountry());
		this.ccTipoCargosDirectosList = serviceCCC.getTipoCargosDirectos();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCCCCargosDirectosForm reporteCCCCargosDirectosForm = (ReporteCCCCargosDirectosForm) this.formReporte;

		reporteCCCCargosDirectosForm.setFechaDesde(DateUtil
				.getDate(reporteCCCCargosDirectosForm.getFechaDesdeD()));
		reporteCCCCargosDirectosForm.setFechaHasta(DateUtil
				.getDate(reporteCCCCargosDirectosForm.getFechaHastaD()));
		this.tipoReporte = reporteCCCCargosDirectosForm.getTipoReporte();
		
		
		params.put("fechaDesde", reporteCCCCargosDirectosForm.getFechaDesde());
		params.put("fechaHasta", reporteCCCCargosDirectosForm.getFechaHasta());
		if (log.isDebugEnabled()) {
			log.debug(params);
		}

		return params;

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteCCCCargosDirectosForm r = (ReporteCCCCargosDirectosForm) this.formReporte;
		if (r.getFechaDesdeD().compareTo(r.getFechaHastaD()) > 0)
			return getResourceMessage("reporteRETDetalleComisionVentaRetailForm.validar.fechas");

		return null;
	}

}