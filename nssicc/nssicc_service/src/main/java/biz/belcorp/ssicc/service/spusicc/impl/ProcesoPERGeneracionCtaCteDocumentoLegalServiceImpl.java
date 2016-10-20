package biz.belcorp.ssicc.service.spusicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ProcesoPERGeneracionCtaCteDocumentoLegalDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ProcesoPERGeneracionCtaCteDocumentoLegalService;

/**
 * Service que controla a la Generacion del Documento Legal
 *  
 * <p>
 * <a href="ProcesoPERGeneracionCtaCteDocumentoLegalServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Richard De los Reyes Prncipe</a>
 * 
 */
@Service("spusicc.procesoPERGeneracionCtaCteDocumentoLegalService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPERGeneracionCtaCteDocumentoLegalServiceImpl extends BaseService implements ProcesoPERGeneracionCtaCteDocumentoLegalService {

	@Resource(name="sisicc.procesoPERGeneracionCtaCteDocumentoLegalDAO")
    private ProcesoPERGeneracionCtaCteDocumentoLegalDAO generacionCtaCteDocumentoLegalDAO;

	public Map executeGeneracionCtaCteDocumentoLegal(Pais pais, Usuario usuario, String codigoInterfaz, String tipOrigenDatos) {
		return generacionCtaCteDocumentoLegalDAO.executeGeneracionCtaCteDocumentoLegal(pais, usuario, codigoInterfaz, tipOrigenDatos);
	}

}
