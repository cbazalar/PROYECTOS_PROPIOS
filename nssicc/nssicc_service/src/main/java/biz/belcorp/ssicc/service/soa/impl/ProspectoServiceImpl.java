/**
 * 
 */
package biz.belcorp.ssicc.service.soa.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import biz.belcorp.ssicc.dao.soa.ProspectoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.soa.ProspectoService;



/**
 * @author peextsbuchelli
 *
 */
@Service("soa.prospectoService")
public class ProspectoServiceImpl extends BaseService implements ProspectoService {
	
	@Resource(name="soa.prospectoDAO")
	private ProspectoDAO prospectoDAO;
	
	/**
	 * @return
	 */
	public ProspectoDAO getProspectoDAO() {
		return prospectoDAO;
	}

	/**
	 * @param cronogramaDAO
	 */
	public void setProspectoDAO(ProspectoDAO cronogramaDAO) {
		this.prospectoDAO = cronogramaDAO;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.www.service.ProspectoService#getValidacionCrediticiaUsuario(java.util.Map)
	 */
	public List getValidacionCrediticiaUsuario(Map criteria) {
		return prospectoDAO.getValidacionCrediticiaUsuario(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.www.service.ProspectoService#getLengthDocumento(java.util.Map)
	 */
	public int getLengthDocumento(Map criteria) {
		// TODO Auto-generated method stub
		return prospectoDAO.getLengthDocumento(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.www.service.ProspectoService#getMotivosBloqueoXConsultora(java.util.Map)
	 */
	public List getMotivosBloqueoXConsultora(Map criteria) {
		// TODO Auto-generated method stub
		return prospectoDAO.getMotivosBloqueoXConsultora(criteria);
	}
	
}