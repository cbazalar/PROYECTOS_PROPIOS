package biz.belcorp.ssicc.service.spusicc.comision.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.comision.ProcesoCOMCierreProcesosCampannaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCierreProcesosCampannaService;

/**
 * @author <a href="mailto: cbazalar@sigcomt.com">Carlos Bazalar</a>
 *
 */
@Service("spusicc.procesoCOMCierreProcesosCampannaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCOMCierreProcesosCampannaServiceImpl extends BaseService implements
	ProcesoCOMCierreProcesosCampannaService {

	@Resource(name="spusicc.procesoCOMCierreProcesosCampannaDAO")
	private ProcesoCOMCierreProcesosCampannaDAO procesoCOMCierreProcesosCampannaDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.ProcesoCOMCierreProcesosCampannaService#executeCierreProcesosCampanna(java.util.Map)
	 */
	public void executeCierreProcesosCampanna(Map criteria) throws Exception {
		this.procesoCOMCierreProcesosCampannaDAO.executeCierreProcesosCampanna(criteria);
	}

	
}
