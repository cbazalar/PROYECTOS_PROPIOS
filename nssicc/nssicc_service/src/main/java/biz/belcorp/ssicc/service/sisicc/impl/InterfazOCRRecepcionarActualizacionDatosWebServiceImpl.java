package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazOCRDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * 
 * @author <a href="mailto:doliva@csigcomt.com">Dennys Oliva Iriarte</a>
 */
@Service("sisicc.interfazOCRRecepcionarActualizacionDatosWebService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazOCRRecepcionarActualizacionDatosWebServiceImpl extends	BaseInterfazEntradaAbstractService {

	@Resource(name="sisicc.interfazOCRDAO")
	InterfazOCRDAO interfazOCRDAO;
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams) throws InterfazException {
		
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());
		
		try { 
			interfazOCRDAO.deleteInterfazOCRRecepcionarActualizacionDatos();
			interfazOCRDAO.executeInterfazOCRRecepcionarActualizacionDatosWeb(map);
		} catch (Exception e) {
			throw new InterfazException("Error al borrar/cargar los registros de la tabla temporal: "+ e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams)	throws InterfazException {
		
		Map map = interfazParams.getQueryParams();
		map.put("usuario",interfazParams.getUsuario());
		map.put("codigoDocumento", Constants.STO_TIPO_DOCUMENTO_SAD);
		try{
				interfazOCRDAO.executeInterfazOCRProcesarConsolidadoActualizacionDatos(map);
				
				
		}catch (Exception e) {
				throw new InterfazException("Error al procesar executeInterfazOCRProcesarConsolidadoActualizacionDatosWeb: "	+ e.getMessage());
		}
		
	}

	
}