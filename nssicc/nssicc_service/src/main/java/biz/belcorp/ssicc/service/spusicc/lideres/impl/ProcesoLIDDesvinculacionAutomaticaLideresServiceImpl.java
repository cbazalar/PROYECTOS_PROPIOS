package biz.belcorp.ssicc.service.spusicc.lideres.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDDesvinculacionAutomaticaLideresDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el Proceso que que Permite desasignar una lider de una 
 * seccion cuando cumpla con ciertos criterios
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoLIDDesvinculacionAutomaticaLideresService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLIDDesvinculacionAutomaticaLideresServiceImpl extends
	BaseInterfazProcesoAbstractService {

	@Resource(name="spusicc.procesoLIDDesvinculacionAutomaticaLideresDAO")
	private ProcesoLIDDesvinculacionAutomaticaLideresDAO procesoLIDDesvinculacionAutomaticaLideresDAO;

	protected void executeStoreProcedure(Map params) {
		procesoLIDDesvinculacionAutomaticaLideresDAO.executeDesvinculacionAutomaticaLideres(params);
	}
	

	
}