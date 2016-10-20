package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

public interface ProcesoCCCenerarInformacionBuroCrediticioDAO extends DAO{

	/**
	 * Ejecuta el proceso de generacion de informacion buro crediticia
	 * @param params
	 */
	void executeGenerarInformacionBuroCrediticia(Map params);

	
	
}
