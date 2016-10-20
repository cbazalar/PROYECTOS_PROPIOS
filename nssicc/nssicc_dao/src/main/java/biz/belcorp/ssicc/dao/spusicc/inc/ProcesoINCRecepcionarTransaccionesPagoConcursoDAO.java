package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoINCRecepcionarTransaccionesPagoConcursoDAO extends DAO {

	/**
	 * Retorna la lista de Pago Concurso
	 * @return
	 */
	List getListPagoConcurso();
	
	/**
	 * Retorna la lista de Motivo Pago
	 * @return
	 */
	List getListMotivoPago();
	
	/**
	 * Inserta Ambito Geografico para la Migracion de Puntos Consultora
	 * 
	 * @param params
	 */
	void insertTransaccionPagoConcurso(Map params);
	
	/**
	 * Ejecura el proceso de actualizacion de Migracion de Puntos Consultora
	 * 
	 * @param params
	 */
	void executeActualizarTransaccionesPagoConcurso(Map params);
	
	/**
	 * Retorna numero carga
	 * @return
	 */
	String getNumeroCarga();
	
}
