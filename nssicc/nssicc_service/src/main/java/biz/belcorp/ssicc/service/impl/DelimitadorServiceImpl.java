/*
 * Created on 21-nov-2005
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

import biz.belcorp.ssicc.dao.sisicc.DelimitadorDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Delimitador;
import biz.belcorp.ssicc.service.DelimitadorService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="DelimitadorServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Service("sisicc.delimitadorService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class DelimitadorServiceImpl extends BaseService implements DelimitadorService {
	
	@Resource(name="sisicc.delimitadorDAO")
	private DelimitadorDAO delimitadorDAO;
	
	/* 
	 * @see biz.belcorp.ssicc.service.DelimitadorService#getDelimitadores(biz.belcorp.ssicc.model.Delimitador)
	 */
	public List getDelimitadores(Delimitador delimitador) {
		return this.delimitadorDAO.getDelimitadores(delimitador);
	}
	
	/* 
	 * @see biz.belcorp.ssicc.service.DelimitadorService#getDelimitador(java.lang.String)
	 */
	public Delimitador getDelimitador(String codigo) {
		return this.delimitadorDAO.getDelimitador(codigo);
	}
}
