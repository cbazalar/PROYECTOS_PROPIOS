package biz.belcorp.ssicc.service.scsicc;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;
/**
 * @author <a href="">Jose Luis Rodriguez</a>
 *
 */
public interface ProcesoSICGenerarPedidosDigitadosZonaService  extends Service{

	
	/**
	 * Obtiene el correo de un gerente de regin
	 * 
	 * @param criteria
	 * @return
	 */
	public String getCorreoGerenteRegion(Map criteria);

	
	/**
	 * Obtiene el correo de un gerente de zona
	 * 
	 * @param criteria
	 * @return
	 */
	public String getCorreoGerenteZona(Map criteria);

}
