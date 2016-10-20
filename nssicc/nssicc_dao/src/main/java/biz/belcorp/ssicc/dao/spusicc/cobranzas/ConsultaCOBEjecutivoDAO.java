
package biz.belcorp.ssicc.dao.spusicc.cobranzas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="ConsultaCOBEjecutivoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florenco Arias</a>
 */
public interface ConsultaCOBEjecutivoDAO extends DAO {
		
	/**
	 * Obtiene la Cartera del Ejecutivo de Cobranzas
	 * @param criteria
	 * @return
	 */
	public List getCarteraEjecutivoByFilter(Map criteria);
	
	/**
	 * Obtiene la Cartera de la Llamada Entrante
	 * @param criteria
	 * @return
	 */
	public List getCarteraLlamadaEntranteByFilter(Map criteria);
	
	/**
	 * Graba la gestion realizada
	 * @param criteria
	 */
	public void saveGestionCartera(Map criteria);
	
	/**
	 * Graba el compromiso de pago
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
	 * Obtiene el listado de referencias de la deudora
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
	 * Obtiene el detalle de la cuenta corriente de la deudora
	 * @param criteria
	 * @return
	 */
	public List getDetalleConsultora(Map criteria);
	
	/**
	 * Obtiene el saldo actual de la deudora
	 * @param criteria
	 * @return
	 */
	public String getSaldoInicial(Map criteria);
	
	/**
	 * Obtiene el detalle de un cargo de la deudora
	 * @param criteria
	 * @return
	 */
	public List getDetalleMovimiento(Map criteria);
	
	/**
	 * Actualiza los telefonos de la deudora
	 * @param criteria
	 */
	public void actualizarTelefonoDeudora(Map criteria);
}
