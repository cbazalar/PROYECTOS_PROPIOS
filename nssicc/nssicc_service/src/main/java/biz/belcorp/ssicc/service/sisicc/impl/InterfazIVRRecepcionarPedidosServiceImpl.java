/*
 * Created on 19/02/2007 05:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazRECRecepcionarBoletasRecojoCabeceraServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazIVRDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazIVRRecepcionarPedidosServiceImpl"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez
 *         </a>
 */
@Service("sisicc.interfazIVRRecepcionarPedidosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazIVRRecepcionarPedidosServiceImpl extends	BaseInterfazEntradaAbstractService {

	@Resource(name="sisicc.interfazIVRDAO")
	InterfazIVRDAO interfazIVRDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#processLine(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, int, java.util.Map)
	 */
	protected void processLine(InterfazParams interfazParams, int lineCount,Map row) throws InterfazException {

		try {
			String numLoteSTO = (String)interfazParams.getQueryParams().get("numLoteSTO");
			row.put("numLoteSTO", numLoteSTO);
			interfazIVRDAO.insertInterfazIVRRecepcionarPedidos(row);

		} catch (Exception e) {
			throw new InterfazException("Registro Nro: " + lineCount + ". " + e.getMessage());
		}
	}

	 /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams) throws InterfazException {
			if (log.isDebugEnabled())
				log.debug("Entering 'afterProcessInterfaz' method");

			interfazIVRDAO.executeRegistraPedidoIVR(interfazParams.getQueryParams());
	    }
}