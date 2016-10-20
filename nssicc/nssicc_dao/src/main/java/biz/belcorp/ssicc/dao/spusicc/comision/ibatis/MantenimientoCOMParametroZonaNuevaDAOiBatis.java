package biz.belcorp.ssicc.dao.spusicc.comision.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.comision.MantenimientoCOMParametroZonaNuevaDAO;
import biz.belcorp.ssicc.dao.spusicc.comision.model.ParametroZonaNueva;


/**
 * @author <a href="mailto:ttataje@csigcomt.com">Telly Tataje Tirado</a>
 *
 */
@Repository("spusicc.mantenimientoCOMParametroZonaNuevaDAO")
public class MantenimientoCOMParametroZonaNuevaDAOiBatis extends BaseDAOiBatis 
    implements MantenimientoCOMParametroZonaNuevaDAO {
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMParametroZonaNuevaDAO#getListaParametroZonaNueva(biz.belcorp.ssicc.spusicc.comision.dao.model.ParametroZonaNueva)
	 */
	public List getListaParametroZonaNueva(ParametroZonaNueva bean) {
		return this.getSqlMapClientTemplate().queryForList(
				"spusicc.comision.mantenimientoCOMSQL.getParametroZonaNueva",bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMParametroZonaNuevaDAO#getParametroZonaNueva(biz.belcorp.ssicc.spusicc.comision.dao.model.ParametroZonaNueva)
	 */
	public ParametroZonaNueva getParametroZonaNueva(ParametroZonaNueva bean) {
		return (ParametroZonaNueva) this.getSqlMapClientTemplate().queryForObject(
				"spusicc.comision.mantenimientoCOMSQL.getParametroZonaNueva",bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionGerenteZonaDAO#updateParametroZonaNueva(biz.belcorp.ssicc.spusicc.comision.dao.model.ParametroZonaNueva, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateParametroZonaNueva(ParametroZonaNueva bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"spusicc.comision.mantenimientoCOMSQL.updateParametroZonaNueva", bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMParametroZonaNuevaDAO#insertParametroZonaNueva(biz.belcorp.ssicc.spusicc.comision.dao.model.ParametroZonaNueva, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertParametroZonaNueva(ParametroZonaNueva bean,
			Usuario usuario) {
		this.getSqlMapClientTemplate().insert(
				"spusicc.comision.mantenimientoCOMSQL.insertParametroZonaNueva", bean);		
	}
	

}
