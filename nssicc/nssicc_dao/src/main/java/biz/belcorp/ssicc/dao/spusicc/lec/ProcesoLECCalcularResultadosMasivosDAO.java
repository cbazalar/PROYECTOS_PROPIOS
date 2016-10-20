package biz.belcorp.ssicc.dao.spusicc.lec;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

public interface ProcesoLECCalcularResultadosMasivosDAO extends DAO{
	/**
	 * Mtodo que permite Calcular Resultados Masivos
	 * @param params
	 */
	public void executeProcesoLECCalcularResultadosMasivos(Map params);
}