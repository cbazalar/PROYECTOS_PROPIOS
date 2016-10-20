package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPECopiarMapaCDDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPECopiarMapaCDService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPECopiarMapaCDServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez</a>
 * 
 */
@Service("spusicc.mantenimientoAPECopiarMapaCDService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPECopiarMapaCDServiceImpl extends BaseService implements
		MantenimientoAPECopiarMapaCDService {

	@Resource(name="spusicc.mantenimientoAPECopiarMapaCDDAO")
	private MantenimientoAPECopiarMapaCDDAO mantenimientoAPECopiarMapaCDDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPECopiarMapaCDService#executeGenerarMapaCDDetalle(java.util.Map)
	 */
	public void executeGenerarMapaCDDetalle(Map criteria) {
		this.mantenimientoAPECopiarMapaCDDAO.executeGenerarMapaCDDetalle(criteria);
	}


}
