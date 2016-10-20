package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazDATDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazDATEnviarMaestroProgramaServiceImpl.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author Yahir Rivas L.
 */
@Service("sisicc.interfazDATEnviarMaestroProgramaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazDATEnviarMaestroProgramaServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService{
	
	@Resource(name="sisicc.interfazDATDAO")
	protected InterfazDATDAO interfazDATDAO;
	
	/**
	 * @return
	 */
	public InterfazDATDAO getInterfazDATDAO() {
		return interfazDATDAO;
	}

	/**
	 * @param interfazDATDAO
	 */
	public void setInterfazDATDAO(InterfazDATDAO interfazDATDAO) {
		this.interfazDATDAO = interfazDATDAO;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	@Override
	protected void executeStoreProcedure(Map params) {
		interfazDATDAO.executeInterfazDATEnviarMaestroPrograma(params);
		
	}	

}
