/*
 * Created on 13-06-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.GenerarLiderDAO;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="GenerarLiderDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jamartinez@belcorp.biz">Jose Martinez</a>
 * 
 */
@Repository("sisicc.generarLiderDAO")
public class GenerarLiderDAOiBatis extends BaseDAOiBatis implements
		GenerarLiderDAO {

	public List getLideresGeneradosByCriteria(Map criteria) {
		List bloqueoLiders = getSqlMapClientTemplate().queryForList(
				"sisicc.ProcesosCOMSQL.getGenerarLidersByCriteria", criteria);
		return bloqueoLiders;
	}

	public void insertLideresGenerados(Map criteria, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"sisicc.ProcesosCOMSQL.insertGenerarLideresNuevas", criteria);
	}

}