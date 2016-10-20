package biz.belcorp.ssicc.service.sisicc.framework.beans;


/* NUEVA REESTRUCTURACION NSSICC */
/**
 * Clase que contiene el status de los procesos realizados en la ejecucion de Interfaces
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 */
public class InterfazStatus {
	
	private int ordenEjecucion;
	private boolean status;
	private String descripcionStatus;
	
	
	public InterfazStatus(int ejecucion, boolean s, String des) {
		this.ordenEjecucion = ejecucion;
		this.status = s;
		this.descripcionStatus = des;
	}


	/**
	 * @return the ordenEjecucion
	 */
	public int getOrdenEjecucion() {
		return ordenEjecucion;
	}


	/**
	 * @param ordenEjecucion the ordenEjecucion to set
	 */
	public void setOrdenEjecucion(int ordenEjecucion) {
		this.ordenEjecucion = ordenEjecucion;
	}


	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}


	/**
	 * @return the descripcionStatus
	 */
	public String getDescripcionStatus() {
		return descripcionStatus;
	}


	/**
	 * @param descripcionStatus the descripcionStatus to set
	 */
	public void setDescripcionStatus(String descripcionStatus) {
		this.descripcionStatus = descripcionStatus;
	}

	
	
}