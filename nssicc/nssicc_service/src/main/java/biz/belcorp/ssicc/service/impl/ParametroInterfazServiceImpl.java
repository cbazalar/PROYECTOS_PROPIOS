/*
 * Created on 12-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.ParametroInterfazDAO;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.sisicc.model.ParametroInterfaz;
import biz.belcorp.ssicc.service.ParametroInterfazService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ParametroInterfazServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

@Service("sisicc.parametroInterfazService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ParametroInterfazServiceImpl extends BaseService implements
		ParametroInterfazService {

	@Resource(name="sisicc.parametroInterfazDAO")
	private  ParametroInterfazDAO parametroInterfazDAO;
	
	/* 
	 * @see biz.belcorp.ssicc.service.ParametroInterfazService#insertParametroInterfaz(biz.belcorp.ssicc.model.ParametroInterfaz, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertParametroInterfaz(ParametroInterfaz parametroInterfaz, Usuario usuario) {
		// TODO Auto-generated method stub
		this.parametroInterfazDAO.insertParametroInterfaz(parametroInterfaz, usuario);
	}
	/* 
	 * @see biz.belcorp.ssicc.service.ParametroInterfazService#removeParametrosByPKInterfaz(biz.belcorp.ssicc.model.InterfazPK)
	 */
	public void removeParametrosByPKInterfaz(InterfazPK interfazPK) {
		// TODO Auto-generated method stub
		this.parametroInterfazDAO.removeParametrosByPKInterfaz(interfazPK);
	}

	/* 
	 * @see biz.belcorp.ssicc.service.ParametroInterfazService#getParametrosByPKInterfaz(biz.belcorp.ssicc.model.InterfazPK)
	 */
	public List getParametrosByPKInterfaz(InterfazPK interfazPK) {
		// TODO Auto-generated method stub
		return this.parametroInterfazDAO.getParametrosByPKInterfaz(interfazPK);
	}

	/* 
	 * @see biz.belcorp.ssicc.service.ParametroInterfazService#getParametroByCriteria(biz.belcorp.ssicc.model.ParametroInterfaz)
	 */
	public ParametroInterfaz getParametroByCriteria(ParametroInterfaz criteria) {
		// TODO Auto-generated method stub
		return this.parametroInterfazDAO.getParametroByCriteria(criteria);
	}
}
