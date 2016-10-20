/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 */
public interface ProcesoPEDCargaMasivaFletesDAO extends DAO {

	/**
	 * Metodo que limpia datos de la tabla temporal
	 * @param codUsuario
	 */
	public void deleteTemporalCargaMasivaFletes(String codUsuario);

	/**
	 * Metodo que inserta los datos de un archivo CSV a la tabla temporal
	 * @param params
	 */
	public void insertaTemporalCargaMasivaFletes(Map params);

	/**
	 * Metodo que valida los datos cargados en la tabla temporal
	 * @param params
	 */
	public void executeValidarCargaMasivaFlete(Map params);

	/**
	 * Metodo que devuelve los resultados de la validacion
	 * @param params
	 * @return
	 */
	public List getCargaMasivaFleteList(Map params);

	/**
	 * Metodo que limpia la tabla de configuracion de detalle de fletes
	 */
	public void deleteDetalleConfiguracionFlete();

	/**
	 * Metodo que limpia la tabla de configuracion de cabecera de fletes
	 */
	public void deleteCabeceraConfiguracionFlete();

	/**
	 * Metodo que inserta la configuracion masiva de fletes 
	 * @param params
	 */
	public void executeActualizarConfiguracionFlete(Map params);
	
	/**
	 * Elimina la tabla temporal 
	 */
	public void deleteTemporalCargaMasivaMontoMinimo(String codUsuario);
	
	/**
	 *Inserta en la tabla temporal
	 */
	public void insertaTemporalCargaMasivaMontoMinimo(Map params);
	
	/**
	 * Executa el validar 
	 */
	public void executeValidarCargaMasivaMontoMinimo(Map params);
	
	/**
	 * Lista de registros con error
	 * @return
	 */
	public List getCargaMasivaMontoMinimoList(Map params);
	
	/**
	 * @param params
	 */
	public void executeActualizarCargaMasivaMontoMinimo(Map params);
	
}
