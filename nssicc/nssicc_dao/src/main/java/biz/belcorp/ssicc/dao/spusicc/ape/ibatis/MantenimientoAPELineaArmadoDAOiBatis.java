package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPELineaArmadoDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.LineaArmado;
/**
 * @author Jose Luis Rodriguez
 *
 */
@Repository("spusicc.mantenimientoAPELineaArmadoDAO")
public class MantenimientoAPELineaArmadoDAOiBatis extends BaseDAOiBatis implements MantenimientoAPELineaArmadoDAO{
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getCentroDistList(java.util.Map)
	 */
	public List getCentroDistList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getCentroDistList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getDescripcionLineaArmadoList(java.util.Map)
	 */
	public List getLineaArmadobyOidCentro(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getLineaArmadobyOidCentro", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getLineaArmadoCabec(java.util.Map)
	 */
	public List getLineaArmadoCabec(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getLineaArmadoCabec",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getOidCentroDistribucionPais(java.util.Map)
	 */
	public String getOidCentroDistribucionPais( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidCentroDistribucionPais", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#executeDeleteCentroDistribucion(java.util.Map)
	 */
	public void deleteLineaArmado(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.deleteLineaArmado", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getCentroDistribucionDefecto(java.util.Map)
	 */
	public String getCentroDistribucionDefecto( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getCentroDistribucionDefecto", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getProgramaCubicajeList(java.util.Map)
	 */
	public List getProgramaCubicajeList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getProgramaCubicajeList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getPlataformaList(java.util.Map)
	 */
	public List getPlataformaList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getPlataformaList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getTipoSolicitudConsoList(java.util.Map)
	 */
	public List getTipoSolicitudConsoList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getTipoSolicitudConsoList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getLineaArmadoObject(java.util.Map)
	 */
	public LineaArmado getLineaArmadoObject(Map criteria) {
		return (LineaArmado)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getLineaArmadoObject", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getUsuarioAlarmaList(java.util.Map)
	 */
	public List getUsuarioAlarmaList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getUsuarioAlarmaList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getNextOidLinaeArmado(java.util.Map)
	 */
	public int getNextOidLinaeArmado(Map criteria) {
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getNextOidLinaeArmado", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getMaxCodLinaeArmado(java.util.Map)
	 */
	public int getMaxCodLinaeArmado(Map criteria) {
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getMaxCodLinaeArmado", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getOidProgCubicaje(java.util.Map)
	 */
	public int getOidProgCubicaje(Map criteria) {
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidProgCubicaje", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getOidPlataforma(java.util.Map)
	 */
	public int getOidPlataforma(Map criteria) {
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidPlataforma", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getExisteLineaDefault(java.util.Map)
	 */
	public int getExisteLineaDefault(Map criteria) {
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getExisteLineaDefault", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getDescripcionLineaDefault(java.util.Map)
	 */
	public String getDescripcionLineaDefault( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getDescripcionLineaDefault", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#insertLineaArmado(java.util.Map)
	 */
	public void insertLineaArmado(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.insertLineaArmado",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#insertUsuarioAlarma(java.util.Map)
	 */
	public void insertUsuarioAlarma(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.insertUsuarioAlarma",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#insertTipoSolicitudLinea(java.util.Map)
	 */
	public void insertTipoSolicitudLinea(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.insertTipoSolicitudLinea",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#updateLineaArmado(java.util.Map)
	 */
	public void updateLineaArmado(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateLineaArmado", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#deleteUsuarioAlarma(java.util.Map)
	 */
	public void deleteUsuarioAlarma(Map criteria){
		getSqlMapClientTemplate().delete("spusicc.ape.MantenimientoAPESQL.deleteUsuarioAlarma", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getNextOidUsuarioAlarma()
	 */
	public int getNextOidUsuarioAlarma() {
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getNextOidUsuarioAlarma");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getTipoSolicitudConsoSelecList(java.util.Map)
	 */
	public List getTipoSolicitudConsoSelecList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getTipoSolicitudConsoSelecList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#deleteTipoSolicitudLinea(java.util.Map)
	 */
	public void deleteTipoSolicitudLinea(Map criteria){
		getSqlMapClientTemplate().delete("spusicc.ape.MantenimientoAPESQL.deleteTipoSolicitudLinea", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getCodigoLineaDefault(java.util.Map)
	 */
	public String getCodigoLineaDefault(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getCodigoLineaDefault", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getPeriodoActual(java.util.Map)
	 */
	public String getPeriodoActual(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getPeriodoActual", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getPeriodoAnterior(java.util.Map)
	 */
	public String getPeriodoAnterior( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getPeriodoAnterior", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getDescCentroDistribucion(java.util.Map)
	 */
	public String getDescCentroDistribucion( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getDescCentroDistribucion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getDescLineaArmado(java.util.Map)
	 */
	public String getDescLineaArmado( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getDescLineaArmado", criteria);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getDescCanal(java.util.Map)
	 */
	public String getDescCanal( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getDescCanal", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getDescMarca(java.util.Map)
	 */
	public String getDescMarca( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getDescMarca", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getTotalesMovimientoList(java.util.Map)
	 */
	public List getTotalesMovimientoList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getTotalesMovimientoList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#validaExistePeriodobyMarcaCanal(java.util.Map)
	 */
	public int validaExistePeriodobyMarcaCanal(Map criteria){
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.validaExistePeriodobyMarcaCanal", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getZonasList(java.util.Map)
	 */
	public List getZonasList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getZonasList", criteria);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPELineaArmadoDAO#getTipoSolicitudList(java.util.Map)
	 */
	public List getTipoSolicitudList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getTipoSolicitudList", criteria);
	}
}