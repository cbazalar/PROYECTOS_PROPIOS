package biz.belcorp.ssicc.dao.spusicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ProcesoPERProcesarMovimientoDAO;

@Repository("spusicc.procesoPERProcesarMovimientoDAO")
public class ProcesoPERProcesarMovimientoDAOiBatis extends BaseDAOiBatis
		implements ProcesoPERProcesarMovimientoDAO {

	public void executeProcesarMovimiento(Map criteria) {
        getSqlMapClientTemplate().update("spusicc.ProcesosPERSQL.procesoPERProcesarMovimiento", criteria);
        return;
	}

}
