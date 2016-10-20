package biz.belcorp.ssicc.dao.spusicc.emprendedoras.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.emprendedoras.ProcesoEMPCargarPreEmprendedorasDAO;
import biz.belcorp.ssicc.dao.spusicc.emprendedoras.model.EstructuraEMPPreEmprendedora;

/**
 * @author <a href="mailto:rramirez@belcorp.biz">Rosalvina Ramirez Guardia</a>
 */
@Repository("spusicc.procesoEMPCargarPreEmprendedorasDAO")
public class ProcesoEMPCargarPreEmprendedorasDAOiBatis  extends	BaseDAOiBatis implements ProcesoEMPCargarPreEmprendedorasDAO {

	public List getProgramas(){
		return getSqlMapClientTemplate().queryForList("spusicc.emprendedoras.procesosEMPSQL.getProgramas");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.ProcesoEMPCargarPreEmprendedorasDAO#obtenerPathUpload(java.util.Map)
	 */
	public String obtenerPathUpload(Map datos) {
		return (String) getSqlMapClientTemplate().
		queryForObject("spusicc.emprendedoras.procesosEMPSQL.getPathUpload", datos);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.ProcesoEMPCargarPreEmprendedorasDAO#insertEstructuraEMPPreEmprendedora(biz.belcorp.ssicc.spusicc.cuentacorriente.model.EstructuraCADMasivos)
	 */
	public void insertEstructuraEMPPreEmprendedora(EstructuraEMPPreEmprendedora estructura){
		getSqlMapClientTemplate().insert("spusicc.emprendedoras.procesosEMPSQL.insertEstructuraEMPPreEmprendedora", estructura);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.ProcesoEMPCargarPreEmprendedorasDAO#deleteTablasCargaPreEmprendedoras(java.util.Map)
	 */
	public void deleteTablasCargaPreEmprendedoras() {	
		getSqlMapClientTemplate().update("spusicc.emprendedoras.procesosEMPSQL.deleteTablasCargaPreEmprendedoras");		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.ProcesoEMPCargarPreEmprendedorasDAO#executeValidarPreEmprendedoras(java.util.Map)
	 */
	public void executeValidarPreEmprendedoras(Map criteria){
		getSqlMapClientTemplate().update("spusicc.emprendedoras.procesosEMPSQL.executeValidarPreEmprendedoras", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.ProcesoEMPCargarPreEmprendedorasDAO#getErroresCargaPreEmprendedoras(java.util.Map)
	 */
	public List getErroresCargaPreEmprendedoras(){
		return getSqlMapClientTemplate().queryForList("spusicc.emprendedoras.procesosEMPSQL.getErroresCargaPreEmprendedoras");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.ProcesoEMPCargarPreEmprendedorasDAO#executeProcesarPreEmprendedoras(java.util.Map)
	 */
	public void executeProcesarPreEmprendedoras(Map datos){
		getSqlMapClientTemplate().update("spusicc.emprendedoras.procesosEMPSQL.executeProcesarPreEmprendedoras", datos);
	}
}
