package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class ParametrosGeneralesCOB extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 2248776890218203480L;
	
	private String codigoParametro;
	private String descripcionParametro;
	private String valorParametro;
	private String observacionParametro;
	

	public ParametrosGeneralesCOB(){
		
	}
	
	public String getCodigoParametro() {
		return codigoParametro;
	}

	public void setCodigoParametro(String codigoParametro) {
		this.codigoParametro = codigoParametro;
	}

	public String getDescripcionParametro() {
		return descripcionParametro;
	}

	public void setDescripcionParametro(String descripcionParametro) {
		this.descripcionParametro = descripcionParametro;
	}

	public String getValorParametro() {
		return valorParametro;
	}

	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}

	public String getObservacionParametro() {
		return observacionParametro;
	}

	public void setObservacionParametro(String observacionParametro) {
		this.observacionParametro = observacionParametro;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
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

}
