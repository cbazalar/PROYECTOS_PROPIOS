package biz.belcorp.ssicc.service.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * interface de codigo de venta opera.
 * <p>
 * <a href="MantenimientoRECProductosFFNNEEService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@belcorp.biz">Cristian Roman </a>
 */
public interface MantenimientoRECProductosFFNNEEService extends Service {

	/**
	 * Metodo que devuelve la lista de productos
	 * @param map
	 * @return
	 */
	public List getProductosFFNNEEList(Map map);
	
	
	/**
	 * Metodo que devuelve la descripcion del producto
	 * @param map
	 * @return
	 */
	public String getDescripcionProducto(Map map);
	
	
	/**
	 * Metodo para devolver el oid del producto
	 * @param map
	 * @return
	 */
	public String getOidProducto(Map map);
	
	
	/**
	 * Metodo que borra los registros de la tabla REC_PRODU_FM
	 * @param map
	 */
	public void deleteProductosFFNNEE(Map map);
	
	
	/**
	 * Inserta en la tabla REC_PRODU_FM
	 * @param codigoPais
	 * @param codigoPeriodoInicio
	 * @param codigoPeriodoFin
	 * @param codigoRegion
	 * @param detalle
	 * @param regionList
	 */
	public void insertProductosFFNNEE(String codigoPais,String codigoPeriodoInicio,String codigoPeriodoFin,String codigoRegion,List detalle,List regionList);	
	
	/**
	 * Inserta en la tabla REC_PRODU_FM desde archivo
	 * @param codigoPais
	 * @param detalle
	 */
	public void insertProductosFFNNEEArchivo(String codigoPais, List detalle);
	
	/**
	 * Devuelve la ruta para guardar el archivo 
	 * @param codigoPais
	 * @return
	 */
	public String obtenerPathUpload(String codigoPais);
	
	/**
	 * Devuelve codigo CUV Ficticio en base al oid del producto
	 * @param map
	 * @return
	 */
	public String getCodigoCUVFicticioProducto(Map map);
}
