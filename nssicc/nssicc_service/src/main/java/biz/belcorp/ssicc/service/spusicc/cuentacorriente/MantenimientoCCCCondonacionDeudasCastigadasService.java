package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author
 *
 */
public interface MantenimientoCCCCondonacionDeudasCastigadasService extends Service {
				
	/**
	 * Obtiene en una lista de la condonacion deudas castigadas
	 * @param datos
	 * @return
	 */
	public List getCondonacionDeudasCastigadasList (Map datos);	
			
	/**
	 * Ejecuta el proceso de Condonacion
	 * @param datos
	 */
	public void executeCondonacionDeudaCastigo(Map datos);	
}

