package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPEControlBalanceoService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href=""> Jose Luis Rodriguez</a>
 */
public interface MantenimientoAPEControlBalanceoService extends Service{

	/**
	 * Devuelve la lista de Control de Balanceo
	 * @param criteria
	 * @return
	 */
	public List getControlBalanceoList(Map criteria);
	
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
