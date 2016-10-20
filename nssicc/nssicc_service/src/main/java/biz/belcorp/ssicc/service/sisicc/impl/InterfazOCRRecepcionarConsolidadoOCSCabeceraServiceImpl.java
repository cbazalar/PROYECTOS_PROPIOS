package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * Service de la recepcion del Consolidado OCS Cabecera de la Interfaz OCR.
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma G:</a>
 */
@Service("sisicc.interfazOCRRecepcionarConsolidadoOCSCabeceraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazOCRRecepcionarConsolidadoOCSCabeceraServiceImpl extends	BaseInterfazEntradaAbstractService {

	protected void beforeReadData(InterfazParams interfazParams) throws InterfazException {
		
		if (log.isDebugEnabled()) log.debug("Entering 'beforeReadData' method");
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());
		interfazSiCCDAO.executeInterfazOCRRecepcionarConsolidadoOCSCabecera(map);
		
	}

}