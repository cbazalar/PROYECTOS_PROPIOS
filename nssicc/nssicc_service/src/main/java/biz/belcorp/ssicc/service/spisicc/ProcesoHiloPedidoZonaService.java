package biz.belcorp.ssicc.service.spisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spisicc.model.ProcesoSpool;

public interface ProcesoHiloPedidoZonaService {
	
	/**
     * Devuelve la lista de Zonas (oid) en base a la campaña y a la fecha de facturacion
     * @param criteria
     * @return
     */
    public List getListaZonasByCampanaFechaFacturacion(Map criteria);
	
	/**
	 * Devuelve la lista de pedidos (oid) en base a la zona (oid), campaña y a la fecha de facturacion
	 * @param criteria
	 * @return
	 */
	public List getListaPedidosByZonasCampanaFechaFacturacion(Map criteria);
	
	/**
	 * Ejecuta stored cuadrar ofertas
	 * @param criteria
	 */
	public void executeCuadrarOfertasPedidoZonaMultihilo(Map criteria);
	
	/**
	 * Ejecuta stored Monto Minimo
	 * @param criteria
	 */
	public void executeMontoMinimoPedidoZonaMultihilo(Map criteria);
	
	/**
	 * Ejecuta stored Monto Maximo
	 * @param criteria
	 */
	public void executeMontoMaximoPedidoZonaMultihilo(Map criteria);
	
	/**
	 * Ejecuta stored Recuperación Obligatoria
	 * @param criteria
	 */
	public void executeRecuperacionObligatoriaPedidoZonaMultihilo(Map criteria);
	
	/**
	 * Ejecuta stored Validar Agregados Mav
	 * @param criteria
	 */
	public void executeValidarAgregadosMavPedidoZonaMultihilo(Map criteria);
}
