package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ReporteLETLideresDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.let.ReporteLETLideresService;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.reporteLETLideresService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class) 
public class ReporteLETLideresServiceImpl extends BaseService implements ReporteLETLideresService{
	
	@Resource(name="spusicc.reporteLETLideresDAO")
	private ReporteLETLideresDAO reporteLETLideresDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ReporteLETLideresService#executeGenerarReporteLideres(java.util.Map)
	 */
	public void executeGenerarReporteLideres(Map map) {
		reporteLETLideresDAO.executeGenerarReporteLideres(map);
	}	
}