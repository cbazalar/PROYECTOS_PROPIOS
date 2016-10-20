package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECIndFactVentasZonaForm;

@ManagedBean
@SessionScoped
public class ReporteRECIndFactVentasZonaAction extends BaseReporteAbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECIndFactVentasZonaForm form = new ReporteRECIndFactVentasZonaForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(this.getFormatoExportacion()))
			return "reporteRECIndFactVentasZonaXLS";
		else
			return " ";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteRECIndFactVentasZonaForm f = (ReporteRECIndFactVentasZonaForm) this.formReporte;
		// params.put("codigoPeriodo",f.getCodigoPeriodo());
		params.put("CodigoPeriodoInicio", f.getCodigoPeriodoInicio());
		params.put("CodigoPeriodoFin", f.getCodigoPeriodoFin());
		params.put("titulo", this.mPantallaPrincipalBean.getResourceMessage("reporteRECIndFactVentasZonaForm.title"));

		return params;
	}
	
	public String setValidarReporte() {
		ReporteRECIndFactVentasZonaForm form = (ReporteRECIndFactVentasZonaForm) this.formReporte;
		Integer fecha1, fecha2;
		fecha1 = Integer.parseInt(form.getCodigoPeriodoInicio());
		fecha2 = Integer.parseInt(form.getCodigoPeriodoFin());
		if (fecha1 > fecha2) {
			String mensaje = this
					.getResourceMessage("reporteRECIndFactVentasZonaForm.errorInicioMayor");
			return mensaje;
		}
		// siccRegionList = new ArrayList<String>();
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		

	}

}
