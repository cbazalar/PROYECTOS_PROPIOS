package biz.belcorp.ssicc.service.spusicc.pej.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pej.ProcesoPEJGraduacionDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el Proceso que permite calcular el Avance de Gestion
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
@Service("spusicc.procesoPEJCalcularAvanceGestionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPEJCalcularAvanceGestionServiceImpl extends BaseInterfazProcesoAbstractService {
	                                                  
	@Resource(name="spusicc.procesoPEJGraduacionDAO")
	private ProcesoPEJGraduacionDAO procesoPEJGraduacionDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) throws InterfazException, Exception {
		this.procesoPEJGraduacionDAO.executeProcesoPEJCalcularAvanceGestion(params);
	}

}
