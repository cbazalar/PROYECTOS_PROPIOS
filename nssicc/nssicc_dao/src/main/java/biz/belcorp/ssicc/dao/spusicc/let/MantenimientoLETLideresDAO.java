package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.sisicc.model.Base;

/**
 * Clase de la declaracin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="MantenimientoLETLideresDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
public interface MantenimientoLETLideresDAO extends DAO {

	
	/**
	 * Obtiene la ultima campaa de cierre de region de la zona pasada como parametro
	 * 
	 * @param criteria
	 * @return
	 */
	public Base getUltimaCampanaCierreRegionxZona(Map criteria);

	/**
	 * Obtiene datos de las secciones de acuerdo a los criterios de busqueda pasados
	 * como parametro
	 * 
	 * @param criteria
	 * @return
	 */
	public List getSeccionesByCriteria(Map criteria);
	
	/**
	 * Ejecuta las diferentes validaciones para saber si se puede realizar la asignacion
	 * de la lider a una respectiva seccion
	 * 
	 * @param params
	 * @return
	 */
	public String validarAsignacionLiderSeccion(Map params);
	
	/**
	 * Devuelve un valor diferente a cero en caso se encuentre a la consultora
	 * asignada como lider con la clasificacion de lider
	 * @param params
	 * @return
	 */
	public String getConsultoraClasificacionLider(Map params);
	
	/**
	 * Inserta el tipo de clasificacion lider para una consultora
	 * @param criteria
	 */
	public void insertClasificacionConsultoraLider(Map criteria);

	/**
	 * Obtiene el ultimo responsable (lider) de una determinada seccion
	 * 
	 * @param criteria
	 * @return
	 */
	public Map getUltimoResponsableSeccion(Map criteria);
	
	/**
	 * Actualiza Fecha Hasta de un determinado registro de la entidad [Historico Gerente]
	 * 
	 * @param oidHistoricoGerente
	 */
	public void updateFechaHastaHistoricoGerente(Map criteria);

	/**
	 * Elimina el registro con clasificacion lider de la consultora anterior
	 * @param criteria
	 */
	public void deleteClasificacionLiderConsultoraAnterior(Map criteria);
	
	/**
	 * Actualiza la lider de la Seccion
	 * 
	 * @param criteria
	 */
	public void updateLiderSeccion(Map criteria);
	
	/**
	 * Inserta un registro en la entidad [Historico Gerente]
	 * 
	 * @param criteria
	 */
	public void insertHistoricoGerente(Map criteria);
	
	/**
	 * Inserta un buzon de mensaje para la responsable anterior de una determinada seccion
	 * 
	 * @param criteria
	 */
	public void insertBuzonMensajeResponsable(Map criteria);
	
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
	 * Elimina registro del historico de gerentes
	 * @param oidHistoricoGerente
	 */
	public void deleteHistoricoGerente(Integer oidHistoricoGerente);
	
	/**
	 * Se obtiene el indicador de Asignacion Lider - LET
	 * @param criteria
	 * @return
	 */
	public String getIndicadorAsignarLider(Map criteria);
	
	/**
	 * Se actualiza los Lideres Pendientes y el Historico Gerencial
	 * @param criteria
	 */
	public void executeActualizarLETLiderPendiente (Map criteria);
	
	/**
	 * Se valida que la lider cumpla una condicin antes de ser asignada
	 * @param criteria
	 * @return
	 */
	public String getValidarAsignarLider(Map criteria);
	
	/**
	 * Obtiene el ultimo responsable (lider) de una determinada seccion. Cuando la campaa proceso es la siguiente
	 * 
	 * @param criteria
	 * @return
	 */
	public Map getUltimoResponsableSeccionValAsi(Map criteria);
	
	/**
	 * Se actualiza el historico de gerencia. Cuando la campaa proceso es la siguient
	 * @param criteria
	 */
	public void updateHistoricoGerenteValAsi(Map criteria);
	
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
	 * Obtiene el oidCliente en base al Codigo Consultora
	 * 
	 * @param codigoConsultora
	 * @return
	 */
	public String getOidClienteByCodigoCliente(String codigoConsultora);

	/**
	 * Obtiene el oidLider en base al oid Cliente
	 * 
	 * @param codigoConsultora
	 * @return
	 */
	public String getOidLider(String oidCliente);

	/**
	 * Obtiene los datos de la Lider
	 * 
	 * @param codigoConsultora
	 * @return
	 */
	public Map getDatosLider(String oidLider);
	/* FIN SA PER-SiCC-2012-0476  */

	public String getIndicadorZonaRegion(Map criteria);

	public void executeValidarAsignacionLiderRegionZona(Map criteria);

	public void executeProcesoDesvinculacion(Map criteria);

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
	 * Encuentra un programa LEC Corporativo en un rango de periodos
	 * @param periodoActual
	 * @return
	 */
	//public Map getEncontrarProgramaLecCorporativo(String periodoActual);
	
	/**
	 * Obtiene el maximo periodo hasta de una lider de una determinada seccion
	 * 
	 * @param codigoCliente
	 * @return
	 */
	public List getMaximoPeriodoHastaLider(String codigoCliente);
	
	/**
	 * Obtiene la descripcion de desvinculacion de acuerdo al codigo
	 * 
	 * @param codigo
	 * @return
	 */
	public String getDescripcionDesvinculacion(String codigo);
	
	/**
	 * Indica si es que la Consultora posee Correo
	 * @param oidCliente
	 * @return
	 */
	public Integer getIndicadorClienteCorreo(String oidCliente);
	
	/**
	 * Encuentra el Concurso y Tramo correspondiente a la campaa proceso
	 * @param criteria
	 * @return
	 */
	public Map getConcursoTramoPrograma(Map criteria);
	
	/**
	 * Devuelve si es que la campaa esta cerrada
	 * @param codigoPeriodo
	 * @return
	 */
	public Integer getCampanaCerrada(String codigoPeriodo);
	
	/**
	 * Obtiene todos los registros de la tabla de objetivos
	 * @return
	 */
	public List getObjetivosAll();
	
	/**
	 * Obtiene todos los registros de la tabla de objetivos retencion 2/2
	 * @return
	 */
	public List getObjetivosRetencion22All();
	
	/**
	 * Obtiene todos los registros de la tabla de objetivos retencion 3/3 y 4/4
	 * @return
	 */
	public List getObjetivosRetencion3344All();
	
	/**
	 * Inserta un objetivo
	 * @param temp
	 */
	public void insertObjetivo(Map temp);
	
	/**
	 * Actualiza un objetivo
	 * @param temp
	 */
	public void updateObjetivo(Map temp);
	
	/**
	 * Inserta una lider objetivo
	 * @param temp
	 */
	public void insertLiderObjetivo(Map temp);
	
	/**
	 * Actualiza una lider objetivo
	 * @param temp
	 */
	public void updateLiderObjetivo(Map temp);
	
	/**
	 * Inserta una Seccion objetivo
	 * @param temp
	 */
	public void insertSeccionObjetivo(Map temp);
	
	/**
	 * Actualiza una Seccion objetivo
	 * @param temp
	 */
	public void updateSeccionObjetivo(Map temp);
	
	/**
	 * valida si el lider esta registrado.
	 * 
	 * @param criteria
	 * @return
	 */
	public String validarCodigoLiderRegistrado(Map criteria);
	
	/**
	 * Obtiene un registro de la tabla de objetivos retencion 3/3 y 4/4
	 * @param criteria
	 * @return
	 */
	public String getObjetivosRetencion3344(Map criteria);
	
	/**
	 * Actualiza una Seccion objetivo 4/4
	 * @param temp
	 */
	public void updateSeccionObjetivo44(Map temp);
	
	/**
	 * Devuelve el OID del Cliente
	 * @param criteria
	 * @return
	 */
	public String getOidCliente(Map criteria);
	
	/**
	 * Obtiene Indicador Posible Lider
	 * 
	 * @param criteria
	 * @return
	 */
	//public Integer getIndicadorPosibleLider(Map criteria);
	
	/**
	 * Obtiene Indicador Posible Lider Historico
	 * 
	 * @param criteria
	 * @return
	 */
	//public Integer getIndicadorPosibleLiderHisto(Map criteria);

	
		
	/**
	 * Verifica si una consultora se encuentra en ZON_HISTO_GEREN
	 * @param criteria
	 * @return
	 */
	public Integer getVerificarLiderSeccionHistoGeren(Map criteria);
	
}