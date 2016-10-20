package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.OrigenChequeo;

public interface MantenimientoPEDOrigenChequeoDAO extends DAO{

	/**
	 * retorna todo los orignes de chequeo de la tabla
	 * @return
	 */
	public List<OrigenChequeo> getOrigenChequeoList();

	/**
	 * actualiza un origen de chequeo
	 * @param criteria
	 */
	public void updateOrigenChequeo(Map<String, Object> criteria);

}
