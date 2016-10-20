package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

public interface ProcesoPEDCargaCUVRecuperarSiguienteSemanaDAO extends DAO {
	
	/**
	 * Inserta Carga CUV Recuperar Siguiente Semana
	 * @param params
	 */
	public void insertCargaCUVRecuperarSiguienteSemana(Map params);

	/**
	 * Devuelve registro con Carga CUV Recuperar Siguiente Semana
	 * @param params
	 */
	public Map getDatosProductoCUVRecuperarSemana(Map params);
	


}
