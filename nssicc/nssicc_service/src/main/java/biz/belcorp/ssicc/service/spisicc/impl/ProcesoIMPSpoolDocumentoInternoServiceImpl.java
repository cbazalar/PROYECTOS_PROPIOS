package biz.belcorp.ssicc.service.spisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spisicc.ProcesoIMPSpoolDocumentoInternoDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author Jose Luis Rodriguez
 *
 */
@Service("spisicc.procesoIMPSpoolDocumentoInternoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoIMPSpoolDocumentoInternoServiceImpl extends BaseInterfazProcesoAbstractService{

	@Resource(name="spisicc.procesoIMPSpoolDocumentoInternoDAO")
	private ProcesoIMPSpoolDocumentoInternoDAO procesoIMPSpoolDocumentoInternoDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		procesoIMPSpoolDocumentoInternoDAO.executeGeneraDocumentoInterno(params);
	}
}