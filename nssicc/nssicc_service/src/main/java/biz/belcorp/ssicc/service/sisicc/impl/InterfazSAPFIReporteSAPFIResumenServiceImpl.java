package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazSAFDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazFormat;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;


/**
 * Service de la Interfaz Envio SAP-FI Corporativo
 * 
 * @author <a href="mailto:ghuertas@sigcomt.com">Gonzalo Huertas</a>
 */
@Service("sisicc.interfazSAPFIReporteSAPFIResumenService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSAPFIReporteSAPFIResumenServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {
	
	@Resource(name="sisicc.interfazSAFDAO")
	protected InterfazSAFDAO interfazSAFDAO; 

	public Map prepareQueryParams(InterfazParams interfazParams) throws InterfazException {
		if (log.isDebugEnabled()) {
		    log.debug("Dentro del metodo 'prepareQueryParams'");
		}
		// Obtenemos los parametros por defecto de la clase padre
		Map queryParams = super.prepareQueryParams(interfazParams);
		InterfazFormat interfazFormat = interfazFormatServiceFacade.getInterfazFormat(interfazParams.getInterfaz());
		Interfaz interfaz = interfazFormat.getInterfaz();
		String directorioResumen =interfaz.getDirectorioEntradaSalida();
		String nombreArchivoResumen=interfaz.getNombreArchivoEntradaSalida();
		String extension = ".csv";
		
		queryParams.put("directorioResumen", directorioResumen);
		queryParams.put("nombreArchivoResumen", nombreArchivoResumen+extension);
		
		return queryParams;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		log.debug("inicio InterfazSAPFIReporteSAPFICabeceraResumenImpl"	+ params);
		try {
			interfazSAFDAO.executeInterfazSAFReporteResumen(params);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}

}