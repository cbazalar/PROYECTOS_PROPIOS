package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * Clase interface donde se declara los mtodos a implementar en el Service
 * 
 * <p>
 * <a href="MantenimientoPEDNivelesRiesgoChequearService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 * 
 */
public interface MantenimientoPEDNivelesRiesgoChequearService extends Service{

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