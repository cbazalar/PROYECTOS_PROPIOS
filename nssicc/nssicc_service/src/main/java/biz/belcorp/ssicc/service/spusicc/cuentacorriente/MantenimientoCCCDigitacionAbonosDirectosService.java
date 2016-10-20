package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.List;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.CargoAbonoDirectoCabecera;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author pejflorencio
 *
 */
public interface MantenimientoCCCDigitacionAbonosDirectosService extends Service {

	/**
	 * @return
	 * Devuelve los tipos de Abonos Directos Digitable
	 */	
	public List getTiposAbonosDirectosDigitables();
		
	
	/**
	 * @param cccCargoAbonoDirecCabec
	 * @param detallesCargoAbonoDirecList
	 * @param usuario
	 * Inserta el Cargo Abono Directo digitado
	 */
	public void insertCargoAbonoDirectoDigitado(CargoAbonoDirectoCabecera cccCargoAbonoDirecCabec, List detallesCargoAbonoDirecList) throws Exception;
		
}
