package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.reclamos.ProcesoRECCierreBRDAO;

@Repository("spusicc.procesoRECCierreBRDAO")
public class ProcesoRECCierreBRDAOIbatis extends BaseDAOiBatis implements 
			ProcesoRECCierreBRDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECCierreBRDAO#getResultadoBRList(java.util.Map)
	 */
	public List getResultadoBRList() {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getResultadoBRList");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECCierreBRDAO#executeValidaRelacionBoletaRecojo(java.util.Map)
	 */
	public Map executeValidaRelacionBoletaRecojo(Map criteria) {
		return (Map)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.executeValidaRelacionBoletaRecojo",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECCierreBRDAO#getListaBoletasBR(java.util.Map)
	 */
	public List getListaBoletasBRList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getListaBoletasBRList",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECCierreBRDAO#getListaBoletasDetalleBRList(java.util.Map)
	 */
	public List getListaBoletasDetalleBRList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getListaBoletasDetalleBRList",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECCierreBRDAO#insertProcesoBoletaTemporal(java.util.Map)
	 */
	public void insertProcesoBoletaTemporal(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertProcesoBoletaTemporal",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECCierreBRDAO#getProcesoBoletaTemporalList(java.util.Map)
	 */
	public List getProcesoBoletaTemporalList() {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getProcesoBoletaTemporalList", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECCierreBRDAO#deleteBoletaTemporal(java.util.Map)
	 */
	public void removeBoletaTemporal(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.reclamos.ReclamosSQL.removeBoletaTemporal",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECCierreBRDAO#executeValidarProcesoBoleta(java.util.Map)
	 */
	public void executeValidarProcesoBoleta(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.executeValidarProcesoBoleta",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECCierreBRDAO#getNumeroLoteBoletasRecojo()
	 */
	public String getNumeroLoteBoletasRecojo() {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getNumeroLoteBoletasRecojo");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECCierreBRDAO#deleteTablaTemporal()
	 */
	public void deleteTablaTemporal() {
		getSqlMapClientTemplate().delete("spusicc.reclamos.ReclamosSQL.deleteTablaTemporal");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECCierreBRDAO#getProcesoBoletaTemporalListIncorrectas()
	 */
	public Integer getProcesoBoletaTemporalListIncorrectas() {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getProcesoBoletaTemporalListIncorrectas");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECCierreBRDAO#insertProcesoBoletaRecojoTemporal(java.util.Map)
	 */
	public void insertProcesoBoletaRecojoTemporal(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertProcesoBoletaRecojoTemporal",criteria);
	}
	
}
