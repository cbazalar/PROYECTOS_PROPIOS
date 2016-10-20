package biz.belcorp.ssicc.dao.spisicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spisicc.MantenimientoIMPProcesoImpresionDAO;
import biz.belcorp.ssicc.dao.spisicc.model.ClasificacionVIP;
import biz.belcorp.ssicc.dao.spisicc.model.Etiqueta;
import biz.belcorp.ssicc.dao.spisicc.model.EtiquetaClasificacion;
import biz.belcorp.ssicc.dao.spisicc.model.EtiquetaEstatus;
import biz.belcorp.ssicc.dao.spisicc.model.ProceImpresion;

/**
 * Implementacion de consultas del modulo de IMP - mantenimiento de procesos de impresion
 * 
 * <p>
 * <a href="MantenimientoIMPProcesoImpresionDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Gonzalo Javier Huertas Agurto</a>
 * 
 */
@Repository("imp.mantenimientoIMPProcesoImpresionDAO") 
public class MantenimientoIMPProcesoImpresionDAOiBatis extends BaseDAOiBatis implements MantenimientoIMPProcesoImpresionDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#getProcesosImpresionByCriteria(java.util.Map)
	 */
	public List getProcesosImpresionByCriteria(Map params) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"imp.MantenimientoIMPSQL.getProcesosImpresionByCriteria", params);
    	return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#getProcesosImpresion(java.util.Map)
	 */
	public ProceImpresion getProcesosImpresion(Map params) {
		return (ProceImpresion) getSqlMapClientTemplate().queryForObject("imp.MantenimientoIMPSQL.getProcesosImpresion", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#insertProcesoImpresion(biz.belcorp.ssicc.spisicc.model.ProceImpresion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertProcesoImpresion(ProceImpresion proceImpresion,
			Usuario usuario) {
		getSqlMapClientTemplate().update("imp.MantenimientoIMPSQL.insertProcesoImpresion", proceImpresion);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#updateProcesoImpresion(biz.belcorp.ssicc.spisicc.model.ProceImpresion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateProcesoImpresion(ProceImpresion proceImpresion,
			Usuario usuario) {
		getSqlMapClientTemplate().update("imp.MantenimientoIMPSQL.updateProcesoImpresion", proceImpresion);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#deleteEtiqueta(biz.belcorp.ssicc.spisicc.model.Etiqueta)
	 */
	public void deleteEtiqueta(Etiqueta etiqueta) {
		getSqlMapClientTemplate().update("imp.MantenimientoIMPSQL.deleteEtiqueta", etiqueta);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#getEtiqueta(java.util.Map)
	 */
	public Etiqueta getEtiqueta(Map params) {
		return (Etiqueta) getSqlMapClientTemplate().queryForObject("imp.MantenimientoIMPSQL.getEtiqueta", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#getEtiquetaByCriteria(java.util.Map)
	 */
	public List getEtiquetaByCriteria(Map params) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"imp.MantenimientoIMPSQL.getEtiquetaByCriteria", params);
    	return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#insertEtiqueta(biz.belcorp.ssicc.spisicc.model.Etiqueta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertEtiqueta(Etiqueta etiqueta, Usuario usuario) {
		getSqlMapClientTemplate().update("imp.MantenimientoIMPSQL.insertEtiqueta", etiqueta);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#updateEtiqueta(biz.belcorp.ssicc.spisicc.model.Etiqueta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEtiqueta(Etiqueta etiqueta, Usuario usuario) {
		getSqlMapClientTemplate().update("imp.MantenimientoIMPSQL.updateEtiqueta", etiqueta);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#getEstatusList(java.util.Map)
	 */
	public List getEstatusList(Map params) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"imp.MantenimientoIMPSQL.getEstatusList", params);
    	return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#getEtiquetasList()
	 */
	public List getEtiquetasList() {
		List resultado = getSqlMapClientTemplate().queryForList(
				"imp.MantenimientoIMPSQL.getEtiquetasList");
    	return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#deleteEtiquetaEstatus(biz.belcorp.ssicc.spisicc.model.EtiquetaEstatus)
	 */
	public void deleteEtiquetaEstatus(EtiquetaEstatus etiquetaEstatus) {
		getSqlMapClientTemplate().update("imp.MantenimientoIMPSQL.deleteEtiquetaEstatus", etiquetaEstatus);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#getEtiquetaEstatusByCriteria(java.util.Map)
	 */
	public List getEtiquetaEstatusByCriteria(Map params) {
		List resultado = getSqlMapClientTemplate().queryForList(
						"imp.MantenimientoIMPSQL.getEtiquetaEstatusByCriteria", params);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#insertEtiquetaEstatus(biz.belcorp.ssicc.spisicc.model.EtiquetaEstatus, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertEtiquetaEstatus(EtiquetaEstatus etiquetaEstatus,
			Usuario usuario) {
		getSqlMapClientTemplate().update("imp.MantenimientoIMPSQL.insertEtiquetaEstatus", etiquetaEstatus);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#getEtiquetaEstatus(java.util.Map)
	 */
	public EtiquetaEstatus getEtiquetaEstatus(Map params) {
		return (EtiquetaEstatus) getSqlMapClientTemplate().queryForObject("imp.MantenimientoIMPSQL.getEtiquetaEstatus", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#deleteEtiquetaClasificacion(biz.belcorp.ssicc.spisicc.model.EtiquetaClasificacion)
	 */
	public void deleteEtiquetaClasificacion(
			EtiquetaClasificacion etiquetaClasificacion) {
		getSqlMapClientTemplate().update("imp.MantenimientoIMPSQL.deleteEtiquetaClasificacion", etiquetaClasificacion);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#getClasificacionList(java.util.Map)
	 */
	public List getClasificacionList(Map params) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"imp.MantenimientoIMPSQL.getClasificacionList",params);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#getEtiquetaClasificacion(java.util.Map)
	 */
	public EtiquetaClasificacion getEtiquetaClasificacion(Map params) {
		return (EtiquetaClasificacion) getSqlMapClientTemplate().queryForObject("imp.MantenimientoIMPSQL.getEtiquetaClasificacion", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#getEtiquetaClasificacionByCriteria(java.util.Map)
	 */
	public List getEtiquetaClasificacionByCriteria(Map params) {
		List resultado = getSqlMapClientTemplate().queryForList(
						"imp.MantenimientoIMPSQL.getEtiquetaClasificacionByCriteria",params);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#getTipoClasificacionList()
	 */
	public List getTipoClasificacionList(Map params) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"imp.MantenimientoIMPSQL.getTipoClasificacionList", params);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#insertEtiquetaClasificacion(biz.belcorp.ssicc.spisicc.model.EtiquetaClasificacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertEtiquetaClasificacion(
			EtiquetaClasificacion etiquetaClasificacion, Usuario usuario) {
		getSqlMapClientTemplate().update("imp.MantenimientoIMPSQL.insertEtiquetaClasificacion", etiquetaClasificacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#getClasificacionVIPByCriteria(java.util.Map)
	 */
	public List getClasificacionVIPByCriteria(Map params) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"imp.MantenimientoIMPSQL.getClasificacionVIPByCriteria",params);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#deleteClasificacionVIP(biz.belcorp.ssicc.spisicc.model.ClasificacionVIP)
	 */
	public void deleteClasificacionVIP(ClasificacionVIP clasificacionVIP) {
		getSqlMapClientTemplate().update("imp.MantenimientoIMPSQL.deleteClasificacionVIP", clasificacionVIP);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#getClasificacionVIP(java.util.Map)
	 */
	public ClasificacionVIP getClasificacionVIP(Map params) {
		return (ClasificacionVIP) getSqlMapClientTemplate().queryForObject("imp.MantenimientoIMPSQL.getClasificacionVIP", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.MantenimientoIMPProcesoImpresionDAO#insertClasificacionVIP(biz.belcorp.ssicc.spisicc.model.ClasificacionVIP, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertClasificacionVIP(ClasificacionVIP clasificacionVIP,
			Usuario usuario) {
		getSqlMapClientTemplate().update("imp.MantenimientoIMPSQL.insertClasificacionVIP", clasificacionVIP);
	}
}