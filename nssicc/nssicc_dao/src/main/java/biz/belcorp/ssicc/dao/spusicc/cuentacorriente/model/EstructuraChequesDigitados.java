/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model;

import java.io.Serializable;

/**
 * @author pejflorencio - Jorge Florencio
 *
 */
public class EstructuraChequesDigitados implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private String numeroLote;
	private String codigoBanco;
	private String codigoConsultora;
	private String fechaCobro;
	private Double importeCheque;	
	
	private String codigoUsuario;

	/**
	 * @return
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return
	 */
	public String getCodigoBanco() {
		return codigoBanco;
	}

	/**
	 * @param codigoBanco
	 */
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	/**
	 * @return
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	/**
	 * @return
	 */
	public String getFechaCobro() {
		return fechaCobro;
	}

	/**
	 * @param fechaCobro
	 */
	public void setFechaCobro(String fechaCobro) {
		this.fechaCobro = fechaCobro;
	}

	/**
	 * @return
	 */
	public Double getImporteCheque() {
		return importeCheque;
	}

	/**
	 * @param importeCheque
	 */
	public void setImporteCheque(Double importeCheque) {
		this.importeCheque = importeCheque;
	}

	/**
	 * @return
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
	
	

}
