package biz.belcorp.ssicc.dao.spusicc.app;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jose Luis Rodriguez
 *
 */
public interface ProcesoAPPCargarSecuenciaZonaDAO extends DAO {

	/**
	 * Metodo que inserta Secuencia Zonas
	 * @param criteria
	 */
	public void insertaSecuenciaZona(Map criteria);

	/**
	 * Limpia la tabla de Secuencia Zonas
	 */
	public void deleteTablaSecuenciaZona();
	
	/**
	 * Ejecuta la carga
	 * @param criteria
	 */
	public void executeCargaSecuenciaZona(Map criteria);
}