package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDTipoChequeoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDTipoChequeoService;

@Service("spusicc.pedidos.mantenimientoPEDTipoChequeoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPEDTipoChequeoServiceImpl extends BaseService implements MantenimientoPEDTipoChequeoService{
	
	@Resource(name="spusicc.pedidos.mantenimientoPEDTipoChequeoDAO")
	private MantenimientoPEDTipoChequeoDAO mantenimientoPEDTipoChequeoDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDTipoChequeoService#getTipoChequeoAll()
	 */
	public List getTipoChequeoAll() {
		return mantenimientoPEDTipoChequeoDAO.getTipoChequeoAll();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDTipoChequeoService#updateTipoChequeo(java.util.Map)
	 */
	public void updateTipoChequeo(List parametrosList) {
		
		for(int i=0;i<parametrosList.size();i++){
			
			Map criteria = (Map)parametrosList.get(i);
			
			mantenimientoPEDTipoChequeoDAO.updateTipoChequeo(criteria);
		}
	}
}