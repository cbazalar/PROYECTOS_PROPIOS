package biz.belcorp.ssicc.dao.edu.gen;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * 
 * @author David Hinostroza Vintes
 * 
 */

public interface GenericoEDUComercialDAO extends DAO {

	public List getPedidosComercial(String dataSource, Map params) throws Exception;
	
	public List getPedidosComercialFacturados(String dataSource, Map params)throws Exception;
	
	public List getPedidosCursosFacturados(String dataSource, Map params)throws Exception;	
	
	public List getPedidosCursosNoFacturados(String dataSource, Map params)throws Exception;	

	public List getConsultorasNuevas(String dataSource, Map params)throws Exception;

	public List getDetalleProducto(String dataSource, Map params)throws Exception;
	
	public List getMatrizProducto(String dataSource, Map params)throws Exception;	
	
	public List getRegionesComercial(String dataSource, Map params)throws Exception;
	
	public List getZonasComercial(String dataSource, Map params)throws Exception;

	public List getControlFacturacionComercial(String dataSource, Map params)throws Exception;
	
	public void insertConsultorasAptas(String dataSource,final List list,final String numeroLote,Map params) throws Exception;
	
	public void insertConsultorasAptasCosto(String dataSource,final List list,final String numeroLote,Map params) throws Exception;	
	
	public void insertConsultorasAptasporProgramar(String dataSource, final List list,final String numeroLote, String periodo,Map params) throws Exception;
	
	public List getConsultorasAptasporProgramar(String dataSource, Map params) throws Exception;
	
	public void insertParametrosCursoCapacitacion(String dataSource, Map params) throws Exception;
	
	public void updateParametrosCursoCapacitacion(String dataSource, Map params) throws Exception;
	
	public void deleteConsultorasAptas(String dataSource, Map params) throws Exception; 
	
	public void deleteConsultorasAptasCosto(String dataSource, Map params);	
	
	public void deleteConsultorasAptasProgramar(String dataSource, Map params);
	
	public void insertAptasHistoricas(String dataSource,final List list,Map params) throws Exception;
	
	public void deleteAptasHistoricas(String dataSource, Map params)throws Exception;
	
	
	public void insertBeneficiosCapacitadas(String dataSource,final List list,Map params) throws Exception;
	
	public void insertBeneficiosCapacitadas(String dataSource,Map params) throws Exception;
	
	public List getBeneficiosCapacitadas(String dataSource, Map params)throws Exception;
	
	public void updateBeneficiosCapacitadas(String dataSource, Map params);
	

	
	public void insertMantenimientoClasificacion(String dataSource,final List list,Map params) throws Exception;
	
	public void insertMantenimientoClasificacion(String dataSource,Map params) throws Exception;
	
	public void insertMantenimientoCodVenta(String dataSource,Map params) throws Exception;	
	
	public List getMantenimientoClasificacion(String dataSource, Map params)throws Exception;
	
	public void updateMantenimientoClasificacion(String dataSource, Map params)throws Exception;
	
	public void deleteMantenimientoClasificacion(String dataSource, Map params)throws Exception;

	public void insertMantenimientoClasificacionInvi(String dataSource,Map params) throws Exception;
	
	public List getMantenimientoClasificacionInvi(String dataSource, Map params)throws Exception;
	
	public void updateMantenimientoClasificacionInvi(String dataSource, Map params)throws Exception;
	
	public void deleteMantenimientoClasificacionInvi(String dataSource, Map params)throws Exception;
	
	/**
	 * Verifica si existe Mensaje de Educacion en el Sistema comercial
	 * @param dataSource
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Integer getExisteMensajeEducacion(String dataSource, Map params) throws Exception;
	
	/**
	 * Inserta Mensaje de Educacion en el Sistema comercial
	 * @param dataSource
	 * @param params
	 * @throws Exception
	 */
	public void insertMensajeEducacion(String dataSource, Map params) throws Exception;
	
	/**
	 * Actualiza Mensaje de Educacion en el Sistema comercial 
	 * @param dataSource
	 * @param params
	 * @throws Exception
	 */
	public void updateMensajeEducacion(String dataSource, Map params) throws Exception;
	
