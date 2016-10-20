package biz.belcorp.ssicc.dao.edu;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDUComercialDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:dhinostroza@belcorp.biz">David Hinostroza Vintes</a>
 */

public interface ProcesoEDUComercialDAO extends DAO{
	
	/**
	 *  Inserta pedidos desde el Comercial hacia Temporal Pedidos
	 *	@param  
	 */
	public void insertTemporalPedidos(List list);
	
	
	/**
	 * Inserta pedidos desde el Comercial hacia Temporal Pedidos
	 * @param list
	 */
	public void insertTemporalPedidosNombreCompleto(final List list);

	
	/**
	 *  Inserta pedidos Facturados desde el Comercial hacia Temporal Pedidos
	 *	@param  
	 */
	public void insertTemporalPedidosFacturados(List list);

	/**
	 *  Inserta cursos Facturados desde el Comercial hacia Temporal Pedidos
	 *	@param  
	 */
	public void insertTemporalPedidosCursosFacturados(List list, String codigoPais, String codigoEmpresa);
	
	/**
	 *  Carga pedidos desde Temporal hacia Historico
	 *	@param  
	 */
	public void updateHistoricoPedidos(String codigoPais, Map params);

	/**
	 *  Actualiza los cursos comprados
	 *	@param  
	 */
	public void updateHistoricoPedidosFacturados(String codigoPais, Map params);

	/**
	 *  Carga cursos facturados desde Temporal hacia Historico
	 *	@param  
	 */
	public void updatePedidosCursosFacturados(String codigoPais, Map params);

	/**
	 *  Carga cursos No facturados desde Temporal hacia Historico
	 *	@param  
	 */
	public void updatePedidosCursosNoFacturados(String codigoPais, Map params);
	
	public void updateMaestroClientes(String codigoPais, Map params);	
	
	/**
	 *  Inserta consultoras nuevas desde el Comercial hacia Maestro Clientes
	 *	@param  
	 */
	public void insertMaestroClientes(List list, Map params)throws Exception;

	/**
	 *  Inserta Regiones desde el Comercial hacia Temporal Regiones
	 *	@param  
	 */
	public void insertTemporalRegiones(List list, Map params);

	/**
	 *  Inserta Zonas desde el Comercial hacia Temporal Zonas
	 *	@param  
	 */
	public void insertTemporalZonas(List list, Map params);

	/**
	 *  Inserta Control Facturacion desde el Comercial hacia Temporal Control
	 *	@param  
	 */
	public void insertTemporalControlFacturacion(List list, Map params);

	/**
	 *  Carga regiones desde Temporal hacia Maestro
	 *	@param  
	 */
	public void updateMaestroRegiones(String codigoPais, Map params);

	/**
	 *  Carga zonas desde Temporal hacia Maestro
	 *	@param  
	 */
	public void updateMaestroZonas(String codigoPais, Map params);

	/**
	 *  Carga controlfacturacion desde Temporal hacia Maestro
	 *	@param  
	 */
	public void updateControlFacturacion(String codigoPais, Map params);
	
	public void deleteTemporalZonas();

	public void deleteTemporalRegiones();

	public void deleteTemporalControlFacturacion();
	
	public void deleteTemporalPedidos(Map params);
	
	public void deleteTemporalPedidosCursos();
		
	public void deleteTemporalConsultorasAptasporProgramar();
	
	/**
	 *  Actualiza el Envio de Aptas para el Historico de Aptas.
	 *	@param  
	 */
	public void updateEnvioHistoricoAptas(Map params);

	/**
	 *  Actualiza el Envio de Aptas Curso con Costo para el Historico de Aptas.
	 *	@param  
	 */
	public void updateEnvioHistoricoAptasCosto(Map params);
	
	/**
	 *  Obtiene las Consultoras Aptas
	 *	@param  
	 */
	public List getConsultorasAptas(Map params);

	/**
	 *  Obtiene las Consultoras Aptas de Cursos con Costo
	 *	@param  
	 */
	public List getConsultorasAptasCosto(Map params);
	
