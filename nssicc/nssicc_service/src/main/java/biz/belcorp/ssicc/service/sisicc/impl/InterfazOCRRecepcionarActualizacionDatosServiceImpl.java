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
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jos A. Cairampoma Granados</a>
 */
@Service("sisicc.interfazOCRRecepcionarActualizacionDatosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazOCRRecepcionarActualizacionDatosServiceImpl extends	BaseInterfazEntradaAbstractService {

	@Resource(name="sisicc.interfazOCRDAO")
	InterfazOCRDAO interfazOCRDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams) throws InterfazException {
		
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());
		String nombre=new String();
		
		try { 
			
				interfazSiCCDAO.deleteInterfazOCRRecepcionarActualizacionDatos();
				interfazSiCCDAO.executeInterfazOCRRecepcionarActualizacionDatos(map);
			

		} catch (Exception e) {
			
				throw new InterfazException("Error al borrar/cargar los registros de la tabla temporal: "+ e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams)	throws InterfazException {
		super.beforeProcessInterfaz(interfazParams);
		Map map = interfazParams.getQueryParams();
		map.put("codigoDocumento", Constants.STO_TIPO_DOCUMENTO_SAD);
		
		String numDocumento=new String();
		try{
				interfazSiCCDAO.executeInterfazOCRProcesarConsolidadoActualizacionDatos(map);
		}catch (Exception e) {
			throw new InterfazException("Error al procesar executeInterfazOCRProcesarConsolidadoActualizacionDatos: "	+ e.getMessage());
		}
		
	}

	
}