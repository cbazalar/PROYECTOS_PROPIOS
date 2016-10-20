/*
 * Created on 26/09/2006 05:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazREUEnviarDocumentosAnuladosServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazMYEEnviarPercepcionesVentaDirectaCabeceraServiceImpl"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("sisicc.interfazMYEEnviarPercepcionesVentaDirectaCabeceraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazMYEEnviarPercepcionesVentaDirectaCabeceraServiceImpl extends
	BaseInterfazSalidaStoredProcedureAbstractService {
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.BaseInterfazSalidaAbstractService#readData(java.util.Map)
	 */
	/*protected List readData(Map queryParams) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'readData' method");
		List list = null;
		try {
			list = interfazSiCCDAO.getInterfazMYEPercepcionesVentaDirectaCabecera(queryParams);
		} catch (Exception e) {
			log.error("Error al leer los datos: " + e.getMessage());
			log.debug(e);
			throw new InterfazException(e.getMessage());
		}
		return list;
	}*/
	
	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO.executeInterfazMYEEnviarVentaDirectaCabecera(params);
	}

	
}
