package biz.belcorp.ssicc.service.spusicc.pre;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
public interface ProcesoPREEliminarMatrizFacturacionService extends Service {
	
	/**
	 * Ejecura el proceso de Eliminar Matriz de Facturación
	 *  
	 * @param params
	 */
	void executeEliminarMatrizFacturacion(Map params);

}