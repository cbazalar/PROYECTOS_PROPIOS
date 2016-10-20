package biz.belcorp.ssicc.dao.spusicc.lideres.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.lideres.MantenimientoLIDLideresDAO;

/**
 * Implementacion del DAO que ejecutara los metodos de mantenimiento de Lideres
 * <p>
 * <a href="MantenimientoLIDLideresDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.mantenimientoLIDLideresDAO")
public class MantenimientoLIDLideresDAOIbatis extends BaseDAOiBatis implements
	MantenimientoLIDLideresDAO {

    /**
     * @param criteria
     * @return
     */
    public Base getUltimaCampanaCierreRegionxZona(Map criteria) {
        return (Base) getSqlMapClientTemplate().queryForObject("spusicc.lideres.MantenimientoLIDSQL.getUltimaCampanaCierreRegionxZona", criteria);
    }	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDLideresDAO#getSeccionesByCriteria(java.util.Map)
	 */
	public List getSeccionesByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lideres.MantenimientoLIDSQL.getSeccionesByCriteria",criteria);
	}

	/**
	 * @param params
	 * @return
	 */
	public String validarAsignacionLiderSeccion(Map params) {
		return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.lideres.MantenimientoLIDSQL.validarAsignacionLiderSeccion", params);
	}
	
	/**
	 * @param criteria
	 * @return
	 */
	public Map getUltimoResponsableSeccion(Map criteria) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"spusicc.lideres.MantenimientoLIDSQL.getUltimoResponsableSeccion", criteria);
	}

	/**
	 * @param oidHistoricoGerente
	 */
	public void updateFechaHastaHistoricoGerente(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.lideres.MantenimientoLIDSQL.updateFechaHastaHistoricoGerente",criteria);
	}

	/**
	 * @param criteria
	 */
	public void insertHistoricoGerente(Map criteria) {
	    getSqlMapClientTemplate().insert("spusicc.lideres.MantenimientoLIDSQL.insertHistoricoGerente", criteria);
	}

	/**
	 * @param criteria
	 */
	public void updateLiderSeccion(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.lideres.MantenimientoLIDSQL.updateLiderSeccion",criteria);
	}
	
	/**
	 * @param criteria
	 */
	public void insertBuzonMensajeResponsable(Map criteria) {
	    getSqlMapClientTemplate().insert("spusicc.lideres.MantenimientoLIDSQL.insertBuzonMensajeResponsable", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDLideresDAO#deleteClasificacionLiderConsultoraAnterior(java.util.Map)
	 */
	public void deleteClasificacionLiderConsultoraAnterior(Map criteria) {
        getSqlMapClientTemplate().delete("spusicc.lideres.MantenimientoLIDSQL.deleteClasificacionLiderConsultoraAnterior", criteria);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDLideresDAO#getConsultoraClasificacionLider(java.util.Map)
	 */
	public String getConsultoraClasificacionLider(Map params) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.lideres.MantenimientoLIDSQL.getConsultoraClasificacionLider", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDLideresDAO#insertClasificacionConsultoraLider(java.util.Map)
	 */
	public void insertClasificacionConsultoraLider(Map criteria) {
	    getSqlMapClientTemplate().insert("spusicc.lideres.MantenimientoLIDSQL.insertClasificacionConsultoraLider", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDLideresDAO#getListCondicionesLideres()
	 */
	public List getListCondicionesLideres() {
		return getSqlMapClientTemplate().queryForList("spusicc.lideres.MantenimientoLIDSQL.getListCondicionesLideres");
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDLideresDAO#getListCondicionDespachoCanasta(java.lang.String)
	 */
	public List getListCondicionDespachoCanasta(String codigoPeriodo) {
		return getSqlMapClientTemplate().queryForList("spusicc.lideres.MantenimientoLIDSQL.getListCondicionDespachoCanasta",codigoPeriodo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDLideresDAO#insertCondicionDespachoCanasta(java.util.Map)
	 */
	public void insertCondicionDespachoCanasta(Map map) {
		getSqlMapClientTemplate().insert("spusicc.lideres.MantenimientoLIDSQL.insertCondicionDespachoCanasta", map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDLideresDAO#updateCondicionDespachoCanasta(java.util.Map)
	 */
	public void updateCondicionDespachoCanasta(Map map) {
		getSqlMapClientTemplate().insert("spusicc.lideres.MantenimientoLIDSQL.updateCondicionDespachoCanasta", map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDLideresDAO#getListProductosCanasta(java.lang.String)
	 */
	public List getListProductosCanasta(String codigoPeriodo) {
		return getSqlMapClientTemplate().queryForList("spusicc.lideres.MantenimientoLIDSQL.getListProductosCanasta",codigoPeriodo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDLideresDAO#insertProductosCanasta(java.util.Map)
	 */
	public void insertProductosCanasta(Map map) {
		getSqlMapClientTemplate().insert("spusicc.lideres.MantenimientoLIDSQL.insertProductosCanasta", map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDLideresDAO#updateProductosCanasta(java.util.Map)
	 */
	public void updateProductosCanasta(Map map) {
		getSqlMapClientTemplate().insert("spusicc.lideres.MantenimientoLIDSQL.updateProductosCanasta", map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDLideresDAO#getListTipoOferta()
	 */
	public List getListTipoOferta() {
		return getSqlMapClientTemplate().queryForList("spusicc.lideres.MantenimientoLIDSQL.getListTipoOferta");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDLideresDAO#getDevuelveOidMatrizFacturacion(java.util.Map)
	 */
	public Integer getDevuelveOidMatrizFacturacion(Map map) {
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.lideres.MantenimientoLIDSQL.getDevuelveOidMatrizFacturacion",map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDLideresDAO#getDescripcionInternacionalizableProducto(java.lang.String)
	 */
	public String getDescripcionInternacionalizableProducto(String codigoSap) {
		return(String)getSqlMapClientTemplate().queryForObject("spusicc.lideres.MantenimientoLIDSQL.getDescripcionInternacionalizableProducto",codigoSap);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDLideresDAO#insertListProductosCanasta(java.util.Map)
	 */
	public void insertListProductosCanasta(Map map) {
		getSqlMapClientTemplate().update("spusicc.lideres.MantenimientoLIDSQL.executeInsertProductosCanasta", map);		
	}
}
