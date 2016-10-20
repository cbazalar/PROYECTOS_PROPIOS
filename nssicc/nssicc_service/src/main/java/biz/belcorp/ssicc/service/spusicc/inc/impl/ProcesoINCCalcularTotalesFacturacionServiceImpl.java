package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCalcularTotalesFacturacionDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service que va a calcular el total de demanda de las ordenes de compra
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoINCCalcularTotalesFacturacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCCalcularTotalesFacturacionServiceImpl extends
	BaseInterfazProcesoAbstractService {
	          
	@Resource(name="spusicc.procesoINCCalcularTotalesFacturacionDAO")
	private ProcesoINCCalcularTotalesFacturacionDAO procesoINCCalcularTotalesFacturacionDAO;

	protected void executeStoreProcedure(Map params) {
		procesoINCCalcularTotalesFacturacionDAO.executeCalcularTotalesFacturacion(params);
	}
	
	
}
