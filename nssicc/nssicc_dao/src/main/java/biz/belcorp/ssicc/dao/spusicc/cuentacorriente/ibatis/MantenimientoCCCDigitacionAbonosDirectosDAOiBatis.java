/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCDigitacionAbonosDirectosDAO;

/**
 * @author pejflorencio
 *
 */
@Repository("spusicc.mantenimientoCCCDigitacionAbonosDirectosDAO")
public class MantenimientoCCCDigitacionAbonosDirectosDAOiBatis extends	BaseDAOiBatis implements MantenimientoCCCDigitacionAbonosDirectosDAO {
	
	
	/* (non-Javadoc)
	 * Obtine los Tipos de Cargos y Abonos Directos
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCDigitacionCADDAO#getTiposCargoAbonoDirectos(java.util.Map)
	 */
	public List getTiposAbonosDirectosDigitables(){
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getTiposAbonosDirectosDigitables");
	}
	
	
	/* (non-Javadoc)
	 * Inserta un Cargo y Abono Directo
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCDigitacionCADDAO#insertCargoAbonoDirectoDigitado(java.util.Map)
	 */
	public void insertCargoAbonoDirectoDigitado(Map criteria){
		getSqlMapClientTemplate().update(
				"spusicc.cuentacorriente.procesosCCCSQL.insertCargoAbonoDirectoDigitado", criteria);
	}
		
}
