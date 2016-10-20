package biz.belcorp.ssicc.dao.spusicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPERActualizarPercepcionesConsolidado;
import biz.belcorp.ssicc.dao.spusicc.MantenimientoPERPercepcionesOtrosCanalesDAO;

/**
 * Implementacion del DAO del mantenimiento de otros canales
 * 
 * <p>
 * <a href="MantenimientoPERPercepcionesOtrosCanalesDAOiBatis.java.html">
 * <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Richard De los Reyes
 *         Prncipe</a>
 * 
 */
@Repository("spusicc.mantenimientoPERPercepcionesOtrosCanalesDAO")
public class MantenimientoPERPercepcionesOtrosCanalesDAOiBatis extends
		BaseDAOiBatis implements MantenimientoPERPercepcionesOtrosCanalesDAO {

	public List getConsolidadoPercepcionesAcumulado(Map criteria) {
		List consolidados = getSqlMapClientTemplate().queryForList(
				"spusicc.ProcesosPERSQL.getConsolidadoPercepcionesAcumulado",
				criteria);
		return consolidados;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.dao.MantenimientoPERPercepcionesOtrosCanalesDAO
	 * #getPercepcionesOtrosCanales(biz.belcorp.ssicc.sisicc.model.
	 * InterfazPERActualizarPercepcionesConsolidado)
	 */
	public List getPercepcionesOtrosCanales(Map criteria) {
		List movimientos = getSqlMapClientTemplate().queryForList(
				"spusicc.ProcesosPERSQL.getPercepcionesOtrosCanales", criteria);
		return movimientos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.dao.MantenimientoPERPercepcionesOtrosCanalesDAO
	 * #getConsolidadoPercepcion(biz.belcorp.ssicc.sisicc.model.
	 * InterfazPERActualizarPercepcionesConsolidado)
	 */
	/**
	 * 
	 * @param cabecera
	 * @return
	 */
	public List getConsolidadoPercepcion(
			InterfazPERActualizarPercepcionesConsolidado consolidado) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"spusicc.ProcesosPERSQL.getConsolidadoPercepcion", consolidado);
		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.dao.MantenimientoPERPercepcionesOtrosCanalesDAO
	 * #updatePercepcionesOtrosCanales(biz.belcorp.ssicc.sisicc.model.
	 * InterfazPERActualizarPercepcionesConsolidado,
	 * biz.belcorp.ssicc.model.Usuario)
	 */
	public void updatePercepcionesOtrosCanales(
			InterfazPERActualizarPercepcionesConsolidado consolidado,
			Usuario usuario) {
		getSqlMapClientTemplate().update(
				"spusicc.ProcesosPERSQL.updatePercepcionesOtrosCanales",
				consolidado);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.dao.MantenimientoPERPercepcionesOtrosCanalesDAO
	 * #removePercepcionesOtrosCanales(biz.belcorp.ssicc.sisicc.model.
	 * InterfazPERActualizarPercepcionesConsolidado,
	 * biz.belcorp.ssicc.model.Usuario)
	 */
	public void removePercepcionesOtrosCanales(
			InterfazPERActualizarPercepcionesConsolidado consolidado,
			Usuario usuario) {
		getSqlMapClientTemplate().update(
				"spusicc.ProcesosPERSQL.removePercepcionesOtrosCanales",
				consolidado);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.dao.MantenimientoPERPercepcionesOtrosCanalesDAO
	 * #getNextCorrelativo(java.lang.String)
	 */
	public String getNextCorrelativo(String codigoPais) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.ProcesosPERSQL.getNextCorrelativo", codigoPais);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.dao.MantenimientoPERPercepcionesOtrosCanalesDAO
	 * #deletePercepcionesSistemasExternos(java.util.Map)
	 */
	public void deletePercepcionesSistemasExternos(Map param) {
		getSqlMapClientTemplate().update(
				"spusicc.ProcesosPERSQL.deletePercepcionesSistemasExternos",
				param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.dao.MantenimientoPERPercepcionesOtrosCanalesDAO
	 * #getConsolidadoPercepcionesAcumuladoDia(java.util.Map)
	 */
	public List getConsolidadoPercepcionesAcumuladoDia(Map criteria) {
		List consolidados = getSqlMapClientTemplate()
				.queryForList(
						"spusicc.ProcesosPERSQL.getConsolidadoPercepcionesAcumuladoDia",
						criteria);
		return consolidados;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.dao.MantenimientoPERPercepcionesOtrosCanalesDAO
	 * #insertNumeracionComprobantesSunat(java.util.Map,
	 * biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertNumeracionComprobantesSunat(Map map, Usuario usuario) {
		String indicadorVigencia = String.valueOf(map.get("indicadorVigencia"));
		String correlativoAutorizacion = getCorrelativoAutorizacion(map);
		map.put("correlativoAutorizacion", correlativoAutorizacion);
		getSqlMapClientTemplate()
				.insert("spusicc.ProcesosPERSQL.insertNumeracionComprobantesSunat",
						map);

		if (Constants.NUMERO_UNO.equals(indicadorVigencia)) {
			getSqlMapClientTemplate()
					.update("spusicc.ProcesosPERSQL.updateVigenciaCerradoComprobantesSunat",
							map);
		}

	}

	/**
	 * Retorna el correlativo de autorizacion
	 * 
	 * @param map
	 * @return
	 */
	private String getCorrelativoAutorizacion(Map map) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.ProcesosPERSQL.getCorrelativoAutorizacion", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.dao.MantenimientoPERPercepcionesOtrosCanalesDAO
	 * #updateNumeracionComprobantesSunat(java.util.Map,
	 * biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateNumeracionComprobantesSunat(Map map, Usuario usuario) {
		String indicadorVigencia = String.valueOf(map.get("indicadorVigencia"));
		getSqlMapClientTemplate()
				.update("spusicc.ProcesosPERSQL.updateNumeracionComprobantesSunat",
						map);
		if (Constants.NUMERO_UNO.equals(indicadorVigencia)) {
			getSqlMapClientTemplate()
					.update("spusicc.ProcesosPERSQL.updateVigenciaCerradoComprobantesSunat",
							map);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.dao.MantenimientoPERPercepcionesOtrosCanalesDAO
	 * #getNumeracionComprobantesSunatList(java.util.Map)
	 */
	public List getNumeracionComprobantesSunatList(Map map) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.ProcesosPERSQL.getNumeracionComprobantesSunatList",
				map);

	}

}