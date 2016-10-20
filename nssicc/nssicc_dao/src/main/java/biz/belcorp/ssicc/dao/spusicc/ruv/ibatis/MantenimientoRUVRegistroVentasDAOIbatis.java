package biz.belcorp.ssicc.dao.spusicc.ruv.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ruv.MantenimientoRUVRegistroVentasDAO;

/**
 * @author peextsapaza
 */
@Repository("spusicc.mantenimientoRUVRegistroVentasDAO")
public class MantenimientoRUVRegistroVentasDAOIbatis extends BaseDAOiBatis implements MantenimientoRUVRegistroVentasDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.ruv.MantenimientoRUVRegistroVentasDAO#getListRegistroVentas(java.util.Map)
	 */
	public List getListRegistroVentas(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ruv.MantenimientoRUVSQL.getListRegistroVentas", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.ruv.MantenimientoRUVRegistroVentasDAO#getTasasImpuesto(java.util.Map)
	 */
	public List getTasasImpuesto(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ruv.MantenimientoRUVSQL.getTasasImpuesto", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.ruv.MantenimientoRUVRegistroVentasDAO#getCanales(java.util.Map)
	 */
	public List getCanales(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ruv.MantenimientoRUVSQL.getCanales", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.ruv.MantenimientoRUVRegistroVentasDAO#getAccesos(java.util.Map)
	 */
	public List getAccesos(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ruv.MantenimientoRUVSQL.getAccesos", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.ruv.MantenimientoRUVRegistroVentasDAO#getSubAccesos(java.util.Map)
	 */
	public List getSubAccesos(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ruv.MantenimientoRUVSQL.getSubAccesos", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.ruv.MantenimientoRUVRegistroVentasDAO#getTiposDocumentoLegal()
	 */
	public List getTiposDocumentoLegal(){
		return getSqlMapClientTemplate().queryForList("spusicc.ruv.MantenimientoRUVSQL.getTiposDocumentoLegal");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.ruv.MantenimientoRUVRegistroVentasDAO#getSociedades(java.util.Map)
	 */
	public List getSociedades(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ruv.MantenimientoRUVSQL.getSociedades", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.ruv.MantenimientoRUVRegistroVentasDAO#getTiposDocumento()
	 */
	public List getTiposDocumento(){
		return getSqlMapClientTemplate().queryForList("spusicc.ruv.MantenimientoRUVSQL.getTiposDocumento");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.ruv.MantenimientoRUVRegistroVentasDAO#getFormatoFechaNumerico(java.lang.String)
	 */
	public Map getFormatoFechaNumerico(String codigoPais) {
		return (Map)getSqlMapClientTemplate().queryForObject(
				"spusicc.ruv.MantenimientoRUVSQL.getFormatoFechaNumerico", codigoPais);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.ruv.MantenimientoRUVRegistroVentasDAO#getTotalRegistroVentas(java.util.Map)
	 */
	public String getTotalRegistroVentas(Map criteria){
		return getSqlMapClientTemplate().queryForObject("spusicc.ruv.MantenimientoRUVSQL.getTotalRegistroVentas", criteria).toString();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.ruv.MantenimientoRUVRegistroVentasDAO#getTotalRegistroRangoVentas(java.util.Map)
	 */
	public String getTotalRegistroRangoVentas(Map criteria) {
		return getSqlMapClientTemplate().queryForObject("spusicc.ruv.MantenimientoRUVSQL.getTotalRegistroRangoVentas", criteria).toString();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.ruv.MantenimientoRUVRegistroVentasDAO#getRegistroVenta(java.lang.String)
	 */
	public Map getRegistroVenta(String oid) {
		return (Map)getSqlMapClientTemplate().queryForObject(
				"spusicc.ruv.MantenimientoRUVSQL.getRegistroVenta", oid);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.ruv.MantenimientoRUVRegistroVentasDAO#insertRegistroVenta(java.util.Map)
	 */
	public void insertRegistroVenta(Map params) {
		getSqlMapClientTemplate().insert(
				"spusicc.ruv.MantenimientoRUVSQL.insertRegistroVenta", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.ruv.MantenimientoRUVRegistroVentasDAO#updateRegistroVenta(java.util.Map)
	 */
	public void updateRegistroVenta(Map params) {
		getSqlMapClientTemplate().update(
				"spusicc.ruv.MantenimientoRUVSQL.updateRegistroVenta", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.ruv.MantenimientoRUVRegistroVentasDAO#deleteRegistroVenta(java.util.Map)
	 */
	public void deleteRegistroVenta(Map params) {
		getSqlMapClientTemplate().update(
				"spusicc.ruv.MantenimientoRUVSQL.deleteRegistroVenta", params);
	}
	
}
