 package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazFLXDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * @author itocto
 * 
 */
@Service("sisicc.interfazFLXRecepcionarVariablesModeloService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazFLXRecepcionarVariablesModeloServiceImpl extends BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazFLXDAO")
	private InterfazFLXDAO interfazFLXDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams)
	throws InterfazException {
    	if (log.isDebugEnabled())
			log.debug("Entering 'beforeReadData' method");
    	
    	Usuario usuario = (Usuario)interfazParams.getQueryParams().get("usuario");
    	
    	Map map = interfazParams.getQueryParams();
    	map.put("codigoUsuario", usuario.getLogin());
    	map.put("nombreArchivo", interfazParams.getTempName());
    	
    	log.debug("Entrando a ejecutar mi procedimiento");
    	
    	try {

    		interfazFLXDAO.executeRecepcionarVariablesModelo(map);
    			
    	} catch (Exception e) {
			throw new InterfazException(e.getMessage());
		}
    	
	}
}
