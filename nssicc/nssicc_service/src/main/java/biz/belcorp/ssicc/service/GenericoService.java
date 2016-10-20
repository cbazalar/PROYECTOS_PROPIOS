package biz.belcorp.ssicc.service;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.service.framework.Service;


/**
 * interface de validacion de STO.
 * <p>
 * <a href="ProcesoSTOService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 */

public interface GenericoService extends Service {

	/**
	 * Obtiene la lista parametros de Pais de la tabla BAS_PARAM_PAIS
	 * @param parametroPais
	 * @return
	 */
	List getParametrosPais(ParametroPais parametroPais);
	
	

	/**
	 * Obtiene el valor del parametro de la tabla BAS_PARAM_PAIS
	 * @param codigoPais
	 * @param codigoSistema
	 * @param codigoParametro
	 * @return
	 */
	String getParametroPais(String codigoPais, String codigoSistema, String codigoParametro);

	/**
	 * @param criteria
	 * @return
	 */
	public String getPeriodoNSiguiente(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public String getPeriodoByFecha(Map criteria);
	


	/**
	 * @param codigoPais
	 * @param codigoMenu
	 * @param url
	 * @param login
	 * @param ip
	 */
	public void executeAuditMenu(String codigoPais,String codigoMenu, String url, String login,String ip);
	
	
	/**
	 * @param criteria
	 * @return
	 */
	public boolean getValidacionDentroRangoPeriodoVigente(Map criteria);


}
