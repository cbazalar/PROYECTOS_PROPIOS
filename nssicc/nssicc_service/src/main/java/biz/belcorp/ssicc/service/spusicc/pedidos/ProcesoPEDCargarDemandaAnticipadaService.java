/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.pedidos;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author itocto
 *
 */
public interface ProcesoPEDCargarDemandaAnticipadaService extends Service{

	/**
	 * Procesa el archivo que se ha subido al servidor
	 * 
	 * @param directorioTemp
	 * @param archivoProcesar
	 */
	int executeProcesarArchivo(String directorioTemp, String archivoProcesar, String codigoPais, String codigoPeriodo) throws Exception;
	
}
