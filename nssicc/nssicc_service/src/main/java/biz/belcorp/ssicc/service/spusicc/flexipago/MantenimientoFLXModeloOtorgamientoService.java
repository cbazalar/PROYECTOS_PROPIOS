package biz.belcorp.ssicc.service.spusicc.flexipago;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.EstatusRechazoFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.EstatusRecomendacionFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.GrupoFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.MotivoRechazoFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.MotivoRecomendacionFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.RangoLDC;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * <p>
 * <a href="MantenimientoFLXModeloOtorgamientoService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *
 */
public interface MantenimientoFLXModeloOtorgamientoService extends Service {
	
	/**
	 * Devuelve la lista de las variables
	 * @param tipoVariable
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
	 * Actualiza un grupo de parametros y sus variables
	 * @param grupo
	 * @param usuario
	 */
	public void updateGrupo(GrupoFLX grupo, Usuario usuario);

	/**
	 * Devuelve la lista de grupos
	 * @param descripcion
	 * @return 
	 */
	public List getGrupos(String descripcion);


	/**
	 * Obtiene los datos del grupo
	 * @param id
	 * @return
	 */
	public GrupoFLX getGrupo(String id);

	/**
	 * Remueve un grupo previamente registrado 
	 * @param id
	 * @param usuario
	 */
	public void removeGrupo(String id, Usuario usuario);

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
	 * Actualiza las regiones de un grupo
	 * @param grupo
	 * @param usuario
	 */
	public void updateRegionesGrupo(GrupoFLX grupo, Usuario usuario);
	
	/**
	 * Devuelve la lista de Parmetros por Grupo
	 * @param codigoGrupo
	 * @return 
	 */
	public List getParametrosByGrupo(String codigoGrupo);
	
	/**
	 * Actualiza el valor de los Parmetros
	 * @param grupo
	 * @param usuario
	 */
	public void updateParametros(List parametros, Usuario usuario);

	/**
	 * Obtiene una lista de motivos de rechazo que coinciden con el parametro de entrada
	 * @param criteria
	 * @return
	 */
	public List getMotivosRechazoByCriteria(Map criteria);

	/**
	 * Elimina el motivo de rechazo
	 * @param codigo
	 * @param usuario
	 */
	public void removeMotivoRechazo(String codigo, Usuario usuario);

	/**
	 * Obtiene un motivo de rechazo por el codigo
	 * @param id
	 * @return
	 */
	public MotivoRechazoFLX getMotivoRechazo(String id);
	
	/**
	 * Inserta un motivo de rechazo
	 * @param motivo
	 * @param usuario
	 */
	public void insertMotivoRechazo(MotivoRechazoFLX motivo, Usuario usuario);
	
	/**
	 * Actualia un motivo de rechazo
	 * @param motivo
	 * @param usuario
	 */
	public void updateMotivoRechazo(MotivoRechazoFLX motivo, Usuario usuario);
	
	/**
	 * Obtiene una lista de motivos de recomendacion que coinciden con el parametro de entrada
	 * @param criteria
	 * @return
	 */
	public List getMotivosRecomendacionByCriteria(Map criteria);
	
	/**
	 * Elimina el motivo de recomendacion
	 * @param codigo
	 * @param usuario
	 */
	public void removeMotivoRecomendacion(String codigo, Usuario usuario);
	
	/**
	 * Obtiene un motivo de recomendacion por el codigo
	 * @param id
	 * @return
	 */
	public MotivoRecomendacionFLX getMotivoRecomendacion(String id);
	
	/**
	 * Inserta un motivo de recomendacion
	 * @param motivo
	 * @param usuario
	 */
	public void insertMotivoRecomendacion(MotivoRecomendacionFLX motivo, Usuario usuario);
	
	/**
	 * Actualia un motivo de recomendacion
	 * @param motivo
	 * @param usuario
	 */
	public void updateMotivoRecomendacion(MotivoRecomendacionFLX motivo, Usuario usuario);
	
