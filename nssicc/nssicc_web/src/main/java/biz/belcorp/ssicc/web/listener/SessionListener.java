package biz.belcorp.ssicc.web.listener;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Ivan Tocto Jaimes
 *
 */
public class SessionListener implements HttpSessionListener {

	protected transient final Log log = LogFactory.getLog(getClass());
	
	public SessionListener()
	{
		
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		log.debug("Sesion creada : "  + event.getSession().getId()+ " el "+ new Date());  
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		
		HttpSession session = event.getSession();  
		  
		log.debug("Sesion terminada : "  + session.getId() + " cerrando la sesion del usuario ");
		
		try {  
		  
			prepareLogoutInfoAndLogoutActiveUser(session);  
		  
		} 
		catch(Exception e) 
		{  		  
			log.error("Error mientras se esta cerrando la sesion del usuario: " + e.getMessage());
		}  
	}

	public void prepareLogoutInfoAndLogoutActiveUser(HttpSession httpSession) 
	{  
		// Solo si es necesario realizar algunas tareas antes de cerrar la sesion  
	}
}
