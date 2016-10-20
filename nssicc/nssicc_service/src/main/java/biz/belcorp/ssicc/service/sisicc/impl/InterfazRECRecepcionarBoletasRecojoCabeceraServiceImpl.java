/*
 * Created on 19/02/2007 05:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazRECRecepcionarBoletasRecojoCabeceraServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazRECRecepcionarBoletasRecojoCabeceraServiceImpl"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose A. Cairampoma Granados
 *         </a>
 */
@Service("sisicc.interfazRECRecepcionarBoletasRecojoCabeceraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazRECRecepcionarBoletasRecojoCabeceraServiceImpl extends	BaseInterfazEntradaAbstractService {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#beforeProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeProcessInterfaz(InterfazParams interfazParams)	throws InterfazException {
		interfazSiCCDAO.deleteInterfazRECRecepcionarBoletasRecojoCabecera();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#processLine(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, int, java.util.Map)
	 */
	protected void processLine(InterfazParams interfazParams, int lineCount,Map row) throws InterfazException {
		
		try {
		    interfazSiCCDAO.insertInterfazRECRecepcionarBoletasRecojoCabecera(row);
		    
		} catch (Exception e) {
			throw new InterfazException("Registro Nro: " + lineCount + ". " + e.getMessage());
		}
	}
	
}
