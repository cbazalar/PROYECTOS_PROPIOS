package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCGenerarCuponesElectronicosDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el Proceso que realiza la generacion de Cupones Electronicos
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoINCGenerarCuponesElectronicosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCGenerarCuponesElectronicosServiceImpl extends
	BaseInterfazProcesoAbstractService {
	          
	@Resource(name="spusicc.procesoINCGenerarCuponesElectronicosDAO")
	private ProcesoINCGenerarCuponesElectronicosDAO procesoINCGenerarCuponesElectronicosDAO;

	protected void executeStoreProcedure(Map params) {
		procesoINCGenerarCuponesElectronicosDAO.executeGenerarCuponesElectronicos(params);
	}
	

}
