package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSTORechazadas2Form;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteSTORechazadas2Action extends BaseReporteAbstractAction
		implements Serializable {

	private String formatoReporte;
	private List siccRegionList;

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
	 * @return
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	private static final long serialVersionUID = 5452137025798023586L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSTORechazadas2Form reporteForm = new ReporteSTORechazadas2Form();
		return reporteForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteSTORechazadas2XLS";

		else
			return "reporteMaestroHorizontal";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteSTORechazadas2XLS";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()ReporteRECMercaderiaSiniestradaAction
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		log.debug("Executing action : setViewAttributes.");
		ReporteSTORechazadas2Form f = (ReporteSTORechazadas2Form) this.formReporte;

		Map criteria = new HashMap();
		criteria.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		criteria.put("codigoUsuario", mPantallaPrincipalBean.getCurrentUser().getLogin());

		// Carga Periodo
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																		// Campanha
																		// Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																			// Campanha
																			// activa
																			// q
																			// se
																			// procesa
																			// actualmente

		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) this
				.getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFact
				.getControlFacturacionById(criteriaPeriodo);

		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.siccRegionList=reporteService.getListaGenerico("getRegionesByPais", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteSTORechazadas2Form f = (ReporteSTORechazadas2Form) this.formReporte;

		this.formatoReporte = f.getFormatoExportacion();
		String codigoPais = this.mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo();

		if (f.getRegionList() != null) {
			if (f.getRegionList().length == 1) {
				if (f.getRegionList()[0] == null
						|| f.getRegionList()[0].compareToIgnoreCase("") == 0) {
					f.setRegionList(null);
				}
			}
		}
		params.put("codigoPais", codigoPais);
		params.put("codigoPeriodo", f.getCodigoPeriodo());

		String condicionRegion = this.obtieneCondicion(f.getRegionList(),
				"E.COD_REGI", "'");
		params.put("condicion", condicionRegion);

		params.put("NroReporte", " ");
		params.put("titulo",
				getResourceMessage("reporteSTORechazadasForm.title"));
		return params;
	}
}