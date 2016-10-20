package biz.belcorp.ssicc.dao.spusicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ProcesoPERCargoAbonoDirectoIncobrableDAO;

@Repository("spusicc.procesoPERCargoAbonoDirectoIncobrableDAO")
public class ProcesoPERCargoAbonoDirectoIncobrableDAOiBatis extends BaseDAOiBatis implements ProcesoPERCargoAbonoDirectoIncobrableDAO{

	public void executeCargoAbonoDirectoIncobrable(Map criteria) {
		log.debug(criteria.get("codigoPais"));
		getSqlMapClientTemplate().update("spusicc.ProcesosPERSQL.procesoPERCargoAbonoDirecto", criteria);
        return;
	}

}
