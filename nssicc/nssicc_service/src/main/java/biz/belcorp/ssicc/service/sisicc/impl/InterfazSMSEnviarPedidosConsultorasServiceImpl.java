package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazSMSDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Service de la Interfaz de Envio de lod Pedidos de las Consultoras del programa SMS
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */

@Service("sisicc.interfazSMSEnviarPedidosConsultorasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSMSEnviarPedidosConsultorasServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazSMSDAO")
	protected InterfazSMSDAO interfazSMSDAO;
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazSMSDAO.executeInterfazSMSEnviarPedidosConsultoras(params);
	}
}