package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazAVIDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Service para el envio de saldos de de Consultoras de la Interfaz AVI.
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("sisicc.InterfazAVIEnviarSaldosConsultoraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazAVIEnviarSaldosConsultoraServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazAVIDAO")
	private InterfazAVIDAO interfazAVIDAO;

	protected void executeStoreProcedure(Map params) {
		interfazAVIDAO.executeInterfazAVISaldosConsultora(params);
	}

	public void setInterfazAVIDAO(InterfazAVIDAO interfazAVIDAO) {
		this.interfazAVIDAO = interfazAVIDAO;
	}
}


