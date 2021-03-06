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
 * Service para la recepcion de Servicios Postventas.
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva</a>
 */
@Service("sisicc.interfazOCRRecepcionarServiciosPostventasDetalleService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazOCRRecepcionarServiciosPostventasDetalleServiceImpl extends BaseInterfazEntradaAbstractService {
	    
	@Resource(name="sisicc.interfazOCRDAO")
	InterfazOCRDAO interfazOCRDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams) throws InterfazException {
		log.debug("Entering 'beforeReadData' method");
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());
		map.put("usuario",interfazParams.getUsuario());
		
		try {
			interfazSiCCDAO.executeInterfazOCRRecepcionarServiciosPostventasDetal(map);			
		} catch (Exception e) {
			throw new InterfazException("Error al borrar/cargar los registros de la tabla : "+ e.getMessage());
		}
	}
  
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams)	throws InterfazException {
		super.beforeProcessInterfaz(interfazParams);
		Map map = interfazParams.getQueryParams();
		map.put("codigoDocumento", Constants.STO_TIPO_DOCUMENTO_SPVC);
		try {
			interfazOCRDAO.executeConsolidadoPostVenta(map);
		} catch (Exception e) {
			throw new InterfazException("Error al procesar executeInterfazOCRProcesarConsolidadoPostventaDetalle: "	+ e.getMessage());
		}	
	}

	
}
