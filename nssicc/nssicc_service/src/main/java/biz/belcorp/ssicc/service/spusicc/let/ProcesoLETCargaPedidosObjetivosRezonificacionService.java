package biz.belcorp.ssicc.service.spusicc.let;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ProcesoLETCargaPedidosObjetivosRezonificacionService extends Service{

	/**
	 * Valida si existe concursos en el rango de periodos del concurso, tantos concursos vigentes, siguientes o subsiguientes
	 * @param criteria
	 * @return
	 */
	public boolean getValidaExisteConcursoByCodigoPeriodo(Map criteria);

	/**
	 * Obtiene la lista de zonas rezonificadas
	 * @param criteria
	 * @return
	 */
	public List getZonasRezonificacion(Map criteria);

	/**
	 * Validar que regiones estan cerradas por medio de sus zonas
	 * @param oidPais 
	 * @param codigoPeriodo 
	 * @param codigoZona
	 * @param regionesCerradasList 
	 * @return
	 */
	public boolean getValidaRegionesCerradas(Integer oidPais, String codigoPeriodo, String[] codigoZona, List regionesCerradasList);

	/**
	 * Ejecuta el proceso de Carga de Pedidos Objetivos por Rezonificaciones
	 * @param params
	 */
	public void executeCargaPedidosObjetivosRezonificacion(Map params);
	

	/**
	 * Verifica si la zona es nueva o rezonificada
	 * @param criteria
	 * @return
	 */
	public Integer getZonaNuevaRezonificada(Map criteria);
	
	/**
	 * Verifica si la zona es nueva
	 * @param criteria
	 * @return
	 */
	public Integer getZonaNueva(Map criteria);
	
	/**
	 * Validar que las zonas seleccionadas sean nuevas o rezonificadas
	 * @param codigoPais
	 * @param indicadorValidaCargaObjetivos
	 * @param codigoPeriodo
	 * @param codigoMarcaDefault
	 * @param codigoCanalDefault
	 * @param codigoZona
	 * @param codigoZonaError 
	 * @return
	 */
	public boolean getValidaZonaNuevaRezonificada(String codigoPais,String indicadorValidaCargaObjetivos, String codigoPeriodo,String codigoMarcaDefault, String codigoCanalDefault,String[] codigoZona, String codigoZonaError);
}