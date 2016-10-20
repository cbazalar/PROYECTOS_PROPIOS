/*
 * 
 */

package biz.belcorp.ssicc.web.framework.base.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.HistoricoReporte;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.bean.ReporteEmitido;
import biz.belcorp.ssicc.reportes.framework.bean.ReporteEnviadoMail;
import biz.belcorp.ssicc.reportes.framework.service.BaseReporteInterface;
import biz.belcorp.ssicc.reportes.framework.service.ReporteExecutionService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteResult;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailResult;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.util.ZipUtil;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;


/**
 * Clase Action abstracta para las Reportes ScSiCC. Define un flujo basico de
 * ejecucion de un reporte de Jasper Reports.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 *
 */

public abstract class BaseReporteAbstractAction extends MBaseSistemaAbstractJSF {

	private static final long serialVersionUID = 8748129238059672840L;
	
	//Bean donde se colocaran los los filtro y el parameter-map del reporte a Generar
	protected BaseReporteForm formReporte;  
	
	protected static final String JASPER_EXTENSION = ".jasper";
	protected static final String PDF_EXTENSION = ".pdf";
	protected static final String XLS_EXTENSION = ".xls";
	protected static final String XLSX_EXTENSION = ".xlsx";
	protected static final String CSV_EXTENSION = ".csv";	
	protected static final String TXT_EXTENSION = ".txt";
	protected static final String ZIP_EXTENSION = ".zip";
		
	private boolean generateTabsXLS = false;
	protected static final Integer MAXIMUM_ROWS_PER_SHEET_XLS = new Integer(50000);
	
	private String outfile = null;
	private String outfileMedia = null;

	protected String formatoExportacion;

	//Verifica que el se encuentra en ejecucion
	//Para ello en el xhtml deben incluirlo con inputHidden
	protected boolean reporteEnEjecucion = false;
	
	private int nroReporteProcesando = 1;

	protected HistoricoReporte currentHistoricoReporte;

	protected List<ReporteEnviadoMail> listaReportesMail;
	protected ReporteEnviadoMail currentReporteEnviadoMail;
	protected boolean viewListaEnviadosMail = false;

	protected boolean visualizarReporte = true;
	protected boolean mostrarLogPantalla = false;
		
	protected boolean mostrarReportePDF = true;
	protected boolean mostrarReporteXLS = false;
	protected boolean mostrarReporteXLS97 = false;
	protected boolean mostrarReporteZIP = false;
	protected boolean mostrarReporteXLSX = false;
	protected boolean mostrarReporteCSV = false;
	protected boolean mostrarReporteOTXT = false;
	protected boolean mostrarReporteOZIP = false;
	
	protected boolean mostrarReporteOCSV = false;
	protected boolean mostrarReporteOJXLSX = false;	
	protected boolean mostrarReporteOOXLSX = false;
	
	protected boolean mostrarEnviarEmail = false;
	protected String messageErrorEmail;
	protected boolean ejecutarReporteJASPER = true;

	protected boolean viewReporteMedia;
	protected int sizeReport = 700;
	protected boolean viewPDFMedia = true;
	
	protected String codigoReporteAuditoria;
	
	//List donde se guardara el resultado de la busqueda y será mostrada en el Datatable
	protected List listaBusqueda;
	protected boolean mostrarListaBusqueda = false;
	protected boolean mostrarListaReporteLog = false;
	protected boolean mostrarBotonBuscar = false;
	protected boolean mostrarReporteMailXLS = false;
	protected boolean mostrarReporteMailPDF = false;
	
	private boolean indicadorMultiReporte = false;
	private String  prefijoArchivo;
    private String nombreReporteJRXMLEjecutar;
	

    protected boolean ejecucionReporteJasper = false;
    protected boolean ejecucionReporteNoJasper = false;
    protected boolean ejecucionReporteMail = false;
	
	//Atributo DataTableModel usado en el Datatable la cual contiene la lista guardada en listaBusqueda
	protected DataTableModel datatableBusqueda;
	private boolean isDeleteTemporal=true;
	
	private String iconPersonalExcel;
	private String iconPersonalPdf;
	protected boolean mostrarExcelXLSX = true;
	protected boolean ignorarCeldaBorder = true;
	
