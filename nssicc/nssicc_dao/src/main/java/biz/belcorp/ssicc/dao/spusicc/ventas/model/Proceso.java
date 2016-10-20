package biz.belcorp.ssicc.dao.spusicc.ventas.model;

import java.io.Serializable;

/**
 * Bean  que guarda  informacion de la entidad CCC_PROCE
 * @author peextjnunez
 *
 */
public class Proceso implements Serializable{
	
	private Integer oidProceso;/**Numero secuencial del Proceso*/
	private Integer oidPais ; /**Numero secual del Pais asociado al proceso*/
	private String codigoProceso; /**Codigo del Proceso*/
	private String descripcionProceso; /**Descripcion del prrceso*/
	private String observacion;/**Observacion del proceso*/
	private Integer indicadorCargo;/**Indicador de cargo y abono*/
	
	/**
	 * @return Returns the codigoProceso.
	 */
	public String getCodigoProceso() {
		return codigoProceso;
	}
	/**
	 * @param codigoProceso The codigoProceso to set.
	 */
	public void setCodigoProceso(String codigoProceso) {
		this.codigoProceso = codigoProceso;
	}
	/**
	 * @return Returns the descripcionProceso.
	 */
	public String getDescripcionProceso() {
		return descripcionProceso;
	}
	/**
	 * @param descripcionProceso The descripcionProceso to set.
	 */
	public void setDescripcionProceso(String descripcionProceso) {
		this.descripcionProceso = descripcionProceso;
	}
	/**
	 * @return Returns the indicadorCargo.
	 */
	public Integer getIndicadorCargo() {
		return indicadorCargo;
	}
	/**
	 * @param indicadorCargo The indicadorCargo to set.
	 */
	public void setIndicadorCargo(Integer indicadorCargo) {
		this.indicadorCargo = indicadorCargo;
	}
	/**
	 * @return Returns the observacion.
	 */
	public String getObservacion() {
		return observacion;
	}
	/**
	 * @param observacion The observacion to set.
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	/**
	 * @return Returns the oidPais.
	 */
	public Integer getOidPais() {
		return oidPais;
	}
	/**
	 * @param oidPais The oidPais to set.
	 */
	public void setOidPais(Integer oidPais) {
		this.oidPais = oidPais;
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
	
	
	
}
