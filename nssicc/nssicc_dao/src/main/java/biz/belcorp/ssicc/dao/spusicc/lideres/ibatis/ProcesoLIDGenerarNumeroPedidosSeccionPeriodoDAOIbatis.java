package biz.belcorp.ssicc.dao.spusicc.lideres.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDGenerarNumeroPedidosSeccionPeriodoDAO;

@Repository("spusicc.procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO")
public class ProcesoLIDGenerarNumeroPedidosSeccionPeriodoDAOIbatis extends BaseDAOiBatis implements 
			ProcesoLIDGenerarNumeroPedidosSeccionPeriodoDAO {

    /**
     * @param codigoPais
     * @return
     */
    public boolean verificaRegionProcesada(Map params) {
        String result = (String) getSqlMapClientTemplate().queryForObject(
        							"spusicc.lideres.ProcesosLIDSQL.getRegionProcesada", params);
        
        if(Integer.parseInt(result)>0)
        	return true;
        else
        	return false;
    }	
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDGenerarNumeroPedidosSeccionPeriodoDAO#executeGenerarNumeroPedidosSeccionPeriodo(java.util.Map)
	 */
	public void executeGenerarNumeroPedidosSeccionPeriodo(Map params) {
		getSqlMapClientTemplate().update("spusicc.lideres.ProcesosLIDSQL.executeGenerarNumeroPedidosSeccionPeriodo",params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDGenerarNumeroPedidosSeccionPeriodoDAO#getRegionesCerradas(java.util.Map)
	 */
	public List getRegionesCerradas(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.lideres.MantenimientoLIDSQL.getRegionesCerradas",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDGenerarNumeroPedidosSeccionPeriodoDAO#getIndicadorValidoZona(java.util.Map)
	 */
	public Integer getIndicadorValidoZona(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.lideres.ProcesosLIDSQL.getIndicadorValidoZona",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDGenerarNumeroPedidosSeccionPeriodoDAO#getIndicadorValidoSeccion(java.util.Map)
	 */
	public Integer getIndicadorValidoSeccion(Map map) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.lideres.ProcesosLIDSQL.getIndicadorValidoSeccion",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDGenerarNumeroPedidosSeccionPeriodoDAO#getPedidoObjetivo(java.util.Map)
	 */
	public Integer getPedidoObjetivo(Map map) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.lideres.ProcesosLIDSQL.getPedidoObjetivo",map);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDGenerarNumeroPedidosSeccionPeriodoDAO#insertSeccionNumeroPedido(java.util.Map)
	 */
	public void insertSeccionNumeroPedido(Map map) {
		getSqlMapClientTemplate().insert("spusicc.lideres.ProcesosLIDSQL.insertSeccionNumeroPedido",map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDGenerarNumeroPedidosSeccionPeriodoDAO#getZonaNuevaRezonificada(java.util.Map)
	 */
	public Integer getZonaNuevaRezonificada(Map mapRow) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.lideres.ProcesosLIDSQL.getZonaNuevaRezonificada", mapRow);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDGenerarNumeroPedidosSeccionPeriodoDAO#getSeccioNueva(java.util.Map)
	 */
	public Integer getSeccioNueva(Map mapRow) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.lideres.ProcesosLIDSQL.getSeccioNueva", mapRow);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDGenerarNumeroPedidosSeccionPeriodoDAO#updateSeccionNumeroPedido(java.util.Map)
	 */
	public void updateSeccionNumeroPedido(Map mapRow) {
		getSqlMapClientTemplate().update("spusicc.lideres.ProcesosLIDSQL.updateSeccionNumeroPedido", mapRow);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDGenerarNumeroPedidosSeccionPeriodoDAO#deleteTemporalCargaPedidosObjetivos()
	 */
	public void deleteTemporalCargaPedidosObjetivos() {
		getSqlMapClientTemplate().delete("spusicc.lideres.ProcesosLIDSQL.deleteTemporalCargaPedidosObjetivos");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDGenerarNumeroPedidosSeccionPeriodoDAO#insertTemporalCargaPedidosObjetivos(java.util.Map)
	 */
	public void insertTemporalCargaPedidosObjetivos(Map params) {
		getSqlMapClientTemplate().insert("spusicc.lideres.ProcesosLIDSQL.insertTemporalCargaPedidosObjetivos", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDGenerarNumeroPedidosSeccionPeriodoDAO#executeValidacionPedidosEstimados(java.util.Map)
	 */
	public void executeValidacionPedidosEstimados(Map params) {
		getSqlMapClientTemplate().update("spusicc.lideres.ProcesosLIDSQL.executeValidacionPedidosEstimados",params);
	}
}
