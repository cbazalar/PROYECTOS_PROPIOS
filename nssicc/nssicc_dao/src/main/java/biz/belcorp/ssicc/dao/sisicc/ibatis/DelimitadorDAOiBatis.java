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
import biz.belcorp.ssicc.dao.sisicc.DelimitadorDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Delimitador;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="DelimitadorDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Repository("sisicc.delimitadorDAO")
public class DelimitadorDAOiBatis extends BaseDAOiBatis implements
		DelimitadorDAO {

	/* 
	 * @see biz.belcorp.ssicc.dao.DelimitadorDAO#getDelimitadores(biz.belcorp.ssicc.model.Delimitador)
	 */
	public List getDelimitadores(Delimitador delimitador) {
		List delimitadores = getSqlMapClientTemplate().queryForList("sisicc.DelimitadorSQL.getDelimitadores", delimitador);
		return delimitadores;
	}

	/* 
	 * @see biz.belcorp.ssicc.dao.DelimitadorDAO#getDelimitador(java.lang.String)
	 */
	public Delimitador getDelimitador(String codigo) {
		// TODO Auto-generated method stub
		return (Delimitador)getSqlMapClientTemplate().queryForObject("sisicc.DelimitadorSQL.getDelimitador", codigo);
	}
}
