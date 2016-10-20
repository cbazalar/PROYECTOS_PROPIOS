package biz.belcorp.ssicc.service.sisicc.framework;

import java.util.Map;

import biz.belcorp.ssicc.dao.scsicc.ProcesoBatchDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOBloqueoControlDAO;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;

/**
 * Service de ejecución de las Interfaces SiSiCC, este Service funciona como
 * interface entre la capa web (invocado por el
 * <code>BaseInterfazAbstractAction</code>) y las implementaciones de los
 * services específicos de cada interfaz.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 * DESFASADO
 */
public interface SSiCC_Desfasado_InterfazExecutionService {

	/**
	 * Ejecuta la Interfaz SiCC.
	 * 
	 * @param params
	 *            parametros de la interfaz
	 * @return InterfazExecutionResult con los resultados de la ejecucion.
	 */
	public SSiCC_Desfasado_InterfazExecutionResult executeInterfaz(Map params) throws Exception;
	
	/**
	 * @return
	 */
	public ProcesoSTOService getStoService();
	
	/**
	 * @return the interfazService
	 */
	public InterfazService getInterfazService();
	
	/**
	 * @return the historicoService
	 */
	public HistoricoService getHistoricoService() ;

	/**
	 * @return the procesoBatchDAO
	 */
	public ProcesoBatchDAO getProcesoBatchDAO() ;
	
	/**
	 * Obtiene la implementacion especifica de la Interfaz paquete  del Map de
	 * implementaciones a partir del codigo.
	 * 
	 * @param codigo
	 * @return
	 */
	public SSiCC_Desfasado_BasePaqueteInterfazService getPaqueteInterfazImplementation(String codigo);
	
	public MantenimientoSTOBloqueoControlDAO getMantenimientoSTOBloqueoControlDAO();
	
	public Map getInterfazImplementations();
	
}