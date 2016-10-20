/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc;


import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;


/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazSABDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 * 
 */

public interface InterfazLETDAO extends DAO {

	/**
	 * Proceso que ejecuta Envo de Campaas Activas
	 * @param params
	 */
	void executeInterfazLETEnviarCampannaActiva(Map params);

	/**
	 * Proceso que ejecuta Envo de Consultoras
	 * @param params
	 */
	void executeInterfazLETEnviarConsultoras(Map params);

	/**
	 * Proceso que ejecuta Envo de Tipos Operacin
	 * @param params
	 */
	void executeInterfazLETEnviarTipoOperacion(Map params);

	/**
	 * Proceso que ejecuta Envo Regiones
	 * @param params
	 */
	void executeInterfazLETEnviarRegiones(Map params);
	
	/**
	 * Proceso que ejecuta Envo Zonas
	 * @param params
	 */
	void executeInterfazLETEnviarZonas(Map params);

	/**
	 * Proceso que ejecuta Envo Territorios
	 * @param params
	 */
	void executeInterfazLETEnviarTerritorios(Map params);

	/**
	 * Proceso que ejecuta Envo Zonas Consultoras
	 * @param params
	 */
	void executeInterfazLETEnviarZonaConsultora(Map params);

	/**
	 * Proceso que ejecuta Envo Territorios Consultora
	 * @param params
	 */
	void executeInterfazLETEnviarTerritorioConsultora(Map params);
	
	/**
	 * Proceso que ejecuta Envo Nuevas
	 * @param params
	 */
	void executeInterfazLETEnviarNuevas(Map params);
	
	/**
	 * Proceso que ejecuta Envo consultora Factura
	 * @param params
	 */
	void executeInterfazLETEnviarConsultoraFactura(Map params);
	
	/**
	 * Proceso que ejecuta Envo Recomendacin
	 * @param params
	 */
	void executeInterfazLETEnviarRecomendacion(Map params);
	
	/**
	 * Proceso que ejecuta Envo Lderes
	 * @param params
	 */
	void executeInterfazLETEnviarLideres(Map params);

	/**
	 * Proceso que ejecuta Envio pagos cabecera
	 * @param params
	 */
	void executeInterfazLETEnviarPagosCabecera(Map params);
	
	/**
	 * Proceso que ejecuta Envio pagos cabecera 1
	 * @param params
	 */
	void executeInterfazLETEnviarPagosCabecera1(Map params);
	
	/**
	 * Proceso que ejecuta Envio pagos detalle
	 * @param params
	 */
	void executeInterfazLETEnviarPagosDetalle(Map params);
	
	/**
	 * Proceso que ejecuta Envio pagos detalle
	 * @param params
	 */
	void executeInterfazLETEnviarPagosDetalle1(Map params);
	
	/**
	 * Proceso que ejecuta Envio tarjetas asociadas cabecera
	 * @param params
	 */
	void executeInterfazLETEnviarTarjetasAsociadasCab(Map params);

	/**
	 * Proceso que ejecuta Envio tarjetas asociadas detalle
	 * @param params
	 */
	void executeInterfazLETEnviarTarjetasAsociadasDeta(Map params);
	/*
	 * Borra todos los registros de la tabla de Cursos LET
	 * 
	 * @param params
	 */
	void deleteInterfazLETRecepcionarCursos(Map params);
	
	/**
	 * Inserta registro en la tabla de Cursos LET
	 * 
	 * @param params
	 */
	void insertInterfazLETRecepcionarCursos(Map params);
	
}
