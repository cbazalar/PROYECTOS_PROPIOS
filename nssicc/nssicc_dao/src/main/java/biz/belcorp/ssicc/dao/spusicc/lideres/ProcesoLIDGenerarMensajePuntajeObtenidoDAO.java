package biz.belcorp.ssicc.dao.spusicc.lideres;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;


public interface ProcesoLIDGenerarMensajePuntajeObtenidoDAO extends DAO {

	/**
	 * @param params  
	 */
	public void executeGenerarMensajePuntajeObtenido(Map params);
	
	/**
	 * Genera Lista de Puntajes obtenidos
	 * @return
	 */
	public List getPuntajeObtenidoList();

	/**
	 * Procedimiento que guarda los mensajes obtenidos por Puntaje
	 * @param params
	 */
	public void saveMensajePuntajeObtenido(Map params);

	/**
	 * Procedimiento que efectua un truncate inicial a los mensajes obtenidos por Puntaje
	 * @param params
	 */
	public void truncateGenerarMensajePuntajeObtenido(Map params);

	
}
