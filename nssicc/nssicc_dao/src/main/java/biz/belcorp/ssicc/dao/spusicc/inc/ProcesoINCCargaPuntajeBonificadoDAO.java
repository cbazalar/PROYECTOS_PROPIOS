package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.inc.model.CargaPuntajeConsultora;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface ProcesoINCCargaPuntajeBonificadoDAO extends DAO {

	
	/**
	 * Ejecura el proceso de insercion de puntajes en
	 * la cuenta corriente sobre los registros validos
	 * de los puntajes d ela consultora
	 * @param params
	 */
	void executeInsercionCuentaCorrientePuntaje(Map params);

	/**
	 * Inserta el puntaje valido o no dela consultora
	 * @param cargaPuntajeConsultora
	 */
	void insertCargaPuntajeConsultora(CargaPuntajeConsultora cargaPuntajeConsultora);

	/**
	 * Actualiza el puntaje valido o no dela consultora
	 * @param cargaPuntajeConsultora
	 */
	void updateIndAplicacionCargaPuntajeConsultora(CargaPuntajeConsultora cargaPuntajeConsultora);

	/**
	 * Retorna numero lote
	 * @return
	 */
	String getNumeroLote();

}
