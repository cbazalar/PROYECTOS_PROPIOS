package biz.belcorp.ssicc.service.spusicc.gen.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCActualizaResumenPedidoDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * 
 * @author Sigcomt
 *
 */
@Service("spusicc.procesoGENActualizaResumenPedidoDiarioService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoGENActualizaResumenPedidoDiarioServiceImpl extends BaseInterfazProcesoAbstractService{
   
	@Resource(name = "spusicc.procesoINCActualizaResumenPedidoDAO")
    private ProcesoINCActualizaResumenPedidoDAO procesoINCActualizaResumenPedidoDAO;
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		procesoINCActualizaResumenPedidoDAO.executeActualizaResumenPedidoDiario(params);
	}

}
