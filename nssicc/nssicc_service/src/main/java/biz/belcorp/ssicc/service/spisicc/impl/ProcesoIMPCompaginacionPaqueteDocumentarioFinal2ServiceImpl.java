package biz.belcorp.ssicc.service.spisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spisicc.ProcesoImpresionDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el proceso de actulaizacion secuenciacion
 * 
 * @author <a href="">Sergio Buchelli</a>
 */
@Service("sisicc.procesoIMPCompaginacionPaqueteDocumentarioFinal2Service")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoIMPCompaginacionPaqueteDocumentarioFinal2ServiceImpl extends BaseInterfazProcesoAbstractService {

	@Resource(name="spisicc.procesoImpresionDAO")
	private ProcesoImpresionDAO procesoImpresionDAO;
		
		/* (non-Javadoc)
		 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
		 */
	protected void executeStoreProcedure(Map params) {			
		procesoImpresionDAO.executeCompaginacionPaqueteDocumentarioFinal2(params);
	}
	
}
