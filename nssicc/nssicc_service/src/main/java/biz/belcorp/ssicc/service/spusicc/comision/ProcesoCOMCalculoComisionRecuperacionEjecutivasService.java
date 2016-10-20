package biz.belcorp.ssicc.service.spusicc.comision;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *
 */
public interface ProcesoCOMCalculoComisionRecuperacionEjecutivasService extends
		Service {

	/**
	 * Se procesa las comiisones de recuperacion
	 * @param map
	 */
	public void executeCalculoComisionRecuperacionEjecutivas(Map params);

	/**
	 * se obtiene el calculo
	 * @param map
	 */
	public Integer getCalculoComisionRecuperacionEjecutivasList(Map params);

	/**
	 * Se procesa las comiisones perdidas de las gerentes de Zonas
	 * @param map
	 */
	public void executeComisionRecuperacionPerdidas(Map map);

	/**
	 * Retorna la lista de comisiones perdidas por GZ
	 * @param params
	 * @return
	 */
	public List getComisionRecuperacionPerdidas(Map params);

	/**
	 * Retorna la lista de comisiones perdidas por GR
	 * @param params
	 * @return
	 */
	public List getComisionRecuperacionPerdidasRegion(Map params);


	/**
	 * Realza la actualizacion de estatus de consultora
	 * @param params
	 */
	public void executeActualizacionEstatusEjecutivas(Map params);

	/**
	 * Realiza la actualizacion de las tablas historicas y eliminacion de las comisiones
	 * @param params
	 */
	public void executeEliminarComisionRecuperacion(Map params);

}