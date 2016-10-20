package biz.belcorp.ssicc.dao.spusicc.pej.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Sigcomt
 *
 */
public class Nivel extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPrograma;
	private String codigoPais;
	private String codigo;
	private String descripcion;
	private String numeroPedidoMinimo;
	private String codigoNivelRequisito;
	private String estado;
	private String correlativo;
	
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
	 * @return the codigoPrograma
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	/**
	 * @param codigoPrograma the codigoPrograma to set
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the numeroPedidoMinimo
	 */
	public String getNumeroPedidoMinimo() {
		return numeroPedidoMinimo;
	}
	/**
	 * @param numeroPedidoMinimo the numeroPedidoMinimo to set
	 */
	public void setNumeroPedidoMinimo(String numeroPedidoMinimo) {
		this.numeroPedidoMinimo = numeroPedidoMinimo;
	}
	/**
	 * @return the codigoNivelRequisito
	 */
	public String getCodigoNivelRequisito() {
		return codigoNivelRequisito;
	}
	/**
	 * @param codigoNivelRequisito the codigoNivelRequisito to set
	 */
	public void setCodigoNivelRequisito(String codigoNivelRequisito) {
		this.codigoNivelRequisito = codigoNivelRequisito;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return the correlativo
	 */
	public String getCorrelativo() {
		return correlativo;
	}
	/**
	 * @param correlativo the correlativo to set
	 */
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Nivel [codigo=" + codigo + ", codigoNivelRequisito="
				+ codigoNivelRequisito + ", codigoPrograma=" + codigoPrograma
				+ ", correlativo=" + correlativo + ", descripcion="
				+ descripcion + ", estado=" + estado + ", numeroPedidoMinimo="
				+ numeroPedidoMinimo + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime
				* result
				+ ((codigoNivelRequisito == null) ? 0 : codigoNivelRequisito
						.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime * result
				+ ((correlativo == null) ? 0 : correlativo.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime
				* result
				+ ((numeroPedidoMinimo == null) ? 0 : numeroPedidoMinimo
						.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj==null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nivel other = (Nivel) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (codigoNivelRequisito == null) {
			if (other.codigoNivelRequisito != null)
				return false;
		} else if (!codigoNivelRequisito.equals(other.codigoNivelRequisito))
			return false;
		if (codigoPrograma == null) {
			if (other.codigoPrograma != null)
				return false;
		} else if (!codigoPrograma.equals(other.codigoPrograma))
			return false;
		if (correlativo == null) {
			if (other.correlativo != null)
				return false;
		} else if (!correlativo.equals(other.correlativo))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (numeroPedidoMinimo == null) {
			if (other.numeroPedidoMinimo != null)
				return false;
		} else if (!numeroPedidoMinimo.equals(other.numeroPedidoMinimo))
			return false;
		return true;
	}
	
	
}
