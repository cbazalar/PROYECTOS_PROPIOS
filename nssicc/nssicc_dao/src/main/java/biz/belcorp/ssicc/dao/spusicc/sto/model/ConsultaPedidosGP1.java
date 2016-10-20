package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;

/**
 * @author Jos Luis Rodriguez
 */
public class ConsultaPedidosGP1 implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer correctas;
	private String codigoCliente;
	private String codigoPeriodo;
	private String codigoRegion;
	private String codigoZona;
	private String descripcionRegion;
	private String fechaProgramadaFacturacion;
	private String oidSolicabec;
	private Integer numGP2;
	private Integer numGP3;
	
	/**
	 * @return the correctas
	 */
	public Integer getCorrectas() {
		return correctas;
	}
	/**
	 * @return the oidSolicabec
	 */
	public String getOidSolicabec() {
		return oidSolicabec;
	}
	/**
	 * @param oidSolicabec the oidSolicabec to set
	 */
	public void setOidSolicabec(String oidSolicabec) {
		this.oidSolicabec = oidSolicabec;
	}
	/**
	 * @param correctas the correctas to set
	 */
	public void setCorrectas(Integer correctas) {
		this.correctas = correctas;
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
	 * @return the fechaProgramadaFacturacion
	 */
	public String getFechaProgramadaFacturacion() {
		return fechaProgramadaFacturacion;
	}
	/**
	 * @param fechaProgramadaFacturacion the fechaProgramadaFacturacion to set
	 */
	public void setFechaProgramadaFacturacion(String fechaProgramadaFacturacion) {
		this.fechaProgramadaFacturacion = fechaProgramadaFacturacion;
	}
	/**
	 * @return the numGP2
	 */
	public Integer getNumGP2() {
		return numGP2;
	}
	/**
	 * @param numGP2 the numGP2 to set
	 */
	public void setNumGP2(Integer numGP2) {
		this.numGP2 = numGP2;
	}
	/**
	 * @return the numGP3
	 */
	public Integer getNumGP3() {
		return numGP3;
	}
	/**
	 * @param numGP3 the numGP3 to set
	 */
	public void setNumGP3(Integer numGP3) {
		this.numGP3 = numGP3;
	}
	
}
