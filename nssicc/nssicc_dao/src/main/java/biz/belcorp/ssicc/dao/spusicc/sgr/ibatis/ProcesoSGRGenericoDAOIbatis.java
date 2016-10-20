package biz.belcorp.ssicc.dao.spusicc.sgr.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sgr.ProcesoSGRGenericoDAO;

/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 *
 */
@Repository("spusicc.procesoSGRGenericoDAO")
public class ProcesoSGRGenericoDAOIbatis extends BaseDAOiBatis implements
														ProcesoSGRGenericoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.ProcesoSGRGenericoDAO#executeCancelarPolizas(java.util.Map)
	 */
	public void executeCancelarPolizas(Map map) {
		getSqlMapClientTemplate().update("spusicc.famsegura.ProcesoSGRSQL.executeCancelarPolizas",map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.ProcesoSGRGenericoDAO#executeIdentificarPolizasVigentes(java.util.Map)
	 */
	public void executeIdentificarPolizasVigentes(Map map) {
		getSqlMapClientTemplate().update("spusicc.famsegura.ProcesoSGRSQL.executeIdentificarPolizasVigentes",map);		
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.ProcesoSGRGenericoDAO#executeActualizarHistoricoCoberturaPoliza(java.util.Map)
	 */
	public void executeActualizarHistoricoCoberturaPoliza(Map params) {
		getSqlMapClientTemplate().update("spusicc.famsegura.ProcesoSGRSQL.executeActualizarHistoricoCoberturaPoliza", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.ProcesoSGRGenericoDAO#executeConsultarPolizaVigenteFFVVGP3(java.util.Map)
	 */
	public void executeConsultarPolizaVigenteFFVVGP3(Map params) {
		getSqlMapClientTemplate().update("spusicc.famsegura.ProcesoSGRSQL.executeConsultarPolizaVigenteFFVVGP3", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.ProcesoSGRGenericoDAO#executeActualizarClasificacionConsultora(java.util.Map)
	 */
	public void executeActualizarClasificacionConsultora(Map params) {
	    getSqlMapClientTemplate().update("spusicc.famsegura.ProcesoSGRSQL.executeActualizarClasificacionConsultora", params);
	}
	
}
