/**
 * 
 */
package biz.belcorp.ssicc.dao.soa.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.soa.ConcursoDAO;

/**
 * @author Danny Amaro
 *
 */
@Repository("soa.concursoDAO")
public class ConcursoDAOiBatis extends BaseDAOiBatis implements ConcursoDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.ConcursoDAO#getDetalleConcursoConstanciaVentas(java.util.Map)
	 */
	public List getDetalleConcursoConstanciaVentas(Map criteria) {
		return getSqlMapClientTemplate().queryForList("soa.ConcursoSQL.getDetalleConcursoConstanciaVentas", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.ConcursoDAO#getTotalDetalleConcursoConstanciaVentas(java.util.Map)
	 */
	public int getTotalGanadorasDetalleConcursoConstanciaVentas(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject(
				"soa.ConcursoSQL.getTotalGanadorasDetalleConcursoConstanciaVentas", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.ConcursoDAO#getConcursosXTipo(java.util.Map)
	 */
	public List getConcursosXTipo(Map criteria) {
		
		return getSqlMapClientTemplate().queryForList("soa.ConcursoSQL.getConcursosXTipo", criteria);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.ConcursoDAO#getDetalleConcursoRecomendacion(java.util.Map)
	 */
	public List getDetalleConcursoRecomendacion(Map criteria) {
		
		return getSqlMapClientTemplate().queryForList("soa.ConcursoSQL.getDetalleConcursoRecomendacion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.ConcursoDAO#getDetalleConsultoraRecomendada(java.util.Map)
	 */
	public List getDetalleConsultoraRecomendada(Map criteria) {
		
		//obtenemos oid cliente
		Long oidCliente = (Long)getSqlMapClientTemplate().queryForObject("soa.ConsultoraSQL.getOidCliente", criteria);
		criteria.put("oidCliente", oidCliente);

		return getSqlMapClientTemplate().queryForList("soa.ConcursoSQL.getDetalleConsultoraRecomendada", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.ConcursoDAO#getDetalleConcursoProgramaNuevas(java.util.Map)
	 */
	public List getDetalleConcursoProgramaNuevas(Map criteria) {
		return getSqlMapClientTemplate().queryForList("soa.ConcursoSQL.getDetalleConcursoProgramaNuevas", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.ConcursoDAO#getPedidosNuevas(java.util.Map)
	 */
	public List getPedidosNuevas(Map criteria) {
		
		   Long oidCliente = (Long)getSqlMapClientTemplate().queryForObject("soa.ConsultoraSQL.getOidCliente", criteria);
	       criteria.put("oidCliente", oidCliente);
	        
	       Long lnperiodoini = (Long)getSqlMapClientTemplate().queryForObject("soa.ConcursoSQL.getLnPeriodoIni", criteria);
	       criteria.put("lnperiodoini", lnperiodoini);
	       
	       Long lnperiodofin = (Long)getSqlMapClientTemplate().queryForObject("soa.ConcursoSQL.getLnPeriodoFin", criteria);
	       criteria.put("lnperiodofin", lnperiodofin);
	       
	       return getSqlMapClientTemplate().queryForList("soa.ConcursoSQL.getPedidosNuevas", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.ConcursoDAO#getDetalleConcursoReconocimiento(java.util.Map)
	 */
	public List getDetalleConcursoReconocimiento(Map criteria) {

		return getSqlMapClientTemplate().queryForList("soa.ConcursoSQL.getDetalleConcursoReconocimiento", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.ConcursoDAO#getInformacionBasicaConcurso(java.util.Map)
	 */
	public List getInformacionBasicaConcurso(Map criteria) {
		//obtenemos el tipo de concurso
		String codigoTipoConcurso = (String)criteria.get("codigoTipoConcurso");
		
		if(Constants.SOA_TIPO_NUEVAS.equals(codigoTipoConcurso))
			return getSqlMapClientTemplate().queryForList("soa.ConcursoSQL.getInformacionBasicaConcursoNuevas", criteria);
		
		return getSqlMapClientTemplate().queryForList("soa.ConcursoSQL.getInformacionBasicaConcurso", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.ConcursoDAO#getDetalleConcursoRegaloPedido(java.util.Map)
	 */
	public List getDetalleConcursoRegaloPedido(Map criteria) {
		return getSqlMapClientTemplate().queryForList("soa.ConcursoSQL.getDetalleConcursoRegaloPedido", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.ConcursoDAO#getOidPeriodoActual(java.util.Map)
	 */
	public List getOidPeriodoActual(Map criteria) {
		return getSqlMapClientTemplate().queryForList("soa.ConcursoSQL.getOidPeriodoActual", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.ConcursoDAO#getTotalConcursantes(java.util.Map)
	 */
	public int getTotalConcursantes(Map criteria) {		
		return (Integer)getSqlMapClientTemplate().queryForObject(
				"soa.ConcursoSQL.getTotalConcursantes", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.ConcursoDAO#getTotalGanadoras(java.util.Map)
	 */
	public int getTotalGanadoras(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject(
				"soa.ConcursoSQL.getTotalConcursantes", criteria);
	}

}
