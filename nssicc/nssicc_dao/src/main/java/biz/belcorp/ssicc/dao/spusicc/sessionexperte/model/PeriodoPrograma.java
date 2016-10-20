package biz.belcorp.ssicc.dao.spusicc.sessionexperte.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="mailto:ivega@belcorp.biz">Isabel Vega Palomino</a>
 *
 */
public class PeriodoPrograma extends AuditableBaseObject 
	implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoPrograma;
	private String codigoPeriodo;
	private String estadoRegistro;
	private List listaProductosPeriodo;
	private String[] productosAsignados;
	private String[] productosNoAsignados;
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
		if (!(object instanceof PeriodoPrograma)) {
			return false;
		}

		PeriodoPrograma prod = (PeriodoPrograma) object;

		return new EqualsBuilder()	.append(this.codigoPais,		prod.codigoPais)
									.append(this.codigoPrograma,	prod.codigoPrograma)
									.append(this.codigoPeriodo,	prod.codigoPeriodo)
									.append(this.estadoRegistro,	prod.estadoRegistro)
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
				.append(this.estadoRegistro)
				.toHashCode();
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("codigoPais", codigoPais)
				.append("codigoPrograma", codigoPrograma)
				.append("codigoPeriodo", codigoPeriodo)
				.append("estadoRegistro",	estadoRegistro)
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
	 * @return Returns the listaProductosPeriodo.
	 */
	public List getListaProductosPeriodo() {
		return listaProductosPeriodo;
	}

	/**
	 * @param listaProductosPeriodo The listaProductosPeriodo to set.
	 */
	public void setListaProductosPeriodo(List listaProductosPeriodo) {
		this.listaProductosPeriodo = listaProductosPeriodo;
	}

	/**
	 * @return Returns the productosAsignados.
	 */
	public String[] getProductosAsignados() {
		return productosAsignados;
	}

	/**
	 * @param productosAsignados The productosAsignados to set.
	 */
	public void setProductosAsignados(String[] productosAsignados) {
		this.productosAsignados = productosAsignados;
	}

	/**
	 * @return Returns the productosNoAsignados.
	 */
	public String[] getProductosNoAsignados() {
		return productosNoAsignados;
	}

	/**
	 * @param productosNoAsignados The productosNoAsignados to set.
	 */
	public void setProductosNoAsignados(String[] productosNoAsignados) {
		this.productosNoAsignados = productosNoAsignados;
	}

}
