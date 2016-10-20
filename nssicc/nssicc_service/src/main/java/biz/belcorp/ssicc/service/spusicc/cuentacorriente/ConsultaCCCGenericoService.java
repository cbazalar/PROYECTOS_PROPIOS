package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaCCCGenericoService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 */
public interface ConsultaCCCGenericoService extends Service {
    	
	/**
	 * Obtiene el valor del parametro de la tabla CCC_PARAM_GENER
	 * @param codigoParametro
	 * @return
	 */
	public String getParametroPais(Map criteria);
	
	/**
	 * Obtiene el valor del parametro de la tabla CCC_PARAM_GENE	
	 * @param codigoParametro
	 * @return
	 */
	public String getParametroPaisbyCodigo(String codigoParametro);
	
	/**
	 * Devuelve la lista de bloqueos
	 * @param criteria
	 * @return
	 */
	public List getTiposBloqueo(Map criteria);	
	
	/**
	 * Obtiene la Lista de los Tipos de Lote Bancario
	 * @return
	 */
	public List getTiposLoteBancarioList ();
	
	/**
	 * Obtiene la Lista de Los Estados del Lote Bancario
	 * @return
	 */
	public List getEstadosLoteBancarioList ();
	
	/**
	 * Obtiene la Lista de Los Estados del Pago Bancario
	 * @return
	 */
	public List getEstadosPagoBancarioList ();
	
	/**
	 * Obtiene la Lista de tipos de eror del Pago Bancario
	 * @return
	 */
	public List getTiposErrorPagoBancarioList ();
	
	/**
	 * Devuelve el detalle de la carga de un lote bancario
	 * @param criteria
	 * @return
	 */
	public List getDetalleCargaLoteBancario(Map criteria);
	
	/**
	 *  Obtiene en el numero de lote
	 * @param criteria
	 */
	public void getNumeroLote(Map criteria);
	
	/**
	 * Se recupera el monto del Saldo unico de la Consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public String getSaldoUnico(Map criteria);
	
	/**
	 * Se recupera el monto del Saldo Vencido de la Consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public String getSaldoVencido(Map criteria);
	
	/**
	 * Se recupera el monto del Saldo historico Total de la Consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public String getSaldoHistoricoTotal(Map criteria);
	
	
	/**
	 * Devuelve los tipos de CAD Documento Legal
	 * 
	 * @param criteria
	 * @return
	 */
	public List getTipoCADDocumentoLegalList(Map criteria);
	
	/**
	 * Devuelve un lista con las cuentas corrientes bancarias
	 * 
	 * @param criteria
	 * @return
	 */
	public List getCuentasCorrientesBancariasList(Map criteria);
	
	/**
	 * Devuelve un lista con las cuentas corrientes bancarias a digitar
	 * 
	 * @param criteria
	 * @return
	 */
	public List getBancosDigitablesList(Map criteria);
	
	/**
	 * Devuelve un lista con las cuentas corrientes bancarias externas
	 * 
	 * @param criteria
	 * @return
	 */
	public List getCuentasCorrientesBancariasExternasList(Map criteria);
		
	/**
	 * @return
	 */
	public List getBancosCheques();
	
	/**
	 * Devuelve un lista con las cuentas corrientes historica de una consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public List getCuentaCorrienteHistoricaConsultoraList(Map criteria);
	
	/**
	 * Devuelve la lista de  los cupones en tramite a depurar
	 * @param criteria
	 * @return
	 */
	public List getDetalleCuponTramiteDepur(Map criteria);
	
	/**
	 * Devuelve la lista de tipos de cargos directos
	 * @return
	 */
	public List getTipoCargosDirectos();
	
	/**
	 * Devuelve la lista de tipos de abonos directos
	 * @return
	 */
	public List getTipoAbonosDirectos();
	
	/**
	 * Devuelve la lista de tipos de cargos directos	 
	 * @return
	 */
	public List getTipoCargosDirectosDigitables();
	
	/**
	 * Devuelve la lista de tipos de abonos directos
	 * @return
	 */
	public List getTipoAbonosDirectosDigitables();
	
	/**
	 * Devuelve la lista de tipos de origen lotes bancarios
	 * @return
	 */
	public List getTipoOrigenLotesBancarios();
	
	/**
	 * Registra el Lote Bancario
	 * @param criteria
	 */
	public void generarCabeceraLoteBancario(Map criteria);
	
	/**
	 * Genera el reporte contable saldos por campaas.
	 *
	 * @param criteria the criteria
	 */
	public void generarContSaldosCampanias(Map criteria) ;
	
	/**
	 * Se recupera el total de factura activa
	 * @param criteria
	 * @return
	 */
	public String getTotalFacturaActiva(Map criteria);
}
