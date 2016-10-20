/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;


import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazLARDAO;


/**
 * 
 * <p>
 * <a href="InterfazLARDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 */

@Repository("sisicc.interfazLARDAO")
public class InterfazLARDAOiBatis extends BaseDAOiBatis implements InterfazLARDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#executeInterfazLAREnvioEstimadosYobel(java.util.Map)
	 */
	public void executeInterfazLAREnvioEstimadosYobel(Map params) {
		getSqlMapClientTemplate().
		update("sisicc.InterfazLARSQL.executeInterfazLAREnvioEstimadosYobel",params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#executeInterfazLAREnvioDocumentosCabeceraComplemento(java.util.Map)
	 */
	public void executeInterfazLAREnvioDocumentosCabeceraComplemento(Map params) {
		getSqlMapClientTemplate().
		update("sisicc.InterfazLARSQL.executeInterfazLAREnvioDocumentosCabeceraComplemento",params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#deleteInterfazLARRecepcionarEstimadosLeader(java.lang.String)
	 */
	public void deleteInterfazLARRecepcionarEstimadosLeader(String codigoPeriodo) {
		getSqlMapClientTemplate().delete(
				"sisicc.InterfazLARSQL.deleteInterfazLARRecepcionarEstimadosLeader", codigoPeriodo);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#insertInterfazLARRecepcionarEstimadosLeader(java.util.Map)
	 */
	public void insertInterfazLARRecepcionarEstimadosLeader(Map params) {
		getSqlMapClientTemplate().
		insert("sisicc.InterfazLARSQL.insertInterfazLARRecepcionarEstimadosLeader",params);		
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#executeGenerarPedidosChequear(java.util.Map)
	 */
	public void executeGenerarPedidosChequear(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLARSQL.executeGenerarPedidosChequear",params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#executeInterfazLAREnviarTipoChequeo(java.util.Map)
	 */
	public void executeInterfazLAREnviarTipoChequeo(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLARSQL.executeInterfazLAREnviarTipoChequeo",params);
		
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#executeInterfazLAREnviarOrigenChequeo(java.util.Map)
	 */
	public void executeInterfazLAREnviarOrigenChequeo(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLARSQL.executeInterfazLAREnviarOrigenChequeo",params);
		
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#executeInterfazLAREnviarResultadoChequeo(java.util.Map)
	 */
	public void executeInterfazLAREnviarResultadoChequeo(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLARSQL.executeInterfazLAREnviarResultadoChequeo",params);
		
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#executeInterfazLAREnviarPedidosChequear(java.util.Map)
	 */
	public void executeInterfazLAREnviarPedidosChequear(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLARSQL.executeInterfazLAREnviarPedidosChequear",params);
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#executeActualizarPedidosChequear(java.util.Map)
	 */
	public void executeActualizarPedidosChequear(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazLARSQL.executeActualizarPedidosChequear",params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#deleteInterfazLARResultadoChequeoCabecera()
	 */
	public void deleteInterfazLARResultadoChequeoCabecera() {
		getSqlMapClientTemplate()
		.delete("sisicc.InterfazLARSQL.deleteInterfazLARResultadoChequeoCabecera",null);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#executeInterfazLARResultadoChequeoCabecera(java.util.Map)
	 */
	public void executeInterfazLARResultadoChequeoCabecera(Map params) {
		getSqlMapClientTemplate()
		.update("sisicc.InterfazLARSQL.executeInterfazLARResultadoChequeoCabecera",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#deleteInterfazLARResultadoChequeoDetalle()
	 */
	public void deleteInterfazLARResultadoChequeoDetalle() {
		getSqlMapClientTemplate()
		.delete("sisicc.InterfazLARSQL.deleteInterfazLARResultadoChequeoDetalle",null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#executeInterfazLARResultadoChequeoDetalle(java.util.Map)
	 */
	public void executeInterfazLARResultadoChequeoDetalle(Map params) {
		getSqlMapClientTemplate()
		.update("sisicc.InterfazLARSQL.executeInterfazLARResultadoChequeoDetalle",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#executeResultadoChequeo(java.util.Map)
	 */
	public void executeResultadoChequeo(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLARSQL.executeResultadoChequeo",params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#executeInterfazLAREnvioLAR1(java.util.Map)
	 */
	public void executeInterfazLAREnvioLAR1(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazLARSQL.executeInterfazLAREnvioLAR1",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#executeInterfazLAREnvioLAR2(java.util.Map)
	 */
	public void executeInterfazLAREnvioLAR2(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazLARSQL.executeInterfazLAREnvioLAR2",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#executeInterfazLAREnvioLAR3(java.util.Map)
	 */
	public void executeInterfazLAREnvioLAR3(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazLARSQL.executeInterfazLAREnvioLAR3",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#executeInterfazLAREnvioLAR4(java.util.Map)
	 */
	public void executeInterfazLAREnvioLAR4(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazLARSQL.executeInterfazLAREnvioLAR4",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#executeInterfazLAREnvioLAR5(java.util.Map)
	 */
	public void executeInterfazLAREnvioLAR5(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazLARSQL.executeInterfazLAREnvioLAR5",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#executeInterfazLAREnvioLAR7Cabecera(java.util.Map)
	 */
	public void executeInterfazLAREnvioLAR7Cabecera(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazLARSQL.executeInterfazLAREnvioLAR7Cabecera",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#executeInterfazLAREnvioLAR7Detalle(java.util.Map)
	 */
	public void executeInterfazLAREnvioLAR7Detalle(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazLARSQL.executeInterfazLAREnvioLAR7Detalle",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#executeInterfazLAREnvioLAR9(java.util.Map)
	 */
	public void executeInterfazLAREnvioLAR9(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazLARSQL.executeInterfazLAREnvioLAR9",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#executeInterfazLAREnvioLAR10(java.util.Map)
	 */
	public void executeInterfazLAREnvioLAR10(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazLARSQL.executeInterfazLAREnvioLAR10",params);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#updateLARDocumentosCabeceraComplemento(java.util.Map)
	 */
	public void updateLARDocumentosCabeceraComplemento(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazLARSQL.updateLARDocumentosCabeceraComplemento",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#executeInterfazLAREnvioLAR11(java.util.Map)
	 */
	public void executeInterfazLAREnvioLAR11(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLARSQL.executeInterfazLAREnvioLAR11",params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#executeInterfazLAREnvioLAR8(java.util.Map)
	 */
	public void executeInterfazLAREnvioLAR8(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLARSQL.executeInterfazLAREnvioLAR8",params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#deleteInterfazLAR8Parametros()
	 */
	public void deleteInterfazLAR8Parametros() {
		getSqlMapClientTemplate()
		.delete("sisicc.InterfazLARSQL.deleteInterfazLAR8Parametros",null);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#insertInterfazLAR8Parametros(java.util.Map)
	 */
	public void insertInterfazLAR8Parametros(Map params) {
		getSqlMapClientTemplate().
		insert("sisicc.InterfazLARSQL.insertInterfazLAR8Parametros",params);			
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#executeInterfazLAREnviarArchivoTIS2(java.util.Map)
	 */
	public void executeInterfazLAREnviarArchivoTIS2(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLARSQL.executeInterfazLAREnviarArchivoTIS2",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLARDAO#existeHistoricoInterfazError(java.util.Map)
	 */
	public boolean existeHistoricoInterfazError(Map criteria) {
		Integer result = (Integer) getSqlMapClientTemplate().queryForObject(
        							"sisicc.InterfazLARSQL.getExisteHistoricoInterfazError", criteria);
        
        if(result.intValue()>0)
        	return true;
        else
        	return false;
    }
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.sisicc.InterfazLARDAO#getExistelARControlCargarInformacionTracking(java.util.Map)
     */
    public Integer getExisteLARControlCargarInformacionTracking(Map criteria) {
    	Integer result = (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazLARSQL.getExisteLARControlCargarInformacionTracking", criteria);
    	return result;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazLARDAO#insertLARControlCargarInformacionTracking(java.util.Map)
	 */
	public void insertLARControlCargarInformacionTracking(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.InterfazLARSQL.insertLARControlCargarInformacionTracking",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazLARDAO#updateLARControlCargarInformacionTracking(java.util.Map)
	 */
	public void updateLARControlCargarInformacionTracking(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.InterfazLARSQL.updateLARControlCargarInformacionTracking",criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazLARDAO#executeLARCargarInformacionTracking(java.util.Map)
	 */
	public void executeLARCargarInformacionTracking(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.InterfazLARSQL.executeLARCargarInformacionTracking",criteria);
	}
	
	
}