package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePEDExportarDemandaAnticipadaForm;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano
 *         Huamán</a>
 * 
 */

@ManagedBean
@SessionScoped
public class ReportePEDExportarDemandaAnticipadaAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePEDExportarDemandaAnticipadaForm form = new ReportePEDExportarDemandaAnticipadaForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reportePEDExportarDemandaAnticipadaXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		ReportePEDExportarDemandaAnticipadaForm f = (ReportePEDExportarDemandaAnticipadaForm) formReporte;
		mostrarReportePDF=false;
		mostrarReporteXLS=true;
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);

		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) this
				.getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFact
				.getControlFacturacionById(criteriaPeriodo);

		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());

	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map<String, Object> params, BaseForm form) throws Exception {

		ReportePEDExportarDemandaAnticipadaForm f = (ReportePEDExportarDemandaAnticipadaForm) formReporte;
		params.put("fecha", DateUtil.getDate(f.getFechaD()));
		params=super.prepareParamsBeforeExecute(params, form);
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		reporteService.executeReportePEDExportarDemandaAnticipada(params);
		return params;
	}

}
