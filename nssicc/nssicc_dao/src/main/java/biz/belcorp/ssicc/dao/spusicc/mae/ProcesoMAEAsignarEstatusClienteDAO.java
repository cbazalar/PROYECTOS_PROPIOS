package biz.belcorp.ssicc.dao.spusicc.mae;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoMAEAsignarEstatusClienteDAO
		extends DAO {

	/**
	 * Proceso que va a Asignar Estatus a Clientes
	 * 
	 * @param params
	 */
	public void executeAsignarEstatusCliente(Map params);
	
	/**
	 * Proceso que revierte Estatus de Consultora
	 * 
	 * @param params
	 */
	public void executeProcesoMAERevertirEstatusConsultora(Map params);

}
