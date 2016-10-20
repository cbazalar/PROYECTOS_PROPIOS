/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cronograma.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cronograma.MantenimientoCRAGrupoZonaDAO;

/**  
 * <p>
 * <a href="MantenimientoCRAGrupoZonaDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:rramirez@belcorp.biz"> Rosalvina Ramirez Guardia </a>
 */
@Repository("spusicc.mantenimientoCRAGrupoZonaDAO")
public class MantenimientoCRAGrupoZonaDAOiBatis extends BaseDAOiBatis implements MantenimientoCRAGrupoZonaDAO {


	public List getGrupos(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cronograma.mantenimientoCRASQL.getGrupos",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAGrupoZonaDAO#getGrupoZonas(java.util.Map)
	 */
	public List getGrupoZonas(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cronograma.mantenimientoCRASQL.getGrupoZonas", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAGrupoZonaDAO#getZonasGrupoRegenerar(java.util.Map)
	 */
	public List getZonasGrupoRegenerar(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cronograma.mantenimientoCRASQL.getZonasGrupoRegenerar", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAGrupoZonaDAO#deleteGrupoZona(java.util.Map)
	 */
	public void deleteGrupoZona(Map criteria){
		getSqlMapClientTemplate().update("spusicc.cronograma.mantenimientoCRASQL.deleteGrupoZona", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAGrupoZonaDAO#getGrupoRegionNoAsignadas()
	 */
	public List getGrupoRegionNoAsignadas(){
		return getSqlMapClientTemplate().queryForList("spusicc.cronograma.mantenimientoCRASQL.getGrupoRegionNoAsignadas");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAGrupoZonaDAO#getZonaAsignadasGrupo(java.lang.String)
	 */
	public List getZonaAsignadasGrupo(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cronograma.mantenimientoCRASQL.getZonaAsignadasGrupo",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAGrupoZonaDAO#getGrupoZonaNoAsignadas(java.util.Map)
	 */
	public List getGrupoZonaNoAsignadas(Map params){
		return getSqlMapClientTemplate().queryForList("spusicc.cronograma.mantenimientoCRASQL.getGrupoZonaNoAsignadas",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAGrupoZonaDAO#insertNombreGrupoZona(java.util.Map)
	 */
	public void insertNombreGrupoZona(Map map){
		getSqlMapClientTemplate().insert("spusicc.cronograma.mantenimientoCRASQL.insertNombreGrupoZona", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAGrupoZonaDAO#updateNombreGrupoZona(java.util.Map)
	 */
	public void updateNombreGrupoZona(Map map){
		getSqlMapClientTemplate().update("spusicc.cronograma.mantenimientoCRASQL.updateNombreGrupoZona", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAGrupoZonaDAO#updateZonasAsignadas(java.util.Map)
	 */
	public void updateZonasAsignadas(Map map) {		
		
		String[] zonas = (String[])map.get("zonas");
		
		if ( zonas != null && zonas.length != 0){
			getSqlMapClientTemplate().delete("spusicc.cronograma.mantenimientoCRASQL.deleteDetalleGrupo", map);
			
			for (int i=0; i < zonas.length; i++){
				map.put("zona", zonas[i]);
				getSqlMapClientTemplate().insert("spusicc.cronograma.mantenimientoCRASQL.insertDetalleGrupo", map);
			}
			
		}
				
	}

}
