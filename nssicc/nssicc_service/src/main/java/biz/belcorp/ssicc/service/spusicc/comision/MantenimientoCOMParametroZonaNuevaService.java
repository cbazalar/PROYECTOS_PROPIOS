/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.comision;

import java.util.List;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.comision.model.ParametroZonaNueva;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:ttataje@csigcomt.com">Telly Tataje Tirado</a>
 *
 */
public interface MantenimientoCOMParametroZonaNuevaService extends Service {
	
    /**
     * Obtiene lista con informacin correspondiente a los Parametros de Zona Nueva 
     * @param bean
     * @return
     */
    public List getListaParametroZonaNueva(ParametroZonaNueva bean);
	
    /**
     * Obtiene la informacin correspondiente a los Parametros de Zona Nueva 
     * @param bean
     * @return
     */
    public ParametroZonaNueva getParametroZonaNueva(ParametroZonaNueva bean);
	
    
	/**
	 * Actualiza registro correspondiente a los Parametros de Zona Nueva 
	 * @param bean
	 * @param usuario
	 */
	public void updateParametroZonaNueva(ParametroZonaNueva bean, Usuario usuario);

	/**
	 * Crea un  registro correspondiente a los Parametros de Zona Nueva 
	 * @param bean
	 * @param usuario
	 */
	public void insertParametroZonaNueva(ParametroZonaNueva bean,Usuario usuario);
	
}