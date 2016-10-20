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
 * Service para la recepcin de Productos Nacionales e Importados en la Interfaz SAM.
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */
@Service("sisicc.interfazSAMRecepcionarProductosNacionalesImportadosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSAMRecepcionarProductosNacionalesImportadosServiceImpl extends
        BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazSAMDAO")
	protected InterfazSAMDAO interfazSAMDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams)
		throws InterfazException {
		log.debug("Entering 'beforeReadData' method");
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());				
		
		try {
			interfazSAMDAO.deleteInterfazSAMRecepcionarProductosNacionalesImportados();	
			interfazSAMDAO.executeInterfazSAMRecepcionarProductosNacionalesImportados(map);									
		} 
		catch (Exception e) {			
			throw new InterfazException("Error al borrar/cargar los registros de la tabla : "+ e.getMessage());
		}
	}	
}
