package biz.belcorp.ssicc.service.spusicc.cobranzas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaCOBTelecobroService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */
public interface ConsultaCOBTelecobroService extends Service {
    	
	public List getConsultorasByFilter(Map criteria);
	public void saveLlamadaConsultora(Map criteria);
	public void saveCompromisoPago(Map criteria);
	public List getListaLlamadas(Map criteria);
	public List getReferencias(Map criteria);
	public List getDetalleConsultora(Map criteria);
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
	 * Obtiene el detalle de movimientos de cuenta
	 * @author sguerra
	 * @param criteria
	 * @return
	 */
	public List getDetalleMovimientoCuenta(Map criteria);
	
	/**
	 * @author sguerra
	 * @param criteria2
	 * @return
	 */
	public Map getDetalleConsultoraCampanha(Map criteria);
	
	/**
	 *
	 * @param listaReporte
	 * @param codigoUsuario
	 */
	public void insertReporteHIPCuentaCorrientesCampanha(List listaReporte, String codigoUsuario);

	/**
	 * Obtener pedido en la camapaa de pedido
	 * @param criteria
	 * @return
	 */
	public Integer getPedidoCampanhaProceso(Map criteria);
	
	/**
	 * Obtener pedido en la camapaa de pedido
	 * @param criteria
	 * @return
	 */
	public Integer getCierreRegionCampanhaProceso(Map criteria);
}
