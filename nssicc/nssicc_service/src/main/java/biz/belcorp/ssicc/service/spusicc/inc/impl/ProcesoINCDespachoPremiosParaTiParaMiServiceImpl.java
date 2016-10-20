package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCDespachoPremiosParaTiParaMiDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el Proceso que despacha premios pendientes de despacho de concursos para ti para mi.
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoINCDespachoPremiosParaTiParaMiService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCDespachoPremiosParaTiParaMiServiceImpl extends
	BaseInterfazProcesoAbstractService {
	   
	@Resource(name="spusicc.procesoINCDespachoPremiosParaTiParaMiDAO")
	private ProcesoINCDespachoPremiosParaTiParaMiDAO procesoINCDespachoPremiosParaTiParaMiDAO;

	protected void executeStoreProcedure(Map params) {
		procesoINCDespachoPremiosParaTiParaMiDAO.executeDespachoPremiosParaTiParaMi(params);
	}
	
	
}
