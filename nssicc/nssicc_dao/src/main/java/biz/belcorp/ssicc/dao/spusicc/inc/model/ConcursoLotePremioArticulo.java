package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;
import java.util.List;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ConcursoLotePremioArticulo extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oid;
	private Integer numeroLote;
	private Integer numeroPremio;
	private Long oidPremioArticulo;
	private String descripcionLote;

	private List listConcursoArticuloLote;
	private List listConcursoArticuloLoteDescuento;
	private Integer indicadorPremiosWeb;
	
	public ConcursoLotePremioArticulo() {
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConcursoLotePremioArticulo [oid=" + oid + ", numeroLote="
				+ numeroLote + ", numeroPremio=" + numeroPremio
				+ ", oidPremioArticulo=" + oidPremioArticulo
				+ ", descripcionLote=" + descripcionLote
				+ ", indicadorPremiosWeb=" + indicadorPremiosWeb
				+ ", listConcursoArticuloLote=" + listConcursoArticuloLote
				+ "]";
	}

	/**
	 * @return the oid
	 */
	public Long getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 */
	public void setOid(Long oid) {
		this.oid = oid;
	}

	/**
	 * @return the numeroLote
	 */
	public Integer getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(Integer numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return the numeroPremio
	 */
	public Integer getNumeroPremio() {
		return numeroPremio;
	}

	/**
	 * @param numeroPremio the numeroPremio to set
	 */
	public void setNumeroPremio(Integer numeroPremio) {
		this.numeroPremio = numeroPremio;
	}

	/**
	 * @return the oidPremioArticulo
	 */
	public Long getOidPremioArticulo() {
		return oidPremioArticulo;
	}

	/**
	 * @param oidPremioArticulo the oidPremioArticulo to set
	 */
	public void setOidPremioArticulo(Long oidPremioArticulo) {
		this.oidPremioArticulo = oidPremioArticulo;
	}

	/**
	 * @return the descripcionLote
	 */
	public String getDescripcionLote() {
		return descripcionLote;
	}

	/**
	 * @param descripcionLote the descripcionLote to set
	 */
	public void setDescripcionLote(String descripcionLote) {
		this.descripcionLote = descripcionLote;
	}

	/**
	 * @return the listConcursoArticuloLote
	 */
	public List getListConcursoArticuloLote() {
		return listConcursoArticuloLote;
	}

	/**
	 * @param listConcursoArticuloLote the listConcursoArticuloLote to set
	 */
	public void setListConcursoArticuloLote(List listConcursoArticuloLote) {
		this.listConcursoArticuloLote = listConcursoArticuloLote;
	}

	public List getListConcursoArticuloLoteDescuento() {
		return listConcursoArticuloLoteDescuento;
	}

	public void setListConcursoArticuloLoteDescuento(
			List listConcursoArticuloLoteDescuento) {
		this.listConcursoArticuloLoteDescuento = listConcursoArticuloLoteDescuento;
	}

	public Integer getIndicadorPremiosWeb() {
		return indicadorPremiosWeb;
	}

	public void setIndicadorPremiosWeb(Integer indicadorPremiosWeb) {
		this.indicadorPremiosWeb = indicadorPremiosWeb;
	}
	
	

}
