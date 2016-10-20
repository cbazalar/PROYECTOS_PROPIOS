package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class Documento extends AuditableBaseObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8646856467505316858L;
	private int oidDocumento;
	private String  nombreDocumento;
	private String  rutaDocumento;
	private String  descripcionDocumento;
	

	public String toString() {
		 return new ToStringBuilder(this)
		 			.append("oidDocumento", this.oidDocumento)
		 			.append("nombreDocumento", this.nombreDocumento)
	                .append("rutaDocumento", this.rutaDocumento)
	                .append("descripcionDocumento", this.descripcionDocumento)
	                .toString();
	}
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return
	 */
	public String getDescripcionDocumento() {
		return descripcionDocumento;
	}
	/**
	 * @param descripcionDocumento
	 */
	public void setDescripcionDocumento(String descripcionDocumento) {
		this.descripcionDocumento = descripcionDocumento;
	}
	/**
	 * @return
	 */
	public String getNombreDocumento() {
		return nombreDocumento;
	}
	/**
	 * @param nombreDocumento
	 */
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}
	/**
	 * @return
	 */
	public int getOidDocumento() {
		return oidDocumento;
	}
	/**
	 * @param oidDocumento
	 */
	public void setOidDocumento(int oidDocumento) {
		this.oidDocumento = oidDocumento;
	}
	/**
	 * @return
	 */
	public String getRutaDocumento() {
		return rutaDocumento;
	}
	/**
	 * @param rutaDocumento
	 */
	public void setRutaDocumento(String rutaDocumento) {
		this.rutaDocumento = rutaDocumento;
	}
	
}
