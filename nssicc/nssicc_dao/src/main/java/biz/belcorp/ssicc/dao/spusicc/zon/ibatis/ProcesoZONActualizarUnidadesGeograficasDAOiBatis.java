package biz.belcorp.ssicc.dao.spusicc.zon.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasDAO;
import biz.belcorp.ssicc.dao.spusicc.zon.model.EstructuraGeografica;

@Repository("spusicc.procesoZONActualizarUnidadesGeograficasDAO")
public class ProcesoZONActualizarUnidadesGeograficasDAOiBatis extends BaseDAOiBatis
	implements ProcesoZONActualizarUnidadesGeograficasDAO {

	public String obtenerPathUpload(String codigoPais) {
		return (String) getSqlMapClientTemplate().
					queryForObject("spusicc.zon.ProcesosZONSQL.getPathUpload", codigoPais);		
	}
	
	public void deleteTablasUnidadesGeograficas() {
        getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.deleteTablasUnidadesGeograficas", null);
	}
	
	public List getEstructurasGeopoliticas(String pais) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.ProcesosZONSQL.getEstructurasGeopoliticas",
				pais);
	}
	
	public void insertEstructuraGeografica(EstructuraGeografica estructuraGeografica) {
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.insertEstructuraGeografica", estructuraGeografica);		
	}

	public void executeValidarEstructurasGeograficas(Map criteria) {
	    getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.executeValidarEstructurasGeograficas", criteria);
	}

	public void executeValidarDatosGeograficos(Map criteria) {
        getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.executeValidarDatosGeograficos", criteria);
	}

	public void executeActualizarUnidadesGeograficas(Map criteria) {
        getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.executeActualizarUnidadesGeograficas", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesGeograficasDAO#getSubgerencias()
	 */
	public List getSubgerencias() {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.ProcesosZONSQL.getSubgerencias", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesGeograficasDAO#getTerritoriosByCriteria(java.util.Map)
	 */
	public List getTerritoriosByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.ProcesosZONSQL.getTerritoriosByCriteria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesGeograficasDAO#getUbigeosByCriteria(java.util.Map)
	 */
	public List getUbigeosByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.ProcesosZONSQL.getUbigeosByCriteria", criteria);
	}
}
