package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOAprobacionMasivaDataCrediticiaDAO;

@Repository("spusicc.procesoSTOAprobacionMasivaDataCrediticiaDAO")
public class ProcesoSTOAprobacionMasivaDataCrediticiaDAOiBatis extends BaseDAOiBatis implements ProcesoSTOAprobacionMasivaDataCrediticiaDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOAprobacionMasivaDataCrediticiaDAO#getValidaSolicCodigoConsultora(java.lang.String)
	 */
	@Override
	public Integer getValidaSolicCodigoConsultora(String value) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getValidaSolicCodigoConsultora",value);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOAprobacionMasivaDataCrediticiaDAO#updateSolicCodigoConsultora(java.util.Map)
	 */
	@Override
	public void updateSolicCodigoConsultora(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.updateSolicCodigoConsultora", criteria);
		
	}

}
