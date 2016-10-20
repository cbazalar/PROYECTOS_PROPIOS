package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDResecuenciacionPedidosDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDResecuenciacionPedidosService;

/**
 * @author PEJCAIRAMPOMA
 *
 */
@Service("spusicc.pedido.procesoPEDResecuenciacionPedidosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPEDResecuenciacionPedidosServiceImpl extends BaseService implements ProcesoPEDResecuenciacionPedidosService{
	
	@Resource(name="spusicc.procesoPEDResecuenciacionPedidosDAO")
	private ProcesoPEDResecuenciacionPedidosDAO procesoPEDResecuenciacionPedidosDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDResecuenciacionPedidosService#executeResecuenciacionPedidos(java.util.Map)
	 */
	public void executeResecuenciacionPedidos(Map params) {
		procesoPEDResecuenciacionPedidosDAO.executeResecuenciacionPedidos(params);
	}

	

}