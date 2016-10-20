/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cobranzas.ibatis;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.ProcesoCOBCargarCronogramaDAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.EstructuraCOBCargarCronograma;


/**
 * The Class ProcesoCOBCargarCronogramaDAOiBatis.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 06/05/2014
 */

@Repository("spusicc.procesoCOBCargarCronogramaDAO")
public class ProcesoCOBCargarCronogramaDAOiBatis extends BaseDAOiBatis implements ProcesoCOBCargarCronogramaDAO {
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBCargarCronogramaDAO#obtenerPathUpload(java.util.Map)
	 */
	public String obtenerPathUpload(Map datos) {
		return (String) getSqlMapClientTemplate().
		queryForObject("sisicc.ProcesosCOBSQL.getPathUpload", datos);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBCargarCronogramaDAO#getTiposEtapa(java.util.Map)
	 */
	public List getTiposEtapa(Map datos) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getEtapasDeudaByPaisSociedad",datos);
	}
	
	public Integer getValidarRegistroCargarCronograma(Map criteria){
		return (Integer)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosCOBSQL.getValidarRegistroCargarCronograma", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBCargarCronogramaDAO#getValidarCargarCronograma(java.util.Map)
	 */
	public Integer getValidarCargarCronograma(Map criteria){
		return (Integer)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosCOBSQL.getValidarCargarCronograma", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBCargarCronogramaDAO#getDevuelveDescripcionEtapa(java.util.Map)
	 */
	public String getDevuelveDescripcionEtapa(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosCOBSQL.getDevuelveDescripcionEtapa", criteria);
	}
	
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBCargarCronogramaDAO#executeProcesarCargarCronograma(java.util.Map)
	 */
	public void executeProcesarCargarCronograma(EstructuraCOBCargarCronograma estructuraCOBCargarCronograma) {
		getSqlMapClientTemplate().update("sisicc.ProcesosCOBSQL.updateCargarCronograma", estructuraCOBCargarCronograma);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBCargarCronogramaDAO#obtenerPathUpload(java.util.Map)
	 */
	public String obtenerPathUploadEstandar(Map datos) {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ProcesosCOBSQL.getPathEstandar", datos);
	}
}
