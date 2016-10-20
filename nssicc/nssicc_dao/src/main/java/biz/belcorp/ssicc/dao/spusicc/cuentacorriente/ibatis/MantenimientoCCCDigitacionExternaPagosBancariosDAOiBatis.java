/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCDigitacionExternaPagosBancariosDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.PagoBancarioExterno;

/**
 * @author pejflorencio
 *
 */
@Repository("spusicc.mantenimientoCCCDigitacionExternaPagosBancariosDAO")
public class MantenimientoCCCDigitacionExternaPagosBancariosDAOiBatis extends	BaseDAOiBatis implements MantenimientoCCCDigitacionExternaPagosBancariosDAO {
						
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCDigitacionPagosBancariosManualesDAO#generarPagoBancarioManual(java.util.Map)
	 */
	public void insertarPagoBancarioExterno(PagoBancarioExterno estructura){
		getSqlMapClientTemplate().update(
				"spusicc.cuentacorriente.procesosCCCSQL.insertarPagoBancarioExterno", estructura);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCDigitacionPagosBancariosManualesDAO#registrarLoteBancarioManual(java.util.Map)
	 */
	public void generarLoteBancarioExterno(Map criteria){
		getSqlMapClientTemplate().update(
				"spusicc.cuentacorriente.procesosCCCSQL.generarLoteBancarioExterno", criteria);
	}
}
