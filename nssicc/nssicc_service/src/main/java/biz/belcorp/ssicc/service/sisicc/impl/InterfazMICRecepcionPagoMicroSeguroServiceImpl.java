/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazMICDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * 
 * <p>
 * <a href="InterfazMICServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 */
@Service("sisicc.interfazMICRecepcionPagoMicroSeguroService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazMICRecepcionPagoMicroSeguroServiceImpl extends BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazMICDAO")
	InterfazMICDAO interfazMICDAO;
	
	
	
	protected void processLine(InterfazParams interfazParams, int lineCount,
		Map row) throws InterfazException {
		try {
			interfazMICDAO.insertInterfazMICRecepcionarPagos(row);
		} catch (Exception e) {
			throw new InterfazException("Error al procesar la linea: "
					+ e.getMessage());
		}
	}


}