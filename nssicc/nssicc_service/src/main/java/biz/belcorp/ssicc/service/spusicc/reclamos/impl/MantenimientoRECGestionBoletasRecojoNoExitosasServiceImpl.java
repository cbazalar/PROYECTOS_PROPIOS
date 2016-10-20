/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.reclamos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECGestionBoletasRecojoNoExitosasDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECGestionBoletasRecojoNoExitosasService;

/**
 * @author peextjcairampoma
 *
 */
@Service("spusicc.mantenimientoRECGestionBoletasRecojoNoExitosasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoRECGestionBoletasRecojoNoExitosasServiceImpl extends BaseService implements MantenimientoRECGestionBoletasRecojoNoExitosasService {
	
	@Resource(name="spusicc.mantenimientoRECGestionBoletasRecojoNoExitosasDAO")
	MantenimientoRECGestionBoletasRecojoNoExitosasDAO mantenimientoRECGestionBoletasRecojoNoExitosasDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECGestionBoletasRecojoNoExitosasService#getBoletasRecojoNOExitosasList(java.util.Map)
	 */
	public List getBoletasRecojoNOExitosasList(Map params) {
		return mantenimientoRECGestionBoletasRecojoNoExitosasDAO.getBoletasRecojoNOExitosasList(params);
	}


	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECGestionBoletasRecojoNoExitosasService#procesarBoletaRecojoNoExitosa(java.util.Map)
	 */
	public void executeProcesoBoletaRecojoNoExitosa(Map params) {
		
		mantenimientoRECGestionBoletasRecojoNoExitosasDAO.executeProcesoBoletaRecojoNoExitosa(params);
	}


	
	
}
