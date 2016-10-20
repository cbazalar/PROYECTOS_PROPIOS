package biz.belcorp.ssicc.dao.spisicc;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spisicc.model.ParametroProImpresion;


/**
 * Service con metodos para las consultas invocados por la pantalla de Parametro Proceso Impresion
 * 
 * <p>
 * <a href="MantenimientoIMPParametroProcesoImpresionDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Yahir Rivas Luna</a>
 * 
 */

public interface MantenimientoIMPParametroProcesoImpresionDAO extends DAO {

	/**
	 * Retorna la lista de parametros proceso impresion
	 * @param parametroProImpresion
	 * @return
	 */
	public List getParametroProImp(ParametroProImpresion parametroProImpresion);
	
	/**
	 * Inserta datos al Parametro proceso impresion.
	 * @param parametroProImpresion
	 */
	public void insertParametroProImp(ParametroProImpresion parametroProImpresion);

	/**
	 * Inserta los datos al historico Parametro  proceso impresion.
	 * Se ejecuta al momento que se elimina el registro de la tabla Parametro proceso impresion.
	 * @param parametroProImpresion
	 */
	public void insertHistoricoParametroProImp(ParametroProImpresion parametroProImpresion);
	
	/**
	 * Actualiza el registro del Parametro  proceso impresion.
	 * @param parametroProImpresion
	 */
	public void updateParametroProImp(ParametroProImpresion parametroProImpresion);
	
	/**
	 * Elimina el registro del Parametro proceso impresion.
	 * @param parametroProImpresion
	 */
	public void deleteParametroProImp(ParametroProImpresion parametroProImpresion);
}