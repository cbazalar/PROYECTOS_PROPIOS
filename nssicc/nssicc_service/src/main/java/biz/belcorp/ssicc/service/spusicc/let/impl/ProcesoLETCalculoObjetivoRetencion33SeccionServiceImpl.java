/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETCalculoObjetivoRetencion33SeccionDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Proceso que realiza el calculo Objetivo Retencion 3/3 Seccion
 * 
 * <p>
 * <a href="ProcesoLETCalculoObjetivoRetencion33SeccionServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Danny Amaro
 *         
 *         
 */
@Service("spusicc.procesoLETCalculoObjetivoRetencion33SeccionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLETCalculoObjetivoRetencion33SeccionServiceImpl extends BaseInterfazProcesoAbstractService{
	
	@Resource(name="spusicc.procesoLETCalculoObjetivoRetencion33SeccionDAO")
	private ProcesoLETCalculoObjetivoRetencion33SeccionDAO procesoLETCalculoObjetivoRetencion33SeccionDAO;
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	@Override
	protected void executeStoreProcedure(Map params) throws InterfazException,
			Exception {
		procesoLETCalculoObjetivoRetencion33SeccionDAO.executeProcesoLETCalculoObjetivoRetencion33Seccion(params);		
	}

}
