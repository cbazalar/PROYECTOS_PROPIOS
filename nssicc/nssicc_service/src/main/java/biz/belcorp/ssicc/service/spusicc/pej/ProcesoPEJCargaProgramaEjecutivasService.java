package biz.belcorp.ssicc.service.spusicc.pej;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *
 */
public interface ProcesoPEJCargaProgramaEjecutivasService {
	
	public List getTipoCarga();
	
	public Map cargarArchivoExcel(Map criteria) throws Exception;
	
	public List executeValidarCargaProgramaEjecutivas(Map params);
	
	public void executeActualizarCargaProgramaEjecutivas(Map params);
}