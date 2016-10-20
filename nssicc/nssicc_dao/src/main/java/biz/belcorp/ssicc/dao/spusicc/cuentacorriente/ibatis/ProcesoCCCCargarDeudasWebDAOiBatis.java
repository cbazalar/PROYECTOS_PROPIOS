package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.ocr.model.ConexionOCRWrapper;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCCargarDeudasWebDAO;

/**
 * Implementacion del DAO que ejecutara la actualizacion de las deudas hacia IPM
 * <p>
 * <a href="ProcesoCCCCargarDeudasWebDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz">JFA</a>
 */
@Repository("spusicc.procesoCCCCargarDeudasWebDAO")
public class ProcesoCCCCargarDeudasWebDAOiBatis extends BaseDAOiBatis implements ProcesoCCCCargarDeudasWebDAO {

			
	public void executeGeneraNovedadesDeudasWeb(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.cuentacorriente.procesosCCCSQL.executeGeneraNovedadesDeudasWeb", criteria);
	}	
		
	public List getListInsertDeudasWeb(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getListInsertDeudasWeb",criteria);
	}
	
	public List getListUpdateDeudasWeb(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getListUpdateDeudasWeb",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.ocr.dao.gen.GenericoOCRComercialDAO#getListProcesoCargaCupon(biz.belcorp.ssicc.ocr.dao.model.ConexionOCRWrapper, java.util.Map)
	 */
	public void insertDeudasWeb(ConexionOCRWrapper conexionOCRWrapper,
			Map params) throws Exception {
		//Connection connection = this.obtenerConexion(conexionOCRWrapper);

		List listDeudas = null;
		DriverManagerDataSource ds = new DriverManagerDataSource();
		try {
			
			ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
			ds.setUrl(conexionOCRWrapper.getHost());
			ds.setUsername(conexionOCRWrapper.getUsuario());
			ds.setPassword(conexionOCRWrapper.getPassword());
			setDataSource(ds);

			listDeudas = getSqlMapClientTemplate().queryForList(
						"spusicc.cuentacorriente.procesosCCCSQL.insertDeudaWeb",
						params);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
		finally {
			ds=null;
		}
	}
	
	public void updateDeudasWeb(ConexionOCRWrapper conexionOCRWrapper,
			Map params) throws Exception {
		//Connection connection = this.obtenerConexion(conexionOCRWrapper);

		List listDeudas = null;
		DriverManagerDataSource ds = new DriverManagerDataSource();
		try {
			
			ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
			ds.setUrl(conexionOCRWrapper.getHost());
			ds.setUsername(conexionOCRWrapper.getUsuario());
			ds.setPassword(conexionOCRWrapper.getPassword());
			setDataSource(ds);

			listDeudas = getSqlMapClientTemplate().queryForList(
						"spusicc.cuentacorriente.procesosCCCSQL.updateDeudaWeb",
						params);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
		finally {
			ds=null;
		}
	}
		
	
	
}
