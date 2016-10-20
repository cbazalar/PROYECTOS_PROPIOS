package biz.belcorp.ssicc.dao.spusicc.sac;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
/**
 * @author <a href="mailto:doliva@csigcomt.com">Dennys Oliva Iriarte</a>
 *
 */
public interface ProcesoSACGenerarReporteDAO extends DAO {	
	
	/**
	 * Devuelve la lista de correos para enviar el reporte generado
	 * @param criteria
	 * @return
	 */
	public List getListaCorreoReporteSACAsistenciaCompartamosEsika(Map criteria);
	
	/**
	 * Devuelve la lista de correos para enviar el reporte generado
	 * @param criteria
	 * @return
	 */
	public List getListaCorreoReporteSACActivasSaldo(Map criteria);
	
	/**
	 * Devuelve lista de correos para enviar en el Reporte de Programa Ejecutiva para Zonas
	 * @param criteria
	 * @return
	 */
	public List getListaCorreoZonaReportePEJProgramaEjecutivas(Map criteria);
	
	/**
	 * Devuelve lista de correos para enviar en el Reporte de Programa Ejecutiva para Secciones
	 * @param criteria
	 * @return
	 */
	public List getListaCorreoSeccionReportePEJProgramaEjecutivas(Map criteria);
	
	/**
	 * Devuelve lista de correos para enviar en el Reporte de Novedades Zonas
	 * @param criteria
	 * @return
	 */
	public String getListaCorreoMAENovedadesZona(Map criteria);
	
	/**
	 * Devuelve lista de correos para enviar en el Reporte de Programa Ejecutiva para Regiones
	 * @param criteria
	 * @return
	 */
	public List getListaCorreoRegionReportePEJProgramaEjecutivas(Map criteria);
	
	/**
	 * Devuelve lista de correos para enviar en el Reporte de Novedades Zonas
	 * @param criteria
	 * @return
	 */
	public String getListaCorreoFLXNovedadesZona(Map criteria);
	
	/**
	 * Metodo que devuelve la cantidad de resultados de Consultoras Habiles
	 * @param parameterMap
	 * @return
	 */
	public String getResultConsulorasHabiles(Map criteria);
	
	/**
	 * Devuelve datos de la zona en el proceso de recuperacion Cobranza
	 * @param criteria
	 * @return
	 */
	public Map getDatosZonaRecuperacionCobranza(Map criteria);
}
