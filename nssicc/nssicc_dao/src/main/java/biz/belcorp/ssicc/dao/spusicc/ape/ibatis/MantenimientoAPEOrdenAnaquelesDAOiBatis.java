package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEOrdenAnaquelesDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.OrdenAnaquel;
/**
 * @author Jose Luis Rodriguez
 *
 */
@Repository("spusicc.mantenimientoAPEOrdenAnaquelesDAO")
public class MantenimientoAPEOrdenAnaquelesDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEOrdenAnaquelesDAO{
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#getMapaOrdenLineaList(java.util.Map)
	 */
	public List getMapaOrdenLineaList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getMapaOrdenLineaList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#getMapaCentroDistribucionComboList(java.util.Map)
	 */
	public List getMapaCentroDistribucionComboList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getMapaCentroDistribucionComboList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#getMapaZonaListComboList(java.util.Map)
	 */
	public List getMapaZonaComboList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getMapaZonaComboList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#getMapaOrdenComboList(java.util.Map)
	 */
	public List getMapaOrdenComboList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getMapaOrdenComboList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#getOidOrdenAnaquel(java.util.Map)
	 */
	public String getOidOrdenAnaquel( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidOrdenAnaquel", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#getOidMapaZonaByCodMapaZona(java.util.Map)
	 */
	public String getOidMapaZonaByCodMapaZona(Map criteria) {
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidMapaZonaByCodMapaZona", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#getLineaMapaOrdenComboList(java.util.Map)
	 */
	public List getLineaMapaOrdenComboList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getLineaMapaOrdenComboList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#getSubLineasNuevoOrdenAnaquel(java.util.Map)
	 */
	public List getSubLineasNuevoOrdenAnaquel(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getSubLineasNuevoOrdenAnaquel", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#validaExisteOrdeAnaquelDefault(java.util.Map)
	 */
	public int validaExisteOrdeAnaquelDefault(Map criteria){
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.validaExisteOrdeAnaquelDefault", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#getDescripcionCentroDistribucion(java.util.Map)
	 */
	public String getDescripcionCentroDistribucion(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getDescripcionCentroDistribucion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#getDescripcionMapaCentroDistribucion(java.util.Map)
	 */
	public String getDescripcionMapaCentroDistribucion(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getDescripcionMapaCentroDistribucion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#getDescripcionMapaZona(java.util.Map)
	 */
	public String getDescripcionMapaZona(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getDescripcionMapaZona", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#getDescripcionMapaAnaquelDefault(java.util.Map)
	 */
	public String getDescripcionMapaAnaquelDefault(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getDescripcionMapaAnaquelDefault", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#getOrdenAnaquelObject(java.util.Map)
	 */
	public OrdenAnaquel getOrdenAnaquelObject(Map criteria){
		return (OrdenAnaquel)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOrdenAnaquelObject", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#getSubLineasModificarOrdenAnaquel(java.util.Map)
	 */
	public List getSubLineasModificarOrdenAnaquel(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getSubLineasModificarOrdenAnaquel", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#getDetalleOrdenAnaquelList(java.util.Map)
	 */
	public List getDetalleOrdenAnaquelList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getDetalleOrdenAnaquelList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#getDetalleOrdenAnaquelConsultaList(java.util.Map)
	 */
	public List getDetalleOrdenAnaquelConsultaList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getDetalleOrdenAnaquelConsultaList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#insertOrdenAnaquelCabecera(java.util.Map)
	 */
	public void insertOrdenAnaquelCabecera(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.insertOrdenAnaquelCabecera",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#insertOrdenAnaquelDetalle(java.util.Map)
	 */
	public void insertOrdenAnaquelDetalle(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.insertOrdenAnaquelDetalle",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#getNextOidOrdenAnaquelCab(java.util.Map)
	 */
	public String getNextOidOrdenAnaquelCab(Map criteria){
		return(String)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getNextOidOrdenAnaquelCab", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#getNextOidOrdenAnaquelDet(java.util.Map)
	 */
	public String getNextOidOrdenAnaquelDet(Map criteria){
		return(String)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getNextOidOrdenAnaquelDet", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#getMaxCodOrdenAnaquel(java.util.Map)
	 */
	public int getMaxCodOrdenAnaquel(Map criteria){
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getMaxCodOrdenAnaquel", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#updateOrdenAnaquelDetalle(java.util.Map)
	 */
	public void updateOrdenAnaquelDetalle(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateOrdenAnaquelDetalle", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#deleteOrdenAnaquelDetalle(java.util.Map)
	 */
	public void deleteOrdenAnaquelDetalle(Map criteria){
		getSqlMapClientTemplate().delete("spusicc.ape.MantenimientoAPESQL.deleteOrdenAnaquelDetalle", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#deleteOrdenAnaquelCabecera(java.util.Map)
	 */
	public void deleteOrdenAnaquelCabecera(Map criteria){
		getSqlMapClientTemplate().delete("spusicc.ape.MantenimientoAPESQL.deleteOrdenAnaquelCabecera", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#deleteIdiomaAnaquel(java.util.Map)
	 */
	public void deleteIdiomaAnaquel(Map criteria){
		getSqlMapClientTemplate().delete("spusicc.ape.MantenimientoAPESQL.deleteIdiomaAnaquel", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#validaExisteAnaquelDetalle(java.util.Map)
	 */
	public int validaExisteAnaquelDetalle(Map criteria){
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.validaExisteAnaquelDetalle", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#updateNumeroOrdenAnaquelDetalle(java.util.Map)
	 */
	public void updateNumeroOrdenAnaquelDetalle(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateNumeroOrdenAnaquelDetalle", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#getOidMapaZonaByMapaCentroZona(java.util.Map)
	 */
	public String getOidMapaZonaByMapaCentroZona(Map criteria){
		return(String)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidMapaZonaByMapaCentroZona", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#getLineaMapaOrdenZonaList(java.util.Map)
	 */
	public List getLineaMapaOrdenZonaList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getLineaMapaOrdenZonaList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#deleteOrdenAnaquelDetbyMapaCent(java.util.Map)
	 */
	public void deleteOrdenAnaquelDetbyMapaCent(Map criteria){
		getSqlMapClientTemplate().delete("spusicc.ape.MantenimientoAPESQL.deleteOrdenAnaquelDetbyMapaCent", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#validaExisteMapaAnaquelDefault(java.util.Map)
	 */
	public String validaExisteMapaAnaquelDefault(Map criteria){
		return(String)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.validaExisteMapaAnaquelDefault", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#updateMapaOrdenAnaquelDefault(java.util.Map)
	 */
	public void updateMapaOrdenAnaquelDefault(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateMapaOrdenAnaquelDefault", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#validaExisteOrdenAnaquelDefault(java.util.Map)
	 */
	public String validaExisteOrdenAnaquelDefault(Map criteria){
		return(String)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.validaExisteOrdenAnaquelDefault", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#getMapaCentroSubLineaList(java.util.Map)
	 */
	public String getMapaCentroSubLineaList(Map criteria){
		return(String)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getMapaCentroSubLineaList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEOrdenAnaquelesDAO#actualizaNumeroOrden(java.util.Map)
	 */
	public void actualizaNumeroOrden(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.actualizaNumeroOrden", criteria);
	}
}