package biz.belcorp.ssicc.service.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;


/**
 * Service con metodos para las consultas invocados por la pantalla de Liberacion de Rechazos
 * 
 * <p>
 * <a href="ProcesoSTOLiberacionRechazoService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Diego Torres Loyola</a>
 * 
 */
public interface ProcesoSTOLiberacionRechazoService extends Service {
	
	/**
	 * Devuelve la lista de procesos que han sido ejecutados para rechazar documentos
	 * @param params
	 * @return
	 */
	public List getProcesosEjecutadosRechazoDocumentoByCriteria(Map params);


}