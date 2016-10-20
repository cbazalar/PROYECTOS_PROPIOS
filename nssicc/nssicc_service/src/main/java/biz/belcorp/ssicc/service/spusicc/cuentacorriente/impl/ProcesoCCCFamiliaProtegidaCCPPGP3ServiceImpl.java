package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazCCCDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

@Service("sisicc.procesoCCCFamiliaProtegidaCCPPGP3Service")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCFamiliaProtegidaCCPPGP3ServiceImpl extends BaseInterfazProcesoAbstractService {

	@Resource(name = "sisicc.interfazCCCDAO")
	private InterfazCCCDAO interfazCCCDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) throws InterfazException, Exception {
		interfazCCCDAO.executeGenerarCargoFamiliaSeguraCCPPGP3(params);
		
		if(log.isDebugEnabled()){
			log.debug(params);
		}
	}
	
	
}
