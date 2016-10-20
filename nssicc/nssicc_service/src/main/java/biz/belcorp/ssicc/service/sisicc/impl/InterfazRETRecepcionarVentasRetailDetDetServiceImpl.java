package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * Service de la recepcion Venta Retail Linea Operacion de la Interfaz RET
 * 
 * @author <a href="mailto:msilva@belcorp.biz">Marco Silva</a>
 */
@Service("sisicc.interfazRETRecepcionarVentasRetailDetDetService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazRETRecepcionarVentasRetailDetDetServiceImpl extends
		BaseInterfazEntradaAbstractService {


	protected void processLine(InterfazParams interfazParams, int lineCount,
			Map row) throws InterfazException {
		try {
			interfazSiCCDAO.insertInterfazRETRecepcionarVentasRetailDetDet(row);
		} catch (Exception e) {
			throw new InterfazException("Error al procesar la linea: "
					+ e.getMessage());
		}
	}
	
	protected void afterProcessInterfaz(InterfazParams interfazParams) throws InterfazException {
		// TODO Auto-generated method stub
		try {
			Map mapQueryParams = (Map) interfazParams.getQueryParams();			
			interfazSiCCDAO.insertRETConsolidadoVentasRetail(mapQueryParams);
			interfazSiCCDAO.insertRETConsolidadoVentasRetailxZona(mapQueryParams);
			interfazSiCCDAO.updateRETConsolidadoVentasRetail(mapQueryParams);			
			
		} catch (Exception e) {
			throw new InterfazException(
					"Error al borrar o insertar los registros de la tabla INT_RET_CONSO_VENTA: "
							+ e.getMessage());
		}
	}

}