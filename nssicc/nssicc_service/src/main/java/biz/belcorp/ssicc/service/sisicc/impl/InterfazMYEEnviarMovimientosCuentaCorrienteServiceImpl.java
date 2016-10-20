/*
 * Created on 26/09/2006 11:45:40 AM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazMYEEnviarMovimientosCuentaCorrienteServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazMYEEnviarMovimientosCuentaCorrienteServiceImpl.java.html">
 * <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("sisicc.interfazMYEEnviarMovimientosCuentaCorrienteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazMYEEnviarMovimientosCuentaCorrienteServiceImpl extends
        BaseInterfazSalidaStoredProcedureAbstractService {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.sisicc.service.BaseInterfazSalidaAbstractService#prepareQueryParams(biz.belcorp.ssicc.sisicc.model.InterfazParams)
     */
    protected Map prepareQueryParams(InterfazParams interfazParams)
            throws InterfazException {
        // Invocamos al padre para obtener los parametros comunes
        Map queryParams = super.prepareQueryParams(interfazParams);

        // Aadimos el codigo del idioma
        queryParams.put("codigoIdioma", interfazParams.getUsuario().getIdioma()
                .getCodigoSiCC());
        return queryParams;
    }

	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO
				.executeInterfazMYEMovimientosCuentaCorriente(params);
	}

}
