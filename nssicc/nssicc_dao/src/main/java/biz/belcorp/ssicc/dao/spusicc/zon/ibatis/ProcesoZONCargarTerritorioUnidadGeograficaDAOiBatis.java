/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.zon.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.zon.ProcesoZONCargarTerritorioUnidadGeograficaDAO;

/**
 * @author Sigcomt
 *
 */
@Repository("spusicc.procesoZONCargarTerritorioUnidadGeograficaDAO")
public class ProcesoZONCargarTerritorioUnidadGeograficaDAOiBatis extends BaseDAOiBatis implements ProcesoZONCargarTerritorioUnidadGeograficaDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONCargarTerritorioUnidadGeograficaDAO#obtenerPathUpload(java.lang.String)
	 */
	public String obtenerPathUpload(String codigoPais) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.zon.ProcesosZONSQL.getPathUpload", codigoPais);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONCargarTerritorioUnidadGeograficaDAO#deleteCargarTerritorioUnidadGeografica(java.lang.String)
	 */
	public void deleteCargarTerritorioUnidadGeografica(String codigoUsuario) {
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.deleteCargarTerritorioUnidadGeografica", codigoUsuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONCargarTerritorioUnidadGeograficaDAO#insertCargarTerritorioUnidadGeografica(java.util.Map)
	 */
	public void insertCargarTerritorioUnidadGeografica(Map params) {
		getSqlMapClientTemplate().insert("spusicc.zon.ProcesosZONSQL.insertCargarTerritorioUnidadGeografica", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONCargarTerritorioUnidadGeograficaDAO#getCargarTerritorioUnidadGeografica(java.lang.String)
	 */
	public List getCargarTerritorioUnidadGeografica(String codigoUsuario) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.ProcesosZONSQL.getCargarTerritorioUnidadGeografica", codigoUsuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONCargarTerritorioUnidadGeograficaDAO#executeProcesarCargaTerritorioUnidadGeografica(java.lang.String)
	 */
	public void executeProcesarCargaTerritorioUnidadGeografica(String codigoUsuario) {
		Map params = new HashMap();
		params.put("codigoUsuario", codigoUsuario);
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.executeProcesarCargaTerritorioUnidadGeografica", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONCargarTerritorioUnidadGeograficaDAO#executeValidarCargaTerritorioUnidadGeografica(java.lang.String)
	 */
	public void executeValidarCargaTerritorioUnidadGeografica(String codigoUsuario) {
		Map params = new HashMap();
		params.put("codigoUsuario", codigoUsuario);
		
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.executeValidarCargaTerritorioUnidadGeografica", params);
	}

}
