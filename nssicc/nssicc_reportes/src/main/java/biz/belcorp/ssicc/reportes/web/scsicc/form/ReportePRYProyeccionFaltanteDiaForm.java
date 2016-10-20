package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */

public class ReportePRYProyeccionFaltanteDiaForm extends BaseReporteForm	implements Serializable{
	
			
	/**
	 * 
	 */
	private static final long serialVersionUID = -3989305858472599989L;
	private String codigoPais;
	private String fechaFacturacion;
	private Date fechaFacturacionD;
	private String nroProductos;
	private String presentacion;
	private String   numeroVersion;

	public ReportePRYProyeccionFaltanteDiaForm() {
		this.nroProductos = "10";
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
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	/**
	 * @return the fechaFacturacionD
	 */
	public Date getFechaFacturacionD() {
		return fechaFacturacionD;
	}

	/**
	 * @param fechaFacturacionD the fechaFacturacionD to set
	 */
	public void setFechaFacturacionD(Date fechaFacturacionD) {
		this.fechaFacturacionD = fechaFacturacionD;
	}

	/**
	 * @return the nroProductos
	 */
	public String getNroProductos() {
		return nroProductos;
	}

	/**
	 * @param nroProductos the nroProductos to set
	 */
	public void setNroProductos(String nroProductos) {
		this.nroProductos = nroProductos;
	}

	
	/**
	 * @return the numeroVersion
	 */
	public String getNumeroVersion() {
		return numeroVersion;
	}

	/**
	 * @param numeroVersion the numeroVersion to set
	 */
	public void setNumeroVersion(String numeroVersion) {
		this.numeroVersion = numeroVersion;
	}

	/**
	 * @return the presentacion
	 */
	public String getPresentacion() {
		return presentacion;
	}

	/**
	 * @param presentacion the presentacion to set
	 */
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}
	
	
	
	

}

