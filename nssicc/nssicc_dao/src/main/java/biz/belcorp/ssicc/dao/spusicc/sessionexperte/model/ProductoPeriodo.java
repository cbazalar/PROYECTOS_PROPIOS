package biz.belcorp.ssicc.dao.spusicc.sessionexperte.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="mailto:ivega@belcorp.biz">Isabel Vega Palomino</a>
 *
 */
public class ProductoPeriodo extends AuditableBaseObject 
	implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoPrograma;
	private String codigoPeriodo;
	private String codigoProducto;
	private String codigoVenta;
	private String descripcionProducto;
	private String precioProducto;
	private String estadoRegistro;
	private int codigoAnho;
	
	/**
	 * @return Returns the codigoAnho.
	 */
	public int getCodigoAnho() {
		return codigoAnho;
	}

	/**
	 * @param codigoAnho The codigoAnho to set.
	 */
	public void setCodigoAnho(int codigoAnho) {
		this.codigoAnho = codigoAnho;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ProductoPeriodo)) {
			return false;
		}

		ProductoPeriodo prod = (ProductoPeriodo) object;

		return new EqualsBuilder()	.append(this.codigoPais,		prod.codigoPais)
									.append(this.codigoPrograma,	prod.codigoPrograma)
									.append(this.codigoPeriodo,	prod.codigoPeriodo)
									.append(this.codigoProducto,	prod.codigoProducto)
									.append(this.codigoVenta,	prod.codigoVenta)
									.append(this.descripcionProducto,prod.descripcionProducto)
									.append(this.precioProducto,prod.precioProducto)
									.append(this.estadoRegistro,	prod.estadoRegistro)
									.append(this.codigoAnho,	prod.codigoAnho)
									.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(2022562655, 96184653)	
				.append(this.codigoPais)
				.append(this.codigoPrograma)
				.append(this.codigoPeriodo)
				.append(this.codigoProducto)
				.append(this.codigoVenta)
				.append(this.descripcionProducto)
				.append(this.precioProducto)
				.append(this.estadoRegistro)
				.append(this.codigoAnho)
				.toHashCode();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("codigoPais", codigoPais)
		.append("codigoPrograma", codigoPrograma)
		.append("codigoPeriodo", codigoPeriodo)
		.append("codigoProducto", codigoProducto)
		.append("codigoVenta", codigoVenta)
		.append("descripcionProducto", descripcionProducto)
		.append("precioProducto", precioProducto)
		.append("estadoRegistro",	estadoRegistro)
		.append("codigoAnho",	codigoAnho)
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
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
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
	 * @return Returns the codigoPrograma.
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	/**
	 * @param codigoPrograma The codigoPrograma to set.
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	/**
	 * @return Returns the codigoVenta.
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}

	/**
	 * @param codigoVenta The codigoVenta to set.
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
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

	/**
	 * @return Returns the estadoRegistro.
	 */
	public String getEstadoRegistro() {
		return estadoRegistro;
	}

	/**
	 * @param estadoRegistro The estadoRegistro to set.
	 */
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	/**
	 * @return Returns the precioProducto.
	 */
	public String getPrecioProducto() {
		return precioProducto;
	}

	/**
	 * @param precioProducto The precioProducto to set.
	 */
	public void setPrecioProducto(String precioProducto) {
		this.precioProducto = precioProducto;
	}

}


