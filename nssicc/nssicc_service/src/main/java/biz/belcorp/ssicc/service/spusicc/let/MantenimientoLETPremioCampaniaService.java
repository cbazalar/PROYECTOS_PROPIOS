package biz.belcorp.ssicc.service.spusicc.let;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * The Class MantenimientoLETPremioCampaniaService.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 07/11/2014
 */
@SuppressWarnings("rawtypes")
public interface MantenimientoLETPremioCampaniaService extends Service {
	
	/**
	 * Método que inserta Premio por Campaña
	 * @param criteria
	 */
	public void insertPremioCampania(Map criteria);
	
	/**
	 * Método que elimina Premio por Campaña de acuerdo al codigo concurso y codigo de periodo de despacho
	 * @param criteria
	 */
	public void deletePremioCampania(Map criteria);
	
	/**
	 * Método que lista los números de concurso
	 * @param criteria
	 * @return lista
	 */
	public List getNumeroConcursoList(Map criteria);
	
	/**
	 * Método que captura el Nivel Campania y Rango Pedido
	 * @param criteria
	 * @return lista
	 */
	public List getNivelRangoList(Map criteria);
	
	/**
	 * Método que lista Premios por Campaña
	 * @param criteria
	 * @return lista
	 */
	public List getPremioCampaniaList(Map criteria);

	/**
	 * Método que valida si el código de venta existe o no
	 * @param criteria
	 * @return lista
	 */
	public int getValidaCodigoVenta(Map criteria);

	/**
	 * Valida que un Concurso y Campaña no se repitan el Mantenimiento Premios por Campaña
	 * @param criteria
	 * @return lista
	 */
	public String getValidaPremioCampaniaExiste(Map criteria);
}
