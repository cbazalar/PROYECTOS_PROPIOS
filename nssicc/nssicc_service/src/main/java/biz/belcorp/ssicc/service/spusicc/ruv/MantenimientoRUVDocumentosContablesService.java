package biz.belcorp.ssicc.service.spusicc.ruv;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author peextdoliva
 */

public interface MantenimientoRUVDocumentosContablesService extends Service{

	/**
	 * Retorna la lista de Tipos de documento contables
	 * @return
	 */
	public List getTipoDocumentoContable();	
	
	/**
	 * Retorna Los limites de los documentos legales
	 * @param criteria
	 * @return
	 */
	public List getLimitesDocumentosLegales(Map criteria);
	
	/**
	 * Retorna el total de los documentos legales
	 * @param criteria
	 * @return
	 */
	public String getTotalDocumentosLegales(Map criteria);
	
	/**
	 * Retorna la lista de documentos Sin impresion, sin unidades, sin total
	 * @param criteria
	 * @return
	 */
	public List getSinImpresionSinUnidades(Map criteria);
	
	/**
	 * Elimina por oid del registro unico de ventas
	 * @param criteria
	 */
	public void deleteRegistroUnicoVentasById(String[] oidRUVList);

	/**
	 * Retorna la lista de documentos legales duplicados  
	 * @param criteria
	 * @return
	 */
	public List getLegalesDuplicados(Map criteria);

	/**
	 * Retorna la lista de documentos sin asignar numero de documento
	 * @param criteria
	 * @return
	 */
	public List getSinAsignarNumeroDocumento(Map criteria);

	/**
	 * Generar la data en las tablas temporales para la lectura del reporte
	 * @param params
	 */
	public void executeGenerarDataDocumentosContables(Map params);
	
	/**
	 * Devuelve la cantidad de documentos por asignar por dias
	 * @param criteria
	 * @return
	 */
	public String getAsignarNulosPorDias(Map criteria);
	
	/**
	 * Devuelve la cantidad de documentos por asignar por rangos
	 * @param criteria
	 * @return
	 */
	public String getAsignarNulosPorRango(Map criteria);
	
	/**
	 * Ejecuta el proceso de asignar nulos por dias
	 * @param criteria
	 */
	public void executeAsignarNulosPorDias(Map criteria);
	
	/**
	 * Ejecuta el proceso de asignar nulos por rango
	 * @param criteria
	 */
	public void executeAsignarNulosPorRango(Map criteria);
	
	/**
	 * @param criteria
	 * @return La descripcin de Seg Acceso
	 */
	public String getDescripcionAcceso(Map criteria);
	
	/**
	 * @param criteria
	 * @return El Oid Seg Acceso
	 */
	public String getOidSegAcceso(Map criteria);

	/**
	 * @param criteria
	 * @return La descripcin de Seg Sub Acceso
	 */
	public String getDescripcionSubAcceso(Map criteria);
	
	/**
	 * @param criteria
	 * @return El Oid Seg Sub Acceso
	 */
	public String getOidSegSubAcceso(Map criteria);
	
	/**
	 * @param criteria
	 * @return La lista de Documentos Contables Legales
	 */
	public List getDocContablesLegales(Map criteria);

	/**
	 * @param criteria
	 * @return La lista de Documentos Contables Internos
	 */
	public List getDocContablesInternos(Map criteria);
	
	/**
	 * @param params
	 * @return Genera una lista de Documentos Legales Duplicados en una tabla temporal y luego lo lista. 
	 */
	public List executeGenerarDataDocumentosLegalDuplicado(Map params);
	
	/**
	 * @param criteria
	 * @return Descripcion de Canal
	 */
	public String getDescCanal( Map criteria);

	/**
	 * @param criteria
	 * @return Oid Canal x Codigo
	 */
	public String getOidCanalxCod(Map criteria);

	/**
	 * Elimina los documento legales Limites y Totales que cumplan con el criterio de busqueda filtrado
	 * @param criteria
	 */
	public void deleteDocuLegalesLimites(Map criteria);

	/**
	 * Elimina los documento legales que cumplan con el criterio de busqueda filtrado
	 * @param criteria
	 */
	public void deleteDocuLegales(Map criteria);

	/**
	 * Elimina los documento internos que cumplan con el criterio de busqueda filtrado
	 * @param criteria
	 */
	public void deleteDocuInternos(Map criteria);

	/**
	 * Devuelve el indicador para mostrar el boton de eliminar
	 * @param codigoPais
	 * @return
	 */
	public String getIndicadorRUVEliminarDocumentoContable(String codigoPais);	
	
	/**
	 * Devuelve el indicador de activacin para nuevas funcionalidades del mdulo de Documentos Contables
	 * @param criteria
	 * @return
	 */
	public String getIndicadorActivacionDocumentoContable(Map criteria);
	
	/**
	 * Devuelve Nmero de Control Lmites
	 * @param criteria
	 * @return
	 */
	public List getLimitesControlDocumentosLegales(Map criteria);
	
	/**
	 * Devuelve Nmero de Control Totales
	 * @param criteria
	 * @return
	 */
	public String getTotalControlDocumentosLegales(Map criteria);
	
	/**
	 * Devuelve la lista de Nmero de Control de Documentos Legales
	 * @param criteria
	 * @return 
	 */
	public List getNumeroControlDocLeg(Map criteria);
	
	/**
	 * Genera una lista de Numero de Control Duplicados en una tabla temporal y luego lo lista.
	 * @param params
	 * @return  
	 */
	public List executeGenerarDataNumeroControlDuplicado(Map params);

	/**
	 * Retorna la lista de documentos sin asignar numero de control
	 * @param criteria
	 * @return
	 */
	public List getSinAsignarNumeroControl(Map criteria);
	
	/**
	 * Devuelve la lista de Numero de Control Nulos
	 * @param criteria
	 * @return 
	 */
	public List getAsignarNulosDiasPais(Map criteria);
	
	/**
	 * Asignar Numero Control por Dias por Pais
	 * @param criteria
	 */
	public void updateNulosDiasPais(List lista, String[] numeroControl, String codigoPais);
	
	/**
	 * Devuelve la cantidad de documentos por asignar por dias Venezuela
	 * @param criteria
	 * @return
	 */
	public String getAsignarNulosPorDiasVenezuela(Map criteria);
	
	/**
	 * Devuelve la cantidad de documentos por asignar por rango de documentos legales Venezuela
	 * @param criteria
	 * @return
	 */
	public String getAsignarNulosPorRangoVenezuela(Map criteria);
	
	/**
	 * Ejecuta el proceso de asignar nulos por dias Venezuela
	 * @param criteria
	 */
	public void executeAsignarNulosPorDiasVenezuela(Map criteria);
	
	/**
	 * Ejecuta el proceso de asignar nulos por rango Venezuela
	 * @param criteria
	 */
	public void executeAsignarNulosPorRangoVenezuela(Map criteria);
	
	/**
	 * Devuelve la lista de Numero de Control Nulos Venezuela
	 * @param criteria
	 * @return 
	 */
	public List getAsignarNulosDiasVenezuela(Map criteria);
	
	/**
	 * Asignar Numero Control por Dias Venezuela
	 * @param criteria
	 */
	public void updateNulosDiasVenezuela(List lista, String[] numeroControl, String codigoPais);
}