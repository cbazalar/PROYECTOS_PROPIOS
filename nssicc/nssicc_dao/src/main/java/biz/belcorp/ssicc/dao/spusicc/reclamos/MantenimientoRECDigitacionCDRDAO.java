/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.LabelValueCDR;
import biz.belcorp.ssicc.dao.model.ReclamoDigitadoCabecera;
import biz.belcorp.ssicc.dao.model.ReclamoDigitadoDetalle;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ProductoAgregacion;

/**
 * @author peextdoliva
 *
 */
public interface MantenimientoRECDigitacionCDRDAO extends DAO {

	/**
	 * @param recDigitCabec
	 * @param usuario
	 * inserta Cabecera de Reclamo Digitado
	 */
	public void insertCabeceraReclamoDigitado(ReclamoDigitadoCabecera recDigitCabec,Usuario usuario);
	/**
	 * @param recDigitDetal
	 * @param usuario
	 * inserta Detalle de Reclamo Digitado
	 */
	public void insertDetalleReclamoDigitado(ReclamoDigitadoDetalle recDigitDetal,Usuario usuario);
	/**
	 * @param criteria
	 * @return
	 * Devuelve las Operaciones de Reclamos
	 */
	public List getOperacionesReclamos(Map criteria);
	/**
	 * @param criteria
	 * @return
	 * Devuelve los Motivos de Reclamos
	 */
	public List getMotivosReclamos(Map criteria);
	/**
	 * @param criteria
	 * @return
	 * Devuelve consultora por el codigo
	 */
	public LabelValueCDR getConsultoraReclamoByCodigo(Map criteria);
	/**
	 * @param criteria
	 * @return
	 * Devuelve el periodo de reclamo 
	 */
	public String getPeriodoReclamo(Map criteria);
	/**
	 * @param criteria
	 * @return
	 * Devuelve el codigo de operacion
	 */
	public String getCodigoOperacion(Map criteria);
	/**
	 * @param params
	 * ejecuta Envio de Reclamos Digitados
	 */
	public void executeEnviarReclamosDigitados(Map params);
	/**
	 * @param criteria
	 * @return
	 * Devuelve valor si existe reclamo
	 */
	public String getExisteReclamo(Map criteria);
	/**
	 * @param criteria
	 * @return
	 * Devuelve lista de Detalles Digitados
	 */
	public List getListaDetallesDigitados(Map criteria);
	/**
	 * @param recDigitCabec
	 * Borra lista de detalles Digitados
	 */
	public void deleteDetallesCDR(ReclamoDigitadoCabecera recDigitCabec);
	
