package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDClasificacionesChequeoDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ClasificacionesChequeo;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDClasificacionesChequeoService;

@Service("spusicc.pedidos.mantenimientoPEDClasificacionesChequeoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPEDClasificacionesChequeoServiceImpl extends BaseService implements MantenimientoPEDClasificacionesChequeoService{
	
	@Resource(name="spusicc.pedidos.mantenimientoPEDClasificacionesChequeoDAO")
	private MantenimientoPEDClasificacionesChequeoDAO mantenimientoPEDClasificacionesChequeoDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDClasificacionesChequeoService#getTipoCliente()
	 */
	public List getTipoCliente() {
		return mantenimientoPEDClasificacionesChequeoDAO.getTipoCliente();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDClasificacionesChequeoService#getClasificacionChequeo(java.util.Map)
	 */
	public List getClasificacionChequeo(Map map) {
		return mantenimientoPEDClasificacionesChequeoDAO.getClasificacionChequeo(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDClasificacionesChequeoService#deleteClasificacionChequeo(java.util.Map)
	 */
	public void deleteClasificacionChequeo(Map map,String[] items) {
		
		for(int i = 0; i < items.length; i++){
			
			String id = items[i];
			
			map.put("codTipoChequeo", StringUtils.split(id, "|")[0]);
			map.put("oidTipoCliente", StringUtils.split(id, "|")[1]);
			map.put("oidSubTipoCliente", StringUtils.split(id, "|")[2]);
			map.put("oidTipoClasificacion", StringUtils.split(id, "|")[3]);
			map.put("oidClasificacion", StringUtils.split(id, "|")[4]);
			
			mantenimientoPEDClasificacionesChequeoDAO.deleteClasificacionChequeo(map);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDClasificacionesChequeoService#insertClasificacionChequeo(java.util.Map)
	 */
	public void insertClasificacionChequeo(Map params) {
		mantenimientoPEDClasificacionesChequeoDAO.insertClasificacionChequeo(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDClasificacionesChequeoService#getClasificacionChequeoObject(java.util.Map)
	 */
	public ClasificacionesChequeo getClasificacionChequeoObject(Map map) {
		return mantenimientoPEDClasificacionesChequeoDAO.getClasificacionChequeoObject(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDClasificacionesChequeoService#updateClasificacionChequeo(java.util.Map)
	 */
	public void updateClasificacionChequeo(Map params) {
		mantenimientoPEDClasificacionesChequeoDAO.updateClasificacionChequeo(params);
	}
}