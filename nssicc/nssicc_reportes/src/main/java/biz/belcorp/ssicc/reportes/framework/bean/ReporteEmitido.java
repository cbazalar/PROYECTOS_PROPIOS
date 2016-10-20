/**
 * 
 */
package biz.belcorp.ssicc.reportes.framework.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Danny Amaro
 *
 */
public class ReporteEmitido implements Serializable{

	private static final long serialVersionUID = 2772717628466064048L;
	
	protected String fileName;
	protected String nombreReporte;

	protected String formato;
	protected String formatoImages;
	
	protected String codigoMenu;
	protected String descripcionMenu;
	
	protected String multiReporte;
	protected String envioMail;
	
	protected boolean showLink;
	
	private Timestamp fechaInicio;
	private Timestamp fechaFin;
	
	private Long duracionSegundos;
	
	public ReporteEmitido(){
		this.showLink = true;
		this.multiReporte = "N";
		this.envioMail = "N";
		this.duracionSegundos = new Long(0);
	}

	/**
	 * @return the multiReporte
	 */
	public String getMultiReporte() {
		return multiReporte;
	}

	/**
	 * @param multiReporte the multiReporte to set
	 */
	public void setMultiReporte(String multiReporte) {
		this.multiReporte = multiReporte;		
		this.showLink =((this.multiReporte.equals("S")||this.envioMail.equals("S"))?false:true);				
	}

	/**
	 * @return the envioMail
	 */
	public String getEnvioMail() {
		return envioMail;
	}

	/**
	 * @param envioMail the envioMail to set
	 */
	public void setEnvioMail(String envioMail) {
		this.envioMail = envioMail;
		this.showLink =((this.multiReporte.equals("S")||this.envioMail.equals("S"))?false:true);		
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	 * @return the formatoImages
	 */
	public String getFormatoImages() {
		return formatoImages;
	}

	/**
	 * @param formatoImages the formatoImages to set
	 */
	public void setFormatoImages(String formatoImages) {
		this.formatoImages = formatoImages;
	}

	/**
	 * @return the codigoMenu
	 */
	public String getCodigoMenu() {
		return codigoMenu;
	}

	/**
	 * @param codigoMenu the codigoMenu to set
	 */
	public void setCodigoMenu(String codigoMenu) {
		this.codigoMenu = codigoMenu;
	}

	/**
	 * @return the descripcionMenu
	 */
	public String getDescripcionMenu() {
		return descripcionMenu;
	}

	/**
	 * @param descripcionMenu the descripcionMenu to set
	 */
	public void setDescripcionMenu(String descripcionMenu) {
		this.descripcionMenu = descripcionMenu;
	}

	/**
	 * @return the formato
	 */
	public String getFormato() {
		return formato;
	}
	
	/**
	 * @return the showLink
	 */
	public boolean isShowLink() {
		return showLink;
	}

	/**
	 * @param showLink the showLink to set
	 */
	public void setShowLink(boolean showLink) {
		this.showLink = showLink;
	}
		
	/**
	 * Seteo de ruta de la imgen del formato
	 * @param formato
	 */
	public void setFormato(String formato) {
		this.formato = formato;
		if(this.formato.equals("XLS")){
			this.formatoImages = "/resources/images/excel.png";
		}else if(this.formato.equals("XLSX")){
			this.formatoImages = "/resources/images/excel-xlsx.jpg";
		}else if(this.formato.equals("CSV")){
			this.formatoImages = "/resources/images/csv.png";
		}else{
			this.formatoImages = "/resources/images/pdf.png";
		}		
	}

	/**
	 * @return the fechaInicio
	 */
	public Timestamp getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public Timestamp getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the duracionSegundos
	 */
	public Long getDuracionSegundos() {
		return duracionSegundos;
	}

	/**
	 * @param duracionSegundos the duracionSegundos to set
	 */
	public void setDuracionSegundos(Long duracionSegundos) {
		this.duracionSegundos = duracionSegundos;
	}
	
	
	
}
