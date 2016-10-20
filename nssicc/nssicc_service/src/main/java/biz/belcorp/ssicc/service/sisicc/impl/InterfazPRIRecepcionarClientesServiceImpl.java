package biz.belcorp.ssicc.service.sisicc.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * Service de la recepcion de Clientes Privilege.
 * 
 * @author <a href="mailto:msilva@belcorp.biz">Marco Silva</a>
 */
@Service("sisicc.interfazPRIRecepcionarClientesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPRIRecepcionarClientesServiceImpl extends
		BaseInterfazEntradaAbstractService {

	protected void beforeReadData(InterfazParams interfazParams)
			throws InterfazException {
		log.debug("Entering 'beforeReadData' method");
		try {
			interfazSiCCDAO.deleteInterfazPRIRecepcionarClientes();
		} catch (Exception e) {
			throw new InterfazException(
					"Error al borrar los registros de la tabla temporal: "
							+ e.getMessage());
		}
	}

	protected void processLine(InterfazParams interfazParams, int lineCount,
			Map row) throws InterfazException {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			try {
				Date fecha = dateFormat.parse((String) row.get("fechaNacimientoCliente"));
				row.put("fechaNacimientoCliente", fecha);
			} catch (ParseException e) {
				row.put("fechaNacimientoCliente", null);
			}
			
			row.put("codigoPais", interfazParams.getQueryParams().get("codigoPais"));
			interfazSiCCDAO.insertInterfazPRIRecepcionarClientes(row);
		} catch (Exception e) {
			throw new InterfazException("Error al procesar la linea: "
					+ e.getMessage());
		}
	}

}