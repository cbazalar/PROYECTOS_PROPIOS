package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazSATDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * <p>
 * <a href="InterfazSATRecepcionarExcepcionesFechaEntregaExactaServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
@Service("sisicc.interfazSATRecepcionarExcepcionesFechaEntregaExactaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSATRecepcionarExcepcionesFechaEntregaExactaServiceImpl extends
		BaseInterfazEntradaAbstractService {
	
	
	@Resource(name="sisicc.interfazSATDAO")
	protected InterfazSATDAO interfazSATDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams) throws InterfazException {
		/*
		log.debug("Entering 'beforeReadData' method");
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());

		try {
			interfazSATDAO.executeInterfazSATRecepcionarExcepcionesFechaEntregaExacta(map);
		} catch (Exception e) {
			throw new InterfazException("Error al procesar los registros: "+ e.getMessage());
		}
		*/
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#processLine(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, int, java.util.Map)
	 */
	protected void processLine(InterfazParams interfazParams, int lineCount, Map row) throws InterfazException {
		interfazSATDAO.insertInterfazSATRecepcionarExcepcionesFechaEntregaExacta(row);
	}
}