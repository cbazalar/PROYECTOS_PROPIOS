package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionFaltanteDAO;

import com.ibatis.sqlmap.client.SqlMapExecutor;
/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */


@Repository("spusicc.mantenimientoINCConfiguracionFaltanteDAO")
public class MantenimientoINCConfiguracionFaltanteDAOIbatis extends BaseDAOiBatis implements
	MantenimientoINCConfiguracionFaltanteDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionFaltanteDAO#getConcursosFaltante(java.util.Map)
	 */
	public List getConcursosFaltante(Map criteria) {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getConcursosFaltante", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionFaltanteDAO#getPremiosFaltante(java.util.Map)
	 */
	public List getPremiosFaltante(Map criteria) {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getPremiosFaltante", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionFaltanteDAO#getRegionesFaltante(java.util.Map)
	 */
	public List getRegionesFaltante(Map criteria) {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getRegionesFaltante", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionFaltanteDAO#getZonasFaltante(java.util.Map)
	 */
	public List getZonasFaltante(Map criteria) {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getZonasFaltante", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionFaltanteDAO#getFaltantes(java.util.Map)
	 */
	public List getFaltantes(Map criteria) {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getFaltantes", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionFaltanteDAO#updateFaltantes(java.util.List)
	 */
	public void updateFaltantes(final List list) throws Exception {
		try{	
			final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			
			public Object doInSqlMapClient(SqlMapExecutor executor)
				throws SQLException {
				executor.startBatch();
				Iterator listIterator = list.iterator();
				while (listIterator.hasNext()) {
					Map dataInsert = (Map) listIterator.next();
					logger.debug("dataInsewrt " + dataInsert);
					getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.updateFaltante", dataInsert);
				}
				int rowsaffected = executor.executeBatch();
				logger.debug("Inicio->" + timestamp.toString()
						+ "Fin-->"
						+ new Timestamp(System.currentTimeMillis()));
				System.out.println("rows afftected by updateFaltantes: " + rowsaffected);
				return null;
			}
		});
			
		}catch(Exception e){
			throw new Exception(e);
		}
	}

}
