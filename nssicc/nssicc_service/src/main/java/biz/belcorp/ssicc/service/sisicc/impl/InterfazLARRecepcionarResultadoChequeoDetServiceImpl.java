package biz.belcorp.ssicc.service.sisicc.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazLARDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * Service de la recepcion de Resulado de Chequeo Detalle.
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
@Service("sisicc.interfazLARRecepcionarResultadoChequeoDetService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazLARRecepcionarResultadoChequeoDetServiceImpl extends	BaseInterfazEntradaAbstractService {

	@Resource(name="sisicc.interfazLARDAO")
	InterfazLARDAO interfazLARDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams) throws InterfazException {
		log.debug("Entering 'beforeReadData' method");
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());		
		map.put("usuario",interfazParams.getUsuario());
		
		try {
			interfazLARDAO.deleteInterfazLARResultadoChequeoDetalle();
			interfazLARDAO.executeInterfazLARResultadoChequeoDetalle(map);
			
		} catch (Exception e) {
			throw new InterfazException("Error al borrar/cargar los registros de la tabla temporal: "+ e.getMessage());
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
	protected void afterProcessInterfaz(InterfazParams interfazParams) throws InterfazException {
		Map map = interfazParams.getQueryParams();
		map.put("usuario", interfazParams.getUsuario());
		map.put("codigoDocumento", Constants.STO_TIPO_DOCUMENTO_OCC);
		log.debug("Entering 'afterProcessInterfaz' method");

		/* Consolida informacion y ejecuta procesos de Programas Nuevas / SSE */
		try {
			interfazLARDAO.executeResultadoChequeo(map);
		} catch (Exception e) {
			throw new InterfazException("Error al procesar procesaSolicitudConsolCabecera: "+ e.getMessage());
		}


	}	  

}