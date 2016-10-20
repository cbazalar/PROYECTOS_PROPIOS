package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDNivelesRiesgoChequearDAO;

/**
 * Clase implementadora del patrn DAO
 * 
 * <p>
 * <a href="MantenimientoPEDNivelesRiesgoChequearDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 * 
 */
@Repository("spusicc.pedidos.mantenimientoPEDNivelesRiesgoChequearDAO")
public class MantenimientoPEDNivelesRiesgoChequearDAOiBatis extends BaseDAOiBatis implements MantenimientoPEDNivelesRiesgoChequearDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDNivelesRiesgoChequearDAO#deletePEDNivelesRiesgoChequear(java.util.Map)
	 */
	public void deletePEDNivelesRiesgoChequear(Map map) {
		log.info("Entro a MantenimientoPEDNivelesRiesgoChequearDAOiBatis - deletePEDNivelesRiesgoChequear(Map)");
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.deletePEDNivelesRiesgoChequear",map);
		log.info("Salio a MantenimientoPEDNivelesRiesgoChequearDAOiBatis - deletePEDNivelesRiesgoChequear(Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDNivelesRiesgoChequearDAO#getListaPEDNivelesRiesgoChequear()
	 */
	public List getListaPEDNivelesRiesgoChequear() {
		log.info("Entro a MantenimientoPEDNivelesRiesgoChequearDAOiBatis - getListaPEDNivelesRiesgoChequear()");
		List lista = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getListaPEDNivelesRiesgoChequear");
		log.info("Salio a MantenimientoPEDNivelesRiesgoChequearDAOiBatis - getListaPEDNivelesRiesgoChequear() - Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDNivelesRiesgoChequearDAO#getNivelRiesgo()
	 */
	public List getNivelRiesgo() {
		log.info("Entro a MantenimientoPEDNivelesRiesgoChequearDAOiBatis - getNivelRiesgo()");
		List lista = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getNivelRiesgo");
		log.info("Salio a MantenimientoPEDNivelesRiesgoChequearDAOiBatis - getNivelRiesgo() - Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDNivelesRiesgoChequearDAO#insertPEDNivelesRiesgoChequear(java.util.Map)
	 */
	public void insertPEDNivelesRiesgoChequear(Map params) {
		log.info("Entro a MantenimientoPEDNivelesRiesgoChequearDAOiBatis - insertPEDNivelesRiesgoChequear(Map)");
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertPEDNivelesRiesgoChequear",params);
		log.info("Salio a MantenimientoPEDNivelesRiesgoChequearDAOiBatis - insertPEDNivelesRiesgoChequear(Map)");
	}

}