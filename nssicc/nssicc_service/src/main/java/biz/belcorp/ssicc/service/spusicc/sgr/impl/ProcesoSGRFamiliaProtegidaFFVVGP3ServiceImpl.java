package biz.belcorp.ssicc.service.spusicc.sgr.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.sgr.ProcesoSGRGenericoDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

@Service("sisicc.procesoSGRFamiliaProtegidaFFVVGP3Service")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSGRFamiliaProtegidaFFVVGP3ServiceImpl extends BaseInterfazProcesoAbstractService {

	@Resource(name="spusicc.procesoSGRGenericoDAO")
	private ProcesoSGRGenericoDAO procesoSGRGenericoDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) throws InterfazException, Exception {
		procesoSGRGenericoDAO.executeConsultarPolizaVigenteFFVVGP3(params);
		
		if(log.isDebugEnabled())
			log.debug(params);
	}
	
	
}
