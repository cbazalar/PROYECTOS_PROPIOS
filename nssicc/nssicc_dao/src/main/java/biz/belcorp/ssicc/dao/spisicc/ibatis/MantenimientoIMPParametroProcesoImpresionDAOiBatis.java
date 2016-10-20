package biz.belcorp.ssicc.dao.spisicc.ibatis;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spisicc.MantenimientoIMPParametroProcesoImpresionDAO;
import biz.belcorp.ssicc.dao.spisicc.model.ParametroProImpresion;
import org.springframework.stereotype.Repository;

/**
 * Implementacion de consultas del modulo de HiperConsulta
 * 
 * <p>
 * <a href="MantenimientoIMPParametroProcesoImpresionDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Yahir Rivas Luna</a>
 * 
 */
@Repository("imp.mantenimientoIMPParametroProcesoImpresionDAO")
public class MantenimientoIMPParametroProcesoImpresionDAOiBatis extends BaseDAOiBatis implements MantenimientoIMPParametroProcesoImpresionDAO {

	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.bas.dao.MantenimientoParametroProcesoImpresionDAO#getParametroProImp(biz.belcorp.ssicc.bas.model.ParametroProImpresion)
     */
	public List getParametroProImp(ParametroProImpresion parametroProImpresion) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"imp.MantenimientoIMPSQL.getParametroProImp", parametroProImpresion);
    	return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.bas.dao.MantenimientoParametroProcesoImpresionDAO#insertParametroProImp(biz.belcorp.ssicc.bas.model.ParametroProImpresion)
	 */
	public void insertParametroProImp(ParametroProImpresion parametroProImpresion) {
		getSqlMapClientTemplate().insert("imp.MantenimientoIMPSQL.insertParametroProImp", 
				parametroProImpresion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.bas.dao.MantenimientoParametroProcesoImpresionDAO#insertHistoricoParametroProImp(biz.belcorp.ssicc.bas.model.ParametroProImpresion)
	 */
	public void insertHistoricoParametroProImp(ParametroProImpresion parametroProImpresion) {
		getSqlMapClientTemplate().insert("imp.MantenimientoIMPSQL.insertHistoParametroProImp", 
				parametroProImpresion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.bas.dao.MantenimientoParametroProcesoImpresionDAO#updateParametroProImp(biz.belcorp.ssicc.bas.model.ParametroProImpresion)
	 */
	public void updateParametroProImp(ParametroProImpresion parametroProImpresion) {
		getSqlMapClientTemplate().update("imp.MantenimientoIMPSQL.updateParametroProImp", 
				parametroProImpresion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.bas.dao.MantenimientoParametroProcesoImpresionDAO#deleteParametroProImp(biz.belcorp.ssicc.bas.model.ParametroProImpresion)
	 */
	public void deleteParametroProImp(ParametroProImpresion parametroProImpresion) {
		getSqlMapClientTemplate().delete("imp.MantenimientoIMPSQL.deleteParametroProImp", 
				parametroProImpresion);
	}
	
}