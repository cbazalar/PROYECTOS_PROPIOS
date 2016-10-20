/*
 * Created on 18/05/2010 04:00:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazIMPEnviarDetallesCuentaCorrienteLaserServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spisicc.ProcesoIMPGeneracionDocumentosLaserDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 *  Service para el envio de Almacenes
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
@Service("sisicc.interfazIMPEnviarAlmacenService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazIMPEnviarAlmacenServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="spisicc.procesoIMPGeneracionDocumentosLaserDAO")
	ProcesoIMPGeneracionDocumentosLaserDAO procesoIMPGeneracionDocumentosLaserDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		procesoIMPGeneracionDocumentosLaserDAO.executeEnviarAlmacen(params);
	}
}