/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazSAMDAO;
/**
 * 
 * <p>
 * <a href="InterfazSAMDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@csigcomt.com">Cristhian Roman</a>
 * 
 */
@Repository("sisicc.interfazSAMDAO")
public class InterfazSAMDAOiBatis extends BaseDAOiBatis implements	InterfazSAMDAO {


	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAMDAO#executeInterfazSAMEnviarMovimientosAlmacen(java.util.Map)
	 */
	public void  executeInterfazSAMEnviarMovimientosAlmacen(Map criteria){
		getSqlMapClientTemplate().update("sisicc.InterfazSAMSQL.executeInterfazSAMEnviarMovimientosAlmacen",criteria);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAMDAO#getNumeroMovimiento(java.util.Map)
	 */
	public String getNumeroMovimiento(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.InterfazSAMSQL.getNumeroMovimiento", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAMDAO#getNumeroRegistrosInterfazStock(java.util.Map)
	 */
	public List getNumeroRegistrosInterfazStock(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.InterfazSAMSQL.getNumeroRegistrosInterfazStock", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAMDAO#deleteInterfazSAMRecepcionarProductosNacionalesImportados()
	 */
	public void deleteInterfazSAMRecepcionarProductosNacionalesImportados(){
		getSqlMapClientTemplate().delete("sisicc.InterfazSAMSQL.deleteInterfazSAMRecepcionarProductosNacionalesImportados");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAMDAO#executeInterfazSAMRecepcionarProductosNacionalesImportados(java.util.Map)
	 */
	public void executeInterfazSAMRecepcionarProductosNacionalesImportados(Map map){
		getSqlMapClientTemplate().update("sisicc.InterfazSAMSQL.executeInterfazSAMRecepcionarProductosNacionalesImportados",map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAMDAO#executeInterfazSAMEnviarMovimientosAlmacenSicc(java.util.Map)
	 */
	public void  executeInterfazSAMEnviarMovimientosAlmacenSicc(Map criteria){
		getSqlMapClientTemplate().update("sisicc.InterfazSAMSQL.executeInterfazSAMEnviarMovimientosAlmacenSicc",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAMDAO#getUltimoNumeroMovimiento(java.util.Map)
	 */
	public String getUltimoNumeroMovimiento(Map criteria){
		getSqlMapClientTemplate().update("sisicc.InterfazSAMSQL.executeUltimoNumeroMovimiento",criteria);
		return (String)criteria.get("numeroMovimiento");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAMDAO#getSecuencialMovimientoAlmacen(java.util.Map)
	 */
	public String getSecuencialMovimientoAlmacen(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.InterfazSAMSQL.getSecuencialMovimientoAlmacen", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAMDAO#insertMovimientoAlmacenCabecera(java.util.Map)
	 */
	public void insertMovimientoAlmacenCabecera(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAMSQL.insertMovimientoAlmacenCabecera", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAMDAO#insertMovimientoAlmacenDetalle(java.util.Map)
	 */
	public void insertMovimientoAlmacenDetalle(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAMSQL.insertMovimientoAlmacenDetalle", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAMDAO#executeInterfazSAMEnviarReservaPROL(java.util.Map)
	 */
	public void  executeInterfazSAMEnviarReservaPROL(Map criteria){
		getSqlMapClientTemplate().update("sisicc.InterfazSAMSQL.executeInterfazSAMEnviarReservaPROL",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAMDAO#executeInterfazSAMEnviarCantidadProducto(java.util.Map)
	 */
	public void executeInterfazSAMEnviarCantidadProducto(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAMSQL.executeInterfazSAMEnviarCantidadProducto",params);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAMDAO#executeRecepcionLotesProducto(java.util.Map)
	 */
	public void executeRecepcionLotesProducto(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAMSQL.executeRecepcionLotesProducto",params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAMDAO#deleteInterfazSAMRecepcionarStockMAV(java.util.Map)
	 */
	public void deleteInterfazSAMRecepcionarStockMAV(Map params) {
		getSqlMapClientTemplate().delete("sisicc.InterfazSAMSQL.deleteInterfazSAMRecepcionarStockMAV",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAMDAO#insertInterfazSAMRecepcionarStockMAV(java.util.Map)
	 */
	public void insertInterfazSAMRecepcionarStockMAV(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAMSQL.insertInterfazSAMRecepcionarStockMAV", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAMDAO#getOidAlmacenMAV(java.lang.String)
	 */
	public String getOidAlmacenMAV(String codigoPais) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.InterfazSAMSQL.getOidAlmacenMAV", codigoPais);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAMDAO#executeRecepcionNegocio(java.util.Map)
	 */
	public void executeRecepcionNegocio(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAMSQL.executeRecepcionNegocio",params);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAMDAO#executeRecepcionSuperGenerico(java.util.Map)
	 */
	public void executeRecepcionSuperGenerico(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAMSQL.executeRecepcionSuperGenerico",params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAMDAO#executeRecepcionGenerico(java.util.Map)
	 */
	public void executeRecepcionGenerico(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAMSQL.executeRecepcionGenerico",params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAMDAO#executeRecepcionDescripcionProductoIdioma(java.util.Map)
	 */
	public void executeRecepcionDescripcionProductoIdioma(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAMSQL.executeRecepcionDescripcionProductoIdioma",params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAMDAO#executeRecepcionProducto(java.util.Map)
	 */
	public void executeRecepcionProducto(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAMSQL.executeRecepcionProducto",params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAMDAO#executeInterfazSAMEnviarMovimientosAlmacenColombia(java.util.Map)
	 */
	public void executeInterfazSAMEnviarMovimientosAlmacenColombia(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAMSQL.executeInterfazSAMEnviarMovimientosAlmacenColombia",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAMDAO#executeInterfazSAMEnviarMovimientosAlmacenSicc(java.util.Map)
	 */
	public void  executeInterfazSAMEnviarMovimientosAlmacenSiccColombia(Map criteria){
		getSqlMapClientTemplate().update("sisicc.InterfazSAMSQL.executeInterfazSAMEnviarMovimientosAlmacenSiccColombia",criteria);
	}
}