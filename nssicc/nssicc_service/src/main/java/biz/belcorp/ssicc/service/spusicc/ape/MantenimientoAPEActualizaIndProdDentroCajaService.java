package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPEActualizaIndProdDentroCajaService.java.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez</a>
 */
public interface MantenimientoAPEActualizaIndProdDentroCajaService extends Service{

	/**
	 * Devuelve los datos de la tabla APE_PRODU_IND_CAJA
	 * @param criteria
	 * @return
	 */
	public List getIndProductoCajaDentroList(Map criteria);
	
	/**
	 * Obtiene el oid de la tabla APE_PRODU_IND_CAJA
	 * @return
	 */
	public String getOidIndProductoCajaDentro(Map criteria);
	
	/**
	 * @param criteria
	 * @return Lista de Productos con Indicador Dentro de Caja
	 */
	public List getProductoIndicadorDentroCajaList(Map criteria);
	
	/**
	 * @param criteria
	 * @return Los datos de Indicador Producuto Dentro Caja
	 */
	public List getIndProdDentroCajaObject(Map criteria);
	
	/**
	 * @param criteria
	 * Permite actualizar el indicador de dentro/fuera de Caja
	 */
	public void updateIndicadorProducto(Map criteria);

	/**
	 * @param criteria
	 * @return Valida el archivo Excel
	 * @throws Exception
	 */
	public boolean validarArchivoExcel(Map criteria) throws Exception;
	
	/**
	 * @param criteria
	 * @return Carga archivo excel
	 * @throws Exception
	 */
	public String executeCargaArchivoExcel(Map criteria) throws Exception;

	/**
	 * @param criteria
	 * @return 1 Si ya existe el indicador, 0 si no existe
	 */
	public String getValidaExisteIndicadorCaja(Map criteria);
	
	/**
	 * @param obj
	 * Permite actualizar el indicador producto en la tabla MAE_PRODU
	 */
	public void actualizaIndicadorDentroFueraCaja(List obj);
	
	/**
	 * @param criteria
	 * Permite actualizar el indicador de producto con el codigo SAP
	 */
	public void updateIndProductoDFCaja(Map criteria);
	
}