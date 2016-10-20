package biz.belcorp.ssicc.dao.spusicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ProcesoPERCruceSaldoPositivoNegativoDAO;

@Repository("spusicc.procesoPERCruceSaldoPositivoNegativoDAO")
public class ProcesoPERCruceSaldoPositivoNegativoDAOiBatis extends BaseDAOiBatis
		implements ProcesoPERCruceSaldoPositivoNegativoDAO {

	public void executeCruceSaldoPositivoNegativo(Map criteria) {
		log.debug(criteria.get("codigoPais"));
		log.debug(criteria.get("numeroLote"));
		getSqlMapClientTemplate().update("spusicc.ProcesosPERSQL.procesoPERCruceSaldoPositivoNegativo", criteria);
        return;
	}

}
