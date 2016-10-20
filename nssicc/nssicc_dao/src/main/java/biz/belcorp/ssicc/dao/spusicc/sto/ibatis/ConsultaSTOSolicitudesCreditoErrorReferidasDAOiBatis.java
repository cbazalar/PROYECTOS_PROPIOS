package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sto.ConsultaSTOSolicitudesCreditoErrorReferidasDAO;

/**
 * @author Dennys Oliva Iriare
 *
 */
@Repository("spusicc.consultaSTOSolicitudesCreditoErrorReferidasDAO")
public class ConsultaSTOSolicitudesCreditoErrorReferidasDAOiBatis extends BaseDAOiBatis implements ConsultaSTOSolicitudesCreditoErrorReferidasDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOExcepcionValidaDeudaDAO#getExcepcionValidaDeudaList(java.util.Map)
	 */
	public List getSolicitudesCreditoErrorReferidosList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getSolicitudesCreditoErrorReferidos", criteria);
	}

}