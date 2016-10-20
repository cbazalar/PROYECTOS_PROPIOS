package biz.belcorp.ssicc.service.edu;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.edu.gen.model.MatrizProducto;
import biz.belcorp.ssicc.dao.edu.gen.model.ProductoDetalle;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDUComercialService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:msilva@belcorp.biz">Marco Silva Moreno</a>
 */

public interface ProcesoEDUComercialService {
	
	/**
	 *  Recepciona pedidos desde el Comercial
	 *	@param  
	 */
	public void executeProcesoEDURecepcionarPedidos(String codigoPais, Map params)throws Exception;


	/**
	 *  Recepciona pedidos Facturados desde el Comercial
	 *	@param  
	 */
	public void executeProcesoEDURecepcionarPedidosFacturados(String codigoPais, Map params)throws Exception;

	/**
	 *  Recepciona cursos Facturados desde el Comercial
	 *	@param  
	 */
	//public void executeProcesoEDURecepcionarCursosFacturados(String codigoPais, Map params)throws Exception;

	/**
	 *  Recepciona cursos No Facturados desde el Comercial al Cierre de campanha
	 *	@param  
	 */
	public void executeProcesoEDURecepcionarCursosNoFacturados(String codigoPais, Map params)throws Exception;
	
	/**
	 *  Recepciona pedidos de tabla Temporal al Historico 
	 *	@param  
	 */
	public void executeProcesoEDUCargarHistoricoPedidos(String codigoPais, Map params)throws Exception;
	
	/**
	 *  Recepciona consultoras nuevas del comercial al Maestro de Clientes 
	 *	@param  
	 */
	public void executeProcesoEDUCargarConsultorasNuevas(String codigoPais, Map params) ;

	/**
	 *  Obtiene Producto desde el comercial 
	 *	@param  codigoProducto (Map)
	 */
	public ProductoDetalle getDetalleProducto(String codigoPais, Map params)throws Exception;
	
	/**
	 *  Actualiza regiones del comercial al Maestro Regiones 
	 *	@param  
	 */
	public void executeProcesoEDUCargarRegiones(String codigoPais, Map params)throws Exception;
	
	/**
	 *  Actualiza zonas del comercial al Maestro Zonas
	 *	@param  
	 */
	public void executeProcesoEDUCargarZonas(String codigoPais, Map params)throws Exception;
	
	/**
	 *  Actualiza Archivo Control del comercial al de Educacion 
	 *	@param  
	 */
	public void executeProcesoEDUCargarControlFacturacion(String codigoPais, Map params)throws Exception;
	
	/**
	 *  Envia  Aptas al Comercial 
	 *	@param  
	 */
	public void executeProcesoEDUEnviarAptas(String codigoPais, Map params) throws Exception;

	/**
	 *  Envia Enviar Aptas Costo Comercial 
	 *	@param  
	 */
	public void executeProcesoEDUEnviarAptasCosto(String codigoPais, Map params) throws Exception;
	
	public MatrizProducto getMatrizProducto(String codigoPais, Map params)throws Exception;	
	
	
	public void executeProcesoEDUEnviarAptasPorProgramar(String codigoPais, Map params) throws Exception;
	
	public void executeProcesoEDUCargarAptasPorProgramar(String codigoPais, Map params)throws Exception;
	
	public void executeInsertParametrosCursoCapacitacion(String codigoPais, Map params)throws Exception;

	public void executeUpdateParametrosCursoCapacitacion(String codigoPais, Map params)throws Exception;
	
	public void executeProcesoEDURenEnviarAptas(String codigoPais, Map params) throws Exception;
	
	public void executeProcesoEDURenEnviarAptasPorProgramar(String codigoPais, Map params) throws Exception;
	
	public void executeProcesoEDUProcesoCargaRecepcionMaestros(String codigoPais, Map params) throws Exception;
	
	public void executeProcesoEDUEnviarHistoricoCapacitadas(String codigoPais, Map params) throws Exception;
	
	public void executeProcesoEDUEnviarBeneficiosCapacitadas(String codigoPais, Map params) throws Exception;
	
	public void executeProcesoEDUCargarBeneficiosCapacitadas(String codigoPais, Map params) throws Exception;
	
	public void executeInsertParametrosClasificacion(String codigoPais,Map params) throws Exception;

	public void executeUpdateParametrosClasificacion(String codigoPais,Map params) throws Exception;
		
	public void executeInsertMantenimientoClasificacion(String codigoPais,Map params) throws Exception;

	public void executeUpdateMantenimientoClasificacion(String codigoPais,Map params) throws Exception;
	
	public void executeInsertUpdateMantenimientoClasificacionValida(String codigoPais,Map params) throws Exception;
	
	public void insertMantenimientoCodVenta(String codigoPais,Map params) throws Exception;	
    
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
	 * Elimina registros existentes en la Tabla Temporal de Historico de Bloqueo 
	 * de Consultoras 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public void deleteHistoricoBloqueoConsultoraTemporal(Map params) throws Exception;
	
	/**
	 * Elimina registros existentes en la Tabla Temporal de Historico de Bloqueo del Sistema Comercial
	 * @param dataSource
	 * @param params
	 * @throws Exception
	 */
	public void deleteHistoricoBloqueoConsultoraTemporalComercial(String dataSource, Map params) throws Exception;
	
	/**
	 * Obtiene Lista de Historico de Bloqueo de Consultora que ser enviada al Sistema Comercial
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getHistoricoBloqueoConsultoraTemporal(Map params) throws Exception;
	
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
	 * Genera Lista de Planilla Programacion para enviarlo al Sistema Comercial
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	public void executeProcesoEDUEnviarGenerarPlanillaProgramacion(String codigoPais, Map params)throws Exception ;
	
	
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
	 * Devuelve lista de Consultoras que sern migradas desde el Sistema Comercial
	 * @param dataSource
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getMigracionComercialConsultora(String codigoPais, Map params) throws Exception;
	
	/**
	 * Inserta en tabla temporal la informacion de consultoras desde el Sistema Comercial para el proceso
	 * de Migracion de data
	 * @param codigoPais
	 * @param params
	 * @param lista
	 * @throws Exception
	 */
	public void insertMigracionComercialConsultoraTemporal(String codigoPais, List lista) throws Exception;

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
	
	
	public Integer getExisteCursoCapacitacion(String codigoPais,Map params) throws Exception;
	
	/**
	 *  Ejecuta proceso de Envio de Equivalencia de Clasificacion 
	 *	@param  
	 */
	public void executeEnvioEquivalenciaClasificacion(String codigoPais, Map params)throws Exception;
	
	/**
	 * Enviar Aptas por Curso Mixto
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	public void executeProcesoEDUEnviarAptasMixtoBloqueo(String codigoPais, Map params) throws Exception;
	
	
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
	 * Borra mensajes de equivalencia en el Sistema Comercial
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	public void executeBorrarEquivalenciaMensaje(String codigoPais, Map params) throws Exception;
	
	
	public List getCodigoMensajeEquivalencia(String codigoPais,Map params) throws Exception;
	
	
	public void executeProcesoEDUCargarPedidosCUV(String codigoPais, Map params)throws Exception;
	
	public void executeRegionesAcerrar(String codigoPais, Map params)throws Exception;
	
	/**
	 * Consigue las campanhas Activas
	 * */
	public List getListCampanhasActivas(Map criteria)throws Exception ;
	
}
