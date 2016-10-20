package biz.belcorp.ssicc.service.edu;

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
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author peextrvela
 *
 */
public interface ProcesoEDURegistroAsistenciaService extends Service{
	             
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
	 * Inserta la asistencia de las consultoras a un determinado curso de capacitacion
	 * @param cursoDictado
	 * 			objeto CursoDictado poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora
	 */
	public boolean insertRegistroAsistencia(HistoricoCursoDictado cursoDictado,List listaTemporal, Usuario usuario) throws Exception;

	/**
	 * Actualiza los registros en la tabla temporal EDU_TMP_HISTO_CURSO_DICTA
	 * @param cursoDictado
	 * 			objeto CursoDictado poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora
	 * @param listaUsuarios
	 *			Lista de todos los clientes
	 * @param listaCapacitados
	 *			Lista de los clientes Capacitados
	 * @param listaPromedio
	 *			Lista de los promedios de los clientes Capacitados 
	 */
	public boolean inserDetalleTemporal(HistoricoCursoDictado cursoDictado, List listaUsuarios,String[] listaCapacitados,String[] listaPromedio, Usuario usuario) throws Exception;	

	/**
	 * Devuelve una lista de registros aptas para ser capacitadas por planilla
	 * @param detallePlanilla
	 * 			objeto HetallePlanilla poblado
	 */
	public List getHistoricoAptasPlanilla(DetallePlanilla detallePlanilla);

	/**
	 * Actualiza los registros en la tabla EDU_HISTO_CAPAC_DETAL
	 * @param cursoDictado
	 * 			objeto CursoDictado poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora
	 * @param listaUsuarios
	 *			Lista de todos los clientes
	 * @param listaPromedio
	 *			Lista de los promedios de los clientes Capacitados 
	 */
	public boolean updatePlanillaRegistroAsistencia(HistoricoCursoDictado cursoDictado, List listaUsuarios,
			String[] listaCapacitados,String[] listaPromedio, Usuario usuario)  throws Exception;	
	
	/**
	 * Actualiza la asistencia de las consultoras a un determinado curso de capacitacion
	 * @param cursoDictado
	 * 			objeto CursoDictado poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora
	 */
	public boolean updateRegistroAsistencia(HistoricoCursoDictado cursoDictado, Usuario usuario) throws Exception;

	/**
	 * Actualiza la asistencia de las consultoras a un determinado curso de capacitacion
	 * @param cursoDictado
	 * 			objeto CursoDictado poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditora
	 */
	public boolean updateRegistroAsistenciaWs(HistoricoCursoDictado cursoDictado, Usuario usuario);
	
	/**
	 * Devuelve una lista de registros de detalles de planillas pertenecientes a un determinado dictado de curso
	 * @param historicoCursoDictadoDetalle
	 * 			objeto HistoricoCursoDictadoDetalle poblado
	 */
	public List getPlanillaCursoDictadoDetalle(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle);
	
	/**
	 * Devuelve Historico d eplanilla
	 * @param criteria
	 * @return
	 */
	public List getHistoricoPlanillaCapacitadas(Map criteria);
	
	/**
	 * Devuelve las planillas en que se ha capacitado
	 * @param criteria
	 * @return
	 */
	public List getHistoricoPlanillaProgramadas(Map criteria);
		
	/**
	 * devuelve lista de os curso de dictado
	 * @param criteria
	 * @return
	 */
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
	 * Situacion actual en que se encuentar una consultora 
	 * @param HistoricoAptas
	 * 			objeto HistoricoAptas .
	 * 
	 */
	
	public List getSituacionActualHistoricoAptas(HistoricoAptas historicoAptas);

	/**
	 * Situacion de la consulrora en todos los cursos para exonerar
	 * @param historicoAptas
	 * @return
	 */
	public List getSituacionActualExoneradoHistoricoAptas(HistoricoAptas historicoAptas);

	/**
	 * Lista de cursos en que va a exonerar
	 * @param historicoAptas
	 * @return
	 */
	public List getCursosExoneracionMasiva(HistoricoAptas historicoAptas);

}
