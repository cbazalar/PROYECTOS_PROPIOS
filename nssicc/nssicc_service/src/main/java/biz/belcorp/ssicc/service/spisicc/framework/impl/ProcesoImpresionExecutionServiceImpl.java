/*
 * Created on 18/01/2007 07:22:55 PM
 * biz.belcorp.ssicc.spisicc.service.framework.impl.ProcesoImpresionExecutionServiceImpl
 */
package biz.belcorp.ssicc.service.spisicc.framework.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spisicc.framework.BaseProcesoImpresionService;
import biz.belcorp.ssicc.service.spisicc.framework.ProcesoImpresionExecutionService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoImpresionExecutionServiceImpl.java.html"> <i>View Source </i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoImpresionExecutionServiceImpl extends BaseService implements ProcesoImpresionExecutionService {

    protected final Log log = LogFactory.getLog(getClass());

    /**
     * Map que contiene las implementaciones especificas de las Interfaces SiCC.
     */
    private Map procesosImpresionImplementations;

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.spisicc.service.framework.ProcesoImpresionExecutionService#executeProcesoImpresion(java.util.Map)
     */
    public void executeProcesoImpresion(Map params, Usuario usuario) {
        if (log.isDebugEnabled())
            log.debug("Entering 'executeProcesoImpresion' method");

        // Obtengo la Interfaz a ejecutar partir de los parametros del Map
        String codigo = (String) params.get("codigoProcesoImpresion");

        BaseProcesoImpresionService procesoImpresionImpl = getProcesoImpresionImplementation(codigo);
        procesoImpresionImpl.executeProcesoImpresion(params, usuario);
    }

    /**
     * Obtiene la implementacion especifica del Proceso de Impresion del Map de
     * implementaciones a partir del codigo.
     * 
     * @param codigo
     *            utilizado como key del Map
     * @return Implementacion especifica
     */
    private BaseProcesoImpresionService getProcesoImpresionImplementation(
            String codigo) {
        return (BaseProcesoImpresionService) procesosImpresionImplementations
                .get(codigo);
    }

    public void setProcesosImpresionImplementations(
            Map procesosImpresionImplementations) {
        this.procesosImpresionImplementations = procesosImpresionImplementations;
    }

}
