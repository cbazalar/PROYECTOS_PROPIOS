/*
 * Created on 29-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.TipoDatoDAO;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="TipoDatoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Repository("sisicc.tipoDatoDAO")
public class TipoDatoDAOiBatis extends BaseDAOiBatis implements TipoDatoDAO {

	/* 
	 * @see biz.belcorp.ssicc.dao.TipoDatoDAO#getTiposDato()
	 */
	public List getTiposDato() {
		List tipos = getSqlMapClientTemplate().queryForList("sisicc.TipoDatoSQL.getTiposDato", null);
		return tipos;		
	}

}
