package biz.belcorp.ssicc.dao.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Gonzalo Javier Huertas Agurto
 *
 */
public interface MantenimientoSTOFormaPagoClasificacionDAO extends DAO{
	/**
	 * Inserta Forma de Pago de Clasificacion
	 * @param criteria
	 */
	public void insertFormaPagoClasificacion(Map criteria);
	
	/**
	 * Devuelve Forma de Pago de Clasificacion
	 * @param criteria
	 * @return
	 */
	public List getFormaPagoClasificacionList(Map criteria);
	
	/**
	 * Elimina Forma de Pago de Clasificacion
	 * @param criteria
	 */
	public void deleteFormaPagoClasificacion(Map criteria);

}