/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.mae.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAEDesbloquearClienteConPedidoDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author itocto
 *
 */
@Service("spusicc.procesoMAEDesbloquearClienteConPedidoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAEDesbloquearClienteConPedidoServiceImpl extends BaseInterfazProcesoAbstractService{

	@Resource(name="spusicc.procesoMAEDesbloquearClienteConPedidoDAO")
	private ProcesoMAEDesbloquearClienteConPedidoDAO procesoMAEDesbloquearClienteConPedidoDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) throws InterfazException,
			Exception {
		if(log.isDebugEnabled())
			log.debug("Parametros: " + params);
		
		procesoMAEDesbloquearClienteConPedidoDAO.executeDesbloquearClienteConPedido(params);
	}


}
