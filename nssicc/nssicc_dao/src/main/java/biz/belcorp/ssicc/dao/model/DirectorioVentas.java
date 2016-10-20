/**
 * 
 */
package biz.belcorp.ssicc.dao.model;

/**
 * @author COMMACIAS
 *
 */
public class DirectorioVentas {

	private String consultora;
	private String regiones;
	private String zona;
	private String cargo;
	private String telefono;
	private String direccion;
	private String nombreCliente;
	private String codigoPais;
	private String campana;
	
	public String getCampana() {
		return campana;
	}
	public void setCampana(String campana) {
		this.campana = campana;
	}
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getConsultora() {
		return consultora;
	}
	public void setConsultora(String consultora) {
		this.consultora = consultora;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getRegiones() {
		return regiones;
	}
	public void setRegiones(String region) {
		this.regiones = region;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
}
