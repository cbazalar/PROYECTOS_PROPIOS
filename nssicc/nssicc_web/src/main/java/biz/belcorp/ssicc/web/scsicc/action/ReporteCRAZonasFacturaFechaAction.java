package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCRAZonasFacturaFechaForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRCapturaPedidoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteCRAZonasFacturaFechaAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7548751822163890703L;
	private String formatoReporte;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCRAZonasFacturaFechaForm reporteForm = new ReporteCRAZonasFacturaFechaForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteCRAZonasFacturaFechaAction - setViewAtributes");
		}
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		MantenimientoOCRCapturaPedidoService service1 = (MantenimientoOCRCapturaPedidoService) getBean("spusicc.pedidos.mantenimientoOCRCapturaPedidoService");
		ReporteCRAZonasFacturaFechaForm f = (ReporteCRAZonasFacturaFechaForm) this.formReporte;
		Map criteria;
		criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", "0");
		criteria.put("indicadorActiva", "1");
		List lista = service.getCampanhasActivasByCriteria(criteria);
		if (lista.size() == 1) {
			criteria = null;
			criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoPeriodo", String.valueOf(lista.get(0)));
			String fecha = service1.getFechaFacturacion(criteria);
			f.setFechaFacturacionD(DateUtil.convertStringToDate(fecha));
		}

		log.debug("Todo Ok: Redireccionando");

	}

	protected String devuelveBeanReporteService() {
		return "reportes.reporteCRAZonasFacturaFechaService";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteCRAZonasFacturaFechaXLS";
		else
			return null;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {

		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		ReporteCRAZonasFacturaFechaForm f = (ReporteCRAZonasFacturaFechaForm) this.formReporte;

		this.formatoReporte = f.getFormatoExportacion();

		String fechaFactura = DateUtil.convertDateToString(f
				.getFechaFacturacionD());
		// params.put("titulo", this.getMessageReporte("", request));
		params.put("fechaFacturacion", fechaFactura);

		return params;
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

}
