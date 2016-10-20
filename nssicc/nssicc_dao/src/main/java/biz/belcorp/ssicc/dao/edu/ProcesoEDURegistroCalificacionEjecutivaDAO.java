package biz.belcorp.ssicc.dao.edu;

import java.util.List;

import biz.belcorp.ssicc.dao.edu.model.HistoricoCapacitadasDetalle;
import biz.belcorp.ssicc.dao.edu.model.HistoricoCursoDictado;
import biz.belcorp.ssicc.dao.edu.model.HistoricoCursoDictadoDetalle;
import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDURegistroCalificacionEjecutivaDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:rvela@belcorp.biz">Robinson Vela Bardales</a>
 */


public interface ProcesoEDURegistroCalificacionEjecutivaDAO extends DAO {


	/** 
	 * Devuelve una lista de registros de cabeceras de cursos dictados
	 * @param historicoCursoDictado
	 * 			objeto HistoricoCursoDictado poblado
	 */
	public List getHistoricoCursoDictado(HistoricoCursoDictado historicoCursoDictado);

	/** 
	 * Devuelve una lista de registros de detalle de capacitadas
	 * @param historicoCapacitadasDetalle
	 * 			objeto HistoricoCapacitadasDetalle poblado
	 */
	public List getHistoricoCapacitadasDetalle(HistoricoCapacitadasDetalle historicoCapacitadasDetalle);
	
	/**
	 * Actualiza la Evaluacin efectuada a la Instructora
	 * @param historicoCapacitadasDetalle
	 * 			objeto HistoricoCapacitadasDetalle poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void updateEvaluacionInstructoraCapacitadas(HistoricoCapacitadasDetalle historicoCapacitadasDetalle, Usuario usuario);

	/**
	 * Actualiza la Evaluacin efectuada a la Instructora
	 * @param historicoCursoDictadoDetalle
	 * 			objeto HistoricoCursoDictadoDetalle poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void updateDetalleDictadoCalificacionEjecutiva(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle, Usuario usuario);

	/**
	 * Actualiza la Evaluacin efectuada a la Instructora en el Resumen de Dictado
	 * @param historicoCursoDictado
	 * 			objeto HistoricoCursoDictado poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void updateDictadoCalificacionEjecutiva(HistoricoCursoDictado historicoCursoDictado, Usuario usuario);

}
