/*
 * Created on 19/01/2007 04:35:40 PM
 * biz.belcorp.ssicc.spisicc.service.impl.ProcesoImpresionLaserServiceImpl
 */
package biz.belcorp.ssicc.service.spisicc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spisicc.ProcesoImpresionDAO;
import biz.belcorp.ssicc.dao.spisicc.model.ProcesoSpool;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO;
import biz.belcorp.ssicc.service.spisicc.ProcesoHiloPedidoZonaService;

/**
 * <p>
 * <a href="ProcesoImpresionLaserServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:ghuertas@sigcomt.com">Gonzalo Huertas </a>
 */
@Service("spisicc.ProcesoHiloPedidoZonaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoHiloPedidoZonaServiceImpl implements ProcesoHiloPedidoZonaService {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@Resource(name="spusicc.procesoPEDDAO")
	private ProcesoPEDDAO procesoPEDDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spisicc.ProcesoHiloPedidoZonaService#getListaZonasByCampanaFechaFacturacion()
	 */
	@Override
	public List getListaZonasByCampanaFechaFacturacion(Map criteria) {
		return procesoPEDDAO.getListaZonasByCampanaFechaFacturacion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spisicc.ProcesoHiloPedidoZonaService#getListaPedidosByZonasCampanaFechaFacturacion(java.util.Map)
	 */
	@Override
	public List getListaPedidosByZonasCampanaFechaFacturacion(Map criteria) {
		return procesoPEDDAO.getListaPedidosByZonasCampanaFechaFacturacion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spisicc.ProcesoHiloPedidoZonaService#executeCuadrarOfertasPedidoZonaMultihilo(java.util.Map)
	 */
	@Override
	public void executeCuadrarOfertasPedidoZonaMultihilo(Map criteria) {
		procesoPEDDAO.executeCuadrarOfertasPedidoZonaMultihilo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spisicc.ProcesoHiloPedidoZonaService#executeMontoMinimoPedidoZonaMultihilo(java.util.Map)
	 */
	@Override
	public void executeMontoMinimoPedidoZonaMultihilo(Map criteria) {
		procesoPEDDAO.executeMontoMinimoPedidoZonaMultihilo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spisicc.ProcesoHiloPedidoZonaService#executeMontoMaximoPedidoZonaMultihilo(java.util.Map)
	 */
	@Override
	public void executeMontoMaximoPedidoZonaMultihilo(Map criteria) {
		procesoPEDDAO.executeMontoMaximoPedidoZonaMultihilo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spisicc.ProcesoHiloPedidoZonaService#executeRecuperacionObligatoriaPedidoZonaMultihilo(java.util.Map)
	 */
	@Override
	public void executeRecuperacionObligatoriaPedidoZonaMultihilo(Map criteria) {
		procesoPEDDAO.executeRecuperacionObligatoriaPedidoZonaMultihilo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spisicc.ProcesoHiloPedidoZonaService#executeValidarAgregadosMavPedidoZonaMultihilo(java.util.Map)
	 */
	@Override
	public void executeValidarAgregadosMavPedidoZonaMultihilo(Map criteria) {
		procesoPEDDAO.executeValidarAgregadosMavPedidoZonaMultihilo(criteria);
	}
	
}
