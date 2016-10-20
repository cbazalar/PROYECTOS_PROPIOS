package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSGRFechasCoberturaForm;

@ManagedBean
@SessionScoped
public class ReporteSGRFechasCoberturaAction  extends BaseReporteAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3911688403359746946L;
	private String formatoReporte;
	private List siccRegionList;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		// TODO Auto-generated method stub
		ReporteSGRFechasCoberturaForm reporteForm=new ReporteSGRFechasCoberturaForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		// TODO Auto-generated method stub
		if ("XLS".equals(formatoReporte))
			return "reporteSGRFechasCoberturaXLS";
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		// TODO Auto-generated method stub
		ReporteSGRFechasCoberturaForm reporteForm= (ReporteSGRFechasCoberturaForm) this.formReporte;
		formatoReporte = reporteForm.getFormatoExportacion();
					
		params.put("codigoPais", reporteForm.getCodigoPais());
		params.put("codigoPeriodoInicio", reporteForm.getCodigoPeriodoInicio());
		params.put("codigoPeriodoFin", reporteForm.getCodigoPeriodoFin());
		params.put("codigoRegion", reporteForm.getCodigoRegion());
		params.put("NroReporte", "");
		params.put("titulo", getResourceMessage("reporteSGRFechasCoberturaForm.title"));		
		return params;

	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		this.mostrarReporteXLS=true;
		this.mostrarReportePDF=false;
		
		ReporteService reporteService=(ReporteService) getBean("scsicc.reporteService");
		ReporteSGRFechasCoberturaForm form=(ReporteSGRFechasCoberturaForm) this.formReporte;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());	
		siccRegionList=reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);
		form.getIdioma().setCodigoISO(usuario.getIdioma().getCodigoISO());
		
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", form.getCodigoPais());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);
		

		form.setCodigoPeriodoInicio(controlFacturacion.getCodigoPeriodo());
		form.setCodigoPeriodoFin(controlFacturacion.getCodigoPeriodo());
		
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}
	
	

}
