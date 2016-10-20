package biz.belcorp.ssicc.service.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ProcesoLETEntregaPremiosConcursoCierreCampanaService extends Service{

	/**
	 * Ejecuta la entrega de premios ed concurso al cierre de campaa
	 * @param params
	 */
	public void executeProcesoLETEntregaPremiosConcursoCierreCampana(Map params);

}