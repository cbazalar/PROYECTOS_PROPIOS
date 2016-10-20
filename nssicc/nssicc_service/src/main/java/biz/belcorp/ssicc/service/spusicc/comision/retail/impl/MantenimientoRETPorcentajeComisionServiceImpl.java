/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.comision.retail.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.comision.retail.MantenimientoRETPorcentajeComisionDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.comision.retail.MantenimientoRETPorcentajeComisionService;

/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
@Service("spusicc.mantenimientoRETPorcentajeComisionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoRETPorcentajeComisionServiceImpl extends BaseService 
    implements MantenimientoRETPorcentajeComisionService {
	
	@Resource(name="spusicc.mantenimientoRETPorcentajeComisionDAO")
	MantenimientoRETPorcentajeComisionDAO mantenimientoRETPorcentajeComisionDAO;

	/**
     * (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.service.MantenimientoRETPorcentajeComisionService#getPorcentajeComision(java.util.Map)
	 */
	public List getPorcentajeComision(Map map) {
		return mantenimientoRETPorcentajeComisionDAO.getPorcentajeComision(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.service.MantenimientoRETPorcentajeComisionService#updatePorcentajeComision(java.util.Map)
	 */
	public void updatePorcentajeComision(Map map) {
		mantenimientoRETPorcentajeComisionDAO.updatePorcentajeComision(map);
		
	}

}