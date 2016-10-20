package biz.belcorp.ssicc.dao.spusicc.ventas.model;

import java.io.Serializable;

/**
 * Bean  que guarda  informacion de la entidad CCC_SUBPR
 * @author peextjnunez
 *
 */
public class SubProceso implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4562602049454011909L;
	private Integer oidSubProceso;/**Numero secuencial de Sub Proceso*/
	private Integer oidProceso;/**Numero secual asociado del Proceso*/
	private String codSubProceso; /**Codigo de SubProceso*/
	private String descripcion ;/**Descripcion de Sub Proceso*/
	private Integer inGestion; /**Indicado de gestion*/
	/**
	 * @return Returns the codSubProceso.
	 */
	public String getCodSubProceso() {
		return codSubProceso;
	}
	/**
	 * @param codSubProceso The codSubProceso to set.
	 */
	public void setCodSubProceso(String codSubProceso) {
		this.codSubProceso = codSubProceso;
	}
	/**
	 * @return Returns the descripcion.
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion The descripcion to set.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return Returns the inGestion.
	 */
	public Integer getInGestion() {
		return inGestion;
	}
	/**
	 * @param inGestion The inGestion to set.
	 */
	public void setInGestion(Integer inGestion) {
		this.inGestion = inGestion;
	}
	/**
	 * @return Returns the oidProceso.
	 */
	public Integer getOidProceso() {
		return oidProceso;
	}
	/**
	 * @param oidProceso The oidProceso to set.
	 */
	public void setOidProceso(Integer oidProceso) {
		this.oidProceso = oidProceso;
	}
	/**
	 * @return Returns the oidSubProceso.
	 */
	public Integer getOidSubProceso() {
		return oidSubProceso;
	}
	/**
	 * @param oidSubProceso The oidSubProceso to set.
	 */
	public void setOidSubProceso(Integer oidSubProceso) {
		this.oidSubProceso = oidSubProceso;
	}
	
	
}
	