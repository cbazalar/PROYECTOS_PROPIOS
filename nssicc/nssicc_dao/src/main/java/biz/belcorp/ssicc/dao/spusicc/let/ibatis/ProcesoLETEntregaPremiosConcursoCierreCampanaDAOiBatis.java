package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETEntregaPremiosConcursoCierreCampanaDAO;

/**
 * @author Jesse James Rios Franco
 *
 */
@Repository("spusicc.procesoLETEntregaPremiosConcursoCierreCampanaDAO")
public class ProcesoLETEntregaPremiosConcursoCierreCampanaDAOiBatis extends BaseDAOiBatis implements ProcesoLETEntregaPremiosConcursoCierreCampanaDAO{

	public void executeProcesoLETEntregaPremiosConcursoCierreCampana(Map params) {
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeProcesoLETEntregaPremiosConcursoCierreCampana", params);
	}
}