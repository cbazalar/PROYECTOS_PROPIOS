package biz.belcorp.ssicc.dao.spusicc.dto.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.dto.ProcesoDTOCalcularDescuentosDAO;

/**
 * @author peextsapaza
 *
 */
@Repository("spusicc.procesoDTOCalcularDescuentosDAO")
public class ProcesoDTOCalcularDescuentosDAOIbatis extends BaseDAOiBatis implements 
				ProcesoDTOCalcularDescuentosDAO  {

	public void executeCalcularDescuentos(Map params) {
		getSqlMapClientTemplate().update("spusicc.dto.ProcesosDTOSQL.executeCalcularDescuentos",params);
		
	}

}
