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
 * Service de la recepcion Venta Retail Operacion de la Interfaz RET
 * 
 * @author <a href="mailto:msilva@belcorp.biz">Marco Silva</a>
 */
@Service("sisicc.interfazRETRecepcionarVentasRetailDetService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazRETRecepcionarVentasRetailDetServiceImpl extends
		BaseInterfazEntradaAbstractService {

	protected void processLine(InterfazParams interfazParams, int lineCount,
			Map row) throws InterfazException {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			try {
				Date fecFact = dateFormat.parse((String) row.get("fecFact"));
				row.put("fecFact", fecFact);
			} catch (ParseException e) {
				row.put("fecFact", null);
			}
			interfazSiCCDAO.insertInterfazRETRecepcionarVentasRetailDet(row);
		} catch (Exception e) {
			throw new InterfazException("Error al procesar la linea: "
					+ e.getMessage());
		}
	}
	

}