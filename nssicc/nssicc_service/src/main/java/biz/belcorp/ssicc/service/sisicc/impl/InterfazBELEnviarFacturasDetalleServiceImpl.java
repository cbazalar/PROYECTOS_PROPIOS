/**
 * 
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
 * <a href="InterfazBELEnviarFacturasCabeceraServiceImpl.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Richard De los Reyes Principe </a>
 */
@Service("sisicc.interfazBELEnviarFacturasDetalleService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazBELEnviarFacturasDetalleServiceImpl extends 
	BaseInterfazSalidaStoredProcedureAbstractService {

		protected void executeStoreProcedure(Map params) {
			interfazSiCCDAO
					.executeInterfazBELEnviarFacturasDetalle(params);
		}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.BaseInterfazSalidaAbstractService#readData(java.util.Map)
	 */
/*		
	protected List readData(Map params) throws InterfazException {
		if (log.isDebugEnabled())
            log.debug("Entering 'readData' method ");
        List listFacturasDetalle = null;
        try {
        	listFacturasDetalle = interfazSiCCDAO
                    .getInterfazBELFacturasDetalle(params);
        } catch (Exception e) {
            log.error("Error al leer los datos: " + e.getMessage());
            throw new InterfazException(e.getMessage());
        }
        return listFacturasDetalle;
	}
*/
}
