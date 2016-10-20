package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
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
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteLETMetasLideresCampanaForm;

@ManagedBean
@SessionScoped
public class ReporteLETMetasLideresCampanaAction extends
		BaseReporteAbstractAction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7608284311304163580L;
	private String formatoReporte;
	private List siccMarcaList;
	private List siccCanalList;
	private List siccRegionList;

	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteXLS = true;
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		ReporteLETMetasLideresCampanaForm f = (ReporteLETMetasLideresCampanaForm) this.formReporte;

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());

		// ################### LISTA MARCA ################
		this.siccMarcaList = interfazSiCCService.getMarcas();

		// ################### LISTA CANAL ################
		this.siccCanalList = interfazSiCCService.getCanalesByCodigoISO(usuario
				.getIdioma().getCodigoISO());

		// ################### LISTA REGIONES ################
		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaOperacion);

		f.setCodigoPais(pais.getCodigo());
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);

		log.debug("Todo Ok: Redireccionando");

	}

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLETMetasLideresCampanaForm form = new ReporteLETMetasLideresCampanaForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteLETMetasLideresCampanaForm form = (ReporteLETMetasLideresCampanaForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion())) {
			return "reporteLETMetasLideresCampanaXLS";
		} else
			return "reporteMaestroVertical";

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if ("PDF".equals(formReporte.getFormatoExportacion())) {
			return "reporteLETMetasLideresCampanaPDF";
		} else
			return "";

	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		log.debug("ReporteLETMetasLideresCampanaAction ::::: prepareParameterMap");

		ReporteLETMetasLideresCampanaForm f = (ReporteLETMetasLideresCampanaForm) this.formReporte;

		formatoReporte = f.getFormatoExportacion();

		params.put("codigoPais", f.getCodigoPais());
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("codigoRegion", f.getCodigoRegion());
		params.put("codigoMarca", f.getCodigoMarca());
		params.put("codigoCanal", f.getCodigoCanal());

		String codigoRegion = (String) params.get("codigoRegion");
		String condicionRegion = "";

		if (!"".equals(codigoRegion)) {
			condicionRegion = " AND a.COD_REGI = '" + f.getCodigoRegion() + "'";
		}

		params.put("condicionRegion", condicionRegion);
		params.put("NroReporte", "");
		params.put("titulo",
				getResourceMessage("reporteLETMetasLideresCampanaForm.titulo"));

		return params;
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
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

}
