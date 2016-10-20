package biz.belcorp.ssicc.dao.spusicc.comision.retail.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.comision.retail.ProcesoRETAsignacionVentasRetailDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso de Asignacion
 * de Ventas Retail a Gerentes de Zona
 * <p>
 * <a href="ProcesoRETAsignacionVentasRetailDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoRETAsignacionVentasRetailDAO")
public class ProcesoRETAsignacionVentasRetailDAOiBatis extends BaseDAOiBatis
		implements ProcesoRETAsignacionVentasRetailDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.dao.ProcesoRETAsignacionVentasRetailDAO#executeAsignacionVentasRetail(java.util.Map)
	 */
	public void executeAsignacionVentasRetail(Map criteria) {
        getSqlMapClientTemplate().update("spusicc.comision.retail.procesoRETSQL.executeAsignacionVentasRetail", criteria);
 	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.dao.ProcesoRETAsignacionVentasRetailDAO#deleteVentaRetailCabecera(java.util.Map)
	 */
	public void deleteVentaRetailCabecera(Map map) {
		getSqlMapClientTemplate().update("spusicc.comision.retail.procesoRETSQL.deleteVentaRetailCabecera", map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.dao.ProcesoRETAsignacionVentasRetailDAO#deleteVentaRetailDetalle(java.util.Map)
	 */
	public void deleteVentaRetailDetalle(Map map) {
		getSqlMapClientTemplate().update("spusicc.comision.retail.procesoRETSQL.deleteVentaRetailDetalle", map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.dao.ProcesoRETAsignacionVentasRetailDAO#getExisteConsolidadoRetail(java.util.Map)
	 */
	public int getExisteConsolidadoRetail(Map map) {
		Integer cont=(Integer)getSqlMapClientTemplate().
						queryForObject("spusicc.comision.retail.procesoRETSQL.getExisteConsolidadoRetail", map);
		return cont;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.dao.ProcesoRETAsignacionVentasRetailDAO#saveConsolidadoRetail(java.util.Map)
	 */
	public void saveConsolidadoRetail(Map map) {
		getSqlMapClientTemplate().
			insert("spusicc.comision.retail.procesoRETSQL.saveConsolidadoRetail", map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.dao.ProcesoRETAsignacionVentasRetailDAO#saveVentaRetailCabec(java.util.Map)
	 */
	public void saveVentaRetailCabec(Map map) {
		getSqlMapClientTemplate().
				insert("spusicc.comision.retail.procesoRETSQL.saveVentaRetailCabec", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.dao.ProcesoRETAsignacionVentasRetailDAO#saveVentaRetailDetalle(java.util.Map)
	 */
	public void saveVentaRetailDetalle(Map map) {
		getSqlMapClientTemplate().
				insert("spusicc.comision.retail.procesoRETSQL.saveVentaRetailDetalle", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.dao.ProcesoRETAsignacionVentasRetailDAO#updateConsolidadoRetail(java.util.Map)
	 */
	public void updateConsolidadoRetail(Map map) {
		getSqlMapClientTemplate().
				update("spusicc.comision.retail.procesoRETSQL.updateConsolidadoRetail", map);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.dao.ProcesoRETAsignacionVentasRetailDAO#listaRetailCabecera(java.util.Map)
	 */
	public List listaRetailCabecera(Map map) {
		return  getSqlMapClientTemplate().
		       queryForList("spusicc.comision.retail.procesoRETSQL.listaRetailCabecera",map);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.dao.ProcesoRETAsignacionVentasRetailDAO#listaRetailDetalle(java.util.Map)
	 */
	public List listaRetailDetalle(Map map) {
	    return getSqlMapClientTemplate().
				queryForList("spusicc.comision.retail.procesoRETSQL.listaRetailDetalle",map);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.dao.ProcesoRETAsignacionVentasRetailDAO#listaRetailDetalle(java.util.Map)
	 */
	public List listaCampanaFechaProceso() {
	    return getSqlMapClientTemplate().
				queryForList("spusicc.comision.retail.procesoRETSQL.listaCampanaFechaProce",null);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.dao.ProcesoRETAsignacionVentasRetailDAO#listaRetailDetalle(java.util.Map)
	 */
	public List listaPais(Map map) {
	    return getSqlMapClientTemplate().
				queryForList("spusicc.comision.retail.procesoRETSQL.listaPais",map);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.dao.ProcesoRETAsignacionVentasRetailDAO#getExisteVentaRetailCabec(java.util.Map)
	 */
	public boolean getExisteVentaRetailCabec(Map map) {
		boolean valida=false;
		Integer cont=(Integer)getSqlMapClientTemplate().queryForObject("spusicc.comision.retail.procesoRETSQL.getExisteVentaRetailCabec", map);
		
		if(cont.intValue()==0){
			valida=true;
		}
		
		return valida;
	}
}
