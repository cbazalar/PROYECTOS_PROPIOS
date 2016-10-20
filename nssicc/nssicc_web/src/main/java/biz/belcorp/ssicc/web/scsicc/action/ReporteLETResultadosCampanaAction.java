package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteLETResultadosCampanaForm;

@ManagedBean
@SessionScoped
public class ReporteLETResultadosCampanaAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7488209189096828974L;
	private String formatoReporte;


	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLETResultadosCampanaForm reporteForm = new ReporteLETResultadosCampanaForm();
		return reporteForm;
	}



	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteLETResultadosCampanaAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");

		ReporteLETResultadosCampanaForm f = (ReporteLETResultadosCampanaForm) this.formReporte;
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", "0");
		criteria.put("indicadorActiva", "1");
		List lista = service.getCampanhasActivasByCriteria(criteria);
		if (lista.size() == 1) {
			f.setCodigoPeriodo((String) lista.get(0));
		}

		log.debug("Todo Ok: Redireccionando");

	}
	


	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteLETResultadosCampanaXLS";
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
		ReporteLETResultadosCampanaForm reef = (ReporteLETResultadosCampanaForm) this.formReporte;
		formatoReporte = reef.getFormatoExportacion();
		
		params.put("codigoPais",reef.getCodigoPais());
		params.put("codigoPeriodo",reef.getCodigoPeriodo());
		params.put("NroReporte", "");
		params.put("titulo", "");

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
