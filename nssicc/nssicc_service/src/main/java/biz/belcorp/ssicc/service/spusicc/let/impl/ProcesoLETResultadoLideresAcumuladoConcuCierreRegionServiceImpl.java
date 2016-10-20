package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETResultadoLideresAcumuladoConcuCierreRegionDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.let.ProcesoLETResultadoLideresAcumuladoConcuCierreRegionService;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.procesoLETResultadoLideresAcumuladoConcuCierreRegionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLETResultadoLideresAcumuladoConcuCierreRegionServiceImpl extends BaseService implements ProcesoLETResultadoLideresAcumuladoConcuCierreRegionService{
	
	@Resource(name="spusicc.procesoLETResultadoLideresAcumuladoConcuCierreRegionDAO")
	private ProcesoLETResultadoLideresAcumuladoConcuCierreRegionDAO procesoLETResultadoLideresAcumuladoConcuCierreRegionDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETResultadoLideresAcumuladoConcuCierreRegionService#executeProcesoLETResultadoLideresAcumuladoConcuCierreRegion(java.util.Map)
	 */
	public void executeProcesoLETResultadoLideresAcumuladoConcuCierreRegion(Map params) {
		procesoLETResultadoLideresAcumuladoConcuCierreRegionDAO.executeProcesoLETResultadoLideresAcumuladoConcuCierreRegion(params);
	}
}