	/**
	 * @param criteria
	 * @return
	 * Devuelve el indicador Express de la Boleta de recojo
	 */
	public String getIndicadorExpressBoletaRecojo(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 * Devuelve lista de CUVs de CDRs a colocar en memoria
	 */
	public List getListaCodigosVentaCDR(Map criteria);

	
	/**
	 * @param reclamoDigitCabec
	 * @param detallesList
	 * @param params
	 */
	public void insertReclamoDigitadoOnline(ReclamoDigitadoCabecera reclamoDigitCabec, List detallesList,Map params);
	
	/**
	 * @return
	 * Devuelve el indicador de centro de servicio
	 */
	public List getIndicadorCentroServicio(Map criteria);
	
	/**
	 * Obtiene los tipos de operacion con su respectiva parametria
	 * @return
	 */
	public List getParametrosOperacionesReclamos();
	
	/**
	 * Metodo que devueleve el monto del pedido y el monto de devolucion
	 * @param criteria
	 * @return
	 */
	public Map getMontoPedidoDevolucion(Map criteria);
	
	/**
	 * Metodo que devueleve el porcentaje de monto de devolucion
	 * @param criteria
	 * @return
	 */
	public String getPorcentajeMontoDevolucion(Map criteria);
	
	/**
	 * Metodo que devueleve el monto del pedido y el monto de Faltantes
	 * @param criteria
	 * @return
	 */
	public Map getMontoPedidoFaltantes(Map criteria);
	
	/**
	 * Metodo que devueleve el porcentaje de monto de Faltantes
	 * @param criteria
	 * @return
	 */
	public String getPorcentajeMontoFaltantes(Map criteria);
	
	/**
	 * Metodo que devueleve el monto del pedido y el monto de cambio
	 * @param criteria
	 * @return
	 */
	public Map getMontoPedidoCambios(Map criteria);
	
	/**
	 * Metodo que devueleve el porcentaje de monto de cambios
	 * @param criteria
	 * @return
	 */
	public String getPorcentajeMontoCambios(Map criteria);
	
	/**
	 * Devuelve el indicador de pedido chequeado
	 * @return
	 */
	public String getIndicadorPedidoChequeado();
	
	/**
	 * Metodo que obtiene el codigo de Resultado de Chequeo de un pedido
	 * @param criteria
	 * @return
	 */
	public String getCodigoResultadoChequeo(Map criteria);
	
	/**
	 * Obtiene el indicador de Rechazo CDR
	 * @param criteria
	 * @return
	 */
	public String getIndicadorCDRRechazo(Map criteria);
	
	/**
	 * Obtiene el la ubicacion de la Consultora
	 * @param codigoConsultora
	 * @return
	 */
	public Map getUbicacionConsultora(Map criteria);
	
	public Integer getSaldoProducto(Map criteria);
	
	public String getIndicadorValorStock(Map criteria);
	public String getPeriodoCDR(Map criteria);
	public String getDescripcionPeriodo(Map criteria);
	public void getCodigoOperacionCorrecto(Map criteria);
	
	/**
	 * Valida Codigo Producto Desea en CDR
	 * @param criteria
	 * @return
	 */
	public String getValExcepProduDesea(Map criteria);
	
	public String getValExcepProduGanador(Map criteria);
	
    public String getValiAceptaCDR(Map criteria);
	
	public String getVerificaOperacion(Map criteria);
	
	
	/**
	 * Valida Unidades Desea en CDR
	 * @param criteria
	 * @return
	 */
	public String getValUnidadDesea(Map criteria);
	
	public String getValUnidadReclamo(Map criteria);
	
	
	public List getCodigoMotivoRechazo();
	public String getValorParam(Map criteria);
	public String getNuevoNumeroPedido(Map criteria);
	public void getMensajeValidaPedido(Map criteria);
	public void ofertaDevolucion(Map criteria);
	public String mensajeError();
	public String mensajeOferta();
	public List getDetalleMensaje();
	public List getGttOferta(Map criteria);
	public void updateGttOferta(Map criteria);
	

	/**
	 * Realiza Validaciones Iniciales para la Recepcion de CDRS
	 * @param criteria
	 * @return
	 */
	public Map getValidacionInicialRecepcionCDR(Map criteria);
	
	/**
	 * Realiza Validaciones para la Recepcion de CDRS
	 * @param criteria
	 * @return
	 */
	public Map getValidacionRecepcionCDR(Map criteria);
	
	/**
	 * Devuelve Lista de CDRs de la tabla INT_RECEP_CDR_DETAL 
	 * @param criteria
	 * @return
	 */
	public List getListaRecepcionCDRDetalle(Map criteria);
	
	
	/**
	 * Valida Recepcion de CDR con indicador Anulado
	 * @param criteria
	 * @return
	 */
	public Integer getValidacionRecepcionCDRAnuladoDetalle(Map criteria);
	
	/**
	 * Valida Recepcion de CDR con indicador Anulado
	 * @param criteria
	 * @return
	 */
	public Integer getValidacionRecepcionCDRDetalle(Map criteria);
	
	/**
	 * Devuelve Lista de CDRs de la tabla de Reclamos
	 * @param criteria
	 * @return
	 */
	public List getListaRecepcionCDRReclamos(Map criteria);
	
	/**
	 * Devuelve datos del Cliente
	 * @param criteria
	 * @return
	 */
	public Map getOidDatosCliente(Map criteria);
	
	
	/**
	 * Inserta Registro en Cabecera Recepcion CDR
	 * @param criteria
	 */
	public void insertCabeceraRecepcionCDR(Map criteria);
	
	
	/**
	 * Actualiza Registro en Cabecera Recepcion CDR
	 * @param criteria
	 */
	public void updateCabeceraRecepcionCDR(Map criteria);
	
	/**
	 * Elimina Registro en Detalle Recepcion CDR
	 * @param criteria
	 */
	public void deleteDetalleRecepcionCDR(Map criteria);
	
	/**
	 * Inserta Registro en Detalle Recepcion CDR
	 * @param criteria
	 */
	public void insertDetalleRecepcionCDR(Map criteria);
	
	/**
	 * Devuelve Nuevo Valor del Secuencial para el Detalle de Recepcin de CDRs
	 * @param criteria
	 * @return
	 */
	public Long getDevuelveSecuencialDetalleRecepcionCDR(Map criteria);
	
	
	/**
	 * Devuelve datos del Producto ingresado
	 * @param criteria
	 * @return
	 */
	public ProductoAgregacion getDevuelveProductoRecepcionCDR(Map criteria);
	
	/**
	 * Devuelve el indicador de pedido Digitacion boletas recojo
	 * @return
	 */
	public String getIndicadorPGRBR();
	
	/**
	 * Devuelve un mensaje de validacion del tipo de motivo de digitacion de CDR
	 * @param criteria
	 * @return
	 */
	public String getValExcepMotivo(Map criteria);
}
