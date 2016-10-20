package biz.belcorp.ssicc.dao.edu.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.edu.ProcesoEDUCalificacionAptasDemandaDAO;
import biz.belcorp.ssicc.dao.edu.model.CalificacionAptaDemandaCampanha;
import biz.belcorp.ssicc.dao.edu.model.CalificacionAptaDemandaCliente;
import biz.belcorp.ssicc.dao.edu.model.CalificacionAptaDemandaRegion;
import biz.belcorp.ssicc.dao.edu.model.CalificacionAptaDemandaZona;
import biz.belcorp.ssicc.dao.edu.model.CalificacionAptasDemandaCurso;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * @author peextrvela
 *
 */
@Repository("edu.procesoEDUCalificacionAptasDemandaDAO")
public class ProcesoEDUCalificacionAptasDemandaDAOiBatis extends 
	BaseDAOiBatis implements ProcesoEDUCalificacionAptasDemandaDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getCalificacionAptasCurso(biz.belcorp.ssicc.edu.dao.model.CalificacionAptasDemandaCurso)
	 */
	public List getCalificacionAptasDemandaCurso(CalificacionAptasDemandaCurso calificacionAptasCurso) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.ProcesosEDUSQL.getCalificacionAptasDemandaCurso",
				calificacionAptasCurso);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getCalificacionDemandaCampanha(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaCampanha)
	 */
	public List getCalificacionAptaDemandaCampanha(CalificacionAptaDemandaCampanha calificacionAptaDemandaCampanha) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.ProcesosEDUSQL.getCalificacionAptaDemandaCampanha",
				calificacionAptaDemandaCampanha);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCalificacionAptasDemandaDAO#getCalificacionAptaDemandaCliente(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaCliente)
	 */
	public List getCalificacionAptaDemandaCliente(CalificacionAptaDemandaCliente calificacionAptaDemandaCliente) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.ProcesosEDUSQL.getCalificacionAptaDemandaCliente",
				calificacionAptaDemandaCliente);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCalificacionAptasDemandaDAO#getCalificacionAptaDemandaRegion(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaRegion)
	 */
	public List getCalificacionAptaDemandaRegion(CalificacionAptaDemandaRegion calificacionAptaDemandaRegion) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.ProcesosEDUSQL.getCalificacionAptaDemandaRegion",
				calificacionAptaDemandaRegion);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCalificacionAptasDemandaDAO#getCalificacionAptaDemandaZona(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaZona)
	 */
	public List getCalificacionAptaDemandaZona(CalificacionAptaDemandaZona calificacionAptaDemandaZona) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.ProcesosEDUSQL.getCalificacionAptaDemandaZona",
				calificacionAptaDemandaZona);
		return resultado;

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCalificacionAptasDemandaDAO#insertCalificacionAptaDemandaCampanha(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaCampanha)
	 */
	public void insertCalificacionAptaDemandaCampanha(CalificacionAptaDemandaCampanha calificacionAptaDemandaCampanha,Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"edu.ProcesosEDUSQL.insertCalificacionAptaDemandaCampanha",
				calificacionAptaDemandaCampanha);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCalificacionAptasDemandaDAO#insertCalificacionAptaDemandaCliente(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaCliente)
	 */
	public void insertCalificacionAptaDemandaCliente(CalificacionAptaDemandaCliente calificacionAptaDemandaCliente,Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert(
				"edu.ProcesosEDUSQL.insertCalificacionAptaDemandaCliente",
				calificacionAptaDemandaCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCalificacionAptasDemandaDAO#insertCalificacionAptaDemandaRegion(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaRegion)
	 */
	public void insertCalificacionAptaDemandaRegion(CalificacionAptaDemandaRegion calificacionAptaDemandaRegion,Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert(
				"edu.ProcesosEDUSQL.insertCalificacionAptaDemandaRegion",
				calificacionAptaDemandaRegion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCalificacionAptasDemandaDAO#insertCalificacionAptaDemandaZona(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaZona)
	 */
	public void insertCalificacionAptaDemandaZona(CalificacionAptaDemandaZona calificacionAptaDemandaZona,Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert(
				"edu.ProcesosEDUSQL.insertCalificacionAptaDemandaZona",
				calificacionAptaDemandaZona);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCalificacionAptasDemandaDAO#removeCalificacionAptaDemandaCampanha(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaCampanha)
	 */
	public void removeCalificacionAptaDemandaCampanha(CalificacionAptaDemandaCampanha calificacionAptaDemandaCampanha) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete(
				"edu.ProcesosEDUSQL.removeCalificacionAptaDemandaCampanha",
				calificacionAptaDemandaCampanha);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCalificacionAptasDemandaDAO#removeCalificacionAptaDemandaCliente(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaCliente)
	 */
	public void removeCalificacionAptaDemandaCliente(CalificacionAptaDemandaCliente calificacionAptaDemandaCliente) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete(
				"edu.ProcesosEDUSQL.removeCalificacionAptaDemandaCliente",
				calificacionAptaDemandaCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCalificacionAptasDemandaDAO#removeCalificacionAptaDemandaRegion(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaRegion)
	 */
	public void removeCalificacionAptaDemandaRegion(CalificacionAptaDemandaRegion calificacionAptaDemandaRegion) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete(
				"edu.ProcesosEDUSQL.removeCalificacionAptaDemandaRegion",
				calificacionAptaDemandaRegion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCalificacionAptasDemandaDAO#removeCalificacionAptaDemandaZona(biz.belcorp.ssicc.edu.dao.model.CalificacionAptaDemandaZona)
	 */
	public void removeCalificacionAptaDemandaZona(CalificacionAptaDemandaZona calificacionAptaDemandaZona) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete(
				"edu.ProcesosEDUSQL.removeCalificacionAptaDemandaZona",
				calificacionAptaDemandaZona);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCalificacionAptasDemandaDAO#executeCalificacionAptasDemanda(java.util.Map)
	 */
	public void executeCalificacionAptasDemanda(Map criteria) {
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.executeCalificacionAptasDemanda",
				criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCalificacionAptasDemandaDAO#executePrevioCalificacionAptasDemanda(java.util.Map)
	 */
	public void executePrevioCalificacionAptasDemanda(Map criteria) {
		Integer contador = (Integer) getSqlMapClientTemplate().queryForObject(
							"edu.ProcesosEDUSQL.getGTTParamProceso", criteria);
		
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.executePrevioCalificacionAptasDemanda",
				criteria);
		String registrosObtenidos = (String) criteria.get("registrosObtenidos");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCalificacionAptasDemandaDAO#getPrevioCalificacionAptasDemanda(java.util.Map)
	 */
	public List getPrevioCalificacionAptasDemanda(Map criteria) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.ProcesosEDUSQL.getPrevioCalificacionAptasDemanda", criteria);
		return resultado;
	}
}
