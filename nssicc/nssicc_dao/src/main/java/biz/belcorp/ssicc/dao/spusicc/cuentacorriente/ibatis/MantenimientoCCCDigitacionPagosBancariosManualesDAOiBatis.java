/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCDigitacionPagosBancariosManualesDAO;

/**
 * @author pejflorencio
 *
 */
@Repository("spusicc.mantenimientoCCCDigitacionPagosBancariosManualesDAO")
public class MantenimientoCCCDigitacionPagosBancariosManualesDAOiBatis extends	BaseDAOiBatis implements MantenimientoCCCDigitacionPagosBancariosManualesDAO {
						
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCDigitacionPagosBancariosManualesDAO#generarPagoBancarioManual(java.util.Map)
	 */
	public void generarPagoBancarioManual(Map criteria){
		getSqlMapClientTemplate().update(
				"spusicc.cuentacorriente.procesosCCCSQL.executeGenerarPagoBancarioManual", criteria);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCDigitacionPagosBancariosManualesDAO#registrarLoteBancarioManual(java.util.Map)
	 */
	public void registrarLoteBancario(Map criteria){
		getSqlMapClientTemplate().update(
				"spusicc.cuentacorriente.procesosCCCSQL.executeRegistrarLoteBancario", criteria);
	}
}
