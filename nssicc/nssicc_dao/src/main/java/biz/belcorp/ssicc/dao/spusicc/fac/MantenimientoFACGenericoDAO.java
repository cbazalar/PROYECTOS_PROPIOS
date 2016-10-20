package biz.belcorp.ssicc.dao.spusicc.fac;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface MantenimientoFACGenericoDAO extends DAO {
	
	/**
	 * retorna los cierres de facturacion 
	 * @param criteria
	 * @return
	 */
	List getCierreFacturacion(Map criteria);

	/**elimna la CierreFacturacion , logicamente
	 * @param map
	 */
	void deleteCierreFacturacion(Map map);

	/**
	 * Realiza la insercion de la CierreFacturacion
	 * @param map
	 */
	void saveCierreFacturacion(Map map);
	
	/**
	 * Retorna el consolidado de cierre
	 * @param criteria
	 * @return
	 */
	List getConsolidadoCierreFacturacion(Map criteria);
	
	/**
	 * Actualiza el cierre de facturacion
	 * @param map
	 */
	void updateCierreFacturacion(Map map);

	/**
	 * Lista que retorna las regiones que faltan pro cerrar en la campanha
	 * @param map 
	 * @return
	 */
	List getRegionesPorCerrar(Map map);
	
	/**
	 * Lista que retorna las zonas que faltan pro cerrar en la campanha
	 * @param map 
	 * @return
	 */
	List getZonasPorCerrar(Map map);

	/**
	 * Retorna la lista de las regiones por cerrar
	 * @param bean
	 * @return
	 */
	List getCierreFacturacionRegion(Map bean);

	/**
	 * Retorna la lista de las zonas por cerrar
	 * @param bean
	 * @return
	 */
	List getCierreFacturacionZona(Map bean);
	
	/**
	 * Retorna la descripcion de la region
	 * @param codigoRegion
	 * @return
	 */
	String getDescripcionRegion(String codigoRegion);

	/**
	 * Retorna el codigo region
	 * @param codigoZona
	 * @return
	 */
	String getCodigoRegion(String codigoZona);

	/**
	 * Retorna la descripcon d ela zonas
	 * @param codigoZona
	 * @return
	 */
	String getDescripcionZona(String codigoZona);
	
	/**
	 * Eliminar region y zonas de sus temporales
	 */
	void deleteTemporales();

	/**
	 * @param bean
	 */
	void saveTemporalZona(Map bean);
	
	/**
	 * eliminar la zona del temporal
	 * @param bean
	 */
	void deleteTemporalZona(Map bean);

	/**
	 * Guardar la region en el temporal
	 * @param bean
	 */
	void saveTemporalRegion(Map bean);

	/**
	 * Eleminar la region del temporal
	 * @param bean
	 */
	void deleteTemporalRegion(Map bean);
	
	/**
	 * Retorna la lista de aprobadas menores a la fecha de cierre ingresada
	 * en el map
	 * @param map
	 * @return
	 */
	List getAprobadasAnteriores(Map map);

	/**
	 * Retorna la lista de canceladadas mayores a la fecha de cierre ingresada
	 * en el map
	 * @param map
	 * @return
	 */
	List getCanceladasPosterior(Map map);

	/* INI JJ  PER-SiCC-2012-0388 */
	/**
	 * Obtiene el codigo de Zona por medio del Oid de Zona
	 * @param oidZona
	 * @return
	 */
	String getCodigoZonaByOidZona(Integer oidZona);
	/* FIN JJ  PER-SiCC-2012-0388 */
	
	/* INI SA PER-SiCC-2012-1120 */
	/**
	 * Obtiene el codigo de Region por medio del Oid de Region
	 * @param oidZona
	 * @return
	 */
	String getCodigoRegionByOidRegion(Integer oidRegion);
	/* FIN SA PER-SiCC-2012-1120 */
	
	/**
	 * Actualiza facturacion electrnica de consultora
	 * @param map
	 */
	void updateAutorizacionFacturacionElectronica(Map map);

	/**
	 * Obtiene el oid del tipo de documento electrnico
	 * @author sguerra
	 * @param criteria
	 */
	String getTipoDocumento(Map criteria);

	/**
	 * Insertar documento electrnico
	 * @author sguerra
	 * @param criteria
	 */
	void insertDocumentoElectronico(Map criteria);

	/**
	 * Actualiza el indicador de facturacin electrnica de la interfaz
	 * @author sguerra
	 * @param criteria
	 */
	void updateIndicadorFacturacionElectronica(Map criteria);

	/**
	 * Elimina las Zonas ingresadas anteriormente
	 */
	public void deleteZonasDeshabilitadasParaEnvio();
	
	/**
	 * Inserta las zonas a ser deshabilitadas para el envio de premios
	 * @param params
	 */
	public void insertZonasDeshabilitadasParaEnvio(Map params);
	
	/**
	 * retorna las zonas deshabilitadas 
	 * @return
	 */
	List getZonasDeshabilitadas();

	/**
	 * Actualiza la tabla temporal cabecera notas de credito retail 
	 * @param criteria
	 */
	public void updateIndicadorProcesoNCRetail(Map criteria);

	/**
	 * Obtiene la lista detallada de estados de facturacin para Boleta y Factura
	 * @author sguerra
	 * @param criteria
	 * @return
	 */
	List getListaEstadoFacturacionDetalladoBF(Map criteria);

	/**
	 * Obtiene la lista detallada de estados de facturacin para notas de crdito y dbito
	 * @author sguerra
	 * @param criteria
	 * @return
	 */
	List getListaEstadoFacturacionDetalladoNCND(Map criteria);

	/**
	 * Obtiene la lista detallada de estados de facturacin para Nota de Credito Retail Contra Boleta y Factura
	 * @author sguerra
	 * @param criteria
	 * @return
	 */
	List getListaEstadoFacturacionDetalladoNCRetailBF(Map criteria);

	/**
	 * Obtiene la lista detallada de estados de facturacin para todos los tipos de documentos
	 * @author sguerra
	 * @param criteria
	 * @return
	 */
	List getListaEstadoFacturacionDetalladoTodos(Map criteria);

	/**
	 * Obtiene la lista consolidada de estados de facturacin para Boleta y Factura
	 * @author sguerra
	 * @param criteria
	 * @return
	 */
	List getListaEstadoFacturacionConsolidadoBF(Map criteria);

	/**
	 * Obtiene la lista consolidada de estados de facturacin para notas de crdito y dbito
	 * @author sguerra
	 * @param criteria
	 * @return
	 */
	List getListaEstadoFacturacionConsolidadoNCND(Map criteria);

	/**
	 * Obtiene la lista consolidada de estados de facturacin para Nota de Credito Retail Contra Boleta y Factura
	 * @author sguerra
	 * @param criteria
	 * @return
	 */
	List getListaEstadoFacturacionConsolidadoNCRetailBF(Map criteria);

	/**
	 * Obtiene la lista consolidada de estados de facturacin para todos los tipos de documentos
	 * @author sguerra
	 * @param criteria
	 * @return
	 */
	List getListaEstadoFacturacionConsolidadoTodos(Map criteria);
	
	/**
	 * Verifica si existe Registros con Tipo Cierre en 'R'
	 * @param criteria
	 * @return
	 */
	public Integer getExisteCierreFacturacionRegion(Map criteria);

	/**
	 * Elimina los datos de las tablas temporales de facturacin electrnica
	 */
	public void deleteTablasTemporalesCabecera();

	/**
	 * Elimina los datos de las tablas temporales de facturacin electrnica
	 */
	public void deleteTablasTemporalesDetalle();
	
	/**
	 * Lista que retorna las zonas abiertas para la campaña de proceso
	 * @param map 
	 * @return
	 */
	List getZonasAbiertas(Map map);
	
	/**
	 * Lista que retorna las regiones abiertas para la campaña de proceso
	 * @param map 
	 * @return
	 */
	List getRegionesAbiertas(Map map);

}
