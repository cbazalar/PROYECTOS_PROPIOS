package biz.belcorp.ssicc.dao.sisicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * DAO de la Interfaz My LBEL.
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
public interface InterfazMLBDAO extends DAO {

	/**
	 * Se ejecuta la interfaz de Envio de Concursos para My Lbel
	 * 
	 * @param params
	 */
	public void executeInterfazMLBEnviarConcursos(Map params);

	/**
	 * Se ejecuta la interfaz de Envio de Niveles de Concursos para My Lbel
	 * 
	 * @param params
	 */
	public void executeInterfazMLBEnviarNiveles(Map params);

	/**
	 * Se ejecuta la interfaz de Envio de Premios de Concursos para My Lbel
	 * 
	 * @param params
	 */
	public void executeInterfazMLBEnviarPremios(Map params);

	/**
	 * Se ejecuta la interfaz de Envio de Descripcion Premios de Concursos para My Lbel
	 * 
	 * @param params
	 */
	public void executeInterfazMLBEnviarDescripcionPremios(Map params);
	
	/**
	 * Se ejecuta la interfaz de Envio de Zonas de Concursos para My Lbel
	 * 
	 * @param params
	 */
	public void executeInterfazMLBEnviarZonas(Map params);
	
	/**
	 * Se ejecuta la interfaz de Envio de Premios Asignados para My Lbel
	 * 
	 * @param params
	 */
	public void executeInterfazMLBEnviarPremiosAsignados(Map params);

	/**
	 * Se ejecuta la interfaz de Envio de Premios Despachados para My Lbel 
	 * 
	 * @param params
	 */
	public void executeInterfazMLBEnviarPremiosDespachados(Map params);

	/**
	 * Se ejecuta la interfaz de Envio de Puntajes para My Lbel 
	 * 
	 * @param params
	 */
	public void executeInterfazMLBEnviarPuntajes(Map params);

	/**
	 * Se ejecuta la interfaz de Envio de Recomendaciones para My Lbel 
	 * 
	 * @param params
	 */
	public void executeInterfazMLBEnviarRecomendaciones(Map params);

	/**
	 * Se ejecuta la interfaz de Envio de Archivo de Control para My Lbel
	 * @param params
	 */
	public void executeInterfazMLBEnviarArchivoControl(Map params);
	
}
