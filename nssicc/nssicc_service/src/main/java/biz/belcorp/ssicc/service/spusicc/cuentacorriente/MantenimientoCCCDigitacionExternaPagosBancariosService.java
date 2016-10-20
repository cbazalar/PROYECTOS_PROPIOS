package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author pejflorencio
 *
 */
public interface MantenimientoCCCDigitacionExternaPagosBancariosService extends Service {
					
	/**
	 * @param criteria
	 * @param detallesPagosBancariosList
	 * @throws Exception
	 */
	public void generarPagoBancarioExterno(Map criteria, List detallesPagosBancariosList) throws Exception;
		
}
