/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCCargarCADDocumentoLegalMasivosDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.EstructuraCADDocumentoLegalMasivos;

/**
 * @author pejflorencio
 *
 */
@Repository("spusicc.procesoCCCCargarCADDocumentoLegalMasivosDAO")
public class ProcesoCCCCargarCADDocumentoLegalMasivosDAOiBatis extends	BaseDAOiBatis implements ProcesoCCCCargarCADDocumentoLegalMasivosDAO {
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarCADDocumentoLegalMasivosDAO#obtenerPathUploadCADDocumentoLegalMasivos(java.util.Map)
	 */
	public String obtenerPathUploadCADDocumentoLegalMasivos(Map datos) {
		return (String) getSqlMapClientTemplate().
		queryForObject("spusicc.cuentacorriente.procesosCCCSQL.getPathUploadCADDocumentoLegalMasivos", datos);		
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarCADDocumentoLegalMasivosDAO#insertEstructuraCADDocumentoLegalMasivos(biz.belcorp.ssicc.spusicc.cuentacorriente.model.EstructuraCADMasivos)
	 */
	public void insertEstructuraCADDocumentoLegalMasivos(EstructuraCADDocumentoLegalMasivos estructura){
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.insertEstructuraCADDocumentoLegalMasivos", estructura);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarCADDocumentoLegalMasivosDAO#deleteTablasCargaCADDocumentoLegalMasivos(java.util.Map)
	 */
	public void deleteTablasCargaCADDocumentoLegalMasivos(Map datos) {		
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.deleteTablasCargaCADDocumentoLegalMasivos", datos);		
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarCADDocumentoLegalMasivosDAO#executeValidarCADDocumentoLegalMasivos(java.util.Map)
	 */
	public void executeValidarCADDocumentoLegalMasivos(Map criteria){
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeValidarCADDocumentoLegalMasivos", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarCADDocumentoLegalMasivosDAO#getErroresCargaCADDocumentoLegalMasivos()
	 */
	public List getErroresCargaCADDocumentoLegalMasivos(Map datos){
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getErroresCargaCADDocumentoLegalMasivos",datos);
	}
		
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarCADDocumentoLegalMasivosDAO#executeProcesarCADDocumentoLegalMasivos(java.util.Map)
	 */
	public void executeProcesarCADDocumentoLegalMasivos(Map datos){
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeProcesarCADDocumentoLegalMasivos", datos);
	}
}
