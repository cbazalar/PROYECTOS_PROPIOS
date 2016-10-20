package biz.belcorp.ssicc.dao.edu;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.edu.model.DetallePlanilla;
import biz.belcorp.ssicc.dao.edu.model.HistoricoAptas;
import biz.belcorp.ssicc.dao.edu.model.HistoricoCapacitadas;
import biz.belcorp.ssicc.dao.edu.model.HistoricoCapacitadasDetalle;
import biz.belcorp.ssicc.dao.edu.model.HistoricoCursoDictado;
import biz.belcorp.ssicc.dao.edu.model.HistoricoCursoDictadoDetalle;
import biz.belcorp.ssicc.dao.edu.model.HistoricoPlanillaInstructora;
import biz.belcorp.ssicc.dao.edu.model.PlanillaProgramacionCurso;
import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDURegistroAsistenciaDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:rvela@belcorp.biz">Robinson Vela Bardales</a>
 */


public interface ProcesoEDURegistroAsistenciaDAO extends DAO {


	/**
	 * Devuelve una lista de registros de planillas de programacion
	 * @param planillaProgramacionCurso
	 * 			objeto PlanillaProgramacionCurso poblado
	 */
	public List getPlanillaProgramacionCurso(PlanillaProgramacionCurso planillaProgramacionCurso);

	/**
	 * Devuelve una lista de registros de detalles de cursos dictados
	 * @param historicoCursoDictadoDetalle
	 * 			objeto HistoricoCursoDictadoDetalle poblado
	 */
	public List getHistoricoCursoDictadoDetalle(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle);

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
	 * Devuelve una lista de registros de cabeceras de capacitadas
	 * @param historicoCapacitadas
	 * 			objeto HistoricoCapacitadas poblado
	 */
	public List getHistoricoCapacitadas(HistoricoCapacitadas historicoCapacitadas);

	/**
	 * Devuelve una lista de registros de aptas
	 * @param historicoAptas
	 * 			objeto HistoricoAptas poblado
	 */
	public List getHistoricoAptas(HistoricoAptas historicoAptas);

