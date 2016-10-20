package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPREActualizarTipoCambioDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPREActualizarTipoCambioService;

/**
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 *
 */
@Service("spusicc.procesoPREActualizarTipoCambioService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPREActualizarTipoCambioServiceImpl extends BaseService implements ProcesoPREActualizarTipoCambioService{

    @Resource(name="spusicc.procesoPREActualizarTipoCambioDAO")
	private ProcesoPREActualizarTipoCambioDAO procesoPREActualizarTipoCambioDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPREActualizarTipoCambioService#updateActualizarTipoCambio(java.util.Map)
	 */
	@Override
	public void updateTipoCambio(Map<String, Object> params) {
		procesoPREActualizarTipoCambioDAO.updateTipoCambio(params);
		procesoPREActualizarTipoCambioDAO.updateTipoCambioConcursos(params);
		procesoPREActualizarTipoCambioDAO.updateTipoCambioNX(params);
	}

   
}
