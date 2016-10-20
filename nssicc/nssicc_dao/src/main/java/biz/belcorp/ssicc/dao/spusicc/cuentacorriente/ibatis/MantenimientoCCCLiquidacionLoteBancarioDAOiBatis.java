package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCLiquidacionLoteBancarioDAO;
/**
  * <p>
 * <a href="MantenimientoCCCLiquidacionLoteBancarioDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz"></a>
 */
@Repository("spusicc.mantenimientoCCCLiquidacionLoteBancarioDAO")
public class MantenimientoCCCLiquidacionLoteBancarioDAOiBatis extends BaseDAOiBatis implements MantenimientoCCCLiquidacionLoteBancarioDAO {

					
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCLiquidacionLoteBancarioDAO#getLotesBancariosList(java.util.Map)
	 */
	public List  getLotesBancariosList(Map datos) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.cuentacorriente.procesosCCCSQL.getLotesBancariosbyFilter", datos);
	}
								
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCLiquidacionLoteBancarioDAO#executeLiquidarLoteBancario(java.util.Map)
	 */
	public void executeLiquidarLoteBancario(Map datos) {
        getSqlMapClientTemplate().update(
                "spusicc.cuentacorriente.procesosCCCSQL.executeLiquidacionLoteBancario", datos);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCLiquidacionLoteBancarioDAO#executeEliminarLoteBancario(java.lang.String)
	 */
	public void executeEliminarLoteBancario(Map datos) {
        getSqlMapClientTemplate().update(
                "spusicc.cuentacorriente.procesosCCCSQL.executeEliminarLoteBancario", datos);
	}
}
