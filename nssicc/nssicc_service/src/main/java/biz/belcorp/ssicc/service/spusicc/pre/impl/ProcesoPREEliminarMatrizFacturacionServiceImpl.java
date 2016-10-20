package biz.belcorp.ssicc.service.spusicc.pre.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pre.ProcesoPREEliminarMatrizFacturacionDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pre.ProcesoPREEliminarMatrizFacturacionService;

/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
@Service("spusicc.procesoPREEliminarMatrizFacturacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPREEliminarMatrizFacturacionServiceImpl extends BaseService implements ProcesoPREEliminarMatrizFacturacionService {
		
	@Resource(name="spusicc.procesoPREEliminarMatrizFacturacionDAO")
	private ProcesoPREEliminarMatrizFacturacionDAO procesoPREEliminarMatrizFacturacionDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pre.ProcesoPREEliminarMatrizFacturacionService#executeEliminarMatrizFacturacion(java.util.Map)
	 */
	@Override
	public void executeEliminarMatrizFacturacion(Map params) {
		procesoPREEliminarMatrizFacturacionDAO.executeEliminarMatrizFacturacion(params);
	}
}