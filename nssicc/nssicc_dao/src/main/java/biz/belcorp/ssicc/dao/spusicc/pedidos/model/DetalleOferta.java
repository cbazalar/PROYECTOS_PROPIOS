/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Ivan Tocto Jaimes
 *
 */
public class DetalleOferta extends AuditableBaseObject implements Serializable {

	private String oid;
	private String oidOferta;
	private String oidProducto;
	private String numeroLinea;
	private String textoBreve;
	private String unidadesEstimadas;
	private String factorRepeticion;
	private String flagPrincipal;
	private String precioCatalogo;
	private String precioPosicionamiento;
	private String costoEstandar;
	private String ventaNetaEstimada;
	private String numeroPaginaCatalogo;
	private String oidCatalogo;
	private String oidTipoOferta;
	private String oidFormaPago;
	private String oidGrupo;
	private String flagDigitable;
	private String flagImprimible;
	private String precioUnitario;
	
	
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
	 * @return the oidProducto
	 */
	public String getOidProducto() {
		return oidProducto;
	}

	/**
	 * @param oidProducto the oidProducto to set
	 */
	public void setOidProducto(String oidProducto) {
		this.oidProducto = oidProducto;
	}

	/**
	 * @return the numeroLinea
	 */
	public String getNumeroLinea() {
		return numeroLinea;
	}

	/**
	 * @param numeroLinea the numeroLinea to set
	 */
	public void setNumeroLinea(String numeroLinea) {
		this.numeroLinea = numeroLinea;
	}

	/**
	 * @return the textoBreve
	 */
	public String getTextoBreve() {
		return textoBreve;
	}

	/**
	 * @param textoBreve the textoBreve to set
	 */
	public void setTextoBreve(String textoBreve) {
		this.textoBreve = textoBreve;
	}

	/**
	 * @return the unidadesEstimadas
	 */
	public String getUnidadesEstimadas() {
		return unidadesEstimadas;
	}

	/**
	 * @param unidadesEstimadas the unidadesEstimadas to set
	 */
	public void setUnidadesEstimadas(String unidadesEstimadas) {
		this.unidadesEstimadas = unidadesEstimadas;
	}

	/**
	 * @return the factorRepeticion
	 */
	public String getFactorRepeticion() {
		return factorRepeticion;
	}

	/**
	 * @param factorRepeticion the factorRepeticion to set
	 */
	public void setFactorRepeticion(String factorRepeticion) {
		this.factorRepeticion = factorRepeticion;
	}

	/**
	 * @return the flagPrincipal
	 */
	public String getFlagPrincipal() {
		return flagPrincipal;
	}

	/**
	 * @param flagPrincipal the flagPrincipal to set
	 */
	public void setFlagPrincipal(String flagPrincipal) {
		this.flagPrincipal = flagPrincipal;
	}

	/**
	 * @return the precioCatalogo
	 */
	public String getPrecioCatalogo() {
		return precioCatalogo;
	}

	/**
	 * @param precioCatalogo the precioCatalogo to set
	 */
	public void setPrecioCatalogo(String precioCatalogo) {
		this.precioCatalogo = precioCatalogo;
	}

	/**
	 * @return the precioPosicionamiento
	 */
	public String getPrecioPosicionamiento() {
		return precioPosicionamiento;
	}

	/**
	 * @param precioPosicionamiento the precioPosicionamiento to set
	 */
	public void setPrecioPosicionamiento(String precioPosicionamiento) {
		this.precioPosicionamiento = precioPosicionamiento;
	}

	/**
	 * @return the costoEstandar
	 */
	public String getCostoEstandar() {
		return costoEstandar;
	}

	/**
	 * @param costoEstandar the costoEstandar to set
	 */
	public void setCostoEstandar(String costoEstandar) {
		this.costoEstandar = costoEstandar;
	}

	/**
	 * @return the ventaNetaEstimada
	 */
	public String getVentaNetaEstimada() {
		return ventaNetaEstimada;
	}

	/**
	 * @param ventaNetaEstimada the ventaNetaEstimada to set
	 */
	public void setVentaNetaEstimada(String ventaNetaEstimada) {
		this.ventaNetaEstimada = ventaNetaEstimada;
	}

