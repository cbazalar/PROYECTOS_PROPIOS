package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * Service de la recepcion de Calificacion Privilege.
 * 
 * @author <a href="mailto:msilva@belcorp.biz">Marco Silva</a>
 */
@Service("sisicc.interfazPRIRecepcionarCalificacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPRIRecepcionarCalificacionServiceImpl extends
		BaseInterfazEntradaAbstractService {

	protected void beforeReadData(InterfazParams interfazParams)
			throws InterfazException {
		log.debug("Entering 'beforeReadData' method");
		try {
			interfazSiCCDAO.deleteInterfazPRIRecepcionarCalificacion();
		} catch (Exception e) {
			throw new InterfazException(
					"Error al borrar los registros de la tabla temporal: "
							+ e.getMessage());
		}
	}

	protected void processLine(InterfazParams interfazParams, int lineCount,
			Map row) throws InterfazException {
		try {
			row.put("codigoPais", interfazParams.getQueryParams().get("codigoPais"));
			interfazSiCCDAO.insertInterfazPRIRecepcionarCalificacion(row);
		} catch (Exception e) {
			throw new InterfazException("Error al procesar la linea: "
					+ e.getMessage());
		}
	}

}