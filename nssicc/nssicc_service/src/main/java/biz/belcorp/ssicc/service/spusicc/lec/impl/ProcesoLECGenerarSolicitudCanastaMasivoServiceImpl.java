package biz.belcorp.ssicc.service.spusicc.lec.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECGenerarSolicitudCanastaMasivoDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

@Service("spusicc.procesoLECGenerarSolicitudCanastaMasivoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLECGenerarSolicitudCanastaMasivoServiceImpl extends BaseInterfazProcesoAbstractService{
	
	@Resource(name="spusicc.procesoLECGenerarSolicitudCanastaMasivoDAO")
	ProcesoLECGenerarSolicitudCanastaMasivoDAO procesoLECGenerarSolicitudCanastaMasivoDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	@Override
	protected void executeStoreProcedure(Map params) throws InterfazException, Exception {
		log.debug(">> ProcesoLECGenerarSolicitudCanastaMasivoServiceImpl.executeStoreProcedure");
		
		params.put("codigoRegion", "");
		params.put("tipoProceso", "");
		
	    procesoLECGenerarSolicitudCanastaMasivoDAO.executeProcesoLECGenerarSolicitudCanastaMasivo(params);
		
	}
}