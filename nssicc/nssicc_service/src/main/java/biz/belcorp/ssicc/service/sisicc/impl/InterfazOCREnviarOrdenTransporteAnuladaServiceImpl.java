package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazOCRDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Service de la Interfaz de Envio de Ordenes de transporte anuladas
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */
@Service("sisicc.interfazOCREnviarOrdenTransporteAnuladaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazOCREnviarOrdenTransporteAnuladaServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {
	
	@Resource(name="sisicc.interfazOCRDAO")
	protected InterfazOCRDAO interfazOCRDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazOCRDAO.executeInterfazOCREnviarOrdenTransporteAnulada(params);
	}
}