package biz.belcorp.ssicc.service.spusicc.reclamos.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.reclamos.ProcesoRECEjecucionMensajesReclamosDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.ProcesoRECEjecucionMensajesReclamosService;

@Service("spusicc.procesoRECEjecucionMensajesReclamosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoRECEjecucionMensajesReclamosServiceImpl extends BaseService implements 	ProcesoRECEjecucionMensajesReclamosService {
	
	@Resource(name="spusicc.procesoRECEjecucionMensajesReclamosDAO")
	ProcesoRECEjecucionMensajesReclamosDAO procesoRECEjecucionMensajesReclamosDAO; 
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ProcesoRECEjecucionMensajesReclamosService#executeRECEjecucionMensajesReclamos(java.util.Map)
	 */
	public void executeRECEjecucionMensajesReclamos(Map params) {
		procesoRECEjecucionMensajesReclamosDAO.executeRECEjecucionMensajesReclamos(params);
		
	}
	
}
