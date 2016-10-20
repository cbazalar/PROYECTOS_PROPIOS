package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:nlopez@csigcomt.com">Nicols Lpez</a>
 *
 */
public interface ProcesoINCCargaPremiosExcelDAO extends DAO {

	/**
	 * Retorna la lista de Concursos Creados  Vigentes
	 * @return
	 */
	public List getListConcursoCreadosVigentes();
	
	/**
	 * @return Permite validar si el concurso tiene premios
	 */
	public String getValidaEntregaPremios(Map criteria);
	
	/**
	 * @return Retorna la secuencia a usar para la tabla temporal
	 */
	public String getObtenerSecTempCargaPremios();
	
	/**
	 * @param criteria
	 * Permite insertar datos de Excel a Tabla temporal Carga Premios
	 */
	public void executeInsercionTempCargaPremios(Map criteria);

	/**
	 * @param params
	 * Permite ejecutar el proceso de validacin de la carga de premios
	 * en la tabla Temporal
	 */
	public void executeProcesoValidaCargaPremios(Map criteria);
	
	/**
	 * @param criteria
	 * @return La lista de errores de la carga de premios
	 */
	public List getListarErroresCargaPremios(Map criteria);
	
	/**
	 * @param criteria
	 * @return Cantidad de registros de premios existentes
	 */
	public String getValidaExistePremios(Map criteria);
	
	/**
	 * @param criteria
	 * Permite cargar los premios de la tabla temporal
	 */
	public void executeProcesoCargaPremiosExcel(Map criteria);
	
	/**
	 * @param criteria
	 * Permite eliminar todos los registros de la tabla temporal
	 */
	public void eliminarRegistrosTablaTempINCCargaPremios(Map criteria);
	
}