	/**
	 * Elimina Mensaje de Educacion en el Sistema comercial 
	 * @param dataSource
	 * @param params
	 * @throws Exception
	 */
	public void deleteMensajeEducacion(String dataSource, Map params) throws Exception;
	
	
	/**
	 * Verifica si existe Empresa Comercializadora en el Sistema comercial
	 * @param dataSource
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Integer getExisteEmpresaComercializadora(String dataSource, Map params) throws Exception;
	
	/**
	 * Inserta Empresa Comercializadora en el Sistema comercial
	 * @param dataSource
	 * @param params
	 * @throws Exception
	 */
	public void insertEmpresaComercializadora(String dataSource, Map params) throws Exception;
	
	/**
	 * Actualiza Empresa Comercializadora en el Sistema comercial 
	 * @param dataSource
	 * @param params
	 * @throws Exception
	 */
	public void updateEmpresaComercializadora(String dataSource, Map params) throws Exception;
	
	/**
	 * Elimina Empresa Comercializadora en el Sistema comercial 
	 * @param dataSource
	 * @param params
	 * @throws Exception
	 */
	public void deleteEmpresaComercializadora(String dataSource, Map params) throws Exception;
	
	
	public Integer getBloqueoConsultora(String dataSource, Map params) throws Exception;
	
	/**
	 * Inserta en Tabla Historica correspondiente al Proceso de Bloqueo de Consultoras
	 * @param dataSource
	 * @param list
	 * @throws Exception
	 */
	public void insertBloqueoConsultora(String dataSource, final List list, Map params) throws Exception;
	
	/**
	 * Elimina registros existentes en la Tabla Temporal de Historico de Bloqueo del Sistema Comercial
	 * @param dataSource
	 * @param params
	 * @throws Exception
	 */
	public void deleteBloqueoConsultora(String dataSource, Map params) throws Exception;
	
	/**
	 * Lista todas aquellas consultoras que sern recodificadas 
	 * @param dataSource
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getRecodificacionConsultora(String dataSource, Map params) throws Exception;
	
	
	/**
	 * Verifica si existe Cronograma de Dictado en el Sistema comercial
	 * @param dataSource
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Integer getExisteCronogramaDictado(String dataSource, Map params) throws Exception;
	
	/**
	 * Inserta Cronograma de Dictado en el Sistema comercial
	 * @param dataSource
	 * @param params
	 * @throws Exception
	 */
	public void insertCronogramaDictado(String dataSource, Map params) throws Exception;
	
	/**
	 * Actualiza Cronograma de Dictado en el Sistema comercial
	 * @param dataSource
	 * @param params
	 * @throws Exception
	 */
	public void updateCronogramaDictado(String dataSource, Map params) throws Exception;
	
	
	/**
	 * Inserta la Generacion de Planilla Programacin en el Sistema Comercial 
	 * @param dataSource
	 * @param params
	 * @param lista
	 * @throws Exception
	 */
	public void insertGenerarPlanillaProgramacion(String dataSource, Map params, final List lista) throws Exception;
	
	/**
	 * Verifica si existe Cronograma de Dictado en el Sistema comercial para el Envio
	 * @param dataSource
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Integer getEnvioExisteCronogramaDictado(String dataSource, Map params) throws Exception ;
	
	
	/**
	 * Elimina Cronograma de Dictado en el Sistema comercial a traves del proceso de Envio
	 * @param dataSource
	 * @param params
	 * @throws Exception
	 */
	public void deleteEnvioCronogramaDictado(String dataSource, Map params) throws Exception;
	
	
	/**
	 * Elimina cronograma Dictado
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	public void deleteCronogramaDictado(String dataSource, Map params) throws Exception;
	
	/**
	 * Inserta Cronograma de Dictado en el Sistema comercial a traves del proceso de Envio
	 * @param dataSource
	 * @param params
	 * @throws Exception
	 */
	public void insertEnvioCronogramaDictado(String dataSource, Map params, final List lista) throws Exception;
	
	 
	/**
	 * Inserta el envio de Status Consultora al sistema Comercial
	 * @param dataSource
	 * @param params
	 * @param lista
	 * @throws Exception
	 */
	public void insertEnvioStatusConsultora(String dataSource, Map params, final List lista) throws Exception;
	
