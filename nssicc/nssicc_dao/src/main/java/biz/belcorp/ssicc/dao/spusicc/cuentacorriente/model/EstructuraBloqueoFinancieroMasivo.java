/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model;

import java.io.Serializable;

/**
 * @author pejflorencio - Jorge Florencio
 *
 */
public class EstructuraBloqueoFinancieroMasivo implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private String codigoConsultora;		
	private String numeroLote;
	private String codigoTipoAccion;
	private String codigoTipoBloqueo;
	private String valorMotivoBloqueo;
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
	 * @return the codigoTipoAccion
	 */
	public String getCodigoTipoAccion() {
		return codigoTipoAccion;
	}

	/**
	 * @param codigoTipoAccion the codigoTipoAccion to set
	 */
	public void setCodigoTipoAccion(String codigoTipoAccion) {
		this.codigoTipoAccion = codigoTipoAccion;
	}

	/**
	 * @return the codigoTipoBloqueo
	 */
	public String getCodigoTipoBloqueo() {
		return codigoTipoBloqueo;
	}

	/**
	 * @param codigoTipoBloqueo the codigoTipoBloqueo to set
	 */
	public void setCodigoTipoBloqueo(String codigoTipoBloqueo) {
		this.codigoTipoBloqueo = codigoTipoBloqueo;
	}

	/**
	 * @return the valorMotivoBloqueo
	 */
	public String getValorMotivoBloqueo() {
		return valorMotivoBloqueo;
	}

	/**
	 * @param valorMotivoBloqueo the valorMotivoBloqueo to set
	 */
	public void setValorMotivoBloqueo(String valorMotivoBloqueo) {
		this.valorMotivoBloqueo = valorMotivoBloqueo;
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
