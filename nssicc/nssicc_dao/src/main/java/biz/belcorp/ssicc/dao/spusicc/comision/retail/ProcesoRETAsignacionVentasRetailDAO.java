package biz.belcorp.ssicc.dao.spusicc.comision.retail;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoRETAsignacionVentasRetailDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
public interface ProcesoRETAsignacionVentasRetailDAO extends DAO {

	/**
	 * Asignacion de las ventas retail a las Gerentes de Zona
	 * 
	 * @param criteria
	 */
	public void executeAsignacionVentasRetail(Map criteria);

	/**
	 * Retorna 1 si existe el tipo de documento a actualziar 
	 * 0 si no existe
	 * @param map
	 * @return
	 */
	public int getExisteConsolidadoRetail(Map map);

	/**
	 * Inserta el consolidado
	 * @param map
	 */
	public void saveConsolidadoRetail(Map map);

	/**
	 * Actualiza el consolidado
	 * @param map
	 */
	public void updateConsolidadoRetail(Map map);

	/**
	 * elimina las ventas Retail cabecera
	 * @param map
	 */
	public void deleteVentaRetailCabecera(Map map);

	/**
	 * Elimina los detalles Retail
	 * @param map
	 */
	public void deleteVentaRetailDetalle(Map map);

	/**
	 * Inserta los registros de venta Retail cabecera
	 * @param map
	 */
	public void saveVentaRetailCabec(Map map);

	/**
	 * Inserta los registros de venta Retail detalle
	 * @param map
	 */
	public void saveVentaRetailDetalle(Map map);
	/**
	 * Lista los registros de la venta Retail cabecera 
	 * @param map
	 * @return 
	 */
	public List listaRetailCabecera(Map map) ;
	/**
	 * Lista los registros de la venta Retail detalle 
	 * @param map
	 * @return 
	 */
	public List listaRetailDetalle(Map map) ;
	/**
	 * Lista los campaa y fecha de proceso
	 * @param map
	 * @return 
	 */
	public List listaCampanaFechaProceso() ;
	/**
	 * Lista  Pais
	 * @param map
	 * @return 
	 */
	public List listaPais(Map map);
	
	
	/**
	 * Retorna false si existe el tipo de documento a actualziar 
	 * true si no existe
	 * @param map
	 * @return
	 */
	public boolean getExisteVentaRetailCabec(Map map);

}
