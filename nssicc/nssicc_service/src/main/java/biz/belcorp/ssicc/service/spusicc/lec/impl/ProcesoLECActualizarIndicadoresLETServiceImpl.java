package biz.belcorp.ssicc.service.spusicc.lec.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECActualizarIndicadoresLETDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Proceso que realiza la Actualizacion de indicadores LET
 * 
 * <p>
 * <a href="ProcesoLECActualizarIndicadoresLETServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Ivan Tocto.
 *         
 */
@Service("spusicc.procesoLECActualizarIndicadoresLETService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLECActualizarIndicadoresLETServiceImpl extends BaseInterfazProcesoAbstractService{
	
	@Resource(name="spusicc.procesoLECActualizarIndicadoresLETDAO")
	private ProcesoLECActualizarIndicadoresLETDAO procesoLECActualizarIndicadoresLETDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	@Override
	protected void executeStoreProcedure(Map params) throws InterfazException, Exception {
		procesoLECActualizarIndicadoresLETDAO.executeProcesoLECActualizarIndicadoresLET(params);
		
	}
	
}
