package biz.belcorp.ssicc.service.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * interface de codigo de venta opera.
 * <p>
 * <a href="MantenimientoRECCodigoVentaOperaService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@belcorp.biz">Cristian Roman </a>
 */
public interface MantenimientoRECCodigoVentaOperaService extends Service {

	/**
	 * Metodo que devuelve la lista de tipos de ofertas
	 * @param map
	 * @return
	 */
	public List getTipoOfertaList(Map map);
	
	/**
	 * Metodo que devuelve la lista de catalogos
	 * @param map
	 * @return
	 */
	public List getCodigoCatalogoList(Map map);
	
	/**
	 * Metodo para devolver la lista de codigos de venta
	 * @param map
	 * @return
	 */
	public List getCodigoVentaOperaList(Map map);
	
	/**
	 * Metodo que elimina los registros de la tabla REC_EXCEP_CODIG_VENTA_OPERA
	 * @param map
	 * @param items
	 */
	public void deleteCodigoVentaOpera(Map map, String[] items);
	
	/**
	 * Metodo que inserta en la tabla REC_EXCEP_CODIG_VENTA_OPERA
	 * @param map
	 */
	public void insertCodigoVentaOpera(Map map);
	
	/**
	 * Metodo que actualiza los campos de la tabla REC_EXCEP_CODIG_VENTA_OPERA
	 * @param map
	 */
	public void updateCodigoVentaOpera(Map map);
}
