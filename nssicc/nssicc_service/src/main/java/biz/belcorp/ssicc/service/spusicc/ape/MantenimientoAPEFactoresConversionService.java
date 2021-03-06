package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.ape.model.FactorConversion;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPEFactoresConversionService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez</a>
 */
public interface MantenimientoAPEFactoresConversionService extends Service {

	/**
	 * Recupera la lista de Factores de Conversin 
	 * 
	 * @param criteria
	 * @return
	 */
	public List getFactoresConversionList(Map criteria);
	
	/**
	 * @param criteria
	 * @return Lista de Unidades de Medida
	 */
	public List getUnidadMedidaList(Map criteria);
	
	/**
	 * @param criteria
	 * @return La lista de Magnitudes
	 */
	public List getMagnitudList(Map criteria);
	
	/**
	 * @param criteria
	 * @return Obtener Oid Magnitud
	 */
	public String getObtenerOidMagnitud(Map criteria);
		
	/**
	 * @param criteria
	 * @return Obtener Oid Unidad Medida
	 */
	public String getObtenerOidUnidadMedida(Map criteria);
	
	/**
	 * Inserta los Factores de conversin
	 * @param criteria
	 */
	public void insertarFactoresConversion(Map criteria);
	
	/**
	 * Actualiza los Factores de conversin
	 * @param criteria
	 */
	public void actualizarFactoresConversion(Map criteria);
	
	/**
	 * @param criteria
	 * @return Los datos de Factor de Conversin
	 */
	public FactorConversion getFactorConversionObject(Map criteria);
	
	/**
	 * Elimina el Factor de Conversin
	 * @param criteria
	 */
	public void eliminarFactorConversion(Map criteria, String[] items);

	/**
	 * @param criteria
	 * @return 0  1 o ms, valida si existe Repeticin de Config. de Und Origen + Und Destino
	 */
	public String validaRepeticionUndOrigenUndDestino(Map criteria);

}
