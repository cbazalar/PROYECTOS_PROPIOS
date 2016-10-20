package biz.belcorp.ssicc.service.spisicc;

import java.util.List;

import biz.belcorp.ssicc.dao.spisicc.model.ProcesoSpool;

public interface ProcesoHiloImpresionService {

	/**
	 * @param proceso
	 */
	public void executeSpoolDetalleFactura(ProcesoSpool proceso);

	/**
	 * 
	 */
	public void deleteProcesoIMPSpoolDetalleFactura();

	/**
	 * @return
	 */
	public List getListaRegionesActivasSpool();

	/**
	 * @return
	 */
	public List getListaZonasActivasSpool();

	/**
	 * @param proceso
	 */
	public void executeSpoolCupones(ProcesoSpool proceso);

	/**
	 * 
	 */
	public void deleteProcesoIMPSpoolCupones();

	/**
	 * Envia la interfaz de ultimas noticias
	 * @param proceso
	 */
	public void executeSpoolUltimasNoticias(ProcesoSpool proceso);

	/**
	 * Envia la interfaz de boleta despacho
	 * @param proceso
	 */
	public void executeSpoolBoletaDespacho(ProcesoSpool proceso);

	/**
	 * Envia la interfaz de orden de compra
	 * @param proceso
	 */
	public void executeSpoolOrdenCompra(ProcesoSpool proceso);

	/**
	 * Envia la interfaz de estado cuenta corriente
	 * @param proceso
	 */
	public void executeSpoolEstadoCtaCte(ProcesoSpool proceso);

	/**
	 * Envia la interfaz de detalle factura por zona
	 * @param proceso
	 */
	public void executeSpoolDetalleFacturaZona(ProcesoSpool proceso);
	
	/**
	 * ejecuta el sp de calcular el consolidado
	 * @param proceso
	 */
	public void executeSpoolCalculaConsolidado(ProcesoSpool proceso);

	/**
	 * Ejecuta el sp de compaginacion final
	 * @param proceso
	 */
	public void executeSpoolCompaginacionFinal(ProcesoSpool proceso);

	/**
	 * ejecuta el sp de generar ruv
	 * @param proceso
	 */
	public void executeSpoolGeneraRuv(ProcesoSpool proceso);

	/**
	 * ejecuta el sp de generar nota debito matricial
	 * @param proceso
	 */
	public void executeSpoolGeneraNotaDebitoMatricial(ProcesoSpool proceso);

	/**
	 * ejecuta el sp de nota credito matricial
	 * @param proceso
	 */
	public void executeSpoolGeneraNotaCreditoMatricial(ProcesoSpool proceso);

	/**
	 * ejecuta el sp de guia remision
	 * @param proceso
	 */
	public void executeSpoolGeneraGuiaRemisionMatricial(ProcesoSpool proceso);

	/**
	 * ejecuta el sp de factura matricial
	 * @param proceso
	 */
	public void executeSpoolGeneraFacturaMatricial(ProcesoSpool proceso);

	/**
	 * ejecuta el sp de dcto legal
	 * @param proceso
	 */
	public void executeSpoolGeneraDocumentosLegales(ProcesoSpool proceso);

	/**ejecuta el sp de cupon
	 * @param proceso
	 */
	public void executeSpoolGeneraCuponMatricial(ProcesoSpool proceso);



	/**ejecuta el sp de boleta matricial
	 * @param proceso
	 */
	public void executeSpoolGeneraBoletaMatricial(ProcesoSpool proceso);

	/**
	 * ejecuta el sp de boleta principal
	 * @param proceso
	 */
	public void executeSpoolGeneraBoletaPremioMatricial(ProcesoSpool proceso);

	/**
	 * ejecuta el sp de cta cte
	 * @param proceso
	 */
	public void executeSpoolGeneraCtaCteService(ProcesoSpool proceso);

	/**
	 * @param proceso
	 */
	public void executeSpoolGeneraBoletasElectronicas(ProcesoSpool proceso);
	
	/**
	 * Ejecuta el sp Factura Laser Multihilo
	 * @param proceso
	 */
	public void executeSpoolFacturaLaserMultihilo(ProcesoSpool proceso);
	
	/**
	 * Ejecuta el sp Nota de Credito Laser Multihilo
	 * @param proceso
	 */
	public void executeSpoolNotaCreditoLaserMultihilo(ProcesoSpool proceso);
	
	/**
	 * Ejecuta el sp Nota de Credito Laser Multihilo
	 * @param proceso
	 */
	public void executeSpoolNotaDebitoLaserMultihilo(ProcesoSpool proceso);

	/**
	 * @param proceso
	 */
	public void executeSpoolDetalleFactura3(ProcesoSpool proceso);
	
	/**
	 * @param proceso
	 */
	public void executeSpoolDetalleFactura4(ProcesoSpool proceso);

	/**
	 * @param proceso
	 */
	public void executeSpoolGenerarCtaCorriente(ProcesoSpool proceso);

	/**
	 * Ejecuta el sp Genera Fecha de Reparto
	 * @param proceso
	 */
	public void executeGeneraFechaRepartoMultihilo(ProcesoSpool proceso);
	
}
