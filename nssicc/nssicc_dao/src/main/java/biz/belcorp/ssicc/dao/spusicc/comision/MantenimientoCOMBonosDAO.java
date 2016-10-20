/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.comision;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.comision.model.Bonos;
import biz.belcorp.ssicc.dao.spusicc.comision.model.DetalleBonos;

/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 *
 */
public interface MantenimientoCOMBonosDAO extends DAO {

	/**
	 * Retorna lista de Bonos
	 * @param params
	 * @return
	 */
	List getBonosEjecutivas(Map params);

	/**
	 * Retorna lista de los detalles del Bono
	 * @param detalleBonos
	 * @return
	 */
	List getListDetalleBonos(DetalleBonos detalleBonos);

	/**
	 * actualiza el bono
	 * @param bono
	 */
	void updateBonos(Bonos bono);

	/**
	 * obtine el codigo d econcepto siguiente
	 * @param bono
	 * @return
	 */
	String getSiguienteCodigoConcepto(Bonos bono);

	/**
	 * inserta bonos
	 * @param bono
	 */
	void insertBonos(Bonos bono);

	/**
	 * inserta detalle bono
	 * @param detalleBonos
	 */
	void insertDetalleBonos(DetalleBonos detalleBonos);

	/**
	 * actualiza detalle bono
	 * @param detalleBonos
	 */
	void updateDetalleBonos(DetalleBonos detalleBonos);
	
}
