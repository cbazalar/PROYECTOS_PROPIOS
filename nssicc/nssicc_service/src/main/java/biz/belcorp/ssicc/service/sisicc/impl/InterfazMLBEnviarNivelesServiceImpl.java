package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazMLBDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Service para el envio de Niveles de Concursos de la Interfaz My LBEL.
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("sisicc.InterfazMLBEnviarNivelesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazMLBEnviarNivelesServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazMLBDAO")
	private InterfazMLBDAO interfazMLBDAO;

	protected void executeStoreProcedure(Map params) {
		interfazMLBDAO.executeInterfazMLBEnviarNiveles(params);
	}

	
}
