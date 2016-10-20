package biz.belcorp.ssicc.dao.spusicc.lideres;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.sisicc.model.Base;

/**
 * @author peextsapaza
 *
 */
public interface MantenimientoLIDLideresDAO extends DAO {

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
	 * Inserta un registro en la entidad [Historico Gerente]
	 * 
	 * @param criteria
	 */
	public void insertHistoricoGerente(Map criteria);

	/**
	 * Actualiza la lider de la Seccion
	 * 
	 * @param criteria
	 */
	public void updateLiderSeccion(Map criteria);
	
	/**
	 * Inserta un buzon de mensaje para la responsable anterior de una determinada seccion
	 * 
	 * @param criteria
	 */
	public void insertBuzonMensajeResponsable(Map criteria);

	/**
	 * Elimina el registro con clasificacion lider de la consultora anterior
	 * @param criteria
	 */
	public void deleteClasificacionLiderConsultoraAnterior(Map criteria);
	
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
	 * Retorna la lista de condicon de despachos activas 
	 * @return
	 */
	public List getListCondicionesLideres();
	
	/**
	 * Retorna la lista de Objeto de Condicion de Despacho de la Canasta
	 * @param codigoPeriodo
	 * @return
	 */
	public List getListCondicionDespachoCanasta(String codigoPeriodo);

	/**
	 * Inserta la Condicion del Despacho de la Canasta
	 * @param map
	 */
	public void insertCondicionDespachoCanasta(Map map);

	/**
	 * Actualiza la Condicion del Despacho de la Canasta
	 * @param map
	 */
	public void updateCondicionDespachoCanasta(Map map);

	/**
	 * Retorna la lista de productos de la canasta
	 * @param codigoPeriodo
	 * @return
	 */
	public List getListProductosCanasta(String codigoPeriodo);
	
	/**
	 * Inserta los Productos  de la Canasta
	 * @param map
	 */
	public void insertProductosCanasta(Map map);

	/**
	 * Actualiza los Productos de la Canasta
	 * @param map
	 */
	public void updateProductosCanasta(Map map);
	
	/**
	 * Retorna la lista de tipo de ofertas
	 * @return
	 */
	public List getListTipoOferta();
	
	/**
	 * Retorna el oid de la matrtiz de la facturacion
	 * @return
	 */
	public Integer getDevuelveOidMatrizFacturacion(Map map);

	/**
	 * Devuelve descripcion producto
	 * @param codigoSap
	 * @return
	 */
	public String getDescripcionInternacionalizableProducto(String codigoSap);
	
	/**
	 * Recorre la lista de producto para insertarlo en la cansta de lideres
	 * @param map
	 */
	public void insertListProductosCanasta(Map map);
}
