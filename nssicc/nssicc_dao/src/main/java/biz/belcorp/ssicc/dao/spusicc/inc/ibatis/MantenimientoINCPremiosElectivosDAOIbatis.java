package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCPremiosElectivosDAO;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
@Repository("spusicc.mantenimientoINCPremiosElectivosDAO")
public class MantenimientoINCPremiosElectivosDAOIbatis extends BaseDAOiBatis implements
													MantenimientoINCPremiosElectivosDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPremiosElectivosDAO#getListParametrosConcursosElectivos()
	 */
	public List getListParametrosConcursosElectivos() {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getListParametrosConcursosElectivos");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPremiosElectivosDAO#executeValidacionesPremiosElectivos(java.util.Map)
	 */
	public void executeValidacionesPremiosElectivos(Map map) {
		getSqlMapClientTemplate().
				update("spusicc.incentivos.MantenimientoINCSQL.executeValidacionesPremiosElectivos",
						map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPremiosElectivosDAO#getPremiosElectivos()
	 */
	public List getPremiosElectivos() {
		return getSqlMapClientTemplate().
		              queryForList("spusicc.incentivos.MantenimientoINCSQL.getPremiosElectivos");		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPremiosElectivosDAO#insertPremioElectivo(java.util.Map)
	 */
	public void insertPremioElectivo(Map map) {
		int numUnidades=Integer.parseInt(String.valueOf(map.get("numUnidades")));
		for(int i=0;i<numUnidades;i++){		
			getSqlMapClientTemplate().
			insert("spusicc.incentivos.MantenimientoINCSQL.insertPremioElectivo",
					map);
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPremiosElectivosDAO#insertPremioElectivoInvalido(java.util.Map)
	 */
	public void insertPremioElectivoInvalido(Map map) {
		
			getSqlMapClientTemplate().
			insert("spusicc.incentivos.MantenimientoINCSQL.insertPremioElectivoInvalido",
					map);
		
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPremiosElectivosDAO#deletePremiosElectivos(java.util.Map)
	 */
	public void deletePremiosElectivos(Map map) {
		getSqlMapClientTemplate().
		delete("spusicc.incentivos.MantenimientoINCSQL.deletePremiosElectivos",
				map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPremiosElectivosDAO#getListCodigoVentas(java.util.Map)
	 */
	public List<Map> getListCodigoVentas(Map<String, String> map) {
		return getSqlMapClientTemplate().
			queryForList("spusicc.incentivos.MantenimientoINCSQL.getListCodigoVentas",map);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPremiosElectivosDAO#getLongitudCodVentaFicticio(java.lang.String)
	 */
	public String getLongitudCodVentaFicticio(String codigoPais) {
		return (String)getSqlMapClientTemplate().
		queryForObject("spusicc.incentivos.MantenimientoINCSQL.getLongitudCodVentaFicticio",codigoPais);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPremiosElectivosDAO#getListParametrosConcursosElectivosDiferido()
	 */
	public List getListParametrosConcursosElectivosDiferido() {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getListParametrosConcursosElectivosDiferido");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPremiosElectivosDAO#deleteCargaPremiosElectivos(java.util.Map)
	 */
	public void deleteCargaPremiosElectivos(Map map) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteCargaPremiosElectivos", map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPremiosElectivosDAO#insertCargaPremiosElectivos(java.util.Map)
	 */
	public void insertCargaPremiosElectivos(Map map) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertCargaPremiosElectivos",	map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPremiosElectivosDAO#executeCargaPremiosElectivos(java.util.Map)
	 */
	public void executeCargaPremiosElectivos(Map map) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.executeCargaPremiosElectivos",	map);
	}	
}
