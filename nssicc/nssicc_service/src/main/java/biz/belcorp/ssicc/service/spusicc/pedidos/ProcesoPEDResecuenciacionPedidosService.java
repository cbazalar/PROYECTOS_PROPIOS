package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

public interface ProcesoPEDResecuenciacionPedidosService extends Service{
	

	/**
	 * Este metodo ejecuta la Resecuenciacion de Pedidos
	 * @param params
	 */
	public void executeResecuenciacionPedidos(Map params);
}