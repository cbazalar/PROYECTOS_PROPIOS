/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazDIRDAO;

/**
 * Implementacin del DAO para la recepcion de maestros del directorio corporativo
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
@Repository("sisicc.interfazDIRDAO")
public class InterfazDIRDAOiBatis extends BaseDAOiBatis implements InterfazDIRDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDIRDAO#insertRegion(java.util.HashMap)
	 */
	public void insertRegion(HashMap criteria) {
		getSqlMapClientTemplate().insert("sisicc.InterfazDIRSQL.insertRegion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDIRDAO#updateRegion(java.util.HashMap)
	 */
	public void updateRegion(HashMap criteria) {
		getSqlMapClientTemplate().update("sisicc.InterfazDIRSQL.updateRegion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDIRDAO#verificarRegionExiste(java.util.HashMap)
	 */
	public int verificarRegionExiste(HashMap criteria) {
		Integer valor = (Integer)getSqlMapClientTemplate().queryForObject("sisicc.InterfazDIRSQL.verificarRegionExiste", criteria);
		
		if(valor == null)
			return 0;
		else
			return valor.intValue();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDIRDAO#insertZona(java.util.HashMap)
	 */
	public void insertZona(HashMap criteria) {
		getSqlMapClientTemplate().insert("sisicc.InterfazDIRSQL.insertZona", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDIRDAO#updateZona(java.util.HashMap)
	 */
	public void updateZona(HashMap criteria) {
		getSqlMapClientTemplate().update("sisicc.InterfazDIRSQL.updateZona", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDIRDAO#verificarZonaExiste(java.util.HashMap)
	 */
	public int verificarZonaExiste(HashMap criteria) {
		Integer valor = (Integer)getSqlMapClientTemplate().queryForObject("sisicc.InterfazDIRSQL.verificarZonaExiste", criteria);
		
		if(valor == null)
			return 0;
		else
			return valor.intValue();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDIRDAO#insertControlFacturacion(java.util.HashMap)
	 */
	public void insertControlFacturacion(HashMap criteria) {
		getSqlMapClientTemplate().insert("sisicc.InterfazDIRSQL.insertControlFacturacion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDIRDAO#updateControlFacturacion(java.util.HashMap)
	 */
	public void updateControlFacturacion(HashMap criteria) {
		getSqlMapClientTemplate().update("sisicc.InterfazDIRSQL.updateControlFacturacion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDIRDAO#verificarControlFacturacionExiste(java.util.HashMap)
	 */
	public int verificarControlFacturacionExiste(HashMap criteria) {
		Integer valor = (Integer)getSqlMapClientTemplate().queryForObject("sisicc.InterfazDIRSQL.verificarControlFacturacionExiste", criteria);
		
		if(valor == null)
			return 0;
		else
			return valor.intValue();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDIRDAO#insertCampanya(java.util.HashMap)
	 */
	public void insertCampanya(HashMap criteria) {
		getSqlMapClientTemplate().insert("sisicc.InterfazDIRSQL.insertCampanya", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDIRDAO#updateCampanya(java.util.HashMap)
	 */
	public void updateCampanya(HashMap criteria) {
		getSqlMapClientTemplate().update("sisicc.InterfazDIRSQL.updateCampanya", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDIRDAO#verificarCampanyaExiste(java.util.HashMap)
	 */
	public int verificarCampanyaExiste(HashMap criteria) {
		Integer valor = (Integer)getSqlMapClientTemplate().queryForObject("sisicc.InterfazDIRSQL.verificarCampanyaExiste", criteria);
		
		if(valor == null)
			return 0;
		else
			return valor.intValue();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDIRDAO#deleteInterfazDIRRecepcionarClientesTemporal()
	 */
	public void deleteInterfazDIRRecepcionarClientesTemporal() {
		getSqlMapClientTemplate().update("sisicc.InterfazDIRSQL.deleteInterfazDIRRecepcionarClientesTemporal", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDIRDAO#insertInterfazDIRRecepcionarClientesTemporal(java.util.Map)
	 */
	public void insertInterfazDIRRecepcionarClientesTemporal(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazDIRSQL.insertInterfazDIRRecepcionarClientesTemporal", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDIRDAO#executeInterfazDIRRecepcionarClientes(java.util.Map)
	 */
	public void executeInterfazDIRRecepcionarClientes(Map parms) {
		getSqlMapClientTemplate().update("sisicc.InterfazDIRSQL.executeInterfazDIRRecepcionarClientes", parms);
	}
	
}
