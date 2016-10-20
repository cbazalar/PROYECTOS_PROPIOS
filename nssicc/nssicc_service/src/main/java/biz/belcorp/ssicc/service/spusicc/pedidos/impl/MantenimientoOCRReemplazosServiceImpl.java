package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoOCRReemplazosDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRReemplazosService;

/**
 * @author peextdoliva
 */
@Service("spusicc.pedidos.mantenimientoOCRReemplazosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoOCRReemplazosServiceImpl extends BaseService implements MantenimientoOCRReemplazosService {

	@Resource(name="spusicc.pedidos.mantenimientoOCRReemplazosDAO")
	MantenimientoOCRReemplazosDAO mantenimientoOCRReemplazosDAO;
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRReemplazosService#getReemplazosByPeriodo(java.util.Map)
	 */
	public List getReemplazosByPeriodo(Map criteria){
		return mantenimientoOCRReemplazosDAO.getReemplazosByPeriodo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRReemplazosService#deleteReemplazos(java.util.Map)
	 */
	public void deleteReemplazos(Map criteria){
		mantenimientoOCRReemplazosDAO.deleteReemplazos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRReemplazosService#insertOCRReemplazos(java.util.Map)
	 */
	public void insertOCRReemplazos(Map criteria){
		mantenimientoOCRReemplazosDAO.insertOCRReemplazos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRReemplazosService#updateReemplazos(java.util.Map)
	 */
	public void updateReemplazos(Map criteria) {
		mantenimientoOCRReemplazosDAO.updateReemplazos(criteria);
		
	}
}
