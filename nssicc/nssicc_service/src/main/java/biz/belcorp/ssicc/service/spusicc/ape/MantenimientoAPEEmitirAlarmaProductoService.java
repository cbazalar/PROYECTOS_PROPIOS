package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPEEmitirAlarmaProductoService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
public interface MantenimientoAPEEmitirAlarmaProductoService extends Service{

	/**
	 * Devuelve la lista de productos para enviar por mail
	 * @param criteria
	 * @return
	 */
	public List getEmitirAlarmaProductoList(Map criteria);
	
	/**
	 * Devuelve la lista de correos de los usuarios
	 * @param criteria
	 * @return
	 */
	public List getMailAlarmaProductoList(Map criteria);
	
	/**
	 * Devuele el oid del siguiente periodo ingresado por pantalla
	 * @param criteria
	 * @return
	 */
	public String getOidPeriodoSiguiente(Map criteria);
}