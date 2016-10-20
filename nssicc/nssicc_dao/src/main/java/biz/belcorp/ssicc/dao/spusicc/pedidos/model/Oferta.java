/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;
import java.util.List;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Ivan Tocto
 *
 */
public class Oferta extends AuditableBaseObject implements Serializable {

	private String oid;
	private String oidEstrategia;
	private String numeroGrupos;
	private String numeroGruposCondicionados;
	private String numeroGruposCondicionantes;
	private String numeroPaquetes;
	private String condicionantes;
	private String condicionados;
	private String oidFormaPago;
	private String oidSubacceso;
	private String oidTipoEstrategia;
	private String oidAcceso;
	private String oidMatriz;
	private String flagDespachoCompleto;
	private String flagDespachoAutomatico;
	private String oidCatalogo;

	private List grupos;
	private List detalles;	
	private CondicionOferta condicion;
	private List rangosPromocion;
	
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
	 * @return the oidEstrategia
	 */
	public String getOidEstrategia() {
		return oidEstrategia;
	}

	/**
	 * @param oidEstrategia the oidEstrategia to set
	 */
	public void setOidEstrategia(String oidEstrategia) {
		this.oidEstrategia = oidEstrategia;
	}

	/**
	 * @return the numeroGrupos
	 */
	public String getNumeroGrupos() {
		return numeroGrupos;
	}

	/**
	 * @param numeroGrupos the numeroGrupos to set
	 */
	public void setNumeroGrupos(String numeroGrupos) {
		this.numeroGrupos = numeroGrupos;
	}

	/**
	 * @return the numeroGruposCondicionados
	 */
	public String getNumeroGruposCondicionados() {
		return numeroGruposCondicionados;
	}

	/**
	 * @param numeroGruposCondicionados the numeroGruposCondicionados to set
	 */
	public void setNumeroGruposCondicionados(String numeroGruposCondicionados) {
		this.numeroGruposCondicionados = numeroGruposCondicionados;
	}

	/**
	 * @return the numeroGruposCondicionantes
	 */
	public String getNumeroGruposCondicionantes() {
		return numeroGruposCondicionantes;
	}

	/**
	 * @param numeroGruposCondicionantes the numeroGruposCondicionantes to set
	 */
	public void setNumeroGruposCondicionantes(String numeroGruposCondicionantes) {
		this.numeroGruposCondicionantes = numeroGruposCondicionantes;
	}

	/**
	 * @return the numeroPaquetes
	 */
	public String getNumeroPaquetes() {
		return numeroPaquetes;
	}

	/**
	 * @param numeroPaquetes the numeroPaquetes to set
	 */
	public void setNumeroPaquetes(String numeroPaquetes) {
		this.numeroPaquetes = numeroPaquetes;
	}

	/**
	 * @return the condicionantes
	 */
	public String getCondicionantes() {
		return condicionantes;
	}

	/**
	 * @param condicionantes the condicionantes to set
	 */
	public void setCondicionantes(String condicionantes) {
		this.condicionantes = condicionantes;
	}

	/**
	 * @return the condicionados
	 */
	public String getCondicionados() {
		return condicionados;
	}

	/**
	 * @param condicionados the condicionados to set
	 */
	public void setCondicionados(String condicionados) {
		this.condicionados = condicionados;
	}

	/**
	 * @return the oidFormaPago
	 */
	public String getOidFormaPago() {
		return oidFormaPago;
	}

	/**
	 * @param oidFormaPago the oidFormaPago to set
	 */
	public void setOidFormaPago(String oidFormaPago) {
		this.oidFormaPago = oidFormaPago;
	}

	/**
	 * @return the oidSubacceso
	 */
	public String getOidSubacceso() {
		return oidSubacceso;
	}

	/**
	 * @param oidSubacceso the oidSubacceso to set
	 */
	public void setOidSubacceso(String oidSubacceso) {
		this.oidSubacceso = oidSubacceso;
	}

	/**
	 * @return the oidTipoEstrategia
	 */
	public String getOidTipoEstrategia() {
		return oidTipoEstrategia;
	}

