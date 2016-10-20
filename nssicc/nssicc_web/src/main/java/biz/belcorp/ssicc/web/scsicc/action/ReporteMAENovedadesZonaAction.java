package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteMAENovedadesZonaForm;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano
 *         Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteMAENovedadesZonaAction extends BaseReporteAbstractAction {

	private String[] listaZonas;
	private String[] listaRegiones;
	private String[] listaTotalSeccion;
	private String nameSubReporte;
	private String formatoReporte;
	private String tipoReporte;
	private String codigoZona;
	private String codigoRegion;
	int cont = 0;
	int count = 0;

	public String[] getListaZonas() {
		return listaZonas;
	}

	public void setListaZonas(String[] listaZonas) {
		this.listaZonas = listaZonas;
	}

	public String[] getListaRegiones() {
		return listaRegiones;
	}

	public void setListaRegiones(String[] listaRegiones) {
		this.listaRegiones = listaRegiones;
	}

	public String[] getListaTotalSeccion() {
		return listaTotalSeccion;
	}

	public void setListaTotalSeccion(String[] listaTotalSeccion) {
		this.listaTotalSeccion = listaTotalSeccion;
	}

	public String getNameSubReporte() {
		return nameSubReporte;
	}

	public void setNameSubReporte(String nameSubReporte) {
		this.nameSubReporte = nameSubReporte;
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

	public String getCodigoZona() {
		return codigoZona;
	}

	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	public String getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	private static final long serialVersionUID = 5452137025798023586L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteMAENovedadesZonaForm reporteForm = new ReporteMAENovedadesZonaForm();
		return reporteForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {

		return "reporteMaestroHorizontalMAENovedadesZona";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {
		if ("PDF".equals(formatoReporte)) {
			return "reporteMAENovedadesZonaPDF";
		}

		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()ReporteRECMercaderiaSiniestradaAction
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReporteMAENovedadesZonaForm.setViewAtributes' method");
		}

		this.mostrarReportePDF = false;
		this.mostrarReporteMailPDF = true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteMAENovedadesZonaAction.prepareParameterMap' method");
		}

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		ReporteMAENovedadesZonaForm reporteForm = (ReporteMAENovedadesZonaForm) this.formReporte;

		Map criteria = new HashMap();
		criteria.put("codigoPais", reporteForm.getCodigoPais());
		criteria.put("estadoCampanha", "0");
		criteria.put("indicadorActiva", "1");
		List lista = service.getCampanhasActivasByCriteria(criteria);
		if (lista.size() == 1) {
			params.put("campania", String.valueOf(lista.get(0)));
		}
		params.put("tipoReporte", this.tipoReporte);// ReporteCOMComisionIngresoAction
		params.put(
				"titulo",
				this.getReportResourceMessage("ReporteAPETotalArticulosAFPForm.titulo"));
		reporteForm
				.setTitulo(this
						.getReportResourceMessage("reporteMAENovedadesZonaForm.titulo"));
		reporteForm.setNroReporte("");
		this.formatoReporte = reporteForm.getFormatoExportacion();
		if (this.isVisualizarReporte()) {
			String zonas = this.listaZonas[this.getNroReporteProcesando() - 1];
			String Regiones = this.listaRegiones[this.getNroReporteProcesando() - 1];
			params.put("codigoZona", zonas);
			params.put("codigoRegion", Regiones);
		}

		cont++;
		params.put("cont", cont);
		params.put("count", count);
		return params;

	}
	

	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteMAENovedadesZonaService";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveBeanMailService()
	 */
	protected String devuelveBeanMailService() {
		return "mae.mailReporteMAENovedadesZona";
	}

	protected boolean continueExecuteReporte(ReporteParams reporteParams) {

		return true;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#getNroReportesAGenerar()
	 */
	protected int getNroReportesAGenerar() {
		//ReportePEJProgramaEjecutivasForm f = (ReportePEJProgramaEjecutivasForm) form;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");	
	
		List lista = reporteService.getListaZonasReporteMAENovedadesZona();
               
	     int tamanno = lista.size();
			this.listaRegiones = new String[tamanno];
			this.listaZonas = new String[tamanno];
			for (int i=0; i < tamanno; i++) {
				String[] cods = StringUtils.split(((Base)lista.get(i)).getCodigo(), ',');
				this.listaZonas[i] = cods[0]; 
				this.listaRegiones[i] = cods[1]; 
			}	
			
			count = this.listaZonas.length;
			return this.listaZonas.length;
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#getNombreArchivoReporte(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nombreArchivoReporte;		
	
		codigoZona = this.listaZonas[this.getNroReporteProcesando() - 1 ];
		codigoRegion = this.listaRegiones[this.getNroReporteProcesando() - 1 ];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		nombreArchivoReporte = this.getPrefijoArchivo() +  "_" +
								   codigoZona + "_" +codigoRegion+
						           sdf.format(new Date(System.currentTimeMillis()));
		
		return nombreArchivoReporte;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#getValorFiltroGrabarReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams)
	 */
	protected String getValorFiltroGrabarReporte(ReporteParams reporteParams) {
		String filtro = "";
			filtro = "Zona: ".concat(this.listaZonas[this.getNroReporteProcesando() - 1 ])
			.concat(" Region: ").concat(this.listaRegiones[this.getNroReporteProcesando() - 1 ]);		
			return filtro;
	}

	

}