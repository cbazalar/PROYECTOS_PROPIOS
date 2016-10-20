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
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.spusicc.pej.MantenimientoPEJProgramaEjecutivasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePEJProgramaEjecutivasForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
public class ReportePEJProgramaEjecutivasAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 6467992741175535675L;
	private String formatoExportacion;
	private String codigoIdiomaIso;
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccSeccionList = {};
	private List pejTipoReporteList;
	private String tipoReporte;
	private String formatoReporte;
	private String nameSubReporte;
	private String[] listaTotal;
	private String[] listaTotalSeccion;

	
	public String getNameSubReporte() {
		return nameSubReporte;
	}

	public void setNameSubReporte(String nameSubReporte) {
		this.nameSubReporte = nameSubReporte;
	}

	public String[] getListaTotal() {
		return listaTotal;
	}

	public void setListaTotal(String[] listaTotal) {
		this.listaTotal = listaTotal;
	}

	public String[] getListaTotalSeccion() {
		return listaTotalSeccion;
	}

	public void setListaTotalSeccion(String[] listaTotalSeccion) {
		this.listaTotalSeccion = listaTotalSeccion;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public List getPejTipoReporteList() {
		return pejTipoReporteList;
	}

	public void setPejTipoReporteList(List pejTipoReporteList) {
		this.pejTipoReporteList = pejTipoReporteList;
	}

	public String getCodigoIdiomaIso() {
		return codigoIdiomaIso;
	}

	public void setCodigoIdiomaIso(String codigoIdiomaIso) {
		this.codigoIdiomaIso = codigoIdiomaIso;
	}

	public String getFormatoExportacion() {
		return formatoExportacion;
	}

	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}

	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		this.mostrarReportePDF = true;
		this.mostrarReporteMailPDF = true;

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoPEJProgramaEjecutivasService service = (MantenimientoPEJProgramaEjecutivasService) getBean("spusicc.mantenimientoPEJProgramaEjecutivasService");
		ReportePEJProgramaEjecutivasForm f = (ReportePEJProgramaEjecutivasForm) formReporte;

		pejTipoReporteList = reporteService.getTipoReporte();

		Map result = service.getPeriodoDefault();

		String codigoPeriodo = (String) result.get("codigoPeriodo");
		String fechaProceso = (String) result.get("fechaProceso");
		f.setCampanyaProceso(codigoPeriodo);
		f.setFechaFacturacion(fechaProceso);

		Map criteria = new HashMap();
		criteria.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());

		siccRegionList = reporteService.getListaGenerico("getRegionesPEJ",
				criteria);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		String reporte = "";

		if ("XLS".equals(formatoReporte)) {
			if (tipoReporte.equals("0001")) {
				reporte = "reportePEJGestionXLS";
			}

			if (tipoReporte.equals("0002")) {
				reporte = "reportePEJPagosXLS";
			}

			if (tipoReporte.equals("0003")) {
				reporte = "reportePEJConsulEjecutivaXLS";
			}
		} else
			reporte = "reporteMaestroHorizontal";

		return reporte;

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		String reporte = "";

		if ("PDF".equals(formatoReporte)) {
			if (tipoReporte.equals("0001")) {
				reporte = "reportePEJGestionPDF";
			}

			if (tipoReporte.equals("0002")) {
				reporte = "reportePEJPagosPDF";
			}

			if (tipoReporte.equals("0003")) {
				reporte = "reportePEJConsulEjecutivaPDF";
			}
		}

		return reporte;
	}

	protected Map prepareParameterMap(Map params) throws Exception {

		MantenimientoPEJProgramaEjecutivasService service = (MantenimientoPEJProgramaEjecutivasService) this
				.getBean("spusicc.mantenimientoPEJProgramaEjecutivasService");
		String codigoPais = mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo();
		Map ejecutiva = new HashMap();
		ejecutiva.put("estado", "1");
		ejecutiva.put("codigoPais", codigoPais);
		List listProg = service.getProgramasByCriteria(ejecutiva);
		if (listProg.size() > 0) {
			Map programaEjecutiva = (Map) listProg.get(0);
			String codigoPrograma = (String) programaEjecutiva
					.get("codigoPrograma");
			params.put("codigoPrograma", codigoPrograma);
		} else {
			params.put("codigoPrograma", null);
		}

		ReportePEJProgramaEjecutivasForm reporteForm = (ReportePEJProgramaEjecutivasForm) formReporte;
		formatoReporte = reporteForm.getFormatoExportacion();
		tipoReporte = reporteForm.getTipoReporte();

		String regionList[] = { reporteForm.getCodigoRegion() };
		String zonaList[] = { reporteForm.getCodigoZona() };
		String seccionList[] = { reporteForm.getCodigoSeccion() };

		String condicionRegion = this.obtieneCondicion(regionList,
				"gere.cod_regi", "'");
		String condicionZonas = this.obtieneCondicion(zonaList,
				"gere.cod_zona", "'");
		String condicionSeccion = this.obtieneCondicion(seccionList,
				"gere.cod_secc", "'");
		String condicion = condicionRegion + condicionZonas + condicionSeccion;
		params.put("condicion", condicion);

		if (tipoReporte.equals("0001")) {
			params.put("titulo",
					this.getResourceMessage("reportePEJGestionForm.titulo"));
		}

		if (tipoReporte.equals("0002")) {
			params.put("titulo",
					this.getResourceMessage("reportePEJPagosForm.titulo"));
		}

		if (tipoReporte.equals("0003")) {
			params.put(
					"titulo",
					this.getResourceMessage("reportePEJConsultoraEjecutivaForm.titulo"));
		}

		params.put("tipoReporte", this.tipoReporte);

		params.put("condicionZonaCorreo", " ");
		params.put("condicionSeccionCorreo", " ");

		if (!this.isVisualizarReporte()) {
			if ((tipoReporte.equals("0001")) || (tipoReporte.equals("0002"))) {
				if (this.listaTotal[this.getNroReporteProcesando() - 1]
						.indexOf("codigoRegion__") < 0) {
					params.put("codigoRegion", null);
					params.put("codigoZona",
							this.listaTotal[this.getNroReporteProcesando() - 1]);
					params.put(
							"condicionZonaCorreo",
							" AND gere.cod_zona='"
									+ this.listaTotal[this
											.getNroReporteProcesando() - 1]
									+ "' ");
				} else {
					params.put("codigoRegion", this.listaTotal[this
							.getNroReporteProcesando() - 1].substring(14));
					params.put(
							"condicionZonaCorreo",
							" AND gere.cod_regi='"
									+ this.listaTotal[this
											.getNroReporteProcesando() - 1]
											.substring(14) + "' ");
				}
			} else {
				params.put(
						"codigoZona",
						this.listaTotalSeccion[this.getNroReporteProcesando() - 1]);
				params.put(
						"condicionSeccionCorreo",
						" AND gere.cod_zona='"
								+ this.listaTotalSeccion[this
										.getNroReporteProcesando() - 1] + "' ");
			}
		}

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
		ReportePEJProgramaEjecutivasForm reporteForm = new ReportePEJProgramaEjecutivasForm();
		return reporteForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */

	protected int getNroReportesAGenerar() {
		ReportePEJProgramaEjecutivasForm f = (ReportePEJProgramaEjecutivasForm) formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		AjaxService ajaxService = (AjaxService) this.getBean("ajaxService");
		List lista = new ArrayList();

		String codigoRegion = f.getCodigoRegion();
		String codigoZona = f.getCodigoZona();
		String codigoSeccion = f.getCodigoSeccion();

		if ((tipoReporte.equals("0001")) || (tipoReporte.equals("0002"))) {

			if (StringUtils.equals(codigoZona, "Todos")
					|| StringUtils.isBlank(codigoZona)) {
				String codigoRegionAux = codigoRegion;
				if (StringUtils.equals(codigoRegion, "Todos")
						|| StringUtils.isBlank(codigoRegion)) {
					codigoRegionAux = "";
				}

				LabelValue[] result = ajaxService.getZonasRegionPEJTodos(
						codigoRegionAux, f.getCampanyaProceso());
				if (result != null) {
					for (int i = 0; i < result.length; i++) {
						LabelValue zonas = result[i];
						lista.add(zonas.getValue());
					}
				}
			} else {
				lista.add(codigoZona);
			}

			if (StringUtils.equals(codigoRegion, "Todos")
					|| StringUtils.isBlank(codigoRegion)) {
				List listaRegiones = reporteService.getListaGenerico(
						"getRegionesPEJ", null);

				for (int i = 0; i < listaRegiones.size(); i++) {
					Base base = (Base) listaRegiones.get(i);
					lista.add("codigoRegion__" + base.getCodigo());
				}
			} else
				lista.add("codigoRegion__" + codigoRegion);

			int tamanno = lista.size();
			this.listaTotal = new String[tamanno];
			for (int i = 0; i < tamanno; i++) {
				this.listaTotal[i] = (String) lista.get(i);
			}
			return this.listaTotal.length;
		} else {
			if (StringUtils.equals(codigoZona, "Todos")
					|| StringUtils.isBlank(codigoZona)) {
				String codigoRegionAux = codigoRegion;
				if (StringUtils.equals(codigoRegion, "Todos")
						|| StringUtils.isBlank(codigoRegion)) {
					codigoRegionAux = "";
				}

				LabelValue[] result = ajaxService.getZonasRegionPEJTodos(
						codigoRegionAux, f.getCampanyaProceso());
				if (result != null) {
					for (int i = 0; i < result.length; i++) {
						LabelValue zonas = result[i];
						lista.add(zonas.getValue());
					}
				}
			} else {
				lista.add(codigoZona);
			}

			int tamanno = lista.size();
			this.listaTotalSeccion = new String[tamanno];
			for (int i = 0; i < tamanno; i++) {
				this.listaTotalSeccion[i] = (String) lista.get(i);
			}
			return this.listaTotalSeccion.length;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction
	 * #getValorFiltroGrabarReporte
	 * (biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected String getValorFiltroGrabarReporte(ReporteParams reporteParams) {
		String filtro = new String();
		if ((tipoReporte.equals("0001")) || (tipoReporte.equals("0002"))) {
			if (this.listaTotal[this.getNroReporteProcesando() - 1]
					.indexOf("codigoRegion__") < 0) {
				filtro = "Zona: ";
				return filtro
						+ this.listaTotal[this.getNroReporteProcesando() - 1];
			} else {
				filtro = "Region: ";
				return filtro
						+ this.listaTotal[this.getNroReporteProcesando() - 1]
								.substring(14);
			}
		} else {
			filtro = "Zona: ";
			return filtro
					+ this.listaTotalSeccion[this.getNroReporteProcesando() - 1];
		}
	}

	protected boolean continueExecuteReporte(ReporteParams reporteParams) {
		return true;
	}

	/**
	 * Obtener Lista de Zonas
	 * 
	 * @param val
	 */
	
	public void showZonasxRegion(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("cambiaZonas...");
		}
		String valor = (String) val.getNewValue();
		if (StringUtils.isNotBlank(valor) || StringUtils.isNotEmpty(valor)) {
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			ReportePEJProgramaEjecutivasForm form = (ReportePEJProgramaEjecutivasForm) this.formReporte;
			form.setCodigoRegion(valor);
			String[] valores = new String[1];
			valores[0] = valor;

			this.setSiccZonaList(ajaxService.getZonasRegionPEJ(valor));

		}
	}
	public void showSeccionxZona(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("cambiaZonas...");
		}
		String valor = (String) val.getNewValue();
		if (StringUtils.isNotBlank(valor) || StringUtils.isNotEmpty(valor)) {
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			ReportePEJProgramaEjecutivasForm form = (ReportePEJProgramaEjecutivasForm) this.formReporte;
			form.setCodigoRegion(valor);
			String[] valores = new String[1];
			valores[0] = valor;

			this.setSiccZonaList(ajaxService.getSeccionesZonaPEJ( valor));

		}
	}

	/**
	 * @return
	 */

	/**
	 * @return
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @param siccZonaList
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}
	
	protected void beforeGrabarReporte() {		
		
		ReportePEJProgramaEjecutivasForm reporteForm = (ReportePEJProgramaEjecutivasForm) formReporte;
		
		this.tipoReporte =reporteForm.getTipoReporte();
		this.formatoReporte ="PDF";
		
		if (tipoReporte.equals("0001")){
			this.nameSubReporte = "reportePEJGestionPDF";
		}
		
		if (tipoReporte.equals("0002")){
			this.nameSubReporte= "reportePEJPagosPDF";
		}
		
		if (tipoReporte.equals("0003")){
			this.nameSubReporte= "reportePEJConsulEjecutivaPDF";
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#getNombreArchivoReporte(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nombreArchivoReporte;		
		if ((tipoReporte.equals("0001")) || (tipoReporte.equals("0002"))) {
			if(this.listaTotal[this.getNroReporteProcesando() - 1 ].indexOf("codigoRegion__")<0) {
				String codigoZona = this.listaTotal[this.getNroReporteProcesando() - 1 ];				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
				nombreArchivoReporte = this.getPrefijoArchivo() +  "_" +
									   codigoZona + "_" +
							           sdf.format(new Date(System.currentTimeMillis()));
			} else {
				String codigoRegion = this.listaTotal[this.getNroReporteProcesando() - 1 ].substring(14);				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
				nombreArchivoReporte = this.getPrefijoArchivo() +  "_" +
										codigoRegion + "_" +
							           sdf.format(new Date(System.currentTimeMillis()));
			}	
		}
		else {
			String codigoZona = this.listaTotalSeccion[this.getNroReporteProcesando() - 1 ];				
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
			nombreArchivoReporte = this.getPrefijoArchivo() +  "_" +
								   codigoZona + "_" +
						           sdf.format(new Date(System.currentTimeMillis()));
		}
		return nombreArchivoReporte;
	}

	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reportePEJProgramaEjecutivasService";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveBeanMailService()
	 */
	protected String devuelveBeanMailService() {
		return "pej.mailReportePEJProgramaEjecutivas";
	}

}