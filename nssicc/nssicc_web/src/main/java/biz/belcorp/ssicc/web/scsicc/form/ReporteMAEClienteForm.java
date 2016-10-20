package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/** 
 * 
 * @author <a href="aoviedo@sigcomt.com">Aurelio Oviedo</a>
 * 
 */
public class ReporteMAEClienteForm extends BaseReporteForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoCliente;
	private String subTipoCliente;
	private String codigoZona;
	private String codigoTerritorio;
	private String nombre1;
    private String nombre2;
	private String apellido1;
	private String apellido2;
	private String indicadorActivo;
	private String compromiso;
	
	private String clienteUnico;
	private String longitudCodigoCliente;
	
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
	 * @return the codigoTerritorio
	 */
	public String getCodigoTerritorio() {
		return codigoTerritorio;
	}
	/**
	 * @param codigoTerritorio the codigoTerritorio to set
	 */
	public void setCodigoTerritorio(String codigoTerritorio) {
		this.codigoTerritorio = codigoTerritorio;
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
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}
	/**
	 * @param indicadorActivo the indicadorActivo to set
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}
	/**
	 * @return the compromiso
	 */
	public String getCompromiso() {
		return compromiso;
	}
	/**
	 * @param compromiso the compromiso to set
	 */
	public void setCompromiso(String compromiso) {
		this.compromiso = compromiso;
	}
	/**
	 * @return the clienteUnico
	 */
	public String getClienteUnico() {
		return clienteUnico;
	}
	/**
	 * @param clienteUnico the clienteUnico to set
	 */
	public void setClienteUnico(String clienteUnico) {
		this.clienteUnico = clienteUnico;
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
	
	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		compromiso = "";
	}
}