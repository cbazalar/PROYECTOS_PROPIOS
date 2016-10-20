package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBSaldosCampanaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCOBSaldosCampanaAction extends BaseReporteAbstractAction
		implements Serializable {

	private static final long serialVersionUID = -688440487591520990L;
	private String formatoReporte;
	private String tipoTotal;
	private List siccMarcaList = new ArrayList();
	private List siccCanalList = new ArrayList();
	private String codigoIdiomaISO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS =  true;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());

		this.siccMarcaList = reporteService.getMarcas();
		this.siccCanalList = reporteService.getCanalesByCodigoISO(usuario
				.getIdioma().getCodigoISO());

		this.codigoIdiomaISO = this.mPantallaPrincipalBean.getCurrentIdioma()
				.getCodigoISO();
		
		ReporteCOBSaldosCampanaForm form1 = (ReporteCOBSaldosCampanaForm) this.formReporte;
		form1.setCodigoPais(pais.getCodigo());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		form1.setCodigoPeriodoInicial(periodo);
		form1.setCodigoPeriodoFinal(periodo);
		form1.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		form1.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		form1.setTipoTotal(Constants.TIPO_TOTAL_PERIODO);		
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCOBSaldosCampanaForm reporteCOBForm = (ReporteCOBSaldosCampanaForm) this.formReporte;
		this.formatoReporte = reporteCOBForm.getFormatoExportacion();
		this.tipoTotal = reporteCOBForm.getTipoTotal();

		// String oidPais
		// =reporteService.getOidString("getOidPaisByCodigoPais",criteria);
		String codigoPais = reporteCOBForm.getCodigoPais();
		String periodoDesde = reporteCOBForm.getCodigoPeriodoInicial();
		String periodoHasta = reporteCOBForm.getCodigoPeriodoFinal();

		String codigoCanal = reporteCOBForm.getCodigoCanal();
		String codigoMarca = reporteCOBForm.getCodigoMarca();

		params.put("codigoPais", codigoPais);
		params.put("codigoPeriodoInicial", periodoDesde);
		params.put("codigoPeriodoFinal", periodoHasta);
		params.put("codigoCanal", codigoCanal);
		params.put("codigoMarca", codigoMarca);

		params.put("NroReporte", "");
		params.put("titulo",
				getReportResourceMessage("reporteCOBSaldosCampanaForm.titulo"));
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBSaldosCampanaForm form = new ReporteCOBSaldosCampanaForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte)) {
			if (Constants.TIPO_TOTAL_PERIODO.equals(tipoTotal))
				return "reporteCOBSaldoCampanaPXLS";
			else if (Constants.TIPO_TOTAL_REGION.equals(tipoTotal))
				return "reporteCOBSaldosCampanaRegionXLS";
			return "reporteCOBSaldosCampanaZonaXLS";
		} else
			return "reporteMaestroVertical";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if (Constants.TIPO_TOTAL_PERIODO.equals(tipoTotal))
			return "reporteCOBSaldosCampanaPeriodoPDF";
		else if (Constants.TIPO_TOTAL_REGION.equals(tipoTotal))
			return "reporteCOBSaldosCampanaRegionPDF";
		return "reporteCOBSaldosCampanaZonaPDF";
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
	 * @return the tipoTotal
	 */
	public String getTipoTotal() {
		return tipoTotal;
	}

	/**
	 * @param tipoTotal
	 *            the tipoTotal to set
	 */
	public void setTipoTotal(String tipoTotal) {
		this.tipoTotal = tipoTotal;
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
	 * @return the codigoIdiomaISO
	 */
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	/**
	 * @param codigoIdiomaISO
	 *            the codigoIdiomaISO to set
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}
}