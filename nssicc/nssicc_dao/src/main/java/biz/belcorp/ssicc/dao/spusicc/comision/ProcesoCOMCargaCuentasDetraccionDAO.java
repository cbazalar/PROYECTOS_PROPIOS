package biz.belcorp.ssicc.dao.spusicc.comision;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoCOMCargaCuentasDetraccionDAO extends DAO {

	/**
	 * Valida si existe una determinada ejecutiva
	 * @param detalle
	 */
	boolean isValidaEjecutiva(String codigoCliente);

	/**
	 * Actualiza las cuentas de detraccion de las ejecutivas
	 * @param list
	 */
	void updateListCuentasDetraccion(List list) throws Exception;

	
}

