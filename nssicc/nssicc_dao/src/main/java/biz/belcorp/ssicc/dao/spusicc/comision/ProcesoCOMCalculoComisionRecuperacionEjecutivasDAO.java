package biz.belcorp.ssicc.dao.spusicc.comision;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

public interface ProcesoCOMCalculoComisionRecuperacionEjecutivasDAO extends DAO {
   	/**
	 * Se procesa las comisiones de recuperacion
	 * @param map
	 */
	public void executeCalculoComisionRecuperacionEjecutivas(Map params);

   	/**
	 * Se obtine el calculo
	 * @param map
	 */
	public Integer getCalculoComisionRecuperacionEjecutivasCount(Map params);

   	/**
	 * Se procesa las comisiones perdidas de las gerentes de Zonas
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