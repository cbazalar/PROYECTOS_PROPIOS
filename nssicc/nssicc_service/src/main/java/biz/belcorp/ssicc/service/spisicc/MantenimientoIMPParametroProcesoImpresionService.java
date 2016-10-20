package biz.belcorp.ssicc.service.spisicc;

import java.util.List;

import biz.belcorp.ssicc.dao.spisicc.model.ParametroProImpresion;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author yrivas
 *
 */
public interface MantenimientoIMPParametroProcesoImpresionService extends Service{

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
