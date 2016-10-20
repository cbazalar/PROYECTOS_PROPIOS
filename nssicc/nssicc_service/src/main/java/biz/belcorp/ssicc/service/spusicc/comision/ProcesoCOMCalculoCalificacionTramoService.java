package biz.belcorp.ssicc.service.spusicc.comision;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza </a>
 *
 */
public interface ProcesoCOMCalculoCalificacionTramoService extends Service {

	public List getTiposComisionistas(String codigoPais);
	
	public List getTramos(String codigoPais);
	
	public void executeCalculoCalificacionTramo(Map params);
	
	public List getTipoCalculoList(String codigoPais);
	
	/**
	 * Metodo que devuelve las campanas del tramo
	 * @param criteria
	 * @return
	 */
	public List getCampanasRango(Map criteria);
	
	public List getComisionByTipo(Map criteria);
}
