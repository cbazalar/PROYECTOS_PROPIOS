/*
 * Created on 23-03-2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.spusicc.sicc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="Base.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 */
public class OpcionesSICC implements Serializable {
	
	private static final long serialVersionUID = 4383729701106113577L;

	private Long oid;
	private String descripcion;
	private String indicadorNivel;
	private Long oidPadre;
	private String valorAcceso;
	private Integer ramaMenu;

	private boolean estadoAcceso;
	
	/**
	 * @return Returns the oid.
	 */
	public Long getOid() {
		return oid;
	}

	/**
	 * @param oid The oid to set.
	 */
	public void setOid(Long oid) {
		this.oid = oid;
	}

	/**
	 * @return Returns the descripcion.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            The descripcion to set.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("oid", this.oid).append(
				"descripcion", this.descripcion).toString();
	}

	/**
	 * @return the indicadorNivel
	 */
	public String getIndicadorNivel() {
		return indicadorNivel;
	}

	/**
	 * @param indicadorNivel the indicadorNivel to set
	 */
	public void setIndicadorNivel(String indicadorNivel) {
		this.indicadorNivel = indicadorNivel;
	}

	/**
	 * @return the oidPadre
	 */
	public Long getOidPadre() {
		return oidPadre;
	}

	/**
	 * @param oidPadre the oidPadre to set
	 */
	public void setOidPadre(Long oidPadre) {
		this.oidPadre = oidPadre;
	}

	/**
	 * @return the valorAcceso
	 */
	public String getValorAcceso() {
		return valorAcceso;
	}

	/**
	 * @param valorAcceso the valorAcceso to set
	 */
	public void setValorAcceso(String valorAcceso) {
		this.valorAcceso = valorAcceso;
	}

	/**
	 * @return the ramaMenu
	 */
	public Integer getRamaMenu() {
		return ramaMenu;
	}

	/**
	 * @param ramaMenu the ramaMenu to set
	 */
	public void setRamaMenu(Integer ramaMenu) {
		this.ramaMenu = ramaMenu;
	}

	/**
	 * @return the estadoAcceso
	 */
	public boolean isEstadoAcceso() {
		return estadoAcceso;
	}

	/**
	 * @param estadoAcceso the estadoAcceso to set
	 */
	public void setEstadoAcceso(boolean estadoAcceso) {
		this.estadoAcceso = estadoAcceso;
	}
	
}
