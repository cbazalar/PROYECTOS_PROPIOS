/*
 * Created on 26/09/2006 05:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazREUEnviarDocumentosAnuladosServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazLLIDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Include class description here.
 * <p>
 * <a href="InterfazLLIEnviarVentaRealDiariaAcumuladaServiceImpl.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="sapaza@belcorp.biz">Sergio Apaza </a>
 */
/**
 * Service del Envio de Venta por Campaa de la Interfaz Leader List.
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("sisicc.interfazLLIEnviarVentaRealDiariaAcumuladaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazLLIEnviarVentaRealDiariaAcumuladaServiceImpl extends
	BaseInterfazSalidaStoredProcedureAbstractService {
	
	@Resource(name="sisicc.interfazLLIDAO")
	private InterfazLLIDAO interfazLLIDAO;
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazLLIDAO.executeInterfazLLIEnviarVentaRealDiariaAcumulada(params);
	}

}