	/**
	 * Insertar un registro de planilla de programacion
	 * @param planillaProgramacionCurso
	 * 			objeto PlanillaProgramacionCurso poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void insertPlanillaProgramacionCurso(PlanillaProgramacionCurso planillaProgramacionCurso, Usuario usuario);

	/**
	 * Modifica un registro de planilla de programacion
	 * @param planillaProgramacionCurso
	 * 			objeto PlanillaProgramacionCurso poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void updatePlanillaProgramacionCurso(PlanillaProgramacionCurso planillaProgramacionCurso, Usuario usuario);

	/**
	 * Elimina Fisicamente un registro o grupo de ellos de la entidad planilla de programacion
	 * @param planillaProgramacionCurso
	 * 			objeto PlanillaProgramacionCurso poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void removeFisicoPlanillaProgramacionCurso(PlanillaProgramacionCurso planillaProgramacionCurso, Usuario usuario);

	/**
	 * Elimina Logicamente un registro o grupo de ellos de la entidad planilla de programacion
	 * @param planillaProgramacionCurso
	 * 			objeto PlanillaProgramacionCurso poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void removePlanillaProgramacionCurso(PlanillaProgramacionCurso planillaProgramacionCurso, Usuario usuario);

	/**
	 * Insertar un registro en el Historico de Cursos
	 * @param historicoCursoDictadoDetalle
	 * 			objeto HistoricoCursoDictadoDetalle poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void insertHistoricoCursoDictadoDetalle(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle, Usuario usuario);

	/**
	 * Modifica un registro en el Historico de Cursos
	 * @param historicoCursoDictadoDetalle
	 * 			objeto HistoricoCursoDictadoDetalle poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void updateHistoricoCursoDictadoDetalle(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle, Usuario usuario);

	/**
	 * Elimina Fisicamente un registro en el Historico de Cursos
	 * @param historicoCursoDictadoDetalle
	 * 			objeto HistoricoCursoDictadoDetalle poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void removeFisicoHistoricoCursoDictadoDetalle(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle, Usuario usuario);

	/**
	 * Elimina Logicamente un registro en el Historico de Cursos
	 * @param historicoCursoDictadoDetalle
	 * 			objeto HistoricoCursoDictadoDetalle poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void removeHistoricoCursoDictadoDetalle(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle, Usuario usuario);

	/**
	 * Insertar un registro en el Historico de Capacitadas
	 * @param historicoCapacitadas
	 * 			objeto HistoricoCapacitadas poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void insertHistoricoCapacitadas(HistoricoCapacitadas historicoCapacitadas, Usuario usuario);

	/**
	 * Modifica un registro en el Historico de Capacitadas
	 * @param historicoCapacitadas
	 * 			objeto HistoricoCapacitadas poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void updateHistoricoCapacitadas(HistoricoCapacitadas historicoCapacitadas, Usuario usuario);

	/**
	 * Elimina Fisicamente un registro en el Historico de Capacitadas
	 * @param historicoCapacitadas
	 * 			objeto HistoricoCapacitadas poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void removeFisicoHistoricoCapacitadas(HistoricoCapacitadas historicoCapacitadas, Usuario usuario);

	/**
	 * Elimina Logicamente un registro en el Historico de Capacitadas
	 * @param historicoCapacitadas
	 * 			objeto HistoricoCapacitadas poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void removeHistoricoCapacitadas(HistoricoCapacitadas historicoCapacitadas, Usuario usuario);

	/**
	 * Insertar un registro en el Historico de Aptas
	 * @param historicoAptas
	 * 			objeto HistoricoAptas poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void insertHistoricoAptas(HistoricoAptas historicoAptas, Usuario usuario);

	/**
	 * Modifica un registro en el Historico de Aptas
	 * @param historicoAptas
	 * 			objeto HistoricoAptas poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void updateHistoricoAptas(HistoricoAptas historicoAptas, Usuario usuario);

	/**
	 * Elimina Logicamente un registro en el Historico de Aptas
	 * @param historicoAptas
	 * 			objeto HistoricoAptas poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void removeHistoricoAptas(HistoricoAptas historicoAptas, Usuario usuario);

	/**
	 * Elimina Fisicamente un registro en el Historico de Aptas
	 * @param historicoAptas
	 * 			objeto HistoricoAptas poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void removeFisicoHistoricoAptas(HistoricoAptas historicoAptas, Usuario usuario);

	/**
	 * Insertar un registro en el Historico de Dictados
	 * @param historicoAptas
	 * 			objeto HistoricoAptas poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void insertHistoricoCursoDictado(HistoricoCursoDictado historicoCursoDictado, Usuario usuario);

	/**
	 * Modifica un registro en el Historico de Dictados
	 * @param historicoAptas
	 * 			objeto HistoricoAptas poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void updateHistoricoCursoDictado(HistoricoCursoDictado historicoCursoDictado, Usuario usuario);

	/**
	 * Modifica un registro en el Historico de Dictados
	 * @param historicoAptas
	 * 			objeto HistoricoAptas poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void updateHistoricoCursoDictadoWs(HistoricoCursoDictado historicoCursoDictado, Usuario usuario);
	
	/**
	 * Elimina Logicamente un registro en el Historico de Dictados
	 * @param historicoAptas
	 * 			objeto HistoricoAptas poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void removeHistoricoCursoDictado(HistoricoCursoDictado historicoCursoDictado, Usuario usuario);

	/**
	 * Elimina Fisicamente un registro en el Historico de Dictados
	 * @param historicoAptas
	 * 			objeto HistoricoAptas poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void removeFisicoHistoricoCursoDictado(HistoricoCursoDictado historicoCursoDictado, Usuario usuario);

	/**
	 * Insertar un registro en el Historico de Capacitaciones - Detalle
	 * @param historicoCapacitadasDetalle
	 * 			objeto HistoricoCapacitadasDetalle poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void insertHistoricoCapacitadasDetalle(HistoricoCapacitadasDetalle historicoCapacitadasDetalle, Usuario usuario);

	/**
	 * Modifica un registro en el Historico de Capacitaciones - Detalle
	 * @param historicoCapacitadasDetalle
	 * 			objeto HistoricoCapacitadasDetalle poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void updateHistoricoCapacitadasDetalle(HistoricoCapacitadasDetalle historicoCapacitadasDetalle, Usuario usuario);

	/**
	 * Elimina Logicamente un registro en el Historico de Capacitaciones - Detalle
	 * @param historicoCapacitadasDetalle
	 * 			objeto HistoricoCapacitadasDetalle poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void removeHistoricoCapacitadasDetalle(HistoricoCapacitadasDetalle historicoCapacitadasDetalle, Usuario usuario);

	/**
	 * Elimina Fisicamente un registro en el Historico de Capacitaciones - Detalle
	 * @param historicoCapacitadasDetalle
	 * 			objeto HistoricoCapacitadasDetalle poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void removeFisicoHistoricoCapacitadasDetalle(HistoricoCapacitadasDetalle historicoCapacitadasDetalle, Usuario usuario);

	/**
	 * Devuelve el ltimo cdigo de curso registrado de la entidad Historico Cursos Dictados
	 * @param historicoCursoDictado
	 * 			objeto HistoricoCursoDictado poblado de la empresa comercializadora
	 */
	public String getMaxHistoricoCursoDictado(HistoricoCursoDictado historicoCursoDictado);

