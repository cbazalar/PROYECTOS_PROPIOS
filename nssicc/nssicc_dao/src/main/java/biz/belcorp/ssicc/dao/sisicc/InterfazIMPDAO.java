package biz.belcorp.ssicc.dao.sisicc;

import java.util.Map;

/**
 * DAO de la Interfaz del Programa de Empresarias
 * 
 * @author <a href="mailto:dooliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */
public interface InterfazIMPDAO {

	/**
	 * @param params
	 */
	public void executeEnviarMaestroEmpresarias(Map params);	
	
	/**
	 * @param params
	 */
	public void executeEnviarBajasEmpresarias(Map params);
	
	/**
	 * @param params
	 */
	public void executeEnviarVinculosEmpresarias(Map params);
	
	
	/**
	 * Envio de Facturas Cabeceras para Peru
	 * @param criteria
	 */
	public void executeInterfazIMPEnviarFacturasCabeceraPeru(Map criteria);
	
	/**
	 * Envio de Facturas Detalle para Peru
	 * @param criteria
	 */
	public void executeInterfazIMPEnviarFacturasDetallePeru(Map criteria); 
	
	/**
	 * Envio de Boletas 065 Cabecera para Peru
	 * @param criteria
	 */
	public void executeInterfazIMPEnviarBoletas065CabeceraPeru(Map criteria);
	
	
	/**
	 * Envio de Boletas 065 Detalle para Peru
	 * @param criteria
	 */
	public void executeInterfazIMPEnviarBoletas065DetallePeru(Map criteria);
	
	
	/**
	 * Envio de Boletas 067 Cabecera para Peru
	 * @param criteria
	 */
	public void executeInterfazIMPEnviarBoletas067CabeceraPeru(Map criteria);
	
	
	/**
	 * Envio de Boletas 067 Detalle para Peru
	 * @param criteria
	 */
	public void executeInterfazIMPEnviarBoletas067DetallePeru(Map criteria);
	
	
	/**
	 * Envio de Notas Debito Cabecera para Peru
	 * @param criteria
	 */
	public void executeInterfazIMPEnviarNotasDebitoCabeceraPeru(Map criteria);
	
	
	/**
	 * Envio de Notas Debito Detalle para Peru
	 * @param criteria
	 */
	public void executeInterfazIMPEnviarNotasDebitoDetallePeru(Map criteria);
	
	/**
	 * Envio de Notas Credito Factura Cabecera para Peru
	 * @param criteria
	 */
	public void executeInterfazIMPEnviarNotasCreditoFacturasCabeceraPeru(Map criteria);
	
	
	/**
	 * Envio de Notas Credito Factura Detalle para Peru
	 * @param criteria
	 */
	public void executeInterfazIMPEnviarNotasCreditoFacturasDetallePeru(Map criteria);
	
	
	/**
	 * Envio de Notas Credito Boletas Cabecera para Peru
	 * @param criteria
	 */
	public void executeInterfazIMPEnviarNotasCreditoBoletasCabeceraPeru(Map criteria);
	
	
	/**
	 * Envio de Notas Credito Boletas Detalle para Peru
	 * @param criteria
	 */
	public void executeInterfazIMPEnviarNotasCreditoBoletasDetallePeru(Map criteria);
	
	/**
	 * Envo de secuenciacion de pedidos
	 * @param params
	 */
	public void executeInterfazIMPEnviarSecuenciaBoletaElectronica(Map params);

	/**
	 * Enva al historico la data de pedidos procesada
	 * @param params
	 */
	public void executeInterfazIMPEnviarBoletaElectronicaHistorico(Map params);

	/**
	 * @param params
	 */
	public void executeInterfazIMPEnviarOrdenImpresioXerox(Map params);

	/**
	 * @author sguerra
	 * @param params
	 * Permite enviar el documento de la cabecera de factura
	 */
	public void executeInterfazIMPEnviarFacturasCabeceraDocumento(Map params);

