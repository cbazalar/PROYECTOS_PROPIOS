package biz.belcorp.ssicc.service.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ClasificacionParaINC;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ClasificacionParaINCDetalle;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoParametrosGenerales;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConstanciaProgramaPuntos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.NuevaConstanciaProgramaPuntos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.RangoConstanciaProgramaPuntos;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface MantenimientoINCConfiguracionConcursoService  extends Service {

	/**
	 * retorna la lista de Clasificaciones de Concurso
	 * 
	 * @return
	 */
	public List getClasificacionesConcurso();

	/**
	 *  retorna la lista de Concursos de acuerdo a determinados criterios
	 *  
	 * @param params
	 * @return
	 */
	public List getConcursosByCriteria(Map criteria);
	
	/**
	 * Elimina un concurso
	 * 
	 * @param calificacionComisionDetalle
	 */
	public void deleteConfiguracionConcurso(Map params);

	/**
	 * retorna la lista de programa de Concurso
	 * 
	 * @return
	 */
	public List getTiposPrograma();
	
	/**
	 * retorna la lista de Base de Calculo
	 * 
	 * @return
	 */
	public List getBaseCalculo();
	
	/**
	 * retorna la lista de a quienes esta dirigida el Concurso
	 * 
	 * @return
	 */
	public List getDirigidos();
	
	/**
	 * retorna la lista de Tipo de Venta
	 * 
	 * @return
	 */
	public List getTipoVenta();

	/**
	 * retorna la lista de Tipo de Exigencia
	 * 
	 * @return
	 */
	public List getTipoExigencia();
	
	/**
	 * retorna la lista de Concursos Recomendadas
	 * 
	 * @return
	 */
	public List getConcursosRecomendadas();	
	
	/**
	 * retorna la lista de Estatus de Cliente
	 * 
	 * @return
	 */
	public List getEstatusCliente();

	/**
	 * retorna la lista de Clasificaciones de Participantes
	 * 
	 * @return
	 */
	public List getClasificacionesParticipante();

	/**
	 * retorna la lista de Tipos de Premiacion
	 * 
	 * @return
	 */
	public List getTiposPremiacion();
	
	/**
	 * retorna la lista de Tipos de Eleccion
	 * 
	 * @return
	 */
	public List getTiposEleccion();

	/**
	 * retorna la lista de Centros de Servicio
	 * 
	 * @return
	 */
	public List getCentrosServicio();

	/**
	 * retorna la lista de Tipos de Productos
	 * 
	 * @return
	 */
	public List getTiposProducto();

	/**
	 * retorna la lista de Tipos de Oferta
	 * 
	 * @return
	 */
	public List getTiposOferta();
	
	/**
	 * retorna la lista de Tipos de Agrupacion
	 * 
	 * @return
	 */
	public List getTiposAgrupacion();

	/**
	 * retorna la lista de Unidades de Negocio
	 * 
	 * @return
	 */
	public List getUnidadesNegocio();

	/**
	 * retorna la lista de SuperGenericos filtrado por criterio de busqueda
	 * 
	 * @return
	 */
	public List getSuperGenericosByCriteria(Map criteria);

	/**
	 * retorna la lista de Genericos filtrado por criterio de busqueda
	 * 
	 * @return
	 */
	public List getGenericosByCriteria(Map criteria);
	
	/**
	 * retorna la lista de Ciclos de Vida
	 * 
	 * @return
	 */
	public List getCiclosVida();	
	
	/**
	 * retorna la lista de Negocios
	 * 
	 * @return
	 */
	public List getNegocios();		

	/**
	 * retorna la lista de Marca de Productos
	 * 
	 * @return
	 */
	public List getMarcaProductos();		
	
	/**
	 * @param concursoParametrosGenerales
	 * @param usuario
	 * @throws Exception
	 */
	public void insertConfiguracionConcurso(ConcursoParametrosGenerales concursoParametrosGenerales, Usuario usuario) throws Exception;
	
	/**
	 * Obtiene un registro en Configuracion de Concurso
	 *  
	 * @param oidConcurso
	 * @return
	 */
	public ConcursoParametrosGenerales getConfiguracionConcurso(String oidConcurso);
	
	/**
	 * @param concursoParametrosGenerales
	 * @param usuario
	 * @throws Exception
	 */
	public void updateConfiguracionConcurso(ConcursoParametrosGenerales concursoParametrosGenerales, Usuario usuario) throws Exception;

	/**
	 * Ejecuta el proceso de replicacion de Configuracion de concurso
	 * @param params
	 */
	public void executeReplicarConfiguracionConcurso(Map params);

	/**
	 * Validamos si se permite eliminar el Concurso
	 * 
	 * @param oidConcurso
	 * @return
	 */
	public boolean validarEliminacionConcurso(String oidConcurso);

	/**
	 * Recupera el codigo SAP asignado para Concursos de Recomendacion
	 * 
	 * @return
	 */
	public String getCodigoSAPRecomendacion();

	/**
	 * retorna la lista de estados de los Concursos
	 * 
	 * @return
	 */
	public List getEstados();
	
	/**
	 * retorna la lista de vigencias de los Concursos
	 * 
	 * @return
	 */
	public List getVigencias();

	/**
	 * retorna la lista de Indicadores de Tipo de Recomendada
	 * 
	 * @return
	 */
	public List getIndicadoresTipoRecomendada();

	/**
	 * Obtiene el Detalle de la Oferta relacionado al CUV
	 * 
	 * @param criteria
	 * @return
	 */
	public Map getDetalleOfertaCUV(Map criteria);

	/**
	 *  retorna la lista de faltantes pendientes de un determinado concurso
	 *  
	 * @param criteria
	 * @return
	 */
	public List getFaltantesPendientesConcurso(Map criteria);
	
	/**
	 *  retorna la lista de premios de un determinado concurso
	 *  
	 * @param criteria
	 * @return
	 */
	public List getPremiosConcurso(Map criteria);
	
	/**
	 *  retorna la lista de faltantes de un determinado concurso
	 *  
	 * @param criteria
	 * @return
	 */
	public List getFaltantesConcurso(Map criteria);

	/**
	 * Inserta un registro en la entidad Reemplazo Premio
	 * 
	 * @param params
	 */
	public void insertReemplazoPremio(Map params);
	
	/**
	 * Actualiza un registro en la entidad Reemplazo Premio
	 * 
	 * @param params
	 */
	public void updateReemplazoPremio(Map params);
	
	/**
	 * Elimina un registro de la entidad Reemplazo Premio
	 * 
	 * @param oidArticuloLote
	 */
	public void deleteReemplazoPremio(String oidReemplazo);
	
	/**
	 * Obtiene la lista de Criterios de Reemplazos
	 * 
	 * @param criteria
	 * @return
	 */
	public List getListCriterioDeReemplazo(Map criteria);

	/**
	 * Valida si ha realizado la ejecucion de un proceso batch de SICC
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean validaEjecucionProcesoBatch(Map criteria);

	/**
	 * Obtiene una lista de registros en la entidad Concurso Reemplazo Ambito
	 * 
	 * @param oidReemplazo
	 * @return
	 */
	public List getListReemplazoPremioAmbito(String oidReemplazo);

	/**
	 * Valida si ha realizado la atencion de un premio 
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean validaPremioAtendido(Map criteria);

	/**
	 * retorna la lista de Concursos Vigentes para Reporte Puntos
	 * 
	 * @return
	 */
	public List getListConcursosVigentesPuntos();
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public List getProgramasConstanciaProgramaPuntosByCriteria(Map params);

	/**
	 * 
	 * @param cpp
	 * @return
	 */
	public ConstanciaProgramaPuntos getConstanciaProgramaPuntos(ConstanciaProgramaPuntos cpp);

	/**
	 * 
	 * @param cpp
	 * @param usuario
	 */
	public void insertConstanciaProgramaPuntos(ConstanciaProgramaPuntos cpp, Usuario usuario);
	
	/**
	 * 
	 * @param cpp
	 * @param usuario
	 */
	public void updateConstanciaProgramaPuntos(ConstanciaProgramaPuntos cpp, Usuario usuario);

	/**
	 * 
	 * @param rango
	 * @param usuario
	 */
	public void insertRangoConstanciaProgramaPuntos(RangoConstanciaProgramaPuntos rango, Usuario usuario) throws Exception;

	/**
	 * 
	 * @param programa
	 * @return
	 */
	public List getRangoConstanciaProgramaPuntosList(Map params);

	/**
	 * 
	 * @param rango
	 * @param usuario
	 */
	public void deleteRangoConstanciaProgramaPuntos(RangoConstanciaProgramaPuntos rango, Usuario usuario);
	
	/**
	 * Devuelve cero si no existen premios descuento.
	 * @return
	 */
	public Integer getExistePremiosDescuento(Map criteria);
	
	/**
	 * Devuelve cero si no existen premios articulos
	 */
	public Integer getExistePremiosArticulo(Map criteria);
	
	/**
	 * @return
	 */
	public String getcodigoProductoDescuento();
	
	public Integer getNivelSelectivo(Map criteria);
	
	
	/**
	 * Devuelve Lista de la tabla INC_PARTI_CONCU_CABEC
	 * @param programa
	 * @return
	 */
	public List getClasificacionParaINCByCriteria(Map params);
	
	/**
	 * Devuelve registro ClasificacionParaINC
	 * @param params
	 * @return
	 */
	public ClasificacionParaINC getClasificacionParaINC(Map params);
	
	/**
	 * Inserta en tabla INC_PARTI_CONCU_CABEC
	 * @param bean
	 * @param usuario
	 */
	public void insertClasificacionParaINC(ClasificacionParaINC bean, Usuario usuario);
	
	/**
	 * Actualiza en tabla INC_PARTI_CONCU_CABEC 
	 * @param bean
	 * @param usuario
	 */
	public void updateClasificacionParaINC(ClasificacionParaINC bean, Usuario usuario);
	
	/**
	 * Elimina registro en tabla INC_PARTI_CONCU_CABEC
	 * @param bean
	 * @param usuario
	 */
	public void deleteClasificacionParaINC(ClasificacionParaINC bean, Usuario usuario);
	
	/**
	 * Inserta en tabla INC_PARTI_CONCU_DETAL
	 * @param bean
	 * @param usuario
	 */
	public void insertClasificacionParaINCDetalle(ClasificacionParaINCDetalle bean, Usuario usuario);
	
	/**
	 * Actualiza en tabla INC_PARTI_CONCU_DETAL
	 * @param bean
	 * @param usuario
	 */
	public void updateClasificacionParaINCDetalle(ClasificacionParaINCDetalle bean, Usuario usuario);
	
	/**
	 * Elimina registro en tabla INC_PARTI_CONCU_DETAL
	 * @param bean
	 * @param usuario
	 */
	public void deleteClasificacionParaINCDetalle(ClasificacionParaINCDetalle bean, Usuario usuario);
	
	/**
	 * Devuelve Lista de la tabla INC_PARTI_CONCU_DETAL
	 * @param programa
	 * @return
	 */
	public List getClasificacionParaINCDetalleByCriteria(Map params);
	
	/**
	 * Devuelve registro ClasificacionParaINCDetalle
	 * @param params
	 * @return
	 */
	public ClasificacionParaINCDetalle getClasificacionParaINCDetalle(Map params);
	
	/**
	 * Devuelve Lista de Tipos de Clientes
	 * @param params
	 * @return
	 */
	public List getTipoClienteList(Map params);
	
	/**
	 * Devuelve Lista de Subtipos de Clientes
	 * @param params
	 * @return
	 */
	public List getSubTipoClienteList(Map params);
	
	/**
	 * Devuelve Lista de Tipo de Clasificaciones
	 * @param params
	 * @return
	 */
	public List getTipoClasificacionList(Map params);
	
	/**
	 * Devuelve Lista de Clasificaciones
	 * @param params
	 * @return
	 */
	public List getClasificacionList(Map params);
	
	/**
	 * Verifica si existe registro de Cabecera INC en la tabla INC_CLASI_PARTI_CONCU
	 * @param params
	 * @return
	 */
	public Integer getExisteRegistroClasificacionPartiConcu(Map params);
	
	/**
	 * Verifica si existe registro de Cabecera INC en la tabla INC_PARTI_CONCU_DETAL
	 * @param params
	 * @return
	 */
	public Integer getExisteRegistroClasificacionDetalle(Map params);
	
	/**
	 * 
	 * @param rango
	 * @param usuario
	 */
	public void insertNuevaConstanciaProgramaPuntos(NuevaConstanciaProgramaPuntos rango, Usuario usuario) throws Exception;

	/**
	 * 
	 * @param programa
	 * @return
	 */
	public List getNuevaConstanciaProgramaPuntosList(Map params);

	/**
	 * 
	 * @param rango
	 * @param usuario
	 */
	public void deleteNuevaConstanciaProgramaPuntos(NuevaConstanciaProgramaPuntos rango, Usuario usuario);
	
	/**
	 * retorna la lista de Concursos Tipo de Programa Puntos
	 * 
	 * @return
	 */
	public List getListConcursosTipoProgramaPuntos();
	
}
