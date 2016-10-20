package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
/**
 * @author Jose Luis Rodriguez
 */

public interface MantenimientoAPEControlBalanceoDAO extends DAO{

	/**
	 * Devuelve la lista de Control de Balanceo
	 * @param criteria
	 * @return
	 */
	public List getControlBalanceoList(Map criteria);
	
	/**
	 * Devuelve la lista de Mapa Centro de Distribucion por Linea de Armado
	 * @param criteria
	 * @return
	 */
	public List getMapaCentroDistbyLineaList(Map criteria);
	
	/**
	 * Obtiene el oid de la Asignacion Producto Anaquel Cabecera
	 * @param criteria
	 * @return
	 */
	public String getOidAsignacionProductoAnaquel(Map criteria);
	
	/**
	 * Obtiene la descripcion del Mapa Zona 
	 * @param criteria
	 * @return
	 */
	public String getDescMapaZonaCabec(Map criteria);
	
	/**
	 * Obtiene la descripcion del Mapa Centro Distribucion
	 * @param criteria
	 * @return
	 */
	public String getDescMapaCentroCabec(Map criteria);
	
	/**
	 * Realiza el balanceo de la Linea de Armado
	 * @param criteria
	 */
	public void executeBalanceoLinea(Map criteria);
	
	/**
	 * Valida si una sublinea ya se encuentra balanceado
	 * @param criteria
	 * @return
	 */
	public int validaBalanceoLinea(Map criteria);
}
