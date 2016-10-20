/*
 * Created on 03/10/2006 12:05:40 AM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazEVIEnviarConsultoraCDRServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazEVIDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * Service para el envio de Consultoras CDR de la Interfaz EVI.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
@Service("sisicc.interfazEVIEnviarConsultoraCDRService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazEVIEnviarConsultoraCDRServiceImpl extends
		BaseInterfazSalidaAbstractService {

	@Resource(name="sisicc.interfazEVIDAO")
	private InterfazEVIDAO interfazEVIDAO;

	protected List readData(Map queryParams) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'readData' method ");
		List listConsultoraCDR = null;
		try {
			listConsultoraCDR = this.interfazEVIDAO
					.getInterfazEVIConsultoraCDR(queryParams);

		} catch (Exception e) {
			log.error("Error al leer los datos: " + e.getMessage());
			throw new InterfazException(e.getMessage());
		}
		return listConsultoraCDR;
	}

	
}
