package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOCambioTipoOrdenPedidosCargadosDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOCambioTipoOrdenPedidosCargadosService;

/**
 * @author peextjrios
 */
@Service("spusicc.procesoSTOCambioTipoOrdenPedidosCargadosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSTOCambioTipoOrdenPedidosCargadosServiceImpl extends BaseService implements ProcesoSTOCambioTipoOrdenPedidosCargadosService{
	
	@Resource(name="spusicc.procesoSTOCambioTipoOrdenPedidosCargadosDAO")
	private ProcesoSTOCambioTipoOrdenPedidosCargadosDAO procesoSTOCambioTipoOrdenPedidosCargadosDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOCambioTipoOrdenPedidosCargadosService#getClientesTipoOrdenList(java.util.Map)
	 */
	public List getClientesTipoOrdenList(Map criteria) {
		return procesoSTOCambioTipoOrdenPedidosCargadosDAO.getClientesTipoOrdenList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOCambioTipoOrdenPedidosCargadosService#updateTipoOrdenPedidosCargados(java.util.Map)
	 */
	public void updateTipoOrdenPedidosCargados(Map criteria) {
		procesoSTOCambioTipoOrdenPedidosCargadosDAO.updateTipoOrdenPedidosCargados(criteria);
	}
}