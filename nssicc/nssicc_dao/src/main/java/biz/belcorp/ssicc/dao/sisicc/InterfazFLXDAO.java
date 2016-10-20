package biz.belcorp.ssicc.dao.sisicc;

import java.util.Map;

/**
 * DAO de la Interfaz Flexipago
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */
public interface InterfazFLXDAO {

	/**
	 * Inserta consultoras habiles FLX
	 * @param params
	 */
	public void executeInterfazAVIRecepcionarConsultorasHabiles(Map params);	
	
	/**
	 * Inserta consultoras comunicacion FLX
	 * @param params
	 */
	public void executeInterfazAVIRecepcionarConsultorasComunicacion(Map params);
	
	/**
	 * Envia los resultados del progrma y actualiza lo enviado
	 * @param params
	 */
	public void executeEnviarResultadoProgramas(Map params);

	/**
	 * Envia relacion consultoras objetdas para GZ y actualiza lo enviado
	 * @param params
	 */
	public void executeEnviarConsultorasObjetadas(Map params);
	
	/**
	 * Envia relacion consultoras habiles 
	 * @param params
	 */
	public void executeEnviarInformacionConsultorasHabiles(Map params);
	
	/**
	 * Ejecuta el proceso de procesar flexipago GP3 
	 * @param params
	 */
	void executeProcesarFlexipagoGP3(Map params);
	

	/**
	 * Recepciona el archivo de consultoras rechazadas desde WEB
	 * @param map
	 */
	public void executeRecepcionarConsultorasRechazadasWEB(Map map);

	/**
	 * Recepciona el archivo de consultoras recomendadas desde WEB
	 * @param map
	 */
	public void executeRecepcionarConsultorasRecomendadasWEB(Map map);

	/**
	 * Genera la informacin de las comslutoras aprbadas desde WEB y las que se informan
	 * @param params
	 */
	public void executeEnvioInformacionProcesosComerciales(Map params);

	/**
	 * REcepciona las variables del modelo que son enviadas por DataMart
	 * @param map
	 */
	public void executeRecepcionarVariablesModelo(Map params);

	/**
	 * Calcula las variables del modelo de flexipago
	 * @param params
	 */
	public void executeCalculoVariablesModelo(Map params);

	/**
	 * Enva los resultados del programa a Datamart
	 * @param params
	 */
	public void executeEnviarResultadosPrograma(Map params);

	/**
	 * Calcula las variables de cuenta corriente para el modelo flexipago
	 * @param params
	 */
	public void executeCalculoVariablesCuentaCorriente(Map params);

	/**
	 * Enva las variables calculadas en cuentas corrientes a Datamart
	 * @param params
	 */
	public void executeEnviarVariablesCuentaCorriente(Map params);

	/**
	 * Recepciona las variables del modelo que son enviadas por DataMart
	 * @param params
	 */
	public void executeRecepcionarVariablesVenta(Map params);
}