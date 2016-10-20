/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model;

import java.io.Serializable;

/**
 * @author pejflorencio - Jorge Florencio
 *
 */
public class EstructuraPagoExterno implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private String numeroLote;
	private String codigoBanco;
	private String codigoConsultora;
	private String fechaPago;
	private Double importePago;	
	
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
	public String getFechaPago() {
		return fechaPago;
	}

	/**
	 * @param fechaPago
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * @return
	 */
	public Double getImportePago() {
		return importePago;
	}

	/**
	 * @param importePago
	 */
	public void setImportePago(Double importePago) {
		this.importePago = importePago;
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
