package biz.belcorp.ssicc.service.spusicc.mic.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.mic.ProcesoMICGeneracionAptasDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.mic.ProcesoMICGeneracionAptasService;

/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
@Service("spusicc.mic.procesoMICGeneracionAptasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMICGeneracionAptasServiceImpl extends BaseService implements ProcesoMICGeneracionAptasService {

	@Resource(name="spusicc.procesoMICGeneracionAptasDAO")
	private ProcesoMICGeneracionAptasDAO procesoMICGeneracionAptasDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.ProcesoMICGeneracionAptasService#executeGeneracionAptasMicroSeguros(java.util.Map)
	 */
	public void executeGeneracionAptasMicroSeguros(Map criteria)
			throws Exception {
		procesoMICGeneracionAptasDAO.executeGeneracionAptasMicroSeguros(criteria);		
	}

	
	
}