	/**
	 * @param oidTipoEstrategia the oidTipoEstrategia to set
	 */
	public void setOidTipoEstrategia(String oidTipoEstrategia) {
		this.oidTipoEstrategia = oidTipoEstrategia;
	}

	/**
	 * @return the oidAcceso
	 */
	public String getOidAcceso() {
		return oidAcceso;
	}

	/**
	 * @param oidAcceso the oidAcceso to set
	 */
	public void setOidAcceso(String oidAcceso) {
		this.oidAcceso = oidAcceso;
	}

	/**
	 * @return the oidMatriz
	 */
	public String getOidMatriz() {
		return oidMatriz;
	}

	/**
	 * @param oidMatriz the oidMatriz to set
	 */
	public void setOidMatriz(String oidMatriz) {
		this.oidMatriz = oidMatriz;
	}

	/**
	 * @return the flagDespachoCompleto
	 */
	public String getFlagDespachoCompleto() {
		return flagDespachoCompleto;
	}

	/**
	 * @param flagDespachoCompleto the flagDespachoCompleto to set
	 */
	public void setFlagDespachoCompleto(String flagDespachoCompleto) {
		this.flagDespachoCompleto = flagDespachoCompleto;
	}

	/**
	 * @return the flagDespachoAutomatico
	 */
	public String getFlagDespachoAutomatico() {
		return flagDespachoAutomatico;
	}

