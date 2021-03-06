package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETCalculoPedidoObjetivoSeccionCampaniaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.let.ProcesoLETCalculoPedidoObjetivoSeccionCampaniaService;

/**
 * Clase de la implementacin de la capa BO (Bussiness Object)
 * 
 * <p>
 * <a href="ProcesoLETCalculoPedidoObjetivoSeccionCampaniaServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
@Service("spusicc.procesoLETCalculoPedidoObjetivoSeccionCampaniaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLETCalculoPedidoObjetivoSeccionCampaniaServiceImpl extends BaseService implements ProcesoLETCalculoPedidoObjetivoSeccionCampaniaService {

	@Resource(name="spusicc.procesoLETCalculoPedidoObjetivoSeccionCampaniaDAO")
	private ProcesoLETCalculoPedidoObjetivoSeccionCampaniaDAO procesoLETCalculoPedidoObjetivoSeccionCampaniaDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETCalculoPedidoObjetivoSeccionCampaniaService#executeProcesoLETCalculoPedidoObjetivoSeccionCampania(java.util.Map)
	 */
	public void executeProcesoLETCalculoPedidoObjetivoSeccionCampania(Map params) {
		log.info("Entro a ProcesoLETCalculoPedidoObjetivoSeccionCampaniaServiceImpl - executeProcesoLETCalculoPedidoObjetivoSeccionCampania(java.util.Map)");
		procesoLETCalculoPedidoObjetivoSeccionCampaniaDAO.executeProcesoLETCalculoPedidoObjetivoSeccionCampania(params);
		log.info("Salio a ProcesoLETCalculoPedidoObjetivoSeccionCampaniaServiceImpl - executeProcesoLETCalculoPedidoObjetivoSeccionCampania(java.util.Map)");
	}

	
	
}
