package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * Service de la recepcion del Consolidado OCS Flexipago de la Interfaz OCR.
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
@Service("sisicc.interfazOCRRecepcionarConsolidadoOCSFlexipagoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazOCRRecepcionarConsolidadoOCSFlexipagoServiceImpl extends	BaseInterfazEntradaAbstractService {


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams) throws InterfazException {

		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());

		interfazSiCCDAO.executeInterfazOCRRecepcionarConsolidadoOCSFlexipago(map);

	}
}