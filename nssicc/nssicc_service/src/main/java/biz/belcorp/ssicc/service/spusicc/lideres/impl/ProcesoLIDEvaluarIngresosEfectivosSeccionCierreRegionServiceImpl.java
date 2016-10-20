package biz.belcorp.ssicc.service.spusicc.lideres.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService;

/**
 * Service que ejecutara que ejecutara los metodos del proceso de Evaluar Ingresos Efectivos de la Seccion al Cierre de Region
 *  
 * <p>
 * <a href="ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
@Service("spusicc.procesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionServiceImpl extends BaseService
		implements  ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService {
	
	@Resource(name="spusicc.procesoLIDEvaluarIngresosEfectivosSeccionCierreRegionDAO")
	ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionDAO procesoLIDEvaluarIngresosEfectivosSeccionCierreRegionDAO; 

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService#getTiposEvaluacion(java.util.Map)
	 */
	public List getTiposEvaluacion(Map criteria) {
		return procesoLIDEvaluarIngresosEfectivosSeccionCierreRegionDAO.getTiposEvaluacion(criteria);
	}
	
	/**
	 * @param params
	 * @return
	 */
	public boolean verificaRegionProcesada(Map params) {
		return procesoLIDEvaluarIngresosEfectivosSeccionCierreRegionDAO.verificaRegionProcesada(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService#executeEvaluarIngresosEfectivosSeccionCierreRegion(java.util.Map)
	 */
	public void executeEvaluarIngresosEfectivosSeccionCierreRegion(Map params) {
		procesoLIDEvaluarIngresosEfectivosSeccionCierreRegionDAO.executeEvaluarIngresosEfectivosSeccionCierreRegion(params);
		
	}



}
