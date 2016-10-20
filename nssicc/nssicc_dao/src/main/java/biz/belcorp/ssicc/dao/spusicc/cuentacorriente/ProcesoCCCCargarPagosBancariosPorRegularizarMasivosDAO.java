/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.EstructuraPagosBancariosPorRegularizarMasivos;

/**
 * @author pejflorencio
 *
 */
public interface ProcesoCCCCargarPagosBancariosPorRegularizarMasivosDAO extends DAO {

	/**
	 * Metodo que obtiene la ruta del directorio temporal
	 * @param datos
	 * @return
	 */
	public String obtenerPathUpload(Map datos);
	
	/**
	 * Metodo que inserta en la tabla temporal
	 * @param estructura
	 */
	public void insertEstructuraPagosBancariosPorRegularizarMasivos(EstructuraPagosBancariosPorRegularizarMasivos estructura);
	
	
	/**
	 * Metodo que borra la tabla temporal de la Caga Fuente Venta Prevista
	 */
	public void deleteTablasCargaPagosBancariosPorRegularizarMasivos();
	
	/**
	 * Metodo que valida la estructura y data del archivo
	 * @param criteria
	 */
	public void executeValidarPagosBancariosPorRegularizarMasivos(Map criteria);
	
	
	
	/**
	 * Metodo que lista los errores de la carga
	 * @return
	 */
	public List getErroresCargaPagosBancariosPorRegularizarMasivos();
    
	
	/**
	 * Metodo que valida la estructura y data del archivo
	 * @param criteria
	 */
	public void executeProcesarPagosBancariosPorRegularizarMasivos(Map datos);
	
}
