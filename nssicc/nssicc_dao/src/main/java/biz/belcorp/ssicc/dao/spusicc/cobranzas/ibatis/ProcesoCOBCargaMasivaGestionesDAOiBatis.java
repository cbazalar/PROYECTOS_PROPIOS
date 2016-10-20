/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cobranzas.ibatis;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.ProcesoCOBCargaMasivaGestionesDAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.EstructuraCOBCargaMasivaGestiones;

/**
 * @author Gonzalo Huertas
 *
 */
@Repository("spusicc.procesoCOBCargaMasivaGestionesDAO")
public class ProcesoCOBCargaMasivaGestionesDAOiBatis extends BaseDAOiBatis implements ProcesoCOBCargaMasivaGestionesDAO {
	
	
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
	public String executeProcesarCargaMasivaGestiones(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ProcesosCOBSQL.executeProcesarCargaMasivaGestiones", criteria);
		return ((String) criteria.get("indIngresado"));
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBCargaMasivaGestionesDAO#getValidarCodigoCliente(java.util.Map)
	 */
	public Integer getValidarCodigoCliente(Map criteria){
		return (Integer)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosCOBSQL.getValidarCodigoCliente", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBCargaMasivaGestionesDAO#getValidarCodigoEtapaAccion(java.util.Map)
	 */
	public Integer getValidarCodigoEtapaAccion(Map criteria){
		return (Integer)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosCOBSQL.getValidarCodigoEtapaAccion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBCargaMasivaGestionesDAO#getValidarCodigoCobrador(java.util.Map)
	 */
	public Integer getValidarCodigoCobrador(Map criteria){
		return (Integer)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosCOBSQL.getValidarCodigoCobrador", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBCargaMasivaGestionesDAO#insertCargaMasivaGestiones(biz.belcorp.ssicc.spusicc.cobranzas.model.EstructuraCOBCargaMasivaGestiones)
	 */
	public void insertCargaMasivaGestiones(EstructuraCOBCargaMasivaGestiones estructura) {
		getSqlMapClientTemplate().update("sisicc.ProcesosCOBSQL.insertCargaMasivaGestiones", estructura);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBCargaMasivaGestionesDAO#deleteTemporalCargaMasivaGestiones()
	 */
	public void deleteTemporalCargaMasivaGestiones() {
		getSqlMapClientTemplate().delete("sisicc.ProcesosCOBSQL.deleteTemporalCargaMasivaGestiones",null);
	}
}
