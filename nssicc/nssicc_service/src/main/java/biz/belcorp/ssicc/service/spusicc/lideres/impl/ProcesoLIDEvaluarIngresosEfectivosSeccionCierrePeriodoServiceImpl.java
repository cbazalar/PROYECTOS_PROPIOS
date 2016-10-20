package biz.belcorp.ssicc.service.spusicc.lideres.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoService;

/**
 * Service que ejecutara que ejecutara los metodos del proceso de Evaluar Ingresos Efectivos de la Seccion al Cierre de Periodo
 *  
 * <p>
 * <a href="ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
@Service("spusicc.procesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoServiceImpl extends BaseService
		implements  ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoService {
	
	@Resource(name="spusicc.procesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoDAO")
	ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoDAO procesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoDAO; 

	/**
	 * @param params
	 * @return
	 */
	public boolean verificaCampanaProcesada(Map params) {
		return procesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoDAO.verificaCampanaProcesada(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoService#executeEvaluarIngresosEfectivosSeccionCierrePeriodo(java.util.Map)
	 */
	public void executeEvaluarIngresosEfectivosSeccionCierrePeriodo(Map params) {
		procesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoDAO.executeEvaluarIngresosEfectivosSeccionCierrePeriodo(params);
		
	}


}
