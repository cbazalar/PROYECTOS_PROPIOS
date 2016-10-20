package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazSAMDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * Service para la interfaz de Recepcionar Stock MAV
 * 
 * @author <a href="mailto:jvelasquez@sigcomt.com">Jorge Velasquez</a>
 * 
 * @author Jorge Velasquez
 */
@Service("sisicc.interfazSAMRecepcionarStockMAVService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSAMRecepcionarStockMAVServiceImpl extends BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazSAMDAO")
	protected InterfazSAMDAO interfazSAMDAO;
	
	private String oidAlmacen;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams) throws InterfazException {
		log.debug("Entering 'beforeReadData' method");
		Map map = interfazParams.getQueryParams();
		map.put("usuario", interfazParams.getUsuario().getLogin());
		map.put("nombreArchivo", interfazParams.getTempName());
		try {
			interfazSAMDAO.deleteInterfazSAMRecepcionarStockMAV(map);
			
			oidAlmacen = interfazSAMDAO.getOidAlmacenMAV(interfazParams.getInterfaz().getCodigoPais());
			
		} catch (Exception e) {
			throw new InterfazException("Error al borrar/cargar los registros de la tabla temporal: "+ e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#processLine(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, int, java.util.Map)
	 */
	protected void processLine(InterfazParams interfazParams, int lineCount,
			Map row) throws InterfazException {
		try {
			
			row.put("oidAlmacen", oidAlmacen);
			interfazSAMDAO.insertInterfazSAMRecepcionarStockMAV(row);
		    
		} catch (Exception e) {
			throw new InterfazException("Registro Nro: " + lineCount + ". " + e.getMessage());
		}
		
	}

}
