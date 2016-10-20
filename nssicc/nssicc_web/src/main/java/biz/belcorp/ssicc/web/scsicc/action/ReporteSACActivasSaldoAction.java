package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACActivasSaldoForm;


// TODO: Auto-generated Javadoc
/**
 * The Class ReporteSACFacturacionDetalleAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 28/08/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSACActivasSaldoAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The lista total. */
	private String[] listaTotal;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSACActivasSaldoForm form = new ReporteSACActivasSaldoForm();
		return form;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSACActivasSaldoForm form = (ReporteSACActivasSaldoForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		return "reporteSACActivasSaldoXLS";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSACActivasSaldoForm form = (ReporteSACActivasSaldoForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		
		this.mostrarReporteMailXLS = false;
		this.mostrarReporteMailPDF = false;
		this.mostrarReportePDF = false;
		this.mostrarListaReporteLog = true;
		
		ReporteSACActivasSaldoForm form = (ReporteSACActivasSaldoForm) this.formReporte;
		
		form.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		
		ReporteSACActivasSaldoForm form = (ReporteSACActivasSaldoForm) this.formReporte;
		int contNroReporteProcesando = 0;
		
		params.put("NroReporte", "");
		this.formatoExportacion="XLS";
		params.put("formatoExportacion",this.formatoExportacion);
		
		form.setFormatoExportacion(this.formatoExportacion);		
		
		if (!this.isVisualizarReporte()) {
			contNroReporteProcesando = this.getNroReporteProcesando();
			
			if(listaTotal != null){
				params.put("codigoZona", this.listaTotal[contNroReporteProcesando - 1 ]);
			}
		}
		
		//if(contNroReporteProcesando>1){
			form.setEnvioEmail(true);		
		//}
		
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#getNroReportesAGenerar()
	 */
	@Override
	protected int getNroReportesAGenerar() {
		ReporteSACActivasSaldoForm form = (ReporteSACActivasSaldoForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");				
		
		List l = new ArrayList();
	    l = reporteService.getListaZonasReporteSACActivasSaldo();
		
	    int tamanno = l.size();
		this.listaTotal = new String[tamanno];
		
		for (int i=0; i < tamanno; i++) {
			this.listaTotal[i] = (String)l.get(i); 
		}
		
		return this.listaTotal.length;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#getValorFiltroGrabarReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams)
	 */
	@Override
	protected String getValorFiltroGrabarReporte(ReporteParams reporteParams) {
		String filtro = new String();
		filtro = "Zona: ";		
		return filtro + this.listaTotal[this.getNroReporteProcesando() - 1 ];
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#getNombreArchivoReporte(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nombreArchivoReporte;		
		String codigoZona = this.listaTotal[this.getNroReporteProcesando() - 1 ];				
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		
		nombreArchivoReporte = this.getPrefijoArchivo() +  "_" +
							   codigoZona + "_" +
					           sdf.format(new Date(System.currentTimeMillis()));
		
		return nombreArchivoReporte;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteSACActivasSaldoServiceImpl";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanMailService()
	 */
	protected String devuelveBeanMailService(){
		return "sac.mailReporteSACActivasSaldoService";
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#beforeGrabarReporte()
	 */
	@Override
	protected void beforeGrabarReporte(){
		ReporteSACActivasSaldoForm form = (ReporteSACActivasSaldoForm) this.formReporte;
		form.setNameSubReporte("reporteSACActivasSaldoXLS");		
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#continueExecuteReporte(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	/**
	 * Continue execute reporte.
	 *
	 * @param reporteParams the reporte params
	 * @return true, if successful
	 */
	protected boolean continueExecuteReporte(ReporteParams reporteParams) {		
		return true;
	}

	/**
	 * Gets the lista total.
	 *
	 * @return the lista total
	 */
	public String[] getListaTotal() {
		return listaTotal;
	}

	/**
	 * Sets the lista total.
	 *
	 * @param listaTotal the new lista total
	 */
	public void setListaTotal(String[] listaTotal) {
		this.listaTotal = listaTotal;
	}
		
}
