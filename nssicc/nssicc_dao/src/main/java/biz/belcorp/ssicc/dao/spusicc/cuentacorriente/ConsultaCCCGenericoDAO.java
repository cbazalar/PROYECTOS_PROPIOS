                                                                             
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;                       
                                                                             
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
                                                                             
/**                                                                          
 * <p>                                                                       
 * <a href="ConsultaCOBGenericoDAO.java.html"> <i>View Source </i> </a>      
 * </p>                                                                      
 *                                                                           
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a> 
 */                                                                          
public interface ConsultaCCCGenericoDAO extends DAO {                        
			                                                                       
	                                                                           
	/**                                                                        
	 * @param codigoParametro                                                  
	 * @return                                                                 
	 */                                                                        
	public String getParametroPais(Map criteria);                              
	                                                                           
	                                                                           
	/**                                                                        
	 * Obtiene el valor del parametro de la tabla CCC_PARAM_GENER	             
	 * @param codigoParametro                                                  
	 * @return                                                                 
	 */                                                                        
	public String getParametroPaisbyCodigo(String codigoParametro);            
	                                                                           
	/**                                                                        
	 * Devuelve los tipos de bloqueo                                           
	 * @param criteria                                                         
	 * @return                                                                 
	 */                                                                        
	public List getTiposBloqueo(Map criteria);	                               
	                                                                           
	/**                                                                        
	 * Obtiene la Lista de Los Tipos del Lote Bancario                         
	 * @return                                                                 
	 */                                                                        
	public List getTiposLoteBancarioList ();                                   
	                                                                           
	/**                                                                        
	 * Obtiene la Lista de Los Estados del Lote Bancario                       
	 * @return                                                                 
	 */                                                                        
	public List getEstadosLoteBancarioList ();                                 
	                                                                           
	/**                                                                        
	 * Obtiene la Lista de los Pagos del Lote Bancario                         
	 * @return                                                                 
	 */                                                                        
	public List getEstadosPagoBancarioList ();                                 
	                                                                           
	/**                                                                        
	 * Obtiene la Lista de los Errores por Pagos Bancarios                     
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
	 * Se recupera el monto del Saldo Total Historico de la Consultora         
	 *                                                                         
	 * @param criteria                                                         
	 * @return                                                                 
	 */                                                                        
	public String getSaldoHistoricoTotal(Map criteria);                        
	                                                                           
	/**                                                                        
	 * Se recupera los tipos de Documento Legal para CAD                       
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
	 * Devuelve un lista con los bancos digitables                             
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
	 * Valida la Existencia de un Cupon 3                                      
	 *                                                                         
	 * @param criteria                                                         
	 * @return                                                                 
	 */                                                                        
	public Integer getExisteCupon(Map criteria);                               
	                                                                           
	/**                                                                        
	 * Devuelve la lista de  los cupones en tramite a depurar                  
	 * @param criteria                                                         
	 * @return                                                                 
	 */                                                                        
	public List getDetalleCuponTramiteDepur(Map criteria);                     
	                                                                           
	                                                                           
	/**                                                                        
	 * Devuelve los tipos de Cargos Directos                                   
	 * @return                                                                 
	 */                                                                        
	public List getTipoCargosDirectos();                                       
	                                                                           
		                                                                         
	/**                                                                        
	 * Devuelve los tipos de Abonos Directos                                   
	 * @return                                                                 
	 */                                                                        
	public List getTipoAbonosDirectos();                                       
	                                                                           
	/**                                                                        
	 * Devuelve los tipos de Cargos Directos                                   
	 * @return                                                                 
	 */                                                                        
	public List getTipoCargosDirectosDigitables();                             
	                                                                           
		                                                                         
	/**                                                                        
	 * Devuelve los tipos de Abonos Directos                                   
	 * @return                                                                 
	 */                                                                        
	public List getTipoAbonosDirectosDigitables();                             
	                                                                           
	/**                                                                        
	 * Devuelve los tipos de origen lotes bancarios                            
	 * @return                                                                 
	 */                                                                        
	public List getTipoOrigenLotesBancarios();                                 
	                                                                           
	/**                                                                        
	 * @param criteria                                                         
	 */                                                                        
	public void generarCabeceraLoteBancario(Map criteria);                     
	                                                                           
                                                                             
	/**                                                                        
	 * @param criteria                                                         
	 * @return                                                                 
	 */                                                                        
	public List getTipoOrigenBanco(Map criteria);                              
	                                                                           
	/**                                                                        
	 * Genera el reporte contable saldos por campa?as.                         
	 *                                                                         
	 * @param criteria the criteria                                            
	 */                                                                        
	public void generarContSaldosCampanias(Map criteria);

	/**
	 * Se recupera el total de factura activa
	 * @param criteria
	 * @return
	 */
	public String getTotalFacturaActiva(Map criteria);
	                                                                           
}                                                                            