package biz.belcorp.ssicc.dao.spusicc.lec.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class CanastaDetalle extends AuditableBaseObject {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPrograma;
	private String secuencia;
	private String secuenciaCanasta;
	private String nombreTipoOferta;
	private String nombreProducto;
    private String precio;
    private String formaPago;
    private String estado;
    private String codigoSap;
    private String codigoTipoOferta;
    private String correlativo;
    private String accion;
	public String getCodigoPais() {
		return codigoPais;
	}
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	public String getSecuencia() {
		return secuencia;
	}
	public String getNombreTipoOferta() {
		return nombreTipoOferta;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public String getPrecio() {
		return precio;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public String getEstado() {
		return estado;
	}
	public String getCodigoSap() {
		return codigoSap;
	}
	public String getCodigoTipoOferta() {
		return codigoTipoOferta;
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
	public void setNombreTipoOferta(String nombreTipoOferta) {
		this.nombreTipoOferta = nombreTipoOferta;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}
	public void setCodigoTipoOferta(String codigoTipoOferta) {
		this.codigoTipoOferta = codigoTipoOferta;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime * result
				+ ((codigoSap == null) ? 0 : codigoSap.hashCode());
		result = prime
				* result
				+ ((codigoTipoOferta == null) ? 0 : codigoTipoOferta.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result
				+ ((formaPago == null) ? 0 : formaPago.hashCode());
		result = prime * result
				+ ((nombreProducto == null) ? 0 : nombreProducto.hashCode());
		result = prime
				* result
				+ ((nombreTipoOferta == null) ? 0 : nombreTipoOferta.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
		result = prime * result
				+ ((secuencia == null) ? 0 : secuencia.hashCode());
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
		CanastaDetalle other = (CanastaDetalle) obj;
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
		if (codigoSap == null) {
			if (other.codigoSap != null)
				return false;
		} else if (!codigoSap.equals(other.codigoSap))
			return false;
		if (codigoTipoOferta == null) {
			if (other.codigoTipoOferta != null)
				return false;
		} else if (!codigoTipoOferta.equals(other.codigoTipoOferta))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (formaPago == null) {
			if (other.formaPago != null)
				return false;
		} else if (!formaPago.equals(other.formaPago))
			return false;
		if (nombreProducto == null) {
			if (other.nombreProducto != null)
				return false;
		} else if (!nombreProducto.equals(other.nombreProducto))
			return false;
		if (nombreTipoOferta == null) {
			if (other.nombreTipoOferta != null)
				return false;
		} else if (!nombreTipoOferta.equals(other.nombreTipoOferta))
			return false;
		if (precio == null) {
			if (other.precio != null)
				return false;
		} else if (!precio.equals(other.precio))
			return false;
		if (secuencia == null) {
			if (other.secuencia != null)
				return false;
		} else if (!secuencia.equals(other.secuencia))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CanastaDetalle [codigoPais=" + codigoPais + ", codigoPrograma="
				+ codigoPrograma + ", secuencia=" + secuencia
				+ ", nombreTipoOferta=" + nombreTipoOferta
				+ ", nombreProducto=" + nombreProducto + ", precio=" + precio
				+ ", formaPago=" + formaPago + ", estado=" + estado
				+ ", codigoSap=" + codigoSap + ", codigoTipoOferta="
				+ codigoTipoOferta + "]";
	}
	public String getSecuenciaCanasta() {
		return secuenciaCanasta;
	}
	public void setSecuenciaCanasta(String secuenciaCanasta) {
		this.secuenciaCanasta = secuenciaCanasta;
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
    
    
    
}
