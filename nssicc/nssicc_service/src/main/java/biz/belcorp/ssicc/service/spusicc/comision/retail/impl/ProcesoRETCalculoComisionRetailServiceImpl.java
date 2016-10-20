package biz.belcorp.ssicc.service.spusicc.comision.retail.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.comision.retail.ProcesoRETCalculoComisionRetailDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.comision.retail.ProcesoRETCalculoComisionRetailService;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoRETCalculoComisionRetailServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */	
@Service("spusicc.procesoRETCalculoComisionRetailService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoRETCalculoComisionRetailServiceImpl extends BaseService
		implements ProcesoRETCalculoComisionRetailService {

	@Resource(name="spusicc.procesoRETCalculoComisionRetailDAO")
	ProcesoRETCalculoComisionRetailDAO procesoRETCalculoComisionRetailDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.service.ProcesoRETCalculoComisionRetailService#executeCalculoComisionRetail(java.util.Map)
	 */
	public void executeCalculoComisionRetail(Map criteria){
		procesoRETCalculoComisionRetailDAO.executeCalculoComisionRetail(criteria);
	}
	

}
