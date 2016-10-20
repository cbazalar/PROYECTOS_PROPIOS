/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.HashMap;
import java.util.Map;


/**
 * DAO para la recepcion de maestros del directorio corporativo
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
public interface InterfazDIRDAO {

	/**
	 * Metodo que ejecuta el procedimiento almacenado de recepcion del archivo de Clientes
	 * @param parms
	 */
	void executeInterfazDIRRecepcionarClientes(Map map);

	/**
	 * Verifca si una region existe.
	 * @param criteria
	 * @return
	 */
	int verificarRegionExiste(HashMap criteria);

	/**
	 * Actualiza los datos de una region
	 * @param criteria
	 */
	void updateRegion(HashMap criteria);

	/**
	 * Inserta una nueva region
	 * @param criteria
	 */
	void insertRegion(HashMap criteria);

	/**
	 * Verifca si una zona existe.
	 * @param criteria
	 * @return
	 */
	int verificarZonaExiste(HashMap criteria);
	
	/**
	 * Actualiza los datos de una zona
	 * @param criteria
	 */
	void updateZona(HashMap criteria);

	/**
	 * Inserta una nueva zona
	 * @param criteria
	 */
	void insertZona(HashMap criteria);

	/**
	 * Verifca si un Control de Facturacion existe.
	 * @param criteria
	 * @return
	 */
	int verificarControlFacturacionExiste(HashMap criteria);

	/**
	 * Actualiza un control de facturacion
	 * @param criteria
	 */
	void updateControlFacturacion(HashMap criteria);

	/**
	 * Inserta un control de facturacion
	 * @param criteria
	 */
	void insertControlFacturacion(HashMap criteria);

	/**
	 * Verifica si una campaa existe
	 * @param criteria
	 * @return
	 */
	int verificarCampanyaExiste(HashMap criteria);

	/**
	 * Actualiza una campaa
	 * @param criteria
	 */
	void updateCampanya(HashMap criteria);

	/**
	 * Inserta una campaa
	 * @param criteria
	 */
	void insertCampanya(HashMap criteria);

	/**
	 * Elimina los datos de un proceso anterior
	 * @param params
	 */
	void deleteInterfazDIRRecepcionarClientesTemporal();

	/**
	 * Inserta un registro en la tabla temporal de clientes
	 * @param params
	 */
	void insertInterfazDIRRecepcionarClientesTemporal(Map params);
	
}
