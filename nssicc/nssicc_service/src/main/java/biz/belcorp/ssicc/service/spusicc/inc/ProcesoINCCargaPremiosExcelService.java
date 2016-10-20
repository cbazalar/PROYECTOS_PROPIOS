package biz.belcorp.ssicc.service.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;
/**
 * @author <a href="mailto:nlopez@csigcomt.com">Nicols Lpez</a>
 *
 */
public interface ProcesoINCCargaPremiosExcelService  extends Service{

	/**
	 * Retorna la lista de Concursos Creados  Vigentes
	 * @return
	 */
	public List getListConcursoCreadosVigentes();
	
	/**
	 * Valida si el archivo cumple con el formato
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	boolean validarArchivoExcel(Map criteria)throws Exception;
	
	/**
	 * Permite la carga del archivo Excel
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	public List cargarArchivoExcel(Map criteria) throws Exception;
	
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
	 * Permite cargar los premios de la tabla temporal
	 */
	public void executeProcesoCargaPremiosExcel(Map criteria);

	/**
	 * @param criteria
	 * Permite eliminar todos los registros de la tabla temporal
	 */
	public void eliminarRegistrosTablaTempINCCargaPremios(Map criteria);

}
