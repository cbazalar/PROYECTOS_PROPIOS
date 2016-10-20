package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazCOBDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Service de la Interfaz Enviar Recuperacin Cobranza FFVV Datamart
 * 
 * @author <a href="#">Yahir Rivas Luna</a>
 */

@Service("sisicc.interfazCOBEnviarRecuCobranzaFFVVDatamartService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazCOBEnviarRecuCobranzaFFVVDatamartServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {
	
	@Resource(name="sisicc.interfazCOBDAO")
	protected InterfazCOBDAO interfazCOBDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazCOBDAO.executeInterfazCOBEnviarRecuCobranzaFFVVDatamart(params);
	}
}