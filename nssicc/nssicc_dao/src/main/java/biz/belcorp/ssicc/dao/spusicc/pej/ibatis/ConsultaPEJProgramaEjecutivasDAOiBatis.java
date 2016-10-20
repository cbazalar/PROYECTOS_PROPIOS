package biz.belcorp.ssicc.dao.spusicc.pej.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pej.ConsultaPEJProgramaEjecutivasDAO;

/**
 * @author Jesse James Rios Franco
 *
 */
@Repository("spusicc.consultaPEJProgramaEjecutivasDAO")
public class ConsultaPEJProgramaEjecutivasDAOiBatis extends BaseDAOiBatis implements ConsultaPEJProgramaEjecutivasDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ConsultaPEJProgramaEjecutivasDAO#getProgramaEjecutivasList(java.util.Map)
	 */
	public List getProgramaEjecutivasList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosPEJSQL.getProgramaEjecutivasList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ConsultaPEJProgramaEjecutivasDAO#getMetaPedido(java.util.Map)
	 */
	public Integer getMetaPedido(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getMetaPedido", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ConsultaPEJProgramaEjecutivasDAO#getDescripcionNivelByEjecuEtap(java.util.Map)
	 */
	public String getDescripcionNivelByEjecuEtap(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getDescripcionNivelByEjecuEtap", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ConsultaPEJProgramaEjecutivasDAO#getCantidadCampanhaEtapa(java.lang.String, java.lang.String)
	 */
	public Integer getCantidadCampanhaEtapa(String codigoPais,String codigoEtapa) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoEtapa", codigoEtapa);
		
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getCantidadCampanhaEtapa", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ConsultaPEJProgramaEjecutivasDAO#getCampanhaInicialEtapa(java.lang.String, java.lang.String)
	 */
	public String getCampanhaInicialEtapa(String codigoPais, String codigoEtapa) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoEtapa", codigoEtapa);
		
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getCampanhaInicialEtapa", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ConsultaPEJProgramaEjecutivasDAO#getCampanhaFinalEtapa(java.lang.String, java.lang.String)
	 */
	public String getCampanhaFinalEtapa(String codigoPais, String codigoEtapa) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoEtapa", codigoEtapa);
		
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getCampanhaFinalEtapa", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ConsultaPEJProgramaEjecutivasDAO#getObtienePeriodo(java.lang.String, java.lang.String, java.lang.Integer)
	 */
	public String getObtienePeriodo(String codigoPais, String periodo,Integer valorRegistro){
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("periodo", periodo);
		criteria.put("valorRegistro", valorRegistro);
		
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getObtienePeriodo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ConsultaPEJProgramaEjecutivasDAO#getVariablesEjecutivasCampaByPeriodoEjec(java.util.Map)
	 */
	public HashMap getVariablesEjecutivasCampaByPeriodoEjec(Map criteria) {
		return (HashMap)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getVariablesEjecutivasCampaByPeriodoEjec", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ConsultaPEJProgramaEjecutivasDAO#getComisionByEjecutivaPeriodo(java.util.Map)
	 */
	public HashMap getComisionByEjecutivaPeriodo(Map criteria) {
		return (HashMap)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getComisionByEjecutivaPeriodo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ConsultaPEJProgramaEjecutivasDAO#getMontosBonosEjecAnhioInicEtapa(java.util.Map)
	 */
	public HashMap getMontosBonosEjecAnhioInicEtapa(Map criteria) {
		return (HashMap)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getMontosBonosEjecAnhioInicEtapa", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ConsultaPEJProgramaEjecutivasDAO#getPagosEjecEtapaList(java.util.Map)
	 */
	public List getPagosEjecEtapaList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosPEJSQL.getPagosEjecEtapaList", criteria);
	}
}