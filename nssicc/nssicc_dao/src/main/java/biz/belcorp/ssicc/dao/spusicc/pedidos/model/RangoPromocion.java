/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Ivan Tocto
 *
 */
public class RangoPromocion extends AuditableBaseObject implements Serializable {

	private String oid;
	private String oidCatalogo;
	private String oidPromocion;
	private String codigoTipoRango;
	private String numeroRangoInterno;
	private String valorInicial;
	private String valorFinal;
	private String indicadorExclusion;

	
	/**
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}

	/**
	 * @return the oidCatalogo
	 */
	public String getOidCatalogo() {
		return oidCatalogo;
	}

	/**
	 * @param oidCatalogo the oidCatalogo to set
	 */
	public void setOidCatalogo(String oidCatalogo) {
		this.oidCatalogo = oidCatalogo;
	}

	/**
	 * @return the oidPromocion
	 */
	public String getOidPromocion() {
		return oidPromocion;
	}

	/**
	 * @param oidPromocion the oidPromocion to set
	 */
	public void setOidPromocion(String oidPromocion) {
		this.oidPromocion = oidPromocion;
	}

	/**
	 * @return the codigoTipoRango
	 */
	public String getCodigoTipoRango() {
		return codigoTipoRango;
	}

	/**
	 * @param codigoTipoRango the codigoTipoRango to set
	 */
	public void setCodigoTipoRango(String codigoTipoRango) {
		this.codigoTipoRango = codigoTipoRango;
	}

	/**
	 * @return the numeroRangoInterno
	 */
	public String getNumeroRangoInterno() {
		return numeroRangoInterno;
	}

	/**
	 * @param numeroRangoInterno the numeroRangoInterno to set
	 */
	public void setNumeroRangoInterno(String numeroRangoInterno) {
		this.numeroRangoInterno = numeroRangoInterno;
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
	 * @return the indicadorExclusion
	 */
	public String getIndicadorExclusion() {
		return indicadorExclusion;
	}

	/**
	 * @param indicadorExclusion the indicadorExclusion to set
	 */
	public void setIndicadorExclusion(String indicadorExclusion) {
		this.indicadorExclusion = indicadorExclusion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RangoPromocion [oid=" + oid + ", oidCatalogo=" + oidCatalogo
				+ ", oidPromocion=" + oidPromocion + ", codigoTipoRango="
				+ codigoTipoRango + ", numeroRangoInterno="
				+ numeroRangoInterno + ", valorInicial=" + valorInicial
				+ ", valorFinal=" + valorFinal + ", indicadorExclusion="
				+ indicadorExclusion + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RangoPromocion other = (RangoPromocion) obj;
		if (codigoTipoRango == null) {
			if (other.codigoTipoRango != null)
				return false;
		} else if (!codigoTipoRango.equals(other.codigoTipoRango))
			return false;
		if (indicadorExclusion == null) {
			if (other.indicadorExclusion != null)
				return false;
		} else if (!indicadorExclusion.equals(other.indicadorExclusion))
			return false;
		if (numeroRangoInterno == null) {
			if (other.numeroRangoInterno != null)
				return false;
		} else if (!numeroRangoInterno.equals(other.numeroRangoInterno))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (oidCatalogo == null) {
			if (other.oidCatalogo != null)
				return false;
		} else if (!oidCatalogo.equals(other.oidCatalogo))
			return false;
		if (oidPromocion == null) {
			if (other.oidPromocion != null)
				return false;
		} else if (!oidPromocion.equals(other.oidPromocion))
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoTipoRango == null) ? 0 : codigoTipoRango.hashCode());
		result = prime
				* result
				+ ((indicadorExclusion == null) ? 0 : indicadorExclusion
						.hashCode());
		result = prime
				* result
				+ ((numeroRangoInterno == null) ? 0 : numeroRangoInterno
						.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result
				+ ((oidCatalogo == null) ? 0 : oidCatalogo.hashCode());
		result = prime * result
				+ ((oidPromocion == null) ? 0 : oidPromocion.hashCode());
		result = prime * result
				+ ((valorFinal == null) ? 0 : valorFinal.hashCode());
		result = prime * result
				+ ((valorInicial == null) ? 0 : valorInicial.hashCode());
		return result;
	}

}
