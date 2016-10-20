package biz.belcorp.ssicc.dao.spusicc.comision;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza </a>
 *
 */
public interface ProcesoCOMCalculoCalificacionTramoDAO extends DAO {
	
	public List getTiposComisionistas(String codigoPais);
	
	public List getTramos(String codigoPais);

	public void executeCalculoCalificacionTramo(Map criteria);
	
	public List getTipoCalculoList(String codigoPais);
	
	/**
	 * Metodo que devuelve las campanas de un tramo
	 * @param criteria
	 * @return
	 */
	public List getCampanasRango(Map criteria);
	
	public  List getComisionByTipo(Map criteria);
}
