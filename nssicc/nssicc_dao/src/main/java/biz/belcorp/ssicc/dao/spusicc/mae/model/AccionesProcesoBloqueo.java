package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;


/**
 * @author <a href="ghuertas@sigcomt.com">Gonzalo Huertas</a>
 *
 */
public class AccionesProcesoBloqueo extends AuditableBaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String oid;
	private String codigoPais;
	private String oidTipoBloqueo;
	private String oidProcesoBloqueo;
	private String oidAccionBloqueo;
	private String indicadorEstado;


	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getOidTipoBloqueo() {
		return oidTipoBloqueo;
	}

	public void setOidTipoBloqueo(String oidTipoBloqueo) {
		this.oidTipoBloqueo = oidTipoBloqueo;
	}

	public String getOidProcesoBloqueo() {
		return oidProcesoBloqueo;
	}

	public void setOidProcesoBloqueo(String oidProcesoBloqueo) {
		this.oidProcesoBloqueo = oidProcesoBloqueo;
	}

	public String getOidAccionBloqueo() {
		return oidAccionBloqueo;
	}

	public void setOidAccionBloqueo(String oidAccionBloqueo) {
		this.oidAccionBloqueo = oidAccionBloqueo;
	}

	public String getIndicadorEstado() {
		return indicadorEstado;
	}

	public void setIndicadorEstado(String indicadorEstado) {
		this.indicadorEstado = indicadorEstado;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "AccionesProcesoBloqueo [codigoPais=" + codigoPais 
				+ ", oidTipoBloqueo=" + oidTipoBloqueo
				+ ", oidProcesoBloqueo=" + oidProcesoBloqueo
				+ ", oidAccionBloqueo=" + oidAccionBloqueo
				+ ", indicadorEstado=" + indicadorEstado+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccionesProcesoBloqueo other = (AccionesProcesoBloqueo) obj;
		
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (indicadorEstado == null) {
			if (other.indicadorEstado != null)
				return false;
		} else if (!indicadorEstado.equals(other.indicadorEstado))
			return false;
		if (oidTipoBloqueo == null) {
			if (other.oidTipoBloqueo != null)
				return false;
		} else if (!oidTipoBloqueo.equals(other.oidTipoBloqueo))
			return false;
		if (oidProcesoBloqueo == null) {
			if (other.oidProcesoBloqueo != null)
				return false;
		} else if (!oidProcesoBloqueo.equals(other.oidProcesoBloqueo))
			return false;
		if (oidAccionBloqueo ==  null) {
			if (other.oidAccionBloqueo != null)
				return false;
		} else if (!oidAccionBloqueo.equals(other.oidAccionBloqueo))
			return false;
		return true;

	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		
		final int prime = 31;
		int result = 1;
		
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime
				* result
				+ ((oidTipoBloqueo == null) ? 0
						: oidTipoBloqueo.hashCode());
		result = prime * result
				+ ((oidProcesoBloqueo == null) ? 0 : oidProcesoBloqueo.hashCode());
		result = prime * result
				+ ((oidAccionBloqueo  == null) ? 0 : oidAccionBloqueo.hashCode());
		result = prime * result
				+ ((indicadorEstado  == null) ? 0 : indicadorEstado.hashCode());
		
		return result;
	}


}
