package biz.belcorp.ssicc.web.scsicc.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePEDSimulacionFaltantesForm;


@ManagedBean
@SessionScoped
public class ReportePEDSimulacionFaltantesAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = -3312413157758748286L;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePEDSimulacionFaltantesForm reporteForm = new ReportePEDSimulacionFaltantesForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {			
		if (StringUtils.equals(this.formatoExportacion, "XLS"))	
			return "reportePEDSimulacionFaltantesXLS";
		else 
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		String reporte="";		
		if (StringUtils.equals(this.formatoExportacion, "PDF"))
			reporte= "reportePEDSimulacionFaltantesPDF";
		return reporte;
	}

	@Override
	protected String devuelveBeanMailService(){
		return "ped.mailReportePEDSimulacionFaltantesService";
	}
	
	

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {	
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReportePEDSimulacionFaltantesForm f = (ReportePEDSimulacionFaltantesForm) this.formReporte;
		f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionDate()));
		//this.formatoReporte = f.getFormatoExportacion();
		params.put("NroReporte", "");		
		params.put("titulo", this.getReportResourceMessage("reportePEDSimulacionFaltantes.titulo"));
		
		if(StringUtils.isNotBlank(f.getPorcentajeMaximoFaltante())){	
			params.put("porcentajeMaximoFaltante", new BigDecimal(f.getPorcentajeMaximoFaltante()));
			Double valorPorcentaje = Double.parseDouble(f.getPorcentajeMaximoFaltante());
			if(valorPorcentaje > 0) 
				params.put("condicionFaltantes", " AND POR_FALT_PROY > " + f.getPorcentajeMaximoFaltante());
			else 
				params.put("condicionFaltantes", " AND POR_FALT_PROY > 0 ");
		} else
			params.put("porcentajeMaximoFaltante", null);
		
		params.put("pedidosAcumulados", reporteService.getPedidosAcumulados(f.getCodigoPeriodo()));
		
		BigDecimal numeroPedidosProyectar = new BigDecimal(f.getNumeroPedidosProyectar());
		BigDecimal promedioMontoPedido = new BigDecimal(f.getPromedioMontoPedido());
		BigDecimal valorProyectado = numeroPedidosProyectar.multiply(promedioMontoPedido);
		params.put("valorProyectado", valorProyectado);				
		params.put("promedioMontoPedido", new BigDecimal(f.getPromedioMontoPedido()));
		params.put("fechaFacturacion", f.getFechaFacturacion());
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteMailPDF = true;
		this.mostrarReporteMailXLS = true;
		this.mostrarReportePDF=false;
		
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		ReportePEDSimulacionFaltantesForm f = (ReportePEDSimulacionFaltantesForm) this.formReporte;	
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);

		// Carga el periodo actual
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setFechaFacturacion(controlFacturacion.getFechaProceso());
		f.setCodigoPais(pais.getCodigo());
		f.setFechaFacturacionDate(DateUtil.convertStringToDate(f.getFechaFacturacion()));
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		if(reporteService.esPrimerDiaFacturacion(f.getCodigoPeriodo()))
			f.setTipoPup("DC");
		else
			f.setTipoPup("DA");
		
	}
	
	@Override
	public String setValidarReporte(){
		ReportePEDSimulacionFaltantesForm f = (ReportePEDSimulacionFaltantesForm) this.formReporte;	
		int nroPedidos=Integer.parseInt(f.getNumeroPedidosProyectar());
		float promedio=Float.parseFloat(f.getPromedioMontoPedido());
		if(nroPedidos==0)
			return this.getResourceMessage("reportePEDSimulacionFaltantesForm.msg.numeroPedidosProyectar");
		if(promedio==0)
			return this.getResourceMessage("reportePEDSimulacionFaltantesForm.msg.promedioMontoPedido");
    	
    	
		return "";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#getIconPersonalExcel()
	 */
	@Override
	public String getIconPersonalExcel() {
		return "/resources/images/excelForm.png";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#getIconPersonalPdf()
	 */
	@Override
	public String getIconPersonalPdf() {
		return "/resources/images/pdfForm2.png";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		// TODO Auto-generated method stub
		return "reportes.reportePEDSimulacionFaltantesService";
	}
	
	
}