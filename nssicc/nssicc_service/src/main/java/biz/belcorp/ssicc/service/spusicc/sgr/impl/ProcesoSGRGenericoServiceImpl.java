package biz.belcorp.ssicc.service.spusicc.sgr.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.sgr.ProcesoSGRGenericoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sgr.ProcesoSGRGenericoService;

/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 *
 */
@Service("spusicc.procesoSGRGenericoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSGRGenericoServiceImpl extends BaseService implements ProcesoSGRGenericoService {

	@Resource(name="spusicc.procesoSGRGenericoDAO")
	ProcesoSGRGenericoDAO procesoSGRGenericoDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.service.ProcesoSGRGenericoService#executeCancelarPolizas(java.util.Map)
	 */
	public void executeCancelarPolizas(Map map) {
		procesoSGRGenericoDAO.executeCancelarPolizas(map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.service.ProcesoSGRGenericoService#executeIdentificarPolizasVigentes(java.util.Map)
	 */
	public void executeIdentificarPolizasVigentes(Map map) {
		procesoSGRGenericoDAO.executeIdentificarPolizasVigentes(map);		
	}
	
	
}
