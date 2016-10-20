package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePEDResultadoChequeoGerenteZonaForm;

@ManagedBean
@SessionScoped
public class ReportePEDResultadoChequeoGerenteZonaAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1249782872048445046L;
	private String formatoReporte;
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePEDResultadoChequeoGerenteZonaForm reporteForm = new ReportePEDResultadoChequeoGerenteZonaForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReportePEDResultadoChequeoGerenteZonaAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ReportePEDResultadoChequeoGerenteZonaForm f = (ReportePEDResultadoChequeoGerenteZonaForm) this.formReporte;
		f.setCodigoPais(pais.getCodigo());
		f.setOidIdiomaIso(usuario.getIdioma().getCodigoISO());

		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoPeriodo(codigoPeriodo);
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaOperacion);

		log.debug("Todo OK: Redireccionando");

	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReportePEDResultadoChequeoGerenteZonaForm form = (ReportePEDResultadoChequeoGerenteZonaForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reportePEDGerenteZonaXLS";
		else
			return "reporteMaestroVertical";

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReportePEDResultadoChequeoGerenteZonaForm form = (ReportePEDResultadoChequeoGerenteZonaForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(formReporte.getFormatoExportacion()))
			return "";
		else
			return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		ReportePEDResultadoChequeoGerenteZonaForm reportePEDForm = (ReportePEDResultadoChequeoGerenteZonaForm) this.formReporte;
		formatoReporte = reportePEDForm.getFormatoExportacion();

		params.put("NroReporte", "");
		params.put(
				"titulo",
				getResourceMessage("reportePEDResultadoChequeoGerenteZonaForm.titulo"));

		log.info("Salio a ReportePEDResultadoChequeoGerenteZonaAction - prepareParameterMap");
		return params;
	}

	/**
	 * Metodo para obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}

		if (!val.equals(null)) {
			String valor = (String) val.getNewValue();
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			this.siccZonaList = ajaxService.getZonasByPaisMarcaCanalRegion(this
					.getmPantallaPrincipalBean().getCurrentCountry()
					.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT, valor);

		} else {
			setSiccZonaList(null);
		}

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

	/**
	 * @return
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

}
