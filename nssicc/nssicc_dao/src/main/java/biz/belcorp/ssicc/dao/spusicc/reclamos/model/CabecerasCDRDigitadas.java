package biz.belcorp.ssicc.dao.spusicc.reclamos.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextcroman@belcorp.biz
 * 
 */
public class CabecerasCDRDigitadas extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoPais;               
    private String cdr;           
    private String codigoPeriodo;             
    private String boletaDespacho; 
    private String codigoCliente;            
    private String nombreCliente;            
    private String region;                   
    private String zona;                     
    private String fechaIngreso;
    private String usuarioDigitacion;
    private String indExpress;
    private String tipoConsulta;
	/**
	 * @return Returns the boletaDespacho.
	 */
	public String getBoletaDespacho() {
		return boletaDespacho;
	}
	/**
	 * @param boletaDespacho The boletaDespacho to set.
	 */
	public void setBoletaDespacho(String boletaDespacho) {
		this.boletaDespacho = boletaDespacho;
	}
	/**
	 * @return Returns the cdr.
	 */
	public String getCdr() {
		return cdr;
	}
	/**
	 * @param cdr The cdr to set.
	 */
	public void setCdr(String cdr) {
		this.cdr = cdr;
	}
	/**
	 * @return Returns the codigoCliente.
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente The codigoCliente to set.
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return Returns the fechaIngreso.
	 */
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	/**
	 * @param fechaIngreso The fechaIngreso to set.
	 */
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	/**
	 * @return Returns the nombreCliente.
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}
	/**
	 * @param nombreCliente The nombreCliente to set.
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	/**
	 * @return Returns the region.
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region The region to set.
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return Returns the usuarioDigitacion.
	 */
	public String getUsuarioDigitacion() {
		return usuarioDigitacion;
	}
	/**
	 * @param usuarioDigitacion The usuarioDigitacion to set.
	 */
	public void setUsuarioDigitacion(String usuarioDigitacion) {
		this.usuarioDigitacion = usuarioDigitacion;
	}
	/**
	 * @return Returns the zona.
	 */
	public String getZona() {
		return zona;
	}
	/**
	 * @param zona The zona to set.
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}
	
	public String getIndExpress() {
		return indExpress;
	}
	public void setIndExpress(String indExpress) {
		this.indExpress = indExpress;
	}
	
	public String getTipoConsulta() {
		return tipoConsulta;
	}
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CabecerasCDRDigitadas [codigoPais=" + codigoPais + ", cdr="
				+ cdr + ", codigoPeriodo=" + codigoPeriodo
				+ ", boletaDespacho=" + boletaDespacho + ", codigoCliente="
				+ codigoCliente + ", nombreCliente=" + nombreCliente
				+ ", region=" + region + ", zona=" + zona + ", fechaIngreso="
				+ fechaIngreso + ", usuarioDigitacion=" + usuarioDigitacion
				+ ", indExpress=" + indExpress + ", tipoConsulta="
				+ tipoConsulta + "]";
	}

	
}
