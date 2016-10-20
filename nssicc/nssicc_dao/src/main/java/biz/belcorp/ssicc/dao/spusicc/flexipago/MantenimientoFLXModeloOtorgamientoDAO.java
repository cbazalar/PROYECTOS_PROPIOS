package biz.belcorp.ssicc.dao.spusicc.flexipago;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.EstatusRechazoFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.EstatusRecomendacionFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.GrupoFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.GrupoRegionFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.GrupoVariableFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.MotivoRechazoFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.MotivoRecomendacionFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.ParametroFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.RangoLDC;

/**
 * <p>
 * <a href="MantenimientoFLXModeloOtorgamientoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *
 */
public interface MantenimientoFLXModeloOtorgamientoDAO extends DAO {
	
	/**
	 * Devuelve la lista de las variables
	 * @param criteria
	 * @return
	 */
	public List getVariables(String tipoVariable);

	/**
	 * Inserta un grupo de parametros y sus variables
	 * @param grupo
	 * @param usuario
	 */
	public void insertGrupo(GrupoFLX grupo, Usuario usuario);

	/**
	 * Devuelve el id de grupo insertado
	 * @return
	 */
	public String getIdGrupo();
	
	/**
	 * Insertar Grupo y variables
	 * @param grupoVariable
	 * @param usuario
	 */
	public void insertGrupoVariable(GrupoVariableFLX grupoVariable, Usuario usuario);
	
	/**
	 * Devuelve la lista de grupos
	 * @param descripcion
	 * @return
	 */
	public List getGrupos(String descripcion);
	
	/**
	 * Devuelve el grupo buscado
	 * @param id
	 * @return
	 */
	public GrupoFLX getGrupo(String id);
	
	/**
	 * Retorna la lista de variables que pertenecen al grupo
	 * @param tipoVariable
	 * @param codigoGrupo
	 * @return
	 */
	public List getVariablesGrupo(String tipoVariable, String codigoGrupo);
	
	/**
	 * Actualiza los grupos de variables
	 * @param grupo
	 * @param usuario
	 */
	public void updateGrupo(GrupoFLX grupo, Usuario usuario);
	
	/**
	 * Actualiza los grupos de variables
	 * @param grupoVariable
	 * @param usuario
	 */
	public void updateGrupoVariable(GrupoVariableFLX grupoVariable, Usuario usuario);
	
	/**
	 * Devuelve un listado de regiones por grupo
	 * @param codigoGrupo
	 * @return
	 */
	public List getGruposRegiones(String codigoGrupo);

	/**
	 * Obtiene un listado de las regiones agrupadas o disponibles para el grupo
	 * @param codigoGrupo
	 * @param flagAgrupadas
	 * @return
	 */
	public List getRegionesAgrupadas(String codigoGrupo, boolean flagAgrupadas);

	/**
	 * Elimina las regiones asignadas a un grupo
	 * @param codigo
	 * @param usuario
	 */
	public void removeRegionesGrupo(String codigo, Usuario usuario);

	/**
	 * Registra una region a un grupo
	 * @param grupoRegion
	 * @param usuario
	 */
	public void insertRegionGrupo(GrupoRegionFLX grupoRegion, Usuario usuario);	
	
	/**
	 * Devuelve la lista de Parmetros por Grupo
	 * @param codigoGrupo
	 * @return 
	 */
	public List getParametrosByGrupo(String codigoGrupo);
	
	/**
	 * Actualiza el valor de un Parmetro
	 * @param parametroFLX
	 */
	public void updateParametro(ParametroFLX parametroFLX, Usuario usuario);

	/**
	 * Obtiene una lista de rechazos que coinciden con el parametro
	 * @param criteria
	 * @return
	 */
	public List getMotivosRechazoByCriteria(Map criteria);

	/**
	 * Obtiene un motivo de rechazo en base al codigo
	 * @param codigo
	 * @return
	 */
	public MotivoRechazoFLX getMotivoRechazo(String codigo);

	/**
	 * Actualiza un motivo de rechazo
	 * @param motivo
	 * @param usuario
	 */
	public void updateMotivoRechazo(MotivoRechazoFLX motivo, Usuario usuario);

	/**
	 * Inserta un motivo de rechazo
	 * @param motivo
	 * @param usuario
	 */
	public void insertMotivoRechazo(MotivoRechazoFLX motivo, Usuario usuario);
	
	/**
	 * Obtiene una lista de recomendaciones que coinciden con el parametro
	 * @param criteria
	 * @return
	 */
	public List getMotivosRecomendacionByCriteria(Map criteria);
	
	/**
	 * Obtiene un motivo de recomendacion en base al codigo
	 * @param codigo
	 * @return
	 */
	public MotivoRecomendacionFLX getMotivoRecomendacion(String codigo);
	
	/**
	 * Actualiza un motivo de recomendacion
	 * @param motivo
	 * @param usuario
	 */
	public void updateMotivoRecomendacion(MotivoRecomendacionFLX motivo, Usuario usuario);
	
