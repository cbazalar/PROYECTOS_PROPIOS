package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteMAEVinculosClienteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteMAEVinculosClienteAction extends BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5724391320283587517L;
	private String formatoReporte;
	private List siccRegionList;
	private List siccTipoVinculoList;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteMAEVinculosClienteForm reporteForm = new ReporteMAEVinculosClienteForm();

		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteMAEVinculosClienteAction - setViewAtributes");
		}

		this.mostrarReporteOCSV = true;
		this.mostrarReportePDF = false;
		Map criteriaPeriodo = new HashMap();

		ReporteMAEVinculosClienteForm reporteMAEVinculosClienteActionForm = (ReporteMAEVinculosClienteForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteriaPeriodo);

		/*
		 * String codigoPeriodo = controlFacturacion.getCodigoPeriodo();
		 * reporteMAEVinculosClienteActionForm
		 * .setCodigoPeriodoInicial(codigoPeriodo);
		 * reporteMAEVinculosClienteActionForm
		 * .setCodigoPeriodoFinal(codigoPeriodo);
		 */

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaPeriodo);
		this.siccTipoVinculoList = reporteService.getTiposVinculosByPais(pais
				.getCodigo());

		log.debug("Todo OK: Redireccionando");

	}

	protected String devuelveBeanReporteService() {
		return "reportes.reporteMAEVinculosClienteService";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		ReporteMAEVinculosClienteForm reporteSICForm = (ReporteMAEVinculosClienteForm) this.formReporte;
		formatoReporte = reporteSICForm.getFormatoExportacion();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		String condicionRegion = obtieneCondicion(reporteSICForm.getRegionList(), "zorg.cod_regi", "'");
		String condicionTipoVinculo = obtieneCondicion(reporteSICForm.getTipoVinculoList(), "tivc.cod_tipo_vinc", "'");
				
		params.put("codigoPeriodoInicial", reporteSICForm.getCodigoPeriodoInicial());
		params.put("codigoPeriodoFinal", reporteSICForm.getCodigoPeriodoFinal());
		params.put("condicionRegion", condicionRegion);
		params.put("condicionTipoVinculo", condicionTipoVinculo);
				
		params.put("NroReporte", "");
		params.put("formatoReporte", formatoReporte);
		params.put("titulo", getResourceMessage("reporteMAEConsejerasBloqueadasDesbloqueadasForm.title"));
	
		
		log.info("Salio a ReporteMAEVinculosClienteAction - prepareParameterMap");
		return params;
	}

	public String setValidarReporte() {
		ReporteMAEVinculosClienteForm form = (ReporteMAEVinculosClienteForm) this.formReporte;
		int codperini = Integer.parseInt(form.getCodigoPeriodoInicial());
		int codperfin = Integer.parseInt(form.getCodigoPeriodoFinal());
		if(codperfin<codperini){
			String mensaje =  this.getResourceMessage("reporteMAEVinculosClienteForm.msg.esMayorcodigoPeriodoInicial");
			return mensaje;
		}

	    					
	    return null;
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

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccTipoVinculoList
	 */
	public List getSiccTipoVinculoList() {
		return siccTipoVinculoList;
	}

	/**
	 * @param siccTipoVinculoList the siccTipoVinculoList to set
	 */
	public void setSiccTipoVinculoList(List siccTipoVinculoList) {
		this.siccTipoVinculoList = siccTipoVinculoList;
	}

	

}
