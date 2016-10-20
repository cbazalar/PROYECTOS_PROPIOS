package biz.belcorp.ssicc.service.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.LabelValueCDR;
import biz.belcorp.ssicc.dao.model.ReclamoDigitadoCabecera;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;


/**
 * The Interface MantenimientoRECDigitacionCDRService.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 06/12/2013
 */
public interface MantenimientoRECDigitacionCDRService extends Service {

	/**
	 * @param recDigitCabec
	 * @param detallesList
	 * @param usuario
	 * @param indicadorModificar
	 * Inserta el reclamo digitado
	 * @param indicadorOnline 
	 */
	public void insertReclamoDigitado(ReclamoDigitadoCabecera recDigitCabec, List detallesList, Usuario usuario, int indicadorModificar);
	/**
	 * @param criteria
	 * @return
	 * Devuelve las operaciones de Reclamos
	 */
	public List getOperacionesReclamos(Map criteria);
	/**
	 * @param criteria
	 * @return
	 * Devuelve los motivos de Reclamos
	 */
	public List getMotivosReclamos(Map criteria);
	/**
	 * @param criteria
	 * @return
	 * Devuelve el codigo de operacion
	 */
	public String getCodigoOperacion(Map criteria);
	/**
	 * @param params
	 * Ejecutra el proceso de envio de Reclamos digitados
	 */
	public void executeEnviarReclamosDigitados(Map params);
	/**
	 * @param criteria
	 * @return
	 * Devuelve la lista de detalles digitados
	 */
	public List getListaDetallesDigitados(Map criteria);
	/**
	 * @param criteria
	 * @return
	 * Devuelve el indicador Express de la Boleta de recojo
	 */
	public String getIndicadorExpressBoletaRecojo(Map criteria);
	
	/**
	 * @param reclamoDigitCabec
	 * @param detallesList
	 * @param params
	 *  Inserta el Reclamo Digitado Online 
	 */
	public void insertReclamoDigitadoOnline(ReclamoDigitadoCabecera reclamoDigitCabec, List detallesList, Map params);
	
	/**
	 * Obtiene los tipos de operacion con su respectiva parametria
	 * @return
	 */
	public List getParametrosOperacionesReclamos();
	
	/**
	 * Devuelve el indicador de pedido chequeado
	 * @return
	 */
	public String getIndicadorPedidoChequeado();
	
	/**
	 * @return Los datos de la Consultora
	 */
	public LabelValueCDR getConsultoraReclamoByCodigo(Map criteria);

	/**
	 * Obtiene el indicador de Rechazo CDR
	 * @param criteria
	 * @return
	 */
	public String getIndicadorCDRRechazo(Map criteria);
	
	public List getCodigoMotivoRechazo();
	public List getDetalleMensaje();
	public List getGttOferta(Map criteria);
	public void updateGttOferta(Map map);
	
	
	/**
	 * Obtiene valor de la tabla STO_PARAM_GENER_OCCRR
	 * @param criteria
	 * @return
	 */
	public String getValorParam(Map criteria);
	
	
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
	 * Actualiza los datos de Recepcion de CDRSs
	 * @param criteria
	 */
	public void executeInsertUpdateRecepcionCDR(Map criteria) throws Exception;
	
	
	/**
	 * Devuelve Nuevo Valor del Secuencial para el Detalle de Recepción de CDRs
	 * @param criteria
	 * @return
	 */
	public Long getDevuelveSecuencialDetalleRecepcionCDR(Map criteria);
	
	/**
	 * Devuelve el indicador de pedido Digitacion boletas recojo
	 * @return
	 */
	public String getIndicadorPGRBR();
	
	public String getSTOParametroGeneralOCR(Map criteria);

}