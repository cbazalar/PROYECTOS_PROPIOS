package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCAplicarRequisitosPremiacionZonaDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author Sergio Apaza
 *
 */
@Service("spusicc.procesoINCAplicarRequisitosPremiacionZonaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCAplicarRequisitosPremiacionZonaServiceImpl extends BaseInterfazProcesoAbstractService{

	@Resource(name="spusicc.procesoINCAplicarRequisitosPremiacionZonaDAO")
	private ProcesoINCAplicarRequisitosPremiacionZonaDAO procesoINCAplicarRequisitosPremiacionZonaDAO;
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		procesoINCAplicarRequisitosPremiacionZonaDAO.executeAplicarRequisitosPremiacionZona(params);
	}
}