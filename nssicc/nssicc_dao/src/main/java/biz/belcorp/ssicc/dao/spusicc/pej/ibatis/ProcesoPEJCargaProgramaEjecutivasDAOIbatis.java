package biz.belcorp.ssicc.dao.spusicc.pej.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pej.ProcesoPEJCargaProgramaEjecutivasDAO;

/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *
 */

@Repository("spusicc.procesoPEJCargaProgramaEjecutivasDAO")
public class ProcesoPEJCargaProgramaEjecutivasDAOIbatis extends BaseDAOiBatis implements ProcesoPEJCargaProgramaEjecutivasDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJCargaProgramaEjecutivasDAO#getTipoCarga()
	 */
	public List getTipoCarga() {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosPEJSQL.getTipoCarga", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJCargaProgramaEjecutivasDAO#getNumeroCarga()
	 */
	public String getNumeroCarga() {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getNumeroCarga");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJCargaProgramaEjecutivasDAO#insertCargaProgramaEjecutivas(java.util.Map)
	 */
	public void insertCargaProgramaEjecutivas(Map params) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosPEJSQL.insertCargaProgramaEjecutivas", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJCargaProgramaEjecutivasDAO#executeValidarCargaProgramaEjecutivas(java.util.Map)
	 */
	public void executeValidarCargaProgramaEjecutivas(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.executeValidarCargaProgramaEjecutivas", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJCargaProgramaEjecutivasDAO#getCargaProgramaEjecutivasList(java.util.Map)
	 */
	public List getCargaProgramaEjecutivasList(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosPEJSQL.getCargaProgramaEjecutivasList", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJCargaProgramaEjecutivasDAO#executeActualizarCargaProgramaEjecutivas(java.util.Map)
	 */
	public void executeActualizarCargaProgramaEjecutivas(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.executeActualizarCargaProgramaEjecutivas", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJCargaProgramaEjecutivasDAO#getExisteClienteCargaProgramaEjecutivas(java.lang.String)
	 */
	public String getExisteClienteCargaProgramaEjecutivas(String codigoCliente) {
		String codigoExiste = (String) getSqlMapClientTemplate(). queryForObject("spusicc.ProcesosPEJSQL.getExisteClienteCargaProgramaEjecutivas", codigoCliente);
		return codigoExiste;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJCargaProgramaEjecutivasDAO#deleteCargaProgramaEjecutivas(java.lang.String)
	 */
	public void deleteCargaProgramaEjecutivas(String codigoUsuario) {
		getSqlMapClientTemplate().delete("spusicc.ProcesosPEJSQL.deleteCargaProgramaEjecutivas", codigoUsuario);
	}
	
}