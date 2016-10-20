package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEConsultarFacturasCajasDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.ConsultaFacturasCajas;

/**
 * @author Nicols Lpez
 *
 */
@Repository("spusicc.mantenimientoAPEConsultarFacturasCajasDAO")
public class MantenimientoAPEConsultarFacturasCajasDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEConsultarFacturasCajasDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConsultarFacturasCajasDAO#getConsultaFacturasCajasList(java.util.Map)
	 */
	public List getConsultaFacturasCajasList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getConsultaFacturasCajasList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConsultarFacturasCajasDAO#getProductosFacturasCajasObject(java.util.Map)
	 */
	public ConsultaFacturasCajas getProductosFacturasCajasObject(Map criteria){
		return (ConsultaFacturasCajas)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getProductosFacturasCajasObject", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConsultarFacturasCajasDAO#getProductosFacturasCajasList(java.util.Map)
	 */
	public List getProductosFacturasCajasList(Map criteria){
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getProductosFacturasCajasList", criteria);
	}

}