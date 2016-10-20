package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * Service de la Interfaz Percepciones de Recaudos Bancarios.
 * 
 * @author Luis Mendoza
 */
@Service("sisicc.interfazPEREnviarRecaudosBancariosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPEREnviarRecaudosBancariosServiceImpl extends
		BaseInterfazSalidaAbstractService {
	
	public List readData(Map queryParams) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'readData' method ");
		List list = null;
		try {
			list = interfazSiCCDAO.getRecaudosBancarios(queryParams);			
		} catch (Exception e) {
			throw new InterfazException(e.getMessage());
		}
		return list;
	}

}