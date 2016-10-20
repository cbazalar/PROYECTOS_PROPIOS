package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECRecepcionCDRForm;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano
 *         Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteRECRecepcionCDRAction extends BaseReporteAbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -315750563493857602L;
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
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECRecepcionCDRForm reporteForm = new ReporteRECRecepcionCDRForm();
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

		ReporteRECRecepcionCDRForm reporteForm = (ReporteRECRecepcionCDRForm) this.formReporte;
		formatoReporte = reporteForm.getFormatoExportacion();

		if ("XLS".equals(this.formatoReporte)) {
			return "reporteRECRecepcionCDRXLS";
		}
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()ReporteRECMercaderiaSiniestradaAction
	 */

	@Override
	protected void setViewAtributes() throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReporteRECRecepcionCDRForm.setViewAtributes' method");
		}
		//
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {
		Map criteria = new HashMap();
		ReporteService reporteService = (ReporteService) this
				.getBean("scsicc.reporteService");
		ReporteRECRecepcionCDRForm f = (ReporteRECRecepcionCDRForm) this.formReporte;
		this.formatoReporte = f.getFormatoExportacion();
		String condicionUsuario = new String("");
		String condicionPeriodoCDR = new String("");
		String condicionPeriodoPedido = new String("");
		String condicionFechaIngreso = new String("");

		if (StringUtils.isNotBlank(f.getCodigoUsuario())) {
			condicionUsuario = " AND rcc.USU_INGR_RECL ='"
					+ f.getCodigoUsuario() + "' ";
		}
		if (StringUtils.isNotBlank(f.getCodigoCampanhaCDR())) {
			criteria.put("codigoPeriodo", f.getCodigoCampanhaCDR());
			int oidPeriodoCDR = reporteService.getOidPeriodo(criteria);
			condicionPeriodoCDR = " AND rcc.oid_peri_recl =" + oidPeriodoCDR;
		}
		if (StringUtils.isNotBlank(f.getCodigoCampanhaPedido())) {
			criteria.put("codigoPeriodo", f.getCodigoCampanhaPedido());
			int oidPeriodo = reporteService.getOidPeriodo(criteria);
			condicionPeriodoPedido = " AND rcc.oid_peri_docu_Refe ="
					+ oidPeriodo;
		}
		if (StringUtils.isNotBlank(f.getFechaIngreso())) {
			condicionFechaIngreso = " AND trunc(rcc.FEC_INGR_RECL) = TO_DATE('"
					+ f.getFechaIngreso() + "', 'dd/mm/yyyy') ";
		}
		params.put("condicionUsuarioCDR", condicionUsuario);
		params.put("condicionPeriodoCDR", condicionPeriodoCDR);
		params.put("condicionPeriodoPedido", condicionPeriodoPedido);
		params.put("condicionFechaIngreso", condicionFechaIngreso);

		return params;

	}

}