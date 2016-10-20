package biz.belcorp.ssicc.dao.spusicc.emprendedoras.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.emprendedoras.ProcesoEMPReasignacionMasivaDAO;
import biz.belcorp.ssicc.dao.spusicc.emprendedoras.model.EstructuraEMPReasignacionMasiva;

/**
 * @author <a href="mailto:dooliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */
@Repository("spusicc.procesoEMPReasignacionMasivaDAO")
public class ProcesoEMPReasignacionMasivaDAOiBatis  extends	BaseDAOiBatis implements ProcesoEMPReasignacionMasivaDAO {
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.ProcesoEMPCargarPreEmprendedorasDAO#obtenerPathUpload(java.util.Map)
	 */
	public String obtenerPathUpload(Map datos) {
		return (String) getSqlMapClientTemplate().
		queryForObject("spusicc.emprendedoras.procesosEMPSQL.getPathUpload", datos);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.ProcesoEMPReasignacionMasivaDAO#insertEstructuraEMPReasignacionMasiva(biz.belcorp.ssicc.spusicc.emprendedoras.model.EstructuraEMPReasignacionMasiva)
	 */
	public void insertEstructuraEMPReasignacionMasiva(EstructuraEMPReasignacionMasiva estructura){
		getSqlMapClientTemplate().insert("spusicc.emprendedoras.procesosEMPSQL.insertEstructuraEMPReasignacionMasiva", estructura);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.ProcesoEMPReasignacionMasivaDAO#deleteEstructuraEMPReasignacionMasiva()
	 */
	public void deleteEstructuraEMPReasignacionMasiva(){
		getSqlMapClientTemplate().delete("spusicc.emprendedoras.procesosEMPSQL.deleteEstructuraEMPReasignacionMasiva");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.ProcesoEMPReasignacionMasivaDAO#executeValidarReasignacionMasiva()
	 */
	public void executeValidarReasignacionMasiva(Map datos){
		getSqlMapClientTemplate().update("spusicc.emprendedoras.procesosEMPSQL.executeValidarReasignacionMasiva",datos);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.ProcesoEMPReasignacionMasivaDAO#getErroresReasignacionMasiva()
	 */
	public List getErroresReasignacionMasiva(){
		return getSqlMapClientTemplate().queryForList("spusicc.emprendedoras.procesosEMPSQL.getErroresReasignacionMasiva");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.ProcesoEMPReasignacionMasivaDAO#executeVinculacionNuevasReactivadas(java.util.Map)
	 */
	public void executeVinculacionNuevasReactivadas(Map datos){
		getSqlMapClientTemplate().update("spusicc.emprendedoras.procesosEMPSQL.executeVinculacionNuevasReactivadas",datos);
	}
}
