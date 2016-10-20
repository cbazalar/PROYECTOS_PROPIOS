/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.percepciones.model;

import java.io.Serializable;

/**
 * @author pejflorencio - Jorge Florencio
 *
 */
public class EstructuraPercepcionesPagosBancariosMasivos implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private String codigoConsultora;
	private String fechaPago;
	private Double importePago;
	private String importePagoValidacion;
	
	private int fila;

	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}
	

	/**
	 * @return the fechaPago
	 */
	public String getFechaPago() {
		return fechaPago;
	}

	/**
	 * @param fechaPago the fechaPago to set
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * @return the importePago
	 */
	public Double getImportePago() {
		return importePago;
	}

	/**
	 * @param importePago the importePago to set
	 */
	public void setImportePago(Double importePago) {
		this.importePago = importePago;
	}
	
	/**
	 * @return the importePagoValidacion
	 */
	public String getImportePagoValidacion() {
		return importePagoValidacion;
	}

	/**
	 * @param importePagoValidacion the importePagoValidacion to set
	 */
	public void setImportePagoValidacion(String importePagoValidacion) {
		this.importePagoValidacion = importePagoValidacion;
	}
	
	/**
	 * @return the fila
	 */
	public int getFila() {
		return fila;
	}

	/**
	 * @param fila the fila to set
	 */
	public void setFila(int fila) {
		this.fila = fila;
	}
	
	
	


}
