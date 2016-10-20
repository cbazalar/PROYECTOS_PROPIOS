package biz.belcorp.ssicc.service.spisicc.framework;

import java.util.concurrent.Callable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spisicc.model.ProcesoSpool;

/**
 * Clase Hilo para invocar procesos STO
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 * 
 */
public class ProcesoIMPSpoolHilo implements Callable<ProcesoSpool> {

	private BaseProcesoIMPSpoolHiloAbstractService procesoHilo;
	private ProcesoSpool proceso;
	protected final transient Log log = LogFactory.getLog(getClass());

	public ProcesoIMPSpoolHilo(
			BaseProcesoIMPSpoolHiloAbstractService procesoHilo, ProcesoSpool proceso) {
		this.procesoHilo = procesoHilo;
		this.proceso = proceso;
	}


	public ProcesoSpool call() throws Exception {
		try {
			procesoHilo.executeHilo(proceso);
		} catch (Exception e) {
			proceso.setSuccess(false);
			proceso.setMensajeError(e.toString());
		}
		return proceso;
		
	}


}
