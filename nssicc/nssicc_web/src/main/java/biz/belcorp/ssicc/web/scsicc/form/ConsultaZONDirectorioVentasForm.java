package biz.belcorp.ssicc.web.scsicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;




/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 * 
 * @struts.form name = "consultaZONDirectorioVentasForm"
 */
public class ConsultaZONDirectorioVentasForm extends BaseSearchForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoCargo;
	private String codigoRol;
	private String codigoPerfil;
	private String codigoRegion;
	private String codigoZona;
	private String estado;
	private String[] estadoList;
	private String email;
	private String barrio;
	private String cub;
	private String resumenDetalle;
	private String tipoCargo;
	private String oidIdioma;
	private String codigoClienteBuscar;
	private String nombreCliente;
	private String numeroDocIdentidadBuscar;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action
	 * .ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset() {

		this.codigoPais = "";

		this.codigoCargo = "";

		this.codigoRol = "";

		this.codigoPerfil = "";

		this.codigoRegion = "";

		this.codigoZona = "";

		this.estado = "";

		this.estadoList = new String[4];

		this.email = "";

		this.barrio = "";

		this.cub = "";

		this.resumenDetalle = "";
		
		this.tipoCargo = "";

		this.codigoClienteBuscar = "";

		this.nombreCliente = "";

		this.numeroDocIdentidadBuscar = "";

	}

	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion
	 *            the codigoRegion to set
	 * 
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
	 * @param codigoZona
	 *            the codigoZona to set
	 * 
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return the codigoCargo
	 */
	public String getCodigoCargo() {
		return codigoCargo;
	}

	/**
	 * @param codigoCargo
	 *            the codigoCargo to set
	 */
	public void setCodigoCargo(String codigoCargo) {
		this.codigoCargo = codigoCargo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getCub() {
		return cub;
	}

	public void setCub(String cub) {
		this.cub = cub;
	}

	public String[] getEstadoList() {
		return estadoList;
	}

	public void setEstadoList(String[] estadoList) {
		this.estadoList = estadoList;
	}

	public String getCodigoRol() {
		return codigoRol;
	}

	public void setCodigoRol(String codigoRol) {
		this.codigoRol = codigoRol;
	}

	public String getCodigoPerfil() {
		return codigoPerfil;
	}

	public void setCodigoPerfil(String codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}

	public String getResumenDetalle() {
		return resumenDetalle;
	}

	public void setResumenDetalle(String resumenDetalle) {
		this.resumenDetalle = resumenDetalle;
	}

	public String getTipoCargo() {
		return tipoCargo;
	}

	public void setTipoCargo(String tipoCargo) {
		this.tipoCargo = tipoCargo;
	}

	public String getCodigoClienteBuscar() {
		return codigoClienteBuscar;
	}

	public void setCodigoClienteBuscar(String codigoClienteBuscar) {
		this.codigoClienteBuscar = codigoClienteBuscar;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getNumeroDocIdentidadBuscar() {
		return numeroDocIdentidadBuscar;
	}

	public void setNumeroDocIdentidadBuscar(String numeroDocIdentidadBuscar) {
		this.numeroDocIdentidadBuscar = numeroDocIdentidadBuscar;
	}

	public String getOidIdioma() {
		return oidIdioma;
	}

	public void setOidIdioma(String oidIdioma) {
		this.oidIdioma = oidIdioma;
	}

}