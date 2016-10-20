package biz.belcorp.ssicc.web.framework.thread;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;

/**
 * Clase Hilo la cual sera utilizada EN LA EJECUCION DE INTERFACES
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 * 
 */
public class BaseHiloInterfaz extends Thread  {
	
	private BaseInterfazAbstractAction interfazHilo;
	private Map<String, Object> params;
	protected transient final Log log = LogFactory.getLog(getClass());
	
	
	/**
	 * @param interfaz
	 * @param params
	 */
	public BaseHiloInterfaz(BaseInterfazAbstractAction interfaz, Map<String, Object> params) {
		this.interfazHilo = interfaz;
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
        	this.interfazHilo.executeHilo(this.params);
        	if (log.isDebugEnabled()) {
    			log.debug("Endering 'run()' method");
    		}
        	
        } 
        catch( Exception e ) {
            e.printStackTrace();
        }
    }



}
