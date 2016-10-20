package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCUPNuevasCuponesForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteCUPNuevasCuponesAction extends BaseReporteAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8555985321387533486L;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception 
	{
		ReporteCUPNuevasCuponesForm reporteForm = new ReporteCUPNuevasCuponesForm();
		return reporteForm;	
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoExportacion))
			return "reporteCUPNuevasCuponesXLS";		
		return "";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception 
	{
		ReporteCUPNuevasCuponesForm f = (ReporteCUPNuevasCuponesForm) this.formReporte;
		this.formatoExportacion = f.getFormatoExportacion();
		
		params.put("codigoPeriodoInicial", f.getCodigoPeriodoInicial());
		params.put("codigoPeriodoFinal", f.getCodigoPeriodoFinal());
		params.put("NroReporte", "");
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarReporteXLS = true;
		this.mostrarReporteOCSV = true;
		this.mostrarReportePDF = false;
		
		ReporteCUPNuevasCuponesForm f = (ReporteCUPNuevasCuponesForm) this.formReporte;
		Map criteriaPeriodo = new HashMap();
		String codigoPais = mPantallaPrincipalBean.getCurrentCountry().getCodigo();
		
		criteriaPeriodo.put("codigoPais", codigoPais);
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);
		
		String codigoPeriodo = controlFacturacion.getCodigoPeriodo();
		f.setCodigoPeriodoInicial(codigoPeriodo);
		f.setCodigoPeriodoFinal(codigoPeriodo);		
	}
	
	@Override
	protected String devuelveBeanReporteService(){
		return "reportes.reporteCUPNuevasCuponesService";
	}
	
	public String setValidarReporte() 
	{
		ReporteCUPNuevasCuponesForm form = (ReporteCUPNuevasCuponesForm) this.formReporte;
		int fecha1D = Integer.parseInt(form.getCodigoPeriodoInicial());
		int fecha2D = Integer.parseInt(form.getCodigoPeriodoFinal());
		if (fecha1D > fecha2D) {
			String mensaje = this.getResourceMessage("reporteCUPNuevasCuponesForm.msg.esMayorcodigoPeriodoInicial");
			return mensaje;
		}
		return null;
		
	}
}
