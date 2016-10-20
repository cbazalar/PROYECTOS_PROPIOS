/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCDigitacionCADDAO;

/**
 * @author pejflorencio
 *
 */
@Repository("spusicc.mantenimientoCCCDigitacionCADDAO")
public class MantenimientoCCCDigitacionCADDAOiBatis extends	BaseDAOiBatis implements MantenimientoCCCDigitacionCADDAO {
	
	
	/* (non-Javadoc)
	 * Obtine los Tipos de Cargos y Abonos Directos
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCDigitacionCADDAO#getTiposCargoAbonoDirectos(java.util.Map)
	 */
	public List getTiposCargoAbonoDirectos(){
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getTiposCargoAbonoDirectos");
	}
	
	
	/* (non-Javadoc)
	 * Inserta un Cargo y Abono Directo
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCDigitacionCADDAO#insertCargoAbonoDirectoDigitado(java.util.Map)
	 */
	public void insertCargoAbonoDirectoDigitado(Map criteria){
		getSqlMapClientTemplate().update(
				"spusicc.cuentacorriente.procesosCCCSQL.insertCargoAbonoDirectoDigitado", criteria);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCDigitacionCADDAO#getIndicadorParametro(java.util.Map)
	 */
	public String getIndicadorParametro(Map datos) {
		return (String) getSqlMapClientTemplate().
		queryForObject("spusicc.cuentacorriente.procesosCCCSQL.getIndicadorParametro", datos);		
	}
		
}
