package biz.belcorp.ssicc.dao.sisicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * <a href="InterfazSATDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@csigcomt.com">Dennys Oliva Iriarte</a>
 */
public interface InterfazSATDAO extends DAO {
	
	/**
	 * @param params
	 * Ejecuta la interfaz Recepcionar Centros de Acopio
	 */	
	public void executeInterfazSATRecepcionarCentrosAcopio(Map params);
	
	/**
	 * @param params
	 * Ejecuta la interfaz Recepcionar Cobertura x Codigo Territorial
	 */	
	public void executeInterfazSATRecepcionarCoberturaCodigoTerritorial(Map params); 
	
	/**
	 * Elimina la data de la tabla donde se carga la interfaz
	 */
	public void deleteInterfazSATRecepcionarCentrosAcopio();
	
	/**
	 * Elimina la data de la tabla donde se carga la interfaz
	 */
	public void deleteInterfazSATRecepcionarCoberturaCodigoTerritorial();
	
	/**
	 * Inserta la data en la tabla correspondiente
	 * @param params
	 * @param usuario
	 */
	public void insertCentrosAcopio(Map params, Usuario usuario);
	
	/**
	 * Inserta la data en la tabla correspondiente
	 * @param params
	 * @param usuario
	 */
	public void insertCoberturaCodigoTerritorial(Map params, Usuario usuario);
	
	/**
	 * Ejecuta la interfaz de Division de Armado por CDP
	 * @param params
	 */
	public void executeInterfazSATRecepcionarDivisionArmadoCDP(Map params);
	
	public void executeInterfazSATRecepcionarOrdenImpresionAPESAT(Map params);
	
	public void executeInterfazSATRecepcionarParametrizacionCalculoFechaEntregaExacta(Map params);
	
	public void executeInterfazSATRecepcionarExcepcionesFechaEntregaExacta(Map params);
	
	public void executeInterfazSATRecepcionarSeguimientoPedido(Map params);
	
	public void executeInterfazSATRecepcionarImpresionBoletasEntrega(Map params);
	
	public int getInterfazSATRecepcionarOrdenImpresionAPESAT(Map params);
	public void insertInterfazSATRecepcionarOrdenImpresionAPESATHistorico(Map params);
	public void deleteInterfazSATRecepcionarOrdenImpresionAPESAT(Map params);
	public void insertInterfazSATRecepcionarOrdenImpresionAPESAT(Map params);
	
	public int getInterfazSATRecepcionarParametrizacionCalculoFechaEntregaExacta(Map params);
	public void insertInterfazSATRecepcionarParametrizacionCalculoFechaEntregaExactaHistorico(Map params);
	public void deleteInterfazSATRecepcionarParametrizacionCalculoFechaEntregaExacta(Map params);
	public void insertInterfazSATRecepcionarParametrizacionCalculoFechaEntregaExacta(Map params);
	
	public void insertInterfazSATRecepcionarExcepcionesFechaEntregaExacta(Map params);
	
	public void insertInterfazSATRecepcionarSeguimientoPedido(Map params);
	
	public void insertInterfazSATRecepcionarImpresionBoletasEntrega(Map params);
}
