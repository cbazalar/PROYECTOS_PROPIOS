package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBDetalladoRecuperacionIncobrableForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteCOBDetalladoRecuperacionIncobrableAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 5094155516559014785L;
	private String formatoReporte;

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		ReporteCOBDetalladoRecuperacionIncobrableForm form = (ReporteCOBDetalladoRecuperacionIncobrableForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		form.getIdioma().setCodigoISO(
				mPantallaPrincipalBean.getCurrentIdioma().getCodigoISO());

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
		if ("XLS".equals(formatoReporte)) {
			return "reporteCOBDetalladoRecuperacionIncobrableXLS";
		} else
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
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteCOBDetalladoRecuperacionIncobrableForm form = (ReporteCOBDetalladoRecuperacionIncobrableForm) this.formReporte;
		String vFechaInicio = DateUtil.getDate(form.getFechaDesdeD());
		String vFechaFin = DateUtil.getDate(form.getFechaHastaD());
		if (!vFechaFin.isEmpty() && !vFechaFin.isEmpty()) {
			if (vFechaInicio.compareToIgnoreCase(vFechaFin) > 0) {
				return "'Fecha Desde' debe ser menor o igual a 'Fecha Hasta'";
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
		ReporteCOBDetalladoRecuperacionIncobrableForm form = new ReporteCOBDetalladoRecuperacionIncobrableForm();
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

		ReporteCOBDetalladoRecuperacionIncobrableForm f = (ReporteCOBDetalladoRecuperacionIncobrableForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();
		Map criteria = params;
		params.put("tipoReporte", f.getTipoReporte());
		
		if(f.getFechaDesdeD() != null){
			f.setFechaDesde(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, f.getFechaDesdeD()));
		}
		
		if(f.getFechaHastaD() != null){
			f.setFechaHasta(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, f.getFechaHastaD()));
		}
		
		params.put("fechaDesde", f.getFechaDesde());
		params.put("fechaHasta", f.getFechaHasta());
		
		params.put("NroReporte", "");
		return params;
	}
}