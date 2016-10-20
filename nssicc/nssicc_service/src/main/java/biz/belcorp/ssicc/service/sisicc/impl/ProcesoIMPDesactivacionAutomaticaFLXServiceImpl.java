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
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author PERRAMIREZ
 *
 */
@Service("sisicc.procesoIMPDesactivacionAutomaticaFLXService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoIMPDesactivacionAutomaticaFLXServiceImpl extends BaseInterfazProcesoAbstractService {

	@Resource(name="sisicc.interfazIMPDAO")
	private InterfazIMPDAO interfazIMPDAO;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService
	 * #executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		
		log.debug("inicio ProcesoIMPDesactivacionAutomaticaFLXServiceImpl"	+ params);
		
		try {
			
			interfazIMPDAO.executeDesactivacionAutomaticaFLX();											
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
				
	}
	
}
