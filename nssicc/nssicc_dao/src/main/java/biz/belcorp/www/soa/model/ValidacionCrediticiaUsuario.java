/**
 * 
 */
package biz.belcorp.www.soa.model;

import java.io.Serializable;

/**
 * @author Danny Amaro
 *
 */
public class ValidacionCrediticiaUsuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7324455263963288416L;

	private String codigoCliente;
	private String apellido1;
	private String apellido2;
	private String nombre1;
	private String nombre2;
	private String deudaBelcorp;
	private String deudaTerceros;
	private String estadoCliente;
	private String simboloMoneda;
	private String descripcionMoneda;
	private String codigoRegion;
	private String codigoZona;
	private String codigoSeccion;
	
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getNombre1() {
		return nombre1;
	}
	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}
	public String getNombre2() {
		return nombre2;
	}
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}
	public String getDeudaBelcorp() {
		return deudaBelcorp;
	}
	public void setDeudaBelcorp(String deudaBelcorp) {
		this.deudaBelcorp = deudaBelcorp;
	}
	public String getDeudaTerceros() {
		return deudaTerceros;
	}
	public void setDeudaTerceros(String deudaTerceros) {
		this.deudaTerceros = deudaTerceros;
	}
	public String getEstadoCliente() {
		return estadoCliente;
	}
	public void setEstadoCliente(String estadoCliente) {
		this.estadoCliente = estadoCliente;
	}
	public String getSimboloMoneda() {
		return simboloMoneda;
	}
	public void setSimboloMoneda(String simboloMoneda) {
		this.simboloMoneda = simboloMoneda;
	}
	public String getDescripcionMoneda() {
		return descripcionMoneda;
	}
	public void setDescripcionMoneda(String descripcionMoneda) {
		this.descripcionMoneda = descripcionMoneda;
	}	
	public String getCodigoRegion() {
		return codigoRegion;
	}
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	public String getCodigoZona() {
		return codigoZona;
	}
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	public String getCodigoSeccion() {
		return codigoSeccion;
	}
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}	
	
	
}
