package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.ocr.model.ConexionOCRWrapper;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCRecepcionarPagosWebDAO;

/**
 * Implementacion del DAO que ejecutara la Generacion de Cuenta Corriente por Documento Legal
 * <p>
 * <a href="ProcesoCCCRecepcionarPagosWebDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz">JFA</a>
 */
@Repository("spusicc.procesoCCCRecepcionarPagosWebDAO")
public class ProcesoCCCRecepcionarPagosWebDAOiBatis extends BaseDAOiBatis implements ProcesoCCCRecepcionarPagosWebDAO {

	
	
	public Integer getUltimoOidPagoWeb(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"spusicc.cuentacorriente.procesosCCCSQL.getUltimoOidPagoWeb",criteria);
	}
	
	public void updateUltimoOidPagoWeb(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.cuentacorriente.procesosCCCSQL.updateUltimoOidPagoWeb", criteria);
	}
	
		
	public List getListDeudasWeb(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getListDeudasWeb",criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.ocr.dao.gen.GenericoOCRComercialDAO#getListProcesoCargaCupon(biz.belcorp.ssicc.ocr.dao.model.ConexionOCRWrapper, java.util.Map)
	 */
	public List getListPagosWeb(ConexionOCRWrapper conexionOCRWrapper,
			Map params) throws Exception {
		//Connection connection = this.obtenerConexion(conexionOCRWrapper);

		List listPagos = null;
		DriverManagerDataSource ds = new DriverManagerDataSource();
		try {
			
			ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
			ds.setUrl(conexionOCRWrapper.getHost());
			ds.setUsername(conexionOCRWrapper.getUsuario());
			ds.setPassword(conexionOCRWrapper.getPassword());
			setDataSource(ds);

			listPagos = getSqlMapClientTemplate().queryForList(
						"spusicc.cuentacorriente.procesosCCCSQL.getListPagosWeb",
						params);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
		finally {
			ds=null;
		}
		return listPagos;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCBloqueoFinancieroPorDiasDeAtrasoDAO#executeBloqueoFinancieroPorDiasDeAtraso(java.util.Map)
	 */
	public void insertPagoWeb(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.cuentacorriente.procesosCCCSQL.insertPagoWeb", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCRecepcionarPagosWebDAO#executeCargaPagosBancoLinea(java.util.Map)
	 */
	public void executeCargaPagosBancoLinea(Map params) {
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeCargaPagosBancoLinea", params);
	}
}
