package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOFormaPagoClasificacionDAO;

/**
 * @author Gonzalo Javier Huertas Agurto
 *
 */
@Repository("spusicc.mantenimientoSTOFormaPagoClasificacionDAO")
public class MantenimientoSTOFormaPagoClasificacionDAOiBatis extends BaseDAOiBatis implements MantenimientoSTOFormaPagoClasificacionDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOFormaPagoClasificacionDAO#insertFormaPagoClasificacion(java.util.Map)
	 */
	public void insertFormaPagoClasificacion(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insertFormaPagoClasificacion",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOFormaPagoClasificacionDAO#getFormaPagoClasificacionList(java.util.Map)
	 */
	public List getFormaPagoClasificacionList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getFormaPagoClasificacionList", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOFormaPagoClasificacionDAO#deleteFormaPagoClasificacion(java.util.Map)
	 */
	public void deleteFormaPagoClasificacion(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.deleteFormaPagoClasificacion", criteria);
	}
}