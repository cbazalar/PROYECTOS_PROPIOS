/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazIMPDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazIMPEnviarNotasCreditoBoletaDetalleRetailServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 */
@Service("sisicc.interfazIMPEnviarNotasCreditoBoletaDetalleRetailService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazIMPEnviarNotasCreditoBoletaDetalleRetailServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazIMPDAO")
	private InterfazIMPDAO interfazIMPDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazIMPDAO.executeInterfazIMPEnviarNotasCreditoBoletaDetalleRetail(params);
	}

}
