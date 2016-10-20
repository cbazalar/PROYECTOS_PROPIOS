package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;

/**
 * @author Jose Luis Rodriguez
 */

public class LineaArmado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6537155206336595946L;
	private String codigoPais;
	private String codigoCentroDistribucion;
	private String oidLineaArmado;
	private String codigoLineaArmado;
	private String descripcionLineaArmado;
	private String codigoCubicaje;
	private String[] codigoTipoSolicitudConso;
	private String codigoPlataforma;
	private String usuarioAlarma;
	private String indicadorLineaAFP;
	private String indicadorLineaDefecto;
	private String numeroCopias;
	private String codigoLineaAframe;
	
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
	 * @return the codigoCentroDistribucion
	 */
	public String getCodigoCentroDistribucion() {
		return codigoCentroDistribucion;
	}
	/**
	 * @param codigoCentroDistribucion the codigoCentroDistribucion to set
	 */
	public void setCodigoCentroDistribucion(String codigoCentroDistribucion) {
		this.codigoCentroDistribucion = codigoCentroDistribucion;
	}
	/**
	 * @return the codigoLineaArmado
	 */
	public String getCodigoLineaArmado() {
		return codigoLineaArmado;
	}
	/**
	 * @param codigoLineaArmado the codigoLineaArmado to set
	 */
	public void setCodigoLineaArmado(String codigoLineaArmado) {
		this.codigoLineaArmado = codigoLineaArmado;
	}
	/**
	 * @return the descripcionLineaArmado
	 */
	public String getDescripcionLineaArmado() {
		return descripcionLineaArmado;
	}
	/**
	 * @param descripcionLineaArmado the descripcionLineaArmado to set
	 */
	public void setDescripcionLineaArmado(String descripcionLineaArmado) {
		this.descripcionLineaArmado = descripcionLineaArmado;
	}
	/**
	 * @return the codigoCubicaje
	 */
	public String getCodigoCubicaje() {
		return codigoCubicaje;
	}
	/**
	 * @param codigoCubicaje the codigoCubicaje to set
	 */
	public void setCodigoCubicaje(String codigoCubicaje) {
		this.codigoCubicaje = codigoCubicaje;
	}
	/**
	 * @return the codigoPlataforma
	 */
	public String getCodigoPlataforma() {
		return codigoPlataforma;
	}
	/**
	 * @param codigoPlataforma the codigoPlataforma to set
	 */
	public void setCodigoPlataforma(String codigoPlataforma) {
		this.codigoPlataforma = codigoPlataforma;
	}
	/**
	 * @return the usuarioAlarma
	 */
	public String getUsuarioAlarma() {
		return usuarioAlarma;
	}
	/**
	 * @param usuarioAlarma the usuarioAlarma to set
	 */
	public void setUsuarioAlarma(String usuarioAlarma) {
		this.usuarioAlarma = usuarioAlarma;
	}
	/**
	 * @return the indicadorLineaAFP
	 */
	public String getIndicadorLineaAFP() {
		return indicadorLineaAFP;
	}
	/**
	 * @param indicadorLineaAFP the indicadorLineaAFP to set
	 */
	public void setIndicadorLineaAFP(String indicadorLineaAFP) {
		this.indicadorLineaAFP = indicadorLineaAFP;
	}
	/**
	 * @return the indicadorLineaDefecto
	 */
	public String getIndicadorLineaDefecto() {
		return indicadorLineaDefecto;
	}
	/**
	 * @param indicadorLineaDefecto the indicadorLineaDefecto to set
	 */
	public void setIndicadorLineaDefecto(String indicadorLineaDefecto) {
		this.indicadorLineaDefecto = indicadorLineaDefecto;
	}
	/**
	 * @return the numeroCopias
	 */
	public String getNumeroCopias() {
		return numeroCopias;
	}
	/**
	 * @param numeroCopias the numeroCopias to set
	 */
	public void setNumeroCopias(String numeroCopias) {
		this.numeroCopias = numeroCopias;
	}
	/**
	 * @return the codigoTipoSolicitudConso
	 */
	public String[] getCodigoTipoSolicitudConso() {
		return codigoTipoSolicitudConso;
	}
	/**
	 * @param codigoTipoSolicitudConso the codigoTipoSolicitudConso to set
	 */
	public void setCodigoTipoSolicitudConso(String[] codigoTipoSolicitudConso) {
		this.codigoTipoSolicitudConso = codigoTipoSolicitudConso;
	}
	
	/**
	 * @return the oidLineaArmado
	 */
	public String getOidLineaArmado() {
		return oidLineaArmado;
	}
	/**
	 * @param oidLineaArmado the oidLineaArmado to set
	 */
	public void setOidLineaArmado(String oidLineaArmado) {
		this.oidLineaArmado = oidLineaArmado;
	}
	
	/**
	 * @return the codigoLineaAframe
	 */
	public String getCodigoLineaAframe() {
		return codigoLineaAframe;
	}
	/**
	 * @param codigoLineaAframe the codigoLineaAframe to set
	 */
	public void setCodigoLineaAframe(String codigoLineaAframe) {
		this.codigoLineaAframe = codigoLineaAframe;
	}
	
}