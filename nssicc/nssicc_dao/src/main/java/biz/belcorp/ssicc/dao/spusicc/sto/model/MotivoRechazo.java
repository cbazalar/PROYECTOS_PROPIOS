package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class MotivoRechazo extends AuditableBaseObject implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String tipoDocumento;
	private String descripcionTipoDocumento;
	private String codigoMotRechazo;
	private String descripcionMotRechazo;
	private String codigoModulo;
	private String indRechazo;
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	public String getCodigoMotRechazo() {
		return codigoMotRechazo;
	}
	
	public void setCodigoMotRechazo(String codigoMotRechazo) {
		this.codigoMotRechazo = codigoMotRechazo;
	}
	
	public String getDescripcionMotRechazo() {
		return descripcionMotRechazo;
	}
	
	public void setDescripcionMotRechazo(String descripcionMotRechazo) {
		this.descripcionMotRechazo = descripcionMotRechazo;
	}
	
	public String getCodigoModulo() {
		return codigoModulo;
	}

	public void setCodigoModulo(String codigoModulo) {
		this.codigoModulo = codigoModulo;
	}
	
	public String getIndRechazo() {
		return indRechazo;
	}
	
	public void setIndRechazo(String indRechazo) {
		this.indRechazo = indRechazo;
	}
	
	public String getDescripcionTipoDocumento() {
		return descripcionTipoDocumento;
	}

	public void setDescripcionTipoDocumento(String descripcionTipoDocumento) {
		this.descripcionTipoDocumento = descripcionTipoDocumento;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return "MotivoRechazo [codigoPais=" + codigoPais + ", tipoDocumento="
				+ tipoDocumento + ", descripcionTipoDocumento="
				+ descripcionTipoDocumento + ", codigoMotRechazo="
				+ codigoMotRechazo + ", descripcionMotRechazo="
				+ descripcionMotRechazo + ", codigoModulo=" + codigoModulo
				+ ", indRechazo=" + indRechazo + "]";
	}
}
