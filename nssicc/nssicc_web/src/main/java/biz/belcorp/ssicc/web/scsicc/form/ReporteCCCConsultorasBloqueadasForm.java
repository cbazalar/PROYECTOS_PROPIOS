package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCCCConsultorasBloqueadasForm extends BaseReporteForm implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoSociedad;
			
	private String tipoBloqueo;
	
	private String tipoEstado;
				
	private String fechaBloqueoDesde;
	
	private String fechaBloqueoHasta;

	private String fechaDesbloqueoDesde;
	
	private String fechaDesbloqueoHasta;
	
	private Date fechaBloqueoDesdeD;
	
	private Date fechaBloqueoHastaD;

	private Date fechaDesbloqueoDesdeD;
	
	private Date fechaDesbloqueoHastaD;
	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoSociedad.
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @param codigoSociedad
	 *            The codigoSociedad to set.
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
						
	/**
	 * @return Returns the fechaBloqueoHasta.
	 */
	public String getFechaBloqueoHasta() {
		return fechaBloqueoHasta;
	}

	/**
	 * @param fechaBloqueoHasta
	 *            The fechaBloqueoHasta to set.
	 * 
	 */
	public void setFechaBloqueoHasta(String fechaBloqueoHasta) {
		this.fechaBloqueoHasta = fechaBloqueoHasta;
	}

	/**
	 * @return the tipoBloqueo
	 */
	public String getTipoBloqueo() {
		return tipoBloqueo;
	}

	/**
	 * @param tipoBloqueo the tipoBloqueo to set
	 */
	public void setTipoBloqueo(String tipoBloqueo) {
		this.tipoBloqueo = tipoBloqueo;
	}

	/**
	 * @return the tipoEstado
	 */
	public String getTipoEstado() {
		return tipoEstado;
	}

	/**
	 * @param tipoEstado the tipoEstado to set
	 */
	public void setTipoEstado(String tipoEstado) {
		this.tipoEstado = tipoEstado;
	}

	/**
	 * @return the fechaBloqueoDesde
	 */
	public String getFechaBloqueoDesde() {
		return fechaBloqueoDesde;
	}

	/**
	 * @param fechaBloqueoDesde the fechaBloqueoDesde to set
	 */
	public void setFechaBloqueoDesde(String fechaBloqueoDesde) {
		this.fechaBloqueoDesde = fechaBloqueoDesde;
	}

	/**
	 * @return the fechaDesbloqueoDesde
	 */
	public String getFechaDesbloqueoDesde() {
		return fechaDesbloqueoDesde;
	}

	/**
	 * @param fechaDesbloqueoDesde the fechaDesbloqueoDesde to set
	 */
	public void setFechaDesbloqueoDesde(String fechaDesbloqueoDesde) {
		this.fechaDesbloqueoDesde = fechaDesbloqueoDesde;
	}

	/**
	 * @return the fechaDesbloqueoHasta
	 */
	public String getFechaDesbloqueoHasta() {
		return fechaDesbloqueoHasta;
	}

	/**
	 * @param fechaDesbloqueoHasta the fechaDesbloqueoHasta to set
	 */
	public void setFechaDesbloqueoHasta(String fechaDesbloqueoHasta) {
		this.fechaDesbloqueoHasta = fechaDesbloqueoHasta;
	}

	/**
	 * @return the fechaBloqueoDesdeD
	 */
	public Date getFechaBloqueoDesdeD() {
		return fechaBloqueoDesdeD;
	}

	/**
	 * @param fechaBloqueoDesdeD the fechaBloqueoDesdeD to set
	 */
	public void setFechaBloqueoDesdeD(Date fechaBloqueoDesdeD) {
		this.fechaBloqueoDesdeD = fechaBloqueoDesdeD;
	}

	/**
	 * @return the fechaBloqueoHastaD
	 */
	public Date getFechaBloqueoHastaD() {
		return fechaBloqueoHastaD;
	}

	/**
	 * @param fechaBloqueoHastaD the fechaBloqueoHastaD to set
	 */
	public void setFechaBloqueoHastaD(Date fechaBloqueoHastaD) {
		this.fechaBloqueoHastaD = fechaBloqueoHastaD;
	}

	/**
	 * @return the fechaDesbloqueoDesdeD
	 */
	public Date getFechaDesbloqueoDesdeD() {
		return fechaDesbloqueoDesdeD;
	}

	/**
	 * @param fechaDesbloqueoDesdeD the fechaDesbloqueoDesdeD to set
	 */
	public void setFechaDesbloqueoDesdeD(Date fechaDesbloqueoDesdeD) {
		this.fechaDesbloqueoDesdeD = fechaDesbloqueoDesdeD;
	}

	/**
	 * @return the fechaDesbloqueoHastaD
	 */
	public Date getFechaDesbloqueoHastaD() {
		return fechaDesbloqueoHastaD;
	}

	/**
	 * @param fechaDesbloqueoHastaD the fechaDesbloqueoHastaD to set
	 */
	public void setFechaDesbloqueoHastaD(Date fechaDesbloqueoHastaD) {
		this.fechaDesbloqueoHastaD = fechaDesbloqueoHastaD;
	}
}