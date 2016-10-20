package biz.belcorp.ssicc.service.spusicc.sto.framework;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import biz.belcorp.ssicc.service.spusicc.sto.framework.beans.DocumentoSTOParams;

/**
 * Clase Hilo para invocar procesos STO
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma Granados</a>
 * 
 */
public class ProcesoSTOHilo extends Thread  {
	
	private BaseProcesoSTOExecutorAbstractService procesoHilo;
	private DocumentoSTOParams documentoSTOParams; 
	
	
	protected transient final Log log = LogFactory.getLog(getClass());
	
	/**
	 * @param procesoHilo
	 * @param documentoSTOParams
	 */
	public ProcesoSTOHilo(BaseProcesoSTOExecutorAbstractService procesoHilo,
			DocumentoSTOParams documentoSTOParams) {
		super();
		this.procesoHilo = procesoHilo;
		this.documentoSTOParams = documentoSTOParams;
	}


	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
        try {
        	if (log.isDebugEnabled()) {
    			log.debug("Entering 'run()' method");
    		}
        	this.procesoHilo.executeHilo(documentoSTOParams);
        	if (log.isDebugEnabled()) {
    			log.debug("Endering 'run()' method");
    		}
       	
        } 
        catch( Exception e ) {
            e.printStackTrace();
        }
    }



}
