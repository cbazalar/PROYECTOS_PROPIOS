package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEConfiguracionCentroDistribucionDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.CentroDistribucion;
/**
 * @author Jose Luis Rodriguez
 *
 */
@Repository("spusicc.mantenimientoAPEConfiguracionCentroDistribucionDAO")
public class MantenimientoAPEConfiguracionCentroDistribucionDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEConfiguracionCentroDistribucionDAO{
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#getCodigoCentroDistribucionList(java.util.Map)
	 */
	public List getCodigoCentroDistribucionList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getCodigoCentroDistribucionList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#getDescripcionCentroDistribucionList(java.util.Map)
	 */
	public List getDescripcionCentroDistribucionList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getDescripcionCentroDistribucionList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#getCentroDistribucionList(java.util.Map)
	 */
	public List getCentroDistribucionList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getCentroDistribucionList",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#getNivelOutsourcingList(java.util.Map)
	 */
	public List getNivelOutsourcingList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getNivelOutsourcingList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#getOrdenList(java.util.Map)
	 */
	public List getOrdenList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getOrdenList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#getOrdenListaPicadoList(java.util.Map)
	 */
	public List getOrdenListaPicadoList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getOrdenListaPicadoList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#getAgrupacionList(java.util.Map)
	 */
	public List getAgrupacionList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getAgrupacionList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#getVisualizacionChequeoList(java.util.Map)
	 */
	public List getVisualizacionChequeoList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getVisualizacionChequeoList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#getCentroDistribucionObject(java.util.Map)
	 */
	public CentroDistribucion getCentroDistribucionObject(Map criteria) {
		return (CentroDistribucion)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getCentroDistribucionObject", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#insertCentroDistribucion(java.util.Map)
	 */
	public void insertCentroDistribucion(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.insertCentroDistribucion",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#updateCentroDistribucion(java.util.Map)
	 */
	public void updateCentroDistribucion(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateCentroDistribucion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#getOidNivelOutsourcing(java.util.Map)
	 */
	public String getOidNivelOutsourcing( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidNivelOutsourcing", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#getOidOrdenListaPicado(java.util.Map)
	 */
	public String getOidOrdenListaPicado( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidOrdenListaPicado", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#getOidOrden(java.util.Map)
	 */
	public String getOidOrden( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidOrden", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#getOidAgrupacionAFP(java.util.Map)
	 */
	public String getOidAgrupacionAFP( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidAgrupacionAFP", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#getOidOrdenVisualizacion(java.util.Map)
	 */
	public String getOidOrdenVisualizacion( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidOrdenVisualizacion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#getOidAgrupacionOLAS(java.util.Map)
	 */
	public String getOidAgrupacionOLAS( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidAgrupacionOLAS", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#insertIdiomasAPE(java.util.Map)
	 */
	public void insertIdiomasAPE(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.insertIdiomasAPE",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#updateIdiomasAPE(java.util.Map)
	 */
	public void updateIdiomasAPE(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateIdiomasAPE", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#getNumAtributoIdioma(java.util.Map)
	 */
	public String getNumAtributoIdioma( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getNumAtributoIdioma", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#getMaximoOidCentroDistribucion(java.util.Map)
	 */
	public String getMaximoOidCentroDistribucion( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getMaximoOidCentroDistribucion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#getExisteCentroDefault(java.util.Map)
	 */
	public int getExisteCentroDefault(Map criteria) {
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getExisteCentroDefault", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#getDescripcionCentroDefault(java.util.Map)
	 */
	public String getDescripcionCentroDefault( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getDescripcionCentroDefault", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#deleteCentroDistribucion(java.util.Map)
	 */
	public void deleteCentroDistribucion(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.ape.MantenimientoAPESQL.deleteCentroDistribucion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#deleteIdiomaAPE(java.util.Map)
	 */
	public void deleteIdiomaAPE(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.ape.MantenimientoAPESQL.deleteIdiomaAPE", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionCentroDistribucionDAO#executeValidarRegistrosCentroDistribucion(java.util.Map)
	 */
	public void executeValidarRegistrosCentroDistribucion(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.executeValidarRegistrosCentroDistribucion", criteria);
	}
	
}