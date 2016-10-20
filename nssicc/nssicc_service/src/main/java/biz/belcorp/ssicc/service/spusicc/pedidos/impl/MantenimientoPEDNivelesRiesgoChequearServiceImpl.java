package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDNivelesRiesgoChequearDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDNivelesRiesgoChequearService;

@Service("spusicc.pedidos.mantenimientoPEDNivelesRiesgoChequearService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPEDNivelesRiesgoChequearServiceImpl extends BaseService implements MantenimientoPEDNivelesRiesgoChequearService{
	
	@Resource(name="spusicc.pedidos.mantenimientoPEDNivelesRiesgoChequearDAO")
	private MantenimientoPEDNivelesRiesgoChequearDAO mantenimientoPEDNivelesRiesgoChequearDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDNivelesRiesgoChequearService#deletePEDNivelesRiesgoChequear(java.util.Map)
	 */
	public void deletePEDNivelesRiesgoChequear(Map map) {
		log.info("Entro a MantenimientoPEDNivelesRiesgoChequearServiceImpl - deletePEDNivelesRiesgoChequear(Map)");
		mantenimientoPEDNivelesRiesgoChequearDAO.deletePEDNivelesRiesgoChequear(map);
		log.info("Salio a MantenimientoPEDNivelesRiesgoChequearServiceImpl - deletePEDNivelesRiesgoChequear(Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDNivelesRiesgoChequearService#getListaPEDNivelesRiesgoChequear()
	 */
	public List getListaPEDNivelesRiesgoChequear() {
		log.info("Entro a MantenimientoPEDNivelesRiesgoChequearServiceImpl - getListaPEDNivelesRiesgoChequear()");
		List lista = mantenimientoPEDNivelesRiesgoChequearDAO.getListaPEDNivelesRiesgoChequear();
		log.info("Salio a MantenimientoPEDNivelesRiesgoChequearServiceImpl - getListaPEDNivelesRiesgoChequear() - Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDNivelesRiesgoChequearService#getNivelRiesgo()
	 */
	public List getNivelRiesgo() {
		log.info("Entro a MantenimientoPEDNivelesRiesgoChequearServiceImpl - getNivelRiesgo()");
		List lista = mantenimientoPEDNivelesRiesgoChequearDAO.getNivelRiesgo();
		log.info("Salio a MantenimientoPEDNivelesRiesgoChequearServiceImpl - getNivelRiesgo() - Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDNivelesRiesgoChequearService#insertPEDNivelesRiesgoChequear(java.util.Map)
	 */
	public void insertPEDNivelesRiesgoChequear(Map params) {
		log.info("Entro a MantenimientoPEDNivelesRiesgoChequearServiceImpl - insertPEDNivelesRiesgoChequear(Map)");
		mantenimientoPEDNivelesRiesgoChequearDAO.insertPEDNivelesRiesgoChequear(params);
		log.info("Salio a MantenimientoPEDNivelesRiesgoChequearServiceImpl - insertPEDNivelesRiesgoChequear(Map)");
	}


}