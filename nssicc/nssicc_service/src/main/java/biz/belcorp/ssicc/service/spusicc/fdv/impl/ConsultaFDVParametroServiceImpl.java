package biz.belcorp.ssicc.service.spusicc.fdv.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.fdv.ConsultaFDVParametroDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.fdv.ConsultaFDVParametroService;

/**
 * <p>
 * <a href="ConsultaFDVParametroServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */
@Service("spusicc.consultaFDVParametroService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ConsultaFDVParametroServiceImpl extends BaseService implements ConsultaFDVParametroService{

	@Resource(name = "spusicc.consultaFDVParametroDAO")
	private ConsultaFDVParametroDAO consultaFDVParametroDAO;


	/**
     * 
     * @see biz.belcorp.ssicc.service.spusicc.fdv.spusicc.fdv.service.ConsultaFDVParametroService#getParametrosFDVByCriteria(java.util.Map)
     */
	public List getParametrosFDVByCriteria(Map criteria) {
		return consultaFDVParametroDAO.getParametrosFDVByCriteria(criteria);
	}

	/**
	 * @param consultaFDVParametroDAO the consultaFDVParametroDAO to set
	 */
	public void setConsultaFDVParametroDAO(
			ConsultaFDVParametroDAO consultaFDVParametroDAO) {
		this.consultaFDVParametroDAO = consultaFDVParametroDAO;
	}
	
	
}
