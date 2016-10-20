package biz.belcorp.ssicc.dao.scsicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
/**
 * @author <a href="">Jose Luis Rodriguez</a>
 *
 */
public interface ProcesoSICGenerarPedidosDigitadosZonaDAO extends DAO {
	
	/**
	 * Obtiene el correo de un gerente de regin
	 * 
	 * @param criteria
	 * @return
	 */
	public String getCorreoGerenteRegion(Map criteria);

	
	/**
	 * Obtiene el correo de un gerente de z
	 * 
	 * @param criteria
	 * @return
	 */
	public String getCorreoGerenteZona(Map criteria);


	/**
	 * Retorna correo gerente regional 
	 * @param map
	 * @return
	 */
	public String getCorreoGerenteRegionByCodigoZona(Map map);
}
