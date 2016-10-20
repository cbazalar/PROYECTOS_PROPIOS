package biz.belcorp.ssicc.service.edu.gen;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author David Hinostroza Vintes
 * 
 */

public interface GenericoEDUFacadeService {

	public List getPedidosComercial(String codigoPais, Map params)throws Exception;
	
	public List getPedidosComercialFacturados(String codigoPais, Map params)throws Exception;
	
	public List getPedidosCursosFacturados(String codigoPais, Map params)throws Exception;	
	
	public List getPedidosCursosNoFacturados(String codigoPais, Map params)throws Exception;	

	public List getConsultorasNuevas(String codigoPais, Map params)throws Exception;

	public List getDetalleProducto(String codigoPais, Map params)throws Exception;
	
	public List getMatrizProducto(String codigoPais, Map params)throws Exception;	

	public List getRegionesComercial(String codigoPais, Map params)throws Exception;
	
	public List getZonasComercial(String codigoPais, Map params)throws Exception;

	public List getControlFacturacionComercial(String codigoPais, Map params)throws Exception;
	
	public void insertConsultorasAptas(String codigoPais, final List list, String numeroLote,Map params) throws Exception;	
	
	public void insertConsultorasAptasCosto(String codigoPais, final List list, String numeroLote,Map params) throws Exception;	
	
	public void insertConsultorasAptasporProgramar(String codigoPais, final List list,String numeroLote, String periodo,Map params) throws Exception ; 
	
	public List getConsultorasAptasporProgramar(String codigoPais, Map params)throws Exception;
	
	public void insertParametrosCursoCapacitacion(String codigoPais, Map params)throws Exception;
	
	public void insertMantenimientoCodVenta(String codigoPais, Map params) throws Exception;	

	public void updateParametrosCursoCapacitacion(String codigoPais,Map params)throws Exception;
	
	public void deleteConsultorasAptas(String codigoPais, Map params) throws Exception;
	
	public void deleteConsultorasAptasCosto(String codigoPais, Map params);	

	public void deleteConsultorasAptasProgramar(String codigoPais, Map params);
	
	public void insertAptasHistoricas(String codigoPais,final List list,Map params) throws Exception;
	
	public void deleteAptasHistoricas(String codigoPais, Map params)throws Exception;
	
	public void deleteBeneficiosCapacitadas(String codigoPais, Map params)throws Exception;	

	public List getBeneficiosCapacitadas(String codigoPais, Map params)throws Exception;
	
	public void insertBeneficiosCapacitadas(String codigoPais,final List list,Map params) throws Exception;
	
	public void updateBeneficiosCapacitadas(String codigoPais, Map params);
	
	
	public void insertMantenimientoClasificacion(String codigoPais,final List list,Map params) throws Exception;
	public List getMantenimientoClasificacion(String codigoPais, Map params)throws Exception;
	public void updateMantenimientoClasificacion(String codigoPais, Map params)throws Exception;

	
	public void insertMantenimientoClasificacion(String codigoPais, Map params)throws Exception;
	public void insertBeneficiosCapacitadas(String codigoPais, Map params) throws Exception;
	
	public void deleteMantenimientoClasificacion(String codigoPais, Map params)throws Exception;

	public List getMantenimientoClasificacionInvi(String codigoPais, Map params)throws Exception;
	
	public void updateMantenimientoClasificacionInvi(String codigoPais, Map params)throws Exception;
	
	public void insertMantenimientoClasificacionInvi(String codigoPais, Map params)throws Exception;
	
	public void deleteMantenimientoClasificacionInvi(String codigoPais, Map params)throws Exception;
	
	
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
	
	/**
	 * Inserta en Tabla Historica correspondiente al Proceso de Bloqueo de Consultoras
	 * @param dataSource
	 * @param list
	 * @throws Exception
	 */
	public void insertBloqueoConsultora(String dataSource, final List list,Map params) throws Exception;
	
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
	public void insertGenerarPlanillaProgramacion(String dataSource, Map params, List lista) throws Exception;
	
	
	/**
	 * Elimina cronograma Dictado
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	public void deleteCronogramaDictado(String codigoPais, Map params) throws Exception;
	
	/**
	 * Inserta el envio de Cronograma de Dictado al sistema Comercial
	 * @param codigoPais
	 * @param params
	 * @param lista
	 * @throws Exception
	 */
	public void insertEnvioCronogramaDictado(String codigoPais, Map params, List lista) throws Exception;
	
