package biz.belcorp.ssicc.web.spusicc.cobranzas.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoCOBCobradorPaisForm extends BaseEditForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String codigoPais;			
	private String codigoCobrador;	
	private String nombreCobrador;	
	private String email;	
	private String direFTP;	
	private String direReceFTP;	
	private String servFTP;	
	private String puerFTP;	
	private String usuarioFTP;		
	private String claveFTP;	
	private String indicadorActividad = "0";			
	private String indicadorJefe = "0";	
	private String indicadorSupervisor = "0";		
	private String indicadorEmailProcesoAsignacion = "0";	
	private String indicadorEmailProcesoActualizacion = "0";
	private String telefono;
	private String direccion;
			
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}


	/**
	 * @return the codigoCobrador
	 */
	public String getCodigoCobrador() {
		return codigoCobrador;
	}

	/**
	 * @param codigoCobrador the codigoCobrador to set
	 *  @struts.validator type = "required"
	 */
	public void setCodigoCobrador(String codigoCobrador) {
		this.codigoCobrador = codigoCobrador;
	}

	/**
	 * @return the nombreCobrador
	 */
	public String getNombreCobrador() {
		return nombreCobrador;
	}

	/**
	 * @param nombreCobrador the nombreCobrador to set
	 */
	public void setNombreCobrador(String nombreCobrador) {
		this.nombreCobrador = nombreCobrador;
	}

	/**
	 * @return the indicadorActividad
	 */
	public String getIndicadorActividad() {
		return indicadorActividad;
	}

	/**
	 * @param indicadorActividad the indicadorActividad to set
	 */
	public void setIndicadorActividad(String indicadorActividad) {
		this.indicadorActividad = indicadorActividad;
	}

	/**
	 * @return the indicadorJefe
	 */
	public String getIndicadorJefe() {
		return indicadorJefe;
	}

	/**
	 * @param indicadorJefe the indicadorJefe to set
	 */
	public void setIndicadorJefe(String indicadorJefe) {
		this.indicadorJefe = indicadorJefe;
	}

	/**
	 * @return the indicadorSupervisor
	 */
	public String getIndicadorSupervisor() {
		return indicadorSupervisor;
	}

	/**
	 * @param indicadorSupervisor the indicadorSupervisor to set
	 */
	public void setIndicadorSupervisor(String indicadorSupervisor) {
		this.indicadorSupervisor = indicadorSupervisor;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the usuarioFTP
	 */
	public String getUsuarioFTP() {
		return usuarioFTP;
	}

	/**
	 * @param usuarioFTP the usuarioFTP to set
	 */
	public void setUsuarioFTP(String usuarioFTP) {
		this.usuarioFTP = usuarioFTP;
	}

	/**
	 * @return the claveFTP
	 */
	public String getClaveFTP() {
		return claveFTP;
	}

	/**
	 * @param claveFTP the claveFTP to set
	 */
	public void setClaveFTP(String claveFTP) {
		this.claveFTP = claveFTP;
	}

	/**
	 * @return the indicadorEmailProcesoAsignacion
	 */
	public String getIndicadorEmailProcesoAsignacion() {
		return indicadorEmailProcesoAsignacion;
	}

	/**
	 * @param indicadorEmailProcesoAsignacion the indicadorEmailProcesoAsignacion to set
	 */
	public void setIndicadorEmailProcesoAsignacion(
			String indicadorEmailProcesoAsignacion) {
		this.indicadorEmailProcesoAsignacion = indicadorEmailProcesoAsignacion;
	}

	/**
	 * @return the indicadorEmailProcesoActualizacion
	 */
	public String getIndicadorEmailProcesoActualizacion() {
		return indicadorEmailProcesoActualizacion;
	}

	/**
	 * @param indicadorEmailProcesoActualizacion the indicadorEmailProcesoActualizacion to set
	 */
	public void setIndicadorEmailProcesoActualizacion(
			String indicadorEmailProcesoActualizacion) {
		this.indicadorEmailProcesoActualizacion = indicadorEmailProcesoActualizacion;
	}

	/**
	 * @return the direFTP
	 */
	public String getDireFTP() {
		return direFTP;
	}

	/**
	 * @param direFTP the direFTP to set
	 */
	public void setDireFTP(String direFTP) {
		this.direFTP = direFTP;
	}

	/**
	 * @return the direReceFTP
	 */
	public String getDireReceFTP() {
		return direReceFTP;
	}

	/**
	 * @param direReceFTP the direReceFTP to set
	 */
	public void setDireReceFTP(String direReceFTP) {
		this.direReceFTP = direReceFTP;
	}

	/**
	 * @return the servFTP
	 */
	public String getServFTP() {
		return servFTP;
	}

	/**
	 * @param servFTP the servFTP to set
	 */
	public void setServFTP(String servFTP) {
		this.servFTP = servFTP;
	}

	/**
	 * @return the puerFTP
	 */
	public String getPuerFTP() {
		return puerFTP;
	}

	/**
	 * @param puerFTP the puerFTP to set
	 */
	public void setPuerFTP(String puerFTP) {
		this.puerFTP = puerFTP;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}