package biz.belcorp.ssicc.service.sisicc.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazMYEDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jos A. Cairampoma Granados</a>
 */
@Service("sisicc.interfazMYERecepcionarActualizacionDatosConsultoraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazMYERecepcionarActualizacionDatosConsultoraServiceImpl extends	BaseInterfazEntradaAbstractService {

	@Resource(name="sisicc.interfazMYEDAO")
	InterfazMYEDAO interfazMYEDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams) throws InterfazException {
		
		log.debug("Entering 'beforeReadData' method");
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());
		try {
			interfazMYEDAO.deleteInterfazMYERecepcionarActualizacionDatosConsultora();
			interfazMYEDAO.executeInterfazMYERecepcionarActualizacionDatosConsultora(map);
		} catch (Exception e) {
			throw new InterfazException("Error al borrar/cargar los registros de la tabla temporal: " + e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#readData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, java.io.File, java.util.List)
	 */
	protected int readData(InterfazParams interfazParams, File tempFile, List data) throws InterfazException {
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams)	throws InterfazException {
		super.beforeProcessInterfaz(interfazParams);
		Map map = interfazParams.getQueryParams();		
		
		try{
			interfazMYEDAO.executeInterfazMYEProcesarActualizacionDatosConsultora(map);
		}catch (Exception e) {
			throw new InterfazException("Error al procesar executeInterfazMYEProcesarActualizacionDatosConsultora: "	+ e.getMessage());
		}
		
	}

}