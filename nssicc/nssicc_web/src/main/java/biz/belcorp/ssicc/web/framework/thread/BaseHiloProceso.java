package biz.belcorp.ssicc.web.framework.thread;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;

/**
 * Clase Hilo la cual sera utilizada EN LA EJECUCION DE INTERFACES
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 * 
 */
public class BaseHiloProceso extends Thread  {
	
	private BaseProcesoAbstractAction procesoHilo;
	private Map<String, Object> params;
	protected transient final Log log = LogFactory.getLog(getClass());
	
	
	/**
	 * @param interfaz
	 * @param params
	 */
	public BaseHiloProceso(BaseProcesoAbstractAction proceso, Map<String, Object> params) {
		this.procesoHilo = proceso;
		this.params = params;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
        try {
        	if (log.isDebugEnabled()) {
    			log.debug("Entering 'run()' method");
    		}
        	this.procesoHilo.executeHilo(this.params);
        	if (log.isDebugEnabled()) {
    			log.debug("Endering 'run()' method");
    		}
        	
        } 
        catch( Exception e ) {
            e.printStackTrace();
        }
    }



}
