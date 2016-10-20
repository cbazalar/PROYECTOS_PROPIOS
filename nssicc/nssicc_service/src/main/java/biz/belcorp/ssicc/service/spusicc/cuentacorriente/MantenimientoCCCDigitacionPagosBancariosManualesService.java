package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.List;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.PagoBancarioCabecera;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author pejflorencio
 *
 */
public interface MantenimientoCCCDigitacionPagosBancariosManualesService extends Service {
				

	/**
	 * @param cccPagoBancarioCabecera
	 * @param detallesPagosBancariosList
	 * @throws Exception
	 */
	public void generarPagoBancarioManual(PagoBancarioCabecera cccPagoBancarioCabecera, List detallesPagosBancariosList) throws Exception;
		
}
