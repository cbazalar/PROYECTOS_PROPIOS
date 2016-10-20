/*
 * Created on 26/12/2005 11:39:41 AM
 * biz.belcorp.ssicc.scdf.service.impl.UltimasNoticiasServiceImpl
 */
package biz.belcorp.ssicc.service.spisicc.impl;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazIMPDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;



/**
 * Service para el proceso de desbloquear a consultoras bloqueadas
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
@Service("spisicc.procesoIMPDesbloquearConsultorasBloqueadasXBRService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoIMPDesbloquearConsultorasBloqueadasXBRServiceImpl extends BaseInterfazProcesoAbstractService {

	@Resource(name="sisicc.interfazIMPDAO")
	private InterfazIMPDAO interfazIMPDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazIMPDAO.executeInterfazIMPDesbloquearConsultorasBloqueadasxBR(params);
	}
	
}