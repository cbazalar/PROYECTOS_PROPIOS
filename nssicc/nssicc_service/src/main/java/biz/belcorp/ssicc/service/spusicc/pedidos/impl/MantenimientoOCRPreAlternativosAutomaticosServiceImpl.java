package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoOCRPreAlternativosAutomaticosDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPreAlternativosAutomaticosService;

/**
 * @author peextcroman
 */
@Service("spusicc.pedidos.mantenimientoOCRPreAlternativosAutomaticosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoOCRPreAlternativosAutomaticosServiceImpl extends BaseService implements MantenimientoOCRPreAlternativosAutomaticosService {

	@Resource(name="spusicc.pedidos.mantenimientoOCRPreAlternativosAutomaticosDAO")
	MantenimientoOCRPreAlternativosAutomaticosDAO mantenimientoOCRPreAlternativosAutomaticosDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPreAlternativosAutomaticosService#getListaAlternativosAutomaticos(java.util.Map)
	 */
	public List getListaAlternativosAutomaticos(Map criteria){
		return mantenimientoOCRPreAlternativosAutomaticosDAO.getListaAlternativosAutomaticos(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPreAlternativosAutomaticosService#deleteAlternativosAutomaticos(java.util.Map)
	 */
	public void deleteAlternativosAutomaticos(Map criteria){
		mantenimientoOCRPreAlternativosAutomaticosDAO.deleteAlternativosAutomaticos(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPreAlternativosAutomaticosService#insertPreAlternativosAutomaticos(java.util.Map)
	 */
	public void insertPreAlternativosAutomaticos(Map criteria){
		mantenimientoOCRPreAlternativosAutomaticosDAO.insertPreAlternativosAutomaticos(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPreAlternativosAutomaticosService#executeProcesoCrearOfertas(java.util.Map)
	 */
	public void executeProcesoCrearOfertas(Map criteria){
		mantenimientoOCRPreAlternativosAutomaticosDAO.executeProcesoCrearOfertas(criteria);
	}
	
}