	/**
	 * Limpia Cronograma de Dictado en el Sistema Comercial
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	public void limpiarCronogramaDictado(String codigoPais, Map params) throws Exception;
	
	/**
	 * Inserta el envio de Status Consultora al sistema Comercial
	 * @param codigoPais
	 * @param params
	 * @param lista
	 * @throws Exception
	 */
	public void insertEnvioStatusConsultora(String codigoPais, Map params, List lista) throws Exception;
	
	/**
	 * Devuelve lista de Consultoras que sern migradas desde el Sistema Comercial
	 * @param dataSource
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getMigracionComercialConsultora(String dataSource, Map params) throws Exception;

	public Integer getExisteCursoCapacitacion(String codigoPais, Map params) throws Exception ;
	
	
	/**
	 *  Ejecuta proceso de Envio de Equivalencia de Clasificacion 
	 *	@param  
	 */
	public void executeEnvioEquivalenciaClasificacion(String codigoPais, Map params) throws Exception;
	
	
	/**
	 * Proceso que efectua la insercion de Consultoras aptas por Curso Mixto en el Sistema comercial
	 * @param codigoPais
	 * @param list
	 * @param numeroLote
	 * @param params
	 * @throws Exception
	 */
	public void insertConsultorasAptasMixtoBloqueo(String codigoPais, final List list, String numeroLote,Map params) throws Exception;
	
	
	/**
	 * Devuelve Lista de Tipos de Clasificacion de Equivalencia
	 * @param codigoPais
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getTipoClasificacionEquivalencia(String codigoPais, Map params) throws Exception;
	
	
	/**
	 * Devuelve Lista de Codigos de Clasificacion de Equivalencia
	 * @param codigoPais
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getCodigoClasificacionEquivalencia(String codigoPais, Map params) throws Exception;
	
	
	/**
	 * Borra mensajes de Equivalencia en el sistema Comercial
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	public void executeBorrarEquivalenciaMensaje(String codigoPais, Map params) throws Exception; 
	
	public List getCodigoMensajeEquivalencia(String codigoPais,Map params) throws Exception; 
	
	public void insertConsultorasConfirmanCursoCosto(String codigoPais, List list, String numeroLote, Map params)throws Exception;

	public void deleteConsultorasConfirmanCursoCosto(String codigoPais, Map params);
	
	public String executeFakeProcesoCursoNoFacturados(String codigoPais, Map params);
	
	public List getPedidosComercialCUV(String codigoPais, Map params)throws Exception;
	
	public List getListRegionesACerrar(String codigoPais,Map params)throws Exception;
	
	

    /**
     * Obtiene Lista de Regiones (Directorio de Ventas)
     * @param codigoPais
     * @param params
     * @return
     * @throws Exception
     */
    public List getZONRegionesComercial(String codigoPais, Map params) throws Exception;
	
	/**
	 * Obtiene Lista de Zonas (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getZONZonasComercial(String codigoPais, Map params) throws Exception;

	/**
	 * Obtiene Lista de Control de Facturacion (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getZONControlFacturacionComercial(String codigoPais, Map params) throws Exception;
	
	/**
	 * Obtiene Lista de Campaas (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getZONCampanhaComercial(String codigoPais, Map params) throws Exception;
	
	/**
	 * Obtiene Lista de Consultoras (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getZONConsultorasComercial(String codigoPais, Map params, int skip, int max) throws Exception;
	
	
	/**
	 * Inserta en tabla de Regiones FOX (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 * @param list
	 * @throws Exception
	 */
	public void insertZONRegionesComercial(String codigoPais, final Map params, final List list) throws Exception;
	
	/**
	 * Inserta en tabla de Zonas FOX (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 * @param list
	 * @throws Exception
	 */
	public void insertZONZonasComercial(String codigoPais, final Map params, final List list) throws Exception;
	
	/**
	 * Inserta en tabla de Historico Gerente FOX (Directorio de Ventas)
	 * @param codigoPais
	 * @param params
	 * @param list
	 * @throws Exception
	 */
	public void insertZONHistorialGerentesComercial(String codigoPais, final Map params, final List list) throws Exception;
}
