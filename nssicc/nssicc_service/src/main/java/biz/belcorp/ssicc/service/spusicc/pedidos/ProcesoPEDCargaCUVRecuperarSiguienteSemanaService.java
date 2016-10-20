package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

public interface ProcesoPEDCargaCUVRecuperarSiguienteSemanaService extends Service {
	
	/**
	 * Inserta Carga CUV Recuperar Siguiente Semana
	 * @param params
	 */
	public void insertCargaCUVRecuperarSiguienteSemana(Map params);

	/**
	 * Realiza validacion de Carga CUV Recuperar Siguiente Semana
	 * @param params
	 */
	public Map executeValidarCargaCUVRecuperarSiguienteSemana(Map params) throws Exception;
	
	/**
	 * Realiza validacion de Carga CUV Recuperar Siguiente Semana en forma unitaria
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	public Map executeValidarCargaCUVUnitarioRecuperarSiguienteSemana(Map criteria) throws Exception;
	
	
	/**
	 * Actualiza Carga CUV Recuperar Siguiente Semana
	 * @param params
	 */
	public void executeActualizarCargaCUVRecuperarSiguienteSemana(Map params);

}
