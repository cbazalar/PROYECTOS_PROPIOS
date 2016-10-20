package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEControlEnvioInterfacesWCSDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.ControlEnvioWCS;
import biz.belcorp.ssicc.dao.spusicc.ape.model.EstadoOlaWCS;

/**
 * @author Nicols Lpez
 *
 */
@Repository("spusicc.mantenimientoAPEControlEnvioInterfacesWCSDAO")
public class MantenimientoAPEControlEnvioInterfacesWCSDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEControlEnvioInterfacesWCSDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEControlEnvioInterfacesWCSDAO#getControlEnvioInterfacesList(java.util.Map)
	 */
	public List getControlEnvioInterfacesList(Map criteria) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getControlEnvioInterfacesList",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEControlEnvioInterfacesWCSDAO#getControlEnvioWCSObject(java.util.Map)
	 */
	public ControlEnvioWCS getControlEnvioWCSObject(Map criteria){
		return (ControlEnvioWCS)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getControlEnvioWCSObject",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEControlEnvioInterfacesWCSDAO#getValEstadoOla(java.util.Map)
	 */
	public EstadoOlaWCS getValEstadoOla(Map criteria){
		return (EstadoOlaWCS)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getValEstadoOla",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEControlEnvioInterfacesWCSDAO#updateEstadoOlasxDia(java.util.Map)
	 */
	public void updateEstadoOlasxDia(Map criteria){
		this.getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateEstadoOlasxDia",criteria);
	}
	
}
