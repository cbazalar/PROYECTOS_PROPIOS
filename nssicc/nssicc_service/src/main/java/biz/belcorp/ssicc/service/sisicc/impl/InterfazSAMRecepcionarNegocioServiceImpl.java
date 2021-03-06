/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazSAMDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * <p>
 * <a href="InterfazSAMRecepcionarNegocioServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Danny Amaro
 */
@Service("sisicc.interfazSAMRecepcionarNegocioService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSAMRecepcionarNegocioServiceImpl extends BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazSAMDAO")
	protected InterfazSAMDAO interfazSAMDAO;
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#processLine(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, int, java.util.Map)
	 */
	protected void processLine(InterfazParams interfazParams, int lineCount,
			Map row) throws InterfazException {
		try {
			
			Interfaz interfaz = interfazParams.getInterfaz();
			row.put("codigoPais", interfaz.getCodigoPais());
			
			interfazSAMDAO.executeRecepcionNegocio(row);
		    
		} catch (Exception e) {
			throw new InterfazException("Registro Nro: " + lineCount + ". " + e.getMessage());
		}
		
	}

}
