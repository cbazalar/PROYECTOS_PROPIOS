package biz.belcorp.ssicc.dao.spusicc.pej.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author itocto
 *
 */
public class Rango extends AuditableBaseObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPrograma;
	private String codigoPais;
	private String codigo;
	private String descripcion;
	private String valorInicial;
	private String valorFinal;
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

	private String estadoRegistro;
	
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
	 * @return the valorInicial
	 */
	public String getValorInicial() {
		return valorInicial;
	}

	/**
	 * @param valorInicial the valorInicial to set
	 */
	public void setValorInicial(String valorInicial) {
		this.valorInicial = valorInicial;
	}

	/**
	 * @return the valorFinal
	 */
	public String getValorFinal() {
		return valorFinal;
	}

	/**
	 * @param valorFinal the valorFinal to set
	 */
	public void setValorFinal(String valorFinal) {
		this.valorFinal = valorFinal;
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
	 * @return the estadoRegistro
	 */
	public String getEstadoRegistro() {
		return estadoRegistro;
	}

	/**
	 * @param estadoRegistro the estadoRegistro to set
	 */
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Rango [codigo=" + codigo + ", codigoPais=" + codigoPais
				+ ", codigoPrograma=" + codigoPrograma + ", correlativo="
				+ correlativo + ", descripcion=" + descripcion + ", estado="
				+ estado + ", estadoRegistro=" + estadoRegistro
				+ ", valorFinal=" + valorFinal + ", valorInicial="
				+ valorInicial + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime * result
				+ ((correlativo == null) ? 0 : correlativo.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result
				+ ((estadoRegistro == null) ? 0 : estadoRegistro.hashCode());
		result = prime * result
				+ ((valorFinal == null) ? 0 : valorFinal.hashCode());
		result = prime * result
				+ ((valorInicial == null) ? 0 : valorInicial.hashCode());
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
		Rango other = (Rango) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
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
		if (estadoRegistro == null) {
			if (other.estadoRegistro != null)
				return false;
		} else if (!estadoRegistro.equals(other.estadoRegistro))
			return false;
		if (valorFinal == null) {
			if (other.valorFinal != null)
				return false;
		} else if (!valorFinal.equals(other.valorFinal))
			return false;
		if (valorInicial == null) {
			if (other.valorInicial != null)
				return false;
		} else if (!valorInicial.equals(other.valorInicial))
			return false;
		return true;
	}

}
