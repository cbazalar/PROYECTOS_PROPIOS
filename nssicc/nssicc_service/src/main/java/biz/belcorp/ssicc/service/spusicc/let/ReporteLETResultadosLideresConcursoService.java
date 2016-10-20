package biz.belcorp.ssicc.service.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ReporteLETResultadosLideresConcursoService extends Service{

	/**
	 * Genera la data para el reporte de resultado de lideres concurso
	 * @param map
	 */
	public void executeGenerarReporteResultadoLider(Map map);

}