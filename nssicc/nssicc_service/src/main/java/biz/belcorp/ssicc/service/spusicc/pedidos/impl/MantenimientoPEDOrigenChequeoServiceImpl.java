package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDOrigenChequeoDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.OrigenChequeo;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDOrigenChequeoService;

@Service("spusicc.pedidos.mantenimientoPEDOrigenChequeoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPEDOrigenChequeoServiceImpl extends BaseService implements MantenimientoPEDOrigenChequeoService{
	
	@Resource(name="spusicc.pedidos.mantenimientoPEDOrigenChequeoDAO")
	private MantenimientoPEDOrigenChequeoDAO mantenimientoPEDOrigenChequeoDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDOrigenPedidosService#getOrigenPedidoList()
	 */
	public List<OrigenChequeo> getOrigenChequeoList() {
		return mantenimientoPEDOrigenChequeoDAO.getOrigenChequeoList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDOrigenPedidosService#updateOrigenChequeo(java.util.Map)
	 */
	public void updateOrigenChequeo(List origenChequeoList,Map criteria, List sencuenciaEvalList) {
		
		for(int i = 0 ; i<origenChequeoList.size(); i++){
			
			OrigenChequeo origenPedido = (OrigenChequeo)origenChequeoList.get(i);
			
			criteria.put("codigoOrigenChequeo", origenPedido.getCodigoOrigenChequeo());
			criteria.put("secuenciaEval", Integer.valueOf((String)sencuenciaEvalList.get(i)));
			
			mantenimientoPEDOrigenChequeoDAO.updateOrigenChequeo(criteria);
			
		}
	}
}