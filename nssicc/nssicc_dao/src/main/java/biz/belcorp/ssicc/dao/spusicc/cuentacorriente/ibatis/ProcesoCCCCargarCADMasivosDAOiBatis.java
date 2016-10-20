/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCCargarCADMasivosDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.EstructuraCADMasivos;

/**
 * @author pejflorencio
 *
 */
@Repository("spusicc.procesoCCCCargarCADMasivosDAO")
public class ProcesoCCCCargarCADMasivosDAOiBatis extends	BaseDAOiBatis implements ProcesoCCCCargarCADMasivosDAO {
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarCADMasivosDAO#obtenerPathUpload(java.util.Map)
	 */
	public String obtenerPathUpload(Map datos) {
		return (String) getSqlMapClientTemplate().
		queryForObject("spusicc.cuentacorriente.procesosCCCSQL.getPathUpload", datos);		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarCADMasivosDAO#insertEstructuraCADMasivos(biz.belcorp.ssicc.spusicc.cuentacorriente.model.EstructuraCADMasivos)
	 */
	public void insertEstructuraCADMasivos(EstructuraCADMasivos estructura){
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.insertEstructuraCADMasivos", estructura);
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarCADMasivosDAO#deleteTablasCargaCargosAbonosMasivos()
	 */
	public void deleteTablasCargaCargosAbonosMasivos(Map datos) {	
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.deleteTablasCargaCargosAbonosMasivos", datos);		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarCADMasivosDAO#executeValidarCargosAbonosMasivos(java.util.Map)
	 */
	public void executeValidarCargosAbonosMasivos(Map criteria){
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeValidarCargosAbonosMasivos", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarCADMasivosDAO#getErroresCargaCargosAbonosMasivos()
	 */
	public List getErroresCargaCargosAbonosMasivos(Map datos){
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getErroresCargaCargosAbonosMasivos",datos);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarCADMasivosDAO#executeProcesarCargosAbonosMasivos(java.util.Map)
	 */
	public void executeProcesarCargosAbonosMasivos(Map datos){
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeProcesarCargosAbonosMasivos", datos);
	}
}
