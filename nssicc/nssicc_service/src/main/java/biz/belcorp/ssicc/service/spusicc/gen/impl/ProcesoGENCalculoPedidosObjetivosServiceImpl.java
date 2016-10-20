package biz.belcorp.ssicc.service.spusicc.gen.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETCalculoPedidoObjetivoSeccionCampaniaDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.procesoGENCalculoPedidosObjetivosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoGENCalculoPedidosObjetivosServiceImpl extends BaseInterfazProcesoAbstractService{
	
	@Resource(name = "spusicc.procesoLETCalculoPedidoObjetivoSeccionCampaniaDAO")
	private ProcesoLETCalculoPedidoObjetivoSeccionCampaniaDAO procesoLETCalculoPedidoObjetivoSeccionCampaniaDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params){
		procesoLETCalculoPedidoObjetivoSeccionCampaniaDAO.executeProcesoLETCalculoPedidoObjetivoSeccionCampania(params);
	}

}