package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.ConsultaOCRRecepcionPedidosDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ConsultaOCRRecepcionPedidosService;

@Service("spusicc.pedidos.consultaOCRRecepcionPedidosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ConsultaOCRRecepcionPedidosServiceImpl extends	BaseService implements ConsultaOCRRecepcionPedidosService {

	@Resource(name="spusicc.pedidos.consultaOCRRecepcionPedidosDAO")
	ConsultaOCRRecepcionPedidosDAO consultaOCRRecepcionPedidosDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ConsultaOCRRecepcionPedidosService#getListaRecepcionPedidosActual(java.util.Map)
	 */
	public List getListaRecepcionPedidosActual(Map criteria){
		return consultaOCRRecepcionPedidosDAO.getListaRecepcionPedidosActual(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ConsultaOCRRecepcionPedidosService#getListaRecepcionPedidosHistorico(java.util.Map)
	 */
	public List getListaRecepcionPedidosHistorico(Map criteria){
		return consultaOCRRecepcionPedidosDAO.getListaRecepcionPedidosHistorico(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ConsultaOCRRecepcionPedidosService#getDetalleRecepcionPedidosHistorico(java.util.Map)
	 */
	public List getDetalleRecepcionPedidosHistorico(Map criteria){
		return consultaOCRRecepcionPedidosDAO.getDetalleRecepcionPedidosHistorico(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ConsultaOCRRecepcionPedidosService#getDetalleRecepcionPedidosActual(java.util.Map)
	 */
	public List getDetalleRecepcionPedidosActual(Map criteria){
		return consultaOCRRecepcionPedidosDAO.getDetalleRecepcionPedidosActual(criteria);
	}
}
