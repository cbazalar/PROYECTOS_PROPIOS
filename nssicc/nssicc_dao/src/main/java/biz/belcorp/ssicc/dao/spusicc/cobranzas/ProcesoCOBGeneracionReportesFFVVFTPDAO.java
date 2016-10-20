package biz.belcorp.ssicc.dao.spusicc.cobranzas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="ProcesoCOBGeneracionReportesFFVVFTPDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
public interface ProcesoCOBGeneracionReportesFFVVFTPDAO extends DAO {

	/**
	 * Ejecuta el proceso que obtiene TODA la data para los reportes
	 * @param criteria
	 */
	public void executeGenerarDataFFVVFTP (Map criteria);
	
	/**
	 * Devuelve las regiones a reportar
	 * @param codigoUsuario
	 * @return
	 */
	public List obtenerRegionesA(String codigoUsuario);
	
	/**
	 * Devuelve las zonas a reportar
	 * @param criteria
	 * @return
	 */
	public List obtenerZonasA(Map criteria);
}