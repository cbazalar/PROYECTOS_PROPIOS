package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Service para el envio de Puntajes de Consultoras de la Interfaz EVI.
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jos A. Cairampoma</a>
 */
@Service("sisicc.interfazEVIEnviarResultadosFacturacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazEVIEnviarResultadosFacturacionServiceImpl extends	BaseInterfazSalidaStoredProcedureAbstractService {


	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO.executeInterfazEVIEnviarResultadosFacturacion(params);
	}
}