	/**
	 *  Obtiene las Consultoras Aptas por Programar
	 *	@param  
	 */
	public List getConsultorasAptasporProgramar(Map params);
	


	/**
	 *  Inserta las Consultoras Aptas por Programar en la tabla Temporal Planificacion de Programacin de Cursos.
	 *	@param  
	 */
	public void insertTemporalConsultorasAptasporProgramar(List list, Map params);
	
	
	public void insertMaestroProgramacionCursos(Map params) throws Exception;
	
	public void updateEnvioHistoricoAptasProgramadas(Map params);
	
	public void updateNumeroLoteSgte(Map params);
	
	public String getNumeroLote(Map params);

	public List getAptasHistoricasFacturacion(Map params) throws Exception;
	
	public List getResultBeneficiosCapacitadas(Map params) throws Exception;
	
	public void updateEnvioBeneficiosCapacitadas(Map params) throws Exception;
	
	public void updateCargarBeneficiosCapacitadas(String codigoPais, Map params);
	
	
	/**
	 * Elimina registros existentes en la Tabla Temporal de Historico de Bloqueo 
	 * de Consultoras 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public void deleteHistoricoBloqueoConsultoraTemporal(Map params) throws Exception;
	
	/**
	 * Obtiene Lista de Historico de Bloqueo de Consultora que será enviada al Sistema Comercial
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getHistoricoBloqueoConsultoraTemporal(Map params) throws Exception;
	
	
	/**
	 * Obtiene Lista de Generacin de Planilla Programación que será enviada al Sistema Comercial
	 * @param params
	 * @return
	 */
	public List getGenerarPlanillaProgramacion(Map params);
	
	/**
	 * Inserta Lista de Invitaciones a Tabla Temporal
	 * @param codigoPais
	 * @param lista
	 * @param params
	 * @throws Exception
	 */
	public void executeInsertaEquivalenciaClasificacion(final List lista)throws Exception;
	
	
	/**
	 * Genera Aptas para Curso Mixto
	 * @param params
	 * @throws Exception
	 */
	public void executeProcesoConsultorasAptasMixtoBloqueo(Map params) throws Exception;
	
	
	/**
	 * Devuelve lista de consultoras Aptas por Curso Mixto
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getConsultorasAptasMixtoBloqueo(Map params) throws Exception;

	
	
	/**
	 * Inserta en tabla temporal la informacion de consultoras desde el Sistema Comercial para el proceso
	 * de Migracion de data
	 * @param codigoPais
	 * @param params
	 * @param lista
	 * @throws Exception
	 */
	public void insertMigracionComercialConsultoraTemporal(String codigoPais, List lista) throws Exception;


	public String[] getListaCursosByTipoCurso(Map params)throws Exception;


	public void executeProcesoConsultorasCompraCursoCosto(Map params)throws Exception;


	public List getConsultorasConfirmCursoCosto(Map params);


	public List getPedidosCursosNoFacturadosMixtoYCosto(String codigoPais, Map params);


	public List getPedidosCursosNoFacturadosMixto(String codigoPais, Map params);


	public void deleteTemporalPedidosCUV(Map params) throws Exception;


	public void insertTemporalPedidosNombreCompletoCUV(List list) throws Exception;


	public void insertTemporalPedidosCUV(List list)throws Exception;


	public Integer validaExecuteProcesoCalificacion(String codigoPais, Map params)throws Exception;


	public void deleteRegionesACerrar(String codigoPais, Map params);


	public void insertarRegionesACerrar(List list)throws Exception;


	/**
	 * Devuelve 1 si la campanha es valida 0: si es invalida
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Integer getValidaCampanha(Map params)throws Exception;


	/**
	 *  Ejecuta el proceoso de actualizacion  del control de facturacion  
	 * @param codigoPais
	 * @param params
	 */
	public void updateToControlFacturacion(String codigoPais, Map params);

	/**
	 * Obtiene Lista de Historico de Bloqueo de Consultora que será enviada al Sistema Comercial
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getHistoricoBloqueoConsultora(Map params);
}
