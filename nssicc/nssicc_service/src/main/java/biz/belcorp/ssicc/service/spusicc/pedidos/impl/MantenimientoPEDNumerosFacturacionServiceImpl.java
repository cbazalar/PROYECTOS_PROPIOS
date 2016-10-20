/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDNumerosFacturacionDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDNumerosFacturacionService;

/**
 * @author sguerra
 *
 */
@Service("spusicc.mantenimientoPEDNumerosFacturacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPEDNumerosFacturacionServiceImpl extends BaseService implements MantenimientoPEDNumerosFacturacionService {

	@Resource(name="spusicc.mantenimientoPEDNumerosFacturacionDAO")
	MantenimientoPEDNumerosFacturacionDAO mantenimientoPEDNumerosFacturacionDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDNumerosFacturacionService#getSociedadList()
	 */
	public List getSociedadList() {
		return mantenimientoPEDNumerosFacturacionDAO.getSociedadList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDNumerosFacturacionService#getTipoDocumentoList()
	 */
	public List getTipoDocumentoList() {
		return mantenimientoPEDNumerosFacturacionDAO.getTipoDocumentoList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDNumerosFacturacionService#getNumerosFacturacionList(java.util.Map)
	 */
	public List getNumerosFacturacionList(Map criteria) {
		return mantenimientoPEDNumerosFacturacionDAO.getNumerosFacturacionList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDNumerosFacturacionService#deleteNumerosFacturacion(java.util.Map)
	 */
	public void deleteNumerosFacturacion(Map criteria) {
		mantenimientoPEDNumerosFacturacionDAO.insertHistoricoNumerosFacturacion(criteria);
		mantenimientoPEDNumerosFacturacionDAO.deleteNumerosFacturacion(criteria);
	}

}
