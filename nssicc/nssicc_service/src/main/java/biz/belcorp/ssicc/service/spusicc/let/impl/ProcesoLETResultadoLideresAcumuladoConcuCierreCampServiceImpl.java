package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETResultadoLideresAcumuladoConcuCierreCampDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.let.ProcesoLETResultadoLideresAcumuladoConcuCierreCampService;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.procesoLETResultadoLideresAcumuladoConcuCierreCampService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLETResultadoLideresAcumuladoConcuCierreCampServiceImpl extends BaseService implements ProcesoLETResultadoLideresAcumuladoConcuCierreCampService{
	
	@Resource(name="spusicc.procesoLETResultadoLideresAcumuladoConcuCierreCampDAO")
	private ProcesoLETResultadoLideresAcumuladoConcuCierreCampDAO procesoLETResultadoLideresAcumuladoConcuCierreCampDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETResultadoLideresAcumuladoConcuCierreCampService#executeProcesoLETResultadoLideresAcumuladoConcuCierreCamp(java.util.Map)
	 */
	public void executeProcesoLETResultadoLideresAcumuladoConcuCierreCamp(Map params) {
		procesoLETResultadoLideresAcumuladoConcuCierreCampDAO.executeProcesoLETResultadoLideresAcumuladoConcuCierreCamp(params);
	}
}