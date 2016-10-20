package biz.belcorp.ssicc.web.spusicc.mae.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoMAEEntidadGenericaTipoDocumentoForm extends BaseEditForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String nombreEntidad;
	private String oidTipoDoc;
	private String codigo;
	private String obligatorio;
	private String descripcion;
	private String siglas;
	private String longitud;
	private String dni;
	private String fiscal;
	private String tipoDocu;
	private String estado;
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the nombreEntidad
	 */
	public String getNombreEntidad() {
		return nombreEntidad;
	}
	/**
	 * @param nombreEntidad the nombreEntidad to set
	 */
	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}
	/**
	 * @return the oidTipoDoc
	 */
	public String getOidTipoDoc() {
		return oidTipoDoc;
	}
	/**
	 * @param oidTipoDoc the oidTipoDoc to set
	 */
	public void setOidTipoDoc(String oidTipoDoc) {
		this.oidTipoDoc = oidTipoDoc;
	}
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the obligatorio
	 */
	public String getObligatorio() {
		return obligatorio;
	}
	/**
	 * @param obligatorio the obligatorio to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setObligatorio(String obligatorio) {
		this.obligatorio = obligatorio;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * @return the siglas
	 */
	public String getSiglas() {
		return siglas;
	}
	/**
	 * @param siglas the siglas to set
	 */
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}
	/**
	 * @return the longitud
	 */
	public String getLongitud() {
		return longitud;
	}
	/**
	 * @param longitud the longitud to set
	 */
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}
	/**
	 * @param dni the dni to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}
	/**
	 * @return the fiscal
	 */
	public String getFiscal() {
		return fiscal;
	}
	/**
	 * @param fiscal the fiscal to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setFiscal(String fiscal) {
		this.fiscal = fiscal;
	}
	/**
	 * @return the tipoDocu
	 */
	public String getTipoDocu() {
		return tipoDocu;
	}
	/**
	 * @param tipoDocu the tipoDocu to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setTipoDocu(String tipoDocu) {
		this.tipoDocu = tipoDocu;
	}
	
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
}