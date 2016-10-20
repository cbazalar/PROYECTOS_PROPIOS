package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCargaOrdenesRetailDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCCargaOrdenesRetailService;

/**
 * Service para el proceso que ejecutara los metodos del proceso de Carga Ordenes Retail
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoINCCargaOrdenesRetailService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCCargaOrdenesRetailServiceImpl extends BaseService implements
	ProcesoINCCargaOrdenesRetailService {

	@Resource(name="spusicc.procesoINCCargaOrdenesRetailDAO")
	private ProcesoINCCargaOrdenesRetailDAO procesoINCCargaOrdenesRetailDAO;


	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaOrdenesRetailService#existeCargaOrdenesRetailProcesado(java.util.Map)
	 */
	public boolean existeCargaOrdenesRetailProcesado(Map criteria) {
		return procesoINCCargaOrdenesRetailDAO.existeCargaOrdenesRetailProcesado(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaOrdenesRetailService#executeCargaOrdenesRetail(java.util.Map)
	 */
	public void executeCargaOrdenesRetail(Map params) {
		procesoINCCargaOrdenesRetailDAO.executeCargaOrdenesRetail(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaOrdenesRetailService#getCargasEjecutadasECM(java.util.Map)
	 */
	public List getCargasEjecutadasECM (Map criteria) {
		return procesoINCCargaOrdenesRetailDAO.getCargasEjecutadasECM(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaOrdenesRetailService#executeAnularCargaOrdenesRetail(java.util.Map)
	 */
	public void executeAnularCargaOrdenesRetail(Map params) {
		procesoINCCargaOrdenesRetailDAO.executeAnularCargaOrdenesRetail(params);
	}
	
}
