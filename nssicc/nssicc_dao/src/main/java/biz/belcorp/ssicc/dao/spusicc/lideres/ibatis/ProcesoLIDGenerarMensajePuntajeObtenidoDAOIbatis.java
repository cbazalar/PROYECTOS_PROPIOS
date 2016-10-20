package biz.belcorp.ssicc.dao.spusicc.lideres.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDGenerarMensajePuntajeObtenidoDAO;

/**
 * @author Leonardo Lizana 
 *
 */
@Repository("spusicc.procesoLIDGenerarMensajePuntajeObtenidoDAO")
public class ProcesoLIDGenerarMensajePuntajeObtenidoDAOIbatis extends BaseDAOiBatis implements
		ProcesoLIDGenerarMensajePuntajeObtenidoDAO {

	public void executeGenerarMensajePuntajeObtenido(Map params) {
		this.getSqlMapClientTemplate().update("spusicc.lideres.ProcesosLIDSQL.executeGenerarMensajePuntajeObtenido",params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDGenerarMensajePuntajeObtenidoDAO#getPuntajeObtenidoList()
	 */
	public List getPuntajeObtenidoList() {
		return this.getSqlMapClientTemplate().queryForList("spusicc.lideres.MantenimientoLIDSQL.getPuntajeObtenidoList",null);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDGenerarMensajePuntajeObtenidoDAO#saveMensajePuntajeObtenido(java.util.Map)
	 */
	public void saveMensajePuntajeObtenido(Map params) {
		 this.getSqlMapClientTemplate().update(
	                "spusicc.lideres.MantenimientoLIDSQL.saveMensajePuntajeObtenido", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDGenerarMensajePuntajeObtenidoDAO#truncateGenerarMensajePuntajeObtenido(java.util.Map)
	 */
	public void truncateGenerarMensajePuntajeObtenido(Map params) {
		this.getSqlMapClientTemplate().update("spusicc.lideres.ProcesosLIDSQL.truncateGenerarMensajePuntajeObtenido",params);
		
	}

}
