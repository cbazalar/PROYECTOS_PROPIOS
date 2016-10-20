package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.gen.ibatis;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.ocr.model.ConexionOCRWrapper;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.gen.GenericoCCCComercialDAO;

/**
 * ImplementacionDAO correspondiente al OCR Comercial proveniente de SQL Server
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 */
@Repository("spusicc.genericoCCCComercialSQLServerDAOiBatis")
public class GenericoCCCComercialSQLServerDAOiBatis extends BaseDAOiBatis
		implements GenericoCCCComercialDAO {

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.ocr.dao.gen.GenericoOCRComercialDAO#getListProcesoCargaCupon(biz.belcorp.ssicc.ocr.dao.model.ConexionOCRWrapper, java.util.Map)
	 */
	public List getListPagosWeb(ConexionOCRWrapper conexionOCRWrapper,
			Map params) throws Exception {
		//Connection connection = this.obtenerConexion(conexionOCRWrapper);

		List listCupon= null;
		DriverManagerDataSource ds = new DriverManagerDataSource();
		try {
			
			ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
			ds.setUrl(conexionOCRWrapper.getHost());
			ds.setUsername(conexionOCRWrapper.getUsuario());
			ds.setPassword(conexionOCRWrapper.getPassword());
			setDataSource(ds);

			listCupon = getSqlMapClientTemplate().queryForList(
						"spusicc.cuentacorriente.procesosCCCSQL.getListPagosWeb",
						params);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
		finally {
			ds=null;
		}
		return listCupon;
	}
	
	public void deleteDeudasWeb(ConexionOCRWrapper conexionOCRWrapper, Map params) throws Exception{
		DriverManagerDataSource ds = new DriverManagerDataSource();
		try {
			
			ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
			ds.setUrl(conexionOCRWrapper.getHost());
			ds.setUsername(conexionOCRWrapper.getUsuario());
			ds.setPassword(conexionOCRWrapper.getPassword());
			setDataSource(ds);

			getSqlMapClientTemplate().update(
						"spusicc.cuentacorriente.procesosCCCSQL.deleteDeudaWeb",
						params);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
		finally {
			ds=null;
		}
	}
	
	public void insertDeudaWeb(ConexionOCRWrapper conexionOCRWrapper,
			Map params) throws Exception {
		

		DriverManagerDataSource ds = new DriverManagerDataSource();
		try {
			
			ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
			ds.setUrl(conexionOCRWrapper.getHost());
			ds.setUsername(conexionOCRWrapper.getUsuario());
			ds.setPassword(conexionOCRWrapper.getPassword());
			setDataSource(ds);
			

			List listInsertDeudasWeb = (List)params.get("listInsertDeudasWeb");
			Iterator it1 = listInsertDeudasWeb.iterator();
			int cont1 = 0;
			while(it1.hasNext()){
				Map map = (Map)it1.next();
				
				log.debug("Insercion : " + cont1);
				
				params.put("codigoConsultora", map.get("codigoConsultora"));
				params.put("nombreConsultora", map.get("nombreConsultora"));
				params.put("montoDeuda", map.get("montoDeuda"));			
			
	                       getSqlMapClientTemplate().update(
						"spusicc.cuentacorriente.procesosCCCSQL.deleteDeudaWeb",
						params);	

				getSqlMapClientTemplate().update(
						"spusicc.cuentacorriente.procesosCCCSQL.insertDeudaWeb",
						params);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
		finally {
			ds=null;
		}		
	}
	
	public void updateDeudaWeb(ConexionOCRWrapper conexionOCRWrapper,
			Map params) throws Exception {
		

		DriverManagerDataSource ds = new DriverManagerDataSource();
		try {
			
			ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
			ds.setUrl(conexionOCRWrapper.getHost());
			ds.setUsername(conexionOCRWrapper.getUsuario());
			ds.setPassword(conexionOCRWrapper.getPassword());
			setDataSource(ds);

				getSqlMapClientTemplate().update(
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
