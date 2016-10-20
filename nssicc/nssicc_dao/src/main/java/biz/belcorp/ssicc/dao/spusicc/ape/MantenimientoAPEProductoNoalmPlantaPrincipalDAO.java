/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.MantenimientoAPEProductoNoalmPlantaPrincipal;

/**
 * @author csoberon
 *
 */
public interface MantenimientoAPEProductoNoalmPlantaPrincipalDAO extends DAO {

	public List getEmpresasExternas();
	
	public List getProductosNoalmPlantaPrincipalByCriteria(Map params);
	
	public MantenimientoAPEProductoNoalmPlantaPrincipal getProductoNoalmPlantaPrincipal(String id);

	public void updateProductoNoalmPlantaPrincipal(MantenimientoAPEProductoNoalmPlantaPrincipal producto);
}
