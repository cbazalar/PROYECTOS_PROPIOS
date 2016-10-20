package biz.belcorp.ssicc.service.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.inc.model.CargaPuntajeConsultora;
import biz.belcorp.ssicc.service.framework.Service;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface ProcesoINCCargaPuntajeBonificadoService  extends Service{

	/**
	 * Retorna los registros del excel del archivo cargado 
	 * @param criteria
	 * @return
	 */
	List cargarArchivoExcel(Map criteria) throws Exception;

	/**
	 * Realiza el proceso de insercion de puntaje en la cuenta corriente para los registros
	 * validos
	 * @param params
	 */
	void executeInsercionCuentaCorrientePuntaje(Map params);
	
	/**
	 * Inserta el puntaje valido o no dela consultora
	 * @param cargaPuntajeConsultora
	 */
	void insertCargaPuntajeConsultora(CargaPuntajeConsultora cargaPuntajeConsultora);

	/**
	 * Valida si el archivo cumple con el formato
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	boolean validarArchivoExcel(Map criteria)throws Exception;

	/**
	 * Valida los datos del Periodo y Codigo de cliente de la Consultora
	 * 
	 * @param oidPais
	 * @param codigoPeriodo
	 * @param codigoCliente
	 * @param usuario
	 * @return
	 */
	public String validarDatosConsultora(String oidPais, String codigoPeriodo, String codigoCliente, Usuario usuario);
	
}
