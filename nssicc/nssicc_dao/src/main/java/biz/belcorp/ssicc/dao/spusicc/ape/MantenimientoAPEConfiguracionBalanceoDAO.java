package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.ConfiguracionBalanceo;

/**
 * @author Nicols Lpez
 *
 */
public interface MantenimientoAPEConfiguracionBalanceoDAO extends DAO {
	   
	/**
	 * Recupera la lista de Configuracion Balanceo 
	 * 
	 * @param criteria
	 * @return
	 */
	public List getConfiguracionBalanceoList(Map criteria);
	
	/**
	 * @param criteria
	 * @return La lista de Funcion de Distribucin de productos
	 */
	public List getFuncionDistribucionList(Map criteria);
	
	/**
	 * @param criteria
	 * @return OidFuncionDist
	 */
	public String getOidFuncionDist(Map criteria);
	
	/**
	 * @param criteria
	 * Realiza la insercin (actualizacin) de los datos de porcentaje y programa en el caso que no existan en el registro de  la entidad Lnea Armado
	 */
	public void insertarConfiguracionBalanceo(Map criteria);
	
	/**
	 * @param criteria
	 * @return Valida Si existe Configuracin de Balanceo
	 */
	public String getValidaExisteConfiguracionBalanceo(Map criteria);
	
	/**
	 * @param criteria
	 * @return Los datos de Configuracin Balanceo Lnea de Armado
	 */
	public ConfiguracionBalanceo getConfiguracionBalanceoObject(Map criteria);
	
	/**
	 * @param criteria
	 * Elimina la Configuracin de Balanceo por Lnea de Armado
	 */
	public void eliminarConfiguracionBalanceo(Map criteria);
	
	/**
	 * @param criteria
	 * @return El Oid Linea Armado por Oid Centro
	 */
	public String getOidLineaArmadoxOidCD(Map criteria);
	
}
