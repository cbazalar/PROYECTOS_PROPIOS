package biz.belcorp.ssicc.service.spusicc.comision.retail.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.comision.retail.ProcesoRETReasignacionGerenteZonaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.comision.retail.ProcesoRETReasignacionGerenteZonaService;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoRETReasignacionGerenteZonaServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
@Service("spusicc.procesoRETReasignacionGerenteZonaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoRETReasignacionGerenteZonaServiceImpl extends BaseService
		implements ProcesoRETReasignacionGerenteZonaService {

	@Resource(name="spusicc.procesoRETReasignacionGerenteZonaDAO")
	ProcesoRETReasignacionGerenteZonaDAO procesoRETReasignacionGerenteZonaDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.service.ProcesoRETReasignacionGerenteZonaService#executeReasignacionGerenteZona(java.util.Map)
	 */
	public void executeReasignacionGerenteZona(Map criteria) {
		procesoRETReasignacionGerenteZonaDAO.executeReasignacionGerenteZona(criteria);
	}
	

	
}
