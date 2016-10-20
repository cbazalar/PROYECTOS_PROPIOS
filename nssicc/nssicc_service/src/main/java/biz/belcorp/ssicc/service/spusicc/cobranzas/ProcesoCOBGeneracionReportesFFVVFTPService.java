package biz.belcorp.ssicc.service.spusicc.cobranzas;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * 
 * <p>
 * <a href="ProcesoCOBGeneracionReportesFFVVFTPService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
public interface ProcesoCOBGeneracionReportesFFVVFTPService extends Service {

	/**
	 * Ejecuta el proceso que obtiene TODA la data para los reportes
	 * @param criteria
	 */
	public void executeGenerarDataFFVVFTP (Map criteria);
	
	/**
	 * Invoca la ejecucion de todos los reportes que seran enviados por FTP
	 * @param criteria
	 */
	public void executeReportes(Map criteria);
}