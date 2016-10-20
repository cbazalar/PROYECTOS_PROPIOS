package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.ProcesoAPEEvaluarCargaDAO;

/**
 * @author Jose Luis Rodriguez
 */
@Repository("spusicc.procesoAPEEvaluarCargaDAO")
public class ProcesoAPEEvaluarCargaDAOiBatis extends BaseDAOiBatis implements ProcesoAPEEvaluarCargaDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ProcesoAPEEvaluarCargaDAO#getNumeroZonaSubLinea(java.util.Map)
	 */
	public int getNumeroZonaSubLinea(Map criteria) {
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getNumeroZonaSubLinea", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ProcesoAPEEvaluarCargaDAO#getMapaZonaCabecera(java.util.Map)
	 */
	public String getMapaZonaCabecera(Map criteria) {
		return(String)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getMapaZonaCabecera", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ProcesoAPEEvaluarCargaDAO#getAsignacionProductoAnaquelCabecera(java.util.Map)
	 */
	public String getAsignacionProductoAnaquelCabecera(Map criteria) {
		return(String)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getAsignacionProductoAnaquelCabecera", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ProcesoAPEEvaluarCargaDAO#getTotalEstimadoSubLinea(java.util.Map)
	 */
	public int getTotalEstimadoSubLinea(Map criteria){
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getTotalEstimadoSubLinea", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ProcesoAPEEvaluarCargaDAO#getEvaluarCargaList(java.util.Map)
	 */
	public List getEvaluarCargaList(Map criteria) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getEvaluarCargaList", criteria);
	}
}