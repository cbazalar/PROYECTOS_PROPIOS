package biz.belcorp.ssicc.dao.spusicc.cobranzas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="ConsultaCOBTelecobroDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */
public interface ConsultaCOBTelecobroDAO extends DAO {
		
	public List getConsultorasByFilter(Map criteria);
	public void saveLlamadaConsultora(Map criteria);
	public void saveCompromisoPago(Map criteria);
	public List getListaLlamadas(Map criteria);
	public List getReferencias(Map criteria);
	public List getDetalleConsultora(Map criteria);
	public String getSaldoInicial(Map criteria);
	public List getDetalleMovimiento(Map criteria);
	
	/**
	 * Obtiene la cabecera de movimientos de bancos
	 * @author sguerra
	 * @param criteria
	 * @return
	 */
	public List getCabeceraMovimientoBanco(Map criteria);
	
	/**
	 * Obtiene el detalle de movimientos de bancos
	 * @author sguerra
	 * @param criteria
	 * @return
	 */
	public List getDetalleMovimientoBanco(Map criteria);
	
	/**
	 * Obtiene el detalle de movimientos de cuentas
	 * @author sguerra
	 * @param criteria
	 * @return
	 */
	public List getDetalleMovimientoCuenta(Map criteria);
	
	/**
	 * @author sguerra
	 * @param criteria
	 */
	public void executeCtaCteUltimasCampanhas(Map criteria);
	
	/**
	 * @author sguerra
	 * @param criteria
	 * @return
	 */
	public List getCtaCteUltimasNCampanhas(Map criteria);
	
	/**
	 * @author sguerra
	 * @param criteria
	 * @return
	 */
	public List getCtaCteUltimasNCampanhasCabecera(Map criteria);
	
        /**
	 * 
	 * @param registro
	 */
	public void insertReporteHIPCuentaCorrientesCampanha(Map registro);

	/**
	 * 
	 * @param codigoUsuario
	 */
	public void deleteReporteHIPCuentaCorrientesCampanha(String codigoUsuario);
	
	/**
	 * Obtener pedido en la camapaña de pedido
	 * @param criteria
	 * @return
	 */
	public Integer getPedidoCampanhaProceso(Map criteria);
	
	/**
	 * Obtener pedido en la camapaña de pedido
	 * @param criteria
	 * @return
	 */
	public Integer getCierreRegionCampanhaProceso(Map criteria);

	/**
	 * Obtiene un valor entero que permite valida si se agregar cuenta de incobrables migradas [1] Si [0] No
	 * @param criteria
	 * @return
	 */
	public Integer getValidarConsultoraIncobrablesMigradas(Map criteria);
	
	/**
	 * Obtiene las cuentas corrientes mas la incobrable migrada
	 * @param criteria
	 * @return
	 */
	public List getCuentaCorrienteConsultoraIncobrablesMigradas(Map criteria);
	
	/**
	 * Obtiene Abono en proceso CDR
	 * @param criteria
	 * @return
	 */
	public List getAbonoProcesoCDR(Map criteria);

}