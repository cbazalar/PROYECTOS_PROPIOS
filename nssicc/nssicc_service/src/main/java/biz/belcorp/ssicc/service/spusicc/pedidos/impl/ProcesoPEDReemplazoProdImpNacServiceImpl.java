package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDReemplazoProdImpNacDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDReemplazoProdImpNacService;

/**
 * @author peextjrios
 */
@Service("spusicc.pedido.procesoPEDReemplazoProdImpNacService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPEDReemplazoProdImpNacServiceImpl extends BaseService implements ProcesoPEDReemplazoProdImpNacService{
	
	@Resource(name="spusicc.pedidos.procesoPEDReemplazoProdImpNacDAO")
	private ProcesoPEDReemplazoProdImpNacDAO reemplazoProdImpNacDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDReemplazoProdImpNacService#executeReemplazoProdImpNac(java.util.Map)
	 */
	public void executeReemplazoProdImpNac(Map params) {
		reemplazoProdImpNacDAO.executeReemplazoProdImpNac(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDReemplazoProdImpNacService#getIndicadorPedidosFacturadosPeriodo(java.util.Map)
	 */
	public String getIndicadorPedidosFacturadosPeriodo(Map params) {
		return reemplazoProdImpNacDAO.getIndicadorPedidosFacturadosPeriodo(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDReemplazoProdImpNacService#getRegistrosProcesadosReemplazoProdImpNac(java.util.Map)
	 */
	public String getRegistrosProcesadosReemplazoProdImpNac(Map params) {
		return reemplazoProdImpNacDAO.getRegistrosProcesadosReemplazoProdImpNac(params);
	}
}