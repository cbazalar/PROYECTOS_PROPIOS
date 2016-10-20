package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSTOCUVsErradosRecepcionadosForm;

@ManagedBean
@SessionScoped
public class ReporteSTOCUVsErradosRecepcionadosAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8654284204742599392L;
	private String formatoReporte;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSTOCUVsErradosRecepcionadosForm reporteForm = new ReporteSTOCUVsErradosRecepcionadosForm();

		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.info("ReporteSTOCUVsErradosRecepcionadosAction - setViewAttributes");
		}
		this.mostrarReporteXLS = true;
		ReporteSTOCUVsErradosRecepcionadosForm f = (ReporteSTOCUVsErradosRecepcionadosForm) this.formReporte;

		f.setOidIdiomaIso(this.mPantallaPrincipalBean.getCurrentIdioma().getCodigoISO());
		Map criteriaPeriodo = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteriaPeriodo);

		
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());

	}


	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSTOCUVsErradosRecepcionadosForm form = (ReporteSTOCUVsErradosRecepcionadosForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reporteSTOCUVsErradosRecepcionadosXLS";
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSTOCUVsErradosRecepcionadosForm form = (ReporteSTOCUVsErradosRecepcionadosForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(formReporte.getFormatoExportacion())) {
			return "reporteSTOCUVsErradosRecepcionadosPDF";
		} else {
			return "";
		}
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
	
		
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
	
		ReporteSTOCUVsErradosRecepcionadosForm reporteSTOForm = (ReporteSTOCUVsErradosRecepcionadosForm) this.formReporte;
		formatoReporte = reporteSTOForm.getFormatoExportacion();
				
										
		params.put("NroReporte", "");
		params.put("codigoPeriodo", reporteSTOForm.getCodigoPeriodo());	
		params.put("titulo",
				getResourceMessage("reporteSTOCUVsErradosRecepcionadosForm.title"));
		
		log.info("ReporteSTOCUVsErradosRecepcionadosForm prepareParameterMap");
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
