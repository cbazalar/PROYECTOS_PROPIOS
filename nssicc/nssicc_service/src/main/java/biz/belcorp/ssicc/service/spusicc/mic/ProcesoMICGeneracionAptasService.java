package biz.belcorp.ssicc.service.spusicc.mic;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface ProcesoMICGeneracionAptasService  extends Service{


	/**
	 * Genera las aptas para los microseguros
	 * @param criteria
	 * @throws Exception
	 */
	void executeGeneracionAptasMicroSeguros(Map criteria)throws Exception;



}
