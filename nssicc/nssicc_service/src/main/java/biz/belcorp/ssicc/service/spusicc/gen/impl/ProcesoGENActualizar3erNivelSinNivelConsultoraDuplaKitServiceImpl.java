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
@Service("spusicc.procesoGENActualizar3erNivelSinNivelConsultoraDuplaKitService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoGENActualizar3erNivelSinNivelConsultoraDuplaKitServiceImpl extends BaseInterfazProcesoAbstractService{

	@Resource(name = "spusicc.procesoGENActualizarNivelConsultoraDuplaKitDAO")
	private ProcesoGENActualizarNivelConsultoraDuplaKitDAO procesoGENActualizarNivelConsultoraDuplaKitDAO;
	
	
	protected void executeStoreProcedure(Map params) throws InterfazException,
			Exception {
		if(log.isDebugEnabled())
			log.debug(params);
		
		procesoGENActualizarNivelConsultoraDuplaKitDAO.executeProcesoGENActualizar3erNivelSinNivelConsultoraDuplaKit(params);
	}

}