	/**
	 *  Metodo que limpia la Grilla de Busqueda
	 * de Busqueda
	 * Para las busquedas tipo AJAX
	 */
	public void limpiarFind(ActionEvent actionEvent) {
		try {	
			this.listaBusqueda = new ArrayList();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			this.setLimpiarFind();
		}
		catch(Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 *  Metodo principal que efectua la busqueda en base a los filtros seleccionados en la pantalla
	 * de Busqueda
	 * Para las busquedas tipo AJAX
	 */
	public void find(ActionEvent actionEvent) {
		this.find();
	}
	
	/**
	 * Metodo principal que efectua la busqueda en base a los filtros seleccionados en la pantalla
	 * de Busqueda
	 * Para las busquedas tipo ACTION
	 */
	public String find() {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'find' method");
		}
		this.accion = this.ACCION_BUSCAR;
		this.viewReporteMedia = false;	
		this.outfileMedia = null;
		
		if(!this.validarFind()){
			this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
			return null;
		}
		
		try {			
			this.datatableBusqueda = null;
			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			if ((this.listaBusqueda == null) ||(this.listaBusqueda.size() == 0)){
				this.addWarn("Warning: ", this.getResourceMessage("errors.datos.fuentes.busqueda"));
			}
			
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		if (log.isWarnEnabled()) {
			log.warn("Finish 'find' method");
		}
		return null;
	}
	
	/**
	 * Realiza acciones personalizadas de validación previa, de existir, antes de realizar Busqueda
	 * @return
	 */
	private boolean validarFind(){
		boolean validacion = true;	
		String lsMensajeError = this.setValidarFind();
		if (StringUtils.isNotBlank(lsMensajeError)) {
			validacion = false;	
			this.setMensajeAlertaDefault(lsMensajeError);
		}
		return validacion;
	}
	
	/**
	 * Devuelve Mensaje de error personalizado de validacion extra antes de realizar busqueda
	 * @return
	 */
	public String setValidarFind(){
		return "";
	}
	
	
	/**
	 * Hook method para la ejecucion de la Busqueda. Esta implementacion devuelve una lista con los valores
	 * respectivos de acuerdo a los filtros seleccionados. Dicho metodo es obligatorio sobreescribirlo 
	 * La busqueda por defecto es por AJAX
	 * @throws Exception
	 */
	protected List setFindAttributes() throws Exception {
		   return null;
  }	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setBeforeViewAtributes()
	 */
	@Override
	protected final void setBeforeViewAtributes() throws Exception {
		super.setBeforeViewAtributes();
		this.formReporte = this.devuelveFormReporte();
		
		/** Valores Genericos seteados de la sesion del usuario **/
		if (this.formReporte != null) {
			String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + "/resources/images/";
			this.formReporte.setUsuario(this.mPantallaPrincipalBean.getCurrentUser());
			this.formReporte.setPais(this.mPantallaPrincipalBean.getCurrentCountry());
			this.formReporte.setIdioma(this.mPantallaPrincipalBean.getCurrentIdioma());
			//this.formReporte.setRutaImagenes(this.getRequest().getRealPath("/")+"images"+System.getProperty("file.separator"));	
			this.formReporte.setRutaImagenes(path);	
			this.formReporte.setParametrosReporteGeneral(this.mPantallaPrincipalBean.getParametrosReporteGeneral());
			this.formReporte.setOidIdiomaIso(this.mPantallaPrincipalBean.getOidIdiomaIso());
			this.formReporte.setLocale(this.mPantallaPrincipalBean.getLocaleKey());
			this.formReporte.setAnyoPeriodo(this.mPantallaPrincipalBean.getAnyoActualperiodo());
			this.formReporte.setEnvioEmail(false);
			this.formReporte.setCodigoProcesoBatch(this.codigoProcesoBatch);
			this.formReporte.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		}
		if (this.mPantallaPrincipalBean != null) {
			this.mPantallaPrincipalBean.setCurrentMenu(this.codigoMenu);
			this.mPantallaPrincipalBean.setCodigoProcesoBatch(this.codigoProcesoBatch);
		}
		
		log.debug("Codigo Menu........... : "+this.codigoMenu);
		log.debug("Codigo Proceso Batch.. : "+this.codigoProcesoBatch);
		
		this.viewReporteMedia = false;		
		this.visualizarReporte = true;		
		this.validacionPrevia = false;
		this.currentReporteEnviadoMail = null;
		this.viewListaEnviadosMail = false;
		this.outfileMedia = null;
		this.viewListaEnviadosMail = false;
		this.setReporteEnEjecucion(true);
		this.setKeyMensajeAlertaDefault("reporte.enEjecucion");
						
	}
	
	/**
	 * Metodo que verifica si el reporte se encuentra actualmente en ejecucion.
	 * @param actionEvent
	 * @return
	 */
	public void verificarEjecucionReporte(ActionEvent actionEvent){
		
		log.debug("Entering 'verificarEjecucionReporte' method");
		log.debug("Formato......... : "+this.getFormatoExportacion());	
		this.ejecucionReporteJasper = true;
		this.ejecucionReporteNoJasper = false;
		this.ejecucionReporteMail = false;
		if(!this.validarReporte()){
			this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
			return ;
		}
		this.mostrarLogPantalla = false;
		this.viewListaEnviadosMail = false;
		
		this.setReporteEnEjecucion(true);
		this.setKeyMensajeAlertaDefault("reporte.enEjecucion");	
		try{
			if(this.mPantallaPrincipalBean.getListaEjecucionReportes() != null && 
						this.mPantallaPrincipalBean.getListaEjecucionReportes().size()>0 &&
						this.mPantallaPrincipalBean.getListaEjecucionReportes().contains(this.mPantallaPrincipalBean.getCurrentMenu())){							
				this.setReporteEnEjecucion(true);	
				this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
				return ;
			}else{		
				this.outfileMedia = "";
				this.setReporteEnEjecucion(false);
				this.formReporte.setFormatoExportacion(this.getFormatoExportacion());
				String formatoExportacion = this.getFormatoExportacion();
				if (formatoExportacion.equals("PDF")) {
					this.getRequestContext().execute("PF('confirmationDialogGenerarReportePDF').show()");
					return;
				}
				else {
					this.getRequestContext().execute("PF('confirmationDialogGenerarReporte').show()");
					return;
				}
					
			}
		}catch(Exception e){
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	
   /**
    * @param actionEvent
   */
    public void verificarEjecucionReporteNoJASPER(ActionEvent actionEvent){
		
		log.debug("Entering 'verificarEjecucionReporteNoJASPER' method");
		log.debug("Formato......... : "+this.getFormatoExportacion());	
		this.ejecucionReporteJasper = false;
		this.ejecucionReporteNoJasper = true;
		this.ejecucionReporteMail = false;
		if(!this.validarReporte()){
			this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
			return ;
		}
		this.mostrarLogPantalla = false;
		this.viewListaEnviadosMail = false;
		
		this.setReporteEnEjecucion(true);
		this.setKeyMensajeAlertaDefault("reporte.enEjecucion");	
		try{
			if(this.mPantallaPrincipalBean.getListaEjecucionReportes() != null && 
						this.mPantallaPrincipalBean.getListaEjecucionReportes().size()>0 &&
						this.mPantallaPrincipalBean.getListaEjecucionReportes().contains(this.mPantallaPrincipalBean.getCurrentMenu())){							
				this.setReporteEnEjecucion(true);	
				this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
				return ;
			}else{				
				this.outfileMedia = "";
				this.setReporteEnEjecucion(false);
				this.formReporte.setFormatoExportacion(this.getFormatoExportacion());
				this.getRequestContext().execute("PF('confirmationDialogGenerarReporteNoJASPER').show()");
				return;
				
			}
		}catch(Exception e){
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
    
   /**
   * @param actionEvent
   */
   public void verificarEjecucionReporteMail(ActionEvent actionEvent){
		
		log.debug("Entering 'verificarEjecucionReporteMail' method");
		log.debug("Formato......... : "+this.getFormatoExportacion());	
		this.mostrarLogPantalla = false;
		this.viewListaEnviadosMail = false;
		this.ejecucionReporteJasper = false;
		this.ejecucionReporteNoJasper = false;
		this.ejecucionReporteMail = true;
		if(!this.validarReporte()){
			this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
			return ;
		}
		
		
		this.setReporteEnEjecucion(true);
		this.setKeyMensajeAlertaDefault("reporte.enEjecucion");	
		try{
			if(this.mPantallaPrincipalBean.getListaEjecucionReportes() != null && 
						this.mPantallaPrincipalBean.getListaEjecucionReportes().size()>0 &&
						this.mPantallaPrincipalBean.getListaEjecucionReportes().contains(this.mPantallaPrincipalBean.getCurrentMenu())){							
				this.setReporteEnEjecucion(true);	
				this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
				return ;
			}else{				
				this.outfileMedia = "";
				this.setReporteEnEjecucion(false);
				this.formReporte.setFormatoExportacion(this.getFormatoExportacion());
				this.getRequestContext().execute("PF('confirmDialogGrabarReporte').show()");
				return;
					
			}
		}catch(Exception e){
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
   /**
    * @param actionEvent
    */
    public void verificarEjecucionReporteMultiMail(ActionEvent actionEvent){
 		
 		log.debug("Entering 'verificarEjecucionReporteMultiMail' method");
 		log.debug("Formato......... : "+this.getFormatoExportacion());	
 		this.mostrarLogPantalla = false;
 		this.viewListaEnviadosMail = false;
 		this.ejecucionReporteJasper = false;
		this.ejecucionReporteNoJasper = false;
		this.ejecucionReporteMail = true;
 		if(!this.validarReporte()){
 			this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
 			return ;
 		}
 		
 		this.setReporteEnEjecucion(true);
 		this.setKeyMensajeAlertaDefault("reporte.enEjecucion");	
 		try{
 			if(this.mPantallaPrincipalBean.getListaEjecucionReportes() != null && 
 						this.mPantallaPrincipalBean.getListaEjecucionReportes().size()>0 &&
 						this.mPantallaPrincipalBean.getListaEjecucionReportes().contains(this.mPantallaPrincipalBean.getCurrentMenu())){							
 				this.setReporteEnEjecucion(true);	
 				this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
 				return ;
 			}else{				
 				this.outfileMedia = "";
 				this.setReporteEnEjecucion(false);
 				this.formReporte.setFormatoExportacion(this.getFormatoExportacion());
 				this.getRequestContext().execute("PF('confirmationDialogGrabarReporte').show()");
 				return;
 					
 			}
 		}catch(Exception e){
 			this.addError("Error: ", this.obtieneMensajeErrorException(e));
 		}
 	}
   

	/**
	 * Metodo principal que ejecuta el Reporte mediante un ActionEvent.
	 */
	public void executeReporte(ActionEvent actionEvent) {
		this.executeReporte();
		return;
	}
	
	/**
	 * Metodo principal que ejecuta el Reporte mediante un Action.
	 * @return
	 */
	public String executeReporte(){
		log.debug("Ingreso executeReporte");
		
		this.ejecutarReporteJASPER = true;
		this.visualizarReporte = true;
		this.mostrarLogPantalla = false;
		this.formReporte.setEnvioEmail(false);
		this.formReporte.setFormatoExportacion(this.formatoExportacion);
		this.ejecucionReporte();
		
		log.debug("FIN executeReporte");
		return null;
	}
	
	/**
	 * Metodo principal que ejecuta el Reporte mediante un ActionEvent (Reportes NO JASPER)
	 */
	public void executeReporteNoJASPER(ActionEvent actionEvent) {
		this.executeReporteNoJASPER();
		return;
	}
	
	/**
	 * Metodo principal que ejecuta el Reporte mediante un Action (Reportes NO JASPER)
	 * @return
	 */
	public String executeReporteNoJASPER(){
		log.debug("Ingreso executeReporteNoJASPER");
		this.ejecutarReporteJASPER = false;
		this.visualizarReporte = true;
		this.mostrarLogPantalla = false;
		this.formReporte.setEnvioEmail(false);
		this.formReporte.setFormatoExportacion(this.formatoExportacion);
		this.ejecucionReporte();
		
		log.debug("FIN executeReporteNoJASPER");
		return null;
	}
	
	
	
	
	/**
	 * @param actionEvent
	 */
	public void grabarReporte(ActionEvent actionEvent){
		this.grabarReporte();
		return;
	}
	
	/**
	 * 
	 */
	public void grabarReporte(){
		log.debug("Ingreso grabarReporte");
		this.ejecutarReporteJASPER = true;
		this.visualizarReporte = false;
		this.formReporte.setEnvioEmail(true);
		this.mostrarLogPantalla = true;
		this.formReporte.setFormatoExportacion(this.formatoExportacion);
		this.beforeGrabarReporte();
		
		this.ejecucionReporte();
		this.afterGrabarReporte();
		log.debug("FIN grabarReporte");
	}
	
	
	/**
	 * Metodo que se ejecuta antes de la ejecucion Principal de GrabarReporte. 
	 * Dicho metodo puede ser sobreescrito 
	 */
	protected void beforeGrabarReporte() {		
		if(log.isDebugEnabled()){
			log.debug("beforeGrabarReporte");
		}
		
	}
	
	/**
	 * Metodo que se ejecuta despues de la ejecucion Principal de GrabarReporte. 
	 * Dicho metodo puede ser sobreescrito 
	 */
	protected void afterGrabarReporte() {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'afterGrabarReporte' method");
		}
		
	}
	
	/**
	 * Lógica PRINCIPAL de proceso de ejecución del Reporte generados por JASPER.
	 */
	public void ejecucionReporte(){
		if (log.isWarnEnabled()) {
			log.warn("Entering 'executeReporte' method");
		}
		String ejecucionReporteError = Constants.NO;
		try {
			this.mPantallaPrincipalBean.getListaEjecucionReportes().add(this.mPantallaPrincipalBean.getCurrentMenu());			
			this.setReporteEnEjecucion(true);
			
			log.debug("En ejecucion.... : "+this.isReporteEnEjecucion());
			
			Map<String, Object> parameterAdicionales = new HashMap<String, Object>();
			
			if(!this.formReporte.isEnvioEmail()) {
				parameterAdicionales = this.inicializarMapForm(this.formReporte);	
			    parameterAdicionales = this.prepareParameterMap(parameterAdicionales);
			    parameterAdicionales = this.prepareParamsBeforeExecute(parameterAdicionales, this.formReporte);
			    String idiomaReporte = (String)parameterAdicionales.get("idiomaReporte");
				String paisReporte = (String)parameterAdicionales.get("paisReporte");
				if (StringUtils.isNotBlank(idiomaReporte) && StringUtils.isNotBlank(paisReporte) ) {
					Locale locale = new Locale(idiomaReporte, paisReporte);
					parameterAdicionales.put(JRParameter.REPORT_LOCALE, locale);
				}
			}
			/* Valores seteados por reporte invocado */		
			this.formReporte.setNombreReporte(this.devuelveNombreReporte());
			this.formReporte.setNombreSubReporte(this.devuelveNombreSubReporte());
			
			this.nombreReporteJRXMLEjecutar = this.formReporte.getNombreReporte();
			if (StringUtils.isNotBlank(this.formReporte.getNombreSubReporte()))
				this.nombreReporteJRXMLEjecutar = this.formReporte.getNombreSubReporte();
			
			this.formReporte.setReporteFileName(this.nombreReporteJRXMLEjecutar);
			if (StringUtils.isBlank(this.nombreReporteJRXMLEjecutar)) {
				this.nombreReporteJRXMLEjecutar = "NO_JASPER - Formato " + this.formatoExportacion;
			}
			
			String idReporteService = this.devuelveBeanReporteService();
			if (StringUtils.isNotBlank(idReporteService)) {
				BaseReporteInterface reporteService = (BaseReporteInterface) this.getBean(idReporteService);
				this.formReporte.setReporteService(reporteService);
				this.formReporte.setBeforeExecuteReporte(true);
			}	
			
			if (this.formReporte.isEnvioEmail()) {
				String idReporteMailService = this.devuelveBeanMailService();
				this.formReporte.setMailService(idReporteMailService);	
				if (StringUtils.isNotBlank(idReporteMailService)) {
					BaseMailService reporteMailService = (BaseMailService) this.getBean(idReporteMailService);
					this.formReporte.setReporteMailService(reporteMailService);				
				}	
				else {
					this.addError("Error: ", "No se encontro Service asociado al Mail");
					return;
				}
			}
			this.procesarReporte(parameterAdicionales);
	
		}
 		catch (Exception e) {
			ejecucionReporteError = Constants.SI;
			String error = this.obtieneMensajeErrorException(e);
			if (StringUtils.equals(PDF_EXTENSION, this.getExtensionReporte()) && this.viewPDFMedia) {
				this.addError("Error: ", error);
			}
			else {
				this.mPantallaPrincipalBean.setMensajeErrorSistema(error);
				try {
					this.redireccionarPagina("_reporteConError");
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				
			}
		}
		finally {
			this.mPantallaPrincipalBean.getListaEjecucionReportes().remove(this.mPantallaPrincipalBean.getCurrentMenu());			
			this.setReporteEnEjecucion(false);
			this.finHistoricoReporte(ejecucionReporteError);
	
		}
		
		this.ingresarListaReportesEmitidos();
		if (log.isWarnEnabled()) {
			log.warn("Finish 'executeReporte' method");
		}
			
	}
	
	
	
	/**
	 * Metodo que invoca a los servicios de de ejecución del reporte. 
	 */
	public void procesarReporte(Map<String, Object> parameterAdicionales) throws Exception {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'procesarReporte' method");
		}
		ReporteExecutionService reporteExecutionService = (ReporteExecutionService)this.getBeanService("reportes.reporteExecutionService");
		Map<String, Object> result = null;			
		ReporteResult reporteResult = null;
		ReporteParams reporteParams = null;
		this.listaReportesMail = new ArrayList<ReporteEnviadoMail>();
			
		
		/* Obtenemos constructor service*/	
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		BaseReporteInterface service = (BaseReporteInterface)this.getBeanService(this.formReporte.getConstructorService());
		
		this.currentHistoricoReporte = new HistoricoReporte();
		this.iniReporteHistorico();
				
		// Ejecución de reporte si es enviado via e-mail	
		if(this.formReporte.isEnvioEmail()){
			
			this.currentHistoricoReporte.setEnvioMail(Constants.SI);				
			int contadorGenerarMultipleReporte = 1;
			this.viewListaEnviadosMail = true;
			
			/* Obteniendo nro de reportes a generar */
			contadorGenerarMultipleReporte = this.getNroReportesAGenerar();
			this.log.debug("contadorGenerarMultipleReporte "+contadorGenerarMultipleReporte);
			if (contadorGenerarMultipleReporte < 0) {					
				this.addWarn("Warning: ", this.getResourceMessage(this.errorKeyGenerarMultipleReporte()));							
				return;				
			}
			
			/* Grabando en Auditoria de Reportes */
			if (contadorGenerarMultipleReporte > 1) {
				this.currentHistoricoReporte.setMultiReporte(Constants.SI);
				this.currentHistoricoReporte.setNumeroMultireportes((long)this.listaReportesMail.size());
			}
			else {
				this.currentHistoricoReporte.setMultiReporte(Constants.NO);
				this.currentHistoricoReporte.setNumeroMultireportes(new Long(1));
			}
			reporteService.insertHistoricoReporte(this.currentHistoricoReporte);
			
			/* Antes de la Ejecucion del Reporte */
			service.beforeGrabarReporte(this.formReporte);	
			
			/* Generando los reportes respectivos en el disco */
			for(int i=0; i < contadorGenerarMultipleReporte; i++){						
				
				this.nroReporteProcesando = i + 1;
				service.beforeIniBucleGrabarReporte(this.formReporte);					
				this.beforeIniBucleGrabarReporte();
				this.currentReporteEnviadoMail = new ReporteEnviadoMail();
				parameterAdicionales = this.inicializarMapForm(this.formReporte);
			    parameterAdicionales = this.prepareParameterMap(parameterAdicionales);
			    parameterAdicionales = this.prepareParamsBeforeExecute(parameterAdicionales, this.formReporte);
			    String idiomaReporte = (String)parameterAdicionales.get("idiomaReporte");
				String paisReporte = (String)parameterAdicionales.get("paisReporte");
				if (StringUtils.isNotBlank(idiomaReporte) && StringUtils.isNotBlank(paisReporte) ) {
					Locale locale = new Locale(idiomaReporte, paisReporte);
					parameterAdicionales.put(JRParameter.REPORT_LOCALE, locale);
				}
				this.formReporte.setNombreReporte(this.devuelveNombreReporte());
				this.formReporte.setNombreSubReporte(this.devuelveNombreSubReporte());
				
				this.nombreReporteJRXMLEjecutar = this.formReporte.getNombreReporte();
				if (StringUtils.isNotBlank(this.formReporte.getNombreSubReporte()))
					this.nombreReporteJRXMLEjecutar = this.formReporte.getNombreSubReporte();
				
				this.formReporte.setReporteFileName(this.nombreReporteJRXMLEjecutar);
				if (StringUtils.isBlank(this.nombreReporteJRXMLEjecutar)) {
					this.nombreReporteJRXMLEjecutar = "NO_JASPER - Formato " + this.formatoExportacion;
				}
				
				//Obtener el Servicio de Ejecución del reporte.
				if (this.ejecutarReporteJASPER) {
					reporteParams = reporteExecutionService.executePrevio(this.formReporte, parameterAdicionales);
					this.configReporteParams(reporteParams);
					
					if(StringUtils.equals(this.formatoExportacion, "ZIP")){						
						reporteResult = reporteExecutionService.executeReporteMultiple(reporteParams);
					}
					else {
						result = reporteExecutionService.generarReporte(this.formReporte, reporteParams);
						reporteResult = (ReporteResult)result.get("reporteResult");
						reporteParams = (ReporteParams)result.get("reporteParams");	
					}
					this.exportarReporte(reporteResult, reporteParams);							
				}
				else {
					reporteParams = reporteExecutionService.executePrevioNoJASPER(this.formReporte, parameterAdicionales);
					this.configReporteParams(reporteParams);
					result = reporteExecutionService.generarReporteNoJASPER(this.formReporte, reporteParams);
					reporteParams = (ReporteParams)result.get("reporteParams");	
					this.exportarReporteNoJASPER(reporteResult, reporteParams);
				}		
				
				/* Envio por Correo */
				this.enviarReporteViaEmail(reporteParams, this.formReporte);		
				
			}				
			
			service.afterGrabarReporte(this.formReporte);
			this.viewListaEnviadosMail = true;
	
		
		}
		else { //Ejecución de reporte si no es enviado vía mail	
			reporteService.insertHistoricoReporte(this.currentHistoricoReporte);
			
			
			if (this.ejecutarReporteJASPER) {
				reporteParams = reporteExecutionService.executePrevio(this.formReporte, parameterAdicionales);
				this.configReporteParams(reporteParams);
				if(StringUtils.equals(this.formatoExportacion, "ZIP")){						
					reporteResult = reporteExecutionService.executeReporteMultiple(reporteParams);
				}
				else {
					result = reporteExecutionService.generarReporte(this.formReporte, reporteParams);
					reporteResult = (ReporteResult)result.get("reporteResult");
					reporteParams = (ReporteParams)result.get("reporteParams");	
				}
				this.exportarReporte(reporteResult, reporteParams);
				this.copiarReporteServidor(reporteParams, this.formReporte);					
			}	
			else {
				reporteParams = reporteExecutionService.executePrevioNoJASPER(this.formReporte, parameterAdicionales);
				this.configReporteParams(reporteParams);
				result = reporteExecutionService.generarReporteNoJASPER(this.formReporte, reporteParams);
				reporteParams = (ReporteParams)result.get("reporteParams");	
				this.exportarReporteNoJASPER(reporteResult, reporteParams);
			}
				
			
		}			
		
		if (log.isWarnEnabled()) {
			log.warn("Finish 'procesarReporte' method");
		}
		
	}
	
	
	/**
	 * Metodo que se ejecuta antes de la ejecucion de reportes multiples. 
	 * Dicho metodo puede ser sobreescrito
	 */
	public void beforeIniBucleGrabarReporte() {
		
	}
	
	
	/**
	 * @param reporteParams
	 */
	protected void configReporteParams(ReporteParams reporteParams) {
		Map params = reporteParams.getQueryParams();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String idiomaReporte = pais.getCodigoIdiomaIso();
		String paisReporte = pais.getCodigoPaisIso();
		Locale locale = new Locale(idiomaReporte,paisReporte);
		params.put(JRParameter.REPORT_LOCALE, locale);
	}

	

	/**
	 * @return
	 */
	protected String exitoKeyEnvioReporteViaMail() {
		return "reporteINCGenerarReporteIncentivos.msg.envioMailCronograma";						
	}
	
	
	/**
	 * @param reporteParams
	 */
	protected void exportarReporteNoJASPER(ReporteResult reporteResult, ReporteParams reporteParams) throws Exception {
		  
		this.formatoExportacion = this.formReporte.getFormatoExportacion();
			
		// variables del virtualizador
		HttpServletRequest request = this.getRequest();
		Map parameterMap = reporteParams.getQueryParams();
		
		String archivo = (String) parameterMap.get("nombreArchivo");
	    String extension = (String) parameterMap.get("extensionArchivo");
	    String directorio = (String) parameterMap.get("directorioArchivo");
	    
		String filePath = directorio + archivo + extension; 
		this.log.debug("Project Path " + filePath);	
		this.outfile = filePath;
		this.formReporte.setOutFile(this.outfile);
		this.codigoReporteAuditoria = archivo + extension;
							
		if (StringUtils.equals(CSV_EXTENSION, extension)) {
			this.exportarReporteArchivoPlanoNoJASPER(reporteParams);			 
		} else if (StringUtils.equals(XLSX_EXTENSION, extension)) {
			this.exportarReporteXLSXNoJASPER(reporteParams);	 
		} else if(StringUtils.equals(TXT_EXTENSION, extension)) {
			this.exportarReporteArchivoPlanoNoJASPER(reporteParams);
		} else if(StringUtils.equals(ZIP_EXTENSION, extension)) {
			this.exportarReporteZIPNoJASPER(reporteResult, reporteParams);
		} 
		else{
			this.exportarReporteArchivoPlanoNoJASPER(reporteParams);
		}
		
		return;
	}
	
	/**
	 * Metodo que exporta reportes con formato CSV / TXT para Reportes NO JASPER
	 * @param reporteParams
	 */
	public void exportarReporteArchivoPlanoNoJASPER(ReporteParams reporteParams) throws Exception {
		if(this.formReporte.isEnvioEmail()) return;
		
		HttpServletResponse response = this.getResponse();
		
		Map parameterMap = reporteParams.getQueryParams();
		
		String archivo = (String) parameterMap.get("nombreArchivo");
	    String extension = (String) parameterMap.get("extensionArchivo");
	    String directorio = (String) parameterMap.get("directorioArchivo");
	    
		String filePath = directorio + archivo + extension; 
		String archivoFinal = this.getNameFile(filePath);
		String contentType = "text/plain";
		
		response.reset();
        response.setContentType(contentType);          
         
        response.setHeader("Content-disposition", "attachment; filename=" + archivoFinal) ;
        log.info(" ---> filePath "+filePath + " contentType "+ contentType);
        this.generarResponseOutputStream(filePath);
        
	}

	
	
   /**
	* etodo que exporta reportes con formato XLSX para Reportes NO JASPER
	* @param reporteParams
	*/
   public void exportarReporteXLSXNoJASPER(ReporteParams reporteParams) throws Exception {
	    if(this.formReporte.isEnvioEmail()) return;
	    
		HttpServletResponse response = this.getResponse();
		
		Map parameterMap = reporteParams.getQueryParams();
		
		String archivo = (String) parameterMap.get("nombreArchivo");
	    String extension = (String) parameterMap.get("extensionArchivo");
	    String directorio = (String) parameterMap.get("directorioArchivo");
	    
		String filePath = directorio + archivo + extension; 
		String archivoFinal = this.getNameFile(filePath);
		String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		
		response.reset();
        response.setContentType(contentType);            
        response.setHeader("Content-disposition", "attachment; filename=" + archivoFinal) ;
        log.info(" ---> filePath "+filePath + " contentType "+ contentType);
        
        this.generarResponseOutputStream(filePath);
            
	 
	}
	
	
	/**
     * @param path
     * @return
     */
    protected String getNameFile(String path) {
    	String [] temp = null;
    	try {
    		temp = path.split(Constants.EDU_FILE_SEPARATOR);    	
        	return temp[temp.length-1];
		} catch (Exception e) {
		}	
    	return path;
    }    
	
	/**
	 * Metodo que verifica el formato del reporte a exportar.
	 * @param reporteResult
	 */
	public void exportarReporte(ReporteResult reporteResult, ReporteParams reporteParams) throws Exception {
		this.formReporte.setFormatoExportacion(this.formatoExportacion)	;	
		
		// variables del virtualizador
		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + "/resources/";
		
		this.outfile = path;
		
	    this.log.debug("Entrando a EXPORTAR WLOGIC");	
		if (StringUtils.equals(PDF_EXTENSION, this.getExtensionReporte())) {			
			this.exportarReportePDF(reporteResult);			
		} else if (StringUtils.equals(CSV_EXTENSION, this.getExtensionReporte())) {
			this.exportarReporteCSV(reporteResult);			 
		} else if (StringUtils.equals(XLS_EXTENSION, this.getExtensionReporte())) {
			if (this.mostrarExcelXLSX) {
				this.exportarReporteXLSX(reporteResult);
			}
			else {
			  this.exportarReporteXLS(reporteResult);	
			}
				
		} else if (StringUtils.equals(XLSX_EXTENSION, this.getExtensionReporte())) {
			this.exportarReporteXLSX(reporteResult);		 
		} else if(StringUtils.equals(TXT_EXTENSION, this.getExtensionReporte())) {
			this.exportarReporteTXT(reporteResult);			
		} else if(StringUtils.equals(ZIP_EXTENSION, this.getExtensionReporte())) {
			this.exportarReporteZIP(reporteResult, reporteParams);			
		}  
		
		else{
			this.exportarReportePDF(reporteResult);			
		}
	
	}
	
	
	
	/**
	 * Metodo que exporta reportes con formato PDF.
	 * @param reporteResult
	 */
	public void exportarReportePDF(ReporteResult reporteResult) throws Exception {
		
		this.log.debug("Entering 'BaseReporteAbstractAction.exportarReportePDF' method");
		
		/** PDF **/		
		this.outfile += this.mPantallaPrincipalBean.getCurrentUser().getLogin()+"_";
		if (StringUtils.isNotBlank(this.formReporte.getNombreSubReporte())) {
			this.outfile += this.formReporte.getNombreSubReporte();
			this.codigoReporteAuditoria = this.formReporte.getNombreSubReporte();
		}
		else {
			this.outfile += this.formReporte.getNombreReporte();
			this.codigoReporteAuditoria = this.formReporte.getNombreReporte();
		}
		this.outfile += PDF_EXTENSION; 
					
		this.log.debug("Virtualizado en disco  " + this.outfile);				
		JasperExportManager.exportReportToPdfFile(reporteResult.getJasperPrint(), this.outfile);
		this.formReporte.setOutFile(this.outfile);
		
		//this.formReporte.setReporteFileName(this.formReporte.getNombreSubReporte());
		if(this.formReporte.isEnvioEmail()) {
			this.outfile = "/resources/"+
					this.mPantallaPrincipalBean.getCurrentUser().getLogin()+"_" + this.codigoReporteAuditoria + PDF_EXTENSION;
			this.outfileMedia = this.outfile;
			return;
		}
		
		if (this.viewPDFMedia) {
			if(this.mPantallaPrincipalBean.isViewPDFMedia()){
				this.outfile = "/resources/"+
						this.mPantallaPrincipalBean.getCurrentUser().getLogin()+"_" + this.codigoReporteAuditoria + PDF_EXTENSION;
				this.outfileMedia = this.outfile;
				this.viewReporteMedia = true;
							
			}else{
				HttpServletResponse response = this.getResponse();
				byte[] bytes = JasperExportManager.exportReportToPdf(reporteResult.getJasperPrint());

				response.reset();
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "inline");
				response.setContentLength(bytes.length);			
				response.getOutputStream().write(bytes);
				response.getOutputStream().flush();
				response.getOutputStream().close();			
				this.responseComplete();			
			}
		}
		else {
			HttpServletResponse response = this.getResponse();
			byte[] bytes = JasperExportManager.exportReportToPdf(reporteResult.getJasperPrint());

			response.reset();
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "inline");
			response.setContentLength(bytes.length);			
			response.getOutputStream().write(bytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();			
			this.responseComplete();
		}
			
		
	}
	
	
	/**
	 * Metodo que exporta reportes con formato CSV.
	 * @param reporteResult
	 */
	public void exportarReporteCSV(ReporteResult reporteResult) throws Exception {
		/** CSV **/
		
		HttpServletResponse response = this.getResponse();
		byte[] output;
		
	
		this.outfile += this.mPantallaPrincipalBean.getCurrentUser().getLogin()+"_";
		this.outfile += this.formReporte.getNombreReporte();
		this.outfile += CSV_EXTENSION;
		
		this.formReporte.setOutFile(this.outfile);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		JRCsvExporter csvExporter = new JRCsvExporter();
		csvExporter.setParameter(JRExporterParameter.JASPER_PRINT, reporteResult.getJasperPrint());			
		csvExporter.setParameter(JRCsvExporterParameter.OUTPUT_STREAM, baos);
		csvExporter.exportReport();
		
		output = baos.toByteArray();
			
		response.reset();
        response.setContentType("text/plain");            
        response.addHeader("Content-Disposition", "attachment; filename="+this.formReporte.getNombreReporte()+CSV_EXTENSION);
        response.setContentLength(output.length);			
        response.getOutputStream().write(output);
        response.getOutputStream().flush();
        response.getOutputStream().close();		
		
        
		this.log.debug("Virtualizado en disco  " + this.outfile);
		
		JRCsvExporter exporter = new JRCsvExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, reporteResult.getJasperPrint());
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, this.outfile);    		
		exporter.exportReport();
		
		this.formReporte.setOutFile(this.outfile);
		//this.formReporte.setReporteFileName(this.formReporte.getNombreReporte());
		this.codigoReporteAuditoria = this.formReporte.getNombreReporte();
        this.responseComplete();

		
	}
	
	public void exportarReporteXLSinDescargaNavegador(ReporteResult reporteResult) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("exportarReporteXLSinDescargaNavegador");
		}
		
		JRXlsExporter xlsExporter = new JRXlsExporter();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		FileOutputStream fos = null;
		
		this.log.info("Cantidad Paginas XLS: " + String.valueOf(reporteResult.getJasperPrint().getPages().size()));
		this.outfile += this.mPantallaPrincipalBean.getCurrentUser().getLogin()+"_";
		this.outfile += this.formReporte.getNombreReporte();
		this.outfile += XLS_EXTENSION;			
		this.formReporte.setOutFile(this.outfile);
		
		//this.formReporte.setReporteFileName(this.formReporte.getNombreReporte());
		this.codigoReporteAuditoria = this.formReporte.getNombreReporte();			
		xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT,reporteResult.getJasperPrint());			
		xlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.TRUE);
		xlsExporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER,Boolean.TRUE);
		xlsExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);			
		if (generateTabsXLS) {
			xlsExporter.setParameter(JRXlsExporterParameter.MAXIMUM_ROWS_PER_SHEET,MAXIMUM_ROWS_PER_SHEET_XLS);
		}			
		xlsExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);							 
		xlsExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,Boolean.TRUE);
		xlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,	baos);						
		String[] sheetNames = {"Reporte"};			
		xlsExporter.setParameter(JRXlsExporterParameter.SHEET_NAMES, sheetNames );
		xlsExporter.exportReport();
		this.log.debug("Virtualizado en disco  " + this.outfile);
		fos = new FileOutputStream (new File(this.outfile)); 
		baos.writeTo(fos);
		fos.flush();
		fos.close();
		
		log.debug("metodo finalizado...");			
		
	}
	
	/**
	 * Metodo que exporta reportes con formato XLS.
	 * @param reporteResult
	 */
	public void exportarReporteXLS(ReporteResult reporteResult) throws Exception {
		/**XLS**/ 
		JRXlsExporter xlsExporter = new JRXlsExporter();
		//JExcelApiExporter xlsExporter = new JExcelApiExporter();
		HttpServletResponse response = this.getResponse();
		
		byte[] output;
		
		this.log.info("Cantidad Paginas XLS: " + String.valueOf(reporteResult.getJasperPrint().getPages().size()));
		this.outfile += this.mPantallaPrincipalBean.getCurrentUser().getLogin()+"_";
		this.outfile += this.formReporte.getNombreReporte();
		this.outfile += XLS_EXTENSION;
		
		this.log.info("Cantidad Paginas XLS: " + String.valueOf(reporteResult.getJasperPrint().getPages().size()));
		this.outfile += this.mPantallaPrincipalBean.getCurrentUser().getLogin()+"_";
		this.outfile += this.formReporte.getNombreReporte();
		this.outfile += XLS_EXTENSION;
		String[] sheetNames = {"Reporte"};
		
		
		if (!this.formReporte.isEnvioEmail()) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT,reporteResult.getJasperPrint());
			xlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.TRUE);
			if (this.ignorarCeldaBorder)
				xlsExporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER,Boolean.TRUE);
			xlsExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
			
			if (generateTabsXLS) {
				xlsExporter.setParameter(JRXlsExporterParameter.MAXIMUM_ROWS_PER_SHEET,MAXIMUM_ROWS_PER_SHEET_XLS);
			}
			
			xlsExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);							 
			xlsExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			xlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,	baos);
			xlsExporter.setParameter(JRXlsExporterParameter.SHEET_NAMES, sheetNames );
			xlsExporter.exportReport();
					
			output = baos.toByteArray();
				
			response.reset();
			response.setContentType("application/vnd.ms-excel");			
			response.addHeader("Content-Disposition", "attachment; filename="+
					this.mPantallaPrincipalBean.getCurrentUser().getLogin()+"_"+this.formReporte.getNombreReporte()+XLS_EXTENSION);		
			response.setContentLength(output.length);
			response.getOutputStream().write(output);
	        
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
		
		
		
		this.log.debug("Virtualizado en disco  " + this.outfile);
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT,reporteResult.getJasperPrint());
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.TRUE);
		if (this.ignorarCeldaBorder)
			exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER,Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
		
		if (generateTabsXLS) {
			exporter.setParameter(JRXlsExporterParameter.MAXIMUM_ROWS_PER_SHEET,MAXIMUM_ROWS_PER_SHEET_XLS);
		}
		
		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);	
		exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, this.outfile );			
		exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES, sheetNames );
		exporter.exportReport();
		
		this.formReporte.setOutFile(this.outfile);
		//this.formReporte.setReporteFileName(this.formReporte.getNombreReporte());
		this.codigoReporteAuditoria = this.formReporte.getNombreReporte();
		
		if (!this.formReporte.isEnvioEmail()) {
			this.responseComplete();
		}
		
	}
	
	
	
	/**
	 * Metodo que exporta reportes con formato XLSX.
	 * @param reporteResult
	 */
	public void exportarReporteXLSX(ReporteResult reporteResult) throws Exception {
		/**XLSX**/
		JRXlsxExporter xlsxExporter = new JRXlsxExporter();				
		HttpServletResponse response = this.getResponse();
		
		byte[] output;

		this.log.info(String.valueOf("Cantidad Paginas XLSX: " + reporteResult.getJasperPrint().getPages().size()));
		this.outfile += this.mPantallaPrincipalBean.getCurrentUser().getLogin()+"_";
		this.outfile += this.formReporte.getNombreReporte();
		this.outfile += XLSX_EXTENSION;
		this.formReporte.setOutFile(this.outfile);
		String[] sheetNames = {"Reporte"};		
		
		if (!this.formReporte.isEnvioEmail()) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			xlsxExporter.setParameter(JRExporterParameter.JASPER_PRINT,reporteResult.getJasperPrint());
			xlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.TRUE);
			if (this.ignorarCeldaBorder)
				xlsxExporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER,Boolean.TRUE);
			xlsxExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
			
			if (generateTabsXLS) {
				xlsxExporter.setParameter(JRXlsExporterParameter.MAXIMUM_ROWS_PER_SHEET,MAXIMUM_ROWS_PER_SHEET_XLS);
			}
			
			xlsxExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);							 
			xlsxExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,Boolean.TRUE);
			xlsxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,	baos);
			xlsxExporter.setParameter(JRXlsExporterParameter.SHEET_NAMES, sheetNames );
			xlsxExporter.exportReport();
			
			
			output = baos.toByteArray();
			this.formReporte.setOutFile(this.outfile);
			//this.formReporte.setReporteFileName(this.formReporte.getNombreReporte());
			this.codigoReporteAuditoria = this.formReporte.getNombreReporte();
						
					
			response.reset();
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");			
			response.addHeader("Content-Disposition", "attachment; filename="+
					this.mPantallaPrincipalBean.getCurrentUser().getLogin()+"_"+this.formReporte.getNombreReporte()+XLSX_EXTENSION);		
			response.setContentLength(output.length);
			response.getOutputStream().write(output);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
		
		this.log.debug("Virtualizado en disco  " + this.outfile);
		JRXlsxExporter exporter = new JRXlsxExporter();
		
		exporter.setParameter(JRExporterParameter.JASPER_PRINT,reporteResult.getJasperPrint());
		
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.TRUE);
		if (this.ignorarCeldaBorder)
			exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER,Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
		
		if (generateTabsXLS) {
			exporter.setParameter(JRXlsExporterParameter.MAXIMUM_ROWS_PER_SHEET,MAXIMUM_ROWS_PER_SHEET_XLS);
		}
		
		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);							 
		exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,Boolean.TRUE);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, this.outfile );
				
		exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES, sheetNames );
		exporter.exportReport();
		
		if (!this.formReporte.isEnvioEmail()) {
			this.responseComplete();
		}
	
	}
	
	/**
	 * Metodo que exporta reportes con formato TXT.
	 * @param reporteResult
	 */
	public void exportarReporteTXT(ReporteResult reporteResult) throws Exception{
		
		HttpServletResponse response = this.getResponse();
		byte[] output;
	
		this.outfile += TXT_EXTENSION;
		this.formReporte.setOutFile(this.outfile);
		
		this.log.debug("Virtualizado en disco  " + this.outfile);
		
		JRTextExporter txtExporter = new JRTextExporter(); 
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		txtExporter.setParameter(JRExporterParameter.JASPER_PRINT, reporteResult.getJasperPrint());
		txtExporter.setParameter(JRCsvExporterParameter.OUTPUT_STREAM, baos);
		txtExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, this.outfile);
		txtExporter.exportReport();

		output = baos.toByteArray();
				
		response.reset();
        response.setContentType("text/plain");  
        response.setHeader("Content-Disposition", "inline");
        response.setContentLength(output.length);			
        response.getOutputStream().write(output);
        response.getOutputStream().flush();
        response.getOutputStream().close();		
		
        this.responseComplete();
		
	}
	
	/**
	 * Metodo que exporta reportes con formato HTML.
	 * @param reporteResult
	 */
	public void exportarReporteHTML(ReporteResult reporteResult) throws Exception  {
		/**HTML**/
		JRHtmlExporter htmlExporter = new JRHtmlExporter();
		HttpServletResponse response = this.getResponse();
		HttpServletRequest request = this.getRequest();
		
		PrintWriter printWriter;
				
		printWriter = response.getWriter();
		response.setContentType("text/html");
		request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, reporteResult.getJasperPrint());
		
		htmlExporter.setParameter(JRExporterParameter.JASPER_PRINT, reporteResult.getJasperPrint());
		htmlExporter.setParameter(JRExporterParameter.OUTPUT_WRITER, printWriter);
		htmlExporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "image?image=");
		htmlExporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR, Boolean.FALSE);
		htmlExporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		htmlExporter.exportReport();
		
		this.responseComplete();
		
	}
	
	/**
	 * Metodo que exporta reportes con formato ZIP.
	 * @param reporteResult
	 * @param reporteParams
	 */
	public void exportarReporteZIP(ReporteResult reporteResult, ReporteParams reporteParams) throws Exception {
		
		/** ZIP **/
		
		HttpServletResponse response = this.getResponse();
		
		//Empaquetamos todos los reportes generados			
	
		if(this.log.isDebugEnabled())
			this.log.debug("Comprimiento reportes ...");
		
		Map queryParams = reporteParams.getQueryParams();
		Map<String, Object> parameterMap = (Map<String, Object>)queryParams.get("parameterMap");
		
		String rutaTemporalReportesGenerados = MapUtils.getString(parameterMap, "rutaTemporalReportesGenerados");
		String rutaArchivoZip = rutaTemporalReportesGenerados.substring(0, rutaTemporalReportesGenerados.length() - 1) + ZIP_EXTENSION;
		
		ZipUtil.zipDir(rutaTemporalReportesGenerados, rutaArchivoZip);

		if(this.log.isDebugEnabled())
			this.log.debug("Comprension finalizada.");
		
		//Ponemos el zip en el flujo
		String nombreArchivoDescargable = MapUtils.getString(parameterMap, "anyo") + "-" + MapUtils.getString(parameterMap, "periodo") + "-" + MapUtils.getString(parameterMap, "nombreProceso") + ZIP_EXTENSION;
		
		response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivoDescargable + "\"");
        
        byte[] buf = new byte[1024];  
          
        String realPath = rutaArchivoZip;  
        File file = new File(realPath);  
        long length = file.length();
        
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));  
        ServletOutputStream out = response.getOutputStream();  
        
        response.setContentLength((int) length);		            
        
        while ((in != null) && ((length = in.read(buf)) != -1)) {  
            out.write(buf, 0, (int) length);  
        }
        
        in.close(); 
        out.flush();
        out.close();
        
    
        //Eliminamos los archivos creados
        File unzip = new File(rutaTemporalReportesGenerados);		            
        if(unzip.exists())
        	FileUtils.deleteDirectory(unzip);
        	
        File zip = new File(rutaArchivoZip);
        if(zip.exists())
        	zip.delete();
        
        this.responseComplete();   
	} 
	
	
	/**
	 * Metodo que exporta reportes con formato ZIP generados en Oracle (Reportes NO Jasper).
	 * @param reporteResult
	 * @param reporteParams
	 */
	public void exportarReporteZIPNoJASPER(ReporteResult reporteResult, ReporteParams reporteParams) throws Exception {
		
		/** ZIP **/
		
		HttpServletResponse response = this.getResponse();
		
		//Empaquetamos todos los reportes generados			
	
		if(this.log.isDebugEnabled())
			this.log.debug("Comprimiento reportes ...");
		
		
		
		Map queryParams = reporteParams.getQueryParams();
		Map<String, Object> parameterMap = (Map<String, Object>)queryParams.get("parameterMap");
		
		String directorio = (String) parameterMap.get("directorioArchivo");
		String nombreArchivoZip = MapUtils.getString(parameterMap, "nombreArchivo", "");
		
		//Ponemos el zip en el flujo
		String nombreArchivoDescargable = directorio + nombreArchivoZip + ZIP_EXTENSION;
		
		response.setContentType("application/force-download");
        //response.addHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivoDescargable + "\"");
        response.addHeader("Content-Disposition", "attachment; filename="+nombreArchivoZip + ZIP_EXTENSION);
        byte[] buf = new byte[1024];  
          
        String realPath = nombreArchivoDescargable;  
        File file = new File(realPath);  
        long length = file.length();
        
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));  
        ServletOutputStream out = response.getOutputStream();  
        
        response.setContentLength((int) length);		            
        
        while ((in != null) && ((length = in.read(buf)) != -1)) {  
            out.write(buf, 0, (int) length);  
        }
        
        in.close(); 
        out.flush();
        out.close();
                
        this.responseComplete();   
	} 
	
	/**
	 * @param reporteParams
	 * @return
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nombreArchivoReporte;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		if (!StringUtils.isBlank(this.prefijoArchivo)) {
			nombreArchivoReporte = this.prefijoArchivo + "_"
				+ sdf.format(new Date(System.currentTimeMillis()));
		} else {
			nombreArchivoReporte = sdf.format(new Date(System.currentTimeMillis()));
		}
		return nombreArchivoReporte;
	}
	
	
	/**
	 * @param request
	 * @throws IOException
	 */
	public void copiarReporteServidor(ReporteParams reporteParams, BaseReporteForm form) throws Exception {
		
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'grabarReporteDisco' method");
		}
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		Map queryParams = reporteParams.getQueryParams();
		
		/* Obteniendo directorio del repositorio a grabar */
		Map criteria = new HashMap();
		criteria.put("codigoPais", form.getPais().getCodigo());		
		criteria.put("nombreReporte",form.getReporteFileName());
		this.log.debug("codigoPais : "+form.getPais().getCodigo()+"		nombreReporte : "+form.getReporteFileName());
		
		Map paramReporte = reporteService.getParametrosReporte(criteria);
				
		/* Generando Reporte en disco */
		if (paramReporte != null) {
			queryParams.putAll(paramReporte);
			this.prefijoArchivo = (String) paramReporte.get("prefijoArchivo");
			String nombreArchivoReporte = this.getNombreArchivoReporte(reporteParams) + 
					                      this.getExtensionReporte(form.getFormatoExportacion());
			String directorioRepositorio = (String) paramReporte.get("directorioRepositorio");
			
		    if (!StringUtils.isBlank(directorioRepositorio)) {
				File srcFile = new File(form.getOutFile());
				File destFile = new File(directorioRepositorio,	nombreArchivoReporte);
				FileUtils.copyFile(srcFile, destFile);
				
				/* En caso el reporte se envie por Correo */
				if(form.isEnvioEmail()){	
					queryParams.put("codigoPais", form.getPais().getCodigo());
					queryParams.put("nombreReporte", form.getReporteFileName());
					queryParams.put("fileAttachment", destFile);
					queryParams.put("nombreArchivoReporte", nombreArchivoReporte);
					reporteParams.setQueryParams(queryParams);
				}
				form.setFileServer(destFile.getAbsolutePath());
				log.debug("Copy file(Exists params) from : "+srcFile.getPath()+" to : "+destFile.getAbsolutePath());
			}
		    
		    String enviarMail = (String) paramReporte.get("enviarCorreo");
		    if (StringUtils.isNotBlank(enviarMail) && Constants.NO.equals(enviarMail)) {
		    	form.setEnvioEmail(false);
		    }
		    
		}
		else {
			
			Map paramReporteGeneral = reporteService.getParametrosReporteGeneral(criteria);
			queryParams.putAll(paramReporteGeneral);
			
			//this.prefijoArchivo = (String) paramReporte.get("prefijoArchivo");
			String nombreArchivoReporte = this.getNombreArchivoReporte(reporteParams) 
					+ this.getExtensionReporte(form.getFormatoExportacion());			
			String directorioRepositorio = (String) paramReporteGeneral.get("directorioRepositorio"); 
													
		    if (!StringUtils.isBlank(directorioRepositorio)) {
				File srcFile = new File(form.getOutFile());
				File destFile = new File(directorioRepositorio,	nombreArchivoReporte);
				FileUtils.copyFile(srcFile, destFile);
				
				/* En caso el reporte se envie por Correo */
				if(form.isEnvioEmail()){	
					queryParams.put("codigoPais", form.getPais().getCodigo());
					queryParams.put("nombreReporte", form.getReporteFileName());
					queryParams.put("fileAttachment", destFile);
					queryParams.put("nombreArchivoReporte", nombreArchivoReporte);
					reporteParams.setQueryParams(queryParams);
				}
				form.setFileServer(destFile.getAbsolutePath());
				log.debug("Copy file(Not Exist Params) from : "+srcFile.getPath()+" to : "+destFile.getAbsolutePath());
			}
			
		}	
		
		
		reporteParams.setQueryParams(queryParams);
		
		
	}
	
	/**
	 * Proceso de envio de reporte via e-mail
	 */
	public void enviarReporteViaEmail(ReporteParams reporteParams, BaseReporteForm form)  {
		String indicadorError = Constants.OK;
		Map criteriaLogDisco = new HashMap();
		String mensaje="";
		MailResult mailResult = new MailResult();
		try {
			this.copiarReporteServidor(reporteParams, form);
			Map queryParams = reporteParams.getQueryParams();
			String directorio = (String)queryParams.get("directorioRepositorio"); 
			mensaje = "Reporte Generado en la Ruta: " + directorio + "  ";
			
			if (!form.isEnvioEmail()) {
				return;
			}
			MailParams mailParams = new MailParams();		
			mailParams.setUsuario(form.getUsuario());			
			mailParams.setPais(form.getPais());
			
			/* obteniendo usuario */
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			queryParams.put("usuarioLogin", usuario.getLogin());		
			reporteParams.setQueryParams(queryParams);
			
			mailParams.setQueryParams(queryParams);
			
			BaseMailService mailService = (BaseMailService) form.getReporteMailService();
			mailResult = mailService.enviarMail(mailParams);	
			
			if (!mailResult.isCompletado()) {
				String mensajeErrorCorreo = this.getResourceMessage("reporte.error.mail", new Object[]{mailResult.getMensajeError()});
				mensaje += mensajeErrorCorreo;
				indicadorError =  Constants.ERROR;
				this.messageErrorEmail = mensajeErrorCorreo;
			}
			else {
				mensaje += this.getResourceMessage("reportesEnviados.mail.lista.reporte.descripcion.exito", new String[]{mailResult.getCorreoDestino()});
				this.currentReporteEnviadoMail.setEstado(this.getResourceMessage("reportesEnviados.mail.lista.reporte.estado.exito"));	
			}
		}
		catch (Exception e) {
			indicadorError =  Constants.ERROR;
			mensaje = this.obtieneMensajeErrorException(e);			
		}
		finally {
			this.prepararedLogView(indicadorError, mensaje, reporteParams, mailResult);
			this.generaLogGrabarReporte(indicadorError, mensaje, reporteParams);
		}
			
	}
	
	/**
	 * Metodo que devuelve la extension del formato del Reporte.
	 * @return
	 */
	public String getExtensionReporte(String formato) {
		if (StringUtils.equals("XLS", formato) || StringUtils.equals("VXLS", formato)) {
			return XLS_EXTENSION;
		}
		if (StringUtils.equals("XLSX", formato) || StringUtils.equals("VXLSX", formato)) {
			return XLSX_EXTENSION;
		}
		if (StringUtils.equals("CSV", formato) || StringUtils.equals("VCSV", formato)) {
			return CSV_EXTENSION;
		}
		if (StringUtils.equals("PDF", formato) || StringUtils.equals("VPDF", formato)) {
			return PDF_EXTENSION;
		}
		if (StringUtils.equals("TXT", formato) || StringUtils.equals("VTXT", formato)) {
			return TXT_EXTENSION;
		}
		if (StringUtils.equals("ZIP", formato)) {
			return ZIP_EXTENSION;
		}		
		return PDF_EXTENSION;
	}
	
	
	
	
	/**
	 * @param codigoPais
	 * @param codigoUsuario
	 * @param formatoExportacion
	 * @param indicadorError
	 */
	private void iniReporteHistorico() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Ini 'iniReporteHistorico' method");
		}
		String codigoPais = this.mPantallaPrincipalBean.getCurrentCountry().getCodigo();
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		this.currentHistoricoReporte.setFechaInicio(new Timestamp(System.currentTimeMillis()));
		this.currentHistoricoReporte.setEnvioMail(Constants.NO);	
		this.currentHistoricoReporte.setCodigoPais(codigoPais);
		this.currentHistoricoReporte.setCodigoMenu(this.codigoMenu);
		this.currentHistoricoReporte.setCodigoReporte(this.nombreReporteJRXMLEjecutar);	
		this.currentHistoricoReporte.setCodigoUsuario(this.mPantallaPrincipalBean.getCurrentUser().getLogin());
		this.currentHistoricoReporte.setFormatoReporte(this.formatoExportacion);
		this.currentHistoricoReporte.setIndicadorError(Constants.NO);
		this.currentHistoricoReporte.setMultiReporte(Constants.NO);
		this.currentHistoricoReporte.setNumeroMultireportes(new Long(1));
		
		try {			
			this.currentHistoricoReporte.setIpMaquina(InetAddress.getLocalHost().getHostAddress());
			
		} 
		catch (Exception e) {
			this.currentHistoricoReporte.setIpMaquina("");
		}
		
		try {		
			InterfazSiCCService interfazSiCCService = (InterfazSiCCService)this.getBean("sisicc.interfazSiCCService");
			this.currentHistoricoReporte.setCodigoPeriodo(interfazSiCCService.getPeriodoDefaultByPaisCanal(codigoPais,"VD"));		
		
		} catch (Exception e) {
			this.currentHistoricoReporte.setCodigoPeriodo("");	
		}
		Long id = reporteService.getDevuelveIdSgteCodHistoricoReporte();
		this.currentHistoricoReporte.setCodigoHistoricoReporte(id);
		
		if (log.isDebugEnabled()) {
			log.debug("Finish 'iniReporteHistorico' method");
		}
	}
	
	/**
	 * Guarda los valores para ser grabados en la auditoria del Reporte
	 * @param nombreReporte
	 * @param codigoPais
	 * @param codigoUsuario
	 * @param formatoExportacion
	 */
	public void finHistoricoReporte(String indicadorError){

		if (log.isDebugEnabled()) {
			log.debug("Entering 'finHistoricoReporte' method");
		}
		
		this.currentHistoricoReporte.setNombreArchivoReporte(this.formReporte.getFileServer());
		this.currentHistoricoReporte.setIndicadorError(indicadorError);
		this.currentHistoricoReporte.setFechaFin(new Timestamp(System.currentTimeMillis()));				
		this.currentHistoricoReporte.setDuracionSegundos
			((long) ((this.currentHistoricoReporte.getFechaFin().getTime() - this.currentHistoricoReporte.getFechaInicio().getTime())/1000));
		
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		reporteService.updateHistoricoReporte(this.currentHistoricoReporte);
		if (log.isDebugEnabled()) {
			log.debug("Finish 'finHistoricoReporte' method");
		}
		
	}
	
	
	
	/**
	 * Ingresa el reporte generado a la lista de Reportes Emitidos
	 * @param nombreReporte
	 */	
	public void ingresarListaReportesEmitidos(){
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ingresarListaReportesEmitidos' method");
		}
		//this.mPantallaPrincipalBean.generarListaReportesEmitidos();
		if (log.isDebugEnabled()) {
			log.debug("Finish 'ingresarListaReportesEmitidos' method");
		}
	}
	
	/**
	 * Inserta log en tabla temporal para que posteriormente sea posible visualizar dicho log en pantalla 
	 * @param reporteService
	 * @param criteriaLogDisco
	 * @param indicadorLog
	 * @param mensaje
	 */
	private void generaLogGrabarReporte(String indicadorLog, String mensaje, ReporteParams reporteParams) {
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		Map params = reporteParams.getQueryParams();
		String valorFiltro = this.getValorFiltroGrabarReporte(reporteParams);
		String usuarioLogin = (String) params.get("usuarioLogin");
				
		Map criteriaLogDisco = new HashMap();
		criteriaLogDisco.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		criteriaLogDisco.put("nombreReporte", MapUtils.getString(params, "nombreArchivoReporte", this.nombreReporteJRXMLEjecutar));
		criteriaLogDisco.put("indicadorLog", indicadorLog);
		criteriaLogDisco.put("descripcionLog", mensaje);
		criteriaLogDisco.put("valorFiltro", valorFiltro);
		criteriaLogDisco.put("usuarioLogin", usuarioLogin);
		
		
		if (this.nroReporteProcesando == 1) {
			Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
			Map paramsLog = new HashMap(); 
			paramsLog.put("usuarioLogin", usuario.getLogin());
			paramsLog.put("nombreReporte", MapUtils.getString(params, "nombreArchivoReporte", this.nombreReporteJRXMLEjecutar));
			if(this.isDeleteTemporal) {
				reporteService.deleteLogReporteDisco(paramsLog);
			}
		}
		reporteService.insertLogReporteDisco(criteriaLogDisco);		
	}
	
	
	/**
	 * Preparared log view.
	 *
	 * @param indicadorLog the indicador log
	 * @param mensaje the mensaje
	 * @param reporteParams the reporte params
	 */
	private void prepararedLogView(String indicadorLog, String mensaje, ReporteParams reporteParams, MailResult mailResult ){
		if(log.isDebugEnabled()){
			log.debug("prepararedLogView");
		}
		Map params = reporteParams.getQueryParams();		
		Usuario usuario = (Usuario)params.get("usuario");
		String valorFiltro = this.getValorFiltroGrabarReporte(reporteParams);
		String zona = (String) params.get("codigoZona");
		String region = (String) params.get("codigoRegion");
		this.currentReporteEnviadoMail.setNombreReporte(MapUtils.getString(params, "nombreArchivoReporte", this.nombreReporteJRXMLEjecutar));
		this.currentReporteEnviadoMail.setIndicadorLog(indicadorLog); 
		this.currentReporteEnviadoMail.setDescripcionLog(mensaje);
		this.currentReporteEnviadoMail.setValorFiltro(valorFiltro);
		this.currentReporteEnviadoMail.setUsuarioLogin(usuario.getLogin());
		this.currentReporteEnviadoMail.setEmailDestino(mailResult.getCorreoDestino());
		this.currentReporteEnviadoMail.setZona(zona);
		this.currentReporteEnviadoMail.setRegion(region);
		this.listaReportesMail.add(this.getCurrentReporteEnviadoMail());
	}
	
	/**
	 * Metodo que obtiene el valor del Filtro a ser grabado en el Log del Reporte.
	 * Dicho metodo puedes ser sobreescrito
	 * @param reporteParams
	 * @return
	 */	
	protected String getValorFiltroGrabarReporte(ReporteParams reporteParams) {
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	protected Map<String, Object> prepareParamsBeforeExecute(
			Map<String, Object> params, BaseForm form) throws Exception {
		return params;
	}
	
	
	/**
	 * @param form
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> inicializarMapForm(BaseReporteForm form) throws Exception {
		// Paso todos los parametros al map
		Map<String, Object> params = BeanUtils.describe(form);
		if (params == null)
			params = new HashMap<String, Object>();
		
		// Agrego los parametros necesarios
		params.put("usuario", form.getUsuario());				
		params.put("descripcionPais", form.getPais().getDescripcion());
		params.put("codigoPais", form.getPais().getCodigo());
		
		if (this.ejecutarReporteJASPER)
			params.put("ejecutarReporteJASPER", Constants.SI);
		else
			params.put("ejecutarReporteJASPER", Constants.NO);
		
		// Remuevo los parametros que no son necesarios del Validator		
		params.remove("pais");
		params.remove("idioma");
		params.remove("parametrosReporteGeneral");
		params.remove("locale");		
		
		params.remove("resultValueMap");
		params.remove("validatorResults");
		params.remove("servletWrapper");
		params.remove("multipartRequestHandler");
		params.remove("class");
		params.remove("page");
		return params;
	}
	
	
	
	/**
	 * Aqui se debe asociar la clase FORM al Manage Beans, la cual internamente se asociara al
	 * atributo formReporte
	 * @return
	 * @throws Exception
	 */
	protected abstract BaseReporteForm devuelveFormReporte() throws Exception;
	
	/**
	 * Aqui se debe asociar el nombre del reporte asociado.
	 * @return
	 * @throws Exception
	 */
	protected abstract String devuelveNombreReporte() throws Exception;
	
	/** Este metodo se debe implementar y declarar el nombre del subreporte en las clases hijas
	 * @return 
	 * @throws Exception
	 */
	protected abstract String devuelveNombreSubReporte() throws Exception;
	
	
	/**
	 * En este metodo se setea el nombre del bean del Servicio de Envio de Mail
	 * @return
	 */
	protected String devuelveBeanMailService(){
		String service = "sisicc.mailUtil";		
		return service;
	}
	
	/**
	 * En este metodo se setea el nombre del bean del Servicio del Constructor de Parametros del Reporte
	 * @return
	 */
	protected String devuelveBeanReporteService(){
		return "reportes.parameterContructorService";
	}
	
	/**
	 * Aqui se declara los filtros especificos del reporte.(Filtros Adicionales y declarados en la pagina) 
	 * @throws Exception
	 */
	protected abstract Map prepareParameterMap(Map params) throws Exception;
	
	/**
	 * Metodo que devuelve la cantidad de Reportes a generar en el Servidor
	 * Dicho metodo debe ser sobreescrito para que genere mas de 1 reporte
	 * @return
	 */
	protected int getNroReportesAGenerar() {
		return 1;
	}
	
	/**
	 * Realiza acciones personalizadas de validación previa, de existir, antes de realizar la verificacion de ejecucion del reporte
	 * @return
	 */
	private boolean validarReporte(){
		this.validacionPrevia = true;	
		String lsMensajeError = this.setValidarReporte();
		if (StringUtils.isNotBlank(lsMensajeError)) {
			this.validacionPrevia = false;	
			this.setReporteEnEjecucion(false);
			this.setMensajeAlertaDefault(lsMensajeError);
		}
		return this.validacionPrevia;
	}
	
	/**
	 * Realiza acciones personalizadas de validación previa, de existir, antes de realizar la verificacion de ejecucion del reporte
	 * Devuelve Mensaje de error personalizado de validacion extra antes de la verificación de la ejecución del reporte 
	 * @return
	 */
	public String setValidarReporte(){
		return "";
	}


	/**
	 * @return
	 */
	protected String errorKeyGenerarMultipleReporte() {
		return null;		
	}
	
	
	
	
	/**
	 * Metodo que devuelve la extension del formato del Reporte.
	 * @return
	 */
	public String getExtensionReporte() {
		if (StringUtils.equals("XLS", this.formatoExportacion) || StringUtils.equals("VXLS", this.formatoExportacion)) {
			return XLS_EXTENSION;
		}
		if (StringUtils.equals("XLSX", this.formatoExportacion) || StringUtils.equals("VXLSX", this.formatoExportacion)) {
			return XLSX_EXTENSION;
		}
		if (StringUtils.equals("CSV", this.formatoExportacion) || StringUtils.equals("VCSV", this.formatoExportacion)) {
			return CSV_EXTENSION;
		}
		if (StringUtils.equals("PDF", this.formatoExportacion) || StringUtils.equals("VPDF", this.formatoExportacion)) {
			return PDF_EXTENSION;
		}
		if (StringUtils.equals("TXT", this.formatoExportacion) || StringUtils.equals("VTXT", this.formatoExportacion)) {
			return TXT_EXTENSION;
		}
		if (StringUtils.equals("ZIP", this.formatoExportacion)) {
			return ZIP_EXTENSION;
		}		
		return PDF_EXTENSION;
	}
	
	/**
	 * @param lista
	 * @param parametro
	 * @param comilla
	 * @return
	 */
	protected String obtieneCondicion(String[] lista, String parametro,
			String comilla) {
		if (lista == null || lista.length == 0)
			return "";
		else {
			String resultado = "";
			for (int i = 0; i < lista.length; i++) {
				String dato = lista[i];
				if (StringUtils.isBlank(dato) || StringUtils.equals(dato, "Todos") || StringUtils.equals(dato, "T") )
					return "";
				if (i == 0)
					resultado = resultado + "(" + comilla + dato + comilla;
				else
					resultado = resultado + "," + comilla + dato + comilla;
			}
			resultado = resultado + ")";
			resultado = " AND " + parametro + " IN " + resultado;
			return resultado;
		}
	}
	
	protected String obtieneCondicionSinT(String[] lista, String parametro,
			String comilla) {
		if (lista == null || lista.length == 0)
			return "";
		else {
			String resultado = "";
			for (int i = 0; i < lista.length; i++) {
				String dato = lista[i];
				if (StringUtils.isBlank(dato) || StringUtils.equals(dato, "Todos"))
					return "";
				if (i == 0)
					resultado = resultado + "(" + comilla + dato + comilla;
				else
					resultado = resultado + "," + comilla + dato + comilla;
			}
			resultado = resultado + ")";
			resultado = " AND " + parametro + " IN " + resultado;
			return resultado;
		}
	} 

	/**
	 * @param lista
	 * @param parametro
	 * @param comilla
	 * @return
	 */
	protected String obtieneCondicionIN(String[] lista, String parametro,
			String comilla ) {
		if (lista == null || lista.length == 0)
			return "";
		else {
			String resultado = "";
			for (int i = 0; i < lista.length; i++) {
				String dato = lista[i];
				if (StringUtils.isBlank(dato) || StringUtils.equals(dato, "Todos") || StringUtils.equals(dato, "T") )
					return "";
				if (i == 0)
					resultado = resultado + "(" + comilla + dato + comilla;
				else
					resultado = resultado + "," + comilla + dato + comilla;
			}
			resultado = resultado + ") ";
			resultado = " IN " + resultado;
			return resultado;
		}
	} 
	
	/**
	 * Aumenta dinámicamente el tamaño del panel del reporte.
	 * @param actionEvent
	 */
	public void aumentarSize(ActionEvent actionEvent) {
		log.debug("Entering 'BaseReporteAbstractAction.aumentarSize' method");
		this.sizeReport +=100;		
	}
	
	/**
	 * Reduce dinámicamente el tamaño del panel del reporte.
	 * @param actionEvent
	 */
	public void reducirSize(ActionEvent actionEvent) {
		log.debug("Entering 'BaseReporteAbstractAction.reducirSize' method");
		if(this.sizeReport > 100){
			this.sizeReport -=100;		
		}		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseAbstractJSF#setObtenerPaginaAyudaPantalla()
	 */
	protected String setObtenerPaginaAyudaPantalla() {
		return "/pages/ayuda/consultaAyudaReportes.xhtml";
	}
	
	
	
	/**
	 * Metodo que descarga un reporte con un formato especifico de la lista de reportes emitidos
	 */
	public void descargarReporte(){
		if (log.isWarnEnabled()) {
			log.warn("Entering 'descargarReporte' method");
		}
		try {
			
			ReporteEmitido reporteEmitido = this.mPantallaPrincipalBean.getCurrentReporteEmitido();
					
			log.debug("File to download : "+reporteEmitido.getFileName());
			File file = new File(reporteEmitido.getFileName());
			log.debug("File downloaded : "+file.getAbsolutePath());
			
		    if(file != null){
		    	
				FileInputStream fileInputStream = new FileInputStream(file);
				
				HttpServletResponse response = this.getResponse();

				response.reset();
								
					
		    	if(reporteEmitido.getFormato().equals("XLS")){
		    		
		    		response.setContentType("application/vnd.ms-excel");
		    		response.addHeader("Content-Disposition", "attachment; filename="+reporteEmitido.getNombreReporte()+XLS_EXTENSION);
		    		
			        ServletOutputStream out = response.getOutputStream();
		            int bit = 256;
		            while ((bit) >= 0) {
		                bit = fileInputStream.read();
		                out.write(bit);
		            }		    			           
		    		
		    	}else if(reporteEmitido.getFormato().equals("CSV")){
		    		
		    		response.setContentType("text/csv");		    		
		    		response.addHeader("Content-Disposition", "attachment; filename="+reporteEmitido.getNombreReporte()+CSV_EXTENSION);
		    				    		
		    	}else if(reporteEmitido.getFormato().equals("XLSX")){
		    		
		    		response.setContentType("vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		    		response.addHeader("Content-Disposition", "attachment; filename="+reporteEmitido.getNombreReporte()+XLSX_EXTENSION);
		    		
			        ServletOutputStream out = response.getOutputStream();
		            int bit = 256;
		            while ((bit) >= 0) {
		                bit = fileInputStream.read();
		                out.write(bit);
		            }
		            
		    	}else{
		    		
		    		response.setContentType("application/pdf");		    		
		    		response.addHeader("Content-Disposition", "attachment; filename="+reporteEmitido.getNombreReporte()+PDF_EXTENSION);
		    		
		    		OutputStream responseOutputStream = response.getOutputStream();
					int bytes;
					while ((bytes = fileInputStream.read()) != -1) {
						responseOutputStream.write(bytes);
					}		    	
					
		    	}
		    	
		    	response.setContentLength((int)file.length());		    		
		    	response.getOutputStream().flush();
		    	response.getOutputStream().close();
		    	fileInputStream.close();	
		    	
		    }
		    
		    this.responseComplete();
		}
		catch (Exception e) {
			String error = this.obtieneMensajeErrorException(e);
			this.addError("Error: ", error);
			this.mPantallaPrincipalBean.setMensajeErrorSistema(error);
			try {
				this.redireccionarPagina("_reporteConError");
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			
		}
		if (log.isWarnEnabled()) {
			log.warn("Finish 'descargarReporte' method");
		}	
	}
	
	
	
	
	/* GET - SEt */
	
	/**
	 * @return the formReporte
	 */
	public BaseReporteForm getFormReporte() {
		return formReporte;
	}

	/**
	 * @param formReporte the formReporte to set
	 */
	public void setFormReporte(BaseReporteForm formReporte) {
		this.formReporte = formReporte;
	}
	
	/**
	 * @return the reporteEnEjecucion
	 */
	public boolean isReporteEnEjecucion() {
		return reporteEnEjecucion;
	}

	/**
	 * @param reporteEnEjecucion the reporteEnEjecucion to set
	 */
	public void setReporteEnEjecucion(boolean reporteEnEjecucion) {
		this.reporteEnEjecucion = reporteEnEjecucion;
	}
	
	/**
	 * @return the formatoExportacion
	 */
	public String getFormatoExportacion() {
		return formatoExportacion;
	}

	/**
	 * @param formatoExportacion the formatoExportacion to set
	 */
	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}
	
	/**
	 * @return the mostrarReportePDF
	 */
	public boolean isMostrarReportePDF() {
		return mostrarReportePDF;
	}

	/**
	 * @param mostrarReportePDF the mostrarReportePDF to set
	 */
	public void setMostrarReportePDF(boolean mostrarReportePDF) {
		this.mostrarReportePDF = mostrarReportePDF;
	}

	/**
	 * @return the mostrarReporteXLS
	 */
	public boolean isMostrarReporteXLS() {
		return mostrarReporteXLS;
	}

	/**
	 * @param mostrarReporteXLS the mostrarReporteXLS to set
	 */
	public void setMostrarReporteXLS(boolean mostrarReporteXLS) {
		this.mostrarReporteXLS = mostrarReporteXLS;
	}

	/**
	 * @return the mostrarReporteXLSX
	 */
	public boolean isMostrarReporteXLSX() {
		return mostrarReporteXLSX;
	}

	/**
	 * @param mostrarReporteXLSX the mostrarReporteXLSX to set
	 */
	public void setMostrarReporteXLSX(boolean mostrarReporteXLSX) {
		this.mostrarReporteXLSX = mostrarReporteXLSX;
	}
	
	/**
	 * @return the mostrarReporteCSV
	 */
	public boolean isMostrarReporteCSV() {
		return mostrarReporteCSV;
	}

	/**
	 * @param mostrarReporteCSV the mostrarReporteCSV to set
	 */
	public void setMostrarReporteCSV(boolean mostrarReporteCSV) {
		this.mostrarReporteCSV = mostrarReporteCSV;
	}
	
	/**
	 * @return the outfile
	 */
	public String getOutfile() {
		return outfile;
	}

	/**
	 * @param outfile the outfile to set
	 */
	public void setOutfile(String outfile) {
		this.outfile = outfile;
	}

	/**
	 * @return the viewReporteMedia
	 */
	public boolean isViewReporteMedia() {
		return viewReporteMedia;
	}

	/**
	 * @param viewReporteMedia the viewReporteMedia to set
	 */
	public void setViewReporteMedia(boolean viewReporteMedia) {
		this.viewReporteMedia = viewReporteMedia;
	}
	
	/**
	 * @return the sizeReport
	 */
	public int getSizeReport() {
		return sizeReport;
	}

	/**
	 * @param sizeReport the sizeReport to set
	 */
	public void setSizeReport(int sizeReport) {
		this.sizeReport = sizeReport;
	}
	
	/**
	 * @return the outfileMedia
	 */
	public String getOutfileMedia() {
		return outfileMedia;
	}

	/**
	 * @param outfileMedia the outfileMedia to set
	 */
	public void setOutfileMedia(String outfileMedia) {
		this.outfileMedia = outfileMedia;
	}
	/**
	 * @return the mostrarEnviarEmail
	 */
	public boolean isMostrarEnviarEmail() {
		return mostrarEnviarEmail;
	}

	/**
	 * @param mostrarEnviarEmail the mostrarEnviarEmail to set
	 */
	public void setMostrarEnviarEmail(boolean mostrarEnviarEmail) {
		this.mostrarEnviarEmail = mostrarEnviarEmail;
	}
	
	/**
	 * @return the visualizarReporte
	 */
	public boolean isVisualizarReporte() {
		return visualizarReporte;
	}

	/**
	 * @param visualizarReporte the visualizarReporte to set
	 */
	public void setVisualizarReporte(boolean visualizarReporte) {
		this.visualizarReporte = visualizarReporte;
	}

	/**
	 * @return the nroReporteProcesando
	 */
	public int getNroReporteProcesando() {
		return nroReporteProcesando;
	}

	/**
	 * @param nroReporteProcesando the nroReporteProcesando to set
	 */
	public void setNroReporteProcesando(int nroReporteProcesando) {
		this.nroReporteProcesando = nroReporteProcesando;
	}
	
	

	/**
	 * @return the listaReportesMail
	 */
	public List<ReporteEnviadoMail> getListaReportesMail() {
		return listaReportesMail;
	}

	/**
	 * @param listaReportesMail the listaReportesMail to set
	 */
	public void setListaReportesMail(List<ReporteEnviadoMail> listaReportesMail) {
		this.listaReportesMail = listaReportesMail;
	}

	/**
	 * @return the currentReporteEnviadoMail
	 */
	public ReporteEnviadoMail getCurrentReporteEnviadoMail() {
		return currentReporteEnviadoMail;
	}

	/**
	 * @param currentReporteEnviadoMail the currentReporteEnviadoMail to set
	 */
	public void setCurrentReporteEnviadoMail(
			ReporteEnviadoMail currentReporteEnviadoMail) {
		this.currentReporteEnviadoMail = currentReporteEnviadoMail;
	}

		
	/**
	 * @return the currentHistoricoReporte
	 */
	public HistoricoReporte getCurrentHistoricoReporte() {
		return currentHistoricoReporte;
	}

	/**
	 * @param currentHistoricoReporte the currentHistoricoReporte to set
	 */
	public void setCurrentHistoricoReporte(HistoricoReporte currentHistoricoReporte) {
		this.currentHistoricoReporte = currentHistoricoReporte;
	}


	/**
	 * @return the listaBusqueda
	 */
	public List getListaBusqueda() {
		return listaBusqueda;
	}


	/**
	 * @param listaBusqueda the listaBusqueda to set
	 */
	public void setListaBusqueda(List listaBusqueda) {
		this.listaBusqueda = listaBusqueda;
	}


	/**
	 * @return the mostrarListaBusqueda
	 */
	public boolean isMostrarListaBusqueda() {
		return mostrarListaBusqueda;
	}


	/**
	 * @param mostrarListaBusqueda the mostrarListaBusqueda to set
	 */
	public void setMostrarListaBusqueda(boolean mostrarListaBusqueda) {
		this.mostrarListaBusqueda = mostrarListaBusqueda;
	}


	/**
	 * @return the mostrarBotonBuscar
	 */
	public boolean isMostrarBotonBuscar() {
		return mostrarBotonBuscar;
	}


	/**
	 * @param mostrarBotonBuscar the mostrarBotonBuscar to set
	 */
	public void setMostrarBotonBuscar(boolean mostrarBotonBuscar) {
		this.mostrarBotonBuscar = mostrarBotonBuscar;
	}


	/**
	 * @return the datatableBusqueda
	 */
	public DataTableModel getDatatableBusqueda() {
		return datatableBusqueda;
	}


	/**
	 * @param datatableBusqueda the datatableBusqueda to set
	 */
	public void setDatatableBusqueda(DataTableModel datatableBusqueda) {
		this.datatableBusqueda = datatableBusqueda;
	}
	
	/**
	 * @param generateTabsXLS the generateTabsXLS to set
	 */
	public void setGenerateTabsXLS(boolean generateTabsXLS) {
		this.generateTabsXLS = generateTabsXLS;
	}

	public boolean isMostrarReporteOJXLSX() {
		return mostrarReporteOJXLSX;
	}

	public void setMostrarReporteOJXLSX(boolean mostrarReporteOJXLSX) {
		this.mostrarReporteOJXLSX = mostrarReporteOJXLSX;
	}

	public boolean isMostrarReporteOOXLSX() {
		return mostrarReporteOOXLSX;
	}

	public void setMostrarReporteOOXLSX(boolean mostrarReporteOOXLSX) {
		this.mostrarReporteOOXLSX = mostrarReporteOOXLSX;
	}

	public boolean isMostrarReporteOCSV() {
		return mostrarReporteOCSV;
	}

	public void setMostrarReporteOCSV(boolean mostrarReporteOCSV) {
		this.mostrarReporteOCSV = mostrarReporteOCSV;
	}

	/**
	 * @return the codigoReporteAuditoria
	 */
	public String getCodigoReporteAuditoria() {
		return codigoReporteAuditoria;
	}

	/**
	 * @param codigoReporteAuditoria the codigoReporteAuditoria to set
	 */
	public void setCodigoReporteAuditoria(String codigoReporteAuditoria) {
		this.codigoReporteAuditoria = codigoReporteAuditoria;
	}

	
	public boolean isDeleteTemporal() {
		return isDeleteTemporal;
	}

	public void setDeleteTemporal(boolean isDeleteTemporal) {
		this.isDeleteTemporal = isDeleteTemporal;
	}

	public boolean isMostrarLogPantalla() {
		return mostrarLogPantalla;
	}

	public void setMostrarLogPantalla(boolean mostrarLogPantalla) {
		this.mostrarLogPantalla = mostrarLogPantalla;
	}

	public boolean isMostrarListaReporteLog() {
		return mostrarListaReporteLog;
	}

	public void setMostrarListaReporteLog(boolean mostrarListaReporteLog) {
		this.mostrarListaReporteLog = mostrarListaReporteLog;
	}

	/**
	 * @return the mostrarReporteMailXLS
	 */
	public boolean isMostrarReporteMailXLS() {
		return mostrarReporteMailXLS;
	}

	/**
	 * @param mostrarReporteMailXLS the mostrarReporteMailXLS to set
	 */
	public void setMostrarReporteMailXLS(boolean mostrarReporteMailXLS) {
		this.mostrarReporteMailXLS = mostrarReporteMailXLS;
	}

	/**
	 * @return the mostrarReporteMailPDF
	 */
	public boolean isMostrarReporteMailPDF() {
		return mostrarReporteMailPDF;
	}

	/**
	 * @param mostrarReporteMailPDF the mostrarReporteMailPDF to set
	 */
	public void setMostrarReporteMailPDF(boolean mostrarReporteMailPDF) {
		this.mostrarReporteMailPDF = mostrarReporteMailPDF;
	}

	/**
	 * @return the indicadorMultiReporte
	 */
	public boolean isIndicadorMultiReporte() {
		return indicadorMultiReporte;
	}

	/**
	 * @param indicadorMultiReporte the indicadorMultiReporte to set
	 */
	public void setIndicadorMultiReporte(boolean indicadorMultiReporte) {
		this.indicadorMultiReporte = indicadorMultiReporte;
	}

	/**
	 * @return the prefijoArchivo
	 */
	public String getPrefijoArchivo() {
		return prefijoArchivo;
	}

	/**
	 * @param prefijoArchivo the prefijoArchivo to set
	 */
	public void setPrefijoArchivo(String prefijoArchivo) {
		this.prefijoArchivo = prefijoArchivo;
	}

	

	/**
	 * @return the viewListaEnviadosMail
	 */
	public boolean isViewListaEnviadosMail() {
		return viewListaEnviadosMail;
	}

	/**
	 * @param viewListaEnviadosMail the viewListaEnviadosMail to set
	 */
	public void setViewListaEnviadosMail(boolean viewListaEnviadosMail) {
		this.viewListaEnviadosMail = viewListaEnviadosMail;
	}

	/**
	 * @return the viewPDFMedia
	 */
	public boolean isViewPDFMedia() {
		return viewPDFMedia;
	}

	/**
	 * @param viewPDFMedia the viewPDFMedia to set
	 */
	public void setViewPDFMedia(boolean viewPDFMedia) {
		this.viewPDFMedia = viewPDFMedia;
	}

	/**
	 * @return the mostrarReporteOTXT
	 */
	public boolean isMostrarReporteOTXT() {
		return mostrarReporteOTXT;
	}

	/**
	 * @param mostrarReporteOTXT the mostrarReporteOTXT to set
	 */
	public void setMostrarReporteOTXT(boolean mostrarReporteOTXT) {
		this.mostrarReporteOTXT = mostrarReporteOTXT;
	}

	/**
	 * @return the mostrarReporteZIP
	 */
	public boolean isMostrarReporteZIP() {
		return mostrarReporteZIP;
	}

	/**
	 * @param mostrarReporteZIP the mostrarReporteZIP to set
	 */
	public void setMostrarReporteZIP(boolean mostrarReporteZIP) {
		this.mostrarReporteZIP = mostrarReporteZIP;
	}

	/**
	 * @return the mostrarReporteOZIP
	 */
	public boolean isMostrarReporteOZIP() {
		return mostrarReporteOZIP;
	}

	/**
	 * @param mostrarReporteOZIP the mostrarReporteOZIP to set
	 */
	public void setMostrarReporteOZIP(boolean mostrarReporteOZIP) {
		this.mostrarReporteOZIP = mostrarReporteOZIP;
	}

	/**
	 * @return the mostrarReporteXLS97
	 */
	public boolean isMostrarReporteXLS97() {
		return mostrarReporteXLS97;
	}

	/**
	 * @param mostrarReporteXLS97 the mostrarReporteXLS97 to set
	 */
	public void setMostrarReporteXLS97(boolean mostrarReporteXLS97) {
		this.mostrarReporteXLS97 = mostrarReporteXLS97;
	}
	
	/**
	 * @return the iconPersonalExcel
	 */
	public String getIconPersonalExcel() {
		return iconPersonalExcel;
	}

	/**
	 * @param iconPersonal the iconPersonalExcel to set
	 */
	public void setIconPersonalExcel(String iconPersonalExcel) {
		this.iconPersonalExcel = iconPersonalExcel;
	}

	/**
	 * @return the iconPersonalPdf
	 */
	public String getIconPersonalPdf() {
		return iconPersonalPdf;
	}

	/**
	 * @param iconPersonalPdf the iconPersonalPdf to set
	 */
	public void setIconPersonalPdf(String iconPersonalPdf) {
		this.iconPersonalPdf = iconPersonalPdf;
	}

	/**
	 * @return the mostrarExcelXLSX
	 */
	public boolean isMostrarExcelXLSX() {
		return mostrarExcelXLSX;
	}

	/**
	 * @param mostrarExcelXLSX the mostrarExcelXLSX to set
	 */
	public void setMostrarExcelXLSX(boolean mostrarExcelXLSX) {
		this.mostrarExcelXLSX = mostrarExcelXLSX;
	}

	/**
	 * @return the ignorarCeldaBorder
	 */
	public boolean isIgnorarCeldaBorder() {
		return ignorarCeldaBorder;
	}

	/**
	 * @param ignorarCeldaBorder the ignorarCeldaBorder to set
	 */
	public void setIgnorarCeldaBorder(boolean ignorarCeldaBorder) {
		this.ignorarCeldaBorder = ignorarCeldaBorder;
	}	
	
	
	
}
