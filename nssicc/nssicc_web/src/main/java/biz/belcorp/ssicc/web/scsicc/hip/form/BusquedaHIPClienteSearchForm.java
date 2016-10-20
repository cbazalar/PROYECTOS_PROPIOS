package biz.belcorp.ssicc.web.scsicc.hip.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="BusquedaHIPClienteSearchForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */

public class BusquedaHIPClienteSearchForm extends BaseSearchForm implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 9035833047705655810L;
	
	private String codigoCliente;	
	private String tipoDocIdentidad;
    private String numeroDocIdentidad;	
    private String nombre1;	
    private String nombre2;	
	private String apellido1;	
	private String apellido2;
	
	private String oidIdioma;
	private String longitudCodigoCliente;
	
	private String codigoPais;
	private String codigoRegion;
	private String codigoZona;
	private String criterio1;
	private String criterio2;
	
	/**
	 * @return Returns the apellido1.
	 */
	
	
	public String getApellido1() {
		return apellido1;
	}
	public String getCriterio1() {
		return criterio1;
	}
	public void setCriterio1(String criterio1) {
		this.criterio1 = criterio1;
	}
	public String getCriterio2() {
		return criterio2;
	}
	public void setCriterio2(String criterio2) {
		this.criterio2 = criterio2;
	}
	/**
	 * @param apellido1 The apellido1 to set.
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	/**
	 * @return Returns the apellido2.
	 */
	public String getApellido2() {
		return apellido2;
	}
	/**
	 * @param apellido2 The apellido2 to set.
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
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
	 * @return Returns the nombre1.
	 */
	public String getNombre1() {
		return nombre1;
	}
	/**
	 * @param nombre1 The nombre1 to set.
	 */
	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}
	/**
	 * @return Returns the nombre2.
	 */
	public String getNombre2() {
		return nombre2;
	}
	/**
	 * @param nombre2 The nombre2 to set.
	 */
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}
	/**
	 * @return Returns the numeroDocIdentidad.
	 */
	public String getNumeroDocIdentidad() {
		return numeroDocIdentidad;
	}
	/**
	 * @param numeroDocIdentidad The numeroDocIdentidad to set.
	 */
	public void setNumeroDocIdentidad(String numeroDocIdentidad) {
		this.numeroDocIdentidad = numeroDocIdentidad;
	}
	/**
	 * @return Returns the tipoDocIdentidad.
	 */
	public String getTipoDocIdentidad() {
		return tipoDocIdentidad;
	}
	/**
	 * @param tipoDocIdentidad The tipoDocIdentidad to set.
	 */
	public void setTipoDocIdentidad(String tipoDocIdentidad) {
		this.tipoDocIdentidad = tipoDocIdentidad;
	}
	/**
	 * @return Returns the oidIdioma.
	 */
	public String getOidIdioma() {
		return oidIdioma;
	}
	/**
	 * @param oidIdioma The oidIdioma to set.
	 */
	public void setOidIdioma(String oidIdioma) {
		this.oidIdioma = oidIdioma;
	}
	/**
	 * @return the longitudCodigoCliente
	 */
	public String getLongitudCodigoCliente() {
		return longitudCodigoCliente;
	}
	/**
	 * @param longitudCodigoCliente the longitudCodigoCliente to set
	 */
	public void setLongitudCodigoCliente(String longitudCodigoCliente) {
		this.longitudCodigoCliente = longitudCodigoCliente;
	}
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
	   
}
