package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCalcularRankingReconocimientoDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el proceso que Calcula Ranking para los concursos de Reconocimiento. 
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoINCCalcularRankingReconocimientoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCCalcularRankingReconocimientoServiceImpl extends
	BaseInterfazProcesoAbstractService {

	@Resource(name="spusicc.procesoINCCalcularRankingReconocimientoDAO")
	private ProcesoINCCalcularRankingReconocimientoDAO procesoINCCalcularRankingReconocimientoDAO;
	                                                          
	protected void executeStoreProcedure(Map params) {
		procesoINCCalcularRankingReconocimientoDAO.executeCalcularRankingReconocimiento(params);
	}
	

	
}
