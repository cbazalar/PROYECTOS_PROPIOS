package biz.belcorp.ssicc.service.spusicc.flexipago.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazFLXDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

@Service("sisicc.procesoFLXEnvioInformacionProcesosComercialesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoFLXEnvioInformacionProcesosComercialesServiceImpl extends BaseInterfazProcesoAbstractService {

	@Resource(name="sisicc.interfazFLXDAO")
	private InterfazFLXDAO interfazFLXDAO;




	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) throws InterfazException, Exception {
		interfazFLXDAO.executeEnvioInformacionProcesosComerciales(params);
	}
	
}
