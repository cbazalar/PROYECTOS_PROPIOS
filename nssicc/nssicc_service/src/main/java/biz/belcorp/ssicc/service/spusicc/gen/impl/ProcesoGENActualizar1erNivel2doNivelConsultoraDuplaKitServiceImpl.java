/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.gen.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.gen.ProcesoGENActualizarNivelConsultoraDuplaKitDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author itocto
 *
 */
@Service("spusicc.procesoGENActualizar1erNivel2doNivelConsultoraDuplaKitService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoGENActualizar1erNivel2doNivelConsultoraDuplaKitServiceImpl
		extends BaseInterfazProcesoAbstractService {

	@Resource(name = "spusicc.procesoGENActualizarNivelConsultoraDuplaKitDAO")
	private ProcesoGENActualizarNivelConsultoraDuplaKitDAO procesoGENActualizarNivelConsultoraDuplaKitDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) throws InterfazException,
			Exception {
		if(log.isDebugEnabled())
			log.debug(params);
		
		procesoGENActualizarNivelConsultoraDuplaKitDAO.executeProcesoGENActualizar1erNivel2doNivelConsultoraDuplaKit(params);
	}

}
