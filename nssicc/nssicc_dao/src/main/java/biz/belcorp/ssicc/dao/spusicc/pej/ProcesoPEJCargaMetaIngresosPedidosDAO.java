package biz.belcorp.ssicc.dao.spusicc.pej;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.pej.model.MetaEjecutivaIngresosPedidos;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ProcesoPEJCargaMetaIngresosPedidosDAO extends DAO{

	/**
	 * Verifica si el codigo de zona ingresado existe
	 * @param codigoZona
	 * @return
	 */
	public Integer getExisteZona(String codigoZona);

	/**
	 * Verifica si el codigo de seccion ingresado existe asociado a la zona ingresada
	 * @param codigoZona
	 * @param codigoSeccion
	 * @return
	 */
	public Integer getExisteSeccionByZona(String codigoZona,String codigoSeccion);

	/**
	 * Verifica si las campaas inicial y final existen en la tabla de etapas
	 * @param campIncial
	 * @param campFinal
	 * @return
	 */
	public Integer getExisteCampInicialFinal(String campIncial, String campFinal);

	/**
	 * Graba los registros validados del excel en la tabla temporal para su proceso
	 * @param metaEjecutivaIngresosPedidos
	 */
	public void saveTempoMetaIngresosPedidos(MetaEjecutivaIngresosPedidos metaEjecutivaIngresosPedidos);

	/**
	 * Borramos la data de la tabla temporal
	 */
	public void deleteTempoMetaIngresosPedidos();

	/**
	 * Realiza la carga de Meta Ingresos y Pedidos
	 * @param codigoPais
	 * @param anioInicial
	 * @param codigoEtapa
	 */
	public void executeCargaMetaIngresosPedidos(String codigoPais,String anioInicial, String codigoEtapa);

}