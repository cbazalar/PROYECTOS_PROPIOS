package biz.belcorp.ssicc.dao.spusicc.pej;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.pej.model.Canasta;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface MantenimientoPEJCanastasDAO extends DAO{

	/**
	 * Valida el codigo de venta
	 * @param codigoPeriodo
	 * @param codigoVenta
	 * @return
	 */
	public Integer validaCodigoVenta(String codigoPeriodo, String codigoVenta);

	/**
	 * Obtiene la lista de canastas
	 * @param criteria
	 * @return
	 */
	public List getCanastasList(Map criteria);

	/**
	 * Elimina las canastas seleccionadas
	 * @param criteria
	 */
	public void deleteCanastas(Map criteria);

	/**
	 * Obtiene la canasta seleccionada desde la grilla
	 * @param codigoPais
	 * @param campanhaDespachoId
	 * @param codigoVentaId
	 * @return
	 */
	public Canasta getCanasta(String codigoPais, String campanhaDespachoId,String codigoVentaId);

	/**
	 * Inserta Canasta
	 * @param canasta
	 */
	public void inserCanasta(Canasta canasta);

	/**
	 * Actualiza la canasta seleccionada
	 * @param canasta
	 */
	public void updateCanasta(Canasta canasta);

	/**
	 * Valida si el codigo de venta ingresado no esta siendo duplicado en la campaa
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @param codigoVenta 
	 * @return
	 */
	public Integer validaCodigoVentaDuplicadoCamp(String codigoPais,String codigoPeriodo,String codigoVenta);

	/**
	 * Valida si la oferta es digitable
	 * @param codigoPeriodo
	 * @param codigoVenta
	 * @return
	 */
	public Integer validaOfertaDigitable(String codigoPeriodo,String codigoVenta);

}