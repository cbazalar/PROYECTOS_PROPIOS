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
 * Service de la recepcion Venta Retail Cabecera de la Interfaz RET
 * 
 * @author <a href="mailto:msilva@belcorp.biz">Marco Silva</a>
 */
@Service("sisicc.interfazRETRecepcionarVentasRetailCabService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazRETRecepcionarVentasRetailCabServiceImpl extends
		BaseInterfazEntradaAbstractService {

	protected void beforeReadData(InterfazParams interfazParams)
			throws InterfazException {
		log.debug("Entering 'beforeReadData' method");
		try {
			interfazSiCCDAO.deleteInterfazRETRecepcionarVentasRetailDetDet();			
			interfazSiCCDAO.deleteInterfazRETRecepcionarVentasRetailDet();			
			interfazSiCCDAO.deleteInterfazRETRecepcionarVentasRetailCab();
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
				Date fecDocRef = dateFormat.parse((String) row.get("fecDocRef"));
				Date fecIngreso = dateFormat.parse((String) row.get("fecIngreso"));			
				row.put("fecDocRef", fecDocRef);
				row.put("fecIngreso", fecIngreso);
			} catch (ParseException e) {
				row.put("fecDocRef", null);
				row.put("fecIngreso", null);
			}
			interfazSiCCDAO.insertInterfazRETRecepcionarVentasRetailCab(row);
		} catch (Exception e) {
			throw new InterfazException("Error al procesar la linea: "
					+ e.getMessage());
		}
	}

}