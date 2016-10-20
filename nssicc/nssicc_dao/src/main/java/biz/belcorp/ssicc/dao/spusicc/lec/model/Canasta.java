package biz.belcorp.ssicc.dao.spusicc.lec.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class Canasta extends AuditableBaseObject {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPrograma;
	private String secuencia;
	private String nombre;
	private String valor;
	private String numeroProductos;
	private String campanyaRegistro;
	private String estado;
	private String usuarioRegistro; 
	private String correlativo;
	private String accion;
	private String campanyaActivacionCanasta;
	
	@Override
	public String toString() {
		return "Canasta [codigoPais=" + codigoPais + ", codigoPrograma="
				+ codigoPrograma + ", secuencia=" + secuencia + ", nombre="
				+ nombre + ", valor=" + valor + ", numeroProductos="
				+ numeroProductos + ", campanyaRegistro=" + campanyaRegistro
				+ ", estado=" + estado + ", usuarioRegistro=" + usuarioRegistro
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((campanyaRegistro == null) ? 0 : campanyaRegistro.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result
				+ ((numeroProductos == null) ? 0 : numeroProductos.hashCode());
		result = prime * result
				+ ((secuencia == null) ? 0 : secuencia.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Canasta other = (Canasta) obj;
		if (campanyaRegistro == null) {
			if (other.campanyaRegistro != null)
				return false;
		} else if (!campanyaRegistro.equals(other.campanyaRegistro))
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
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (numeroProductos == null) {
			if (other.numeroProductos != null)
				return false;
		} else if (!numeroProductos.equals(other.numeroProductos))
			return false;
		if (secuencia == null) {
			if (other.secuencia != null)
				return false;
		} else if (!secuencia.equals(other.secuencia))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
	public String getCodigoPais() {
		return codigoPais;
	}
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	public String getSecuencia() {
		return secuencia;
	}
	public String getNombre() {
		return nombre;
	}
	public String getValor() {
		return valor;
	}
	public String getNumeroProductos() {
		return numeroProductos;
	}
	public String getCampanyaRegistro() {
		return campanyaRegistro;
	}
	public String getEstado() {
		return estado;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public void setNumeroProductos(String numeroProductos) {
		this.numeroProductos = numeroProductos;
	}
	public void setCampanyaRegistro(String campanyaRegistro) {
		this.campanyaRegistro = campanyaRegistro;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}
	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}
	public String getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	/**
	 * @return the campanyaActivacionCanasta
	 */
	public String getCampanyaActivacionCanasta() {
		return campanyaActivacionCanasta;
	}
	/**
	 * @param campanyaActivacionCanasta the campanyaActivacionCanasta to set
	 */
	public void setCampanyaActivacionCanasta(String campanyaActivacionCanasta) {
		this.campanyaActivacionCanasta = campanyaActivacionCanasta;
	}
}
