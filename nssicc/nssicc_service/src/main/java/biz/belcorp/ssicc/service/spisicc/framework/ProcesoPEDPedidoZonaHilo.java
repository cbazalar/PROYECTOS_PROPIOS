package biz.belcorp.ssicc.service.spisicc.framework;

import java.util.concurrent.Callable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spisicc.model.ProcesoPedidoZona;

/**
 * Clase Hilo para invocar procesos STO
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 * 
 */
public class ProcesoPEDPedidoZonaHilo implements Callable<ProcesoPedidoZona> {

	private BaseProcesoPEDPedidoZonaHiloAbstractService procesoHilo;
	private ProcesoPedidoZona proceso;
	protected final transient Log log = LogFactory.getLog(getClass());

	public ProcesoPEDPedidoZonaHilo(
			BaseProcesoPEDPedidoZonaHiloAbstractService procesoHilo, ProcesoPedidoZona proceso) {
		this.procesoHilo = procesoHilo;
		this.proceso = proceso;
	}


	public ProcesoPedidoZona call() throws Exception {
		try {
			procesoHilo.executeHilo(proceso);
		} catch (Exception e) {
			proceso.setSuccess(false);
			proceso.setMensajeError(e.toString());
		}
		return proceso;
		
	}


}
