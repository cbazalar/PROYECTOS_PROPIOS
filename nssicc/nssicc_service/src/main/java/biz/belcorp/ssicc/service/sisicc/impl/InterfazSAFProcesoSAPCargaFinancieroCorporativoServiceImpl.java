package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazSAFDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service de la Interfaz Envio SAP-FI Corporativo
 * 
 * @author <a href="mailto:ghuertasa@sigcomt.com">Gonzalo Huertas</a>
 */
@Service("sisicc.interfazSAFProcesoSAPCargaFinancieroCorporativoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSAFProcesoSAPCargaFinancieroCorporativoServiceImpl extends BaseInterfazProcesoAbstractService {
	
	@Resource(name="sisicc.interfazSAFDAO")
	protected InterfazSAFDAO interfazSAFDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazSAFDAO.executeInterfazSAFProcesoSAPCargaFinancieroCorporativo(params);
	}
}