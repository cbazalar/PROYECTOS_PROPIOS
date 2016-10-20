package biz.belcorp.ssicc.service.sisicc.framework.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;


/* NUEVA REESTRUCTURACION NSSICC */
/**
 * Clase que encapsula el resultado de la ejecucion de las Interfaces SiCC
 * unitarias o de paquete. Contiene el numero de lote y el detalle de la
 * ejecucion de interfaces unitarias.
 * 
 * Mejoras Realizadas NSSICC:
 *  - Creacion de lista de Interfaces del Paquete
 *  - Creacion de lista de Interfaces Seleccionadas del Paquete
 *  - Colocacion de <InterfazResult> en atributo interfazResults
 *  - Creacion de Constructor con Parametros
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
public class InterfazExecutionResult {
	private String numeroLote;

	private Interfaz interfaz;
	
	private InterfazResult interfazResultPaquete;

	private List<InterfazResult> interfazResultsInterfaz;
	
	private boolean ejecutarSTO;
	
	/* INI FRAMEWORK NSSICC */
	List<Interfaz> listaInterfacesEmpaquetadas;
	private List<Map> listaParamsInterfaz;
	/* FIN FRAMEWORK NSSICC */
	

	/**
	 * Constructor de la clase
	 */
	public InterfazExecutionResult() {
		this.numeroLote = "";
		this.interfaz = new Interfaz();
		this.interfazResultPaquete = new InterfazResult();
		this.interfazResultsInterfaz = new ArrayList();
		this.listaInterfacesEmpaquetadas = new ArrayList<Interfaz>(); 
		this.ejecutarSTO = false;
		this.listaParamsInterfaz  = new ArrayList();
	}
	
	/* INI FRAMEWORK NSSICC */
	/**
	 * Constructor de la clase
	 * @param numeroLote
	 * @param interfaz
	 */
	public InterfazExecutionResult(String numeroLote, Interfaz interfaz) {
		this.numeroLote = numeroLote;
		this.interfaz = interfaz;
		this.interfazResultsInterfaz = new ArrayList();
		this.listaInterfacesEmpaquetadas = new ArrayList<Interfaz>();
		this.ejecutarSTO = false;
		this.listaParamsInterfaz  = new ArrayList();
	}
	/* FIN FRAMEWORK NSSICC */

	/**
	 * Prueba si la ejecucion de la interfaz fue completada con exito.
	 * 
	 * @return true si todas las ejecuciones fueron completadas, false en caso
	 *         de que alguna halla fallado.
	 */
	public boolean ejecucionCompletada() {
		/*if (!this.interfazResultPaquete.isCompletado())
			return false;
		*/
		
		Iterator iterator = interfazResultsInterfaz.iterator();
		while (iterator.hasNext()) {
			InterfazResult interfazResult = (InterfazResult) iterator.next();
			if (!interfazResult.isCompletado())
				return false;
		}
		return true;
	}

	
	public String getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	public Interfaz getInterfaz() {
		return interfaz;
	}

	public void setInterfaz(Interfaz interfaz) {
		this.interfaz = interfaz;
	}

	/**
	 * @return the ejecutarSTO
	 */
	public boolean isEjecutarSTO() {
		return ejecutarSTO;
	}

	/**
	 * @param ejecutarSTO the ejecutarSTO to set
	 */
	public void setEjecutarSTO(boolean ejecutarSTO) {
		this.ejecutarSTO = ejecutarSTO;
	}

	
	/* INI FRAMEWORK NSSICC */

	/**
	 * @return the listaInterfacesEmpaquetadas
	 */
	public List<Interfaz> getListaInterfacesEmpaquetadas() {
		return listaInterfacesEmpaquetadas;
	}

	/**
	 * @param listaInterfacesEmpaquetadas the listaInterfacesEmpaquetadas to set
	 */
	public void setListaInterfacesEmpaquetadas(
			List<Interfaz> listaInterfacesEmpaquetadas) {
		this.listaInterfacesEmpaquetadas = listaInterfacesEmpaquetadas;
	}

	/**
	 * @return the interfazResultPaquete
	 */
	public InterfazResult getInterfazResultPaquete() {
		return interfazResultPaquete;
	}

	/**
	 * @param interfazResultPaquete the interfazResultPaquete to set
	 */
	public void setInterfazResultPaquete(InterfazResult interfazResultPaquete) {
		this.interfazResultPaquete = interfazResultPaquete;
	}

	/**
	 * @return the interfazResultsInterfaz
	 */
	public List<InterfazResult> getInterfazResultsInterfaz() {
		return interfazResultsInterfaz;
	}

	/**
	 * @param interfazResultsInterfaz the interfazResultsInterfaz to set
	 */
	public void setInterfazResultsInterfaz(
			List<InterfazResult> interfazResultsInterfaz) {
		this.interfazResultsInterfaz = interfazResultsInterfaz;
	}

	/**
	 * @return the listaParamsInterfaz
	 */
	public List<Map> getListaParamsInterfaz() {
		return listaParamsInterfaz;
	}

	/**
	 * @param listaParamsInterfaz the listaParamsInterfaz to set
	 */
	public void setListaParamsInterfaz(List<Map> listaParamsInterfaz) {
		this.listaParamsInterfaz = listaParamsInterfaz;
	}
	
	
	/**
	 * @return
	 */
	public List getInterfazResults() {
		return interfazResultsInterfaz;
	}

	
	/* FIN FRAMEWORK NSSICC */
	
}
