package biz.belcorp.ssicc.service.sisicc.framework.beans;

import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;

/* NUEVA REESTRUCTURACION NSSICC */
/**
 * Clase que encapsula el resultado de la ejecucion de las Interfaces SiCC
 * unitarias.
 * Mejoras Realizadas NSSICC:
 *  - Colocacion de indicadores booleanos completados por etapas
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 * 
 */
public class InterfazResult {
	private String numeroLote;

	private Interfaz interfaz;

	private String mensaje = "";

	private int registrosProcesados = 0;

	private boolean completado = false;
	
	/* INI FRAMEWORK NSSICC */
	private Long nroNivel;
	private Long nroHilo;
	
	private boolean completadoProcesosBeforeInterfaz = false;
	private boolean completadoInterfaz = false;
	private boolean completadoProcesosAfterInterfaz = false;
	/* FIN FRAMEWORK NSSICC */

	
	/**
	 * Constructor BASE
	 */
	public InterfazResult() {

	}	
	
	/**
	 * @param numeroLote
	 * @param interfaz
	 */
	public InterfazResult(String numeroLote, Interfaz interfaz) {
		this.numeroLote = numeroLote;
		this.interfaz = interfaz;
		
		/* INI FRAMEWORK NSSICC */
		this.nroNivel = interfaz.getNivelHilo();
		this.nroHilo = interfaz.getOrdenHilo(); 
		this.completadoProcesosBeforeInterfaz = false;
		this.completadoInterfaz = false;
		this.completadoProcesosAfterInterfaz = false;
		/* FIN FRAMEWORK NSSICC */
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	public int getRegistrosProcesados() {
		return registrosProcesados;
	}

	public void setRegistrosProcesados(int registrosProcesados) {
		this.registrosProcesados = registrosProcesados;
	}

	public boolean isCompletado() {
		return completado;
	}

	public void setCompletado(boolean completado) {
		this.completado = completado;
	}

	public Interfaz getInterfaz() {
		return interfaz;
	}

	public void setInterfaz(Interfaz interfaz) {
		this.interfaz = interfaz;
	}

	/* INI FRMAEWORK NSSICC */
	/**
	 * @return the completadoProcesosBeforeInterfaz
	 */
	public boolean isCompletadoProcesosBeforeInterfaz() {
		return completadoProcesosBeforeInterfaz;
	}

	/**
	 * @param completadoProcesosBeforeInterfaz the completadoProcesosBeforeInterfaz to set
	 */
	public void setCompletadoProcesosBeforeInterfaz(
			boolean completadoProcesosBeforeInterfaz) {
		this.completadoProcesosBeforeInterfaz = completadoProcesosBeforeInterfaz;
	}

	

	
	/**
	 * @return the completadoInterfaz
	 */
	public boolean isCompletadoInterfaz() {
		return completadoInterfaz;
	}

	/**
	 * @param completadoInterfaz the completadoInterfaz to set
	 */
	public void setCompletadoInterfaz(boolean completadoInterfaz) {
		this.completadoInterfaz = completadoInterfaz;
	}

		

	/**
	 * @return the completadoProcesosAfterInterfaz
	 */
	public boolean isCompletadoProcesosAfterInterfaz() {
		return completadoProcesosAfterInterfaz;
	}

	/**
	 * @param completadoProcesosAfterInterfaz the completadoProcesosAfterInterfaz to set
	 */
	public void setCompletadoProcesosAfterInterfaz(
			boolean completadoProcesosAfterInterfaz) {
		this.completadoProcesosAfterInterfaz = completadoProcesosAfterInterfaz;
	}

	/**
	 * @return the nroNivel
	 */
	public Long getNroNivel() {
		return nroNivel;
	}

	/**
	 * @param nroNivel the nroNivel to set
	 */
	public void setNroNivel(Long nroNivel) {
		this.nroNivel = nroNivel;
	}

	/**
	 * @return the nroHilo
	 */
	public Long getNroHilo() {
		return nroHilo;
	}

	/**
	 * @param nroHilo the nroHilo to set
	 */
	public void setNroHilo(Long nroHilo) {
		this.nroHilo = nroHilo;
	}

	
	
	
	
	/* FIN FRMAEWORK NSSICC */
	
	
}
