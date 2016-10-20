package biz.belcorp.ssicc.service.spusicc.lec.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECCalcularObjetivosBonosDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;


/**
 * <p>
 * <a href="ProcesoLECCalcularObjetivosBonosServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Yahir Rivas L.
 *         
 */
@Service("spusicc.procesoLECCalcularObjetivosBonosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLECCalcularObjetivosBonosServiceImpl extends BaseInterfazProcesoAbstractService{
	
	@Resource(name="spusicc.procesoLECCalcularObjetivosBonosDAO")
	private ProcesoLECCalcularObjetivosBonosDAO procesoLECCalcularObjetivosBonosDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	@Override
	protected void executeStoreProcedure(Map params) throws InterfazException, Exception {
		log.debug(">> ProcesoLECCalcularObjetivosBonosServiceImpl.executeStoreProcedure");
		procesoLECCalcularObjetivosBonosDAO.executeProcesoLECCalcularObjetivosBonos(params);
	}
}