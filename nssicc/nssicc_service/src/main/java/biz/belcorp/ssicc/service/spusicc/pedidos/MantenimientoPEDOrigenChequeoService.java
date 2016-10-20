package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.pedidos.model.OrigenChequeo;
import biz.belcorp.ssicc.service.framework.Service;

public interface MantenimientoPEDOrigenChequeoService extends Service{

	/**
	 * retorna todo los orignes de chequeo de la tabla
	 * @return
	 */
	public List<OrigenChequeo> getOrigenChequeoList();

	/**
	 * actualiza un origen de chequeo
	 * @param origenChequeoList 
	 * @param criteria
	 * @param sencuenciaEvalList 
	 */
	public void updateOrigenChequeo(List origenChequeoList, Map<String, Object> criteria, List sencuenciaEvalList);

}
