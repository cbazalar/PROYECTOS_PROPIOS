package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOParametroValidacionDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ParametroValidacion;

/**
 * Service con metodos para las consultas invocados por la pantalla de Parametro Validacion
 * 
 * <p>
 * <a href="MantenimientoSTOParametroValidacionDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Yahir Rivas Luna</a>
 * 
 */
@Repository("spusicc.mantenimientoSTOParametroValidacionDAO")
public class MantenimientoSTOParametroValidacionDAOiBatis extends BaseDAOiBatis implements MantenimientoSTOParametroValidacionDAO {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOParametroValidacionDAO#deleteParametroValidacion(biz.belcorp.ssicc.spusicc.sto.model.ParametroValidacion)
	 */
	public void deleteParametroValidacion(
			ParametroValidacion parametroValidacion) {
		getSqlMapClientTemplate().delete("sto.MantenimientoSTOSQL.deleteParametroValidacion", 
				parametroValidacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOParametroValidacionDAO#getParametroValidacion(biz.belcorp.ssicc.spusicc.sto.model.ParametroValidacion)
	 */
	public List getParametroValidacion(ParametroValidacion parametroValidacion) {

		return getSqlMapClientTemplate().queryForList(
				"sto.MantenimientoSTOSQL.getParametroValidacion", parametroValidacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOParametroValidacionDAO#insertHistoricoParametroValidacion(biz.belcorp.ssicc.spusicc.sto.model.ParametroValidacion)
	 */
	public void insertHistoricoParametroValidacion(
			ParametroValidacion parametroValidacion) {
		getSqlMapClientTemplate().insert("sto.MantenimientoSTOSQL.insertHistoParametroValidacion", 
				parametroValidacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOParametroValidacionDAO#insertParametrValidacion(biz.belcorp.ssicc.spusicc.sto.model.ParametroValidacion)
	 */
	public void insertParametrValidacion(ParametroValidacion parametroValidacion) {
		getSqlMapClientTemplate().insert("sto.MantenimientoSTOSQL.insertParametroValidacion", 
				parametroValidacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOParametroValidacionDAO#updateParametroValidacion(biz.belcorp.ssicc.spusicc.sto.model.ParametroValidacion)
	 */
	public void updateParametroValidacion(
			ParametroValidacion parametroValidacion) {
		getSqlMapClientTemplate().update("sto.MantenimientoSTOSQL.updateParametroValidacion", 
				parametroValidacion);	
	}
	
}