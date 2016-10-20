/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;
import java.sql.Timestamp;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;
import biz.belcorp.ssicc.dao.util.DateUtil;

/**
 * @author Danny Amaro
 *
 */
public class PedidoSeguidoSTO extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 448272855322597674L;
		
	private String codigoPais;
	private String codigoConsultora;
	private String codigoPeriodo;
	private String etapa;
	private Timestamp fecha;
	private String fechaSeg;
	private String horaSeg;
	private String novedad;
	private String estado;
	private String grupo;
	private String orden;
	

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
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}
	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
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
	 * @return the etapa
	 */
	public String getEtapa() {
		return etapa;
	}
	/**
	 * @param etapa the etapa to set
	 */
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	/**
	 * @return the fecha
	 */
	public Timestamp getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Timestamp fecha) {	
		this.fecha = fecha;
		if(fecha!=null){
			this.fechaSeg = DateUtil.getDate(fecha);
			this.horaSeg = DateUtil.getTimeNow(fecha);
		}
	}
	/**
	 * @return the novedad
	 */
	public String getNovedad() {
		return novedad;
	}
	/**
	 * @param novedad the novedad to set
	 */
	public void setNovedad(String novedad) {
		this.novedad = novedad;
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
	 * @return the fechaSeg
	 */
	public String getFechaSeg() {
		return fechaSeg;
	}
	/**
	 * @param fechaSeg the fechaSeg to set
	 */
	public void setFechaSeg(String fechaSeg) {
		this.fechaSeg = fechaSeg;
	}
	/**
	 * @return the horaSeg
	 */
	public String getHoraSeg() {
		return horaSeg;
	}
	/**
	 * @param horaSeg the horaSeg to set
	 */
	public void setHoraSeg(String horaSeg) {
		this.horaSeg = horaSeg;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime				
				+ ((codigoConsultora == null) ? 0 : codigoConsultora.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());		
		result = prime * result
				+ ((codigoPeriodo == null) ? 0 : codigoPeriodo.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((etapa == null) ? 0 : etapa.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((novedad == null) ? 0 : novedad.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PedidoSeguidoSTO [codigoConsultora=" + codigoConsultora
				+ ", codigoPais=" + codigoPais + ", codigoPeriodo=" + codigoPeriodo
				+ ", estado=" + estado + ", etapa=" + etapa + ", fecha="
				+ fecha + ", novedad=" + novedad + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}	
		if (!(obj instanceof PedidoSeguidoSTO)) {
			return false;
		}
		PedidoSeguidoSTO other = (PedidoSeguidoSTO) obj;
		if (codigoConsultora == null) {
			if (other.codigoConsultora != null) {
				return false;
			}
		} else if (!codigoConsultora.equals(other.codigoConsultora)) {
			return false;
		}
		if (codigoPais == null) {
			if (other.codigoPais != null) {
				return false;
			}
		} else if (!codigoPais.equals(other.codigoPais)) {
			return false;
		}
		if (codigoPeriodo == null) {
			if (other.codigoPeriodo != null) {
				return false;
			}
		} else if (!codigoPeriodo.equals(other.codigoPeriodo)) {
			return false;
		}
		if (estado == null) {
			if (other.estado != null) {
				return false;
			}
		} else if (!estado.equals(other.estado)) {
			return false;
		}
		if (etapa == null) {
			if (other.etapa != null) {
				return false;
			}
		} else if (!etapa.equals(other.etapa)) {
			return false;
		}
		if (fecha == null) {
			if (other.fecha != null) {
				return false;
			}
		} else if (!fecha.equals(other.fecha)) {
			return false;
		}
		if (novedad == null) {
			if (other.novedad != null) {
				return false;
			}
		} else if (!novedad.equals(other.novedad)) {
			return false;
		}
		return true;
	}
	/**
	 * @return the grupo
	 */
	public String getGrupo() {
		return grupo;
	}
	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	/**
	 * @return the orden
	 */
	public String getOrden() {
		return orden;
	}
	/**
	 * @param orden the orden to set
	 */
	public void setOrden(String orden) {
		this.orden = orden;
	}
	
	
}
