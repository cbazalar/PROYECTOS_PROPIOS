package biz.belcorp.ssicc.service.spusicc.comision;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoCOMCargaCuentasDetraccionService extends Service {

	/**
	 * Valida si el archivo cumple con el formato
	 * 
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	boolean validarArchivoExcel(Map criteria)throws Exception;

	/**
	 * Se encarga de validar el archivo excel
	 *  
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	boolean validarCargaExcel(Map criteria)throws Exception;
	
	/**
	 * Carga los registros del excel a las cuentas de detraccion de las ejecutivas
	 * 
	 * @param criteria
	 * @throws Exception
	 */
	void executeCargaArchivoExcel(Map criteria)throws Exception;

}
