/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazRETDAO;

/**
 * 
 * <p>
 * <a href="InterfazSABDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 */
@Repository("sisicc.interfazRETDAO")
public class InterfazRETDAOiBatis extends BaseDAOiBatis implements InterfazRETDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazRETDAO#deleteInterfazRETRecepcionarVentasRetail(java.util.Map)
	 */
	public void deleteInterfazRETRecepcionarVentasRetail(Map map) {
		//elimna detalles
		getSqlMapClientTemplate().delete(
				"sisicc.InterfazRETSQL.deleteInterfazRETRecepcionarVentasRetailDetalle", map);
		//elimina las cabeceras
		getSqlMapClientTemplate().delete(
				"sisicc.InterfazRETSQL.deleteInterfazRETRecepcionarVentasRetail", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazRETDAO#executeInterfazRETRecepcionarVentasRetail(java.util.HashMap)
	 */
	public void insertInterfazRETRecepcionarVentasRetail(HashMap map) {
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazRETSQL.insertInterfazRETRecepcionarVentasRetail", map);		
		
	}

	public Integer getExisteCabeceraVentasRetail(HashMap map) {
		return (Integer)getSqlMapClientTemplate().queryForObject("sisicc.InterfazRETSQL.getExisteCabeceraVentasRetail", map);

	}

	public void insertInterfazRETRecepcionarVentasRetailDetalle(HashMap map) {
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazRETSQL.insertInterfazRETRecepcionarVentasRetailDetalle", map);				
	}

	public List getInterfazRETEnviarPagoComisionesRetailGZ(Map params) {
		return (List)getSqlMapClientTemplate().
				queryForList("sisicc.InterfazRETSQL.getInterfazRETEnviarPagoComisionesRetailGZ", 
						params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazRETDAO#executeInterfazRETEnviarInformacionVenta(java.util.Map)
	 */
	public void executeInterfazRETEnviarInformacionVenta(Map params) {
		getSqlMapClientTemplate().
		       queryForList("sisicc.InterfazRETSQL.executeInterfazRETEnviarInformacionVenta",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazRETDAO#executeInterfazRETEnviarDetalleFacturasVD(java.util.Map)
	 */
	public void executeInterfazRETEnviarDetalleFacturasVD(Map params) {
		getSqlMapClientTemplate().queryForList("sisicc.InterfazRETSQL.executeInterfazRETEnviarDetalleFacturasVD",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazRETDAO#executeInterfazRETEnviarInformacionRetail(java.util.Map)
	 */
	public void executeInterfazRETEnviarInformacionRetail(Map params) {
		getSqlMapClientTemplate().queryForList("sisicc.InterfazRETSQL.executeInterfazRETEnviarInformacionRetail",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazRETDAO#executeInterfazRETEnviarInformacionClientesRetail(java.util.Map)
	 */
	@Override
	public void executeInterfazRETEnviarInformacionClientesRetail(Map params) {
		getSqlMapClientTemplate().queryForList("sisicc.InterfazRETSQL.executeInterfazRETEnviarInformacionClientesRetail",params);
	}

}