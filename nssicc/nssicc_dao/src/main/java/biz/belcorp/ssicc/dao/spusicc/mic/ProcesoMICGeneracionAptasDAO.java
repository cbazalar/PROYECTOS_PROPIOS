package biz.belcorp.ssicc.dao.spusicc.mic;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface ProcesoMICGeneracionAptasDAO extends DAO {
	
	/**
	 * Realiza la genreacion de aptas para microseguros
	 * @param params
	 */
	void executeGeneracionAptasMicroSeguros(Map map);

	/**
	 * Devuleve los parametos de microseguros
	 * @return
	 */
	Map getParametrosMicroSeguro();

	/**
	 * Valida si se debe enviar el archivo de pago de microseguros segun
	 * tipo d eoperacion 01:IPM 02:ASEGURADORA
	 * Retorna 1: si se debe enviar , 0 si no hay envio
	 * @return
	 */
	Integer validaEnvioMicroseguros(String tipo);

	/**
	 * Actualiza el indicaro de cambio de scheduler
	 * @param micPrams
	 */
	void updateParametros(Map micPrams);


}
