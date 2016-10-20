package biz.belcorp.ssicc.service.edu.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.edu.ProcesoEDUActualizarParametrosCursoDAO;
import biz.belcorp.ssicc.service.edu.ProcesoEDUActualizarParametrosCursoService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *
 */
@Service("edu.procesoEDUActualizarParametrosCursoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoEDUActualizarParametrosCursoServiceImpl extends BaseService  
		implements ProcesoEDUActualizarParametrosCursoService	{
	
	@Resource(name="edu.procesoEDUActualizarParametrosCursoDAO")
	ProcesoEDUActualizarParametrosCursoDAO procesoEDUActualizarParametrosCursoDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUActualizarParametrosCursoService#executeActualizarParametrosCurso(java.util.Map)
	 */
	public void executeActualizarParametrosCurso(Map params)  {
		procesoEDUActualizarParametrosCursoDAO.executeActualizarParametrosCurso(params);
	}
	
	

		
}
