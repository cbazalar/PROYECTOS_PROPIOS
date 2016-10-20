package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessages;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteFLXSeguimientoFinanciamientoForm;

@ManagedBean
@SessionScoped
public class ReporteFLXSeguimientoFinanciamientoAction extends
		BaseReporteAbstractAction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7752515155905570425L;
	private String formatoReporte;
	private List siccRegionList;
	private LabelValue[] siccSeccionList = {};
	private LabelValue[] siccZonaList = {};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteFLXSeguimientoFinanciamientoForm form = new ReporteFLXSeguimientoFinanciamientoForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo());

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteria);

	}

	public void loadzonas(ValueChangeEvent val) {
		log.debug("loadzonas");

		String[] valores = (String[]) val.getNewValue();
		if (!val.equals(null) && valores.length > 0) {
			String[] regiones = (String[]) val.getNewValue();

			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.siccZonaList = aSvc
					.getZonasMultipleByPaisMarcaCanalRegion(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, regiones,
							Constants.FORMATO_TOTAL);

		} else {
			setSiccZonaList(null);
			setSiccSeccionList(null);

		}

	}

	//
	public void loadseccion(ValueChangeEvent val) {
		log.debug("loadseccion");
		ReporteFLXSeguimientoFinanciamientoForm form = (ReporteFLXSeguimientoFinanciamientoForm) this.formReporte;
		String[] regiones = (String[]) form.getRegionList();
		String[] zonas = (String[]) val.getNewValue();
		if (regiones.length > 0 && zonas.length > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			setSiccSeccionList(aSvc
					.getSeccionMultipleByPaisMarcaCanalRegionZona(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, regiones, zonas,
							Constants.FORMATO_TOTAL));

		} else {
			setSiccSeccionList(null);

		}

	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte)) {
			return "reporteFLXSeguimientoFinanciamientoXLS";
		} else {
			return "reporteMaestroHorizontal";
		}

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;

	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ActionMessages messages = new ActionMessages();
		String strMessage = null;

		ReporteFLXSeguimientoFinanciamientoForm f = (ReporteFLXSeguimientoFinanciamientoForm) this.formReporte;
		f.setFechaInicio("");
		f.setFechaFin("");
		if (f.getFechaInicioD() != null) {
			f.setFechaInicio(DateUtil.convertDateToString(f.getFechaInicioD()));
		}
		if (f.getFechaFinD() != null) {
			f.setFechaFin(DateUtil.convertDateToString(f.getFechaFinD()));
		}

		formatoReporte = f.getFormatoExportacion();

		String condicionRegion = this.obtieneCondicion(f.getRegionList(),
				"ZR.COD_REGI", "'");
		params.put("condicionRegion", condicionRegion);
		log.debug("condicionRegion" + condicionRegion.toString());

		String condicionZona = this.obtieneCondicion(f.getZonaList(),
				"ZZ.COD_ZONA", "'");
		params.put("condicionZona", condicionZona);
		log.debug("condicionZona" + condicionZona.toString());

		String condicionSeccion = this.obtieneCondicion(f.getSeccionList(),
				"ZS.COD_SECC", "'");
		params.put("condicionSeccion", condicionSeccion);
		log.debug("condicionSeccion" + condicionSeccion.toString());

		params.put("codigoCampana", f.getCodigoCampana());

		if (StringUtils.isNotBlank(f.getFechaInicio())) {
			params.put("fechaInicio", f.getFechaInicio());
		} else
			params.put("fechaInicio", null);

		if (StringUtils.isNotBlank(f.getFechaFin())) {
			params.put("fechaFin", f.getFechaFin());
		} else
			params.put("fechaFin", null);

		params.put("NroReporte", "");

		String titulo = getReportResourceMessage("reporteFLXSeguimientoFinanciamiento.titulo");
		params.put("titulo", titulo);

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

	/**
	 * @return the siccSeccionList
	 */
	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList
	 *            the siccSeccionList to set
	 */
	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
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

}
