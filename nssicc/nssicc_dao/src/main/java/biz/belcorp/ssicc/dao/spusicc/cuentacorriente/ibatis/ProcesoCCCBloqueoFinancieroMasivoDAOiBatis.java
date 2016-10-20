/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCBloqueoFinancieroMasivoDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.EstructuraBloqueoFinancieroMasivo;

/**
 * @author pejflorencio
 *
 */
@Repository("spusicc.procesoCCCBloqueoFinancieroMasivoDAO")
public class ProcesoCCCBloqueoFinancieroMasivoDAOiBatis extends	BaseDAOiBatis implements ProcesoCCCBloqueoFinancieroMasivoDAO {
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarBloqueoFinancieroMasivoDAO#obtenerPathUploadBloqueoFinancieroMasivo(java.util.Map)
	 */
	public String obtenerPathUploadBloqueoFinancieroMasivo(Map datos) {
		return (String) getSqlMapClientTemplate().
		queryForObject("spusicc.cuentacorriente.procesosCCCSQL.getPathUploadBloqueoFinancieroMasivo", datos);		
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarBloqueoFinancieroMasivoDAO#insertEstructuraBloqueoFinancieroMasivo(biz.belcorp.ssicc.spusicc.cuentacorriente.model.EstructuraCADMasivos)
	 */
	public void insertEstructuraBloqueoFinancieroMasivo(EstructuraBloqueoFinancieroMasivo estructura){
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.insertEstructuraBloqueoFinancieroMasivo", estructura);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarBloqueoFinancieroMasivoDAO#deleteTablasCargaBloqueoFinancieroMasivo(java.util.Map)
	 */
	public void deleteTablasBloqueoFinancieroMasivo(Map datos) {		
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.deleteTablasBloqueoFinancieroMasivo", datos);		
	}
		
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCBloqueoFinancieroMasivoDAO#executeValidarBloqueoFinancieroMasivo(java.util.Map)
	 */
	public void executeValidarBloqueoFinancieroMasivo(Map criteria){
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeValidarBloqueoFinancieroMasivo", criteria);
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCBloqueoFinancieroMasivoDAO#getErroresBloqueoFinancieroMasivo(java.util.Map)
	 */
	public List getErroresBloqueoFinancieroMasivo(Map datos){
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getErroresBloqueoFinancieroMasivo",datos);
	}
		
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCBloqueoFinancieroMasivoDAO#executeProcesarBloqueoFinancieroMasivo(java.util.Map)
	 */
	public void executeProcesarBloqueoFinancieroMasivo(Map datos){
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeProcesarBloqueoFinancieroMasivo", datos);
	}
}
