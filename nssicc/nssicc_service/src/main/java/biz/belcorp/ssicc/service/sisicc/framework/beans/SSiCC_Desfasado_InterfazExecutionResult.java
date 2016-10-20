package biz.belcorp.ssicc.service.sisicc.framework.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;


/**
 * Clase que encapsula el resultado de la ejecucion de las Interfaces SiCC
 * unitarias o de paquete. Contiene el numero de lote y el detalle de la
 * ejecución de interfaces unitarias.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 * DESFASADO
 */
public class SSiCC_Desfasado_InterfazExecutionResult {
	private String numeroLote;

	private Interfaz interfaz;

	private List interfazResults;
	
	private boolean ejecutarSTO;
	
	/* INI CAMBIOS MULTILOTE */
	private Historico historicoEjecucion;
	/* FIN CAMBIOS MULTILOTE */

	public SSiCC_Desfasado_InterfazExecutionResult() {
		this.numeroLote = "";
		this.interfazResults = new ArrayList();
		ejecutarSTO=false;
	}

	/**
	 * Prueba si la ejecucion de la interfaz fue completada con exito.
	 * 
	 * @return true si todas las ejecuciones fueron completadas, false en caso
	 *         de que alguna halla fallado.
	 */
	public boolean ejecucionCompletada() {
		Iterator iterator = interfazResults.iterator();
		while (iterator.hasNext()) {
			InterfazResult interfazResult = (InterfazResult) iterator.next();
			if (!interfazResult.isCompletado())
				return false;
		}
		return true;
	}

	public List getInterfazResults() {
		return interfazResults;
	}

	public void setInterfazResults(List interfazResults) {
		this.interfazResults = interfazResults;
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

	/**
	 * @return the historicoEjecucion
	 */
	public Historico getHistoricoEjecucion() {
		return historicoEjecucion;
	}

	/**
	 * @param historicoEjecucion the historicoEjecucion to set
	 */
	public void setHistoricoEjecucion(Historico historicoEjecucion) {
		this.historicoEjecucion = historicoEjecucion;
	}
}

