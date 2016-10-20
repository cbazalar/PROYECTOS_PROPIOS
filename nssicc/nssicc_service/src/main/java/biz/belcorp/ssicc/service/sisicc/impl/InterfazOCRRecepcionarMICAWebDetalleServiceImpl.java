package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazEVIDAO;
import biz.belcorp.ssicc.dao.sisicc.InterfazOCRDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * Service de la recepcion de  MICA Web - DD Detalle de la Interfaz OCR.
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
@Service("sisicc.interfazOCRRecepcionarMICAWebDetalleService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazOCRRecepcionarMICAWebDetalleServiceImpl extends BaseInterfazEntradaAbstractService {

	@Resource(name="sisicc.interfazOCRDAO")
	protected InterfazOCRDAO interfazOCRDAO;
	
	@Resource(name="sisicc.interfazEVIDAO")
	protected InterfazEVIDAO interfazEVIDAO;

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams)throws InterfazException {
		
		log.debug("Entering 'beforeReadData' method");
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());
			interfazOCRDAO.executeInterfazOCRWebDDDetalle(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams)	throws InterfazException {
		Map map = interfazParams.getQueryParams();				
		map.put("usuario",interfazParams.getUsuario());
		map.put("codigoDocumento", Constants.STO_TIPO_DOCUMENTO_OCC);
		
		log.debug("Entering 'afterProcessInterfaz' method");		
		/* Consolida informacion y ejecuta procesos de Programas Nuevas / SSE */ 
		try {
			interfazOCRDAO.executeConsolidadoMicaWeb(map);
		} catch (Exception e) {
			throw new InterfazException("Error al procesar procesaSolicitudConsolCabecera: "+ e.getMessage());
		}
		
		/* Actualiza Nro de Lote Mica */
		try {
			interfazSiCCDAO.executeActualizaNumeroLoteMica(map);
		} catch (Exception e) {
			log.error("Error en afterProcessInterfaz " + e.getMessage());			
		}
		
		/*interfazEVIDAO.updateInterfazEVIRecepcionarOCSaldoDeudor(interfazParams.getQueryParams());
        interfazEVIDAO.cargarResumenesPrefacturacion();
        interfazEVIDAO.actualizaOCSConsultorasInactivas(interfazParams.getQueryParams());*/
	}
}