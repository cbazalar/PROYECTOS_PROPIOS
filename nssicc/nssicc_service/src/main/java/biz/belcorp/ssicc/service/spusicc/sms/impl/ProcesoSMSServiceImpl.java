/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.sms.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sms.ProcesoSMSDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sms.ProcesoSMSService;

/**
 * @author Danny Amaro
 *
 */

@Service("spusicc.procesoSMSService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSMSServiceImpl extends BaseService implements ProcesoSMSService{
	
	@Resource(name="spusicc.procesoSMSDAO")
	ProcesoSMSDAO procesoSMSDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sms.service.ProcesoSMSService#getSMSList(java.util.Map)
	 */
	public List getSMSList(Map criteria) {		
		return this.procesoSMSDAO.getSMSList(criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sms.service.ProcesoSMSService#insertSMS(java.util.Map, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertSMS(Map criteria, Usuario usuario) {
		this.procesoSMSDAO.insertSMS(criteria, usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sms.service.ProcesoSMSService#removeSMS(java.util.Map)
	 */
	public void removeSMS(Map criteria) {
		this.procesoSMSDAO.removeSMS(criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sms.service.ProcesoSMSService#activarSMS(java.util.Map)
	 */
	public void activarSMS(Map criteria) {
		this.procesoSMSDAO.activarSMS(criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sms.service.ProcesoSMSService#desactivarSMS(java.util.Map)
	 */
	public void desactivarSMS(Map criteria) {
		this.procesoSMSDAO.desactivarSMS(criteria);		
	}

}
