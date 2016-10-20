/**
 * 
 */
package biz.belcorp.ssicc.dao.soa.ibatis;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.soa.ConsultoraDAO;

/**
 * @author Danny Amaro
 * 
 */
@Repository("soa.consultoraDAO")
public class ConsultoraDAOiBatis extends BaseDAOiBatis implements ConsultoraDAO
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.soa.dao.ConsultoraDAO#getObtenerCursosCapacitacion(
	 * java.util.Map)
	 */

	public List getObtenerCursosCapacitacion(Map criteria)
	{
		List result = null;
		//obtenemos el periodo actual
		//String codigoPeriodo = (String)getSqlMapClientTemplate().queryForObject("soa.VisitasSQL.getPeriodoActual", criteria);
		//criteria.put("codigoPeriodo", codigoPeriodo);
		//obtenemos las campnhas previos
		//String campanhaInicial = (String)getSqlMapClientTemplate().queryForObject("soa.ConsultoraSQL.getPeriodoPrevios", criteria);		
		//criteria.put("campanhaInicial", campanhaInicial);
		result = (List) getSqlMapClientTemplate().queryForList("soa.ConsultoraSQL.getObtenerCursosCapacitacion", criteria);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.soa.dao.ConsultoraDAO#getObtenerConsultoras(
	 * java.util.Map)
	 */

	public List getObtenerListaConsultoras(Map criteria)
	{
		List result = null;
		result = (List) getSqlMapClientTemplate().queryForList("soa.ConsultoraSQL.getObtenerListaConsultoras", criteria);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.soa.dao.ConsultoraDAO#getOperaciones(java.util.Map)
	 */
	public List getObtenerOperaciones(Map criteria)
	{
		List result = null;
		//obtenemos el periodo actual si campanha de referecia es null o vacio
		String campanhaReferencia = (String) criteria.get("campanhaReferencia");
		if(StringUtils.isEmpty(campanhaReferencia)){
		  campanhaReferencia = (String)getSqlMapClientTemplate().queryForObject("soa.VisitasSQL.getPeriodoActual", criteria);		  
		}
		criteria.put("codigoPeriodo", campanhaReferencia);
		//obtenemos las campnhas previos
		String campanhaInicial = (String)getSqlMapClientTemplate().queryForObject("soa.ConsultoraSQL.getPeriodoPrevios", criteria);		
		criteria.put("campanhaInicial", campanhaInicial);
		//obtenemos oid cliente
		Long oidCliente = (Long)getSqlMapClientTemplate().queryForObject("soa.ConsultoraSQL.getOidCliente", criteria);
		criteria.put("oidCliente", oidCliente);
		//
		result = (List) getSqlMapClientTemplate().queryForList("soa.ConsultoraSQL.getObtenerOperaciones", criteria);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.soa.dao.ConsultoraDAO#getOperaciones(java.util.Map)
	 */
	public List getObtenerConsultora(Map criteria)
	{
		List result = null;
		String codigoPeriodo = (String)getSqlMapClientTemplate().queryForObject("soa.VisitasSQL.getPeriodoActual", criteria);		
		criteria.put("codigoPeriodo", codigoPeriodo);
		Long oidPeriodo = (Long)getSqlMapClientTemplate().queryForObject("soa.VisitasSQL.getOidPeriodoActual", criteria);
		Long oidPeriodoIni = (Long)getSqlMapClientTemplate().queryForObject("soa.VisitasSQL.getOidPeriodoIni", criteria);
		criteria.put("oidPeriodo", oidPeriodo);
		log.debug("codigo Periodo "+ codigoPeriodo+ " oidPeriodo "+oidPeriodo);
		criteria.put("oidPeriodoInicial", oidPeriodoIni);//3 periodos hacia atras		
		result = (List) getSqlMapClientTemplate().queryForList("soa.ConsultoraSQL.getObtenerConsultora", criteria);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.soa.dao.ConsultoraDAO#getOperaciones(java.util.Map)
	 */
	public List getObtenerPasosVisitaConsul(Map criteria)
	{
		List result = null;
		result = (List) getSqlMapClientTemplate().queryForList("soa.ConsultoraSQL.getObtenerPasosVisitaConsul", criteria);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.soa.dao.ConsultoraDAO#getPalancaConsultora(java.util
	 * .Map)
	 */
	public List getObtenerPalancaConsultora(Map criteria)
	{
		List result = null;
		result = (List) getSqlMapClientTemplate().queryForList("soa.ConsultoraSQL.getObtenerPalancaConsult", criteria);
		return result;
	}
}
