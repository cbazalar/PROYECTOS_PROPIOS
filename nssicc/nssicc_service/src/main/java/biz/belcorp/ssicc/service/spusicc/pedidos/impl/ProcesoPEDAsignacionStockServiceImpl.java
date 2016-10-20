package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDAsignacionStockDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDAsignacionStockService;

/**
 * @author Jose Cairampoma
 */
@Service("spusicc.pedido.procesoPEDAsignacionStockService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPEDAsignacionStockServiceImpl extends BaseService implements ProcesoPEDAsignacionStockService{
	
	@Resource(name="spusicc.pedidos.procesoPEDAsignacionStockDAO")
	private ProcesoPEDAsignacionStockDAO procesoPEDAsignacionStockDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDAsignacionStockService#executeAsignacionStock(java.util.Map)
	 */
	public void executeAsignacionStock(Map params) {
		procesoPEDAsignacionStockDAO.executeAsignacionStock(params);
	}

}