package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.EstructuraChequesDigitados;

/**
 * <p>
 * <a href="MantenimientoCCCDigitacionPagosChequesDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 */

public interface MantenimientoCCCDigitacionPagosChequesDAO extends DAO {
	

	/**
	 * Devuelve el listado de bancos para el pago de cheques
	 * @param criteria
	 * @return
	 */
	public List getBancosCheques(Map criteria); 

	/**
	 * Devuelve las sucursales de los bancos
	 * @param criteria
	 * @return
	 */
	public List getSucursalesCheques(Map criteria);
	
	/**
	 * Genera el pago del cheque
	 * @param criteria
	 */
	public void generarPagoCheque(EstructuraChequesDigitados estructura);
	
	/**
	 * Registrar lote bancario
	 * @param criteria
	 */
	public void generarLoteBancarioCheque(Map criteria);
	
	/**
	 * Retorna los datos de la consultora por documento de identidad
	 * @param criteria
	 * @return
	 */
	public List getCabeceraConsultoraByDocIdentidad(Map criteria);
	
	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public Integer getClasificacionConsultoraByDocIdentidad(Map criteria);
	
	/**
	 * 
	 * @param criteriaConsultora
	 * @return
	 */
	public String getConsultoraByDocIdentidad(Map criteriaConsultora);
	
	
	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public Integer getActivaConsultoraByDocIdentidad(Map criteria);
}
