package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePERControlAsistenciaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReportePERControlAsistenciaAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = -5334392440004421688L;
	private String formatoReporte;
	private List siccMarcaList = new ArrayList();
	private List siccCanalList = new ArrayList();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReportePERControlAsistenciaForm f = (ReportePERControlAsistenciaForm) this.formReporte;
		this.formatoReporte = f.getFormatoExportacion();

		String seleccion = StringUtils.left(f.getSeleccion(), 1);
		params.put("seleccion", seleccion);
		params.put("NroReporte", " ");
		params.put(
				"titulo",
				getReportResourceMessage("reportePERControlAsistenciaForm.titulo"));
		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePERControlAsistenciaForm form = new ReportePERControlAsistenciaForm();
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
		return "reporteMaestroVerticalControlAsistencia";
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
		if (("PDF".equals(this.formatoReporte)))
			return "reportePERControlAsistencia";
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveBeanReporteService()
	 */
	protected String devuelveBeanReporteService() {
		return "reportes.reportePERControlAsistenciaService";
	}

	@Override
	protected void setViewAtributes() throws Exception {
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		this.siccMarcaList = service.getMarcas();
		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());

		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		ReportePERControlAsistenciaForm f = (ReportePERControlAsistenciaForm) this.formReporte;
		f.setCodigoPeriodo(codigoPeriodo);
		f.setCodigoPais(pais.getCodigo());
		log.debug("Todo Ok: Redireccionando");

		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
	}

	public LabelValue[] loadRegionMarcaCanal() {
		LabelValue[] regiones = null;
		try {
			ReportePERControlAsistenciaForm form = (ReportePERControlAsistenciaForm) this.formReporte;
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			regiones = aSvc
					.getRegionesByPaisMarcaCanal(this.mPantallaPrincipalBean
							.getCurrentCountry().getCodigo(), form
							.getCodigoMarca(), form.getCodigoCanal());
			return regiones;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return null;
		}

	}

	public LabelValue[] loadZonaRegion() {
		try {
			LabelValue[] zonas = null;
			ReportePERControlAsistenciaForm form = (ReportePERControlAsistenciaForm) this.formReporte;
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			zonas = aSvc.getZonasByPaisCanalRegion(this.mPantallaPrincipalBean
					.getCurrentCountry().getCodigo(), form.getCodigoCanal(),
					form.getCodigoRegion());
			return zonas;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return null;
		}

	}

	public String setValidarReporte() {
		ReportePERControlAsistenciaForm form = (ReportePERControlAsistenciaForm) this.formReporte;
		boolean existeRegion = false;
		boolean existeZona = false;

		String codigoRegionForm = form.getCodigoRegion();
		String codigoZonaForm = form.getCodigoZona();

		LabelValue[] regiones = null;
		LabelValue[] zonas = null;

		regiones = loadRegionMarcaCanal();
		for (int i = 0; i < regiones.length; i++) {
			if (regiones[i].getValue().compareTo(codigoRegionForm) == 0) {
				existeRegion = true;
			}
		}
		if (!existeRegion) {
			return "Ingrese una región válida para el Canal y Marca Elegidos";
		} else {

			zonas = loadZonaRegion();
			if (codigoZonaForm.isEmpty()) {
				return null;
			}
			for (int i = 0; i < zonas.length; i++) {
				if (zonas[i].getValue().compareTo(codigoZonaForm) == 0) {
					existeZona = true;
				}
			}
			if (!existeZona) {
				return "Ingrese una zona válida para la región elegida";
			} else {
				return null;
			}
		}
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
}