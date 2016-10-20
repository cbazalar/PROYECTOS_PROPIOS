package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.ControlEnvioWCS;
import biz.belcorp.ssicc.dao.spusicc.ape.model.EstadoOlaWCS;

/**
 * @author Nicols Lpez
 *
 */

public interface MantenimientoAPEControlEnvioInterfacesWCSDAO extends DAO{
	
	/**
	 * @param criteria
	 * @return La lista de Envo de Interfaces WCS
	 */
	public List getControlEnvioInterfacesList(Map criteria);

	/**
	 * @param criteria
	 * @return Objeto Control de Envio
	 */
	public ControlEnvioWCS getControlEnvioWCSObject(Map criteria);
	
	/**
	 * @param criteria
	 * @return Valor Estado de OLA
	 */
	public EstadoOlaWCS getValEstadoOla(Map criteria);
	
	/**
	 * @param criteria
	 * Actualiza el Estado de OLA con '0' y fec_hora_libe en Nulo
	 */
	public void updateEstadoOlasxDia(Map criteria);
	
}
