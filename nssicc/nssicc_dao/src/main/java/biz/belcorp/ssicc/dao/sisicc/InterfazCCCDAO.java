package biz.belcorp.ssicc.dao.sisicc;

import java.util.Map;

/**
 * DAO de la Interfaz de Cuenta Corriente
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio</a>
 */
public interface InterfazCCCDAO {

	
	/**
	 * Procedimiento que borra los registros de la tabla temporal,
	 * @param row
	 */
	public void deleteInterfazCCCRecepcionarMovimientosBancarios(Map row);
	
	/**
	 * Procedimiento que inserta un registro de pago bancario en una tabla temporal,
	 * @param map
	 */
	public void insertInterfazCCCRecepcionarMovimientosBancarios(Map map);
	
	/**
	 * Procedimiento que carga en los pagos bancarios desde la tabla temporal hacia la tabla de movimientos bancarios.
	 * @param map
	 */	
	public void executeInterfazCCCRecepcionarMovimientosBancarios(Map map);
	
	/**
	 * Procedimiento que ejecuta la interfaz de envio de los saldos de las consultoras
	 * @param map
	 */
	public void executeInterfazCCCEnviarSaldosConsultoras(Map map);
	
	/**
	 * Procedimiento que ejecuta la interfaz de envio de los saldos de las consultoras castigadas
	 * @param map
	 */
	public void executeInterfazCCCEnviarSaldosCastigadas(Map map);
	
	
	/**
	 * Procedimiento que ejecuta la interfaz de envio de los saldos del maestro consultoras
	 * @param map
	 */
	public void executeInterfazCCCEnviarMaestroConsultoras(Map map);
	
	/**
	 * Procedimiento que ejecuta la interfaz de envio de los saldos de las novedades de consultoras
	 * @param map
	 */
	public void executeInterfazCCCEnviarNovedadesConsultoras(Map map);
	
	
	/**
	 * Procedimiento que ejecuta la interfaz de envio de SAPFI Cobranzas
	 * @param map
	 */
	public void executeInterfazCCCEnviarSAPFICobranzas(Map map);

	
	/**
	 *  Obtiene en el numero de lote
	 * @param criteria
	 */
	public void getNumeroLote(Map criteria);

	public String getValParaCCCParamGener(final String codigoParametro);

	public Integer getCantidadRegistroMovimBancaRuteo();

	public void insertCCCMovimBancaRuteo(final String numeroLote);

	public void executeCCCRegistrarLoteBancario(Map params);
	
	/**
	 * Ejecuta el prceso de generacion de cargos para seguro familia 
	 * @param params
	 */
	void executeGenerarCargoFamiliaSeguraCCPPGP3(Map params);
	
	/**
	 * Ejecuta el prceso de generacion de cargos de gastos administrativos 
	 * @param params
	 */
	void executeGenerarCargoGastoAdministrativoCCPPGP3(Map params);
	
	/**
	 * Ejecuta el proceso para Cargas deudas web
	 * @param params
	 */
	void executeInterfazCCCCargasDeudasWeb(Map params);
	
	/**
	 * Ejecuta el proceso de generacion de intereses de mora
	 * @param params
	 */
	public void executeGenerarInteresesMoraCCPPGP3(Map params);
	
}
