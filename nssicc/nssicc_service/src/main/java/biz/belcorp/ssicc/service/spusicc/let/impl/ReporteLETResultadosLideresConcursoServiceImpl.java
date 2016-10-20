package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ReporteLETResultadosLideresConcursoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.let.ReporteLETResultadosLideresConcursoService;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.reporteLETResultadosLideresConcursoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteLETResultadosLideresConcursoServiceImpl extends BaseService implements ReporteLETResultadosLideresConcursoService{
	
	@Resource(name="spusicc.reporteLETResultadosLideresConcursoDAO")
	private ReporteLETResultadosLideresConcursoDAO reporteLETResultadosLideresConcursoDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ReporteLETResultadosLideresConcursoService#executeGenerarReporteResultadoLider(java.util.Map)
	 */
	public void executeGenerarReporteResultadoLider(Map map) {
		reporteLETResultadosLideresConcursoDAO.executeGenerarReporteResultadoLider(map);
	}
}