package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazOCRDAO;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * The Class InterfazPEDEnviarMatrizFacturacionFuturaServiceImpl.
 *
 * @author Belcorp
 * @version 1.0
 * 05:41:42 PM
 */
@Service("sisicc.interfazPEDEnviarMatrizFacturacionFuturaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPEDEnviarMatrizFacturacionFuturaServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService{
	
	@Resource(name="sisicc.interfazOCRDAO")
	private InterfazOCRDAO interfazOCRDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#prepareQueryParams(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	
	protected Map prepareQueryParams(InterfazParams interfazParams) throws InterfazException {
		
		// Obtenemos los parametros por defecto de la clase padre
		Map queryParams = super.prepareQueryParams(interfazParams);
		
		String fechaActual = DateUtil.getDateTime("yyyyMMdd", new Date());
		String codigoCentro = (String)queryParams.get("codigoCentro");
		
		
		String nombreArchivo = interfazParams.getInterfaz().getNombreArchivoEntradaSalida()+codigoCentro+"_"+fechaActual; 
		queryParams.put("nombreArchivo", nombreArchivo );
		interfazParams.getInterfaz().setNombreArchivoEntradaSalida(nombreArchivo);
		return queryParams;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		
		interfazOCRDAO.executeEnviarMatrizFacturacionFutura(params);
	}

}