	/**
	 * @return the numeroPaginaCatalogo
	 */
	public String getNumeroPaginaCatalogo() {
		return numeroPaginaCatalogo;
	}

	/**
	 * @param numeroPaginaCatalogo the numeroPaginaCatalogo to set
	 */
	public void setNumeroPaginaCatalogo(String numeroPaginaCatalogo) {
		this.numeroPaginaCatalogo = numeroPaginaCatalogo;
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
	 * @return the oidTipoOferta
	 */
	public String getOidTipoOferta() {
		return oidTipoOferta;
	}

	/**
	 * @param oidTipoOferta the oidTipoOferta to set
	 */
	public void setOidTipoOferta(String oidTipoOferta) {
		this.oidTipoOferta = oidTipoOferta;
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
	 * @return the oidGrupo
	 */
	public String getOidGrupo() {
		return oidGrupo;
	}

	/**
	 * @param oidGrupo the oidGrupo to set
	 */
	public void setOidGrupo(String oidGrupo) {
		this.oidGrupo = oidGrupo;
	}

	/**
	 * @return the flagDigitable
	 */
	public String getFlagDigitable() {
		return flagDigitable;
	}

	/**
	 * @param flagDigitable the flagDigitable to set
	 */
	public void setFlagDigitable(String flagDigitable) {
		this.flagDigitable = flagDigitable;
	}

	/**
	 * @return the flagImprimible
	 */
	public String getFlagImprimible() {
		return flagImprimible;
	}

	/**
	 * @param flagImprimible the flagImprimible to set
	 */
	public void setFlagImprimible(String flagImprimible) {
		this.flagImprimible = flagImprimible;
	}

	/**
	 * @return the precioUnitario
	 */
	public String getPrecioUnitario() {
		return precioUnitario;
	}

	/**
	 * @param precioUnitario the precioUnitario to set
	 */
	public void setPrecioUnitario(String precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DetalleOferta [oid=" + oid + ", oidOferta=" + oidOferta
				+ ", oidProducto=" + oidProducto + ", numeroLinea="
				+ numeroLinea + ", textoBreve=" + textoBreve
				+ ", unidadesEstimadas=" + unidadesEstimadas
				+ ", factorRepeticion=" + factorRepeticion + ", flagPrincipal="
				+ flagPrincipal + ", precioCatalogo=" + precioCatalogo
				+ ", precioPosicionamiento=" + precioPosicionamiento
				+ ", costoEstandar=" + costoEstandar + ", ventaNetaEstimada="
				+ ventaNetaEstimada + ", numeroPaginaCatalogo="
				+ numeroPaginaCatalogo + ", oidCatalogo=" + oidCatalogo
				+ ", oidTipoOferta=" + oidTipoOferta + ", oidFormaPago="
				+ oidFormaPago + ", oidGrupo=" + oidGrupo + ", flagDigitable="
				+ flagDigitable + ", flagImprimible=" + flagImprimible
				+ ", precioUnitario=" + precioUnitario + "]";
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
		DetalleOferta other = (DetalleOferta) obj;
		if (costoEstandar == null) {
			if (other.costoEstandar != null)
				return false;
		} else if (!costoEstandar.equals(other.costoEstandar))
			return false;
		if (factorRepeticion == null) {
			if (other.factorRepeticion != null)
				return false;
		} else if (!factorRepeticion.equals(other.factorRepeticion))
			return false;
		if (flagDigitable == null) {
			if (other.flagDigitable != null)
				return false;
		} else if (!flagDigitable.equals(other.flagDigitable))
			return false;
		if (flagImprimible == null) {
			if (other.flagImprimible != null)
				return false;
		} else if (!flagImprimible.equals(other.flagImprimible))
			return false;
		if (flagPrincipal == null) {
			if (other.flagPrincipal != null)
				return false;
		} else if (!flagPrincipal.equals(other.flagPrincipal))
			return false;
		if (numeroLinea == null) {
			if (other.numeroLinea != null)
				return false;
		} else if (!numeroLinea.equals(other.numeroLinea))
			return false;
		if (numeroPaginaCatalogo == null) {
			if (other.numeroPaginaCatalogo != null)
				return false;
		} else if (!numeroPaginaCatalogo.equals(other.numeroPaginaCatalogo))
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
		if (oidFormaPago == null) {
			if (other.oidFormaPago != null)
				return false;
		} else if (!oidFormaPago.equals(other.oidFormaPago))
			return false;
		if (oidGrupo == null) {
			if (other.oidGrupo != null)
				return false;
		} else if (!oidGrupo.equals(other.oidGrupo))
			return false;
		if (oidOferta == null) {
			if (other.oidOferta != null)
				return false;
		} else if (!oidOferta.equals(other.oidOferta))
			return false;
		if (oidProducto == null) {
			if (other.oidProducto != null)
				return false;
		} else if (!oidProducto.equals(other.oidProducto))
			return false;
		if (oidTipoOferta == null) {
			if (other.oidTipoOferta != null)
				return false;
		} else if (!oidTipoOferta.equals(other.oidTipoOferta))
			return false;
		if (precioCatalogo == null) {
			if (other.precioCatalogo != null)
				return false;
		} else if (!precioCatalogo.equals(other.precioCatalogo))
			return false;
		if (precioPosicionamiento == null) {
			if (other.precioPosicionamiento != null)
				return false;
		} else if (!precioPosicionamiento.equals(other.precioPosicionamiento))
			return false;
		if (precioUnitario == null) {
			if (other.precioUnitario != null)
				return false;
		} else if (!precioUnitario.equals(other.precioUnitario))
			return false;
		if (textoBreve == null) {
			if (other.textoBreve != null)
				return false;
		} else if (!textoBreve.equals(other.textoBreve))
			return false;
		if (unidadesEstimadas == null) {
			if (other.unidadesEstimadas != null)
				return false;
		} else if (!unidadesEstimadas.equals(other.unidadesEstimadas))
			return false;
		if (ventaNetaEstimada == null) {
			if (other.ventaNetaEstimada != null)
				return false;
		} else if (!ventaNetaEstimada.equals(other.ventaNetaEstimada))
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
				+ ((costoEstandar == null) ? 0 : costoEstandar.hashCode());
		result = prime
				* result
				+ ((factorRepeticion == null) ? 0 : factorRepeticion.hashCode());
		result = prime * result
				+ ((flagDigitable == null) ? 0 : flagDigitable.hashCode());
		result = prime * result
				+ ((flagImprimible == null) ? 0 : flagImprimible.hashCode());
		result = prime * result
				+ ((flagPrincipal == null) ? 0 : flagPrincipal.hashCode());
		result = prime * result
				+ ((numeroLinea == null) ? 0 : numeroLinea.hashCode());
		result = prime
				* result
				+ ((numeroPaginaCatalogo == null) ? 0 : numeroPaginaCatalogo
						.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result
				+ ((oidCatalogo == null) ? 0 : oidCatalogo.hashCode());
		result = prime * result
				+ ((oidFormaPago == null) ? 0 : oidFormaPago.hashCode());
		result = prime * result
				+ ((oidGrupo == null) ? 0 : oidGrupo.hashCode());
		result = prime * result
				+ ((oidOferta == null) ? 0 : oidOferta.hashCode());
		result = prime * result
				+ ((oidProducto == null) ? 0 : oidProducto.hashCode());
		result = prime * result
				+ ((oidTipoOferta == null) ? 0 : oidTipoOferta.hashCode());
		result = prime * result
				+ ((precioCatalogo == null) ? 0 : precioCatalogo.hashCode());
		result = prime
				* result
				+ ((precioPosicionamiento == null) ? 0 : precioPosicionamiento
						.hashCode());
		result = prime * result
				+ ((precioUnitario == null) ? 0 : precioUnitario.hashCode());
		result = prime * result
				+ ((textoBreve == null) ? 0 : textoBreve.hashCode());
		result = prime
				* result
				+ ((unidadesEstimadas == null) ? 0 : unidadesEstimadas
						.hashCode());
		result = prime
				* result
				+ ((ventaNetaEstimada == null) ? 0 : ventaNetaEstimada
						.hashCode());
		return result;
	}

}
