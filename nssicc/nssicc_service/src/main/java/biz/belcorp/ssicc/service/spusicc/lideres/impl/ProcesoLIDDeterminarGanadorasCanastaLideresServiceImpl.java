package biz.belcorp.ssicc.service.spusicc.lideres.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDDeterminarGanadorasCanastaLideresDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el proceso que Determina Ganadoras de Canasta de Lideres
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoLIDDeterminarGanadorasCanastaLideresService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLIDDeterminarGanadorasCanastaLideresServiceImpl extends
	BaseInterfazProcesoAbstractService {

	@Resource(name="spusicc.procesoLIDDeterminarGanadorasCanastaLideresDAO")
	private ProcesoLIDDeterminarGanadorasCanastaLideresDAO procesoLIDDeterminarGanadorasCanastaLideresDAO;

	protected void executeStoreProcedure(Map params) {
		procesoLIDDeterminarGanadorasCanastaLideresDAO.executeDeterminarGanadorasCanastaLideres(params);
	}
	

	
}