package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.ConfiguracionBalanceoSubLinea;

/**
 * @author Nicols Lpez
 *
 */
public interface MantenimientoAPEConfiguracionBalanceoSubLineaDAO extends DAO {
	   
	/**
	 * Recupera la lista de Configuracion Balanceo SubLinea 
	 * 
	 * @param criteria
	 * @return
	 */
	public List getConfiguracionBlSublineaList(Map criteria);
	
	/**
	 * @param criteria
	 * @return La configuracin de Balanceo de Sub Lnea
	 */
	public ConfiguracionBalanceoSubLinea getObtenerConfiguracionBalanceoSubLinea(Map criteria);
	
	/**
	 * Actualiza la Configuracin de Sub Lnea
	 * @param criteria
	 */
	public void actualizaConfiguracionSubLinea(Map criteria);
	
}
