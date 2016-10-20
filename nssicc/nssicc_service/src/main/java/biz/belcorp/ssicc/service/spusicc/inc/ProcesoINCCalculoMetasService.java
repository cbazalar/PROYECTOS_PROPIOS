package biz.belcorp.ssicc.service.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:avillavicencio@csigcomt.com">AV</a>
 *
 */
public interface ProcesoINCCalculoMetasService  extends Service{

	/**
	 * Realiza el proceso de insercion de calculo de metas
	 * validos
	 * @param params
	 */
	void executeCalculoMetas(Map params);
	
}
