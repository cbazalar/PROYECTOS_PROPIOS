package biz.belcorp.ssicc.service.spusicc.cobranzas;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * 
 * <p>
 * <a href="ProcesoCOBGeneracionArchivosProveedoresFTPService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
public interface ProcesoCOBGeneracionArchivosProveedoresFTPService extends Service {
	
	/**
	 * Invoca la ejecucion de todos los reportes que seran enviados por FTP
	 * @param criteria
	 */
	public void executeReportes(Map criteria);
}