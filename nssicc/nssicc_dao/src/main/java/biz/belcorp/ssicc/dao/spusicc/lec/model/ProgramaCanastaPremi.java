package biz.belcorp.ssicc.dao.spusicc.lec.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class ProgramaCanastaPremi extends AuditableBaseObject {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPrograma;
	private String secCanasta;
	private String secuencia;
	private String codigoTipoCanasta;
	private String codigoNivel;
	private String nivel;
	private String codTipoBono;
	private String secBonoNivel;
	private String secAmbGeogra;
	private String codTipoUsoGeogra;
	private String codTipoMediCobr;
	private String nomCanasta;
	private String codCanasta;
	private String costoCanasta;
	private String exigenciaCanasta;
	private String tramo;
	private String codTramo;
	private String correlativo;
	private String accion;

	private String codigoPeriodo;
	private String nombreTipoCanasta;
	
	public String getCodigoTipoCanasta() {
		return codigoTipoCanasta;
	}
	public void setCodigoTipoCanasta(String codigoTipoCanasta) {
		this.codigoTipoCanasta = codigoTipoCanasta;
	}
	
	
	
	

	@Override
	public String toString() {
		return "ProgramaCanastaPremi [codigoPais=" + codigoPais
				+ ", codigoPrograma=" + codigoPrograma + ", secCanasta="
				+ secCanasta + ", secuencia=" + secuencia
				+ ", codigoTipoCanasta=" + codigoTipoCanasta + ", codigoNivel="
				+ codigoNivel + ", nivel=" + nivel + ", codTipoBono="
				+ codTipoBono + ", secBonoNivel=" + secBonoNivel
				+ ", secAmbGeogra=" + secAmbGeogra + ", codTipoUsoGeogra="
				+ codTipoUsoGeogra + ", codTipoMediCobr=" + codTipoMediCobr
				+ ", nomCanasta=" + nomCanasta + ", codCanasta=" + codCanasta
				+ ", costoCanasta=" + costoCanasta + ", exigenciaCanasta="
				+ exigenciaCanasta + ", tramo=" + tramo + ", codTramo="
				+ codTramo + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codCanasta == null) ? 0 : codCanasta.hashCode());
		result = prime * result
				+ ((codTipoBono == null) ? 0 : codTipoBono.hashCode());
		result = prime * result
				+ ((codTipoMediCobr == null) ? 0 : codTipoMediCobr.hashCode());
		result = prime
				* result
				+ ((codTipoUsoGeogra == null) ? 0 : codTipoUsoGeogra.hashCode());
		result = prime * result
				+ ((codTramo == null) ? 0 : codTramo.hashCode());
		result = prime * result
				+ ((codigoNivel == null) ? 0 : codigoNivel.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime * result
				+ ((costoCanasta == null) ? 0 : costoCanasta.hashCode());
		result = prime
				* result
				+ ((exigenciaCanasta == null) ? 0 : exigenciaCanasta.hashCode());
		result = prime * result + ((nivel == null) ? 0 : nivel.hashCode());
		result = prime * result
				+ ((nomCanasta == null) ? 0 : nomCanasta.hashCode());
		result = prime * result
				+ ((secAmbGeogra == null) ? 0 : secAmbGeogra.hashCode());
		result = prime * result
				+ ((secBonoNivel == null) ? 0 : secBonoNivel.hashCode());
		result = prime * result
				+ ((secCanasta == null) ? 0 : secCanasta.hashCode());
		result = prime * result
				+ ((secuencia == null) ? 0 : secuencia.hashCode());
		result = prime * result + ((tramo == null) ? 0 : tramo.hashCode());
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
		ProgramaCanastaPremi other = (ProgramaCanastaPremi) obj;
		if (codCanasta == null) {
			if (other.codCanasta != null)
				return false;
		} else if (!codCanasta.equals(other.codCanasta))
			return false;
		if (codTipoBono == null) {
			if (other.codTipoBono != null)
				return false;
		} else if (!codTipoBono.equals(other.codTipoBono))
			return false;
		if (codTipoMediCobr == null) {
			if (other.codTipoMediCobr != null)
				return false;
		} else if (!codTipoMediCobr.equals(other.codTipoMediCobr))
			return false;
		if (codTipoUsoGeogra == null) {
			if (other.codTipoUsoGeogra != null)
				return false;
		} else if (!codTipoUsoGeogra.equals(other.codTipoUsoGeogra))
			return false;
		if (codTramo == null) {
			if (other.codTramo != null)
				return false;
		} else if (!codTramo.equals(other.codTramo))
			return false;
		if (codigoNivel == null) {
			if (other.codigoNivel != null)
				return false;
		} else if (!codigoNivel.equals(other.codigoNivel))
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
		if (costoCanasta == null) {
			if (other.costoCanasta != null)
				return false;
		} else if (!costoCanasta.equals(other.costoCanasta))
			return false;
		if (exigenciaCanasta == null) {
			if (other.exigenciaCanasta != null)
				return false;
		} else if (!exigenciaCanasta.equals(other.exigenciaCanasta))
			return false;
		if (nivel == null) {
			if (other.nivel != null)
				return false;
		} else if (!nivel.equals(other.nivel))
			return false;
		if (nomCanasta == null) {
			if (other.nomCanasta != null)
				return false;
		} else if (!nomCanasta.equals(other.nomCanasta))
			return false;
		if (secAmbGeogra == null) {
			if (other.secAmbGeogra != null)
				return false;
		} else if (!secAmbGeogra.equals(other.secAmbGeogra))
			return false;
		if (secBonoNivel == null) {
			if (other.secBonoNivel != null)
				return false;
		} else if (!secBonoNivel.equals(other.secBonoNivel))
			return false;
		if (secCanasta == null) {
			if (other.secCanasta != null)
				return false;
		} else if (!secCanasta.equals(other.secCanasta))
			return false;
		if (secuencia == null) {
			if (other.secuencia != null)
				return false;
		} else if (!secuencia.equals(other.secuencia))
			return false;
		if (tramo == null) {
			if (other.tramo != null)
				return false;
		} else if (!tramo.equals(other.tramo))
			return false;
		return true;
	}
	public String getCodigoPais() {
		return codigoPais;
	}
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	public String getSecCanasta() {
		return secCanasta;
	}
	public String getSecuencia() {
		return secuencia;
	}
	public String getCodigoNivel() {
		return codigoNivel;
	}
	public String getNivel() {
		return nivel;
	}
	public String getCodTipoBono() {
		return codTipoBono;
	}
	public String getSecBonoNivel() {
		return secBonoNivel;
	}
	public String getSecAmbGeogra() {
		return secAmbGeogra;
	}
	public String getCodTipoUsoGeogra() {
		return codTipoUsoGeogra;
	}
	public String getCodTipoMediCobr() {
		return codTipoMediCobr;
	}
	public String getNomCanasta() {
		return nomCanasta;
	}
	public String getCodCanasta() {
		return codCanasta;
	}
	public String getCostoCanasta() {
		return costoCanasta;
	}
	public String getExigenciaCanasta() {
		return exigenciaCanasta;
	}
	public String getTramo() {
		return tramo;
	}
	public String getCodTramo() {
		return codTramo;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
	public void setSecCanasta(String secCanasta) {
		this.secCanasta = secCanasta;
	}
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}
	public void setCodigoNivel(String codigoNivel) {
		this.codigoNivel = codigoNivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public void setCodTipoBono(String codTipoBono) {
		this.codTipoBono = codTipoBono;
	}
	public void setSecBonoNivel(String secBonoNivel) {
		this.secBonoNivel = secBonoNivel;
	}
	public void setSecAmbGeogra(String secAmbGeogra) {
		this.secAmbGeogra = secAmbGeogra;
	}
	public void setCodTipoUsoGeogra(String codTipoUsoGeogra) {
		this.codTipoUsoGeogra = codTipoUsoGeogra;
	}
	public void setCodTipoMediCobr(String codTipoMediCobr) {
		this.codTipoMediCobr = codTipoMediCobr;
	}
	public void setNomCanasta(String nomCanasta) {
		this.nomCanasta = nomCanasta;
	}
	public void setCodCanasta(String codCanasta) {
		this.codCanasta = codCanasta;
	}
	public void setCostoCanasta(String costoCanasta) {
		this.costoCanasta = costoCanasta;
	}
	public void setExigenciaCanasta(String exigenciaCanasta) {
		this.exigenciaCanasta = exigenciaCanasta;
	}
	public void setTramo(String tramo) {
		this.tramo = tramo;
	}
	public void setCodTramo(String codTramo) {
		this.codTramo = codTramo;
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
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the nombreTipoCanasta
	 */
	public String getNombreTipoCanasta() {
		return nombreTipoCanasta;
	}
	/**
	 * @param nombreTipoCanasta the nombreTipoCanasta to set
	 */
	public void setNombreTipoCanasta(String nombreTipoCanasta) {
		this.nombreTipoCanasta = nombreTipoCanasta;
	}
	
	

}
