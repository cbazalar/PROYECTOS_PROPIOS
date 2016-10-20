package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEMapaZonaLineaArmadoDAO;

/**
 * @author Nicols Lpez
 *
 */
@Repository("spusicc.mantenimientoAPEMapaZonaLineaArmadoDAO")
public class MantenimientoAPEMapaZonaLineaArmadoDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEMapaZonaLineaArmadoDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMapaZonaLineaArmadoDAO#getCodMapaZonaCabecera(java.util.Map)
	 */
	public String getCodMapaZonaCabecera(Map criteria) {
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getMaxCodMapaZona", criteria).toString();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMapaZonaLineaArmadoDAO#insertarMapaZonaCabecera(java.util.Map)
	 */
	public void insertarMapaZonaCabecera(Map criteria) {
		this.getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.insertMapaZonaCabecera", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMapaZonaLineaArmadoDAO#getOidMapaZona(java.util.Map)
	 */
	public String getOidMapaZona(Map criteria) {
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidSequenceMapaZona", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMapaZonaLineaArmadoDAO#insertarMapaZonaDetalle(java.util.Map)
	 */
	public void insertarMapaZonaDetalle(Map criteria) {
		this.getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.insertMapaZonaDetalle", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMapaZonaLineaArmadoDAO#getMapaZonaxOidMapaCD(java.util.Map)
	 */
	public List getMapaZonaxOidMapaCD(Map criteria){
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getMapaZonaListxOidMapaCD", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMapaZonaLineaArmadoDAO#getListaMapaZonaLineaArmado(java.util.Map)
	 */
	public List getListaMapaZonaLineaArmado(Map criteria){
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getListaMapaZonaLineaArmado", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMapaZonaLineaArmadoDAO#getObtenerDetMapaZonaLineaArmado(java.util.Map)
	 */
	public List getObtenerDetMapaZonaLineaArmado(Map criteria){
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getObtenerDetMapaZonaLineaArmado", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMapaZonaLineaArmadoDAO#getSubLineaComboList(java.util.Map)
	 */
	public List getSubLineaComboList(Map criteria){
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getSubLineaComboList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMapaZonaLineaArmadoDAO#getObtenerCodigoCDxOidMapaCD(java.util.Map)
	 */
	public String getObtenerCodigoCDxOidMapaCD(Map criteria) {
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getObtenerCodigoCDxOidMapaCD", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMapaZonaLineaArmadoDAO#getObtenerCodLineaArmadoxOidSubLinea(java.util.Map)
	 */
	public String getObtenerCodLineaArmadoxOidSubLinea(Map criteria) {
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getObtenerCodLinArmadxOidSubLinArmad", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMapaZonaLineaArmadoDAO#getObtenerCodigoMapaCD(java.util.Map)
	 */
	public String getObtenerCodigoMapaCD(Map criteria) {
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getObtenerCodigoMapaCD", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMapaZonaLineaArmadoDAO#getSubLineaporLineaList(java.util.Map)
	 */
	public List getSubLineaporLineaList(Map criteria) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getSubLineaporLineaList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMapaZonaLineaArmadoDAO#updateMapaZonaCabecera(java.util.Map)
	 */
	public void updateMapaZonaCabecera(Map criteria){
		this.getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateMapaZonaCabecera", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMapaZonaLineaArmadoDAO#deleteLineaArmado(java.util.Map)
	 */
	public void deleteLineaMapaZona(Map criteria){
		this.getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.deleteMapaZona", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMapaZonaLineaArmadoDAO#deleteMapaZonaxLineaArmado(java.util.Map)
	 */
	public void deleteMapaZonaxLineaArmado(Map criteria){
		this.getSqlMapClientTemplate().delete("spusicc.ape.MantenimientoAPESQL.deleteMapaZonaxLineaArmado", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMapaZonaLineaArmadoDAO#getValidaExisteMapaZonaDefecto(java.util.Map)
	 */
	public String getValidaExisteMapaZonaDefecto(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getValidaExisteMapaZonaDefecto", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMapaZonaLineaArmadoDAO#getObtenerBahiaMayorxSubLinea(java.util.Map)
	 */
	public String getObtenerBahiaMayorxSubLinea(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getObtenerBahiaMayorxSubLinea", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMapaZonaLineaArmadoDAO#getObtenerDescripcionSubLinea(java.util.Map)
	 */
	public String getObtenerDescripcionSubLinea(Map criteria){ 
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getObtenerDescripcionSubLinea", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMapaZonaLineaArmadoDAO#getOidMapaCDxOidCentro(java.util.Map)
	 */
	public String getOidMapaCDxOidCentro(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidMapaCDxOidCentro", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMapaZonaLineaArmadoDAO#getObtenerBahiasxSubLineaList(java.util.Map)
	 */
	public List getObtenerBahiasxSubLineaList(Map criteria){
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getObtenerBahiasxSubLineaList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMapaZonaLineaArmadoDAO#getObtenerCantidadBahiasxSubLinea(java.util.Map)
	 */
	public String getObtenerCantidadBahiasxSubLinea(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getObtenerCantidadBahiasxSubLinea", criteria);
	}
	
}
