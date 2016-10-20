package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;
import java.util.Map;

/**
 * DAO de la Interfaz Ejecutivo Virtual.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
public interface InterfazEVIDAO {

	public void updateInterfazEVIRecepcionarOCSaldoDeudor(Map params);

	public void actualizaOCSConsultorasInactivas(Map params);	

	public void insertInterfazEVIRecepcionarOC(Map params);

	public List getInterfazEVIRecepcionarPrimerosPedidos(Map params);

	public void insertInterfazEVIRecepcionarPrimerosPedidos(Map params);

	public void updateInterfazEVIRecepcionarPrimerosPedidos(Map params);

	public void cargarResumenesPrefacturacion();

	/**
	 * Realiza la llamada al Store Procedure para la carga de la informacion
	 * para Facturacion-Cabecera
	 * 
	 * @param params
	 *            parametros del store procedure
	 */
	public void cargarFacturacionCabecera(Map params);

	/**
	 * Realiza la llamada al Store Procedure para la carga de la informacion
	 * para Facturacion-Detalle
	 * 
	 * @param params
	 *            parametros del store procedure
	 */
	public void cargarFacturacionDetalle(Map params);

	public List getInterfazEVIPrefacturacionCabecera(Map params);

	public List getInterfazEVIPrefacturacionDetalle(Map params);

	public List getInterfazEVIFacturacionCabecera(Map params);

	public List getInterfazEVIFacturacionDetalle(Map params);

	public List getInterfazEVIConsultoraCDR(Map params);

	public void executeInterfazEVIConsultoraPuntaje(Map params);
}
