package biz.belcorp.ssicc.dao.edu.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.edu.ProcesoEDURegistroCalificacionEjecutivaDAO;
import biz.belcorp.ssicc.dao.edu.model.HistoricoCapacitadasDetalle;
import biz.belcorp.ssicc.dao.edu.model.HistoricoCursoDictado;
import biz.belcorp.ssicc.dao.edu.model.HistoricoCursoDictadoDetalle;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * @author peextrvela
 *
 */
/**
 * @author peextcbazalar
 *
 */
@Repository("edu.procesoEDURegistroCalificacionEjecutivaDAO")
public class ProcesoEDURegistroCalificacionEjecutivaDAOiBatis extends 
	BaseDAOiBatis implements ProcesoEDURegistroCalificacionEjecutivaDAO{

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
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegistroCalificacionEjecutivaDAO#updateEvaluacionInstructoraCapacitadas(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadasDetalle, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEvaluacionInstructoraCapacitadas(HistoricoCapacitadasDetalle historicoCapacitadasDetalle, Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.updateEvaluacionInstructoraCapacitadas",
				historicoCapacitadasDetalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegistroCalificacionEjecutivaDAO#updateDetalleDictadoCalificacionEjecutiva(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictadoDetalle, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateDetalleDictadoCalificacionEjecutiva(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle, Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.updateDetalleDictadoCalificacionEjecutiva",
				historicoCursoDictadoDetalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURegistroCalificacionEjecutivaDAO#updateDictadoCalificacionEjecutiva(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictado, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateDictadoCalificacionEjecutiva(HistoricoCursoDictado historicoCursoDictado, Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUSQL.updateDictadoCalificacionEjecutiva",
				historicoCursoDictado);
	}
	
}
