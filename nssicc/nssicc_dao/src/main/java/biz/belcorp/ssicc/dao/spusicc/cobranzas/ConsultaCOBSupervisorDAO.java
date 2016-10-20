
package biz.belcorp.ssicc.dao.spusicc.cobranzas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="ConsultaCOBTelecobroDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florenco Arias</a>
 */
public interface ConsultaCOBSupervisorDAO extends DAO {
	
	/**
	 * Obtiene la cartera del supervisor
	 * @param criteria
	 * @return
	 */
	public List getCarteraSupervisorByFilter(Map criteria);
	
	/**
	 * Obtiene la consultora del supervisor
	 * @param criteria
	 * @return
	 */
	public List getConsultoraSupervisorByFilter(Map criteria);
	
	/**
	 * Graba la gestion realizada
	 * @param criteria
	 */
	public void saveGestionCartera(Map criteria);
	
	/**
	 * Graba las gestiones con compromiso de pago
	 * @param criteria
	 */
	public void saveCompromisoPago(Map criteria);
	
	/**
	 * Obtiene el listado de gestiones realizadas sobre la deudora
	 * @param criteria
	 * @return
	 */
	public List getListaGestionesCartera(Map criteria);
	
	/**
	 * Obtiene las referencias para un cartera especifica
	 * @param criteria
	 * @return
	 */
	public List getReferencias(Map criteria);
	
	/**
	 * Obtiene el listado de referencias de la deudora
	 * @param criteria
	 * @return
	 */
	public List getReferenciasDeudora(Map criteria);
	
	/**
	 * Obtiene el listado de referencias de la deudora
	 * @param criteria
	 * @return
	 */
	public List getAvalesDeudora(Map criteria);

	/**
	 * Obtiene el detalle de la consultora
	 * @param criteria
	 * @return
	 */
	public List getDetalleConsultora(Map criteria);
	
	/**
	 * Obtiene el detalle de la cuenta corriente de la consultora
	 * @param criteria
	 * @return
	 */
	public List getDetalleMovimiento(Map criteria);	
	
	/**
	 * Obtiene el saldo inicial de la deudora
	 * @param criteria
	 * @return
	 */
	public String getSaldoInicial(Map criteria);
	
	/**
	 * Actualiza el telefono de la deudora
	 * @param criteria
	 */
	public void actualizarTelefonoDeudora(Map criteria);
	
	/**
	 * Rebaja la cartera de la deudora
	 * @param criteria
	 */
	public void rebajarTemporalCarteraDeudora(Map criteria);
	
	/**
	 * Rebaja la cartera de la deudora
	 * @param criteria
	 */
	public void rebajarDefinitivoCarteraDeudora(Map criteria);
	
	/**
	 * Bloquea financieramente una consultora.
	 * @param criteria
	 */
	public void bloquearFinancieroDeudora(Map criteria);
}
