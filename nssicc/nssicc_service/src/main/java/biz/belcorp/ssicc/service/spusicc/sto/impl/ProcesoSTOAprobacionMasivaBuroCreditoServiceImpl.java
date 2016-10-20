/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOAprobacionMasivaBuroCreditoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOAprobacionMasivaBuroCreditoService;

/**
 * @author sguerra
 *
 */
@Service("spusicc.procesoSTOAprobacionMasivaBuroCreditoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSTOAprobacionMasivaBuroCreditoServiceImpl extends BaseService implements ProcesoSTOAprobacionMasivaBuroCreditoService {

	@Resource(name="spusicc.procesoSTOAprobacionMasivaBuroCreditoDAO")
	ProcesoSTOAprobacionMasivaBuroCreditoDAO procesoSTOAprobacionMasivaBuroCreditoDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOAprobacionMasivaBuroCreditoService#getValidaNumeroDocumento(java.lang.String)
	 */
	public Integer getValidaNumeroDocumento(String value) {
		return procesoSTOAprobacionMasivaBuroCreditoDAO.getValidaNumeroDocumento(value);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOAprobacionMasivaBuroCreditoService#updateNumeroDocumento(java.util.Map)
	 */
	public void updateNumeroDocumento(Map criteria) {
		procesoSTOAprobacionMasivaBuroCreditoDAO.updateNumeroDocumento(criteria);
	}

}
