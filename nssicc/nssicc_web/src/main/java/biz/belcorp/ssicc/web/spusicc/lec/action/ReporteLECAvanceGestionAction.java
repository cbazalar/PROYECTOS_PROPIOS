package biz.belcorp.ssicc.web.spusicc.lec.action;

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
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECProgramaCorporativoService;
import biz.belcorp.ssicc.service.spusicc.pej.MantenimientoPEJProgramaEjecutivasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.lec.form.ReporteLECAvanceGestionForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"unchecked", "rawtypes"})
public class ReporteLECAvanceGestionAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = -8283811688652696544L;

	private String formatoReporte;
	private List siccRegionList;
	private LabelValue[] siccZonaList;
	private List siccSeccionList;

	private String[] listaTotal;
	private String[] listaTotalSeccion;
	private String nameSubReporte;

	/**
	 * @return
	 */
	public String[] getListaTotalSeccion() {
		return listaTotalSeccion;
	}

	/**
	 * @param listaTotalSeccion
	 */
	public void setListaTotalSeccion(String[] listaTotalSeccion) {
		this.listaTotalSeccion = listaTotalSeccion;
	}

	/**
	 * @return
	 */
	public String getNameSubReporte() {
		return nameSubReporte;
	}

	/**
	 * @param nameSubReporte
	 */
	public void setNameSubReporte(String nameSubReporte) {
		this.nameSubReporte = nameSubReporte;
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

	/**
	 * @return
	 */
	public List getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList
	 */
	public void setSiccSeccionList(List siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLECAvanceGestionForm reporteForm = new ReporteLECAvanceGestionForm();
		return reporteForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		String reporte = "";
		
		if ("XLS".equals(this.formatoReporte)) {
			reporte = "reporteLECAvanceGestionXLS";
		} else
			reporte = "reporteMaestroHorizontalLECAvanceGestion";

		return reporte;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		String reporte = "";

		if ("PDF".equals(this.formatoReporte)) {
			reporte = "reporteLECAvanceGestionPDF";
		}

		return reporte;

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#getNroReportesAGenerar()
	 */
	protected int getNroReportesAGenerar() {
		ReporteLECAvanceGestionForm f = (ReporteLECAvanceGestionForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		AjaxService ajaxService = (AjaxService) this.getBean("ajaxService");
		List lista = new ArrayList();

		String codigoRegion = f.getCodigoRegion();
		String codigoZona = f.getCodigoZona();

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
		// }

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#getValorFiltroGrabarReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams)
	 */
	protected String getValorFiltroGrabarReporte(ReporteParams reporteParams) {
		String filtro = new String();
		if (this.listaTotal[this.getNroReporteProcesando() - 1]
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
	
	/**
	 * @return
	 * @throws Exception
	 */
	private List llenarListaTotal() throws Exception
	{
		ReporteLECAvanceGestionForm f = (ReporteLECAvanceGestionForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		AjaxService ajaxService = (AjaxService) this.getBean("ajaxService");
		List lista = new ArrayList();

		String codigoRegion = f.getCodigoRegion();
		String codigoZona = f.getCodigoZona();

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
		
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#getNombreArchivoReporte(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		
		int j = this.getNroReportesAGenerar();
		this.listaTotal = new String[j];
		try {
			List lista = new ArrayList();
			lista = this.llenarListaTotal();
			int i;
			for (i=0; i<lista.size(); i++) {
				listaTotal[i] = (String)lista.get(i);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));

		}
		
		String nombreArchivoReporte;		
			if(this.listaTotal[this.getNroReporteProcesando() - 1 ].indexOf("codigoRegion__")<0 ) {
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
		//}
	
		return nombreArchivoReporte;
	}
	
	protected boolean continueExecuteReporte(ReporteParams reporteParams) {
		return true;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteLECAvanceGestionForm reporteForm = (ReporteLECAvanceGestionForm) this.formReporte;
		reporteForm.setFechaFacturacion(DateUtil.getDate(reporteForm.getFechaFacturacionD()));
		params.put("codigoPrograma", reporteForm.getCodigoPrograma());

		this.formatoReporte = reporteForm.getFormatoExportacion();

		String regionList[] = { reporteForm.getCodigoRegion() };
		String zonaList[] = { reporteForm.getCodigoZona() };

		String condicionRegion = this.obtieneCondicion(regionList,
				"zorg.cod_regi", "'");
		String condicionZonas = this.obtieneCondicion(zonaList,
				"zzon.cod_zona", "'");
		String condicion = condicionRegion + condicionZonas;
		params.put("condicion", condicion);

		params.put("titulo",
				this.getResourceMessage("reporteLECAvanceGestionForm.titulo"));

		params.put("condicionZonaCorreo", " ");
		params.put("condicionSeccionCorreo", " ");

		if (!this.isVisualizarReporte()) {

			if (this.listaTotal[this.getNroReporteProcesando() - 1]
					.indexOf("codigoRegion__") < 0) {
				params.put("codigoRegion", null);
				params.put("codigoZona",
						this.listaTotal[this.getNroReporteProcesando() - 1]);
				params.put("condicionZonaCorreo", " AND zzon.cod_zona='"
						+ this.listaTotal[this.getNroReporteProcesando() - 1]
						+ "' ");
				String campanyaProceso = reporteService.getCampanyaBonoZona(params);
				String campanyaRecaudo = reporteService.getCampanyaRecaudoZona(params);
				
				params.put("campanyaProceso", campanyaProceso);
				params.put("campanyaRecaudo", campanyaRecaudo);
				
			} else {
				params.put("codigoRegion", this.listaTotal[this
						.getNroReporteProcesando() - 1].substring(14));
				params.put(
						"condicionZonaCorreo",
						" AND zorg.cod_regi='"
								+ this.listaTotal[this
										.getNroReporteProcesando() - 1]
										.substring(14) + "' ");
				String campanyaProceso = reporteService.getCampanyaBonoRegion(params);
				String campanyaRecaudo = reporteService.getCampanyaRecaudoRegion(params);
				
				params.put("campanyaProceso", campanyaProceso);
				params.put("campanyaRecaudo", campanyaRecaudo);
			}
		}else {
			params.put("codigoRegion", reporteForm.getCodigoRegion());
			params.put("codigoZona", reporteForm.getCodigoZona());
			params.put("campanyaProceso", reporteForm.getCampanyaProceso());
			params.put("campanyaRecaudo", reporteService.getCampanyaRecaudoRegionZona(params));
		}
		
		MantenimientoLECProgramaCorporativoService lecService = (MantenimientoLECProgramaCorporativoService)getBean("spusicc.mantenimientoLECProgramaCorporativoService");
		String campanhaRecaudo= (String)params.get("campanyaRecaudo");

		Map map1 = lecService.getEncontrarProgramaLecCorporativo(campanhaRecaudo);
		params.put("codigoPrograma2",map1.get("codigoPrograma") );
		
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarReporteXLS = true;
		this.mostrarReporteMailPDF = true;
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoPEJProgramaEjecutivasService service = (MantenimientoPEJProgramaEjecutivasService) getBean("spusicc.mantenimientoPEJProgramaEjecutivasService");
		ReporteLECAvanceGestionForm f = (ReporteLECAvanceGestionForm) formReporte;
		MantenimientoLECProgramaCorporativoService lecService = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
		Map result = service.getPeriodoDefault();

		String codigoPeriodo = (String) result.get("codigoPeriodo");
		String fechaProceso = (String) result.get("fechaProceso");
		f.setCampanyaProceso(codigoPeriodo);
		f.setFechaFacturacion(fechaProceso);
		f.setFechaFacturacionD(DateUtil.convertStringToDate(f.getFechaFacturacion()));
		Map map1 = lecService.getEncontrarProgramaLecCorporativo(f
				.getCampanyaProceso());

		f.setCodigoPrograma(map1.get("codigoPrograma") == null ? "" : map1.get(
				"codigoPrograma").toString());
		f.setDescPrograma(map1.get("descPrograma") == null ? "" : map1.get(
				"descPrograma").toString());

		Map criteria = new HashMap();
		criteria.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		this.siccRegionList = reporteService.getListaGenerico("getRegionesPEJ",
				criteria);
		this.siccSeccionList = new ArrayList();
		this.listaTotal = new String[0]; 

	}

	protected String devuelveBeanMailService() {
		return "lec.mailReporteLECAvanceGestion";
	}

	public void cambiaZonasByRegion(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("cambiaZonas...");
		}
		try {
			
			
			if (StringUtils.isNotEmpty(val.getNewValue().toString())
					|| StringUtils.isNotBlank(val.getNewValue().toString())) {
				String valores = (String) val.getNewValue();
				AjaxService ajaxService = (AjaxService) getBean("ajaxService");				
				this.siccZonaList= ajaxService.getZonasRegionPEJ(valores);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#beforeGrabarReporte()
	 */
	protected void beforeGrabarReporte() {
     	this.formatoReporte = "PDF";
		this.nameSubReporte = "reporteLECAvanceGestionPDF";

	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#afterGrabarReporte()
	 */
	@Override
	protected void afterGrabarReporte() {
		super.afterGrabarReporte();
		log.debug("afterGrabarReporte");
		this.setVisualizarReporte(true);
	}
	
}
