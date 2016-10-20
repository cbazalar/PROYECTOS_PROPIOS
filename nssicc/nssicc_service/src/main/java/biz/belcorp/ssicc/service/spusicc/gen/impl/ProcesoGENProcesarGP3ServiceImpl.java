package biz.belcorp.ssicc.service.spusicc.gen.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spncd.MantenimientoCUPProgDsctoDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author Jose Luis Rodriguez
 *
 */
@Service("spusicc.procesoGENProcesarGP3Service")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoGENProcesarGP3ServiceImpl extends BaseInterfazProcesoAbstractService{

	@Resource(name = "spncd.mantenimientoCUPProgDsctoDAO")
	private MantenimientoCUPProgDsctoDAO mantenimientoCUPProgDsctoDAO;

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) 
	throws InterfazException,Exception {
		mantenimientoCUPProgDsctoDAO.executeEntregaRxP(params);
	}

}