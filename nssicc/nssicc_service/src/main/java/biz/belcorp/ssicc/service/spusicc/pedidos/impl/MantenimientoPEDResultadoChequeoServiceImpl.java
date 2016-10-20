package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDResultadoChequeoDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ResultadoChequeo;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDResultadoChequeoService;

/**
 * @author Jesse J. Rios Franco
 *
 */
@Service("spusicc.pedidos.mantenimientoPEDResultadoChequeoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPEDResultadoChequeoServiceImpl extends BaseService implements MantenimientoPEDResultadoChequeoService{
	
	@Resource(name="spusicc.pedidos.mantenimientoPEDResultadoChequeoDAO")
	private MantenimientoPEDResultadoChequeoDAO mantenimientoPEDResultadoChequeoDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDResultadoChequeoService#getResultadosChequeoList(java.util.Map)
	 */
	public List getResultadosChequeoList(Map map) {
		return mantenimientoPEDResultadoChequeoDAO.getResultadosChequeoList(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDResultadoChequeoService#deleteResultadosChequeo(java.util.Map)
	 */
	public void deleteResultadosChequeo(Map map) {
		mantenimientoPEDResultadoChequeoDAO.deleteResultadosChequeo(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDResultadoChequeoService#getResultadoChequeoObject(java.lang.String)
	 */
	public ResultadoChequeo getResultadoChequeoObject(String id) {
		return mantenimientoPEDResultadoChequeoDAO.getResultadoChequeoObject(id);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDResultadoChequeoService#insertResultadoChequeo(java.util.Map)
	 */
	public void insertResultadoChequeo(Map params) {
		mantenimientoPEDResultadoChequeoDAO.insertResultadoChequeo(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDResultadoChequeoService#updateResultadoChequeo(java.util.Map)
	 */
	public void updateResultadoChequeo(Map params) {
		mantenimientoPEDResultadoChequeoDAO.updateResultadoChequeo(params);
	}
}
