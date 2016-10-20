/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECLiquidacionBoletaRecojoDAO;


/**
 * @author peextdoliva
 *
 */
@Repository("spusicc.mantenimientoRECLiquidacionBoletaRecojoDAO")
public class MantenimientoRECLiquidacionBoletaRecojoDAOIbatis extends	BaseDAOiBatis implements MantenimientoRECLiquidacionBoletaRecojoDAO {
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECLiquidacionBoletaRecojoDAO#getEstados()
	 */
	public List getEstados(){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getEstados", "");
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECLiquidacionBoletaRecojoDAO#getCabecerasBoletasReclamo(java.util.Map)
	 */
	public List getCabecerasBoletasReclamo(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getCabecerasBoletasReclamo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECLiquidacionBoletaRecojoDAO#getDetallesBoletasReclamo(java.util.Map)
	 */
	public List getDetallesBoletasReclamo(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getDetallesBoletasReclamo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECLiquidacionBoletaRecojoDAO#getTotalBR(java.util.Map)
	 */
	public String getTotalBR(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getTotalBR", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECLiquidacionBoletaRecojoDAO#getPendientesBR(java.util.Map)
	 */
	public String getPendientesBR(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getPendientesBR", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECLiquidacionBoletaRecojoDAO#getAprobadasBR(java.util.Map)
	 */
	public String getAprobadasBR(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getAprobadasBR", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECLiquidacionBoletaRecojoDAO#getRechazadasBR(java.util.Map)
	 */
	public String getRechazadasBR(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getRechazadasBR", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECLiquidacionBoletaRecojoDAO#getTotalCargosBR(java.util.Map)
	 */
	public String getTotalCargosBR(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getTotalCargosBR", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECLiquidacionBoletaRecojoDAO#getTotalAbonosBR(java.util.Map)
	 */
	public String getTotalAbonosBR(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getTotalAbonosBR", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECLiquidacionBoletaRecojoDAO#aprobarReclamoDigitados(java.util.Map)
	 */
	public void aprobarReclamoDigitados(Map params){
		getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.aprobarReclamoDigitados",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECLiquidacionBoletaRecojoDAO#updateRechazarReclamoDigitados(java.lang.String)
	 */
	public void updateRechazarReclamoDigitados(Map param){
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.updateRechazarReclamoDigitados", param);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECLiquidacionBoletaRecojoDAO#updateRecalcularBoletaRecojo(java.util.Map)
	 */
	public void updateRecalcularBoletaRecojo(Map criteria) {
		log.info("Entro a MantenimientoRECLiquidacionBoletaRecojoDAOIbatis - updateRecalcularBoletaRecojo");
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.updateRecalcularBoletaRecojo", criteria);
		log.info("Salio a MantenimientoRECLiquidacionBoletaRecojoDAOIbatis - updateRecalcularBoletaRecojo");
	}
	
}
