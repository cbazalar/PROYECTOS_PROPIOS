package biz.belcorp.ssicc.service.spusicc.mav.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.mav.ProcesoMAVCargaMasivaDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

@Service("sisicc.procesoMAVActualizarGerentesDirectorioService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAVActualizarGerentesDirectorioServiceImpl extends BaseInterfazProcesoAbstractService {

	@Resource(name="spusicc.procesoMAVCargaMasivaDAO")
	private ProcesoMAVCargaMasivaDAO procesoMAVCargaMasivaDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) throws InterfazException, Exception {
		procesoMAVCargaMasivaDAO.executeActualizarGerentesDirectorio(params);
	}	
}
