package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;

import biz.belcorp.ssicc.service.framework.Service;

public interface MantenimientoPEDTipoChequeoService extends Service{

	/**
	 * retorna todos los tipos de chequeo de la tabla
	 * @return
	 */
	public List getTipoChequeoAll();

	/**
	 * Actualiza un tipo de chequeo
	 * @param parametrosList 
	 */
	public void updateTipoChequeo(List parametrosList);
}