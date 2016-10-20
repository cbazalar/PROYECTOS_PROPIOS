/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETCalculoPedidosObjetivosSeccionCampaniaDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Proceso que realiza el calculo de pedidos Objetvos por Seccion y Campania
 * 
 * <p>
 * <a href="ProcesoLETCalculoPedidosObjetivosSeccionCampaniaServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Danny Amaro
 *         
 */
@Service("spusicc.procesoLETCalculoPedidosObjetivosSeccionCampaniaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLETCalculoPedidosObjetivosSeccionCampaniaServiceImpl extends BaseInterfazProcesoAbstractService{
	
	@Resource(name="spusicc.procesoLETCalculoPedidosObjetivosSeccionCampaniaDAO")
	private ProcesoLETCalculoPedidosObjetivosSeccionCampaniaDAO procesoLETCalculoPedidosObjetivosSeccionCampaniaDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	@Override
	protected void executeStoreProcedure(Map params) throws InterfazException,Exception {
		
		procesoLETCalculoPedidosObjetivosSeccionCampaniaDAO.executeProcesoLETCalculoPedidosObjetivosSeccionCampania(params);
		
	}
	
}
