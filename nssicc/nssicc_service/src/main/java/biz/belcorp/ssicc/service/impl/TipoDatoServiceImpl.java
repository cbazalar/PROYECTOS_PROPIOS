/*
 * Created on 29-nov-2005
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

import biz.belcorp.ssicc.dao.sisicc.TipoDatoDAO;
import biz.belcorp.ssicc.service.TipoDatoService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="TipoDatoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Service("sisicc.tipoDatoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class TipoDatoServiceImpl extends BaseService implements TipoDatoService {

	@Resource(name="sisicc.tipoDatoDAO")
	private TipoDatoDAO tipoDatoDAO;
	
	/* 
	 * @see biz.belcorp.ssicc.service.TipoDatoService#getTiposDato()
	 */
	public List getTiposDato() {
		// TODO Auto-generated method stub
		return this.tipoDatoDAO.getTiposDato();
	}

}
