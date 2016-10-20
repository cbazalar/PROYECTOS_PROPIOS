/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCDigitacionCargosDirectosDAO;

/**
 * @author pejflorencio
 *
 */
@Repository("spusicc.mantenimientoCCCDigitacionCargosDirectosDAO")
public class MantenimientoCCCDigitacionCargosDirectosDAOiBatis extends	BaseDAOiBatis implements MantenimientoCCCDigitacionCargosDirectosDAO {
	
	
	/* (non-Javadoc)
	 * Obtine los Tipos de Cargos y Abonos Directos
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCDigitacionCADDAO#getTiposCargoAbonoDirectos(java.util.Map)
	 */
	public List getTiposCargosDirectosDigitables(){
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getTiposCargosDirectosDigitables");
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
