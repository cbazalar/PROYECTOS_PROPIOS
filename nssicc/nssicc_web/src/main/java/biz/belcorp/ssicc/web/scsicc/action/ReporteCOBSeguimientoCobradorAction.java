package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBSeguimientoGestionCobradorForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCOBSeguimientoCobradorAction extends BaseReporteAbstractAction
		implements Serializable {

	private static final long serialVersionUID = 5094155516559014785L;
	private String formatoReporte;


	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = true;
		ReporteCOBSeguimientoGestionCobradorForm form = (ReporteCOBSeguimientoGestionCobradorForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		form.getIdioma().setCodigoISO(this.mPantallaPrincipalBean.getCurrentIdioma().getCodigoISO());
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
		if ("XLS".equals(this.formatoReporte)){
			return  "reporteCOBSeguimientoGestionCobradorXLS";
		}else
			return "reporteMaestroVertical";
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
		return "reporteCOBSeguimientoGestionCobradorPDF";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteCOBSeguimientoGestionCobradorForm form = (ReporteCOBSeguimientoGestionCobradorForm) this.formReporte;
		String vFechaInicio = DateUtil.getDate(form.getFechaDesdeD());
		String vFechaFin = DateUtil.getDate(form.getFechaHastaD());
		if (!vFechaFin.isEmpty() && !vFechaFin.isEmpty()) {
			if (vFechaInicio.compareTo(vFechaFin) > 0) {
				return "La fecha de Inicio no puede ser Mayor a la Fecha Fin";
			}
		}
		return null;
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
		ReporteCOBSeguimientoGestionCobradorForm form = new ReporteCOBSeguimientoGestionCobradorForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCOBSeguimientoGestionCobradorForm f = (ReporteCOBSeguimientoGestionCobradorForm) this.formReporte;
		this.formatoReporte = f.getFormatoExportacion();
		f.setFechaDesde(DateUtil.getDate(f.getFechaDesdeD()));
		f.setFechaHasta(DateUtil.getDate(f.getFechaHastaD()));
		params.put("codigoPais", f.getCodigoPais());
		params.put("fechaDesde", f.getFechaDesde());
		params.put("fechaHasta", f.getFechaHasta());
		params.put("NroReporte", "");
		params.put("titulo", getReportResourceMessage("reporteCOBSeguimientoGestionCobradorForm.titulo"));
		return params;
	}	
}