	/**
	 * @author sguerra
	 * @param params
	 * Permite enviar el documento del detalle de factura
	 */
	public void executeInterfazIMPEnviarFacturasDetalleDocumento(Map params);

	/**
	 * @author sguerra
	 * @param params
	 * Permite enviar el documento de la cabecera de boleta 065
	 */
	public void executeInterfazIMPEnviarBoletas065CabeceraDocumento(Map params);

	/**
	 * @author sguerra 
	 * @param params
	 * Permite enviar el documento del detalle de boleta 065
	 */
	public void executeInterfazIMPEnviarBoletas065DetalleDocumento(Map params);

	/**
	 * @author sguerra
	 * @param params
	 * Permite enviar el documento de la cabecera de boleta 067
	 */
	public void executeInterfazIMPEnviarBoletas067CabeceraDocumento(Map params);

	/**
	 * @author sguerra
	 * @param params
	 * Permite enviar el documento del detalle de boleta 067
	 */
	public void executeInterfazIMPEnviarBoletas067DetalleDocumento(Map params);

	/**
	 * @author sguerra
	 * @param params
	 * Permite enviar el documento de la cabecera de notas de debito
	 */
	public void executeInterfazIMPEnviarNotasDebitoCabeceraDocumento(Map params);

	/**
	 * @author sguerra
	 * @param params
	 * Permite enviar el documento del detalle de de notas de debito
	 */
	public void executeInterfazIMPEnviarNotasDebitoDetalleDocumento(Map params);

	/**
	 * @author sguerra
	 * @param params
	 * Permite enviar el documento de la cabecera de notas de credito por factura
	 */
	public void executeInterfazIMPEnviarNotasCreditoFacturasCabeceraDocumento(
			Map params);

	/**
	 * @author sguerra
	 * @param params
	 * Permite enviar el documento del detalle de notas de credito por factura
	 */
	public void executeInterfazIMPEnviarNotasCreditoFacturasDetalleDocumento(
			Map params);

	/**
	 * @author sguerra
	 * @param params
	 * Permite enviar el documento de la cabecera de notas de credito por boleta
	 */
	public void executeInterfazIMPEnviarNotasCreditoBoletasCabeceraDocumento(
			Map params);

	/**
	 * @author sguerra
	 * @param params
	 * Permite enviar el documento del detalle de notas de credito por boleta
	 */
	public void executeInterfazIMPEnviarNotasCreditoBoletasDetalleDocumento(
			Map params);

	/**
	 * @param params
	 */
	public void executeInterfazIMPEnviarOrdenImpresioXeroxAlterna(Map params);

	public void executeDesactivacionAutomaticaFLX();

	/**
	 * Permite enviar el documento de la cabecera de notas de credito por boleta retail
	 * @author sguerra
	 * @param params
	 */
	public void executeInterfazIMPEnviarNotasCreditoBoletaCabeceraRetail(Map params);

	/**
	 * Permite enviar el documento del detalle de notas de credito por boleta retail
	 * @author sguerra
	 * @param params
	 */
	public void executeInterfazIMPEnviarNotasCreditoBoletaDetalleRetail(Map params);

	/**
	 * Permite enviar el documento de la cabecera de notas de credito por factura retail
	 * @author sguerra
	 * @param params
	 */
	public void executeInterfazIMPEnviarNotasCreditoFacturaCabeceraRetail(Map params);

	/**
	 * Permite enviar el documento del detalle de notas de credito por factura retail
	 * @author sguerra
	 * @param params
	 */
	public void executeInterfazIMPEnviarNotasCreditoFacturaDetalleRetail(Map params);
	
	public void executeInterfazIMPProcesoFlexipago();		
	

	/**
	 * Permite desbloquear consultoras bloqueadas x BR
	 * @author aoviedo
	 * @param params
	 */
	public void executeInterfazIMPDesbloquearConsultorasBloqueadasxBR(Map params);
	
	
	
}