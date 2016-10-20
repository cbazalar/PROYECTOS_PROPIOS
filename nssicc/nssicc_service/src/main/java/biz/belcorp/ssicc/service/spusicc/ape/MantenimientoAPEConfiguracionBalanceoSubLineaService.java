package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.ape.model.ConfiguracionBalanceoSubLinea;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPEConfiguracionBalanceoSubLineaService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez</a>
 */
public interface MantenimientoAPEConfiguracionBalanceoSubLineaService extends Service {

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
