/**
 * 
 */
package biz.belcorp.ssicc.reportes.framework.bean;

import java.io.Serializable;

/**
 * @author Danny Amaro
 *
 */
public class ReporteEnviadoMail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6227532341992401546L;
	
	protected String nombreReporte;
	protected String region;
	protected String zona;
	protected String emailDestino;
	protected String estado;
	protected String indicadorLog;
	protected String descripcionLog;
	protected String valorFiltro;
	protected String usuarioLogin;
	
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
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return the zona
	 */
	public String getZona() {
		return zona;
	}
	/**
	 * @param zona the zona to set
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}
	/**
	 * @return the emailDestino
	 */
	public String getEmailDestino() {
		return emailDestino;
	}
	/**
	 * @param emailDestino the emailDestino to set
	 */
	public void setEmailDestino(String emailDestino) {
		this.emailDestino = emailDestino;
	}
	
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getIndicadorLog() {
		return indicadorLog;
	}
	public void setIndicadorLog(String indicadorLog) {
		this.indicadorLog = indicadorLog;
	}
	
	public String getDescripcionLog() {
		return descripcionLog;
	}
	public void setDescripcionLog(String descripcionLog) {
		this.descripcionLog = descripcionLog;
	}
	public String getValorFiltro() {
		return valorFiltro;
	}
	public void setValorFiltro(String valorFiltro) {
		this.valorFiltro = valorFiltro;
	}
	public String getUsuarioLogin() {
		return usuarioLogin;
	}
	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

}
