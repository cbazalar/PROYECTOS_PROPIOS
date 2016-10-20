package biz.belcorp.ssicc.web.scsicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.zon.model.UnidadGeografica;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteZONMovimientoTerritorioCampanaForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteZONMovimientoTerritorioCampanaAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4061861735071677319L;
	private String formatoReporte;
	private List siccMarcaList;
	private List siccCanalList;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteZONMovimientoTerritorioCampanaForm reporteForm = new ReporteZONMovimientoTerritorioCampanaForm();

		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteZONMovimientoTerritorioCampanaAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		this.mostrarReporteOCSV = true;

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		// Cargamos los combos a utilizar
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		ReporteZONMovimientoTerritorioCampanaForm reporteForm = (ReporteZONMovimientoTerritorioCampanaForm) this.formReporte;

		this.siccMarcaList = svc.getMarcas();
		this.siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());

		reporteForm.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		reporteForm.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);

		log.debug("Todo OK: Redireccionando");

	}

	protected String devuelveBeanReporteService() {
		return "reportes.reporteZONMovimientoTerritorioCampanaService";
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteZONMovimientoTerritorioCampanaXLS";
		else
			return "reporteMaestroHorizontal";
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
		ReporteZONMovimientoTerritorioCampanaForm reporteForm = (ReporteZONMovimientoTerritorioCampanaForm) this.formReporte;
		formatoReporte = reporteForm.getFormatoExportacion();

		ProcesoZONActualizarUnidadesGeograficasService service = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		List listEstructurasGeopoliticas = service
				.getEstructurasGeopoliticas(pais.getCodigo());

		params.put("descOrden1", " ");
		params.put("descOrden2", " ");
		params.put("descOrden3", " ");

		String fechaActua = DateUtil.convertDateToString(reporteForm
				.getFechaActuaD());
		if (StringUtils.isBlank(fechaActua))
			params.put("condicionFechaActua", "");
		else {
			String condicion = " AND TRUNC(ua_actual.fec_ulti_actu) = to_date('"
					+ fechaActua + "','DD/MM/YYYY')";
			params.put("condicionFechaActua", condicion);
		}

		params.put("fechaActua", reporteForm.getFechaActua());
		params.put("consultora", reporteForm.getConsultora());

		if (listEstructurasGeopoliticas != null
				&& listEstructurasGeopoliticas.size() >= 3) {
			UnidadGeografica ug1 = (UnidadGeografica) listEstructurasGeopoliticas
					.get(0);
			UnidadGeografica ug2 = (UnidadGeografica) listEstructurasGeopoliticas
					.get(1);
			UnidadGeografica ug3 = (UnidadGeografica) listEstructurasGeopoliticas
					.get(2);

			params.put("descOrden1", ug1.getDescripcionEstruGeopo());
			params.put("descOrden2", ug2.getDescripcionEstruGeopo());
			params.put("descOrden3", ug3.getDescripcionEstruGeopo());

		} else if (listEstructurasGeopoliticas != null
				&& listEstructurasGeopoliticas.size() >= 2) {
			UnidadGeografica ug1 = (UnidadGeografica) listEstructurasGeopoliticas
					.get(0);
			UnidadGeografica ug2 = (UnidadGeografica) listEstructurasGeopoliticas
					.get(1);

			params.put("descOrden1", ug1.getDescripcionEstruGeopo());
			params.put("descOrden2", ug2.getDescripcionEstruGeopo());

		}
		if (listEstructurasGeopoliticas != null
				&& listEstructurasGeopoliticas.size() >= 1) {
			UnidadGeografica ug1 = (UnidadGeografica) listEstructurasGeopoliticas
					.get(0);
			params.put("descOrden1", ug1.getDescripcionEstruGeopo());
		}

		params.put("formatoReporte", formatoReporte);
		return params;
	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte the formatoReporte to set
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
	 * @param siccMarcaList the siccMarcaList to set
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
	 * @param siccCanalList the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}
	
	

}