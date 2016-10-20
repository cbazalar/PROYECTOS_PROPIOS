package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ProcesoLETEntregaPremiosConcursoCierreCampanaDAO extends DAO{

	/**
	 * Ejecuta la entrega de premios de concurso al cierre de campaa
	 * @param params
	 */
	public void executeProcesoLETEntregaPremiosConcursoCierreCampana(Map params);

}