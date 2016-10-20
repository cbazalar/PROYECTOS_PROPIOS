package biz.belcorp.ssicc.service.spusicc.cup.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cup.ReporteCUPIngresoMetasDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cup.ReporteCUPIngresoMetasService;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.reporteCUPIngresoMetasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCUPIngresoMetasServiceImpl extends BaseService implements ReporteCUPIngresoMetasService{
	
	@Resource(name = "spusicc.reporteCUPIngresoMetasDAO")
	private ReporteCUPIngresoMetasDAO reporteCUPIngresoMetasDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.nvs.service.ReporteNVSIngresoMetasService#executeReporteNVSIngresoMetas(java.util.Map)
	 */
	public void executeReporteCUPIngresoMetas(Map params) {
		
		String estado = (String)params.get("estado");
		
		if(StringUtils.isBlank(estado))
			estado = "Todos";
		
		params.put("estado", estado);
		
		reporteCUPIngresoMetasDAO.executeReporteCUPIngresoMetas(params);
	}
	
}