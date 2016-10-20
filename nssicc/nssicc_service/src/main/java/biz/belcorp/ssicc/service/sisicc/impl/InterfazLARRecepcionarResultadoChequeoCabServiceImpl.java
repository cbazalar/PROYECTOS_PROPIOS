package biz.belcorp.ssicc.service.sisicc.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazLARDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * Service de la recepcion de Resultado de Chequeo Cabecera.
 * 
 * @author <a href="">Jose Luis Rodriguez </a>
 */
@Service("sisicc.interfazLARRecepcionarResultadoChequeoCabService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazLARRecepcionarResultadoChequeoCabServiceImpl extends	BaseInterfazEntradaAbstractService {

	@Resource(name="sisicc.interfazLARDAO")
	InterfazLARDAO InterfazLARDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams) throws InterfazException {
		
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());
		
		try { InterfazLARDAO.deleteInterfazLARResultadoChequeoCabecera();
		InterfazLARDAO.executeInterfazLARResultadoChequeoCabecera(map);
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
	
		

}
		

