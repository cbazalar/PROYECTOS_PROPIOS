/**
 * 
 */
package biz.belcorp.ssicc.dao.scsicc.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.scsicc.GraficoDAO;

/**
 * 
 * <p>
 * <a href="GraficoDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Carlos Bazalar La Rosa</a>
 * 
 */
@Repository("scsicc.graficoDAO")
public class GraficoDAOiBatis extends BaseDAOiBatis implements GraficoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.GraficoDAO#getResumenConsultorasxEstado(java.util.Map)
	 */
	public List getResumenConsultorasxEstadoPie(Map params) throws Exception {
		List lista = getResumenConsultorasxEstadoPieHistorico(params);
			return lista;
		
		}
		
		public List getResumenConsultorasxEstadoPieHistorico(Map params) throws Exception {
		List lista  = new ArrayList();
		String situacion = (String) params.get("situacion");
		String codigoRegion = (String) params.get("codigoRegion");
		log.debug(" getResumenConsultorasxEstadoPieHistorico");
		log.debug("------------> "+params.get("situacion"));
		/* Situacion solamente y Region todos */
		if (!Constants.EDU_SITUACION_TODOS.equals(situacion) && Constants.EDU_REGION_TODOS.equals(codigoRegion)) {
			if (Constants.ESTADO_CAPACITADA_PENDIENTE.equals(situacion)){
				lista  = getSqlMapClientTemplate().queryForList(
								"scsicc.GraficosSQL.getResumenConsultorasxEstadoPieHistoricoSituacionAptas", params);
			
				return lista;
			}
			if (Constants.ESTADO_CAPACITADA_PROGRAMADA.equals(situacion)){
				lista  = getSqlMapClientTemplate().queryForList(
								"scsicc.GraficosSQL.getResumenConsultorasxEstadoPieHistoricoSituacionProgramadas", params);
			
			return lista;
			}
			if (Constants.ESTADO_CAPACITADA_CAPACITADA.equals(situacion)){
				lista  = getSqlMapClientTemplate().queryForList(
								"scsicc.GraficosSQL.getResumenConsultorasxEstadoPieHistoricoSituacionCapacitadas", params);
			
			return lista;
			}
			
			return lista  = getSqlMapClientTemplate().queryForList(
					"scsicc.GraficosSQL.getResumenConsultorasxEstadoPieSituacionDemas", params);                 	
		}
		
		/* Situacion todos y Region solamente */
		if (Constants.EDU_SITUACION_TODOS.equals(situacion) && !Constants.EDU_REGION_TODOS.equals(codigoRegion)) {
			lista  = getSqlMapClientTemplate().queryForList(
							"scsicc.GraficosSQL.getResumenConsultorasxEstadoPieHistoricoRegion", params);
			return lista;
		}
		
		/* Situacion y Region */
		if (!Constants.EDU_SITUACION_TODOS.equals(situacion) && !Constants.EDU_REGION_TODOS.equals(codigoRegion)) {
			if (Constants.ESTADO_CAPACITADA_PENDIENTE.equals(situacion))
				return lista  = getSqlMapClientTemplate().queryForList(
								"scsicc.GraficosSQL.getResumenConsultorasxEstadoPieHistoricoZonaAptas", params);
			if (Constants.ESTADO_CAPACITADA_PROGRAMADA.equals(situacion))
				return lista  = getSqlMapClientTemplate().queryForList(
								"scsicc.GraficosSQL.getResumenConsultorasxEstadoPieHistoricoZonaProgramadas", params);
			if (Constants.ESTADO_CAPACITADA_CAPACITADA.equals(situacion))
				return lista  = getSqlMapClientTemplate().queryForList(
								"scsicc.GraficosSQL.getResumenConsultorasxEstadoPieHistoricoZonaCapacitadas", params);
			//****************************************************************
			lista  = getSqlMapClientTemplate().queryForList(
					"scsicc.GraficosSQL.getResumenConsultorasxEstadoPieSituacionDemas", params);
			//****************************************************************
			return lista;
		}
		
		/* Situacion todos y Region todos */
		lista  = getSqlMapClientTemplate().queryForList(
					"scsicc.GraficosSQL.getResumenConsultorasxEstadoPieHistorico", params);
		return lista;
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.GraficoDAO#getResumenConsultorasxEstadoBar(java.util.Map)
	 */
	public List getResumenConsultorasxEstadoBar(Map params) throws Exception {
		List lista  = new ArrayList();
		String situacion = (String) params.get("situacion");
		String codigoRegion = (String) params.get("codigoRegion");
		log.debug("getResumenConsultorasxEstadoBar ");
		/* Situacion solamente y Region todos */
		if (!Constants.EDU_SITUACION_TODOS.equals(situacion) && Constants.EDU_REGION_TODOS.equals(codigoRegion)) {
			if (Constants.ESTADO_CAPACITADA_CAPACITADA.equals(situacion)){
				lista  = getSqlMapClientTemplate().queryForList(
							"scsicc.GraficosSQL.getResumenConsultorasxEstadoBarSituacionCapacitada", params);
				return lista;
			}
			//***********************************************************************
			if (Constants.ESTADO_CAPACITADA_PROGRAMADA.equals(situacion)){
				lista  = getSqlMapClientTemplate().queryForList(
								"scsicc.GraficosSQL.getResumenConsultorasxEstadoBarSituacionProgramada", params);	
				return lista;
			}	
			if (Constants.ESTADO_CAPACITADA_PENDIENTE.equals(situacion)){
				lista  = getSqlMapClientTemplate().queryForList(
								"scsicc.GraficosSQL.getResumenConsultorasxEstadoBarSituacionAptas", params);			
				return lista;
			}
			//***********************************************************************			
				lista  = getSqlMapClientTemplate().queryForList(
							"scsicc.GraficosSQL.getResumenConsultorasxEstadoBarSituacionDemas", params);
			return lista;
		}
		
		/* Situacion todos y Region solamente */
		if (Constants.EDU_SITUACION_TODOS.equals(situacion) && !Constants.EDU_REGION_TODOS.equals(codigoRegion)) {
			lista  = getSqlMapClientTemplate().queryForList(
						"scsicc.GraficosSQL.getResumenConsultorasxEstadoBarRegion", params);
			return lista;
		}
		
		/* Situacion y Region */
		if (!Constants.EDU_SITUACION_TODOS.equals(situacion) && !Constants.EDU_REGION_TODOS.equals(codigoRegion)) {
			if (Constants.ESTADO_CAPACITADA_CAPACITADA.equals(situacion)){
				return lista  = getSqlMapClientTemplate().queryForList(
							"scsicc.GraficosSQL.getResumenConsultorasxEstadoBarZonaCapacitada", params);
			}
			//***************************************************************************************
			if (Constants.ESTADO_CAPACITADA_PROGRAMADA.equals(situacion)){
				return lista  = getSqlMapClientTemplate().queryForList(
							"scsicc.GraficosSQL.getResumenConsultorasxEstadoBarZonaProgramada", params);
			}
			if (Constants.ESTADO_CAPACITADA_PENDIENTE.equals(situacion)){
				return lista  = getSqlMapClientTemplate().queryForList(
						"scsicc.GraficosSQL.getResumenConsultorasxEstadoBarZonaAptas", params);
			}
			//***************************************************************************************			
				lista  = getSqlMapClientTemplate().queryForList(
							"scsicc.GraficosSQL.getResumenConsultorasxEstadoBarZonaDemas", params);
			return lista;
		}
		
		/* Situacion todos y Region todos */
		lista = getSqlMapClientTemplate().queryForList(
					"scsicc.GraficosSQL.getResumenConsultorasxEstadoBar", params);
		return lista;
		
	}
}