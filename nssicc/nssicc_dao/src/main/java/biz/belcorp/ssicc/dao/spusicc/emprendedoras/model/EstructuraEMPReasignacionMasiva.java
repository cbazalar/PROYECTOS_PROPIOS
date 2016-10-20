package biz.belcorp.ssicc.dao.spusicc.emprendedoras.model;

import java.io.Serializable;

/**
 * @author <a href="mailto:dooliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */

public class EstructuraEMPReasignacionMasiva implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
			
	private String codigoConsultora;
	private String codigoNuevaEmprendedora;
	private int fila;
	private String codigoUsuario;
		
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
	 * @return the codigoNuevaEmprendedora
	 */
	public String getCodigoNuevaEmprendedora() {
		return codigoNuevaEmprendedora;
	}
	/**
	 * @param codigoNuevaEmprendedora the codigoNuevaEmprendedora to set
	 */
	public void setCodigoNuevaEmprendedora(String codigoNuevaEmprendedora) {
		this.codigoNuevaEmprendedora = codigoNuevaEmprendedora;
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


}
