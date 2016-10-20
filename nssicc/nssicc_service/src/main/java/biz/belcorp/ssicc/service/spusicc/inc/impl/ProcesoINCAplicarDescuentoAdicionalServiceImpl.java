package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCAplicarDescuentoAdicionalDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el Proceso que Aplica descuento adicional en la facturacin
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoINCAplicarDescuentoAdicionalService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCAplicarDescuentoAdicionalServiceImpl extends
	BaseInterfazProcesoAbstractService {
	  
	@Resource(name="spusicc.procesoINCAplicarDescuentoAdicionalDAO")
	private ProcesoINCAplicarDescuentoAdicionalDAO procesoINCAplicarDescuentoAdicionalDAO;

	protected void executeStoreProcedure(Map params) {
		procesoINCAplicarDescuentoAdicionalDAO.executeAplicarDescuentoAdicional(params);
	}
	

}
