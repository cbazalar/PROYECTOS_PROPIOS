package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteVENGeneralConsultoraEgresadaForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteVENGeneralConsultoraEgresadaAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = -7437037073158481782L;
	private String formatoReporte;
	private List siccMarcaList = new ArrayList();
	private List siccCanalList = new ArrayList();
	private String siccPeriodoInicial;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private String periodoActual = null;
	private String codigoMarca = null;
	private String codigoCanal = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteVENGeneralConsultoraEgresadaForm form = new ReporteVENGeneralConsultoraEgresadaForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteVENGeneralConsultoraEgresadaXLS";
		else
			return "reporteMaestroHorizontal";
	}

	/**
	 * @param Muestra
	 *            lista de zonas segun la region escodiga.
	 */

	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		try {
			ReporteVENGeneralConsultoraEgresadaForm form = (ReporteVENGeneralConsultoraEgresadaForm) this.formReporte;
			String regiones = (String) val.getNewValue();
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(aSvc.getZonasByPaisMarcaCanalRegion(
					form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT, regiones));
			form.setCodigoZona(null);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if ("PDF".equals(this.formatoReporte))
			return "reporteVENGeneralConsultoraEgresadaPDF";
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteVENGeneralConsultoraEgresadaForm reporteVENForm = (ReporteVENGeneralConsultoraEgresadaForm) this.formReporte;
		this.formatoReporte = reporteVENForm.getFormatoExportacion();

		String codigoRegion = (String) params.get("codigoRegion");
		String codigoZona = (String) params.get("codigoZona");
		if ("Todos".equals(codigoRegion)) {
			codigoRegion = null;
			params.put("codigoRegion", codigoRegion);
		}
		if ("Todos".equals(codigoZona)) {
			codigoZona = null;
			params.put("codigoZona", codigoZona);
		}
		params.put("condicionUsuario", "NO");
		params.put("NroReporte", " ");
		params.put(
				"titulo",
				getReportResourceMessage("reporteVENFeneralConsultoraEgresada.titulo"));
		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.siccMarcaList = service.getMarcas();
		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());
		this.siccRegionList = aSvc.getRegionesByPaisMarcaCanal(
				pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT);
		this.siccPeriodoInicial = service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		ReporteVENGeneralConsultoraEgresadaForm reporteForm = (ReporteVENGeneralConsultoraEgresadaForm) this.formReporte;
		reporteForm.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));
		reporteForm.setCodigoPais(pais.getCodigo());
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		reporteForm.setCodigoCanal(this.codigoCanal);
		reporteForm.setCodigoMarca(this.codigoMarca);

		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		reporteForm.setCodigoPeriodo(codigoPeriodo);
		
		

		log.debug("Todo Ok: Redireccionando");
	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 *            the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 *            the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList
	 *            the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the siccPeriodoInicial
	 */
	public String getSiccPeriodoInicial() {
		return siccPeriodoInicial;
	}

	/**
	 * @param siccPeriodoInicial
	 *            the siccPeriodoInicial to set
	 */
	public void setSiccPeriodoInicial(String siccPeriodoInicial) {
		this.siccPeriodoInicial = siccPeriodoInicial;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the periodoActual
	 */
	public String getPeriodoActual() {
		return periodoActual;
	}

	/**
	 * @param periodoActual
	 *            the periodoActual to set
	 */
	public void setPeriodoActual(String periodoActual) {
		this.periodoActual = periodoActual;
	}

	/**
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca
	 *            the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal
	 *            the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}
}