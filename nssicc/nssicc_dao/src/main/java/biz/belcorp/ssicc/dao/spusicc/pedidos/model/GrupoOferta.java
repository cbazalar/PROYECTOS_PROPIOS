/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;
import java.util.List;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Ivan Tocto Jaimes
 *
 */
public class GrupoOferta extends AuditableBaseObject implements Serializable {

	private String oid;
	private String oidOferta;
	private String numero;
	private String factorCuadre;
	private String oidIndicadorCuadreTipoEstrategia;
	private String flagCondicionante;
	private String flagCondicionado;
	private String flagGrupo;
	
	private List detalles;
	
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
	 * @return the oidOferta
	 */
	public String getOidOferta() {
		return oidOferta;
	}

	/**
	 * @param oidOferta the oidOferta to set
	 */
	public void setOidOferta(String oidOferta) {
		this.oidOferta = oidOferta;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the factorCuadre
	 */
	public String getFactorCuadre() {
		return factorCuadre;
	}

	/**
	 * @param factorCuadre the factorCuadre to set
	 */
	public void setFactorCuadre(String factorCuadre) {
		this.factorCuadre = factorCuadre;
	}

	/**
	 * @return the oidIndicadorCuadreTipoEstrategia
	 */
	public String getOidIndicadorCuadreTipoEstrategia() {
		return oidIndicadorCuadreTipoEstrategia;
	}

	/**
	 * @param oidIndicadorCuadreTipoEstrategia the oidIndicadorCuadreTipoEstrategia to set
	 */
	public void setOidIndicadorCuadreTipoEstrategia(
			String oidIndicadorCuadreTipoEstrategia) {
		this.oidIndicadorCuadreTipoEstrategia = oidIndicadorCuadreTipoEstrategia;
	}

	/**
	 * @return the flagCondicionante
	 */
	public String getFlagCondicionante() {
		return flagCondicionante;
	}

	/**
	 * @param flagCondicionante the flagCondicionante to set
	 */
	public void setFlagCondicionante(String flagCondicionante) {
		this.flagCondicionante = flagCondicionante;
	}

	/**
	 * @return the flagCondicionado
	 */
	public String getFlagCondicionado() {
		return flagCondicionado;
	}

	/**
	 * @param flagCondicionado the flagCondicionado to set
	 */
	public void setFlagCondicionado(String flagCondicionado) {
		this.flagCondicionado = flagCondicionado;
	}


	/**
	 * @return the flagGrupo
	 */
	public String getFlagGrupo() {
		return flagGrupo;
	}

	/**
	 * @param flagGrupo the flagGrupo to set
	 */
	public void setFlagGrupo(String flagGrupo) {
		this.flagGrupo = flagGrupo;
	}

	/**
	 * @return the detalles
	 */
	public List getDetalles() {
		return detalles;
	}

	/**
	 * @param detalles the detalles to set
	 */
	public void setDetalles(List detalles) {
		this.detalles = detalles;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GrupoOferta [oid=" + oid + ", oidOferta=" + oidOferta
				+ ", numero=" + numero + ", factorCuadre=" + factorCuadre
				+ ", oidIndicadorCuadreTipoEstrategia="
				+ oidIndicadorCuadreTipoEstrategia + ", flagCondicionante="
				+ flagCondicionante + ", flagCondicionado=" + flagCondicionado
				+ ", flagGrupo=" + flagGrupo + "]";
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
		GrupoOferta other = (GrupoOferta) obj;
		if (factorCuadre == null) {
			if (other.factorCuadre != null)
				return false;
		} else if (!factorCuadre.equals(other.factorCuadre))
			return false;
		if (flagCondicionado == null) {
			if (other.flagCondicionado != null)
				return false;
		} else if (!flagCondicionado.equals(other.flagCondicionado))
			return false;
		if (flagCondicionante == null) {
			if (other.flagCondicionante != null)
				return false;
		} else if (!flagCondicionante.equals(other.flagCondicionante))
			return false;
		if (flagGrupo == null) {
			if (other.flagGrupo != null)
				return false;
		} else if (!flagGrupo.equals(other.flagGrupo))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (oidIndicadorCuadreTipoEstrategia == null) {
			if (other.oidIndicadorCuadreTipoEstrategia != null)
				return false;
		} else if (!oidIndicadorCuadreTipoEstrategia
				.equals(other.oidIndicadorCuadreTipoEstrategia))
			return false;
		if (oidOferta == null) {
			if (other.oidOferta != null)
				return false;
		} else if (!oidOferta.equals(other.oidOferta))
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
				+ ((factorCuadre == null) ? 0 : factorCuadre.hashCode());
		result = prime
				* result
				+ ((flagCondicionado == null) ? 0 : flagCondicionado.hashCode());
		result = prime
				* result
				+ ((flagCondicionante == null) ? 0 : flagCondicionante
						.hashCode());
		result = prime * result
				+ ((flagGrupo == null) ? 0 : flagGrupo.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime
				* result
				+ ((oidIndicadorCuadreTipoEstrategia == null) ? 0
						: oidIndicadorCuadreTipoEstrategia.hashCode());
		result = prime * result
				+ ((oidOferta == null) ? 0 : oidOferta.hashCode());
		return result;
	}

}
