package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

public interface MantenimientoPEDTipoChequeoDAO extends DAO{

	/**
	 * retorna todos los tipos de chequeo de la tabla
	 * @return
	 */
	public List getTipoChequeoAll();

	/**
	 * Actualiza un tip de chequeo
	 * @param criteria
	 */
	public void updateTipoChequeo(Map criteria);

}
