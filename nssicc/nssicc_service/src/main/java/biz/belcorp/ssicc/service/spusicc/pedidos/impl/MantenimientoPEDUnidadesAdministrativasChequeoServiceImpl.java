package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDUnidadesAdministrativasChequeoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDUnidadesAdministrativasChequeoService;

@Service("spusicc.pedidos.mantenimientoPEDUnidadesAdministrativasChequeoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPEDUnidadesAdministrativasChequeoServiceImpl extends BaseService implements MantenimientoPEDUnidadesAdministrativasChequeoService{
	
	@Resource(name="spusicc.pedidos.mantenimientoPEDUnidadesAdministrativasChequeoDAO")
	private MantenimientoPEDUnidadesAdministrativasChequeoDAO mantenimientoPEDUnidadesAdministrativasChequeoDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDUnidadesAdministrativasChequeoService#getUnidadesAdministrativasChequeoList(java.util.Map)
	 */
	public List getUnidadesAdministrativasChequeoList(Map map) {
		return mantenimientoPEDUnidadesAdministrativasChequeoDAO.getUnidadesAdministrativasChequeoList(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDUnidadesAdministrativasChequeoService#insertUnidadesAdministrativasChequeo(java.util.Map)
	 */
	public void insertUnidadesAdministrativasChequeo(Map map) {
		mantenimientoPEDUnidadesAdministrativasChequeoDAO.insertUnidadesAdministrativasChequeo(map);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDUnidadesAdministrativasChequeoService#deleteUnidadesAdministrativasChequeo(java.util.Map, java.lang.String[])
	 */
	public void deleteUnidadesAdministrativasChequeo(Map map,String[] ids) {
		
		for(int i=0;i<ids.length;i++){
			String id = ids[i];
			map.put("oidUnidAdmiCheq", id);
			
			mantenimientoPEDUnidadesAdministrativasChequeoDAO.deleteUnidadesAdministrativasChequeo(map);
		}
	}
	
}