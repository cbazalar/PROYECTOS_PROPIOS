package biz.belcorp.ssicc.dao.edu.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.edu.ProcesoEDURegistroAsistenciaDAO;
import biz.belcorp.ssicc.dao.edu.model.DetallePlanilla;
import biz.belcorp.ssicc.dao.edu.model.HistoricoAptas;
import biz.belcorp.ssicc.dao.edu.model.HistoricoCapacitadas;
import biz.belcorp.ssicc.dao.edu.model.HistoricoCapacitadasDetalle;
import biz.belcorp.ssicc.dao.edu.model.HistoricoCursoDictado;
import biz.belcorp.ssicc.dao.edu.model.HistoricoCursoDictadoDetalle;
import biz.belcorp.ssicc.dao.edu.model.HistoricoPlanillaInstructora;
import biz.belcorp.ssicc.dao.edu.model.PlanillaProgramacionCurso;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;


/**
 * @author peextcbazalar
 *
 */
@Repository("edu.procesoEDURegistroAsistenciaDAO")
public class ProcesoEDURegistroAsistenciaDAOiBatis extends 
	BaseDAOiBatis implements ProcesoEDURegistroAsistenciaDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getHistoricoAptas(biz.belcorp.ssicc.edu.dao.model.HistoricoAptas)
	 */
	public List getHistoricoAptas(HistoricoAptas historicoAptas) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getHistoricoAptas",
				historicoAptas);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getHistoricoCapacitadas(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadas)
	 */
	public List getHistoricoCapacitadas(HistoricoCapacitadas historicoCapacitadas) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getHistoricoCapacitadas",
				historicoCapacitadas);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getHistoricoCapacitadasDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadasDetalle)
	 */
	public List getHistoricoCapacitadasDetalle(HistoricoCapacitadasDetalle historicoCapacitadasDetalle) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getHistoricoCapacitadasDetalle",
				historicoCapacitadasDetalle);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getHistoricoCursoDictado(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictado)
	 */
	public List getHistoricoCursoDictado(HistoricoCursoDictado historicoCursoDictado) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getHistoricoCursoDictado",
				historicoCursoDictado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getHistoricoCursoDictadoDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictadoDetalle)
	 */
	public List getHistoricoCursoDictadoDetalle(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getHistoricoCursoDictadoDetalle",
				historicoCursoDictadoDetalle);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getMaxHistoricoCursoDictado(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictado)
	 */
	public String getMaxHistoricoCursoDictado(HistoricoCursoDictado historicoCursoDictado) {
		String codigo = (String)getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUSQL.getMaxHistoricoCursoDictado",
				historicoCursoDictado);
		if (codigo==null) codigo="";
		return codigo;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getPlanillaProgramacionCurso(biz.belcorp.ssicc.edu.dao.model.PlanillaProgramacionCurso)
	 */
	public List getPlanillaProgramacionCurso(PlanillaProgramacionCurso planillaProgramacionCurso) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getPlanillaProgramacionCurso",
				planillaProgramacionCurso);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#insertHistoricoAptas(biz.belcorp.ssicc.edu.dao.model.HistoricoAptas, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertHistoricoAptas(HistoricoAptas historicoAptas, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUSQL.insertHistoricoAptas",
				historicoAptas);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#insertHistoricoCapacitadas(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadas, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertHistoricoCapacitadas(HistoricoCapacitadas historicoCapacitadas, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUSQL.insertHistoricoCapacitadas",
				historicoCapacitadas);
		getSqlMapClientTemplate().delete(
				"edu.MantenimientoEDUSQL.deleteConsultoraPlanillaNoRegistrada",
				historicoCapacitadas);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#insertHistoricoCapacitadasDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadasDetalle, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertHistoricoCapacitadasDetalle(HistoricoCapacitadasDetalle historicoCapacitadasDetalle, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUSQL.insertHistoricoCapacitadasDetalle",
				historicoCapacitadasDetalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#insertHistoricoCursoDictado(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictado, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertHistoricoCursoDictado(HistoricoCursoDictado historicoCursoDictado, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUSQL.insertHistoricoCursoDictado",
				historicoCursoDictado);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#insertHistoricoCursoDictadoDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictadoDetalle, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertHistoricoCursoDictadoDetalle(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUSQL.insertHistoricoCursoDictadoDetalle",
				historicoCursoDictadoDetalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#insertPlanillaProgramacionCurso(biz.belcorp.ssicc.edu.dao.model.PlanillaProgramacionCurso, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertPlanillaProgramacionCurso(PlanillaProgramacionCurso planillaProgramacionCurso, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUSQL.insertPlanillaProgramacionCurso",
				planillaProgramacionCurso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#removeFisicoHistoricoAptas(biz.belcorp.ssicc.edu.dao.model.HistoricoAptas, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeFisicoHistoricoAptas(HistoricoAptas historicoAptas, Usuario usuario) {
		getSqlMapClientTemplate().delete(
				"edu.MantenimientoEDUSQL.removeFisicoHistoricoAptas",
				historicoAptas);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#removeFisicoHistoricoCapacitadas(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadas, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeFisicoHistoricoCapacitadas(HistoricoCapacitadas historicoCapacitadas, Usuario usuario) {
		getSqlMapClientTemplate().delete(
				"edu.MantenimientoEDUSQL.removeFisicoHistoricoCapacitadas",
				historicoCapacitadas);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#removeFisicoHistoricoCapacitadasDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadasDetalle, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeFisicoHistoricoCapacitadasDetalle(HistoricoCapacitadasDetalle historicoCapacitadasDetalle, Usuario usuario) {
		getSqlMapClientTemplate().delete(
				"edu.MantenimientoEDUSQL.removeFisicoHistoricoCapacitadasDetalle",
				historicoCapacitadasDetalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#removeFisicoHistoricoCursoDictado(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictado, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeFisicoHistoricoCursoDictado(HistoricoCursoDictado historicoCursoDictado, Usuario usuario) {
		getSqlMapClientTemplate().delete(
				"edu.MantenimientoEDUSQL.removeFisicoHistoricoCursoDictado",
				historicoCursoDictado);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#removeFisicoHistoricoCursoDictadoDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictadoDetalle, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeFisicoHistoricoCursoDictadoDetalle(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle, Usuario usuario) {
		getSqlMapClientTemplate().delete(
				"edu.MantenimientoEDUSQL.removeFisicoHistoricoCursoDictadoDetalle",
				historicoCursoDictadoDetalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#removeFisicoPlanillaProgramacionCurso(biz.belcorp.ssicc.edu.dao.model.PlanillaProgramacionCurso, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeFisicoPlanillaProgramacionCurso(PlanillaProgramacionCurso planillaProgramacionCurso, Usuario usuario) {
		getSqlMapClientTemplate().delete(
				"edu.MantenimientoEDUSQL.removeFisicoPlanillaProgramacionCurso",
				planillaProgramacionCurso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#removeHistoricoAptas(biz.belcorp.ssicc.edu.dao.model.HistoricoAptas, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeHistoricoAptas(HistoricoAptas historicoAptas, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.removeHistoricoAptas",
				historicoAptas);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#removeHistoricoCapacitadas(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadas, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeHistoricoCapacitadas(HistoricoCapacitadas historicoCapacitadas, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.removeHistoricoCapacitadas",
				historicoCapacitadas);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#removeHistoricoCapacitadasDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadasDetalle, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeHistoricoCapacitadasDetalle(HistoricoCapacitadasDetalle historicoCapacitadasDetalle, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.removeHistoricoCapacitadasDetalle",
				historicoCapacitadasDetalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#removeHistoricoCursoDictado(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictado, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeHistoricoCursoDictado(HistoricoCursoDictado historicoCursoDictado, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.removeHistoricoCursoDictado",
				historicoCursoDictado);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#removeHistoricoCursoDictadoDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictadoDetalle, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeHistoricoCursoDictadoDetalle(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.removeHistoricoCursoDictadoDetalle",
				historicoCursoDictadoDetalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#removePlanillaProgramacionCurso(biz.belcorp.ssicc.edu.dao.model.PlanillaProgramacionCurso, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removePlanillaProgramacionCurso(PlanillaProgramacionCurso planillaProgramacionCurso, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.removePlanillaProgramacionCurso",
				planillaProgramacionCurso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#updateHistoricoAptas(biz.belcorp.ssicc.edu.dao.model.HistoricoAptas, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateHistoricoAptas(HistoricoAptas historicoAptas, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.updateHistoricoAptas",
				historicoAptas);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#updateHistoricoCapacitadas(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadas, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateHistoricoCapacitadas(HistoricoCapacitadas historicoCapacitadas, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.updateHistoricoCapacitadas",
				historicoCapacitadas);
		getSqlMapClientTemplate().delete(
				"edu.MantenimientoEDUSQL.deleteConsultoraPlanillaNoRegistrada",
				historicoCapacitadas);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#updateHistoricoCapacitadasDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadasDetalle, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateHistoricoCapacitadasDetalle(HistoricoCapacitadasDetalle historicoCapacitadasDetalle, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.updateHistoricoCapacitadasDetalle",
				historicoCapacitadasDetalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#updateHistoricoCursoDictado(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictado, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateHistoricoCursoDictado(HistoricoCursoDictado historicoCursoDictado, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.updateHistoricoCursoDictado",
				historicoCursoDictado);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#updateHistoricoCursoDictado(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictado, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateHistoricoCursoDictadoWs(HistoricoCursoDictado historicoCursoDictado, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.updateHistoricoCursoDictadoWs",
				historicoCursoDictado);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#updateHistoricoCursoDictadoDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictadoDetalle, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateHistoricoCursoDictadoDetalle(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.updateHistoricoCursoDictadoDetalle",
				historicoCursoDictadoDetalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#updatePlanillaProgramacionCurso(biz.belcorp.ssicc.edu.dao.model.PlanillaProgramacionCurso, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updatePlanillaProgramacionCurso(PlanillaProgramacionCurso planillaProgramacionCurso, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.updatePlanillaProgramacionCurso",
				planillaProgramacionCurso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegistroAsistenciaDAO#getCapacitadasTemporal(biz.belcorp.ssicc.edu.dao.model.DetallePlanilla)
	 */
	public List getCapacitadasTemporal(DetallePlanilla detallePlanilla) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getCapacitadasTemporal",
				detallePlanilla);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegistroAsistenciaDAO#insertCapacitadasTemporal(biz.belcorp.ssicc.edu.dao.model.DetallePlanilla, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertCapacitadasTemporal(DetallePlanilla detallePlanilla, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUSQL.insertCapacitadasTemporal",
				detallePlanilla);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegistroAsistenciaDAO#removeCapacitadasTemporal(biz.belcorp.ssicc.edu.dao.model.DetallePlanilla, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeCapacitadasTemporal(DetallePlanilla detallePlanilla, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.removeCapacitadasTemporal",
				detallePlanilla);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegistroAsistenciaDAO#updateCapacitadasTemporal(biz.belcorp.ssicc.edu.dao.model.DetallePlanilla, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateCapacitadasTemporal(DetallePlanilla detallePlanilla, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.updateCapacitadasTemporal",
				detallePlanilla);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegistroAsistenciaDAO#getHistoricoAptasPlanilla(biz.belcorp.ssicc.edu.dao.model.DetallePlanilla)
	 */
	public List getHistoricoAptasPlanilla(DetallePlanilla detallePlanilla) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getHistoricoAptasPlanilla",
				detallePlanilla);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegistroAsistenciaDAO#getMaxCursoCapacitacionDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadasDetalle)
	 */
	public String getMaxCursoCapacitacionDetalle(HistoricoCapacitadasDetalle historicoCapacitadasDetalle) {
		String codigo = (String)getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUSQL.getMaxCursoCapacitacionDetalle",
				historicoCapacitadasDetalle);
		if (codigo==null) codigo="";
		return codigo;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegistroAsistenciaDAO#getMaxCursoCapacitacionDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadasDetalle)
	 */
	public String existClienteCursoDictadoDetalle(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle) {
		String codigo = (String)getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUSQL.existClienteCursoDictadoDetalle",
				historicoCursoDictadoDetalle);
		return codigo;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegistroAsistenciaDAO#getMaxCursoCapacitacionDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadasDetalle)
	 */
	public String existClienteCursoCapacitadaDetalle(HistoricoCapacitadasDetalle historicoCapacitadasDetalle) {
		String codigo = (String)getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUSQL.existClienteCursoCapacitadaDetalle",
				historicoCapacitadasDetalle);
		return codigo;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegistroAsistenciaDAO#getPlanillaCursoDictadoDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictadoDetalle)
	 */
	public List getPlanillaCursoDictadoDetalle(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getPlanillaCursoDictadoDetalle",
				historicoCursoDictadoDetalle);
		return resultado;
	}
	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegistroAsistenciaDAO#getHistoricoPlanillaProgramadas(java.util.Map)
	 */
	public List getHistoricoPlanillaProgramadas(Map criteria) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getHistoricoPlanillaProgramadas",
				criteria);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegistroAsistenciaDAO#getHistoricoPlanillaCapacitadas(java.util.Map)
	 */
	public List getHistoricoPlanillaCapacitadas(Map criteria) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getHistoricoPlanillaCapacitadas",
				criteria);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegistroAsistenciaDAO#getHistoricoCursoDictadoCapacitadas(java.util.Map)
	 */
	public List getHistoricoCursoDictadoCapacitadas(Map criteria) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getHistoricoCursoDictadoCapacitadas",
				criteria);
		return resultado;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#updateHistoricoPlanillaInstructora(biz.belcorp.ssicc.edu.dao.model.HistoricoPlanillaInstructora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateHistoricoPlanillaInstructora(HistoricoPlanillaInstructora historicoPlanillaInstructora, Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.updateHistoricoPlanillaInstructora",
				historicoPlanillaInstructora);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getHistoricoPlanillaInstructora(biz.belcorp.ssicc.edu.dao.model.HistoricoPlanillaInstructora)
	 */
	public List getHistoricoPlanillaInstructora(HistoricoPlanillaInstructora historicoPlanillaInstructora) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getHistoricoPlanillaInstructora",
				historicoPlanillaInstructora);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegistroAsistenciaDAO#getSituacionActualHistoricoAptas(biz.belcorp.ssicc.edu.dao.model.HistoricoAptas)
	 */
	public List getSituacionActualHistoricoAptas(HistoricoAptas historicoAptas) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getSituacionActualHistoricoAptas",
				historicoAptas);
		return resultado;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegistroAsistenciaDAO#verificarCampannaRegistroAsistencia(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictado, java.util.List)
	 */
	public boolean verificarCampannaRegistroAsistencia(HistoricoCursoDictado cursoDictado, List listaUsuarios) {
		for(int i=0;i<listaUsuarios.size();i++){
			HistoricoCursoDictadoDetalle tempo = new HistoricoCursoDictadoDetalle();			
			tempo = (HistoricoCursoDictadoDetalle)listaUsuarios.get(i);
			Map criteria = new HashMap();
			criteria.put("codigoPais", tempo.getCodigoPais());
			criteria.put("codigoEmpresa", tempo.getCodigoEmpresa());
			criteria.put("codigoCurso", tempo.getCodigoCurso());
			criteria.put("codigoCliente", tempo.getCodigoCliente());
			criteria.put("codigoCampanha", cursoDictado.getCampanhaInicio());
			Integer contador = (Integer)getSqlMapClientTemplate().queryForObject(
					"edu.MantenimientoEDUSQL.verificarCampannaRegistroAsistencia",
					criteria);
			if (contador != null && contador.intValue() > 0) {
				return false;
			}
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegistroAsistenciaDAO#verificarCampannaRegistroAsistencia(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictado, java.util.List)
	 */
	public boolean verificarCampannaRegistroAsistenciaInsert(HistoricoCursoDictado cursoDictado, List listaUsuarios) {
		for(int i=0;i<listaUsuarios.size();i++){
			DetallePlanilla tempo = new DetallePlanilla();			
			tempo = (DetallePlanilla)listaUsuarios.get(i);
			Map criteria = new HashMap();
			criteria.put("codigoPais", tempo.getCodigoPais());
			criteria.put("codigoEmpresa", tempo.getCodigoEmpresa());
			criteria.put("codigoCurso", tempo.getCodigoCurso());
			criteria.put("codigoCliente", tempo.getCodigoCliente());
			criteria.put("codigoCampanha", cursoDictado.getCampanhaInicio());
			Integer contador = (Integer)getSqlMapClientTemplate().queryForObject(
					"edu.MantenimientoEDUSQL.verificarCampannaRegistroAsistencia",
					criteria);
			if (contador != null && contador.intValue() > 0) {
				return false;
			}
		}
		return true;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegistroAsistenciaDAO#getSituacionActualExoneradoHistoricoAptas(biz.belcorp.ssicc.edu.dao.model.HistoricoAptas)
	 */
	public List getSituacionActualExoneradoHistoricoAptas(HistoricoAptas historicoAptas){
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getSituacionActualExoneradoHistoricoAptas",
				historicoAptas);
		return resultado;
	}

	/**
	 * @author sbuchelli
	 * @param codigoPais
	 * @param codigoEmpresa
	 * @param codigoCurso
	 * @param codigoRegion
	 * @param codigoInstructora
	 * @param campanhaInicio
	 * @return Lista de Planillas por instructoras con su respectiva zona univoca
	 * 
	 * */
	public List getHistoricoPlanillaInstructoraZona(HistoricoPlanillaInstructora historicoPlanillaInstructora) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getHistoricoPlanillaInstructoraZona",
				historicoPlanillaInstructora);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegistroAsistenciaDAO#getCursosExoneracionMasiva(biz.belcorp.ssicc.edu.dao.model.HistoricoAptas)
	 */
	public List getCursosExoneracionMasiva(HistoricoAptas historicoAptas) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUSQL.getCursosExoneracionMasiva",
				historicoAptas);
		return resultado;
	}
}
