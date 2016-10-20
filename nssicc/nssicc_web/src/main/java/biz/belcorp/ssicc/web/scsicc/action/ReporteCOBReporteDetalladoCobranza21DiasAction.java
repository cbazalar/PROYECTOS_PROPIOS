package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.ArrayList;
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
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBReporteDetalladoCobranza21DiasForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCOBReporteDetalladoCobranza21DiasAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = -7015464028674315305L;
	private String formatoReporte;
	private String codigoIdiomaISO;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private List siccSociedadList = new ArrayList();

	@Override
	protected void setViewAtributes() throws Exception {
		log.info("Entro a ReporteCOBReporteDetalladoCobranza21DiasAction - setViewAttributes");

		this.mostrarReporteXLS = true;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();

		this.siccRegionList = aSvc.getRegionesByPaisMarcaCanal(pais.getCodigo(),
				Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT);

		this.siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());
		ReporteCOBReporteDetalladoCobranza21DiasForm reporteCOBForm = (ReporteCOBReporteDetalladoCobranza21DiasForm) this.formReporte;
		reporteCOBForm.setCodigoPais(pais.getCodigo());

		log.info("Salio a ReporteCOBReporteDetalladoCobranza21DiasAction - setViewAttributes");
	}

	public String setValidarReporte() {
		ReporteCOBReporteDetalladoCobranza21DiasForm form = (ReporteCOBReporteDetalladoCobranza21DiasForm) this.formReporte;
		Integer fecha1, fecha2;
		fecha1 = Integer.parseInt(form.getCodigoPeriodoInicio());
		fecha2 = Integer.parseInt(form.getCodigoPeriodoFin());

		if (fecha1 > fecha2) {
			String mensaje = this
					.getResourceMessage("reporteCOBReporteDetalladoCobranza21DiasForm.msg.validacionCampanha");
			return mensaje;
		}

		return null;
	}

	/**
	 * Obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		try {
			ReporteCOBReporteDetalladoCobranza21DiasForm form = (ReporteCOBReporteDetalladoCobranza21DiasForm) this.formReporte;
			String[] regiones = (String[]) val.getNewValue();
			if (!val.equals(null) && regiones.length > 0) {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(
						form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, regiones,
						Constants.FORMATO_TOTAL));
				form.setCodigoZona(null);
			} else {
				this.siccZonaList = null;
				form.setCodigoZona(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	
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
			return "reporteCOBReporteDetalladoCobranza21DiasXLS";
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
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if ("PDF".equals(formatoReporte))
			return "reporteCOBReporteDetalladoCobranza21DiasPDF";

		return null;
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
		ReporteCOBReporteDetalladoCobranza21DiasForm form = new ReporteCOBReporteDetalladoCobranza21DiasForm();
		return form;
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
		ReporteCOBReporteDetalladoCobranza21DiasForm reporteCOBForm = (ReporteCOBReporteDetalladoCobranza21DiasForm) this.formReporte;
		this.formatoReporte = reporteCOBForm.getFormatoExportacion();
		String condicionRegion = "";
		String condicionZona = "";

		condicionRegion = this.obtieneCondicion(
				reporteCOBForm.getCodigoRegion(), "cbz.COD_REGI", "'");
		condicionZona = this.obtieneCondicion(reporteCOBForm.getCodigoZona(),
				"cbz.COD_ZONA", "'");

		params.put(
				"titulo",
				getReportResourceMessage("reporteCOBReporteDetalladoCobranza21DiasForm.titulo"));

		params.put("codigoPais", reporteCOBForm.getCodigoPais());
		params.put("codigoPeriodoInicio",
				reporteCOBForm.getCodigoPeriodoInicio());
		params.put("codigoPeriodoFin", reporteCOBForm.getCodigoPeriodoFin());
		params.put("codigoSociedad", reporteCOBForm.getCodigoSociedad());
		params.put("condicionRegion", condicionRegion != null ? condicionRegion
				: "");
		params.put("condicionZona", condicionZona != null ? condicionZona : "");
		reporteCOBForm.setFormatoExportacion(this.formatoReporte);
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
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList
	 *            the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
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
}