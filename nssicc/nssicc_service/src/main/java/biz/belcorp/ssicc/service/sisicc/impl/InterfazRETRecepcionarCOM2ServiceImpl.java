package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * Service para la recepcion del archivo COM-2 de la Interfaz RET.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */

@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazRETRecepcionarCOM2ServiceImpl extends
		BaseInterfazEntradaAbstractService {

	protected void processLine(InterfazParams interfazParams, int lineCount,
			Map row) throws InterfazException {
		interfazSiCCDAO.insertarInterfazRETRecepcionatCOM2(row);
	}
}
