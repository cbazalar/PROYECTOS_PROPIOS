package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.CargoAbonoDirectoCabecera;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author pejflorencio
 *
 */
public interface MantenimientoCCCDigitacionCADService extends Service {

	/**
	 * @return
	 * Devuelve los tipos de Cargos y Abonos Directos
	 */	
	public List getTiposCargoAbonoDirectos();
		
	/**
	 * Devuelve el valor del parametro segun mapa
	 * @param datos
	 * @return
	 */
	public String getIndicadorParametro(Map datos);
	
	/**
	 * @param cccCargoAbonoDirecCabec
	 * @param detallesCargoAbonoDirecList
	 * @param usuario
	 * Inserta el Cargo Abono Directo digitado
	 */
	public void insertCargoAbonoDirectoDigitado(CargoAbonoDirectoCabecera cccCargoAbonoDirecCabec, List detallesCargoAbonoDirecList) throws Exception;
		
}
