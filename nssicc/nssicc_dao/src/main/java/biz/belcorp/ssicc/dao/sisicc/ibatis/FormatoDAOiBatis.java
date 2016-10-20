/*
 * Created on 21-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.FormatoDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Formato;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="FormatoDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Repository("sisicc.formatoDAO")
public class FormatoDAOiBatis extends BaseDAOiBatis implements FormatoDAO {

	/* 
	 * @see biz.belcorp.ssicc.dao.FormatoDAO#getFormatos(biz.belcorp.ssicc.model.Formato)
	 */
	public List getFormatos(Formato formato) {
		List formatos = getSqlMapClientTemplate().queryForList("sisicc.FormatoSQL.getFormatos", formato);
		return formatos;
	}

	/* 
	 * @see biz.belcorp.ssicc.dao.FormatoDAO#getFormato(java.lang.String)
	 */
	public Formato getFormato(String codigo) {
		// TODO Auto-generated method stub
		return (Formato)getSqlMapClientTemplate().queryForObject("sisicc.FormatoSQL.getFormato", codigo);
	}
}
