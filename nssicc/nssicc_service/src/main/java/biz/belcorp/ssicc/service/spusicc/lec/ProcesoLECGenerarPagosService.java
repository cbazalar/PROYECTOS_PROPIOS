package biz.belcorp.ssicc.service.spusicc.lec;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

public interface ProcesoLECGenerarPagosService extends Service{

	public List getTipoPago(Map map);
	
	public void executeGenerarPagoRegular(Map params);
	
	public void executeGenerarPagoAdicional(Map params);
	
	/**
	 * Devuelve Lista de tipos de Pago
	 * @param params
	 * @return
	 */
	public List getTipoPago02(Map params);
	
	
	/**
	 * Devuelve Lista de Motivos de Bloqueo
	 * @param params
	 * @return
	 */
	public List getTipoMotivoBloqueo(Map params);
	
}
