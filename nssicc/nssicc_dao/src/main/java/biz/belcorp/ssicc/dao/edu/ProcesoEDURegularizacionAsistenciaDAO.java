package biz.belcorp.ssicc.dao.edu;

import java.util.Map;

import biz.belcorp.ssicc.dao.edu.model.HistoricoAptas;
import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDURegularizacionAsistenciaDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 */


public interface ProcesoEDURegularizacionAsistenciaDAO extends DAO {

	/**Regulariza asistencia
	 * @param historicoAptas
	 * @param usuario
	 * @return
	 */
	public String confirmarRegularizacion(HistoricoAptas historicoAptas, Usuario usuario);

	/**
	 * Elima la asistencia
	 * @param historicoAptas
	 * @param usuario
	 * @return
	 */
	public String eliminarAsistencia(HistoricoAptas historicoAptas, Usuario usuario);
	
	/**
	 * Exonera a una consultora
	 * @param historicoAptas
	 * @param usuario
	 * @return
	 */
	public String confirmarExoneracion(HistoricoAptas historicoAptas, Usuario usuario);
	
	/**
	 * insrta consuloras enviadas desde un excel a un temporal para luego ser procesada
	 * @param criteria
	 */
	public void inserTemporalExoneradas(Map criteria);

	/**
	 * elimina registros tabla temporal
	 * @param criteria
	 */
	public void deleteTemporalExoneradas(Map criteria);
	
	/**
	 * Exonera masivamente a consultoras por curso
	 * @param historicoAptas
	 * @param usuario
	 * @return
	 */
	public String confirmarExoneracionMasiva(HistoricoAptas historicoAptas,
			Usuario usuario);
}	

