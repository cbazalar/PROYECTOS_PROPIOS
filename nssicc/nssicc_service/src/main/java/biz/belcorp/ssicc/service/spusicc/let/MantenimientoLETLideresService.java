package biz.belcorp.ssicc.service.spusicc.let;

import java.util.List;
import java.util.Map;

import javax.naming.directory.InvalidAttributeValueException;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * Clase de la declaracin de la capa BO (Bussiness Object)
 * 
 * <p>
 * <a href="MantenimientoLETLideresService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
public interface MantenimientoLETLideresService extends Service {
	
	/**
	 * Obtiene datos de las secciones de acuerdo a los criterios de busqueda pasados
	 * como parametro
	 * 
	 * @param criteria
	 * @return
	 */
	public List getSeccionesByCriteria(Map criteria) throws Exception;

	/**
	 * Ejecuta las diferentes validaciones para saber si se puede realizar la asignacion
	 * de la lider a una respectiva seccion
	 * 
	 * @param params
	 * @return
	 */
	public String validarAsignacionLiderSeccion(Map params);
	
	/**
	 * Realiza la asignacion/desasignacion de la lider a la seccion.
	 * 
	 * @param criteria
	 */
	public void execAsignacionLiderSeccion(Map criteria) throws InvalidAttributeValueException ;

	/**
	 * Recupera oidPais apartir del codigo Pais
	 * @param criteria
	 * @return
	 */
	public String getOidPaisByCodigoPaisLET(Map criteria);

	/**
	 * Recupera oidMarca apartir del codigo Marca
	 * @param criteria
	 * @return
	 */
	public String getOidMarcaByCodigoMarcaGenericoLET(Map criteria);

	/**
	 * Recupera oidCanal apartir del codigo Canal
	 * @param criteria
	 * @return
	 */
	public String getOidCanalByCodigoCanalGenericoLET(Map criteria);

	/**
	 * Obtiene el indicador del Programa LET
	 * @param codigoPais
	 * @return
	 */
	public Integer getIndicadorProgramaLet(String codigoPais);

	/**
	 * Encuentra un concurso LET en un rango de periodos
	 * @param periodoActual
	 * @return
	 */
	public Integer getEncontrarConcursoLet(String periodoActual);
	
	/**
	 * Obtiene el oid del codigo de periodo
	 * @param codigoPerido
	 * @return
	 */
	public Integer getOidPeriodoByCodigoPeriodo(String codigoPerido);
	
	/**
	 * Obtiene el oid del periodo anterior por medio del codigo de perido, pais ,marca, canal
	 * @param criteria
	 * @return
	 */
	public Integer getOidPeriodoAnteriorByCodigoPeriodo(Map criteria);
	
	/**
	 * Obtiene el codigo de periodo por medio del oid de periodo
	 * @param oidPeriodo
	 * @return
	 */
	public String getCodigoPeriodoByOidPeriodo(Integer oidPeriodo);
	
	/**
	 * Elimina registro del historico gerente
	 * @param oidHistoricoGerente
	 */
	public void deleteHistoricoGerente(Integer oidHistoricoGerente);
	
	/**
	 * Se obtiene el indicador de Asignacion Lider - LET
	 * @param criteria
	 * @return
	 */
	public String getIndicadorAsignarLider(Map criteria);
	/* INI JJ PER-SiCC-2012-0201  */
	/**
	 * Ontiene el oid del periodo siguiente por medio de codigo de periodo
	 * @param criteria
	 * @return
	 */
	public Integer getOidPeriodoSiguienteByCodigoPeriodo(Map criteria);
	/* FIN JJ PER-SiCC-2012-0201  */
	
	/* INI SA PER-SiCC-2012-0476  */
	/**
	 * Obtiene los datos de la Lider
	 * 
	 * @param codigoConsultora
	 * @return
	 */
	public Map getDatosLider(String codigoConsultora);
	/* FIN SA PER-SiCC-2012-0476  */

	public void executeValidarAsignacionLiderRegionZona(Map params);

	public void executeProcesoDesvinculacion(Map params);

	public String validarDesvinculacion(Map criteria);
	
	/**
	 * Obtiene las consultoras lideres por pas
	 * 
	 * @param codigoPais
	 * @return
	 */
	public List getLideres(String codigoPais);
	
	/**
	 * Encuentra un programa LET Corporativo en un rango de periodos
	 * @param periodoActual
	 * @return
	 */
	public Integer getEncontrarProgramaLetCorporativo(String periodoActual);	
	
	/**
	 * Encuentra el Concurso y Tramo correspondiente a la campaa proceso
	 * @param criteria
	 * @return
	 */
	public Map getConcursoTramoPrograma(Map criteria);
	
	/**
	 * Realiza la carga del archivo excel, procede con las validaciones respectivas antes de procesar el excel e inserta en la lista pasada
	 * como segundo parametro los datos del excel y los registros que tuvieron error en el tercer parametro
	 * @param criteria
	 * @param objetivosList
	 * @param errorObjetivosListList
	 * @param flagErrorGlobal
	 * @return
	 */
	public boolean loadfileExcel(Map criteria, List objetivosList, List errorObjetivosListList, Boolean flagErrorGlobal) throws Exception;
	
	/**
	 * Ejecuta la carga de objetivos
	 * @param objetivosList
	 */
	public void executeCargaObjetivos(List objetivosList);
	
	/**
	 * Ejecuta la carga de objetivos retencion 2/2
	 * @param objetivosList
	 */
	public void executeCargaObjetivosRetencion22(List objetivosList);
	
	/**
	 * Ejecuta la carga de objetivos retencion 3/3 y 4/4
	 * @param objetivosList
	 * @param tipoCarga
	 */
	public void executeCargaObjetivosRetencion3344(List objetivosList, String tipoCarga);
	
	/**
	 * @param codigoPeriodo
	 * @return
	 */
	public Integer getCampanaCerrada(String codigoPeriodo);
	
	
	/**
	 * Devuelve el OID del Cliente
	 * @param criteria
	 * @return
	 */
	public String getOidCliente(Map criteria);
	
	/**
	 * Encuentra un programa LEC Corporativo en un rango de periodos
	 * @param periodoActual
	 * @return
	 */
	public Map getEncontrarProgramaLecCorporativo(String periodoActual);
	
	/**
	 * Obtiene Indicador Posible Lider
	 * 
	 * @param criteria
	 * @return
	 */
	public Integer getIndicadorPosibleLider(Map criteria);
	
	/**
	 * Obtiene Indicador Posible Lider Historico
	 * 
	 * @param criteria
	 * @return
	 */
	public Integer getIndicadorPosibleLiderHisto(Map criteria);
	
	/**
	 * Obtiene el maximo periodo hasta de una lider de una determinada seccion
	 * 
	 * @param codigoCliente
	 * @return
	 */
	public List getMaximoPeriodoHastaLider(String codigoCliente);
	
	/**
	 * Elimina el registro con clasificacion lider de la consultora anterior
	 * @param criteria
	 */
	public void deleteClasificacionLiderConsultoraAnterior(Map criteria);
}