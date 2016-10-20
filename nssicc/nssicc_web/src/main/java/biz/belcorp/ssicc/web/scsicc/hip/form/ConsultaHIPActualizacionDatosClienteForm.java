package biz.belcorp.ssicc.web.scsicc.hip.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan Altamirano</a>
 * 
 */
public class ConsultaHIPActualizacionDatosClienteForm extends BaseSearchForm {
	private static final long serialVersionUID = 1L;

	private String codCliente;
	private String nomCliente;
	private String desRegZonTerri;
	private String telefono;
	private String celular;
	private String email;
	private Date fechaNacimiento;
	private String primerApellido;
	private String segundoApellido;
	private String primerNombre;
	private String segundoNombre;
	private String documento;
	private String validarCaracteres1;
	private String validarCaracteres2;
	private String validarCaracteres3;
	private String cadenaCaracteresV1;
	private String cadenaCaracteresNV1;
	private String cadenaCaracteresV2;
	private String cadenaCaracteresNV2;
	private String cadenaCaracteresV3;
	private String cadenaCaracteresNV3;
	private boolean graboOK;
	
	private String fechaActual;

	private String edadMinima;
	private String edadMaxima;
	
	

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public String getNomCliente() {
		return nomCliente;
	}

	public void setNomCliente(String nomCliente) {
		this.nomCliente = nomCliente;
	}

	public String getDesRegZonTerri() {
		return desRegZonTerri;
	}

	public void setDesRegZonTerri(String desRegZonTerri) {
		this.desRegZonTerri = desRegZonTerri;
	}
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getValidarCaracteres1() {
		return validarCaracteres1;
	}

	public void setValidarCaracteres1(String validarCaracteres1) {
		this.validarCaracteres1 = validarCaracteres1;
	}

	public String getValidarCaracteres2() {
		return validarCaracteres2;
	}

	public void setValidarCaracteres2(String validarCaracteres2) {
		this.validarCaracteres2 = validarCaracteres2;
	}

	public String getValidarCaracteres3() {
		return validarCaracteres3;
	}

	public void setValidarCaracteres3(String validarCaracteres3) {
		this.validarCaracteres3 = validarCaracteres3;
	}

	public String getCadenaCaracteresV1() {
		return cadenaCaracteresV1;
	}

	public void setCadenaCaracteresV1(String cadenaCaracteresV1) {
		this.cadenaCaracteresV1 = cadenaCaracteresV1;
	}

	public String getCadenaCaracteresNV1() {
		return cadenaCaracteresNV1;
	}

	public void setCadenaCaracteresNV1(String cadenaCaracteresNV1) {
		this.cadenaCaracteresNV1 = cadenaCaracteresNV1;
	}

	public String getCadenaCaracteresV2() {
		return cadenaCaracteresV2;
	}

	public void setCadenaCaracteresV2(String cadenaCaracteresV2) {
		this.cadenaCaracteresV2 = cadenaCaracteresV2;
	}

	public String getCadenaCaracteresNV2() {
		return cadenaCaracteresNV2;
	}

	public void setCadenaCaracteresNV2(String cadenaCaracteresNV2) {
		this.cadenaCaracteresNV2 = cadenaCaracteresNV2;
	}

	public String getCadenaCaracteresV3() {
		return cadenaCaracteresV3;
	}

	public void setCadenaCaracteresV3(String cadenaCaracteresV3) {
		this.cadenaCaracteresV3 = cadenaCaracteresV3;
	}

	public String getCadenaCaracteresNV3() {
		return cadenaCaracteresNV3;
	}

	public void setCadenaCaracteresNV3(String cadenaCaracteresNV3) {
		this.cadenaCaracteresNV3 = cadenaCaracteresNV3;
	}

	public boolean isGraboOK() {
		return graboOK;
	}

	public void setGraboOK(boolean graboOK) {
		this.graboOK = graboOK;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the fechaActual
	 */
	public String getFechaActual() {
		return fechaActual;
	}

	/**
	 * @param fechaActual the fechaActual to set
	 */
	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}

	/**
	 * @return the edadMinima
	 */
	public String getEdadMinima() {
		return edadMinima;
	}

	/**
	 * @param edadMinima the edadMinima to set
	 */
	public void setEdadMinima(String edadMinima) {
		this.edadMinima = edadMinima;
	}

	/**
	 * @return the edadMaxima
	 */
	public String getEdadMaxima() {
		return edadMaxima;
	}

	/**
	 * @param edadMaxima the edadMaxima to set
	 */
	public void setEdadMaxima(String edadMaxima) {
		this.edadMaxima = edadMaxima;
	}	
	
	

}
