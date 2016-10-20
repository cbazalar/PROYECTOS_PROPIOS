package biz.belcorp.ssicc.dao.spncd.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Diego Torres Loyola
 *
 */
public class SecuenciaValidacion extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigo;
	private String validacion;
	private String secuenciaNueva;
	private String secuenciaOriginal;
	private String estado;
	private String codigoPais;
	private String codigoTipoDocumento;

	public SecuenciaValidacion() {
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getValidacion() {
		return validacion;
	}

	public void setValidacion(String validacion) {
		this.validacion = validacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
	 * @return the codigoTipoDocumento
	 */
	public String getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}

	/**
	 * @param codigoTipoDocumento the codigoTipoDocumento to set
	 */
	public void setCodigoTipoDocumento(String codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}

	/**
	 * @return the secuenciaNueva
	 */
	public String getSecuenciaNueva() {
		return secuenciaNueva;
	}

	/**
	 * @param secuenciaNueva the secuenciaNueva to set
	 */
	public void setSecuenciaNueva(String secuenciaNueva) {
		this.secuenciaNueva = secuenciaNueva;
	}

	/**
	 * @return the secuenciaOriginal
	 */
	public String getSecuenciaOriginal() {
		return secuenciaOriginal;
	}

	/**
	 * @param secuenciaOriginal the secuenciaOriginal to set
	 */
	public void setSecuenciaOriginal(String secuenciaOriginal) {
		this.secuenciaOriginal = secuenciaOriginal;
	}
	
	
	
	
}