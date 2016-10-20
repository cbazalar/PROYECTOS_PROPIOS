package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEAsignarVersionesProductosDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.VersionProducto;
/**
 * @author Jose Luis Rodriguez
 *
 */
@Repository("spusicc.mantenimientoAPEAsignarVersionesProductosDAO")
public class MantenimientoAPEAsignarVersionesProductosDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEAsignarVersionesProductosDAO{
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#getVersionesList(java.util.Map)
	 */
	public List getVersionesList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getVersionesList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#getVersionesAsignadosList(java.util.Map)
	 */
	public List getVersionesAsignadosList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getVersionesAsignadosList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#getOidPeriodobyMarcaCanal(java.util.Map)
	 */
	public String getOidPeriodobyMarcaCanal(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidPeriodobyMarcaCanal", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#getVersionesbyPeriodoList(java.util.Map)
	 */
	public List getVersionesbyPeriodoList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getVersionesbyPeriodoList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#getMapaZonaVersionList(java.util.Map)
	 */
	public List getMapaZonaVersionList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getMapaZonaVersionList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#getVersionProductoObject(java.util.Map)
	 */
	public VersionProducto getVersionProductoObject(Map criteria) {
		return (VersionProducto)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getVersionProductoObject", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#getMapaZonaComboList(java.util.Map)
	 */
	public List getMapaZonaComboList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getMapaZonaComboList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#getCodigoMapaZona(java.util.Map)
	 */
	public String getCodigoMapaZona(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getCodigoMapaZona", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#getExisteVersionPeriodoMapaCD(java.util.Map)
	 */
	public String getExisteVersionPeriodoMapaCD(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getExisteVersionPeriodoMapaCD", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#getVerificaVersionActiva(java.util.Map)
	 */
	public String getVerificaVersionActiva(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getVerificaVersionActiva", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#getOidProcesoAPEEstimado(java.util.Map)
	 */
	public String getOidProcesoAPEEstimado(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidProcesoAPEEstimado", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#getNextOidAsignacionProductoCab()
	 */
	public String getNextOidAsignacionProductoCab(){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getNextOidAsignacionProductoCab"); 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#executeInsertVersionesDetalle(java.util.Map)
	 */
	public void executeInsertVersionesDetalle(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.executeInsertVersionesDetalle", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#insertVersionesCab(java.util.Map)
	 */
	public void insertVersionesCab(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.insertVersionesCab",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#desactivarVersionesActuales(java.util.Map)
	 */
	public void desactivarVersionesActuales(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.desactivarVersionesActuales",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#updateVersiones(java.util.Map)
	 */
	public void updateVersiones(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateVersiones",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#deleteVersionesCab(java.util.Map)
	 */
	public void deleteVersionesCab(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.ape.MantenimientoAPESQL.deleteVersionesCab",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#deleteVersionesDet(java.util.Map)
	 */
	public void deleteVersionesDet(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.ape.MantenimientoAPESQL.deleteVersionesDet",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#validaVersionAnaquelListaPicado(java.util.Map)
	 */
	public String validaVersionAnaquelListaPicado(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.validaVersionAnaquelListaPicado", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#getOidMapaCDbyCod(java.util.Map)
	 */
	public String getOidMapaCDbyCod(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidMapaCDbyCod", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#getOidLineaVersion(java.util.Map)
	 */
	public String getOidLineaVersion(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidLineaVersion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#getExisteOidPeriodoCanalMarca(java.util.Map)
	 */
	public int getExisteOidPeriodoCanalMarca(Map criteria){
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getExisteOidPeriodoCanalMarca", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#getVersionActiva(java.util.Map)
	 */
	public String getVersionActiva(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getVersionActiva", criteria);
	}
	
}