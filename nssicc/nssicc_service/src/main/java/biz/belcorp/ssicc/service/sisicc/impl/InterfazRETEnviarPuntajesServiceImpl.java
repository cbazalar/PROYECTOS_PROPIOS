package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * Service para el envio de archivos de Puntajes para la Interfaz RET.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */

@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazRETEnviarPuntajesServiceImpl extends
		BaseInterfazSalidaAbstractService {

	protected List readData(Map params) throws InterfazException {
		return null;
	}

}
