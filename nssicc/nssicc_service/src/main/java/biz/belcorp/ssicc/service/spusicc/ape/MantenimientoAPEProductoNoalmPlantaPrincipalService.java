/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.ape.model.MantenimientoAPEProductoNoalmPlantaPrincipal;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author csoberon
 *
 */
public interface MantenimientoAPEProductoNoalmPlantaPrincipalService extends Service {
	
	public List getEmpresasExternas();
	
	public List getProductosNoalmPlantaPrincipalByCriteria(Map params);
	
	public MantenimientoAPEProductoNoalmPlantaPrincipal getProductoNoalmPlantaPrincipal(String id);
	
	public void updateProductoNoalmPlantaPrincipal(MantenimientoAPEProductoNoalmPlantaPrincipal producto);
}
