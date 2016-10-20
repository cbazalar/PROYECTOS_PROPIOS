package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.ProcesoAPEImportarOrdenAnaquelesDAO;

/**
 * Implementacion del DAO que ejecutara los metodos de importacin de orden de Anaqueles
 * <p>
 * <a href="ProcesoAPEImportarOrdenAnaquelesDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoAPEImportarOrdenAnaquelesDAO")
public class ProcesoAPEImportarOrdenAnaquelesDAOiBatis extends BaseDAOiBatis implements
	ProcesoAPEImportarOrdenAnaquelesDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ProcesoAPEImportarOrdenAnaquelesDAO#getMapaCentroDistribucion(java.util.Map)
	 */
	public List getMapaCentroDistribucion(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ape.ProcesosAPESQL.getMapaCentroDistribucion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ProcesoAPEImportarOrdenAnaquelesDAO#getMapaZonaCentroDistribucion(java.util.Map)
	 */
	public List getMapaZonaCentroDistribucion(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ape.ProcesosAPESQL.getMapaZonaCentroDistribucion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ProcesoAPEImportarOrdenAnaquelesDAO#getOrdenAnaqueles(java.util.Map)
	 */
	public List getOrdenAnaqueles(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ape.ProcesosAPESQL.getOrdenAnaqueles", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ProcesoAPEImportarOrdenAnaquelesDAO#getOrdenAnaquel(java.util.Map)
	 */
	public Map getOrdenAnaquel(Map criteria) {
		return (HashMap) getSqlMapClientTemplate().queryForObject("spusicc.ape.ProcesosAPESQL.getOrdenAnaquel", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ProcesoAPEImportarOrdenAnaquelesDAO#getSubLineaOrdenAnaquel(java.util.Map)
	 */
	public List getSubLineaOrdenAnaquel(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ape.ProcesosAPESQL.getSubLineaOrdenAnaquel", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ProcesoAPEImportarOrdenAnaquelesDAO#getDetalleMapaCDBySubLinea(java.util.Map)
	 */
	public List getDetalleMapaCDBySubLinea(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ape.ProcesosAPESQL.getDetalleMapaCDBySubLinea", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ProcesoAPEImportarOrdenAnaquelesDAO#getDetalleOrdenAnaquelBySubLinea(java.util.Map)
	 */
	public List getDetalleOrdenAnaquelBySubLinea(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ape.ProcesosAPESQL.getDetalleOrdenAnaquelBySubLinea", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ProcesoAPEImportarOrdenAnaquelesDAO#insertDetalleOrdenAnaquel(java.util.Map)
	 */
	public void insertDetalleOrdenAnaquel(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ape.ProcesosAPESQL.insertDetalleOrdenAnaquel", criteria);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ProcesoAPEImportarOrdenAnaquelesDAO#updateDetalleOrdenAnaquel(java.util.Map)
	 */
	public void updateDetalleOrdenAnaquel(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ape.ProcesosAPESQL.updateDetalleOrdenAnaquel", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ProcesoAPEImportarOrdenAnaquelesDAO#getTipoSolConsolidado(java.util.Map)
	 */
	public List getTipoSolConsolidado(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.ape.ProcesosAPESQL.getTipoSolConsolidado", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ProcesoAPEImportarOrdenAnaquelesDAO#actualizarIndicador(java.util.Map)
	 */
	public void actualizarIndicador(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.actualizarIndicador", params);
	}
}
