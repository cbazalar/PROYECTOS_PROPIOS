package biz.belcorp.ssicc.dao.edu.ibatis;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.edu.ConsultaEDUCursoCapacitacionDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;

/**
 * @author peextrvela
 *
 */
@Repository("edu.consultaEDUCursoCapacitacionDAO")
public class ConsultaEDUCursoCapacitacionDAOiBatis extends 
	BaseDAOiBatis implements ConsultaEDUCursoCapacitacionDAO{


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ConsultaEDUCursoCapacitacionDAO#getConsultaCursoCapacitacionAptas(biz.belcorp.ssicc.edu.dao.model.ConsultaCursoCapacitacion)
	 */
	public List getConsultaCursoCapacitacionAptas(Map criterios) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getConsultaCursoCapacitacionAptas",
				criterios);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ConsultaEDUCursoCapacitacionDAO#getConsultaCursoCapacitacionCapacitadas(biz.belcorp.ssicc.edu.dao.model.ConsultaCursoCapacitacion)
	 */
	public List getConsultaCursoCapacitacionCapacitadas(Map criterios) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getConsultaCursoCapacitacionCapacitadas",
				criterios);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ConsultaEDUCursoCapacitacionDAO#getConsultaCursoCapacitacionProgramadas(biz.belcorp.ssicc.edu.dao.model.ConsultaCursoCapacitacion)
	 */
	public List getConsultaCursoCapacitacionProgramadas(Map criterios) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getConsultaCursoCapacitacionProgramadas",
				criterios);
		return resultado;
	}

	public List getListStatusAptaConsultora(Map criterios) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getListStatusAptaConsultora",
				criterios);
		return resultado;
	}

	public List getListStatusProgrConsultora(Map criterios) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getListStatusProgrConsultora",
				criterios);
		return resultado;
	}

	public List getListStatusCapacConsultora(Map criterios) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getListStatusCapacConsultora",
				criterios);
		return resultado;
	}

	public List getListStatusBenefConsultora(Map criterios) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getListStatusBenefConsultora",
				criterios);
		return resultado;
	}

	public String[] getNivelesxAlcanzar(Map criterios) {
		String[] niveles=new String[0];
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getNivelesxAlcanzar",
				criterios);
		log.debug("nivel x alcanzar >>> " + resultado.size() );
		Iterator it = resultado.iterator();
		niveles=new String[resultado.size()];
		int i=0;
		while(it.hasNext()){
			String value= (String)it.next();
			niveles[i++]=value;
		}
		return niveles;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ConsultaEDUCursoCapacitacionDAO#getConsultaCursoCapacitacionNoAptas(java.util.Map)
	 */
	public List getConsultaCursoCapacitacionNoAptas(Map criterios) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getConsultaCursoCapacitacionNoAptas",
				criterios);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ConsultaEDUCursoCapacitacionDAO#getSituaciones(java.util.Map)
	 */
	public List getSituaciones(Map criterios) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getSituaciones",
				criterios);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ConsultaEDUCursoCapacitacionDAO#getConsultaCursoCapacitacionPendientes(java.util.Map)
	 */
	public List getConsultaCursoCapacitacionPendientes(Map criterios) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getConsultaCursoCapacitacionPendientes",
				criterios);
		return resultado;
	}
	
}
