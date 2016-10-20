package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOMComisionAsignacionesGzForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteCOMComisionAsignacionesGzAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = -3547459184583142444L;
	private String formatoReporte;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOMComisionAsignacionesGzForm form = new ReporteCOMComisionAsignacionesGzForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteCOMComisionAsignacionesGzXLS";
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteCOMComisionAsignacionesGzPDF";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOMComisionAsignacionesGzAction.prepareParameterMap' method");
		}
		
		ReporteCOMComisionAsignacionesGzForm form = (ReporteCOMComisionAsignacionesGzForm) this.formReporte;
		form.setTitulo(this.getReportResourceMessage("reporteCOMComisionAsignacionesGzForm.titulo"));

		params.put("codigoPais",form.getCodigoPais());
		params.put("codigoPeriodo",form.getCodigoPeriodo());
		params.put("NroReporte", "");
		params.put("titulo", this.getReportResourceMessage("reporteCOMComisionAsignacionesGzForm.titulo"));
		
		formatoReporte = this.formReporte.getFormatoExportacion();
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOMComisionAsignacionesGzAction.setViewAtributes' method");
		}
		
		this.mostrarReporteXLS = true;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		ReporteCOMComisionAsignacionesGzForm f = (ReporteCOMComisionAsignacionesGzForm) this.formReporte;
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", "0");
		criteria.put("indicadorActiva", "1");
		
		List lista = service.getCampanhasActivasByCriteria(criteria);
		if (lista.size() == 1) {
			f.setCodigoPeriodo((String) lista.get(0));
		}
		
		f.setCodigoPais(pais.getCodigo());
		f.setDescPais(pais.getDescripcion());
		
		formatoReporte = this.formReporte.getFormatoExportacion();
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}
}