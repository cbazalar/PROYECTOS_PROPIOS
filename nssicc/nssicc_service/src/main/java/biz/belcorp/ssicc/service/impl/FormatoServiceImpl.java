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

import biz.belcorp.ssicc.dao.sisicc.FormatoDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Formato;
import biz.belcorp.ssicc.service.FormatoService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="FormatoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Service("sisicc.formatoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class FormatoServiceImpl extends BaseService implements FormatoService {

	@Resource(name="sisicc.formatoDAO")	
	private FormatoDAO formatoDAO;
	
	/* 
	 * @see biz.belcorp.ssicc.service.FormatoService#getFormatos(biz.belcorp.ssicc.model.Formato)
	 */
	public List getFormatos(Formato formato) {
		return this.formatoDAO.getFormatos(formato);
	}
	
	/* 
	 * @see biz.belcorp.ssicc.service.FormatoService#getFormato(java.lang.String)
	 */
	public Formato getFormato(String codigo) {
		// TODO Auto-generated method stub
		return this.formatoDAO.getFormato(codigo);
	}
}
