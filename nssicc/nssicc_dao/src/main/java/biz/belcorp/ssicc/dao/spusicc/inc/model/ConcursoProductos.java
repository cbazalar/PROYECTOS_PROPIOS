package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;
import java.util.List;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ConcursoProductos extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oid;
	private Integer comunicacionValidos;
	private String mensajeValidos;
	private Integer comunicacionExigidos;
	private String mensajeExigidos;
	private Integer comunicacionExcluidos;
	private String mensajeExcluidos;
	private Integer comunicacionBonificados;
	private Long oidConcurso;
	private String mensajeBonificados;
	
	private List listConcursoProductosValidos;
	private List listConcursoProductosBonificados;
	private List listConcursoProductosExcluidos;
	private List listConcursoProductosExigidos;
	
	private boolean indRedefinirProductosValidos;
	private boolean indRedefinirProductosBonificados;
	private boolean indRedefinirProductosExcluidos;
	private boolean indRedefinirProductosExigidos;
	
	public ConcursoProductos() {
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
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
	 * @return the comunicacionValidos
	 */
	public Integer getComunicacionValidos() {
		return comunicacionValidos;
	}

	/**
	 * @param comunicacionValidos the comunicacionValidos to set
	 */
	public void setComunicacionValidos(Integer comunicacionValidos) {
		this.comunicacionValidos = comunicacionValidos;
	}

	/**
	 * @return the mensajeValidos
	 */
	public String getMensajeValidos() {
		return mensajeValidos;
	}

	/**
	 * @param mensajeValidos the mensajeValidos to set
	 */
	public void setMensajeValidos(String mensajeValidos) {
		this.mensajeValidos = mensajeValidos;
	}

	/**
	 * @return the comunicacionExigidos
	 */
	public Integer getComunicacionExigidos() {
		return comunicacionExigidos;
	}

	/**
	 * @param comunicacionExigidos the comunicacionExigidos to set
	 */
	public void setComunicacionExigidos(Integer comunicacionExigidos) {
		this.comunicacionExigidos = comunicacionExigidos;
	}

	/**
	 * @return the mensajeExigidos
	 */
	public String getMensajeExigidos() {
		return mensajeExigidos;
	}

	/**
	 * @param mensajeExigidos the mensajeExigidos to set
	 */
	public void setMensajeExigidos(String mensajeExigidos) {
		this.mensajeExigidos = mensajeExigidos;
	}

	/**
	 * @return the comunicacionExcluidos
	 */
	public Integer getComunicacionExcluidos() {
		return comunicacionExcluidos;
	}

	/**
	 * @param comunicacionExcluidos the comunicacionExcluidos to set
	 */
	public void setComunicacionExcluidos(Integer comunicacionExcluidos) {
		this.comunicacionExcluidos = comunicacionExcluidos;
	}

	/**
	 * @return the mensajeExcluidos
	 */
	public String getMensajeExcluidos() {
		return mensajeExcluidos;
	}

	/**
	 * @param mensajeExcluidos the mensajeExcluidos to set
	 */
	public void setMensajeExcluidos(String mensajeExcluidos) {
		this.mensajeExcluidos = mensajeExcluidos;
	}

	/**
	 * @return the comunicacionBonificados
	 */
	public Integer getComunicacionBonificados() {
		return comunicacionBonificados;
	}

	/**
	 * @param comunicacionBonificados the comunicacionBonificados to set
	 */
	public void setComunicacionBonificados(Integer comunicacionBonificados) {
		this.comunicacionBonificados = comunicacionBonificados;
	}

	/**
	 * @return the oidConcurso
	 */
	public Long getOidConcurso() {
		return oidConcurso;
	}

	/**
	 * @param oidConcurso the oidConcurso to set
	 */
	public void setOidConcurso(Long oidConcurso) {
		this.oidConcurso = oidConcurso;
	}

	/**
	 * @return the mensajeBonificados
	 */
	public String getMensajeBonificados() {
		return mensajeBonificados;
	}

	/**
	 * @param mensajeBonificados the mensajeBonificados to set
	 */
	public void setMensajeBonificados(String mensajeBonificados) {
		this.mensajeBonificados = mensajeBonificados;
	}

	/**
	 * @return the listConcursoProductosValidos
	 */
	public List getListConcursoProductosValidos() {
		return listConcursoProductosValidos;
	}

	/**
	 * @param listConcursoProductosValidos the listConcursoProductosValidos to set
	 */
	public void setListConcursoProductosValidos(List listConcursoProductosValidos) {
		this.listConcursoProductosValidos = listConcursoProductosValidos;
	}

	/**
	 * @return the listConcursoProductosBonificados
	 */
	public List getListConcursoProductosBonificados() {
		return listConcursoProductosBonificados;
	}

	/**
	 * @param listConcursoProductosBonificados the listConcursoProductosBonificados to set
	 */
	public void setListConcursoProductosBonificados(
			List listConcursoProductosBonificados) {
		this.listConcursoProductosBonificados = listConcursoProductosBonificados;
	}

	/**
	 * @return the listConcursoProductosExcluidos
	 */
	public List getListConcursoProductosExcluidos() {
		return listConcursoProductosExcluidos;
	}

	/**
	 * @param listConcursoProductosExcluidos the listConcursoProductosExcluidos to set
	 */
	public void setListConcursoProductosExcluidos(
			List listConcursoProductosExcluidos) {
		this.listConcursoProductosExcluidos = listConcursoProductosExcluidos;
	}

	/**
	 * @return the listConcursoProductosExigidos
	 */
	public List getListConcursoProductosExigidos() {
		return listConcursoProductosExigidos;
	}

	/**
	 * @param listConcursoProductosExigidos the listConcursoProductosExigidos to set
	 */
	public void setListConcursoProductosExigidos(List listConcursoProductosExigidos) {
		this.listConcursoProductosExigidos = listConcursoProductosExigidos;
	}

	/**
	 * @return the indRedefinirProductosValidos
	 */
	public boolean isIndRedefinirProductosValidos() {
		return indRedefinirProductosValidos;
	}

	/**
	 * @param indRedefinirProductosValidos the indRedefinirProductosValidos to set
	 */
	public void setIndRedefinirProductosValidos(boolean indRedefinirProductosValidos) {
		this.indRedefinirProductosValidos = indRedefinirProductosValidos;
	}

	/**
	 * @return the indRedefinirProductosBonificados
	 */
	public boolean isIndRedefinirProductosBonificados() {
		return indRedefinirProductosBonificados;
	}

	/**
	 * @param indRedefinirProductosBonificados the indRedefinirProductosBonificados to set
	 */
	public void setIndRedefinirProductosBonificados(
			boolean indRedefinirProductosBonificados) {
		this.indRedefinirProductosBonificados = indRedefinirProductosBonificados;
	}

	/**
	 * @return the indRedefinirProductosExcluidos
	 */
	public boolean isIndRedefinirProductosExcluidos() {
		return indRedefinirProductosExcluidos;
	}

	/**
	 * @param indRedefinirProductosExcluidos the indRedefinirProductosExcluidos to set
	 */
	public void setIndRedefinirProductosExcluidos(
			boolean indRedefinirProductosExcluidos) {
		this.indRedefinirProductosExcluidos = indRedefinirProductosExcluidos;
	}

	/**
	 * @return the indRedefinirProductosExigidos
	 */
	public boolean isIndRedefinirProductosExigidos() {
		return indRedefinirProductosExigidos;
	}

	/**
	 * @param indRedefinirProductosExigidos the indRedefinirProductosExigidos to set
	 */
	public void setIndRedefinirProductosExigidos(
			boolean indRedefinirProductosExigidos) {
		this.indRedefinirProductosExigidos = indRedefinirProductosExigidos;
	}

}
