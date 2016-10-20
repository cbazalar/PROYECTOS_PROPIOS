package biz.belcorp.ssicc.service.spusicc.sgr;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface ProcesoSGRGenericoService  extends Service{

	/**
	 * Proceso que se encarga de cancelar las polizas 
	 * @param map
	 */
	void executeCancelarPolizas(Map map);

	/**
	 * Proceso que se encrag de identificar las polizar y actulizar su estado a vigentes
	 * @param map
	 */
	void executeIdentificarPolizasVigentes(Map map);


	

}
