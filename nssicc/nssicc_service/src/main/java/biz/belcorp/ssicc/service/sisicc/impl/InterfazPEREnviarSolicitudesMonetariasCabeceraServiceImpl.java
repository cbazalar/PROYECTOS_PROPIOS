package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Service de la Interfaz de Envio Solicitudes Monetarias - Cabecera.
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Lennon Shimokawa</a>
 */
@Service("sisicc.interfazPEREnviarSolicitudesMonetariasCabeceraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPEREnviarSolicitudesMonetariasCabeceraServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {

	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO
				.executeInterfazRETEnviarSolicitudesMonetariasCabecera(params);
	}
}