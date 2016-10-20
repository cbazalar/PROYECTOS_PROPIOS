package biz.belcorp.ssicc.dao.spusicc.sessionexperte.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * A simple JavaBean to represent label-value pairs. This is most commonly used
 * when constructing user interface elements which have a label to be displayed
 * to the user, and a corresponding value to be returned to the server. One
 * example is the <code>&lt;html:options&gt;</code> tag. <p/><p/>Note: this
 * class has a natural ordering that is inconsistent with equals.
 * </p>
 * 
 * @see org.apache.struts.util.LabelValueBean
 */
public class LabelDatosProductoValue /*extends AuditableBaseObject*/ 
	implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigoPais = null;
	private String codigoProducto = null;
	private String descripcionProducto = null;
    //private String precioProducto = null;
    
    /**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof LabelDatosProductoValue)) {
			return false;
		}
		LabelDatosProductoValue label = (LabelDatosProductoValue) object;
		return new EqualsBuilder()
				.append(this.codigoPais, label.codigoPais)
				.append(this.codigoProducto, label.codigoProducto)
				.append(this.descripcionProducto, label.descripcionProducto)
				//.append(this.precioProducto,label.precioProducto)
				.isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1207529343, 1627144477)
				.append(this.codigoPais)
				.append(this.codigoProducto)
				.append(this.descripcionProducto)
				//.append(this.precioProducto)
				.toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("codigoPais", this.codigoPais)
				.append("codigoProducto", this.codigoProducto)
				.append("descripcionProducto", this.descripcionProducto)
				//.append("precioProducto", this.precioProducto)
				.toString();
	}
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return Returns the codigoProducto.
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}
	/**
	 * @param codigoProducto The codigoProducto to set.
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	/**
	 * @return Returns the descripcionProducto.
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	/**
	 * @param descripcionProducto The descripcionProducto to set.
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

}