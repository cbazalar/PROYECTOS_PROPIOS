package biz.belcorp.ssicc.dao.scsicc.model;

import java.io.Serializable;

/**
 *  @author <a href="mailto:">Efrain Fernandez</a>
 */

public class CronogramaActividades implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codActividad;
	private String desActividad;
	private String fecPeriodoAnterior;
	private String fecPeriodoActual;
	private String fecPeriodoDespues;
	private String fecPeriodoDespues2;
	
	/**
	 * @return
	 */
	public String getCodActividad() {
		return codActividad;
	}
	/**
	 * @param codActividad
	 */
	public void setCodActividad(String codActividad) {
		this.codActividad = codActividad;
	}
	/**
	 * @return
	 */
	public String getDesActividad() {
		return desActividad;
	}
	/**
	 * @param desActividad
	 */
	public void setDesActividad(String desActividad) {
		this.desActividad = desActividad;
	}

	/**
	 * @return
	 */
	public String getFecPeriodoAnterior() {
		return fecPeriodoAnterior;
	}
	/**
	 * @param fecPeriodoAnterior
	 */
	public void setFecPeriodoAnterior(String fecPeriodoAnterior) {
		this.fecPeriodoAnterior = fecPeriodoAnterior;
	}
	/**
	 * @return
	 */
	public String getFecPeriodoActual() {
		return fecPeriodoActual;
	}
	/**
	 * @param fecPeriodoActual
	 */
	public void setFecPeriodoActual(String fecPeriodoActual) {
		this.fecPeriodoActual = fecPeriodoActual;
	}
	/**
	 * @return
	 */
	public String getFecPeriodoDespues() {
		return fecPeriodoDespues;
	}
	/**
	 * @param fecPeriodoDespues
	 */
	public void setFecPeriodoDespues(String fecPeriodoDespues) {
		this.fecPeriodoDespues = fecPeriodoDespues;
	}
	/**
	 * @return the fecPeriodoDespues2
	 */
	public String getFecPeriodoDespues2() {
		return fecPeriodoDespues2;
	}
	/**
	 * @param fecPeriodoDespues2 the fecPeriodoDespues2 to set
	 */
	public void setFecPeriodoDespues2(String fecPeriodoDespues2) {
		this.fecPeriodoDespues2 = fecPeriodoDespues2;
	}
	
}
