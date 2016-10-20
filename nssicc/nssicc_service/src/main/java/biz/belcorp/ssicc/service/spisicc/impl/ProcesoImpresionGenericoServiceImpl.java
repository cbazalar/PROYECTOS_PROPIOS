/*
 * Created on 20/05/2010 04:13:40 PM
 * biz.belcorp.ssicc.spisicc.service.impl.ProcesoImpresionTransporteServiceImpl
 */
package biz.belcorp.ssicc.service.spisicc.impl;

import java.io.File;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spisicc.model.ProcesoImpresion;
import biz.belcorp.ssicc.service.spisicc.framework.BaseProcesoImpresionAbstractService;

/**
 * Este proceso solo retornar los archivos de proceso como archivos de salida
 * para que simplemente se compriman y envien por FTP.
 * <p>
 * <a href="ProcesoImpresionGenericoServiceImpl.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli </a>
 */
@Service("spisicc.procesoImpresionGenericoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoImpresionGenericoServiceImpl extends BaseProcesoImpresionAbstractService {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.spisicc.service.framework.BaseProcesoImpresionAbstractService#createArchivosSalida(biz.belcorp.ssicc.model.ProcesoImpresion,
     *      java.util.Map)
     */
    protected File[] createArchivosSalida(ProcesoImpresion proceso, Map params) {
        log.debug("Dentro del mtodo 'createArchivosSalida'");
        return getArchivosProceso(proceso, params);
    }
}
