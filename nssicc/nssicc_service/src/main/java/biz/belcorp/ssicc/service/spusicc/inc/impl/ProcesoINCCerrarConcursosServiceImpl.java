package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCerrarConcursosDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el proceso que Cerrar Concursos. 
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
@Service("spusicc.procesoINCCerrarConcursosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCCerrarConcursosServiceImpl extends BaseInterfazProcesoAbstractService {

	@Resource(name="spusicc.procesoINCCerrarConcursosDAO")
	private ProcesoINCCerrarConcursosDAO procesoINCCerrarConcursosDAO;
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		procesoINCCerrarConcursosDAO.executeCerrarConcursos(params);
	}
	
}
