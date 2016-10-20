/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 *
 */
public interface ProcesoPEDCargaMasivaFletesService {

	/**
	 * Metodo que permite cargar el archivo CSV a una tabla temporal
	 * @param lineas
	 * @param usuario
	 * @return
	 */
	public Map cargarArchivoExcelCSV(List lineas, Usuario usuario);

	/**
	 * Metodo que valida los datos cargados en la tabla temporal
	 * @param params
	 * @return
	 */
	public List executeValidarCargaMasivaFlete(Map params);

	/**
	 * Metodo que actualiza la configuracion de fletes
	 * @param params
	 */
	public void executeActualizarCargaMasivaFlete(Map params);
	
	/**
	 * metodo que carga archivos csv en tabla temporal
	 * @param usuario
	 * @return
	 */
	public Map cargarArchivoCSVMontoMinimo(List lineas, Usuario usuario);
	
	/**
	 * valida carga masiva Monto minimo
	 * @return
	 */
	public List executeValidarCargaMasivaMontoMinimo(Map params);
	
	/**
	 * Actualiza monto minimo
	 */
	public void executeActualizarCargaMasivaMontoMinimo(Map params);

}
