package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.ProcesoAPESecuenciaClientesDAO;

/**
 * Implementacion del DAO que ejecutara los metodos de secuencia de clientes
 * <p>
 * <a href="ProcesoAPESecuenciaClientesDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:doliva@csigcomt.com">Dennys Oliva Iriarte</a>
 */
@Repository("spusicc.procesoAPESecuenciaClientesDAO")
public class ProcesoAPESecuenciaClientesDAOiBatis extends BaseDAOiBatis implements
	ProcesoAPESecuenciaClientesDAO {
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ProcesoAPESecuenciaClientesDAO#insertSecuenciaClientes(java.util.Map)
	 */
	public void insertSecuenciaClientes(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ape.ProcesosAPESQL.insertSecuenciaClientes", criteria);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ProcesoAPESecuenciaClientesDAO#deleteSecuenciaClientes(java.util.Map)
	 */
	public void deleteSecuenciaClientes(Map criteria) {
        getSqlMapClientTemplate().delete("spusicc.ape.ProcesosAPESQL.deleteSecuenciaClientes", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ProcesoAPESecuenciaClientesDAO#executeProcesoAPECargaRutasCliente(java.util.Map)
	 */
	public void executeProcesoAPECargaRutasCliente(Map queryParams) {
		getSqlMapClientTemplate().update("spusicc.ape.ProcesosAPESQL.executeProcesoAPECargaRutasCliente",queryParams);
	}
}
