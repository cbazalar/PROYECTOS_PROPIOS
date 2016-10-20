package biz.belcorp.ssicc.service.edu;

import java.util.List;

import biz.belcorp.ssicc.dao.edu.model.HistoricoCapacitadasDetalle;
import biz.belcorp.ssicc.dao.edu.model.HistoricoCursoDictado;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author peextrvela
 *
 */
public interface ProcesoEDURegistroCalificacionEjecutivaService extends Service{
	             
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
	public void updateRegistroCalificacionEjecutiva(HistoricoCursoDictado cursoDictado, List listaUsuarios,
				String[] listaPromedio, Usuario usuario)  throws Exception;	

}
