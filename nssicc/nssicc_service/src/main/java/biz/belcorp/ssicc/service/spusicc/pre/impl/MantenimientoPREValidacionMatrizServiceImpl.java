package biz.belcorp.ssicc.service.spusicc.pre.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPREValidacionMatrizDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pre.MantenimientoPREValidacionMatrizService;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.mantenimientoPREValidacionMatrizService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPREValidacionMatrizServiceImpl extends BaseService implements
	MantenimientoPREValidacionMatrizService {

	@Resource(name="spusicc.mantenimientoPREValidacionMatrizDAO")
	MantenimientoPREValidacionMatrizDAO mantenimientoPREValidacionMatrizDAO;
	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pre.service.MantenimientoPREValidacionMatrizService#getListValidacionMatriz(java.util.Map)
	 */
	public List getListValidacionMatriz(Map criteria) {
		return mantenimientoPREValidacionMatrizDAO.getListValidacionMatriz(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pre.service.MantenimientoPREValidacionMatrizService#updateValidacionMatriz(java.util.Map)
	 */
	public void updateValidacionMatriz(Map params) {
		mantenimientoPREValidacionMatrizDAO.updateValidacionMatriz(params);
	}

}
