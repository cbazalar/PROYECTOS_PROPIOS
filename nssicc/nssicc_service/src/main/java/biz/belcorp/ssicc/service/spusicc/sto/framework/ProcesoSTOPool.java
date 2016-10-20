package biz.belcorp.ssicc.service.spusicc.sto.framework;

import java.util.concurrent.Callable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.spusicc.sto.framework.beans.DocumentoSTOParams;

/**
 * Clase Hilo para invocar procesos STO
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 * 
 */
public class ProcesoSTOPool implements Callable<DocumentoSTOParams> {

	private BaseProcesoSTOExecutorAbstractService procesoHilo;
	private DocumentoSTOParams proceso;
	protected final transient Log log = LogFactory.getLog(getClass());

	public ProcesoSTOPool(
			BaseProcesoSTOExecutorAbstractService procesoHilo, DocumentoSTOParams proceso) {
		this.procesoHilo = procesoHilo;
		this.proceso = proceso;
	}


	public DocumentoSTOParams call() throws Exception {
		try {
			procesoHilo.executeHilo(proceso);
		} catch (Exception e) {
			proceso.getHistoricoTipoDocumento().setFlagError(Constants.SI);
			proceso.getHistoricoTipoDocumento().setDescripcionError(e.toString());
		}
		return proceso;
		
	}


}
