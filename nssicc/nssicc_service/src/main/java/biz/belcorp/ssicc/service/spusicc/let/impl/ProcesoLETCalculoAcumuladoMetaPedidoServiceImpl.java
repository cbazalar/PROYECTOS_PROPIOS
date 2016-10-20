package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETCalculoAcumuladoMetaPedidoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.let.ProcesoLETCalculoAcumuladoMetaPedidoService;

/**
 * Clase de la implementacin de la capa BO (Bussiness Object)
 * 
 * <p>
 * <a href="ProcesoLETCalculoAcumuladoMetaPedidoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
@Service("spusicc.procesoLETCalculoAcumuladoMetaPedidoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLETCalculoAcumuladoMetaPedidoServiceImpl extends BaseService implements ProcesoLETCalculoAcumuladoMetaPedidoService {

	@Resource(name="spusicc.procesoLETCalculoAcumuladoMetaPedidoDAO")
	private ProcesoLETCalculoAcumuladoMetaPedidoDAO procesoLETCalculoAcumuladoMetaPedidoDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETCalculoAcumuladoMetaPedidoService#executeProcesoLETCalculoAcumuladoMetaPedido(java.util.Map)
	 */
	public void executeProcesoLETCalculoAcumuladoMetaPedido(Map params) {
		log.info("Entro a ProcesoLETCalculoAcumuladoMetaPedidoServiceImpl - executeProcesoLETCalculoAcumuladoMetaPedido(java.util.Map)");
		procesoLETCalculoAcumuladoMetaPedidoDAO.executeProcesoLETCalculoAcumuladoMetaPedido(params);
		log.info("Salio a ProcesoLETCalculoAcumuladoMetaPedidoServiceImpl - executeProcesoLETCalculoAcumuladoMetaPedido(java.util.Map)");
	}

	
}
