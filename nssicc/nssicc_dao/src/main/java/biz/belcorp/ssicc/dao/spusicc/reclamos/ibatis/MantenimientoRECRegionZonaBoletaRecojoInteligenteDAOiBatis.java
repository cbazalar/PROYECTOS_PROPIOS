/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECRegionZonaBoletaRecojoInteligenteDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.RegionZonaBoletaRecojoInteligente;


/**
 * @author Gonzalo Huertas
 *
 */		
@Repository("spusicc.mantenimientoRECRegionZonaBoletaRecojoInteligenteDAO")
public class MantenimientoRECRegionZonaBoletaRecojoInteligenteDAOiBatis extends	BaseDAOiBatis implements MantenimientoRECRegionZonaBoletaRecojoInteligenteDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECRegionZonaBoletaRecojoInteligenteDAO#getRegionZonaBoletaRecojoInteligente(java.util.Map)
	 */
	public List getRegionZonaBoletaRecojoInteligenteList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getRegionZonaBoletaRecojoInteligenteList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECRegionZonaBoletaRecojoInteligenteDAO#insertRegionZonaBoletaRecojoInteligente(biz.belcorp.ssicc.spusicc.reclamos.model.RegionZonaBoletaRecojoInteligente)
	 */
	public void insertRegionZonaBoletaRecojoInteligente(RegionZonaBoletaRecojoInteligente motivo, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertRegionZonaBoletaRecojoInteligente", motivo);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECRegionZonaBoletaRecojoInteligenteDAO#deleteRegionZonaBoletaRecojoInteligente(java.lang.String)
	 */
	public void deleteRegionZonaBoletaRecojoInteligente(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.reclamos.ReclamosSQL.deleteRegionZonaBoletaRecojoInteligente", criteria);
		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECRegionZonaBoletaRecojoInteligenteDAO#getExisteRegionZonaBoletaRecojoInteligenteByCriteria(biz.belcorp.ssicc.spusicc.reclamos.model.RegionZonaBoletaRecojoInteligente)
	 */
	public String getExisteRegionZonaBoletaRecojoInteligenteByCriteria(RegionZonaBoletaRecojoInteligente regionZonaBoletaRecojoInteligente) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getExisteRegionZonaBoletaRecojoInteligenteByCriteria", regionZonaBoletaRecojoInteligente);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECRegionZonaBoletaRecojoInteligenteDAO#updateRegionZonaBoletaRecojoInteligente(biz.belcorp.ssicc.spusicc.reclamos.model.RegionZonaBoletaRecojoInteligente)
	 */
	public void updateRegionZonaBoletaRecojoInteligente(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.updateRegionZonaBoletaRecojoInteligente", criteria);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECRegionZonaBoletaRecojoInteligenteDAO#getRegionByZonaList(java.util.Map)
	 */
	public List getRegionByZonaList(RegionZonaBoletaRecojoInteligente regionZonaBoletaRecojoInteligente) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getRegionByZonaList", regionZonaBoletaRecojoInteligente);
	}
}
