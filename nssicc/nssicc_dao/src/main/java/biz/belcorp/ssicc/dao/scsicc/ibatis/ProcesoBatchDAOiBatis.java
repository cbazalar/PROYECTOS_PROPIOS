/*
 * Created on 20/09/2006 09:28:01 PM
 * biz.belcorp.ssicc.sisicc.dao.ibatis.Interfaz2DAOiBatis
 */
package biz.belcorp.ssicc.dao.scsicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scsicc.ProcesoBatchDAO;
import biz.belcorp.ssicc.dao.sisicc.model.NroLoteMultiHilo;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatch;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoBatchDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar </a>
 */
@Repository("scsicc.procesoBatchDAO")
public class ProcesoBatchDAOiBatis extends BaseDAOiBatis implements ProcesoBatchDAO {
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ProcesoBatchDAO#getSecuenciaSiguienteProcesoBatchActu()
	 */
	public Long getSecuenciaSiguienteProcesoBatchActu() {
		Long valor = (Long) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConfiguracionInterfaz2SQL.getSecuenciaSiguienteProcesoBatchActu", null);
		return valor;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ProcesoBatchDAO#getProcesoBatchByCriteria(java.util.Map)
	 */
	public List getProcesoBatchByCriteria(Map criteria) {
		List procesos = getSqlMapClientTemplate().queryForList(
				"sisicc.ConfiguracionInterfaz2SQL.getProcesoBatchByCriteria",
				criteria);
		return procesos;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.Interfaz2DAO#getProcesoBatchActuByCriteria(java.util.Map)
	 */
	public List getProcesoBatchActuByCriteria(Map criteria) {
		List procesos = getSqlMapClientTemplate().queryForList(
				"sisicc.ConfiguracionInterfaz2SQL.getProcesoBatchActuByCriteria",
				criteria);
		return procesos;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ProcesoBatchDAO#getObtieneNroLoteProcesoBatchActu(java.util.Map)
	 */
	public List<NroLoteMultiHilo> getObtieneNroLoteProcesoBatchActu(Map criteria) {
		List queryForList = getSqlMapClientTemplate().queryForList(
				"sisicc.ConfiguracionInterfaz2SQL.getObtieneNroLoteProcesoBatchActu",
				criteria);
		List<NroLoteMultiHilo> procesos = queryForList;
		return procesos;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ProcesoBatchDAO#getProcesoBatchActuByEstado(java.util.Map)
	 */
	public ProcesoBatch getProcesoBatchActuByEstado(Map criteria) {
		ProcesoBatch proceso = (ProcesoBatch) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConfiguracionInterfaz2SQL.getProcesoBatchActuByEstado",
				criteria);
		return proceso;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.Interfaz2DAO#getProcesoBatchHistoByCriteria(java.util.Map)
	 */
	public List getProcesoBatchHistoByCriteria(Map criteria) {
		List procesos = getSqlMapClientTemplate().queryForList(
				"sisicc.ConfiguracionInterfaz2SQL.getProcesoBatchHistoByCriteria",
				criteria);
		return procesos;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.Interfaz2DAO#insertProcesoBatchActu(java.util.Map)
	 */
    public void insertProcesoBatchActu(Map criteria, Usuario usuario) {
    	getSqlMapClientTemplate().insert("sisicc.ConfiguracionInterfaz2SQL.insertProcesoBatchActu",
				criteria);
	} 
    
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.scsicc.ProcesoBatchDAO#deleteProcesoBatchActu(java.util.Map, biz.belcorp.ssicc.dao.model.Usuario)
     */
    public void deleteProcesoBatchActu(Map criteria, Usuario usuario) {
    	getSqlMapClientTemplate().delete("sisicc.ConfiguracionInterfaz2SQL.deleteProcesoBatchActu",
				criteria);
	} 
    
    /*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.Interfaz2DAO#updateProcesoBatchActu(java.util.Map)
	 */    
	public void updateProcesoBatchActu(Map criteria, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.ConfiguracionInterfaz2SQL.updateProcesoBatchActu",
				criteria);		
	}
	
	public void updateProcesoBatchActuLote(Map criteria, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.ConfiguracionInterfaz2SQL.updateProcesoBatchActuLote",
				criteria);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ProcesoBatchDAO#updateProcesoBatchEtapaProceso(java.util.Map, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateProcesoBatchEtapaProceso(Map criteria, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.ConfiguracionInterfaz2SQL.updateProcesoBatchEtapaProceso",
				criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ProcesoBatchDAO#updateProcesoBatchRecomendacion(java.util.Map, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateProcesoBatchRecomendacion(Map criteria, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.ConfiguracionInterfaz2SQL.updateProcesoBatchRecomendacion",
				criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ProcesoBatchDAO#executeProcesoBatchActuRecomendacionError(java.util.Map)
	 */
	public void executeProcesoBatchActuRecomendacionError(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ConfiguracionInterfaz2SQL.executeProcesoBatchActuRecomendacionError",
				criteria);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.Interfaz2DAO#updateEstadoProcesoBatchActu(java.util.Map)
	 */    
	public void updateEstadoProcesoBatchActu(Map criteria, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.ConfiguracionInterfaz2SQL.updateEstadoProcesoBatchActu",
				criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ProcesoBatchDAO#getProcesoBatchActuDependientesByCriteria(java.util.Map)
	 */
	public List getProcesoBatchActuDependientesByCriteria(Map criteria) {
		
		return getSqlMapClientTemplate().queryForList("sisicc.ConfiguracionInterfaz2SQL.getProcesoBatchActuDependientesByCriteria",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ProcesoBatchDAO#getParametrosProcesoBatch(java.util.Map)
	 */
	public List getParametrosProcesoBatch(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConfiguracionInterfaz2SQL.getParametrosProcesoBatch",criteria);
	}
}
