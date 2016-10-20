package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * Clase interface donde se declara los mtodos a implementar el patrn DAO
 * 
 * <p>
 * <a href="MantenimientoPEDNivelesRiesgoChequearDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 * 
 */
public interface MantenimientoPEDNivelesRiesgoChequearDAO extends DAO {

	/**
	 * Retorna todos los niveles de riesgo
	 * @return
	 */
	public List getNivelRiesgo();
	
	/**
	 * Retorna la lista general del Mantenimiento Niveles de Riesgo a Chequear
	 * @return
	 */
	public List getListaPEDNivelesRiesgoChequear();

	/**
	 * Eliminar un dato del Mantenimiento Niveles de Riesgo a Chequear
	 * @param map
	 */
	public void deletePEDNivelesRiesgoChequear(Map map);

	/**
	 * Insertar un dato del Mantenimiento Niveles de Riesgo a Chequear 
	 * @param params
	 */
	public void insertPEDNivelesRiesgoChequear(Map params);

}