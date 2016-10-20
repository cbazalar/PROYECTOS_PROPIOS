package biz.belcorp.ssicc.service.spusicc.comision;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface ProcesoCOMCargaBonosService  extends Service{

	/**
	 * Valida si el archivo cumple con el formato
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	boolean validarArchivoExcel(Map criteria)throws Exception;

	/**
	 * Carga los registros del excel al detalle del bono
	 * @param criteria
	 * @throws Exception
	 */
	void executeCargaArchivoExcel(Map criteria)throws Exception;

	/**
	 * Se encarga de validar el archivo excel 
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	boolean validarCargaExcel(Map criteria)throws Exception;


}
