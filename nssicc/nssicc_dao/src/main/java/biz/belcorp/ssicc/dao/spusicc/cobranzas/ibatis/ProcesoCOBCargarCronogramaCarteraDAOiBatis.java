package biz.belcorp.ssicc.dao.spusicc.cobranzas.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.ProcesoCOBCargarCronogramaCarteraDAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.EstructuraCronogramaCartera;

/**
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 */

@Repository("spusicc.procesoCOBCargarCronogramaCarteraDAO")
public class ProcesoCOBCargarCronogramaCarteraDAOiBatis  extends	BaseDAOiBatis implements ProcesoCOBCargarCronogramaCarteraDAO {
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.ProcesoEMPCargarPreEmprendedorasDAO#obtenerPathUpload(java.util.Map)
	 */
	public String obtenerPathUpload(Map datos) {
		return (String) getSqlMapClientTemplate().
		queryForObject("sisicc.ProcesosCOBSQL.getPathUpload", datos);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.ProcesoEMPCargarPreEmprendedorasDAO#insertEstructuraEMPPreEmprendedora(biz.belcorp.ssicc.spusicc.cuentacorriente.model.EstructuraCADMasivos)
	 */
	public void insertEstructuraCronogramaCartera(EstructuraCronogramaCartera estructura){
		getSqlMapClientTemplate().insert("sisicc.ProcesosCOBSQL.insertEstructuraCronogramacartera", estructura);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.ProcesoEMPCargarPreEmprendedorasDAO#deleteTablasCargaPreEmprendedoras(java.util.Map)
	 */
	public void deleteTablasCargaCronogramaCartera(Map datos) {	
		getSqlMapClientTemplate().update("sisicc.ProcesosCOBSQL.deleteTablasCargaCronogramaCartera");		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.ProcesoEMPCargarPreEmprendedorasDAO#executeValidarPreEmprendedoras(java.util.Map)
	 */
	public void executeValidarCronogramaCartera(Map criteria){
		getSqlMapClientTemplate().update("sisicc.ProcesosCOBSQL.executeValidarCronogramaCartera", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.ProcesoEMPCargarPreEmprendedorasDAO#getErroresCargaPreEmprendedoras(java.util.Map)
	 */
	public List getErroresCargaCronogramaCartera(){
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getErroresCargaCronogramaCartera");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.ProcesoEMPCargarPreEmprendedorasDAO#executeProcesarPreEmprendedoras(java.util.Map)
	 */
	public void executeProcesarCronogramaCartera(Map datos){
		getSqlMapClientTemplate().update("sisicc.ProcesosCOBSQL.executeProcesarCronogramaCartera", datos);
	}
}