	/**
	 * Obtiene una lista de estatus de recomendacion que coinciden con el parametro de entrada
	 * @param criteria
	 * @return
	 */
	public List getEstatusRecomendacionByCriteria(Map criteria);
	
	/**
	 * Elimina el estatus de recomendacion
	 * @param codigo
	 * @param usuario
	 */
	public void removeEstatusRecomendacion(String codigo, Usuario usuario);
	
	/**
	 * Obtiene un estatus de recomendacion por el codigo
	 * @param id
	 * @return
	 */
	public EstatusRecomendacionFLX getEstatusRecomendacion(String id);
	
	/**
	 * Inserta un estatus de recomendacion
	 * @param estatus
	 * @param usuario
	 */
	public void insertEstatusRecomendacion(EstatusRecomendacionFLX estatus, Usuario usuario);
	
	/**
	 * Actualia un estatus de recomendacion
	 * @param estatus
	 * @param usuario
	 */
	public void updateEstatusRecomendacion(EstatusRecomendacionFLX estatus, Usuario usuario);
	
	/**
	 * Obtiene una lista de estatus de rechazo que coinciden con el parametro de entrada
	 * @param criteria
	 * @return
	 */
	public List getEstatusRechazoByCriteria(Map criteria);
	
	/**
	 * Elimina el estatus de rechazo
	 * @param codigo
	 * @param usuario
	 */
	public void removeEstatusRechazo(String codigo, Usuario usuario);
	
	/**
	 * Obtiene un estatus de rechazo por el codigo
	 * @param id
	 * @return
	 */
	public EstatusRechazoFLX getEstatusRechazo(String id);
	
	/**
	 * Inserta un estatus de rechazo
	 * @param estatus
	 * @param usuario
	 */
	public void insertEstatusRechazo(EstatusRechazoFLX estatus, Usuario usuario);
	
	/**
	 * Actualia un estatus de rechazo
	 * @param estatus
	 * @param usuario
	 */
	public void updateEstatusRechazo(EstatusRechazoFLX estatus, Usuario usuario);
	

	/**
	 * Obtiene el listado de todas las zonas excluidas
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
	 * Inserta una zona excluida en la BD
	 * @param zonaExcluida
	 * @param usuario
	 */
	public void insertZonaExcluida(Map zonaExcluida, Usuario usuario);

	/**
	 * Obtiene la lista de rangos de LDC
	 * @return
	 */
	public List getRangosLDC();

	/**
	 * Actualiza los rangos de LDC
	 * @param rangos
	 * @param usuario
	 */
	public void updateRangosLDC(List rangos, Usuario usuario);

	/**
	 * 
	 * @param rango
	 * @param usuario
	 */
	public void insertRangoLDC(RangoLDC rango, Usuario usuario);

	/**
	 * 
	 * @param oidRango
	 * @param usuario
	 */
	public void deleteRangoLDC(String oidRango, Usuario usuario);

	/**
	 * Carga el archivo de lineas de credito
	 * @param criteria
	 * @return
	 */
	public int cargarArchivoLineasCreditoXLS(Map criteria) throws Exception;

	/**
	 * Ejecuta el proceso de validacion
	 * 
	 * @param params
	 * @return
	 */
	public List executeValidarArchivoLineasCredito(Map params);

	/**
	 * Procesa el archivo de lineas de credito
	 * @param criteria
	 */
	public void executeProcesarArchivoLineasCredito(Map criteria);

	/**
	 * Carga el archivo de consultoras a deshabilitar
	 * 
	 * @param criteria
	 * @return
	 */
	public int cargarArchivoConsultorasDeshabilitarXLS(Map criteria) throws Exception;

	/**
	 * Ejecuta el proceso de validación de consultoras a deshabilitar
	 * 
	 * @param params
	 * @return
	 */
	public List executeValidarArchivoConsultorasDeshabilitar(Map params);

	/**
	 * Procesa el archivo de consulotras a deshabilitar
	 *  
	 * @param criteria
	 */
	public void executeProcesarArchivoConsultorasDeshabilitar(Map criteria);

}