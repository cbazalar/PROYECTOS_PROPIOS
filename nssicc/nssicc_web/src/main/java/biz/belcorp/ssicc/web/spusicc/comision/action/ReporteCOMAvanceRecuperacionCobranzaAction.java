package biz.belcorp.ssicc.web.spusicc.comision.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.comision.form.ReporteCOMAvanceRecuperacionCobranzaForm;

@SuppressWarnings({ "rawtypes", "unchecked" })
@ManagedBean
@SessionScoped
public class ReporteCOMAvanceRecuperacionCobranzaAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = -4187406052448095594L;

	private String[] listaTotal;
	private List siccRegionList;
	private LabelValue[] siccZonaList;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOMAvanceRecuperacionCobranzaForm r = new ReporteCOMAvanceRecuperacionCobranzaForm();
		return r;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		String reporte="reporteCOMAvanceRecupCobRegionXLS";
		int numero = this.getNroReporteProcesando();
		
		if (this.listaTotal != null) {
			String filtro = this.listaTotal[numero - 1];
			if (this.listaTotal[numero - 1].indexOf("reporteDir") == 0) {
				reporte = "reporteCOMAvanceRecupCobDirXLS";
			} else if (this.listaTotal[numero - 1].indexOf("codigoRegion__") < 0) {
				reporte = "reporteCOMAvanceRecupCobZonaXLS";
			} else {
				reporte = "reporteCOMAvanceRecupCobRegionXLS";
			}
		}
		return reporte;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		String reporte = "";
		return reporte;

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteCOMAvanceRecuperacionCobranzaForm reporteForm = (ReporteCOMAvanceRecuperacionCobranzaForm) this.formReporte;

		String regionList[] = reporteForm.getCodigoRegion();
		String zonaList[] = reporteForm.getCodigoZona();

		params.put("codigoPeriodo", reporteForm.getCodigoPeriodo());
		params.put("codigoPeriodoRecup", reporteForm.getCodigoPeriodoRecup());

		params.put(
				"titulo",
				getResourceMessage("reporteCOMAvanceRecuperacionCobranzaForm.titulo"));

		params.put("condicionZonaCorreo", " ");
		params.put("condicionRegionCorreo", " ");
		params.put("condicion", " ");

		if (!this.isVisualizarReporte()) {
			if (this.listaTotal[this.getNroReporteProcesando() - 1]
					.indexOf("reporteDir") == 0) {
				params.put("codigoDir", "codigoDir");
			} else if (this.listaTotal[this.getNroReporteProcesando() - 1]
					.indexOf("codigoRegion__") < 0) {
				params.put("codigoDir", null);
				params.put("codigoRegion", null);
				params.put("codigoZona",
						this.listaTotal[this.getNroReporteProcesando() - 1]);
				params.put(
						"condicionZonaCorreo",
						" AND nvl(lide.zonaBono,lide.zonaRecaudo)='"
								+ this.listaTotal[this
										.getNroReporteProcesando() - 1] + "' ");
			} else {
				params.put("codigoDir", null);
				params.put("codigoRegion", this.listaTotal[this
						.getNroReporteProcesando() - 1].substring(14));
				params.put(
						"condicionRegionCorreo",
						" AND NVL(lide.regionBono,lide.regionRecaudo) ='"
								+ this.listaTotal[this
										.getNroReporteProcesando() - 1]
										.substring(14) + "' ");
			}
		} else {
			String condicionRegion = this.obtieneCondicion(regionList,
					"NVL(lide.regionBono,lide.regionRecaudo)", "'");
			String condicionZonas = this.obtieneCondicion(zonaList,
					"nvl(lide.zonaBono,lide.zonaRecaudo)", "'");
			String condicion = condicionRegion + condicionZonas;
			params.put("condicion", condicion);
		}

		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteXLS = true;
		this.mostrarReporteMailXLS = true;
		this.mostrarReportePDF = false;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteCOMAvanceRecuperacionCobranzaForm f = (ReporteCOMAvanceRecuperacionCobranzaForm) this.formReporte;
		f.setCodigoPeriodo("");
		f.setCodigoPeriodoRecup("");
		f.setCodigoRegion(null);
		f.setCodigoZona(null);
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());

		this.siccRegionList = reporteService.getListaGenerico("getRegionesPEJ",
				criteria);
		this.siccZonaList = new LabelValue[] {};
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#getNroReportesAGenerar()
	 */
	@Override
	protected int getNroReportesAGenerar() {

		ReporteCOMAvanceRecuperacionCobranzaForm f = (ReporteCOMAvanceRecuperacionCobranzaForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		AjaxService ajaxService = (AjaxService) this.getBean("ajaxService");
		List lista = new ArrayList();

		String codigoRegion = f.getCodigoRegion()[0];
		String codigoZona = f.getCodigoZona()[0];

		if (StringUtils.equals(codigoZona, "Todos")
				|| StringUtils.isBlank(codigoZona)) {
			String codigoRegionAux = codigoRegion;
			if (StringUtils.equals(codigoRegion, "Todos")
					|| StringUtils.isBlank(codigoRegion)) {
				codigoRegionAux = "";

				LabelValue[] result = ajaxService.getZonasRegionPEJTodos(
						codigoRegionAux, "");
				if (result != null) {
					for (int i = 0; i < result.length; i++) {
						LabelValue zonas = result[i];
						lista.add(zonas.getValue());
					}
				}
			} else {
				for (int j = 0; j < f.getCodigoRegion().length; j++) {
					codigoRegionAux = f.getCodigoRegion()[j];

					LabelValue[] result = ajaxService.getZonasRegionPEJTodos(
							codigoRegionAux, "");
					if (result != null) {
						for (int i = 0; i < result.length; i++) {
							LabelValue zonas = result[i];
							lista.add(zonas.getValue());
						}
					}
				}
			}
		} else {
			for (int j = 0; j < f.getCodigoZona().length; j++) {
				lista.add(f.getCodigoZona()[j]);
			}
		}

		if (StringUtils.equals(codigoRegion, "Todos")
				|| StringUtils.isBlank(codigoRegion)) {
			List listaRegiones = reporteService.getListaGenerico(
					"getRegionesPEJ", null);

			for (int i = 0; i < listaRegiones.size(); i++) {
				Base base = (Base) listaRegiones.get(i);
				lista.add("codigoRegion__" + base.getCodigo());
			}
		} else {

			for (int j = 0; j < f.getCodigoRegion().length; j++) {
				lista.add("codigoRegion__" + f.getCodigoRegion()[j]);
			}

		}

		lista.add("reporteDir");

		int tamanno = lista.size();
		this.listaTotal = new String[tamanno];
		for (int i = 0; i < tamanno; i++) {
			this.listaTotal[i] = (String) lista.get(i);
		}
		return this.listaTotal.length;

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#getValorFiltroGrabarReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams)
	 */
	@Override
	protected String getValorFiltroGrabarReporte(ReporteParams reporteParams) {

		String filtro = new String();
		if (this.listaTotal[this.getNroReporteProcesando() - 1]
				.indexOf("reporteDir") == 0) {
			filtro = "Direccion: ";
			return filtro;
		} else if (this.listaTotal[this.getNroReporteProcesando() - 1]
				.indexOf("codigoRegion__") < 0) {
			filtro = "Zona: ";
			return filtro + this.listaTotal[this.getNroReporteProcesando() - 1];
		} else {
			filtro = "Region: ";
			return filtro
					+ this.listaTotal[this.getNroReporteProcesando() - 1]
							.substring(14);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#getNombreArchivoReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams)
	 */
	@Override
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {

		String nombreArchivoReporte;

		if (this.listaTotal[this.getNroReporteProcesando() - 1]
				.indexOf("reporteDir") == 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
			nombreArchivoReporte = this.getPrefijoArchivo() + "_" + "DIRECCION"
					+ "_" + sdf.format(new Date(System.currentTimeMillis()));
		} else if (this.listaTotal[this.getNroReporteProcesando() - 1]
				.indexOf("codigoRegion__") < 0) {
			String codigoZona = this.listaTotal[this.getNroReporteProcesando() - 1];
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
			nombreArchivoReporte = this.getPrefijoArchivo() + "_" + codigoZona
					+ "_" + sdf.format(new Date(System.currentTimeMillis()));
		} else {
			String codigoRegion = this.listaTotal[this
					.getNroReporteProcesando() - 1].substring(14);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
			nombreArchivoReporte = this.getPrefijoArchivo() + "_"
					+ codigoRegion + "_"
					+ sdf.format(new Date(System.currentTimeMillis()));
		}
		return nombreArchivoReporte;

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanMailService()
	 */
	@Override
	protected String devuelveBeanMailService() {
		return "com.mailReporteCOMAvanceRecuperacionCobranza";
	}

	/**
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

			String[] valor = (String[]) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			if (valor != null && valor.length > 0)
				this.siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(
						pais.getCodigo(), "T", "VD", valor, "");
			else
				this.siccZonaList = null;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @return
	 */
	public String[] getListaTotal() {
		return listaTotal;
	}

	/**
	 * @param listaTotal
	 */
	public void setListaTotal(String[] listaTotal) {
		this.listaTotal = listaTotal;
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