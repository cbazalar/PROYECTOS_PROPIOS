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
import biz.belcorp.ssicc.web.scsicc.form.ReportePROLPedidosFacturadosForm;

@ManagedBean
@SessionScoped
public class ReportePROLPedidosFacturadosAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3266298876443387845L;

	private String formatoReporte;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePROLPedidosFacturadosForm reporteForm = new ReportePROLPedidosFacturadosForm();
		return reporteForm;
	}



	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReportePROLPedidosFacturadosAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		ReportePROLPedidosFacturadosForm f = (ReportePROLPedidosFacturadosForm) this.formReporte;

		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente 
	
    	MantenimientoOCRPedidoControlFacturacionService service =(MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);

		f.setCampanhia(controlFacturacion.getCodigoPeriodo());

	}
	
	

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReportePROLPedidosFacturadosForm form = (ReportePROLPedidosFacturadosForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reportePROLPedidosFacturadosXLS";
		else
			return " ";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return " ";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		ReportePROLPedidosFacturadosForm reportePROLForm = (ReportePROLPedidosFacturadosForm) this.formReporte;
		formatoReporte = reportePROLForm.getFormatoExportacion();

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String campanhia =  reportePROLForm.getCampanhia();
		
		params.put("codigoPais", pais.getCodigo());
		params.put("campanhia", campanhia);
		
		log.debug("Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
		
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
