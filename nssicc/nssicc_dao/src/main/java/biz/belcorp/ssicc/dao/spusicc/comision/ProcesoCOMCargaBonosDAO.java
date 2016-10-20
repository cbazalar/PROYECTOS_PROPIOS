package biz.belcorp.ssicc.dao.spusicc.comision;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.comision.model.DetalleBonos;

public interface ProcesoCOMCargaBonosDAO extends DAO {

	/**
	 * Elimina todos los detalles con un codigo de concepto dado
	 * @param detalle
	 */
	void deleteDetalleBonos(DetalleBonos detalle);

	/**
	 * Inserta los detalles del bono
	 * @param list
	 */
	void insertListDetalleBonos(List list) throws Exception;

	
}
