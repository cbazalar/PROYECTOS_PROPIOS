package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;
import java.util.Map;

/**
 * DAO de la Interfaz Asistente Virtual.
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
public interface InterfazAVIDAO {

	/**
	 * retorna lista pre facturacion cabecera
	 * @param params
	 * @return
	 */
	public List getInterfazAVIPrefacturacionCabecera(Map params);

	/**
	 * retorna lista pre facturacion detalle
	 * @param params
	 * @return
	 */	
	public List getInterfazAVIPrefacturacionDetalle(Map params);

	/**
	 * proceso que relaiza la carga de facturacion cabecera
	 * @param params
	 */
	public void cargarFacturacionCabecera(Map params);

	/**
	 * proceso que relaiza la carga de facturacion detalle
	 * @param params
	 */
	public void cargarFacturacionDetalle(Map params);
	
	/**
	 * retorna lista para enviar cabecera
	 * @param params
	 * @return
	 */
	public List getInterfazAVIFacturacionCabecera(Map params);

	/**
	 * retorna lista para enviar detalle
	 * @param params
	 * @return
	 */	
	public List getInterfazAVIFacturacionDetalle(Map params);

	/**
	 * retorna lista para enviar cabecera cdr
	 * @param params
	 * @return
	 */	
	public List getInterfazAVIConsultoraCDRCabecera(Map params);
	
	/**
	 * retorna lista para enviar detalle cdr
	 * @param params
	 * @return
	 */	
	public List getInterfazAVIConsultoraCDRDetalle(Map params);
	
	/**
	 * ejecuta el proceos de envio detalle cdr
	 * @param params
	 */
	public void executeInterfazAVIConsultoraCDRDetalle(Map params);

	/**
	 * ejecuta el proceso de envio de puntaje
	 * @param params
	 */
	public void executeInterfazAVIConsultoraPuntaje(Map params);
	
	/**
	 * retorn lista de territotio
	 * @param params
	 * @return
	 */
	public List getInterfazAVITerritorio(Map params);

	/**
	 * Se ejecuta la interfaz de Carga de Saldo de Consultoras (AVI)
	 * 
	 * @param params
	 */
	public void executeInterfazAVISaldosConsultora(Map params);
	/**
	 * Se ejecuta la interfaz de Consultoras Bloqueadas
	 * @param params
	 */
	public void executeInterfazAVIConsultorasBloqueada(Map params);
	/**
	 * Se ejecuta la interfaz de Tipos de Bloqueo
	 * @param params
	 */
	public void executeInterfazAVITiposBloqueo(Map params);

	/**
	 * Proceso que hace el envio de secciones
	 * @param params
	 */
	public void executeInterfazAVIEnvioSecciones(Map params);

	/**
	 * Proceso que realiza el envio de las metas - logros
	 * @param params
	 */
	public void executeInterfazAVIEnvioMetasLogro(Map params);

	/**
	 * Proceso que realiza el envio de las Ventas -Ganacias -Asistencia
	 * @param params
	 */
	public void executeInterfazAVIEnvioVentaGanancia(Map params);

	/**
	 * Proceso que realiza el envio de las Ganacia por Marca
	 * @param params
	 */
	public void executeInterfazAVIEnvioVentaMarca(Map params);

	/**
	 * Proceso que realiza el enviod e indicadores GGZZ
	 * @param params
	 */
	public void executeInterfazAVIEnviarIndicadoresGGZZ(Map params);

	/**
	 * Proceso que realiza el envio  de informacion Adicional
	 * @param params
	 */
	public void executeInterfazAVIEnviarInformacionAdicional(Map params);

	/**
	 * Inserta logros AVI
	 * @param params
	 */
	public void insertInterfazAVIRecepcionarLogros(Map params);
	
	/**
	 * Realiza la validacion por tipo de recpcion 
	 * @param params
	 */
	public void executeValidacionRecepcionAsistenteVirtual(Map params);

	/**
	 * Actualiza Interfaz Recepciona Logros
	 * @param row
	 */
	public void updateInterfazAVIRecepcionarLogros(Map params);

	/**
	 * Proceso que realiza el envio de tipos de logros
	 * @param params
	 */
	public void executeInterfazAVIEnviarTiposLogros(Map params);

	/* INI SA PER-SiCC-2012-0344 */
	/**
	 * Obtiene la lista de Tipos de Visita
	 * 
	 * @return
	 */
	public List getListTiposVisita();
	/* FIN SA PER-SiCC-2012-0344 */
	
	/**
	 * Proceso que ejecuta Interfaz AVI-1
	 * @param params
	 */
	public void executeInterfazAVIPrefacturacionCabecera(Map params);
	
	/**
	 * Proceso que ejecuta Interfaz AVI-2
	 * @param params
	 */
	public void executeInterfazAVIPrefacturacionDetalle(Map params);
	
	/**
	 * Proceso que ejecuta Interfaz AVI-5
	 * @param params
	 */
	public void executeInterfazAVIConsultoraCDRCabecera(Map params);
	
	
	/**
	 * Proceso que ejecuta Interfaz AVI-8
	 * @param params
	 */
	public void executeInterfazAVIEnviarTerritorio(Map params);
	
}