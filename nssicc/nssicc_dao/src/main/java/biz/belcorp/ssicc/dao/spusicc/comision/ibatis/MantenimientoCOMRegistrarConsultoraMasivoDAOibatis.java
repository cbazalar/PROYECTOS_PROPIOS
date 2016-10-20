package biz.belcorp.ssicc.dao.spusicc.comision.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.comision.MantenimientoCOMRegistrarConsultoraMasivoDAO;

/**
 * @author Aurelio Oviedo
 *
 */
@Repository("spusicc.mantenimientoCOMRegistrarConsultoraMasivoDAO")
public class MantenimientoCOMRegistrarConsultoraMasivoDAOibatis extends BaseDAOiBatis implements MantenimientoCOMRegistrarConsultoraMasivoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMRegistrarConsultoraMasivoDAO#executeCargarListaZonas(java.util.Map)
	 */
	public void executeObtenerListaZonasAsociadas(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ProcesosCOMSQL.executeObtenerListaZonasAsociadas",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMRegistrarConsultoraMasivoDAO#executeValidarRegistroxCodigoConsultora(java.util.Map)
	 */
	public void executeValidarRegistroxCodigoConsultora(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ProcesosCOMSQL.executeValidarRegistroxCodigoConsultora",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMRegistrarConsultoraMasivoDAO#insertarConsultoraNuevas(java.util.Map)
	 */
	public void insertarConsultoraNuevas(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ProcesosCOMSQL.insertarConsultoraNuevas",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMRegistrarConsultoraMasivoDAO#insertFacturacionPrograma(java.util.Map)
	 */
	public void insertFacturacionPrograma(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ProcesosCOMSQL.insertFacturacionPrograma",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMRegistrarConsultoraMasivoDAO#getIndicadorConstanciaProgramaAsociado(java.util.Map)
	 */
	public String getIndicadorConstanciaProgramaAsociado(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosCOMSQL.getIndicadorConstanciaProgramaAsociado",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMRegistrarConsultoraMasivoDAO#insertNivelesConsultoras(java.util.Map)
	 */
	public void insertNivelesConsultoras(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ProcesosCOMSQL.insertNivelesConsultoras",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMRegistrarConsultoraMasivoDAO#getListaMatrizEquivalente(java.util.Map)
	 */
	public List getListaMatrizEquivalente(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOMSQL.getListaMatrizEquivalente", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMRegistrarConsultoraMasivoDAO#updateConsultoraNuevas(java.util.Map)
	 */
	public void updateConsultoraNuevas(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ProcesosCOMSQL.updateConsultoraNuevas",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMRegistrarConsultoraMasivoDAO#updateConsultoraCupon(java.util.Map)
	 */
	public void updateConsultoraCupon(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ProcesosCOMSQL.updateConsultoraCupon",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMRegistrarConsultoraMasivoDAO#updateFacturacionPrograma(java.util.Map)
	 */
	public void updateFacturacionPrograma(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ProcesosCOMSQL.updateFacturacionPrograma",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMRegistrarConsultoraMasivoDAO#updateNivelesConsultoras(java.util.Map)
	 */
	public void updateNivelesConsultoras(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ProcesosCOMSQL.updateNivelesConsultoras",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMRegistrarConsultoraMasivoDAO#getPeriodoNSiguiente(java.util.Map)
	 */
	public String getPeriodoNSiguiente(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.GenericoSQL.getPeriodoNSiguiente",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMRegistrarConsultoraMasivoDAO#existeNivelConsultora(java.util.Map)
	 */
	public boolean existeNivelConsultora(Map criteria) {
		List resultado = getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOMSQL.existeNivelConsultora",criteria);
		
		if (resultado == null) {
			return false; // Inserta
		}else {
			return true; // Actualiza
		}
		
	}
}