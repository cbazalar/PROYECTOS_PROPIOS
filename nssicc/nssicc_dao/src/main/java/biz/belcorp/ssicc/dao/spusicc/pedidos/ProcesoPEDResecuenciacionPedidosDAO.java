package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author PEJCAIRAMPOMA
 *
 */
public interface ProcesoPEDResecuenciacionPedidosDAO extends DAO{

	/**
	 * Este metodo ejecuta la Resecuenciacion de Pedidos
	 * @param params
	 */
	void executeResecuenciacionPedidos(Map params);
	
	
}