	public String existClienteCursoDictadoDetalle(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle);
	
	public String existClienteCursoCapacitadaDetalle(HistoricoCapacitadasDetalle historicoCapacitadasDetalle);	
	/**
	 * Insertar un registro en el Historico de Capacitaciones Temporal- Detalle
	 * @param detallePlanilla
	 * 			objeto HetallePlanilla poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void insertCapacitadasTemporal(DetallePlanilla detallePlanilla, Usuario usuario);

	/**
	 * Elimina un registro en forma Logica del Historico de Capacitaciones Temporal- Detalle
	 * @param detallePlanilla
	 * 			objeto HetallePlanilla poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void removeCapacitadasTemporal(DetallePlanilla detallePlanilla, Usuario usuario);

	/**
	 * Actualiza un registro en forma Logica del Historico de Capacitaciones Temporal- Detalle
	 * @param detallePlanilla
	 * 			objeto HetallePlanilla poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public void updateCapacitadasTemporal(DetallePlanilla detallePlanilla, Usuario usuario);

	/**
	 * Devuelve una lista de registros en forma Logica del Historico de Capacitaciones Temporal- Detalle
	 * @param detallePlanilla
	 * 			objeto HetallePlanilla poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora 
	 */
	public List getCapacitadasTemporal(DetallePlanilla detallePlanilla);

	/**
	 * Devuelve una lista de registros aptas para ser capacitadas por planilla
	 * @param detallePlanilla
	 * 			objeto HetallePlanilla poblado
	 */
	public List getHistoricoAptasPlanilla(DetallePlanilla detallePlanilla);

	/**
	 * Devuelve el ltimo cdigo de curso registrado de la entidad Historico Capacitadas
	 * @param historicoCapacitadasDetalle
	 * 			objeto HistoricoCapacitadasDetalle poblado de la empresa comercializadora
	 */
	public String getMaxCursoCapacitacionDetalle(HistoricoCapacitadasDetalle historicoCapacitadasDetalle);

	
	/**
	 * Devuelve una lista de registros de detalles de planillas pertenecientes a un determinado dictado de curso
	 * @param historicoCursoDictadoDetalle
	 * 			objeto HistoricoCursoDictadoDetalle poblado
	 */
	public List getPlanillaCursoDictadoDetalle(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle);
	
	public List getHistoricoPlanillaCapacitadas(Map criteria);
	
	public List getHistoricoPlanillaProgramadas(Map criteria);
		
	public List getHistoricoCursoDictadoCapacitadas(Map criteria);

	/**
	 * Modifica las planillas por Instructora
	 * @param historicoPlanillaInstructora
	 * 			objeto HistoricoPlanillaInstructora poblado.
	 * @param usuario
	 * 			objeto Usuario para el registro de Auditora
	 */
	public void updateHistoricoPlanillaInstructora(HistoricoPlanillaInstructora historicoPlanillaInstructora, Usuario usuario);
	
	/**
	 * Lista las planillas por Instructora
	 * @param historicoPlanillaInstructora
	 * 			objeto HistoricoPlanillaInstructora poblado.
	 * @param usuario
	 * 			objeto Usuario para el registro de Auditora
	 */
	public List getHistoricoPlanillaInstructora(HistoricoPlanillaInstructora historicoPlanillaInstructora);

	/**
	 * @param historicoAptas
	 * @return
	 */
	public List getSituacionActualHistoricoAptas(HistoricoAptas historicoAptas);
	
	/**
	 * Verifica que las que la campaa de programacion de las consultoras sea menor a la Campaa de Dictado
	 * @param cursoDictado
	 * @param listaUsuarios
	 * @return
	 */
	public boolean verificarCampannaRegistroAsistencia(HistoricoCursoDictado cursoDictado, List listaUsuarios);
	
	
	/**
	 * Verifica que las que la campaa de programacion de las consultoras sea menor a la Campaa de Dictado
	 * @param cursoDictado
	 * @param listaUsuarios
	 * @return
	 */
	public boolean verificarCampannaRegistroAsistenciaInsert(HistoricoCursoDictado cursoDictado, List listaUsuarios);
	
	/**Situacion de la consultora en todos los cursos
	 * @param historicoAptas
	 * @return
	 */
	public List getSituacionActualExoneradoHistoricoAptas(HistoricoAptas historicoAptas);

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
	public List getHistoricoPlanillaInstructoraZona(HistoricoPlanillaInstructora historicoPlanillaInstructora);

	/**
	 * lista de curos que serviran para la exoneracion masiva
	 * @param historicoAptas
	 * @return
	 */
	public List getCursosExoneracionMasiva(HistoricoAptas historicoAptas);
}
