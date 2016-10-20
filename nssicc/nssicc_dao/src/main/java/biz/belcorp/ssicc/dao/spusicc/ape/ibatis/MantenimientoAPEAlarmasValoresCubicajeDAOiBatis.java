package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEAlarmasValoresCubicajeDAO;

/**
 * @author David Ramos
 */
@Repository("spusicc.mantenimientoAPEAlarmasValoresCubicajeDAO")
public class MantenimientoAPEAlarmasValoresCubicajeDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEAlarmasValoresCubicajeDAO{


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAlarmasValoresCubicajeDAO#getAlarmasValoresCubicajeList(java.util.Map)
	 */
	public List getAlarmasValoresCubicajeList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getAlarmasValoresCubicajeList", criteria);
	}

	
}