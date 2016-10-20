/**
 * 
 */
package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="UsuarioBloqueo.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public class UsuarioOpcionHiperConsulta extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2726951425380061294L;

	private String codigoPais;
	
    private String codigoUsuarioBloqueo;

    private String codigoOpcionConsulta;

    private String nombreOpcionConsulta;
    
    private long correlativo;
    
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		UsuarioOpcionHiperConsulta other = (UsuarioOpcionHiperConsulta) obj;
		
		if (this.codigoPais == null) {
			if (other.codigoPais != null) {
				return false;
			}
		} else if (!this.codigoPais.equals(other.codigoPais)) {
			return false;
		}
		if (this.codigoOpcionConsulta == null) {
			if (other.codigoOpcionConsulta != null) {
				return false;
			}
		} else if (!this.codigoOpcionConsulta.equals(other.codigoOpcionConsulta)) {
			return false;
		}
		if (this.codigoUsuarioBloqueo == null) {
			if (other.codigoUsuarioBloqueo != null) {
				return false;
			}
		} else if (!this.codigoUsuarioBloqueo.equals(other.codigoUsuarioBloqueo)) {
			return false;
		}
		if (this.correlativo != other.correlativo) {
			return false;
		}
			
		if (this.nombreOpcionConsulta == null) {
			if (other.nombreOpcionConsulta != null) {
				return false;
			}
		} else if (!this.nombreOpcionConsulta.equals(other.nombreOpcionConsulta)) {
			return false;
		}
		return true;
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 13;
		result = prime * result
				+ ((this.codigoPais == null) ? 0 : this.codigoPais.hashCode());
		result = prime
				* result
				+ ((this.codigoOpcionConsulta == null) ? 0 : this.codigoOpcionConsulta
						.hashCode());
		result = prime * result
				+ ((this.codigoUsuarioBloqueo == null) ? 0 : this.codigoUsuarioBloqueo.hashCode());
		result = prime * result + (int) (this.correlativo ^ (this.correlativo >>> 32));
		result = prime
				* result
				+ ((this.nombreOpcionConsulta == null) ? 0 : this.nombreOpcionConsulta
						.hashCode());
		return result;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UsuarioBloqueo [codigoPais="
				+ this.codigoPais + ", codigoOpcionConsulta=" + this.codigoOpcionConsulta
				+ ", codigoUsuarioBloqueo=" + this.codigoUsuarioBloqueo + ", correlativo="
				+ this.correlativo +  this.nombreOpcionConsulta
				+ "]";
	}



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
	 * @return the codigoUsuarioBloqueo
	 */
	public String getCodigoUsuarioBloqueo() {
		return codigoUsuarioBloqueo;
	}



	/**
	 * @param codigoUsuarioBloqueo the codigoUsuarioBloqueo to set
	 */
	public void setCodigoUsuarioBloqueo(String codigoUsuarioBloqueo) {
		this.codigoUsuarioBloqueo = codigoUsuarioBloqueo;
	}



	/**
	 * @return the codigoOpcionConsulta
	 */
	public String getCodigoOpcionConsulta() {
		return codigoOpcionConsulta;
	}



	/**
	 * @param codigoOpcionConsulta the codigoOpcionConsulta to set
	 */
	public void setCodigoOpcionConsulta(String codigoOpcionConsulta) {
		this.codigoOpcionConsulta = codigoOpcionConsulta;
	}




	/**
	 * @return the nombreOpcionConsulta
	 */
	public String getNombreOpcionConsulta() {
		return nombreOpcionConsulta;
	}



	/**
	 * @param nombreOpcionConsulta the nombreOpcionConsulta to set
	 */
	public void setNombreOpcionConsulta(String nombreOpcionConsulta) {
		this.nombreOpcionConsulta = nombreOpcionConsulta;
	}



	/**
	 * @return the correlativo
	 */
	public long getCorrelativo() {
		return correlativo;
	}



	/**
	 * @param correlativo the correlativo to set
	 */
	public void setCorrelativo(long correlativo) {
		this.correlativo = correlativo;
	}
	
	

}
