package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;

/**
 * 
 */
public class ConsultaPolizas implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoTipoDocumento;
	private String fechaCarga;
	private String fechaModificacion;
	private String numeroLote;
	private String codigoRegion;
	private String descripcionRegion;
	private String codigoZona;
	private String codigoCliente;
	private String codigoPeriodo;
	private Integer cargadas;
	private Integer enviadas;
	private Integer erradas;
	private Integer rechazadas;
	
	/**
	 * @return the fechaCarga
	 */
	public String getFechaCarga() {
		return fechaCarga;
	}
	/**
	 * @param fechaCarga the fechaCarga to set
	 */
	public void setFechaCarga(String fechaCarga) {
		this.fechaCarga = fechaCarga;
	}
	/**
	 * @return the fechaModificacion
	 */
	public String getFechaModificacion() {
		return fechaModificacion;
	}
	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
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
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return the descripcionRegion
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}
	/**
	 * @param descripcionRegion the descripcionRegion to set
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}
	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the cargadas
	 */
	public Integer getCargadas() {
		return cargadas;
	}
	/**
	 * @param cargadas the cargadas to set
	 */
	public void setCargadas(Integer cargadas) {
		this.cargadas = cargadas;
	}
	/**
	 * @return the enviadas
	 */
	public Integer getEnviadas() {
		return enviadas;
	}
	/**
	 * @param enviadas the enviadas to set
	 */
	public void setEnviadas(Integer enviadas) {
		this.enviadas = enviadas;
	}
	/**
	 * @return the erradas
	 */
	public Integer getErradas() {
		return erradas;
	}
	/**
	 * @param erradas the erradas to set
	 */
	public void setErradas(Integer erradas) {
		this.erradas = erradas;
	}
	/**
	 * @return the rechazadas
	 */
	public Integer getRechazadas() {
		return rechazadas;
	}
	/**
	 * @param rechazadas the rechazadas to set
	 */
	public void setRechazadas(Integer rechazadas) {
		this.rechazadas = rechazadas;
	}
	/**
	 * @return the codigoTipoDocumento
	 */
	public String getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}
	/**
	 * @param codigoTipoDocumento the codigoTipoDocumento to set
	 */
	public void setCodigoTipoDocumento(String codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}

}