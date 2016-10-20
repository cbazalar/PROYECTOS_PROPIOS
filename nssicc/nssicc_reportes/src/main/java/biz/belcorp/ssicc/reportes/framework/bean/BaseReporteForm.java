package biz.belcorp.ssicc.reportes.framework.bean;

import java.util.Locale;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.service.BaseReporteInterface;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;

/**
 * Form base para los Reportes ScSiCC. Los forms de los reportes deben heredar
 * de este Form.
 * 
 * @author <a href="">Marco Agurto</a>
 * 
 */
public class BaseReporteForm extends BaseForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8561444683700565665L;

	private static final String JasperReport = null;
	
	/** Valores seteados en los action de cada reporte **/
	protected String formatoExportacion;
	protected String tipoReporteAMostrar;
	
	protected String reporteFileName;
	
	protected String nombreReporte;
	protected String nombreSubReporte;

	protected String titulo;
	protected String NroReporte;
	
	private String subReporteDir1;	
	private String subReporteDir2;

	/** Valores Seteados en el base de cada reporte **/	
	protected Usuario usuario;
	
	protected Pais pais;
	protected Idioma idioma = new Idioma();
	protected String rutaImagenes;
	protected Map parametrosReporteGeneral;
    protected String oidIdiomaIso;

    protected Locale locale;
    
	private String anyoPeriodo; // Año del periodo actual, defaul campaña.
	protected String codigoPeriodo;

	// Nombre del Service Asociado de construccion de parametros adicionales
	private String constructorService = "reportes.parameterContructorService"; 
	private BaseReporteInterface reporteService;

	//Flag que indica si existe logica adicional en los parametros antes de la ejecucion del reporte(dafault false)
	private boolean beforeExecuteReporte = false;
	
	//Envio por correo
	protected boolean envioEmail = false;
	protected boolean errorEnvioEmail = false;
	protected String mensajeErrorMail;
	
	protected String mailService = new String(); //"sisicc.baseMailAbstractService";
	private BaseMailService reporteMailService;
		
	protected String outFile;
	protected String fileServer;

	protected String codigoProceso;
	protected String codigoProcesoBatch;
	protected String codigoPais;
	/**
	 * @return Returns the formatoExportacion.
	 */
	public String getFormatoExportacion() {
		return formatoExportacion;
	}

	/**
	 * @param formatoExportacion The formatoExportacion to set.
	 */
	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}

	/**
	 * @return the tipoReporteAMostrar
	 */
	public String getTipoReporteAMostrar() {
		return tipoReporteAMostrar;
	}

	/**
	 * @param tipoReporteAMostrar the tipoReporteAMostrar to set
	 */
	public void setTipoReporteAMostrar(String tipoReporteAMostrar) {
		this.tipoReporteAMostrar = tipoReporteAMostrar;
	}	
	
	/**
	 * @return the reporteFileName
	 */
	public String getReporteFileName() {
		return reporteFileName;
	}

	/**
	 * @param reporteFileName the reporteFileName to set
	 */
	public void setReporteFileName(String reporteFileName) {
		this.reporteFileName = reporteFileName;
	}
	
	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * @return the pais
	 */
	public Pais getPais() {
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	/**
	 * @return the parametrosReporteGeneral
	 */
	public Map getParametrosReporteGeneral() {
		return parametrosReporteGeneral;
	}

	/**
	 * @param parametrosReporteGeneral the parametrosReporteGeneral to set
	 */
	public void setParametrosReporteGeneral(Map parametrosReporteGeneral) {
		this.parametrosReporteGeneral = parametrosReporteGeneral;
	}
	
	/**
	 * @return the idioma
	 */
	public Idioma getIdioma() {
		return idioma;
	}

	/**
	 * @param idioma the idioma to set
	 */
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	
	/**
	 * @return the oidIdiomaIso
	 */
	public String getOidIdiomaIso() {
		return oidIdiomaIso;
	}

	/**
	 * @param oidIdiomaIso the oidIdiomaIso to set
	 */
	public void setOidIdiomaIso(String oidIdiomaIso) {
		this.oidIdiomaIso = oidIdiomaIso;
	}
	
	/**
	 * @return the rutaImagenes
	 */
	public String getRutaImagenes() {
		return rutaImagenes;
	}

	/**
	 * @param rutaImagenes the rutaImagenes to set
	 */
	public void setRutaImagenes(String rutaImagenes) {
		this.rutaImagenes = rutaImagenes;
	}
	
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the nroReporte
	 */
	public String getNroReporte() {
		return NroReporte;
	}

	/**
	 * @param nroReporte the nroReporte to set
	 */
	public void setNroReporte(String nroReporte) {
		NroReporte = nroReporte;
	}
	
	/**
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	/**
	 * @return the nombreReporte
	 */
	public String getNombreReporte() {
		return nombreReporte;
	}

	/**
	 * @param nombreReporte the nombreReporte to set
	 */
	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}

	/**
	 * @return the nombreSubReporte
	 */
	public String getNombreSubReporte() {
		return nombreSubReporte;
	}

	/**
	 * @param nombreSubReporte the nombreSubReporte to set
	 */
	public void setNombreSubReporte(String nombreSubReporte) {
		this.nombreSubReporte = nombreSubReporte;
	}
	
	/**
	 * @return the anyoPeriodo
	 */
	public String getAnyoPeriodo() {
		return anyoPeriodo;
	}

	/**
	 * @param anyoPeriodo the anyoPeriodo to set
	 */
	public void setAnyoPeriodo(String anyoPeriodo) {
		this.anyoPeriodo = anyoPeriodo;
	}
	
	/**
	 * @return the subReporteDir1
	 */
	public String getSubReporteDir1() {
		return subReporteDir1;
	}

	/**
	 * @param subReporteDir1 the subReporteDir1 to set
	 */
	public void setSubReporteDir1(String subReporteDir1) {
		this.subReporteDir1 = subReporteDir1;
	}

	/**
	 * @return the subReporteDir2
	 */
	public String getSubReporteDir2() {
		return subReporteDir2;
	}

	/**
	 * @param subReporteDir2 the subReporteDir2 to set
	 */
	public void setSubReporteDir2(String subReporteDir2) {
		this.subReporteDir2 = subReporteDir2;
	}
	
	/**
	 * @return the constructorService
	 */
	public String getConstructorService() {
		return constructorService;
	}

	/**
	 * @param constructorService the constructorService to set
	 */
	public void setConstructorService(String constructorService) {
		this.constructorService = constructorService;
	}
	
	/**
	 * @return the beforeExecuteReporte
	 */
	public boolean isBeforeExecuteReporte() {
		return beforeExecuteReporte;
	}

	/**
	 * @param beforeExecuteReporte the beforeExecuteReporte to set
	 */
	public void setBeforeExecuteReporte(boolean beforeExecuteReporte) {
		this.beforeExecuteReporte = beforeExecuteReporte;
	}
	
	/**
	 * @return the envioEmail
	 */
	public boolean isEnvioEmail() {
		return envioEmail;
	}

	/**
	 * @param envioEmail the envioEmail to set
	 */
	public void setEnvioEmail(boolean envioEmail) {
		this.envioEmail = envioEmail;
	}
	
	/**
	 * @return the errorEnvioEmail
	 */
	public boolean isErrorEnvioEmail() {
		return errorEnvioEmail;
	}

	/**
	 * @param errorEnvioEmail the errorEnvioEmail to set
	 */
	public void setErrorEnvioEmail(boolean errorEnvioEmail) {
		this.errorEnvioEmail = errorEnvioEmail;
	}
	
	/**
	 * @return the mailService
	 */
	public String getMailService() {
		return mailService;
	}

	/**
	 * @param mailService the mailService to set
	 */
	public void setMailService(String mailService) {
		this.mailService = mailService;
	}
	
	/**
	 * @return the outFile
	 */
	public String getOutFile() {
		return outFile;
	}

	/**
	 * @param outFile the outFile to set
	 */
	public void setOutFile(String outFile) {
		this.outFile = outFile;
	}
	
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	/**
	 * @return the codigoProceso
	 */
	public String getCodigoProceso() {
		return codigoProceso;
	}

	/**
	 * @param codigoProceso the codigoProceso to set
	 */
	public void setCodigoProceso(String codigoProceso) {
		this.codigoProceso = codigoProceso;
	}
	
	/**
	 * @return the codigoProcesoBatch
	 */
	public String getCodigoProcesoBatch() {
		return codigoProcesoBatch;
	}

	/**
	 * @param codigoProcesoBatch the codigoProcesoBatch to set
	 */
	public void setCodigoProcesoBatch(String codigoProcesoBatch) {
		this.codigoProcesoBatch = codigoProcesoBatch;
	}
	
	/**
	 * @return the fileServer
	 */
	public String getFileServer() {
		return fileServer;
	}

	/**
	 * @param fileServer the fileServer to set
	 */
	public void setFileServer(String fileServer) {
		this.fileServer = fileServer;
	}

	/**
	 * @return the reporteService
	 */
	public BaseReporteInterface getReporteService() {
		return reporteService;
	}

	/**
	 * @param reporteService the reporteService to set
	 */
	public void setReporteService(BaseReporteInterface reporteService) {
		this.reporteService = reporteService;
	}

	/**
	 * @return the reporteMailService
	 */
	public BaseMailService getReporteMailService() {
		return reporteMailService;
	}

	/**
	 * @param reporteMailService the reporteMailService to set
	 */
	public void setReporteMailService(BaseMailService reporteMailService) {
		this.reporteMailService = reporteMailService;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the mensajeErrorMail
	 */
	public String getMensajeErrorMail() {
		return mensajeErrorMail;
	}

	/**
	 * @param mensajeErrorMail the mensajeErrorMail to set
	 */
	public void setMensajeErrorMail(String mensajeErrorMail) {
		this.mensajeErrorMail = mensajeErrorMail;
	}
	

	
	
}
