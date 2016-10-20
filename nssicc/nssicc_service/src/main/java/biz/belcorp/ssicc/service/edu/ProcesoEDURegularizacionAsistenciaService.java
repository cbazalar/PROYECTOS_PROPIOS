package biz.belcorp.ssicc.service.edu;

import java.util.Map;

import biz.belcorp.ssicc.dao.edu.model.HistoricoAptas;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author 
 *
 */
public interface ProcesoEDURegularizacionAsistenciaService extends Service{

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
	 * carga archivo excel
	 * @param criteria
	 * @throws Exception
	 */
	public void cargarArchivoExcel(Map criteria) throws Exception;

	/**
	 * Exonera masivamente a consultoras por curso
	 * @param historicoAptas
	 * @param usuario
	 * @return
	 */
	public String confirmarExoneracionMasiva(HistoricoAptas historicoAptas,
			Usuario usuario);

}