	/**
	 * @param flagDespachoAutomatico the flagDespachoAutomatico to set
	 */
	public void setFlagDespachoAutomatico(String flagDespachoAutomatico) {
		this.flagDespachoAutomatico = flagDespachoAutomatico;
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
	 * @return the grupos
	 */
	public List getGrupos() {
		return grupos;
	}

	/**
	 * @param grupos the grupos to set
	 */
	public void setGrupos(List grupos) {
		this.grupos = grupos;
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

	/**
	 * @return the condicion
	 */
	public CondicionOferta getCondicion() {
		return condicion;
	}

	/**
	 * @param condicion the condicion to set
	 */
	public void setCondicion(CondicionOferta condicion) {
		this.condicion = condicion;
	}

	/**
	 * @return the rangosPromocion
	 */
	public List getRangosPromocion() {
		return rangosPromocion;
	}

	/**
	 * @param rangosPromocion the rangosPromocion to set
	 */
	public void setRangosPromocion(List rangosPromocion) {
		this.rangosPromocion = rangosPromocion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Oferta [oid=" + oid + ", oidEstrategia=" + oidEstrategia
				+ ", numeroGrupos=" + numeroGrupos
				+ ", numeroGruposCondicionados=" + numeroGruposCondicionados
				+ ", numeroGruposCondicionantes=" + numeroGruposCondicionantes
				+ ", numeroPaquetes=" + numeroPaquetes + ", condicionantes="
				+ condicionantes + ", condicionados=" + condicionados
				+ ", oidFormaPago=" + oidFormaPago + ", oidSubacceso="
				+ oidSubacceso + ", oidTipoEstrategia=" + oidTipoEstrategia
				+ ", oidAcceso=" + oidAcceso + ", oidMatriz=" + oidMatriz
				+ ", flagDespachoCompleto=" + flagDespachoCompleto
				+ ", flagDespachoAutomatico=" + flagDespachoAutomatico
				+ ", oidCatalogo=" + oidCatalogo + ", grupos=" + grupos + "]";
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
		Oferta other = (Oferta) obj;
		if (condicionados == null) {
			if (other.condicionados != null)
				return false;
		} else if (!condicionados.equals(other.condicionados))
			return false;
		if (condicionantes == null) {
			if (other.condicionantes != null)
				return false;
		} else if (!condicionantes.equals(other.condicionantes))
			return false;
		if (flagDespachoAutomatico == null) {
			if (other.flagDespachoAutomatico != null)
				return false;
		} else if (!flagDespachoAutomatico.equals(other.flagDespachoAutomatico))
			return false;
		if (flagDespachoCompleto == null) {
			if (other.flagDespachoCompleto != null)
				return false;
		} else if (!flagDespachoCompleto.equals(other.flagDespachoCompleto))
			return false;
		if (grupos == null) {
			if (other.grupos != null)
				return false;
		} else if (!grupos.equals(other.grupos))
			return false;
		if (numeroGrupos == null) {
			if (other.numeroGrupos != null)
				return false;
		} else if (!numeroGrupos.equals(other.numeroGrupos))
			return false;
		if (numeroGruposCondicionados == null) {
			if (other.numeroGruposCondicionados != null)
				return false;
		} else if (!numeroGruposCondicionados
				.equals(other.numeroGruposCondicionados))
			return false;
		if (numeroGruposCondicionantes == null) {
			if (other.numeroGruposCondicionantes != null)
				return false;
		} else if (!numeroGruposCondicionantes
				.equals(other.numeroGruposCondicionantes))
			return false;
		if (numeroPaquetes == null) {
			if (other.numeroPaquetes != null)
				return false;
		} else if (!numeroPaquetes.equals(other.numeroPaquetes))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (oidAcceso == null) {
			if (other.oidAcceso != null)
				return false;
		} else if (!oidAcceso.equals(other.oidAcceso))
			return false;
		if (oidCatalogo == null) {
			if (other.oidCatalogo != null)
				return false;
		} else if (!oidCatalogo.equals(other.oidCatalogo))
			return false;
		if (oidEstrategia == null) {
			if (other.oidEstrategia != null)
				return false;
		} else if (!oidEstrategia.equals(other.oidEstrategia))
			return false;
		if (oidFormaPago == null) {
			if (other.oidFormaPago != null)
				return false;
		} else if (!oidFormaPago.equals(other.oidFormaPago))
			return false;
		if (oidMatriz == null) {
			if (other.oidMatriz != null)
				return false;
		} else if (!oidMatriz.equals(other.oidMatriz))
			return false;
		if (oidSubacceso == null) {
			if (other.oidSubacceso != null)
				return false;
		} else if (!oidSubacceso.equals(other.oidSubacceso))
			return false;
		if (oidTipoEstrategia == null) {
			if (other.oidTipoEstrategia != null)
				return false;
		} else if (!oidTipoEstrategia.equals(other.oidTipoEstrategia))
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
				+ ((condicionados == null) ? 0 : condicionados.hashCode());
		result = prime * result
				+ ((condicionantes == null) ? 0 : condicionantes.hashCode());
		result = prime
				* result
				+ ((flagDespachoAutomatico == null) ? 0
						: flagDespachoAutomatico.hashCode());
		result = prime
				* result
				+ ((flagDespachoCompleto == null) ? 0 : flagDespachoCompleto
						.hashCode());
		result = prime * result + ((grupos == null) ? 0 : grupos.hashCode());
		result = prime * result
				+ ((numeroGrupos == null) ? 0 : numeroGrupos.hashCode());
		result = prime
				* result
				+ ((numeroGruposCondicionados == null) ? 0
						: numeroGruposCondicionados.hashCode());
		result = prime
				* result
				+ ((numeroGruposCondicionantes == null) ? 0
						: numeroGruposCondicionantes.hashCode());
		result = prime * result
				+ ((numeroPaquetes == null) ? 0 : numeroPaquetes.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result
				+ ((oidAcceso == null) ? 0 : oidAcceso.hashCode());
		result = prime * result
				+ ((oidCatalogo == null) ? 0 : oidCatalogo.hashCode());
		result = prime * result
				+ ((oidEstrategia == null) ? 0 : oidEstrategia.hashCode());
		result = prime * result
				+ ((oidFormaPago == null) ? 0 : oidFormaPago.hashCode());
		result = prime * result
				+ ((oidMatriz == null) ? 0 : oidMatriz.hashCode());
		result = prime * result
				+ ((oidSubacceso == null) ? 0 : oidSubacceso.hashCode());
		result = prime
				* result
				+ ((oidTipoEstrategia == null) ? 0 : oidTipoEstrategia
						.hashCode());
		return result;
	}

}
