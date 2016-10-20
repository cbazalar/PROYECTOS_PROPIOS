package biz.belcorp.ssicc.service.sisicc.impl;


import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazMYEDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

@Service("sisicc.interfazMYERecepcionarPremiosWebService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazMYERecepcionarPremiosWebServiceImpl extends BaseInterfazEntradaAbstractService {

	@Resource(name="sisicc.interfazMYEDAO")
	InterfazMYEDAO interfazMYEDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams) throws InterfazException {
		
		log.debug("Entering 'beforeReadData' method");
		Map map = interfazParams.getQueryParams();
		Usuario usuario = (Usuario)interfazParams.getQueryParams().get("usuario");
		map.put("nombreArchivo", interfazParams.getTempName());
		map.put("codigousuario", usuario.getLogin());
		
	    interfazMYEDAO.executeInterfazMYERecepcionarPremiosWeb(map);

	}
}
