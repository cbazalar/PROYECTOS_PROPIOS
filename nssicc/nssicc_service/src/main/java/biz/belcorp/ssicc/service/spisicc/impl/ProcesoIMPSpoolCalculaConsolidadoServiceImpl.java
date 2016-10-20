/*
 * Created on 19/01/2007 04:35:40 PM
 * biz.belcorp.ssicc.spisicc.service.impl.ProcesoImpresionLaserServiceImpl
 */
package biz.belcorp.ssicc.service.spisicc.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spisicc.model.ProcesoSpool;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.spisicc.framework.BaseProcesoIMPSpoolHiloAbstractService;

/**
 * <p>
 * <a href="ProcesoIMPSpoolCalculaConsolidadoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Sergio buchelli</a>
 */
@Service("spisicc.procesoIMPSpoolCalculaConsolidadoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoIMPSpoolCalculaConsolidadoServiceImpl extends BaseProcesoIMPSpoolHiloAbstractService {
	
	
	/**
	 * Metodo ejecutado antes de 'processInterfaz'. Este mtodo no tiene
	 * implementacin, su intencion es el de ser sobrescrito en caso se requiera
	 * realizar una tarea adicional antes del procesamiento de la Interfaz.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 */
	protected void beforeProcessInterfaz(InterfazParams interfazParams)	throws InterfazException {
	}
	
    /**
     * @param params
     */
    public void executeHilo(ProcesoSpool proceso){
        try {
        	procesoHiloImpresionService.executeSpoolCalculaConsolidado(proceso);
		} catch (Exception e) {
			proceso.setSuccess(false);
			proceso.setMensajeError(e.toString());
			
		}
    }

}