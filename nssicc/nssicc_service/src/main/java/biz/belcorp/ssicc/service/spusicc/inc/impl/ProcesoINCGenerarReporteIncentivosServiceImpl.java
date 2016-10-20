package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCGenerarReporteIncentivosDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCGenerarReporteIncentivosService;

/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
@Service("spusicc.procesoINCGenerarReporteIncentivosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCGenerarReporteIncentivosServiceImpl extends BaseService implements
														ProcesoINCGenerarReporteIncentivosService {
	
	@Resource(name="spusicc.procesoINCGenerarReporteIncentivosDAO")
	private ProcesoINCGenerarReporteIncentivosDAO procesoDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCGenerarReporteIncentivosService#executeGenerarReporteIncentivos(java.util.Map)
	 */
	public void executeGenerarReporteIncentivos(Map map) {
		procesoDAO.executeGenerarReporteIncentivos(map);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCGenerarReporteIncentivosService#getEstadoPremioDespacho(java.util.Map)
	 */
	public Integer getSizeEstadoPremioDespacho(Map map) {
		return procesoDAO.getSizeEstadoPremioDespacho(map);
	}			
}
