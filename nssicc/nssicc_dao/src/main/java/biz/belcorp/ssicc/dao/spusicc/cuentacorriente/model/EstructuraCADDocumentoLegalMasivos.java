/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model;

import java.io.Serializable;

/**
 * @author pejflorencio - Jorge Florencio
 *
 */
public class EstructuraCADDocumentoLegalMasivos implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private String codigoConsultora;	
	private String numeroBoletaDespachoReferencia;
	private String codigoProducto;
	private Double importeMovimiento;
	private String importeMovimientoValidacion;
	private String numeroLote;
	private String codigoUsuario;	
	
	
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
	 * @return the importeMovimiento
	 */
	public Double getImporteMovimiento() {
		return importeMovimiento;
	}

	/**
	 * @param importeMovimiento the importeMovimiento to set
	 */
	public void setImporteMovimiento(Double importeMovimiento) {
		this.importeMovimiento = importeMovimiento;
	}
	

	/**
	 * @return the importeMovimientoValidacion
	 */
	public String getImporteMovimientoValidacion() {
		return importeMovimientoValidacion;
	}

	/**
	 * @param importeMovimientoValidacion the importeMovimientoValidacion to set
	 */
	public void setImporteMovimientoValidacion(String importeMovimientoValidacion) {
		this.importeMovimientoValidacion = importeMovimientoValidacion;
	}

	/**
	 * @return the numeroBoletaDespachoReferencia
	 */
	public String getNumeroBoletaDespachoReferencia() {
		return numeroBoletaDespachoReferencia;
	}

	/**
	 * @param numeroBoletaDespachoReferencia the numeroBoletaDespachoReferencia to set
	 */
	public void setNumeroBoletaDespachoReferencia(
			String numeroBoletaDespachoReferencia) {
		this.numeroBoletaDespachoReferencia = numeroBoletaDespachoReferencia;
	}

	/**
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
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
