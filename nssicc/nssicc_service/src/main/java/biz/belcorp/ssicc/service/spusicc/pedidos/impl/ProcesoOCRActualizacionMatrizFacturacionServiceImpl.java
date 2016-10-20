package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoOCRActualizacionMatrizFacturacionDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoOCRActualizacionMatrizFacturacionService;

@Service("spusicc.procesoOCRActualizacionMatrizFacturacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoOCRActualizacionMatrizFacturacionServiceImpl extends BaseService implements 	ProcesoOCRActualizacionMatrizFacturacionService {
	
	@Resource(name="spusicc.procesoOCRActualizacionMatrizFacturacionDAO")
	ProcesoOCRActualizacionMatrizFacturacionDAO procesoOCRActualizacionMatrizFacturacionDAO; 
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ProcesoOCRActualizacionMatrizFacturacionService#executeOCRActualizacionMatrizFacturacion(java.util.Map)
	 */
	public void executeOCRActualizacionMatrizFacturacion(Map params) {
		procesoOCRActualizacionMatrizFacturacionDAO.executeOCRActualizacionMatrizFacturacion(params);
		
	}
	
}
