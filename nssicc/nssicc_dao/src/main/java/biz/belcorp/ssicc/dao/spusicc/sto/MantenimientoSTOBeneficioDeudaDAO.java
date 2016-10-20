package biz.belcorp.ssicc.dao.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface MantenimientoSTOBeneficioDeudaDAO extends DAO{

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
	public void deleteBeneficioDeuda(Map criteria);

	/**
	 * Inserta Beneficio Deuda 
	 * @param criteria
	 */
	public void insertDeudaBenficio(Map criteria);

}