	/**
	 * Devuelve lista de Consultoras que sern migradas desde el Sistema Comercial
	 * @param dataSource
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getMigracionComercialConsultora(String dataSource, Map params) throws Exception;
	
	/**
	 * Limpia Cronograma de Dictado en el Sistema Comercial
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	public void limpiarCronogramaDictado(String dataSource, Map params) throws Exception;
	
	/**
	 * Verifica si existe el curso de capacitacion en el Comercial
	 * @param fuenteDatos
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Integer getExisteCursoCapacitacion(String fuenteDatos, Map params) throws Exception;
	
	
	/**
	 * Devuelve Lista de Tipos de Clasificacion de Equivalencia
	 * @param fuenteDatos
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getTipoClasificacionEquivalencia(String fuenteDatos, Map params) throws Exception;
	
	
	/**
	 * Devuelve Lista de Codigos de Clasificacion de Equivalencia
	 * @param fuenteDatos
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getCodigoClasificacionEquivalencia(String fuenteDatos, Map params) throws Exception;
	
	
	/**
	 * Ejecuta el envio de Clasificacion en caso el indicador de Equivalencia de Clasificacion este activado
	 * @param fuenteDatos
	 * @param params
	 * @throws Exception
	 */
	public void executeEnvioEquivalenciaClasificacion(String fuenteDatos, Map params) throws Exception;
	
	
	/**
	 * Borra mensajes de Equivalencia en el Sistema Comercial
	 * @param fuenteDatos
	 * @param params
	 * @throws Exception
	 */
	public void executeBorrarEquivalenciaMensaje(String fuenteDatos, Map params) throws Exception;
	
	/**
	 * @param params
	 */
	public void setParams(Map params);

	public List getCodigoMensajeEquivalencia(String fuenteDatos, Map params)throws Exception;

	/**
	 * Inserta Consultoras que confirman la compra de un curso 
	 * @param fuenteDatos
	 * @param list
	 * @param numeroLote
	 * @param params
	 * @throws Exception
	 */
	public void insertConsultorasConfirmanCursoCosto(String fuenteDatos, List list, String numeroLote,Map params)throws Exception;

	/**
	 * Borra Consultoras que confirman la compra de un curso 
	 * @param fuenteDatos
	 * @param list
	 * @param numeroLote
	 * @param params
	 * @throws Exception
	 */
	public void deleteConsultorasConfirmanCursoCosto(String fuenteDatos, Map params);

	public String executeFakeProcesoCursoNoFacturados(String fuenteDatos, Map params);

	public List getPedidosComercialCUV(String fuenteDatos, Map params) throws Exception;

	public List getListRegionesACerrar(String fuenteDatos, Map params)throws Exception;

	
	/**
     * Obtiene Lista de Regiones (Directorio de Ventas)
     * @param codigoPais
     * @param params
     * @return
     * @throws Exception
     */
    public List getZONRegionesComercial(String dataSource, Map params) throws Exception;
	
	/**
	 * Obtiene Lista de Zonas (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getZONZonasComercial(String dataSource, Map params) throws Exception;

	/**
	 * Obtiene Lista de Control de Facturacion (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getZONControlFacturacionComercial(String dataSource, Map params) throws Exception;
	
	/**
	 * Obtiene Lista de Campaas (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getZONCampanhaComercial(String dataSource, Map params) throws Exception;
	
	/**
	 * Obtiene Lista de Consultoras (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getZONConsultorasComercial(String dataSource, Map params, int skip, int max) throws Exception;

	/**
	 * Inserta en tabla de Regiones FOX (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 * @param list
	 * @throws Exception
	 */
	public void insertZONRegionesComercial(String dataSource, final Map params, final List list) throws Exception;
	
	/**
	 * Inserta en tabla de Zonas FOX (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 * @param list
	 * @throws Exception
	 */
	public void insertZONZonasComercial(String dataSource, final Map params, final List list) throws Exception;
	
	/**
	 * Inserta en tabla de Historico Gerente FOX (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 * @param list
	 * @throws Exception
	 */
	public void insertZONHistorialGerentesComercial(String dataSource, final Map params, final List list) throws Exception;

	
}
