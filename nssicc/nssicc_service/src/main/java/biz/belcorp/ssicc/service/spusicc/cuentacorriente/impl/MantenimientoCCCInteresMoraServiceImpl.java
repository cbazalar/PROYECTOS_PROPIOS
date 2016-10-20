/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

  
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCInteresMoraDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCInteresMoraService;

/**
 * @author pejflorencio
 *
 */
@Service("spusicc.mantenimientoCCCInteresMoraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCCCInteresMoraServiceImpl extends BaseService implements MantenimientoCCCInteresMoraService {
	
	@Resource(name = "spusicc.mantenimientoCCCInteresMoraDAO")
	MantenimientoCCCInteresMoraDAO mantenimientoCCCInteresMoraDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCInteresMoraService#getInteresMontoMora()
	 */
	public Map getInteresMontoMora(){
		
		return mantenimientoCCCInteresMoraDAO.getInteresMontoMora();
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCInteresMoraService#insertInteresMontoMora(java.util.Map)
	 */
	public void insertInteresMontoMora(Map criteria){
		mantenimientoCCCInteresMoraDAO.insertInteresMontoMora(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCInteresMoraService#updateInteresMontoMora(java.util.Map)
	 */
	public void updateInteresMontoMora(Map criteria){
		mantenimientoCCCInteresMoraDAO.updateInteresMontoMora(criteria);
	}
}
