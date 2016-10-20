package biz.belcorp.ssicc.dao.spusicc.aco;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author sbuchelli
 *
 */
public interface ProcesoACOShellDAO extends DAO {


	/**
	 * Ejecuta proceso Shell Aco
	 * @param params
	 */
	public void executeProcesoShell(Map criteria);

}