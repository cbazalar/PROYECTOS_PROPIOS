package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.ProcesoAPEPreasignacionPorVariacionEstimadosDAO;

/**
 * @author Christian Gonzales Komiya
 *
 */
@Repository("spusicc.procesoAPEPreasignacionPorVariacionEstimadosDAO")            
public class ProcesoAPEPreasignacionPorVariacionEstimadosDAOiBatis extends BaseDAOiBatis 
 	implements ProcesoAPEPreasignacionPorVariacionEstimadosDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarVersionesProductosDAO#getFuentePeriodoPreasignacionList()
	 */
	public List getFuentePeriodoPreasignacionList() {
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getFuentePeriodoPreasignacionList");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ProcesoAPEPreasignacionPorVariacionEstimadosDAO#executePreasignacionPorVariacionEstimados(java.util.Map)
	 */
	public void executePreasignacionPorVariacionEstimados(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.executePreasignacionPorVariacionEstimados", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ProcesoAPEPreasignacionPorVariacionEstimadosDAO#getCodigoFuncionDistribucionPorOidSublinea(java.lang.String)
	 */
	public String getCodigoFuncionDistribucionPorOidSublinea(String oidSublinea) {
		 return(String)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getCodigoFuncionDistribucionPorOidSublinea", oidSublinea);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ProcesoAPEPreasignacionPorVariacionEstimadosDAO#executePreasignacionAframe(java.util.Map)
	 */
	public void executePreasignacionAframe(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.executePreasignacionAframe", criteria);
		
	}
}