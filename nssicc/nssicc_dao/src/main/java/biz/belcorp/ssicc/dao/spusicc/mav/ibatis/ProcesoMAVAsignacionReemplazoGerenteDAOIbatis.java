package biz.belcorp.ssicc.dao.spusicc.mav.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mav.ProcesoMAVAsignacionReemplazoGerenteDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.procesoMAVAsignacionReemplazoGerenteDAO")
public class ProcesoMAVAsignacionReemplazoGerenteDAOIbatis extends BaseDAOiBatis implements
	ProcesoMAVAsignacionReemplazoGerenteDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVAsignacionReemplazoGerenteDAO#getGerentesRegiones(java.util.Map)
	 */
	public List getGerentesRegiones(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAVSQL.getGerentesRegiones", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVAsignacionReemplazoGerenteDAO#executeAsignacionGerenteRegion(java.util.Map)
	 */
	public void executeAsignacionGerenteRegion(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAVSQL.executeAsignacionGerenteRegion", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVAsignacionReemplazoGerenteDAO#getGerentesZonas(java.util.Map)
	 */
	public List getGerentesZonas(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAVSQL.getGerentesZonas", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVAsignacionReemplazoGerenteDAO#executeAsignacionGerenteZona(java.util.Map)
	 */
	public void executeAsignacionGerenteZona(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAVSQL.executeAsignacionGerenteZona", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVAsignacionReemplazoGerenteDAO#getRemisionesMaterialPromocional(java.util.Map)
	 */
	public List getRemisionesMaterialPromocional(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAVSQL.getRemisionesMaterialPromocional", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVAsignacionReemplazoGerenteDAO#getDatosRemisionesMaterialPromocional(java.util.Map)
	 */
	public Map getDatosRemisionesMaterialPromocional(Map params) {
		return (HashMap) getSqlMapClientTemplate().queryForObject("spusicc.ProcesosMAVSQL.getDatosRemisionesMaterialPromocional", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVAsignacionReemplazoGerenteDAO#getAbastecimientoMaterial(java.util.Map)
	 */
	public List getAbastecimientoMaterial(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAVSQL.getAbastecimientoMaterial", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVAsignacionReemplazoGerenteDAO#getListaActividades(java.util.Map)
	 */
	public List getListaActividades(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAVSQL.getListaActividades", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVAsignacionReemplazoGerenteDAO#getDatosHistoricoMaterialPromocional(java.util.Map)
	 */
	public Map getDatosHistoricoMaterialPromocional(Map params) {
		return (HashMap) getSqlMapClientTemplate().queryForObject("spusicc.ProcesosMAVSQL.getDatosHistoricoMaterialPromocional", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVAsignacionReemplazoGerenteDAO#getHistoricoMaterialPromocional(java.util.Map)
	 */
	public List getHistoricoMaterialPromocional(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAVSQL.getHistoricoMaterialPromocional", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVAsignacionReemplazoGerenteDAO#getCabeceraArmadoGeneral(java.util.Map)
	 */
	public List getCabeceraArmadoGeneral(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAVSQL.getCabeceraArmadoGeneral", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVAsignacionReemplazoGerenteDAO#getDetalleArmadoGeneral(java.util.Map)
	 */
	public List getDetalleArmadoGeneral(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAVSQL.getDetalleArmadoGeneral", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVAsignacionReemplazoGerenteDAO#getPlanillaEntregaMaterial(java.util.Map)
	 */
	public List getPlanillaEntregaMaterial(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAVSQL.getPlanillaEntregaMaterial", params);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVAsignacionReemplazoGerenteDAO#executeAsignacionNumeroCajas(java.util.Map)
	 */
	public void executeAsignacionNumeroCajas(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAVSQL.executeAsignacionNumeroCajas", params);
	}
	
}
