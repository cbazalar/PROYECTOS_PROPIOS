package biz.belcorp.ssicc.web.spusicc.mae.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoMAEBloqueoDesbloqueoClienteSearchForm extends BaseSearchForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3290625123834071386L;
	private String codigoPais;
	private String codigoCliente;
	private String nombreCliente;
	private String codigoTipoDocumentoIdentidad;
	private String numeroDocumentoIdentidad;
	private String nombre1;
    private String nombre2;
	private String apellido1;
	private String apellido2;
	private String subTipoCliente;
	private String indicadorSolicitudCredito;
	private boolean graboOK;
	private String codigoUsuario;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}


	/**
	 * @param codigoPais the codigoPais to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}


	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}


	/**
	 * @param codigoClienteBuscar the codigoCliente to set
	 * 
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}


	/**
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}


	/**
	 * @param nombreClienteBuscar the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}


	/**
	 * @return the codigoTipoDocumentoIdentidad
	 */
	public String getCodigoTipoDocumentoIdentidad() {
		return codigoTipoDocumentoIdentidad;
	}


	/**
	 * @param codigoTipoDocumentoIdentidad the codigoTipoDocumentoIdentidad to set
	 */
	public void setCodigoTipoDocumentoIdentidad(String codigoTipoDocumentoIdentidad) {
		this.codigoTipoDocumentoIdentidad = codigoTipoDocumentoIdentidad;
	}


	/**
	 * @return the numeroDocumentoIdentidad
	 */
	public String getNumeroDocumentoIdentidad() {
		return numeroDocumentoIdentidad;
	}


	/**
	 * @param numeroDocumentoIdentidad the numeroDocumentoIdentidad to set
	 */
	public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
		this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
	}

	/**
	 * @return the nombre1
	 */
	public String getNombre1() {
		return nombre1;
	}


	/**
	 * @param nombre1 the nombre1 to set
	 */
	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}


	/**
	 * @return the nombre2
	 */
	public String getNombre2() {
		return nombre2;
	}


	/**
	 * @param nombre2 the nombre2 to set
	 */
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}


	/**
	 * @return the apellido1
	 */
	public String getApellido1() {
		return apellido1;
	}


	/**
	 * @param apellido1 the apellido1 to set
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}


	/**
	 * @return the apellido2
	 */
	public String getApellido2() {
		return apellido2;
	}


	/**
	 * @param apellido2 the apellido2 to set
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}


	/**
	 * @return the subTipoCliente
	 */
	public String getSubTipoCliente() {
		return subTipoCliente;
	}


	/**
	 * @param subTipoCliente the subTipoCliente to set
	 */
	public void setSubTipoCliente(String subTipoCliente) {
		this.subTipoCliente = subTipoCliente;
	}


	/**
	 * @return the indicadorSolicitudCredito
	 */
	public String getIndicadorSolicitudCredito() {
		return indicadorSolicitudCredito;
	}


	/**
	 * @param indicadorSolicitudCredito the indicadorSolicitudCredito to set
	 */
	public void setIndicadorSolicitudCredito(String indicadorSolicitudCredito) {
		this.indicadorSolicitudCredito = indicadorSolicitudCredito;
	}


	/**
	 * @return the graboOK
	 */
	public boolean isGraboOK() {
		return graboOK;
	}


	/**
	 * @param graboOK the graboOK to set
	 */
	public void setGraboOK(boolean graboOK) {
		this.graboOK = graboOK;
	}


	/**
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}


	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
}