	/**
	 * Inserta un motivo de recomendacion
	 * @param motivo
	 * @param usuario
	 */
	public void insertMotivoRecomendacion(MotivoRecomendacionFLX motivo, Usuario usuario);
	
	/**
	 * Obtiene una lista de estatus de recomendaciones que coinciden con el parametro
	 * @param criteria
	 * @return
	 */
	public List getEstatusRecomendacionByCriteria(Map criteria);
	
	/**
	 * Obtiene un estatus de recomendacion en base al codigo
	 * @param codigo
	 * @return
	 */
	public EstatusRecomendacionFLX getEstatusRecomendacion(String codigo);
	
	/**
	 * Actualiza un estatus de recomendacion
	 * @param estatus
	 * @param usuario
	 */
	public void updateEstatusRecomendacion(EstatusRecomendacionFLX estatus, Usuario usuario);
	
	/**
	 * Inserta un estatus de recomendacion
	 * @param estatus
	 * @param usuario
	 */
	public void insertEstatusRecomendacion(EstatusRecomendacionFLX estatus, Usuario usuario);
	
	/**
	 * Obtiene una lista de estatus de rechazos que coinciden con el parametro
	 * @param criteria
	 * @return
	 */
	public List getEstatusRechazoByCriteria(Map criteria);
	
	/**
	 * Obtiene un estatus de rechazo en base al codigo
	 * @param codigo
	 * @return
	 */
	public EstatusRechazoFLX getEstatusRechazo(String codigo);
	
	/**
	 * Actualiza un estatus de rechazo
	 * @param estatus
	 * @param usuario
	 */
	public void updateEstatusRechazo(EstatusRechazoFLX estatus, Usuario usuario);
	
	/**
	 * Inserta un estatus de rechazo
	 * @param estatus
	 * @param usuario
	 */
	public void insertEstatusRechazo(EstatusRechazoFLX estatus, Usuario usuario);

	/**
	 * Obtiene un parametro por su codigo (OID)
	 * @param codigo
	 * @return
	 */
	public ParametroFLX getParametro(String codigo);

	/**
	 * Obtiene el objeto grupovariable por su oid
	 * @param codigo
	 * @return
	 */
	public GrupoVariableFLX getGrupoVariable(String codigo);
	
	/**
	 * Obtiene las regiones que pertenecen a un grupo
	 * @param codigoGrupo
	 * @return
	 */
	public List getRegionesByGrupo(String codigoGrupo);
	

	/**
	 * Ovtiene el listado de zonas excluidas
	 * @param params
	 * @return
	 */
	public List getZonasExcluidasByCriteria(Map params);

	/**
	 * Elimina una zona excluida
	 * @param params
	 */
	public void removeZonaExcluida(Map params);

	/**
	 * Inserta una zona excluida
	 * 
	 * @param zonaExcluida
	 */
	public void insertZonaExcluida(Map zonaExcluida);

	/**
	 * Obtiene los rangos de LDC
	 * 
	 * @return
	 */
	public List getRangosLDC();

	/**
	 * Obtiene un rango de LDC por el oid
	 * @param oid
	 * @return
	 */
	public RangoLDC getRangoLDC(String oid);

	/**
	 * Actualiza un rango de LDC
	 * @param rangoLDC
	 * @param usuario
	 */
	public void updateRangoLDC(RangoLDC rangoLDC, Usuario usuario);
	
	/**
	 * 
	 * @param rango
	 * @param usuario
	 */
	public void insertRangoLDC(RangoLDC rango, Usuario usuario);

	/**
	 * Elimina los datos de la tabla temporal de LDC
	 *  
	 * @param login
	 */
	public void deleteTemporalLineasCredito(String usuario);

	/**
	 * Inserta un registro en la tabla temporal de LDC
	 * @param params
	 */
	public void insertTemporalLineasCredito(Map params);

	/**
	 * 
	 * @param params
	 */
	public void executeValidarArchivoLineasCredito(Map params);

	/**
	 * 
	 * @param params
	 * @return
	 */
	public List getErroresArchivoLineasCredito(Map params);

	/**
	 * 
	 * @param criteria
	 */
	public void executeProcesarArchivoLineasCredito(Map criteria);

	/**
	 * 
	 * @param codigoUsuario
	 */
	public void deleteTemporalConsultorasDeshabilitar(String codigoUsuario);

	/**
	 * 
	 * @param params
	 */
	public void insertTemporalConsultorasDeshabilitar(Map params);

	/**
	 * 
	 * @param params
	 */
	public void executeValidarArchivoConsultorasDeshabilitar(Map params);

	/**
	 * 
	 * @param params
	 * @return
	 */
	public List getErroresArchivoConsultorasDeshabilitar(Map params);

	/**
	 * 
	 * @param criteria
	 */
	public void executeProcesarArchivoConsultorasDeshabilitar(Map criteria);

}