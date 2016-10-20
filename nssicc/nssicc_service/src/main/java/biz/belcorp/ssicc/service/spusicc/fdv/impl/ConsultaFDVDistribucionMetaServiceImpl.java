package biz.belcorp.ssicc.service.spusicc.fdv.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.fdv.ConsultaFDVDistribucionMetaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.fdv.ConsultaFDVDistribucionMetaService;

/**
 * <p>
 * <a href="ConsultaFDVDistribucionMetaServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */
@Service("spusicc.consultaFDVDistribucionMetaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ConsultaFDVDistribucionMetaServiceImpl extends BaseService implements ConsultaFDVDistribucionMetaService{

	@Resource(name = "spusicc.consultaFDVDistribucionMetaDAO")
	private ConsultaFDVDistribucionMetaDAO consultaFDVDistribucionMetaDAO;


	/**
     * 
     * @see biz.belcorp.ssicc.service.spusicc.fdv.spusicc.fdv.service.ConsultaFDVDistribucionMetaService#getDistribucionMetaFDVByCriteria(java.util.Map)
     */
	public List getDistribucionMetaFDVByCriteria(Map criteria) {
		return consultaFDVDistribucionMetaDAO.getDistribucionMetaFDVByCriteria(criteria);
	}


	/**
	 * @param consultaFDVDistribucionMetaDAO the consultaFDVDistribucionMetaDAO to set
	 */
	public void setConsultaFDVDistribucionMetaDAO(
			ConsultaFDVDistribucionMetaDAO consultaFDVDistribucionMetaDAO) {
		this.consultaFDVDistribucionMetaDAO = consultaFDVDistribucionMetaDAO;
	}

	
	
}
