package biz.belcorp.ssicc.dao.spusicc.fdv;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="ProcesoFDVFileVariDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */

public interface ProcesoFDVFileVariDAO extends DAO{

	/**
	 * Guarda la informacion capturada del archivo de variables exogenas
	 * 
	 * @param varList
	 *            lista de objetos a ser insertados
     * @param codProc
     *            el codigo del proceso
     * @param cleanData
     * 			  campo que nos indicara si se desea limpiar los datos de la tabla
     * 			  antes de realizar las inserciones.           
	 * @throws Exception 
	 */
	public void insertVariablesExogFDV(final List varList, final String codProc, final boolean cleanData) throws Exception;

}
