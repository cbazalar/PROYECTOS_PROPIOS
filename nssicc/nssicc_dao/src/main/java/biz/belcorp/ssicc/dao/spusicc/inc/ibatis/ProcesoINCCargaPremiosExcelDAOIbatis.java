package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCargaPremiosExcelDAO;

/**
 * @author <a href="mailto:nlopez@csigcomt.com">Nicols Lpez</a>
 *
 */
@Repository("spusicc.procesoINCCargaPremiosExcelDAO")
public class ProcesoINCCargaPremiosExcelDAOIbatis extends BaseDAOiBatis implements ProcesoINCCargaPremiosExcelDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaPremiosExcelDAO#getListConcursoVigentes()
	 */
	public List getListConcursoCreadosVigentes() {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.ProcesoINCSQL.getListConcursoCreadosVigentes");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaPremiosExcelDAO#getValidaEntregaPremios(java.util.Map)
	 */
	public String getValidaEntregaPremios(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.incentivos.ProcesoINCSQL.getValidaEntregaPremios",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaPremiosExcelDAO#getObtenerSecTempCargaPremios()
	 */
	public String getObtenerSecTempCargaPremios(){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.incentivos.ProcesoINCSQL.getObtenerSecTempCargaPremios");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaPremiosExcelDAO#executeInsercionTempCargaPremios(java.util.Map)
	 */
	public void executeInsercionTempCargaPremios(Map criteria){
		this.getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeInsercionTempCargaPremios",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaPremiosExcelDAO#executeProcesoValidaCargaPremios(java.util.Map)
	 */
	public void executeProcesoValidaCargaPremios(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeProcesoValidaCargaPremios", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaPremiosExcelDAO#getListarErroresCargaPremios(java.util.Map)
	 */
	public List getListarErroresCargaPremios(Map criteria){
		return this.getSqlMapClientTemplate().queryForList("spusicc.incentivos.ProcesoINCSQL.getListarErroresCargaPremios", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaPremiosExcelDAO#getValidaExistePremios(java.util.Map)
	 */
	public String getValidaExistePremios(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.incentivos.ProcesoINCSQL.getValidaExistePremios", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaPremiosExcelDAO#executeProcesoCargaPremiosExcel(java.util.Map)
	 */
	public void executeProcesoCargaPremiosExcel(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeProcesoCargaPremiosExcel", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaPremiosExcelDAO#eliminarRegistrosTablaTempINCCargaPremios(java.util.Map)
	 */
	public void eliminarRegistrosTablaTempINCCargaPremios(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.ProcesoINCSQL.getEliminarRegTablaTempINCCargaPremios",criteria);
	}
	
}
