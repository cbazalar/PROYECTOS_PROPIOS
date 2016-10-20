package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETResultadoSeccionCampaniaCierreCampaniaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.let.ProcesoLETResultadoSeccionCampaniaCierreCampaniaService;

/**
 * Clase de la implementacin de la capa BO (Bussiness Object)
 * 
 * <p>
 * <a href="ProcesoLETResultadoSeccionCampaniaCierreCampaniaServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
@Service("spusicc.procesoLETResultadoSeccionCampaniaCierreCampaniaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLETResultadoSeccionCampaniaCierreCampaniaServiceImpl extends BaseService implements ProcesoLETResultadoSeccionCampaniaCierreCampaniaService {

	@Resource(name="spusicc.procesoLETResultadoSeccionCampaniaCierreCampaniaDAO")
	private ProcesoLETResultadoSeccionCampaniaCierreCampaniaDAO procesoLETResultadoSeccionCampaniaCierreCampaniaDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETCalculoPedidoObjetivoSeccionCampaniaService#executeProcesoLETCalculoPedidoObjetivoSeccionCampania(java.util.Map)
	 */
	public void executeProcesoLETResultadoSeccionesCampaniaCieCam(Map params) {
		log.info("Entro a ProcesoLETResultadoSeccionCampaniaCierreCampaniaServiceImpl - executeProcesoLETResultadoSeccionesCampaniaCieCam(java.util.Map)");
		procesoLETResultadoSeccionCampaniaCierreCampaniaDAO.executeProcesoLETResultadoSeccionesCampaniaCieCam(params);
		log.info("Salio a ProcesoLETResultadoSeccionCampaniaCierreCampaniaServiceImpl - executeProcesoLETResultadoSeccionesCampaniaCieCam(java.util.Map)");
	}


	
}
