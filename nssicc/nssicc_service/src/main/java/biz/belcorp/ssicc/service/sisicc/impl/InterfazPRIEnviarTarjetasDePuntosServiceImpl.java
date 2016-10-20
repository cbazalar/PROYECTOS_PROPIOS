/*
 * Created on 26/09/2006 05:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazREUEnviarDocumentosAnuladosServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.scdf.TarjetaDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazPRIEnviarTarjetasDePuntosServiceImpl"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("sisicc.interfazPRIEnviarTarjetasDePuntosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPRIEnviarTarjetasDePuntosServiceImpl extends
		BaseInterfazSalidaAbstractService {

	@Resource(name="scdf.tarjetaDAO")
	TarjetaDAO tarjetaDAO;
	
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#beforeProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
     */
    protected void beforeProcessInterfaz(InterfazParams interfazParams) throws InterfazException {
        Map queryParams = interfazParams.getQueryParams();
        String codigoPais = (String)queryParams.get("codigoPais");
        tarjetaDAO.updatePeriodoTarjetaByPais(codigoPais);
    }
    
    /*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.BaseInterfazSalidaAbstractService#readData(java.util.Map)
	 */
	protected List readData(Map queryParams) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'readData' method");
		List list = null;
		try {
			String codigoPais = (String)queryParams.get("codigoPais");
			log.debug("codigoPais "+codigoPais);
			list = this.tarjetaDAO.getTarjetasMapByPais(codigoPais);
		} catch (Exception e) {
			log.error("Error al leer los datos: " + e.getMessage());
			throw new InterfazException(e.getMessage());
		}
		return list;
	}

}
