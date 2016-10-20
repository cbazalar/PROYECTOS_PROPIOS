package biz.belcorp.ssicc.service.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Jesse James Rios Franco
 */

public interface MantenimientoSTOBeneficioDeudaService extends Service{

	/**
	 * Devuelve la lista de Beneficio Deuda de acuerdo a los filtros
	 * @param criteria
	 * @return
	 */
	public List getBeneficioDeudaList(Map criteria);

	/**
	 * Elimina los Beneficios Deuda por el OID
	 * @param parametros
	 */
	public void deleteBeneficioDeuda(Map parametros);

	/**
	 * Inserta Beneficio Deuda 
	 * @param criteria
	 */
	public int insertDeudaBenficio(Map criteria);

}