package biz.belcorp.ssicc.service.edu;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *
 */
public interface ProcesoEDUCalificacionAptasAutomaticaService {
	
	
	/**
	 * Devuelve Lista de cursos que se pueden realizar calificacion automatica de Aptas 
	 * @param criteria
	 * @return
	 */
	public List getCursosCalificacionAptasAutomaticaByCriteria(Map criteria);
		
	/**
	 * Realiza el proceso de Calificacin de Aptas Automtica
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	public void executeCalificacionAptasAutomatica(String codigoPais, Map params) throws Exception;
	
	
	/**
	 * Realiza el proceso de Calificacin y Envio de Aptas Automatica
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	public void executeCalificacionEnviarAptasAutomatica(String codigoPais, Map params) throws Exception;
	
	/**
	 * Funcion que devuelve la campaa Actual de Proceso
	 * @param criteria
	 * @return
	 */
	public String getCampannaActualProceso(Map criteria);
	
	/**
	 * Funcion que devuelve la Fecha de Facturacin de la 
     * Campaa de Proceso
	 * @param criteria
	 * @return
	 */
	public String getFechaProcesoFacturacion(Map criteria);
	
}
