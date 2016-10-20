package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPESubLineaArmadoDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.SubLineaArmado;
/**
 * @author Jose Luis Rodriguez
 *
 */
@Repository("spusicc.mantenimientoAPESubLineaArmadoDAO")
public class MantenimientoAPESubLineaArmadoDAOiBatis extends BaseDAOiBatis implements MantenimientoAPESubLineaArmadoDAO{
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#getLineaArmadobyOidCentro(java.util.Map)
	 */
	public List getLineaArmadobyOidCentro(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getLineaArmadobyOidCentro", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#getCodLineaArmadaDefecto(java.util.Map)
	 */
	public String getCodLineaArmadaDefecto( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getCodLineaArmadaDefecto", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#getSistemaPicadoList(java.util.Map)
	 */
	public List getSistemaPicadoList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getSistemaPicadoList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#getCodigoImpresoraList()
	 */
	public List getCodigoImpresoraList(){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getCodigoImpresoraList");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#getSubLineaArmadoCabec(java.util.Map)
	 */
	public List getSubLineaArmadoCabec(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getSubLineaArmadoCabec", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#getOidLineaArmadobyCodigo(java.util.Map)
	 */
	public String getOidLineaArmadobyCodigo(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidLineaArmadobyCodigo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#deleteSubLineaArmado(java.util.Map)
	 */
	public void deleteSubLineaArmado(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.deleteSubLineaArmado", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#getLineaArmadoComboList(java.util.Map)
	 */
	public List getLineaArmadoComboList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getLineaArmadoComboList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#getCodLineaArmadaDefectoList(java.util.Map)
	 */
	public String getCodLineaArmadaDefectoList(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getCodLineaArmadaDefectoList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#getSubLineaArmadoObject(java.util.Map)
	 */
	public SubLineaArmado getSubLineaArmadoObject(Map criteria) {
		return (SubLineaArmado)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getSubLineaArmadoObject", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#getTipoCajaProductoList(java.util.Map)
	 */
	public List getTipoCajaProductoList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getTipoCajaProductoList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#getTipoCajaSubLineaList(java.util.Map)
	 */
	public List getTipoCajaSubLineaList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getTipoCajaSubLineaList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#getOidSubLineaArmadobyCodigo(java.util.Map)
	 */
	public String getOidSubLineaArmadobyCodigo(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidSubLineaArmadobyCodigo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#deleteTipoCaja(java.util.Map)
	 */
	public void deleteTipoCaja(Map criteria){
		getSqlMapClientTemplate().delete("spusicc.ape.MantenimientoAPESQL.deleteTipoCaja", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#getNextOidTipoCaja()
	 */
	public int getNextOidTipoCaja() {
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getNextOidTipoCaja");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#getOidTipoCajaProducto(java.util.Map)
	 */
	public String getOidTipoCajaProducto(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidTipoCajaProducto", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#insertTipoCaja(java.util.Map)
	 */
	public void insertTipoCaja(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.insertTipoCaja",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#insertSubLineaArmado(java.util.Map)
	 */
	public void insertSubLineaArmado(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.insertSubLineaArmado",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#insertImpresoraSubLinea(java.util.Map)
	 */
	public void insertImpresoraSubLinea(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.insertImpresoraSubLinea",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#getOidSistemaPicado(java.util.Map)
	 */
	public String getOidSistemaPicado(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidSistemaPicado", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#getNextOidSubLinea()
	 */
	public int getNextOidSubLinea() {
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getNextOidSubLinea");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#getMaxCodSubLinesArmado()
	 */
	public int getMaxCodSubLinesArmado(){
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getMaxCodSubLinesArmado");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#updateSubLineaArmado(java.util.Map)
	 */
	public void updateSubLineaArmado(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateSubLineaArmado", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#getExisteLetraAnquelCD(java.util.Map)
	 */
	public int getExisteLetraAnquelCD(Map criteria){
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getExisteLetraAnquelCD", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#getOidCodigoImpresora(java.util.Map)
	 */
	public int getOidCodigoImpresora(Map criteria){
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidCodigoImpresora", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#deleteCodigoImpresora(java.util.Map)
	 */
	public void deleteCodigoImpresora(Map criteria){
		getSqlMapClientTemplate().delete("spusicc.ape.MantenimientoAPESQL.deleteCodigoImpresora", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#getCodigoLineaArmadobyOid(java.util.Map)
	 */
	public String getCodigoLineaArmadobyOid(Map criteria){
		return(String)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getCodigoLineaArmadobyOid", criteria);
	}
}