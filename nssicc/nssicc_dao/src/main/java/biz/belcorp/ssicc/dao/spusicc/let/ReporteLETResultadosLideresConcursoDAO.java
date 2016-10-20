package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ReporteLETResultadosLideresConcursoDAO extends DAO{

	/**
	 * Genera la data para el reporte de Resultado de lideres concurso
	 * @param map
	 */
	public void executeGenerarReporteResultadoLider(Map map);

}