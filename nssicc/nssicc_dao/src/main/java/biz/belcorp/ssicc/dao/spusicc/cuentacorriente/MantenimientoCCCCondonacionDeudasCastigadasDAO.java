package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author
 *
 */
public interface MantenimientoCCCCondonacionDeudasCastigadasDAO extends DAO {

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

